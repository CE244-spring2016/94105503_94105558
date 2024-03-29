package Controller;

import Auxiliary.Formula;
import Model.EnemyVersion;
import javafx.util.Pair;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Set;

/*
    We need init here!

	We could divide this class to 5-6 other classes
*/

public class UserInterface implements Serializable
{
    private boolean customed;
    private int BattleId = 0;
    private int initialXP = 0;
    private int initialMoney = 0;
    private int immortalityPotionNum;
    private int startingX;
    private int startingY;
    private int mapWidth;
    private int mapHeight;
    private String mapAddress;
    private UltimateImage gameOverBackground;
    private ArrayList<ArrayList<JLabel>> map = new ArrayList<>();
    private ArrayList<String> heroClassNames = new ArrayList<>(); // new it in the constructor
    private HashMap<String, HashMap<String, Integer>> heroClassDatas = new HashMap<>(); // new it in the constructor
    private HashMap<String, ArrayList<String>> heroClassAbilities = new HashMap<>();
    private HashMap<String, Integer> heroClassInventorySizes = new HashMap<>(); // A new variable!!
    private HashMap<String, String> ItemDescription = new HashMap<>();
    private ArrayList<String> heroNames = new ArrayList<>();
    private ArrayList<String> heroAttributes = new ArrayList<>();                         // must make this fully in the constructor
    private HashMap<String, String> herosAndTheirClasses = new HashMap<>();
    private HashMap<String, ArrayList<String>> herosAndTheirAbilities = new HashMap<>();
    private HashMap<UltimateImage, String> herosAndTheirImages = new HashMap<>();
    private HashMap<String, HashMap<String, ArrayList<HashMap<String, Integer>>>> allHeroRequiredAbilities = new HashMap<>();
    private ArrayList<String> normalEnemyNames = new ArrayList<>();
    private HashMap<String, ArrayList<EnemyVersion>> normalEnemyDatas = new HashMap<>();
    private HashMap<UltimateImage, String> allNormalEnemyImages = new HashMap<>();
    private HashMap<String, String> abilityDescription = new HashMap<>();
    private ArrayList<String> enemyAttributes = new ArrayList<>();                        // must make this fully in the constructor
    private ArrayList<String> possibleNormalEnemyTargets = new ArrayList<>(); // A new variable
    private HashMap<String, ArrayList<String>> enemyVersionNames = new HashMap<>();
    private ArrayList<String> bossEnemyNames = new ArrayList<>();
    private HashMap<String, HashMap<String, Integer>> bossEnemyDatas = new HashMap<>();
    private ArrayList<String> bossEnemyAttributes = new ArrayList<>();
    private ArrayList<String> changableBossEnemyAttributes = new ArrayList<>();
    private HashMap<String, Integer> bossEnemyAngerPoints = new HashMap<>();
    private HashMap<String, HashMap<String, Integer>> bossEnemyAngerAdditions = new HashMap<>();
    private HashMap<String, HashMap<String, String>> bossEnemyEarlyEffects = new HashMap<>();
    private ArrayList<String> possibleBossEnemyTargets = new ArrayList<>();
    private HashMap<String, String> bossEnemyTargets = new HashMap<>();
    private HashMap<UltimateImage, String> bossEnemyImages = new HashMap<>();
    private ArrayList<String> itemNames = new ArrayList<>();
    private HashMap<String, HashMap<String, Integer>> itemDatas = new HashMap<>();
    private HashMap<String, String> itemTargets = new HashMap<>();
    private ArrayList<String> itemAttributes = new ArrayList<>();                         // must make this fully in the constructor
    private ArrayList<String> possibleItemTargets = new ArrayList<>();                    // must make this fully in the constructor
    private ArrayList<String> inflationedItems = new ArrayList<>();
    private ArrayList<String> instantEffectItems = new ArrayList<>();
    private HashMap<String, Integer> nonInstantEffectItemsUseLimit = new HashMap<>();
    private ArrayList<String> abilityNames = new ArrayList<>();
    private HashMap<String, HashMap<String, ArrayList<Formula>>> allAbiliyFormulas = new HashMap<>(); // Even non targeted enemy share and cooldown is handled here
    private HashMap<String, String> abilityTargets = new HashMap<>();
    private ArrayList<String> possibleAbilityTargets = new ArrayList<>();                 // must make this fully in the constructor
    private HashMap<String, ArrayList<Integer>> allAbilityUpgradeXPs = new HashMap<>();
    private ArrayList<String> abilityAttributes = new ArrayList<>();                      // must make this fully in the constructor
    private HashMap<String, ArrayList<HashMap<String, Integer>>> allRequiredAbilities = new HashMap<>();
    private HashMap<String, ArrayList<Integer>> abilityLuckPercents = new HashMap<>();
    private HashMap<String, String> primaryVariableNames = new HashMap<>();
    private HashMap<String, ArrayList<Integer>> secondaryTargetShares = new HashMap<>();
    private HashMap<String, ArrayList<Integer>> allAbilityCooldowns = new HashMap<>();
    private ArrayList<String> instantEffectConditionAbilities = new ArrayList<>();
    private ArrayList<ArrayList<String>> allShopItemNames = new ArrayList<>();
    private ArrayList<HashMap<String, Integer>> allShopItemMoneyCosts = new ArrayList<>();
    private ArrayList<Integer> allShopInflationValues = new ArrayList<>();
    private ArrayList<String> gameStory = new ArrayList<>();
    private ArrayList<ArrayList<String>> storyEnemyGroups = new ArrayList<>();
    private ArrayList<Integer> enemyGroupXPs = new ArrayList<>();
    private ArrayList<Integer> enemyGroupMoneys = new ArrayList<>();
    private HashMap<String, String> allAbilitySuccessMessages = new HashMap<>();
    private HashMap<String, String> allEnemySuccessMessages = new HashMap<>();
    private HashMap<String, String> allItemSuccessMessages = new HashMap<>();
    private ArrayList<String> tileSources = new ArrayList<>();
    private ArrayList<Integer> battleGidNums = new ArrayList<>();
    private ArrayList<UltimateImage> battleBackgroundSources = new ArrayList<>();
    private ArrayList<Integer> shopGidNums = new ArrayList<>();
    private ArrayList<UltimateImage> shopBackgroundSources = new ArrayList<>();
    private ArrayList<UltimateImage> shopKeeperSources = new ArrayList<>();
    private ArrayList<String> shopWelcomeMessages = new ArrayList<>();
    private ArrayList<Integer> abilityUpgradeGidNums = new ArrayList<>();
    private ArrayList<UltimateImage> abilityUpgradeBackgroundSources = new ArrayList<>();
    private ArrayList<Pair<String, String>> heroEnteringMessage = new ArrayList<>();
    private HashMap<String, UltimateImage> heroFaces = new HashMap<>();
    private ArrayList<Integer> storyGidNums = new ArrayList<>();
    private ArrayList<UltimateImage> storyBackgroundSources = new ArrayList<>();
    private HashMap<Integer, Integer> doorsAndTheirDirections = new HashMap<>();
    private HashMap<Integer, Integer> doorsAndTheirKeys = new HashMap<>();
    private ArrayList<Integer> keyGidNums = new ArrayList<>();
    private ArrayList<String> attributesWithMax = new ArrayList<>();
    private ArrayList<String> attributeWithTemp = new ArrayList<>();
    public UserInterface()
    {
        // create stuff

//        checkCustom(customed);
    }

    public ArrayList<ArrayList<JLabel>> getMap()
    {
        return map;
    }

    public void setMap(ArrayList<ArrayList<JLabel>> map)
    {
        this.map = map;
    }

    public void checkCustom(boolean customed)
    {
        this.customed = customed;
//        System.out.println("How do you want to start?(Enter the right number)");
//        System.out.println("1- Start normal game");
//        System.out.println("2- Start custom game(you can create your own game here)");
        System.out.println("Press any key to start the story...");
        System.out.println("___custom game is under construction___");
//        String input = in.nextLine();
        // Check wrong input
//        if (input.equals("2"))
//        {
//            this.customed = true;
//            startCreating(in);
//        } else
//        {
        if (customed)
        {
            return;
        }
        init();
//        }
    }


    private void init()
    {
        //Enemy success Messages
        allEnemySuccessMessages.put("tank", "damaged all of your heros");
        allEnemySuccessMessages.put("thug", "");
        allEnemySuccessMessages.put("angel", "");
        //Item success Messages
        allItemSuccessMessages.put("healthpotion", "just healed");
        allItemSuccessMessages.put("magicpotion", "just ofered");
        //Success Messages
        allAbilitySuccessMessages.put("overpoweredattack", " just did an overpowered attack on");
        allAbilitySuccessMessages.put("sacrifice", " just sacrificed himself to damage all his enemies with");
        allAbilitySuccessMessages.put("elixir", " just healed");
        allAbilitySuccessMessages.put("caretaker", " just gave");
        allAbilitySuccessMessages.put("boost", " just boosted");
        allAbilitySuccessMessages.put("manabeam", " just helped");
        //storyEnemyGroups VAHIDCHECK
        ArrayList<String> storyEnemyGroups1 = new ArrayList<>();
        ArrayList<String> storyEnemyGroups2 = new ArrayList<>();
        ArrayList<String> storyEnemyGroups3 = new ArrayList<>();
        ArrayList<String> storyEnemyGroups4 = new ArrayList<>();
        ArrayList<String> storyEnemyGroups5 = new ArrayList<>();
        ArrayList<String> storyEnemyGroups6 = new ArrayList<>();
        storyEnemyGroups1.add("weak thug");
        storyEnemyGroups1.add("weak thug");
        storyEnemyGroups1.add("weak thug");
        storyEnemyGroups1.add("weak angel");

        storyEnemyGroups2.add("able thug");
        storyEnemyGroups2.add("able thug");
        storyEnemyGroups2.add("weak angel");
        storyEnemyGroups2.add("weak tank");

        storyEnemyGroups3.add("able thug");
        storyEnemyGroups3.add("mighty thug");
        storyEnemyGroups3.add("able angel");
        storyEnemyGroups3.add("weak tank");

        storyEnemyGroups4.add("able thug");
        storyEnemyGroups4.add("able thug");
        storyEnemyGroups4.add("able thug");
        storyEnemyGroups4.add("able tank");
        storyEnemyGroups4.add("able tank");

        storyEnemyGroups5.add("mighty thug");
        storyEnemyGroups5.add("mighty thug");
        storyEnemyGroups5.add("able angel");
        storyEnemyGroups5.add("able tank");
        storyEnemyGroups5.add("able tank");

        storyEnemyGroups6.add("thecollector");
        ArrayList<String> thugVersionNames = new ArrayList<>();
        thugVersionNames.add("able");
        thugVersionNames.add("weak");
        thugVersionNames.add("mighty");
        enemyVersionNames.put("thug", thugVersionNames);
        ArrayList<String> angelVersionNames = new ArrayList<>();
        angelVersionNames.add("weak");
        angelVersionNames.add("able");
        enemyVersionNames.put("angel", angelVersionNames);
        ArrayList<String> TankVersionNames = new ArrayList<>();
        TankVersionNames.add("weak");
        TankVersionNames.add("able");
        enemyVersionNames.put("tank", TankVersionNames);

        storyEnemyGroups.add(storyEnemyGroups1);
        storyEnemyGroups.add(storyEnemyGroups2);
        storyEnemyGroups.add(storyEnemyGroups3);
        storyEnemyGroups.add(storyEnemyGroups4);
        storyEnemyGroups.add(storyEnemyGroups5);
        storyEnemyGroups.add(storyEnemyGroups6);
        //VAHIDCHECK

        //enemyGroupXP VAHIDCHECK
        enemyGroupXPs.add(20);
        enemyGroupXPs.add(25);
        enemyGroupXPs.add(30);
        enemyGroupXPs.add(20);
        enemyGroupXPs.add(35);
        enemyGroupXPs.add(0);
        //enemyGroupMoneys
        enemyGroupMoneys.add(50);
        enemyGroupMoneys.add(60);
        enemyGroupMoneys.add(70);
        enemyGroupMoneys.add(70);
        enemyGroupMoneys.add(80);
        enemyGroupMoneys.add(0);
        //VAHIDCHECK
        //gameStory VAHIDCHECK
        gameStory.add("You’ve entered the castle, it takes a while for your eyes to get used to " +
                "the darkness but the horrifying halo of your enemies is vaguely visible. " +
                "Angel’s unsettling presence and the growling of thugs tell you that your first battle has BEGUN!");
        gameStory.add("As you wander into the hall you realize the surrounding doors can lead your" +
                " destiny to something far worse than you expected. You know what’s anticipating you" +
                " behind the only open door but there’s no other choice.");
        gameStory.add("The door behind you is shut with a thunderous sound and you progress into " +
                "the next hall holding the first key that you’ve found, hoping to seek the second one.");
//        gameStory.add("Lol Optional"); // CHECK PLEASE
        gameStory.add("Running with the second key in your hand, you unlock the door back to the first hall " +
                "and use the first key to burst into your most terrifying nightmares.");
        gameStory.add("You feel hopeless and exhausted as you stalk to the final door. What’s behind that door makes" +
                " your hearts pound and your spines shake with fear, but you came here to do one thing and backing down" +
                " is not an option.");
        gameStory.add("The collector falls down on his knees, he’s strained and desperate but still tries to drag himself" +
                " toward Epoch. He knows his era has come to an end. The ancient time machine calls you to end the disorder " +
                "and bring unity under its glorious wings, now it’s your turn to be the MASTERS OF TIME!");

        //VAHIDCHECK

        //shop
        HashMap<String, Integer> shopItemMoneyCosts = new HashMap<>();
        HashMap<String, Integer> shopItemMoneyCosts2 = new HashMap<>();
        HashMap<String, Integer> shopItemMoneyCosts3 = new HashMap<>();

        shopItemMoneyCosts.put("guide", 4);
        shopItemMoneyCosts.put("toughen", 4);
        shopItemMoneyCosts.put("defy", 4);
        shopItemMoneyCosts.put("sword", 25);
        shopItemMoneyCosts.put("energyboots", 20);
        shopItemMoneyCosts.put("armor", 25);
        shopItemMoneyCosts.put("magicstick", 28);
        shopItemMoneyCosts.put("healthpotion", 15);
        shopItemMoneyCosts.put("magicpotion", 15);

        HashMap<String, String> itemdescription = new HashMap<>();
        itemdescription.put("guide", "+20 maximum magic");
        itemdescription.put("toughen", "+20 maximum health");
        itemdescription.put("defy", "+8 attack power");
        itemdescription.put("sword", "+80 attack power");
        itemdescription.put("energyboots", "+1 energy point");
        itemdescription.put("armor", "+200 maximum health");
        itemdescription.put("magicstick", "+150 maximum magic");
        itemdescription.put("healthpotion", "+100 health points for the user or one of his/her allies");
        itemdescription.put("magicpotion", "+50 magic points for the user or one of his/her allies");
        setItemDescription(itemdescription);

        allShopItemMoneyCosts.add(shopItemMoneyCosts);

        shopItemMoneyCosts2.put("guide", 4);
        shopItemMoneyCosts2.put("toughen", 4);
        shopItemMoneyCosts2.put("defy", 4);
        shopItemMoneyCosts2.put("sword", 25);
        shopItemMoneyCosts2.put("energyboots", 20);
        shopItemMoneyCosts2.put("armor", 25);
        shopItemMoneyCosts2.put("magicstick", 28);
        shopItemMoneyCosts2.put("healthpotion", 15);
        shopItemMoneyCosts2.put("magicpotion", 15);

        allShopItemMoneyCosts.add(shopItemMoneyCosts2);

        shopItemMoneyCosts3.put("guide", 4);
        shopItemMoneyCosts3.put("toughen", 4);
        shopItemMoneyCosts3.put("defy", 4);
        shopItemMoneyCosts3.put("sword", 25);
        shopItemMoneyCosts3.put("energyboots", 20);
        shopItemMoneyCosts3.put("armor", 25);
        shopItemMoneyCosts3.put("magicstick", 28);
        shopItemMoneyCosts3.put("healthpotion", 15);
        shopItemMoneyCosts3.put("magicpotion", 15);

        allShopItemMoneyCosts.add(shopItemMoneyCosts3);
        //
        allShopInflationValues.add(2);
        allShopInflationValues.add(2);
        allShopInflationValues.add(2);
        //
        immortalityPotionNum = 3;
        initialMoney = 40;
        initialXP = 15;
        BattleId = 6;
        //
        heroClassNames.add("fighter");
        heroClassNames.add("supporter");
        HashMap<String, Integer> fighterData = new HashMap<>();
        fighterData.put("attack", 120);
        fighterData.put("temp attack", 0);
        fighterData.put("max health", 200);
        fighterData.put("current health", 200);
        fighterData.put("health refill", 10);
        fighterData.put("max magic", 120);
        fighterData.put("current magic", 120);
        fighterData.put("magic refill", 5);
        fighterData.put("max EP", 6);
        fighterData.put("current EP", 6);
        heroClassDatas.put("fighter", fighterData);
        ArrayList<String> fighterAbilities = new ArrayList<>();
        fighterAbilities.add("fighttraining");
        fighterAbilities.add("workout");
        heroClassAbilities.put("fighter", fighterAbilities);
        heroClassInventorySizes.put("fighter", 2);

        /*************/

        HashMap<String, Integer> supporterData = new HashMap<>();
        supporterData.put("max health", 220);
        supporterData.put("current health", 220);
        supporterData.put("health refill", 5);
        supporterData.put("max magic", 200);
        supporterData.put("current magic", 200);
        supporterData.put("magic refill", 10);
        supporterData.put("attack", 80);
        supporterData.put("temp attack", 0);
        supporterData.put("max EP", 5);
        supporterData.put("current EP", 5);
        heroClassDatas.put("supporter", supporterData);
        ArrayList<String> supporterAbilities = new ArrayList<>();
        supporterAbilities.add("quickasabunny");
        supporterAbilities.add("magiclessons");
        heroClassAbilities.put("supporter", supporterAbilities);
        heroClassInventorySizes.put("supporter", 3);

        //khode hero ha
        heroNames.add("chrome");
        heroNames.add("eley");
        heroNames.add("bolti");
        heroNames.add("meryl");
        herosAndTheirClasses.put("eley", "fighter");
        herosAndTheirClasses.put("chrome", "fighter");
        herosAndTheirClasses.put("meryl", "supporter");
        herosAndTheirClasses.put("bolti", "supporter");
        ArrayList<String> eleyAbilities = new ArrayList<>();
        ArrayList<String> chromeAbilities = new ArrayList<>();
        ArrayList<String> merylAbilities = new ArrayList<>();
        ArrayList<String> boltiAbilities = new ArrayList<>();
        eleyAbilities.add("overpoweredattack");
        eleyAbilities.add("swirlingattack");
        chromeAbilities.add("sacrifice");
        chromeAbilities.add("criticalstrike");
        merylAbilities.add("elixir");
        merylAbilities.add("caretaker");
        boltiAbilities.add("boost");
        boltiAbilities.add("manabeam");
        herosAndTheirAbilities.put("eley", eleyAbilities);
        herosAndTheirAbilities.put("chrome", chromeAbilities);
        herosAndTheirAbilities.put("meryl", merylAbilities);
        herosAndTheirAbilities.put("bolti", boltiAbilities);

        abilityDescription.put("fighttraining", "Permanently increases attack power<br>" +
                "Upgrade1: +30 attack power for 2 xp points<br>" +
                "Upgrade2: +30 attack power for 3 xp points<br>" +
                "Upgrade3: +30 attack power for 4 xp points");

        abilityDescription.put("workout", "Permanently increases maximum health<br>" +
                "Upgrade 1: +50 maximum health for 2 xp points<br>" +
                "Upgrade 2: +50 maximum health for 3 xp points<br>" +
                "Upgrade 3: +50 maximum health for 4 xp points");
        abilityDescription.put("quickasabunny", "Permanently increases energy points<br>" +
                "Upgrade1: +1 energy point for 2 xp points<br>" +
                "Upgrade2: +1 energy point for 3 xp points<br>" +
                "Upgrade3: +1 energy point for 4 xp points");
        abilityDescription.put("magiclessons", "Permanently increases maximum magic<br>" +
                "Upgrade 1: +50 maximum magic for 2 xp points<br>" +
                "Upgrade 2: +50 maximum magic for 3 xp points<br>" +
                "Upgrade 3: +50 maximum magic for 4 xp points");
        abilityDescription.put("overpoweredattack", "Attacks an enemy with N times attack power for 2 energy points and 50 magic points<br>" +
                "Upgrade 1: N=1.2 for 2 xp points<br>" +
                "Upgrade 2: N=1.4 for 4 xp points<br>" +
                "Upgrade 3: N=1.6 for 6 xp points");
        abilityDescription.put("swirlingattack", "While attacking, non-targeted enemies also take P percent of its damage<br>" +
                "Upgrade 1: P=10 for 2 xp points<br>" +
                "Upgrade 2: P=20 for 3 xp points<br>" +
                "Upgrade 3: P=30 for 4 xp points");
        abilityDescription.put("sacrifice", "Damages all the enemies with 3H power at the cost of H of his own health, needs 3 energy points, 60 magic points and has a 1 turn cooldown<br>" +
                "Upgrade 1: H=40 for 2 xp points<br>" +
                "Upgrade 2: H=50 for 3 xp points<br>" +
                "Upgrade 3: H=60 for 4 xp points");
        abilityDescription.put("criticalstrike", "Has a permanent P percent chance of doing an attack with double power<br>" +
                "Upgrade 1: P=20 for 2 xp points<br>" +
                "Upgrade 2: P=30 for 3 xp points<br>" +
                "Upgrade 3: P=40 for 4 xp points");
        abilityDescription.put("elixir", "Refills H points of her own health or an ally’s, for 2 energy points and 60 magic points<br>" +
                "Upgrade 1: H=100 for 2 xp points and takes 1 turn to cool down<br>" +
                "Upgrade 2: H=150 for 3 xp points, takes 1 turn to cool down<br>" +
                "Upgrade 3: H=150 for 5 xp points, cools down instantly");
        abilityDescription.put("caretaker", "Gives 1 energy point to an ally for 30 magic points (this ep does not last until the end of the battle and is only usable during the current turn)<br>" +
                "Upgrade 1: takes 2 energy points and has a 1 turn cooldown for 2 xp points<br>" +
                "Upgrade 2: takes 2 energy points and cools down instantly for 3 xp points<br>" +
                "Upgrade 3 takes 1 energy point and cools down instantly for 5 xp points");
        abilityDescription.put("boost", "Gives X bonus attack power to himself or an ally, which lasts till the end of the battle, for 2 energy points and 50 magic points (this bonus attack power can stack up)<br>" +
                "Upgrade 1: A=20 for 2 xp points and takes 1 turn to cool down<br>" +
                "Upgrade 2: A=30 for 3 xp points and takes 1 turn to cool down<br>" +
                "Upgrade 3: A=30 for 5 xp points and cools down instantly");
        abilityDescription.put("manabeam", "ives M magic points to himself or an ally for 1 energy point and 50 magic points<br>" +
                "Upgrade 1: M=50 for 2 xp points and takes 1 turn to cool down<br>" +
                "Upgrade 2: M=80 for 3 xp points and takes 1 turn to cool down<br>" +
                "Upgrade 3: M=80 for 4 xp points and cools down instantly");

        /*************/
        normalEnemyNames.add("thug");
        normalEnemyNames.add("angel");
        normalEnemyNames.add("tank");

        HashMap<String, Integer> weakThugData = new HashMap<>();
        weakThugData.put("move attack", 50);
        weakThugData.put("max health", 200);
        weakThugData.put("current health", 200);

        HashMap<String, Integer> ableThugData = new HashMap<>();
        ableThugData.put("move attack", 90);
        ableThugData.put("max health", 300);
        ableThugData.put("current health", 300);

        HashMap<String, Integer> mightyThugData = new HashMap<>();
        mightyThugData.put("move attack", 150);
        mightyThugData.put("current health", 400);
        mightyThugData.put("max health", 400);

        //ENEMYVERSION SHOULD HAVE ANOTHER PARAMETER IN ITS CONSTRUCTOR EXP: WEAKTHUGH---> weak , thug
        ArrayList<EnemyVersion> thugEnemyVersion = new ArrayList<>();
        thugEnemyVersion.add(new EnemyVersion("weak", "thug", weakThugData, "a hero"));
        thugEnemyVersion.add(new EnemyVersion("able", "thug", ableThugData, "a hero"));
        thugEnemyVersion.add(new EnemyVersion("mighty", "thug", mightyThugData, "a hero"));
        normalEnemyDatas.put("thug", thugEnemyVersion);

        /*************/

        HashMap<String, Integer> weakAngelData = new HashMap<>();
        weakAngelData.put("move heal", 100);
        weakAngelData.put("max health", 150);
        weakAngelData.put("current health", 150);

        HashMap<String, Integer> ableAngelData = new HashMap<>();
        ableAngelData.put("move heal", 150);
        ableAngelData.put("max health", 250);
        ableAngelData.put("current health", 250);

        ArrayList<EnemyVersion> angelEnemyVersion = new ArrayList<>();
        angelEnemyVersion.add(new EnemyVersion("weak", "angel", weakAngelData, "an ally"));
        angelEnemyVersion.add(new EnemyVersion("able", "angel", ableAngelData, "an ally"));
        normalEnemyDatas.put("angel", angelEnemyVersion);

        /*************/

        HashMap<String, Integer> weakTankData = new HashMap<>();
        weakTankData.put("move attack", 30);
        weakTankData.put("max health", 400);
        weakTankData.put("current health", 400);

        HashMap<String, Integer> ableTankData = new HashMap<>();
        ableTankData.put("move attack", 90);
        ableTankData.put("max health", 500);
        ableTankData.put("current health", 500);

        ArrayList<EnemyVersion> tankEnemyVersion = new ArrayList<>();
        tankEnemyVersion.add(new EnemyVersion("weak", "tank", weakTankData, "all heros"));
        tankEnemyVersion.add(new EnemyVersion("able", "tank", ableTankData, "all heros"));
        normalEnemyDatas.put("tank", tankEnemyVersion);

        /*************/

        bossEnemyNames.add("thecollector");

        HashMap<String, Integer> collectorData = new HashMap<>();
        collectorData.put("max health", 1000);
        collectorData.put("current health", 1000);
        collectorData.put("move attack", 150);
        bossEnemyDatas.put("thecollector", collectorData);

        HashMap<String, Integer> angerAdditions = new HashMap<>();
        angerAdditions.put("move attack", 100);
        bossEnemyAngerAdditions.put("thecollector", angerAdditions);

        HashMap<String, String> earlyEffects = new HashMap<>();
        earlyEffects.put("current EP", "2 to 4");
        bossEnemyEarlyEffects.put("thecollector", earlyEffects);

        bossEnemyAngerPoints.put("thecollector", 400);
        bossEnemyTargets.put("thecollector", "2 hero");

        /*************/
        //Items
        //nonInstantEffectItemUseLimt
        nonInstantEffectItemsUseLimit.put("healthpotion", 3);
        nonInstantEffectItemsUseLimit.put("magicpotion", 3);

        //instantEffectItems
        instantEffectItems.add("toughen");
        instantEffectItems.add("guide");
        instantEffectItems.add("defy");
        instantEffectItems.add("sword");
        instantEffectItems.add("energyboots");
        instantEffectItems.add("armor");
        instantEffectItems.add("magicstick");
        //Item names
        itemNames.add("toughen");
        itemNames.add("guide");
        itemNames.add("defy");
        itemNames.add("sword");
        itemNames.add("energyboots");
        itemNames.add("armor");
        itemNames.add("magicstick");
        itemNames.add("healthpotion");
        itemNames.add("magicpotion");
        //item targets
        itemTargets.put("toughen", "himself");
        itemTargets.put("guide", "himself");
        itemTargets.put("defy", "himself");
        itemTargets.put("sword", "himself");
        itemTargets.put("energyboots", "himself");
        itemTargets.put("armor", "himself");
        itemTargets.put("magicstick", "himself");
        itemTargets.put("healthpotion", "an ally");
        itemTargets.put("magicpotion", "an ally");
        //inflation Item
        inflationedItems.add("toughen");
        inflationedItems.add("guide");
        inflationedItems.add("defy");

        //ItemData VAHIDCHECK
        HashMap<String, Integer> toughenData = new HashMap<>();
        toughenData.put("max health", 20);
        itemDatas.put("toughen", toughenData);

        HashMap<String, Integer> guideData = new HashMap<>();
        guideData.put("max magic", 20);
        itemDatas.put("guide", guideData);

        HashMap<String, Integer> defyData = new HashMap<>();
        defyData.put("attack", 8);
        itemDatas.put("defy", defyData);

        HashMap<String, Integer> swordData = new HashMap<>();
        swordData.put("attack", 80);
        itemDatas.put("sword", swordData);

        HashMap<String, Integer> energyBootsData = new HashMap<>();
        energyBootsData.put("current EP", 1);
        itemDatas.put("energyboots", energyBootsData);

        HashMap<String, Integer> armorData = new HashMap<>();
        armorData.put("max health", 200);
        itemDatas.put("armor", armorData);

        HashMap<String, Integer> magicStickData = new HashMap<>();
        magicStickData.put("max magic", 150);
        itemDatas.put("magicstick", magicStickData);

        HashMap<String, Integer> healthPotionData = new HashMap<>();
        healthPotionData.put("current health", 100);
        itemDatas.put("healthpotion", healthPotionData);

        HashMap<String, Integer> magicPotionData = new HashMap<>();
        magicPotionData.put("current magic", 50);
        itemDatas.put("magicpotion", magicPotionData);
        //VAHIDCHECK
        ArrayList<String> shopItemNames = new ArrayList<>();
        ArrayList<String> shopItemNames2 = new ArrayList<>();
        ArrayList<String> shopItemNames3 = new ArrayList<>();

        shopItemNames.add("toughen");
        shopItemNames.add("guide");
        shopItemNames.add("defy");
        shopItemNames.add("sword");
        shopItemNames.add("energy boots");
        shopItemNames.add("armor");
        shopItemNames.add("magic stick");
        shopItemNames.add("health potion");
        shopItemNames.add("magic potion");

        allShopItemNames.add(shopItemNames);

        shopItemNames2.add("toughen");
        shopItemNames2.add("guide");
        shopItemNames2.add("defy");
        shopItemNames2.add("sword");
        shopItemNames2.add("energy boots");
        shopItemNames2.add("armor");
        shopItemNames2.add("magic stick");
        shopItemNames2.add("health potion");
        shopItemNames2.add("magic potion");

        allShopItemNames.add(shopItemNames2);

        shopItemNames3.add("toughen");
        shopItemNames3.add("guide");
        shopItemNames3.add("defy");
        shopItemNames3.add("sword");
        shopItemNames3.add("energy boots");
        shopItemNames3.add("armor");
        shopItemNames3.add("magic stick");
        shopItemNames3.add("health potion");
        shopItemNames3.add("magic potion");

        allShopItemNames.add(shopItemNames3);
        /*************/
        //ABILITIES
        //instant
        instantEffectConditionAbilities.add("fighttraining");
        instantEffectConditionAbilities.add("workout");
        instantEffectConditionAbilities.add("quickasabunny");
        instantEffectConditionAbilities.add("magiclessons");
        instantEffectConditionAbilities.add("swirlingattack");
        instantEffectConditionAbilities.add("criticalstrike");
        //names
        abilityNames.add("fighttraining");
        abilityNames.add("workout");
        abilityNames.add("quickasabunny");
        abilityNames.add("magiclessons");
        abilityNames.add("overpoweredattack");
        abilityNames.add("swirlingattack");
        abilityNames.add("sacrifice");
        abilityNames.add("criticalstrike");
        abilityNames.add("elixir");
        abilityNames.add("caretaker");
        abilityNames.add("boost"); // LOL
        abilityNames.add("manabeam");
        //targets
        abilityTargets.put("fighttraining", "himself");
        abilityTargets.put("workout", "himself");
        abilityTargets.put("quickasabunny", "himself");
        abilityTargets.put("magiclessons", "himself");
        abilityTargets.put("overpoweredattack", "an enemy");//target is enemy
        abilityTargets.put("swirlingattack", "himself");//target is a enemy but it has tasire janebi
        abilityTargets.put("sacrifice", "all enemies");
        abilityTargets.put("criticalstrike", "himself");
        abilityTargets.put("elixir", "an ally");
        abilityTargets.put("caretaker", "an ally");
        abilityTargets.put("boost", "an ally");
        abilityTargets.put("manabeam", "an ally");
        //upgradeXP
        ArrayList<Integer> fightTrainingXP = new ArrayList<>();
        fightTrainingXP.add(2);
        fightTrainingXP.add(3);
        fightTrainingXP.add(4);
        allAbilityUpgradeXPs.put("fighttraining", fightTrainingXP);

        ArrayList<Integer> workOutXP = new ArrayList<>();
        workOutXP.add(2);
        workOutXP.add(3);
        workOutXP.add(4);
        allAbilityUpgradeXPs.put("workout", workOutXP);

        ArrayList<Integer> quickAsBunnyXP = new ArrayList<>();
        quickAsBunnyXP.add(2);
        quickAsBunnyXP.add(3);
        quickAsBunnyXP.add(4);
        allAbilityUpgradeXPs.put("quickasabunny", quickAsBunnyXP);

        ArrayList<Integer> magicLessonsXP = new ArrayList<>();
        magicLessonsXP.add(2);
        magicLessonsXP.add(3);
        magicLessonsXP.add(4);
        allAbilityUpgradeXPs.put("magiclessons", magicLessonsXP);

        ArrayList<Integer> overPoweredAttackXP = new ArrayList<>();
        overPoweredAttackXP.add(2);
        overPoweredAttackXP.add(4);
        overPoweredAttackXP.add(6);
        allAbilityUpgradeXPs.put("overpoweredattack", overPoweredAttackXP);

        ArrayList<Integer> swirlingAttackXP = new ArrayList<>();
        swirlingAttackXP.add(2);
        swirlingAttackXP.add(3);
        swirlingAttackXP.add(4);
        allAbilityUpgradeXPs.put("swirlingattack", swirlingAttackXP);

        ArrayList<Integer> sacrificeXP = new ArrayList<>();
        sacrificeXP.add(2);
        sacrificeXP.add(3);
        sacrificeXP.add(4);
        allAbilityUpgradeXPs.put("sacrifice", sacrificeXP);

        ArrayList<Integer> criticalStrikeXP = new ArrayList<>();
        criticalStrikeXP.add(2);
        criticalStrikeXP.add(3);
        criticalStrikeXP.add(4);
        allAbilityUpgradeXPs.put("criticalstrike", criticalStrikeXP);

        ArrayList<Integer> elixirXP = new ArrayList<>();
        elixirXP.add(2);
        elixirXP.add(3);
        elixirXP.add(5);
        allAbilityUpgradeXPs.put("elixir", elixirXP);

        ArrayList<Integer> caretakerXP = new ArrayList<>();
        caretakerXP.add(2);
        caretakerXP.add(3);
        caretakerXP.add(5);
        allAbilityUpgradeXPs.put("caretaker", caretakerXP);

        ArrayList<Integer> boostXP = new ArrayList<>();
        boostXP.add(2);
        boostXP.add(3);
        boostXP.add(5);
        allAbilityUpgradeXPs.put("boost", boostXP);

        ArrayList<Integer> manaBeamXP = new ArrayList<>();
        manaBeamXP.add(2);
        manaBeamXP.add(3);
        manaBeamXP.add(4);
        allAbilityUpgradeXPs.put("manabeam", manaBeamXP);

        //abilityRequired
        // LOL

        HashMap<String, ArrayList<HashMap<String, Integer>>> eleyRequirements = new HashMap<>();
        HashMap<String, ArrayList<HashMap<String, Integer>>> chromeRequirements = new HashMap<>();
        HashMap<String, ArrayList<HashMap<String, Integer>>> merylRequirements = new HashMap<>();
        HashMap<String, ArrayList<HashMap<String, Integer>>> boltiRequirements = new HashMap<>();
        ArrayList<HashMap<String, Integer>> fightTrainingUpgradeRequirements = new ArrayList<>();
        fightTrainingUpgradeRequirements.add(new HashMap<>());
        fightTrainingUpgradeRequirements.add(new HashMap<>());
        fightTrainingUpgradeRequirements.add(new HashMap<>());
        eleyRequirements.put("fighttraining", fightTrainingUpgradeRequirements);
        chromeRequirements.put("fighttraining", fightTrainingUpgradeRequirements);


        ArrayList<HashMap<String, Integer>> workOutUpgradeRequirements = new ArrayList<>();
        workOutUpgradeRequirements.add(new HashMap<>());
        workOutUpgradeRequirements.add(new HashMap<>());
        workOutUpgradeRequirements.add(new HashMap<>());
        eleyRequirements.put("workout", fightTrainingUpgradeRequirements);
        chromeRequirements.put("workout", fightTrainingUpgradeRequirements);

        ArrayList<HashMap<String, Integer>> quickAsABunnyUpgradeRequirements = new ArrayList<>();
        quickAsABunnyUpgradeRequirements.add(new HashMap<>());
        quickAsABunnyUpgradeRequirements.add(new HashMap<>());
        quickAsABunnyUpgradeRequirements.add(new HashMap<>());
        boltiRequirements.put("quickasabunny", quickAsABunnyUpgradeRequirements);
        merylRequirements.put("quickasabunny", quickAsABunnyUpgradeRequirements);

        ArrayList<HashMap<String, Integer>> magicLessonsUpgradeRequirements = new ArrayList<>();
        magicLessonsUpgradeRequirements.add(new HashMap<>());
        magicLessonsUpgradeRequirements.add(new HashMap<>());
        magicLessonsUpgradeRequirements.add(new HashMap<>());
        boltiRequirements.put("magiclessons", quickAsABunnyUpgradeRequirements);
        merylRequirements.put("magiclessons", quickAsABunnyUpgradeRequirements);

        ArrayList<HashMap<String, Integer>> boostUpgradeRequirements = new ArrayList<>();
        boostUpgradeRequirements.add(new HashMap<>());
        boostUpgradeRequirements.add(new HashMap<>());
        boostUpgradeRequirements.add(new HashMap<>());
        boltiRequirements.put("boost", boostUpgradeRequirements);

        HashMap<String, Integer> overPoweredAttackUpgradeRequirement1 = new HashMap<>();
        overPoweredAttackUpgradeRequirement1.put("fighttraining", 1);
        HashMap<String, Integer> overPoweredAttackUpgradeRequirement2 = new HashMap<>();
        overPoweredAttackUpgradeRequirement2.put("fighttraining", 2);
        HashMap<String, Integer> overPoweredAttackUpgradeRequirement3 = new HashMap<>();
        overPoweredAttackUpgradeRequirement3.put("fighttraining", 3);
        ArrayList<HashMap<String, Integer>> overPoweredAttackUpgradeRequirements = new ArrayList<>();
        overPoweredAttackUpgradeRequirements.add(overPoweredAttackUpgradeRequirement1);
        overPoweredAttackUpgradeRequirements.add(overPoweredAttackUpgradeRequirement2);
        overPoweredAttackUpgradeRequirements.add(overPoweredAttackUpgradeRequirement3);
        eleyRequirements.put("overpoweredattack", overPoweredAttackUpgradeRequirements);

        HashMap<String, Integer> swirlingAttackUpgradeRequirement1 = new HashMap<>();
        swirlingAttackUpgradeRequirement1.put("workout", 1);
        ArrayList<HashMap<String, Integer>> swirlingAttackUpgradeRequirements = new ArrayList<>();
        swirlingAttackUpgradeRequirements.add(swirlingAttackUpgradeRequirement1);
        swirlingAttackUpgradeRequirements.add(new HashMap<>());
        swirlingAttackUpgradeRequirements.add(new HashMap<>());
        eleyRequirements.put("swirlingattack", swirlingAttackUpgradeRequirements);

        HashMap<String, Integer> sacrificeUpgradeRequirement1 = new HashMap<>();
        sacrificeUpgradeRequirement1.put("workout", 1);
        HashMap<String, Integer> sacrificeUpgradeRequirement2 = new HashMap<>();
        sacrificeUpgradeRequirement2.put("workout", 2);
        HashMap<String, Integer> sacrificeUpgradeRequirement3 = new HashMap<>();
        sacrificeUpgradeRequirement3.put("workout", 3);
        ArrayList<HashMap<String, Integer>> sacrificeUpgradeRequirements = new ArrayList<>();
        sacrificeUpgradeRequirements.add(sacrificeUpgradeRequirement1);
        sacrificeUpgradeRequirements.add(sacrificeUpgradeRequirement2);
        sacrificeUpgradeRequirements.add(sacrificeUpgradeRequirement3);
        chromeRequirements.put("sacrifice", sacrificeUpgradeRequirements);

        HashMap<String, Integer> criticalStrikeUpgradeRequirement1 = new HashMap<>();
        criticalStrikeUpgradeRequirement1.put("fighttraining", 1);
        ArrayList<HashMap<String, Integer>> criticalStrikeUpgradeRequirements = new ArrayList<>();
        criticalStrikeUpgradeRequirements.add(criticalStrikeUpgradeRequirement1);
        criticalStrikeUpgradeRequirements.add(new HashMap<>()); // LOL
        criticalStrikeUpgradeRequirements.add(new HashMap<>()); // LOL
        chromeRequirements.put("criticalstrike", criticalStrikeUpgradeRequirements);

        HashMap<String, Integer> elixirUpgradeRequirement1 = new HashMap<>();
        HashMap<String, Integer> elixirUpgradeRequirement2 = new HashMap<>();
        elixirUpgradeRequirement2.put("magiclessons", 1);
        HashMap<String, Integer> elixirUpgradeRequirement3 = new HashMap<>();
        elixirUpgradeRequirement3.put("magiclessons", 2);
        ArrayList<HashMap<String, Integer>> elixirUpgradeRequirements = new ArrayList<>();
        elixirUpgradeRequirements.add(elixirUpgradeRequirement1);
        elixirUpgradeRequirements.add(elixirUpgradeRequirement2);
        elixirUpgradeRequirements.add(elixirUpgradeRequirement3);
        merylRequirements.put("elixir", elixirUpgradeRequirements);

        HashMap<String, Integer> caretakerUpgradeRequirement1 = new HashMap<>();
        caretakerUpgradeRequirement1.put("quickasabunny", 1);
        HashMap<String, Integer> caretakerUpgradeRequirement2 = new HashMap<>();
        caretakerUpgradeRequirement2.put("quickasabunny", 2);
        HashMap<String, Integer> caretakerUpgradeRequirement3 = new HashMap<>();
        caretakerUpgradeRequirement3.put("quickasabunny", 3);
        ArrayList<HashMap<String, Integer>> caretakerUpgradeRequirements = new ArrayList<>();
        caretakerUpgradeRequirements.add(caretakerUpgradeRequirement1);
        caretakerUpgradeRequirements.add(caretakerUpgradeRequirement2);
        caretakerUpgradeRequirements.add(caretakerUpgradeRequirement3);
        merylRequirements.put("caretaker", caretakerUpgradeRequirements);

        HashMap<String, Integer> manaBeamUpgradeRequirement1 = new HashMap<>();
        manaBeamUpgradeRequirement1.put("magiclessons", 1);
        HashMap<String, Integer> manaBeamUpgradeRequirement2 = new HashMap<>();
        manaBeamUpgradeRequirement2.put("magiclessons", 2);
        HashMap<String, Integer> manaBeamUpgradeRequirement3 = new HashMap<>();
        manaBeamUpgradeRequirement3.put("magiclessons", 3);
        ArrayList<HashMap<String, Integer>> manaBeamUpgradeRequirements = new ArrayList<>();
        manaBeamUpgradeRequirements.add(manaBeamUpgradeRequirement1);
        manaBeamUpgradeRequirements.add(manaBeamUpgradeRequirement2);
        manaBeamUpgradeRequirements.add(manaBeamUpgradeRequirement3);
        boltiRequirements.put("manabeam", manaBeamUpgradeRequirements);

        allHeroRequiredAbilities.put("eley", eleyRequirements);
        allHeroRequiredAbilities.put("chrome", chromeRequirements);
        allHeroRequiredAbilities.put("meryl", merylRequirements);
        allHeroRequiredAbilities.put("bolti", boltiRequirements);
        //I think requirements are finished but Im not sure

        //secondaryTarget

        //ArrayList<Integer> swirlingAttackNonTargetShareUpgrades = new ArrayList<>();
        //swirlingAttackNonTargetShareUpgrades.add(10);
        //swirlingAttackNonTargetShareUpgrades.add(20);
        //swirlingAttackNonTargetShareUpgrades.add(30);
        //secondaryTargetShares.put("swirlingAttack", swirlingAttackNonTargetShareUpgrades);

        //formula

        //private HashMap<String, HashMap<String, ArrayList<Formula>>> allAbiliyFormulas

        //fightTraining
        HashMap<String, ArrayList<Formula>> fightTrainingFormula = new HashMap<>();
        Formula fightTrainingEffectFormulaUpgrade1 = new Formula("30", null);
        Formula fightTrainingEffectFormulaUpgrade2 = new Formula("30", null);
        Formula fightTrainingEffectFormulaUpgrade3 = new Formula("30", null);
        ArrayList<Formula> fightTrainingEffectFormulaUpgrades = new ArrayList<>();
        fightTrainingEffectFormulaUpgrades.add(fightTrainingEffectFormulaUpgrade1);
        fightTrainingEffectFormulaUpgrades.add(fightTrainingEffectFormulaUpgrade2);
        fightTrainingEffectFormulaUpgrades.add(fightTrainingEffectFormulaUpgrade3);
        fightTrainingFormula.put("attack", fightTrainingEffectFormulaUpgrades);
        allAbiliyFormulas.put("fighttraining", fightTrainingFormula);

        //workOut
        HashMap<String, ArrayList<Formula>> workOutFormula = new HashMap<>();
        Formula workOutEffectFormulaUpgrade1 = new Formula("50", null);
        Formula workOutEffectFormulaUpgrade2 = new Formula("50", null);
        Formula workOutEffectFormulaUpgrade3 = new Formula("50", null);
        ArrayList<Formula> workOutEffectFormulaUpgrades = new ArrayList<>();
        workOutEffectFormulaUpgrades.add(workOutEffectFormulaUpgrade1);
        workOutEffectFormulaUpgrades.add(workOutEffectFormulaUpgrade2);
        workOutEffectFormulaUpgrades.add(workOutEffectFormulaUpgrade3);
        workOutFormula.put("max health", workOutEffectFormulaUpgrades);
        allAbiliyFormulas.put("workout", workOutFormula);

        //quickAsBunny

        HashMap<String, ArrayList<Formula>> quickAsBunnyFormula = new HashMap<>();
        Formula quickAsBunnyEffectFormulaUpgrade1 = new Formula("1", null);
        Formula quickAsBunnyEffectFormulaUpgrade2 = new Formula("1", null);
        Formula quickAsBunnyEffectFormulaUpgrade3 = new Formula("1", null);
        ArrayList<Formula> quickAsBunnyEffectFormulaUpgrades = new ArrayList<>();
        quickAsBunnyEffectFormulaUpgrades.add(quickAsBunnyEffectFormulaUpgrade1);
        quickAsBunnyEffectFormulaUpgrades.add(quickAsBunnyEffectFormulaUpgrade2);
        quickAsBunnyEffectFormulaUpgrades.add(quickAsBunnyEffectFormulaUpgrade3);
        quickAsBunnyFormula.put("max EP", quickAsBunnyEffectFormulaUpgrades); // LOL
        allAbiliyFormulas.put("quickasabunny", quickAsBunnyFormula);


        //magicLessons

        HashMap<String, ArrayList<Formula>> magicLessonsFormula = new HashMap<>();
        Formula magicLessonsEffectFormulaUpgrade1 = new Formula("50", null);
        Formula magicLessonsEffectFormulaUpgrade2 = new Formula("50", null);
        Formula magicLessonsEffectFormulaUpgrade3 = new Formula("50", null);
        ArrayList<Formula> magicLessonsEffectFormulaUpgrades = new ArrayList<>();
        magicLessonsEffectFormulaUpgrades.add(magicLessonsEffectFormulaUpgrade1);
        magicLessonsEffectFormulaUpgrades.add(magicLessonsEffectFormulaUpgrade2);
        magicLessonsEffectFormulaUpgrades.add(magicLessonsEffectFormulaUpgrade3);
        magicLessonsFormula.put("max magic", magicLessonsEffectFormulaUpgrades);
        allAbiliyFormulas.put("magiclessons", magicLessonsFormula);

        //overPoweredAttack
        HashMap<String, ArrayList<Formula>> overPoweredAttackFormula = new HashMap<>();
        Formula overPoweredAttackEffectFormulaUpgrade1 = new Formula("(0-1.2) * (attack + temp attack)", null);
        Formula overPoweredAttackEffectFormulaUpgrade2 = new Formula("(0-1.4) * (attack + temp attack)", null);
        Formula overPoweredAttackEffectFormulaUpgrade3 = new Formula("(0-1.6) * (attack + temp attack)", null);
        ArrayList<Formula> overPoweredAttackEffectFormulaUpgrades = new ArrayList<>();
        overPoweredAttackEffectFormulaUpgrades.add(overPoweredAttackEffectFormulaUpgrade1);
        overPoweredAttackEffectFormulaUpgrades.add(overPoweredAttackEffectFormulaUpgrade2);
        overPoweredAttackEffectFormulaUpgrades.add(overPoweredAttackEffectFormulaUpgrade3);
        ArrayList<Formula> overPoweredAttackCostFormula1 = new ArrayList<>();
        ArrayList<Formula> overPoweredAttackCostFormula2 = new ArrayList<>();
        Formula overPoweredAttackCostEP1 = new Formula("0-2", null);
        Formula overPoweredAttackCostEP2 = new Formula("0-2", null);
        Formula overPoweredAttackCostEP3 = new Formula("0-2", null);
        Formula overPoweredAttackCostMagic1 = new Formula("0-50", null);
        Formula overPoweredAttackCostMagic2 = new Formula("0-50", null);
        Formula overPoweredAttackCostMagic3 = new Formula("0-50", null);
        overPoweredAttackCostFormula1.add(overPoweredAttackCostEP1);
        overPoweredAttackCostFormula1.add(overPoweredAttackCostEP2);
        overPoweredAttackCostFormula1.add(overPoweredAttackCostEP3);
        overPoweredAttackCostFormula2.add(overPoweredAttackCostMagic1);
        overPoweredAttackCostFormula2.add(overPoweredAttackCostMagic2);
        overPoweredAttackCostFormula2.add(overPoweredAttackCostMagic3);
        overPoweredAttackFormula.put("current health", overPoweredAttackEffectFormulaUpgrades);
        overPoweredAttackFormula.put("cost EP", overPoweredAttackCostFormula1);
        overPoweredAttackFormula.put("cost magic", overPoweredAttackCostFormula2);
        allAbiliyFormulas.put("overpoweredattack", overPoweredAttackFormula);


        //swirlingAttack

        HashMap<String, ArrayList<Formula>> swirlingAttackFormula = new HashMap<>();
        Formula swirlingAttackEffectFormulaUpgrade1 = new Formula("10", null);
        Formula swirlingAttackEffectFormulaUpgrade2 = new Formula("20", null);
        Formula swirlingAttackEffectFormulaUpgrade3 = new Formula("30", null);
        ArrayList<Formula> swirlingAttackEffectFormulaUpgrades = new ArrayList<>();
        swirlingAttackEffectFormulaUpgrades.add(swirlingAttackEffectFormulaUpgrade1);
        swirlingAttackEffectFormulaUpgrades.add(swirlingAttackEffectFormulaUpgrade2);
        swirlingAttackEffectFormulaUpgrades.add(swirlingAttackEffectFormulaUpgrade3);
        swirlingAttackFormula.put("nontargetedshare", swirlingAttackEffectFormulaUpgrades);
        allAbiliyFormulas.put("swirlingattack", swirlingAttackFormula);

        //sacrifice

        HashMap<String, ArrayList<Formula>> sacrificeFormula = new HashMap<>();
        Formula sacrificeEffectFormulaUpgrade1 = new Formula("0 - 120", null);
        Formula sacrificeEffectFormulaUpgrade2 = new Formula("0 - 150", null);
        Formula sacrificeEffectFormulaUpgrade3 = new Formula("0 - 180", null);
        ArrayList<Formula> sacrificeEffectFormulaUpgrades = new ArrayList<>();
        sacrificeEffectFormulaUpgrades.add(sacrificeEffectFormulaUpgrade1);
        sacrificeEffectFormulaUpgrades.add(sacrificeEffectFormulaUpgrade2);
        sacrificeEffectFormulaUpgrades.add(sacrificeEffectFormulaUpgrade3);
        ArrayList<Formula> sacrificeCostFormula1 = new ArrayList<>();
        ArrayList<Formula> sacrificeCostFormula2 = new ArrayList<>();
        ArrayList<Formula> sacrificeCostFormula3 = new ArrayList<>();
        Formula sacrificeCostEP1 = new Formula("0- 3", null);
        Formula sacrificeCostEP2 = new Formula("0 - 3", null);
        Formula sacrificeCostEP3 = new Formula("0 - 3", null);
        Formula sacrificeCostMagic1 = new Formula("0 - 60", null);
        Formula sacrificeCostMagic2 = new Formula("0 - 60", null);
        Formula sacrificeCostMagic3 = new Formula("0- 60", null);
        Formula sacrificeCostHealth1 = new Formula("0 - 40", null);
        Formula sacrificeCostHealth2 = new Formula("0- 50", null);
        Formula sacrificeCostHealth3 = new Formula("0- 60", null);
        sacrificeCostFormula1.add(sacrificeCostEP1);
        sacrificeCostFormula1.add(sacrificeCostEP2);
        sacrificeCostFormula1.add(sacrificeCostEP3);
        sacrificeCostFormula2.add(sacrificeCostMagic1);
        sacrificeCostFormula2.add(sacrificeCostMagic2);
        sacrificeCostFormula2.add(sacrificeCostMagic3);
        sacrificeCostFormula3.add(sacrificeCostHealth1);
        sacrificeCostFormula3.add(sacrificeCostHealth2);
        sacrificeCostFormula3.add(sacrificeCostHealth3);
        sacrificeFormula.put("current health", sacrificeEffectFormulaUpgrades);
        sacrificeFormula.put("cost EP", sacrificeCostFormula1);
        sacrificeFormula.put("cost magic", sacrificeCostFormula2);
        sacrificeFormula.put("cost health", sacrificeCostFormula3);
        allAbiliyFormulas.put("sacrifice", sacrificeFormula);
        //coolDown
        ArrayList<Integer> sacrificeCooldownUpgrade = new ArrayList<>();
        sacrificeCooldownUpgrade.add(1);
        sacrificeCooldownUpgrade.add(1);
        sacrificeCooldownUpgrade.add(1);
        allAbilityCooldowns.put("sacrifice", sacrificeCooldownUpgrade);

        //criticalStrike

        HashMap<String, ArrayList<Formula>> criticalStrikeFormula = new HashMap<>();
        Formula criticalStrikeEffectFormulaUpgrade1 = new Formula("20", null);
        Formula criticalStrikeEffectFormulaUpgrade2 = new Formula("30", null);
        Formula criticalStrikeEffectFormulaUpgrade3 = new Formula("40", null);
        ArrayList<Formula> criticalStrikeEffectFormulaUpgrades = new ArrayList<>();
        criticalStrikeEffectFormulaUpgrades.add(criticalStrikeEffectFormulaUpgrade1);
        criticalStrikeEffectFormulaUpgrades.add(criticalStrikeEffectFormulaUpgrade2);
        criticalStrikeEffectFormulaUpgrades.add(criticalStrikeEffectFormulaUpgrade3);
        criticalStrikeFormula.put("criticalchance", criticalStrikeEffectFormulaUpgrades);
        allAbiliyFormulas.put("criticalstrike", criticalStrikeFormula);

        //elixir
        HashMap<String, ArrayList<Formula>> elixirFormula = new HashMap<>();
        Formula elixirEffectFormulaUpgrade1 = new Formula("100", null);
        Formula elixirEffectFormulaUpgrade2 = new Formula("150", null);
        Formula elixirEffectFormulaUpgrade3 = new Formula(" 150", null);
        ArrayList<Formula> elixirEffectFormulaUpgrades = new ArrayList<>();
        elixirEffectFormulaUpgrades.add(elixirEffectFormulaUpgrade1);
        elixirEffectFormulaUpgrades.add(elixirEffectFormulaUpgrade2);
        elixirEffectFormulaUpgrades.add(elixirEffectFormulaUpgrade3);
        ArrayList<Formula> elixirCostFormula1 = new ArrayList<>();
        ArrayList<Formula> elixirCostFormula2 = new ArrayList<>();
        Formula elixirCostEP1 = new Formula("0 - 2", null);
        Formula elixirCostEP2 = new Formula("0 - 2", null);
        Formula elixirCostEP3 = new Formula("0 - 2", null);
        Formula elixirCostMagic1 = new Formula("0 - 60", null);
        Formula elixirCostMagic2 = new Formula("0 - 60", null);
        Formula elixirCostMagic3 = new Formula("0 - 60", null);
        elixirCostFormula1.add(elixirCostEP1);
        elixirCostFormula1.add(elixirCostEP2);
        elixirCostFormula1.add(elixirCostEP3);
        elixirCostFormula2.add(elixirCostMagic1);
        elixirCostFormula2.add(elixirCostMagic2);
        elixirCostFormula2.add(elixirCostMagic3);
        elixirFormula.put("current health", elixirEffectFormulaUpgrades);
        elixirFormula.put("cost EP", elixirCostFormula1);
        elixirFormula.put("cost magic", elixirCostFormula2);
        allAbiliyFormulas.put("elixir", elixirFormula);

        //coolDown
        ArrayList<Integer> elixirCooldownUpgrade = new ArrayList<>();
        elixirCooldownUpgrade.add(1);
        elixirCooldownUpgrade.add(1);
        elixirCooldownUpgrade.add(0);
        allAbilityCooldowns.put("elixir", elixirCooldownUpgrade);

        //caretaker
        HashMap<String, ArrayList<Formula>> caretakerFormula = new HashMap<>();
        Formula caretakerEffectFormulaUpgrade1 = new Formula("1", null);
        Formula caretakerEffectFormulaUpgrade2 = new Formula("1", null);
        Formula caretakerEffectFormulaUpgrade3 = new Formula("1", null);
        ArrayList<Formula> caretakerEffectFormulaUpgrades = new ArrayList<>();
        caretakerEffectFormulaUpgrades.add(caretakerEffectFormulaUpgrade1);
        caretakerEffectFormulaUpgrades.add(caretakerEffectFormulaUpgrade2);
        caretakerEffectFormulaUpgrades.add(caretakerEffectFormulaUpgrade3);
        ArrayList<Formula> caretakerCostFormula1 = new ArrayList<>();
        ArrayList<Formula> caretakerCostFormula2 = new ArrayList<>();
        Formula caretakerCostEP1 = new Formula("0 - 2", null);
        Formula caretakerCostEP2 = new Formula("0 - 2", null);
        Formula caretakerCostEP3 = new Formula("0 - 1", null);
        Formula caretakerCostMagic1 = new Formula("0 - 30", null);
        Formula caretakerCostMagic2 = new Formula("0 - 30", null);
        Formula caretakerCostMagic3 = new Formula("0 - 30", null);
        caretakerCostFormula1.add(caretakerCostEP1);
        caretakerCostFormula1.add(caretakerCostEP2);
        caretakerCostFormula1.add(caretakerCostEP3);
        caretakerCostFormula2.add(caretakerCostMagic1);
        caretakerCostFormula2.add(caretakerCostMagic2);
        caretakerCostFormula2.add(caretakerCostMagic3);
        caretakerFormula.put("current EP", caretakerEffectFormulaUpgrades); // LOL
        caretakerFormula.put("cost EP", caretakerCostFormula1);
        caretakerFormula.put("cost magic", caretakerCostFormula2);
        allAbiliyFormulas.put("caretaker", caretakerFormula);

        //coolDown
        ArrayList<Integer> caretakerCooldownUpgrade = new ArrayList<>();
        caretakerCooldownUpgrade.add(1);
        caretakerCooldownUpgrade.add(0);
        caretakerCooldownUpgrade.add(0);
        allAbilityCooldowns.put("caretaker", caretakerCooldownUpgrade);

        //boost
        //in ghesmat temporary attack power = temporary attack power + A(vali bahs injas ke mitunan ru ham jam shan va inke faghat tu
        //hamun turn hast va ru attackPower miad
        HashMap<String, ArrayList<Formula>> boostFormula = new HashMap<>();
        Formula boostEffectFormulaUpgrade1 = new Formula("20", null);
        Formula boostEffectFormulaUpgrade2 = new Formula("30", null);
        Formula boostEffectFormulaUpgrade3 = new Formula("30", null);
        ArrayList<Formula> boostEffectFormulaUpgrades = new ArrayList<>();
        boostEffectFormulaUpgrades.add(boostEffectFormulaUpgrade1);
        boostEffectFormulaUpgrades.add(boostEffectFormulaUpgrade2);
        boostEffectFormulaUpgrades.add(boostEffectFormulaUpgrade3);
        ArrayList<Formula> boostCostFormula1 = new ArrayList<>();
        ArrayList<Formula> boostCostFormula2 = new ArrayList<>();
        Formula boostCostEP1 = new Formula("0 - 2", null);
        Formula boostCostEP2 = new Formula("0 - 2", null);
        Formula boostCostEP3 = new Formula("0 - 2", null);
        Formula boostCostMagic1 = new Formula("0 - 50", null);
        Formula boostCostMagic2 = new Formula("0- 50", null);
        Formula boostCostMagic3 = new Formula("0- 50", null);
        boostCostFormula1.add(boostCostEP1);
        boostCostFormula1.add(boostCostEP2);
        boostCostFormula1.add(boostCostEP3);
        boostCostFormula2.add(boostCostMagic1);
        boostCostFormula2.add(boostCostMagic2);
        boostCostFormula2.add(boostCostMagic3);
        boostFormula.put("temp attack", boostEffectFormulaUpgrades);
        boostFormula.put("cost EP", boostCostFormula1);
        boostFormula.put("cost magic", boostCostFormula2);
        allAbiliyFormulas.put("boost", boostFormula);
        //coolDown
        ArrayList<Integer> boostCooldownUpgrade = new ArrayList<>();
        boostCooldownUpgrade.add(1);
        boostCooldownUpgrade.add(1);
        boostCooldownUpgrade.add(0);
        allAbilityCooldowns.put("boost", boostCooldownUpgrade);

        //manaBeam
        HashMap<String, ArrayList<Formula>> manaBeamFormula = new HashMap<>();
        Formula manaBeamEffectFormulaUpgrade1 = new Formula("50", null);
        Formula manaBeamEffectFormulaUpgrade2 = new Formula("80", null);
        Formula manaBeamEffectFormulaUpgrade3 = new Formula("80", null);
        ArrayList<Formula> manaBeamEffectFormulaUpgrades = new ArrayList<>();
        manaBeamEffectFormulaUpgrades.add(manaBeamEffectFormulaUpgrade1);
        manaBeamEffectFormulaUpgrades.add(manaBeamEffectFormulaUpgrade2);
        manaBeamEffectFormulaUpgrades.add(manaBeamEffectFormulaUpgrade3);
        ArrayList<Formula> manaBeamCostFormula1 = new ArrayList<>();
        ArrayList<Formula> manaBeamCostFormula2 = new ArrayList<>();
        Formula manaBeamCostEP1 = new Formula("0 - 1", null);
        Formula manaBeamCostEP2 = new Formula("0 - 1", null);
        Formula manaBeamCostEP3 = new Formula("0 - 1", null);
        Formula manaBeamCostMagic1 = new Formula("0 - 50", null);
        Formula manaBeamCostMagic2 = new Formula("0 - 50", null);
        Formula manaBeamCostMagic3 = new Formula("0 - 50", null);
        manaBeamCostFormula1.add(manaBeamCostEP1);
        manaBeamCostFormula1.add(manaBeamCostEP2);
        manaBeamCostFormula1.add(manaBeamCostEP3);
        manaBeamCostFormula2.add(manaBeamCostMagic1);
        manaBeamCostFormula2.add(manaBeamCostMagic2);
        manaBeamCostFormula2.add(manaBeamCostMagic3);
        manaBeamFormula.put("current magic", manaBeamEffectFormulaUpgrades);
        manaBeamFormula.put("cost EP", manaBeamCostFormula1);
        manaBeamFormula.put("cost magic", manaBeamCostFormula2);
        allAbiliyFormulas.put("manabeam", manaBeamFormula);
        //coolDown
        ArrayList<Integer> manaBeamCooldownUpgrade = new ArrayList<>();
        manaBeamCooldownUpgrade.add(1);
        manaBeamCooldownUpgrade.add(1);
        manaBeamCooldownUpgrade.add(0);
        allAbilityCooldowns.put("manabeam", manaBeamCooldownUpgrade);
        /*************/ //ability bye bye! well THERE IS MORE.. DAMMIT
        // Find Tile Sources
        findTileSources();

        // Hero Images
        createHeroImages();

        // Enemy Images
        createEnemyImages();

        // Battle Icons
        createBattleIcons();

        // create battle backgrounds
        createBattleBackgrounds();

        // Create Shop Icons
        createShopIcons();

        // Create Shop Backgrounds
        createShopBackgrounds();

        // Create ShopKeepers
        createShopKeepers();

        // Create shop Welcome Messages
        createShopWelcomeMessages();

        // Create Ability Upgrade Icons
        createAbilityUpgradeIcons();

        // Create Ability Upgrade Backgrounds
        createAbilityUpgradeBackgrounds();

        // Create Entering Messages
        createEnteringMessage();

        // Create Hero Faces
        createHeroFaces();

        // Create Story Icons
        createStoryIcons();

        // Create Story Backgrounds
        createStoryBackgrounds();

        // Create Door Icons
        doorsAndTheirDirections.put(174, KeyEvent.VK_RIGHT);
        doorsAndTheirDirections.put(47, KeyEvent.VK_DOWN);
        doorsAndTheirDirections.put(64, KeyEvent.VK_RIGHT);
        doorsAndTheirDirections.put(69, KeyEvent.VK_RIGHT);
        doorsAndTheirDirections.put(88, KeyEvent.VK_UP);

        // Choose Door Keys
        doorsAndTheirKeys.put(174, 0);
        doorsAndTheirKeys.put(47, 2);
        doorsAndTheirKeys.put(64, 1);
        doorsAndTheirKeys.put(69, 0);
        doorsAndTheirKeys.put(88, 3);

        // Create Key Icons
        keyGidNums.add(235);
        keyGidNums.add(16);
        keyGidNums.add(110);

        // Starting x and y and map address

        startingX = 32;
        startingY = 15 * 32;
        mapAddress = "Tiled/MyMap.tmx";
        gameOverBackground = new UltimateImage("Main Pics/Story/GameOver1.png");
    }

    private void createStoryBackgrounds()
    {
        storyBackgroundSources.add(new UltimateImage("Main Pics/Story/DemonCastle.png"));
        storyBackgroundSources.add(new UltimateImage("Main Pics/Story/Dungeon5.png"));
        storyBackgroundSources.add(new UltimateImage("Main Pics/Story/Factory1.png"));
        storyBackgroundSources.add(new UltimateImage("Main Pics/Story/Devil.png"));
        storyBackgroundSources.add(new UltimateImage("Main Pics/Story/DarkSpace1.png"));
        storyBackgroundSources.add(new UltimateImage("Main Pics/Story/DarkSpace1.png"));
    }

    private void createStoryIcons()
    {
        storyGidNums.add(151);
        storyGidNums.add(238);
        storyGidNums.add(113);
        storyGidNums.add(58);
    }

    private void createHeroFaces()
    {
        heroFaces.put("chrome", new UltimateImage(0, 0, 96, 96, "Main Pics/Faces/Actor1.png"));
        heroFaces.put("eley", new UltimateImage(96, 0, 96, 96, "Main Pics/Faces/Actor1.png"));
        heroFaces.put("bolti", new UltimateImage(192, 0, 96, 96, "Main Pics/Faces/Actor1.png"));
        heroFaces.put("meryl", new UltimateImage(288, 0, 96, 96, "Main Pics/Faces/Actor1.png"));
    }

    private void createEnteringMessage()
    {
        heroEnteringMessage.add(new Pair<>("eley", "wow.. This is an ideal place to meditate"));
        heroEnteringMessage.add(new Pair<>("chrome", "So... Calming..."));
        heroEnteringMessage.add(new Pair<>("bolti", "Guys.. Lets do SOME HARDCORE TRAINING!!!"));
        heroEnteringMessage.add(new Pair<>("meryl", "Seems like a great place to improve my supporting abilities"));
    }

    private void createAbilityUpgradeBackgrounds()
    {
        abilityUpgradeBackgroundSources.add(new UltimateImage("Backdrop/WireMesh.png"));
        abilityUpgradeBackgroundSources.add(new UltimateImage("Backdrop/Translucent.png"));
        abilityUpgradeBackgroundSources.add(new UltimateImage("Backdrop/Finalpanorama.png"));
    }

    private void createAbilityUpgradeIcons()
    {
        abilityUpgradeGidNums.add(228);
        abilityUpgradeGidNums.add(143);
        abilityUpgradeGidNums.add(26);
    }

    private void createShopWelcomeMessages()
    {
        shopWelcomeMessages.add("Welcome young heros..");
        shopWelcomeMessages.add("How may I help you?");
        shopWelcomeMessages.add("He is close... hehehe");
    }

    private void createShopKeepers()
    {
        shopKeeperSources.add(new UltimateImage("Main Pics/Characters/ShopKeeper1.png"));
        shopKeeperSources.add(new UltimateImage("Main Pics/Characters/ShopKeeper2.png"));
        shopKeeperSources.add(new UltimateImage("Main Pics/Characters/ShopKeeper3.png"));
    }

    private void createShopBackgrounds()
    {
        shopBackgroundSources.add(new UltimateImage("Backdrop/Factory.png"));
        shopBackgroundSources.add(new UltimateImage("Backdrop/Mine.png"));
        shopBackgroundSources.add(new UltimateImage("Backdrop/DemonicWorld.png"));
    }

    private void createShopIcons()
    {
        shopGidNums.add(213);
        shopGidNums.add(183);
        shopGidNums.add(145);
    }

    private void createBattleBackgrounds()
    {
        battleBackgroundSources.add(new UltimateImage("Backdrop/Paranoia.png"));
        battleBackgroundSources.add(new UltimateImage("Backdrop/Fireee.png"));
        battleBackgroundSources.add(new UltimateImage("Backdrop/Forest.png"));
        battleBackgroundSources.add(new UltimateImage("Backdrop/Darkforest.png"));
        battleBackgroundSources.add(new UltimateImage("Backdrop/Decimatedtown.png"));
        battleBackgroundSources.add(new UltimateImage("Backdrop/Sewers.png"));
    }

    private void findTileSources()
    {
        tileSources.add("Main Pics/Tiles/Inside_A5.png");
        tileSources.add("Main Pics/Doors/Door.png");
        tileSources.add("Main Pics/Items/IconSet.png");
        tileSources.add("Main Pics/SpecialPlaces/World_B.png");
        tileSources.add("Main Pics/Items/Thunder.png");
        tileSources.add("Main Pics/Collisions/Collision.png");
    }

    private void createBattleIcons()
    {
        battleGidNums.add(181);
        battleGidNums.add(176);
        battleGidNums.add(21);
        battleGidNums.add(218);
        battleGidNums.add(66);
        battleGidNums.add(28);
    }

    private void createEnemyImages()
    {
        for (String enemyName : normalEnemyDatas.keySet())
        {
            ArrayList<EnemyVersion> enemyVersions = normalEnemyDatas.get(enemyName);
            for (EnemyVersion enemyVersion : enemyVersions)
            {
                UltimateImage ultimateImage = null;
                if (enemyName.toLowerCase().equals("thug"))
                {
                    switch (enemyVersion.getName())
                    {
                        case "weak":
                            ultimateImage = new UltimateImage("Monsters/43.png");
                            break;
                        case "able":
                            ultimateImage = new UltimateImage("Monsters/44.png");
                            break;
                        case "mighty":
                            ultimateImage = new UltimateImage("Monsters/45.png");
                            break;
                    }
                } else if (enemyName.toLowerCase().equals("angel"))
                {
                    switch (enemyVersion.getName())
                    {
                        case "weak":
                            ultimateImage = new UltimateImage("Monsters/46.png");
                            break;
                        case "able":
                            ultimateImage = new UltimateImage("Monsters/47.png");
                            break;
                    }
                } else if (enemyName.toLowerCase().equals("tank"))
                {
                    switch (enemyVersion.getName())
                    {
                        case "weak":
                            ultimateImage = new UltimateImage("Monsters/41.png");
                            break;
                        case "able":
                            ultimateImage = new UltimateImage("Monsters/42.png");
                            break;
                    }
                }
                allNormalEnemyImages.put(ultimateImage, enemyVersion.getName() + " " + enemyVersion.getMainEnemyName());
            }
        }

        UltimateImage ultimateImage = new UltimateImage("Monsters/48.png");
        bossEnemyImages.put(ultimateImage, bossEnemyNames.get(0));
    }

    private void createHeroImages()
    {
        for (int i = 0; i < 4; i++)
        {
            herosAndTheirImages.put(new UltimateImage(i * 96, 0, 96, 128, "Main Pics/Characters/Actor1.png"), heroNames.get(i));
        }
    }

    public void startCreating(Scanner in)
    {
        System.out.println("Welcome!");
        while (true)
        {
            System.out.println("What do you want to create?(Enter the right number)");
            System.out.println("1- Hero Class");
            System.out.println("2- Hero");
            System.out.println("3- Normal Enemy");
            System.out.println("4- Boss Enemy");
            System.out.println("5- Item");
            System.out.println("6- Ability");
            System.out.println("7- Shop Data");
            System.out.println("8- Storyline");

            String input = in.next();
            switch (input)
            {
                case "1":
                    creatHeroClass(in);
                    break;
                case "2":
                    createHero(in);
                    break;
                case "3":
                    createNormalEnemy(in);
                    break;
                case "4":
                    createBossEnemy(in);
                    break;
                case "5":
                    createItem(in);
                    break;
                case "6":
                    createAbility(in);
                    break;
                case "7":
                    createShopData(in);
                    break;
                case "8":
                    createStoryline(in);
                    break;
            }

            System.out.println("Want to create anything else?(Enter the right number)");

            if (!yesNoQuestion(in))
            {
                break;
            }
        }
    }


    private void creatHeroClass(Scanner in)
    {
        String heroClassName;
        HashMap<String, Integer> heroClassData = new HashMap<>();

        while (true)
        {
            System.out.print("Please enter the name of the class you want to make: ");
            heroClassName = in.next();

            System.out.println("Are you sure?(Enter the right number)");

            if (yesNoQuestion(in))
            {
                heroClassNames.add(heroClassName);
                break;
            }
        }

        System.out.println("Please enter the amount you want for each attribute");

        for (String heroAttribute : heroAttributes)
        {
            while (true)
            {
                System.out.print(heroAttribute + ": ");

                String attributeAmount = in.next();
                if (attributeAmount.matches("[0-9]+") && attributeAmount.length() < 8)
                {
                    int attributeAmountNum = Integer.parseInt(attributeAmount);

                    heroClassData.put(heroAttribute, attributeAmountNum);
                    break;
                } else
                {
                    // Invalid Input
                }
            }
        }
        heroClassDatas.put(heroClassName, heroClassData);

        System.out.println("How many abilities do you want to give to this class?");
        //check invalid input

        int abilityNum = in.nextInt();

        System.out.println("Please enter the name of the abilities you want this class to have:");
        showAbilityNames();
        ArrayList<String> thisClassAbilities = new ArrayList<>();

        while (true)
        {
            String abilityName = in.next();
            if (abilityNames.contains(abilityName))
            {
                thisClassAbilities.add(abilityName);
            } else
            {
                //invalid input
                continue;
            }

            System.out.println("Do you want to add any other ability?(Enter the right number)");
            if (!yesNoQuestion(in))
            {
                break;
            }
        }
        heroClassAbilities.put(heroClassName, thisClassAbilities);

        System.out.println("Please enter the size of the inventory of the heros in this class: ");
        //check invalid input
        int inventorySize = in.nextInt();
        heroClassInventorySizes.put(heroClassName, inventorySize);

        System.out.println("Hero Class was made!");
    }


    private void createHero(Scanner in)
    {
        String heroName, heroClassName;
        ArrayList<String> abilityList = new ArrayList<>();

        while (true)
        {
            System.out.print("Please enter the name of the hero you want to make: ");
            heroName = in.next();

            System.out.println("Are you sure?(Enter the right number)");

            if (yesNoQuestion(in))
            {
                heroNames.add(heroName);
                break;
            }
        }

        while (true)
        {
            System.out.println("Please choose a class for this hero: ");
            showHeroClasses();

            heroClassName = in.next();
            if (heroClassNames.contains(heroClassName))
            {
                System.out.println("Are you sure?(Enter the right number)");

                if (yesNoQuestion(in))
                {
                    herosAndTheirClasses.put(heroName, heroClassName);
                    break;
                }
            } else
            {
                // invalid input
            }
        }

        System.out.println("Please enter the name of the abilities you want this hero to have:");
        showAbilityNames();
        while (true)
        {
            String abilityName = in.next();
            if (abilityNames.contains(abilityName))
            {
                abilityList.add(abilityName);
            } else
            {
                //invalid input
                continue;
            }

            System.out.println("Do you want to add any other ability?(Enter the right number)");
            if (!yesNoQuestion(in))
            {
                break;
            }

            System.out.println("Please enter the name of the next ability you want this hero to have: ");
        }
        herosAndTheirAbilities.put(heroName, abilityList);

        System.out.println("Hero was made!");
    }


    private void createNormalEnemy(Scanner in)
    {
        String enemyName;
        ArrayList<EnemyVersion> enemyVersions = new ArrayList<>();

        while (true)
        {
            System.out.print("Please enter the name of the enemy you want to make: ");
            enemyName = in.next();

            System.out.println("Are you sure?(Enter the right number)");

            if (yesNoQuestion(in))
            {
                normalEnemyNames.add(enemyName);
                break;
            }
        }

        System.out.println("How many versions do you want to make for this enemy?");
        //check invalid input
        int versionNum = in.nextInt();

        for (int i = 0; i < versionNum; i++)
        {
            EnemyVersion enemyVersion = makeEnemyVersion(in, enemyName);
            enemyVersions.add(enemyVersion);

            System.out.println("Version added!");
        }
        normalEnemyDatas.put(enemyName, enemyVersions);

        System.out.println("Enemy was made!");
    }


    private void createBossEnemy(Scanner in)
    {
        String bossName;
        ArrayList<String> specialConditions = new ArrayList<>();
        ArrayList<String> earlySpecialEffects = new ArrayList<>();
        HashMap<String, Integer> bossData = new HashMap<>();

        while (true) // Boss Enemy name
        {
            System.out.print("Please enter the name of the boss enemy you want to make: ");
            bossName = in.next();

            System.out.println("Are you sure?(Enter the right number)");

            if (yesNoQuestion(in))
            {
                bossEnemyNames.add(bossName);
                break;
            }
        }

        System.out.println("Please enter the amount you want for each attribute");

        for (int i = 0; i < enemyAttributes.size(); i++) //Boss Enemy data
        {
            while (true)
            {
                String attributeName = enemyAttributes.get(i); // It will be nice if it goes up

                System.out.print(attributeName + ": ");

                String attributeAmount = in.next();
                if (attributeAmount.matches("[0-9]+") && attributeAmount.length() < 8)
                {
                    int attributeAmountNum = Integer.parseInt(attributeAmount);
                    bossData.put(attributeName, attributeAmountNum);
                    break;
                } else
                {
                    // Invalid Input
                }
            }
        }

        bossEnemyDatas.put(bossName, bossData);

        // Must handle Condition and early effects
        System.out.println("Sometimes Boss Enemies get angry.. When their health gets lower than a specific amount...");
        System.out.println("When they get angry the way they make their moves will change!");
        System.out.println("So how much is that specific amount for this boss?");
        //check invalid input
        int angerPoint = in.nextInt();
        bossEnemyAngerPoints.put(bossName, angerPoint);
        HashMap<String, Integer> angerAdditions = new HashMap<>();

        while (true) // getting anger data
        {
            System.out.println("Which of the following will change because of anger?");
            showChangableBossEnemyAttributes();
            //check invalid input
            String changableAttribute = in.next();
            if (changableBossEnemyAttributes.contains(changableAttribute))
            {
                System.out.println("By how much?");
                //check invalid input
                int additionAmount = in.nextInt();
                angerAdditions.put(changableAttribute, additionAmount);
            } else
            {
                System.out.println("Invalid input! Please try again");
                continue;
            }

            System.out.println("Will anger have any more effect?");

            if (!yesNoQuestion(in))
            {
                break;
            }
        }
        bossEnemyAngerAdditions.put(bossName, angerAdditions);

        System.out.println("Bosses cause some effects at the start of each turn");
        System.out.println("The amount of that cause will be a random number in a given range");
        HashMap<String, String> earlyEffects = new HashMap<>();

        while (true)
        {
            System.out.println("Which of the following will change?");
            showHeroAttributes();
            //check invalid input
            String chosenAttribute = in.next();
            if (heroAttributes.contains(chosenAttribute))
            {
                System.out.println("Enter the range(a to b)");
                System.out.print("a: ");
                //check invalid input
                String a = in.next();
                System.out.print("b: ");
                String b = in.next();
                String effectRange = a + " to " + b;
                earlyEffects.put(chosenAttribute, effectRange);
            } else
            {
                System.out.println("Invalid input! Please try again");
                continue;
            }

            System.out.println("More effects?");

            if (!yesNoQuestion(in))
            {
                break;
            }
        }

        bossEnemyEarlyEffects.put(bossName, earlyEffects);

        while (true)
        {
            System.out.println("Please enter the target of this boss enemy");
            showPossibleBossEnemyTargets();
            //check invalid input
            String target = in.next();
            if (target.equals("specific number"))
            {
                System.out.println("How many?");
                //check invalid input
                String targetNum = in.next();
                while (true)
                {
                    System.out.println("Enemy or Hero?");
                    String targetKind = in.next();
                    target = targetNum + " " + targetKind;
                    if (targetKind.equals("enemy") || targetKind.equals("hero"))
                    {
                        bossEnemyTargets.put(bossName, target);
                        break;
                    }

                    System.out.println("Invalid input! Please try again");
                }
            } else if (possibleBossEnemyTargets.contains(target))
            {
                bossEnemyTargets.put(bossName, target);
                break;
            } else
            {
                System.out.println("Invalid input! Please try again");
                continue;
            }
        }

        System.out.println("Boss Enemy was made!");
    }


    private void showPossibleBossEnemyTargets()
    {
        possibleBossEnemyTargets.forEach(System.out::println);
    }

    private void showHeroAttributes()
    {
        heroAttributes.forEach(System.out::println);
    }

    private void showChangableBossEnemyAttributes()
    {
        changableBossEnemyAttributes.forEach(System.out::println);
    }

    private void createItem(Scanner in)
    {
        String itemName, itemTarget;
        HashMap<String, Integer> itemData = new HashMap<>();

        while (true)
        {
            System.out.print("Please enter the name of the item you want to make: ");
            itemName = in.next();

            System.out.println("Are you sure?(Enter the right number)");

            if (yesNoQuestion(in))
            {
                itemNames.add(itemName);
                break;
            }
        }

        while (true)
        {
            System.out.println("Please enter the name of the attribute that you want this item to affect it");
            showItemAttributes();
            String attributeName = in.next();

            if (!itemAttributes.contains(attributeName))
            {
                // invalid input
                continue;
            }

            while (true)
            {
                System.out.println("Please enter the amount of effect: ");
                String attributeAmount = in.next();

                if (attributeAmount.matches("[0-9]+") && attributeAmount.length() < 8)
                {
                    int attributeAmountNum = Integer.parseInt(attributeAmount);
                    itemData.put(attributeName, attributeAmountNum);
                    break;
                } else
                {
                    // Invalid Input
                }
            }

            System.out.println("Do you want any other effect for this item?");

            if (!yesNoQuestion(in))
            {
                break;
            }
        }

        while (true)
        {
            System.out.println("Please enter the target of this item");
            showPossibleItemTargets();
            itemTarget = in.next();

            if (itemTargets.containsKey(itemTarget))
            {
                itemTargets.put(itemName, itemTarget);
                break;
            }

            //invalid input
        }

        System.out.println("Does inflation affect this item?(will it get more expensive after each time you buy it?)");

        if (yesNoQuestion(in))
        {
            inflationedItems.add(itemName);
        }

        System.out.println("Does this ability take effect exactly after buying?");

        if (yesNoQuestion(in))
        {
            instantEffectItems.add(itemName);
        }

        System.out.println("Item was made!");
    }


    private void createAbility(Scanner in)
    {
        String abilityName, abilityTarget;
        int upgradeNum;
        HashMap<String, ArrayList<Formula>> formulas = new HashMap<>();
        ArrayList<Integer> abilityUpgradeXPs = new ArrayList<>();
        //HashMap<String, ArrayList<Double>> extraVariables = new HashMap<>();

        while (true) // Ability name
        {
            System.out.print("Please enter the name of the ability you want to make: ");
            abilityName = in.next();

            System.out.println("Are you sure?(Enter the right number)");

            if (yesNoQuestion(in))
            {
                abilityNames.add(abilityName);
                break;
            }
        }

        System.out.println("How many upgrades does this ability have?");
        //check Invalid input
        upgradeNum = in.nextInt();

        for (int i = 0; i < upgradeNum; i++) // Ability upgrade XPs
        {
            System.out.print("Please enter how much xp is needed to get this upgrade: ");
            //check invalid input
            int upgradeXP = in.nextInt();

            abilityUpgradeXPs.add(upgradeXP);
        }

        for (String abilityAttribute1 : abilityAttributes)
        {
            HashMap<String, ArrayList<Double>> extraVariables = new HashMap<>();
            System.out.println("Does this ability have any effect on " + abilityAttribute1);

            if (!yesNoQuestion(in))
            {
                continue;
            }

            System.out.println("For creating a formula for the effect on " + abilityAttribute1 + " do you need any extra variables?");

            if (yesNoQuestion(in))
            {
                System.out.println("How many?");
                //check invalid input
                int variableNum = in.nextInt();

                for (int j = 0; j < variableNum; j++)
                {
                    ArrayList<Double> variableValues = new ArrayList<>();
                    System.out.print("Enter variable name(don't use numbers): ");
                    //check invalid input
                    String variableName = in.next();
                    System.out.println("Please enter its value for each upgrade");
                    for (int k = 0; k < upgradeNum; k++)
                    {
                        System.out.print("Upgrade" + (k + 1) + ": ");
                        //check invalid input.. NOT MORE THAN ONE FLOATING POINT
                        double variableValue = in.nextDouble();
                        variableValues.add(variableValue);
                    }

                    extraVariables.put(variableName, variableValues);
                }
            }

            System.out.println("Please enter the formula");
            showAllVariables(extraVariables.keySet());
            String formulaString = getFormulaString(in);
            ArrayList<Formula> differentUpgradeFormulas = new ArrayList<>();

            for (int j = 0; j < upgradeNum; j++)
            {
                HashMap<String, Double> formulaData = new HashMap<>();
                for (String variableName : extraVariables.keySet())
                {
                    double variableValue = extraVariables.get(variableName).get(j); // might need more explanation
                    formulaData.put(variableName, variableValue);
                }
                Formula formula = new Formula(formulaString, formulaData);
                differentUpgradeFormulas.add(formula);
            }

            formulas.put(abilityAttribute1, differentUpgradeFormulas);

            System.out.println("In some abilities there is a chance for the ability to work differently");
            System.out.println("Is this ability affected by this kind of luck?");

            if (yesNoQuestion(in))
            {
                System.out.println("Please enter the luck formula");
                showAllVariables(extraVariables.keySet());
                showNormalDamageFormula(formulaString);
                String luckFormulaString = getFormulaString(in);
                ArrayList<Formula> differentLuckUpgradeFormulas = new ArrayList<>();
//				ArrayList<Integer> luckPercents = new ArrayList<>();

                for (int j = 0; j < upgradeNum; j++)
                {
                    HashMap<String, Double> formulaData = new HashMap<>();
                    for (String variableName : extraVariables.keySet())
                    {
                        double variableValue = extraVariables.get(variableName).get(j); // might need more explanation
                        formulaData.put(variableName, variableValue);
                    }
                    Formula formula = new Formula(luckFormulaString, formulaData);
                    differentLuckUpgradeFormulas.add(formula);

//					System.out.print("Please enter the percent of this luck: ");
//					int luckPercent = in.nextInt();
//					luckPercents.add(luckPercent);
                }

//				abilityLuckPercents.put(abilityName, luckPercents);
                formulas.put("luck " + abilityAttribute1, differentLuckUpgradeFormulas);
            }
        }

        // Check if luck exists for this ability or not
        System.out.println("Please enter the percent chance of luck for each upgrade");
        ArrayList<Integer> luckPercents = new ArrayList<>();
        for (int i = 0; i < upgradeNum; i++) // ability luck percents
        {
            System.out.print("Upgrade " + (i + 1) + ": ");
            // check invalid input
            int luckPercent = in.nextInt();
            luckPercents.add(luckPercent);
        }
        abilityLuckPercents.put(abilityName, luckPercents);

        System.out.println("Is it required to have an upgrade of any other ability before getting any of the upgrades of this ability?");

        if (yesNoQuestion(in)) // required abilities
        {
            ArrayList<HashMap<String, Integer>> requieredAbilities = new ArrayList<>();
            for (int i = 0; i < upgradeNum; i++)
            {
                System.out.println("Does upgrade" + (i + 1) + " require any other abilities?");
                HashMap<String, Integer> requieredAbilityForUpgrade = new HashMap<>();
                if (yesNoQuestion(in))
                {
                    while (true)
                    {
                        System.out.println("Which ability?");
                        String requieredAbilityName = getRequieredAbilityName(in, abilityName);
                        System.out.println("Which Upgrade?");
                        int requieredAbilityUpgrade = getRequieredAbilityUpgrade(in, requieredAbilityName);

                        requieredAbilityForUpgrade.put(requieredAbilityName, requieredAbilityUpgrade);

                        System.out.println("Does this upgrade need more requiered abilities?");
                        if (!yesNoQuestion(in))
                        {
                            break;
                        }
                    }
                }

                requieredAbilities.add(requieredAbilityForUpgrade);
            }
            allRequiredAbilities.put(abilityName, requieredAbilities);
        }


        while (true) // ability target
        {
            System.out.println("Please enter the target of this ability");
            showPossibleAbilityTargets();
            abilityTarget = in.next();

            if (abilityTargets.containsKey(abilityTarget))
            {
                abilityTargets.put(abilityName, abilityTarget);
                if (isMultipuleTarget(abilityTarget))
                {
                    System.out.println("We will choose one of the targets as the main one");
                    ArrayList<Integer> secondaryTargetShare = new ArrayList<>();
                    for (int i = 0; i < upgradeNum; i++)
                    {
                        System.out.println("How much do you want the percent share of other targets be in upgrade " + (i + 1) + "?");
                        System.out.println("(obviously if you want all of the targets to be the same, enter 100");
                        System.out.print("Share: ");
                        //check valid input
                        int secondaryTargetShareAmount = in.nextInt();
                        secondaryTargetShare.add(secondaryTargetShareAmount);
                    }

                    secondaryTargetShares.put(abilityName, secondaryTargetShare);
                }
                break;
            }
            //invalid input
        }

        System.out.println("How many cooldown turns does this ability have for each upgrade?");
        System.out.println("(enter 0 if it doesn't have a cooldown)");
        ArrayList<Integer> cooldowns = new ArrayList<>();
        for (int i = 0; i < upgradeNum; i++) // ability cooldowns
        {
            System.out.print("Upgrade " + (i + 1) + ": ");
            //Check invalid input
            int cooldownNum = in.nextInt();
            cooldowns.add(cooldownNum);
        }
        allAbilityCooldowns.put(abilityName, cooldowns);

        System.out.println("Does this ability take effect exactly after upgrading?");

        if (yesNoQuestion(in)) // instant effect check
        {
            instantEffectConditionAbilities.add(abilityName);
        } else
        {
            System.out.println("How many times can this item be used before finishing?");
            //check invalid input
            int useLimit = in.nextInt();
            nonInstantEffectItemsUseLimit.put(abilityName, useLimit);
        }


        System.out.println("Ability was freaking finally made!");
    }


    private void createShopData(Scanner in)
    {
        while (true)
        {
            System.out.println("Which item do you want to add to your shop?");
            showItemNames();
            //check invalid input
            String itemName = in.next();
            if (itemNames.contains(itemName))
            {
//                shopItemNames.add(itemName);
            } else
            {
                System.out.println("Invalid input! Please try again");
                continue;
            }

            System.out.println("Please enter it's cost: ");
            //check invalid input
            int itemCost = in.nextInt();
//            shopItemMoneyCosts.put(itemName, itemCost);

            System.out.println("Do you want to add any other items to your shop?");

            if (!yesNoQuestion(in))
            {
                break;
            }
        }

        System.out.println("How much will be the inflation in this shop?");

        //check invalid input
//        shopInflationValue = in.nextInt();

        System.out.println("Shop data completed!");
    }


    private void createStoryline(Scanner in)
    {
        System.out.println("How many parts does your game story consist of?");
        System.out.println("(each part shows us part of the story and has it's own enemy group)");
        //check invalid input
        BattleId = in.nextInt();
        for (int i = 0; i < BattleId; i++)
        {
            ArrayList<String> enemyNames = new ArrayList<>();
            System.out.println("Please enter the part of story related to this part:");
            //Check invalid input
            String storyPart = getThisStoryPart(in);
            gameStory.add(storyPart);

            System.out.println("How many enemies will the heros encounter in this part?");
            //check invalid input
            int enemyGroupNum = in.nextInt();

            for (int j = 0; j < enemyGroupNum; j++)
            {
                System.out.println("Do you want to add a boss enemy or a normal enemy?(Enter the right number)");
                System.out.println("1- Normal Enemy");
                System.out.println("2- Boss Enemy");
                // check invalid input
                int enemyChoice = in.nextInt();
                if (enemyChoice == 1)
                {
                    while (true)
                    {
                        System.out.println("Choose the enemy");
                        showNormalEnemyNames();
                        String normalEnemy = in.next();
                        if (normalEnemyNames.contains(normalEnemy))
                        {
                            System.out.println("Which version?");
                            showEnemyVersion(normalEnemy);
                            String normalEnemyVersion = getEnemyVersion(in, normalEnemy);
                            enemyNames.add(normalEnemyVersion + " " + normalEnemy);
                            break;
                        }

                        System.out.println("Invalid input! Please try again");
                    }
                } else if (enemyChoice == 2)
                {
                    while (true)
                    {
                        System.out.println("Choose the enemy");
                        showBossEnemyNames();
                        String bossEnemy = in.next();
                        if (bossEnemyNames.contains(bossEnemy))
                        {
                            enemyNames.add(bossEnemy);
                            break;
                        }

                        System.out.println("Invalid input! Please try again");
                    }
                }
            }

            storyEnemyGroups.add(enemyNames);

            System.out.println("Will heros get any xp after beating these enemies?");
            int xpAmount = 0;
            if (yesNoQuestion(in))
            {
                System.out.println("How much?");
                //check invalid input
                xpAmount = in.nextInt();
            }
            enemyGroupXPs.add(xpAmount);

            System.out.println("Will heros get any money after beating these enemies?");
            int moneyAmount = 0;
            if (yesNoQuestion(in))
            {
                System.out.println("How much?");
                //check invalid input
                moneyAmount = in.nextInt();
            }
            enemyGroupMoneys.add(moneyAmount);
        }

        System.out.println("There are some items named \"Immortality Potion\"");
        System.out.println("When one of the heros die, it will automaticly revive that hero");
        System.out.println("If you run out of them and one of the heros die.. GAME OVER");
        System.out.println("So how many of them do you want to have at the beginning?");
        //check invalid input
        immortalityPotionNum = in.nextInt();

        System.out.println("How much xp does the team have at the beginning?");
        //check invalid input
        initialXP = in.nextInt();

        System.out.println("How much money does the team have at the beginning?");
        //check invalid input
        initialMoney = in.nextInt();

        System.out.println("Storyline Ended");
    }


    private EnemyVersion makeEnemyVersion(Scanner in, String enemyName)
    {
        String versionName, versionTarget;
        HashMap<String, Integer> versionData = new HashMap<>();

        while (true)
        {
            System.out.print("Please enter the name of the version you want to make: ");
            versionName = in.next();

            System.out.println("Are you sure?(Enter the right number)");

            if (yesNoQuestion(in))
            {
                break;
            }
        }

        System.out.println("Please enter the amount you want for each attribute");

        for (int i = 0; i < enemyAttributes.size(); i++)
        {
            while (true)
            {
                String attributeName = enemyAttributes.get(i); // It will be nice if it goes up

                System.out.print(attributeName + ": ");

                String attributeAmount = in.next();
                if (attributeAmount.matches("[0-9]+") && attributeAmount.length() < 8)
                {
                    int attributeAmountNum = Integer.parseInt(attributeAmount);

                    versionData.put(attributeName, attributeAmountNum);
                    break;
                } else
                {
                    // Invalid Input
                }
            }
        }

        while (true)
        {
            System.out.println("Please enter the target of this enemy version");
            showPossibleNormalEnemyTargets();
            versionTarget = in.next();
            if (possibleNormalEnemyTargets.contains(versionTarget))
            {
                break;
            }

            System.out.println("Invalid input! Please try again");

        }

        EnemyVersion enemyVersion = new EnemyVersion(versionName, enemyName, versionData, versionTarget);

        return enemyVersion;
    }


    private boolean yesNoQuestion(Scanner in)
    {
        System.out.println("1- Yes");
        System.out.println("2- No");
        while (true)
        {
            String input = in.next();
            if (input.equals("1"))
            {
                return true;
            } else if (input.equals("2"))
            {
                return false;
            }

            System.out.println("Invalid input! Please try again.");
        }
    }


    private void showAbilityNames()
    {
        abilityNames.forEach(System.out::println);
    }


    private void showHeroClasses()
    {
        heroClassNames.forEach(System.out::println);
    }


    private void showItemAttributes()
    {
        itemAttributes.forEach(System.out::println);
    }


    private void showPossibleItemTargets()
    {
        possibleItemTargets.forEach(System.out::println);
    }


    private void showAllVariables(Set<String> variables)
    {
        System.out.println("Available variables:");

        variables.forEach(System.out::println);
        primaryVariableNames.keySet().forEach(System.out::println);
    }


    private String getFormulaString(Scanner in)
    {


        return "L.O.L";
    }


    private void showNormalDamageFormula(String formula)
    {
        System.out.println("Normal damage formula: " + formula);
    }


    private String getRequieredAbilityName(Scanner in, String mainAbilityName)
    {
        String requiredAbility;

        while (true)
        {
            System.out.println("List of available abilities:");
            for (String abilityName : abilityNames)
            {
                if (abilityName.equals(mainAbilityName))
                {
                    continue;
                }
                System.out.println(abilityName);
            }

            requiredAbility = in.next();

            if (!requiredAbility.equals(mainAbilityName) && abilityNames.contains(requiredAbility))
            {
                return requiredAbility;
            } else
            {
                System.out.println("Invalid input! Please try again");
            }
        }
    }


    private int getRequieredAbilityUpgrade(Scanner in, String requiredAbilityName)
    {
        int upgradeNum = allAbilityUpgradeXPs.get(requiredAbilityName).size(), requiredUpgrade;
        System.out.println(upgradeNum + " upgrades are available");
        System.out.print("Upgrade num: ");
        while (true)
        {
            requiredUpgrade = in.nextInt();
            if (requiredUpgrade > 0 && requiredUpgrade <= upgradeNum)
            {
                return requiredUpgrade;
            }
            System.out.println("Invalid input! Please try again");
        }
    }


    private void showPossibleAbilityTargets()
    {
        System.out.println("Available targets for an ability:");

        for (int i = 0; i < possibleAbilityTargets.size(); i++)
        {
            System.out.println(possibleAbilityTargets);
        }
    }


    private boolean isMultipuleTarget(String abilityTarget)
    {
        return abilityTarget.equals("all enemies") || abilityTarget.equals("all allies");
    }


    private void showItemNames()
    {
        System.out.println("Available items:");
        itemNames.forEach(System.out::println);
    }


    private String getThisStoryPart(Scanner in)
    {
        String storyPart;
        while (true)
        {
            in.nextLine();
            storyPart = in.nextLine();

            System.out.println("Are you sure?");
            if (yesNoQuestion(in))
            {
                break;
            }

            System.out.println("Please enter the part of story related to this part:");
        }

        return storyPart;
    }


    private void showNormalEnemyNames()
    {
        System.out.println("List of enemies:");
        normalEnemyNames.forEach(System.out::println);
    }


    private String getEnemyVersion(Scanner in, String mainEnemyName)
    {
        String enemyVersionName;
        ArrayList<EnemyVersion> thisEnemyVersions = normalEnemyDatas.get(mainEnemyName);
        ArrayList<String> versionNames = new ArrayList<>();
        for (EnemyVersion version : thisEnemyVersions)
        {
            versionNames.add(version.getName());
        }

        while (true)
        {
            enemyVersionName = in.next();
            if (versionNames.contains(enemyVersionName))
            {
                return enemyVersionName;
            }

            System.out.println("Invalid input! Please try again");
        }
    }


    private void showEnemyVersion(String mainEnemyName)
    {
        System.out.println("This enemy's list of version:");
        ArrayList<EnemyVersion> thisEnemyVersions = normalEnemyDatas.get(mainEnemyName);
        for (EnemyVersion version : thisEnemyVersions)
        {
            System.out.println(version.getName());
        }
    }


    private void showBossEnemyNames()
    {
        System.out.println("List of boss enemies: ");
        bossEnemyNames.forEach(System.out::println);
    }


    private void showPossibleNormalEnemyTargets()
    {
        possibleNormalEnemyTargets.forEach(System.out::println);
    }


    public ArrayList<String> getGameStory()
    {
        return gameStory;
    }


    public void setGameStory(ArrayList<String> gameStory)
    {
        this.gameStory = gameStory;
    }


    public boolean isCustomed()
    {
        return customed;
    }


    public void setCustomed(boolean customed)
    {
        this.customed = customed;
    }

    public int getBattleId()
    {
        return BattleId;
    }


    public void setBattleId(int battleId)
    {
        this.BattleId = battleId;
    }


    public int getInitialXP()
    {
        return initialXP;
    }


    public void setInitialXP(int initialXP)
    {
        this.initialXP = initialXP;
    }


    public int getInitialMoney()
    {
        return initialMoney;
    }


    public void setInitialMoney(int initialMoney)
    {
        this.initialMoney = initialMoney;
    }


    public int getImmortalityPotionNum()
    {
        return immortalityPotionNum;
    }


    public void setImmortalityPotionNum(int immortalityPotionNum)
    {
        this.immortalityPotionNum = immortalityPotionNum;
    }


    public ArrayList<String> getHeroClassNames()
    {
        return heroClassNames;
    }


    public void setHeroClassNames(ArrayList<String> heroClassNames)
    {
        this.heroClassNames = heroClassNames;
    }


    public HashMap<String, HashMap<String, Integer>> getHeroClassDatas()
    {
        return heroClassDatas;
    }


    public void setHeroClassDatas(HashMap<String, HashMap<String, Integer>> heroClassDatas)
    {
        this.heroClassDatas = heroClassDatas;
    }


    public HashMap<String, ArrayList<String>> getHeroClassAbilities()
    {
        return heroClassAbilities;
    }


    public void setHeroClassAbilities(HashMap<String, ArrayList<String>> heroClassAbilities)
    {
        this.heroClassAbilities = heroClassAbilities;
    }


    public ArrayList<String> getHeroAttributes()
    {
        return heroAttributes;
    }


    public void setHeroAttributes(ArrayList<String> heroAttributes)
    {
        this.heroAttributes = heroAttributes;
    }


    public HashMap<String, String> getHerosAndTheirClasses()
    {
        return herosAndTheirClasses;
    }


    public void setHerosAndTheirClasses(HashMap<String, String> herosAndTheirClasses)
    {
        this.herosAndTheirClasses = herosAndTheirClasses;
    }


    public HashMap<String, ArrayList<String>> getHerosAndTheirAbilities()
    {
        return herosAndTheirAbilities;
    }


    public void setHerosAndTheirAbilities(HashMap<String, ArrayList<String>> herosAndTheirAbilities)
    {
        this.herosAndTheirAbilities = herosAndTheirAbilities;
    }


    public ArrayList<String> getNormalEnemyNames()
    {
        return normalEnemyNames;
    }


    public void setNormalEnemyNames(ArrayList<String> normalEnemyNames)
    {
        this.normalEnemyNames = normalEnemyNames;
    }


    public HashMap<String, ArrayList<EnemyVersion>> getNormalEnemyDatas()
    {
        return normalEnemyDatas;
    }


    public void setNormalEnemyDatas(HashMap<String, ArrayList<EnemyVersion>> normalEnemyDatas)
    {
        this.normalEnemyDatas = normalEnemyDatas;
    }


    public ArrayList<String> getEnemyAttributes()
    {
        return enemyAttributes;
    }


    public void setEnemyAttributes(ArrayList<String> enemyAttributes)
    {
        this.enemyAttributes = enemyAttributes;
    }


    public ArrayList<String> getBossEnemyNames()
    {
        return bossEnemyNames;
    }


    public void setBossEnemyNames(ArrayList<String> bossEnemyNames)
    {
        this.bossEnemyNames = bossEnemyNames;
    }


    public HashMap<String, HashMap<String, Integer>> getBossEnemyDatas()
    {
        return bossEnemyDatas;
    }


    public void setBossEnemyDatas(HashMap<String, HashMap<String, Integer>> bossEnemyDatas)
    {
        this.bossEnemyDatas = bossEnemyDatas;
    }


    public ArrayList<String> getItemNames()
    {
        return itemNames;
    }


    public void setItemNames(ArrayList<String> itemNames)
    {
        this.itemNames = itemNames;
    }


    public HashMap<String, HashMap<String, Integer>> getItemDatas()
    {
        return itemDatas;
    }


    public void setItemDatas(HashMap<String, HashMap<String, Integer>> itemDatas)
    {
        this.itemDatas = itemDatas;
    }


    public HashMap<String, String> getItemTargets()
    {
        return itemTargets;
    }


    public void setItemTargets(HashMap<String, String> itemTargets)
    {
        this.itemTargets = itemTargets;
    }


    public ArrayList<String> getItemAttributes()
    {
        return itemAttributes;
    }


    public void setItemAttributes(ArrayList<String> itemAttributes)
    {
        this.itemAttributes = itemAttributes;
    }


    public ArrayList<String> getPossibleItemTargets()
    {
        return possibleItemTargets;
    }


    public void setPossibleItemTargets(ArrayList<String> possibleItemTargets)
    {
        this.possibleItemTargets = possibleItemTargets;
    }


    public ArrayList<String> getInflationedItems()
    {
        return inflationedItems;
    }


    public void setInflationedItems(ArrayList<String> inflationedItems)
    {
        this.inflationedItems = inflationedItems;
    }


    public ArrayList<String> getInstantEffectItems()
    {
        return instantEffectItems;
    }


    public void setInstantEffectItems(ArrayList<String> instantEffectItems)
    {
        this.instantEffectItems = instantEffectItems;
    }


    public ArrayList<String> getAbilityNames()
    {
        return abilityNames;
    }


    public void setAbilityNames(ArrayList<String> abilityNames)
    {
        this.abilityNames = abilityNames;
    }


    public HashMap<String, HashMap<String, ArrayList<Formula>>> getAllAbiliyFormulas()
    {
        return allAbiliyFormulas;
    }


    public void setAllAbiliyFormulas(HashMap<String, HashMap<String, ArrayList<Formula>>> allAbiliyFormulas)
    {
        this.allAbiliyFormulas = allAbiliyFormulas;
    }


    public HashMap<String, String> getAbilityTargets()
    {
        return abilityTargets;
    }


    public void setAbilityTargets(HashMap<String, String> abilityTargets)
    {
        this.abilityTargets = abilityTargets;
    }


    public ArrayList<String> getPossibleAbilityTargets()
    {
        return possibleAbilityTargets;
    }


    public void setPossibleAbilityTargets(ArrayList<String> possibleAbilityTargets)
    {
        this.possibleAbilityTargets = possibleAbilityTargets;
    }


    public HashMap<String, ArrayList<Integer>> getAllAbilityUpgradeXPs()
    {
        return allAbilityUpgradeXPs;
    }


    public void setAllAbilityUpgradeXPs(HashMap<String, ArrayList<Integer>> allAbilityUpgradeXPs)
    {
        this.allAbilityUpgradeXPs = allAbilityUpgradeXPs;
    }


    public ArrayList<String> getAbilityAttributes()
    {
        return abilityAttributes;
    }


    public void setAbilityAttributes(ArrayList<String> abilityAttributes)
    {
        this.abilityAttributes = abilityAttributes;
    }


    public HashMap<String, ArrayList<HashMap<String, Integer>>> getAllRequiredAbilities()
    {
        return allRequiredAbilities;
    }


    public void setAllRequiredAbilities(HashMap<String, ArrayList<HashMap<String, Integer>>> allRequiredAbilities)
    {
        this.allRequiredAbilities = allRequiredAbilities;
    }


    public HashMap<String, ArrayList<Integer>> getAbilityLuckPercents()
    {
        return abilityLuckPercents;
    }


    public void setAbilityLuckPercents(HashMap<String, ArrayList<Integer>> abilityLuckPercents)
    {
        this.abilityLuckPercents = abilityLuckPercents;
    }


    public HashMap<String, String> getPrimaryVariableNames()
    {
        return primaryVariableNames;
    }


    public void setPrimaryVariableNames(HashMap<String, String> primaryVariableNames)
    {
        this.primaryVariableNames = primaryVariableNames;
    }


    public HashMap<String, ArrayList<Integer>> getSecondaryTargetShares()
    {
        return secondaryTargetShares;
    }


    public void setSecondaryTargetShares(HashMap<String, ArrayList<Integer>> secondaryTargetShares)
    {
        this.secondaryTargetShares = secondaryTargetShares;
    }


    public HashMap<String, ArrayList<Integer>> getAllAbilityCooldowns()
    {
        return allAbilityCooldowns;
    }


    public void setAllAbilityCooldowns(HashMap<String, ArrayList<Integer>> allAbilityCooldowns)
    {
        this.allAbilityCooldowns = allAbilityCooldowns;
    }


    public ArrayList<String> getInstantEffectConditionAbilities()
    {
        return instantEffectConditionAbilities;
    }


    public void setInstantEffectConditionAbilities(ArrayList<String> instantEffectConditionAbilities)
    {
        this.instantEffectConditionAbilities = instantEffectConditionAbilities;
    }


    public ArrayList<ArrayList<String>> getStoryEnemyGroups()
    {
        return storyEnemyGroups;
    }


    public void setStoryEnemyGroups(ArrayList<ArrayList<String>> storyEnemyGroups)
    {
        this.storyEnemyGroups = storyEnemyGroups;
    }


    public ArrayList<Integer> getEnemyGroupXPs()
    {
        return enemyGroupXPs;
    }


    public void setEnemyGroupXPs(ArrayList<Integer> enemyGroupXPs)
    {
        this.enemyGroupXPs = enemyGroupXPs;
    }


    public ArrayList<Integer> getEnemyGroupMoneys()
    {
        return enemyGroupMoneys;
    }


    public void setEnemyGroupMoneys(ArrayList<Integer> enemyGroupMoneys)
    {
        this.enemyGroupMoneys = enemyGroupMoneys;
    }


    public HashMap<String, Integer> getNonInstantEffectItemsUseLimit()
    {
        return nonInstantEffectItemsUseLimit;
    }


    public void setNonInstantEffectItemsUseLimit(HashMap<String, Integer> nonInstantEffectItemsUseLimit)
    {
        this.nonInstantEffectItemsUseLimit = nonInstantEffectItemsUseLimit;
    }


    public HashMap<String, Integer> getHeroClassInventorySizes()
    {
        return heroClassInventorySizes;
    }

    public void setHeroClassInventorySizes(HashMap<String, Integer> heroClassInventorySizes)
    {
        this.heroClassInventorySizes = heroClassInventorySizes;
    }

    public ArrayList<String> getHeroNames()
    {
        return heroNames;
    }

    public void setHeroNames(ArrayList<String> heroNames)
    {
        this.heroNames = heroNames;
    }

    public ArrayList<String> getPossibleNormalEnemyTargets()
    {
        return possibleNormalEnemyTargets;
    }

    public void setPossibleNormalEnemyTargets(ArrayList<String> possibleNormalEnemyTargets)
    {
        this.possibleNormalEnemyTargets = possibleNormalEnemyTargets;
    }

    public HashMap<String, ArrayList<String>> getEnemyVersionNames()
    {
        return enemyVersionNames;
    }

    public void setEnemyVersionNames(HashMap<String, ArrayList<String>> enemyVersionNames)
    {
        this.enemyVersionNames = enemyVersionNames;
    }

    public ArrayList<String> getBossEnemyAttributes()
    {
        return bossEnemyAttributes;
    }

    public void setBossEnemyAttributes(ArrayList<String> bossEnemyAttributes)
    {
        this.bossEnemyAttributes = bossEnemyAttributes;
    }

    public ArrayList<String> getChangableBossEnemyAttributes()
    {
        return changableBossEnemyAttributes;
    }

    public void setChangableBossEnemyAttributes(ArrayList<String> changableBossEnemyAttributes)
    {
        this.changableBossEnemyAttributes = changableBossEnemyAttributes;
    }

    public HashMap<String, Integer> getBossEnemyAngerPoints()
    {
        return bossEnemyAngerPoints;
    }

    public void setBossEnemyAngerPoints(HashMap<String, Integer> bossEnemyAngerPoints)
    {
        this.bossEnemyAngerPoints = bossEnemyAngerPoints;
    }

    public HashMap<String, HashMap<String, Integer>> getBossEnemyAngerAdditions()
    {
        return bossEnemyAngerAdditions;
    }

    public void setBossEnemyAngerAdditions(HashMap<String, HashMap<String, Integer>> bossEnemyAngerAdditions)
    {
        this.bossEnemyAngerAdditions = bossEnemyAngerAdditions;
    }

    public HashMap<String, HashMap<String, String>> getBossEnemyEarlyEffects()
    {
        return bossEnemyEarlyEffects;
    }

    public void setBossEnemyEarlyEffects(HashMap<String, HashMap<String, String>> bossEnemyEarlyEffects)
    {
        this.bossEnemyEarlyEffects = bossEnemyEarlyEffects;
    }

    public ArrayList<String> getPossibleBossEnemyTargets()
    {
        return possibleBossEnemyTargets;
    }

    public void setPossibleBossEnemyTargets(ArrayList<String> possibleBossEnemyTargets)
    {
        this.possibleBossEnemyTargets = possibleBossEnemyTargets;
    }

    public HashMap<String, String> getBossEnemyTargets()
    {
        return bossEnemyTargets;
    }

    public void setBossEnemyTargets(HashMap<String, String> bossEnemyTargets)
    {
        this.bossEnemyTargets = bossEnemyTargets;
    }

    public HashMap<String, String> getAllAbilitySuccessMessages()
    {
        return allAbilitySuccessMessages;
    }

    public void setAllAbilitySuccessMessages(HashMap<String, String> allAbilitySuccessMessages)
    {
        this.allAbilitySuccessMessages = allAbilitySuccessMessages;
    }

    public HashMap<UltimateImage, String> getHerosAndTheirImages()
    {
        return herosAndTheirImages;
    }

    public void setHerosAndTheirImages(HashMap<UltimateImage, String> herosAndTheirImages)
    {
        this.herosAndTheirImages = herosAndTheirImages;
    }

    public HashMap<UltimateImage, String> getAllNormalEnemyImages()
    {
        return allNormalEnemyImages;
    }

    public void setAllNormalEnemyImages(HashMap<UltimateImage, String> allNormalEnemyImages)
    {
        this.allNormalEnemyImages = allNormalEnemyImages;
    }

    public HashMap<UltimateImage, String> getBossEnemyImages()
    {
        return bossEnemyImages;
    }

    public void setBossEnemyImages(HashMap<UltimateImage, String> bossEnemyImages)
    {
        this.bossEnemyImages = bossEnemyImages;
    }

    public HashMap<String, String> getAllEnemySuccessMessages()
    {
        return allEnemySuccessMessages;
    }

    public void setAllEnemySuccessMessages(HashMap<String, String> allEnemySuccessMessages)
    {
        this.allEnemySuccessMessages = allEnemySuccessMessages;
    }

    public HashMap<String, String> getAllItemSuccessMessages()
    {
        return allItemSuccessMessages;
    }

    public void setAllItemSuccessMessages(HashMap<String, String> allItemSuccessMessages)
    {
        this.allItemSuccessMessages = allItemSuccessMessages;
    }

    public ArrayList<Integer> getBattleGidNums()
    {
        return battleGidNums;
    }

    public void setBattleGidNums(ArrayList<Integer> battleGidNums)
    {
        this.battleGidNums = battleGidNums;
    }

    public ArrayList<String> getTileSources()
    {
        return tileSources;
    }

    public void setTileSources(ArrayList<String> tileSources)
    {
        this.tileSources = tileSources;
    }

    public int getStartingX()
    {
        return startingX;
    }

    public void setStartingX(int startingX)
    {
        this.startingX = startingX;
    }

    public int getStartingY()
    {
        return startingY;
    }

    public void setStartingY(int startingY)
    {
        this.startingY = startingY;
    }

    public String getMapAddress()
    {
        return mapAddress;
    }

    public void setMapAddress(String mapAddress)
    {
        this.mapAddress = mapAddress;
    }

    public ArrayList<UltimateImage> getBattleBackgroundSources()
    {
        return battleBackgroundSources;
    }

    public void setBattleBackgroundSources(ArrayList<UltimateImage> battleBackgroundSources)
    {
        this.battleBackgroundSources = battleBackgroundSources;
    }

    public ArrayList<ArrayList<String>> getAllShopItemNames()
    {
        return allShopItemNames;
    }

    public void setAllShopItemNames(ArrayList<ArrayList<String>> allShopItemNames)
    {
        this.allShopItemNames = allShopItemNames;
    }

    public ArrayList<HashMap<String, Integer>> getAllShopItemMoneyCosts()
    {
        return allShopItemMoneyCosts;
    }

    public void setAllShopItemMoneyCosts(ArrayList<HashMap<String, Integer>> allShopItemMoneyCosts)
    {
        this.allShopItemMoneyCosts = allShopItemMoneyCosts;
    }

    public ArrayList<Integer> getAllShopInflationValues()
    {
        return allShopInflationValues;
    }

    public void setAllShopInflationValues(ArrayList<Integer> allShopInflationValues)
    {
        this.allShopInflationValues = allShopInflationValues;
    }

    public ArrayList<Integer> getShopGidNums()
    {
        return shopGidNums;
    }

    public void setShopGidNums(ArrayList<Integer> shopGidNums)
    {
        this.shopGidNums = shopGidNums;
    }

    public ArrayList<UltimateImage> getShopBackgroundSources()
    {
        return shopBackgroundSources;
    }

    public void setShopBackgroundSources(ArrayList<UltimateImage> shopBackgroundSources)
    {
        this.shopBackgroundSources = shopBackgroundSources;
    }

    public ArrayList<UltimateImage> getShopKeeperSources()
    {
        return shopKeeperSources;
    }

    public void setShopKeeperSources(ArrayList<UltimateImage> shopKeeperSources)
    {
        this.shopKeeperSources = shopKeeperSources;
    }

    public ArrayList<String> getShopWelcomeMessages()
    {
        return shopWelcomeMessages;
    }

    public void setShopWelcomeMessages(ArrayList<String> shopWelcomeMessages)
    {
        this.shopWelcomeMessages = shopWelcomeMessages;
    }

    public HashMap<String, HashMap<String, ArrayList<HashMap<String, Integer>>>> getAllHeroRequiredAbilities()
    {
        return allHeroRequiredAbilities;
    }

    public void setAllHeroRequiredAbilities(HashMap<String, HashMap<String, ArrayList<HashMap<String, Integer>>>> allHeroRequiredAbilities)
    {
        this.allHeroRequiredAbilities = allHeroRequiredAbilities;
    }

    public HashMap<String, UltimateImage> getHeroFaces()
    {
        return heroFaces;
    }

    public void setHeroFaces(HashMap<String, UltimateImage> heroFaces)
    {
        this.heroFaces = heroFaces;
    }

    public ArrayList<Pair<String, String>> getHeroEnteringMessage()
    {
        return heroEnteringMessage;
    }

    public void setHeroEnteringMessage(ArrayList<Pair<String, String>> heroEnteringMessage)
    {
        this.heroEnteringMessage = heroEnteringMessage;
    }

    public ArrayList<UltimateImage> getAbilityUpgradeBackgroundSources()
    {
        return abilityUpgradeBackgroundSources;
    }

    public void setAbilityUpgradeBackgroundSources(ArrayList<UltimateImage> abilityUpgradeBackgroundSources)
    {
        this.abilityUpgradeBackgroundSources = abilityUpgradeBackgroundSources;
    }

    public ArrayList<Integer> getAbilityUpgradeGidNums()
    {
        return abilityUpgradeGidNums;
    }

    public void setAbilityUpgradeGidNums(ArrayList<Integer> abilityUpgradeGidNums)
    {
        this.abilityUpgradeGidNums = abilityUpgradeGidNums;
    }

    public ArrayList<Integer> getStoryGidNums()
    {
        return storyGidNums;
    }

    public void setStoryGidNums(ArrayList<Integer> storyGidNums)
    {
        this.storyGidNums = storyGidNums;
    }

    public ArrayList<UltimateImage> getStoryBackgroundSources()
    {
        return storyBackgroundSources;
    }

    public void setStoryBackgroundSources(ArrayList<UltimateImage> storyBackgroundSources)
    {
        this.storyBackgroundSources = storyBackgroundSources;
    }

    public UltimateImage getGameOverBackground()
    {
        return gameOverBackground;
    }

    public void setGameOverBackground(UltimateImage gameOverBackground)
    {
        this.gameOverBackground = gameOverBackground;
    }

    public ArrayList<Integer> getKeyGidNums()
    {
        return keyGidNums;
    }

    public void setKeyGidNums(ArrayList<Integer> keyGidNums)
    {
        this.keyGidNums = keyGidNums;
    }

    public HashMap<Integer, Integer> getDoorsAndTheirDirections()
    {
        return doorsAndTheirDirections;
    }

    public void setDoorsAndTheirDirections(HashMap<Integer, Integer> doorsAndTheirDirections)
    {
        this.doorsAndTheirDirections = doorsAndTheirDirections;
    }

    public HashMap<Integer, Integer> getDoorsAndTheirKeys()
    {
        return doorsAndTheirKeys;
    }

    public void setDoorsAndTheirKeys(HashMap<Integer, Integer> doorsAndTheirKeys)
    {
        this.doorsAndTheirKeys = doorsAndTheirKeys;
    }

    public ArrayList<String> getAttributesWithMax()
    {
        return attributesWithMax;
    }

    public void setAttributesWithMax(ArrayList<String> attributesWithMax)
    {
        this.attributesWithMax = attributesWithMax;
    }

    public ArrayList<String> getAttributeWithTemp()
    {
        return attributeWithTemp;
    }

    public void setAttributeWithTemp(ArrayList<String> attributeWithTemp)
    {
        this.attributeWithTemp = attributeWithTemp;
    }

    public HashMap<String, String> getItemDescription()
    {
        return ItemDescription;
    }

    public void setItemDescription(HashMap<String, String> itemDescription)
    {
        ItemDescription = itemDescription;
    }

    public HashMap<String, String> getAbilityDescription()
    {
        return abilityDescription;
    }

    public void setAbilityDescription(HashMap<String, String> abilityDescription)
    {
        this.abilityDescription = abilityDescription;
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
}