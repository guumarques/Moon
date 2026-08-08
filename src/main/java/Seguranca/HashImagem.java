package Seguranca;

import javax.imageio.ImageIO;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;

public final class HashImagem
{
    private static final int TAMANHO = 8;

    private HashImagem() {}

    public static long hashPerceptual(byte[] bytes) throws IOException
    {
        BufferedImage original = ImageIO.read(new ByteArrayInputStream(bytes));
        if (original == null)
        {
            throw new IOException("Não foi possível decodificar a imagem");
        }

        BufferedImage reduzida = new BufferedImage(TAMANHO, TAMANHO, BufferedImage.TYPE_BYTE_GRAY);
        Graphics graphics = reduzida.getGraphics();
        graphics.drawImage(original, 0, 0, TAMANHO, TAMANHO, null);
        graphics.dispose();

        int[] tons = new int[TAMANHO * TAMANHO];
        long soma = 0L;
        for (int y = 0; y < TAMANHO; y++)
        {
            for (int x = 0; x < TAMANHO; x++)
            {
                int tom = reduzida.getRaster().getSample(x, y, 0);
                tons[y * TAMANHO + x] = tom;
                soma += tom;
            }
        }

        int media = (int) (soma / tons.length);

        long hash = 0L;
        for (int tom : tons)
        {
            hash <<= 1;
            if (tom >= media)
            {
                hash |= 1L;
            }
        }

        return hash;
    }

    public static int distanciaHamming(long a, long b)
    {
        return Long.bitCount(a ^ b);
    }
}
