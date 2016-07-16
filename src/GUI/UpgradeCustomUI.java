package GUI;

import Auxiliary.Formula;

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
public class UpgradeCustomUI extends JPanel
{
    private Controller controller;
    private int width = (int) (Toolkit.getDefaultToolkit().getScreenSize().getWidth() * 2) / 6;
    private int height = (int) (Toolkit.getDefaultToolkit().getScreenSize().getHeight() * 2) / 6;
    private int upgradeNum;
    private int currentUpgrade;
    private String abilityName;
    private HashMap<String, Double> extraVariables = new HashMap<>();
    private JButton saveButton;

    public UpgradeCustomUI(int upgradeNum, int currentUpgrade, String abilityName, Controller controller)
    {
        System.out.println(width + " " + height);
        this.controller = controller;
        this.upgradeNum = upgradeNum;
        this.currentUpgrade = currentUpgrade;
        this.abilityName = abilityName;
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
        JLabel titleLabel = new JLabel("<html><font size=\"8\">Upgrade " + currentUpgrade + " of ability " + abilityName + "</font></html>");
        titleLabel.setBounds((width * 300) / 960, 0, (width * 400) / 960, (height * 150) / 540);
        this.add(titleLabel);

        saveButton = new JButton("<html><font size=\"3\">Save</font></html>");
        saveButton.setBounds((width * 340) / 960, (height * 400) / 540, (width * 100) / 960, (height * 40) / 540);
        this.add(saveButton);

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

        JComboBox formulaAttributeComboBox = new JComboBox(wantedList.toArray());
        formulaAttributeComboBox.setBounds((width * 205) / 960, (height * 190) / 540, (width * 175) / 960, (height * 30) / 540);
        this.add(formulaAttributeComboBox);

        JLabel attributeLabel = new JLabel("<html><font size=\"3\">Attribute</font></html>");
        attributeLabel.setBounds((width * 150) / 960, (height * 190) / 540, (width * 50) / 960, (height * 30) / 540);
        this.add(attributeLabel);

        JTextField formulaField = new JTextField();
        formulaField.setBounds((width * 390) / 960, (height * 190) / 540, (width * 300) / 960, (height * 30) / 540);
        this.add(formulaField);

        JButton addFormulaButton = new JButton("<html><font size=\"3\">Add formula");
        addFormulaButton.setBounds((width * 700) / 960, (height * 190) / 540, (width * 175) / 960, (height * 30) / 540);
        this.add(addFormulaButton);
        addFormulaButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                if (!formulaField.getText().equals(""))
                {
                    Formula formula = new Formula(formulaField.getText(), extraVariables);
                    HashMap<String, ArrayList<Formula>> allAttributeFormulas = controller.getUserInterface().getAllAbiliyFormulas().get(abilityName);
                    if (allAttributeFormulas == null)
                    {
                        controller.getUserInterface().getAllAbiliyFormulas().put(abilityName, new HashMap<>());
                    }

                    ArrayList<Formula> formulas = controller.getUserInterface().getAllAbiliyFormulas().get(abilityName).get(formulaAttributeComboBox.getSelectedItem());
                    if (formulas == null)
                    {
                        controller.getUserInterface().getAllAbiliyFormulas().get(abilityName).put((String) formulaAttributeComboBox.getSelectedItem(), new ArrayList<>());
                    }

                    controller.getUserInterface().getAllAbiliyFormulas().get(abilityName).get((String) formulaAttributeComboBox.getSelectedItem()).add(formula);
                    formulaField.setText("");
                    formulaAttributeComboBox.removeItem(formulaAttributeComboBox.getSelectedItem());
                }
            }
        });

        //////////////////////

        JLabel upgradeXpLabel = new JLabel("<html><font size=\"3\">How much xp needed to upgrade?</font></html>");
        upgradeXpLabel.setBounds((width * 130) / 960, (height * 280) / 540, (width * 200) / 960, (height * 30) / 540);
        this.add(upgradeXpLabel);

        JTextField upgradeXpNum = new JTextField();
        upgradeXpNum.setBounds((width * 340) / 960, (height * 280) / 540, (width * 100) / 960, (height * 30) / 540);
        this.add(upgradeXpNum);

        //////////////////////

        JLabel cooldownLabel = new JLabel("<html><font size=\"3\">How much cooldown does this upgrade need?</font></html>");
        cooldownLabel.setBounds((width * 130) / 960, (height * 360) / 540, (width * 200) / 960, (height * 30) / 540);
        this.add(cooldownLabel);

        JTextField cooldownNum = new JTextField();
        cooldownNum.setBounds((width * 340) / 960, (height * 360) / 540, (width * 100) / 960, (height * 30) / 540);
        this.add(cooldownNum);

        //////////////////////

        saveButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                try
                {
                    int num = Integer.parseInt(upgradeXpNum.getText());
                    try
                    {
                        int num2 = Integer.parseInt(cooldownNum.getText());
                        ArrayList<Integer> upgradeXps = controller.getUserInterface().getAllAbilityUpgradeXPs().get(abilityName);
                        if (upgradeXps == null)
                        {
                            controller.getUserInterface().getAllAbilityUpgradeXPs().put(abilityName, new ArrayList<>());
                        }
                        controller.getUserInterface().getAllAbilityUpgradeXPs().get(abilityName).add(Integer.parseInt(upgradeXpNum.getText()));

                        ArrayList<Integer> cooldowns = controller.getUserInterface().getAllAbilityCooldowns().get(abilityName);
                        if (cooldowns == null)
                        {
                            controller.getUserInterface().getAllAbilityCooldowns().put(abilityName, new ArrayList<>());
                        }
                        controller.getUserInterface().getAllAbilityCooldowns().get(abilityName).add(Integer.parseInt(cooldownNum.getText()));

                        if (upgradeNum != currentUpgrade)
                        {
                            controller.setPanel(new UpgradeCustomUI(upgradeNum, currentUpgrade + 1, abilityName, controller));
                        }
                        else
                        {
                            controller.setPanel(MainCustomPanel.getInstance());
                        }
                    }
                    catch (Exception e2)
                    {
                        cooldownNum.setText("Invalid Input");
                    }
                }
                catch (Exception e1)
                {
                    upgradeXpNum.setText("Invalid Input");
                }
            }
        });
    }
}
