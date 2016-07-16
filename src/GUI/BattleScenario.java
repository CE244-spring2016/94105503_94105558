package GUI;

import Controller.NetworkScenario;
import Controller.UltimateImage;
import Exceptions.*;
import Model.Hero;
import Model.NetworkHandler;
import javafx.util.Pair;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.Semaphore;

/**
 * Created by ruhollah on 6/30/2016.
 */
public class BattleScenario
{
    private JPanel panel = new JPanel();
    private BattleTextBox textBox;
    private GamePanel gamePanel;
    private Controller controller;
    private BufferedImage backGround;
    private ArrayList<HeroSprite> heroSprites = new ArrayList<>();
    private ArrayList<EnemySprite> enemySprites = new ArrayList<>();
    private int width = (int) (Toolkit.getDefaultToolkit().getScreenSize().getWidth() * 2) / 3;
    private int height = (int) (Toolkit.getDefaultToolkit().getScreenSize().getHeight() * 2) / 3;
    private int enemyWidth;
    private int enemyHeight;
    private int heroWidth;
    private int heroHeight;
    private int id;

    private int badChance = 0;
    private int chance;
    private int lolPort = 2001; // LOL THIS IS NOT A JOKE
    private int turn;
    private Semaphore semaphore = new Semaphore(0);
    private boolean isNetwork = false;

    private UltimateImage selectedHero;
    private UltimateImage selectedTarget;
    private String selectedItem;
    private String selectedAbility;
    private JLabel timerLabel;
    private int timerNum;

    public BattleScenario(int id, ArrayList<HeroSprite> heroSprites, GamePanel gamePanel, BufferedImage backGround)
    {
        this.gamePanel = gamePanel;
        this.id = id;
        this.heroSprites = heroSprites;
        this.backGround = backGround;
        this.controller = gamePanel.getController();
        panel.setLayout(null);
        panel.setSize(width, height);
        panel.setPreferredSize(new Dimension(width, height));
        createEnemySprites();

        JButton closeButton = new JButton("close");
        closeButton.setBounds(850, 500, 100, 100);
        closeButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
//                Controller controller = controller;
//                controller.setPanel(gamePanel);
                end();
            }
        });
        panel.add(closeButton);

        enemyHeight = height / 7;
        heroHeight = height / 7;

        if (enemySprites.size() <= 12)
        {
            enemyWidth = width / 12;
        } else
        {
            int n = (enemySprites.size() / 4) + 1;
            enemyWidth = width / (4 * n);
        }

        if (heroSprites.size() <= 12)
        {
            heroWidth = width / 12;
        } else
        {
            int n = (heroSprites.size() / 4) + 1;
            heroWidth = width / (4 * n);
        }

        showHeros();
        showEnemies();
        showTextBox();
        showBackground();
    }

    public BattleScenario(ArrayList<HeroSprite> heroSprites, int chance, BufferedImage backGround, Controller controller)
    {
        this.heroSprites = heroSprites;
        this.backGround = backGround;
        this.turn = controller.getNetworkScenario().getChoice();
        this.controller = controller;
        this.chance = chance;
        this.isNetwork = true;
        panel.setLayout(null);
        panel.setSize(width, height);
        panel.setPreferredSize(new Dimension(width, height));

        timerLabel = new JLabel();
        timerLabel.setBounds(850, 0, 100, 100);
        timerLabel.setText("<html><font size=\"10\">" + timerNum + "</font></html>");
        panel.add(timerLabel);

        JButton closeButton = new JButton("close");
        closeButton.setBounds(850, 500, 100, 100);
        closeButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
//                Controller controller = controller;
//                controller.setPanel(gamePanel);
                end();
            }
        });
        panel.add(closeButton);

        enemyHeight = height / 7;
        enemyWidth = width / 12;

        heroHeight = height / 7;
        heroWidth = width / 12;

        showHeros();
        showOpponentHeroes();
        showTextBox();
        showBackground();
        showStarter();
//        startFighting();
    }

    public void startFighting()
    {
        new Thread(new Runnable()
        {
            @Override
            public void run()
            {
                while (true)
                {
//                            ExecutorService service = Executors.newSingleThreadExecutor();
                    controller.getNetworkScenario().heroEPRefills();
                    if (turn == badChance)
                    {
                        textBox.setScrollSituation(BattleTextBox.ScrollSituation.OpponentNetworkTurn);
                        waitforOther();
                        textBox.setScrollSituation(BattleTextBox.ScrollSituation.Default);
                        controller.getNetworkScenario().heroEPRefills();
                        if (turn == 1)
                            turn--;
                        else if (turn == 0)
                            turn++;
                    } else if (turn == chance)
                    {
                        if (lolPort == 2001 && controller.getNetworkScenario().getChoice() == chance)
                        {
                            controller.getNetworkScenario().costEP(0);
                        }

                        TimeThread timeThread = new TimeThread();
                        timeThread.start();
                        fightOther();
//                        try
//                        {
//                            timeThread.join();
//                        } catch (InterruptedException e)
//                        {
//                            e.printStackTrace();
//                        }
//                                service.shutdown();
                        controller.getNetworkScenario().heroEPRefills();

                        if (turn == 1)
                            turn--;
                        else if (turn == 0)
                            turn++;
                    }

                    if (controller.getNetworkScenario().getChoice() == 0)
                    {
                        controller.getNetworkScenario().getHostJoin().setServer(lolPort);
                        lolPort++;
                        try
                        {
                            Thread.sleep(100);
                        } catch (InterruptedException e)
                        {
                            e.printStackTrace();
                        }
                    }

                    if (controller.getNetworkScenario().getChoice() == 1)
                    {
                        controller.getNetworkScenario().getHostJoin().setClient(lolPort, controller.getNetworkScenario().getIP());
//                hostJoin.setClient(i);
                        lolPort++;
                    }
                    controller.getNetworkScenario().setIn(controller.getNetworkScenario().getHostJoin().getIn());
                    controller.getNetworkScenario().setOut(controller.getNetworkScenario().getHostJoin().getOut());

                }
            }
        }).start();
    }

    private void waitforOther()
    {
        ArrayList<Hero> heros = controller.getNetworkScenario().getHeros();
        controller.getNetworkScenario().getCommonMsg().setEnemyHeros(heros);
        controller.getNetworkScenario().getNtwhandler().setCommonMsg(controller.getNetworkScenario().getCommonMsg());
        controller.getNetworkScenario().getNtwhandler().send();
        controller.getNetworkScenario().getNtwhandler().receive();
        controller.getNetworkScenario().setCommonMsg(controller.getNetworkScenario().getNtwhandler().getCommonMsg());
        controller.getNetworkScenario().setHeros(controller.getNetworkScenario().getCommonMsg().getHeros());
        controller.getNetworkScenario().setEnemyHeros(controller.getNetworkScenario().getCommonMsg().getEnemyHeros());
        if (controller.getNetworkScenario().getCommonMsg().getWinner() != -1)
        {
//            System.out.println("You Lose");
//            System.exit(0);
            ArrayList<String> loseMessage = new ArrayList<>();
            loseMessage.add("You Lose!");
            textBox.setScrollSituation(BattleTextBox.ScrollSituation.GameOver);
            textBox.showMoveExplanation(loseMessage);
            System.exit(0);
        }
    }

    private void fightOther()
    {
        NetworkScenario networkScenario = controller.getNetworkScenario();
        NetworkHandler networkHandler = networkScenario.getNtwhandler();

        networkHandler.receive();
        networkScenario.setCommonMsg(networkHandler.getCommonMsg());
        networkScenario.setEnemyHeros(networkScenario.getCommonMsg().getEnemyHeros());

        //  startFightings();
        try
        {
            semaphore.acquire();
        } catch (InterruptedException e)
        {
            e.printStackTrace();
        }

        if (networkScenario.getCommonMsg().getWinner() == networkScenario.getChoice())
        {
            networkScenario.getCommonMsg().setEnemyHeros(networkScenario.getHeros());
            networkScenario.getCommonMsg().setHeros(networkScenario.getHeros());
            networkHandler.setCommonMsg(networkScenario.getCommonMsg());
            networkHandler.send();

            ArrayList<String> list = new ArrayList<>();
            list.add("You Win");
//            textBox.setScrollSituation(BattleTextBox.ScrollSituation.NetworkWinner);
            textBox.showMoveExplanation(list);
            try
            {
                Thread.sleep(3000);
                System.exit(0);
            } catch (InterruptedException e)
            {
                e.printStackTrace();
            }
//            System.out.println("You Win");
//            System.exit(0);
        }
        networkScenario.getCommonMsg().setEnemyHeros(networkScenario.getHeros());
        networkScenario.getCommonMsg().setHeros(networkScenario.getEnemyHeros());
        //commonMsg.setMessages(messages);
        networkHandler.setCommonMsg(networkScenario.getCommonMsg());
        networkHandler.send();
    }

    private void showStarter()
    {
        lolPort = 2001;
        turn = controller.getNetworkScenario().choice;
        chance = controller.getNetworkScenario().getCommonMsg().getChance();
        badChance = 0;
        if (chance == 1)
        {
            badChance = 0;
        } else if (chance == 0)
        {
            badChance = 1;
        }
        if (chance == controller.getNetworkScenario().getChoice())
        {
            ArrayList<String> startingMessage = new ArrayList<>();
            startingMessage.add("You are the starter");
            textBox.showMoveExplanation(startingMessage);
        } else
        {
            ArrayList<String> startingMessage = new ArrayList<>();
            startingMessage.add("He is the starter");
            textBox.showMoveExplanation(startingMessage);
        }
    }

    private void showOpponentHeroes()
    {
        for (int i = 0; i < heroSprites.size(); i++)
        {
            HeroLabel label = new HeroLabel(heroSprites.get(i).getAllImages());
            label.setBounds(width * 1 / 10, (i % 4) * (heroHeight + 20), heroWidth, heroHeight);
            label.addMouseListener(new MouseListener()
            {
                @Override
                public void mouseClicked(MouseEvent e)
                {
                    try
                    {
                        if (textBox.getScrollSituation() == BattleTextBox.ScrollSituation.Attack)
                        {
                            selectedTarget = label.getUltimateImage();

                            controller.networkRegularAttack(selectedHero, selectedTarget);

                            textBox.getScrollPane().setVisible(false);
                            ArrayList<String> thisTurnLog = controller.getTurnLog();
                            textBox.showMoveExplanation(thisTurnLog);
                        } else if (textBox.getScrollSituation() == BattleTextBox.ScrollSituation.ChooseAbilityEnemyTarget)
                        {
                            selectedTarget = label.getUltimateImage();
//                            controller.useSingleTargetedAbility(selectedHero, selectedTarget, label.getId(), getId() - 1, selectedAbility);

                            textBox.getScrollPane().setVisible(false);
                            ArrayList<String> thisTurnLog = controller.getTurnLog();
                            textBox.showMoveExplanation(thisTurnLog);
                        } else if (textBox.getScrollSituation() == BattleTextBox.ScrollSituation.ChooseItemEnemyTarget)
                        {
                            selectedTarget = label.getUltimateImage();
//                            controller.useSingleTargetedItem(selectedHero, selectedTarget, label.getId(), getId() - 1, selectedItem);
                        }
                    } catch (NoMoreUpgradeException | NotStrongEnoughException | NotEnoughXPException | NotEnoughMoneyException |
                            AbilityCooldownException | AbilityNotAcquieredException | FullInventoryException |
                            NotEnoughRequiredAbilitiesException e1)
                    {
                        ArrayList<String> exceptionMessage = new ArrayList<>();
                        exceptionMessage.add(e1.getMessage());
                        textBox.showMoveExplanation(exceptionMessage);
//                        textBox.setScrollSituation(BattleTextBox.ScrollSituation.Default);
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
            ImageIcon icon = (ImageIcon) heroSprites.get(i).getFirstRightImage().getIcon();
            BufferedImage bufferedHero = (BufferedImage) icon.getImage();
            Image resizedHero = bufferedHero.getScaledInstance(label.getWidth(), label.getHeight(), Image.SCALE_SMOOTH);
            label.setIcon(new ImageIcon(resizedHero));
            panel.add(label);
        }
    }

    private void createEnemySprites()
    {
        ArrayList<Pair<UltimateImage, Integer>> enemyImageAndId = controller.getEnemyGroupImage(id - 1);
        for (Pair<UltimateImage, Integer> enemy : enemyImageAndId)
        {
            EnemySprite enemySprite = new EnemySprite(enemy.getKey(), enemy.getValue());
            enemySprites.add(enemySprite);
        }
    }

    private void showTextBox()
    {
        BufferedImage textBoxImage = null;
        try
        {
            textBoxImage = ImageIO.read(new File("Main Pics/BattleTextBox/BattleTextBox.png"));
            Image resizedTextBox = textBoxImage.getScaledInstance(width, (height - enemyHeight * 4 - 80), Image.SCALE_SMOOTH);
            textBox = new BattleTextBox(0, enemyHeight * 4 + 80, width, (height - enemyHeight * 4 - 80), controller, this);
            textBox.setBoxImage(resizedTextBox);
//            textBox.addText("Choose a hero");
//            textBox.setScrollSituation(BattleTextBox.ScrollSituation.Default);
            ArrayList<String> possibleMoves = new ArrayList<>();
            textBox.addScrollObjects(possibleMoves);
            panel.add(textBox.getDoneButton());
            panel.add(textBox.getScrollPane());
            panel.add(textBox.getTextLabel());
            panel.add(textBox.getBox());
//            controller.enemyIntroduction(getId() - 1);
//            textBox.showMoveExplanation(controller.getTurnLog());
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    private void showBackground()
    {
        JLabel label = new JLabel();
        label.setBounds(0, 0, width, height);
        Image resizedBackground = backGround.getScaledInstance(label.getWidth(), label.getHeight(), Image.SCALE_SMOOTH);
        label.setIcon(new ImageIcon(resizedBackground));
        panel.add(label);
    }

    private void showEnemies()
    {
        if (enemySprites.size() != 1)
        {
            for (int i = 0; i < enemySprites.size(); i++)
            {
                EnemyLabel label = new EnemyLabel(enemySprites.get(i).getId());
                label.setBounds(enemyWidth * (i / 4), (i % 4) * (enemyHeight + 20), enemyWidth, enemyHeight);
                Image resizedEnemy = enemySprites.get(i).getEnemyImage().makeImage().getScaledInstance(label.getWidth(), label.getHeight(), Image.SCALE_SMOOTH);
                label.setIcon(new ImageIcon(resizedEnemy));
                label.setUltimateImage(enemySprites.get(i).getEnemyImage());
                label.addMouseListener(new MouseListener()
                {
                    @Override
                    public void mouseClicked(MouseEvent e)
                    {
                        try
                        {
                            if (textBox.getScrollSituation() == BattleTextBox.ScrollSituation.Attack)
                            {
                                selectedTarget = label.getUltimateImage();

                                controller.regularAttack(selectedHero, selectedTarget, label.getId(), getId() - 1);

                                textBox.getScrollPane().setVisible(false);
                                ArrayList<String> thisTurnLog = controller.getTurnLog();
                                textBox.showMoveExplanation(thisTurnLog);
                            } else if (textBox.getScrollSituation() == BattleTextBox.ScrollSituation.ChooseAbilityEnemyTarget)
                            {
                                selectedTarget = label.getUltimateImage();
                                controller.useSingleTargetedAbility(selectedHero, selectedTarget, label.getId(), getId() - 1, selectedAbility);

                                textBox.getScrollPane().setVisible(false);
                                ArrayList<String> thisTurnLog = controller.getTurnLog();
                                textBox.showMoveExplanation(thisTurnLog);
                            } else if (textBox.getScrollSituation() == BattleTextBox.ScrollSituation.ChooseItemEnemyTarget)
                            {
                                selectedTarget = label.getUltimateImage();
                                controller.useSingleTargetedItem(selectedHero, selectedTarget, label.getId(), getId() - 1, selectedItem);
                            }
                            else if (textBox.getScrollSituation() == BattleTextBox.ScrollSituation.Info)
                            {
                                String enemyInfo = "";
                                enemyInfo = controller.findEnemyInfo(label.getUltimateImage(), id - 1);
                                ArrayList<String> organizedInfo = new ArrayList<String>();
                                for (String infoPart : enemyInfo.split("\\n"))
                                {
                                    organizedInfo.add(infoPart);
                                }
                                textBox.showMoveExplanation(organizedInfo);
                            }
                        } catch (NoMoreUpgradeException | NotStrongEnoughException | NotEnoughXPException | NotEnoughMoneyException |
                                AbilityCooldownException | AbilityNotAcquieredException | FullInventoryException |
                                NotEnoughRequiredAbilitiesException e1)
                        {
                            ArrayList<String> exceptionMessage = new ArrayList<>();
                            exceptionMessage.add(e1.getMessage());
                            textBox.showMoveExplanation(exceptionMessage);
//                        textBox.setScrollSituation(BattleTextBox.ScrollSituation.Default);
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
                panel.add(label);
                enemySprites.get(i).setEnemyLabel(label);
            }
        } else
        {
            EnemyLabel label = new EnemyLabel(enemySprites.get(0).getId());
            label.setBounds(enemyWidth, enemyHeight + 20, 2 * enemyWidth, 2 * enemyHeight);
            Image resizedEnemy = enemySprites.get(0).getEnemyImage().makeImage().getScaledInstance(label.getWidth(), label.getHeight(), Image.SCALE_SMOOTH);
            label.setIcon(new ImageIcon(resizedEnemy));
            label.setUltimateImage(enemySprites.get(0).getEnemyImage());
            label.addMouseListener(new MouseListener()
            {
                @Override
                public void mouseClicked(MouseEvent e)
                {
                    try
                    {
                        if (textBox.getScrollSituation() == BattleTextBox.ScrollSituation.Attack)
                        {
                            selectedTarget = label.getUltimateImage();

                            controller.regularAttack(selectedHero, selectedTarget, label.getId(), getId() - 1);

                            textBox.getScrollPane().setVisible(false);
                            ArrayList<String> thisTurnLog = controller.getTurnLog();
                            textBox.showMoveExplanation(thisTurnLog);
                        } else if (textBox.getScrollSituation() == BattleTextBox.ScrollSituation.ChooseAbilityEnemyTarget)
                        {
                            selectedTarget = label.getUltimateImage();
                            controller.useSingleTargetedAbility(selectedHero, selectedTarget, label.getId(), getId() - 1, selectedAbility);

                            textBox.getScrollPane().setVisible(false);
                            ArrayList<String> thisTurnLog = controller.getTurnLog();
                            textBox.showMoveExplanation(thisTurnLog);
                        } else if (textBox.getScrollSituation() == BattleTextBox.ScrollSituation.ChooseItemEnemyTarget)
                        {
                            selectedTarget = label.getUltimateImage();
                            controller.useSingleTargetedItem(selectedHero, selectedTarget, label.getId(), getId() - 1, selectedItem);
                        }
                    } catch (NoMoreUpgradeException | NotStrongEnoughException | NotEnoughXPException | NotEnoughMoneyException |
                            AbilityCooldownException | AbilityNotAcquieredException | FullInventoryException |
                            NotEnoughRequiredAbilitiesException e1)
                    {
                        ArrayList<String> exceptionMessage = new ArrayList<>();
                        exceptionMessage.add(e1.getMessage());
                        textBox.showMoveExplanation(exceptionMessage);
//                        textBox.setScrollSituation(BattleTextBox.ScrollSituation.Default);
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
            panel.add(label);
            enemySprites.get(0).setEnemyLabel(label);
        }
    }

    private void showHeros()
    {
        for (int i = 0; i < heroSprites.size(); i++)
        {
            HeroLabel label = new HeroLabel(heroSprites.get(i).getAllImages());
            label.setBounds(width * 4 / 5, (i % 4) * (heroHeight + 20), heroWidth, heroHeight);
            label.addMouseListener(new MouseListener()
            {
                @Override
                public void mouseClicked(MouseEvent e)
                {
                    try
                    {
                        if (textBox.getScrollSituation() == BattleTextBox.ScrollSituation.Default ||
                                textBox.getScrollSituation() == BattleTextBox.ScrollSituation.Ability ||
                                textBox.getScrollSituation() == BattleTextBox.ScrollSituation.Move ||
                                textBox.getScrollSituation() == BattleTextBox.ScrollSituation.Item)
                        {
                            selectedHero = label.getUltimateImage();
                            textBox.addText("What should " + controller.findHeroName(selectedHero) + " do?");
                            ArrayList<String> heroPossibleMoves = new ArrayList<>();
                            heroPossibleMoves.add("Attack?");
                            heroPossibleMoves.add("Cast An Ability?");
                            heroPossibleMoves.add("Use An Item?");
                            heroPossibleMoves.add("Info");
                            textBox.addScrollObjects(heroPossibleMoves);
                            textBox.setScrollSituation(BattleTextBox.ScrollSituation.Move);
                        } else if (textBox.getScrollSituation() == BattleTextBox.ScrollSituation.ChooseAbilityHeroTarget)
                        {
                            selectedTarget = label.getUltimateImage();
                            controller.useSingleTargetedAbility(selectedHero, selectedTarget, getId() - 1, selectedAbility);

                            textBox.getScrollPane().setVisible(false);
                            ArrayList<String> thisTurnLog = controller.getTurnLog();
                            textBox.showMoveExplanation(thisTurnLog);
                        } else if (textBox.getScrollSituation() == BattleTextBox.ScrollSituation.ChooseItemHeroTarget)
                        {
                            selectedTarget = label.getUltimateImage();
                            controller.useSingleTargetedItem(selectedHero, selectedTarget, getId() - 1, selectedItem);

                            textBox.getScrollPane().setVisible(false);
                            ArrayList<String> thisTurnLog = controller.getTurnLog();
                            textBox.showMoveExplanation(thisTurnLog);
                        } else if (textBox.getScrollSituation() == BattleTextBox.ScrollSituation.Info)
                        {
                            String heroInfo = "";
                            if (isNetwork)
                            {
                                heroInfo = controller.findNetworkHeroInfo(label.getUltimateImage());
                            }
                            else
                            {
                                heroInfo = controller.findHeroInfo(label.getUltimateImage());
                            }
                            ArrayList<String> organizedInfo = new ArrayList<String>();
                            for (String infoPart : heroInfo.split("\\n"))
                            {
                                organizedInfo.add(infoPart);
                            }
                            textBox.showMoveExplanation(organizedInfo);
                        }
                    } catch (FullInventoryException | AbilityNotAcquieredException | NotStrongEnoughException |
                            NotEnoughMoneyException | NotEnoughRequiredAbilitiesException | NoMoreUpgradeException |
                            AbilityCooldownException | NotEnoughXPException e1)
                    {
                        ArrayList<String> exceptionMessage = new ArrayList<>();
                        exceptionMessage.add(e1.getMessage());
                        textBox.showMoveExplanation(exceptionMessage);
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
            ImageIcon icon = (ImageIcon) heroSprites.get(i).getFirstLeftImage().getIcon();
            BufferedImage bufferedHero = (BufferedImage) icon.getImage();
            Image resizedHero = bufferedHero.getScaledInstance(label.getWidth(), label.getHeight(), Image.SCALE_SMOOTH);
            label.setIcon(new ImageIcon(resizedHero));
            panel.add(label);
        }
    }

    public JPanel getPanel()
    {
        return panel;
    }

    public void setPanel(JPanel panel)
    {
        this.panel = panel;
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public String getSelectedAbility()
    {
        return selectedAbility;
    }

    public void setSelectedAbility(String selectedAbility)
    {
        this.selectedAbility = selectedAbility;
    }

    public void startEnemyIntroduction()
    {
        controller.enemyIntroduction(getId() - 1);
        controller.earlyBossEffects(getId() - 1);
        textBox.showMoveExplanation(controller.getTurnLog());
    }

    public void deleteDeadEnemies()
    {
        ArrayList<EnemySprite> deadEnemies = new ArrayList<>();

        for (EnemySprite enemySprite : enemySprites)
        {
            UltimateImage ultimateImage = enemySprite.getEnemyImage();
            if (!controller.isAlive(ultimateImage, getId() - 1, enemySprite.getId()))
            {
                deadEnemies.add(enemySprite);
                panel.remove(enemySprite.getEnemyLabel());
                panel.repaint();
            }
        }

        enemySprites.removeAll(deadEnemies);
        if (enemySprites.size() == 0)
        {
            controller.endBattle(getId() - 1);
            textBox.setScrollSituation(BattleTextBox.ScrollSituation.End);
            textBox.showMoveExplanation(controller.getTurnLog());
        }
    }

    public void end()
    {
        if (controller.getGameScenario().getEnemyGroups().size() != id)
        {
            controller.setPanel(gamePanel);
        } else
        {
            controller.setPanel(gamePanel.getFinalStoryPanel());
        }
    }

    public void setSelectedItem(String selectedItem)
    {
        this.selectedItem = selectedItem;
    }

    public void gameOver()
    {
        gamePanel.gameOver();
    }

    public boolean isNetwork()
    {
        return isNetwork;
    }

    public void setNetwork(boolean network)
    {
        isNetwork = network;
    }

    public UltimateImage getSelectedHero()
    {
        return selectedHero;
    }

    public void setSelectedHero(UltimateImage selectedHero)
    {
        this.selectedHero = selectedHero;
    }

    public Semaphore getSemaphore()
    {
        return semaphore;
    }

    public void setSemaphore(Semaphore semaphore)
    {
        this.semaphore = semaphore;
    }

    public class EnemyLabel extends JLabel
    {
        private Integer id;
        private UltimateImage ultimateImage;

        public EnemyLabel(Integer id)
        {
            this.id = id;
        }

        public Integer getId()
        {
            return id;
        }

        public void setId(Integer id)
        {
            this.id = id;
        }

        public UltimateImage getUltimateImage()
        {
            return ultimateImage;
        }

        public void setUltimateImage(UltimateImage ultimateImage)
        {
            this.ultimateImage = ultimateImage;
        }
    }

    class TimeThread extends Thread
    {
        @Override
        public void run()
        {
            for (int i = 0; i < 40; i++)
            {
                try
                {
                    sleep(1000);
                    timerLabel.setText("<html><font size=\"10\">" + (40 - i) + "</font></html>");
                } catch (InterruptedException e)
                {
                    e.printStackTrace();
                }
            }
            timerLabel.setText("<html><font size=\"10\">" + 40 + "</font></html>");
            if (semaphore.availablePermits() == 0)
                semaphore.release();
        }
    }
}
