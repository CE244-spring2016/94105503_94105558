package GUI;

import Exceptions.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

/**
 * Created by ruhollah on 7/8/2016.
 */
public class ShopItemBox
{
    private JLabel itemBox = new JLabel();
    private int x;
    private int y;
    private int width;
    private int height;
    private Controller controller;
    private ShopScenario shopScenario;
    private final int OPTION_TEXT_SIZE;
    private MyScrollPane scrollPane = new MyScrollPane();
    private MyScrollPanel myScrollPanel = new MyScrollPanel();
    private String selectedItem;

    public ShopItemBox(int x, int y, int width, int height, Controller controller, ShopScenario shopScenario)
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

    public void addScrollObjects(ArrayList<String> data)
    {
        scrollPane.setVisible(true);
        scrollPane.setBounds(x, y + (20 * height) / 1920, width - (20 * width) / 1920, height - (20 * height) / 384);
        myScrollPanel.removeAll();
        MyViewPort viewPort = new MyViewPort();

        for (int i = 0; i < data.size(); i++)
        {
            JLabel label = new JLabel("<html><font size=\"" + OPTION_TEXT_SIZE + "\" face=\"Chiller\">" + data.get(i) + "</font></html>");
            label.setBounds((i % 2) * width / 2 + (width * 20) / 1920, (i / 2) * (height / 6), width / 2 - (10 * width) / 1920, height / 6 - (20 * height) / 384);
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
                    try
                    {
                        if(shopScenario.getShopTextBox().getScrollSituation() == ShopTextBox.ScrollSituation.Buy)
                        {
                            selectedItem = data.get(finalI);
                            itemBox.setVisible(false);
                            scrollPane.setVisible(false);
                            shopScenario.getShopTextBox().setScrollSituation(ShopTextBox.ScrollSituation.ChoosingBuyerHero);
                            shopScenario.getShopTextBox().addText("Choose a hero");
                        }
                        else if(shopScenario.getShopTextBox().getScrollSituation() == ShopTextBox.ScrollSituation.Sell)
                        {
                            selectedItem = data.get(finalI);
                            itemBox.setVisible(false);
                            scrollPane.setVisible(false);
                            controller.sellItem(selectedItem, shopScenario.getSelectedHero(), shopScenario.getId() - 1);
                            shopScenario.getShopTextBox().showMoveExplanation(controller.getTurnLog());
                        }
                    } catch (FullInventoryException | AbilityNotAcquieredException | NotEnoughMoneyException |
                            AbilityCooldownException | NotStrongEnoughException | NoMoreUpgradeException |
                            NotEnoughXPException | NotEnoughRequiredAbilitiesException e1)
                    {
                        ArrayList<String> exceptionMessage = new ArrayList<>();
                        exceptionMessage.add(e1.getMessage());
                        shopScenario.getShopTextBox().showMoveExplanation(exceptionMessage);
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
                    String itemDescription = controller.findItemDescription(data.get(finalI));
                    shopScenario.getShopTextBox().addText(itemDescription);
                }

                @Override
                public void mouseExited(MouseEvent e)
                {
                    shopScenario.getShopTextBox().addText("Choose an item");
                }
            });
            myScrollPanel.add(label);
        }

        viewPort.setView(myScrollPanel);
        scrollPane.setViewport(viewPort);

        if(data.size() > 4)
        {
            myScrollPanel.setPreferredSize(new Dimension(width - (20 * width) / 1920, (data.size() / 2 + data.size() % 2) * (height / 6)));
        }
        else if(data.size() == 0)
        {
            scrollPane.setVisible(false);
        }
    }

    public void setBoxImage(Image resizedTextBox)
    {
        itemBox.setIcon(new ImageIcon(resizedTextBox));
        itemBox.setBounds(x, y, width, height);
    }

    public JLabel getItemBox()
    {
        return itemBox;
    }

    public void setItemBox(JLabel itemBox)
    {
        this.itemBox = itemBox;
    }

    public String getSelectedItem()
    {
        return selectedItem;
    }

    public void setSelectedItem(String selectedItem)
    {
        this.selectedItem = selectedItem;
    }

    public MyScrollPane getScrollPane()
    {
        return scrollPane;
    }

    public void setScrollPane(MyScrollPane scrollPane)
    {
        this.scrollPane = scrollPane;
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
}
