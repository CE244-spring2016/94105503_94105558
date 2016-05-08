package Model;/*
    InstantEffectItems are only for Heros
	
	It is probobly done
*/

import java.util.ArrayList;
import java.util.HashMap;

public class InstantEffectItem extends Item
{
    public void takeEffect(ArrayList<Warrior> targets)
    {
        HashMap<String, Integer> effects = getEffects();

        for (String effectType : effects.keySet()) // Check every effect
        {
            for (Warrior warrior : targets)        // Check every Warrior
            {
                //classData in the UML :| probably should change the name
                HashMap<String, Integer> warriorData = ((Hero) warrior).getData();
                if (effectType.startsWith("percent"))
                {
                    percentEffect(effectType, warriorData/*, true*/);
                } else
                {
                    normalEffect(effectType, warriorData);
                }
            }
        }
    }


    public void percentEffect(String effectType, HashMap<String, Integer> warriorData/*, boolean isMaxPercent*/)
    {
        HashMap<String, Integer> effects = getEffects();
        Integer effectAmount = effects.get(effectType);

        effectType = effectType.substring(8);

        Integer dataAmount = warriorData.get(effectType)/*, helperAmount = warriorData.get(helper)*/;
        warriorData.put(effectType, dataAmount + (dataAmount / 100) * effectAmount);
    }


    public void normalEffect(String effectType, HashMap<String, Integer> warriorData)
    {
        HashMap<String, Integer> effects = getEffects();

        Integer dataAmount = warriorData.get(effectType), effectAmount = effects.get(effectType);

        warriorData.put(effectType, dataAmount + effectAmount);
    }
}