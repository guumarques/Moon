package Parceria.Ticket;

import net.dv8tion.jda.api.components.actionrow.ActionRow;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

public class ParceriaTicket extends ListenerAdapter
{
    private final EmbedTicketParceria embed;
    private final BotaoTicketParceria botao;

    public ParceriaTicket(EmbedTicketParceria embed, BotaoTicketParceria botao) {
        this.embed = embed;
        this.botao = botao;
    }

    @Override
    public void onMessageReceived(@NotNull MessageReceivedEvent event)
    {
        super.onMessageReceived(event);

        Guild guild = event.getGuild();
        String idDenuncia = "1227975869817950248";
        TextChannel denunciaCanal = guild.getTextChannelById(idDenuncia);

        if(!event.getChannel().getId().equals(idDenuncia))
        {
            return;
        }

        if(event.getMessage().getContentRaw().equals("!ticketparceria"))
        {
            if(denunciaCanal != null)
            {
                denunciaCanal.sendMessageEmbeds(embed.chatTicket(guild)).setComponents(ActionRow.of(botao.botao(guild))).queue();
            }
        }
    }
}
