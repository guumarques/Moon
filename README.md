Moon é meu bot de discord. Para uso, acesse o nosso servidor em: https://discord.gg/bHfrNHtCZK

## Auditoria de golpe de aposta

Monitor de imagens roda a cada 10 minutos nos canais configurados. Quando detecta imagem com padrão parecido às referências (pasta `imagens/`), registra evento em `auditoria_golpe_aposta.log`, na raiz do projeto.

Formato da linha:
```
08/08/2026 14:32:10 | Nome: fulano | ID: 123456789012345678 | Canal: 1247769364262817843 | Mensagem: 987654321098765432
```
