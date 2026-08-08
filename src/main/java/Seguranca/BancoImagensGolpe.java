package Seguranca;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

public class BancoImagensGolpe
{
    private final List<Long> hashesReferencia = new ArrayList<>();

    public BancoImagensGolpe(String pastaImagens) throws IOException
    {
        File pasta = new File(pastaImagens);
        File[] arquivos = pasta.listFiles();

        if (arquivos == null)
        {
            throw new IOException("Pasta de imagens de referência não encontrada: " + pastaImagens);
        }

        for (File arquivo : arquivos)
        {
            byte[] bytes = Files.readAllBytes(arquivo.toPath());
            if (MagicNumberUtil.isImagem(bytes))
            {
                hashesReferencia.add(HashImagem.hashPerceptual(bytes));
            }
        }

        if (hashesReferencia.isEmpty())
        {
            throw new IOException("Nenhuma imagem de referência válida encontrada em: " + pastaImagens);
        }
    }

    public boolean provavelGolpe(long hash, int limiar)
    {
        for (long referencia : hashesReferencia)
        {
            if (HashImagem.distanciaHamming(hash, referencia) <= limiar)
            {
                return true;
            }
        }
        return false;
    }
}
