package Model;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by ruhollah on 7/10/2016.
 */
public class NonEffectiveItem extends Item
{

    public NonEffectiveItem(String name, String target, HashMap<String, Integer> effects)
    {
        super(name, target, effects);
        setItemSize(0);
    }

    @Override
    public void takeEffect(ArrayList<Warrior> warriors)
    {

    }
}
