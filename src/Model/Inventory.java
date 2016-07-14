package Model;

import java.io.Serializable;
import java.util.ArrayList;

public class Inventory implements Serializable
{
    private int maxSize;
    private int currentSize;
    private ArrayList<Item> items = new ArrayList<>();

    public Inventory(int size)
    {
        setMaxSize(size);
    }

    public ArrayList<Item> getItems()
    {
        return items;
    }

    public void setItems(ArrayList<Item> items)
    {
        this.items = items;
    }

    public int getMaxSize()
    {
        return maxSize;
    }

    public void setMaxSize(int maxSize)
    {
        this.maxSize = maxSize;
    }


    public void setCurrentSize(int size)
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


    public void removeItem(Item item)
    {
        if (items.contains(item))
        {
            items.remove(item);
        }
    }


    public Item getItem(String itemName)
    {
        for (Item item : items)
        {
            if (item.getName().equals(itemName))
            {
                return item;
            }
        }

        return null;
    }


    public boolean isFreeSpace()
    {
        return currentSize < maxSize;

    }
}