package GUI;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;
import Controller.*;

/**
 * Created by ruhollah on 7/13/2016.
 */
public class CustomAbilityUpgrade extends JPanel
{
    MapEditor panel;
    Controller controller;
    private int width = (int) (Toolkit.getDefaultToolkit().getScreenSize().getWidth() * 2) / 8;
    private int height = (int) (Toolkit.getDefaultToolkit().getScreenSize().getHeight() * 2) / 5;

    public CustomAbilityUpgrade(MapEditor panel)
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

        JLabel storyLabel = new JLabel("<html><font size=\"6\">Custom ability upgrade center</font></html>");
        storyLabel.setBounds((width * 200) / 720, (height * 40) / 648, (width * 300) / 720, (height * 60) / 648);
        this.add(storyLabel);

        JLabel backgroundLabel = new JLabel("<html><font size=\"3\">Enter background source</font></html>");
        backgroundLabel.setBounds((width * 200) / 720, (height * 100) / 648, (width * 150) / 720, (height * 30) / 648);
        this.add(backgroundLabel);

        JTextField backgroundSourceField = new JTextField();
        backgroundSourceField.setBounds((width * 350) / 720, (height * 100) / 648, (width * 160) / 720, (height * 30) / 648);
        this.add(backgroundSourceField);

        JButton doneButton = new JButton("done");
        doneButton.setBounds((width * 200) / 720, (height * 140) / 648, (width * 100) / 720, (height * 30) / 648);
        doneButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                try
                {
                    ImageIO.read(new File(backgroundSourceField.getText()));
                    controller.getUserInterface().getAbilityUpgradeBackgroundSources().add(new UltimateImage(backgroundSourceField.getText()));
                    controller.setPanel(panel);
                } catch (IOException e1)
                {
                    backgroundSourceField.setText("Invalid Source");
                }
            }
        });
        this.add(doneButton);
    }
}
