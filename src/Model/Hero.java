package Model;

import Auxiliary.Luck;
import Exceptions.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;


public class Hero extends Warrior implements Serializable
{
    private static int money;
    private static int XP;
    private static int immortalityPotionNum;
    private static ArrayList<Item> teamItems = new ArrayList<>();
    private String heroTrate = "";
    private String heroClassName;
    private ArrayList<Ability> abilities = new ArrayList<>();
    private String attackSuccessMessage = null;
    private Inventory inventory;
    private HashMap<String, Integer> data = new HashMap<>();
    private int criticalChance; // Check with teammate
    private int nonTargetedEnemiesShare; // Check with teammate
    private HashMap<String, ArrayList<HashMap<String, Integer>>> abilityRequirements = new HashMap<>();

    public Hero(String heroName, String heroClassName, HashMap<String, Integer> data,
                int inventorySize, ArrayList<Ability> abilities, HashMap<String, ArrayList<HashMap<String, Integer>>> abilityRequirements)
    {
        setName(heroName);
        setHeroClassName(heroClassName);
        setInventory(new Inventory(inventorySize));
        setAbilities(abilities);
        setData(data);
        setAbilityRequirements(abilityRequirements);
        setHeroTrate();
    }

    public static int getMoney()
    {
        return money;
    }

    public static void setMoney(int money)
    {
        Hero.money = money;
    }

    public static int getXP()
    {
        return XP;
    }

    public static void setXP(int XP)
    {
        Hero.XP = XP;
    }

    public static int getImmortalityPotionNum()
    {
        return immortalityPotionNum;
    }

    public static void setImmortalityPotionNum(int immortalityPotionNum)
    {
        Hero.immortalityPotionNum = immortalityPotionNum;
    }

    public String getHeroTrate()
    {
        setHeroTrate();
        return heroTrate;
    }

    public void setHeroTrate()
    {
        heroTrate = "";
        Set<String> effect = this.data.keySet();
        String[] keys = new String[effect.size()];
        effect.toArray(keys);
        for (String key : keys)
        {
            String[] k = key.split(" ");
            if (k.length > 1)
            {
                if (k[0].equals("current"))
                {
                    int max = searchMax(k[1]);
                    if (max != 0)
                    {
                        heroTrate += k[1] + " : " + this.data.get(key) + " \\ " + max + "\n";
                    }
                }
            } else
            {
                int temp = searchTemp(key);
                int tp = this.data.get(key) + temp;
                heroTrate += key + " : " + (tp);
            }
        }
    }

    private int searchTemp(String s)
    {
        Set<String> effect = this.data.keySet();
        String[] keys = new String[effect.size()];
        effect.toArray(keys);
        for (int i = 0; i < keys.length; i++)
        {
            String[] k = keys[i].split(" ");
            if (k.length > 1)
            {
                if (k[0].equals("temp") && k[1].equals(s))
                {
                    return this.data.get(keys[i]);
                }
            }
        }
        return 0;
    }

    private int searchMax(String s)
    {
        Set<String> effect = this.data.keySet();
        String[] keys = new String[effect.size()];
        effect.toArray(keys);
        for (int i = 0; i < keys.length; i++)
        {
            String[] k = keys[i].split(" ");
            if (k.length > 1)
            {
                if (k[0].equals("max") && k[1].equals(s))
                {
                    return this.data.get(keys[i]);
                }
            }
        }
        return 0;
    }

    public String getAttackSuccessMessage()
    {
        return attackSuccessMessage;
    }

    public void setAttackSuccessMessage(String attackSuccessMessage)
    {
        this.attackSuccessMessage = attackSuccessMessage;
    }

    public int getNonTargetedEnemiesShare()
    {
        return nonTargetedEnemiesShare;
    }

    public void setNonTargetedEnemiesShare(int nonTargetedEnemiesShare)
    {
        this.nonTargetedEnemiesShare = nonTargetedEnemiesShare;
    }

    public int getCriticalChance()
    {
        return criticalChance;
    }

    public void setCriticalChance(int criticalChance)
    {
        this.criticalChance = criticalChance;
    }

    public Inventory getInventory()
    {
        return inventory;
    }


    public void setInventory(Inventory inventory)
    {
        this.inventory = inventory;
    }


    public ArrayList<Ability> getAbilities()
    {
        return abilities;
    }


    public void setAbilities(ArrayList<Ability> abilities)
    {
        this.abilities = abilities;
    }


    public HashMap<String, Integer> getData()
    {
        return data;
    }


    public void setData(HashMap<String, Integer> data)
    {
        this.data = data;
    }


    public String getHeroClassName()
    {
        return heroClassName;
    }


    public void setHeroClassName(String heroClassName)
    {
        this.heroClassName = heroClassName;
    }

    public ArrayList<String> useAbility(String abilityName, ArrayList<Warrior> enemies, ArrayList<Hero> allies, String moreSpecificTarget) throws AbilityNotAcquieredException, AbilityCooldownException, NotStrongEnoughException // LOL
    {
        Ability ability = findAbility(abilityName);
        ArrayList<Warrior> abilityTargets = new ArrayList<>();
        ArrayList<Warrior> secondaryTargets = new ArrayList<>();
        ArrayList<String> result = new ArrayList<>();
        if (ability != null && ability.isCastValid()) // LOL
        {
            String target = ability.getAbilityTarget();
            switch (target)
            {
                case "himself":
                    abilityTargets.add(this);
                    break;
                case "an ally":
                    Hero targetAlly = null;
                    if (moreSpecificTarget.equals("everyone"))
                    {
                        // needs input
                        return result;
                    }
                    for (Hero ally : allies)
                    {
                        if (ally.getName().equals(moreSpecificTarget))
                        {
                            targetAlly = ally;
                            abilityTargets.add(targetAlly);
                        }
                    }

                    if (targetAlly == null)
                    {
                        // invalid name
                        return result;
                    }
                    break;
                case "all allies":
                    abilityTargets.addAll(allies);
                    break;
                case "an enemy":
                    //	Enemy targetEnemy = null;
                    if (moreSpecificTarget.equals("everyone"))
                    {
                        // needs input
                        return result;
                    }
                    for (Warrior enemy : enemies)
                    {
                        if (enemy.getFullName().equals(moreSpecificTarget))
                        {
                            abilityTargets.add(enemy);
                        } else
                        {
                            secondaryTargets.add(enemy);
                        }
                    }

                    if (abilityTargets.size() == 0)
                    {
                        // invalid name
                        return result;
                    }
                    break;
                case "all enemies":
                    abilityTargets.addAll(enemies);
                    break;
                case "everyone":
                    abilityTargets.addAll(allies);
                    abilityTargets.addAll(enemies);
                    break;
                default:
                    //invalid input
                    break;
            }
            if (ability instanceof ActiveAbility)
            {
//                ability.printSuccessMessage(abilityTargets);
                ArrayList<String> targetNames = new ArrayList<>();
                for (Warrior warrior : abilityTargets)
                {
                    targetNames.add(warrior.getName());
                }
                result = ability.getFullAbilitySuccessMessage(targetNames);
            }
            ability.castAbility(abilityTargets, secondaryTargets);
        } else
        {
            //invalid input
        }

        return result;
    }

    public ArrayList<String> useItem(String itemName, ArrayList<Warrior> enemies, ArrayList<Hero> allies, String moreSpecificTarget) // LOL
    {
        Item item = findItem(itemName);
        ArrayList<Warrior> itemTargets = new ArrayList<>();

        if (item != null)
        {
            String target = item.getTarget();
            switch (target)
            {
                case "himself":
                    itemTargets.add(this);
                    break;
                case "an ally":
                    Hero targetAlly = null;
                    if (moreSpecificTarget.equals("everyone"))
                    {
                        // needs input
                        return null;
                    }
                    for (Hero ally : allies)
                    {
                        if (ally.getName().equals(moreSpecificTarget))
                        {
                            targetAlly = ally;
                            itemTargets.add(targetAlly);
                        }
                    }

                    if (targetAlly == null)
                    {
                        System.out.println("Invalid hero name");
                        return null;
                    }
                    break;
                case "all allies":
                    itemTargets.addAll(allies);
                    break;
                case "an enemy":
                    Warrior targetEnemy = null;
                    if (moreSpecificTarget.equals("everyone"))
                    {
                        // needs input
                        return null;
                    }
                    for (Warrior enemy : enemies)
                    {
                        if (enemy.getFullName().equals(moreSpecificTarget))
                        {
                            targetEnemy = enemy;
                            itemTargets.add(enemy);
                        }
                    }

                    if (targetEnemy == null)
                    {
                        System.out.println("Invalid enemy name");
                        return null;
                    }
                    break;
                case "all enemies":
                    itemTargets.addAll(enemies);
                    break;
                case "everyone":
                    itemTargets.addAll(allies);
                    itemTargets.addAll(enemies);
                    break;
                default:
                    //invalid input
                    break;
            }

            item.takeEffect(itemTargets);
            if (item instanceof NonInstantEffectItem)
            {
                return item.printSuccessMessage(itemTargets, this.getName());
            }
        } else
        {
            //invalid input
        }

        return new ArrayList<>();
    }


    public Ability findAbility(String abilityName)
    {
        for (Ability ability : abilities)
        {
            if (ability.getName().equals(abilityName))
            {
                return ability;
            }
        }

        return null;
    }


    public Item findItem(String itemName)
    {
        return inventory.getItem(itemName);
    }


    public void sell(Item item)
    {
        inventory.removeItem(item);
        inventory.setCurrentSize(inventory.getCurrentSize() - item.getItemSize());
        // Is it really done?
    }


    public void buy(Item item)
    {
        inventory.addItem(item);
        inventory.setCurrentSize(inventory.getCurrentSize() + item.getItemSize());
        //Check instanteffectitem in gamescenario DAMNIT
    }

    public ArrayList<String> regularAttack(ArrayList<Warrior> enemies, String specificName) throws NotStrongEnoughException
    {
        int criticalEffect = 1;
        Warrior mainTarget = null;
        ArrayList<Warrior> secondaryTargets = new ArrayList<>();
        ArrayList<String> result = new ArrayList<>();

        for (Warrior enemy : enemies)
        {
            if (enemy.getFullName().equals(specificName))
            {
                mainTarget = enemy;
            } else
            {
                secondaryTargets.add(enemy);
            }
        }

        if (mainTarget == null)
        {
            System.out.println("wrong name");
            return result;
        }

        if (data.get("current EP") < 2)
        {
//            System.out.println("You don’t have enough energy points");
            throw new NotStrongEnoughException("You don’t have enough energy points");
//            return;
        }

        if (Luck.isLikely(criticalChance))
        {
            criticalEffect = 2;
        }

        HashMap<String, Integer> mainTargetData = mainTarget.getData();
        int mainTargetHealth = mainTargetData.get("current health");
        //FK
//        System.out.println(this.getName() + " has successfully attacked " + mainTarget.getFullName() + " with " + ((data.get("attack") + data.get("temp attack")) * criticalEffect) + " attack power");
        setAttackSuccessMessage("your " + this.getName() + " has successfully attacked " + "his " + mainTarget.getFullName() + " with " + ((data.get("attack") + data.get("temp attack")) * criticalEffect) + " attack power");
        result.add(this.getName() + " has successfully attacked " + mainTarget.getFullName() + " with " + ((data.get("attack") + data.get("temp attack")) * criticalEffect) + " attack power");
        //FK
        mainTargetData.put("current health", mainTargetHealth - (data.get("attack") + data.get("temp attack")) * criticalEffect);
        this.getData().put("current EP", this.getData().get("current EP") - 2);
        if (this.getData().get("current EP") < 0)
        {
            this.getData().put("current EP", 0);
        }

        for (Warrior enemy : secondaryTargets)
        {
            HashMap<String, Integer> secondaryTargetData = enemy.getData();
            int secondaryTargetHealth = secondaryTargetData.get("current health");
            secondaryTargetData.put("current health", secondaryTargetHealth - (((data.get("attack") + data.get("temp attack")) * criticalEffect) * nonTargetedEnemiesShare) / 100);
        }

        //Fing done :|

        return result;
    }

    @Override
    protected String getFullName()
    {
        return name;
    }

    public ArrayList<String> upgradeAbility(String abilityName) throws NotEnoughRequiredAbilitiesException, NoMoreUpgradeException, NotEnoughXPException
    {
        Ability ability = findAbility(abilityName);
        ArrayList<String> log = new ArrayList<>();

        if (ability.getCurrentUpgradeNum() == ability.getUpgradeXPs().size())
        {
//            System.out.println("This ability cannot be upgraded anymore");
            throw new NoMoreUpgradeException("This ability cannot be upgraded anymore");
//            return false;
        }

        if (Hero.getXP() < ability.getUpgradeXPs().get(ability.getCurrentUpgradeNum()))
        {
//            System.out.println("Your experience is insufficient");
            throw new NotEnoughXPException("Your experience is insufficient");
//            return false;
        }

        ArrayList<HashMap<String, Integer>> requiredAbilities = abilityRequirements.get(abilityName);
        HashMap<String, Integer> mustHaveAbilities = new HashMap<>();
        if (requiredAbilities != null) // LOL
        {
            mustHaveAbilities = requiredAbilities.get(ability.getCurrentUpgradeNum());
        }


        for (String mustHaveAbilityName : mustHaveAbilities.keySet())
        {
            Ability requieredAbility = findAbility(mustHaveAbilityName);

            if (requieredAbility == null)
            {
//                System.out.println("Required abilities aren't acquired");
                throw new NotEnoughRequiredAbilitiesException("Required abilities aren't acquired");
//                return false;
            } else
            {
                if (requieredAbility.getCurrentUpgradeNum() < mustHaveAbilities.get(mustHaveAbilityName))
                {
//                    System.out.println("Required abilities aren't acquired");
                    throw new NotEnoughRequiredAbilitiesException("Required abilities aren't acquired");
//                    return false;
                }
            }
        }

        ability.setCurrentUpgradeNum(ability.getCurrentUpgradeNum() + 1);

        //bugable no
        Hero.setXP(Hero.getXP() - ability.getUpgradeXPs().get(ability.getCurrentUpgradeNum() - 1));
        if (ability.getCurrentUpgradeNum() == 1)
//                        System.out.printf("%s acquired successfully, your current experience is: %d\n", ability.getName(), Hero.getXP());
            log.add(ability.getName() + " acquired successfully, your current experience is: " + Hero.getXP());
        else if (ability.getCurrentUpgradeNum() > 1)
//                        System.out.printf("%s upgraded successfully, your current experience is: %d\n", ability.getName(), Hero.getXP());
            log.add(ability.getName() + " upgraded successfully, your current experience is: " + Hero.getXP());

        return log;
    }

    public ArrayList<String> findRequirements(String abilityName)
    {
        ArrayList<String> result = new ArrayList<>();
        Ability ability = findAbility(abilityName);
        if (ability.getCurrentUpgradeNum() == ability.getUpgradeXPs().size())
        {
            result.add("No more upgrades available");
        } else
        {
            if (abilityRequirements.get(abilityName) == null || abilityRequirements.get(abilityName).get(ability.getCurrentUpgradeNum()) == null)
            {
                result.add("No requirements for next upgrade");
                return result;
            }

            HashMap<String, Integer> requirements = abilityRequirements.get(abilityName).get(ability.getCurrentUpgradeNum());

            if (requirements.size() == 0)
            {
                result.add("No requirements for next upgrade");
            } else
            {
                result.add("Upgrading this ability requires:");
                for (String requiredAbilityName : requirements.keySet())
                {
                    result.add("Upgrade " + requirements.get(requiredAbilityName) + " of Ability " + requiredAbilityName);
                }
            }
        }

        return result;
    }

    public static void addTeamItem(Item item)
    {
        teamItems.add(item);
    }

    public static ArrayList<Item> getTeamItems()
    {
        return teamItems;
    }

    public HashMap<String, ArrayList<HashMap<String, Integer>>> getAbilityRequirements()
    {
        return abilityRequirements;
    }

    public void setAbilityRequirements(HashMap<String, ArrayList<HashMap<String, Integer>>> abilityRequirements)
    {
        this.abilityRequirements = abilityRequirements;
    }
}