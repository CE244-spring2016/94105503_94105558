package Model;

import java.util.HashMap;

/**
 * Created by Vahid on 5/5/2016.
 */
public class NormalEnemy extends Enemy
{
	public void setData(HashMap<String, Integer> data)
	{
		this.data = data;
	}

	private HashMap<String, Integer> data;
    //private String name; Its handled in warrior :|
    private int ID; // maybe move this to enemy group?

	public NormalEnemy(String name, int ID, HashMap<String, Integer> data)
	{
		setName(name);
		setID(ID);
		setData(data);
	}
	
	
    public HashMap<String, Integer> getData()
	{
        return data;
    }
	
	
	/*public void setName(String name)
	{
		this.name = name;
	}
	

    public String getName() 
	{
        return name;
    }*/
	
	
	public void setID(int ID)
	{
		this.ID = ID;
	}
	
	
    public int getID() 
	{
        return ID;
    }
}
