package Model;

import java.util.HashMap;


public class EnemyVersion
{
    String name;
    String mainEnemyName;
    HashMap<String, Integer> data;
    String target;


    public EnemyVersion(String name, String mainEnemyName, HashMap<String, Integer> data, String target)
    {
        this.data = new HashMap<>();
        setData(data);
        setName(name);
        setMainEnemyName(mainEnemyName);
        setTarget(target);
    }

    public EnemyVersion(String name, String mainEnemyName, HashMap<String, Integer> data)
    {

    }

    public String getMainEnemyName()
    {
        return mainEnemyName;
    }

    public void setMainEnemyName(String mainEnemyName)
    {
        this.mainEnemyName = mainEnemyName;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public HashMap<String, Integer> getData()
    {
        return data;
    }

    public void setData(HashMap<String, Integer> data)
    {
        this.data.putAll(data);
    }

    public void setTarget(String target)
    {
        this.target = target;
    }

    public String getTarget()
    {
        return target;
    }
}