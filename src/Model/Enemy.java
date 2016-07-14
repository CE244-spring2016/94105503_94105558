package Model;

import java.util.ArrayList;
import java.util.HashMap;


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


    public String getFullName() // LOL
    {
        String fullName = "";

        if (this instanceof NormalEnemy)
        {
            fullName = ((NormalEnemy) this).getVersion() + "-" + name;
            if (ID != 0)
            {
                fullName += "-" + Integer.toString(ID);
            }
        } else if (this instanceof BossEnemy)
        {
            fullName = name;
            if (ID != 0)
            {
                fullName += Integer.toString(ID);
            }
        }

        return fullName;
    }

    public abstract ArrayList<String> startAMove(ArrayList<Hero> heros, ArrayList<Enemy> allies);
}
