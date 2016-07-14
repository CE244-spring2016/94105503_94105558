package Controller;

import Auxiliary.Formula;
import Auxiliary.Luck;
import Exceptions.AbilityCooldownException;
import Exceptions.AbilityNotAcquieredException;
import Exceptions.NotStrongEnoughException;
import Model.*;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.concurrent.*;

/**
 * Created by Vahid on 7/7/2016.
 */
public class NetworkScenario
{
    Scanner scanner;
    private String name = "";
    private String enemyName = "";
    private int enemyImmortalityPotionNum = 0;
    private int myImmortalityPotionNum = 0;
    private GameScenario gameScenario;
    private ArrayList<Hero> heros;
    private ArrayList<Hero> enemyHeros;
    private ObjectInputStream in;
    private ObjectOutputStream out;
    private NetworkHandler ntwhandler;
    private HostJoin hostJoin;
    private CommonMsg commonMsg;
    //vahid "choice is 1 if client and 0 if server
    private int choice;
    private Callable<Object> startFightingss = new Callable<Object>()
    {

        @Override
        public Object call() throws Exception
        {
            ArrayList<Hero> enemies = enemyHeros;
            ArrayList<String> messages = new ArrayList<>();
            showStartFighting();
            String order = gameScenario.normalizer(scanner.nextLine());
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
                        showStartFighting();
                        break;
                    default:
                        parseOrder(order, "fighting", messages);
                        break;
                }

                for (Hero enemy : enemies) // LOL
                {
                    if (enemy.getData().get("current health") <= 0)
                    {
                        if (enemyImmortalityPotionNum == 0)
                        {
                            System.out.println("his " + enemy.getName() + " is dead");
//                        message.setMessage("your "+ enemy.getName()+" is dead");
//                        ntwhandler.setMessage(message);
//                        ntwhandler.sendMessage();
                            commonMsg.setWinner(choice);
                            return 1;
                        } else
                        {
                            setEnemyImmortalityPotionNum(getEnemyImmortalityPotionNum() - 1);
                            gameScenario.reviveHero(enemy);
                            System.out.println("his " + enemy.getName() + " is dying, immortality potion was used for reincarnation process, he now has “" +
                                    getEnemyImmortalityPotionNum() + " immortality potions left");
//                        message.setMessage("your " + enemy.getName() + " is dying, immortality potion was used for reincarnation process, you now have “" +
//                                getEnemyImmortalityPotionNum() + " immortality potions left");
//                        ntwhandler.setMessage(message);
//                        ntwhandler.sendMessage();
                        }
                    }
                }

                order = gameScenario.normalizer(scanner.nextLine());
            }
//        message.setSendingMessage(false);
//        ntwhandler.setMessage(message);
//        ntwhandler.sendMessage();
            commonMsg.setEnemyHeros(heros);
            commonMsg.setHeros(enemies);
            ntwhandler.setCommonMsg(commonMsg);
            ntwhandler.send();
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
            return 0;
        }


    };

    public NetworkScenario(Scanner scanner, GameScenario gameScenario)
    {
        this.scanner = scanner;
        this.gameScenario = gameScenario;
        heros = this.gameScenario.getHeros();
        commonMsg = new CommonMsg(heros);
        hostJoin = new HostJoin(commonMsg);
        hostJoinHandle(hostJoin);
        this.in = hostJoin.getIn();
        this.out = hostJoin.getOut();
        ntwhandler = new NetworkHandler(commonMsg, this.in, this.out);
    }


    private void hostJoinHandle(HostJoin hostJoin)
    {
        String order = scanner.nextLine();
        if (order.equals("host"))
        {
            choice = 0;
            hostJoin.setServer(2000);
        } else if (order.equals("join"))
        {
            choice = 1;
            hostJoin.setClient(2000);

        }
    }

    private void modifyXPMoneyImmortal()
    {
        if (choice == 0)
        {
            int money = 0;
            int xp = 0;
            int immortalityPotion = 0;
            System.out.println("Enter XP");
            xp = Integer.parseInt(scanner.nextLine());
            System.out.println("Enter Money");
            money = Integer.parseInt(scanner.nextLine());
            System.out.println("Enter Immortaltiy potion num");
            immortalityPotion = Integer.parseInt(scanner.nextLine());
            commonMsg.setMoney(money);
            commonMsg.setXp(xp);
            commonMsg.setImmortalityPotion(immortalityPotion);
            Hero.setXP(xp);
            Hero.setMoney(money);
            Hero.setImmortalityPotionNum(immortalityPotion);
            setEnemyImmortalityPotionNum(immortalityPotion);
            setMyImmortalityPotionNum(immortalityPotion);
            ntwhandler.send();
        } else if (choice == 1)
        {
            ntwhandler.receive();
            commonMsg = ntwhandler.getCommonMsg();
            Hero.setXP(commonMsg.getXp());
            Hero.setMoney(commonMsg.getMoney());
            Hero.setImmortalityPotionNum(commonMsg.getImmortalityPotion());
        }
    }

    private void startFighting()
    {
        int i = 2001;
        int turn = choice;
        int chance = commonMsg.getChance();
        int badChance = 0;
        if (chance == 1)
        {
            badChance = 0;
        } else if (chance == 0)
        {
            badChance = 1;
        }
        if (choice == chance)
            System.out.println("You are Starter");
        else
        {
            System.out.println("he is starter");
        }
        int cntr = 0;
        while (true)
        {
            ExecutorService service = Executors.newSingleThreadExecutor();
            heroEPRefills();
            if (turn == badChance)
            {
                waitforOther();
                heroEPRefills();
                if (turn == 1)
                    turn--;
                else if (turn == 0)
                    turn++;
            } else if (turn == chance)
            {
                if (i == 2001 && choice == chance)
                    costEP(0);
                fightOther(service);
                service.shutdown();
                heroEPRefills();

                if (turn == 1)
                    turn--;
                else if (turn == 0)
                    turn++;
            }

            if (choice == 0)
            {
                hostJoin.setServer(i);
                i++;
                try
                {
                    Thread.sleep(100);
                } catch (InterruptedException e)
                {
                    e.printStackTrace();
                }
            }

            if (choice == 1)
            {
                hostJoin.setClient(i);
                i++;
            }
            this.in = hostJoin.getIn();
            this.out = hostJoin.getOut();

        }
//        while (true)
//        {
//            heroEPRefills();
//            if (commonMsg.getTurn() % 2 == 0)
//            {
//                if (choice == whatIsChoice())
//                {
//
//                    costEP(commonMsg.getTurn());
//                    commonMsg = ntwhandler.getCommonMsg();
//                    enemyHeros = commonMsg.getEnemyHeros();
//                    startFightings();
////                    ExecutorService service = Executors.newSingleThreadExecutor();
////                    final Future<Object> f = service.submit(startFightingss);
////                    try
////                    {
////                        f.get(60, TimeUnit.SECONDS);
////                    } catch (InterruptedException | ExecutionException e)
////                    {
////                       f.cancel(true);
////                    } catch (TimeoutException e)
////                    {
////                        System.out.println("Your time is over");
//////                        message.setSendingMessage(false);
//////                        ntwhandler.setMessage(message);
//////                        ntwhandler.sendMessage();
////                        f.cancel(true);
////                    } finally
////                    {
//
//                    if (commonMsg.getWinner() == choice)
//                    {
//                        System.out.println("You Win");
//                        commonMsg.setEnemyHeros(heros);
//                        commonMsg.setHeros(enemyHeros);
//                        ntwhandler.setCommonMsg(commonMsg);
//                        ntwhandler.send();
//                        break;
//                    }
//                    commonMsg.setEnemyHeros(heros);
//                    commonMsg.setHeros(enemyHeros);
//                    ntwhandler.setCommonMsg(commonMsg);
//                    ntwhandler.send();
////                    }
//                } else
//                {
//                    commonMsg.setEnemyHeros(heros);
//                    ntwhandler.setCommonMsg(commonMsg);
//                    ntwhandler.send();
//                    System.out.println("wait for your competitor");
////                    ExecutorService service = Executors.newSingleThreadExecutor();
////                    final Future<Object> f = service.submit(new Callable<Object>()
////                    {
////                        @Override
////                        public Object call() throws Exception
////                        {
////                            ntwhandler.receiveMessage();
////                            while(ntwhandler.getMessage().isSendingMessage()) {
////                                ntwhandler.receiveMessage();
////                                System.out.println(ntwhandler.getMessage().getMessage());
////                            }
////                            return 1;
////                        }
////                    });
////                    try
////                    {
////                        f.get(60, TimeUnit.SECONDS);
////                    } catch (InterruptedException | ExecutionException e)
////                    {
////                        e.printStackTrace();
////                    } catch (TimeoutException e)
////                    {
////                       f.cancel(true);
////                    }
//                    commonMsg = ntwhandler.getCommonMsg();
//                    enemyHeros = commonMsg.getEnemyHeros();
//                    heros = commonMsg.getHeros();
//
//
//                    if (commonMsg.getWinner() != -1)
//                    {
//                        System.out.println("You Lose");
//                        break;
//                    }
//
//                }
//            } else if (commonMsg.getTurn() % 2 == 1)
//            {
//
//                if (choice == whatIsChoice())
//                {
//                    commonMsg.setEnemyHeros(heros);
//                    ntwhandler.setCommonMsg(commonMsg);
//                    ntwhandler.send();
//                    System.out.println("wait for your competitor");
////                    ExecutorService service = Executors.newSingleThreadExecutor();
////                    final Future<Object> f = service.submit(new Callable<Object>()
////                    {
////                        @Override
////                        public Object call() throws Exception
////                        {
////                            ntwhandler.receiveMessage();
////                            while(ntwhandler.getMessage().isSendingMessage()) {
////                                ntwhandler.receiveMessage();
////                                System.out.println(ntwhandler.getMessage().getMessage());
////                            }
////                            return 1;
////                        }
////                    });
////                    try
////                    {
////                        f.get(60, TimeUnit.SECONDS);
////                    } catch (InterruptedException e)
////                    {
////                        e.printStackTrace();
////                    } catch (ExecutionException e)
////                    {
////                        e.printStackTrace();
////                    } catch (TimeoutException e)
////                    {
////                        f.cancel(true);
////                    }}
//                    commonMsg = ntwhandler.getCommonMsg();
//                    enemyHeros = commonMsg.getEnemyHeros();
//                    heros = commonMsg.getHeros();
//
//
//                    if (commonMsg.getWinner() != -1)
//                    {
//                        System.out.println("You Lose");
//                        break;
//                    }
//
//                } else
//                {
//                    commonMsg = ntwhandler.getCommonMsg();
//                    enemyHeros = commonMsg.getEnemyHeros();
////                    ExecutorService service = Executors.newSingleThreadExecutor();
////                    final Future<Object> f = service.submit(startFightingss);
////                    try
////                    {
////                        f.get(60, TimeUnit.SECONDS);
////                    } catch (InterruptedException | ExecutionException e)
////                    {
////                       f.cancel(true);
////                    } catch (TimeoutException e)
////                    {
////                        System.out.println("Your time is over");
//////                        message.setSendingMessage(false);
//////                        ntwhandler.setMessage(message);
//////                        ntwhandler.sendMessage();
////                        f.cancel(true);
////                    } finally
////                    {
//                    startFightings();
//                    if (commonMsg.getWinner() == choice)
//                    {
//                        System.out.println("You Win");
//                        commonMsg.setEnemyHeros(heros);
//                        commonMsg.setHeros(enemyHeros);
//                        ntwhandler.setCommonMsg(commonMsg);
//                        ntwhandler.send();
//                        break;
//                    }
//                    commonMsg.setEnemyHeros(heros);
//                    commonMsg.setHeros(enemyHeros);
//                    ntwhandler.setCommonMsg(commonMsg);
//                    ntwhandler.send();
////                    }
//                }
//            }
//            commonMsg.setTurn(commonMsg.getTurn() + 1);
//            commonMsg.setSuccessMessage(true);
//        }
    }

    private void fightOther(ExecutorService service)
    {
        ntwhandler.receive();
        commonMsg = ntwhandler.getCommonMsg();
        enemyHeros = commonMsg.getEnemyHeros();
        ArrayList<String> messages = new ArrayList<>();
        messages = commonMsg.getMessages();
        for (String message : messages)
        {
            System.out.println(message);
        }
        Callable<Object> startFightingss = new Callable<Object>()
        {

            @Override
            public Object call() throws Exception
            {
                ArrayList<String> message = new ArrayList<>();
                ArrayList<Hero> enemies = enemyHeros;
                showStartFighting();
                String order = gameScenario.normalizer(scanner.nextLine());
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
                            showStartFighting();
                            break;
                        default:
                            parseOrder(order, "fighting", message);
                            break;
                    }

                    for (Hero enemy : enemies) // LOL
                    {
                        if (enemy.getData().get("current health") <= 0)
                        {
                            if (enemyImmortalityPotionNum == 0)
                            {
                                System.out.println("his " + enemy.getName() + " is dead");
                                message.add("your " + enemy.getName() + " is dead");
                                commonMsg.setWinner(choice);
                                return 1;
                            } else
                            {
                                setEnemyImmortalityPotionNum(getEnemyImmortalityPotionNum() - 1);
                                gameScenario.reviveHero(enemy);
                                System.out.println("his " + enemy.getName() + " is dying, immortality potion was used for reincarnation process, he now has “" +
                                        getEnemyImmortalityPotionNum() + " immortality potions left");
                                message.add("your " + enemy.getName() + " is dying, immortality potion was used for reincarnation process, you now have “" +
                                        getEnemyImmortalityPotionNum() + " immortality potions left");
//                        ntwhandler.setMessage(message);
//                        ntwhandler.sendMessage();
                            }
                        }
                    }

                    order = gameScenario.normalizer(scanner.nextLine());
                }
//        message.setSendingMessage(false);
//        ntwhandler.setMessage(message);
//        ntwhandler.sendMessage();
                commonMsg.setEnemyHeros(heros);
                commonMsg.setMessages(message);
                commonMsg.setHeros(enemies);
                ntwhandler.setCommonMsg(commonMsg);
                ntwhandler.send();
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
                return 0;
            }


        };
        Future<Object> f = service.submit(startFightingss);
        try
        {
            f.get(50, TimeUnit.SECONDS);
        } catch (InterruptedException | ExecutionException e)
        {
        } catch (TimeoutException e)
        {
            System.out.println("Your time is over");
        }
        //  startFightings();

        if (commonMsg.getWinner() == choice)
        {
            System.out.println("You Win");
            commonMsg.setEnemyHeros(heros);
            commonMsg.setHeros(enemyHeros);
            ntwhandler.setCommonMsg(commonMsg);
            ntwhandler.send();
            System.exit(0);
        }
        commonMsg.setEnemyHeros(heros);
        commonMsg.setHeros(enemyHeros);
        //commonMsg.setMessages(messages);
        ntwhandler.setCommonMsg(commonMsg);
        ntwhandler.send();


    }


    private void waitforOther()
    {
        commonMsg.setEnemyHeros(heros);
        ntwhandler.setCommonMsg(commonMsg);
        ntwhandler.send();
        ntwhandler.receive();
        commonMsg = ntwhandler.getCommonMsg();
        heros = commonMsg.getHeros();
        enemyHeros = commonMsg.getEnemyHeros();
        if (commonMsg.getWinner() != -1)
        {
            System.out.println("You Lose");
            System.exit(0);
        }
    }

    private void heroAttack(String command, ArrayList<String> messages)
    {
        String[] commands = command.split(" ");
        Hero hero = null;
        for (Hero hero1 : heros)
        {
            if (hero1.getName().equals(commands[0]))
                hero = hero1;
        }
        ArrayList<Hero> heroEnemies = enemyHeros;
        ArrayList<Warrior> realEnemies = new ArrayList<>();
        for (Hero enemy : heroEnemies)
        {
            realEnemies.add(enemy);
        }
        ArrayList<String> enemyHerosName = new ArrayList<>();
        for (Hero heroEnemy : heroEnemies)
        {
            enemyHerosName.add(heroEnemy.getName());
        }
        if (hero != null && commands[1].equals("attack") && (enemyHerosName.contains(commands[2])))
        {
            try
            {
                hero.regularAttack(realEnemies, commands[2]);
                System.out.println(hero.getAttackSuccessMessage());
            } catch (NotStrongEnoughException e)
            {
                e.printStackTrace();
            }
            String message = hero.getAttackSuccessMessage();
            message = message.replaceFirst("his", "your");
            String messagess[] = message.split(" ");
            messagess[0] = "his";
            message = String.join(" ", messagess);
            messages.add(message);
        } else
        {
            System.out.println("Invalid command");
        }
    }

    public void startNetworking()
    {
        hostJoinHandle(hostJoin);
//        modifyXPMoneyImmortal();
//        chooseName();
//        isReady();
//        gameScenario.startUpgrading();
//        gameScenario.shopping();
        starter();
        startFighting();

    }

    private void isReady()
    {
        System.out.println("Are you ready(yes/no)");
        while (!scanner.nextLine().equals("yes"))
        {
        }
        commonMsg.setReady(true);
        ntwhandler.setCommonMsg(commonMsg);
        ntwhandler.send();
        System.out.println("waiting for your competitor");
        ntwhandler.receive();
        commonMsg = ntwhandler.getCommonMsg();
        if (commonMsg.isReady())
        {
            System.out.println("he is ready");
        }
    }

    private void chooseName()
    {
        System.out.println("Enter Your Name");
        this.name = scanner.nextLine();
        commonMsg.setName(name);
        ntwhandler.setCommonMsg(commonMsg);
        ntwhandler.send();
        ntwhandler.receive();
        commonMsg = ntwhandler.getCommonMsg();
        this.enemyName = commonMsg.getName();
        System.out.println("your name is " + name);
        System.out.println("your enemy name is " + enemyName);
    }

    //VAHID
    public void costEP(int cntr)
    {
        if (cntr == 0)
        {
            for (Hero hero : heros)
            {
                HashMap<String, Integer> data = hero.getData();
                data.put("current EP", data.get("current EP") / 2);
                data.put("max EP", data.get("max EP") / 2);
            }
        }

    }

    private int chooseStarter()
    {
        boolean x = Luck.isLikely(50);
        if (x)
        {
            return 1;
        } else
        {
            return 0;
        }
    }

    private void starter()
    {
        if (choice == 0)
        {
            int chance = chooseStarter();
            commonMsg.setChance(chance);
            ntwhandler.setCommonMsg(commonMsg);
            ntwhandler.send();
        } else
        {
            ntwhandler.receive();
            commonMsg = ntwhandler.getCommonMsg();
        }
    }


   /* public void startFightings()
    {
        ArrayList<Hero> enemies = enemyHeros;
        showStartFighting();
        String order = gameScenario.normalizer(this.scanner.nextLine());
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
                    showStartFighting();
                    break;
                default:
                    parseOrder(order, "fighting", );
                    break;
            }

            for (Hero enemy : enemies) // LOL
            {
                if (enemy.getData().get("current health") <= 0)
                {
                    if (enemyImmortalityPotionNum == 0)
                    {
                        System.out.println("his " + enemy.getName() + " is dead");
//                        message.setMessage("your "+ enemy.getName()+" is dead");
//                        ntwhandler.setMessage(message);
//                        ntwhandler.sendMessage();
                        commonMsg.setWinner(choice);
                        return;
                    } else
                    {
                        setEnemyImmortalityPotionNum(getEnemyImmortalityPotionNum() - 1);
                        gameScenario.reviveHero(enemy);
                        System.out.println("his " + enemy.getName() + " is dying, immortality potion was used for reincarnation process, he now has “" +
                                getEnemyImmortalityPotionNum() + " immortality potions left");
//                        message.setMessage("your " + enemy.getName() + " is dying, immortality potion was used for reincarnation process, you now have “" +
//                                getEnemyImmortalityPotionNum() + " immortality potions left");
//                        ntwhandler.setMessage(message);
//                        ntwhandler.sendMessage();
                    }
                }
            }

            order = gameScenario.normalizer(this.scanner.nextLine());
        }
//        message.setSendingMessage(false);
//        ntwhandler.setMessage(message);
//        ntwhandler.sendMessage();
        commonMsg.setEnemyHeros(heros);
        commonMsg.setHeros(enemies);
        ntwhandler.setCommonMsg(commonMsg);
        ntwhandler.send();
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
    }*/

    private void showStartFighting()
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

    public void parseOrder(String command, String situation, ArrayList<String> messages)
    {
        GameScenario.commandsOrder order = whatIsOrder(command, situation, 0);
        if (order != null)
        {
            switch (order)
            {
                case heroAttack:
                    heroAttack(command, messages);
                    break;
                case useItemEveryone:
                    useItemEveryone(command, messages);
                    break;
                case useItemSpecific:
                    useItemSpecific(command, messages);
                    break;
                case heroDescription:
                    gameScenario.heroDescription(command);
                    break;
                case abilityDescription:
                    gameScenario.abilityDescription(command);
                    break;
                case ItemDescription:
                    gameScenario.itemDescription(command);
                    break;
                case heroCastAbilityOnEveryone:
                    heroCastEveryoneAbility(command, messages);
                    break;
                case heroCastAbilityOnSpecieficTarget:
                    heroCastSpecieficAbility(command, messages);
                    break;

                default:
                    System.out.println("Invalid command");
                    break;

            }
        } else
        {
            System.out.println("Invalid command");
        }
    }

    private GameScenario.commandsOrder whatIsOrder(String command, String situation, int gameTurn)
    {
        String[] commands = command.split(" ");
        if (commands.length == 3 && commands[1].equals("cast") && gameScenario.checkCastEveryoneAbility(command, gameTurn) && situation.equals("fighting"))
        {
            return GameScenario.commandsOrder.heroCastAbilityOnEveryone;
        } else if (commands.length == 5 && commands[1].equals("cast") && checkCastSpecieficAbility(command) && situation.equals("fighting"))
        {
            return GameScenario.commandsOrder.heroCastAbilityOnSpecieficTarget;
        } else if (commands.length == 3 && commands[1].equals("use") && gameScenario.checkUseItemEveryone(command, gameTurn) && situation.equals("fighting"))
        {
            return GameScenario.commandsOrder.useItemEveryone;

        } else if (commands.length == 5 && commands[1].equals("use") && checkUseItemSpecific(command) && situation.equals("fighting"))
        {
            return GameScenario.commandsOrder.useItemSpecific;
        } else if (commands.length == 2 && commands[1].equals("?") && gameScenario.checkHeroDescription(command) && (situation.equals("introduce heros") ||
                situation.equals("fighting")))
        {
            return GameScenario.commandsOrder.heroDescription;
        } else if (commands.length == 2 && commands[1].equals("?") && gameScenario.checkAbilityDescription(command) && situation.equals("fighting"))
        {
            return GameScenario.commandsOrder.abilityDescription;
        } else if (commands.length == 2 && commands[1].equals("?") && gameScenario.checkItemDescription(command) && ((situation.equals("shopping")) ||
                situation.equals("fighting")))
        {
            return GameScenario.commandsOrder.ItemDescription;
        } else if (commands.length == 3 && commands[1].equals("attack") && checkHeroAttack(command) && situation.equals("fighting"))
        {
            return GameScenario.commandsOrder.heroAttack;
        } else
        {
            return null;
        }
    }

    private boolean checkUseItemSpecific(String command)
    {
        ArrayList<String> heroNames = new ArrayList<>();
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
        ArrayList<String> enemyNames = new ArrayList<>();
        for (Hero enemyHero : enemyHeros)
        {
            enemyNames.add(enemyHero.getName());
        }

        if (hero != null && gameScenario.userInterface.getItemNames().contains(commands[2]) &&
                commands[3].equals("on") && (enemyNames.contains(commands[4]) || heroNames.contains(commands[4])))
        {
            return true;
        } else
        {
            return false;
        }
    }

    private boolean checkHeroAttack(String command)
    {
        String[] commands = command.split(" ");
        ArrayList<String> enemyNames = new ArrayList<>();
        for (Hero enemyHero : enemyHeros)
        {
            enemyNames.add(enemyHero.getName());
        }
        for (Hero hero : heros)
        {
            if (hero.getName().equals(commands[0]))
            {
                if (enemyNames.contains(commands[2]))
                    return true;
            }
        }
        return false;
    }

    private boolean checkCastSpecieficAbility(String command)
    {
        ArrayList<String> heroNames = new ArrayList<>();
        ArrayList<String> enemyFullNames = new ArrayList<>();
        String[] commands = command.split(" ");
        for (Hero hero : heros)
        {
            heroNames.add(hero.getName());
        }
        for (Hero enemyHero : enemyHeros)
        {
            enemyFullNames.add(enemyHero.getName());
        }
        if (heroNames.contains(commands[0]) && gameScenario.userInterface.getAbilityNames().contains(commands[2]) && commands[3].equals("on") &&
                (enemyFullNames.contains(commands[4]) || heroNames.contains(commands[4])))
        {
            return true;
        } else
            return false;
    }

    //Ok
    private void heroCastSpecieficAbility(String command, ArrayList<String> message)
    {
        String[] commands = command.split(" ");
        Hero hero = null;
        for (Hero hero1 : heros)
        {
            if (hero1.getName().equals(commands[0]))
                hero = hero1;
        }
        ArrayList<Hero> heroEnemies = enemyHeros;
        ArrayList<Warrior> realEnemies = new ArrayList<>();
        for (Hero enemy : heroEnemies)
        {
            realEnemies.add(enemy);
        }
        if (hero != null && gameScenario.userInterface.getHerosAndTheirAbilities().get(hero.getName()).contains(commands[2]) && commands[3].equals("on"))
        {
            Ability ability = hero.findAbility(commands[2]);
            if (ability != null && ability instanceof ActiveAbility) // LOL
            {
                try
                {
                    hero.useAbility(ability.getName(), realEnemies, heros, commands[4]);
                    ability.printSuccessMessage(realEnemies);
                } catch (AbilityNotAcquieredException e)
                {
                    e.printStackTrace();
                } catch (AbilityCooldownException e)
                {
                    e.printStackTrace();
                } catch (NotStrongEnoughException e)
                {
                    e.printStackTrace();
                }
                gameScenario.correctCurrentAttributes();
            } else
            {
                System.out.println("This ability is not castable"); // LOL
            }
        } else if (!gameScenario.userInterface.getHerosAndTheirAbilities().get(hero.getName()).contains(commands[2]))
        {
            // LOL
            System.out.println("This hero can not cast this ability");
        } else
        {
            System.out.println("Invalid Command"); // LOL
        }
    }

    //OK
    private void heroCastEveryoneAbility(String command, ArrayList<String> message)
    {
        String[] commands = command.split(" ");
        Hero hero = null;
        for (Hero hero1 : heros)
        {
            if (hero1.getName().equals(commands[0]))
                hero = hero1;
        }
        if (hero != null && gameScenario.userInterface.getHerosAndTheirAbilities().get(hero.getName()).contains(commands[2]))
        {
            Ability ability = hero.findAbility(commands[2]);
            if (ability != null && ability instanceof ActiveAbility) // LOL
            {
                ArrayList<Hero> heroEnemies = enemyHeros;
                ArrayList<Warrior> realEnemies = new ArrayList<>();
                for (Hero enemy : heroEnemies)
                {
                    realEnemies.add(enemy);
                }
                try
                {
                    hero.useAbility(ability.getName(), realEnemies, heros, "everyone");
                } catch (AbilityNotAcquieredException e)
                {
                    e.printStackTrace();
                } catch (AbilityCooldownException e)
                {
                    e.printStackTrace();
                } catch (NotStrongEnoughException e)
                {
                    e.printStackTrace();
                }
                gameScenario.correctCurrentAttributes(); // LOL
            } else
            {
                System.out.println("This ability is not castable");
            }
        } else if (!gameScenario.userInterface.getHerosAndTheirAbilities().get(hero.getName()).contains(commands[2]))
        {
            // LOL
            System.out.println("This hero can not cast this ability");
        } else
        {
            System.out.println("Invalid Command"); // LOL
        }
    }


    //OK
    private void useItemEveryone(String command, ArrayList<String> messages)
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
                ArrayList<Hero> heroEnemies = enemyHeros;
                ArrayList<Warrior> realEnemies = new ArrayList<>();
                for (Hero enemy : heroEnemies)
                {
                    realEnemies.add(enemy);
                }
                hero.useItem(item.getName(), realEnemies, heros, "everyone");
                gameScenario.correctCurrentAttributes(); // LOL
            } else
            {
                System.out.println("You don't have this item");
            }
        } else if (hero == null)
        {
            System.out.println("Invalid command");
        } else
        {
            System.out.println("You don’t have this item");
        }
    }

    //Ok
    private void useItemSpecific(String command, ArrayList<String> message)
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
                ArrayList<Hero> heroEnemies = enemyHeros;
                ArrayList<Warrior> realEnemies = new ArrayList<>();
                for (Hero enemy : heroEnemies)
                {
                    realEnemies.add(enemy);
                }
                hero.useItem(item.getName(), realEnemies, heros, commands[4]);
                gameScenario.correctCurrentAttributes(); // LOL
            } else
            {
                // LOL
                System.out.println("This item is not usable!");
            }
        } else if (hero == null)
        {
            // LOL
            System.out.println("Invalid command");
        } else
        {
            System.out.println("You don’t have this item");
        }
    }

    //Ok
    private void heroEPRefills()
    {
        for (Hero hero : heros)
        {
            HashMap<String, Integer> userData = gameScenario.userInterface.getHeroClassDatas().get(hero.getHeroClassName());
            int maxEP = userData.get("max EP");
            HashMap<String, Integer> x = hero.getData();
            x.put("current EP", maxEP);
            hero.setData(x);
        }

    }


    //Setter Getter
    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getEnemyName()
    {
        return enemyName;
    }

    public void setEnemyName(String enemyName)
    {
        this.enemyName = enemyName;
    }

    public GameScenario getGameScenario()
    {
        return gameScenario;
    }

    public void setGameScenario(GameScenario gameScenario)
    {
        this.gameScenario = gameScenario;
    }

    public ArrayList<Hero> getHeros()
    {
        return heros;
    }

    public void setHeros(ArrayList<Hero> heros)
    {
        this.heros = heros;
    }

    public ArrayList<Hero> getEnemyHeros()
    {
        return enemyHeros;
    }

    public void setEnemyHeros(ArrayList<Hero> enemyHeros)
    {
        this.enemyHeros = enemyHeros;
    }

    public ObjectInputStream getIn()
    {
        return in;
    }

    public void setIn(ObjectInputStream in)
    {
        this.in = in;
    }

    public ObjectOutputStream getOut()
    {
        return out;
    }

    public void setOut(ObjectOutputStream out)
    {
        this.out = out;
    }

    public NetworkHandler getNtwhandler()
    {
        return ntwhandler;
    }

    public void setNtwhandler(NetworkHandler ntwhandler)
    {
        this.ntwhandler = ntwhandler;
    }

    public HostJoin getHostJoin()
    {
        return hostJoin;
    }

    public void setHostJoin(HostJoin hostJoin)
    {
        this.hostJoin = hostJoin;
    }

    public CommonMsg getCommonMsg()
    {
        return commonMsg;
    }

    public void setCommonMsg(CommonMsg commonMsg)
    {
        this.commonMsg = commonMsg;
    }

    public int getChoice()
    {
        return choice;
    }

    public void setChoice(int choice)
    {
        this.choice = choice;
    }

    public Scanner getScanner()
    {
        return scanner;
    }

    public void setScanner(Scanner scanner)
    {
        this.scanner = scanner;
    }

    public Callable<Object> getStartFightingss()
    {
        return startFightingss;
    }

    public void setStartFightingss(Callable<Object> startFightingss)
    {
        this.startFightingss = startFightingss;
    }

    public int getMyImmortalityPotionNum()
    {
        return myImmortalityPotionNum;
    }

    public void setMyImmortalityPotionNum(int myImmortalityPotionNum)
    {
        this.myImmortalityPotionNum = myImmortalityPotionNum;
    }

    public int getEnemyImmortalityPotionNum()
    {
        return enemyImmortalityPotionNum;
    }

    public void setEnemyImmortalityPotionNum(int enemyImmortalityPotionNum)
    {
        this.enemyImmortalityPotionNum = enemyImmortalityPotionNum;
    }
}
