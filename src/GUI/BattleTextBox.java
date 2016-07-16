package GUI;

import Exceptions.*;
import Model.Hero;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.concurrent.Semaphore;
import Controller.*;

/**
 * Created by ruhollah on 7/4/2016.
 */
public class BattleTextBox
{
    private Controller controller;
    private JLabel textLabel = new JLabel();
    private JLabel box = new JLabel();
    private MyScrollPane scrollPane = new MyScrollPane();
    private MyScrollPanel myScrollPanel = new MyScrollPanel();
    private JButton doneButton = new JButton();
    private int x;
    private int y;
    private int width;
    private int height;
    private final int OPTION_TEXT_SIZE;
    private BattleScenario battleScenario;
    private ScrollSituation scrollSituation = ScrollSituation.Default;

    public BattleTextBox(int x, int y, int width, int height, Controller controller, BattleScenario battleScenario)
    {
//        System.out.println(width + " " + height);
        this.battleScenario = battleScenario;
        this.controller = controller;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        OPTION_TEXT_SIZE = (10 * width * height) / (1920 * 384);
        doneButton.setText("<html><font size=\"10\" face=\"niagara solid\">End Turn</font></html>");
        doneButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                if (battleScenario.isNetwork())
                {
                    battleScenario.getSemaphore().release();
                }
                else
                {
                    controller.enemyTurn(battleScenario.getId() - 1);
                }
                ArrayList<String> turnLog = controller.getTurnLog();
                if (Hero.getImmortalityPotionNum() == -1)
                {
                    scrollSituation = ScrollSituation.GameOver;
                }
                showMoveExplanation(turnLog);
            }
        });
        doneButton.setBounds(width / 2 - (50 * width) / 1920, 0, (100 * width) / 1920, (100 * height) / 384);

        MyViewPort viewPort = new MyViewPort();
        viewPort.setView(myScrollPanel);
        scrollPane.setViewport(viewPort);
    }


    public void addText(String text)
    {
        textLabel.setText("<html><font size=\"10\">" + text + "</font></html>");
        textLabel.setBounds(this.x + width / 20, this.y, width / 2, height);
    }

    public void setBoxImage(Image resizedTextBox)
    {
        box.setIcon(new ImageIcon(resizedTextBox));
        box.setBounds(x, y, width, height);
    }

    public void addScrollObjects(ArrayList<String> data)
    {
        scrollPane.setVisible(true);
        scrollPane.setBounds(x + width / 2, y + (20 * width) / 1920, width / 2 - (20 * width) / 1920, height - (20 * height) / 384);
        myScrollPanel.removeAll();
        MyViewPort viewPort = new MyViewPort();

        for (int i = 0; i < data.size(); i++)
        {
            JLabel label = new JLabel("<html><font size=\"" + OPTION_TEXT_SIZE + "\" face=\"Chiller\">" + data.get(i) + "</font></html>");
            label.setBounds((i % 2) * width / 4, (i / 2) * (height / 3), width / 4 - (10 * width) / 1920, height / 2 - (20 * height) / 384);
            label.addMouseListener(new MouseListener()
            {
                @Override
                public void mouseClicked(MouseEvent e)
                {

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
                    label.setText("<html><font size=\"" + OPTION_TEXT_SIZE + "\" face=\"Pristina\"><b>" +
                            label.getText().substring(35 + Integer.toString(OPTION_TEXT_SIZE).length(), label.getText().indexOf("</font>")) +
                            "</b></font></html>");
                }

                @Override
                public void mouseExited(MouseEvent e)
                {
                    label.setText("<html><font size=\"" + OPTION_TEXT_SIZE + "\" face=\"Chiller\">" +
                            label.getText().substring(39 + Integer.toString(OPTION_TEXT_SIZE).length(), label.getText().indexOf("</b>")) +
                            "</font></html>");
                }
            });
            int finalI = i;
            label.addMouseListener(new MouseListener()
            {
                @Override
                public void mouseClicked(MouseEvent e)
                {
                    if(scrollSituation == ScrollSituation.Move)
                    {
                        if(data.get(finalI).equals("Cast An Ability?"))
                        {
                            addText("Which Ability?");
                            ArrayList<String> abilityNames = controller.findAbilityNamesOfAHero(battleScenario.getSelectedHero());
                            scrollSituation = ScrollSituation.Ability;
                            addScrollObjects(abilityNames);
                        }
                        else if(data.get(finalI).equals("Attack?"))
                        {
                            scrollSituation = ScrollSituation.Attack;
                            addText("Choose an enemy");
                            scrollPane.setVisible(false);
                        }
                        else if(data.get(finalI).equals("Use An Item?"))
                        {
                            addText("Which Item?");
                            ArrayList<String> itemNames = controller.findInBattleItemNamesOfAHero(battleScenario.getSelectedHero());
                            scrollSituation = ScrollSituation.Item;
                            addScrollObjects(itemNames);
                        }
                        else if (data.get(finalI).equals("Info"))
                        {
                            addText("Choose someone");
                            scrollSituation = ScrollSituation.Info;
                            scrollPane.setVisible(false);
                        }
                    }
                    else if(scrollSituation == ScrollSituation.Ability)
                    {
                        try{
                            String target = controller.getAbilityTarget(data.get(finalI));
                            if(target.equals("all enemies") || target.equals("all allies") || target.equals("everyone"))
                            {
                                UltimateImage selectedHero = battleScenario.getSelectedHero();
                                controller.useMultiTargetedAbility(selectedHero, battleScenario.getId(), data.get(finalI));

                                scrollPane.setVisible(false);
                                ArrayList<String> thisTurnLog = controller.getTurnLog();
                                showMoveExplanation(thisTurnLog);
                            }
                            else if(target.equals("an enemy") || target.equals("an ally"))
                            {
                                if(target.equals("an enemy"))
                                {
                                    scrollSituation = ScrollSituation.ChooseAbilityEnemyTarget;
                                    addText("Choose an enemy");
                                }
                                else
                                {
                                    scrollSituation = ScrollSituation.ChooseAbilityHeroTarget;
                                    addText("Choose a target hero");
                                }

                                battleScenario.setSelectedAbility(data.get(finalI));
                                scrollPane.setVisible(false);
                            }
                        } catch (FullInventoryException | AbilityNotAcquieredException | NotStrongEnoughException |
                                NotEnoughMoneyException | NoMoreUpgradeException | NotEnoughXPException |
                                AbilityCooldownException | NotEnoughRequiredAbilitiesException e1)
                        {
                            ArrayList<String> exceptionMessage = new ArrayList<>();
                            exceptionMessage.add(e1.getMessage());
                            showMoveExplanation(exceptionMessage);
                        }
                    }
                    else if (scrollSituation == ScrollSituation.Item)
                    {
                        try{
                            String target = controller.getItemTarget(data.get(finalI));
                            if(target.equals("all enemies") || target.equals("all allies") || target.equals("everyone"))
                            {
                                UltimateImage selectedHero = battleScenario.getSelectedHero();
                                controller.useMultiTargetedItem(selectedHero, battleScenario.getId(), data.get(finalI));

                                scrollPane.setVisible(false);
                                ArrayList<String> thisTurnLog = controller.getTurnLog();
                                showMoveExplanation(thisTurnLog);
                            }
                            else if(target.equals("an enemy") || target.equals("an ally"))
                            {
                                if(target.equals("an enemy"))
                                {
                                    scrollSituation = ScrollSituation.ChooseItemEnemyTarget;
                                    addText("Choose an enemy");
                                }
                                else
                                {
                                    scrollSituation = ScrollSituation.ChooseItemHeroTarget;
                                    addText("Choose a target hero");
                                }

                                battleScenario.setSelectedItem(data.get(finalI));
                                scrollPane.setVisible(false);
                            }
                        } catch (FullInventoryException | AbilityNotAcquieredException | NotStrongEnoughException |
                                NotEnoughMoneyException | NoMoreUpgradeException | NotEnoughXPException |
                                AbilityCooldownException | NotEnoughRequiredAbilitiesException e1)
                        {
                            ArrayList<String> exceptionMessage = new ArrayList<>();
                            exceptionMessage.add(e1.getMessage());
                            showMoveExplanation(exceptionMessage);
                        }
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

                }

                @Override
                public void mouseExited(MouseEvent e)
                {

                }
            });
            myScrollPanel.add(label);
        }

        viewPort.setView(myScrollPanel);
        scrollPane.setViewport(viewPort);

        if(data.size() > 4)
        {
            myScrollPanel.setPreferredSize(new Dimension(width / 2 - (20 * width) / 1920, (data.size() / 2 + data.size() % 2) * (height / 3)));
        }
        else if(data.size() == 0)
        {
            scrollPane.setVisible(false);
        }
    }

    public void showMoveExplanation(ArrayList<String> thisTurnLog)
    {
        if(scrollSituation != ScrollSituation.End && scrollSituation != ScrollSituation.GameOver)
        {
            scrollSituation = ScrollSituation.ShowingMessage;
        }
        doneButton.setVisible(false);
        scrollPane.setVisible(false);
        Semaphore semaphore = new Semaphore(0);

        Thread textShowingThread = new Thread(new Runnable()
        {
            @Override
            public void run()
            {
                for (String explanation : thisTurnLog)
                {
                    addText(explanation);
                    try
                    {
                        semaphore.acquire();
                    } catch (InterruptedException e)
                    {
                        e.printStackTrace();
                    }
                }

                textLabel.removeAll();
                if(scrollSituation == ScrollSituation.End)
                {
                    doneButton.setVisible(false);
                    battleScenario.end();
                }
                else if (scrollSituation == ScrollSituation.GameOver)
                {
                    battleScenario.gameOver();
                }
                addText("Choose a hero");
                if (!battleScenario.isNetwork())
                {
                    battleScenario.deleteDeadEnemies();
                }
                if(scrollSituation != ScrollSituation.End && scrollSituation != ScrollSituation.OpponentNetworkTurn)
                {
                    scrollSituation = ScrollSituation.Default;
                    doneButton.setVisible(true);
                }
            }
        });

        textShowingThread.start();

        box.addMouseListener(new MouseListener()
        {
            @Override
            public void mouseClicked(MouseEvent e)
            {
                semaphore.release();
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

            }

            @Override
            public void mouseExited(MouseEvent e)
            {

            }
        });
    }

    public JLabel getTextLabel()
    {
        return textLabel;
    }

    public void setTextLabel(JLabel textLabel)
    {
        this.textLabel = textLabel;
    }

    public JLabel getBox()
    {
        return box;
    }

    public void setBox(JLabel box)
    {
        this.box = box;
    }

    public MyScrollPane getScrollPane()
    {
        return scrollPane;
    }

    public void setScrollPane(MyScrollPane scrollPane)
    {
        this.scrollPane = scrollPane;
    }

    public ScrollSituation getScrollSituation()
    {
        return scrollSituation;
    }

    public void setScrollSituation(ScrollSituation scrollSituation)
    {
        this.scrollSituation = scrollSituation;
    }

    public JButton getDoneButton()
    {
        return doneButton;
    }

    public void setDoneButton(JButton doneButton)
    {
        this.doneButton = doneButton;
    }

    public class MyViewPort extends JViewport
    {
        public MyViewPort()
        {
            this.setOpaque(false);
            this.setPreferredSize(new Dimension(width / 2, height));
        }
    }

    public class MyScrollPanel extends JPanel
    {
        public MyScrollPanel()
        {
            this.setOpaque(false);
            this.setPreferredSize(new Dimension(width / 2, height));
            this.setLayout(null);
        }
    }

    public class MyScrollPane extends JScrollPane
    {
        public MyScrollPane()
        {
            this.setOpaque(false);
            this.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        }
    }

    enum ScrollSituation
    {
        Move, Ability, Attack, Default, ChooseAbilityEnemyTarget, ChooseAbilityHeroTarget,
        Item, ShowingMessage, End, ChooseItemEnemyTarget, ChooseItemHeroTarget, GameOver,
        OpponentNetworkTurn, YourNetworkTurn, Info
    }

}
