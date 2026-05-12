package TicketParceria;

import Parceria.Ticket.BotaoTicketParceria;
import Parceria.Ticket.CanalTicketParceria;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.interaction.component.ButtonInteractionEvent;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class BotaoTicketParceriaTest
{
    @InjectMocks
    private BotaoTicketParceria botaoTicket;

    @Mock
    private User user;

    @Mock
    private ButtonInteractionEvent eventButton;

    @Mock
    private CanalTicketParceria canalTicket;

    @Test
    public void deveChamarCriarCanalQuandoBotaoForClicado()
    {
        //arrange
        when(eventButton.getComponentId()).thenReturn("abrir_ticket_parceria");
        when(eventButton.getUser()).thenReturn(user);

        //act
        botaoTicket.onButtonInteraction(eventButton);

        //assert
        verify(canalTicket).criarCanal(any(), any(), any());
    }
}
