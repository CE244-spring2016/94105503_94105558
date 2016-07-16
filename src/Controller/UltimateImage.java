package Controller;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;

/**
 * Created by ruhollah on 7/12/2016.
 */
public class UltimateImage implements Serializable
{
    private int x;
    private int y;
    private int width;
    private int height;
    private String source;
    private Type type;

    public UltimateImage(String source)
    {
        this.source = source;
        type = Type.Full;
    }

    public UltimateImage(int x, int y, int width, int height, String source)
    {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.source = source;
        type = Type.Part;
    }

    public BufferedImage makeImage()
    {
        try
        {
            if (type == Type.Part)
            {
                    BufferedImage bufferedImage = ImageIO.read(new File(source));
                    return bufferedImage.getSubimage(x, y, width, height);
            }
            else
            {
                return ImageIO.read(new File(source));
            }
        } catch (IOException e)
        {
            e.printStackTrace();
        }

        return null;
    }

    public enum Type
    {
        Full, Part
    }
}
