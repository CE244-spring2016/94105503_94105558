package Model;

import Auxiliary.Formula;

import java.util.ArrayList;
import java.util.HashMap;


public class ActiveAbility extends Ability
{
	public ActiveAbility(String abilityName, String abilityTarget, ArrayList<Integer> upgradeXP, ArrayList<Integer> luckPercents,
                         ArrayList<Integer> abilityCooldownNums, ArrayList<HashMap<String, Integer>> requiredAbilities,
                         HashMap<String, ArrayList<Formula>> formulas, String abilityDescription)
    {
        super(abilityName, abilityTarget, upgradeXP, luckPercents, abilityCooldownNums, requiredAbilities, formulas, abilityDescription);
    }
}
