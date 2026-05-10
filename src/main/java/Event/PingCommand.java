package Event;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

public class PingCommand extends ListenerAdapter {

    @Override
    public void onMessageReceived(@NotNull MessageReceivedEvent event) {
        if (event.getMessage().getContentRaw().equals("!ping")) {
            long ping = event.getJDA().getGatewayPing();
            event.getChannel().sendMessage("🏓 Pong! Latência: **" + ping + "ms**").queue();
        }
    }
}