package Topic;

import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;
import net.dv8tion.jda.api.events.guild.member.GuildMemberJoinEvent;
import net.dv8tion.jda.api.events.guild.member.GuildMemberRemoveEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ChangeTopic extends ListenerAdapter
{
    private final Guild guild;
    private final FormarNumeros formarNumeros;
    private final ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

    public ChangeTopic(Guild guild,FormarNumeros formarNumeros)
    {
        this.guild = guild;
        this.formarNumeros = formarNumeros;
    }

    @Override
    public void onGuildMemberJoin(@NotNull GuildMemberJoinEvent event)
    {
        topico();
    }

    @Override
    public void onGuildMemberRemove(@NotNull GuildMemberRemoveEvent event)
    {
        topico();
    }

    public void topico()
    {
        TextChannel channel = guild.getTextChannelById("1223664906138816703");
        int memberCount = guild.getMemberCount();

        if(channel == null)
        {
            System.out.println("Canal inexistente - TOPIC");
            return;
        }

        scheduler.schedule(() ->
        {
            System.out.println(LocalDateTime.now().format(FORMATTER) + " -> " + memberCount + " membros agora");

            String topico = formarNumeros.formarNumerosComEmojis(memberCount);
            String palavras = formarNumeros.outrosEmojis();
            channel.getManager().setTopic(palavras + topico).queue();
        }, 5, TimeUnit.MINUTES);
    }
}
