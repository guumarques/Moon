package TicketParceria;

import Parceria.Ticket.BotaoTicketParceria;
import Parceria.Ticket.EmbedTicketParceria;
import Parceria.Ticket.ParceriaTicket;
import net.dv8tion.jda.api.components.MessageTopLevelComponent;
import net.dv8tion.jda.api.components.buttons.Button;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;
import net.dv8tion.jda.api.entities.channel.unions.MessageChannelUnion;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.requests.restaction.MessageCreateAction;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ParceriaTicketTest
{
    @Mock
    private BotaoTicketParceria botao;

    @Mock
    private MessageCreateAction messageCreateAction;

    @Mock
    private MessageEmbed messageEmbed;

    @Mock
    private EmbedTicketParceria embedTicket;

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
    private ParceriaTicket parceria;

    @Test
    public void testaComando() //também verifica se a embed foi enviada
    {
        //arrange - define o comportamento do mock(pensando no retorno da funcionalidade)
        when(event.getGuild()).thenReturn(guild);
        when(guild.getTextChannelById("1227975869817950248")).thenReturn(channel);
        when(event.getChannel()).thenReturn(messageChannelUnion);
        when(messageChannelUnion.getId()).thenReturn("1227975869817950248");
        when(event.getMessage()).thenReturn(message);
        when(message.getContentRaw()).thenReturn("!ticketparceria");
        when(botao.botao(guild)).thenReturn(Button.secondary("abrir_ticket_parceria", "Abrir"));
        when(embedTicket.chatTicket(guild)).thenReturn(messageEmbed);
        when(channel.sendMessageEmbeds(messageEmbed)).thenReturn(messageCreateAction);
        when(messageCreateAction.setComponents((MessageTopLevelComponent[]) any())).thenReturn(messageCreateAction);

        //act - o que eu quero testar(no caso, seria se a embed é enviada quando digito o comando)
        parceria.onMessageReceived(event);

        //assert - tô verificando comportamento, não resultado
        verify(embedTicket).chatTicket(guild); //verifica se o método chatTicket foi chamado
        verify(botao).botao(guild);
        verify(channel).sendMessageEmbeds(any(MessageEmbed.class));
    }
}
