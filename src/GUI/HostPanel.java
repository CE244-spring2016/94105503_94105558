package GUI;

import Auxiliary.Luck;
import Controller.NetworkScenario;
import Model.CommonMsg;
import Model.Hero;
import Model.NetworkHandler;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import Controller.*;

/**
 * Created by ruhollah on 7/13/2016.
 */
public class HostPanel extends JPanel
{
    private Controller controller;
    private JLabel waitLabel = new JLabel("<html><font size=\"5\">Waiting for someone to join...</font></html>");
    private int width = (int) (Toolkit.getDefaultToolkit().getScreenSize().getWidth() * 2) / 3;
    private int height = (int) (Toolkit.getDefaultToolkit().getScreenSize().getHeight() * 2) / 3;
    private NetworkScenario networkScenario;
    private CommonMsg commonMsg;
    private NetworkHandler networkHandler;


    public HostPanel(Controller controller)
    {
        this.controller = controller;
        this.networkScenario = controller.getNetworkScenario();
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

        waitLabel.setBounds((width * 750) / 1920, (height * 130) / 1080, (width * 300) / 1920, (height * 50) / 1080);
        this.add(waitLabel);
        networkScenario.setChoice(0);
        networkScenario.getHostJoin().setServer(2000);
        waitLabel.setText("<html><font size=\"4\">Connected.. Please enter the wanted data</font></html>");
        networkScenario.setIn(networkScenario.getHostJoin().getIn());
        networkScenario.setOut(networkScenario.getHostJoin().getOut());
        networkScenario.setNtwhandler(new NetworkHandler(networkScenario.getCommonMsg(), networkScenario.getIn(), networkScenario.getOut()));
        this.commonMsg = networkScenario.getCommonMsg();
        this.networkHandler = networkScenario.getNtwhandler();
        afterConnection();
    }

    private void afterConnection()
    {
        JLabel xpLabel = new JLabel("XP Amount");
        xpLabel.setBounds((width * 700) / 1920, (height * 280) / 1080, (width * 150) / 1920, (height * 30) / 1080);
        this.add(xpLabel);

        JTextField xpField = new JTextField();
        xpField.setBounds((width * 880) / 1920, (height * 280) / 1080, (width * 100) / 1920, (height * 30) / 1080);
        this.add(xpField);

        JLabel moneyLabel = new JLabel("Money Amount");
        moneyLabel.setBounds((width * 700) / 1920, (height * 320) / 1080, (width * 150) / 1920, (height * 30) / 1080);
        this.add(moneyLabel);

        JTextField moneyField = new JTextField();
        moneyField.setBounds((width * 880) / 1920, (height * 320) / 1080, (width * 100) / 1920, (height * 30) / 1080);
        this.add(moneyField);

        JLabel immortalityLabel = new JLabel("Immortality Potion Amount");
        immortalityLabel.setBounds((width * 700) / 1920, (height * 360) / 1080, (width * 150) / 1920, (height * 30) / 1080);
        this.add(immortalityLabel);

        JTextField immortalityField = new JTextField();
        immortalityField.setBounds((width * 880) / 1920, (height * 360) / 1080, (width * 100) / 1920, (height * 30) / 1080);
        this.add(immortalityField);

        JLabel nameLabel = new JLabel("Enter Name");
        nameLabel.setBounds((width * 700) / 1920, (height * 400) / 1080, (width * 150) / 1920, (height * 30) / 1080);
        this.add(nameLabel);

        JTextField nameField = new JTextField();
        nameField.setBounds((width * 880) / 1920, (height * 400) / 1080, (width * 100) / 1920, (height * 30) / 1080);
        this.add(nameField);

        JButton doneButton = new JButton("done");
        doneButton.setBounds((width * 800) / 1920, (height * 440) / 1080, (width * 100) / 1920, (height * 30) / 1080);
        doneButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                if (!xpField.getText().equals("") && !moneyField.getText().equals("") && !immortalityField.getText().equals("") && !nameField.getText().equals(""))
                {
                    try
                    {
                        int xpNum = Integer.parseInt(xpField.getText());
                        int moneyNum = Integer.parseInt((moneyField.getText()));
                        int immortalityNum = Integer.parseInt(immortalityField.getText());
                        Hero.setXP(xpNum);
                        Hero.setMoney(moneyNum);
                        Hero.setImmortalityPotionNum(immortalityNum);
                        networkScenario.setEnemyImmortalityPotionNum(immortalityNum);
                        networkScenario.setMyImmortalityPotionNum(immortalityNum);
                        networkScenario.setName(nameField.getText());
                        commonMsg.setMoney(moneyNum);
                        commonMsg.setImmortalityPotion(immortalityNum);
                        commonMsg.setXp(xpNum);
                        commonMsg.setName(nameField.getText());
                        networkHandler.setCommonMsg(commonMsg);
                        System.out.println("YES F");
                        networkHandler.send();
                        networkHandler.receive();
                        commonMsg = networkHandler.getCommonMsg();
                        networkScenario.setEnemyName(commonMsg.getName());
                        System.out.println(networkScenario.getEnemyName());
                        System.out.println("NO U");
                        getReadyForShopAndAbilityUpgrade();
                    }
                    catch (Exception e1)
                    {
                        e1.printStackTrace();
                        xpField.setText("Invalid Inputs");
                        moneyField.setText("");
                        immortalityField.setText("");
                    }
                }
            }
        });
        this.add(doneButton);
    }

    private void getReadyForShopAndAbilityUpgrade()
    {
        ArrayList<UltimateImage> shopImages = controller.getUserInterface().getShopBackgroundSources();
        ArrayList<UltimateImage> shopKeepers = controller.getUserInterface().getShopKeeperSources();

        UltimateImage shopBackground = shopImages.get(Luck.getRandom(0, 2));
        UltimateImage shopKeeper = shopKeepers.get(Luck.getRandom(0, 2));

        ArrayList<HeroSprite> heroSprites = new ArrayList<>();

        for (UltimateImage ultimateImage : controller.getUserInterface().getHerosAndTheirImages().keySet())
        {
            heroSprites.add(new HeroSprite(ultimateImage));
        }

        controller.setNetworkScenario(networkScenario);
        controller.setPanel(new ShopScenario(heroSprites, shopBackground, shopKeeper, controller).getPanel());
    }
}