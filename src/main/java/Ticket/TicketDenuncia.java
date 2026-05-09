package Ticket;

import net.dv8tion.jda.api.components.actionrow.ActionRow;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

public class TicketDenuncia extends ListenerAdapter
{
    private final EmbedTicket embed;
    private final BotaoTicket botao;
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
                denunciaCanal.sendMessageEmbeds(embed.chatTicket(guild)).setComponents(ActionRow.of(botao.botao(guild))).queue();
            }
        }
    }

    public TicketDenuncia(EmbedTicket embed, BotaoTicket botao)
    {
        this.embed = embed;
        this.botao = botao;
    }
}
