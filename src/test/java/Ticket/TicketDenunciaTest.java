package Ticket;

import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;
import net.dv8tion.jda.api.entities.channel.unions.MessageChannelUnion;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.requests.restaction.ChannelAction;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.*;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class TicketDenunciaTest
{
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

    @Mock
    private TextChannel textChannel;

    @Mock
    private ChannelAction<TextChannel> channelAction;

    @InjectMocks
    private TicketDenuncia denuncia;

    @Test
    public void testaComando()
    {
        //arrange - define o comportamento do mock(pensando no retorno da funcionalidade)
        when(event.getGuild()).thenReturn(guild);
        when(guild.getTextChannelById("1228530812044050503")).thenReturn(channel);
        when(event.getChannel()).thenReturn(messageChannelUnion); //retorna messagechannelunion em vez de channel haha
        when(messageChannelUnion.getId()).thenReturn("1228530812044050503");
        when(event.getMessage()).thenReturn(message);
        when(message.getContentRaw()).thenReturn("!ticket");

        //act
        denuncia.onMessageReceived(event);

        //assert - tô verificando comportamento, não resultado
        verify(embedTicket).chatTicket(guild, channel);
    }

    @Test
    public void criaTicketTest()
    {/*
         when(guild.createTextChannel("teste")).thenReturn(channelAction);
         when(channelAction.complete()).thenReturn(textChannel);

         TextChannel resultado = denuncia.criarCanal(guild, "teste");

         assertNotNull(resultado);
         verify(guild).createTextChannel(anyString());
         */
    }
}
