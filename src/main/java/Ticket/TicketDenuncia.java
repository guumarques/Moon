package Ticket;

import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

public class TicketDenuncia extends ListenerAdapter
{
    private final EmbedTicket embed;
    @Override
    public void onMessageReceived(@NotNull MessageReceivedEvent event)
    {
        super.onMessageReceived(event);

        Guild guild = event.getGuild();
        String idDenuncia = "1228530812044050503";
        TextChannel denunciaCanal = guild.getTextChannelById(idDenuncia);

        if(!event.getChannel().getId().equals(idDenuncia))
        {
            return;
        }

        if(event.getMessage().getContentRaw().equals("!ticket"))
        {
            if(denunciaCanal != null)
            {
                embed.chatTicket(guild, denunciaCanal);
            }
        }
    }

    public TicketDenuncia(EmbedTicket embed)
    {
        this.embed = embed;
    }
}
