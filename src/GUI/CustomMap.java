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
public class CustomMap extends JPanel
{
    private Controller controller;
    private int width = (int) (Toolkit.getDefaultToolkit().getScreenSize().getWidth() * 2) / 16;
    private int height = (int) (Toolkit.getDefaultToolkit().getScreenSize().getHeight() * 2) / 16;

    public CustomMap(Controller controller)
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
        JLabel rowLabel = new JLabel("rows");
        rowLabel.setBounds((width * 60) / 360, (height * 60) / 202, (width * 30) / 360, (height * 30) / 202);
        this.add(rowLabel);

        JTextField rowField = new JTextField();
        rowField.setBounds((width * 90) / 360, (height * 60) / 202, (width * 60) / 360, (height * 30) / 202);
        this.add(rowField);

        JLabel colLabel = new JLabel("cols");
        colLabel.setBounds((width * 175) / 360, (height * 60) / 202, (width * 30) / 360, (height * 30) / 202);
        this.add(colLabel);

        JTextField colField = new JTextField();
        colField.setBounds((width * 200) / 360, (height * 60) / 202, (width * 60) / 360, (height * 30) / 202);
        this.add(colField);

        JButton doneButton = new JButton("done");
        doneButton.setBounds((width * 120) / 360, (height * 100) / 202, (width * 65) / 360, (height * 30) / 202);
        doneButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                if (!colField.getText().equals("") && !rowField.getText().equals(""))
                {
                    controller.setPanel(new MapEditor(Integer.parseInt(rowField.getText()), Integer.parseInt(colField.getText()), controller));
                }
            }
        });
        this.add(doneButton);
    }
}
