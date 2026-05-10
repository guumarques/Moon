package SlashCommands;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

import java.awt.Color;

public class AvatarCommand extends ListenerAdapter {

    @Override
    public void onSlashCommandInteraction(@NotNull SlashCommandInteractionEvent event) {
        if (!event.getName().equals("avatar")) return;

        Member member = event.getOption("usuario") != null
                ? event.getOption("usuario").getAsMember()
                : event.getMember();

        if (member == null) {
            event.reply("Não consegui encontrar o usuário!").setEphemeral(true).queue();
            return;
        }

        EmbedBuilder embed = new EmbedBuilder()
                .setTitle("Avatar de " + member.getEffectiveName())
                .setImage(member.getEffectiveAvatarUrl() + "?size=512")
                .setColor(Color.decode("#FF4160"));

        event.replyEmbeds(embed.build()).queue();
    }
}