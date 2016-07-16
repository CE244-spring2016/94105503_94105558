package GUI;

import Controller.GameScenario;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by ruhollah on 7/12/2016.
 */
public class MapEditor extends JPanel
{
    private final int tileWidth = 32;
    private final int tileHeight = 32;
    private Controller controller;
    private int row;
    private int col;
    private int width = (int) (Toolkit.getDefaultToolkit().getScreenSize().getWidth() * 7) / 8;
    private int height = (int) (Toolkit.getDefaultToolkit().getScreenSize().getHeight() * 7) / 8;
    private BufferedImage[] normalSprites1;
    private BufferedImage[] normalSprites2;
    private BufferedImage[] objectSprites1;
    private BufferedImage[] objectSprites2;
    private BufferedImage[] objectSprites3;
    private BufferedImage collisionSprite;
    private boolean holding = false;
    private boolean erasing = false;
    private ImageIcon selectedIcon;
    private MyScrollPane scrollPane = new MyScrollPane();
    private MyScrollPanel scrollPanel = new MyScrollPanel();
    private int layerNum = 1;
    private ArrayList<ArrayList<JLabel>> layers = new ArrayList<>();
    private ArrayList<JLabel> currentLabels = new ArrayList<>();
    private boolean layersFinished = false;
    private Situation situation = Situation.Default;
    private JLabel layerLabel;
    private JButton erasingButton;

    public MapEditor(int row, int col, Controller controller)
    {
        System.out.println(width + " " + height);
        this.controller = controller;
        this.row = row;
        this.col = col;
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
        MyViewPort viewPort = new MyViewPort();
        viewPort.setView(scrollPanel);
        scrollPane.setViewport(viewPort);

        create();
    }

    private void create()
    {
        createLabels();
        createNormalTiles();
        createItemTiles();
        try
        {
            collisionSprite = ImageIO.read(new File("Main Pics/Collisions/Collision.png"));
        } catch (IOException e)
        {
            e.printStackTrace();
        }
        layerLabel = new JLabel("<html><font size=\"10\">Layer " + layerNum + "</font></html>");
        layerLabel.setBounds((width * 1880) / 2520, (height * 45) / 1417, (width * 245) / 2125, (height * 125) / 1417);
        this.add(layerLabel);
        scrollPane.setBounds((width * 1530) / 2520, (height * 275) / 1417, (width * 825) / 2125, (height * 625) / 1417);
        this.add(scrollPane);

        JButton normalButton1 = new JButton("Normal 1");
        normalButton1.setBounds((width * 1530) / 2520, (height * 910) / 1417, (width * 100) / 2125, (height * 30) / 1417);
        normalButton1.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                scrollPanel.removeAll();
                createKeys(normalSprites1, 16, 8);
                scrollPanel.repaint();
            }
        });
        this.add(normalButton1);

        JButton normalButton2 = new JButton("Normal 2");
        normalButton2.setBounds((width * 1640) / 2520, (height * 910) / 1417, (width * 100) / 2125, (height * 30) / 1417);
        normalButton2.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                scrollPanel.removeAll();
                createKeys(normalSprites2, 15, 16);
                scrollPanel.repaint();
            }
        });
        this.add(normalButton2);

        JButton object1 = new JButton("Object 1");
        object1.setBounds((width * 1530) / 2520, (height * 950) / 1417, (width * 100) / 2125, (height * 30) / 1417);
        object1.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                scrollPanel.removeAll();
                createKeys(objectSprites1, 39, 16);
                scrollPanel.repaint();
            }
        });
        this.add(object1);

        JButton object2 = new JButton("Object 2");
        object2.setBounds((width * 1640) / 2520, (height * 950) / 1417, (width * 100) / 2125, (height * 30) / 1417);
        object2.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                scrollPanel.removeAll();
                createKeys(objectSprites2, 8, 12);
                scrollPanel.repaint();
            }
        });
        this.add(object2);

        JButton object3 = new JButton("Object 3");
        object3.setBounds((width * 1750) / 2520, (height * 950) / 1417, (width * 100) / 2125, (height * 30) / 1417);
        object3.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                scrollPanel.removeAll();
                createKeys(objectSprites3, 16, 16);
                scrollPanel.repaint();
            }
        });
        this.add(object3);

        JButton doneButton = new JButton("done");
        doneButton.setBounds((width * 1530) / 2520, (height * 990) / 1417, (width * 100) / 2125, (height * 30) / 1417);
        doneButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                layerNum++;
                layerLabel.setText("<html><font size=\"10\">Layer " + layerNum + "</font></html>");
                createLabels();
            }
        });
        this.add(doneButton);

        JButton endButton = new JButton("Next");
        endButton.setBounds((width * 1530) / 2520, (height * 1030) / 1417, (width * 100) / 2125, (height * 30) / 1417);
        endButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                layerNum++;
                if (situation == Situation.Default)
                {
                    layerLabel.setText("<html><font size=\"10\">Choose Walls</font></html>");
                    normalButton1.setVisible(false);
                    normalButton2.setVisible(false);
                    object1.setVisible(false);
                    object2.setVisible(false);
                    object3.setVisible(false);
                    doneButton.setVisible(false);
                    scrollPanel.removeAll();
                    BufferedImage[] collision = new BufferedImage[1];
                    collision[0] = collisionSprite;
                    createKeys(collision, 1, 1);
                    scrollPane.repaint();
                    situation = Situation.ChoosingWall;
                } else if (situation == Situation.ChoosingWall)
                {
                    layersFinished = true;
                    layerLabel.setText("<html><font size=\"10\">Choose door tiles</font></html>");
                    situation = Situation.ChoosingDoor;
                    erasingButton.setVisible(false);
                } else if (situation == Situation.ChoosingDoor)
                {
                    layerLabel.setText("<html><font size=\"10\">Choose battle tiles</font></html>");
//                    createDoorData();
                    situation = Situation.ChoosingBattle;
                } else if (situation == Situation.ChoosingBattle)
                {
                    layerLabel.setText("<html><font size=\"10\">Choose shop tiles</font></html>");
//                    createBattleData();
                    situation = Situation.ChoosingShop;
                } else if (situation == Situation.ChoosingShop)
                {
                    layerLabel.setText("<html><font size=\"8\">Choose ability upgrade tiles</font></html>");
//                    createAbilityUpgradeData();
                    situation = Situation.ChoosingAbilityUpgrade;
                } else if (situation == Situation.ChoosingAbilityUpgrade)
                {
                    layerLabel.setText("<html><font size=\"10\">Choose story tiles</font></html>");
//                    createStoryData();
                    situation = Situation.ChoosingStory;
                } else if (situation == Situation.ChoosingStory)
                {
                    layerLabel.setText("<html><font size=\"10\">Choose the starting point</font></html>");
//                    createKeyData();
                    situation = Situation.ChooseStartingPoint;
                }
                if (situation != Situation.ChoosingWall && situation != Situation.ChoosingDoor)
                {
                    for (JLabel label : currentLabels)
                    {
                        label.setIcon(null);
                    }
                }
                createLabels();
            }
        });
        this.add(endButton);

        erasingButton = new JButton("Erase");
        erasingButton.setBounds((width * 1530) / 2520, (height * 1070) / 1417, (width * 100) / 2125, (height * 30) / 1417);
        erasingButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                if (situation != Situation.ChoosingKey && situation != Situation.ChoosingDoor)
                {
                    erasing = true;
                }
            }
        });
        this.add(erasingButton);
    }

    public void addText(String text)
    {
        layerLabel.setText("<html><font size=\"10\">" + text + "</font></html>");
    }

    private void createItemTiles()
    {
        objectSprites1 = new BufferedImage[16 * 39];
        BufferedImage bigImg = null;
        try
        {
            bigImg = ImageIO.read(new File("Main Pics/Items/IconSet.png"));
        } catch (IOException e)
        {
            e.printStackTrace();
        }

        for (int i = 0; i < 39; i++)
        {
            for (int j = 0; j < 16; j++)
            {
                if (bigImg != null)
                {
                    objectSprites1[(i * 16) + j] = bigImg.getSubimage(j * tileWidth, i * tileHeight, tileWidth, tileHeight);
                }
            }
        }

        objectSprites2 = new BufferedImage[12 * 8];
        BufferedImage bigImg2 = null;
        try
        {
            bigImg2 = ImageIO.read(new File("Main Pics/Doors/Door.png"));
        } catch (IOException e)
        {
            e.printStackTrace();
        }

        for (int i = 0; i < 8; i++)
        {
            for (int j = 0; j < 12; j++)
            {
                if (bigImg2 != null)
                {
                    objectSprites2[(i * 12) + j] = bigImg2.getSubimage(j * tileWidth, i * tileHeight, tileWidth, tileHeight);
                }
            }
        }

        try
        {
            objectSprites2[12 * 8 - 1] = ImageIO.read(new File("Main Pics/Items/Thunder.png"));
        } catch (IOException e)
        {
            e.printStackTrace();
        }

        ///

        objectSprites3 = new BufferedImage[16 * 16];
        BufferedImage bigImg3 = null;
        try
        {
            bigImg3 = ImageIO.read(new File("Main Pics/SpecialPlaces/World_B.png"));
        } catch (IOException e)
        {
            e.printStackTrace();
        }

        for (int i = 0; i < 16; i++)
        {
            for (int j = 0; j < 16; j++)
            {
                if (bigImg3 != null)
                {
                    objectSprites3[(i * 16) + j] = bigImg3.getSubimage(j * tileWidth, i * tileHeight, tileWidth, tileHeight);
                }
            }
        }
    }

    private void createNormalTiles()
    {
        normalSprites1 = new BufferedImage[8 * 16];
        BufferedImage bigImg = null;
        try
        {
            bigImg = ImageIO.read(new File("Main Pics/Tiles/Inside_A5.png"));
        } catch (IOException e)
        {
            e.printStackTrace();
        }

        for (int i = 0; i < 16; i++)
        {
            for (int j = 0; j < 8; j++)
            {
                if (bigImg != null)
                {
                    normalSprites1[(i * 8) + j] = bigImg.getSubimage(j * tileWidth, i * tileHeight, tileWidth, tileHeight);
                }
            }
        }

        normalSprites2 = new BufferedImage[15 * 16];
        BufferedImage bigImg2 = null;
        try
        {
            bigImg2 = ImageIO.read(new File("Main Pics/Tiles/TileA4.png"));
        } catch (IOException e)
        {
            e.printStackTrace();
        }

        for (int i = 0; i < 15; i++)
        {
            for (int j = 0; j < 16; j++)
            {
                if (bigImg2 != null)
                {
                    normalSprites2[(i * 16) + j] = bigImg2.getSubimage(j * tileWidth, i * tileHeight, tileWidth, tileHeight);
                }
            }
        }
    }

    private void createLabels()
    {
        currentLabels = new ArrayList<>();
        for (int i = 50; i < 50 + row * tileHeight; i += 32)
        {
            for (int j = 50; j < 50 + col * tileWidth; j += 32)
            {
                JLabel label = new JLabel();
                label.setBounds(j, i, tileWidth, tileHeight);
                currentLabels.add(label);
                MyMouseListener mouseListener = new MyMouseListener(this, label, layerNum);
                label.addMouseListener(mouseListener);
                this.add(label);
                this.setComponentZOrder(label, 0);
            }
        }
        if (!layersFinished)
        {
            layers.add(currentLabels);
        }
    }

    private void createKeys(BufferedImage[] sprites, int rows, int cols)
    {
        for (int i = 0; i < rows; i++)
        {
            for (int j = 0; j < cols; j++)
            {
                JButton button = new JButton(new ImageIcon(sprites[i * cols + j]));
                button.setBounds(j * tileWidth, i * tileHeight, tileWidth, tileHeight);
                button.addActionListener(new ActionListener()
                {
                    @Override
                    public void actionPerformed(ActionEvent e)
                    {
                        erasing = false;
                        selectedIcon = (ImageIcon) button.getIcon();
                    }
                });
                scrollPanel.add(button);
            }
        }
    }

    public void createGame()
    {
        controller.getUserInterface().setMap(layers);
        controller.getUserInterface().setMapWidth(col * 32);
        controller.getUserInterface().setMapHeight(row * 32);
        controller.setGameScenario(new GameScenario(controller.getUserInterface(), new Scanner(System.in)));
        controller.setGamePanel(new GamePanel(controller, controller.getUserInterface().getMap(), col * 32, row * 32));
        JOptionPane optionPane = new JOptionPane("Do you want to save?",
                JOptionPane.PLAIN_MESSAGE, JOptionPane.DEFAULT_OPTION,
                null);
        JDialog dialog = optionPane.createDialog("Children Of Time");
        JButton saveButton = new JButton("Yes");
        saveButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                String fileName2 = "UserInterface.txt";
                try
                {
                    FileOutputStream fos2 = new FileOutputStream(fileName2);
                    ObjectOutputStream oos2 = new ObjectOutputStream(fos2);
                    oos2.writeObject(controller.getUserInterface());
                    oos2.close();
                } catch (IOException e1)
                {
                    e1.printStackTrace();
                }
                dialog.dispose();
            }
        });
        JButton notsaveButton = new JButton("NO");
        notsaveButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {

            }
        });
        Object[] options = {saveButton, notsaveButton};
        optionPane.setOptions(options);
        optionPane.setInitialValue(options[0]);
        dialog.setVisible(true);
        controller.setPanel(controller.getGamePanel());
    }

    @Override
    protected void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        Rectangle rect = new Rectangle(50, 50, col * tileWidth, row * tileHeight);
        g2d.draw(rect);
        for (int i = 50; i < (50 + col * tileHeight); i += tileHeight)
        {
            g2d.drawLine(i, 50, i, 50 + row * tileHeight);
        }

        for (int i = 50; i < (50 + row * tileHeight); i += tileWidth)
        {
            g2d.drawLine(50, i, 50 + col * tileWidth, i);
        }
    }

    public boolean isHolding()
    {
        return holding;
    }

    public void setHolding(boolean holding)
    {
        this.holding = holding;
    }

    public boolean isErasing()
    {
        return erasing;
    }

    public void setErasing(boolean erasing)
    {
        this.erasing = erasing;
    }

    public ImageIcon getSelectedIcon()
    {
        return selectedIcon;
    }

    public void setSelectedIcon(ImageIcon selectedIcon)
    {
        this.selectedIcon = selectedIcon;
    }

    public Controller getController()
    {
        return controller;
    }

    public void setController(Controller controller)
    {
        this.controller = controller;
    }

    public int getLayerNum()
    {
        return layerNum;
    }

    public void setLayerNum(int layerNum)
    {
        this.layerNum = layerNum;
    }

    public Situation getSituation()
    {
        return situation;
    }

    public void setSituation(Situation situation)
    {
        this.situation = situation;
    }

    public int getRow()
    {
        return row;
    }

    public void setRow(int row)
    {
        this.row = row;
    }

    public int getCol()
    {
        return col;
    }

    public void setCol(int col)
    {
        this.col = col;
    }

    public JButton getErasingButton()
    {
        return erasingButton;
    }

    public void setErasingButton(JButton erasingButton)
    {
        this.erasingButton = erasingButton;
    }

    public enum Situation
    {
        Default, ChoosingWall, ChoosingDoor, ChoosingAbilityUpgrade,
        ChoosingBattle, ChoosingStory, ChoosingShop, ChoosingKey, ChooseStartingPoint
    }

    public class MyViewPort extends JViewport
    {
        public MyViewPort()
        {
            this.setOpaque(false);
            this.setPreferredSize(new Dimension(width / 2, height));
        }
    }

    public class MyScrollPanel extends JPanel
    {
        public MyScrollPanel()
        {
            this.setOpaque(false);
            this.setPreferredSize(new Dimension(width / 2, height));
            this.setLayout(null);
        }
    }

    public class MyScrollPane extends JScrollPane
    {
        public MyScrollPane()
        {
            this.setOpaque(false);
            this.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        }
    }
}
