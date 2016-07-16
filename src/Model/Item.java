package Model;
/*
    Must check with teammate
*/

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

public abstract class Item implements Serializable
{
    protected int itemSize;
    protected String name;
    protected Hero itemHolder;
    protected String itemDescription;

    public String getSuccessMessage()
    {
        return successMessage;
    }

    public void setSuccessMessage(String successMessage)
    {
        this.successMessage = successMessage;
    }

    public void setItemHolder(Hero itemHolder)
    {
        this.itemHolder = itemHolder;
    }

    protected String successMessage = "";
    protected HashMap<String, Integer> effects;
    protected String target;

    public Item(String name, String target, HashMap<String, Integer> effects)
    {
        setName(name);
        setTarget(target);
        setEffects(effects);
    }


    public int getItemSize()
    {
        return itemSize;
    }

    public void setItemSize(int itemSize)
    {
        this.itemSize = itemSize;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
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

    public HashMap<String, Integer> getEffects()
    {
        return effects;
    }

    // ??
    public void setEffects(HashMap<String, Integer> effects)
    {
        this.effects = effects;
    }

    public String getTarget()
    {
        return target;
    }

    public void setTarget(String target)
    {
        this.target = target;
    }

    public abstract void takeEffect(ArrayList<Warrior> warriors);

    public ArrayList<String> printSuccessMessage(ArrayList<Warrior> targets, String heroName)
    {
        ArrayList<String> log = new ArrayList<>();
        for (String effectFormula : effects.keySet())
        {
            for (Warrior target1 : targets)
            {
//                System.out.println(heroName + " " + successMessage + " " + target1.getName() + " " + effects.get(effectFormula) + " " + effectFormula);
                log.add(heroName + " " + successMessage + " " + target1.getName() + " " + effects.get(effectFormula) + " " + effectFormula);
            }
        }

        return log;
    }

    public String getItemDescription()
    {
        return itemDescription;
    }

    public void setItemDescription(String itemDescription)
    {
        this.itemDescription = itemDescription;
    }

    //public abstract void normalEffect(String effectType, HashMap<String, Integer> warriorData);

    //public abstract void percentEffect(String effectType, HashMap<String, Integer> warriorData, boolean isMaxPercent);
}