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
 * Created by ruhollah on 7/11/2016.
 */
public class HeroClassCustomUI extends JPanel
{
    private Controller controller;
    private int width = (int) (Toolkit.getDefaultToolkit().getScreenSize().getWidth() * 2) / 8;
    private int height = (int) (Toolkit.getDefaultToolkit().getScreenSize().getHeight() * 2) / 5;

    public HeroClassCustomUI(Controller controller)
    {
        System.out.println(width + " " + height);
        this.controller = controller;
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
                controller.setPanel(MainCustomPanel.getInstance());
            }
        });
        this.add(backButton);

        /////

        JLabel label = new JLabel("<html><font size=\"10\">Add a hero class</font></html>");
        label.setBounds((width * 215) / 720, (height * 65) / 648, (width * 305) / 720, (height * 95) / 648);
        this.add(label);

        /////

        JLabel nameLabel = new JLabel("<html><font size=\"5\">Name</font></html>");
        nameLabel.setBounds((width * 95) / 720, (height * 180) / 648, (width * 100) / 720, (height * 30) / 648);
        this.add(nameLabel);

        JTextField textArea = new JTextField();
        textArea.setBounds((width * 175) / 720, (height * 180) / 648, (width * 305) / 720, (height * 30) / 648);
        this.add(textArea);

        /////

        JLabel attributeLabel = new JLabel("<html><font size=\"5\">Attribute</font></html>");
        attributeLabel.setBounds((width * 95) / 720, (height * 230) / 648, (width * 80) / 720, (height * 30) / 648);
        this.add(attributeLabel);

        ArrayList<String> wantedList = new ArrayList<>();
        for (String attributeName : controller.getUserInterface().getHeroAttributes())
        {
            if (controller.getUserInterface().getAttributesWithMax().contains(attributeName))
            {
                wantedList.add("max " + attributeName);
            }
            else
            {
                wantedList.add(attributeName);
            }
        }

        JComboBox classAttributeBox = new JComboBox(wantedList.toArray());
        classAttributeBox.setBounds((width * 175) / 720, (height * 230) / 648, (width * 150) / 720, (height * 30) / 648);
        this.add(classAttributeBox);

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
                    if (!textArea.getText().equals("") && !attributeField.getText().equals(""))
                    {
                        if (!controller.getUserInterface().getHeroClassNames().contains(textArea.getText()))
                        {
                            controller.getUserInterface().getHeroClassNames().add(textArea.getText());
                        }
                        HashMap<String, Integer> data = controller.getUserInterface().getHeroClassDatas().get(textArea.getText());
                        if (data == null)
                        {
                            controller.getUserInterface().getHeroClassDatas().put(textArea.getText(), new HashMap<>());
                        }
                        controller.getUserInterface().getHeroClassDatas().get(textArea.getText()).put((String) classAttributeBox.getSelectedItem(), Integer.parseInt(attributeField.getText()));

                        if (((String) classAttributeBox.getSelectedItem()).startsWith("max "))
                        {
                            String currentAtt = ((String) classAttributeBox.getSelectedItem()).replace("max ", "current ");
                            controller.getUserInterface().getHeroClassDatas().get(textArea.getText()).put(currentAtt, Integer.parseInt(attributeField.getText()));
                        }

                        if (controller.getUserInterface().getAttributesWithMax().contains((String) classAttributeBox.getSelectedItem()))
                        {
                            String tempVal = "temp " + classAttributeBox.getSelectedItem();
                            controller.getUserInterface().getHeroClassDatas().get(textArea.getText()).put(tempVal, 0);
                        }

                        attributeField.setText("");
                        classAttributeBox.removeItem(classAttributeBox.getSelectedItem());
                    }
                }
                catch (Exception e1)
                {
                    attributeField.setText("Invalid Input");
                }
            }
        });
        this.add(addAttributeButton);

        /////

        JLabel inventoryLabel = new JLabel("<html><font size=\"3\">Inventory size</font></html>");
        inventoryLabel.setBounds((width * 95) / 720, (height * 270) / 648, (width * 100) / 720, (height * 30) / 648);
        this.add(inventoryLabel);

        JTextField inventoryField = new JTextField();
        inventoryField.setBounds((width * 175) / 720, (height * 270) / 648, (width * 100) / 720, (height * 30) / 648);
        this.add(inventoryField);

        /////

        JComboBox abilityBox = new JComboBox(controller.getUserInterface().getAbilityNames().toArray());
        abilityBox.setBounds((width * 175) / 720, (height * 310) / 648, (width * 150) / 720, (height * 30) / 648);
        this.add(abilityBox);

        JButton addAbilityButton = new JButton("Add Ability");
        addAbilityButton.setBounds((width * 335) / 720, (height * 310) / 648, (width * 145) / 720, (height * 30) / 648);
        addAbilityButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                if (!textArea.getText().equals(""))
                {
                    ArrayList<String> abilities = controller.getUserInterface().getHeroClassAbilities().get(textArea.getText());
                    if (abilities == null)
                    {
                        controller.getUserInterface().getHeroClassAbilities().put(textArea.getText(), new ArrayList<>());
                    }
                    controller.getUserInterface().getHeroClassAbilities().get(textArea.getText()).add((String) abilityBox.getSelectedItem());
                    abilityBox.removeItem(abilityBox.getSelectedItem());
                    controller.getUserInterface().getHeroClassInventorySizes().put(textArea.getText(), Integer.parseInt(inventoryField.getText()));
                }
            }
        });
        this.add(addAbilityButton);
    }
}
