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
public class CustomAttributeUI extends JPanel
{
    private Controller controller;
    private int width = (int) (Toolkit.getDefaultToolkit().getScreenSize().getWidth() * 2) / 8;
    private int height = (int) (Toolkit.getDefaultToolkit().getScreenSize().getHeight() * 2) / 8;

    public CustomAttributeUI(Controller controller)
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
        backButton.setBounds((width * 625) / 720, (height * 15) / 405, (width * 70) / 720, (height * 55) / 405);
        backButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                controller.setPanel(MainCustomPanel.getInstance());
            }
        });
        this.add(backButton);

        JLabel label = new JLabel("<html><font size=\"10\">Add an attribute</font></html>");
        label.setBounds((width * 215) / 720, (height * 65) / 405, (width * 305) / 720, (height * 95) / 405);
        this.add(label);

        JLabel nameLabel = new JLabel("<html><font size=\"5\">Name</font></html>");
        nameLabel.setBounds((width * 115) / 720, (height * 180) / 405, (width * 100) / 720, (height * 40) / 405);
        this.add(nameLabel);

        JTextField textArea = new JTextField();
        textArea.setBounds((width * 215) / 720, (height * 180) / 405, (width * 305) / 720, (height * 40) / 405);
        this.add(textArea);

        JCheckBox enemyCheckBox = new JCheckBox("Enemy attribute");
        enemyCheckBox.setBounds((width * 0) / 720, (height * 250) / 405, (width * 150) / 720, (height * 30) / 405);
        this.add(enemyCheckBox);

        JCheckBox heroCheckBox = new JCheckBox("Hero attribute");
        heroCheckBox.setBounds((width * 200) / 720, (height * 250) / 405, (width * 150) / 720, (height * 30) / 405);
        this.add(heroCheckBox);

        JCheckBox maxMinCheckBox = new JCheckBox("Has max");
        maxMinCheckBox.setBounds((width * 400) / 720, (height * 250) / 405, (width * 150) / 720, (height * 30) / 405);
        this.add(maxMinCheckBox);

        JCheckBox tempCheckBox = new JCheckBox("Temporary effect");
        tempCheckBox.setBounds((width * 600) / 720, (height * 250) / 405, (width * 150) / 720, (height * 30) / 405);
        this.add(tempCheckBox);

        JButton addButton = new JButton("<html><font size=\"5\">Add</font></html>");
        addButton.setBounds((width * 310) / 720, (height * 325) / 405, (width * 90) / 720, (height * 30) / 405);
        addButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                if(!textArea.getText().equals(""))
                {
                    if (enemyCheckBox.isSelected())
                    {
                        controller.getUserInterface().getEnemyAttributes().add(textArea.getText());
                    }

                    if (heroCheckBox.isSelected())
                    {
                        controller.getUserInterface().getHeroAttributes().add(textArea.getText());
                    }

                    if (maxMinCheckBox.isSelected())
                    {
                        controller.getUserInterface().getAttributesWithMax().add(textArea.getText());
                    }

                    if (tempCheckBox.isSelected())
                    {
                        controller.getUserInterface().getAttributeWithTemp().add(textArea.getText());
                    }

                    controller.getUserInterface().getAbilityAttributes().add(textArea.getText());
                }

                textArea.setText("");
                enemyCheckBox.setSelected(false);
                heroCheckBox.setSelected(false);
                maxMinCheckBox.setSelected(false);
                tempCheckBox.setSelected(false);
            }
        });
        this.add(addButton);
    }
}
