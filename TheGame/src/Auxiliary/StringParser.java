package Auxiliary;

import Model.Enemy;
import Model.Hero;

import java.util.ArrayList;

/**
 * Created by Vahid on 5/5/2016.
 */
enum commandsOrder
{
    heroAttack,
    useItem,
    Description,
    heroCastAbility,
    itemSell,
    itemBuy,
    heroAbilityDescription,
    acquireAbility;
}

public class StringParser
{
    ArrayList<Hero> heros = new ArrayList<>();

    //it smallize all the characters
    public String normalizer(String input)
    {
        String result;
        result = input.toLowerCase();
        //result = result.replaceAll("\\s+","");
        return result;
    }

    public void parseOrder(String command)
    {
        if (whatIsOrder(command) != null)
        {
            switch (whatIsOrder(command))
            {
                case heroAttack:
                    break;
                case useItem:
                    break;
                case Description:
                    break;
                case heroAbilityDescription:
                    break;
                case itemBuy:
                    break;
                case itemSell:
                    break;
                case heroCastAbility:
                    break;
                case acquireAbility:
                    break;
                default:
                    break;

            }
        } else
        {
            return "";
        }
        return "";
    }

    //bre tu gamcenaro

    private commandsOrder whatIsOrder(String command)
    {
        String[] commands = command.split(" ");
        if (commands[0].equals("acquire") && checkBuyAbility(command))
        {
            return commandsOrder.acquireAbility;
        } else if (commands[0].equals("sell") && checkSellItem(command))
        {
            return commandsOrder.itemSell;
        } else if (commands[0].equals("buy") && checkBuyItem(command))
        {
            return commandsOrder.itemBuy;
        } else if (commands[1].equals("cast") && checkCastAbility(command))
        {
            return commandsOrder.heroCastAbility;
        } else if (commands[1].equals("use") && checkUseItem(command))
        {
            return commandsOrder.useItem;
        } else if(commands[1].equals("?") && checkDescription(command)) {
            return commandsOrder.Description;
        } else if(commands[1].equals("attack") && checkHeroAttack(command)) {
            return commandsOrder.heroAttack;
        } else if(commands[2].equals("?") && checkHeroAbilityDescription(command)) {
            return commandsOrder.heroAbilityDescription;
        } else {
            return null;
        }
    }

    private boolean checkHeroAbilityDescription(String command)
    {
        String[] commands = command.split(" ");
        for (int i = 0; i < heros.size(); i++)
        {
            if(heros.get(i).getName().equals(commands[1])) {
                for (int j = 0; j < heros.get(i).getAbilities().size(); j++)
                {
                    if(heros.get(i).getAbilities().get(i).getName().equals(commands[2])) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private boolean checkHeroAttack(String command)
    {
        return false;
    }

    private boolean checkDescription(String command)
    {
        return false;
    }

    private boolean checkUseItem(String command)
    {
        return false;
    }

    private boolean checkCastAbility(String command)
    {
        return false;
    }

    private boolean checkBuyItem(String command)
    {
        return false;
    }

    private boolean checkSellItem(String command)
    {
        return false;
    }

    private boolean checkBuyAbility(String command)
    {
        return false;
    }
}
