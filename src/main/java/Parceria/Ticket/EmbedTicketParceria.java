package Parceria.Ticket;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import java.awt.*;

public class EmbedTicketParceria extends ListenerAdapter
{
    public MessageEmbed chatTicket(Guild guild)
    {
        // Criar embed para a mensagem de boas-vindas
        Color minhacor = new Color(255, 255, 255);
        EmbedBuilder embedBuilder = new EmbedBuilder()
                .setTitle("📢 Bem-vindo ao canal de parcerias!")
                .setThumbnail(guild.getIconUrl())
                .setDescription("Olá, pessoal! \n\n"
                        + "Nosso servidor está em busca de novas parcerias mutuamente benéficas. "
                        + "Se você administra um servidor e deseja crescer junto conosco, confira os requisitos abaixo!")

                // Seção de Requisitos
                .addField("<a:almdecorbowpink:1223409342376706139> **Requisitos:**", "\n"
                        + "  <a:Love:1503575713897517138> Mínimo de **2000 membros** no servidor \n"
                        + "  <a:Love:1503575713897517138> Atividade regular e respeito às regras \n"
                        + "  <a:Love:1503575713897517138> Conteúdo adequado às diretrizes da comunidade", false)

                // Seção de Benefícios
                .addField("<a:almdecorbowpink:1223409342376706139> **Benefícios:**", "\n"
                        + "  <a:Love:1503575713897517138> Divulgação mútua nos canais de anúncio \n"
                        + "  <a:Love:1503575713897517138> Integração entre comunidades \n"
                        + "  <a:Love:1503575713897517138> Apoio no crescimento de ambos os servidores\n\n"
                        + "Se você deseja fazer parceria conosco, por favor, clique no botão abaixo!", false)



                .setImage("https://pa1.aminoapps.com/7552/c0bed5df916d82abf25aab97ac2d65dd51f18e48r1-540-199_hq.gif")
                .setColor(minhacor)
                .setFooter("Aguardamos sua parceria! 🤝");

        return embedBuilder.build();
    }

    public MessageEmbed ticketAberto(Guild guild, User user)
    {
        Color minhacor = new Color(255, 255, 255);
        EmbedBuilder embedBuilder = new EmbedBuilder()
                .setTitle("<:78391starquestionmark:1503585361442508962> Como Solicitar Parceria <:78391starquestionmark:1503585361442508962>")
                .setThumbnail(guild.getIconUrl())
                .setDescription("**Antes de enviar sua solicitação, verifique se seu servidor atende aos requisitos:**\n\n"
                        + "<a:Love:1503575713897517138> Mínimo de **2000 membros**\n"
                        + "<a:Love:1503575713897517138> Servidor ativo e dentro das diretrizes do Discord\n\n"
                        + "**Sua mensagem deve conter:** \n\n"
                        + "<a:Love:1503575713897517138> **Nome do Servidor**\n"
                        + "<a:Love:1503575713897517138> **Descrição do Servidor**\n"
                        + "<a:Love:1503575713897517138> **Representante: @MençãoDoResponsável**\n"
                        + "<a:Love:1503575713897517138> **discord.gg/seulink**\n\n")

                // Exemplo Prático
                .addField("<a:almdecorbowpink:1223409342376706139> **Exemplo REAL do nosso servidor:**",
                        "```md\n" +
                                "# 月 Santuário Lunar\n" +
                                "O nosso servidor conta com uma comunidade muito acolhedora, que adora conversar sobre temas diversos, principalmente jogos e animes!\n" +
                                "Por falar em jogos, jogamos vários jogos em call e sempre tem um Garticzinho, pra rirmos bastante!\n" +
                                "💸 Dobro de sonhos na Loritta(x2)\n" +
                                "🤓 Nosso principal foco é fazer bastante amizades, nos divertir e quem sabe, encontrar o amor da sua vida!\n" +
                                "Temos sistema de XP, para adquirir cargos diversos e mostrar sua atividade no servidor!\n" +
                                "💞 Todos são bem-vindos para entrar no servidor, todos os canais são disponíveis para todas as idades.\n" +
                                "🤖 Contamos com diversas atividades para promover a interação e amizade, aba de fotos, minigames, como mudae e jogos da Loritta.\n" +
                                "🩵 Temos cargos de VIP, para você poder personalizar seu cargo e criar calls exclusivas para você e seus amigos, além de benefícios para boosters.\n\n" +
                                "Venha fazer parte do Santuário Lunar :3\n\n" +
                                "Representante: @Ayanokoji\n" +
                                "# discord.gg/santuario\n" +
                                "```", false)
                .setColor(minhacor)
                .setImage("https://cdn.discordapp.com/attachments/875131727041937420/877723401219878972/image3-1-4.gif")
                .setFooter("Obrigado pela parceria!");

        return embedBuilder.build();
    }
}
