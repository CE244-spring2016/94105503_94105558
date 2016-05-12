package Model;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Vahid on 5/5/2016.
 */
public abstract class Enemy extends Warrior
{
    protected int ID;
    protected String target;
    protected HashMap<String, Integer> data;

    public Enemy(String name, String target, HashMap<String, Integer> data)
    {
        setName(name);
        setID(0);
        setTarget(target);
        setData(data);
    }

    public HashMap<String, Integer> getData()
    {
        return data;
    }

    public void setData(HashMap<String, Integer> data)
    {
        this.data = data;
    }

    public void setTarget(String target)
    {
        this.target = target;
    }

    public int getID()
    {
        return ID;
    }

    public String getTarget()
    {
        return target;
    }

    public void setID(int ID)
    {
        this.ID = ID;
    }


    public String getFullName()
    {
        String fullName = "";

        if (this instanceof NormalEnemy)
        {
            //FK
            fullName = ((NormalEnemy) this).getVersion()+"-" + name;
            if (ID != 0)
            {
                fullName = fullName + "-"+ Integer.toString(ID);
            }
        } else if (this instanceof BossEnemy)
        {
            fullName = name;
            if (ID != 0)
            {
                fullName = fullName + "-" + Integer.toString(ID);
            }
        }
        //FK

        return fullName;
    }

    public abstract void startAMove(ArrayList<Hero> heros, ArrayList<Enemy> allies);
}
