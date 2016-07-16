package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * Created by ruhollah on 7/11/2016.
 */
public final class MainCustomPanel extends JPanel
{
    private static MainCustomPanel mainCustomPanel;
    private Controller controller;
    private int width = (int) (Toolkit.getDefaultToolkit().getScreenSize().getWidth() * 2) / 4;
    private int height = (int) (Toolkit.getDefaultToolkit().getScreenSize().getHeight() * 2) / 4;

    public static MainCustomPanel getInstance()
    {
        return mainCustomPanel;
    }

    public static MainCustomPanel getInstance(Controller controller)
    {
        if(mainCustomPanel == null)
        {
            mainCustomPanel = new MainCustomPanel(controller);
        }

        return mainCustomPanel;
    }

    private MainCustomPanel(Controller controller)
    {
        this.controller = controller;
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
        makeAttributeButton();

        makeAbilityButton();

        JButton heroClassButton = new JButton("<html><font size=\"5\">Hero Class</font></html>");
        heroClassButton.setBounds((width * 380) / 1440, (35 * height) / 800, (130 * width) / 1440, (height * 50) / 800);
        heroClassButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                controller.setPanel(new HeroClassCustomUI(controller));
            }
        });
        this.add(heroClassButton);

        JButton heroButton = new JButton("<html><font size=\"5\">Hero</font></html>");
        heroButton.setBounds((width * 520) / 1440, (35 * height) / 800, (130 * width) / 1440, (height * 50) / 800);
        heroButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                controller.setPanel(new CustomHero(controller));
            }
        });
        this.add(heroButton);

        JButton mapButton = new JButton("<html><font size=\"5\">Map</font></html>");
        mapButton.setBounds((width * 585) / 1440, (95 * height) / 800, (130 * width) / 1440, (height * 50) / 800);
        mapButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                controller.setPanel(new CustomMap(controller));
            }
        });
        this.add(mapButton);

        JButton normalEnemyButton = new JButton("<html><font size=\"5\">Normal Enemy</font></html>");
        normalEnemyButton.setBounds((width * 660) / 1440, (35 * height) / 800, (130 * width) / 1440, (height * 50) / 800);
        normalEnemyButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                controller.setPanel(new CustomNormalEnemy(controller));
            }
        });
        this.add(normalEnemyButton);

        JButton bossEnemyButton = new JButton("<html><font size=\"5\">Boss Enemy</font></html>");
        bossEnemyButton.setBounds((width * 800) / 1440, (35 * height) / 800, (130 * width) / 1440, (height * 50) / 800);
        bossEnemyButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                controller.setPanel(new CustomBossEnemy(controller));
            }
        });
        this.add(bossEnemyButton);

        JButton itemButton = new JButton("<html><font size=\"5\">Item</font></html>");
        itemButton.setBounds((width * 940) / 1440, (35 * height) / 800, (130 * width) / 1440, (height * 50) / 800);
        itemButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                controller.setPanel(new CustomItem(controller));
            }
        });
        this.add(itemButton);

//        JButton shopButton = new JButton("<html><font size=\"5\">Shop</font></html>");
//        shopButton.setBounds((width * 1080) / 1440, (35 * height) / 800, (130 * width) / 1440, (height * 50) / 800);
//        this.add(shopButton);
//
//        JButton storyButton = new JButton("<html><font size=\"5\">Story</font></html>");
//        storyButton.setBounds((width * 1220) / 1440, (35 * height) / 800, (130 * width) / 1440, (height * 50) / 800);
//        this.add(storyButton);

        JLabel label = new JLabel("<html><font size=\"20\">Choose want you want to make</font></html>");
        label.setBounds((width * 495) / 1440, (height * 260) / 800, (405 * width) / 1440, (height * 175) / 800);
        this.add(label);
    }

    private void makeAbilityButton()
    {
        JButton abilityButton = new JButton("<html><font size=\"5\">Ability</font></html>");
        abilityButton.setBounds((width * 240) / 1440, (35 * height) / 800, (130 * width) / 1440, (height * 50) / 800);
        abilityButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                controller.setPanel(new AbilityCustomUI(controller));
            }
        });
        this.add(abilityButton);
    }

    private void makeAttributeButton()
    {
        JButton attributeButton = new JButton("<html><font size=\"5\">Attribute</font></html>");
        attributeButton.setBounds((width * 100) / 1440, (35 * height) / 800, (130 * width) / 1440, (height * 50) / 800);
        attributeButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                controller.setPanel(new CustomAttributeUI(controller));
            }
        });
        this.add(attributeButton);
    }

    public Controller getController()
    {
        return controller;
    }

    public void setController(Controller controller)
    {
        this.controller = controller;
    }
}
