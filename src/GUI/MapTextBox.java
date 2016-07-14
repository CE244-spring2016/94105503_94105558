package GUI;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Created by ruhollah on 7/11/2016.
 */
public class MapTextBox
{
    private JLabel box = new JLabel();
    private JLabel text = new JLabel();
    private int x;
    private int y;
    private int width;
    private int height;
    private GamePanel gamePanel;

    public MapTextBox(int x, int y, int width, int height, GamePanel gamePanel)
    {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.gamePanel = gamePanel;

        text.addMouseListener(new MouseListener()
        {
            @Override
            public void mouseClicked(MouseEvent e)
            {
                text.setText("");
                box.setVisible(false);
                gamePanel.addKeyListener(gamePanel.getUltimateListener());
                gamePanel.setFocusable(true);
                gamePanel.requestFocus();
            }

            @Override
            public void mousePressed(MouseEvent e)
            {

            }

            @Override
            public void mouseReleased(MouseEvent e)
            {

            }

            @Override
            public void mouseEntered(MouseEvent e)
            {

            }

            @Override
            public void mouseExited(MouseEvent e)
            {

            }
        });
        showBox();
    }

    private void showBox()
    {
        try
        {
            text.setBounds(x, y, width, height);
            box.setBounds(x, y, width, height);
            BufferedImage bufferedImage = ImageIO.read(new File("Main Pics/MapTextBox/MapTextBox.png"));
            Image resizedImage = bufferedImage.getScaledInstance(width, height, Image.SCALE_SMOOTH);
            box.setIcon(new ImageIcon(resizedImage));
            box.setVisible(false);
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public void setBox(JLabel box)
    {
        this.box = box;
    }

    public int getX()
    {
        return x;
    }

    public void setX(int x)
    {
        this.x = x;
    }

    public int getY()
    {
        return y;
    }

    public void setY(int y)
    {
        this.y = y;
    }

    public int getWidth()
    {
        return width;
    }

    public void setWidth(int width)
    {
        this.width = width;
    }

    public int getHeight()
    {
        return height;
    }

    public void setHeight(int height)
    {
        this.height = height;
    }

    public JLabel getText()
    {
        return text;
    }

    public void setText(JLabel text)
    {
        this.text = text;
    }

    public JLabel getBox()
    {
        return box;
    }

    public void addText(String message)
    {
        text.setText("<html><font size=\"4\" face=\"Papyrus\">" + message + "</font></html>");
        box.setVisible(true);
    }
}
