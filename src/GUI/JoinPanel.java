package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import Auxiliary.Luck;
import Controller.*;
import Model.*;

/**
 * Created by ruhollah on 7/13/2016.
 */
public class JoinPanel extends JPanel
{
    private Controller controller;
    private int width = (int) (Toolkit.getDefaultToolkit().getScreenSize().getWidth() * 2) / 16;
    private int height = (int) (Toolkit.getDefaultToolkit().getScreenSize().getHeight() * 2) / 16;
    private NetworkScenario networkScenario;
    private CommonMsg commonMsg;
    private NetworkHandler networkHandler;

    public JoinPanel(Controller controller)
    {
        this.controller = controller;
        this.networkScenario = controller.getNetworkScenario();
        commonMsg = networkScenario.getCommonMsg();
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
        JLabel enterLabel = new JLabel("<html><font size=\"4\">Enter host IP</font></html>");
        enterLabel.setBounds((width * 100) / 360, (height * 25) / 202, (width * 160) / 360, (height * 30) / 202);
        this.add(enterLabel);

        JTextField ipField = new JTextField();
        ipField.setBounds((width * 80) / 360, (height * 65) / 202, (width * 200) / 360, (height * 30) / 202);
        this.add(ipField);

        JButton doneButton = new JButton("<html><font size=\"4\">Join</font></html>");
        doneButton.setBounds((width * 80) / 360, (height * 105) / 202, (width * 200) / 360, (height * 30) / 202);
        doneButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                if (!ipField.getText().equals(""))
                {
                    networkScenario.setChoice(1);
                    try
                    {
                        networkScenario.setIP(ipField.getText());
                        networkScenario.getHostJoin().setClient(2000, ipField.getText());
                        networkScenario.setIn(networkScenario.getHostJoin().getIn());
                        networkScenario.setOut(networkScenario.getHostJoin().getOut());
                        networkScenario.setNtwhandler(new NetworkHandler(networkScenario.getCommonMsg(), networkScenario.getIn(), networkScenario.getOut()));
                        networkHandler = networkScenario.getNtwhandler();
                        ipField.setVisible(false);
                        enterLabel.setVisible(false);
                        doneButton.setVisible(false);
                        afterConnection();
                    }
                    catch (Exception e2)
                    {
                        ipField.setText("Invalid input");
                    }
                }
            }
        });
        this.add(doneButton);

    }

    private void afterConnection()
    {
        networkScenario.getNtwhandler().receive();
        networkScenario.setCommonMsg(networkScenario.getNtwhandler().getCommonMsg());

        CommonMsg commonMsg = networkScenario.getCommonMsg();

        networkScenario.setEnemyName(commonMsg.getName());

        Hero.setXP(commonMsg.getXp());
        Hero.setMoney(commonMsg.getMoney());
        Hero.setImmortalityPotionNum(commonMsg.getImmortalityPotion());

        JLabel enterLabel = new JLabel("<html><font size=\"4\">Enter name</font></html>");
        enterLabel.setBounds((width * 100) / 360, (height * 25) / 202, (width * 160) / 360, (height * 30) / 202);
        this.add(enterLabel);

        JTextField nameField = new JTextField();
        nameField.setBounds((width * 80) / 360, (height * 65) / 202, (width * 200) / 360, (height * 30) / 202);
        this.add(nameField);

        JButton doneButton = new JButton("<html><font size=\"4\">Enter</font></html>");
        doneButton.setBounds((width * 80) / 360, (height * 105) / 202, (width * 200) / 360, (height * 30) / 202);
        doneButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                if (!nameField.getText().equals(""))
                {
                    handleNaming(nameField.getText());
//                    isReady();
                    getReadyForShopAndAbilityUpgrade();
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

    private void handleNaming(String name)
    {
        commonMsg.setName(name);
        networkScenario.setName(name);
        networkHandler.setCommonMsg(commonMsg);
        networkHandler.send();
        System.out.println(networkScenario.getEnemyName());
        this.removeAll();
    }
}
