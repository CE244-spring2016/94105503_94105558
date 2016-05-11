package Model;

/**
 * Created by Vahid on 5/5/2016.
 */
public class BossEnemy extends Enemy
{
	int angerPoint;
	String target;
	HashMap<String, Integer> angerEffects = new HashMap<>();
	HashMap<String, String> earlyTurnEffects = new HashMap<>();
	
	public BossEnemy(String name, String target, int angerPoint, HashMap<String, Integer> angerEffects, HashMap<String, String> earlyTurnEffects)
	{
		// set and get please...
	}
	
	
	public void startAMove(ArrayList<Hero> heros, ArrayList<Enemy> allies)
	{
		String[] targetNameParts = target.split(" ");
		ArrayList<Warrior> moveTargets = new ArrayList<>();
		if(target.equals("all allies"))
		{
			moveTargets.addAll(allies);
		}
		else if(target.equals("all heros"))
		{
			moveTargets.addAll(heros);
		}
		else if(targetNameParts.length == 2 && targetNameParts[1].equals("hero"))
		{
			ArrayList<Integer> targetIndexes = getRandomNums(Integer.parseInt(targetNameParts[0]), heros.size() - 1);
			for(int i = 0; i < targetIndexes; i++)
			{
				moveTargets.add(heros.get(targetIndexes.get(i)));
			}
		}
		else if(targetNameParts.length == 2 && targetNameParts[1].equals("enemy"))
		{
			ArrayList<Integer> targetIndexes = getRandomNums(Integer.parseInt(targetNameParts[0]), allies.size() - 1);
			for(int i = 0; i < targetIndexes; i++)
			{
				moveTargets.add(allies.get(targetIndexes.get(i)));
			}
		}
		else if(target.equals("everyone"))
		{
			moveTargets.addAll(allies);
			moveTargets.addAll(heros);
		}
		
		makeAMove(moveTargets);
	}
	
	
	public void makeAMove(ArrayList<Warrior> targets)
	{
		for(Warrior warrior : targets)
		{
			if(warrior instanceof Hero)
			{
				HashMap<String, Integer> warriorData = ((Hero)warrior).getData();
			}
			else if(warrior instanceof Enemy)
			{
				HashMap<String, Integer> warriorData = ((Enemy)warrior).getData();
			}
			for(String moveAttribute : data.keySet())
			{
				String[] attributeNameParts = moveAttribute.split(" ");
				if(attributeNameParts[0].equals("move"))
				{
					int effectAmount = data.get(moveAttribute);
					if(attributeNameParts[1].equals("attack"))
					{
						warriorData.put("current health", warriorData.get("current health") - effectAmount);
					}
					else if(attributeNameParts[1].equals("heal"))
					{
						warriorData.put("current health", warriorData.get("current health") + effectAmount);
					}
				}
			}
		}
	}
	
	
	public ArrayList<Integer> getRandomNums(int x, int y)
	{
		ArrayList<Integer> result = new ArrayList<>();
		
		while(result.size() != x)
		{
			int randomNum = Luck.getRandom(0, y);
			
			if(!result.contains(randomNum))
			{
				result.add(randomNum);
			}
		}
		
		return result;
	}
	
	
	public void earlyTurnEffect(ArrayList<Hero> heros)
	{
		for(Hero hero : heros)
		{
			HashMap<String, Integer> heroData = hero.getData();
			for(String attribute : earlyTurnEffects.keySet())
			{
				int currentAmount = heroData.get(attribute);
				int effectAmount = earlyTurnEffects.get(attribute);
				heroData.put(attribute, currentAmount - effectAmount);
			}
		}
	}
	
	
	public void getAngry()
	{
		for(String angerEffect : angerEffects.keySet())
		{
			int effectAmount = angerEffects.get(angerEffect);
			int currentAmount = data.get(angerEffect);
			data.put(angerEffect, currentAmount + effectAmount);
		}
	}
}
