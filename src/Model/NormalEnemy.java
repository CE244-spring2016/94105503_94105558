package Model;

import Auxiliary.Luck;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;


public class NormalEnemy extends Enemy
{
    private String version;


    private String successMessage;

    public NormalEnemy(String name, String version, String target, HashMap<String, Integer> data)
    {
        super(name, target, data);
        setID(0);
        setVersion(version);
    }

    public String getVersion()
    {
        return version;
    }

    public void setVersion(String version)
    {
        this.version = version;
    }

    public HashMap<String, Integer> getData()
    {
        return data;
    }

    public void setData(HashMap<String, Integer> data)
    {
        this.data = data;
    }

    public ArrayList<String> startAMove(ArrayList<Hero> heros, ArrayList<Enemy> allies)
    {
        ArrayList<Warrior> enemyMoveTargets = new ArrayList<>();

        switch (target)
        {
            case "himself":
                enemyMoveTargets.add(this);
                break;
            case "an ally":
            {
                int targetIndex = Luck.getRandom(0, allies.size() - 1);
                enemyMoveTargets.add(allies.get(targetIndex));
                break;
            }
            case "all allies":
                enemyMoveTargets.addAll(allies);
                break;
            case "a hero":
            {
                int targetIndex = Luck.getRandom(0, heros.size() - 1);
                enemyMoveTargets.add(heros.get(targetIndex));
                break;
            }
            case "all heros":
                enemyMoveTargets.addAll(heros);
                break;
            case "everyone":
                enemyMoveTargets.addAll(allies);
                enemyMoveTargets.addAll(heros);
                break;
            default:
                //invalid input
                break;
        }

        makeAMove(enemyMoveTargets);
        return getFullEnemyTurnLog(enemyMoveTargets);
    }


    private void makeAMove(ArrayList<Warrior> targets)
    {
        for (Warrior warrior : targets)
        {
            HashMap<String, Integer> warriorData = new HashMap<>();
            if (warrior instanceof Hero)
            {
                warriorData = ((Hero) warrior).getData();
            } else if (warrior instanceof Enemy)
            {
                warriorData = ((Enemy) warrior).getData();
            }
            for (String moveAttribute : data.keySet())
            {
                String[] attributeNameParts = moveAttribute.split(" ");
                if (attributeNameParts[0].equals("move"))
                {
                    int effectAmount = data.get(moveAttribute);
                    if (attributeNameParts[1].equals("attack"))
                    {
                        warriorData.put("current health", warriorData.get("current health") - effectAmount);
                    } else if (attributeNameParts[1].equals("heal"))
                    {
                        warriorData.put("current health", warriorData.get("current health") + effectAmount);
                    }
                }
            }
        }
    }

    public String getSuccessMessage()
    {
        return successMessage;
    }

    public void setSuccessMessage(String successMessage)
    {
        this.successMessage = successMessage;
    }

    private void printSuccessMessage(ArrayList<Warrior> targets)
    {
        String[] targetNameParts = target.split(" ");
        if (targetNameParts[0].equals("all"))
        {
            System.out.println(getFullName() + " just " + successMessage + " with " + data.get("move attack") + " power");
        }

        if (data.containsKey("move attack"))
        {
            System.out.println(getFullName() + " just attacked " + targets.get(0).getName() + " with " + data.get("move attack") + " power");
        } else if (data.containsKey("move heal"))
        {
            System.out.println(getFullName() + " just healed " + ((NormalEnemy)targets.get(0)).getFullName() + " with " + data.get("move heal") + " health potions");
        }
    }

    private ArrayList<String> getFullEnemyTurnLog(ArrayList<Warrior> targets)
    {
        String[] targetNameParts = target.split(" ");
        ArrayList<String> log = new ArrayList<>();

        if (targetNameParts[0].equals("all"))
        {
//            System.out.println(getFullName() + " just " + successMessage + " with " + data.get("move attack") + " power");
            log.add(getFullName() + " just " + successMessage + " with " + data.get("move attack") + " power");
        }
        else if (data.containsKey("move attack"))
        {
//            System.out.println(getFullName() + " just attacked " + targets.get(0).getName() + " with " + data.get("move attack") + " power");
            log.add(getFullName() + " just attacked " + targets.get(0).getName() + " with " + data.get("move attack") + " power");
        } else if (data.containsKey("move heal"))
        {
//            System.out.println(getFullName() + " just healed " + ((NormalEnemy)targets.get(0)).getFullName() + " with " + data.get("move heal") + " health potions");
            log.add(getFullName() + " just healed " + ((NormalEnemy)targets.get(0)).getFullName() + " with " + data.get("move heal") + " health potions");
        }

        return log;
    }
}
