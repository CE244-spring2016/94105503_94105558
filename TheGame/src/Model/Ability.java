package Model;

import Auxiliary.Formula;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.ForkJoinPool;

/**
 * Created by Vahid on 5/5/2016.
 */
public abstract class Ability 
{
    protected int cooldownTurn;
    protected int currentUpgradeNum;
    protected String name;
    protected String abilityTarget;
    protected Hero hero;
    protected ArrayList<Integer> upgradeXPs;
    protected ArrayList<Integer> luckPercents;
	protected ArrayList<Integer> abilityCooldownNums;
    protected ArrayList<HashMap<String, Integer>> requiredAbilities;
    protected HashMap<String, ArrayList<Formula>> formulas;
    protected boolean instantEffective; // Maybe we should handle this in a subclass??
	
	public Ability(String name, String abilityTarget, Hero hero, ArrayList<Integer> upgradeXPs, ArrayList<Integer> luckPercents, ArrayList<Integer> abilityCooldownNums, ArrayList<HashMap<String, Integer>> requiredAbilities, HashMap<String, ArrayList<Formula>> formulas)
	{
		cooldownTurn = 0;
		currentUpgradeNum = 0;
		setName(name);
		setAbilityTarget(abilityTarget);
		setHero(hero);
		setUpgradeXPs(upgradeXPs);
		setLuckPercents(luckPercents);
		setAbilityCooldownNums(abilityCooldownNums);
		setRequiredAbilities(requiredAbilities);
		setFormulas(formulas);
	}
	
	// Add setter getter please LOL
}
