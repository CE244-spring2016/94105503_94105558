package Controller;

import Auxiliary.Formula;
import Model.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

/**
 * Created by Vahid on 7/12/2016.
 */
public class LocalNetwork
{
    Scanner scanner;
    private String firstName = "";
    private String secondName = "";
    private int firstImmortalityPotionNum = 0;
    private int secondImmortalityPotionNum = 0;
    private int firstXP;
    private int secondXP;
    private int secondMoney;
    private int firstMoney;
    private int turn = 0;
    private GameScenario gameScenario1;
    private GameScenario gameScenario2;
    private ArrayList<Hero> herosOne;
    private ArrayList<Hero> herosTwo;
    private NetworkScenario networkScenario1;
    private NetworkScenario networkScenario2;
//    private ExecutorService service = Executors.new
//
    public LocalNetwork(GameScenario gameScenario1,GameScenario gameScenario2, Scanner scanner, NetworkScenario networkScenario1, NetworkScenario networkScenario2)
    {
        this.scanner = scanner;
        this.gameScenario1 = gameScenario1;
        this.gameScenario2 = gameScenario2;
        setHerosOne(gameScenario1.getHeros());
        setHerosTwo(gameScenario2.getHeros());
        this.networkScenario1 = networkScenario1;
        this.networkScenario2 = networkScenario2;
    }

    public void LocalNetworkStart()
    {
        if(turn == 0){
            host();
            System.out.println("Enter Your Name");
            setFirstName(scanner.nextLine());
            //shopping
            //upgrading
            turn++;
            System.out.println("Turn is changed");
        }
        if(turn == 1) {
            System.out.println("Enter Your Name");
            setSecondName(scanner.nextLine());
            //shopping
            //upgrading
            turn++;
            System.out.println("turn is changed");
        }
        while(true) {
            EPRefill(herosOne);
            EPRefill(herosTwo);
            if(turn % 2 == 0){
                if(turn == 2){
                            for (Hero hero : herosOne)
                            {
                                HashMap<String, Integer> data = hero.getData();
                                data.put("current EP", data.get("current EP") / 2);
                                data.put("max EP", data.get("max EP") / 2);
                            }

                    }
                StartFighting(0);
                turn++;
                System.out.println("turn is changed");
            } else {
                StartFighting(1);
                turn++;
                System.out.println("turn is changed");
            }
        }

    }

    private void StartFighting(int i) {
        ArrayList<Hero> enemies = new ArrayList<>();
        int enemyImmortalityPotionNum = 0;
        ArrayList<Hero> heros = new ArrayList<>();
        if(i == 0){
            enemies = herosTwo;
            enemyImmortalityPotionNum = secondImmortalityPotionNum;
            heros = herosOne;
            networkScenario1.setEnemyHeros(enemies);
            networkScenario1.setHeros(heros);
        } else {
            enemies = herosOne;
            enemyImmortalityPotionNum = firstImmortalityPotionNum;
            heros = herosTwo;
            networkScenario2.setEnemyHeros(enemies);
            networkScenario2.setHeros(heros);
        }

        showStartFighting(enemies, heros);
        String order = gameScenario1.normalizer(this.scanner.nextLine());
        while (!order.equals("done"))
        {

            switch (order)
            {
                case "help":
                    System.out.println("(item name) + “?” \uF0E0 (item description)\n" +
                            "(ability name) + “?”\uF0E0 (ability description)\n" +
                            "(hero name) + “?” \uF0E0 (hero description)\n" +
                            "(enemy name) + “?”\uF0E0 (enemy description)\n" +
                            "(hero name) + “ cast “ + (ability name) + “ on “ + (hero name / enemy name and id)\uF0E0\n" +
                            "(ability success message)\n" +
                            "(hero name) + “ use “ + (item name) + “ on “ + (hero name / enemy name and id) \uF0E0\n" +
                            "(item success message)");
                    System.out.println("(hero name) + “ attack “ + (enemy name and id) \uF0E0");
                    break;
                case "again":
                    showStartFighting(enemies, heros);
                    break;
                default:
                    if(i == 0)
                   networkScenario1.parseOrder(order, "fighting", new ArrayList<>());
                    else
                    networkScenario2.parseOrder(order, "fighting", new ArrayList<>());
                    break;
            }

            for (Hero enemy : enemies) // LOL
            {
                if (enemy.getData().get("current health") <= 0)
                {
                    if (enemyImmortalityPotionNum == 0)
                    {
                        System.out.println("his " + enemy.getName() + " is dead");
                        return;
                    } else
                    {
                        enemyImmortalityPotionNum = enemyImmortalityPotionNum - 1;
                        if(i == 0){
                            secondImmortalityPotionNum--;
                        } else {
                            firstImmortalityPotionNum--;
                        }
                        gameScenario1.reviveHero(enemy);
                        System.out.println("his " + enemy.getName() + " is dying, immortality potion was used for reincarnation process, he now has “" +
                                enemyImmortalityPotionNum + " immortality potions left");
//                        message.setMessage("your " + enemy.getName() + " is dying, immortality potion was used for reincarnation process, you now have “" +
//                                getEnemyImmortalityPotionNum() + " immortality potions left");
//                        ntwhandler.setMessage(message);
//                        ntwhandler.sendMessage();
                    }
                }
            }

            order = gameScenario1.normalizer(this.scanner.nextLine());
        }
        //FK
        for (Hero hero : heros)
        {
            for (int j = 0; j < hero.getAbilities().size(); j++)
            {
                if (hero.getAbilities().get(j).getCooldownTurn() > 0)
                {
                    hero.getAbilities().get(j).setCooldownTurn(hero.getAbilities().get(j).getCooldownTurn() - 1);
                }
            }
        }
        //FK
    }

    private void showStartFighting(ArrayList<Hero> enemyHeros, ArrayList<Hero> heros)
    {
        for (Hero hero : heros)
        {
            System.out.printf("%s\n", hero.getName());
            System.out.printf("Health: %d/%d\n", hero.getData().get("current health"), hero.getData().get("max health"));
            System.out.printf("Magic: %d/%d\n", hero.getData().get("current magic"), hero.getData().get("max magic"));
            System.out.printf("Energy Points: %d\n", hero.getData().get("current EP"));
            System.out.printf("Attack: %d\n", (hero.getData().get("attack") + hero.getData().get("temp attack")));
            ArrayList<Ability> heroAbilities = hero.getAbilities();
            for (Ability heroAbility : heroAbilities)
            {
                if (heroAbility instanceof ActiveAbility)
                {
                    if (heroAbility.getCurrentUpgradeNum() > 0)
                    {
                        HashMap<String, ArrayList<Formula>> heroAbilityFormula = heroAbility.getFormulas();
                        System.out.println("can cast " + heroAbility.getName());
                        if (heroAbilityFormula.containsKey("cost EP")) // LOL
                        {
                            System.out.println("for " + -1 * heroAbilityFormula.get("cost EP").get(heroAbility.getCurrentUpgradeNum() - 1).parseFormula(null) + " EP");
                        }
                        if (heroAbilityFormula.containsKey("cost magic"))
                        {
                            System.out.println("for " + -1 * heroAbilityFormula.get("cost magic").get(heroAbility.getCurrentUpgradeNum() - 1).parseFormula(null) + " magic point");
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
        ArrayList<Hero> enemies = enemyHeros;
        ArrayList<String> enemiesNames = new ArrayList<>();
        for (Hero enemy : enemies)
        {
            enemiesNames.add(enemy.getName());

        }
        for (int i = 0; i < enemiesNames.size(); i++)
        {
            System.out.printf("%s Health: %d / %d ", enemiesNames.get(i), enemies.get(i).getData().get("current health"), enemies.get(i).getData().get("max health"));
        }
    }

    private void EPRefill(ArrayList<Hero> heros) {
        for (Hero hero : heros)
        {
            HashMap<String, Integer> userData = gameScenario1.userInterface.getHeroClassDatas().get(hero.getHeroClassName());
            int maxEP = userData.get("max EP");
            HashMap<String, Integer> x = hero.getData();
            x.put("current EP", maxEP);
            hero.setData(x);
        }

    }

    private void host() {
        System.out.println("You are host... Enter XP");
        int xp = Integer.parseInt(scanner.nextLine());
        setFirstXP(xp);
        setSecondXP(xp);
        System.out.println("Enter money");
        int money = Integer.parseInt(scanner.nextLine());
        setFirstMoney(money);
        setSecondMoney(money);
        System.out.println("Enter Immortality Potion Numbers");
        int immortaltiy = Integer.parseInt(scanner.nextLine());
        setFirstImmortalityPotionNum(immortaltiy);
        setSecondImmortalityPotionNum(immortaltiy);
    }

    public int getFirstMoney()
    {
        return firstMoney;
    }

    public void setFirstMoney(int firstMoney)
    {
        this.firstMoney = firstMoney;
    }

    public int getFirstXP()
    {
        return firstXP;
    }

    public void setFirstXP(int firstXP)
    {
        this.firstXP = firstXP;
    }

    public int getSecondXP()
    {
        return secondXP;
    }

    public void setSecondXP(int secondXP)
    {
        this.secondXP = secondXP;
    }

    public int getSecondMoney()
    {
        return secondMoney;
    }

    public void setSecondMoney(int secondMoney)
    {
        this.secondMoney = secondMoney;
    }

    public Scanner getScanner()
    {
        return scanner;
    }

    public void setScanner(Scanner scanner)
    {
        this.scanner = scanner;
    }

    public String getFirstName()
    {
        return firstName;
    }

    public void setFirstName(String firstName)
    {
        this.firstName = firstName;
    }

    public String getSecondName()
    {
        return secondName;
    }

    public void setSecondName(String secondName)
    {
        this.secondName = secondName;
    }

    public int getFirstImmortalityPotionNum()
    {
        return firstImmortalityPotionNum;
    }

    public void setFirstImmortalityPotionNum(int firstImmortalityPotionNum)
    {
        this.firstImmortalityPotionNum = firstImmortalityPotionNum;
    }

    public int getSecondImmortalityPotionNum()
    {
        return secondImmortalityPotionNum;
    }

    public void setSecondImmortalityPotionNum(int secondImmortalityPotionNum)
    {
        this.secondImmortalityPotionNum = secondImmortalityPotionNum;
    }


    public ArrayList<Hero> getHerosOne()
    {
        return herosOne;
    }

    public void setHerosOne(ArrayList<Hero> herosOne)
    {
        this.herosOne = herosOne;
    }

    public ArrayList<Hero> getHerosTwo()
    {

        return herosTwo;
    }

    public void setHerosTwo(ArrayList<Hero> herosTwo)
    {
        this.herosTwo = herosTwo;
    }
}
