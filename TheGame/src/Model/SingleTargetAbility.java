package Model;

import Auxiliary.Formula;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Vahid on 5/5/2016.
 */
public class SingleTargetAbility extends Ability
{
    public SingleTargetAbility(String abilityName, String abilityTarget, ArrayList<Integer> upgradeXP, ArrayList<Integer> luckPercents, ArrayList<Integer> abilityCooldownNums, ArrayList<HashMap<String, Integer>> requiredAbilities, HashMap<String, ArrayList<Formula>> formulas, boolean instantEffect)
    {
        super(abilityName, abilityTarget, upgradeXP, luckPercents, abilityCooldownNums, requiredAbilities, formulas, instantEffect);
    }
}
