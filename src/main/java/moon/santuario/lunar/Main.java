package moon.santuario.lunar;

import SlashCommands.AvatarCommand;
import SlashCommands.PingCommand;
import Ticket.BotaoTicket;
import Ticket.CanalTicket;
import Ticket.EmbedTicket;
import Ticket.TicketDenuncia;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import net.dv8tion.jda.api.requests.GatewayIntent;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main
{
    static void main() throws InterruptedException {
        String token = System.getenv("BOT_TOKEN");

        if (token == null) {
            throw new IllegalStateException("Token não encontrado! Configure a variável de ambiente BOT_TOKEN");
        }

        JDA api = JDABuilder.createDefault(token).enableIntents(GatewayIntent.MESSAGE_CONTENT)
                .build();

        EmbedTicket embedTicket = new EmbedTicket();
        CanalTicket canalTicket = new CanalTicket(embedTicket);
        BotaoTicket botaoTicket = new BotaoTicket(canalTicket);
        TicketDenuncia ticketDenuncia = new TicketDenuncia(embedTicket, botaoTicket);
        PingCommand pingCommand = new PingCommand();
        AvatarCommand avatarCommand = new AvatarCommand();

        api.addEventListener(
                ticketDenuncia,
                botaoTicket, pingCommand,
                avatarCommand); // registra os dois separadamente

        // registra o slash command
        api.awaitReady(); // espera o bot conectar completamente

        api.getGuildById("1223392724497993778")
                .updateCommands()
                .addCommands(
                        Commands.slash("ping", "Verifica a latência do bot"),
                        Commands.slash("avatar", "Mostra o avatar de um usuário")
                                .addOption(OptionType.USER, "usuario", "Usuário que deseja ver o avatar", false)
                )
                .queue();
    }
}