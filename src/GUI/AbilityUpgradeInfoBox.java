package GUI;

import javax.swing.*;
import java.awt.*;

/**
 * Created by ruhollah on 7/9/2016.
 */
public class AbilityUpgradeInfoBox
{
    private JLabel infoBox = new JLabel();
    private int x;
    private int y;
    private int width;
    private int height;
    private Controller controller;
    private AbilityUpgradeScenario abilityUpgradeScenario;
    private final int OPTION_TEXT_SIZE;
    private MyScrollPane descriptionScrollPane = new MyScrollPane();
    private MyScrollPane requirementScrollPane = new MyScrollPane();
    private MyScrollTextPane descriptionScrollTextPane = new MyScrollTextPane();
    private MyScrollTextPane requirementScrollTextPane = new MyScrollTextPane();
    private String selectedAbility;

    public AbilityUpgradeInfoBox(int x, int y, int width, int height, Controller controller, AbilityUpgradeScenario abilityUpgradeScenario)
    {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.controller = controller;
        this.abilityUpgradeScenario = abilityUpgradeScenario;
        OPTION_TEXT_SIZE = (10 * width * height) / (1920 * 384);

        MyViewPort viewPort = new MyViewPort();
        viewPort.setView(descriptionScrollTextPane);
        descriptionScrollPane.setViewport(viewPort);
        descriptionScrollPane.setBounds(this.x + (30 * abilityUpgradeScenario.getWidth()) / 1920,
                this.y + (abilityUpgradeScenario.getHeight() * 100) / 1070,
                this.width, this.height / 2 - (abilityUpgradeScenario.getHeight() * 50) / 1070);

        MyViewPort viewPort2 = new MyViewPort();
        viewPort2.setView(requirementScrollTextPane);
        requirementScrollPane.setViewport(viewPort2);
        requirementScrollPane.setBounds(this.x + (30 * abilityUpgradeScenario.getWidth()) / 1920,
                this.height / 2 + (abilityUpgradeScenario.getHeight() * 50) / 1070,
                this.width, this.height / 2 - (abilityUpgradeScenario.getHeight() * 50) / 1070);
    }

    public void setBoxImage(Image resizedTextBox)
    {
        infoBox.setIcon(new ImageIcon(resizedTextBox));
        infoBox.setBounds(x, y, width, height);
        infoBox.setVisible(false);
    }

    public JLabel getInfoBox()
    {
        return infoBox;
    }

    public void setInfoBox(JLabel infoBox)
    {
        this.infoBox = infoBox;
    }

    public int getX()
    {
        return x;
    }

    public void setX(int x)
    {
        this.x = x;
    }

    public int getY()
    {
        return y;
    }

    public void setY(int y)
    {
        this.y = y;
    }

    public int getWidth()
    {
        return width;
    }

    public void setWidth(int width)
    {
        this.width = width;
    }

    public int getHeight()
    {
        return height;
    }

    public void setHeight(int height)
    {
        this.height = height;
    }

    public MyScrollPane getDescriptionScrollPane()
    {
        return descriptionScrollPane;
    }

    public void setDescriptionScrollPane(MyScrollPane descriptionScrollPane)
    {
        this.descriptionScrollPane = descriptionScrollPane;
    }

    public MyScrollPane getRequirementScrollPane()
    {
        return requirementScrollPane;
    }

    public void setRequirementScrollPane(MyScrollPane requirementScrollPane)
    {
        this.requirementScrollPane = requirementScrollPane;
    }

    public MyScrollTextPane getDescriptionScrollTextPane()
    {
        return descriptionScrollTextPane;
    }

    public void setDescriptionScrollTextPane(MyScrollTextPane descriptionScrollTextPane)
    {
        this.descriptionScrollTextPane = descriptionScrollTextPane;
    }

    public MyScrollTextPane getRequirementScrollTextPane()
    {
        return requirementScrollTextPane;
    }

    public void setRequirementScrollTextPane(MyScrollTextPane requirementScrollTextPane)
    {
        this.requirementScrollTextPane = requirementScrollTextPane;
    }

    public String getSelectedAbility()
    {
        return selectedAbility;
    }

    public void setSelectedAbility(String selectedAbility)
    {
        this.selectedAbility = selectedAbility;
    }

    public class MyViewPort extends JViewport
    {
        public MyViewPort()
        {
            this.setOpaque(false);
            this.setPreferredSize(new Dimension(width, height));
        }
    }

    public class MyScrollTextPane extends JTextPane
    {
        public MyScrollTextPane()
        {
            this.setOpaque(false);
            this.setPreferredSize(new Dimension(width , height / 2));
            this.setLayout(null);
            this.setEditable(false);
        }
    }

    public class MyScrollPane extends JScrollPane
    {
        public MyScrollPane()
        {
            this.setOpaque(false);
            this.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
            this.setVisible(false);
        }
    }
}
