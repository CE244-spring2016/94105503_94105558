package Model;

public class InflationedItem extends InstantEffectItem
{
    public InflationedItem(String name, String target, HashMap<String, Integer> effects)
    {
		super(name, target, effects);
		setItemSize(0);
    }
}