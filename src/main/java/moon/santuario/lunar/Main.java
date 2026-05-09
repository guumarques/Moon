package moon.santuario.lunar;

import Ticket.BotaoTicket;
import Ticket.CanalTicket;
import Ticket.EmbedTicket;
import Ticket.TicketDenuncia;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.requests.GatewayIntent;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main
{
    static void main()
    {
        JDA api = JDABuilder.createDefault("").enableIntents(GatewayIntent.MESSAGE_CONTENT).build();

        EmbedTicket embedTicket = new EmbedTicket();
        CanalTicket canalTicket = new CanalTicket(embedTicket);
        BotaoTicket botaoTicket = new BotaoTicket(canalTicket);
        TicketDenuncia ticketDenuncia = new TicketDenuncia(embedTicket, botaoTicket);

        api.addEventListener(ticketDenuncia, botaoTicket); // registra os dois separadamente
    }
}