package GUI;

import javax.swing.*;
import java.awt.*;
import java.util.Scanner;

/**
 * Created by ruhollah on 6/29/2016.
 */
public class UI
{
    public static void main(String[] args)
    {
        EventQueue.invokeLater(new Runnable()
        {
            @Override
            public void run()
            {
                JFrame frame = new JFrame();
                Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
                int width = (int) screenSize.getWidth() / 4;
                int height = (int) screenSize.getHeight() / 2;
                frame.setSize(new Dimension((int) screenSize.getWidth() / 4, (int) screenSize.getHeight() / 2));
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);
                Controller controller = new Controller(frame);
            }
        });
    }
}
