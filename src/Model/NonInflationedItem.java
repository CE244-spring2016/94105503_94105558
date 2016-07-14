package Model;/*
    HashMap in the constructor?
*/

import java.util.HashMap;

public class NonInflationedItem extends InstantEffectItem
{
    public NonInflationedItem(String name, String target, HashMap<String, Integer> effects)
    {
		super(name, target, effects);
		setItemSize(1);
    }
}