package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * Created by ruhollah on 7/11/2016.
 */
public class AbilityCustomUI extends JPanel
{
    private Controller controller;
    private int width = (int) (Toolkit.getDefaultToolkit().getScreenSize().getWidth() * 2) / 8;
    private int height = (int) (Toolkit.getDefaultToolkit().getScreenSize().getHeight() * 2) / 5;

    public AbilityCustomUI(Controller controller)
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

        JLabel label = new JLabel("<html><font size=\"10\">Add an ability</font></html>");
        label.setBounds((width * 215) / 720, (height * 65) / 648, (width * 305) / 720, (height * 95) / 648);
        this.add(label);

        /////

        JLabel nameLabel = new JLabel("<html><font size=\"5\">Name</font></html>");
        nameLabel.setBounds((width * 95) / 720, (height * 180) / 648, (width * 100) / 720, (height * 40) / 648);
        this.add(nameLabel);

        JTextField textArea = new JTextField();
        textArea.setBounds((width * 175) / 720, (height * 180) / 648, (width * 305) / 720, (height * 40) / 648);
        this.add(textArea);

        /////

        JLabel abilityKindLabel = new JLabel("<html><font size=\"3\">Ability Kind</font></html>");
        abilityKindLabel.setBounds((width * 520) / 720, (height * 180) / 648, (width * 90) / 720, (height * 30) / 648);
        this.add(abilityKindLabel);

        String[] abilityKinds = {"Active", "Passive"};
        JComboBox<String> comboBox = new JComboBox(abilityKinds);
        comboBox.setBounds((width * 600) / 720, (height * 180) / 648, (width * 100) / 720, (height * 30) / 648);
        this.add(comboBox);

        /////

        JLabel numLabel = new JLabel("<html><font size=\"3\">How many upgrades?</font></html>");
        numLabel.setBounds((width * 120) / 720, (height * 260) / 648, (width * 200) / 720, (height * 30) / 648);
        this.add(numLabel);

        JTextField numTextArea = new JTextField();
        numTextArea.setBounds((width * 260) / 720, (height * 260) / 648, (width * 200) / 720, (height * 30) / 648);
        this.add(numTextArea);

        /////

        JLabel targetLabel = new JLabel("<html><font size=\"3\">Choose the target</font></html>");
        targetLabel.setBounds((width * 490) / 720, (height * 260) / 648, (width * 170) / 720, (height * 30) / 648);
        this.add(targetLabel);

        String[] targetKinds = {"an enemy", "an ally", "all enemies", "all allies", "himself"};
        JComboBox targetComboBox = new JComboBox(targetKinds);
        targetComboBox.setBounds((width * 600) / 720, (height * 260) / 648, (width * 100) / 720, (height * 30) / 648);
        this.add(targetComboBox);

        /////

        JLabel descriptionLabel = new JLabel("<html><font size=\"5\">Add description</font></html>");
        descriptionLabel.setBounds((width * 300) / 720, (height * 330) / 648, (width * 100) / 720, (height * 60) / 648);
        this.add(descriptionLabel);

        JTextArea descriptionArea = new JTextArea();
        descriptionArea.setBounds((width * 240) / 720, (height * 410) / 648, (width * 240) / 720, (height * 150) / 648);
        this.add(descriptionArea);

        /////

        JLabel abilitySuccessLabel = new JLabel("<html><font size=\"3\">Enter ability success message</font></html>");
        abilitySuccessLabel.setBounds((width * 75) / 720, (height * 575) / 648, (width * 150) / 720, (height * 30) / 648);
        this.add(abilitySuccessLabel);

        JTextField abilitySuccessField = new JTextField();
        abilitySuccessField.setBounds((width * 225) / 720, (height * 575) / 648, (width * 100) / 720, (height * 30) / 648);
        this.add(abilitySuccessField);

        /////

        JButton addButton = new JButton("<html><font size=\"5\">Add</font></html>");
        addButton.setBounds((width * 310) / 720, (height * 610) / 648, (width * 90) / 720, (height * 30) / 648);
        addButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                if(!textArea.getText().equals(""))
                {
                    try
                    {
                        int upgradeNum = Integer.parseInt(numTextArea.getText());
//                        controller.getUserInterface().getAbilityNames().add(textArea.getText());
                        controller.setPanel(new UpgradeCustomUI(upgradeNum, 1, textArea.getText(), controller));
                        if(comboBox.getSelectedItem().equals("Passive"))
                        {
                            controller.getUserInterface().getInstantEffectConditionAbilities().add(textArea.getText());
                        }

                        controller.getUserInterface().getAbilityTargets().put(textArea.getText(), (String) targetComboBox.getSelectedItem());
                        controller.getUserInterface().getAbilityNames().add(textArea.getText());
                        controller.getUserInterface().getAllAbilitySuccessMessages().put(textArea.getText(), abilitySuccessField.getText());
                        controller.getUserInterface().getAbilityDescription().put(textArea.getText(), descriptionArea.getText());
                    }
                    catch (Exception e1)
                    {
                        numTextArea.setText("Invalid Input");
                    }
                }
            }
        });
        this.add(addButton);
    }
}
