package GUI;

import javax.jws.soap.SOAPBinding;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.util.Scanner;

import Controller.*;

/**
 * Created by ruhollah on 7/10/2016.
 */
public class GameOverScenario
{
    private GamePanel gamePanel;
    private JPanel panel = new JPanel();
    private BufferedImage background;
    private JLabel mainLabel = new JLabel();
    private int width = (int) (Toolkit.getDefaultToolkit().getScreenSize().getWidth() * 2) / 3;
    private int height = (int) (Toolkit.getDefaultToolkit().getScreenSize().getHeight() * 2) / 3;

    public GameOverScenario(BufferedImage background, GamePanel gamePanel)
    {
        this.background = background;
        this.gamePanel = gamePanel;
        panel.setLayout(null);
        panel.setSize(width, height);
        panel.setPreferredSize(new Dimension(width, height));

        showMainLabel();
        showBackground();
    }

    private void showMainLabel()
    {
        mainLabel.setBounds((width* 710) / 1920, (800 * height) / 1070, (width * 200) / 1920, (200 * height) / 1070);
        mainLabel.setText("<html><font size=\"20\" face=\"MS Gothic\" color=\"white\">Main Menu</font></html>");
        mainLabel.addMouseListener(new MouseListener()
        {
            @Override
            public void mouseClicked(MouseEvent e)
            {
                UserInterface userInterface = new UserInterface();
                gamePanel.getController().setPanel(OpeningPanel.getInstance());
                gamePanel.getController().setUserInterface(userInterface);
                gamePanel.getController().setGameScenario(new GameScenario(userInterface, new Scanner(System.in)));
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
                mainLabel.setText("<html><font size=\"20\" face=\"MS Gothic\" color=\"gray\">Main Menu</font></html>");
            }

            @Override
            public void mouseExited(MouseEvent e)
            {
                mainLabel.setText("<html><font size=\"20\" face=\"MS Gothic\" color=\"white\">Main Menu</font></html>");
            }
        });
        panel.add(mainLabel);
    }

    private void showBackground()
    {
        JLabel label = new JLabel();
        label.setBounds(0, 0, width, height);
        Image resizedBackground = background.getScaledInstance(label.getWidth(), label.getHeight(), Image.SCALE_SMOOTH);
        label.setIcon(new ImageIcon(resizedBackground));
        panel.add(label);
    }

    public JPanel getPanel()
    {
        return panel;
    }

    public void setPanel(JPanel panel)
    {
        this.panel = panel;
    }
}
