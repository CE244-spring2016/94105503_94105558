import java.util.*;

public class EnemyVersion
{
	String name;
	HashMap<String, Integer> data;
	
	public EnemyVersion(String name, HashMap<String, Integer> data)
	{
		this.data = new HashMap<>();
		setData(data);
		setName(name);
	}
	
	
	public void setName(String name)
	{
		this.name = name;
	}
	
	
	public String getName()
	{
		return name;
	}
	
	
	public void setData(HashMap<String, Integer> data)
	{
		this.data.putAll(data);
	}
	
	
	public HashMap<String, Integer> getData()
	{
		return data;
	}
}