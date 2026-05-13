package Topic;

import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;
import net.dv8tion.jda.api.managers.channel.concrete.TextChannelManager;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TopicTest
{
    @Mock
    private TextChannelManager textChannelManager;

    @Mock
    private FormarNumeros formarNumeros;

    @Mock
    private TextChannel channel;

    @Mock
    private Guild guild;

    @InjectMocks
    private ChangeTopic changeTopic;

    @Test
    public void testaMudancaTopico()
    {
        //arrange
        when(guild.getTextChannelById("1223664906138816703")).thenReturn(channel);
        when(guild.getMemberCount()).thenReturn(4000);
        when(formarNumeros.formarNumerosComEmojis(4000)).thenReturn("1\uFE0F⃣0\uFE0F⃣0\uFE0F⃣");
        when(formarNumeros.outrosEmojis()).thenReturn("👥 ");
        when(channel.getManager()).thenReturn(textChannelManager);
        when(textChannelManager.setTopic(anyString())).thenReturn(textChannelManager);

        //act
        changeTopic.topico();

        //assert
        verify(textChannelManager).setTopic(anyString());
    }
}
