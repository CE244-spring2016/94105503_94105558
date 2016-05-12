package Model;

import Auxiliary.Luck;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Vahid on 5/5/2016.
 */
public class Hero extends Warrior
{
    private static int money;
    private static int XP;
    private static int immortalityPotionNum;


    private String heroClassName;
    private ArrayList<Ability> abilities = new ArrayList<>();
    private Inventory inventory;
    private HashMap<String, Integer> data = new HashMap<>();
	private int criticalChance; // Check with teammate
	private int nonTargetedEnemiesShare; // Check with teammate

    public Hero(String heroName, String heroClassName, HashMap<String, Integer> data, int inventorySize, ArrayList<Ability> abilities)
    {
        setName(heroName);
        setHeroClassName(heroClassName);
        setInventory(new Inventory(inventorySize));
        setAbilities(abilities);
        setData(data);
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
	
	
	public void useAbility(String abilityName, ArrayList<Enemy> enemies, ArrayList<Hero> allies, String moreSpecificTarget)
	{
		Ability ability = findAbility(abilityName);
		ArrayList<Warrior> abilityTargets = new ArrayList<>();
		ArrayList<Warrior> secondaryTargets = new ArrayList<>();
		if(ability != null)
		{
			String target = ability.getAbilityTarget();
			if(target.equals("himself"))
			{
				abilityTargets.add(this);
			}
			else if(target.equals("an ally"))
			{
				Hero targetAlly = null;
				if(moreSpecificTarget.equals("everyone"))
				{
					// needs input
					return;
				}
				for(Hero ally : allies)
				{
					if(ally.getName().equals(moreSpecificTarget))
					{
						abilityTargets.add(targetAlly);
					}
				}
				
				if(targetAlly == null)
				{
					// invalid name
					return;
				}
			}
			else if(target.equals("all allies"))
			{
				abilityTargets.addAll(allies);
			}
			else if(target.equals("an enemy"))
			{
			//	Enemy targetEnemy = null;
				if(moreSpecificTarget.equals("everyone"))
				{
					// needs input
					return;
				}
				for(Enemy enemy : enemies)
				{
					if(enemy.getFullName().equals(moreSpecificTarget))
					{
						abilityTargets.add(enemy);
					}
					else
					{
						secondaryTargets.add(enemy);
					}
				}
				
				if(abilityTargets.size() == 0)
				{
					// invalid name
					return;
				}
			}
			else if(target.equals("all enemies"))
			{
				abilityTargets.addAll(enemies);
			}
			else if(target.equals("everyone"))
			{
				abilityTargets.addAll(allies);
				abilityTargets.addAll(enemies);
			}
			else
			{
				//invalid input
			}
			
			ability.castAbility(abilityTargets, secondaryTargets);
		}
		else
		{
			//invalid input
		}
	}
	
	
	public void useItem(String itemName, ArrayList<Enemy> enemies, ArrayList<Hero> allies, String moreSpecificTarget)
	{
		Item item = findItem(itemName);
		ArrayList<Warrior> itemTargets = new ArrayList<>();
		
		if(item != null)
		{
			String target = item.getTarget();
			if(target.equals("himself"))
			{
				itemTargets.add(this);
			}
			else if(target.equals("an ally"))
			{
				Hero targetAlly = null;
				if(moreSpecificTarget.equals("everyone"))
				{
					// needs input
					return;
				}
				for(Hero ally : allies)
				{
					if(ally.getName().equals(moreSpecificTarget))
					{
						targetAlly = ally;
						itemTargets.add(targetAlly);
					}
				}
				
				if(targetAlly == null)
				{
					// invalid name
					return;
				}
			}
			else if(target.equals("all allies"))
			{
				itemTargets.addAll(allies);
			}
			else if(target.equals("an enemy"))
			{
				Enemy targetEnemy = null;
				if(moreSpecificTarget.equals("everyone"))
				{
					// needs input
					return;
				}
				for(Enemy enemy : enemies)
				{
					if(enemy.getFullName().equals(moreSpecificTarget))
					{
						itemTargets.add(enemy);
					}
					else
					{
						itemTargets.add(enemy);
					}
				}
				
				if(targetEnemy == null)
				{
					// invalid name
					return;
				}
			}
			else if(target.equals("all enemies"))
			{
				itemTargets.addAll(enemies);
			}
			else if(target.equals("everyone"))
			{
				itemTargets.addAll(allies);
				itemTargets.addAll(enemies);
			}
			else
			{
				//invalid input
			}
			
			item.takeEffect(itemTargets);
		}
		else
		{
			//invalid input
		}
	}
	
	
	public Ability findAbility(String abilityName)
	{
		for(Ability ability : abilities)
		{
			if(ability.getName().equals(abilityName))
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
	
	
	public void regularAttack(ArrayList<Enemy> enemies, String specificName)
	{
		int criticalEffect = 1;
		Enemy mainTarget = null;
		ArrayList<Enemy> secondaryTargets = new ArrayList<>();
		for(Enemy enemy : enemies)
		{
			if(enemy.getFullName().equals(specificName))
			{
				mainTarget = enemy;
			}
			else
			{
				secondaryTargets.add(enemy);
			}
		}
		
		if(mainTarget == null)
		{
			System.out.println("wrong name");
			return;
		}
		
		if(data.get("current EP") < 2)
		{
			System.out.println("not enough ep");
			return;
		}
		
		if(Luck.isLikely(criticalChance))
		{
			criticalEffect = 2;
		}
		
		HashMap<String, Integer> mainTargetData = mainTarget.getData();
		int mainTargetHealth = mainTargetData.get("current health");
		mainTargetData.put("current health", mainTargetHealth - (data.get("attack") + data.get("temp attack"))*criticalEffect);
		this.getData().put("current EP", this.getData().get("current EP") - 2);

		for(Enemy enemy : secondaryTargets)
		{
			HashMap<String, Integer> secondaryTargetData = enemy.getData();
			int secondaryTargetHealth = secondaryTargetData.get("current health");
			secondaryTargetData.put("current health", secondaryTargetHealth - (((data.get("attack") + data.get("temp attack"))*criticalEffect) * nonTargetedEnemiesShare) / 100);
		}
		
		//Fing done :|
	}
}
