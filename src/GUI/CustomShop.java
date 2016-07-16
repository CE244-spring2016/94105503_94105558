package GUI;

import javax.imageio.ImageIO;
import javax.swing.*;
import Controller.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by ruhollah on 7/13/2016.
 */
public class CustomShop extends JPanel
{
    MapEditor panel;
    Controller controller;
    private int width = (int) (Toolkit.getDefaultToolkit().getScreenSize().getWidth() * 2) / 8;
    private int height = (int) (Toolkit.getDefaultToolkit().getScreenSize().getHeight() * 2) / 5;

    public CustomShop(MapEditor panel)
    {
        this.panel = panel;
        this.controller = panel.getController();
        System.out.println(width + " " + height);
        this.controller = panel.getController();
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
                controller.setPanel(panel);
            }
        });
        this.add(backButton);

        JLabel storyLabel = new JLabel("<html><font size=\"7\">Custom shop</font></html>");
        storyLabel.setBounds((width * 200) / 720, (height * 40) / 648, (width * 300) / 720, (height * 50) / 648);
        this.add(storyLabel);

        JLabel itemLabel = new JLabel("<html><font size=\"3\">Item name</font></html>");
        itemLabel.setBounds((width * 20) / 720, (height * 100) / 648, (width * 100) / 720, (height * 30) / 648);
        this.add(itemLabel);

        ArrayList<String> itemNames = controller.getUserInterface().getItemNames();
        HashMap<String, Integer> itemCosts = new HashMap<>();
        ArrayList<String> shopItems = new ArrayList<>();
        JComboBox itemBox = new JComboBox(itemNames.toArray());
        itemBox.setBounds((width * 100) / 720, (height * 100) / 648, (width * 200) / 720, (height * 30) / 648);
        this.add(itemBox);

        JLabel costLabel = new JLabel("<html><font size=\"3\">Cost amount</font></html>");
        costLabel.setBounds((width * 310) / 720, (height * 100) / 648, (width * 100) / 720, (height * 30) / 648);
        this.add(costLabel);

        JTextField costField = new JTextField();
        costField.setBounds((width * 410) / 720, (height * 100) / 648, (width * 100) / 720, (height * 30) / 648);
        this.add(costField);

        JButton addMoneyButton = new JButton("add");
        addMoneyButton.setBounds((width * 520) / 720, (height * 100) / 648, (width * 100) / 720, (height * 30) / 648);
        addMoneyButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                try
                {
                    int costAmount = Integer.parseInt(costField.getText());
                    itemCosts.put((String) itemBox.getSelectedItem(), costAmount);
                    shopItems.add((String) itemBox.getSelectedItem());
                } catch (Exception e1)
                {
                    costField.setText("Invalid Input");
                }
            }
        });
        this.add(addMoneyButton);

        JLabel inflationLabel = new JLabel("<html><font size=\"3\">Enter inflation amount</font></html>");
        inflationLabel.setBounds((width * 200) / 720, (height * 140) / 648, (width * 150) / 720, (height * 30) / 648);
        this.add(inflationLabel);

        JTextField inflationField = new JTextField();
        inflationField.setBounds((width * 350) / 720, (height * 140) / 648, (width * 160) / 720, (height * 30) / 648);
        this.add(inflationField);

        JLabel backgroundLabel = new JLabel("<html><font size=\"3\">Enter background source</font></html>");
        backgroundLabel.setBounds((width * 200) / 720, (height * 180) / 648, (width * 150) / 720, (height * 30) / 648);
        this.add(backgroundLabel);

        JTextField backgroundSourceField = new JTextField();
        backgroundSourceField.setBounds((width * 350) / 720, (height * 180) / 648, (width * 160) / 720, (height * 30) / 648);
        this.add(backgroundSourceField);

        JLabel shopKeeperLabel = new JLabel("<html><font size=\"3\">Enter shop keeper source</font></html>");
        shopKeeperLabel.setBounds((width * 200) / 720, (height * 220) / 648, (width * 150) / 720, (height * 30) / 648);
        this.add(shopKeeperLabel);

        JTextField shopKeeperSourceField = new JTextField();
        shopKeeperSourceField.setBounds((width * 350) / 720, (height * 220) / 648, (width * 160) / 720, (height * 30) / 648);
        this.add(shopKeeperSourceField);

        JLabel welcomeLabel = new JLabel("<html><font size=\"3\">Enter welcome message</font></html>");
        welcomeLabel.setBounds((width * 200) / 720, (height * 260) / 648, (width * 150) / 720, (height * 30) / 648);
        this.add(welcomeLabel);

        JTextField welcomeField = new JTextField();
        welcomeField.setBounds((width * 350) / 720, (height * 260) / 648, (width * 160) / 720, (height * 30) / 648);
        this.add(welcomeField);

        JButton doneButton = new JButton("done");
        doneButton.setBounds((width * 200) / 720, (height * 300) / 648, (width * 100) / 720, (height * 30) / 648);
        doneButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                try
                {
                    ImageIO.read(new File(shopKeeperSourceField.getText()));
                    int inflationAmount = Integer.parseInt(inflationField.getText());

                    controller.getUserInterface().getShopKeeperSources().add(new UltimateImage(shopKeeperSourceField.getText()));
                    controller.getUserInterface().getShopBackgroundSources().add(new UltimateImage(backgroundSourceField.getText()));
                    controller.getUserInterface().getAllShopInflationValues().add(inflationAmount);
                    controller.getUserInterface().getShopWelcomeMessages().add(welcomeField.getText());
                    controller.getUserInterface().getAllShopItemNames().add(shopItems);
                    controller.getUserInterface().getAllShopItemMoneyCosts().add(itemCosts);
                    controller.setPanel(panel);
                } catch (IOException e1)
                {
                    shopKeeperSourceField.setText("Invalid Inputs");
                    backgroundSourceField.setText("");
                } catch (Exception e2)
                {
                    inflationField.setText("Invalid Input");
                }
            }
        });
        this.add(doneButton);
    }
}
