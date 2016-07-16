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
import java.util.ArrayList;
import Controller.*;
import javafx.util.Pair;

/**
 * Created by ruhollah on 7/14/2016.
 */
public class CustomHero extends JPanel
{
    Controller controller;
    private int width = (int) (Toolkit.getDefaultToolkit().getScreenSize().getWidth() * 2) / 8;
    private int height = (int) (Toolkit.getDefaultToolkit().getScreenSize().getHeight() * 2) / 5;

    public CustomHero(Controller controller)
    {
        this.controller = controller;
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

        JLabel label = new JLabel("<html><font size=\"10\">Add a hero</font></html>");
        label.setBounds((width * 215) / 720, (height * 65) / 648, (width * 305) / 720, (height * 95) / 648);
        this.add(label);

        JLabel nameLabel = new JLabel("<html><font size=\"5\">Name</font></html>");
        nameLabel.setBounds((width * 95) / 720, (height * 180) / 648, (width * 100) / 720, (height * 40) / 648);
        this.add(nameLabel);

        JTextField nameField = new JTextField();
        nameField.setBounds((width * 175) / 720, (height * 180) / 648, (width * 305) / 720, (height * 40) / 648);
        this.add(nameField);

        JLabel classLabel = new JLabel("<html><font size=\"3\">Class name</font></html>");
        classLabel.setBounds((width * 95) / 720, (height * 230) / 648, (width * 100) / 720, (height * 30) / 648);
        this.add(classLabel);

        ArrayList<String> classNames = controller.getUserInterface().getHeroClassNames();
        JComboBox classBox = new JComboBox(classNames.toArray());
        classBox.setBounds((width * 205) / 720, (height * 230) / 648, (width * 100) / 720, (height * 30) / 648);
        this.add(classBox);

        JLabel faceSourceLabel = new JLabel("<html><font size=\"3\">Enter source image of this hero's face</font></html>");
        faceSourceLabel.setBounds((width * 75) / 720, (height * 270) / 648, (width * 150) / 720, (height * 30) / 648);
        this.add(faceSourceLabel);

        JTextField faceSourceField = new JTextField();
        faceSourceField.setBounds((width * 220) / 720, (height * 270) / 648, (width * 100) / 720, (height * 30) / 648);
        this.add(faceSourceField);

        JLabel faceNumLabel = new JLabel("<html><font size=\"3\">Enter number of the face</font></html>");
        faceNumLabel.setBounds((width * 330) / 720, (height * 270) / 648, (width * 100) / 720, (height * 30) / 648);
        this.add(faceNumLabel);

        JTextField faceNumField = new JTextField();
        faceNumField.setBounds((width * 430) / 720, (height * 270) / 648, (width * 100) / 720, (height * 30) / 648);
        this.add(faceNumField);

        JLabel spriteLabel = new JLabel("<html><font size=\"3\">Enter source image of this hero's sprite</font></html>");
        spriteLabel.setBounds((width * 75) / 720, (height * 310) / 648, (width * 150) / 720, (height * 30) / 648);
        this.add(spriteLabel);

        JTextField spriteField = new JTextField();
        spriteField.setBounds((width * 225) / 720, (height * 310) / 648, (width * 100) / 720, (height * 30) / 648);
        this.add(spriteField);

        JLabel spriteNumLabel = new JLabel("<html><font size=\"3\">Enter number of the sprite</font></html>");
        spriteNumLabel.setBounds((width * 330) / 720, (height * 310) / 648, (width * 100) / 720, (height * 30) / 648);
        this.add(spriteNumLabel);

        JTextField spriteNumField = new JTextField();
        spriteNumField.setBounds((width * 430) / 720, (height * 310) / 648, (width * 100) / 720, (height * 30) / 648);
        this.add(spriteNumField);

        JLabel enteringLabel = new JLabel("<html><font size=\"3\">Enter entering message</font></html>");
        enteringLabel.setBounds((width * 200) / 720, (height * 350) / 648, (width * 100) / 720, (height * 30) / 648);
        this.add(enteringLabel);

        JTextField enteringField = new JTextField();
        enteringField.setBounds((width * 200) / 720, (height * 380) / 648, (width * 300) / 720, (height * 30) / 648);
        this.add(enteringField);

        JButton doneButton = new JButton("Done");
        doneButton.setBounds((width * 200) / 720, (height * 420) / 648, (width * 100) / 720, (height * 30) / 648);
        doneButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                if (!nameField.getText().equals("") && !faceSourceField.getText().equals("") &&
                        !faceNumField.getText().equals("") && !spriteField.getText().equals("") && !spriteNumField.getText().equals(""))
                {
                    try
                    {
                        ImageIO.read(new File(faceSourceField.getText()));
                        ImageIO.read(new File(spriteField.getText()));
                        int faceNum = Integer.parseInt(faceNumField.getText());
                        int spriteNum = Integer.parseInt(spriteNumField.getText());
                        Pair<String, String> enteringMessage = new Pair<>(nameField.getText(), enteringField.getText());

                        controller.getUserInterface().getHeroNames().add(nameField.getText());
                        controller.getUserInterface().getHerosAndTheirClasses().put(nameField.getText(), (String) classBox.getSelectedItem());
                        controller.getUserInterface().getHeroFaces().put(nameField.getText(),
                                new UltimateImage(((faceNum - 1) % 4) * 96, ((faceNum - 1) / 4) * 96, 96, 96, faceSourceField.getText()));
                        controller.getUserInterface().getHerosAndTheirImages()
                                .put(new UltimateImage(((spriteNum - 1) % 4) * 96, ((spriteNum - 1) / 4) * 128, 96, 128, spriteField.getText()),
                                        nameField.getText());
                        controller.getUserInterface().getHeroEnteringMessage().add(enteringMessage);
                        controller.setPanel(new CustomHeroAbilities(controller, nameField.getText(), (String) classBox.getSelectedItem()));
                    } catch (IOException e1)
                    {
                        faceSourceField.setText("Invalid Inputs");
                        spriteField.setText("");
                    } catch (Exception e2)
                    {
                        faceNumField.setText("Invalid Inputs");
                        spriteNumField.setText("");
                        e2.printStackTrace();
                    }
                }
            }
        });
        this.add(doneButton);
    }
}
