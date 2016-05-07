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

    private ArrayList<String> heroClassNames; // new it in the constructor
    private HashMap<String, HashMap<String, Integer>> heroClassDatas; // new it in the constructor
    private HashMap<String, String> heroClassAbilities;

    //private ArrayList<String> abilityNames;
    private ArrayList<String> heroAttributes;                         // must make this fully in the constructor

    private HashMap<String, String> herosAndTheirClasses;
    private HashMap<String, ArrayList<String>> herosAndTheirAbilities;

    private ArrayList<String> normalEnemyNames;
    private HashMap<String, ArrayList<EnemyVersion>> normalEnemyDatas;
    private ArrayList<String> enemyAttributes;                        // must make this fully in the constructor

    private ArrayList<String> bossEnemyNames;
    private HashMap<String, HashMap<String, Integer>> bossEnemyDatas;
    private HashMap<String, ArrayList<String>> bossEnemySpecialConditions;
    private HashMap<String, ArrayList<String>> bossEnemyEarlyTurnEffects;

    private ArrayList<String> itemNames;
    private HashMap<String, HashMap<String, Integer>> itemDatas;
    private HashMap<String, String> itemTargets;
    private ArrayList<String> itemAttributes;                         // must make this fully in the constructor
    private ArrayList<String> possibleItemTargets;                    // must make this fully in the constructor

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
    private HashMap<String, Boolean> instantEffectCondition;


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

        //khode hero ha mundan

        /*************/

        HashMap<String, Integer> weakThugData = new HashMap<>();
        weakThugData.put("attack", 50);
        weakThugData.put("max health", 200);

        HashMap<String, Integer> ableThugData = new HashMap<>();
        ableThugData.put("attack", 90);
        ableThugData.put("max health", 300);

        HashMap<String, Integer> mightyThugData = new HashMap<>();
        mightyThugData.put("attack", 150);
        mightyThugData.put("max health", 400);

        ArrayList<EnemyVersion> thugEnemyVersion = new ArrayList<>();
        thugEnemyVersion.add(new EnemyVersion("weakThug", weakThugData));
        thugEnemyVersion.add(new EnemyVersion("ableThug", ableThugData));
        thugEnemyVersion.add(new EnemyVersion("mightyThug", mightyThugData));
        normalEnemyDatas.put("thug", thugEnemyVersion);

        /*************/

        HashMap<String, Integer> weakAngelData = new HashMap<>();
        weakThugData.put("heal", 100);
        weakThugData.put("max health", 150);

        HashMap<String, Integer> ableAngelData = new HashMap<>();
        ableThugData.put("heal", 150);
        ableThugData.put("max health", 250);

        ArrayList<EnemyVersion> angelEnemyVersion = new ArrayList<>();
        angelEnemyVersion.add(new EnemyVersion("weakAngel", weakAngelData));
        angelEnemyVersion.add(new EnemyVersion("ableAngel", ableAngelData));
        normalEnemyDatas.put("angel", angelEnemyVersion);

        /*************/

        HashMap<String, Integer> weakTankData = new HashMap<>();
        weakThugData.put("attack", 30);
        weakThugData.put("max health", 400);

        HashMap<String, Integer> ableTankData = new HashMap<>();
        ableThugData.put("attack", 90);
        ableThugData.put("max health", 500);

        ArrayList<EnemyVersion> tankEnemyVersion = new ArrayList<>();
        tankEnemyVersion.add(new EnemyVersion("weakTank", weakTankData));
        tankEnemyVersion.add(new EnemyVersion("ableTank", ableTankData));
        normalEnemyDatas.put("tank", tankEnemyVersion);

        /*************/

        //boss must be init

        /*************/
        //Items
        //ItemData
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
        itemDatas.put("energyBoots", energyBootsData);

        HashMap<String, Integer> armorData = new HashMap<>();
        armorData.put("max health", 200);
        itemDatas.put("armor", armorData);

        HashMap<String, Integer> magicStickData = new HashMap<>();
        magicStickData.put("max magic", 150);
        itemDatas.put("magicStick", magicStickData);

        HashMap<String, Integer> healthPotionData = new HashMap<>();
        healthPotionData.put("current health", 100);
        itemDatas.put("healthPotion", healthPotionData);

        HashMap<String, Integer> magicPotionData = new HashMap<>();
        magicPotionData.put("current magic", 50);
        itemDatas.put("magicPotion", magicPotionData);

        //item targets and etc remains
        /*************/
        //ABILITIES
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

        //instant
        
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


        //swirlingAttack

        //sacrifice

        //criticalStrike

        //elixir

        //caretaker

        //boost

        //manaBeam

    }


    public void startCreating(Scanner in) {
        System.out.println("Welcome!");
        while (true) {
            System.out.println("What do you want to create?(Enter the right number)");
            System.out.println("1- Hero Class");
            System.out.println("2- Hero");
            System.out.println("3- Normal Enemy");
            System.out.println("4- Boss Enemy");
            System.out.println("5- Item");
            System.out.println("6- Ability");

            String input = in.next();
            if (input.equals("1")) {
                creatHeroClass(in);
            } else if (input.equals("2")) {
                createHero(in);
            } else if (input.equals("3")) {
                createNormalEnemy(in);
            } else if (input.equals("4")) {
                createBossEnemy(in);
            } else if (input.equals("5")) {
                createItem(in);
            } else if (input.equals("6")) {
                createAbility(in);
            }

            System.out.println("Want to create anything else?(Enter the right number)");

            if (!yesNoQuestion(in)) {
                break;
            }
        }
    }


    private void creatHeroClass(Scanner in) {
        String heroClassName;
        HashMap<String, Integer> heroClassData = new HashMap<>();

        while (true) {
            System.out.print("Please enter the name of the class you want to make: ");
            heroClassName = in.next();

            System.out.println("Are you sure?(Enter the right number)");

            if (yesNoQuestion(in)) {
                heroClassNames.add(heroClassName);
                break;
            }
        }

        System.out.println("Please enter the amount you want for each attribute");

        for (int i = 0; i < heroAttributes.size(); i++) {
            while (true) {
                String attributeName = heroAttributes.get(i);

                System.out.print(attributeName + ": ");

                String attributeAmount = in.next();
                if (attributeAmount.matches("[0-9]+") && attributeAmount.length() < 8) {
                    int attributeAmountNum = Integer.parseInt(attributeAmount);

                    heroClassData.put(attributeName, attributeAmountNum);
                    break;
                } else {
                    // Invalid Input
                }
            }
        }

        System.out.println("How many abilities do you want to give to this class?");
        //check invalid input

        int abilityNum = in.nextInt();

        System.out.println("Please enter the name of the abilities you want this class to have:");
        showAbilityNames();
        while (true) {
            String abilityName = in.next();
            if (abilityNames.contains(abilityName)) {
                heroClassAbilities.put(heroClassName, abilityName);
            } else {
                //invalid input
                continue;
            }

            System.out.println("Do you want to add any other ability?(Enter the right number)");
            if (!yesNoQuestion(in)) {
                break;
            }
        }

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
}