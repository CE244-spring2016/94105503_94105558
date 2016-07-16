package GUI;

import Model.EnemyVersion;

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
 * Created by ruhollah on 7/14/2016.
 */
public class CustomEnemyVersion extends JPanel
{
    private Controller controller;
    private int width = (int) (Toolkit.getDefaultToolkit().getScreenSize().getWidth() * 2) / 8;
    private int height = (int) (Toolkit.getDefaultToolkit().getScreenSize().getHeight() * 2) / 5;
    private String enemyName;
    private String versionName;

    public CustomEnemyVersion(Controller controller, String enemyName, String versionName)
    {
        System.out.println(width + " " + height);
        this.controller = controller;
        this.enemyName = enemyName;
        this.versionName = versionName;
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

        if (!controller.getUserInterface().getNormalEnemyNames().contains(enemyName))
        {
            controller.getUserInterface().getNormalEnemyNames().add(enemyName);
        }

        create();
    }

    private void create()
    {
        HashMap<String, Integer> data = new HashMap<>();

        JLabel label = new JLabel("<html><font size=\"10\">Create " + versionName + " " + enemyName + "</font></html>");
        label.setBounds((width * 215) / 720, (height * 65) / 648, (width * 305) / 720, (height * 95) / 648);
        this.add(label);

        JLabel attributeLabel = new JLabel("<html><font size=\"5\">Attribute</font></html>");
        attributeLabel.setBounds((width * 95) / 720, (height * 170) / 648, (width * 80) / 720, (height * 30) / 648);
        this.add(attributeLabel);

        String[] wantedList = {"move attack", "move heal", "current health", "max health"};
        JComboBox versionAttributeBox = new JComboBox(wantedList);
        versionAttributeBox.setBounds((width * 175) / 720, (height * 170) / 648, (width * 150) / 720, (height * 30) / 648);
        this.add(versionAttributeBox);

        JTextField attributeField = new JTextField();
        attributeField.setBounds((width * 335) / 720, (height * 170) / 648, (width * 145) / 720, (height * 30) / 648);
        this.add(attributeField);

        JButton addAttributeButton = new JButton("add");
        addAttributeButton.setBounds((width * 490) / 720, (height * 170) / 648, (width * 100) / 720, (height * 30) / 648);
        addAttributeButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                try
                {
                    int attributeVal = Integer.parseInt(attributeField.getText());
                    data.put((String) versionAttributeBox.getSelectedItem(), attributeVal);
                    versionAttributeBox.removeItem(versionAttributeBox.getSelectedItem());
                    attributeField.setText("");
                } catch (Exception e2)
                {
                    attributeField.setText("Invalid Input");
                }
            }
        });
        this.add(addAttributeButton);

        JLabel spriteLabel = new JLabel("<html><font size=\"3\">Enter source image of this enemy's sprite</font></html>");
        spriteLabel.setBounds((width * 75) / 720, (height * 210) / 648, (width * 150) / 720, (height * 30) / 648);
        this.add(spriteLabel);

        JTextField spriteField = new JTextField();
        spriteField.setBounds((width * 225) / 720, (height * 210) / 648, (width * 100) / 720, (height * 30) / 648);
        this.add(spriteField);

        String[] targets = {"an ally", "a hero", "all allies", "all heros", "himself"};
        JComboBox targetBox = new JComboBox(targets);
        targetBox.setBounds((width * 335) / 720, (height * 210) / 648, (width * 100) / 720, (height * 30) / 648);
        this.add(targetBox);

        JButton doneButton = new JButton("Done");
        doneButton.setBounds((width * 200) / 720, (height * 250) / 648, (width * 100) / 720, (height * 30) / 648);
        doneButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                try
                {
                    ImageIO.read(new File(spriteField.getText()));
                    String fullName = versionName + " " + enemyName;
                    EnemyVersion enemyVersion = new EnemyVersion(versionName, enemyName, data, (String) targetBox.getSelectedItem());
                    controller.getUserInterface().getNormalEnemyDatas().putIfAbsent(enemyName, new ArrayList<>());
                    controller.getUserInterface().getNormalEnemyDatas().get(enemyName).add(enemyVersion);
                    controller.getUserInterface().getEnemyVersionNames().putIfAbsent(enemyName, new ArrayList<>());
                    controller.getUserInterface().getEnemyVersionNames().get(enemyName).add(versionName);
                    controller.getUserInterface().getAllNormalEnemyImages().put(new UltimateImage(spriteField.getText()), fullName);
                    controller.setPanel(MainCustomPanel.getInstance());
                } catch (IOException e1)
                {
                    spriteField.setText("Invalid Input");
                }
            }
        });
        this.add(doneButton);
    }
}
