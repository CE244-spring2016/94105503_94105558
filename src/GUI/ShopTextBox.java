package GUI;

import Auxiliary.Luck;
import Exceptions.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.concurrent.Semaphore;
import Controller.*;

/**
 * Created by ruhollah on 7/8/2016.
 */
public class ShopTextBox
{
    private int x;
    private int y;
    private int width;
    private int height;
    private Controller controller;
    private ShopScenario shopScenario;
    private JLabel descriptionTextBox = new JLabel();
    private JLabel textLabel = new JLabel();
    private JLabel imageLabel = new JLabel();
    private MyScrollPane scrollPane = new MyScrollPane();
    private MyScrollPanel myScrollPanel = new MyScrollPanel();
    private final int OPTION_TEXT_SIZE;
    private ScrollSituation scrollSituation = ScrollSituation.Default;

    public ShopTextBox(int x, int y, int width, int height, Controller controller, ShopScenario shopScenario)
    {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.controller = controller;
        this.shopScenario = shopScenario;
        OPTION_TEXT_SIZE = (10 * width * height) / (1920 * 384);

        MyViewPort viewPort = new MyViewPort();
        viewPort.setView(myScrollPanel);
        scrollPane.setViewport(viewPort);
    }

    public void addText(String text)
    {
        textLabel.setText("<html><font size=\"10\">" + text + "</font></html>");
        textLabel.setBounds((365 * width) / 1920, this.y, (width * 1415) / 1920, height);
    }

    public void setBoxImage(Image resizedTextBox)
    {
        descriptionTextBox.setIcon(new ImageIcon(resizedTextBox));
        descriptionTextBox.setBounds(x, y, width, height);
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
                    if (scrollSituation == ScrollSituation.Move)
                    {
                        if(data.get(finalI).equals("Buy"))
                        {
                            addText("Choose an item");
                            shopScenario.getShopItemBox().getItemBox().setVisible(true);
                            ArrayList<String> itemNames = controller.findItemNamesOfAShop(shopScenario.getId() - 1);
                            scrollSituation = ScrollSituation.Buy;
                            shopScenario.getShopItemBox().addScrollObjects(itemNames);
                            scrollPane.setVisible(false);
                        }
                        else if (data.get(finalI).equals("Sell"))
                        {
                            shopScenario.getShopItemBox().getItemBox().setVisible(true);
                            addText("Which hero?");
                            scrollSituation = ScrollSituation.ChoosingSellerHero;
                            scrollPane.setVisible(false);
                        }
                        else  if (data.get(finalI).equals("Exit"))
                        {
                            if (shopScenario.isNetwork())
                            {
                                shopScenario.end(controller.getUserInterface().getAbilityUpgradeBackgroundSources().get(Luck.getRandom(0, 2)));
                            }
                            else
                            {
                                shopScenario.end();
                            }

                            welcome();
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
        if(scrollSituation != ScrollSituation.End)
        {
            scrollSituation = ScrollSituation.ShowingMessage;
        }
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
//                if(scrollSituation == ScrollSituation.End)
//                {
//                    shopScenario.end();
//                }
                addText("Choose");
                if(scrollSituation != ScrollSituation.End)
                {
                    scrollSituation = ScrollSituation.Move;
                    ArrayList<String> possibleMoves = new ArrayList<>();
                    possibleMoves.add("Buy");
                    possibleMoves.add("Sell");
                    possibleMoves.add("Exit");
                    addScrollObjects(possibleMoves);
                }
            }
        });

        textShowingThread.start();

        descriptionTextBox.addMouseListener(new MouseListener()
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

    public JLabel getDescriptionTextBox()
    {
        return descriptionTextBox;
    }

    public void setDescriptionTextBox(JLabel descriptionTextBox)
    {
        this.descriptionTextBox = descriptionTextBox;
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

    public ScrollSituation getScrollSituation()
    {
        return scrollSituation;
    }

    public void setScrollSituation(ScrollSituation scrollSituation)
    {
        this.scrollSituation = scrollSituation;
    }

    public MyScrollPane getScrollPane()
    {
        return scrollPane;
    }

    public void setScrollPane(MyScrollPane scrollPane)
    {
        this.scrollPane = scrollPane;
    }

    public JLabel getTextLabel()
    {
        return textLabel;
    }

    public void setTextLabel(JLabel textLabel)
    {
        this.textLabel = textLabel;
    }

    public void welcome()
    {
        ArrayList<String> possibleMoves = new ArrayList<>();
        possibleMoves.add(controller.getWelcomeMessage(shopScenario.getId() - 1));
        shopScenario.getShopItemBox().getItemBox().setVisible(false);
        showMoveExplanation(possibleMoves);
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
        Default, Move, Buy, Sell, ChoosingBuyerHero, ChoosingSellerHero, End, ShowingMessage, ChoosingItem
    }
}
