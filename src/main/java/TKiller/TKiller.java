package TKiller;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.commands.OptionMapping;
import org.jetbrains.annotations.NotNull;
import java.awt.*;
import java.util.Random;

public class TKiller extends ListenerAdapter
{
    @Override
    public void onSlashCommandInteraction(@NotNull SlashCommandInteractionEvent event)
    {
        if(event.getName().equals("matartesao"))
        {

            String footerTESAOmorto = "https://media.tenor.com/AiTZSofmb8QAAAAi/girls-frontline-negev.gif";
            String footerTESAOvivo = "https://cdn3.emoji.gg/emojis/9671-mercylifeguard.gif";

            OptionMapping option1 = event.getOption("usuario1");
            OptionMapping option2 = event.getOption("usuario2");

            if(option1 == null && option2 == null)
            {
                event.reply("Você precisa selecionar dois usuários!").setEphemeral(true).queue();
                return;
            }

            User user1 = option1.getAsUser();
            User user2 = option2.getAsUser();

            Random random = new Random();
            int percentage = random.nextInt(101);

            EmbedBuilder embed = new EmbedBuilder()
                    .setTitle("Resultado de Compatibilidade do Tesão")
                    .setDescription(String.format("%s matou " + "`" + "%d%%" + "`" + " do tesão de %s", user1.getAsMention(), percentage, user2.getAsMention()))
                    .setColor(Color.decode("#f20c0c"))
                    .setThumbnail("https://media1.tenor.com/m/JFNwZU79814AAAAC/naughty-smirk.gif");

            if (percentage == 100)
            {
                String gifUrl = "https://imgur.com/L0fnt8y.gif";
                embed.setImage(gifUrl);
                embed.setColor(Color.decode("#f20c0c"));
                embed.setFooter("Tesão morto" , footerTESAOmorto);
            }
            else
            {
                embed.setFooter("Tesão ainda vivo" , footerTESAOvivo);
                embed.setColor(Color.decode("#f20c0c"));
            }

            event.replyEmbeds(embed.build()).queue();
        }
    }
}
