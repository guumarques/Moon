package Parceria.Ticket;

import net.dv8tion.jda.api.components.buttons.Button;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.interaction.component.ButtonInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

public class BotaoTicketParceria extends ListenerAdapter
{
    private final CanalTicketParceria canalTicket;
    private Guild guild;

    public BotaoTicketParceria(CanalTicketParceria canalTicket)
    {
        this.canalTicket = canalTicket;
    }


    public Button botao(Guild guild)
    {
        this.guild = guild;
        return Button.secondary("abrir_ticket_parceria", "Abrir");
    }

    @Override
    public void onButtonInteraction(@NotNull ButtonInteractionEvent event)
    {
        User user = event.getUser();
        if(event.getComponentId().equals("abrir_ticket_parceria"))
        {
            canalTicket.criarCanal(guild, user, event);
        }
    }


}
