package Ticket;

import net.dv8tion.jda.api.components.buttons.Button;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.interaction.component.ButtonInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

public class BotaoTicket extends ListenerAdapter
{
    private final CanalTicket canalTicket;
    private Guild guild;

    public BotaoTicket(CanalTicket canalTicket)
    {
        this.canalTicket = canalTicket;
    }

    public Button botao(Guild guild)
    {
        this.guild = guild;
        return Button.secondary("abrir_ticket", "Abrir");
    }

    @Override
    public void onButtonInteraction(@NotNull ButtonInteractionEvent event)
    {
        User user = event.getUser();
        if(event.getComponentId().equals("abrir_ticket"))
        {
            canalTicket.criarCanal(guild, user, event);
        }
    }
}
