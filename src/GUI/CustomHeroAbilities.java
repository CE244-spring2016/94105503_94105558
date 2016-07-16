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
 * Created by ruhollah on 7/14/2016.
 */
public class CustomHeroAbilities extends JPanel
{
    private Controller controller;
    private String heroName;
    private String className;
    private int width = (int) (Toolkit.getDefaultToolkit().getScreenSize().getWidth() * 2) / 8;
    private int height = (int) (Toolkit.getDefaultToolkit().getScreenSize().getHeight() * 2) / 5;
    private int currentUpgrade = 1;
    private JLabel abilityUpdatesLabel;
    private JComboBox requirementsBox;
    private JComboBox abilityBox;
    private JLabel requirementsLabel;
    private JButton addAbilityButton;
    private JLabel abilityLabel;
    private JButton addRequirementButton;
    private JButton doneButton;
    private JButton endButton;

    public CustomHeroAbilities(Controller controller, String heroName, String className)
    {
        this.controller = controller;
        this.heroName = heroName;
        this.className = className;
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
        abilityLabel = new JLabel("<html><font size=\"3\">Ability name</font></html>");
        abilityLabel.setBounds((width * 200) / 720, (height * 100) / 648, (width * 150) / 720, (height * 30) / 648);
        this.add(abilityLabel);

        ArrayList<String> allAbilities = new ArrayList<>();
        allAbilities.addAll(controller.getUserInterface().getAbilityNames());
        if (controller.getUserInterface().getHeroClassAbilities().get(className) != null)
        {
            allAbilities.removeAll(controller.getUserInterface().getHeroClassAbilities().get(className));
        }

        abilityBox = new JComboBox(allAbilities.toArray());
        abilityBox.setBounds((width * 350) / 720, (height * 100) / 648, (width * 150) / 720, (height * 30) / 648);
        this.add(abilityBox);


        addAbilityButton = new JButton("Add");
        addAbilityButton.setBounds((width * 510) / 720, (height * 100) / 648, (width * 100) / 720, (height * 30) / 648);
        addAbilityButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                ArrayList<String> heroAbilities = controller.getUserInterface().getHerosAndTheirAbilities().get(heroName);
                if (heroAbilities == null)
                {
                    controller.getUserInterface().getHerosAndTheirAbilities().put(heroName, new ArrayList<>());
                }
                controller.getUserInterface().getHerosAndTheirAbilities().get(heroName).add((String) abilityBox.getSelectedItem());
                updateRequirementBox();
                changeTopVisibility(false);
                changeBottomVisibility(true);
            }
        });
        this.add(addAbilityButton);


        abilityUpdatesLabel = new JLabel("Update 1");
        abilityUpdatesLabel.setBounds((width * 300) / 720, (height * 180) / 648, (width * 100) / 720, (height * 30) / 648);
        this.add(abilityUpdatesLabel);
        abilityUpdatesLabel.setVisible(false);


        requirementsLabel = new JLabel("Requirement");
        requirementsLabel.setBounds((width * 100) / 720, (height * 220) / 648, (width * 100) / 720, (height * 30) / 648);
        requirementsLabel.setVisible(false);
        this.add(requirementsLabel);


        updateRequirementBox();
        this.add(requirementsBox);
        requirementsBox.setVisible(false);

        addRequirementButton = new JButton("Add");
        addRequirementButton.setBounds((width * 610) / 720, (height * 220) / 648, (width * 100) / 720, (height * 30) / 648);
        addRequirementButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                String chosenAbilityName = (String) abilityBox.getSelectedItem();

                HashMap<String, ArrayList<HashMap<String, Integer>>> heroAbilityData = controller.getUserInterface().getAllHeroRequiredAbilities().get(heroName);
                if (heroAbilityData == null)
                {
                    controller.getUserInterface().getAllHeroRequiredAbilities().put(heroName, new HashMap<>());
                    heroAbilityData = controller.getUserInterface().getAllHeroRequiredAbilities().get(heroName);
                }

                ArrayList<HashMap<String, Integer>> abilityData = heroAbilityData.get(chosenAbilityName);
                if (abilityData == null)
                {
                    heroAbilityData.put(chosenAbilityName, new ArrayList<>());
                    abilityData = heroAbilityData.get(chosenAbilityName);
                }

                if (abilityData.size() < currentUpgrade)
                {
                    abilityData.add(new HashMap<>());
                }
                HashMap<String, Integer> requirements = abilityData.get(currentUpgrade - 1);
                String requirementName = ((String) requirementsBox.getSelectedItem()).split(" ")[4];
                int requirementNum = Integer.parseInt(((String) requirementsBox.getSelectedItem()).split(" ")[1]);
                requirements.put(requirementName, requirementNum);
                requirementsBox.removeItem(requirementsBox.getSelectedItem());
            }
        });
        addRequirementButton.setVisible(false);
        this.add(addRequirementButton);

        doneButton = new JButton("Done");
        doneButton.setBounds((width * 200) / 720, (height * 260) / 648, (width * 100) / 720, (height * 30) / 648);
        doneButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                int maxUpgrade = controller.getUserInterface().getAllAbilityUpgradeXPs().get(abilityBox.getSelectedItem()).size();
                killTheNulls();
                if (maxUpgrade == currentUpgrade)
                {
                    currentUpgrade = 1;
                    changeTopVisibility(true);
                    changeBottomVisibility(false);
                    abilityBox.removeItem(abilityBox.getSelectedItem());
                } else
                {
                    currentUpgrade++;
                    updateRequirementBox();
                }
                abilityUpdatesLabel.setText("Upgrade " + currentUpgrade);
            }
        });
        doneButton.setVisible(false);
        this.add(doneButton);

        endButton = new JButton("End");
        endButton.setBounds((width * 200) / 720, (height * 300) / 648, (width * 100) / 720, (height * 30) / 648);
        endButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                controller.setPanel(MainCustomPanel.getInstance());
            }
        });
        this.add(endButton);
    }

    private void killTheNulls()
    {
        ArrayList<HashMap<String, Integer>> antiNullList = new ArrayList<>();
        antiNullList.add(new HashMap<>());
        HashMap<String, ArrayList<HashMap<String, Integer>>> antiNullMap = new HashMap<>();
        antiNullMap.put((String) abilityBox.getSelectedItem(), antiNullList);
        if (controller.getUserInterface().getAllHeroRequiredAbilities().get(heroName) == null)
        {
            controller.getUserInterface().getAllHeroRequiredAbilities().put(heroName, antiNullMap);
        }
        else if (controller.getUserInterface().getAllHeroRequiredAbilities().get(heroName).get(abilityBox.getSelectedItem()) == null)
        {
            controller.getUserInterface().getAllHeroRequiredAbilities().get(heroName).putAll(antiNullMap);
        }
    }

    private void changeBottomVisibility(boolean situation)
    {
        abilityUpdatesLabel.setVisible(situation);
        requirementsBox.setVisible(situation);
        requirementsLabel.setVisible(situation);
        addRequirementButton.setVisible(situation);
        doneButton.setVisible(situation);
//        endButton.setVisible(situation);
    }

    public void changeTopVisibility(boolean situation)
    {
        abilityLabel.setVisible(situation);
        abilityBox.setVisible(situation);
        addAbilityButton.setVisible(situation);
        endButton.setVisible(situation);
    }

    private void updateRequirementBox()
    {
        ArrayList<String> wantedList = new ArrayList<>();
        ArrayList<String> abilities = new ArrayList<>();
        abilities.addAll(controller.getUserInterface().getAbilityNames());
        abilities.remove(abilityBox.getSelectedItem());
        for (String abilityName : abilities)
        {
            int upgradeNum = controller.getUserInterface().getAllAbilityUpgradeXPs().get(abilityName).size();
            for (int i = 0; i < upgradeNum; i++)
            {
                wantedList.add("Upgrade " + (i + 1) + " of ability " + abilityName);
            }
        }
        if (this.isAncestorOf(requirementsBox))
        {
            this.remove(requirementsBox);
        }
        requirementsBox = new JComboBox(wantedList.toArray());
        requirementsBox.setBounds((width * 200) / 720, (height * 220) / 648, (width * 400) / 720, (height * 30) / 648);
        this.setVisible(true);
        this.add(requirementsBox);
//        this.add(requirementsBox);
    }
}
