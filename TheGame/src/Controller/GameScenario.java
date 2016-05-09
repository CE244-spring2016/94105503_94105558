package Controller;

import Auxiliary.Formula;
import Auxiliary.StringParser;
import Model.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class GameScenario
{
    UserInterface userInterface;

    StringParser stringParser;
    ArrayList<Warrior> warrior; // Is this needed?
    ArrayList<Ability> abilities = new ArrayList<>();
    Scanner scanner;
    private ArrayList<Hero> heros = new ArrayList<>();
    private ArrayList<Enemy> enemis = new ArrayList<>();

    public GameScenario(UserInterface userInterface, StringParser stringParser, Scanner in)
    {
        this.userInterface = userInterface;
        this.stringParser = stringParser;

        scanner = in;
        ArrayList<String> heroNames = userInterface.getHeroNames();
        for (int i = 0; i < heroNames.size(); i++)
        {
            String heroName = heroNames.get(i);
            String heroClassName = userInterface.getHerosAndTheirClasses().get(heroName);
            HashMap<String, Integer> heroData = new HashMap<>();
            heroData.putAll(userInterface.getHeroClassDatas().get(heroClassName));
            int inventorySize = userInterface.getHeroClassInventorySizes().get(heroClassName);
            ArrayList<String> heroAbilities = new ArrayList<>();
            heroAbilities = userInterface.getHerosAndTheirAbilities().get(heroName);
            heroAbilities.addAll(userInterface.getHeroClassAbilities().get(heroClassName));
            ArrayList<Ability> abilities = new ArrayList<>();
            for (int j = 0; j < heroAbilities.size(); j++)
            {
                String abilityName = heroAbilities.get(j);
                String abilityTarget = userInterface.getAbilityTargets().get(abilityName);
                ArrayList<Integer> upgradeXP = new ArrayList<>();
                upgradeXP = userInterface.getAllAbilityUpgradeXPs().get(abilityName);
                ArrayList<Integer> luckPercents = new ArrayList<>();
                luckPercents = userInterface.getAbilityLuckPercents().get(abilityName);
                ArrayList<Integer> abilityCooldownNums = new ArrayList<>();
                abilityCooldownNums = userInterface.getAllAbilityCooldowns().get(abilityName);
                ArrayList<HashMap<String, Integer>> requiredAbilities = new ArrayList<>();
                requiredAbilities = userInterface.getAllRequiredAbilities().get(abilityName);
                HashMap<String, ArrayList<Formula>> formulas = new HashMap<>();
                formulas.putAll(userInterface.getAllAbiliyFormulas().get(abilityName));
                String tragets = userInterface.getAbilityTargets().get(abilityName);
                boolean isInstantEffect = false;
                if (userInterface.getInstantEffectConditionAbilities().contains(abilityName))
                {
                    isInstantEffect = true;
                }

                if (stringParser.isSingleTarget(tragets))
                {
                    abilities.add(new SingleTargetAbility(abilityName, abilityTarget, upgradeXP, luckPercents,
                            abilityCooldownNums, requiredAbilities, formulas, isInstantEffect));
                }
            }
            Hero hero = new Hero(heroName, heroClassName, heroData, inventorySize, abilities);
            warrior.add(hero);
            for (int j = 0; j < abilities.size(); j++)
            {
                abilities.get(i).setHero(hero);
            }
        }
        //creating objects like hero , enemy ,...

    }

    public ArrayList<Ability> getAbilities()
    {
        return abilities;
    }

    public void scenario()
    {
        introduceHeros();

        for (int i = 0; i < userInterface.getGameTurns(); i++)
        {
            tellStory(i);
            showEnemyData();
            startUpgrading();
            shopping();
            startFighting(i);
        }

        tellStory(userInterface.getGameTurns());

    }

    public void introduceHeros()
    {
        stringParser.introduceHeros(heros);
        String order = stringParser.normalizer(this.scanner.next());
        while (!order.equals("done"))
        {
            if (order.equals("help"))
            {
                System.out.println("(Class name) + ?\uF0E0 (class description)");
                System.out.println("(hero name) + ? \uF0E0 (hero description)");
            }
            else if (order.equals("again"))
            {
                stringParser.introduceHeros(heros);
            }
            else
            {
               stringParser.parseOrder(order);
            }
           order = stringParser.normalizer(this.scanner.next());
        }
    }


    public void tellStory(int storyPart)
    {
        String story = userInterface.getGameStory().get(storyPart);
        System.out.println(story);
    }


    public void showEnemyData()
    {

        stringParser.showEnemyData(enemis);
        String order = stringParser.normalizer(this.scanner.next());
        while (!order.equals("done"))
        {
            if (order.equals("help"))
            {
                //helping
            }
            else if (order.equals("again"))
            {
                stringParser.showEnemyData(enemis);
            }
            else
            {
                stringParser.parseOrder(order);
            }
            order = stringParser.normalizer(this.scanner.next());
        }

    }


    public void startUpgrading()
    {
        stringParser.heroUpgrading(heros);
        String order = stringParser.normalizer(this.scanner.next());
        while (!order.equals("done"))
        {
            if (order.equals("help"))
            {
                //helping
            } else if (order.equals("again"))
            {
                stringParser.heroUpgrading(heros);
            } else
            {
                stringParser.parseOrder(order);
            }
            order = stringParser.normalizer(this.scanner.next());
        }

    }


    public void shopping()
    {
        stringParser.shoping(heros);
        String order = stringParser.normalizer(this.scanner.next());
        while (!order.equals("done"))
        {
            if (order.equals("help"))
            {
                //helping
            } else if (order.equals("again"))
            {
                stringParser.shoping(heros);
            } else
            {
                stringParser.parseOrder(order);
            }
            order = stringParser.normalizer(this.scanner.next());
        }
    }


    public void startFighting(int i)
    {

    }
}