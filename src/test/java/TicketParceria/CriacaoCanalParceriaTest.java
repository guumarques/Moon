package TicketParceria;

import Parceria.Ticket.BotaoCanalNovoParceria;
import Parceria.Ticket.CanalTicketParceria;
import Parceria.Ticket.EmbedTicketParceria;
import net.dv8tion.jda.api.components.MessageTopLevelComponent;
import net.dv8tion.jda.api.components.buttons.Button;
import net.dv8tion.jda.api.entities.*;
import net.dv8tion.jda.api.entities.channel.concrete.Category;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;
import net.dv8tion.jda.api.events.interaction.component.ButtonInteractionEvent;
import net.dv8tion.jda.api.requests.restaction.ChannelAction;
import net.dv8tion.jda.api.requests.restaction.MessageCreateAction;
import net.dv8tion.jda.api.requests.restaction.interactions.ReplyCallbackAction;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CriacaoCanalParceriaTest
{
    @Mock
    private MessageEmbed messageEmbed;

    @Mock
    private BotaoCanalNovoParceria botaoCanalNovo;

    @Mock
    private EmbedTicketParceria embedTicket;

    @Mock
    private MessageCreateAction messageCreateAction;

    @Mock
    private Category category;

    @Mock
    private ReplyCallbackAction replyCallbackAction;

    @Mock
    private ChannelAction<TextChannel> channelAction;

    @Mock
    private Role role;

    @Mock
    private IMentionable iMentionable;

    @Mock
    private TextChannel channel;

    @Mock
    private Member member;

    @Mock
    private User user;

    @Mock
    private ButtonInteractionEvent eventButton;

    @Mock
    private Guild guild;

    @InjectMocks
    private CanalTicketParceria canalTicket;

    @Test
    public void criaCanalPeloBotao()
    {
        //arrange
        when(guild.getCategoryById("1227975047029723247")).thenReturn(category);
        when(user.getName()).thenReturn("_ayanokoji");
        when(eventButton.getMember()).thenReturn(member);
        when(eventButton.deferReply(true)).thenReturn(replyCallbackAction);
        when(category.createTextChannel(anyString())).thenReturn(channelAction);
        when(channelAction.addPermissionOverride(any(), isNull(), any())).thenReturn(channelAction);
        when(channelAction.addPermissionOverride(any(), any(), isNull())).thenReturn(channelAction);

        //act
        canalTicket.criarCanal(guild, user, eventButton);

        //assert
        verify(category).createTextChannel(anyString());  //verifica se cria o canal
    }

    @Test
    public void deveMensagemCanalEnviarEmbedEBotao()
    {
        when(guild.getRoleById("1244410454458110092")).thenReturn(role);
        when(channel.sendMessage(iMentionable.getAsMention())).thenReturn(messageCreateAction);
        when(embedTicket.ticketAberto(eq(guild), eq(user) )).thenReturn(messageEmbed);
        when(botaoCanalNovo.novoCanalBotao()).thenReturn((Button.secondary("fechar_ticket_parceria", "Fechar")));
        when(channel.sendMessageEmbeds(any(MessageEmbed.class))).thenReturn(messageCreateAction);
        when(messageCreateAction.setComponents((MessageTopLevelComponent[]) any())).thenReturn(messageCreateAction);

        canalTicket.mensagemCanal(channel, guild, user);

        verify(embedTicket).ticketAberto(guild, user);
        verify(botaoCanalNovo).novoCanalBotao();
        verify(channel).sendMessageEmbeds(any(MessageEmbed.class));
    }
}
