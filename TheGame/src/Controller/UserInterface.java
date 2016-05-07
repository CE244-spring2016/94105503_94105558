package Controller;

import Auxiliary.Formula;
import Model.EnemyVersion;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

/*
    We need init here!
	
	We could divide this class to 5-6 other classes
*/

public class UserInterface {
    private boolean customed;
    private int shopInflationValue;
    private int gameTurns;
    private int initialXP = 0;
    private int initialMoney = 0;
    private int immortalityPotionNum;


    private ArrayList<String> heroClassNames; // new it in the constructor
    private HashMap<String, HashMap<String, Integer>> heroClassDatas; // new it in the constructor
    private HashMap<String, ArrayList<String>> heroClassAbilities;
	private HashMap<String, Integer> heroClassInventorySizes; // A new variable!!

    private ArrayList<String> heroAttributes;                         // must make this fully in the constructor

    private HashMap<String, String> herosAndTheirClasses;
    private HashMap<String, ArrayList<String>> herosAndTheirAbilities;

    private ArrayList<String> normalEnemyNames;
    private HashMap<String, ArrayList<EnemyVersion>> normalEnemyDatas;
    private ArrayList<String> enemyAttributes;                        // must make this fully in the constructor
	private ArrayList<String> possibleNormalEnemyTargets; // A new variable
	
    private ArrayList<String> bossEnemyNames;
    private HashMap<String, HashMap<String, Integer>> bossEnemyDatas;
    private HashMap<String, ArrayList<String>> bossEnemySpecialConditions;
    private HashMap<String, ArrayList<String>> bossEnemyEarlyTurnEffects;

    private ArrayList<String> itemNames;
    private HashMap<String, HashMap<String, Integer>> itemDatas;
    private HashMap<String, String> itemTargets;
    private ArrayList<String> itemAttributes;                         // must make this fully in the constructor
    private ArrayList<String> possibleItemTargets;                    // must make this fully in the constructor
    private ArrayList<String> inflationItems;
    private ArrayList<String> instantEffectItems;
    private HashMap<String, Integer> nonInstantEffectItemsUseLimit;

    private ArrayList<String> abilityNames;
    private HashMap<String, HashMap<String, ArrayList<Formula>>> allAbiliyFormulas; // Even non targeted enemy share and cooldown is handled here
    private HashMap<String, String> abilityTargets;
    private ArrayList<String> possibleAbilityTargets;                 // must make this fully in the constructor
    private HashMap<String, ArrayList<Integer>> allAbilityUpgradeXPs;
    private ArrayList<String> abilityAttributes;                      // must make this fully in the constructor
    private HashMap<String, ArrayList<HashMap<String, Integer>>> allRequiredAbilities;
    private HashMap<String, ArrayList<Integer>> abilityLuckPercents;
    private HashMap<String, String> primaryVariableNames;
    private HashMap<String, ArrayList<Integer>> secondaryTargetShares;
    private HashMap<String, ArrayList<Integer>> allAbilityCooldowns;
    private ArrayList<String> instantEffectConditionAbilities;

    private ArrayList<String> shopItemNames;
    private HashMap<String, Integer> shopItemMoneyCosts;

    private ArrayList<String> gameStory;
    private ArrayList<ArrayList<String>> storyEnemyGroups;
    private ArrayList<Integer> enemyGroupXPs;
    private ArrayList<Integer> enemyGroupMoneys;


    public void checkCustom(Scanner in) {
        System.out.println("How do you want to start?(Enter the right number)");
        System.out.println("1- Start normal game");
        System.out.println("2- Start custom game(you can create your own game here)");

        String input = in.next();
        // Check wrong input
        if (input.equals("2")) {
            this.customed = true;
            startCreating(in);
        } else {
            this.customed = false;
            init();
        }
    }

    public void init() {
        //storyEnemyGroups
        ArrayList<String> storyEnemyGroups1 = new ArrayList<>();
        ArrayList<String> storyEnemyGroups2 = new ArrayList<>();
        ArrayList<String> storyEnemyGroups3 = new ArrayList<>();
        ArrayList<String> storyEnemyGroups4 = new ArrayList<>();
        ArrayList<String> storyEnemyGroups5 = new ArrayList<>();
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
        storyEnemyGroups4.add("mighty thug");
        storyEnemyGroups4.add("mighty thug");
        storyEnemyGroups4.add("able angel");
        storyEnemyGroups4.add("able tank");
        storyEnemyGroups4.add("able tank");
        storyEnemyGroups5.add("collector");

        storyEnemyGroups.add(storyEnemyGroups1);
        storyEnemyGroups.add(storyEnemyGroups2);
        storyEnemyGroups.add(storyEnemyGroups3);
        storyEnemyGroups.add(storyEnemyGroups4);
        storyEnemyGroups.add(storyEnemyGroups5);

        //enemyGroupXP
        enemyGroupXPs.add(20);
        enemyGroupXPs.add(25);
        enemyGroupXPs.add(30);
        enemyGroupXPs.add(35);
        //enemyGroupMoneys
        enemyGroupMoneys.add(50);
        enemyGroupMoneys.add(60);
        enemyGroupMoneys.add(70);
        enemyGroupMoneys.add(80);
        //gameStory
        gameStory.add("You’ve entered the castle, it takes a while for your eyes to get used to " +
                "the darkness but the horrifying halo of your enemies is vaguely visible. " +
                "Angel’s unsettling presence and the growling of thugs tell you that your first battle has BEGUN!");
        gameStory.add("As you wander into the hall you realize the surrounding doors can lead your" +
                " destiny to something far worse than you expected. You know what’s anticipating you" +
                " behind the only open door but there’s no other choice.");
        gameStory.add("The door behind you is shut with a thunderous sound and you progress into " +
                "the next hall holding the first key that you’ve found, hoping to seek the second one.");
        gameStory.add("Running with the second key in your hand, you unlock the door back to the first hall " +
                "and use the first key to burst into your most terrifying nightmares.");
        gameStory.add("You feel hopeless and exhausted as you stalk to the final door. What’s behind that door makes" +
                " your hearts pound and your spines shake with fear, but you came here to do one thing and backing down" +
                " is not an option.");
        gameStory.add("The collector falls down on his knees, he’s strained and desperate but still tries to drag himself" +
                " toward Epoch. He knows his era has come to an end. The ancient time machine calls you to end the disorder " +
                "and bring unity under its glorious wings, now it’s your turn to be the MASTERS OF TIME!");
        //shop
        shopItemNames.add("guide");
        shopItemNames.add("defy");
        shopItemNames.add("sword");
        shopItemNames.add("energyboots");
        shopItemNames.add("armor");
        shopItemNames.add("magicstick");
        shopItemNames.add("healthpotion");
        shopItemNames.add("magicpotion");
        shopItemNames.add("toughen");

        shopItemMoneyCosts.put("guide", 4);
        shopItemMoneyCosts.put("toughen", 4);
        shopItemMoneyCosts.put("defy", 4);
        shopItemMoneyCosts.put("sword", 25);
        shopItemMoneyCosts.put("energyboots", 20);
        shopItemMoneyCosts.put("armor", 25);
        shopItemMoneyCosts.put("magicstick", 28);
        shopItemMoneyCosts.put("healthpotion", 15);
        shopItemMoneyCosts.put("magicpotion", 15);
        //

        immortalityPotionNum = 3;
        initialMoney = 40;
        initialXP = 15;
        gameTurns = 5;
        shopInflationValue = 2;
        //
        heroClassNames.add("fighter");
        heroClassNames.add("supporter");
        HashMap<String, Integer> fighterData = new HashMap<>();
        fighterData.put("attack", 120);
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
        fighterAbilities.add("fightTraining");
        fighterAbilities.add("workOut");
        heroClassAbilities.put("fighter", fighterAbilities);

        /*************/

        HashMap<String, Integer> supporterData = new HashMap<>();
        supporterData.put("max health", 220);
        supporterData.put("current health", 220);
        supporterData.put("health refill", 5);
        supporterData.put("max magic", 200);
        supporterData.put("current magic", 200);
        supporterData.put("magic refill", 10);
        supporterData.put("attack", 80);
        supporterData.put("max EP", 5);
        supporterData.put("current EP", 5);
        heroClassDatas.put("supporter", supporterData);
        ArrayList<String> supporterAbilities = new ArrayList<>();
        supporterAbilities.add("quickAsBunny");
        supporterAbilities.add("magicLessons");
        heroClassAbilities.put("supporter", supporterAbilities);

        //khode hero ha
        herosAndTheirClasses.put("eley", "fighter");
        herosAndTheirClasses.put("chrome", "fighter");
        herosAndTheirClasses.put("meryl", "supporter");
        herosAndTheirClasses.put("bolti", "supporter");
        ArrayList<String> eleyAbilities = new ArrayList<>();
        ArrayList<String> chromeAbilities = new ArrayList<>();
        ArrayList<String> merylAbilities = new ArrayList<>();
        ArrayList<String> boltiAbilities = new ArrayList<>();
        eleyAbilities.add("overpoweredAttack");
        eleyAbilities.add("SwirlingAttack");
        chromeAbilities.add("sacrifice");
        chromeAbilities.add("criticalStrike");
        merylAbilities.add("elixir");
        merylAbilities.add("caretaker");
        boltiAbilities.add("boost");
        boltiAbilities.add("manaBeam");
        herosAndTheirAbilities.put("eley", eleyAbilities);
        herosAndTheirAbilities.put("chrome", chromeAbilities);
        herosAndTheirAbilities.put("meryl", merylAbilities);
        herosAndTheirAbilities.put("bolti", boltiAbilities);


        /*************/
        normalEnemyNames.add("thug");
        normalEnemyNames.add("angel");
        normalEnemyNames.add("tank");

        HashMap<String, Integer> weakThugData = new HashMap<>();
        weakThugData.put("attack", 50);
        weakThugData.put("max health", 200);

        HashMap<String, Integer> ableThugData = new HashMap<>();
        ableThugData.put("attack", 90);
        ableThugData.put("max health", 300);

        HashMap<String, Integer> mightyThugData = new HashMap<>();
        mightyThugData.put("attack", 150);
        mightyThugData.put("max health", 400);

        //ENEMYVERSION SHOULD HAVE ANOTHER PARAMETER IN ITS CONSTRUCTOR EXP: WEAKTHUGH---> weak , thug
        ArrayList<EnemyVersion> thugEnemyVersion = new ArrayList<>();
        thugEnemyVersion.add(new EnemyVersion("weak", "thug", weakThugData));
        thugEnemyVersion.add(new EnemyVersion("able", "thug", ableThugData));
        thugEnemyVersion.add(new EnemyVersion("mighty", "thug", mightyThugData));
        normalEnemyDatas.put("thug", thugEnemyVersion);

        /*************/

        HashMap<String, Integer> weakAngelData = new HashMap<>();
        weakThugData.put("heal", 100);
        weakThugData.put("max health", 150);

        HashMap<String, Integer> ableAngelData = new HashMap<>();
        ableThugData.put("heal", 150);
        ableThugData.put("max health", 250);

        ArrayList<EnemyVersion> angelEnemyVersion = new ArrayList<>();
        angelEnemyVersion.add(new EnemyVersion("weak", "angel", weakAngelData));
        angelEnemyVersion.add(new EnemyVersion("able", "angel", ableAngelData));
        normalEnemyDatas.put("angel", angelEnemyVersion);

        /*************/

        HashMap<String, Integer> weakTankData = new HashMap<>();
        weakThugData.put("attack", 30);
        weakThugData.put("max health", 400);

        HashMap<String, Integer> ableTankData = new HashMap<>();
        ableThugData.put("attack", 90);
        ableThugData.put("max health", 500);

        ArrayList<EnemyVersion> tankEnemyVersion = new ArrayList<>();
        tankEnemyVersion.add(new EnemyVersion("weak", "tank", weakTankData));
        tankEnemyVersion.add(new EnemyVersion("able", "tank", ableTankData));
        normalEnemyDatas.put("tank", tankEnemyVersion);

        /*************/

        //boss must be init

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
        itemTargets.put("healthpotion", "himself or an ally");
        itemTargets.put("magicpotion", "himself or an ally");
        //inflation Item
        inflationItems.add("toughen");
        inflationItems.add("guide");
        inflationItems.add("defy");
        //ItemData
        HashMap<String, Integer> toughenData = new HashMap<>();
        toughenData.put("max health", 20);
        toughenData.put("cost", 4);
        itemDatas.put("toughen", toughenData);

        HashMap<String, Integer> guideData = new HashMap<>();
        guideData.put("max magic", 20);
        guideData.put("cost", 4);
        itemDatas.put("guide", guideData);

        HashMap<String, Integer> defyData = new HashMap<>();
        defyData.put("attack", 8);
        defyData.put("cost", 4);
        itemDatas.put("defy", defyData);

        HashMap<String, Integer> swordData = new HashMap<>();
        swordData.put("attack", 80);
        swordData.put("cost", 25);
        itemDatas.put("sword", swordData);

        HashMap<String, Integer> energyBootsData = new HashMap<>();
        energyBootsData.put("current EP", 1);
        energyBootsData.put("cost", 20);
        itemDatas.put("energyboots", energyBootsData);

        HashMap<String, Integer> armorData = new HashMap<>();
        armorData.put("max health", 200);
        armorData.put("cost", 25);
        itemDatas.put("armor", armorData);

        HashMap<String, Integer> magicStickData = new HashMap<>();
        magicStickData.put("max magic", 150);
        magicStickData.put("cost", 28);
        itemDatas.put("magicStick", magicStickData);

        HashMap<String, Integer> healthPotionData = new HashMap<>();
        healthPotionData.put("current health", 100);
        healthPotionData.put("cost", 15);
        itemDatas.put("healthPotion", healthPotionData);

        HashMap<String, Integer> magicPotionData = new HashMap<>();
        magicPotionData.put("current magic", 50);
        magicPotionData.put("cost", 15);
        itemDatas.put("magicPotion", magicPotionData);

        //item targets and etc remains
        /*************/
        //ABILITIES
        //instant
        instantEffectConditionAbilities.add("fightTraining");
        instantEffectConditionAbilities.add("workOut");
        instantEffectConditionAbilities.add("quickAsBunny");
        instantEffectConditionAbilities.add("magicLessons");
        //names
        abilityNames.add("fightTraining");
        abilityNames.add("workOut");
        abilityNames.add("quickAsBunny");
        abilityNames.add("magicLessons");
        abilityNames.add("overPoweredAttack");
        abilityNames.add("swirlingAttack");
        abilityNames.add("sacrifice");
        abilityNames.add("criticalStrike");
        abilityNames.add("elixir");
        abilityNames.add("caretaker");
        abilityNames.add("boots");
        abilityNames.add("manaBeam");
        //targets
        abilityTargets.put("fightTraining", "himself");
        abilityTargets.put("workOut", "himself");
        abilityTargets.put("quickAsBunny", "himself");
        abilityTargets.put("magicLessons", "himself");
        abilityTargets.put("overPoweredAttack", "an enemy");//target is enemy
        abilityTargets.put("swirlingAttack", "an enemy");//target is a enemy but it has tasire janebi
        abilityTargets.put("sacrifice", "all enemy");
        abilityTargets.put("criticalStrike", "an enemy");
        abilityTargets.put("elixir", "himself or an ally");
        abilityTargets.put("caretaker", "an ally");
        abilityTargets.put("boots", "himself or an ally");
        abilityTargets.put("manaBeam", "himself or an ally");
        //upgradeXP
        ArrayList<Integer> fightTrainingXP = new ArrayList<>();
        fightTrainingXP.add(2);
        fightTrainingXP.add(3);
        fightTrainingXP.add(4);
        allAbilityUpgradeXPs.put("fightTraining", fightTrainingXP);

        ArrayList<Integer> workOutXP = new ArrayList<>();
        workOutXP.add(2);
        workOutXP.add(3);
        workOutXP.add(4);
        allAbilityUpgradeXPs.put("workOut", workOutXP);

        ArrayList<Integer> quickAsBunnyXP = new ArrayList<>();
        quickAsBunnyXP.add(2);
        quickAsBunnyXP.add(3);
        quickAsBunnyXP.add(4);
        allAbilityUpgradeXPs.put("quickAsBunny", quickAsBunnyXP);

        ArrayList<Integer> magicLessonsXP = new ArrayList<>();
        magicLessonsXP.add(2);
        magicLessonsXP.add(3);
        magicLessonsXP.add(4);
        allAbilityUpgradeXPs.put("magicLessons", magicLessonsXP);

        ArrayList<Integer> overPoweredAttackXP = new ArrayList<>();
        overPoweredAttackXP.add(2);
        overPoweredAttackXP.add(4);
        overPoweredAttackXP.add(6);
        allAbilityUpgradeXPs.put("overPoweredAttack", overPoweredAttackXP);

        ArrayList<Integer> swirlingAttackXP = new ArrayList<>();
        swirlingAttackXP.add(2);
        swirlingAttackXP.add(3);
        swirlingAttackXP.add(4);
        allAbilityUpgradeXPs.put("swirlingAttack", swirlingAttackXP);

        ArrayList<Integer> sacrificeXP = new ArrayList<>();
        sacrificeXP.add(2);
        sacrificeXP.add(3);
        sacrificeXP.add(4);
        allAbilityUpgradeXPs.put("sacrifice", sacrificeXP);

        ArrayList<Integer> criticalStrikeXP = new ArrayList<>();
        criticalStrikeXP.add(2);
        criticalStrikeXP.add(3);
        criticalStrikeXP.add(4);
        allAbilityUpgradeXPs.put("criticalStrike", criticalStrikeXP);

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
        allAbilityUpgradeXPs.put("manaBeam", manaBeamXP);
        //luckPercent

        ArrayList<Integer> criticalStrikePercent = new ArrayList<>();
        criticalStrikePercent.add(20);
        criticalStrikePercent.add(30);
        criticalStrikePercent.add(40);
        abilityLuckPercents.put("criticalStrike", criticalStrikePercent);

        //abilityRequired


        HashMap<String, Integer> overPoweredAttackUpgradeRequirement1 = new HashMap<>();
        overPoweredAttackUpgradeRequirement1.put("fightTraining", 1);
        HashMap<String, Integer> overPoweredAttackUpgradeRequirement2 = new HashMap<>();
        overPoweredAttackUpgradeRequirement2.put("fightTraining", 2);
        HashMap<String, Integer> overPoweredAttackUpgradeRequirement3 = new HashMap<>();
        overPoweredAttackUpgradeRequirement3.put("fightTraining", 3);
        ArrayList<HashMap<String, Integer>> overPoweredAttackUpgradeRequirements = new ArrayList<>();
        overPoweredAttackUpgradeRequirements.add(overPoweredAttackUpgradeRequirement1);
        overPoweredAttackUpgradeRequirements.add(overPoweredAttackUpgradeRequirement2);
        overPoweredAttackUpgradeRequirements.add(overPoweredAttackUpgradeRequirement3);
        allRequiredAbilities.put("overPoweredAttack", overPoweredAttackUpgradeRequirements);

        HashMap<String, Integer> swirlingAttackUpgradeRequirement1 = new HashMap<>();
        swirlingAttackUpgradeRequirement1.put("workOut", 1);
        ArrayList<HashMap<String, Integer>> swirlingAttackUpgradeRequirements = new ArrayList<>();
        swirlingAttackUpgradeRequirements.add(swirlingAttackUpgradeRequirement1);
        allRequiredAbilities.put("swirlingAttack", swirlingAttackUpgradeRequirements);

        HashMap<String, Integer> sacrificeUpgradeRequirement1 = new HashMap<>();
        sacrificeUpgradeRequirement1.put("workOut", 1);
        HashMap<String, Integer> sacrificeUpgradeRequirement2 = new HashMap<>();
        sacrificeUpgradeRequirement2.put("workOut", 2);
        HashMap<String, Integer> sacrificeUpgradeRequirement3 = new HashMap<>();
        sacrificeUpgradeRequirement3.put("workOut", 3);
        ArrayList<HashMap<String, Integer>> sacrificeUpgradeRequirements = new ArrayList<>();
        sacrificeUpgradeRequirements.add(sacrificeUpgradeRequirement1);
        sacrificeUpgradeRequirements.add(sacrificeUpgradeRequirement2);
        sacrificeUpgradeRequirements.add(sacrificeUpgradeRequirement3);
        allRequiredAbilities.put("sacrifice", sacrificeUpgradeRequirements);

        HashMap<String, Integer> criticalStrikeUpgradeRequirement1 = new HashMap<>();
        criticalStrikeUpgradeRequirement1.put("fightTraining", 1);
        ArrayList<HashMap<String, Integer>> criticalStrikeUpgradeRequirements = new ArrayList<>();
        criticalStrikeUpgradeRequirements.add(criticalStrikeUpgradeRequirement1);
        allRequiredAbilities.put("criticalStrike", criticalStrikeUpgradeRequirements);

        HashMap<String, Integer> elixirUpgradeRequirement1 = new HashMap<>();
        HashMap<String, Integer> elixirUpgradeRequirement2 = new HashMap<>();
        elixirUpgradeRequirement2.put("magicLessons", 1);
        HashMap<String, Integer> elixirUpgradeRequirement3 = new HashMap<>();
        elixirUpgradeRequirement3.put("magicLessons", 2);
        ArrayList<HashMap<String, Integer>> elixirUpgradeRequirements = new ArrayList<>();
        elixirUpgradeRequirements.add(elixirUpgradeRequirement1);
        elixirUpgradeRequirements.add(elixirUpgradeRequirement2);
        elixirUpgradeRequirements.add(elixirUpgradeRequirement3);
        allRequiredAbilities.put("sacrifice", elixirUpgradeRequirements);

        HashMap<String, Integer> caretakerUpgradeRequirement1 = new HashMap<>();
        caretakerUpgradeRequirement1.put("quickAsBunny", 1);
        HashMap<String, Integer> caretakerUpgradeRequirement2 = new HashMap<>();
        caretakerUpgradeRequirement2.put("quickAsBunny", 2);
        HashMap<String, Integer> caretakerUpgradeRequirement3 = new HashMap<>();
        caretakerUpgradeRequirement3.put("quickAsBunny", 3);
        ArrayList<HashMap<String, Integer>> caretakerUpgradeRequirements = new ArrayList<>();
        caretakerUpgradeRequirements.add(caretakerUpgradeRequirement1);
        caretakerUpgradeRequirements.add(caretakerUpgradeRequirement2);
        caretakerUpgradeRequirements.add(caretakerUpgradeRequirement3);
        allRequiredAbilities.put("caretaker", caretakerUpgradeRequirements);

        HashMap<String, Integer> manaBeamUpgradeRequirement1 = new HashMap<>();
        manaBeamUpgradeRequirement1.put("magicLessons", 1);
        HashMap<String, Integer> manaBeamUpgradeRequirement2 = new HashMap<>();
        manaBeamUpgradeRequirement2.put("magicLessons", 2);
        HashMap<String, Integer> manaBeamUpgradeRequirement3 = new HashMap<>();
        manaBeamUpgradeRequirement3.put("magicLessons", 3);
        ArrayList<HashMap<String, Integer>> manaBeamUpgradeRequirements = new ArrayList<>();
        manaBeamUpgradeRequirements.add(manaBeamUpgradeRequirement1);
        manaBeamUpgradeRequirements.add(manaBeamUpgradeRequirement2);
        manaBeamUpgradeRequirements.add(manaBeamUpgradeRequirement3);
        allRequiredAbilities.put("manaBeam", manaBeamUpgradeRequirements);
        //I think requirements are finished but Im not sure

        //secondaryTarget
        ArrayList<Integer> swirlingAttackNonTargetShareUpgrades = new ArrayList<>();
        swirlingAttackNonTargetShareUpgrades.add(10);
        swirlingAttackNonTargetShareUpgrades.add(20);
        swirlingAttackNonTargetShareUpgrades.add(30);
        secondaryTargetShares.put("swirlingAttack", swirlingAttackNonTargetShareUpgrades);

        //formula

        //private HashMap<String, HashMap<String, ArrayList<Formula>>> allAbiliyFormulas

        //fightTraining
        HashMap<String, ArrayList<Formula>> fightTrainingFormula = new HashMap<>();
        Formula fightTrainingEffectFormulaUpgrade1 = new Formula("attack + 30", null);
        Formula fightTrainingEffectFormulaUpgrade2 = new Formula("attack + 30", null);
        Formula fightTrainingEffectFormulaUpgrade3 = new Formula("attack + 30", null);
        ArrayList<Formula> fightTrainingEffectFormulaUpgrades = new ArrayList<>();
        fightTrainingEffectFormulaUpgrades.add(fightTrainingEffectFormulaUpgrade1);
        fightTrainingEffectFormulaUpgrades.add(fightTrainingEffectFormulaUpgrade2);
        fightTrainingEffectFormulaUpgrades.add(fightTrainingEffectFormulaUpgrade3);
        fightTrainingFormula.put("attack", fightTrainingEffectFormulaUpgrades);
        allAbiliyFormulas.put("fightTraining", fightTrainingFormula);

        //workOut
        HashMap<String, ArrayList<Formula>> workOutFormula = new HashMap<>();
        Formula workOutEffectFormulaUpgrade1 = new Formula("max health + 50", null);
        Formula workOutEffectFormulaUpgrade2 = new Formula("max health + 50", null);
        Formula workOutEffectFormulaUpgrade3 = new Formula("max health + 50", null);
        ArrayList<Formula> workOutEffectFormulaUpgrades = new ArrayList<>();
        workOutEffectFormulaUpgrades.add(workOutEffectFormulaUpgrade1);
        workOutEffectFormulaUpgrades.add(workOutEffectFormulaUpgrade2);
        workOutEffectFormulaUpgrades.add(workOutEffectFormulaUpgrade3);
        workOutFormula.put("max health", workOutEffectFormulaUpgrades);
        allAbiliyFormulas.put("workOut", workOutFormula);

        //quickAsBunny

        HashMap<String, ArrayList<Formula>> quickAsBunnyFormula = new HashMap<>();
        Formula quickAsBunnyEffectFormulaUpgrade1 = new Formula("EP + 1", null);
        Formula quickAsBunnyEffectFormulaUpgrade2 = new Formula("EP + 1", null);
        Formula quickAsBunnyEffectFormulaUpgrade3 = new Formula("EP + 1", null);
        ArrayList<Formula> quickAsBunnyEffectFormulaUpgrades = new ArrayList<>();
        quickAsBunnyEffectFormulaUpgrades.add(quickAsBunnyEffectFormulaUpgrade1);
        quickAsBunnyEffectFormulaUpgrades.add(quickAsBunnyEffectFormulaUpgrade2);
        quickAsBunnyEffectFormulaUpgrades.add(quickAsBunnyEffectFormulaUpgrade3);
        quickAsBunnyFormula.put("EP", quickAsBunnyEffectFormulaUpgrades);
        allAbiliyFormulas.put("quickAsBunny", quickAsBunnyFormula);


        //magicLessons

        HashMap<String, ArrayList<Formula>> magicLessonsFormula = new HashMap<>();
        Formula magicLessonsEffectFormulaUpgrade1 = new Formula("max magic + 50", null);
        Formula magicLessonsEffectFormulaUpgrade2 = new Formula("max magic + 50", null);
        Formula magicLessonsEffectFormulaUpgrade3 = new Formula("max magic + 50", null);
        ArrayList<Formula> magicLessonsEffectFormulaUpgrades = new ArrayList<>();
        magicLessonsEffectFormulaUpgrades.add(magicLessonsEffectFormulaUpgrade1);
        magicLessonsEffectFormulaUpgrades.add(magicLessonsEffectFormulaUpgrade2);
        magicLessonsEffectFormulaUpgrades.add(magicLessonsEffectFormulaUpgrade3);
        magicLessonsFormula.put("max magic", magicLessonsEffectFormulaUpgrades);
        allAbiliyFormulas.put("magicLessons", magicLessonsFormula);

        //overPoweredAttack
        HashMap<String, ArrayList<Formula>> overPoweredAttackFormula = new HashMap<>();
        Formula overPoweredAttackEffectFormulaUpgrade1 = new Formula("1.2 * attack", null);
        Formula overPoweredAttackEffectFormulaUpgrade2 = new Formula("1.4 * attack", null);
        Formula overPoweredAttackEffectFormulaUpgrade3 = new Formula("1.6 * attack", null);
        ArrayList<Formula> overPoweredAttackEffectFormulaUpgrades = new ArrayList<>();
        overPoweredAttackEffectFormulaUpgrades.add(overPoweredAttackEffectFormulaUpgrade1);
        overPoweredAttackEffectFormulaUpgrades.add(overPoweredAttackEffectFormulaUpgrade2);
        overPoweredAttackEffectFormulaUpgrades.add(overPoweredAttackEffectFormulaUpgrade3);
        ArrayList<Formula> overPoweredAttackCostFormula1 = new ArrayList<>();
        ArrayList<Formula> overPoweredAttackCostFormula2 = new ArrayList<>();
        Formula overPoweredAttackCostEP1 = new Formula("EP - 2", null);
        Formula overPoweredAttackCostEP2 = new Formula("EP - 2", null);
        Formula overPoweredAttackCostEP3 = new Formula("EP - 2", null);
        Formula overPoweredAttackCostMagic1 = new Formula("current magic - 50", null);
        Formula overPoweredAttackCostMagic2 = new Formula("current magic - 50", null);
        Formula overPoweredAttackCostMagic3 = new Formula("current magic - 50", null);
        overPoweredAttackCostFormula1.add(overPoweredAttackCostEP1);
        overPoweredAttackCostFormula1.add(overPoweredAttackCostEP2);
        overPoweredAttackCostFormula1.add(overPoweredAttackCostEP3);
        overPoweredAttackCostFormula2.add(overPoweredAttackCostMagic1);
        overPoweredAttackCostFormula2.add(overPoweredAttackCostMagic2);
        overPoweredAttackCostFormula2.add(overPoweredAttackCostMagic3);
        overPoweredAttackFormula.put("damage", overPoweredAttackEffectFormulaUpgrades);
        overPoweredAttackFormula.put("CostEp", overPoweredAttackCostFormula1);
        overPoweredAttackFormula.put("CostMagic", overPoweredAttackCostFormula2);
        allAbiliyFormulas.put("overPoweredAttack", overPoweredAttackFormula);


        //swirlingAttack----> hanu handle nashode

        //sacrifice

        HashMap<String, ArrayList<Formula>> sacrificeFormula = new HashMap<>();
        Formula sacrificeEffectFormulaUpgrade1 = new Formula("120", null);
        Formula sacrificeEffectFormulaUpgrade2 = new Formula("150", null);
        Formula sacrificeEffectFormulaUpgrade3 = new Formula("180", null);
        ArrayList<Formula> sacrificeEffectFormulaUpgrades = new ArrayList<>();
        sacrificeEffectFormulaUpgrades.add(sacrificeEffectFormulaUpgrade1);
        sacrificeEffectFormulaUpgrades.add(sacrificeEffectFormulaUpgrade2);
        sacrificeEffectFormulaUpgrades.add(sacrificeEffectFormulaUpgrade3);
        ArrayList<Formula> sacrificeCostFormula1 = new ArrayList<>();
        ArrayList<Formula> sacrificeCostFormula2 = new ArrayList<>();
        ArrayList<Formula> sacrificeCostFormula3 = new ArrayList<>();
        Formula sacrificeCostEP1 = new Formula("EP - 3", null);
        Formula sacrificeCostEP2 = new Formula("EP - 3", null);
        Formula sacrificeCostEP3 = new Formula("EP - 3", null);
        Formula sacrificeCostMagic1 = new Formula("current magic - 60", null);
        Formula sacrificeCostMagic2 = new Formula("current magic - 60", null);
        Formula sacrificeCostMagic3 = new Formula("current magic - 60", null);
        Formula sacrificeCostHealth1 = new Formula("current health - 40", null);
        Formula sacrificeCostHealth2 = new Formula("current health - 50", null);
        Formula sacrificeCostHealth3 = new Formula("current health - 60", null);
        sacrificeCostFormula1.add(sacrificeCostEP1);
        sacrificeCostFormula1.add(sacrificeCostEP2);
        sacrificeCostFormula1.add(sacrificeCostEP3);
        sacrificeCostFormula2.add(sacrificeCostMagic1);
        sacrificeCostFormula2.add(sacrificeCostMagic2);
        sacrificeCostFormula2.add(sacrificeCostMagic3);
        sacrificeCostFormula3.add(sacrificeCostHealth1);
        sacrificeCostFormula3.add(sacrificeCostHealth2);
        sacrificeCostFormula3.add(sacrificeCostHealth3);
        sacrificeFormula.put("damage", sacrificeEffectFormulaUpgrades);
        sacrificeFormula.put("CostEp", sacrificeCostFormula1);
        sacrificeFormula.put("CostMagic", sacrificeCostFormula2);
        sacrificeFormula.put("CostHealth", sacrificeCostFormula3);
        allAbiliyFormulas.put("sacrifice", sacrificeFormula);
        //coolDown
        ArrayList<Integer> sacrificeCooldownUpgrade = new ArrayList<>();
        sacrificeCooldownUpgrade.add(1);
        sacrificeCooldownUpgrade.add(1);
        sacrificeCooldownUpgrade.add(1);
        allAbilityCooldowns.put("sacrifice", sacrificeCooldownUpgrade);

        //criticalStrike---> hanu handle nashode

        //elixir
        HashMap<String, ArrayList<Formula>> elixirFormula = new HashMap<>();
        Formula elixirEffectFormulaUpgrade1 = new Formula("current health + 100", null);
        Formula elixirEffectFormulaUpgrade2 = new Formula("current health + 150", null);
        Formula elixirEffectFormulaUpgrade3 = new Formula("current health + 150", null);
        ArrayList<Formula> elixirEffectFormulaUpgrades = new ArrayList<>();
        elixirEffectFormulaUpgrades.add(elixirEffectFormulaUpgrade1);
        elixirEffectFormulaUpgrades.add(elixirEffectFormulaUpgrade2);
        elixirEffectFormulaUpgrades.add(elixirEffectFormulaUpgrade3);
        ArrayList<Formula> elixirCostFormula1 = new ArrayList<>();
        ArrayList<Formula> elixirCostFormula2 = new ArrayList<>();
        Formula elixirCostEP1 = new Formula("EP - 2", null);
        Formula elixirCostEP2 = new Formula("EP - 2", null);
        Formula elixirCostEP3 = new Formula("EP - 2", null);
        Formula elixirCostMagic1 = new Formula("current magic - 60", null);
        Formula elixirCostMagic2 = new Formula("current magic - 60", null);
        Formula elixirCostMagic3 = new Formula("current magic - 60", null);
        elixirCostFormula1.add(elixirCostEP1);
        elixirCostFormula1.add(elixirCostEP2);
        elixirCostFormula1.add(elixirCostEP3);
        elixirCostFormula2.add(elixirCostMagic1);
        elixirCostFormula2.add(elixirCostMagic2);
        elixirCostFormula2.add(elixirCostMagic3);
        elixirFormula.put("current health", elixirEffectFormulaUpgrades);
        elixirFormula.put("CostEp", elixirCostFormula1);
        elixirFormula.put("CostMagic", elixirCostFormula2);
        allAbiliyFormulas.put("elixir", elixirFormula);

        //coolDown
        ArrayList<Integer> elixirCooldownUpgrade = new ArrayList<>();
        elixirCooldownUpgrade.add(1);
        elixirCooldownUpgrade.add(1);
        elixirCooldownUpgrade.add(0);
        allAbilityCooldowns.put("elixir", elixirCooldownUpgrade);

        //caretaker
        HashMap<String, ArrayList<Formula>> caretakerFormula = new HashMap<>();
        Formula caretakerEffectFormulaUpgrade1 = new Formula("EP + 1", null);
        Formula caretakerEffectFormulaUpgrade2 = new Formula("EP + 1", null);
        Formula caretakerEffectFormulaUpgrade3 = new Formula("EP + 1", null);
        ArrayList<Formula> caretakerEffectFormulaUpgrades = new ArrayList<>();
        caretakerEffectFormulaUpgrades.add(caretakerEffectFormulaUpgrade1);
        caretakerEffectFormulaUpgrades.add(caretakerEffectFormulaUpgrade2);
        caretakerEffectFormulaUpgrades.add(caretakerEffectFormulaUpgrade3);
        ArrayList<Formula> caretakerCostFormula1 = new ArrayList<>();
        ArrayList<Formula> caretakerCostFormula2 = new ArrayList<>();
        Formula caretakerCostEP1 = new Formula("EP - 2", null);
        Formula caretakerCostEP2 = new Formula("EP - 2", null);
        Formula caretakerCostEP3 = new Formula("EP - 1", null);
        Formula caretakerCostMagic1 = new Formula("current magic - 30", null);
        Formula caretakerCostMagic2 = new Formula("current magic - 30", null);
        Formula caretakerCostMagic3 = new Formula("current magic - 30", null);
        caretakerCostFormula1.add(caretakerCostEP1);
        caretakerCostFormula1.add(caretakerCostEP2);
        caretakerCostFormula1.add(caretakerCostEP3);
        caretakerCostFormula2.add(caretakerCostMagic1);
        caretakerCostFormula2.add(caretakerCostMagic2);
        caretakerCostFormula2.add(caretakerCostMagic3);
        caretakerFormula.put("current EP", caretakerEffectFormulaUpgrades);
        caretakerFormula.put("CostEp", caretakerCostFormula1);
        caretakerFormula.put("CostMagic", caretakerCostFormula2);
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
        Formula boostEffectFormulaUpgrade1 = new Formula("temporary attack power + 20", null);
        Formula boostEffectFormulaUpgrade2 = new Formula("temporary attack power + 20", null);
        Formula boostEffectFormulaUpgrade3 = new Formula("temporary attack power + 20", null);
        ArrayList<Formula> boostEffectFormulaUpgrades = new ArrayList<>();
        boostEffectFormulaUpgrades.add(boostEffectFormulaUpgrade1);
        boostEffectFormulaUpgrades.add(boostEffectFormulaUpgrade2);
        boostEffectFormulaUpgrades.add(boostEffectFormulaUpgrade3);
        ArrayList<Formula> boostCostFormula1 = new ArrayList<>();
        ArrayList<Formula> boostCostFormula2 = new ArrayList<>();
        Formula boostCostEP1 = new Formula("EP - 2", null);
        Formula boostCostEP2 = new Formula("EP - 2", null);
        Formula boostCostEP3 = new Formula("EP - 2", null);
        Formula boostCostMagic1 = new Formula("current magic - 50", null);
        Formula boostCostMagic2 = new Formula("current magic - 50", null);
        Formula boostCostMagic3 = new Formula("current magic - 50", null);
        boostCostFormula1.add(boostCostEP1);
        boostCostFormula1.add(boostCostEP2);
        boostCostFormula1.add(boostCostEP3);
        boostCostFormula2.add(boostCostMagic1);
        boostCostFormula2.add(boostCostMagic2);
        boostCostFormula2.add(boostCostMagic3);
        boostFormula.put("temporary attack power", boostEffectFormulaUpgrades);
        boostFormula.put("CostEp", boostCostFormula1);
        boostFormula.put("CostMagic", boostCostFormula2);
        allAbiliyFormulas.put(" boost", boostFormula);
        //coolDown
        ArrayList<Integer> boostCooldownUpgrade = new ArrayList<>();
        boostCooldownUpgrade.add(1);
        boostCooldownUpgrade.add(1);
        boostCooldownUpgrade.add(0);
        allAbilityCooldowns.put(" boost", boostCooldownUpgrade);

        //manaBeam
        HashMap<String, ArrayList<Formula>> manaBeamFormula = new HashMap<>();
        Formula manaBeamEffectFormulaUpgrade1 = new Formula("current magic + 50", null);
        Formula manaBeamEffectFormulaUpgrade2 = new Formula("current magic + 50", null);
        Formula manaBeamEffectFormulaUpgrade3 = new Formula("current magic + 50", null);
        ArrayList<Formula> manaBeamEffectFormulaUpgrades = new ArrayList<>();
        manaBeamEffectFormulaUpgrades.add(boostEffectFormulaUpgrade1);
        manaBeamEffectFormulaUpgrades.add(boostEffectFormulaUpgrade2);
        manaBeamEffectFormulaUpgrades.add(boostEffectFormulaUpgrade3);
        ArrayList<Formula> manaBeamCostFormula1 = new ArrayList<>();
        ArrayList<Formula> manaBeamCostFormula2 = new ArrayList<>();
        Formula manaBeamCostEP1 = new Formula("EP - 1", null);
        Formula manaBeamCostEP2 = new Formula("EP - 1", null);
        Formula manaBeamCostEP3 = new Formula("EP - 1", null);
        Formula manaBeamCostMagic1 = new Formula("current magic - 50", null);
        Formula manaBeamCostMagic2 = new Formula("current magic - 50", null);
        Formula manaBeamCostMagic3 = new Formula("current magic - 50", null);
        manaBeamCostFormula1.add(manaBeamCostEP1);
        manaBeamCostFormula1.add(manaBeamCostEP2);
        manaBeamCostFormula1.add(manaBeamCostEP3);
        manaBeamCostFormula2.add(manaBeamCostMagic1);
        manaBeamCostFormula2.add(manaBeamCostMagic2);
        manaBeamCostFormula2.add(manaBeamCostMagic3);
        manaBeamFormula.put("current magic", manaBeamEffectFormulaUpgrades);
        manaBeamFormula.put("CostEp", manaBeamCostFormula1);
        manaBeamFormula.put("CostMagic", manaBeamCostFormula2);
        allAbiliyFormulas.put("manaBeam", manaBeamFormula);
        //coolDown
        ArrayList<Integer> manaBeamCooldownUpgrade = new ArrayList<>();
        manaBeamCooldownUpgrade.add(1);
        manaBeamCooldownUpgrade.add(1);
        manaBeamCooldownUpgrade.add(0);
        allAbilityCooldowns.put("manaBeam", manaBeamCooldownUpgrade);
        /*************/ //ability bye bye!

    }


    public void startCreating(Scanner in) 
	{
    System.out.println("Welcome!");
		while(true)
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
			if(input.equals("1"))
			{
				creatHeroClass(in);
			}
			else if(input.equals("2"))
			{
				createHero(in);
			}
			else if(input.equals("3"))
			{
				createNormalEnemy(in);
			}
			else if(input.equals("4"))
			{
				createBossEnemy(in);
			}
			else if(input.equals("5"))
			{
				createItem(in);
			}
			else if(input.equals("6"))
			{
				createAbility(in);
			}
			else if(input.equals("7"))
			{
				createShopData(in);
			}
			else if(input.equals("8"))
			{
				createStoryline(in);
			}
			
			System.out.println("Want to create anything else?(Enter the right number)");
			
			if(!yesNoQuestion(in))
			{
				break;
			}
		}
    }


    private void creatHeroClass(Scanner in) 
	{
        String heroClassName;
		HashMap<String, Integer> heroClassData = new HashMap<>();
		
		while(true)
		{
			System.out.print("Please enter the name of the class you want to make: ");
			heroClassName = in.next();
			
			System.out.println("Are you sure?(Enter the right number)");
			
			if(yesNoQuestion(in))
			{
				heroClassNames.add(heroClassName);
				break;
			}
		}
		
		System.out.println("Please enter the amount you want for each attribute");
		
		for(int i = 0; i < heroAttributes.size(); i++)
		{
			while(true)
			{
				String attributeName = heroAttributes.get(i);
				
				System.out.print(attributeName + ": ");
				
				String attributeAmount = in.next();
				if(attributeAmount.matches("[0-9]+") && attributeAmount.length() < 8)
				{
					int attributeAmountNum = Integer.parseInt(attributeAmount);
					
					heroClassData.put(attributeName, attributeAmountNum);
					break;
				}
				else
				{
					// Invalid Input
				}
			}
		}
		
		System.out.println("How many abilities do you want to give to this class?");
		//check invalid input
		
		int abilityNum = in.nextInt();
		
		System.out.println("Please enter the name of the abilities you want this class to have:");
		showAbilityNames();
		ArrayList<String> thisClassAbilities = new ArrayList<>();
		
		while(true)
		{
			String abilityName = in.next();
			if(abilityNames.contains(abilityName))
			{
				thisClassAbilities.add(abilityName);
			}
			else
			{
				//invalid input
				continue;
			}
			
			System.out.println("Do you want to add any other ability?(Enter the right number)");
			if(!yesNoQuestion(in))
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


    private void createHero(Scanner in) {
        String heroName, heroClassName;
        ArrayList<String> abilityList = new ArrayList<>();

        while (true) {
            System.out.print("Please enter the name of the hero you want to make: ");
            heroName = in.next();

            System.out.println("Are you sure?(Enter the right number)");

            if (yesNoQuestion(in)) {
                //heroClassNames.add(heroClassName);
                break;
            }
        }

        while (true) {
            System.out.println("Please choose a class for this hero: ");
            showHeroClasses();

            heroClassName = in.next();
            if (heroClassNames.contains(heroClassName)) {
                System.out.println("Are you sure?(Enter the right number)");

                if (yesNoQuestion(in)) {
                    herosAndTheirClasses.put(heroName, heroClassName);
                    break;
                }
            } else {
                // invalid input
            }
        }

        System.out.println("Please enter the name of the abilities you want this hero to have:");
        showAbilityNames();
        while (true) {
            String abilityName = in.next();
            if (abilityNames.contains(abilityName)) {
                abilityList.add(abilityName);
            } else {
                //invalid input
                continue;
            }

            System.out.println("Do you want to add any other ability?(Enter the right number)");
            if (!yesNoQuestion(in)) {
                break;
            }

            System.out.println("Please enter the name of the next ability you want this hero to have: ");
        }
        herosAndTheirAbilities.put(heroName, abilityList);

        System.out.println("Hero was made!");
    }


    private void createNormalEnemy(Scanner in) {
        String enemyName;
        ArrayList<EnemyVersion> enemyVersions = new ArrayList<>();

        while (true) {
            System.out.print("Please enter the name of the enemy you want to make: ");
            enemyName = in.next();

            System.out.println("Are you sure?(Enter the right number)");

            if (yesNoQuestion(in)) {
                normalEnemyNames.add(enemyName);
                break;
            }
        }

        System.out.println("How many versions do you want to make for this enemy?");
        //check invalid input
        int versionNum = in.nextInt();

        for (int i = 0; i < versionNum; i++) {
            EnemyVersion enemyVersion = makeEnemyVersion(in);
            enemyVersions.add(enemyVersion);

            System.out.println("Version added!");
        }
        normalEnemyDatas.put(enemyName, enemyVersions);

        System.out.println("Enemy was made!");
    }


    private void createBossEnemy(Scanner in) {
        String bossName;
        ArrayList<String> specialConditions = new ArrayList<>();
        ArrayList<String> earlySpecialEffects = new ArrayList<>();
        HashMap<String, Integer> bossData = new HashMap<>();

        while (true) {
            System.out.print("Please enter the name of the boss enemy you want to make: ");
            bossName = in.next();

            System.out.println("Are you sure?(Enter the right number)");

            if (yesNoQuestion(in)) {
                bossEnemyNames.add(bossName);
                break;
            }
        }

        System.out.println("Please enter the amount you want for each attribute");

        for (int i = 0; i < enemyAttributes.size(); i++) {
            while (true) {
                String attributeName = enemyAttributes.get(i); // It will be nice if it goes up

                System.out.print(attributeName + ": ");

                String attributeAmount = in.next();
                if (attributeAmount.matches("[0-9]+") && attributeAmount.length() < 8) {
                    int attributeAmountNum = Integer.parseInt(attributeAmount);
                    bossData.put(attributeName, attributeAmountNum);
                    break;
                } else {
                    // Invalid Input
                }
            }
        }

        bossEnemyDatas.put(bossName, bossData);

        // Must handle Condition and early effects
    }


    private void createItem(Scanner in) {
        String itemName, itemTarget;
        HashMap<String, Integer> itemData = new HashMap<>();

        while (true) {
            System.out.print("Please enter the name of the item you want to make: ");
            itemName = in.next();

            System.out.println("Are you sure?(Enter the right number)");

            if (yesNoQuestion(in)) {
                itemNames.add(itemName);
                break;
            }
        }

        while (true) {
            System.out.println("Please enter the name of the attribute that you want this item to affect it");
            showItemAttributes();
            String attributeName = in.next();

            if (!itemAttributes.contains(attributeName)) {
                // invalid input
                continue;
            }

            while (true) {
                System.out.println("Please enter the amount of effect: ");
                String attributeAmount = in.next();

                if (attributeAmount.matches("[0-9]+") && attributeAmount.length() < 8) {
                    int attributeAmountNum = Integer.parseInt(attributeAmount);
                    itemData.put(attributeName, attributeAmountNum);
                    break;
                } else {
                    // Invalid Input
                }
            }

            System.out.println("Do you want any other effect for this item?");

            if (!yesNoQuestion(in)) {
                break;
            }
        }

        while (true) {
            System.out.println("Please enter the target of this item");
            showPossibleItemTargets();
            itemTarget = in.next();

            if (itemTargets.containsKey(itemTarget)) {
                itemTargets.put(itemName, itemTarget);
                break;
            }

            //invalid input
        }

        System.out.println("Item was made!");
    }


    private void createAbility(Scanner in) {

    }


    private EnemyVersion makeEnemyVersion(Scanner in) {
        String versionName;
        HashMap<String, Integer> versionData = new HashMap<>();

        while (true) {
            System.out.print("Please enter the name of the version you want to make: ");
            versionName = in.next();

            System.out.println("Are you sure?(Enter the right number)");

            if (yesNoQuestion(in)) {
                break;
            }
        }

        System.out.println("Please enter the amount you want for each attribute");

        for (int i = 0; i < enemyAttributes.size(); i++) {
            while (true) {
                String attributeName = enemyAttributes.get(i); // It will be nice if it goes up

                System.out.print(attributeName + ": ");

                String attributeAmount = in.next();
                if (attributeAmount.matches("[0-9]+") && attributeAmount.length() < 8) {
                    int attributeAmountNum = Integer.parseInt(attributeAmount);

                    versionData.put(attributeName, attributeAmountNum);
                    break;
                } else {
                    // Invalid Input
                }
            }
        }

        EnemyVersion enemyVersion = new EnemyVersion(versionName, versionData);

        return enemyVersion;
    }


    private boolean yesNoQuestion(Scanner in) {
        System.out.println("1- Yes");
        System.out.println("2- No");
        while (true) {
            String input = in.next();
            if (input.equals("1")) {
                return true;
            } else if (input.equals("2")) {
                return false;
            }

            System.out.println("Invalid input! Please try again.");
        }
    }


    private void showAbilityNames() {
        for (int i = 0; i < abilityNames.size(); i++) {
            System.out.println(abilityNames.get(i));
        }
    }


    private void showHeroClasses() {
        for (String heroClassName : heroClassNames) {
            System.out.println(heroClassName);
        }
    }


    private void showItemAttributes() {
        for (int i = 0; i < itemAttributes.size(); i++) {
            System.out.println(itemAttributes.get(i));
        }
    }


    private void showPossibleItemTargets() {
        for (int i = 0; i < possibleItemTargets.size(); i++) {
            System.out.println(possibleItemTargets.get(i));
        }
    }


    public ArrayList<String> getGameStory() {
        return gameStory;
    }

    public void setGameStory(ArrayList<String> gameStory) {
        this.gameStory = gameStory;
    }

    public boolean isCustomed() {
        return customed;
    }

    public void setCustomed(boolean customed) {
        this.customed = customed;
    }

    public int getShopInflationValue() {
        return shopInflationValue;
    }

    public void setShopInflationValue(int shopInflationValue) {
        this.shopInflationValue = shopInflationValue;
    }

    public int getGameTurns() {
        return gameTurns;
    }

    public void setGameTurns(int gameTurns) {
        this.gameTurns = gameTurns;
    }

    public int getInitialXP() {
        return initialXP;
    }

    public void setInitialXP(int initialXP) {
        this.initialXP = initialXP;
    }

    public int getInitialMoney() {
        return initialMoney;
    }

    public void setInitialMoney(int initialMoney) {
        this.initialMoney = initialMoney;
    }

    public int getImmortalityPotionNum() {
        return immortalityPotionNum;
    }

    public void setImmortalityPotionNum(int immortalityPotionNum) {
        this.immortalityPotionNum = immortalityPotionNum;
    }

    public ArrayList<String> getHeroClassNames() {
        return heroClassNames;
    }

    public void setHeroClassNames(ArrayList<String> heroClassNames) {
        this.heroClassNames = heroClassNames;
    }

    public HashMap<String, HashMap<String, Integer>> getHeroClassDatas() {
        return heroClassDatas;
    }

    public void setHeroClassDatas(HashMap<String, HashMap<String, Integer>> heroClassDatas) {
        this.heroClassDatas = heroClassDatas;
    }

    public HashMap<String, ArrayList<String>> getHeroClassAbilities() {
        return heroClassAbilities;
    }

    public void setHeroClassAbilities(HashMap<String, ArrayList<String>> heroClassAbilities) {
        this.heroClassAbilities = heroClassAbilities;
    }

    public ArrayList<String> getHeroAttributes() {
        return heroAttributes;
    }

    public void setHeroAttributes(ArrayList<String> heroAttributes) {
        this.heroAttributes = heroAttributes;
    }

    public HashMap<String, String> getHerosAndTheirClasses() {
        return herosAndTheirClasses;
    }

    public void setHerosAndTheirClasses(HashMap<String, String> herosAndTheirClasses) {
        this.herosAndTheirClasses = herosAndTheirClasses;
    }

    public HashMap<String, ArrayList<String>> getHerosAndTheirAbilities() {
        return herosAndTheirAbilities;
    }

    public void setHerosAndTheirAbilities(HashMap<String, ArrayList<String>> herosAndTheirAbilities) {
        this.herosAndTheirAbilities = herosAndTheirAbilities;
    }

    public ArrayList<String> getNormalEnemyNames() {
        return normalEnemyNames;
    }

    public void setNormalEnemyNames(ArrayList<String> normalEnemyNames) {
        this.normalEnemyNames = normalEnemyNames;
    }

    public HashMap<String, ArrayList<EnemyVersion>> getNormalEnemyDatas() {
        return normalEnemyDatas;
    }

    public void setNormalEnemyDatas(HashMap<String, ArrayList<EnemyVersion>> normalEnemyDatas) {
        this.normalEnemyDatas = normalEnemyDatas;
    }

    public ArrayList<String> getEnemyAttributes() {
        return enemyAttributes;
    }

    public void setEnemyAttributes(ArrayList<String> enemyAttributes) {
        this.enemyAttributes = enemyAttributes;
    }

    public ArrayList<String> getBossEnemyNames() {
        return bossEnemyNames;
    }

    public void setBossEnemyNames(ArrayList<String> bossEnemyNames) {
        this.bossEnemyNames = bossEnemyNames;
    }

    public HashMap<String, HashMap<String, Integer>> getBossEnemyDatas() {
        return bossEnemyDatas;
    }

    public void setBossEnemyDatas(HashMap<String, HashMap<String, Integer>> bossEnemyDatas) {
        this.bossEnemyDatas = bossEnemyDatas;
    }

    public HashMap<String, ArrayList<String>> getBossEnemySpecialConditions() {
        return bossEnemySpecialConditions;
    }

    public void setBossEnemySpecialConditions(HashMap<String, ArrayList<String>> bossEnemySpecialConditions) {
        this.bossEnemySpecialConditions = bossEnemySpecialConditions;
    }

    public HashMap<String, ArrayList<String>> getBossEnemyEarlyTurnEffects() {
        return bossEnemyEarlyTurnEffects;
    }

    public void setBossEnemyEarlyTurnEffects(HashMap<String, ArrayList<String>> bossEnemyEarlyTurnEffects) {
        this.bossEnemyEarlyTurnEffects = bossEnemyEarlyTurnEffects;
    }

    public ArrayList<String> getItemNames() {
        return itemNames;
    }

    public void setItemNames(ArrayList<String> itemNames) {
        this.itemNames = itemNames;
    }

    public HashMap<String, HashMap<String, Integer>> getItemDatas() {
        return itemDatas;
    }

    public void setItemDatas(HashMap<String, HashMap<String, Integer>> itemDatas) {
        this.itemDatas = itemDatas;
    }

    public HashMap<String, String> getItemTargets() {
        return itemTargets;
    }

    public void setItemTargets(HashMap<String, String> itemTargets) {
        this.itemTargets = itemTargets;
    }

    public ArrayList<String> getItemAttributes() {
        return itemAttributes;
    }

    public void setItemAttributes(ArrayList<String> itemAttributes) {
        this.itemAttributes = itemAttributes;
    }

    public ArrayList<String> getPossibleItemTargets() {
        return possibleItemTargets;
    }

    public void setPossibleItemTargets(ArrayList<String> possibleItemTargets) {
        this.possibleItemTargets = possibleItemTargets;
    }

    public ArrayList<String> getInflationItems() {
        return inflationItems;
    }

    public void setInflationItems(ArrayList<String> inflationItems) {
        this.inflationItems = inflationItems;
    }

    public ArrayList<String> getInstantEffectItems() {
        return instantEffectItems;
    }

    public void setInstantEffectItems(ArrayList<String> instantEffectItems) {
        this.instantEffectItems = instantEffectItems;
    }

    public ArrayList<String> getAbilityNames() {
        return abilityNames;
    }

    public void setAbilityNames(ArrayList<String> abilityNames) {
        this.abilityNames = abilityNames;
    }

    public HashMap<String, HashMap<String, ArrayList<Formula>>> getAllAbiliyFormulas() {
        return allAbiliyFormulas;
    }

    public void setAllAbiliyFormulas(HashMap<String, HashMap<String, ArrayList<Formula>>> allAbiliyFormulas) {
        this.allAbiliyFormulas = allAbiliyFormulas;
    }

    public HashMap<String, String> getAbilityTargets() {
        return abilityTargets;
    }

    public void setAbilityTargets(HashMap<String, String> abilityTargets) {
        this.abilityTargets = abilityTargets;
    }

    public ArrayList<String> getPossibleAbilityTargets() {
        return possibleAbilityTargets;
    }

    public void setPossibleAbilityTargets(ArrayList<String> possibleAbilityTargets) {
        this.possibleAbilityTargets = possibleAbilityTargets;
    }

    public HashMap<String, ArrayList<Integer>> getAllAbilityUpgradeXPs() {
        return allAbilityUpgradeXPs;
    }

    public void setAllAbilityUpgradeXPs(HashMap<String, ArrayList<Integer>> allAbilityUpgradeXPs) {
        this.allAbilityUpgradeXPs = allAbilityUpgradeXPs;
    }

    public ArrayList<String> getAbilityAttributes() {
        return abilityAttributes;
    }

    public void setAbilityAttributes(ArrayList<String> abilityAttributes) {
        this.abilityAttributes = abilityAttributes;
    }

    public HashMap<String, ArrayList<HashMap<String, Integer>>> getAllRequiredAbilities() {
        return allRequiredAbilities;
    }

    public void setAllRequiredAbilities(HashMap<String, ArrayList<HashMap<String, Integer>>> allRequiredAbilities) {
        this.allRequiredAbilities = allRequiredAbilities;
    }

    public HashMap<String, ArrayList<Integer>> getAbilityLuckPercents() {
        return abilityLuckPercents;
    }

    public void setAbilityLuckPercents(HashMap<String, ArrayList<Integer>> abilityLuckPercents) {
        this.abilityLuckPercents = abilityLuckPercents;
    }

    public HashMap<String, String> getPrimaryVariableNames() {
        return primaryVariableNames;
    }

    public void setPrimaryVariableNames(HashMap<String, String> primaryVariableNames) {
        this.primaryVariableNames = primaryVariableNames;
    }

    public HashMap<String, ArrayList<Integer>> getSecondaryTargetShares() {
        return secondaryTargetShares;
    }

    public void setSecondaryTargetShares(HashMap<String, ArrayList<Integer>> secondaryTargetShares) {
        this.secondaryTargetShares = secondaryTargetShares;
    }

    public HashMap<String, ArrayList<Integer>> getAllAbilityCooldowns() {
        return allAbilityCooldowns;
    }

    public void setAllAbilityCooldowns(HashMap<String, ArrayList<Integer>> allAbilityCooldowns) {
        this.allAbilityCooldowns = allAbilityCooldowns;
    }

    public ArrayList<String> getInstantEffectConditionAbilities() {
        return instantEffectConditionAbilities;
    }

    public void setInstantEffectConditionAbilities(ArrayList<String> instantEffectConditionAbilities) {
        this.instantEffectConditionAbilities = instantEffectConditionAbilities;
    }

    public ArrayList<String> getShopItemNames() {
        return shopItemNames;
    }

    public void setShopItemNames(ArrayList<String> shopItemNames) {
        this.shopItemNames = shopItemNames;
    }

    public HashMap<String, Integer> getShopItemMoneyCosts() {
        return shopItemMoneyCosts;
    }

    public void setShopItemMoneyCosts(HashMap<String, Integer> shopItemMoneyCosts) {
        this.shopItemMoneyCosts = shopItemMoneyCosts;
    }

    public ArrayList<ArrayList<String>> getStoryEnemyGroups() {
        return storyEnemyGroups;
    }

    public void setStoryEnemyGroups(ArrayList<ArrayList<String>> storyEnemyGroups) {
        this.storyEnemyGroups = storyEnemyGroups;
    }

    public ArrayList<Integer> getEnemyGroupXPs() {
        return enemyGroupXPs;
    }

    public void setEnemyGroupXPs(ArrayList<Integer> enemyGroupXPs) {
        this.enemyGroupXPs = enemyGroupXPs;
    }

    public ArrayList<Integer> getEnemyGroupMoneys() {
        return enemyGroupMoneys;
    }

    public void setEnemyGroupMoneys(ArrayList<Integer> enemyGroupMoneys) {
        this.enemyGroupMoneys = enemyGroupMoneys;
    }
	
    public HashMap<String, Integer> getNonInstantEffectItemsUseLimit() {
        return nonInstantEffectItemsUseLimit;
    }

    public void setNonInstantEffectItemsUseLimit(HashMap<String, Integer> nonInstantEffectItemsUseLimit) {
        this.nonInstantEffectItemsUseLimit = nonInstantEffectItemsUseLimit;
    }
}