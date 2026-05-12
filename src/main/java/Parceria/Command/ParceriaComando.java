package Parceria.Command;

import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.commands.OptionMapping;
import org.jetbrains.annotations.NotNull;

public class ParceriaComando extends ListenerAdapter
{
    private final Guild guild;

    public ParceriaComando(Guild guild)
    {
        this.guild = guild;
    }

    @Override
    public void onSlashCommandInteraction(@NotNull SlashCommandInteractionEvent event)
    {
        if(event.getName().equals("parceria"))
        {
            String subcommand = event.getSubcommandName();
            Role helper = guild.getRoleById("1244410454458110092");
            Role parceria = guild.getRoleById("1227990056338194452");
            Member helperMembro = event.getMember();

            if (helper == null || parceria == null)
            {
                event.reply("Erro: Cargos de configuração não encontrados.").setEphemeral(true).queue();
                return;
            }

            if (subcommand == null)
            {
                event.reply("Por favor, insira um subcomando").setEphemeral(true).queue();
                return;
            }

            if (subcommand.equals("setar"))
            {
                if (helperMembro == null || !helperMembro.getRoles().contains(helper))
                {
                    event.reply("Você não possui o cargo de helper para usar este comando.").setEphemeral(true).queue();
                    return;
                }

                OptionMapping optionMapping = event.getOption("nome");
                if (optionMapping == null)
                {
                    event.reply("Você precisa selecionar um membro").setEphemeral(true).queue();
                    return;
                }

                Member member = optionMapping.getAsMember();
                if (member == null)
                {
                    event.reply("Membro não encontrado!").setEphemeral(true).queue();
                    return;
                }

                guild.addRoleToMember(member, parceria).queue(
                        success -> event.reply("Parceria setada com sucesso para " + member.getAsMention()).queue(),
                        error -> event.reply("Falha ao adicionar cargo: " + error.getMessage()).setEphemeral(true).queue()
                );
            }
        }
    }
}