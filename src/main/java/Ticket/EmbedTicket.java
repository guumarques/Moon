package Ticket;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.entities.User;

import java.awt.*;

public class EmbedTicket
{
    public MessageEmbed chatTicket(Guild guild)
    {
        Color mycolor = new Color(255, 255, 255);
        EmbedBuilder embedBuilder = new EmbedBuilder()
                .setTitle("Bem-vindo ao canal de denúncias!")
                .setThumbnail(guild.getIconUrl())
                .setDescription("Se você identificou alguma violação de regras, você está no lugar certo.\n\nしAntes de mais nada, por favor, verifique o canal <#1223394240319586335> para se certificar da sua denúncia.")
                .setImage("https://cdn.discordapp.com/attachments/875131727041937420/888663341680189450/image0-1-1.gif?ex=661a1d3b&is=6607a83b&hm=ee4ed0541021a372af7cad519c071597190547670b23e88ba0d8815d486526e3&")
                .setColor(mycolor);

        return embedBuilder.build();
    }

    public MessageEmbed ticketAberto(Guild guild, User user)
    {
        Color mycolor = new Color(255, 255, 255);
        EmbedBuilder embedBuilder = new EmbedBuilder()
                .setTitle("Bem-vindo ao canal de denúncias!")
                .setThumbnail(guild.getIconUrl())
                .setDescription("**" + user.getGlobalName() + "**" + ", um staff irá atendê-lo em alguns instantes."
                        + " Por favor, aguarde! \n\n し**Enquanto isso, por favor, informe sua denúncia. Siga as**"
                        + " **recomendações abaixo.**")
                .addField("<a:9174heartarrow:1225889006995112048> Violação por escrito:", "Caso tenha acontecido alguma quebra de regra em um chat "
                        + "público do servidor, favor enviar uma captura de tela e, posteriormente, um staff irá analisar.", false)
                .addField("<a:9174heartarrow:1225889006995112048> Violação por chamada de voz:", """
                        Caso tenha acontecido alguma quebra de regra em uma\
                         chamada de voz do servidor, favor anexar uma gravação de vídeo referente ao acontecimento.\
                         Caso não seja possível, favor chamar um staff.
                        
                        **Observação**: *É estritamente proibido \
                        fazer denúncias falsas ou de brincadeira. Qualquer tentativa de denúncia falsa, seja por \
                        mal-entendido ou intenção deliberada, resultará em punições. A denúncia deve ser feita \
                        com seriedade, considerando suas consequências e baseada em informações precisas e \
                        verificáveis. O descumprimento dessa regra será tratado com rigor, visando preservar a \
                        integridade e a credibilidade do sistema de denúncias*.""", false)
                .setImage("https://cdn.discordapp.com/attachments/875131727041937420/888663341680189450/image0-1-1.gif?ex=661a1d3b&is=6607a83b&hm=ee4ed0541021a372af7cad519c071597190547670b23e88ba0d8815d486526e3&")
                .setColor(mycolor);

        return embedBuilder.build();
    }

}
