package GUI;

import Exceptions.*;
import Model.Shop;

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
import java.util.StringJoiner;

/**
 * Created by ruhollah on 7/8/2016.
 */
public class ShopScenario
{
    private BufferedImage background;
    private BufferedImage shopKeeper;
    private ShopTextBox shopTextBox;
    private ShopItemBox shopItemBox;
    private JPanel panel = new JPanel();
    private ArrayList<HeroSprite> heroSprites = new ArrayList<>();
    private GamePanel gamePanel;
    private int width = (int) (Toolkit.getDefaultToolkit().getScreenSize().getWidth() * 2) / 3;
    private int height = (int) (Toolkit.getDefaultToolkit().getScreenSize().getHeight() * 2) / 3;
    private int heroWidth;
    private int heroHeight;
    private int id;
    private BufferedImage selectedItem;
    private BufferedImage selectedHero;
    private BufferedImage selectedTargetHero;

    public ShopScenario(int id, ArrayList<HeroSprite> heroSprites, GamePanel gamePanel, BufferedImage background, BufferedImage shopKeeper)
    {
        this.id = id;
        this.heroSprites = heroSprites;
        this.gamePanel = gamePanel;
        this.background = background;
        this.shopKeeper = shopKeeper;
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
                shopTextBox.welcome();
                end();
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


        showItemBox();
        showTextBox();
        setHeroSize();
        showHeros();
        showShopKeeper();
        showBackground();
    }

    private void showItemBox()
    {
        BufferedImage textBoxImage = null;
        try
        {
            textBoxImage = ImageIO.read(new File("Main Pics/ShopTextBox/ShopItemBox.png"));
            Image resizedTextBox = textBoxImage.getScaledInstance((width * 720) / 1920, (height * 750) / 1070, Image.SCALE_SMOOTH);
            shopItemBox = new ShopItemBox((width * 1200) / 1920, 0, (width * 720) / 1920, (height * 750) / 1070, gamePanel.getController(), this);
            shopItemBox.setBoxImage(resizedTextBox);
//            textBox.addText("Choose a hero");
//            textBox.setScrollSituation(BattleTextBox.ScrollSituation.Default);
            ArrayList<String> possibleMoves = new ArrayList<>();
//            shopTextBox.addScrollObjects(possibleMoves);
//            panel.add(shopTextBox.getDoneButton());
//            panel.add(shopTextBox.getScrollPane());
//            panel.add(shopTextBox.getTextLabel());
            panel.add(shopItemBox.getScrollPane());
            panel.add(shopItemBox.getItemBox());
//            gamePanel.getController().enemyIntroduction(getId() - 1);
//            textBox.showMoveExplanation(gamePanel.getController().getTurnLog());
        } catch (IOException e)
        {
            e.printStackTrace();
        }
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
                    try
                    {
                        if(shopTextBox.getScrollSituation() == ShopTextBox.ScrollSituation.ChoosingSellerHero)
                        {
                            selectedHero = label.getBufferedImage();
                            ArrayList<String> itemNames = gamePanel.getController().findItemNamesOfAHero(selectedHero);
                            shopTextBox.setScrollSituation(ShopTextBox.ScrollSituation.Sell);
                            if(itemNames.size() != 0)
                            {
                                shopTextBox.addText("Choose an item");
                                shopItemBox.addScrollObjects(itemNames);
                            }
                            else
                            {
                                ArrayList<String> error = new ArrayList<>();
                                error.add("This hero has no items");
                                shopItemBox.getScrollPane().setVisible(false);
                                shopItemBox.getItemBox().setVisible(false);
                                shopTextBox.showMoveExplanation(error);
                            }
                        }
                        else if (shopTextBox.getScrollSituation() == ShopTextBox.ScrollSituation.ChoosingBuyerHero)
                        {
                            selectedHero = label.getBufferedImage();
                            gamePanel.getController().buyItem(selectedHero, getShopItemBox().getSelectedItem(), id - 1);
                            shopTextBox.showMoveExplanation(gamePanel.getController().getTurnLog());
                        }
                    } catch (FullInventoryException | AbilityNotAcquieredException | NotStrongEnoughException |
                            AbilityCooldownException | NotEnoughRequiredAbilitiesException | NoMoreUpgradeException |
                            NotEnoughXPException | NotEnoughMoneyException e1)
                    {
                        ArrayList<String> exceptionMessage = new ArrayList<>();
                        exceptionMessage.add(e1.getMessage());
                        shopTextBox.showMoveExplanation(exceptionMessage);
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
        }
    }

    private void setHeroSize()
    {
        heroHeight = (height - shopTextBox.getHeight()) / 7;
        heroWidth = (width * 2 / 5) / 8;
    }

    private void showTextBox()
    {
        BufferedImage textBoxImage = null;
        try
        {
            textBoxImage = ImageIO.read(new File("Main Pics/ShopTextBox/ShopTextBox.png"));
            Image resizedTextBox = textBoxImage.getScaledInstance(width, (height - (height * 750) / 1070), Image.SCALE_SMOOTH);
            shopTextBox = new ShopTextBox(0, (height * 750) / 1070, width, (height - (height * 750) / 1070), gamePanel.getController(), this);
            shopTextBox.setBoxImage(resizedTextBox);
//            shopTextBox.addText(gamePanel.getController().getWelcomeMessage(id - 1));
//            textBox.setScrollSituation(BattleTextBox.ScrollSituation.Default);
            shopTextBox.welcome();
//            shopTextBox.addScrollObjects(possibleMoves);
//            panel.add(shopTextBox.getDoneButton());
            panel.add(shopTextBox.getScrollPane());
            panel.add(shopTextBox.getTextLabel());
            shopTextBox.setScrollSituation(ShopTextBox.ScrollSituation.Default);
            panel.add(shopTextBox.getDescriptionTextBox());
//            gamePanel.getController().enemyIntroduction(getId() - 1);
//            textBox.showMoveExplanation(gamePanel.getController().getTurnLog());
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    private void showShopKeeper()
    {
        JLabel label = new JLabel();
        label.setBounds((width * 700) / 1920, (height * 200) / 1070, 450, 600);
        Image resizedBackground = shopKeeper.getScaledInstance(label.getWidth(), label.getHeight(), Image.SCALE_SMOOTH);
        label.setIcon(new ImageIcon(resizedBackground));
        panel.add(label);
    }

    private void showBackground()
    {
        JLabel label = new JLabel();
        label.setBounds(0, 0, width, height);
        Image resizedBackground = background.getScaledInstance(label.getWidth(), label.getHeight(), Image.SCALE_SMOOTH);
        label.setIcon(new ImageIcon(resizedBackground));
        panel.add(label);
    }


    public JPanel getPanel()
    {
        return panel;
    }

    public void setPanel(JPanel panel)
    {
        this.panel = panel;
    }

    public BufferedImage getSelectedItem()
    {
        return selectedItem;
    }

    public void setSelectedItem(BufferedImage selectedItem)
    {
        this.selectedItem = selectedItem;
    }

    public BufferedImage getSelectedHero()
    {
        return selectedHero;
    }

    public void setSelectedHero(BufferedImage selectedHero)
    {
        this.selectedHero = selectedHero;
    }

    public BufferedImage getSelectedTargetHero()
    {
        return selectedTargetHero;
    }

    public void setSelectedTargetHero(BufferedImage selectedTargetHero)
    {
        this.selectedTargetHero = selectedTargetHero;
    }

    public ShopItemBox getShopItemBox()
    {
        return shopItemBox;
    }

    public void setShopItemBox(ShopItemBox shopItemBox)
    {
        this.shopItemBox = shopItemBox;
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public ShopTextBox getShopTextBox()
    {
        return shopTextBox;
    }

    public void setShopTextBox(ShopTextBox shopTextBox)
    {
        this.shopTextBox = shopTextBox;
    }

    public void end()
    {
        gamePanel.getController().setPanel(gamePanel);
    }
}
