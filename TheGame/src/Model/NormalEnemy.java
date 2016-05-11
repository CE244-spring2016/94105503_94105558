package Model;

import java.util.HashMap;

/**
 * Created by Vahid on 5/5/2016.
 */
public class NormalEnemy extends Enemy
{
	String version;
	private HashMap<String, Integer> data;

	public String getVersion()
	{
		return version;
	}

	public void setVersion(String version)
	{
		this.version = version;
	}


	public void setData(HashMap<String, Integer> data)
	{
		this.data = data;
	}


	public NormalEnemy(String name, String version,HashMap<String, Integer> data)
	{
		setID(0);
		setName(name);
		setData(data);
	}
	
	
    public HashMap<String, Integer> getData()
	{
        return data;
    }
	
	public void startAMove(ArrayList<Hero> heros, ArrayList<Enemy> allies)
	{
		ArrayList<Warrior> enemyMoveTargets = new ArrayList<>();
		
		if(target.equals("himself"))
		{
			enemyMoveTargets.add(this);
		}
		else if(target.equals("an ally"))
		{
			int targetIndex = Luck(0, allies.size() - 1);
			enemyMoveTargets.add(allies.get(targetIndex));
		}
		else if(target.equals("all allies"))
		{
			enemyMoveTargets.addAll(allies);
		}
		else if(target.equals("a hero"))
		{
			int targetIndex = Luck(0, enemies.size() - 1);
			enemyMoveTargets.add(enemies.get(targetIndex));
		}
		else if(target.equals("all heros"))
		{
			enemyMoveTargets.addAll(enemies);
		}
		else if(target.equals("everyone"))
		{
			enemyMoveTargets.addAll(allies);
			enemyMoveTargets.addAll(heros);
		}
		else
		{
			//invalid input
		}
		
		makeAMove(enemyMoveTargets);
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
}
