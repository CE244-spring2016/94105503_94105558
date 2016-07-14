package GUI;

import Exceptions.*;
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

/**
 * Created by ruhollah on 6/30/2016.
 */
public class BattleScenario
{
    private JPanel panel = new JPanel();
    private BattleTextBox textBox;
    private GamePanel gamePanel;
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
    private BufferedImage selectedHero;
    private BufferedImage selectedTarget;
    private String selectedItem;
    private String selectedAbility;

    public BattleScenario(int id, ArrayList<HeroSprite> heroSprites, GamePanel gamePanel, BufferedImage backGround)
    {
        this.gamePanel = gamePanel;
        this.id = id;
        this.heroSprites = heroSprites;
        this.backGround = backGround;
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
//                Controller controller = gamePanel.getController();
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

    private void createEnemySprites()
    {
        ArrayList<Pair<BufferedImage, Integer>> enemyImageAndId = gamePanel.getController().getEnemyGroupImage(id - 1);
        for (Pair<BufferedImage, Integer> enemy : enemyImageAndId)
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
            textBox = new BattleTextBox(0, enemyHeight * 4 + 80, width, (height - enemyHeight * 4 - 80), gamePanel.getController(), this);
            textBox.setBoxImage(resizedTextBox);
//            textBox.addText("Choose a hero");
//            textBox.setScrollSituation(BattleTextBox.ScrollSituation.Default);
            ArrayList<String> possibleMoves = new ArrayList<>();
            textBox.addScrollObjects(possibleMoves);
            panel.add(textBox.getDoneButton());
            panel.add(textBox.getScrollPane());
            panel.add(textBox.getTextLabel());
            panel.add(textBox.getBox());
//            gamePanel.getController().enemyIntroduction(getId() - 1);
//            textBox.showMoveExplanation(gamePanel.getController().getTurnLog());
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
                Image resizedEnemy = enemySprites.get(i).getEnemyImage().getScaledInstance(label.getWidth(), label.getHeight(), Image.SCALE_SMOOTH);
                label.setIcon(new ImageIcon(resizedEnemy));
                label.setBufferedImage(enemySprites.get(i).getEnemyImage());
                label.addMouseListener(new MouseListener()
                {
                    @Override
                    public void mouseClicked(MouseEvent e)
                    {
                        try
                        {
                            if (textBox.getScrollSituation() == BattleTextBox.ScrollSituation.Attack)
                            {
                                selectedTarget = label.getBufferedImage();

                                gamePanel.getController().regularAttack(selectedHero, selectedTarget, label.getId(), getId() - 1);

                                textBox.getScrollPane().setVisible(false);
                                ArrayList<String> thisTurnLog = gamePanel.getController().getTurnLog();
                                textBox.showMoveExplanation(thisTurnLog);
                            } else if (textBox.getScrollSituation() == BattleTextBox.ScrollSituation.ChooseAbilityEnemyTarget)
                            {
                                selectedTarget = label.getBufferedImage();
                                gamePanel.getController().useSingleTargetedAbility(selectedHero, selectedTarget, label.getId(), getId() - 1, selectedAbility);

                                textBox.getScrollPane().setVisible(false);
                                ArrayList<String> thisTurnLog = gamePanel.getController().getTurnLog();
                                textBox.showMoveExplanation(thisTurnLog);
                            }
                            else if (textBox.getScrollSituation() == BattleTextBox.ScrollSituation.ChooseItemEnemyTarget)
                            {
                                selectedTarget = label.getBufferedImage();
                                gamePanel.getController().useSingleTargetedItem(selectedHero, selectedTarget, label.getId(), getId() - 1, selectedItem);
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
        }
        else
        {
            EnemyLabel label = new EnemyLabel(enemySprites.get(0).getId());
            label.setBounds(enemyWidth, enemyHeight + 20, 2 * enemyWidth, 2 * enemyHeight);
            Image resizedEnemy = enemySprites.get(0).getEnemyImage().getScaledInstance(label.getWidth(), label.getHeight(), Image.SCALE_SMOOTH);
            label.setIcon(new ImageIcon(resizedEnemy));
            label.setBufferedImage(enemySprites.get(0).getEnemyImage());
            label.addMouseListener(new MouseListener()
            {
                @Override
                public void mouseClicked(MouseEvent e)
                {
                    try
                    {
                        if (textBox.getScrollSituation() == BattleTextBox.ScrollSituation.Attack)
                        {
                            selectedTarget = label.getBufferedImage();

                            gamePanel.getController().regularAttack(selectedHero, selectedTarget, label.getId(), getId() - 1);

                            textBox.getScrollPane().setVisible(false);
                            ArrayList<String> thisTurnLog = gamePanel.getController().getTurnLog();
                            textBox.showMoveExplanation(thisTurnLog);
                        } else if (textBox.getScrollSituation() == BattleTextBox.ScrollSituation.ChooseAbilityEnemyTarget)
                        {
                            selectedTarget = label.getBufferedImage();
                            gamePanel.getController().useSingleTargetedAbility(selectedHero, selectedTarget, label.getId(), getId() - 1, selectedAbility);

                            textBox.getScrollPane().setVisible(false);
                            ArrayList<String> thisTurnLog = gamePanel.getController().getTurnLog();
                            textBox.showMoveExplanation(thisTurnLog);
                        }
                        else if (textBox.getScrollSituation() == BattleTextBox.ScrollSituation.ChooseItemEnemyTarget)
                        {
                            selectedTarget = label.getBufferedImage();
                            gamePanel.getController().useSingleTargetedItem(selectedHero, selectedTarget, label.getId(), getId() - 1, selectedItem);
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
                            selectedHero = label.getBufferedImage();
                            textBox.addText("What should " + gamePanel.getController().findHeroName(selectedHero) + " do?");
                            ArrayList<String> heroPossibleMoves = new ArrayList<>();
                            heroPossibleMoves.add("Attack?");
                            heroPossibleMoves.add("Cast An Ability?");
                            heroPossibleMoves.add("Use An Item?");
                            textBox.addScrollObjects(heroPossibleMoves);
                            textBox.setScrollSituation(BattleTextBox.ScrollSituation.Move);
                        } else if (textBox.getScrollSituation() == BattleTextBox.ScrollSituation.ChooseAbilityHeroTarget)
                        {
                            selectedTarget = label.getBufferedImage();
                            gamePanel.getController().useSingleTargetedAbility(selectedHero, selectedTarget, getId() - 1, selectedAbility);

                            textBox.getScrollPane().setVisible(false);
                            ArrayList<String> thisTurnLog = gamePanel.getController().getTurnLog();
                            textBox.showMoveExplanation(thisTurnLog);
                        }
                        else if (textBox.getScrollSituation() == BattleTextBox.ScrollSituation.ChooseItemHeroTarget)
                        {
                            selectedTarget = label.getBufferedImage();
                            gamePanel.getController().useSingleTargetedItem(selectedHero, selectedTarget, getId() - 1, selectedItem);

                            textBox.getScrollPane().setVisible(false);
                            ArrayList<String> thisTurnLog = gamePanel.getController().getTurnLog();
                            textBox.showMoveExplanation(thisTurnLog);
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
        gamePanel.getController().enemyIntroduction(getId() - 1);
        gamePanel.getController().earlyBossEffects(getId() - 1);
        textBox.showMoveExplanation(gamePanel.getController().getTurnLog());
    }

    public void deleteDeadEnemies()
    {
        ArrayList<EnemySprite> deadEnemies = new ArrayList<>();

        for (EnemySprite enemySprite : enemySprites)
        {
            BufferedImage bufferedImage = enemySprite.getEnemyImage();
            if (!gamePanel.getController().isAlive(bufferedImage, getId() - 1, enemySprite.getId()))
            {
                deadEnemies.add(enemySprite);
                panel.remove(enemySprite.getEnemyLabel());
                panel.repaint();
            }
        }

        enemySprites.removeAll(deadEnemies);
        if(enemySprites.size() == 0)
        {
            gamePanel.getController().endBattle(getId() - 1);
            textBox.setScrollSituation(BattleTextBox.ScrollSituation.End);
            textBox.showMoveExplanation(gamePanel.getController().getTurnLog());
        }
    }

    public void end()
    {
        if(gamePanel.getController().getGameScenario().getEnemyGroups().size() != id)
        {
            gamePanel.getController().setPanel(gamePanel);
        }
        else
        {
            gamePanel.getController().setPanel(gamePanel.getFinalStoryPanel());
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

    public class EnemyLabel extends JLabel
    {
        private Integer id;
        private BufferedImage bufferedImage;

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

        public BufferedImage getBufferedImage()
        {
            return bufferedImage;
        }

        public void setBufferedImage(BufferedImage bufferedImage)
        {
            this.bufferedImage = bufferedImage;
        }
    }

    public BufferedImage getSelectedHero()
    {
        return selectedHero;
    }

    public void setSelectedHero(BufferedImage selectedHero)
    {
        this.selectedHero = selectedHero;
    }
}
