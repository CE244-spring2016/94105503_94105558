package Model;

import Auxiliary.Formula;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Vahid on 5/5/2016.
 */
public abstract class Ability
{
    protected int cooldownTurn;
    protected int currentUpgradeNum;
    protected String name = new String();
    protected String abilityTarget = new String();
    protected Hero hero;
    protected ArrayList<Integer> upgradeXPs = new ArrayList<>();
    protected ArrayList<Integer> luckPercents = new ArrayList<>();
    protected ArrayList<Integer> abilityCooldownNums = new ArrayList<>();
    protected ArrayList<HashMap<String, Integer>> requiredAbilities = new ArrayList<>();
    protected HashMap<String, ArrayList<Formula>> formulas = new HashMap<>();

    protected boolean instantEffective; // Maybe we should handle this in a subclass??

    public Ability(String name, String abilityTarget,ArrayList<Integer> upgradeXPs, ArrayList<Integer> luckPercents,
                   ArrayList<Integer> abilityCooldownNums, ArrayList<HashMap<String, Integer>> requiredAbilities,
                   HashMap<String, ArrayList<Formula>> formulas, boolean instantEffective)
    {
        cooldownTurn = 0;
        currentUpgradeNum = 0;
        setName(name);
        setAbilityTarget(abilityTarget);
        setUpgradeXPs(upgradeXPs);
        setLuckPercents(luckPercents);
        setAbilityCooldownNums(abilityCooldownNums);
        setRequiredAbilities(requiredAbilities);
        setFormulas(formulas);
        setInstantEffective(instantEffective);
    }

    public int getCooldownTurn()
    {
        return cooldownTurn;
    }

    public void setCooldownTurn(int cooldownTurn)
    {
        this.cooldownTurn = cooldownTurn;
    }

    public int getCurrentUpgradeNum()
    {
        return currentUpgradeNum;
    }

    public void setCurrentUpgradeNum(int currentUpgradeNum)
    {
        this.currentUpgradeNum = currentUpgradeNum;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getAbilityTarget()
    {
        return abilityTarget;
    }

    public void setAbilityTarget(String abilityTarget)
    {
        this.abilityTarget = abilityTarget;
    }

    public Hero getHero()
    {
        return hero;
    }

    public void setHero(Hero hero)
    {
        this.hero = hero;
    }

    public ArrayList<Integer> getUpgradeXPs()
    {
        return upgradeXPs;
    }

    public void setUpgradeXPs(ArrayList<Integer> upgradeXPs)
    {
        this.upgradeXPs = upgradeXPs;
    }

    public ArrayList<Integer> getLuckPercents()
    {
        return luckPercents;
    }

    public void setLuckPercents(ArrayList<Integer> luckPercents)
    {
        this.luckPercents = luckPercents;
    }

    public ArrayList<Integer> getAbilityCooldownNums()
    {
        return abilityCooldownNums;
    }

    public void setAbilityCooldownNums(ArrayList<Integer> abilityCooldownNums)
    {
        this.abilityCooldownNums = abilityCooldownNums;
    }

    public ArrayList<HashMap<String, Integer>> getRequiredAbilities()
    {
        return requiredAbilities;
    }

    public void setRequiredAbilities(ArrayList<HashMap<String, Integer>> requiredAbilities)
    {
        this.requiredAbilities = requiredAbilities;
    }

    public HashMap<String, ArrayList<Formula>> getFormulas()
    {
        return formulas;
    }

    public void setFormulas(HashMap<String, ArrayList<Formula>> formulas)
    {
        this.formulas = formulas;
    }

    public boolean isInstantEffective()
    {
        return instantEffective;
    }

    public void setInstantEffective(boolean instantEffective)
    {
        this.instantEffective = instantEffective;
    }
}
