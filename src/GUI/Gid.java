package GUI;

import javax.swing.*;
import java.awt.image.BufferedImage;

/**
 * Created by ruhollah on 6/27/2016.
 */
public class Gid extends JLabel
{
    private boolean wall = false;

    public Gid()
    {
        wall = false;
    }

    public Gid(ImageIcon imageIcon)
    {
        super(imageIcon);
    }

    public void setImageIcon(ImageIcon imageIcon)
    {
        setImageIcon(imageIcon);
    }

    public boolean isWall()
    {
        return wall;
    }

    public void setWall(boolean wall)
    {
        this.wall = wall;
    }
}
