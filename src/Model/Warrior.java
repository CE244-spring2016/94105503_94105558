package Model;


import java.io.Serializable;
import java.util.HashMap;

public abstract class Warrior  implements Serializable
{
	String name;
	protected HashMap<String, Integer> data;

	protected abstract HashMap<String, Integer> getData();
	protected abstract String getFullName();

	public void setName(String name)
	{
		this.name = name;
	}
	
	
	public String getName()
	{
		return name;
	}
}
