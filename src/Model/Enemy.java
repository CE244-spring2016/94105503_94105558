package Model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;


public abstract class Enemy extends Warrior
{
    protected int ID;
    protected String target;
    protected HashMap<String, Integer> data;
    protected String enemyTrate;

    public Enemy(String name, String target, HashMap<String, Integer> data)
    {
        setName(name);
        setID(0);
        setTarget(target);
        setData(data);
        setEnemyTrate();
    }

    public String getEnemyTrate()
    {
        setEnemyTrate();
        return enemyTrate;
    }

    public void setEnemyTrate()
    {
        enemyTrate = "";
        Set<String> effect = this.data.keySet();
        String[] keys = new String[effect.size()];
        effect.toArray(keys);
        for (String key : keys)
        {
            String[] k = key.split(" ");
            if (k.length > 1)
            {
                if (k[0].equals("current"))
                {
                    int max = searchMax(k[1]);
                    if (max != 0)
                    {
                        enemyTrate += k[1] + " : " + this.data.get(key) + " \\ " + max + "\n";
                    }
                }
            } else
            {
                int tp = this.data.get(key);
                enemyTrate += key + " : " + (tp);
            }
        }
    }

    private int searchMax(String s)
    {
        Set<String> effect = this.data.keySet();
        String[] keys = new String[effect.size()];
        effect.toArray(keys);
        for (int i = 0; i < keys.length; i++)
        {
            String[] k = keys[i].split(" ");
            if (k.length > 1)
            {
                if (k[0].equals("max") && k[1].equals(s))
                {
                    return this.data.get(keys[i]);
                }
            }
        }
        return 0;
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
