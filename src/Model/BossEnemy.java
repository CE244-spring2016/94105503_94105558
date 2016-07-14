package Model;

import Auxiliary.Luck;

import java.util.ArrayList;
import java.util.HashMap;


public class BossEnemy extends Enemy
{
	private int angerPoint;
	//String target;
	private HashMap<String, Integer> angerEffects = new HashMap<>();
	private HashMap<String, String> earlyTurnEffects = new HashMap<>();
	private boolean angry = false;

	public BossEnemy(String name, String target, int angerPoint, HashMap<String, Integer> angerEffects, HashMap<String, String> earlyTurnEffects, HashMap<String, Integer> data)
	{
		super(name, target, data);
		setAngerEffects(angerEffects);
		setEarlyTurnEffects(earlyTurnEffects);
		setAngerPoint(angerPoint);
	}

	public HashMap<String, String> getEarlyTurnEffects()
	{
		return earlyTurnEffects;
	}

	public void setEarlyTurnEffects(HashMap<String, String> earlyTurnEffects)
	{
		this.earlyTurnEffects = earlyTurnEffects;
	}

	public HashMap<String, Integer> getAngerEffects()
	{
		return angerEffects;
	}

	public void setAngerEffects(HashMap<String, Integer> angerEffects)
	{
		this.angerEffects = angerEffects;
	}

	public int getAngerPoint()
	{
		return angerPoint;
	}

	public void setAngerPoint(int angerPoint)
	{
		this.angerPoint = angerPoint;
	}

	public ArrayList<String> startAMove(ArrayList<Hero> heros, ArrayList<Enemy> allies)
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
			for (Integer targetIndexe : targetIndexes)
			{
				moveTargets.add(heros.get(targetIndexe));
			}
		}
		else if(targetNameParts.length == 2 && targetNameParts[1].equals("enemy"))
		{
			ArrayList<Integer> targetIndexes = getRandomNums(Integer.parseInt(targetNameParts[0]), allies.size() - 1);
			for (Integer targetIndexe : targetIndexes)
			{
				moveTargets.add(allies.get(targetIndexe));
			}
		}
		else if(target.equals("everyone"))
		{
			moveTargets.addAll(allies);
			moveTargets.addAll(heros);
		}

		return makeAMove(moveTargets);
	}

	private ArrayList<String> makeAMove(ArrayList<Warrior> targets)
	{
        ArrayList<String> log = new ArrayList<>();

		for(Warrior warrior : targets)
		{
			HashMap<String, Integer> warriorData = new HashMap<>();
			if(warrior instanceof Hero)
			{
				warriorData = ((Hero)warrior).getData();
			}
			else if(warrior instanceof Enemy)
			{
				warriorData = ((Enemy) warrior).getData();
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

//			System.out.println("Collector just attacked " + warrior.getName() + " with " + data.get("move attack") + " power");
            log.add("Collector just attacked " + warrior.getName() + " with " + data.get("move attack") + " power");
		}

        return log;
	}


	private ArrayList<Integer> getRandomNums(int x, int y)
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


	public ArrayList<String> earlyTurnEffect(ArrayList<Hero> heros)
	{
        ArrayList<String> result = new ArrayList<>();

		for(Hero hero : heros)
		{
			HashMap<String, Integer> heroData = hero.getData();
			for(String attribute : earlyTurnEffects.keySet())
			{
				int currentAmount = heroData.get(attribute);
				String[] effectRange = earlyTurnEffects.get(attribute).split(" ");
				int effectAmount = Luck.getRandom(Integer.parseInt(effectRange[0]), Integer.parseInt(effectRange[2]));
				heroData.put(attribute, currentAmount - effectAmount);
//				System.out.println(getName() + " just burned " + effectAmount + " energy Points from " + hero.getName());
                result.add(getName() + " just burned " + effectAmount + " energy Points from " + hero.getName());
			}
		}

        return result;
	}


	public void getAngry()
	{
		for(String angerEffect : angerEffects.keySet())
		{
			int effectAmount = angerEffects.get(angerEffect);
			int currentAmount = data.get(angerEffect);
			data.put(angerEffect, currentAmount + effectAmount);
		}

		angry = true;
	}


	public boolean isAngry()
	{
		return angry;
	}

	public void setAngry(boolean angry)
	{
		this.angry = angry;
	}
}
