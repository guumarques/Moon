package moon.santuario.lunar;

import Parceria.Command.ParceriaComando;
import Parceria.Ticket.*;
import Ticket.*;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import net.dv8tion.jda.api.interactions.commands.build.SubcommandData;
import net.dv8tion.jda.api.requests.GatewayIntent;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main
{
    static void main() throws InterruptedException
    {
        String token = System.getenv("BOT_TOKEN");

        if (token == null)
        {
            throw new IllegalStateException("Token não encontrado! Configure a variável de ambiente BOT_TOKEN");
        }

        JDA api = JDABuilder.createDefault(token).enableIntents(GatewayIntent.MESSAGE_CONTENT).build();
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

        api.addEventListener(
                ticketDenuncia,
                botaoTicket,
                botaoCanalNovo,

                parceriaTicket,
                botaoTicketParceria,
                botaoCanalNovoParceria,
                parceriaComando
                );

        guild.updateCommands().addCommands
                (
                Commands.slash("parceria", "seta uma parceria")
                        .addSubcommands(
                                new SubcommandData("setar", "seta um parceiro")
                                        .addOption(OptionType.USER, "nome", "Selecione o membro", true)
                        )
        ).queue();
    }
}