package Seguranca;

public final class MagicNumberUtil
{
    private MagicNumberUtil() {}

    public static boolean isImagem(byte[] bytes)
    {
        if (bytes == null || bytes.length < 4)
        {
            return false;
        }

        if ((bytes[0] & 0xFF) == 0xFF && (bytes[1] & 0xFF) == 0xD8 && (bytes[2] & 0xFF) == 0xFF)
        {
            return true; // JPEG
        }

        if ((bytes[0] & 0xFF) == 0x89 && bytes[1] == 0x50 && bytes[2] == 0x4E && bytes[3] == 0x47)
        {
            return true; // PNG
        }

        if (bytes[0] == 0x47 && bytes[1] == 0x49 && bytes[2] == 0x46 && bytes[3] == 0x38)
        {
            return true; // GIF87a / GIF89a
        }

        if (bytes.length >= 12 && bytes[0] == 0x52 && bytes[1] == 0x49 && bytes[2] == 0x46 && bytes[3] == 0x46
                && bytes[8] == 0x57 && bytes[9] == 0x45 && bytes[10] == 0x42 && bytes[11] == 0x50)
        {
            return true; // RIFF....WEBP
        }

        if (bytes[0] == 0x42 && bytes[1] == 0x4D)
        {
            return true; // BMP
        }

        return false;
    }
}
