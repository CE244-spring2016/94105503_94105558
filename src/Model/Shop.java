package Model;

import Exceptions.FullInventoryException;
import Exceptions.NotEnoughMoneyException;

import java.io.Serializable;
import java.util.HashMap;


public class Shop implements Serializable
{
    private int inflationValue;
    private HashMap<String, Integer> itemNamesAndCost;

    public Shop(int inflationValue, HashMap<String, Integer> itemNamesAndCost)
    {
        setInflationValue(inflationValue);
        setItemNamesAndCost(itemNamesAndCost);
    }

    public int getInflationValue()
    {
        return inflationValue;
    }

    public void setInflationValue(int inflationValue)
    {
        this.inflationValue = inflationValue;
    }

    public HashMap<String, Integer> getItemNamesAndCost()
    {
        return itemNamesAndCost;
    }

    public void setItemNamesAndCost(HashMap<String, Integer> itemNamesAndCost)
    {
        this.itemNamesAndCost = itemNamesAndCost;
    }

    public String sell(Hero hero, Item item) throws NotEnoughMoneyException, FullInventoryException
    {
        String itemName = item.getName();
        int currentItemCost = itemNamesAndCost.get(itemName);
        if (Hero.getMoney() < itemNamesAndCost.get(itemName))
        {
//            System.out.println("You don’t have enough money");
            throw new NotEnoughMoneyException("You don’t have enough money");
//            return;
        }

        if (item.getItemSize() != 0 && !hero.getInventory().isFreeSpace())
        {
//            System.out.println(hero.getName() + "’s inventory is full");
            throw new FullInventoryException(hero.getName() + "’s inventory is full");
//            return;
        }

        if (itemNamesAndCost.keySet().contains(itemName))
        {
            Hero.setMoney(Hero.getMoney() - currentItemCost);
            hero.buy(item);
            if (item instanceof InflationedItem)
            {
                itemNamesAndCost.put(itemName, currentItemCost + inflationValue);
            }
//            System.out.println(itemName + " bought successfully, your current wealth is: " + Hero.getMoney());
        }

        return itemName + " bought successfully, your current wealth is: " + Hero.getMoney();
    }


    public String buy(Hero hero, Item item)
    {
        int itemSellingValue, itemBuyingValue = itemNamesAndCost.get(item.getName());
        itemSellingValue = (itemBuyingValue / 2);
        if (item instanceof NonInstantEffectItem)
        {
            itemSellingValue = itemSellingValue * ((((NonInstantEffectItem) item).getUseLimit() / ((NonInstantEffectItem) item).getMaxUseLimit()));
        }
        Hero.setMoney(Hero.getMoney() + itemSellingValue);
        hero.sell(item);
        //FK
//        System.out.println(item.getName() + " successfully sold, your current wealth is: " + Hero.getMoney());
        return item.getName() + " successfully sold, your current wealth is: " + Hero.getMoney();
        //FK
    }
}