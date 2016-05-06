package Model;

public class InflationedItem extends InstantEffectItem
{
	// DEALING WITH COST!!!
	
	
	public InflationedItem(/*HashMap<String, Integer> effects, */String name, int size)
	{
		setItemSize(size);
		setName(name);
		// We won't add all the effects together in the constructor, we will add one by one
	}
}