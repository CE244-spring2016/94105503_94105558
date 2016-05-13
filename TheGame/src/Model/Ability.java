package Model;

import Auxiliary.Formula;
import Auxiliary.Luck;

import java.util.ArrayList;
import java.util.HashMap;

public abstract class Ability
{
    protected int cooldownTurn;
    protected int currentUpgradeNum;
    protected String name = "";
    protected String abilityTarget = "";
    protected Hero hero;
    protected ArrayList<Integer> upgradeXPs = new ArrayList<>();
    protected ArrayList<Integer> luckPercents = new ArrayList<>();
    protected ArrayList<Integer> abilityCooldownNums = new ArrayList<>();
    protected ArrayList<HashMap<String, Integer>> requiredAbilities = new ArrayList<>();
    protected HashMap<String, ArrayList<Formula>> formulas = new HashMap<>();

    //protected boolean instantEffective; // Maybe we should handle this in a subclass??

    public Ability(String name, String abilityTarget, ArrayList<Integer> upgradeXPs, ArrayList<Integer> luckPercents,
                   ArrayList<Integer> abilityCooldownNums, ArrayList<HashMap<String, Integer>> requiredAbilities,
                   HashMap<String, ArrayList<Formula>> formulas)
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


    public void castAbility(ArrayList<Warrior> mainTargets, ArrayList<Warrior> secondaryTargets)
    {
        int luckPercent = 0;

        if (luckPercents.size() != 0)
        {
            luckPercent = luckPercents.get(currentUpgradeNum - 1);
        }

        if (Luck.isLikely(luckPercent))
        {
            castWithLuck(mainTargets, secondaryTargets);
        } else
        {
            castWithoutLuck(mainTargets, secondaryTargets);
        }
    }


    private void castWithLuck(ArrayList<Warrior> mainTargets, ArrayList<Warrior> secondaryTargets)
    {
        HashMap<String, Integer> userData = hero.getData();
        for (Warrior warrior : mainTargets)
        {
            HashMap<String, Integer> targetData = new HashMap<>();
            if (warrior instanceof Hero)
            {
                targetData = ((Hero) warrior).getData();
            } else
            {
                targetData = ((Enemy) warrior).getData();
            }

            for (String attribute : formulas.keySet())
            {
                Formula formula = formulas.get(attribute).get(currentUpgradeNum - 1);
                int effectAmount = formula.parseFormula(userData);
                String[] attributeNameParts = attribute.split(" ");

                if (attributeNameParts.length > 1 && attributeNameParts[0].equals("luck") && attributeNameParts[1].equals("cost"))
                {
                    attribute = attribute.substring(10);
                    if (userData.keySet().contains(attribute))
                    {
                        int attributeAmount = userData.get(attribute);
                        userData.put(attribute, attributeAmount + effectAmount);
                    }
                } else if (attributeNameParts[0].equals("luck"))
                {
                    attribute = attribute.substring(5);
                    int attributeAmount = targetData.get(attribute);

                    if ((warrior instanceof Enemy) && attribute.equals("current health") && (effectAmount < 0)) // LOL
                    {
                        doDamage(mainTargets, secondaryTargets, effectAmount);
                    } else if (targetData.keySet().contains(attribute))
                    {
                        targetData.put(attribute, attributeAmount + effectAmount);
                    }
                }
            }
        }
    }


    private void castWithoutLuck(ArrayList<Warrior> mainTargets, ArrayList<Warrior> secondaryTargets)
    {
        HashMap<String, Integer> userData = hero.getData();
        for (Warrior warrior : mainTargets)
        {
            HashMap<String, Integer> targetData;
            if (warrior instanceof Hero)
            {
                targetData = ((Hero) warrior).getData();
            } else
            {
                targetData = ((Enemy) warrior).getData();
            }

            for (String attribute : formulas.keySet())
            {
                Formula formula = formulas.get(attribute).get(currentUpgradeNum - 1);
                int effectAmount = formula.parseFormula(userData);
                String[] attributeNameParts = attribute.split(" ");

                if (attributeNameParts[0].equals("cost"))
                {
                    attribute = attribute.substring(5);
                    attribute = "current " + attribute;
                    if (userData.keySet().contains(attribute))
                    {
                        int attributeAmount = userData.get(attribute);
                        userData.put(attribute, attributeAmount + effectAmount);
                    }
                } else if (attributeNameParts[0].equals("nontargetedshare"))
                {
                    hero.setNonTargetedEnemiesShare(effectAmount);
                } else if (attributeNameParts[0].equals("criticalchance"))
                {
                    hero.setCriticalChance(effectAmount);
                } else if (!attributeNameParts[0].equals("luck"))
                {
                    int attributeAmount = targetData.get(attribute);
                    if ((warrior instanceof Enemy) && attribute.equals("current health") && (effectAmount < 0)) // LOL
                    {
                        doDamage(mainTargets, secondaryTargets, effectAmount); // LOL
                    } else if (targetData.keySet().contains(attribute))
                    {
                        attributeAmount = targetData.get(attribute); // MUST CHECK WITH TEAMMATE
                        targetData.put(attribute, attributeAmount + effectAmount);
                    }
                }
            }
        }
    }


    private void doDamage(ArrayList<Warrior> mainTargets, ArrayList<Warrior> secondaryTargets, int damageAmount)
    {
        HashMap<String, Integer> targetData;
        int criticalChance = hero.getCriticalChance(), criticalEffect = 1, secondaryTargetsShare = hero.getNonTargetedEnemiesShare();

        if (Luck.isLikely(criticalChance)) //MUST REMOVE CRITICAL FROM ABILITIES
        {
            criticalEffect = 1;
        }

        for (Warrior target : mainTargets)
        {
            targetData = ((Enemy) target).getData();
            int currentHealth = targetData.get("current health");
            targetData.put("current health", currentHealth + (damageAmount * criticalEffect));
        }

        for (Warrior target : secondaryTargets)
        {
            targetData = ((Enemy) target).getData();
            int currentHealth = targetData.get("current health");
            targetData.put("current health", currentHealth + (damageAmount * criticalEffect * secondaryTargetsShare) / 100);
        }
    }


    public boolean isUpgradeValid()
    {
        if (currentUpgradeNum == upgradeXPs.size())
        {
            System.out.println("This ability cannot be upgraded anymore");
            return false;
        }

        if (Hero.getXP() < upgradeXPs.get(currentUpgradeNum))
        {
            System.out.println("Your experience is insufficient");
            return false;
        }
        HashMap<String, Integer> mustHaveAbilities = new HashMap<>();
        if (requiredAbilities.size() > 0) // LOL
        {
            mustHaveAbilities = requiredAbilities.get(currentUpgradeNum);
        }


        for (String mustHaveAbilityName : mustHaveAbilities.keySet())
        {
            Ability ability = hero.findAbility(mustHaveAbilityName);

            if (ability == null)
            {
                System.out.println("Required abilities aren't acquired");
                return false;
            } else
            {
                if (ability.getCurrentUpgradeNum() < mustHaveAbilities.get(mustHaveAbilityName))
                {
                    System.out.println("Required abilities aren't acquired");
                    return false;
                }
            }
        }

        return true;
    }


    public boolean isCastValid() // LOL
    {
        if(currentUpgradeNum == 0)
        {
            System.out.println("You have not yet acquired this ability");
            return false;
        }

        HashMap<String, Integer> userData = hero.getData();
        for (String attribute : formulas.keySet())
        {
            if (attribute.equals("cost health"))
            {
                continue;
            }
            String[] attributeNameParts = attribute.split(" ");

            if (attributeNameParts[0].equals("cost"))
            {
                Formula formula = formulas.get(attribute).get(currentUpgradeNum - 1);
                attribute = attribute.substring(5);
                attribute = "current " + attribute; // LOL
                if (userData.get(attribute) < (-1) * (formula.parseFormula(userData))) // LOL
                {
                    System.out.println("you don't have enough " + attributeNameParts[1]);
                    return false;
                }
            }
        }

        if (cooldownTurn != 0)
        {
            System.out.println("Your desired ability is still in cooldown");
            return false;
        }

        return true;
    }
}
