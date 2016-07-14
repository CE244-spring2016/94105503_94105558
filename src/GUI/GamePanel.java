package GUI;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;

import Controller.*;

/**
 * Created by ruhollah on 6/27/2016.
 */
public class GamePanel extends JPanel/* implements ActionListener*/
{
    private Controller controller;
    private MapTextBox topTextBox;
    private MapTextBox bottomTextBox;
    private int rows;
    private int cols;
    private int gidWidth = 32;
    private int gidHeight = 32;
    private int mapWidth;
    private int mapHeight;
    private String dataStart = "<data";
    private String dataEnd = "</data";
    private ArrayList<BufferedImage> sprites = new ArrayList<>();
    private ArrayList<Gid> gids = new ArrayList<>();
    private ArrayList<ArrayList<String[]>> layers = new ArrayList<>();
    private ArrayList<HeroSprite> heroSprites = new ArrayList<>();
    private HeroSprite heroSprite;
    private String mapAddress;
    private String tileSourceAddress;
    private String spriteAddress;
    private int startingX;
    private int startingY;
    private boolean showing = false;
    private UserInterface userInterface;
    private ArrayList<String> tileSources = new ArrayList<>();
    private HashMap<Gid, BattleScenario> battleMap = new HashMap<>();
    private ArrayList<Integer> battleGidNums = new ArrayList<>();
    private ArrayList<BufferedImage> battleBackgrounds = new ArrayList<>();
    private BattleScenario[] battleScenarios;
    private ArrayList<Integer> shopGidNums = new ArrayList<>();
    private ArrayList<BufferedImage> shopBackgrounds = new ArrayList<>();
    private ArrayList<BufferedImage> shopKeepers = new ArrayList<>();
    private ShopScenario[] shopScenarios;
    private HashMap<Gid, ShopScenario> shopMap = new HashMap<>();
    private ArrayList<Integer> abilityUpgradeGidNums = new ArrayList<>();
    private ArrayList<BufferedImage> abilityUpgradeBackgrounds = new ArrayList<>();
    private AbilityUpgradeScenario[] abilityUpgradeScenarios;
    private HashMap<Gid, AbilityUpgradeScenario> abilityUpgradeMap = new HashMap<>();
    private ArrayList<Integer> storyGidNums = new ArrayList<>();
    private ArrayList<BufferedImage> storyBackgrounds = new ArrayList<>();
    private ArrayList<String> storyParts = new ArrayList<>();
    private StoryScenario[] storyScenarios;
    private HashMap<Gid, StoryScenario> storyMap = new HashMap<>();
    private HashMap<Integer, Integer> doorDirections = new HashMap<>();
    private HashMap<Integer, Integer> doorKeys = new HashMap<>();
    private HashMap<Integer, Door> doors = new HashMap<>();
    private HashMap<Gid, Door> doorMap = new HashMap<>();
    private ArrayList<Integer> keyGidNums = new ArrayList<>();
    private HashMap<Gid, Integer> keyMap = new HashMap<>();
    private TAdapter ultimateListener = new TAdapter();
    private GameOverScenario gameOverScenario;

    public GamePanel(Controller controller)
    {
        this.controller = controller;
        this.userInterface = controller.getUserInterface();
        this.mapAddress = userInterface.getMapAddress();
        this.battleGidNums = userInterface.getBattleGidNums();
        this.tileSources = userInterface.getTileSources();
        this.battleBackgrounds = userInterface.getBattleBackgroundSources();
        battleScenarios = new BattleScenario[battleGidNums.size()];
        this.shopGidNums = userInterface.getShopGidNums();
        this.shopBackgrounds = userInterface.getShopBackgroundSources();
        this.shopKeepers = userInterface.getShopKeeperSources();
        shopScenarios = new ShopScenario[shopGidNums.size()];
        this.abilityUpgradeGidNums = userInterface.getAbilityUpgradeGidNums();
        this.abilityUpgradeBackgrounds = userInterface.getAbilityUpgradeBackgroundSources();
        abilityUpgradeScenarios = new AbilityUpgradeScenario[abilityUpgradeGidNums.size()];
        this.storyGidNums = userInterface.getStoryGidNums();
        this.storyBackgrounds = userInterface.getStoryBackgroundSources();
        this.storyParts = userInterface.getGameStory();
        storyScenarios = new StoryScenario[storyGidNums.size()];
        this.doorDirections = userInterface.getDoorsAndTheirDirections();
        this.doorKeys = userInterface.getDoorsAndTheirKeys();
        this.keyGidNums = userInterface.getKeyGidNums();
        gameOverScenario = new GameOverScenario(this.userInterface.getGameOverBackground(), this);
        startingX = userInterface.getStartingX();
        startingY = userInterface.getStartingY();

        setLayout(null);
        this.addKeyListener(ultimateListener);
        this.setFocusable(true);
        this.requestFocus();

        makeHero();
        saveTileImages();
        readMap();
        showTextBox();
        createGids();
        showMap();

        this.setSize(new Dimension(mapWidth, mapHeight));
        this.setPreferredSize(new Dimension(mapWidth, mapHeight));
        showStartingStory();
    }

    private void showTextBox()
    {
        bottomTextBox = new MapTextBox(mapWidth / 5, mapHeight * 14 / 17, mapWidth * 3 / 5, mapHeight * 3 / 17, this);
        System.out.println(mapWidth);
        this.setComponentZOrder(bottomTextBox.getText(), 0);
        this.setComponentZOrder(bottomTextBox.getBox(), 1);
        this.add(bottomTextBox.getText());
        this.add(bottomTextBox.getBox());
        topTextBox = new MapTextBox(mapWidth / 5, 0, mapWidth * 3 / 5, mapHeight * 3 / 17, this);
        this.setComponentZOrder(topTextBox.getText(), 0);
        this.setComponentZOrder(topTextBox.getBox(), 1);
        this.add(topTextBox.getText());
        this.add(topTextBox.getBox());
    }

    public void showStartingStory()
    {
        controller.setPanel(new StoryScenario(storyBackgrounds.get(0), storyParts.get(0), this).getPanel());
    }

    private void makeHero()
    {
        ArrayList<String> heroNames = userInterface.getHeroNames();
        for (BufferedImage bufferedImage : userInterface.getHerosAndTheirImages().keySet())
        {
            heroSprites.add(new HeroSprite(bufferedImage));
        }
        heroSprite = heroSprites.get(0);
    }

    private void saveTileImages()
    {
        for (String source : tileSources)
        {
            BufferedImage bigImg = null;
            try
            {
                bigImg = ImageIO.read(new File(source));
            } catch (IOException e)
            {
                e.printStackTrace();
            }
            if (bigImg != null)
            {
                rows = bigImg.getHeight() / 32;
                cols = bigImg.getWidth() / 32;
            }

            for (int i = 0; i < rows; i++)
            {
                for (int j = 0; j < cols; j++)
                {
                    if (bigImg != null)
                    {
                        sprites.add(bigImg.getSubimage(j * gidWidth, i * gidHeight, gidWidth, gidHeight));
                        if (battleGidNums.contains(sprites.size() - 1))
                        {
                            int num = battleGidNums.indexOf(sprites.size() - 1);
                            battleScenarios[num] = new BattleScenario(num + 1, heroSprites, this, battleBackgrounds.get(num));
                        } else if (shopGidNums.contains(sprites.size() - 1))
                        {
                            int num = shopGidNums.indexOf(sprites.size() - 1);
                            shopScenarios[num] = new ShopScenario(num + 1, heroSprites, this, shopBackgrounds.get(num), shopKeepers.get(num));
//                            System.out.println((num + 1) + " " + (sprites.size() - 1));
                        } else if (abilityUpgradeGidNums.contains(sprites.size() - 1))
                        {
                            int num = abilityUpgradeGidNums.indexOf(sprites.size() - 1);
                            abilityUpgradeScenarios[num] = new AbilityUpgradeScenario(num + 1, heroSprites, this, abilityUpgradeBackgrounds.get(num));
                        } else if (storyGidNums.contains(sprites.size() - 1))
                        {
                            int num = storyGidNums.indexOf(sprites.size() - 1);
                            storyScenarios[num] = new StoryScenario(storyBackgrounds.get(num + 1), storyParts.get(num + 1), this);
                        } else if (doorDirections.containsKey(sprites.size() - 1))
                        {
                            doors.put(sprites.size() - 1, new Door(doorDirections.get(sprites.size() - 1), doorKeys.get(sprites.size() - 1)));
                        }
                    }
                }
            }
        }
    }

    private void showMap()
    {
//        heroSprite.getCurrentPosition().setBounds(startingX, startingY, 32, 32);
        heroSprite.setX(startingX);
        heroSprite.setY(startingY);
        add(heroSprite.getCurrentPosition());
        for (Gid gid : gids)
        {
            add(gid);
        }

    }

    private void readMap()
    {
        String line;
        try
        {
            FileReader fileReader = new FileReader(mapAddress);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            while ((line = bufferedReader.readLine()) != null)
            {
                if (line.toLowerCase().contains(dataStart))
                {
                    addLayer(bufferedReader);
                }
            }
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    private void addLayer(BufferedReader bufferedReader)
    {
        String line;
        ArrayList<String[]> mapData = new ArrayList<>();

        try
        {
            while ((line = bufferedReader.readLine()) != null)
            {
                if (line.toLowerCase().contains(dataEnd))
                {
                    break;
                }
                mapData.add(line.split(","));
            }
        } catch (IOException e)
        {
            e.printStackTrace();
        }

        mapWidth = mapData.get(0).length * 32;
        mapHeight = mapData.size() * 32;
        layers.add(mapData);
    }

    private void createGids()
    {
        for (int k = layers.size() - 1; k >= 0; k--)
        {
            boolean wallFlag = false;
            if (k == layers.size() - 1)
            {
                wallFlag = true;
            }
            ArrayList<String[]> lines = layers.get(k);

            for (int i = 0; i < lines.size(); i++)
            {
                String[] line = lines.get(i);
                for (int j = 0; j < line.length; j++)
                {
                    int dataValue = Integer.parseInt(line[j]) - 1;
                    if (dataValue == -1)
                    {
                        gids.add(new Gid());
                        continue;
                    }
                    Gid gid = new Gid(new ImageIcon(sprites.get(dataValue)));

                    if (k == 0)
                    {
                        int num = i * line.length + j;

                        if (battleGidNums.contains(num))
                        {
                            battleSetter(gid, battleGidNums.indexOf(num));
                        } else if (shopGidNums.contains(num))
                        {
                            shopSetter(gid, shopGidNums.indexOf(num));
                        } else if (abilityUpgradeGidNums.contains(num))
                        {
                            abilityUpgradeSetter(gid, abilityUpgradeGidNums.indexOf(num));
                        } else if (storyGidNums.contains(num))
                        {
                            storySetter(gid, storyGidNums.indexOf(num));
                        } else if (doorDirections.containsKey(num))
                        {
                            doorSetter(gid, num);
                        } else if (keyGidNums.contains(num))
                        {
                            keySetter(gid, keyGidNums.indexOf(num) + 1);
                        }
                    }
                    gid.setWall(wallFlag);
                    gid.setBounds(j * 32, i * 32, 32, 32);
                    gids.add(gid);
                    if (wallFlag)
                    {
                        gid.setVisible(false);
                    }
                }
            }
        }
    }

    @Override
    protected void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        Rectangle rectangle = new Rectangle(0, 0, 3000, 3000);
        g2d.draw(rectangle);
        g2d.setColor(Color.BLACK);
        g2d.fill(rectangle);
        if (heroSprite != null)
        {
            heroSprite.getCurrentPosition().setBounds(heroSprite.getX(), heroSprite.getY(), heroSprite.getWidth(), heroSprite.getHeight());
        }
        Toolkit.getDefaultToolkit().sync();
    }

    private void keySetter(Gid gid, int num)
    {
        keyMap.put(gid, num);
    }

    private void doorSetter(Gid gid, int num)
    {
        doorMap.put(gid, doors.get(num));
    }

    private void storySetter(Gid gid, int id)
    {
        storyMap.put(gid, storyScenarios[id]);
    }

    private void abilityUpgradeSetter(Gid gid, int id)
    {
        abilityUpgradeMap.put(gid, abilityUpgradeScenarios[id]);
    }

    private void shopSetter(Gid gid, int id)
    {
        shopMap.put(gid, shopScenarios[id]);
    }

    private void battleSetter(Gid gid, int id)
    {
        battleMap.put(gid, battleScenarios[id]);
    }

    public int getMapWidth()
    {
        return mapWidth;
    }

    public void setMapWidth(int mapWidth)
    {
        this.mapWidth = mapWidth;
    }

    public int getMapHeight()
    {
        return mapHeight;
    }

    public void setMapHeight(int mapHeight)
    {
        this.mapHeight = mapHeight;
    }

    public String getTileSourceAddress()
    {
        return tileSourceAddress;
    }

    public void setTileSourceAddress(String tileSourceAddress)
    {
        this.tileSourceAddress = tileSourceAddress;
    }

    public String getSpriteAddress()
    {
        return spriteAddress;
    }

    public void setSpriteAddress(String spriteAddress)
    {
        this.spriteAddress = spriteAddress;
    }

    public JPanel getFinalStoryPanel()
    {
        return new StoryScenario(storyBackgrounds.get(storyBackgrounds.size() - 1), storyParts.get(storyParts.size() - 1), this).getPanel();
    }

    public void gameOver()
    {
        controller.setPanel(gameOverScenario.getPanel());
    }

    private class TAdapter extends KeyAdapter
    {

        @Override
        public void keyReleased(KeyEvent e)
        {
            heroSprite.keyReleased(e);
        }

        @Override
        public void keyPressed(KeyEvent e)
        {
            setFocusable(false);
            if (showing)
            {
                return;
            }
            showing = true;
            Thread showThread = new Thread(new Runnable()
            {
                @Override
                public void run()
                {
                    heroSprite.keyPressed(e);
                    heroSprite.move();

                    if (!isKey())
                    {
                        if (!isStory())
                        {
                            if (!isBattle())
                            {
                                if (!isShop())
                                {
                                    isAbilityUpgrade();
                                }
                            }
                        }
                    }

                    if (isCollision())
                    {
                        heroSprite.moveBack();
                        heroSprite.changeSide(e);
                    } else
                    {
                        if (isDoor(e))
                        {
                            repaint();
                            try
                            {
                                Thread.sleep(90L);
                            } catch (InterruptedException e1)
                            {
                                e1.printStackTrace();
                            }
                            heroSprite.keyPressed(e);
                            heroSprite.move();
                            heroSprite.keyReleased(e);
                        }
                        else
                        {
                            heroSprite.moveBack();
                            heroSprite.changeSide(e);
                        }
                    }

                    repaint();
                    showing = false;
                    setFocusable(true);
                    requestFocus();
                }
            });

            showThread.start();
        }
    }

    private boolean isDoor(KeyEvent e)
    {
        for (Gid gid : doorMap.keySet())
        {
            if (intersects(gid, heroSprite))
            {
                this.setComponentZOrder(bottomTextBox.getText(), 0);
                this.setComponentZOrder(bottomTextBox.getBox(), 1);
                this.setComponentZOrder(topTextBox.getText(), 0);
                this.setComponentZOrder(topTextBox.getBox(), 1);
                if (doorMap.get(gid).getDirection() != e.getKeyCode())
                {
                    this.removeKeyListener(ultimateListener);
                    this.setFocusable(false);
                    if (heroSprite.getX() > mapHeight / 2)
                    {
                        topTextBox.addText("It seems there is no door knob. It's impossible to open the door from this side");
                    }
                    else
                    {
                        bottomTextBox.addText("It seems there is no door knob. It's impossible to open the door from this side");
                    }
                    return false;
                }
                else if (!controller.heroContainsKey(doorMap.get(gid).getId()))
                {
                    this.removeKeyListener(ultimateListener);
                    this.setFocusable(false);
                    if (heroSprite.getX() > mapHeight / 2)
                    {
                        topTextBox.addText("\"Door" + doorMap.get(gid).getId() + "\"" + "<br>" + "It seems to be lucked!");
                    }
                    else
                    {
                        bottomTextBox.addText("\"Door" + doorMap.get(gid).getId() + "\"" + "<br>" + "It seems to be lucked!");
                    }
                    return false;
                }
                heroSprite.move();
                heroSprite.move();
                heroSprite.move();
                heroSprite.move();
                heroSprite.move();
                heroSprite.move();

                return true;
            }
        }
        return true;
    }

    private boolean isKey()
    {
        for (Gid gid : keyMap.keySet())
        {
            if (intersects(gid, heroSprite))
            {
                controller.addTeamItemKey(keyMap.get(gid));
                this.setComponentZOrder(bottomTextBox.getText(), 0);
                this.setComponentZOrder(bottomTextBox.getBox(), 1);
                this.setComponentZOrder(topTextBox.getText(), 0);
                this.setComponentZOrder(topTextBox.getBox(), 1);
                this.setComponentZOrder(heroSprite.getCurrentPosition(), 2);
                this.setComponentZOrder(gid, 3);
                if (heroSprite.getX() > mapHeight / 2)
                {
                    topTextBox.addText("Key" + keyMap.get(gid) + " obtained!");
                }
                else
                {
                    bottomTextBox.addText("Key" + keyMap.get(gid) + " obtained!");
                }
                keyMap.remove(gid);
                this.removeKeyListener(ultimateListener);
                this.setFocusable(false);
                return true;
            }
        }

        return false;
    }

    private boolean isStory()
    {
        for (Gid gid : storyMap.keySet())
        {
            if (intersects(gid, heroSprite))
            {
                StoryScenario storyScenario = storyMap.get(gid);
                controller.setPanel(storyScenario.getPanel());
                storyMap.remove(gid);
                this.setComponentZOrder(heroSprite.getCurrentPosition(), 1);
                this.setComponentZOrder(gid, 2);
                return true;
            }
        }

        return false;
    }

    private void isAbilityUpgrade()
    {
        for (Gid gid : abilityUpgradeMap.keySet())
        {
            if (intersects(gid, heroSprite))
            {
                AbilityUpgradeScenario abilityUpgradeScenario = abilityUpgradeMap.get(gid);
                controller.setPanel(abilityUpgradeScenario.getPanel());
                heroSprite.moveBack();
                heroSprite.moveBack();
                return;
            }
        }
    }

    private boolean isShop()
    {
        for (Gid gid : shopMap.keySet())
        {
            if (intersects(gid, heroSprite))
            {
                ShopScenario shopScenario = shopMap.get(gid);
                controller.setPanel(shopScenario.getPanel());
                heroSprite.moveBack();
                heroSprite.moveBack();
                return true;
            }
        }

        return false;
    }

    private boolean isBattle()
    {
        for (Gid gid : battleMap.keySet())
        {
            if (intersects(gid, heroSprite))
            {
                BattleScenario battleScenario = battleMap.get(gid);
                controller.setPanel(battleScenario.getPanel());
                battleScenario.startEnemyIntroduction();
                battleMap.remove(gid);
//                battleTiles.remove(gid);
                this.setComponentZOrder(heroSprite.getCurrentPosition(), 1);
                this.setComponentZOrder(gid, 2);
                return true;
            }
        }
        return false;
    }

    private boolean isCollision()
    {
        for (Gid gid : gids)
        {
            if (gid.isWall() && intersects(gid, heroSprite))
            {
                return true;
            }
        }
        return false;
    }

    private boolean intersects(Gid testA, HeroSprite testB)
    {
        Rectangle rectA = new Rectangle(testA.getX(), testA.getY(), testA.getWidth(), testA.getHeight());
        Rectangle rectB = new Rectangle(testB.getX(), testB.getY(), testB.getWidth(), testB.getHeight());

        return rectA.intersects(rectB);
    }

    public Controller getController()
    {
        return controller;
    }

    public void setController(Controller controller)
    {
        this.controller = controller;
    }

    public TAdapter getUltimateListener()
    {
        return ultimateListener;
    }

    public void setUltimateListener(TAdapter ultimateListener)
    {
        this.ultimateListener = ultimateListener;
    }

    public ArrayList<String> getStoryParts()
    {
        return storyParts;
    }

    public void setStoryParts(ArrayList<String> storyParts)
    {
        this.storyParts = storyParts;
    }
}
