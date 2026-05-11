package moon.santuario.lunar;

import Ticket.*;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.requests.GatewayIntent;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main
{
    static void main() throws InterruptedException
    {
        String guild = "1223392724497993778";
        String token = System.getenv("BOT_TOKEN");

        if (token == null) {
            throw new IllegalStateException("Token não encontrado! Configure a variável de ambiente BOT_TOKEN");
        }

        JDA api = JDABuilder.createDefault(token).enableIntents(GatewayIntent.MESSAGE_CONTENT)
                .build();

        EmbedTicket embedTicket = new EmbedTicket();
        BotaoCanalNovo botaoCanalNovo = new BotaoCanalNovo();
        CanalTicket canalTicket = new CanalTicket(embedTicket, botaoCanalNovo);
        BotaoTicket botaoTicket = new BotaoTicket(canalTicket);
        TicketDenuncia ticketDenuncia = new TicketDenuncia(embedTicket, botaoTicket);

        api.addEventListener(
                ticketDenuncia,
                botaoTicket,
                botaoCanalNovo);

        api.awaitReady(); // espera o bot conectar completamente
        api.getGuildById(guild).updateCommands().queue();

    }
}