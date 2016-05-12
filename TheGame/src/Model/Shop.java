package Model;

import java.util.HashMap;

/**
 * Created by Vahid on 5/5/2016.
 */
public class Shop
{
	int inflationValue;
	HashMap<String, Integer> itemNamesAndCost;
	
	public Shop(int inflationValue, HashMap<String, Integer> itemNamesAndCost)
	{
		setInflationValue(inflationValue);
		setItemNamesAndCost(itemNamesAndCost);
	}
	
	
	public void setInflationValue(int inflationValue)
	{
		this.inflationValue = inflationValue;
	}
	
	
	public int getInflationValue()
	{
		return inflationValue;
	}
	
	
	public void setItemNamesAndCost(HashMap<String, Integer> itemNamesAndCost)
	{
		this.itemNamesAndCost = itemNamesAndCost;
	}
	
	
	public HashMap<String, Integer> getItemNamesAndCost()
	{
		return itemNamesAndCost;
	}
	
	
	public void sell(Hero hero, Item item)
	{
		String itemName = item.getName();
		int currentItemCost = itemNamesAndCost.get(itemName);
		if(Hero.getMoney() < itemNamesAndCost.get(itemName))
		{
			System.out.println("You don’t have enough money");
			return;
		}
		
		if(item.getItemSize() != 0 && !hero.getInventory().isFreeSpace())
		{
			System.out.println(hero.getName() +"’s inventory is full");
			return;
		}
		
		if(itemNamesAndCost.keySet().contains(itemName))
		{
			Hero.setMoney(Hero.getMoney() - currentItemCost);
			hero.buy(item);
			System.out.println(itemName + "bought successfully, your current wealth is: " + Hero.getMoney());
			if(item instanceof InflationedItem)
			{
				itemNamesAndCost.put(itemName, currentItemCost + inflationValue);
			}
		}
	}
	
	
	public void buy(Hero hero, Item item)
	{
		int itemSellingValue, itemBuyingValue = itemNamesAndCost.get(item.getName());
		itemSellingValue = (itemBuyingValue / 2);
		if(item instanceof NonInstantEffectItem)
		{
			itemSellingValue = itemSellingValue * ((((NonInstantEffectItem) item).getUseLimit() / ((NonInstantEffectItem) item).getMaxUseLimit()));
		}
		Hero.setMoney(Hero.getMoney() + itemSellingValue);
		hero.sell(item);
	}
}