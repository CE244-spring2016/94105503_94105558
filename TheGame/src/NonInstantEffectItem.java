/*
	MAIN PROBLEM
	
	Not being able to handle stuff like:
		reducing 10% of max health from current health
		
	NEED A CONNECTION BETWEEN MAX AND CURRENT ATTRIBUTES
	
	adding the key "max"
	
	I think it's fixed
*/

public class NonInstantEffectItem extends Item
{
	private int cost;
	private int useLimit;
	
	public NonInstantEffectItem(String name, int size, int cost, int useLimit)
	{
		setName(name);
		setItemSize(size);
		setCost(cost);
		setUseLimit(useLimit);
	}
	
	
	public void setCost(int cost)
	{
		this.cost = cost;
	}
	
	
	public int getCost()
	{
		return cost;
	}
	
	
	public void setUseLimit(int useLimit)
	{
		this.useLimit = useLimit;
	}
	
	
	public int getUseLimit()
	{
		return useLimit;
	}
	
	
	public void takeEffect(ArrayList<Warrior> targets)
	{
		HashMap<String, Integer> effects = getEffects();
		
		for(String effectType : effects.keySet()) // Check every effect
		{
			for(Warrior warrior : targets)        // Check every Warrior
			{
				//classData in the UML :| probobly should change the name
				if(warrior instanceof Hero)
				{
					HashMap<String, Integer> warriorData = ((Hero)warrior).getData();
				}
				else if(warrior instanceof Enemy)
				{
					HashMap<String, Integer> warriorData = ((Enemy)warrior).getData(); // maybe we should put data on the enemy class?
				}
				
				if(effectType.startsWith("max percent")
				{
					//effectType = effectType.substring(7);     // removing the "reduce " from the begining
					
					percentEffect(effectType, warriorData, true);
				}
				else if(effectType.startsWith("percent")
				{
					percentEffect(effectType, warriorData, false);
				}
				else
				{
					increasingEffect(effectType, warriorData);
				}
			}
		}
	}
	
	
	public void percentEffect(String effectType, HashMap<String, Integer> warriorData, boolean isMaxPercent)
	{
		HashMap<String, Integer> effects = getEffects();
		Integer effectAmount = effects.get(effectType);
		
		if(isMaxPercent)
		{
			effectType = effectType.substring(12);
			helper = "max " + effectType;
		}
		else
		{
			effectType = effectType.substring(8);
			helper = effectType;
		}
		
		
		Integer dataAmount = warriorData.get(effectType), helperAmount = warriorData.get(helper);
		warriorData.put(effectType, dataAmount + (helperAmount / 100) * effectAmount);
	}
	
	
	public void normalEffect(String effectType, HashMap<String, Integer> warriorData)
	{
		HashMap<String, Integer> effects = getEffects();
		
		Integer dataAmount = warriorData.get(effectType), effectAmount = effects.get(effectType);
		
		warriorData.put(effectType, dataAmount + effectAmount);
	}
}