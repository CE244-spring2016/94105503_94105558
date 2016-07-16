package GUI;

import Controller.UltimateImage;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import Controller.*;

/**
 * Created by ruhollah on 7/13/2016.
 */
public class CustomBattle extends JPanel
{
    MapEditor panel;
    Controller controller;
    private int width = (int) (Toolkit.getDefaultToolkit().getScreenSize().getWidth() * 2) / 8;
    private int height = (int) (Toolkit.getDefaultToolkit().getScreenSize().getHeight() * 2) / 5;

    public CustomBattle(MapEditor panel)
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

        JLabel storyLabel = new JLabel("<html><font size=\"7\">Custom battle</font></html>");
        storyLabel.setBounds((width * 200) / 720, (height * 40) / 648, (width * 300) / 720, (height * 50) / 648);
        this.add(storyLabel);

        ArrayList<String> enemies = new ArrayList<>();
        ArrayList<String> thisBattleEnemies = new ArrayList<>();
        HashMap<String, ArrayList<String>> normalEnemiesAndVersions = controller.getUserInterface().getEnemyVersionNames();
        ArrayList<String> bossNames = controller.getUserInterface().getBossEnemyNames();

        for (String enemyName : normalEnemiesAndVersions.keySet())
        {
            for (String enemyVersion : normalEnemiesAndVersions.get(enemyName))
            {
                enemies.add(enemyVersion + " " + enemyName);
            }
        }

        for (String bossName : bossNames)
        {
            enemies.add(bossName);
        }

        JComboBox enemyBox = new JComboBox(enemies.toArray());
        enemyBox.setBounds((width * 200) / 720, (height * 100) / 648, (width * 200) / 720, (height * 30) / 648);
        this.add(enemyBox);

        JButton addButton = new JButton("add");
        addButton.setBounds((width * 410) / 720, (height * 100) / 648, (width * 100) / 720, (height * 30) / 648);
        addButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                thisBattleEnemies.add((String) enemyBox.getSelectedItem());
            }
        });
        this.add(addButton);

        JLabel backgroundLabel = new JLabel("<html><font size=\"3\">Enter background source</font></html>");
        backgroundLabel.setBounds((width * 200) / 720, (height * 140) / 648, (width * 150) / 720, (height * 30) / 648);
        this.add(backgroundLabel);

        JTextField sourceField = new JTextField();
        sourceField.setBounds((width * 350) / 720, (height * 140) / 648, (width * 160) / 720, (height * 30) / 648);
        this.add(sourceField);

        JLabel xpLabel = new JLabel("<html><font size=\"3\">Enter xp reward amount</font></html>");
        xpLabel.setBounds((width * 200) / 720, (height * 180) / 648, (width * 150) / 720, (height * 30) / 648);
        this.add(xpLabel);

        JTextField xpField = new JTextField();
        xpField.setBounds((width * 350) / 720, (height * 180) / 648, (width * 160) / 720, (height * 30) / 648);
        this.add(xpField);

        JLabel moneyLabel = new JLabel("<html><font size=\"3\">Enter money reward amount</font></html>");
        moneyLabel.setBounds((width * 200) / 720, (height * 220) / 648, (width * 150) / 720, (height * 30) / 648);
        this.add(moneyLabel);

        JTextField moneyField = new JTextField();
        moneyField.setBounds((width * 350) / 720, (height * 220) / 648, (width * 160) / 720, (height * 30) / 648);
        this.add(moneyField);

        JButton doneButton = new JButton("done");
        doneButton.setBounds((width * 200) / 720, (height * 340) / 648, (width * 100) / 720, (height * 30) / 648);
        doneButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                if (!sourceField.getText().equals(""))
                {
                    try
                    {
                        ImageIO.read(new File(sourceField.getText()));
                        int xpAmount = Integer.parseInt(xpField.getText());
                        int moneyAmount = Integer.parseInt(moneyField.getText());

                        controller.getUserInterface().getBattleBackgroundSources().add(new UltimateImage(sourceField.getText()));
                        controller.getUserInterface().getStoryEnemyGroups().add(thisBattleEnemies);
                        controller.getUserInterface().getEnemyGroupXPs().add(xpAmount);
                        controller.getUserInterface().getEnemyGroupMoneys().add(moneyAmount);
                        controller.getUserInterface().setBattleId(controller.getUserInterface().getBattleId() + 1);
                        controller.setPanel(panel);
                    } catch (IOException e1)
                    {
                        sourceField.setText("Invalid Source");
                    } catch (Exception e2)
                    {
                        xpField.setText("Invalid Inputs");
                        moneyField.setText("");
                    }
                }
            }
        });
        this.add(doneButton);
    }
}
