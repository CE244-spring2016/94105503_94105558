package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * Created by ruhollah on 7/13/2016.
 */
public final class NetworkPanel extends JPanel
{
    private static NetworkPanel networkPanel;
    private Controller controller;
    private JLabel waitLabel = new JLabel("<html><font size=\"5\">Waiting for someone to join...</font></html>");
    private int width = (int) (Toolkit.getDefaultToolkit().getScreenSize().getWidth() * 2) / 3;
    private int height = (int) (Toolkit.getDefaultToolkit().getScreenSize().getHeight() * 2) / 3;

    public static NetworkPanel getInstance()
    {
        return networkPanel;
    }

    public static NetworkPanel getInstance(Controller controller)
    {
        if (networkPanel == null)
        {
            networkPanel = new NetworkPanel(controller);
        }

        return networkPanel;
    }

    private NetworkPanel(Controller controller)
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
        JButton hostButton = new JButton("<html><font size=\"5\">Host</font></html>");
        hostButton.setBounds((width * 750) / 1920, (height * 230) / 1080, (width * 160) / 1920, (height * 50) / 1080);
        hostButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                controller.setPanel(new HostPanel(controller));
            }
        });
        this.add(hostButton);

        JButton joinButton = new JButton("<html><font size=\"5\">Join</font></html>");
        joinButton.setBounds((width * 750) / 1920, (height * 330) / 1080, (width * 160) / 1920, (height * 50) / 1080);
        joinButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                controller.setPanel(new JoinPanel(controller));
            }
        });
        this.add(joinButton);
    }
}