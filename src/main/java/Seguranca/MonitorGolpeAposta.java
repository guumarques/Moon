package Seguranca;

import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

import java.io.InputStream;
import java.net.URI;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

public class MonitorGolpeAposta extends ListenerAdapter
{
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
    private static final int LIMIAR_HAMMING = 10;
    private static final String ARQUIVO_AUDITORIA = "auditoria_golpe_aposta.log";

    private final List<String> canaisMonitorados;
    private final BancoImagensGolpe bancoImagensGolpe;
    private final RegistradorAuditoria registradorAuditoria;
    private final Set<Long> mensagensProcessadas = ConcurrentHashMap.newKeySet();

    public MonitorGolpeAposta(List<String> canaisMonitorados, BancoImagensGolpe bancoImagensGolpe)
    {
        this.canaisMonitorados = canaisMonitorados;
        this.bancoImagensGolpe = bancoImagensGolpe;
        this.registradorAuditoria = new RegistradorAuditoria(ARQUIVO_AUDITORIA);
    }

    @Override
    public void onMessageReceived(@NotNull MessageReceivedEvent event)
    {
        Message mensagem = event.getMessage();

        if (mensagem.getAttachments().isEmpty())
        {
            return;
        }

        if (!canaisMonitorados.contains(event.getChannel().getId()))
        {
            return;
        }

        // o gateway do Discord pode reenviar o mesmo evento em reconexões (resume);
        // sem essa guarda, a mesma mensagem gera ban/delete e linha de auditoria duplicados
        if (!mensagensProcessadas.add(mensagem.getIdLong()))
        {
            return;
        }

        analisarMensagem(mensagem);
    }

    private void analisarMensagem(Message mensagem)
    {
        for (Message.Attachment anexo : mensagem.getAttachments())
        {
            try
            {
                // getUrl() (cdn.discordapp.com) traz o arquivo original; getProxy() (media.discordapp.net)
                // transcodifica a imagem para WEBP, formato que o ImageIO não decodifica sem lib extra
                byte[] bytes;
                try (InputStream inputStream = URI.create(anexo.getUrl()).toURL().openStream())
                {
                    bytes = inputStream.readAllBytes();
                }

                if (!MagicNumberUtil.isImagem(bytes))
                {
                    continue;
                }

                long hash = HashImagem.hashPerceptual(bytes);
                if (bancoImagensGolpe.provavelGolpe(hash, LIMIAR_HAMMING))
                {
                    registrarEPunir(mensagem, anexo);
                    return;
                }
            }
            catch (Exception e)
            {
                System.out.println(LocalDateTime.now().format(FORMATTER) + " -> Erro ao analisar anexo na mensagem " + mensagem.getId() + ": " + e.getMessage());
            }
        }
    }

    private void registrarEPunir(Message mensagem, Message.Attachment anexo)
    {
        System.out.println(LocalDateTime.now().format(FORMATTER)
                + " -> [ALERTA GOLPE APOSTA] Canal: " + mensagem.getChannel().getId()
                + " | Autor: " + mensagem.getAuthor().getEffectiveName() + " (" + mensagem.getAuthor().getId() + ")"
                + " | Mensagem: " + mensagem.getId()
                + " | Anexo: " + anexo.getFileName());

        registradorAuditoria.registrar(
                mensagem.getAuthor().getEffectiveName(),
                mensagem.getAuthor().getId(),
                mensagem.getChannel().getId(),
                mensagem.getId()
        );

        mensagem.delete().queue(
                sucesso -> {},
                erro -> System.out.println(LocalDateTime.now().format(FORMATTER)
                        + " -> Erro ao apagar mensagem " + mensagem.getId() + ": " + erro.getMessage())
        );

        mensagem.getGuild().ban(mensagem.getAuthor(), 0, TimeUnit.DAYS)
                .reason("Golpe de aposta detectado - imagem correspondente a padrão conhecido")
                .queue(
                        sucesso -> {},
                        erro -> System.out.println(LocalDateTime.now().format(FORMATTER)
                                + " -> Erro ao banir usuário " + mensagem.getAuthor().getId() + ": " + erro.getMessage())
                );
    }
}
