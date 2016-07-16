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
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by ruhollah on 7/14/2016.
 */
public class CustomBossEnemy extends JPanel
{
    Controller controller;
    private int width = (int) (Toolkit.getDefaultToolkit().getScreenSize().getWidth() * 2) / 8;
    private int height = (int) (Toolkit.getDefaultToolkit().getScreenSize().getHeight() * 2) / 5;

    public CustomBossEnemy(Controller controller)
    {
        this.controller = controller;
        System.out.println(width + " " + height);
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
        HashMap<String, Integer> bossData = new HashMap<>();
        HashMap<String, Integer> angerAddition = new HashMap<>();
        HashMap<String, String> earlyEffects = new HashMap<>();

        JButton backButton = new JButton("<html><font size=\"3\">Back to menu</font></html>");
        backButton.setBounds((width * 625) / 720, (height * 15) / 648, (width * 70) / 720, (height * 55) / 648);
        backButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                controller.setPanel(MainCustomPanel.getInstance());
            }
        });
        this.add(backButton);

        JLabel label = new JLabel("<html><font size=\"10\">Add a boss enemy</font></html>");
        label.setBounds((width * 215) / 720, (height * 65) / 648, (width * 305) / 720, (height * 95) / 648);
        this.add(label);

        JLabel nameLabel = new JLabel("<html><font size=\"5\">Name</font></html>");
        nameLabel.setBounds((width * 95) / 720, (height * 180) / 648, (width * 100) / 720, (height * 40) / 648);
        this.add(nameLabel);

        JTextField nameField = new JTextField();
        nameField.setBounds((width * 175) / 720, (height * 180) / 648, (width * 305) / 720, (height * 40) / 648);
        this.add(nameField);

        JLabel attributeLabel = new JLabel("<html><font size=\"5\">Attribute</font></html>");
        attributeLabel.setBounds((width * 95) / 720, (height * 230) / 648, (width * 80) / 720, (height * 30) / 648);
        this.add(attributeLabel);

        String[] wantedList = {"move attack", "move heal", "current health", "max health"};
        JComboBox bossAttributeBox = new JComboBox(wantedList);
        bossAttributeBox.setBounds((width * 175) / 720, (height * 230) / 648, (width * 150) / 720, (height * 30) / 648);
        this.add(bossAttributeBox);

        JTextField attributeField = new JTextField();
        attributeField.setBounds((width * 335) / 720, (height * 230) / 648, (width * 145) / 720, (height * 30) / 648);
        this.add(attributeField);

        JButton addAttributeButton = new JButton("add");
        addAttributeButton.setBounds((width * 490) / 720, (height * 230) / 648, (width * 100) / 720, (height * 30) / 648);
        addAttributeButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                try
                {
                    int attVal = Integer.parseInt(attributeField.getText());
                    bossData.put((String) bossAttributeBox.getSelectedItem(), attVal);
                    bossAttributeBox.removeItem(bossAttributeBox.getSelectedItem());
                    attributeField.setText("");
                } catch (Exception e1)
                {
                    attributeField.setText("Invalid Input");
                }
            }
        });
        this.add(addAttributeButton);

        /////

        JLabel angerLabel = new JLabel("Anger point");
        angerLabel.setBounds((width * 200) / 720, (height * 270) / 648, (width * 100) / 720, (height * 30) / 648);
        this.add(angerLabel);

        JTextField angerField = new JTextField();
        angerField.setBounds((width * 300) / 720, (height * 270) / 648, (width * 100) / 720, (height * 30) / 648);
        this.add(angerField);

        /////

        JLabel additionLabel = new JLabel("<html><font size=\"3\">Anger addition</font></html>");
        additionLabel.setBounds((width * 95) / 720, (height * 310) / 648, (width * 80) / 720, (height * 30) / 648);
        this.add(additionLabel);

        String[] wantedList2 = {"move attack", "move heal", "current health", "max health"};
        JComboBox bossAttributeAdditionBox = new JComboBox(wantedList2);
        bossAttributeAdditionBox.setBounds((width * 175) / 720, (height * 310) / 648, (width * 150) / 720, (height * 30) / 648);
        this.add(bossAttributeAdditionBox);

        JTextField additionField = new JTextField();
        additionField.setBounds((width * 335) / 720, (height * 310) / 648, (width * 145) / 720, (height * 30) / 648);
        this.add(additionField);

        JButton additionButton = new JButton("add");
        additionButton.setBounds((width * 490) / 720, (height * 310) / 648, (width * 100) / 720, (height * 30) / 648);
        additionButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                try
                {
                    int attVal = Integer.parseInt(additionField.getText());
                    angerAddition.put((String) bossAttributeAdditionBox.getSelectedItem(), attVal);
                    bossAttributeAdditionBox.removeItem(bossAttributeAdditionBox.getSelectedItem());
                    additionField.setText("");
                } catch (Exception e1)
                {
                    additionField.setText("Invalid Input");
                }
            }
        });
        this.add(additionButton);

        /////

        JLabel earlyLabel = new JLabel("<html><font size=\"3\">Anger addition</font></html>");
        earlyLabel.setBounds((width * 95) / 720, (height * 350) / 648, (width * 80) / 720, (height * 30) / 648);
        this.add(earlyLabel);

        ArrayList<String> newList = new ArrayList<>();
        for (String attributeName : controller.getUserInterface().getAbilityAttributes())
        {
            if (controller.getUserInterface().getAttributesWithMax().contains(attributeName))
            {
                newList.add("current " + attributeName);
                newList.add("max " + attributeName);
            }
            else
            {
                newList.add(attributeName);
            }
        }
        JComboBox earlyBox = new JComboBox(newList.toArray());
        earlyBox.setBounds((width * 175) / 720, (height * 350) / 648, (width * 150) / 720, (height * 30) / 648);
        this.add(earlyBox);

        JTextField earlyField = new JTextField();
        earlyField.setBounds((width * 335) / 720, (height * 350) / 648, (width * 100) / 720, (height * 30) / 648);
        this.add(earlyField);

        JLabel toLabel = new JLabel("to");
        toLabel.setBounds((width * 440) / 720, (height * 350) / 648, (width * 50) / 720, (height * 30) / 648);
        this.add(toLabel);

        JTextField earlyField2 = new JTextField();
        earlyField2.setBounds((width * 500) / 720, (height * 350) / 648, (width * 100) / 720, (height * 30) / 648);
        this.add(earlyField2);

        JButton earlyButton = new JButton("add");
        earlyButton.setBounds((width * 610) / 720, (height * 350) / 648, (width * 100) / 720, (height * 30) / 648);
        earlyButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                try
                {
                    int attVal = Integer.parseInt(earlyField.getText());
                    int attVal2 = Integer.parseInt(earlyField2.getText());
                    String effect = attVal + " to " + attVal2;
                    earlyEffects.put((String) earlyBox.getSelectedItem(), effect);
                    earlyBox.removeItem(earlyBox.getSelectedItem());
                    earlyField.setText("");
                    earlyField2.setText("");
                } catch (Exception e1)
                {
                    earlyField.setText("Invalid Input");
                    earlyField2.setText("");
                }
            }
        });
        this.add(earlyButton);

        /////

        JLabel spriteLabel = new JLabel("<html><font size=\"3\">Enter source image of this enemy's sprite</font></html>");
        spriteLabel.setBounds((width * 75) / 720, (height * 390) / 648, (width * 150) / 720, (height * 30) / 648);
        this.add(spriteLabel);

        JTextField spriteField = new JTextField();
        spriteField.setBounds((width * 225) / 720, (height * 390) / 648, (width * 100) / 720, (height * 30) / 648);
        this.add(spriteField);

        /////

        JLabel targetLabel = new JLabel("<html><font size=\"3\">Enter target</font></html>");
        targetLabel.setBounds((width * 75) / 720, (height * 430) / 648, (width * 150) / 720, (height * 30) / 648);
        this.add(targetLabel);

        JTextField targetField = new JTextField();
        targetField.setBounds((width * 225) / 720, (height * 430) / 648, (width * 100) / 720, (height * 30) / 648);
        this.add(targetField);

        /////

        JButton doneButton = new JButton("Done");
        doneButton.setBounds((width * 200) / 720, (height * 470) / 648, (width * 100) / 720, (height * 30) / 648);
        doneButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                if (!targetField.getText().equals("") && !nameField.getText().equals(""))
                {
                    try
                    {
                        String name = nameField.getText();
                        int angerVal = Integer.parseInt(angerField.getText());
                        ImageIO.read(new File(spriteField.getText()));

                        controller.getUserInterface().getBossEnemyAngerPoints().put(name, angerVal);
                        controller.getUserInterface().getBossEnemyTargets().put(name, targetField.getText());
                        controller.getUserInterface().getBossEnemyDatas().put(name, bossData);
                        controller.getUserInterface().getBossEnemyAngerAdditions().put(name, angerAddition);
                        controller.getUserInterface().getBossEnemyEarlyEffects().put(name, earlyEffects);
                        controller.setPanel(MainCustomPanel.getInstance());
                    } catch (IOException e1)
                    {
                        spriteField.setText("Invalid Input");
                    } catch (Exception e2)
                    {
                        angerField.setText("Invalid Input");
                    }
                }
            }
        });
        this.add(doneButton); // HANG IN THERE.. JUST A LITTLE MORE
    }
}
