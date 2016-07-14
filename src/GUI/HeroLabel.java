package GUI;

import javax.swing.*;
import java.awt.image.BufferedImage;

/**
 * Created by ruhollah on 7/8/2016.
 */
public class HeroLabel extends JLabel
{
    private BufferedImage bufferedImage;

    public HeroLabel()
    {
    }

    public HeroLabel(BufferedImage bufferedImage)
    {
        this.bufferedImage = bufferedImage;
    }

    public BufferedImage getBufferedImage()
    {
        return bufferedImage;
    }

    public void setBufferedImage(BufferedImage bufferedImage)
    {
        this.bufferedImage = bufferedImage;
    }
}
