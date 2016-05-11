package Model;

import java.net.InetSocketAddress;
import java.util.HashMap;

/**
 * Created by Vahid on 5/5/2016.
 */
public class Enemy extends Warrior
{
    private int ID;
	private String target;
	private HashMap<String, Integer> data;
	
    public int getID()
    {
        return ID;
    }

    public void setID(int ID)
    {
        this.ID = ID;
    }
	
	public void setTartget(String target)
	{
		this.target = target;
	}
	
	public String getTarget()
	{
		return target;
	}
	
	
	public String getFullName()
	{
		String fullName = "";
		
		if(this instanceof NormalEnemy)
		{
			fullName = ((NormalEnemy)this).getVersion() + name;
			if(ID != 0)
			{
				fullName += Integer.toString(ID);
			}
		}
		else if(this instanceof BossEnemy)
		{
			fullName = name;
			if(ID != 0)
			{
				fullName += Integer.toString(ID);
			}
		}
		
		return fullName;
	}
	
	public abstract void startAMove(ArrayList<Hero> heros, ArrayList<Enemy> allies);
}
