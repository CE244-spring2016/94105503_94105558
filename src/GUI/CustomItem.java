package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by ruhollah on 7/15/2016.
 */
public class CustomItem extends JPanel
{
    Controller controller;
    private int width = (int) (Toolkit.getDefaultToolkit().getScreenSize().getWidth() * 2) / 8;
    private int height = (int) (Toolkit.getDefaultToolkit().getScreenSize().getHeight() * 2) / 5;
    private JCheckBox inflationCheck;
    private JCheckBox activeCheck;
    private JTextField useLimitField;

    public CustomItem(Controller controller)
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
        HashMap<String, Integer> itemData = new HashMap<>();

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

        JLabel label = new JLabel("<html><font size=\"10\">Add an item</font></html>");
        label.setBounds((width * 215) / 720, (height * 65) / 648, (width * 305) / 720, (height * 95) / 648);
        this.add(label);

        JLabel nameLabel = new JLabel("<html><font size=\"5\">Name</font></html>");
        nameLabel.setBounds((width * 95) / 720, (height * 180) / 648, (width * 100) / 720, (height * 40) / 648);
        this.add(nameLabel);

        JTextField nameField = new JTextField();
        nameField.setBounds((width * 175) / 720, (height * 180) / 648, (width * 305) / 720, (height * 40) / 648);
        this.add(nameField);

        /////

        JLabel attributeLabel = new JLabel("<html><font size=\"5\">Attribute</font></html>");
        attributeLabel.setBounds((width * 95) / 720, (height * 230) / 648, (width * 80) / 720, (height * 30) / 648);
        this.add(attributeLabel);

        ArrayList<String> wantedList = new ArrayList<>();
        for (String attributeName : controller.getUserInterface().getAbilityAttributes())
        {
            if (controller.getUserInterface().getAttributeWithTemp().contains(attributeName))
            {
                wantedList.add("temp " + attributeName);
            }

            wantedList.add("cost " + attributeName);

            if (controller.getUserInterface().getAttributesWithMax().contains(attributeName))
            {
                wantedList.add("current " + attributeName);
                wantedList.add("max " + attributeName);
            }
            else
            {
                wantedList.add(attributeName);
            }
        }

        JComboBox itemAttributeBox = new JComboBox(wantedList.toArray());
        itemAttributeBox.setBounds((width * 240) / 960, (height * 230) / 648, (width * 175) / 960, (height * 30) / 648);
        this.add(itemAttributeBox);

        JTextField attributeField = new JTextField();
        attributeField.setBounds((width * 340) / 720, (height * 230) / 648, (width * 145) / 720, (height * 30) / 648);
        this.add(attributeField);

        JButton addAttributeButton = new JButton("add");
        addAttributeButton.setBounds((width * 490) / 720, (height * 230) / 648, (width * 100) / 720, (height * 30) / 648);
        addAttributeButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                if (!nameField.getText().equals(""))
                {
                    try
                    {
                        int attVal = Integer.parseInt(attributeField.getText());
                        itemData.put((String) itemAttributeBox.getSelectedItem(), attVal);
                        attributeField.setText("");
                        itemAttributeBox.removeItem(itemAttributeBox.getSelectedItem());
                    } catch (Exception e1)
                    {
                        attributeField.setText("Invalid Input");
                    }
                }
            }
        });
        this.add(addAttributeButton);

        /////

        JLabel targetLabel = new JLabel("<html><font size=\"3\">Choose the target</font></html>");
        targetLabel.setBounds((width * 200) / 720, (height * 270) / 648, (width * 170) / 720, (height * 30) / 648);
        this.add(targetLabel);

        String[] targetKinds = {"an enemy", "an ally", "all enemies", "all allies", "himself"};
        JComboBox targetComboBox = new JComboBox(targetKinds);
        targetComboBox.setBounds((width * 380) / 720, (height * 270) / 648, (width * 100) / 720, (height * 30) / 648);
        this.add(targetComboBox);

        /////

        inflationCheck = new JCheckBox("Inflationed");
        inflationCheck.setBounds((width * 200) / 720, (height * 320) / 648, (width * 100) / 720, (height * 30) / 648);
        inflationCheck.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                if (activeCheck.isSelected())
                {
                    inflationCheck.setSelected(false);
                }
            }
        });
        this.add(inflationCheck);

        activeCheck = new JCheckBox("Active");
        activeCheck.setBounds((width * 200) / 720, (height * 350) / 648, (width * 100) / 720, (height * 30) / 648);
        activeCheck.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                if (activeCheck.isSelected() && inflationCheck.isSelected())
                {
                    inflationCheck.setSelected(false);
                }

                if (activeCheck.isSelected())
                {
                    useLimitField.setEditable(true);
                }
                else
                {
                    useLimitField.setText("");
                    useLimitField.setEditable(false);
                }
            }
        });
        this.add(activeCheck);

        JLabel useLimitLabel = new JLabel("<html><font size=\"3\">Enter item use limit</font></html>");
        useLimitLabel.setBounds((width * 75) / 720, (height * 390) / 648, (width * 150) / 720, (height * 30) / 648);
        this.add(useLimitLabel);

        useLimitField = new JTextField();
        useLimitField.setBounds((width * 200) / 720, (height * 390) / 648, (width * 100) / 720, (height * 30) / 648);
        this.add(useLimitField);

        /////

        JLabel itemSuccessLabel = new JLabel("<html><font size=\"3\">Enter item success message</font></html>");
        itemSuccessLabel.setBounds((width * 75) / 720, (height * 430) / 648, (width * 150) / 720, (height * 30) / 648);
        this.add(itemSuccessLabel);

        JTextField itemSuccessField = new JTextField();
        itemSuccessField.setBounds((width * 225) / 720, (height * 430) / 648, (width * 100) / 720, (height * 30) / 648);
        this.add(itemSuccessField);

        /////

        JLabel descriptionLabel = new JLabel("<html><font size=\"5\">Add description</font></html>");
        descriptionLabel.setBounds((width * 100) / 720, (height * 475) / 648, (width * 100) / 720, (height * 60) / 648);
        this.add(descriptionLabel);

        JTextArea descriptionArea = new JTextArea();
        descriptionArea.setBounds((width * 240) / 720, (height * 475) / 648, (width * 240) / 720, (height * 150) / 648);
        this.add(descriptionArea);

        /////

        JButton doneButton = new JButton("Done");
        doneButton.setBounds((width * 490) / 720, (height * 500) / 648, (width * 100) / 720, (height * 30) / 648);
        doneButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                if (!nameField.getText().equals(""))
                {
                    try
                    {
                        String itemName = nameField.getText();
                        if (activeCheck.isSelected())
                        {
                            if (itemSuccessField.getText().equals(""))
                            {
                                throw new Exception();
                            }
                            int useLimit;
                            useLimit = Integer.parseInt(useLimitField.getText());
                            controller.getUserInterface().getNonInstantEffectItemsUseLimit().put(itemName, useLimit);
                            controller.getUserInterface().getAllItemSuccessMessages().put(itemName, itemSuccessField.getText());
                        }
                        else
                        {
                            controller.getUserInterface().getInstantEffectItems().add(itemName);
                            if (inflationCheck.isSelected())
                            {
                                controller.getUserInterface().getInflationedItems().add(itemName);
                            }
                        }
                        controller.getUserInterface().getItemNames().add(itemName);
                        controller.getUserInterface().getItemDatas().put(itemName, itemData);
                        controller.getUserInterface().getItemTargets().put(itemName, (String) targetComboBox.getSelectedItem());
                        controller.getUserInterface().getItemDescription().put(itemName, descriptionArea.getText());
                        controller.setPanel(MainCustomPanel.getInstance());
                    } catch (Exception e1)
                    {
                        useLimitField.setText("Invalid Input");
                    }
                }
            }
        });
        this.add(doneButton);
    }
}
