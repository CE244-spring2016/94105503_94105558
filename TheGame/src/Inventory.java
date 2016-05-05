public class Inventory
{
	private int maxSize;
	private int currentSize;
	private ArrayList<Item> items;
	
	public Inventory(int size)
	{
		setMaxSize(size);
	}
	
	
	public void setMaxSize(int maxSize)
	{
		this.maxSize = maxSize;
	}
	
	
	public int getMaxSize()
	{
		return maxSize;
	}
	
	
	public void setSize(int size)
	{
		this.currentSize = size;
	}
	
	
	public int getCurrentSize()
	{
		return currentSize;
	}
	
	
	public void addItem(Item item)
	{
		items.add(item);
	}
	
	
	public void getItem(String itemName)
	{
		for(Item item : items)
		{
			if(item.getName().equals(itemName))
			{
				return item;
			}
		}
		
		return null;
	}
}