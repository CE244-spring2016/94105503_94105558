package Model;

/**
 * Created by Vahid on 5/5/2016.
 */
public class ActiveAbility extends Ability
{
	public PassiveAbility(String abilityName, String abilityTarget, ArrayList<Integer> upgradeXP, ArrayList<Integer> luckPercents, ArrayList<Integer> abilityCooldownNums, ArrayList<HashMap<String, Integer>> requiredAbilities, HashMap<String, ArrayList<Formula>> formulas)
    {
        super(abilityName, abilityTarget, upgradeXP, luckPercents, abilityCooldownNums, requiredAbilities, formulas);
    }
}
