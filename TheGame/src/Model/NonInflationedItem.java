package Model;/*
    HashMap in the constructor?
*/

public class NonInflationedItem extends InstantEffectItem
{
    public NonInflationedItem(String name, String target, HashMap<String, Integer> effects)
    {
		super(name, target, effects);
		setItemSize(1);
    }
}