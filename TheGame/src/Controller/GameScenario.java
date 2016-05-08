package Controller;

import Auxiliary.Formula;
import Auxiliary.StringParser;
import Model.Ability;
import Model.Hero;
import Model.SingleTargetAbility;
import Model.Warrior;

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
            Hero hero = new Hero(/*heroname,*/ heroClassName, heroData, inventorySize, abilities);
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
        System.out.println("Eley (fighter) – Chrome (fighter) – Meryl (Supporter) – Bolti (Supporter)");
        String order = stringParser.normalizer(this.scanner.next());
        while (!order.equals("done"))
        {
            if (order.equals("help"))
            {
                System.out.println("(Class name) + ?\uF0E0 (class description)");
                System.out.println("(hero name) + ? \uF0E0 (hero description)");
            }
            if (order.equals("again"))
            {
                System.out.println("Eley (fighter) – Chrome (fighter) – Meryl (Supporter) – Bolti (Supporter)");
            }
            switch (order)
            {
                case "fighter?":
                    break;
                case "supporter?":
                    break;
                case "eley?":
                    break;
                case "chrome?":
                    break;
                case "meryl?":
                    break;
                case "bolti?":
                    break;

            }
        }
    }


    public void tellStory(int storyPart)
    {
        String story = userInterface.getGameStory().get(storyPart);
        System.out.println(story);
    }


    public void showEnemyData()
    {

    }


    public void startUpgrading()
    {
        String command = userInterface.getUpgradeCommand();
        while (!command.equals("Done"))
        {
            // do the job

            command = userInterface.getUpgradeCommand();
        }
    }


    public void shopping()
    {
        String command = userInterface.getShoppingCommand();
        while (!command.equals("Done"))
        {
            // do the job

            command = userInterface.getShoppingCommand();
        }
    }


    public void startFighting(int i)
    {

    }
}