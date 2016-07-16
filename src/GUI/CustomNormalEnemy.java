package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * Created by ruhollah on 7/14/2016.
 */
public class CustomNormalEnemy extends JPanel
{
    private Controller controller;
    private int width = (int) (Toolkit.getDefaultToolkit().getScreenSize().getWidth() * 2) / 8;
    private int height = (int) (Toolkit.getDefaultToolkit().getScreenSize().getHeight() * 2) / 5;

    public CustomNormalEnemy(Controller controller)
    {
        System.out.println(width + " " + height);
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
        JButton backButton = new JButton("<html><font size=\"3\">Back to menu</font></html>");
        backButton.setBounds((width * 625) / 720, (height * 15) / 648, (width * 70) / 720, (height * 55) / 648);
        backButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                controller.setPanel(MainCustomPanel.getInstance());
            }
        });
        this.add(backButton);

        JLabel label = new JLabel("<html><font size=\"10\">Add an enemy</font></html>");
        label.setBounds((width * 215) / 720, (height * 65) / 648, (width * 305) / 720, (height * 95) / 648);
        this.add(label);

        /////

        JLabel nameLabel = new JLabel("<html><font size=\"4\">Name</font></html>");
        nameLabel.setBounds((width * 75) / 720, (height * 180) / 648, (width * 100) / 720, (height * 40) / 648);
        this.add(nameLabel);

        JTextField nameField = new JTextField();
        nameField.setBounds((width * 175) / 720, (height * 180) / 648, (width * 305) / 720, (height * 40) / 648);
        this.add(nameField);

        /////

        JLabel versionLabel = new JLabel("<html><font size=\"4\">Version name</font></html>");
        versionLabel.setBounds((width * 75) / 720, (height * 230) / 648, (width * 100) / 720, (height * 40) / 648);
        this.add(versionLabel);

        JTextField versionField = new JTextField();
        versionField.setBounds((width * 175) / 720, (height * 230) / 648, (width * 305) / 720, (height * 40) / 648);
        this.add(versionField);

        /////

        JLabel enemySuccessLabel = new JLabel("<html><font size=\"3\">Enter enemy success message</font></html>");
        enemySuccessLabel.setBounds((width * 75) / 720, (height * 280) / 648, (width * 150) / 720, (height * 30) / 648);
        this.add(enemySuccessLabel);

        JTextField enemySuccessField = new JTextField();
        enemySuccessField.setBounds((width * 225) / 720, (height * 280) / 648, (width * 100) / 720, (height * 30) / 648);
        this.add(enemySuccessField);

        /////

        JButton addButton = new JButton("Add");
        addButton.setBounds((width * 200) / 720, (height * 320) / 648, (width * 100) / 720, (height * 30) / 648);
        addButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                if (!nameField.getText().equals("") && !versionField.getText().equals(""))
                {
                    controller.getUserInterface().getAllEnemySuccessMessages().put(nameField.getText(), enemySuccessField.getText());
                    controller.setPanel(new CustomEnemyVersion(controller, nameField.getText(), versionField.getText()));
                }
            }
        });
        this.add(addButton);
    }
}
