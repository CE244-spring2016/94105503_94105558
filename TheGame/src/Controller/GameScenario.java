package Controller;

import Auxiliary.Formula;
import Auxiliary.StringParser;
import Model.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class GameScenario
{
    private UserInterface userInterface;

    private StringParser stringParser;
    private ArrayList<Warrior> warrior;
    private Scanner scanner;
    private ArrayList<EnemyGroup> enemyGroups = new ArrayList<>();
    private ArrayList<Hero> heros = new ArrayList<>();
    private Shop shop;

    public GameScenario(UserInterface userInterface, StringParser stringParser, Scanner in)
    {
        this.userInterface = userInterface;
        this.stringParser = stringParser;
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
                upgradeXP = this.userInterface.getAllAbilityUpgradeXPs().get(abilityName);
                ArrayList<Integer> luckPercents = new ArrayList<>();
                luckPercents = this.userInterface.getAbilityLuckPercents().get(abilityName);
                ArrayList<Integer> abilityCooldownNums = new ArrayList<>();
                abilityCooldownNums = this.userInterface.getAllAbilityCooldowns().get(abilityName);
                ArrayList<HashMap<String, Integer>> requiredAbilities = new ArrayList<>();
                requiredAbilities = this.userInterface.getAllRequiredAbilities().get(abilityName);
                HashMap<String, ArrayList<Formula>> formulas = new HashMap<>();
                formulas.putAll(this.userInterface.getAllAbiliyFormulas().get(abilityName));
                String tragets = this.userInterface.getAbilityTargets().get(abilityName);
                boolean isInstantEffect = false;
                if (this.userInterface.getInstantEffectConditionAbilities().contains(abilityName))
                {
                    isInstantEffect = true;
                }

                if (stringParser.isSingleTarget(tragets))
                {
                    abilities.add(new SingleTargetAbility(abilityName, abilityTarget, upgradeXP, luckPercents,
                            abilityCooldownNums, requiredAbilities, formulas, isInstantEffect));
                }
            }
            Warrior hero = new Hero(heroName, heroClassName, heroData, inventorySize, abilities);
            warrior.add(hero);
            heros.add((Hero) hero);

            for (int j = 0; j < abilities.size(); j++)
            {
                abilities.get(i).setHero((Hero) hero);
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
        for (int i = 0; i < userInterface.getGameTurns(); i++)
        {
            enemyGroups.add(new EnemyGroup(createEnemies(i), userInterface.getEnemyGroupXPs().get(i), userInterface.getEnemyGroupMoneys().get(i), i));
            tellStory(i);
            showEnemyData(i);
            startUpgrading();
            shopping();
            startFighting(i);
        }

        tellStory(userInterface.getGameTurns());

    }

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

                    for (EnemyVersion enemyVersion1 : enemyVersions)
                    {
                        if (enemyVersion1.getName().equals(enemiesVersionAndName.split(" ")[0]))
                        {
                            enemyVersionDatas.putAll(enemyVersion1.getData());
                            break;
                        }
                    }
                    Warrior enemy = new NormalEnemy(enemiesVersionAndName.split(" ")[1], enemiesVersionAndName.split(" ")[0], enemyVersionDatas)
                    enemies.add((Enemy) enemy);
                    warrior.add(enemy);

                }
            } else
            {
                //createBoss
            }
        }
        return enemies;
    }

    public void introduceHeros()//It is finished
    {
        introducingHeros(heros);
        String order = stringParser.normalizer(this.scanner.next());
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
                stringParser.parseOrder(order);
            }
            order = stringParser.normalizer(this.scanner.next());
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
        String order = stringParser.normalizer(this.scanner.next());
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
                stringParser.parseOrder(order);
            }
            order = stringParser.normalizer(this.scanner.next());
        }

    }

    //think about dying enemies
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
        for (NormalEnemy normalEnemy : normalEnemies)
        {
            if (normalEnemy.getID() == 0)
            {
                normalEnemiesNames.add(normalEnemy.getVersion() + normalEnemy.getName());
            } else
            {
                normalEnemiesNames.add(normalEnemy.getVersion() + normalEnemy.getName() + normalEnemy.getID());
            }
        }
        for(BossEnemy bossEnemy : bossEnemies) {
            if(bossEnemy.getID() == 0)
            {
                bossEnemiesNames.add(bossEnemy.getName());
            } else {
                bossEnemiesNames.add(bossEnemy.getName() + bossEnemy.getID());
            }
        }
        System.out.printf("You've encountered");
        for (String normalEnemiesName : normalEnemiesNames)
        {
            System.out.printf("%s, ", normalEnemiesName);
        }
        for (String bossEnemiesName : bossEnemiesNames)
        {
            System.out.printf("%s, ", bossEnemiesName);
        }
        System.out.printf("\n");
    }


    public void startUpgrading()
    {
        showHeroUpgrade();
        String order = stringParser.normalizer(this.scanner.next());
        while (!order.equals("done"))
        {
            if (order.equals("help"))
            {
                //helping
            } else if (order.equals("again"))
            {
                showHeroUpgrade();
            } else
            {
                stringParser.parseOrder(order);
            }
            order = stringParser.normalizer(this.scanner.next());
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
        showShopping(heros);
        String order = stringParser.normalizer(this.scanner.next());
        while (!order.equals("done"))
        {
            if (order.equals("help"))
            {
                //helping
            } else if (order.equals("again"))
            {
                showShopping(heros);
            } else
            {
                stringParser.parseOrder(order);
            }
            order = stringParser.normalizer(this.scanner.next());
        }
    }

    private void showShopping(ArrayList<Hero> heros)
    {
        //Items
    }

    public void startFighting(int i)
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
                if (heroAbility.getCurrentUpgradeNum() > 0)
                {
                    HashMap<String, ArrayList<Formula>> heroAbilityFormula = heroAbility.getFormulas();
                    System.out.printf("can cast %s for %d energy points, %d magic points", heroAbility.getName(),
                            -1 * heroAbilityFormula.get("costEP").get(heroAbility.getCurrentUpgradeNum()).parseFormula(null),
                            -1 * heroAbilityFormula.get("costMagic").get(heroAbility.getCurrentUpgradeNum()).parseFormula(null));
                    if (heroAbility.getCooldownTurn() > 0)
                    {
                        System.out.printf("and %d turn cooldown\n", heroAbility.getCooldownTurn());
                    } else
                    {
                        System.out.printf("\n");
                    }
                }
            }
            ArrayList<Item> heroItems = hero.getInventory().getItems();
            for (Item heroItem : heroItems)
            {
                //item
            }
        }
        //enemies

    }
}