package TicketParceria;

import Parceria.Command.ParceriaComando;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.OptionMapping;
import net.dv8tion.jda.api.requests.restaction.AuditableRestAction;
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
public class ParceriaComandoTest
{
    @Mock
    private AuditableRestAction<Void> auditableRestAction;

    @Mock
    private Member member;

    @Mock
    private OptionMapping optionMapping;

    @Mock
    private ReplyCallbackAction replyCallbackAction;

    @Mock
    private Role role;

    @Mock
    private SlashCommandInteractionEvent event;

    @Mock
    private Guild guild;

    @InjectMocks
    private ParceriaComando parceriaComando;

    @Test
    public void testaComandoParceria()
    {
        //arrange
        when(event.getName()).thenReturn("parceria");
        when(event.getSubcommandName()).thenReturn("setar");
        when(guild.getRoleById("1244410454458110092")).thenReturn(role);
        when(guild.getRoleById("1227990056338194452")).thenReturn(role);
        when(member.getRoles()).thenReturn(java.util.List.of(role));
        when(event.getMember()).thenReturn(member);
        when(event.getOption(anyString())).thenReturn(optionMapping);
        when(optionMapping.getAsMember()).thenReturn(member);
        when(guild.addRoleToMember(any(Member.class), any(Role.class))).thenReturn(auditableRestAction);

        //act
        parceriaComando.onSlashCommandInteraction(event);

        //assert
        verify(guild).addRoleToMember(member, role);
    }
}