package GUI;

import GUI.MapEditor;
import jdk.nashorn.internal.scripts.JO;

import javax.swing.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

/**
 * Created by ruhollah on 6/28/2016.
 */
public class MyMouseListener implements MouseListener
{
    private MapEditor panel;
    private JLabel label;
    private int labelLayerLevel;
    private int gidNum;
    private static int prevGidNum;


    public MyMouseListener(MapEditor panel, JLabel label, int labelLayerLevel)
    {
        this.panel = panel;
        this.label = label;
        this.labelLayerLevel = labelLayerLevel;
        gidNum = (((label.getY() - 50) / 32) * panel.getCol()) + (label.getX() - 50) / 32;
    }

    @Override
    public void mouseClicked(MouseEvent e)
    {
        if (panel.getLayerNum() == labelLayerLevel)
        {
            ImageIcon image = this.panel.getSelectedIcon();
            if(!panel.isErasing())
            {
                this.label.setIcon(image);
            }
            else
            {
                if(image != null)
                {
                    this.label.setIcon(null);
                }
            }
        }
    }

    @Override
    public void mousePressed(MouseEvent e)
    {
        if (panel.getLayerNum() == labelLayerLevel)
        {
            ImageIcon image = this.panel.getSelectedIcon();
            if(!panel.isErasing())
            {
                this.label.setIcon(image);
            }
            else
            {
                if(image != null)
                {
                    this.label.setIcon(null);
                }
            }
            panel.setHolding(true);
        }

        if (panel.getSituation() == MapEditor.Situation.ChoosingDoor)
        {
            int reply = JOptionPane.showConfirmDialog(null, "Are you sure?", "Children Of Time", JOptionPane.YES_NO_OPTION);
            if (reply == JOptionPane.YES_OPTION)
            {
                chooseDoorDirection();
                panel.setHolding(false);
            }
            else
            {
                label.setIcon(null);
                panel.setHolding(false);
            }
        }
        else if (panel.getSituation() == MapEditor.Situation.ChoosingKey)
        {
            int reply = JOptionPane.showConfirmDialog(null, "Are you sure?", "Children Of Time", JOptionPane.YES_NO_OPTION);
            if (reply == JOptionPane.YES_OPTION)
            {
                panel.getController().getUserInterface().getKeyGidNums().add(gidNum);
                int keyAmount = panel.getController().getUserInterface().getKeyGidNums().size();
                panel.getController().getUserInterface().getDoorsAndTheirKeys().put(prevGidNum, keyAmount);
                panel.setHolding(false);
                panel.addText("Choose door tiles");
                panel.setSituation(MapEditor.Situation.ChoosingDoor);
            }
            else
            {
                label.setIcon(null);
                panel.setHolding(false);
            }
        }
        else if (panel.getSituation() == MapEditor.Situation.ChoosingBattle)
        {
            int reply = JOptionPane.showConfirmDialog(null, "Are you sure?", "Children Of Time", JOptionPane.YES_NO_OPTION);
            if (reply == JOptionPane.YES_OPTION)
            {
                panel.getController().getUserInterface().getBattleGidNums().add(gidNum);
                panel.getController().setPanel(new CustomBattle(panel));
                panel.setHolding(false);
            }
            else
            {
                label.setIcon(null);
                panel.setHolding(false);
            }
        }
        else if (panel.getSituation() == MapEditor.Situation.ChoosingShop)
        {
            int reply = JOptionPane.showConfirmDialog(null, "Are you sure?", "Children Of Time", JOptionPane.YES_NO_OPTION);
            if (reply == JOptionPane.YES_OPTION)
            {
                panel.getController().getUserInterface().getShopGidNums().add(gidNum);
                panel.getController().setPanel(new CustomShop(panel)); // TODO
                panel.setHolding(false);
            }
            else
            {
                label.setIcon(null);
                panel.setHolding(false);
            }
        }
        else if (panel.getSituation() == MapEditor.Situation.ChoosingAbilityUpgrade)
        {
            int reply = JOptionPane.showConfirmDialog(null, "Are you sure?", "Children Of Time", JOptionPane.YES_NO_OPTION);
            if (reply == JOptionPane.YES_OPTION)
            {
                panel.getController().getUserInterface().getAbilityUpgradeGidNums().add(gidNum);
                panel.getController().setPanel(new CustomAbilityUpgrade(panel)); // TODO
                panel.setHolding(false);
            }
            else
            {
                label.setIcon(null);
                panel.setHolding(false);
            }
        }
        else if (panel.getSituation() == MapEditor.Situation.ChoosingStory)
        {
            int reply = JOptionPane.showConfirmDialog(null, "Are you sure?", "Children Of Time", JOptionPane.YES_NO_OPTION);
            if (reply == JOptionPane.YES_OPTION)
            {
                panel.getController().getUserInterface().getStoryGidNums().add(gidNum);
                panel.getController().setPanel(new CustomStory(panel)); // TODO
                panel.setHolding(false);
            }
            else
            {
                label.setIcon(null);
                panel.setHolding(false);
            }
        }
        else if (panel.getSituation() == MapEditor.Situation.ChooseStartingPoint)
        {
            int reply = JOptionPane.showConfirmDialog(null, "Are you sure?", "Children Of Time", JOptionPane.YES_NO_OPTION);
            if (reply == JOptionPane.YES_OPTION)
            {
                panel.getController().getUserInterface().setStartingX(label.getX() - 50);
                panel.getController().getUserInterface().setStartingY(label.getY() - 50);
                panel.getController().setPanel(new CustomInitials(panel));
                panel.setHolding(false);
            }
            else
            {
                label.setIcon(null);
                panel.setHolding(false);
            }
            // Start game
        }
    }

    private void chooseDoorDirection()
    {
        JOptionPane directionPane = new JOptionPane("Which Direction?", JOptionPane.QUESTION_MESSAGE, JOptionPane.DEFAULT_OPTION);
        JDialog dialog = directionPane.createDialog("Children Of Time");

        JButton leftButton = new JButton("Left");
        leftButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                panel.getController().getUserInterface().getDoorsAndTheirDirections().put(gidNum, KeyEvent.VK_LEFT);
                dialog.dispose();
                checkDoorKey();
            }
        });

        JButton rightButton = new JButton("Right");
        rightButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                panel.getController().getUserInterface().getDoorsAndTheirDirections().put(gidNum, KeyEvent.VK_RIGHT);
                dialog.dispose();
                checkDoorKey();
            }
        });

        JButton upButton = new JButton("Up");
        upButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                panel.getController().getUserInterface().getDoorsAndTheirDirections().put(gidNum, KeyEvent.VK_UP);
                dialog.dispose();
                checkDoorKey();
            }
        });

        JButton downButton = new JButton("Down");
        downButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                panel.getController().getUserInterface().getDoorsAndTheirDirections().put(gidNum, KeyEvent.VK_DOWN);
                dialog.dispose();
                checkDoorKey();
            }
        });

        Object[] options = {leftButton, rightButton, upButton, downButton};
        directionPane.setOptions(options);
        dialog.setVisible(true);
    }

    private void checkDoorKey()
    {
        int reply = JOptionPane.showConfirmDialog(null, "Is there a key needed to open this door", "Children Of Time", JOptionPane.YES_NO_OPTION);
        if (reply == JOptionPane.YES_OPTION)
        {
            prevGidNum = gidNum;
            panel.setSituation(MapEditor.Situation.ChoosingKey);
            panel.addText("Choose the key tile");
        }
        else
        {
            panel.getController().getUserInterface().getDoorsAndTheirKeys().put(gidNum, 0);
        }
    }

    @Override
    public void mouseReleased(MouseEvent e)
    {
        if (panel.getLayerNum() == labelLayerLevel)
        {
            panel.setHolding(false);
        }
    }

    @Override
    public void mouseEntered(MouseEvent e)
    {
        if (panel.getLayerNum() == labelLayerLevel)
        {
            if(panel.isHolding())
            {
                ImageIcon image = this.panel.getSelectedIcon();
                if(!panel.isErasing())
                {
                    this.label.setIcon(image);
                }
                else
                {
                    if(image != null)
                    {
                        this.label.setIcon(null);
                    }
                }
            }
        }
    }

    @Override
    public void mouseExited(MouseEvent e)
    {

    }

    public JLabel getLabel()
    {
        return label;
    }

    public void setLabel(JLabel label)
    {
        this.label = label;
    }
}
