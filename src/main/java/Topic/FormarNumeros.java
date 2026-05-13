package Topic;

public class FormarNumeros
{
    public String formarNumerosComEmojis(int memberCount)
    {
        String numeros = String.valueOf(memberCount);
        StringBuilder resultado = new StringBuilder();

        for (char c : numeros.toCharArray()) {
            switch (c) {
                case '0' -> resultado.append("<:5445number0:1503575679810408519>");
                case '1' -> resultado.append("<:5494number1:1503575681110380594>");
                case '2' -> resultado.append("<:1784number2:1503575665876926474>");
                case '3' -> resultado.append("<:1501number3:1503575663251030017>");
                case '4' -> resultado.append("<:5857number4:1503575684952625275>");
                case '5' -> resultado.append("<:7234number5:1503575694377226281>");
                case '6' -> resultado.append("<:1257number6:1503575661653004358>");
                case '7' -> resultado.append("<:5807number7:1503575683723694232>");
                case '8' -> resultado.append("<:1027number8:1503575659153195038>");
                case '9' -> resultado.append("<:6098number9:1503575686496129024>");
            }
        }

        return "<a:4754zodiacpisces:1503575678514233577>" + resultado + "<a:7821zodiaccapricorn:1503575696402812998>";
    }

    public String outrosEmojis()
    {
        return "<a:nitroboosterrmz1:1503575721853980764>" +
                "<:9958letterm:1503575700823740530>" + //M
                "<:3442lettere:1503575669949599746>" + //e
                "<:9958letterm:1503575700823740530>" + //M
                "<:3437letterb:1503575668422742026>" + //B
                "<:1634letterr:1503575664458993764>" + //R
                "<:2264lettero:1503575667042943116>" + //O
                "<:1116letters:1503575660357095434>" + //S
                "<a:nitroboosterrmz1:1503575721853980764>"; //arrow
    }
}