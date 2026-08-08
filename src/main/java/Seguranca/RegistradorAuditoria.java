package Seguranca;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class RegistradorAuditoria
{
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
    private final String caminhoArquivo;

    public RegistradorAuditoria(String caminhoArquivo)
    {
        this.caminhoArquivo = caminhoArquivo;
    }

    public synchronized void registrar(String nome, String id, String canalId, String mensagemId)
    {
        String linha = FORMATTER.format(LocalDateTime.now())
                + " | Nome: " + nome
                + " | ID: " + id
                + " | Canal: " + canalId
                + " | Mensagem: " + mensagemId
                + System.lineSeparator();

        try (FileWriter escritor = new FileWriter(caminhoArquivo, true))
        {
            escritor.write(linha);
        }
        catch (IOException e)
        {
            System.out.println("Falha ao escrever no arquivo de auditoria: " + e.getMessage());
        }
    }
}
