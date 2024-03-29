package GUI;

import Controller.GameScenario;
import Controller.NetworkScenario;
import Controller.UserInterface;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.Scanner;

/**
 * Created by ruhollah on 6/29/2016.
 */
public final class OpeningPanel extends JPanel
{
    //    private int width;
//    private int height;
    private Controller controller;
    private static OpeningPanel openingPanel;

    public static OpeningPanel getInstance(int width, int height, Controller controller)
    {
        openingPanel = new OpeningPanel(width, height, controller);
        return openingPanel;
    }

    public static OpeningPanel getInstance()
    {
        return openingPanel;
    }

    private OpeningPanel(int width, int height, Controller controller)
    {
        this.setSize(width, height);
        this.setPreferredSize(new Dimension(width, height));
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
        create();
    }

    private void create()
    {
        JLabel label = new JLabel("<html><font size = \"20\">Welcome!</font></html>");
        label.setBounds((getWidth() * 270) / 694, (getHeight() * 130) / 746, (getWidth() * 200) / 694, (getHeight() * 100) / 746);
        this.add(label);

        JButton button = new JButton("<html><font size = \"5\">Single Player</font></html>");
        button.setBounds((getWidth() * 260) / 694, (getHeight() * 250) / 746, (getWidth() * 160) / 694, (getHeight() * 50) / 746);
        button.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                controller.getUserInterface().checkCustom(false);
                controller.setGameScenario(new GameScenario(controller.getUserInterface(), new Scanner(System.in)));
                controller.startSinglePlayerGame();
            }
        });
        this.add(button);

        JButton customButton = new JButton("<html><font size=\"5\">Custom</font></html>");
        customButton.setBounds((getWidth() * 260) / 694, (getHeight() * 350) / 746, (getWidth() * 160) / 694, (getHeight() * 50) / 746);
        customButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                controller.getUserInterface().checkCustom(true);
                controller.startCustomMaking();
            }
        });
        this.add(customButton);

        JButton multiButton = new JButton("<html><font size=\"5\">MultiPlayer</font></html>");
        multiButton.setBounds((getWidth() * 260) / 694, (getHeight() * 450) / 746, (getWidth() * 160) / 694, (getHeight() * 50) / 746);
        multiButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                controller.getUserInterface().checkCustom(false);
                controller.setGameScenario(new GameScenario(controller.getUserInterface(), new Scanner(System.in)));
                controller.setNetworkScenario(new NetworkScenario(new Scanner(System.in), controller.getGameScenario()));
                controller.startMultiPlayer();
            }
        });
        this.add(multiButton);

        JButton loadButton = new JButton("<html><font size=\"5\">Load</font></html>");
        loadButton.setBounds((getWidth() * 260) / 694, (getHeight() * 550) / 746, (getWidth() * 160) / 694, (getHeight() * 50) / 746);
        loadButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                ObjectInputStream objectinputstream = null;
                try
                {
                    FileInputStream streamIn = new FileInputStream("UserInterface.txt");
                    objectinputstream = new ObjectInputStream(streamIn);
                    try
                    {
                        controller.setUserInterface((UserInterface) objectinputstream.readObject());
                    } catch (ClassNotFoundException e1)
                    {
                        e1.printStackTrace();
                    }
                } catch (IOException e1)
                {
                    e1.printStackTrace();
                }

//                controller.getUserInterface().checkCustom(false);
                controller.setGameScenario(new GameScenario(controller.getUserInterface(), new Scanner(System.in)));
//                controller.setNetworkScenario(new NetworkScenario(new Scanner(System.in), controller.getGameScenario()));
                controller.startLoadGame();
            }
        });
        this.add(loadButton);


    }
}
