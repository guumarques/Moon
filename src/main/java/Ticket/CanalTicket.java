package Ticket;

import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.components.actionrow.ActionRow;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.entities.channel.concrete.Category;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;
import net.dv8tion.jda.api.events.interaction.component.ButtonInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.util.EnumSet;

public class CanalTicket extends ListenerAdapter
{
    private final EmbedTicket embedTicket;
    private final BotaoCanalNovo botaoCanalNovo;

    public void criarCanal(Guild guild, User user, ButtonInteractionEvent event)
    {
        Category category = guild.getCategoryById("1223415954550034433");
        String nome = user.getName();
        Member member = event.getMember();

        event.deferReply(true).queue();
        if(category != null && member != null)
        {
            category.createTextChannel(nome)
                    .addPermissionOverride(guild.getPublicRole(), null, EnumSet.of(Permission.VIEW_CHANNEL)) // Deny @everyone
                    .addPermissionOverride(member, EnumSet.of(Permission.VIEW_CHANNEL, Permission.MESSAGE_SEND), null) // Allow specific member
                    .queue(
                    canal ->
                    {
                        event.getHook().sendMessage("Canal criado em " + canal.getAsMention()).queue();
                        mensagemCanal(canal, guild, user);
                    }
            );
        }
    }

    public void mensagemCanal(TextChannel channel, Guild guild, User user)
    {
        Role staff = guild.getRoleById("1223852657244897361");
        if(staff != null)
        {
            channel.sendMessage(staff.getAsMention()).queue();
            channel.sendMessageEmbeds(embedTicket.ticketAberto(guild, user)).setComponents(ActionRow.of(botaoCanalNovo.novoCanalBotao())).queue();
        }
    }

    public CanalTicket(EmbedTicket embedTicket, BotaoCanalNovo botaoCanalNovo)
    {
        this.embedTicket = embedTicket;
        this.botaoCanalNovo = botaoCanalNovo;
    }
}
