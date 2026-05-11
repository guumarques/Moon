package Ticket;

import net.dv8tion.jda.api.components.MessageTopLevelComponent;
import net.dv8tion.jda.api.components.buttons.Button;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;
import net.dv8tion.jda.api.entities.channel.unions.MessageChannelUnion;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.requests.restaction.MessageCreateAction;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class TicketDenunciaTest
{
    @Mock
    private BotaoTicket botao;

    @Mock
    private MessageCreateAction messageCreateAction;

    @Mock
    private MessageEmbed messageEmbed;

    @Mock
    private EmbedTicket embedTicket;

    @Mock
    private TextChannel channel;

    @Mock
    private Guild guild;

    @Mock
    private Message message;

    @Mock
    private MessageChannelUnion messageChannelUnion;

    @Mock
    private MessageReceivedEvent event;

    @InjectMocks
    private TicketDenuncia denuncia;

    @Test
    public void testaComando() //também verifica se a embed foi enviada
    {
        //arrange - define o comportamento do mock(pensando no retorno da funcionalidade)
        when(event.getGuild()).thenReturn(guild);
        when(guild.getTextChannelById("1223416214110605374")).thenReturn(channel);
        when(event.getChannel()).thenReturn(messageChannelUnion);
        when(messageChannelUnion.getId()).thenReturn("1223416214110605374");
        when(event.getMessage()).thenReturn(message);
        when(message.getContentRaw()).thenReturn("!ticket");
        when(botao.botao(guild)).thenReturn(Button.secondary("abrir_ticket", "Abrir"));
        when(embedTicket.chatTicket(guild)).thenReturn(messageEmbed);
        when(channel.sendMessageEmbeds(messageEmbed)).thenReturn(messageCreateAction);
        when(messageCreateAction.setComponents((MessageTopLevelComponent[]) any())).thenReturn(messageCreateAction);

        //act - o que eu quero testar(no caso, seria se a embed é enviada quando digito o comando)
        denuncia.onMessageReceived(event);

        //assert - tô verificando comportamento, não resultado
        verify(embedTicket).chatTicket(guild); //verifica se o método chatTicket foi chamado
        verify(botao).botao(guild);
        verify(channel).sendMessageEmbeds(any(MessageEmbed.class));
    }
}
