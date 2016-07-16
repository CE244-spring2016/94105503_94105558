package GUI;

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
public class StoryScenario
{
    private JPanel panel = new JPanel();
    private BufferedImage background;
    private JLabel textBox = new JLabel();
    private JLabel closeButton = new JLabel();
    private int width = (int) (Toolkit.getDefaultToolkit().getScreenSize().getWidth() * 2) / 3;
    private int height = (int) (Toolkit.getDefaultToolkit().getScreenSize().getHeight() * 2) / 3;
    private String storyPart;
    private GamePanel gamePanel;

    public StoryScenario(BufferedImage background, String storyPart, GamePanel gamePanel)
    {
        this.background = background;
        this.gamePanel = gamePanel;
        this.storyPart = storyPart;
        panel.setLayout(null);
        panel.setSize(width, height);
        panel.setPreferredSize(new Dimension(width, height));

        createTextBox();
        createCloseButton();
        createBackground();
    }

    private void createBackground()
    {
        JLabel label = new JLabel();
        label.setBounds(0, 0, width, height);
        Image resizedBackground = background.getScaledInstance(label.getWidth(), label.getHeight(), Image.SCALE_SMOOTH);
        label.setIcon(new ImageIcon(resizedBackground));
        panel.add(label);
    }

    private void createCloseButton()
    {
        closeButton.setText("<html><font size=\"30\" face=\"Papyrus\" color=\"blue\">X</font></html>");
        closeButton.setBounds((width * 1770) / 1920, (height * 5) / 1070, (145 * width) / 1920, (height * 125) / 1070);
        closeButton.addMouseListener(new MouseListener()
        {
            @Override
            public void mouseClicked(MouseEvent e)
            {
                if(gamePanel.getStoryParts().get(gamePanel.getStoryParts().size() - 1).equals(storyPart))
                {
                    UserInterface userInterface = new UserInterface();
                    gamePanel.getController().setPanel(OpeningPanel.getInstance());
                    gamePanel.getController().setUserInterface(userInterface);
                    gamePanel.getController().setGameScenario(new GameScenario(userInterface, new Scanner(System.in)));
                }
                else
                {
                    gamePanel.getController().setPanel(gamePanel);
                }
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
                closeButton.setText("<html><font size=\"30\" face=\"Papyrus\" color=\"red\">X</font></html>");
            }

            @Override
            public void mouseExited(MouseEvent e)
            {
                closeButton.setText("<html><font size=\"30\" face=\"Papyrus\" color=\"blue\">X</font></html>");
            }
        });
        panel.add(closeButton);
    }

    private void createTextBox()
    {
        textBox.setText("<html><font size=\"10\" face=\"Papyrus\" color=\"red\">" + storyPart + "</font></html>");
        textBox.setBounds((width * 1390) / 1920, (height * 260) / 1070, (width * 530) / 1920, (height * 810) / 1070);
        panel.add(textBox);
    }

    public JLabel getTextBox()
    {
        return textBox;
    }

    public void setTextBox(JLabel textBox)
    {
        this.textBox = textBox;
    }

    public JLabel getCloseButton()
    {
        return closeButton;
    }

    public void setCloseButton(JLabel closeButton)
    {
        this.closeButton = closeButton;
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

    public JPanel getPanel()
    {
        return panel;
    }

    public void setPanel(JPanel panel)
    {
        this.panel = panel;
    }
}
