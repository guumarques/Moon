package Ticket;

import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.events.interaction.component.ButtonInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class CanalTicket extends ListenerAdapter
{
    public void criarCanal(Guild guild, String nome, ButtonInteractionEvent event)
    {
        event.deferReply().queue();
        guild.createTextChannel(nome).queue(
                canal ->
                {
                    event.getHook().sendMessage("Canal criado em " + canal.getAsMention()).queue();
                }
        );
    }
}
