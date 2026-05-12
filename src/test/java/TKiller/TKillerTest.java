package TKiller;

import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.OptionMapping;
import net.dv8tion.jda.api.requests.restaction.interactions.ReplyCallbackAction;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TKillerTest
{
    @Mock
    private ReplyCallbackAction replyCallbackAction;

    @Mock
    private User user;

    @Mock
    private OptionMapping optionMapping;

    @Mock
    private SlashCommandInteractionEvent event;

    @InjectMocks
    private TKiller tKiller;

    @Test
    public void testaComandoTesao()
    {
        //arrange
        when(event.getName()).thenReturn("matartesao");
        when(event.getOption(anyString())).thenReturn(optionMapping);
        when(optionMapping.getAsUser()).thenReturn(user);
        when(event.replyEmbeds(any(MessageEmbed.class))).thenReturn(replyCallbackAction);

        //act
        tKiller.onSlashCommandInteraction(event);

        //assert
        assertNotNull(replyCallbackAction);
        verify(event).replyEmbeds(any(MessageEmbed.class));
    }
}
