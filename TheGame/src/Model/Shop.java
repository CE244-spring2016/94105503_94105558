package Model;

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
}