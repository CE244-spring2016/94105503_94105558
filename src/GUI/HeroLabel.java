package GUI;

import javax.swing.*;
import Controller.*;

/**
 * Created by ruhollah on 7/8/2016.
 */
public class HeroLabel extends JLabel
{
    private UltimateImage ultimateImage;

    public HeroLabel()
    {
    }

    public HeroLabel(UltimateImage ultimateImage)
    {
        this.ultimateImage = ultimateImage;
    }

    public UltimateImage getUltimateImage()
    {
        return ultimateImage;
    }

    public void setUltimateImage(UltimateImage ultimateImage)
    {
        this.ultimateImage = ultimateImage;
    }
}
