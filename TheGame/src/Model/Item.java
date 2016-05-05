package Model;/*
	Must check with teammate
*/

import java.util.ArrayList;
import java.util.HashMap;

public abstract class Item
{
	protected int itemSize;
	protected String name;
	protected Hero itemHolder;
	protected HashMap<String, Integer> effects;
	protected String target;
	
	public void setItemSize(int itemSize)
	{
		this.itemSize = itemSize;
	}
	
	
	public int getItemSize()
	{
		return itemSize;
	}
	
	
	public void setName(String name)
	{
		this.name = name;
	}
	
	
	public String getName()
	{
		return name;
	}
	
	/*
		there are 2 choices (because java is pass by value)
		1- make itemHolder a public field
		2- send an array to this method
	*/
	public void setHero(Hero hero)
	{
		itemHolder = hero;
	}
	
	
	public Hero getItemHolder()
	{
		return itemHolder;
	}
	
	
	public void addEffect(String name, Integer size)
	{
		effects.put(name, size);
	}
	
	// ??
	public void setEffects(HashMap<String, Integer> effects)
	{
		this.effects = effects;
	}
	
	
	public HashMap<String, Integer> getEffects()
	{
		return effects;
	}
	
	
	public void setTarget(String target)
	{
		this.target = target;
	}
	
	
	public String getTarget()
	{
		return target;
	}
	
	
	public abstract void takeEffect(ArrayList<Warrior> warriors);
	public abstract void normalEffect(String effectType, HashMap<String, Integer> warriorData);
	public abstract void percentEffect(String effectType, HashMap<String, Integer> warriorData);
}