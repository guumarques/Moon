package moon.santuario.lunar;

import Parceria.Command.ParceriaComando;
import Parceria.Ticket.*;
import Seguranca.BancoImagensGolpe;
import Seguranca.MonitorGolpeAposta;
import TKiller.TKiller;
import Ticket.*;
import Topic.ChangeTopic;
import Topic.FormarNumeros;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import net.dv8tion.jda.api.interactions.commands.build.SubcommandData;
import net.dv8tion.jda.api.requests.GatewayIntent;
import io.github.cdimascio.dotenv.Dotenv;

import java.io.IOException;
import java.util.List;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main
{
    static void main() throws InterruptedException
    {
        Dotenv dotenv = Dotenv.load();
        String token = dotenv.get("BOT_TOKEN");

        if (token == null)
        {
            throw new IllegalStateException("Token não encontrado! Configure a variável de ambiente BOT_TOKEN");
        }

        JDA api = JDABuilder.createDefault(token).enableIntents(GatewayIntent.MESSAGE_CONTENT, GatewayIntent.GUILD_MEMBERS).build();
        api.awaitReady(); // espera o bot conectar completamente
        Guild guild = api.getGuildById("1223392724497993778");
        if (guild == null)
        {
            System.out.println("Guild não encontrada!");
            return;
        }

        EmbedTicket embedTicket = new EmbedTicket();
        BotaoCanalNovo botaoCanalNovo = new BotaoCanalNovo();
        CanalTicket canalTicket = new CanalTicket(embedTicket, botaoCanalNovo);
        BotaoTicket botaoTicket = new BotaoTicket(canalTicket);
        TicketDenuncia ticketDenuncia = new TicketDenuncia(embedTicket, botaoTicket);

        EmbedTicketParceria embedTicketParceria = new EmbedTicketParceria();
        BotaoCanalNovoParceria botaoCanalNovoParceria = new BotaoCanalNovoParceria();
        CanalTicketParceria canalTicketParceria = new CanalTicketParceria(embedTicketParceria, botaoCanalNovoParceria);
        BotaoTicketParceria botaoTicketParceria = new BotaoTicketParceria(canalTicketParceria);
        ParceriaTicket parceriaTicket = new ParceriaTicket(embedTicketParceria, botaoTicketParceria);
        ParceriaComando parceriaComando = new ParceriaComando(guild);

        TKiller tKillerComando = new TKiller();
        FormarNumeros formarNumeros = new FormarNumeros();
        ChangeTopic changeTopic = new ChangeTopic(guild, formarNumeros);

        List<Object> listeners = new java.util.ArrayList<>(List.of(
                ticketDenuncia,
                botaoTicket,
                botaoCanalNovo,

                parceriaTicket,
                botaoTicketParceria,
                botaoCanalNovoParceria,
                parceriaComando,

                tKillerComando,
                changeTopic
        ));

        try
        {
            BancoImagensGolpe bancoImagensGolpe = new BancoImagensGolpe("imagens");
            List<String> canaisMonitorados = List.of(
                    "1247769364262817843",
                    "1508497525089239232",
                    "1247413112777474049",
                    "1508468229046009948"
            );
            listeners.add(new MonitorGolpeAposta(canaisMonitorados, bancoImagensGolpe));
        }
        catch (IOException e)
        {
            System.out.println("Monitor de golpe de aposta desativado: " + e.getMessage());
        }

        api.addEventListener(listeners.toArray());

        guild.updateCommands().addCommands
                (
                Commands.slash("parceria", "seta uma parceria")
                        .addSubcommands(
                                new SubcommandData("setar", "seta um parceiro")
                                        .addOption(OptionType.USER, "nome", "Selecione o membro", true)
                        ),
                Commands.slash("matartesao", "mata o tesão de alguém")
                        .addOption(OptionType.USER, "usuario1", "nome do primeiro usuário", true)
                        .addOption(OptionType.USER, "usuario2", "nome do segundo usuário", true)
        ).queue();
    }
}