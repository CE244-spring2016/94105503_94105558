package Auxiliary;

import Model.Enemy;
import Model.Hero;

import java.util.ArrayList;

import static Auxiliary.commandsOrder.*;

/**
 * Created by Vahid on 5/5/2016.
 */
enum commandsOrder {
    heroAttack,
    userItem,
    itemDescription,
    abilityDescription,
    heroDescription,
    enemyDescription,
    heroCastAbility,
    itemSell,
    itemBuy,
    heroAbilityDescription,
    acquireAbility,
    heroClassDescription;
}
public class StringParser
{
    //it smallize all the characters and sticks all of them together
    public String normalizer(String input) {
        String result;
        result = input.toLowerCase();
        result = result.replaceAll("\\s+","");
        return result;
    }
    public void parseOrder(String command) {
        if(whatIsOrder(command) != null)
        {
            switch (whatIsOrder(command))
            {
                case heroAttack:
                    break;
                case userItem:
                    break;
                case itemDescription:
                    break;
                case abilityDescription:
                    break;
                case heroAbilityDescription:
                    break;
                case heroDescription:
                    break;
                case enemyDescription:
                    break;
                case itemBuy:
                    break;
                case itemSell:
                    break;
                case heroCastAbility:
                    break;
                case acquireAbility:
                    break;
                case heroClassDescription:
                    break;
                default:
                    break;

            }
        }
        else {
            return "";
        }
        return "";
    }

    //bre tu gamcenaro

    private commandsOrder whatIsOrder(String command)
    {
        return null;
    }

    public void introduceHeros(ArrayList<Hero> heros)
    {

    }

    public void showEnemyData(ArrayList<Enemy> enemis)
    {

    }

    public void heroUpgrading(ArrayList<Hero> heros)
    {

    }

    public void shoping(ArrayList<Hero> heros)
    {

    }
}
