package Controller;

import Auxiliary.Formula;
import Model.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class GameScenario
{
    private UserInterface userInterface;

    private ArrayList<Warrior> warrior = new ArrayList<>();
    private Scanner scanner;
    private ArrayList<EnemyGroup> enemyGroups = new ArrayList<>();
    private ArrayList<Hero> heros = new ArrayList<>();
    private Shop shop;

    public GameScenario(UserInterface userInterface, Scanner in)
    {
        this.userInterface = userInterface;
        shop = new Shop(this.userInterface.getShopInflationValue(), this.userInterface.getShopItemMoneyCosts());

        scanner = in;
        ArrayList<String> heroNames = this.userInterface.getHeroNames();
        for (int i = 0; i < heroNames.size(); i++)
        {
            String heroName = heroNames.get(i);
            String heroClassName = this.userInterface.getHerosAndTheirClasses().get(heroName);
            HashMap<String, Integer> heroData = new HashMap<>();
            heroData.putAll(this.userInterface.getHeroClassDatas().get(heroClassName));
            int inventorySize = this.userInterface.getHeroClassInventorySizes().get(heroClassName);
            ArrayList<String> heroAbilities = new ArrayList<>();
            heroAbilities = this.userInterface.getHerosAndTheirAbilities().get(heroName);
            heroAbilities.addAll(this.userInterface.getHeroClassAbilities().get(heroClassName));
            ArrayList<Ability> abilities = new ArrayList<>();
            for (int j = 0; j < heroAbilities.size(); j++)
            {
                String abilityName = heroAbilities.get(j);
                String abilityTarget = this.userInterface.getAbilityTargets().get(abilityName);
                ArrayList<Integer> upgradeXP = new ArrayList<>();
                if (this.userInterface.getAllAbilityUpgradeXPs().get(abilityName) != null)
                {
                    upgradeXP = this.userInterface.getAllAbilityUpgradeXPs().get(abilityName);
                }
                ArrayList<Integer> luckPercents = new ArrayList<>();
                if (this.userInterface.getAbilityLuckPercents().get(abilityName) != null)
                {
                    luckPercents = this.userInterface.getAbilityLuckPercents().get(abilityName);
                }
                ArrayList<Integer> abilityCooldownNums = new ArrayList<>();
                if (this.userInterface.getAllAbilityCooldowns().get(abilityName) != null)
                {
                    abilityCooldownNums = this.userInterface.getAllAbilityCooldowns().get(abilityName);
                }
                ArrayList<HashMap<String, Integer>> requiredAbilities = new ArrayList<>();
                if (this.userInterface.getAllRequiredAbilities().get(abilityName) != null)
                {
                    requiredAbilities = this.userInterface.getAllRequiredAbilities().get(abilityName);
                }
                HashMap<String, ArrayList<Formula>> formulas = new HashMap<>();
                if (this.userInterface.getAllAbiliyFormulas().get(abilityName) != null)
                {
                    formulas.putAll(this.userInterface.getAllAbiliyFormulas().get(abilityName));
                }
                String tragets = this.userInterface.getAbilityTargets().get(abilityName); // The hell is this?
                boolean isInstantEffect = false;
                if (this.userInterface.getInstantEffectConditionAbilities().contains(abilityName))
                {
                    isInstantEffect = true;
                }

                if (!isInstantEffect) // is this correct
                {
                    abilities.add(new ActiveAbility(abilityName, abilityTarget, upgradeXP, luckPercents,
                            abilityCooldownNums, requiredAbilities, formulas));
                } else
                {
                    abilities.add(new PassiveAbility(abilityName, abilityTarget, upgradeXP, luckPercents,
                            abilityCooldownNums, requiredAbilities, formulas));
                }
            }
            Warrior hero = new Hero(heroName, heroClassName, heroData, inventorySize, abilities);
            warrior.add(hero);
            heros.add((Hero) hero);

            for (int j = 0; j < abilities.size(); j++)
            {
                abilities.get(j).setHero((Hero) hero);
            }
            ((Hero) hero).setInventory(new Inventory(this.userInterface.getHeroClassInventorySizes().get(((Hero) hero).getHeroClassName())));
        }

    }

    public void scenario()
    {
        Hero.setXP(userInterface.getInitialXP());
        Hero.setMoney(userInterface.getInitialMoney());
        Hero.setImmortalityPotionNum(userInterface.getImmortalityPotionNum());
        introduceHeros();//ok
        for (int i = 0; i < 5; i++)
        {
            enemyGroups.add(new EnemyGroup(createEnemies(i), userInterface.getEnemyGroupXPs().get(i), userInterface.getEnemyGroupMoneys().get(i), i));
        }
        for (int i = userInterface.getGameTurns() - 1; i < userInterface.getGameTurns(); i++)
        {

            tellStory(i);
            showEnemyData(i);
            startUpgrading();
            shopping();
            // System.out.println(normalEnemyFullNames(i));
            startFighting(i);
        }

        tellStory(userInterface.getGameTurns());

    }

    //target is needed in creatign enemy (consructor)
    private ArrayList<Enemy> createEnemies(int gameTurn)
    {
        ArrayList<Enemy> enemies = new ArrayList<>();
        ArrayList<String> enemiesVersionAndNames = userInterface.getStoryEnemyGroups().get(gameTurn);
        for (String enemiesVersionAndName : enemiesVersionAndNames)
        {
            if (enemiesVersionAndName.contains(" "))
            {
                if (userInterface.getNormalEnemyNames().contains(enemiesVersionAndName.split(" ")[1]) &&
                        userInterface.getEnemyVersionNames().get(enemiesVersionAndName.split(" ")[1]).contains(enemiesVersionAndName.split(" ")[0]))
                {
                    ArrayList<EnemyVersion> enemyVersions = userInterface.getNormalEnemyDatas().get(enemiesVersionAndName.split(" ")[1]);
                    HashMap<String, Integer> enemyVersionDatas = new HashMap<>();
                    String target = "";

                    for (EnemyVersion enemyVersion1 : enemyVersions)
                    {
                        if (enemyVersion1.getName().equals(enemiesVersionAndName.split(" ")[0]))
                        {
                            enemyVersionDatas.putAll(enemyVersion1.getData());
                            target = enemyVersion1.getTarget();
                            break;
                        }
                    }
                    Warrior enemy = new NormalEnemy(enemiesVersionAndName.split(" ")[1], enemiesVersionAndName.split(" ")[0], target, enemyVersionDatas);
                    enemies.add((Enemy) enemy);
                    warrior.add(enemy);

                }
            } else
            {
                //createBoss
                String target = userInterface.getBossEnemyTargets().get(enemiesVersionAndName);
                HashMap<String, Integer> data = userInterface.getBossEnemyDatas().get(enemiesVersionAndName);
                int angerPoint = userInterface.getBossEnemyAngerPoints().get(enemiesVersionAndName);
                HashMap<String, Integer> angerEffects = userInterface.getBossEnemyAngerAdditions().get(enemiesVersionAndName);
                HashMap<String, String> earlyTurnEffect = userInterface.getBossEnemyEarlyEffects().get(enemiesVersionAndName);

                Warrior bossEnemy = new BossEnemy(enemiesVersionAndName, target, angerPoint, angerEffects, earlyTurnEffect, data);
                enemies.add((Enemy) bossEnemy);
                warrior.add(bossEnemy);
            }
        }
        return enemies;
    }

    public void introduceHeros()//It is finished
    {
        introducingHeros(heros);
        String order = normalizer(this.scanner.nextLine());
        while (!order.equals("done"))
        {
            if (order.equals("help"))
            {
                System.out.println("(Class name) + ?\uF0E0 (class description)");
                System.out.println("(hero name) + ? \uF0E0 (hero description)");
            } else if (order.equals("again"))
            {
                introducingHeros(heros);
            } else
            {
                parseOrder(order, "introduce heros", 0);
            }
            order = normalizer(this.scanner.nextLine());
        }
    }

    private void introducingHeros(ArrayList<Hero> heros)//finished
    {
        for (int i = 0; i < heros.size() - 1; i++)
        {
            System.out.printf("%s (%s) -", heros.get(i).getName(), heros.get(i).getHeroClassName());
        }
        System.out.printf("%s (%s)\n", heros.get(heros.size() - 1).getName(), heros.get(heros.size() - 1).getHeroClassName());
    }


    public void tellStory(int storyPart)
    {
        String story = userInterface.getGameStory().get(storyPart);
        System.out.println(story);
    }


    public void showEnemyData(int gameTurn)
    {

        showingEnemyData(gameTurn);
        String order = normalizer(this.scanner.nextLine());
        while (!order.equals("done"))
        {
            if (order.equals("help"))
            {
                System.out.println("(enemy name) + ? \uF0E0 (enemy description)");
            } else if (order.equals("again"))
            {
                showingEnemyData(gameTurn);
            } else
            {
                parseOrder(order, "show enemy data", gameTurn);
            }
            order = normalizer(this.scanner.nextLine());
        }

    }

    //OK
    private ArrayList<String> normalEnemyFullNames(int gameTurn)
    {
        EnemyGroup enemyGroup;
        enemyGroup = this.enemyGroups.get(gameTurn);
        ArrayList<Enemy> enemies = enemyGroup.getEnemies();
        ArrayList<NormalEnemy> normalEnemies = new ArrayList<>();
        for (Enemy enemy : enemies)
        {
            if (enemy instanceof NormalEnemy)
            {
                normalEnemies.add((NormalEnemy) enemy);
            }

        }
        ArrayList<String> normalEnemiesNames = new ArrayList<>();
        for (NormalEnemy normalEnemy : normalEnemies)
        {
            if (normalEnemy.getID() == 0)
            {
                normalEnemiesNames.add(normalEnemy.getVersion() + "-" + normalEnemy.getName());
            } else
            {
                normalEnemiesNames.add(normalEnemy.getVersion() + "-" + normalEnemy.getName() + "-" + normalEnemy.getID());
            }
        }
        return normalEnemiesNames;
    }

    //OK
    private ArrayList<String> bossEnemyFullNames(int gameTurn)
    {
        EnemyGroup enemyGroup;
        enemyGroup = this.enemyGroups.get(gameTurn);
        ArrayList<Enemy> enemies = enemyGroup.getEnemies();
        ArrayList<BossEnemy> bossEnemies = new ArrayList<>();
        for (Enemy enemy : enemies)
        {
            if (enemy instanceof BossEnemy)
            {
                bossEnemies.add((BossEnemy) enemy);
            }
        }
        ArrayList<String> bossEnemiesNames = new ArrayList<>();
        for (BossEnemy bossEnemy : bossEnemies)
        {
            if (bossEnemy.getID() == 0)
            {
                bossEnemiesNames.add(bossEnemy.getName());
            } else
            {
                bossEnemiesNames.add(bossEnemy.getName() + bossEnemy.getID());
            }
        }
        return bossEnemiesNames;
    }

    private void showingEnemyData(int gameTurn)
    {
        EnemyGroup enemyGroup;
        enemyGroup = this.enemyGroups.get(gameTurn);
        ArrayList<Enemy> enemies = enemyGroup.getEnemies();
        ArrayList<NormalEnemy> normalEnemies = new ArrayList<>();
        ArrayList<BossEnemy> bossEnemies = new ArrayList<>();
        for (Enemy enemy : enemies)
        {
            if (enemy instanceof NormalEnemy)
            {
                normalEnemies.add((NormalEnemy) enemy);
            } else if (enemy instanceof BossEnemy)
            {
                bossEnemies.add((BossEnemy) enemy);
            }
        }
        ArrayList<String> normalEnemiesNames = new ArrayList<>();
        ArrayList<String> bossEnemiesNames = new ArrayList<>();
        normalEnemiesNames = normalEnemyFullNames(gameTurn);
        bossEnemiesNames = bossEnemyFullNames(gameTurn);

//        for (NormalEnemy normalEnemy : normalEnemies)
//        {
//            if (normalEnemy.getID() == 0)
//            {
//                normalEnemiesNames.add(normalEnemy.getVersion() + normalEnemy.getName());
//            } else
//            {
//                normalEnemiesNames.add(normalEnemy.getVersion() + normalEnemy.getName() + normalEnemy.getID());
//            }
//        }
//        for (BossEnemy bossEnemy : bossEnemies)
//        {
//            if (bossEnemy.getID() == 0)
//            {
//                bossEnemiesNames.add(bossEnemy.getName());
//            } else
//            {
//                bossEnemiesNames.add(bossEnemy.getName() + bossEnemy.getID());
//            }
//        }
        System.out.printf("You've encountered");
        for (String normalEnemiesName : normalEnemiesNames)
        {
            System.out.printf(" %s, ", normalEnemiesName);
        }
        for (String bossEnemiesName : bossEnemiesNames)
        {
            System.out.printf(" %s, ", bossEnemiesName);
        }
        System.out.printf("\n");
    }


    public void startUpgrading()
    {
        showHeroUpgrade();
        String order = normalizer(this.scanner.nextLine());
        while (!order.equals("done"))
        {
            if (order.equals("help"))
            {
                System.out.println("(hero name) +(ability name) + ?\uF0E0 (ability description)");
                System.out.println(" Acquire + (ability name) +  for  + (hero name)\uF0E0 (acquiring ability)");
            } else if (order.equals("again"))
            {
                showHeroUpgrade();
            } else
            {
                parseOrder(order, "start upgrading", 0);
            }
            order = normalizer(this.scanner.nextLine());
        }

    }

    private void showHeroUpgrade()
    {
        for (Hero hero : heros)
        {
//            System.out.println(hero.getName());
//            String[] heroTraits = new String[hero.getData().keySet().size()];
//            hero.getData().keySet().toArray(heroTraits);
//            for (String heroTrait : heroTraits)
//            {
//                System.out.printf("%s : %d\n", heroTrait, hero.getData().get(heroTrait));
//            }
            System.out.printf("%s\n", hero.getName());
            System.out.printf("Health: %d/%d\n", hero.getData().get("current health"), hero.getData().get("max health"));
            System.out.printf("Magic: %d/%d\n", hero.getData().get("current magic"), hero.getData().get("max magic"));
            System.out.printf("Energy Points: %d\n", hero.getData().get("current EP"));
            System.out.printf("Attak: %d\n", hero.getData().get("attack"));
            ArrayList<Ability> heroAbilities = new ArrayList<>();
            heroAbilities = hero.getAbilities();
            for (Ability heroAbility : heroAbilities)
            {
                if (heroAbility.getCurrentUpgradeNum() == 0)
                {
                    System.out.printf("%s not acquired\n", heroAbility.getName());

                } else
                {
                    System.out.printf("%s upgrade number: %d\n", heroAbility.getName(), heroAbility.getCurrentUpgradeNum());
                }
            }
        }
        System.out.printf("Your current experience is %d", Hero.getXP());
    }


    public void shopping()
    {
        showShopping();
        String order = normalizer(this.scanner.nextLine());
        while (!order.equals("done"))
        {
            if (order.equals("help"))
            {
                System.out.println("(item name) + ? \uF0E0 (item description)");
                System.out.println("Buy + (item name) +  for  + (hero name)\uF0E0 (buy item)");
                System.out.println("Sell + (item name) +  of + (hero name)\uF0E0 (sell item)");
            } else if (order.equals("again"))
            {
                showShopping();
            } else
            {
                parseOrder(order, "shopping", 0);
            }
            order = normalizer(this.scanner.nextLine());
        }
    }

    private void showShopping()
    {
        ArrayList<String> itemNames = userInterface.getShopItemNames();
        System.out.printf("This shop offers you \n");
        for (String itemName : itemNames)
        {
            System.out.printf("%s for %d dollars \n", itemName, shop.getItemNamesAndCost().get(itemName));
        }
        System.out.printf("\n");
        for (Hero hero : heros)
        {
            System.out.printf("%s ", hero.getName());
            ArrayList<Item> heroItems = new ArrayList<>();
            heroItems = hero.getInventory().getItems();
            for (Item heroItem : heroItems)
            {
                System.out.printf("has %s worth %d dollars \n ", heroItem.getName(), shop.getItemNamesAndCost().get(heroItem.getName()) / 2);
            }
            System.out.printf("\n");

        }
        System.out.printf("Your current wealth is %d :\n", Hero.getMoney());
    }

    public void startFighting(int gameTurn)
    {
        ArrayList<Enemy> enemies = enemyGroups.get(gameTurn).getEnemies();
        int xpAmount = userInterface.getEnemyGroupXPs().get(gameTurn);
        int moneyAmount = userInterface.getEnemyGroupMoneys().get(gameTurn);
        showStartFighting(gameTurn);

        while (enemies.size() != 0)
        {
            String order = normalizer(this.scanner.nextLine());
            ArrayList<Enemy> deadEnemies = new ArrayList<>();
            ArrayList<Hero> deadHeros = new ArrayList<>();
            while (!order.equals("done"))
            {
                if (order.equals("help"))
                {
                    System.out.println("(item name) + “?” \uF0E0 (item description)\n" +
                            "(ability name) + “?”\uF0E0 (ability description)\n" +
                            "(hero name) + “?” \uF0E0 (hero description)\n" +
                            "(enemy name) + “?”\uF0E0 (enemy description)\n" +
                            "(hero name) + “ cast “ + (ability name) + “ on “ + (hero name / enemy name and id)\uF0E0\n" +
                            "(ability success message)\n" +
                            "(hero name) + “ use “ + (item name) + “ on “ + (hero name / enemy name and id) \uF0E0\n" +
                            "(item success message)");
                    System.out.println("(hero name) + “ attack “ + (enemy name and id) \uF0E0");
                } else if (order.equals("again"))
                {
                    showStartFighting(gameTurn);
                } else
                {

                    parseOrder(order, "fighting", gameTurn);
                }
                order = normalizer(this.scanner.nextLine());
            }
            for (Enemy enemy : enemies)
            {
                if (enemy.getData().get("current health") <= 0)
                {
                    deadEnemies.add(enemy);
                }
            }
            enemies.removeAll(deadEnemies);

            for (Enemy enemy : enemies)
            {
                if (enemy instanceof BossEnemy)
                {
                    BossEnemy boss = (BossEnemy) enemy;
                    int currentHealth = boss.getData().get("current health");
                    int angerPoint = boss.getAngerPoint();
                    if (currentHealth <= angerPoint)
                    {
                        boss.getAngry();
                    }

                }
            }
            enemiesMakeMove(gameTurn);
        }

        Hero.setXP(Hero.getXP() + xpAmount);
        Hero.setMoney((Hero.getMoney() + moneyAmount));

        // print wanted lines
    }

    private void enemiesMakeMove(int gameTurn)
    {
        ArrayList<Enemy> enemies = enemyGroups.get(gameTurn).getEnemies();
        for (Enemy enemy : enemies)
        {
            if (enemy instanceof BossEnemy)
            {
                ((BossEnemy) enemy).earlyTurnEffect(heros);
            }
        }
        for (Enemy enemy : enemies)
        {

            enemy.startAMove(heros, enemies);

            for (Hero hero : heros)
            {
                if (hero.getData().get("current health") <= 0)
                {
                    if (Hero.getImmortalityPotionNum() == 0)
                    {
                        System.out.println(hero.getName() + " is dead and so is the spirit of this adventure, Game Over!");
                    } else
                    {
                        Hero.setImmortalityPotionNum(Hero.getImmortalityPotionNum() - 1);
                        reviveHero(hero);
                        System.out.println(hero.getName() + " is dying, immortality potion was used for reincarnation process, you now have “" +
                                 Hero.getImmortalityPotionNum()+ " immortality potions left");
                    }
                }
            }
        }
    }

    private void reviveHero(Hero hero)
    {
        HashMap<String, Integer> data = hero.getData();
        for (String attribute : data.keySet())
        {
            String[] attributeNameParts = attribute.split(" ");
            if (attributeNameParts[0].equals("current"))
            {
                attributeNameParts[0] = "max ";
                String attributeMax = "";
                for (String attributeNamePart : attributeNameParts)
                {
                    attributeMax += attributeNamePart;
                }
                int maxAmount = data.get(attributeMax);
                data.put(attribute, maxAmount);
            }
        }

        data.put("temp attack", 0);
    }

    //VAHIDCHECK
    private void showStartFighting(int gameTurn)
    {
        for (Hero hero : heros)
        {
            System.out.printf("%s\n", hero.getName());
            System.out.printf("Health: %d/%d\n", hero.getData().get("current health"), hero.getData().get("max health"));
            System.out.printf("Magic: %d/%d\n", hero.getData().get("current magic"), hero.getData().get("max magic"));
            System.out.printf("Energy Points: %d\n", hero.getData().get("current EP"));
            System.out.printf("Attak: %d\n", hero.getData().get("attack"));
            ArrayList<Ability> heroAbilities = hero.getAbilities();
            for (Ability heroAbility : heroAbilities)
            {
                if (heroAbility instanceof ActiveAbility)
                {
                    if (heroAbility.getCurrentUpgradeNum() > 0)
                    {
                        HashMap<String, ArrayList<Formula>> heroAbilityFormula = heroAbility.getFormulas();
                        System.out.println("can cast " + heroAbility.getName());
                        if (heroAbilityFormula.containsKey("cost EP"))
                        {
                            System.out.println("for " + -1 * heroAbilityFormula.get("cost EP").get(heroAbility.getCurrentUpgradeNum()).parseFormula(null) + " EP");
                        }
                        if (heroAbilityFormula.containsKey("cost magic"))
                        {
                            System.out.println("for " + -1 * heroAbilityFormula.get("cost magic").get(heroAbility.getCurrentUpgradeNum()).parseFormula(null) + " magic point");
                        }
                        if (heroAbility.getCooldownTurn() > 0)
                        {
                            System.out.printf("and %d turn cooldown\n", heroAbility.getCooldownTurn());
                        } else
                        {
                            System.out.printf("\n");
                        }
                    }
                }
            }
            ArrayList<Item> heroItems = hero.getInventory().getItems();
            for (Item heroItem : heroItems)
            {
                if (heroItem instanceof NonInstantEffectItem)
                    System.out.printf("Can use %s\n", heroItem.getName());
            }
        }
        EnemyGroup enemyGroup;
        enemyGroup = this.enemyGroups.get(gameTurn);
        ArrayList<Enemy> enemies = enemyGroup.getEnemies();
        ArrayList<NormalEnemy> normalEnemies = new ArrayList<>();
        ArrayList<BossEnemy> bossEnemies = new ArrayList<>();
        for (Enemy enemy : enemies)
        {
            if (enemy instanceof NormalEnemy)
            {
                normalEnemies.add((NormalEnemy) enemy);
            } else if (enemy instanceof BossEnemy)
            {
                bossEnemies.add((BossEnemy) enemy);
            }
        }
        ArrayList<String> normalEnemiesNames = normalEnemyFullNames(gameTurn);
        ArrayList<String> bossEnemiesNames = bossEnemyFullNames(gameTurn);
        for (int i = 0; i < normalEnemiesNames.size(); i++)
        {
            System.out.printf("%s Health: %d / %d", normalEnemiesNames.get(i), normalEnemies.get(i).getData().get("current health"), normalEnemies.get(i).getData().get("max health"));
        }
        System.out.println("\n");
        for (int i = 0; i < bossEnemiesNames.size(); i++)
        {
            System.out.println(bossEnemiesNames.get(i) + " Health: "+bossEnemies.get(i).getData().get("current health" + " / " + bossEnemies.get(i).getData().get("max health")));
        }
    }
    //VAHIDCHECK

    /***********/

    //it smallize all the characters
    public String normalizer(String input)
    {
        String result;
        result = input.toLowerCase();
        //result = result.replaceAll("\\s+","");
        return result;
    }

    //VAHIDCHECK
    public void parseOrder(String command, String situation, int gameTurn)
    {
        if (whatIsOrder(command, situation, gameTurn) != null)
        {
            switch (whatIsOrder(command, situation, gameTurn))
            {
                case heroAttack:
                    heroAttack(command, gameTurn);
                    break;
                case useItemEveryone:
                    useItemEveryone(command, gameTurn);
                    break;
                case useItemSpecific:
                    useItemSpecific(command, gameTurn);
                    break;
                case heroDescription:
                    heroDescription(command);
                    break;
                case abilityDescription:
                    abilityDescription(command);
                    break;
                case ItemDescription:
                    System.out.println("come and fuck...");
                    itemDescription(command);
                    break;
                case enemyDescription:
                    enemyDescription(command);
                    break;
                case heroClassDescription:
                    heroClassDescription(command);
                    break;
                case heroAbilityDescription:
                    heroAbilityDescription(command);
                    break;
                case itemBuy:
                    itemBuy(command);
                    break;
                case itemSell:
                    itemSell(command);
                    break;
                case heroCastAbilityOnEveryone:
                    heroCastEveryoneAbility(command, gameTurn);
                    break;
                case heroCastAbilityOnSpecieficTarget:
                    heroCastSpecieficAbility(command, gameTurn);
                    break;
                case acquireAbility:
                    aquireAbility(command);
                    break;

                default:
                    System.out.println("Fuck1");
                    System.out.println("Invalid command");
                    break;

            }
        } else
        {
            System.out.println("fuck2");
            System.out.println("Invalid command");
        }
    }
    //VAHIDCHECK

    private void useItemSpecific(String command, int gameTurn)
    {
        String[] commands = command.split(" ");
        Hero hero = null;
        for (Hero hero1 : heros)
        {
            if (hero1.getName().equals(commands[0]))
                hero = hero1;
        }
        Item item = null;
        if (hero != null)
        {
            item = hero.findItem(commands[2]);
        }
        if (hero != null && item != null)
        {
            if (item instanceof NonInstantEffectItem)
            {
                hero.useItem(item.getName(), enemyGroups.get(gameTurn).getEnemies(), heros, commands[4]);
            } else
            {
                System.out.println("You don't have this item");
            }
        } else if (hero == null)
        {
            System.out.println("fuck3");
            System.out.println("Invalid command");
        } else
        {
            System.out.println("You don’t have this item");
        }
    }

    private void useItemEveryone(String command, int gameTurn)
    {
        String[] commands = command.split(" ");
        Hero hero = null;
        for (Hero hero1 : heros)
        {
            if (hero1.getName().equals(commands[0]))
                hero = hero1;
        }
        Item item = null;
        if (hero != null)
        {
            item = hero.findItem(commands[2]);
        }
        if (hero != null && item != null)
        {
            if (item instanceof NonInstantEffectItem)
            {
                hero.useItem(item.getName(), enemyGroups.get(gameTurn).getEnemies(), heros, "everyone");
            } else
            {
                System.out.println("You don't have this item");
            }
        } else if (hero == null)
        {
            System.out.println("fuck 5");
            System.out.println("Invalid command");
        } else
        {
            System.out.println("You don’t have this item");
        }
    }

    private void aquireAbility(String command)
    {
        String[] commands = command.split(" ");
        Hero hero = null;
        for (Hero hero1 : heros)
        {
            if (hero1.getName().equals(commands[3]))
                hero = hero1;
        }
        if (hero != null && userInterface.getHerosAndTheirAbilities().get(hero.getName()).contains(commands[1]))
        {
            Ability ability = hero.findAbility(commands[1]);
            if (ability != null)
            {
                if (ability.isUpgradeValid())
                {
                    ability.setCurrentUpgradeNum(ability.getCurrentUpgradeNum() + 1);
                    if (ability instanceof PassiveAbility)
                    {
                        hero.useAbility(ability.getName(), new ArrayList<Enemy>(), heros, "himself");
                    }
                    correctCurrentAttributes(hero);
                    //bugable no
                    Hero.setXP(Hero.getXP() - ability.getUpgradeXPs().get(ability.getCurrentUpgradeNum() - 1));
                    if (ability.getCurrentUpgradeNum() == 1)
                        System.out.printf("%s acquired successfully, your current experience is: %d\n", ability.getName(), Hero.getXP());
                    else if (ability.getCurrentUpgradeNum() > 1)
                        System.out.printf("%s upgraded successfully, your current experience is: %d\n", ability.getName(), Hero.getXP());
                }
            }
        } else if (hero == null)
        {
            System.out.println("fuck 10");
            System.out.println("Invalid command");
        } else
        {
            System.out.println("This hero can not acquire this ability");
        }
    }

    private void correctCurrentAttributes(Hero hero)
    {
        HashMap<String, Integer> data = hero.getData();
        for (String attribute : data.keySet())
        {
            String[] attributeNameParts = attribute.split(" ");
            if (attributeNameParts[0].equals("current"))
            {
                attributeNameParts[0] = "max ";
                String attributeMax = "";
                for (String attributeNamePart : attributeNameParts)
                {
                    attributeMax += attributeNamePart;
                }
                int maxAmount = data.get(attributeMax);
                data.put(attribute, maxAmount);
            }
        }
    }

    private void heroCastEveryoneAbility(String command, int gameTurn)
    {
        String[] commands = command.split(" ");
        Hero hero = null;
        for (Hero hero1 : heros)
        {
            if (hero1.getName().equals(commands[0]))
                hero = hero1;
        }
        if (hero != null && userInterface.getHerosAndTheirAbilities().get(hero.getName()).contains(commands[2]))
        {
            Ability ability = hero.findAbility(commands[2]);
            if (ability != null)
            {
                hero.useAbility(ability.getName(), enemyGroups.get(gameTurn).getEnemies(), heros, "everyone");
            }
        } else if (hero == null)
        {
            System.out.println("fuck 10");
            System.out.println("Invalid command");
        } else
        {
            System.out.println("This hero can not cast this ability");
        }
    }

    private void heroCastSpecieficAbility(String command, int gameTurn)
    {
        String[] commands = command.split(" ");
        Hero hero = null;
        for (Hero hero1 : heros)
        {
            if (hero1.getName().equals(commands[0]))
                hero = hero1;
        }
        if (hero != null && userInterface.getHerosAndTheirAbilities().get(hero.getName()).contains(commands[2]) && commands[3].equals("on"))
        {
            Ability ability = hero.findAbility(commands[2]);
            if (ability != null)
            {
                hero.useAbility(ability.getName(), enemyGroups.get(gameTurn).getEnemies(), heros, commands[4]);
            }
        } else if (hero == null)
        {
            System.out.println("TRee");
            System.out.println("Invalid command");
        } else
        {
            System.out.println("This hero can not cast this ability");
        }
    }

    private void itemSell(String command)
    {
        String[] commands = command.split(" ");
        Hero hero = null;
        for (Hero hero1 : heros)
        {
            if (hero1.getName().equals(commands[3]))
                hero = hero1;
        }
        if (hero != null && hero.getInventory().getItem(commands[1]) != null)
        {
            shop.buy(hero, hero.getInventory().getItem(commands[1]));
        } else if (hero != null)
        {
            System.out.println(hero.getName() + "doesn't have" + commands[1]);
        } else
        {
            System.out.println("FUck you asshole");
            System.out.println("Invalid command");
        }
    }

    private void itemBuy(String command)
    {
        String[] commands = command.split(" ");
        Hero hero = null;
        for (Hero hero1 : heros)
        {
            if (hero1.getName().equals(commands[3]))
                hero = hero1;
        }
        Item item;
        if (userInterface.getInstantEffectItems().contains(commands[1]))
        {
            if (userInterface.getInflationedItems().contains(commands[1]))
            {
                item = new InflationedItem(commands[1], userInterface.getItemTargets().get(commands[1]), userInterface.getItemDatas().get(commands[1]));
            } else
            {
                item = new NonInflationedItem(commands[1], userInterface.getItemTargets().get(commands[1]), userInterface.getItemDatas().get(commands[1]));
            }
        } else
        {
            item = new NonInstantEffectItem(commands[1], userInterface.getItemTargets().get(commands[1]), userInterface.getNonInstantEffectItemsUseLimit().get(commands[1]), userInterface.getItemDatas().get(commands[1]));
        }
        shop.sell(hero, item);
        if (hero != null && item instanceof InstantEffectItem)
        {
            hero.useItem(item.getName(), new ArrayList<Enemy>(), heros, "himself");
        }
        correctCurrentAttributes(hero);

    }

    private void heroAbilityDescription(String command)
    {
        String[] commands = command.split(" ");
        Hero hero = null;
        for (Hero hero1 : heros)
        {
            if (hero1.getName().equals(commands[0]))
                hero = hero1;
        }
        if (hero != null)
        {
            if (userInterface.getHerosAndTheirAbilities().get(hero.getName()).contains(commands[1]))
            {
                switch (commands[1])
                {
                    case "fighttraining":
                        System.out.println("Fight training\n" +
                                "Permanently increases attack power\n" +
                                "Upgrade1: +30 attack power for 2 xp points\n" +
                                "Upgrade2: +30 attack power for 3 xp points\n" +
                                "Upgrade3: +30 attack power for 4 xp points");

                        break;
                    case "workout":
                        System.out.println("Work out\n" +
                                "Permanently increases maximum health\n" +
                                "Upgrade 1: +50 maximum health for 2 xp points\n" +
                                "Upgrade 2: +50 maximum health for 3 xp points\n" +
                                "Upgrade 3: +50 maximum health for 4 xp points");
                        break;
                    case "quickasabunny":
                        System.out.println("Quick as a bunny\n" +
                                "Permanently increases energy points\n" +
                                "Upgrade1: +1 energy point for 2 xp points\n" +
                                "Upgrade2: +1 energy point for 3 xp points\n" +
                                "Upgrade3: +1 energy point for 4 xp points");
                        break;
                    case "magiclessons":
                        System.out.println("Quick as a bunny\n" +
                                "Permanently increases energy points\n" +
                                "Upgrade1: +1 energy point for 2 xp points\n" +
                                "Upgrade2: +1 energy point for 3 xp points\n" +
                                "Upgrade3: +1 energy point for 4 xp points");
                        break;
                    case "overpoweredattack":
                        System.out.println("Overpowered attack\n" +
                                "Attacks an enemy with N times power for 2 energy points and 50 magic points\n" +
                                "Upgrade 1: N=1.2 for 2 xp points, needs Fight training upgrade 1\n" +
                                "Upgrade 2: N=1.4 for 4 xp points, needs Fight training upgrade 2\n" +
                                "Upgrade 3: N=1.6 for 6 xp points, needs Fight training upgrade 3");
                        break;
                    case "swirlingattack":
                        System.out.println("While attacking, non-targeted enemies also take P percent of its damage\n" +
                                "Upgrade 1: P=10 for 2 xp points, needs Work out upgrade 1\n" +
                                "Upgrade 2: P=20 for 3 xp points\n" +
                                "Upgrade 3: P=30 for 4 xp points");
                        break;
                    case "sacrifice":
                        System.out.println("Sacrifice\n" +
                                "Damages all the enemies with 3H power at the cost of H of his own health, needs 3 energy points, 60 magic points and has a 1 turn cooldown\n" +
                                "Upgrade 1: H=40 for 2 xp points, needs Work out upgrade 1\n" +
                                "Upgrade 2: H=50 for 3 xp points, needs Work out upgrade 2\n" +
                                "Upgrade 3: H=60 for 4 xp points, needs Work out upgrade 3");
                        break;
                    case "criticalstrike":
                        System.out.println("Has a permanent P percent chance of doing an attack with double power (does not affect other abilities)\n" +
                                "Upgrade 1: P=20 for 2 xp points, needs Fight training upgrade 1\n" +
                                "Upgrade 2: P=30 for 3 xp points\n" +
                                "Upgrade 3: P=40 for 4 xp points");
                        break;
                    case "elixir":
                        System.out.println("Elixir\n" +
                                "Refills H points of her own health or an ally’s, for 2 energy points and 60 magic points\n" +
                                "Upgrade 1: H=100 for 2 xp points and takes 1 turn to cool down\n" +
                                "Upgrade 2: H=150 for 3 xp points, takes 1 turn to cool down and needs Magic lessons upgrade 1\n" +
                                "Upgrade 3: H=150 for 5 xp points, cools down instantly and needs Magic lessons upgrade 2");
                        break;
                    case "caretaker":
                        System.out.println("Caretaker\n" +
                                "Gives 1 energy point to an ally for 30 magic points (this ep does not last until the end of the battle and is only usable during the current turn)\n" +
                                "Upgrade 1: takes 2 energy points and has a 1 turn cooldown for 2 xp points, needs Quick as a bunny upgrade 1\n" +
                                "Upgrade 2: takes 2 energy points and cools down instantly for 3 xp points, needs Quick as a bunny upgrade 2\n" +
                                "Upgrade 3 takes 1 energy point and cools down instantly for 5 xp points, needs Quick as a bunny upgrade 3");
                        break;
                    case "boots":
                        System.out.println("Boost\n" +
                                "Gives X bonus attack power to himself or an ally, which lasts till the end of the battle, for 2 energy points and 50 magic points (this bonus attack power can stack up)\n" +
                                "Upgrade 1: A=20 for 2 xp points and takes 1 turn to cool down\n" +
                                "Upgrade 2: A=30 for 3 xp points and takes 1 turn to cool down\n" +
                                "Upgrade 3: A=30 for 5 xp points and cools down instantly");
                        break;
                    case "manabeam":
                        System.out.println("Mana beam\n" +
                                "Gives M magic points to himself or an ally for 1 energy point and 50 magic points\n" +
                                "Upgrade 1: M=50 for 2 xp points and takes 1 turn to cool down, needs magic lessons upgrade 1\n" +
                                "Upgrade 2: M=80 for 3 xp points and takes 1 turn to cool down, needs magic lessons upgrade 2\n" +
                                "Upgrade 3: M=80 for 4 xp points and cools down instantly, needs magic lessons upgrade 3");
                        break;
                    default:
                        System.out.println("There is no ability with name " + commands[1]);
                        break;
                }
                if (hero.findAbility(commands[1]) != null)
                {
                    Ability ability = hero.findAbility(commands[1]);
                    System.out.println("Your upgrade is:" + " upgrade " + ability.getCurrentUpgradeNum());
                    System.out.println("You need " + ability.getUpgradeXPs().get(ability.getCurrentUpgradeNum()) + "XP point");
                }
            } else
            {
                System.out.println(hero.getName() + "doesn't have" + commands[1]);
            }
        }
    }

    private void heroClassDescription(String command)
    {
        String[] commands = command.split(" ");
        if (userInterface.getHeroClassNames().contains(commands[0]))
        {
            switch (commands[0])
            {
                case "fighter":
                    System.out.println("Fighter class:\n" +
                            "Maximum health: 200\n" +
                            "Health refill rate: 10 percent of maximum health\n" +
                            "Maximum magic: 120\n" +
                            "Magic refill rate: 5 percent of maximum magic\n" +
                            "Attack power: 120\n" +
                            "Energy points: 6\n" +
                            "Inventory size: 2");
                    break;
                case "supporter":
                    System.out.println("Maximum health: 220\n" +
                            "Health refill rate: 5 percent of maximum health\n" +
                            "Maximum magic: 200\n" +
                            "Magic refill rate: 10 percent of maximum magic\n" +
                            "Attack power: 80\n" +
                            "Energy points: 5\n" +
                            "Inventory size: 3");
            }
        } else
        {
            System.out.println("TREE OF LIFE");
            System.out.println("Invalid command");
        }
    }

    private void enemyDescription(String command)
    {
        String[] commands = command.split(" ");
        if (userInterface.getNormalEnemyNames().contains(commands[0]) || userInterface.getBossEnemyNames().contains(commands[0]))
        {
            switch (commands[0])
            {
                case "tank":
                    System.out.println("Tank:\n" +
                            "Attacks every one of your heroes in each turn\n" +
                            "Weak version: Attack Power=30, Maximum health=400\n" +
                            "Able version: Attack Power=90, Maximum health=500");
                    break;
                case "thug":
                    System.out.println("Thug:\n" +
                            "Attacks one of your heroes in each turn\n" +
                            "Weak version: Attack Power=50, Maximum health=200\n" +
                            "Able version: Attack Power=90, Maximum health=300\n" +
                            "Mighty version: Attack Power=150, Maximum health=400");
                    break;
                case "angel":
                    System.out.println("Angel:\n" +
                            "Heals one of her allies in each turn\n" +
                            "Weak version: Healing Amount=100, Maximum health=150\n" +
                            "Able version: Healing Amount =150, Maximum health=250");
                    break;
                case "thecollector":
                    System.out.println("Final Boss:\n" +
                            "Burns 2 to 4 energy points of each hero and attacks 2 of them in each turn\n" +
                            "Maximum health: 1000\n" +
                            "Attack power when his current health is higher than 400: 150\n" +
                            "Attack power when his current health is below 400: 250");
                    break;
            }
        } else
        {
            System.out.println("There is no enemy with name "+ commands[0]);
        }
    }

    private void itemDescription(String command)
    {
        String[] commands = command.split(" ");
        switch (commands[0])
        {
            case "toughen":
                System.out.println("+20 maximum health");
                break;
            case "guide":
                System.out.println("+20 maximum magic");
                break;
            case "defy":
                System.out.println("+8 attack power");
                break;
            case "sword":
                System.out.println("+80 attack power, costs 25 dollars");
                break;
            case "energyboots":
                System.out.println("+1 energy point, costs 20 dollars");
                break;
            case "armor":
                System.out.println("+200 maximum health, costs 25 dollars");
                break;
            case "magicstick":
                System.out.println("+150 maximum magic, costs 28 dollars");
                break;
            case "healthpotion":
                System.out.println("+100 health points for the user or one of his/her allies, costs 15 dollars");
                break;
            case "magicpotion":
                System.out.println("+50 magic points for the user or one of his/her allies, costs 15 dollars");
                break;
            default:
                System.out.println("HOLYSHIt");
                System.out.println("Invalid command");
                break;
        }
    }

    private void abilityDescription(String command)
    {
        String[] commands = command.split(" ");
        switch (commands[0])
        {
            case "fighttraining":
                System.out.println("Fight training\n" +
                        "Permanently increases attack power\n" +
                        "Upgrade1: +30 attack power for 2 xp points\n" +
                        "Upgrade2: +30 attack power for 3 xp points\n" +
                        "Upgrade3: +30 attack power for 4 xp points");

                break;
            case "worout":
                System.out.println("Work out\n" +
                        "Permanently increases maximum health\n" +
                        "Upgrade 1: +50 maximum health for 2 xp points\n" +
                        "Upgrade 2: +50 maximum health for 3 xp points\n" +
                        "Upgrade 3: +50 maximum health for 4 xp points");
                break;
            case "quickasabunny":
                System.out.println("Quick as a bunny\n" +
                        "Permanently increases energy points\n" +
                        "Upgrade1: +1 energy point for 2 xp points\n" +
                        "Upgrade2: +1 energy point for 3 xp points\n" +
                        "Upgrade3: +1 energy point for 4 xp points");
                break;
            case "magiclessons":
                System.out.println("Quick as a bunny\n" +
                        "Permanently increases energy points\n" +
                        "Upgrade1: +1 energy point for 2 xp points\n" +
                        "Upgrade2: +1 energy point for 3 xp points\n" +
                        "Upgrade3: +1 energy point for 4 xp points");
                break;
            case "overpoweredattack":
                System.out.println("Overpowered attack\n" +
                        "Attacks an enemy with N times power for 2 energy points and 50 magic points\n" +
                        "Upgrade 1: N=1.2 for 2 xp points, needs Fight training upgrade 1\n" +
                        "Upgrade 2: N=1.4 for 4 xp points, needs Fight training upgrade 2\n" +
                        "Upgrade 3: N=1.6 for 6 xp points, needs Fight training upgrade 3");
                break;
            case "swirlingattack":
                System.out.println("While attacking, non-targeted enemies also take P percent of its damage\n" +
                        "Upgrade 1: P=10 for 2 xp points, needs Work out upgrade 1\n" +
                        "Upgrade 2: P=20 for 3 xp points\n" +
                        "Upgrade 3: P=30 for 4 xp points");
                break;
            case "sacrifice":
                System.out.println("Sacrifice\n" +
                        "Damages all the enemies with 3H power at the cost of H of his own health, needs 3 energy points, 60 magic points and has a 1 turn cooldown\n" +
                        "Upgrade 1: H=40 for 2 xp points, needs Work out upgrade 1\n" +
                        "Upgrade 2: H=50 for 3 xp points, needs Work out upgrade 2\n" +
                        "Upgrade 3: H=60 for 4 xp points, needs Work out upgrade 3");
                break;
            case "criticalStrike":
                System.out.println("Has a permanent P percent chance of doing an attack with double power (does not affect other abilities)\n" +
                        "Upgrade 1: P=20 for 2 xp points, needs Fight training upgrade 1\n" +
                        "Upgrade 2: P=30 for 3 xp points\n" +
                        "Upgrade 3: P=40 for 4 xp points");
                break;
            case "elixir":
                System.out.println("Elixir\n" +
                        "Refills H points of her own health or an ally’s, for 2 energy points and 60 magic points\n" +
                        "Upgrade 1: H=100 for 2 xp points and takes 1 turn to cool down\n" +
                        "Upgrade 2: H=150 for 3 xp points, takes 1 turn to cool down and needs Magic lessons upgrade 1\n" +
                        "Upgrade 3: H=150 for 5 xp points, cools down instantly and needs Magic lessons upgrade 2");
                break;
            case "caretaker":
                System.out.println("Caretaker\n" +
                        "Gives 1 energy point to an ally for 30 magic points (this ep does not last until the end of the battle and is only usable during the current turn)\n" +
                        "Upgrade 1: takes 2 energy points and has a 1 turn cooldown for 2 xp points, needs Quick as a bunny upgrade 1\n" +
                        "Upgrade 2: takes 2 energy points and cools down instantly for 3 xp points, needs Quick as a bunny upgrade 2\n" +
                        "Upgrade 3 takes 1 energy point and cools down instantly for 5 xp points, needs Quick as a bunny upgrade 3");
                break;
            case "boots":
                System.out.println("Boost\n" +
                        "Gives X bonus attack power to himself or an ally, which lasts till the end of the battle, for 2 energy points and 50 magic points (this bonus attack power can stack up)\n" +
                        "Upgrade 1: A=20 for 2 xp points and takes 1 turn to cool down\n" +
                        "Upgrade 2: A=30 for 3 xp points and takes 1 turn to cool down\n" +
                        "Upgrade 3: A=30 for 5 xp points and cools down instantly");
                break;
            case "manabeam":
                System.out.println("Mana beam\n" +
                        "Gives M magic points to himself or an ally for 1 energy point and 50 magic points\n" +
                        "Upgrade 1: M=50 for 2 xp points and takes 1 turn to cool down, needs magic lessons upgrade 1\n" +
                        "Upgrade 2: M=80 for 3 xp points and takes 1 turn to cool down, needs magic lessons upgrade 2\n" +
                        "Upgrade 3: M=80 for 4 xp points and cools down instantly, needs magic lessons upgrade 3");
                break;
            default:
                System.out.println("There is no ability with name" + commands[0]);
                break;
        }
    }

    private void heroDescription(String command)
    {
        String[] commands = command.split(" ");
        switch (commands[0])
        {
            case "eley":
                System.out.println("Eley:\n" +
                        "Class: Fighter\n" +
                        "Ability 3: Overpowered attack\n" +
                        "Attacks an enemy with N times power for 2 energy points and 50 magic points\n" +
                        "Upgrade 1: N=1.2 for 2 xp points, needs Fight training upgrade 1\n" +
                        "Upgrade 2: N=1.4 for 4 xp points, needs Fight training upgrade 2\n" +
                        "Upgrade 3: N=1.6 for 6 xp points, needs Fight training upgrade 3\n" +
                        "Success message: “Eley just did an overpowered attack on “ + (target) + “ with “ + (damage done) + “ damage”\n" +
                        "Ability 4: Swirling attack\n" +
                        "While attacking, non-targeted enemies also take P percent of its damage\n" +
                        "Upgrade 1: P=10 for 2 xp points, needs Work out upgrade 1\n" +
                        "Upgrade 2: P=20 for 3 xp points\n" +
                        "Upgrade 3: P=30 for 4 xp points");
                break;
            case "chrome":
                System.out.println("Chrome:\n" +
                        "Class: Fighter\n" +
                        "Ability 3: Sacrifice\n" +
                        "Damages all the enemies with 3H power at the cost of H of his own health, needs 3 energy points, 60 magic points and has a 1 turn cooldown\n" +
                        "Upgrade 1: H=40 for 2 xp points, needs Work out upgrade 1\n" +
                        "Upgrade 2: H=50 for 3 xp points, needs Work out upgrade 2\n" +
                        "Upgrade 3: H=60 for 4 xp points, needs Work out upgrade 3\n" +
                        "Success message: “Chrome just sacrificed himself to damage all his enemies with “ + (damage done) + “ power“\n" +
                        "Ability 4: Critical strike\n" +
                        "Has a permanent P percent chance of doing an attack with double power (does not affect other abilities)\n" +
                        "Upgrade 1: P=20 for 2 xp points, needs Fight training upgrade 1\n" +
                        "Upgrade 2: P=30 for 3 xp points\n" +
                        "Upgrade 3: P=40 for 4 xp points");
                break;
            case "meryl":
                System.out.println("Meryl:\n" +
                        "Class: Supporter\n" +
                        "Ability 3: Elixir\n" +
                        "Refills H points of her own health or an ally’s, for 2 energy points and 60 magic points\n" +
                        "Upgrade 1: H=100 for 2 xp points and takes 1 turn to cool down\n" +
                        "Upgrade 2: H=150 for 3 xp points, takes 1 turn to cool down and needs Magic lessons upgrade 1\n" +
                        "Upgrade 3: H=150 for 5 xp points, cools down instantly and needs Magic lessons upgrade 2\n" +
                        "Success message: “Meryl just healed “ + (target) + “ with “ + (healing amount) + “ health points”\n" +
                        "Ability 4: Caretaker\n" +
                        "Gives 1 energy point to an ally for 30 magic points (this ep does not last until the end of the battle and is only usable during the current turn)\n" +
                        "Upgrade 1: takes 2 energy points and has a 1 turn cooldown for 2 xp points, needs Quick as a bunny upgrade 1\n" +
                        "Upgrade 2: takes 2 energy points and cools down instantly for 3 xp points, needs Quick as a bunny upgrade 2\n" +
                        "Upgrade 3 takes 1 energy point and cools down instantly for 5 xp points, needs Quick as a bunny upgrade 3\n" +
                        "Success message: “Meryl just gave “ + (target) + “ 1 energy point”");
                break;
            case "bolti":
                System.out.println("Bolti:\n" +
                        "Class: Supporter\n" +
                        "Ability 3: Boost\n" +
                        "Gives X bonus attack power to himself or an ally, which lasts till the end of the battle, for 2 energy points and 50 magic points (this bonus attack power can stack up)\n" +
                        "Upgrade 1: A=20 for 2 xp points and takes 1 turn to cool down\n" +
                        "Upgrade 2: A=30 for 3 xp points and takes 1 turn to cool down\n" +
                        "Upgrade 3: A=30 for 5 xp points and cools down instantly\n" +
                        "Success message: “Bolti just boosted “ + (target) + “ with “ + (A) + “ power”\n" +
                        "Ability 4: Mana beam\n" +
                        "Gives M magic points to himself or an ally for 1 energy point and 50 magic points\n" +
                        "Upgrade 1: M=50 for 2 xp points and takes 1 turn to cool down, needs magic lessons upgrade 1\n" +
                        "Upgrade 2: M=80 for 3 xp points and takes 1 turn to cool down, needs magic lessons upgrade 2\n" +
                        "Upgrade 3: M=80 for 4 xp points and cools down instantly, needs magic lessons upgrade 3\n" +
                        "Success message: “Bolti just helped “ + (target) + “ with “ + (M) + “ magic points”");
                break;
            default:
                System.out.println("WHAT THE fuck WE are doing now");
                System.out.println("invalid command");
                break;
        }
    }

    private void heroAttack(String command, int gameTurn)
    {
        String[] commands = command.split(" ");
        Hero hero = null;
        for (Hero hero1 : heros)
        {
            if (hero1.getName().equals(commands[0]))
                hero = hero1;
        }
        ArrayList<Enemy> enemies = enemyGroups.get(gameTurn).getEnemies();
        ArrayList<String> normalEnemyFullName = normalEnemyFullNames(gameTurn);
        ArrayList<String> bossEnemyFullName = bossEnemyFullNames(gameTurn);
        if (hero != null && commands[1].equals("attack") && (normalEnemyFullName.contains(commands[2]) ||
                bossEnemyFullName.contains(commands[2])))
        {
            hero.regularAttack(enemies, commands[2]);
        } else
        {
            System.out.println("NZOOOO");
            System.out.println("Invalid command");
        }
    }

    private commandsOrder whatIsOrder(String command, String situation, int gameTurn)
    {
        String[] commands = command.split(" ");
        if (commands.length == 4 && commands[0].equals("acquire") && checkBuyAbility(command) && situation.equals("start upgrading"))
        {
            return commandsOrder.acquireAbility;
        } else if (commands.length == 4 && commands[0].equals("sell") && checkSellItem(command) && situation.equals("shopping"))
        {
            return commandsOrder.itemSell;
        } else if (commands.length == 4 && commands[0].equals("buy") && checkBuyItem(command) && situation.equals("shopping"))
        {
            return commandsOrder.itemBuy;
        } else if (commands.length == 3 && commands[1].equals("cast") && checkCastEveryoneAbility(command, gameTurn) && situation.equals("fighting"))
        {
            return commandsOrder.heroCastAbilityOnEveryone;
        } else if (commands.length == 5 && commands[1].equals("cast") && checkCastSpecieficAbility(command, gameTurn) && situation.equals("fighting"))
        {
            return commandsOrder.heroCastAbilityOnSpecieficTarget;
        } else if (commands.length == 3 && commands[1].equals("use") && checkUseItemEveryone(command, gameTurn) && situation.equals("fighting"))
        {
            return commandsOrder.useItemEveryone;

        } else if (commands.length == 5 && commands[1].equals("use") && checkUseItemSpecific(command, gameTurn) && situation.equals("fighting"))
        {
            return commandsOrder.useItemSpecific;
        } else if (commands.length == 2 && commands[1].equals("?") && checkHeroDescription(command) && (situation.equals("introduce heros") ||
                situation.equals("fighting")))
        {
            return commandsOrder.heroDescription;
        } else if (commands.length == 2 && commands[1].equals("?") && checkAbilityDescription(command) && situation.equals("fighting"))
        {
            return commandsOrder.abilityDescription;
        } else if (commands.length == 2 && commands[1].equals("?") && checkItemDescription(command) && ((situation.equals("shopping")) ||
                situation.equals("fighting")))
        {
            System.out.println("LOL");
            return commandsOrder.ItemDescription;
        } else if (commands.length == 2 && commands[1].equals("?") && checkEnemyDescription(command) && (situation.equals("fighting")) ||
                situation.equals("show enemy data"))
        {
            return commandsOrder.enemyDescription;
        } else if (commands.length == 2 && commands[1].equals("?") && checkHeroClassDescription(command) && situation.equals("introduce heros"))
        {
            return commandsOrder.heroClassDescription;
        } else if (commands.length == 3 && commands[1].equals("attack") && checkHeroAttack(command, gameTurn) && situation.equals("fighting"))
        {
            System.out.println("f u c ...");
            return commandsOrder.heroAttack;
        } else if (commands.length == 3 && commands[2].equals("?") && checkHeroAbilityDescription(command) && situation.equals("start upgrading"))
        {
            return commandsOrder.heroAbilityDescription;
        } else
        {
            System.out.println("YSSSS");
            return null;
        }
    }

    //VAHIDCHECK
    private boolean checkUseItemSpecific(String command, int gameTurn)
    {
        ArrayList<String> heroNames = new ArrayList<>();
        ArrayList<String> normalEnemyFullNames = new ArrayList<>();
        ArrayList<String> bossEnemyFullNames = new ArrayList<>();
        String[] commands = command.split(" ");
        Hero hero = null;
        for (Hero hero1 : heros)
        {
            heroNames.add(hero1.getName());
            if (hero1.getName().equals(commands[0]))
            {
                hero = hero1;
            }
        }
        normalEnemyFullNames = normalEnemyFullNames(gameTurn);
        bossEnemyFullNames = bossEnemyFullNames(gameTurn);
        if (hero != null && userInterface.getItemNames().contains(commands[2]) &&
                commands[3].equals("on") && (normalEnemyFullNames.contains(commands[4]) || bossEnemyFullNames.contains(commands[4]) || heroNames.contains(commands[4])))
        {
            return true;
        } else
        {
            return false;
        }
    }
    //VAHIDCHECK

    private boolean checkUseItemEveryone(String command, int gameTurn)
    {
        ArrayList<String> heroNames = new ArrayList<>();
        ArrayList<String> normalEnemyFullNames = new ArrayList<>();
        ArrayList<String> bossEnemyFullNames = new ArrayList<>();
        String[] commands = command.split(" ");
        for (Hero hero : heros)
        {
            heroNames.add(hero.getName());
        }
        if (heroNames.contains(commands[0]) && userInterface.getItemNames().contains(commands[2]))
        {
            return true;
        } else
        {
            return false;
        }
    }

    private boolean checkHeroClassDescription(String command)
    {
        String[] commands = command.split(" ");
        if (userInterface.getHeroClassNames().contains(commands[0]))
            return true;
        else
            return false;
    }

    private boolean checkEnemyDescription(String command)
    {
        String[] commands = command.split(" ");
        if (userInterface.getNormalEnemyNames().contains(commands[0]) || userInterface.getBossEnemyNames().contains(commands[0]))
            return true;
        else
            return false;
    }

    private boolean checkItemDescription(String command)
    {
        String[] commands = command.split(" ");
        if (userInterface.getItemNames().contains(commands[0]))
            return true;
        else
            return false;
    }

    private boolean checkAbilityDescription(String command)
    {
        String[] commands = command.split(" ");
        if (userInterface.getAbilityNames().contains(commands[0]))
            return true;
        else
            return false;
    }

    private boolean checkHeroDescription(String command)
    {
        String[] commands = command.split(" ");
        if (userInterface.getHeroNames().contains(commands[0]))
            return true;
        else
            return false;
    }

    private boolean checkHeroAbilityDescription(String command)
    {
        String[] commands = command.split(" ");
        for (int i = 0; i < heros.size(); i++)
        {
            if (heros.get(i).getName().equals(commands[0]))
            {
                for (int j = 0; j < heros.get(i).getAbilities().size(); j++)
                {
                    if (heros.get(i).getAbilities().get(i).getName().equals(commands[1]))
                    {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private boolean checkHeroAttack(String command, int gameTurn)
    {
        System.out.println("fuckdo");
        String[] commands = command.split(" ");
        ArrayList<String> normalEnemyFullName = normalEnemyFullNames(gameTurn);
        ArrayList<String> bossEnemyFullName = bossEnemyFullNames(gameTurn);
        for (Hero hero : heros)
        {
            if (hero.getName().equals(commands[0]))
            {
                if (normalEnemyFullName.contains(commands[2]) || bossEnemyFullName.contains(commands[2]))
                    return true;
            }
        }
        return false;
    }

    private boolean checkCastEveryoneAbility(String command, int gameTurn)
    {
        ArrayList<String> heroNames = new ArrayList<>();
        ArrayList<String> normalEnemyFullNames = new ArrayList<>();
        ArrayList<String> bossEnemyFullNames = new ArrayList<>();
        String[] commands = command.split(" ");
        for (Hero hero : heros)
        {
            heroNames.add(hero.getName());
        }
        normalEnemyFullNames = normalEnemyFullNames(gameTurn);
        bossEnemyFullNames = bossEnemyFullNames(gameTurn);
        if (heroNames.contains(commands[0]) && userInterface.getAbilityNames().contains(commands[2]) && commands[3].equals("on") &&
                (normalEnemyFullNames.contains(commands[3]) || bossEnemyFullNames.contains(commands[4]) || heroNames.contains(commands[3])))
        {
            return true;
        } else
            return false;
    }

    private boolean checkCastSpecieficAbility(String command, int gameTurn)
    {
        ArrayList<String> heroNames = new ArrayList<>();
        String[] commands = command.split(" ");
        for (Hero hero : heros)
        {
            heroNames.add(hero.getName());
        }
        if (heroNames.contains(commands[0]) && userInterface.getAbilityNames().contains(commands[2]))
        {
            return true;
        } else
            return false;
    }

    private boolean checkBuyItem(String command)
    {
        ArrayList<String> heroNames = new ArrayList<>();
        String[] commands = command.split(" ");
        for (Hero hero : heros)
        {
            heroNames.add(hero.getName());
        }
        if (userInterface.getItemNames().contains(commands[1]) && commands[2].equals("for") && heroNames.contains(commands[3]))
        {
            return true;
        } else
            return false;
    }

    private boolean checkSellItem(String command)
    {
        ArrayList<String> heroNames = new ArrayList<>();
        String[] commands = command.split(" ");
        for (Hero hero : heros)
        {
            heroNames.add(hero.getName());
        }
        if (userInterface.getItemNames().contains(commands[1]) && commands[2].equals("of") && heroNames.contains(commands[3]))
        {
            return true;
        } else
            return false;
    }

    private boolean checkBuyAbility(String command)
    {
        ArrayList<String> heroNames = new ArrayList<>();
        String[] commands = command.split(" ");
        for (Hero hero : heros)
        {
            heroNames.add(hero.getName());
        }
        if (userInterface.getAbilityNames().contains(commands[1]) && commands[2].equals("for") && heroNames.contains(commands[3]))
        {
            return true;
        } else
            return false;
    }

    /***********/
    enum commandsOrder
    {
        heroAttack,
        ItemDescription,
        abilityDescription,
        heroDescription,
        heroClassDescription,
        enemyDescription,
        itemSell,
        itemBuy,
        heroAbilityDescription,
        acquireAbility, heroCastAbilityOnEveryone, heroCastAbilityOnSpecieficTarget, useItemEveryone, useItemSpecific;
    }
}