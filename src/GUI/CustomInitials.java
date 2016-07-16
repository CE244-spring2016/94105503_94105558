package GUI;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;
import Controller.*;

/**
 * Created by ruhollah on 7/14/2016.
 */
public class CustomInitials extends JPanel
{
    MapEditor panel;
    Controller controller;
    private int width = (int) (Toolkit.getDefaultToolkit().getScreenSize().getWidth() * 2) / 8;
    private int height = (int) (Toolkit.getDefaultToolkit().getScreenSize().getHeight() * 2) / 5;

    public CustomInitials(MapEditor panel)
    {
        this.panel = panel;
        this.controller = panel.getController();
        System.out.println(width + " " + height);
        this.controller = panel.getController();
        this.setLayout(null);
        this.setFocusable(true);
        this.requestFocus();
        this.addMouseListener(new MouseListener()
        {
            @Override
            public void mouseClicked(MouseEvent e)
            {

            }

            @Override
            public void mousePressed(MouseEvent e)
            {
                System.out.println(e.getX() + ", " + e.getY());
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
        this.setSize(width, height);
        this.setPreferredSize(new Dimension(width, height));

        create();
    }

    private void create()
    {
        JButton backButton = new JButton("<html><font size=\"3\">Back to menu</font></html>");
        backButton.setBounds((width * 625) / 720, (height * 15) / 648, (width * 70) / 720, (height * 55) / 648);
        backButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                controller.setPanel(panel);
            }
        });
        this.add(backButton);

        JLabel xpLabel = new JLabel("<html><font size=\"3\">Initial XP</font></html>");
        xpLabel.setBounds((width * 180) / 720, (height * 100) / 648, (width * 150) / 720, (height * 30) / 648);
        this.add(xpLabel);

        JTextField xpField = new JTextField();
        xpField.setBounds((width * 350) / 720, (height * 100) / 648, (width * 150) / 720, (height * 30) / 648);
        this.add(xpField);

        JLabel moneyLabel = new JLabel("<html><font size=\"3\">Initial money</font></html>");
        moneyLabel.setBounds((width * 180) / 720, (height * 140) / 648, (width * 150) / 720, (height * 30) / 648);
        this.add(moneyLabel);

        JTextField moneyField = new JTextField();
        moneyField.setBounds((width * 350) / 720, (height * 140) / 648, (width * 150) / 720, (height * 30) / 648);
        this.add(moneyField);

        JLabel potionLabel = new JLabel("<html><font size=\"3\">Initial amount of immortality potion</font></html>");
        potionLabel.setBounds((width * 180) / 720, (height * 180) / 648, (width * 150) / 720, (height * 30) / 648);
        this.add(potionLabel);

        JTextField potionField = new JTextField();
        potionField.setBounds((width * 350) / 720, (height * 180) / 648, (width * 150) / 720, (height * 30) / 648);
        this.add(potionField);

        JLabel startingStoryLabel = new JLabel("<html><font size=\"3\">Starting story background source</font></html>");
        startingStoryLabel.setBounds((width * 180) / 720, (height * 220) / 648, (width * 150) / 720, (height * 30) / 648);
        this.add(startingStoryLabel);

        JTextField startingStoryField = new JTextField();
        startingStoryField.setBounds((width * 350) / 720, (height * 220) / 648, (width * 150) / 720, (height * 30) / 648);
        this.add(startingStoryField);

        JTextArea startingStoryArea = new JTextArea();
        startingStoryArea.setBounds((width * 350) / 720, (height * 260) / 648, (width * 150) / 720, (height * 30) / 648);
        this.add(startingStoryArea);

        JLabel endingStoryLabel = new JLabel("<html><font size=\"3\">Ending story background source</font></html>");
        endingStoryLabel.setBounds((width * 180) / 720, (height * 300) / 648, (width * 150) / 720, (height * 30) / 648);
        this.add(endingStoryLabel);

        JTextField endingStoryField = new JTextField();
        endingStoryField.setBounds((width * 350) / 720, (height * 300) / 648, (width * 150) / 720, (height * 30) / 648);
        this.add(endingStoryField);

        JTextArea endingStoryArea = new JTextArea();
        endingStoryArea.setBounds((width * 350) / 720, (height * 340) / 648, (width * 150) / 720, (height * 30) / 648);
        this.add(endingStoryArea);

        JButton doneButton = new JButton("done");
        doneButton.setBounds((width * 200) / 720, (height * 380) / 648, (width * 100) / 720, (height * 30) / 648);
        doneButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                try
                {
                    int xpAmount = Integer.parseInt(xpField.getText());
                    int moneyAmount = Integer.parseInt(moneyField.getText());
                    int immortalityPotionNum = Integer.parseInt(potionField.getText());
                    ImageIO.read(new File(startingStoryField.getText()));
                    ImageIO.read(new File(endingStoryField.getText()));

                    controller.getUserInterface().setInitialXP(xpAmount);
                    controller.getUserInterface().setInitialMoney(moneyAmount);
                    controller.getUserInterface().setImmortalityPotionNum(immortalityPotionNum);
                    controller.getUserInterface().getStoryBackgroundSources().add(0, new UltimateImage(startingStoryField.getText()));
                    controller.getUserInterface().getGameStory().add(0, startingStoryArea.getText());
                    controller.getUserInterface().getStoryBackgroundSources().add(new UltimateImage(endingStoryField.getText()));
                    controller.getUserInterface().getGameStory().add(endingStoryArea.getText());
                    panel.createGame();
                } catch (IOException e1)
                {
                    startingStoryField.setText("");
                    endingStoryField.setText("");
                } catch (Exception e1)
                {
                    e1.printStackTrace();
                    xpField.setText("Invalid Inputs");
                    moneyField.setText("");
                    potionField.setText("");
                }
            }
        });
        this.add(doneButton);
    }
}
