package GUI;

import Auxiliary.Luck;
import Controller.GameScenario;
import Controller.UserInterface;
import Exceptions.*;
import Model.*;
import javafx.util.Pair;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

/**
 * Created by ruhollah on 6/29/2016.
 */
public class Controller
{
    private JFrame frame;
    private JPanel mainPanel = new JPanel();
    private OpeningPanel openingPanel;
    private GamePanel singlePlayerGame;
    private GamePanel multiplayerGame;
    private GameScenario gameScenario;
    private UserInterface userInterface;

    public Controller(JFrame jFrame)
    {
        Scanner in = new Scanner(System.in);
        UserInterface userInterface = new UserInterface(in);
        gameScenario = new GameScenario(userInterface, in);
        this.userInterface = userInterface;
        this.frame = jFrame;
        this.openingPanel = OpeningPanel.getInstance(frame.getWidth(), frame.getHeight(), this);

        this.setPanel(openingPanel);
    }

    public void setPanel(JPanel panel)
    {
        JPanel contentPane = (JPanel) frame.getContentPane();

        contentPane.removeAll();
        mainPanel.removeAll();
        mainPanel.add(panel);
        mainPanel.setPreferredSize(new Dimension(panel.getWidth(), panel.getHeight()));
        contentPane.add(mainPanel);
        panel.setFocusable(true);
        panel.requestFocus();
        contentPane.revalidate();
        contentPane.repaint();
        frame.pack();
    }

    public void startSinglePlayerGame()
    {
        singlePlayerGame = new GamePanel(this);
        singlePlayerGame.setPreferredSize(new Dimension(singlePlayerGame.getMapWidth(), singlePlayerGame.getMapHeight()));
        setPanel(singlePlayerGame);
        singlePlayerGame.showStartingStory();
//        frame.pack();
//        frame.setSize(singlePlayerGame.getMapWidth(), singlePlayerGame.getMapHeight());
    }

    public String findHeroName(BufferedImage bufferedImage)
    {
        HashMap<BufferedImage, String> allHeroImages = userInterface.getHerosAndTheirImages();

        for (BufferedImage thisBufferedImage : allHeroImages.keySet())
        {
            if (thisBufferedImage.equals(bufferedImage))
            {
                return allHeroImages.get(bufferedImage);
            }
        }

        System.out.println("LOL?!");
        return null;
    }

    public ArrayList<String> findAbilityNamesOfAHero(BufferedImage heroImage) // must clean
    {
        ArrayList<String> result = new ArrayList<>();
        String heroName = findHeroName(heroImage);
        ArrayList<Hero> heros = gameScenario.getHeros();
        Hero wantedHero = null;
        for (Hero hero : heros)
        {
            if (hero.getName().equals(heroName))
            {
                wantedHero = hero;
                break;
            }
        }
        if (wantedHero != null)
        {
            ArrayList<Ability> abilities = wantedHero.getAbilities();
            for (Ability ability : abilities)
            {
                if (ability instanceof ActiveAbility && ability.getCurrentUpgradeNum() != 0)
                {
                    result.add(ability.getName());
                }
            }

            return result;
        }

        return null;
    }

    public ArrayList<Pair<BufferedImage, Integer>> getEnemyGroupImage(int id)
    {
        ArrayList<Enemy> wantedEnemies = gameScenario.getEnemyGroups().get(id).getEnemies();
        ArrayList<Pair<BufferedImage, Integer>> result = new ArrayList<>();

        for (Enemy enemy : wantedEnemies)
        {
            String enemyName = enemy.getName();
            Integer enemyID = enemy.getID();
            if (enemy instanceof NormalEnemy)
            {
                enemyName = ((NormalEnemy) enemy).getVersion() + " " + enemyName;
            }

            result.add(new Pair<>(findEnemyImage(enemyName), enemyID));
        }

        return result;
    }

    public BufferedImage findEnemyImage(String enemyName)
    {
        HashMap<BufferedImage, String> allBossImages = userInterface.getBossEnemyImages();
        HashMap<BufferedImage, String> allNormalImages = userInterface.getAllNormalEnemyImages();

        for (BufferedImage bufferedImage : allBossImages.keySet())
        {
            if (allBossImages.get(bufferedImage).equals(enemyName))
            {
                return bufferedImage;
            }
        }

        for (BufferedImage bufferedImage : allNormalImages.keySet())
        {
            if (allNormalImages.get(bufferedImage).equals(enemyName))
            {
                return bufferedImage;
            }
        }

        return null;
    }

    public String getAbilityTarget(String abilityName)
    {
        HashMap<String, String> abilityNamesAndTargets = userInterface.getAbilityTargets();
        for (String ability : abilityNamesAndTargets.keySet())
        {
            if (ability.equals(abilityName))
            {
                return abilityNamesAndTargets.get(ability);
            }
        }

        return null;
    }

    public void useMultiTargetedAbility(BufferedImage selectedHero, int id, String abilityName) throws NoMoreUpgradeException, NotStrongEnoughException, NotEnoughXPException, NotEnoughMoneyException, NotEnoughRequiredAbilitiesException, AbilityCooldownException, AbilityNotAcquieredException, FullInventoryException
    {
        String order = findHeroName(selectedHero) + " cast " + abilityName;
        gameScenario.startFighting(id - 1, order);
    }

    public ArrayList<String> getTurnLog()
    {
        return gameScenario.getThisTurnLog();
    }

    public void regularAttack(BufferedImage selectedHero, BufferedImage selectedEnemy, Integer enemyId, int BattleId) throws NoMoreUpgradeException, NotStrongEnoughException, NotEnoughXPException, NotEnoughMoneyException, NotEnoughRequiredAbilitiesException, AbilityCooldownException, AbilityNotAcquieredException, FullInventoryException
    {
        String heroName = findHeroName(selectedHero);
        String enemyFullName = findEnemyFullName(selectedEnemy, enemyId);
//        System.out.println(heroName + " attack " + enemyFullName);
        gameScenario.startFighting(BattleId, heroName + " attack " + enemyFullName);
    }

    private String findEnemyFullName(BufferedImage selectedEnemy, Integer id)
    {
        HashMap<BufferedImage, String> allBossImages = userInterface.getBossEnemyImages();
        HashMap<BufferedImage, String> allNormalImages = userInterface.getAllNormalEnemyImages();
        HashMap<BufferedImage, String> allEnemyImaged = new HashMap<>();
        String fullName = "";
        allEnemyImaged.putAll(allBossImages);
        allEnemyImaged.putAll(allNormalImages);

        for (String enemyName : allEnemyImaged.values())
        {
            if (selectedEnemy.equals(findEnemyImage(enemyName)))
            {
                fullName = enemyName;
                if (id != 0)
                {
                    fullName += " " + id;
                }

                fullName = fullName.replaceAll(" ", "-");
                break;
            }
        }

        return fullName;
    }

    public void useSingleTargetedAbility(BufferedImage selectedHero, BufferedImage selectedEnemy, Integer enemyId, int BattleId, String selectedAbility) throws NoMoreUpgradeException, NotStrongEnoughException, NotEnoughXPException, NotEnoughMoneyException, NotEnoughRequiredAbilitiesException, AbilityCooldownException, AbilityNotAcquieredException, FullInventoryException
    {
        String heroName = findHeroName(selectedHero);
        String enemyFullName = findEnemyFullName(selectedEnemy, enemyId);

        gameScenario.startFighting(BattleId, heroName + " cast " + selectedAbility + " on " + enemyFullName);
    }

    public void useSingleTargetedAbility(BufferedImage selectedHero, BufferedImage selectedTarget, int BattleId, String selectedAbility) throws NoMoreUpgradeException, NotStrongEnoughException, NotEnoughXPException, NotEnoughMoneyException, NotEnoughRequiredAbilitiesException, AbilityCooldownException, AbilityNotAcquieredException, FullInventoryException
    {
        String heroName = findHeroName(selectedHero);
        String targetName = findHeroName(selectedTarget);

        gameScenario.startFighting(BattleId, heroName + " cast " + selectedAbility + " on " + targetName);
    }

    public void enemyTurn(int gameTurn)
    {
        gameScenario.enemyTurn(gameTurn);
    }

    public void enemyIntroduction(int gameTurn)
    {
        gameScenario.showingEnemyData(gameTurn);
    }

    public void endBattle(int gameTurn)
    {
        gameScenario.battleEnded(gameTurn);
    }

    public boolean isAlive(BufferedImage enemyImage, int gameTurn, Integer enemyId)
    {
        ArrayList<Enemy> enemies = gameScenario.getEnemyGroups().get(gameTurn).getEnemies();
        for (Enemy enemy : enemies)
        {
            String enemyName = enemy.getName();
            if (enemy instanceof NormalEnemy)
            {
                enemyName = ((NormalEnemy) enemy).getVersion() + " " + enemyName;
            }

            if (enemyImage.equals(findEnemyImage(enemyName)) && enemy.getID() == enemyId)
            {
                return true;
            }
        }

        return false;
    }

    public JFrame getFrame()
    {
        return frame;
    }

    public void setFrame(JFrame frame)
    {
        this.frame = frame;
    }

    public GameScenario getGameScenario()
    {
        return gameScenario;
    }

    public void setGameScenario(GameScenario gameScenario)
    {
        this.gameScenario = gameScenario;
    }

    public UserInterface getUserInterface()
    {
        return userInterface;
    }

    public void setUserInterface(UserInterface userInterface)
    {
        this.userInterface = userInterface;
    }

    public ArrayList<String> findItemNamesOfAShop(int shopId)
    {
        return userInterface.getAllShopItemNames().get(shopId);
    }

    public ArrayList<String> findItemNamesOfAHero(BufferedImage selectedHero)
    {
        ArrayList<String> itemNames = new ArrayList<>();
        String heroName = findHeroName(selectedHero);
        ArrayList<Hero> heros = gameScenario.getHeros();
        ArrayList<Item> heroItems = new ArrayList<>();

        for (Hero hero : heros)
        {
            if(hero.getName().equals(heroName))
            {
                heroItems = hero.getInventory().getItems();
            }
        }

        for (Item item : heroItems)
        {
            itemNames.add(item.getName());
        }

        return itemNames;
    }

    public String getWelcomeMessage(int shopId)
    {
        return userInterface.getShopWelcomeMessages().get(shopId);
    }

    public void sellItem(String selectedItem, BufferedImage selectedHero, int shopId) throws NoMoreUpgradeException, NotStrongEnoughException, NotEnoughXPException, NotEnoughMoneyException, NotEnoughRequiredAbilitiesException, AbilityCooldownException, AbilityNotAcquieredException, FullInventoryException
    {
        String heroName = findHeroName(selectedHero);
        gameScenario.shopping("sell " + selectedItem + " of " + heroName, shopId);
    }

    public void buyItem(BufferedImage selectedHero, String selectedItem, int shopId) throws NoMoreUpgradeException, NotStrongEnoughException, NotEnoughXPException, NotEnoughMoneyException, NotEnoughRequiredAbilitiesException, AbilityCooldownException, AbilityNotAcquieredException, FullInventoryException
    {
        String heroName = findHeroName(selectedHero);
        String[] itemNameParts = selectedItem.split(" ");
        selectedItem = "";
        for (String namePart : itemNameParts)
        {
            selectedItem += namePart;
        }
        gameScenario.shopping("buy " + selectedItem + " for " + heroName, shopId);
    }

    public ArrayList<String> findNonAcquiredAbilities(BufferedImage selectedHero)
    {
        ArrayList<String> result = new ArrayList<>();
        String heroName = findHeroName(selectedHero);
        ArrayList<Hero> heros = gameScenario.getHeros();

        for (Hero hero : heros)
        {
            if(hero.getName().equals(heroName))
            {
                ArrayList<Ability> abilities = hero.getAbilities();
                for (Ability ability : abilities)
                {
                    if (ability.getCurrentUpgradeNum() == 0)
                    {
                        result.add(ability.getName());
                    }
                }
            }
        }

        return result;
    }

    public ArrayList<String> findAcquiredAbilities(BufferedImage selectedHero)
    {
        ArrayList<String> result = new ArrayList<>();
        String heroName = findHeroName(selectedHero);
        ArrayList<Hero> heros = gameScenario.getHeros();

        for (Hero hero : heros)
        {
            if(hero.getName().equals(heroName))
            {
                ArrayList<Ability> abilities = hero.getAbilities();
                for (Ability ability : abilities)
                {
                    if (ability.getCurrentUpgradeNum() != 0)
                    {
                        result.add(ability.getName());
                    }
                }
            }
        }

        return result;
    }

    public Pair<BufferedImage, String> getStartingMessages()
    {
        ArrayList<Pair<String, String>> enteringMessage = userInterface.getHeroEnteringMessage();
        HashMap<String, BufferedImage> heros = userInterface.getHeroFaces();
        int randomNum = Luck.getRandom(0, 3);
        Pair<String, String> chosenPair = enteringMessage.get(randomNum);
        for (String heroName : heros.keySet())
        {
            if(heroName.equals(chosenPair.getKey()))
            {
                return new Pair<>(heros.get(heroName), chosenPair.getValue());
            }
        }

        return null;
    }

    public void upgradeAbility(String abilityName, BufferedImage selectedHero) throws NoMoreUpgradeException, NotStrongEnoughException, NotEnoughXPException, NotEnoughMoneyException, NotEnoughRequiredAbilitiesException, AbilityCooldownException, AbilityNotAcquieredException, FullInventoryException
    {
        String heroName = findHeroName(selectedHero);
        String order = "acquire " + abilityName + " for " + heroName;
        gameScenario.startUpgrading(order);
    }

    public ArrayList<String> findAbilityRequirement(BufferedImage selectedHero, String abilityName)
    {
        ArrayList<String> result = new ArrayList<>();
        String heroName = findHeroName(selectedHero);
        Hero wantedHero;
        ArrayList<Hero> heros = gameScenario.getHeros();
        for (Hero hero : heros)
        {
            if(hero.getName().equals(heroName))
            {
                wantedHero = hero;
                result = wantedHero.findRequirements(abilityName);
                break;
            }
        }

        return result;
    }

    public ArrayList<String> findInBattleItemNamesOfAHero(BufferedImage selectedHero)
    {
        String heroName = findHeroName(selectedHero);
        ArrayList<Hero> heros = gameScenario.getHeros();
        Hero wantedHero;
        ArrayList<Item> items = new ArrayList<>();
        ArrayList<String> itemNames = new ArrayList<>();
        for (Hero hero : heros)
        {
            if(hero.getName().equals(heroName))
            {
                wantedHero = hero;
                items = wantedHero.getInventory().getItems();
                break;
            }
        }

        for (Item item : items)
        {
            if(item instanceof NonInstantEffectItem)
            {
                itemNames.add(item.getName());
            }
        }

        return itemNames;
    }

    public String getItemTarget(String itemName)
    {
        HashMap<String, String> itemsAndTargets = userInterface.getItemTargets();
        String target = itemsAndTargets.get(itemName);
        return target;
    }

    public void useMultiTargetedItem(BufferedImage selectedHero, int battleId, String itemName) throws NoMoreUpgradeException, NotStrongEnoughException, NotEnoughXPException, NotEnoughMoneyException, NotEnoughRequiredAbilitiesException, AbilityCooldownException, AbilityNotAcquieredException, FullInventoryException
    {
        String order = findHeroName(selectedHero) + " use " + itemName;
        gameScenario.startFighting(battleId - 1, order);
    }

    public void useSingleTargetedItem(BufferedImage selectedHero, BufferedImage selectedTarget, int battleId, String selectedItem) throws NoMoreUpgradeException, NotStrongEnoughException, NotEnoughXPException, NotEnoughMoneyException, NotEnoughRequiredAbilitiesException, AbilityCooldownException, AbilityNotAcquieredException, FullInventoryException
    {
        String heroName = findHeroName(selectedHero);
        String targetName = findHeroName(selectedTarget);

        gameScenario.startFighting(battleId, heroName + " use " + selectedItem+ " on " + targetName);
    }

    public void useSingleTargetedItem(BufferedImage selectedHero, BufferedImage selectedEnemy, Integer enemyId, int battleId, String selectedItem) throws NoMoreUpgradeException, NotStrongEnoughException, NotEnoughXPException, NotEnoughMoneyException, NotEnoughRequiredAbilitiesException, AbilityCooldownException, AbilityNotAcquieredException, FullInventoryException
    {
        String heroName = findHeroName(selectedHero);
        String enemyFullName = findEnemyFullName(selectedEnemy, enemyId);

        gameScenario.startFighting(battleId, heroName + " use " + selectedItem+ " on " + enemyFullName);
    }

    public void addTeamItemKey(Integer keyId)
    {
        Item item = new NonEffectiveItem("key" + keyId, null, null);
        Hero.addTeamItem(item);
    }

    public boolean heroContainsKey(int doorId)
    {
        if (doorId == 0)
        {
            return true;
        }

        ArrayList<Item> teamItems = Hero.getTeamItems();
        for (Item item : teamItems)
        {
            if (item.getName().equals("key" + doorId))
            {
                return true;
            }
        }

        return false;
    }

    public void earlyBossEffects(int battleId)
    {
        gameScenario.earlyEffects(battleId);
    }


}
