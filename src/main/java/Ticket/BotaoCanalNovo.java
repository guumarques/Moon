package Ticket;

import net.dv8tion.jda.api.components.buttons.Button;
import net.dv8tion.jda.api.events.interaction.component.ButtonInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;


public class BotaoCanalNovo extends ListenerAdapter
{
    public Button novoCanalBotao()
    {
        return Button.secondary("fechar_ticket", "Fechar");
    }

    @Override
    public void onButtonInteraction(@NotNull ButtonInteractionEvent event)
    {
        if(event.getComponentId().equals("fechar_ticket"))
        {
            event.reply("").queue(reply ->
            {
                final int[] segundos = {5};
                ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();
                scheduler.scheduleAtFixedRate(() ->
                {
                    if(segundos[0] == 0)
                    {
                        scheduler.shutdown();
                        event.getChannel().delete().queue();
                    }
                    else if(segundos[0] > 1 && segundos[0] <= 5)
                    {
                        reply.editOriginal("Ticket fechando em " + segundos[0] + " segundos").queue();
                        segundos[0]--;
                    }
                    else
                    {
                        reply.editOriginal("Ticket fechando em " + segundos[0] + " segundo").queue();
                        segundos[0]--;
                    }
                }, 1, 1, TimeUnit.SECONDS);
            });
        }
    }
}
