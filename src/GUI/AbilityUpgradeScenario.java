package GUI;

import Exceptions.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.AreaAveragingScaleFilter;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by ruhollah on 7/9/2016.
 */
public class AbilityUpgradeScenario
{
    private BufferedImage background;
    private AbilityUpgradeTextBox textBox;
    private AbilityUpgradeInfoBox infoBox;
    private JPanel panel = new JPanel()
    {
        @Override
        protected void paintComponent(Graphics g)
        {
            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D) g;
            Rectangle rect = new Rectangle(0, 0, 5000, 3000);
            g2d.setColor(Color.BLACK);
            g2d.draw(rect);
            g2d.fill(rect);
        }
    };
    private ArrayList<HeroSprite> heroSprites = new ArrayList<>();
    private GamePanel gamePanel;
    private int width = (int) (Toolkit.getDefaultToolkit().getScreenSize().getWidth() * 2) / 3;
    private int height = (int) (Toolkit.getDefaultToolkit().getScreenSize().getHeight() * 2) / 3;
    private int heroWidth;
    private int heroHeight;
    private int id;
    private BufferedImage selectedAbility;
    private BufferedImage selectedHero;

    public AbilityUpgradeScenario(int id, ArrayList<HeroSprite> heroSprites, GamePanel gamePanel, BufferedImage background)
    {
        this.id = id;
        this.heroSprites = heroSprites;
        this.gamePanel = gamePanel;
        this.background = background;
        panel.setLayout(null);
        panel.setSize(width, height);
        panel.setPreferredSize(new Dimension(width, height));

        JButton closeButton = new JButton("close");
        closeButton.setBounds(850, 0, 100, 100);
        closeButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                Controller controller = gamePanel.getController();
                controller.setPanel(gamePanel);
            }
        });
        panel.add(closeButton);
        panel.addMouseListener(new MouseListener()
        {
            @Override
            public void mouseClicked(MouseEvent e)
            {
                System.out.println(e.getX() + " " + e.getY());
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

        showTextBox();
        showInfoBox();
        setHeroSize();
        showHeros();
        showBackground();
    }

    private void showInfoBox()
    {
        BufferedImage textBoxImage = null;
        try
        {
            textBoxImage = ImageIO.read(new File("Main Pics/ShopTextBox/ShopItemBox.png"));
            Image resizedTextBox = textBoxImage.getScaledInstance((width * 720) / 1920, (height * 750) / 1070, Image.SCALE_SMOOTH);
            infoBox = new AbilityUpgradeInfoBox((width * 1200) / 1920, 0, (width * 720) / 1920, (height * 750) / 1070, gamePanel.getController(), this);
            infoBox.setBoxImage(resizedTextBox);
//            textBox.addText("Choose a hero");
//            textBox.setScrollSituation(BattleTextBox.ScrollSituation.Default);
            ArrayList<String> possibleMoves = new ArrayList<>();
//            shopTextBox.addScrollObjects(possibleMoves);
//            panel.add(shopTextBox.getDoneButton());
//            panel.add(shopTextBox.getScrollPane());
//            panel.add(shopTextBox.getTextLabel());
            panel.add(infoBox.getDescriptionScrollPane());
            panel.add(infoBox.getRequirementScrollPane());
            panel.add(infoBox.getInfoBox());
//            gamePanel.getController().enemyIntroduction(getId() - 1);
//            textBox.showMoveExplanation(gamePanel.getController().getTurnLog());
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    private void showTextBox()
    {
        BufferedImage textBoxImage = null;
        try
        {
            textBoxImage = ImageIO.read(new File("Main Pics/AbilityUpgradeTextBox/AbilityUpgradeTextBox.png"));
            Image resizedTextBox = textBoxImage.getScaledInstance(width, (height - (height * 750) / 1070), Image.SCALE_SMOOTH);
            textBox = new AbilityUpgradeTextBox(0, (height * 750) / 1070, width, (height - (height * 750) / 1070), gamePanel.getController(), this);
            textBox.setBoxImage(resizedTextBox);
//            shopTextBox.addText(gamePanel.getController().getWelcomeMessage(id - 1));
//            textBox.setScrollSituation(BattleTextBox.ScrollSituation.Default);
            textBox.welcome();
//            shopTextBox.addScrollObjects(possibleMoves);
//            panel.add(shopTextBox.getDoneButton());
            panel.add(textBox.getScrollPane());
            panel.add(textBox.getTextLabel());
            panel.add(textBox.getImageLabel());
            textBox.setScrollSituation(AbilityUpgradeTextBox.ScrollSituation.Default);
            panel.add(textBox.getDescriptionTextBox());
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
        label.setBounds(0, 0, width, height - textBox.getHeight());
        Image resizedBackground = background.getScaledInstance(label.getWidth(), label.getHeight(), Image.SCALE_SMOOTH);
        label.setIcon(new ImageIcon(resizedBackground));
        panel.add(label);
    }

    private void showHeros()
    {
        for (int i = 0; i < heroSprites.size(); i++)
        {
            HeroLabel label = new HeroLabel(heroSprites.get(i).getAllImages());
            label.setBounds(width * (i / 5) / 20, (i % 6) * heroHeight + 20, heroWidth, heroHeight);
            ImageIcon icon = (ImageIcon) heroSprites.get(i).getFirstRightImage().getIcon();
            BufferedImage bufferedHero = (BufferedImage) icon.getImage();
            Image resizedHero = bufferedHero.getScaledInstance(label.getWidth(), label.getHeight(), Image.SCALE_SMOOTH);
            label.setIcon(new ImageIcon(resizedHero));
            label.addMouseListener(new MouseListener()
            {
                @Override
                public void mouseClicked(MouseEvent e)
                {
//                    try
//                    {
                        if(textBox.getScrollSituation() == AbilityUpgradeTextBox.ScrollSituation.Acquire)
                        {
                            selectedHero = label.getBufferedImage();
                            ArrayList<String> nonAcquiredAbilities = gamePanel.getController().findNonAcquiredAbilities(selectedHero);
                            if(nonAcquiredAbilities.size() != 0)
                            {
                                textBox.addText("Choose an ability");
                                textBox.addScrollObjects(nonAcquiredAbilities);
                                textBox.setScrollSituation(AbilityUpgradeTextBox.ScrollSituation.ChoosingAbility);
                                infoBox.getInfoBox().setVisible(true);
                            }
                            else
                            {
                                ArrayList<String> error = new ArrayList<>();
                                error.add("There are no abilities to be acquired");
                                textBox.showMoveExplanation(error);
                            }
                        }
                        else if (textBox.getScrollSituation() == AbilityUpgradeTextBox.ScrollSituation.Upgrade)
                        {
                            selectedHero = label.getBufferedImage();
                            ArrayList<String> acquiredAbilities = gamePanel.getController().findAcquiredAbilities(selectedHero);
                            if(acquiredAbilities.size() != 0)
                            {
                                textBox.addText("Choose an ability");
                                textBox.addScrollObjects(acquiredAbilities);
                                textBox.setScrollSituation(AbilityUpgradeTextBox.ScrollSituation.ChoosingAbility);
                                infoBox.getInfoBox().setVisible(true);
                            }
                            else
                            {
                                ArrayList<String> error = new ArrayList<>();
                                error.add("This hero has no acquired abilities");
                                textBox.showMoveExplanation(error);
                            }
                        }
//                    } catch (FullInventoryException | AbilityNotAcquieredException | NotStrongEnoughException |
//                            AbilityCooldownException | NotEnoughRequiredAbilitiesException | NoMoreUpgradeException |
//                            NotEnoughXPException | NotEnoughMoneyException e1)
//                    {
//                        ArrayList<String> exceptionMessage = new ArrayList<>();
//                        exceptionMessage.add(e1.getMessage());
//                        textBox.showMoveExplanation(exceptionMessage);
//                    }
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
        }
    }

    private void setHeroSize()
    {
        heroHeight = (height - textBox.getHeight()) / 7;
        heroWidth = (width * 2 / 5) / 8;
    }

    public JPanel getPanel()
    {
        return panel;
    }

    public void setPanel(JPanel panel)
    {
        this.panel = panel;
    }

    public AbilityUpgradeTextBox getTextBox()
    {
        return textBox;
    }

    public void setTextBox(AbilityUpgradeTextBox textBox)
    {
        this.textBox = textBox;
    }

    public AbilityUpgradeInfoBox getInfoBox()
    {
        return infoBox;
    }

    public void setInfoBox(AbilityUpgradeInfoBox infoBox)
    {
        this.infoBox = infoBox;
    }

    public int getWidth()
    {
        return width;
    }

    public void setWidth(int width)
    {
        this.width = width;
    }

    public int getHeight()
    {
        return height;
    }

    public void setHeight(int height)
    {
        this.height = height;
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public BufferedImage getSelectedHero()
    {
        return selectedHero;
    }

    public void setSelectedHero(BufferedImage selectedHero)
    {
        this.selectedHero = selectedHero;
    }

    public void end()
    {
        gamePanel.getController().setPanel(gamePanel);
    }
}
