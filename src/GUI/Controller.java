package GUI;

import Auxiliary.Luck;
import Controller.GameScenario;
import Controller.NetworkScenario;
import Controller.UltimateImage;
import Controller.UserInterface;
import Exceptions.*;
import Model.*;
import javafx.util.Pair;

import javax.swing.*;
import java.awt.*;
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
    private GameScenario gameScenario;
    private NetworkScenario networkScenario;
    private UserInterface userInterface;
    private MainCustomPanel mainCustomPanel;
    private NetworkPanel networkPanel;
    private GamePanel gamePanel;

    public Controller(JFrame jFrame)
    {
        Scanner in = new Scanner(System.in);
        UserInterface userInterface = new UserInterface();
//        gameScenario = new GameScenario(userInterface, in);
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

    public String findHeroName(UltimateImage ultimateImage)
    {
        HashMap<UltimateImage, String> allHeroImages = userInterface.getHerosAndTheirImages();

        for (UltimateImage thisUltimateImage : allHeroImages.keySet())
        {
            if (thisUltimateImage.equals(ultimateImage))
            {
                return allHeroImages.get(ultimateImage);
            }
        }

        System.out.println("LOL?!");
        return null;
    }

    public ArrayList<String> findAbilityNamesOfAHero(UltimateImage heroImage) // must clean
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

    public ArrayList<Pair<UltimateImage, Integer>> getEnemyGroupImage(int id)
    {
        ArrayList<Enemy> wantedEnemies = gameScenario.getEnemyGroups().get(id).getEnemies();
        ArrayList<Pair<UltimateImage, Integer>> result = new ArrayList<>();

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

    public UltimateImage findEnemyImage(String enemyName)
    {
        HashMap<UltimateImage, String> allBossImages = userInterface.getBossEnemyImages();
        HashMap<UltimateImage, String> allNormalImages = userInterface.getAllNormalEnemyImages();

        for (UltimateImage ultimateImage : allBossImages.keySet())
        {
            if (allBossImages.get(ultimateImage).equals(enemyName))
            {
                return ultimateImage;
            }
        }

        for (UltimateImage ultimateImage : allNormalImages.keySet())
        {
            if (allNormalImages.get(ultimateImage).equals(enemyName))
            {
                return ultimateImage;
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

    public void useMultiTargetedAbility(UltimateImage selectedHero, int id, String abilityName) throws NoMoreUpgradeException, NotStrongEnoughException, NotEnoughXPException, NotEnoughMoneyException, NotEnoughRequiredAbilitiesException, AbilityCooldownException, AbilityNotAcquieredException, FullInventoryException
    {
        String order = findHeroName(selectedHero) + " cast " + abilityName;
        gameScenario.startFighting(id - 1, order);
    }

    public ArrayList<String> getTurnLog()
    {
        return gameScenario.getThisTurnLog();
    }

    public void regularAttack(UltimateImage selectedHero, UltimateImage selectedEnemy, Integer enemyId, int BattleId) throws NoMoreUpgradeException, NotStrongEnoughException, NotEnoughXPException, NotEnoughMoneyException, NotEnoughRequiredAbilitiesException, AbilityCooldownException, AbilityNotAcquieredException, FullInventoryException
    {
        String heroName = findHeroName(selectedHero);
        String enemyFullName = findEnemyFullName(selectedEnemy, enemyId);
//        System.out.println(heroName + " attack " + enemyFullName);
        gameScenario.startFighting(BattleId, heroName + " attack " + enemyFullName);
    }

    private String findEnemyFullName(UltimateImage selectedEnemy, Integer id)
    {
        HashMap<UltimateImage, String> allBossImages = userInterface.getBossEnemyImages();
        HashMap<UltimateImage, String> allNormalImages = userInterface.getAllNormalEnemyImages();
        HashMap<UltimateImage, String> allEnemyImaged = new HashMap<>();
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

    public void useSingleTargetedAbility(UltimateImage selectedHero, UltimateImage selectedEnemy, Integer enemyId, int BattleId, String selectedAbility) throws NoMoreUpgradeException, NotStrongEnoughException, NotEnoughXPException, NotEnoughMoneyException, NotEnoughRequiredAbilitiesException, AbilityCooldownException, AbilityNotAcquieredException, FullInventoryException
    {
        String heroName = findHeroName(selectedHero);
        String enemyFullName = findEnemyFullName(selectedEnemy, enemyId);

        gameScenario.startFighting(BattleId, heroName + " cast " + selectedAbility + " on " + enemyFullName);
    }

    public void useSingleTargetedAbility(UltimateImage selectedHero, UltimateImage selectedTarget, int BattleId, String selectedAbility) throws NoMoreUpgradeException, NotStrongEnoughException, NotEnoughXPException, NotEnoughMoneyException, NotEnoughRequiredAbilitiesException, AbilityCooldownException, AbilityNotAcquieredException, FullInventoryException
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

    public boolean isAlive(UltimateImage enemyImage, int gameTurn, Integer enemyId)
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

    public ArrayList<String> findItemNamesOfAHero(UltimateImage selectedHero)
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

    public void sellItem(String selectedItem, UltimateImage selectedHero, int shopId) throws NoMoreUpgradeException, NotStrongEnoughException, NotEnoughXPException, NotEnoughMoneyException, NotEnoughRequiredAbilitiesException, AbilityCooldownException, AbilityNotAcquieredException, FullInventoryException
    {
        String heroName = findHeroName(selectedHero);
        gameScenario.shopping("sell " + selectedItem + " of " + heroName, shopId);
    }

    public void buyItem(UltimateImage selectedHero, String selectedItem, int shopId) throws NoMoreUpgradeException, NotStrongEnoughException, NotEnoughXPException, NotEnoughMoneyException, NotEnoughRequiredAbilitiesException, AbilityCooldownException, AbilityNotAcquieredException, FullInventoryException
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

    public ArrayList<String> findNonAcquiredAbilities(UltimateImage selectedHero)
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

    public ArrayList<String> findAcquiredAbilities(UltimateImage selectedHero)
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

    public Pair<UltimateImage, String> getStartingMessages()
    {
        ArrayList<Pair<String, String>> enteringMessage = userInterface.getHeroEnteringMessage();
        HashMap<String, UltimateImage> heros = userInterface.getHeroFaces();
        int randomNum = Luck.getRandom(0, enteringMessage.size() - 1);
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

    public void upgradeAbility(String abilityName, UltimateImage selectedHero) throws NoMoreUpgradeException, NotStrongEnoughException, NotEnoughXPException, NotEnoughMoneyException, NotEnoughRequiredAbilitiesException, AbilityCooldownException, AbilityNotAcquieredException, FullInventoryException
    {
        String heroName = findHeroName(selectedHero);
        String order = "acquire " + abilityName + " for " + heroName;
        gameScenario.startUpgrading(order);
    }

    public ArrayList<String> findAbilityRequirement(UltimateImage selectedHero, String abilityName)
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

    public ArrayList<String> findInBattleItemNamesOfAHero(UltimateImage selectedHero)
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

    public void useMultiTargetedItem(UltimateImage selectedHero, int battleId, String itemName) throws NoMoreUpgradeException, NotStrongEnoughException, NotEnoughXPException, NotEnoughMoneyException, NotEnoughRequiredAbilitiesException, AbilityCooldownException, AbilityNotAcquieredException, FullInventoryException
    {
        String order = findHeroName(selectedHero) + " use " + itemName;
        gameScenario.startFighting(battleId - 1, order);
    }

    public void useSingleTargetedItem(UltimateImage selectedHero, UltimateImage selectedTarget, int battleId, String selectedItem) throws NoMoreUpgradeException, NotStrongEnoughException, NotEnoughXPException, NotEnoughMoneyException, NotEnoughRequiredAbilitiesException, AbilityCooldownException, AbilityNotAcquieredException, FullInventoryException
    {
        String heroName = findHeroName(selectedHero);
        String targetName = findHeroName(selectedTarget);

        gameScenario.startFighting(battleId, heroName + " use " + selectedItem+ " on " + targetName);
    }

    public void useSingleTargetedItem(UltimateImage selectedHero, UltimateImage selectedEnemy, Integer enemyId, int battleId, String selectedItem) throws NoMoreUpgradeException, NotStrongEnoughException, NotEnoughXPException, NotEnoughMoneyException, NotEnoughRequiredAbilitiesException, AbilityCooldownException, AbilityNotAcquieredException, FullInventoryException
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

    public void startCustomMaking()
    {
        mainCustomPanel = MainCustomPanel.getInstance(this);
        setPanel(mainCustomPanel);
//        singlePlayerGame.showStartingStory();
    }

    public void startMultiPlayer()
    {
        networkPanel = NetworkPanel.getInstance(this);
        setPanel(networkPanel);
    }

    public NetworkScenario getNetworkScenario()
    {
        return networkScenario;
    }

    public void setNetworkScenario(NetworkScenario networkScenario)
    {
        this.networkScenario = networkScenario;
    }

    public void networkRegularAttack(UltimateImage selectedHero, UltimateImage selectedTarget) throws NoMoreUpgradeException, NotStrongEnoughException, NotEnoughXPException, NotEnoughMoneyException, NotEnoughRequiredAbilitiesException, AbilityCooldownException, AbilityNotAcquieredException, FullInventoryException
    {
        String yourHero = findHeroName(selectedHero);
        String opponentHero = findHeroName(selectedTarget);

        String order = yourHero + " attack " + opponentHero;
        gameScenario.startNetworkFighting(order);
    }

    public String findItemDescription(String itemName)
    {
        String correctName = itemName.replace(" ", "");
        return userInterface.getItemDescription().get(correctName);
    }

    public String findAbilityDescription(String abilityName)
    {
        String correctName = abilityName.replace(" ", "");
        return userInterface.getAbilityDescription().get(correctName);
    }

    public String findHeroInfo(UltimateImage heroImage)
    {
        String result = "";
        String heroName = findHeroName(heroImage);
        ArrayList<Hero> heros = gameScenario.getHeros();
        for (Hero hero : heros)
        {
            if (hero.getName().equals(heroName))
            {
                result = hero.getHeroTrate();
            }
        }

        return result;
    }
    public void startLoadGame()
    {
        gamePanel = new GamePanel(this, userInterface.getMap(), userInterface.getMapWidth(), userInterface.getMapHeight());
        this.setPanel(gamePanel);
    }

    public GamePanel getGamePanel()
    {
        return gamePanel;
    }

    public void setGamePanel(GamePanel gamePanel)
    {
        this.gamePanel = gamePanel;
    }

    public String findNetworkHeroInfo(UltimateImage ultimateImage)
    {
        String result = "";
        String heroName = findHeroName(ultimateImage);
        ArrayList<Hero> heros = networkScenario.getHeros();
        for (Hero hero : heros)
        {
            if (hero.getName().equals(heroName))
            {
                result = hero.getHeroTrate();
            }
        }

        return result;
    }
}
