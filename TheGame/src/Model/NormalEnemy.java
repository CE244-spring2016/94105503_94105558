package Model;

import java.util.HashMap;

/**
 * Created by Vahid on 5/5/2016.
 */
public class NormalEnemy extends Enemy
{
	String version;

	public String getVersion()
	{
		return version;
	}

	public void setVersion(String version)
	{
		this.version = version;
	}


	public void setData(HashMap<String, Integer> data)
	{
		this.data = data;
	}

	private HashMap<String, Integer> data;

	public NormalEnemy(String name, String version,HashMap<String, Integer> data)
	{
		setID(0);
		setName(name);
		setData(data);
	}
	
	
    public HashMap<String, Integer> getData()
	{
        return data;
    }

}
