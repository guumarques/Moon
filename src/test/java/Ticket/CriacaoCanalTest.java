package Ticket;

import net.dv8tion.jda.api.entities.*;
import net.dv8tion.jda.api.entities.channel.concrete.Category;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;
import net.dv8tion.jda.api.events.interaction.component.ButtonInteractionEvent;
import net.dv8tion.jda.api.interactions.InteractionHook;
import net.dv8tion.jda.api.requests.restaction.ChannelAction;
import net.dv8tion.jda.api.requests.restaction.MessageCreateAction;
import net.dv8tion.jda.api.requests.restaction.WebhookMessageCreateAction;
import net.dv8tion.jda.api.requests.restaction.interactions.ReplyCallbackAction;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.function.Consumer;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CriacaoCanalTest
{
    @Mock
    private EmbedTicket embedTicket;

    @Mock
    private Role publicRole;

    @Mock
    private WebhookMessageCreateAction<Message> webhookMessageCreateAction;

    @Mock
    private MessageCreateAction messageCreateAction;

    @Mock
    private Category category;

    @Mock
    private ReplyCallbackAction replyCallbackAction;

    @Mock
    private ChannelAction<TextChannel> channelAction;

    @Mock
    private TextChannel channel;

    @Mock
    private InteractionHook hook;

    @Mock
    private Member member;

    @Mock
    private User user;

    @Mock
    private ButtonInteractionEvent eventButton;

    @Mock
    private Guild guild;

    @InjectMocks
    private CanalTicket canalTicket;

    @Test
    public void criaCanalPeloBotao()
    {
        //arrange
        when(eventButton.getMember()).thenReturn(member);
        when(eventButton.deferReply(true)).thenReturn(replyCallbackAction);
        when(guild.getCategoryById("1223415954550034433")).thenReturn(category);
        when(guild.getPublicRole()).thenReturn(publicRole); // adiciona esse
        when(user.getName()).thenReturn("_ayanokoji");
        when(category.createTextChannel(anyString())).thenReturn(channelAction);
        when(channelAction.addPermissionOverride(any(), isNull(), any())).thenReturn(channelAction);
        when(channelAction.addPermissionOverride(any(), any(), isNull())).thenReturn(channelAction);
        doAnswer(invocation -> {
            Consumer<TextChannel> callback = invocation.getArgument(0);
            callback.accept(channel);
            return null;
        }).when(channelAction).queue(any());
        when(eventButton.getHook()).thenReturn(hook);
        when(hook.sendMessage(anyString())).thenReturn(webhookMessageCreateAction);
        when(embedTicket.ticketAberto(any(Guild.class), any(User.class))).thenReturn(mock(MessageEmbed.class));
        when(channel.sendMessageEmbeds(any(MessageEmbed.class))).thenReturn(messageCreateAction);

        //act
        canalTicket.criarCanal(guild, user, eventButton);

        //assert
        verify(category).createTextChannel(anyString());  //verifica se cria o canal
        verify(hook).sendMessage(anyString()); //verifica se mostra a mensagem efêmera
        verify(embedTicket).ticketAberto(any(Guild.class), any(User.class)); //verifica se a embed é enviada
    }
}
