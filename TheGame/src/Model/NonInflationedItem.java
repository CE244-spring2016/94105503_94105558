package Model;/*
    HashMap in the constructor?
*/

public class NonInflationedItem extends InstantEffectItem
{
    private int cost;

    public NonInflationedItem(String name, int size, int cost)
    {
        setName(name);
        setItemSize(size);
        setCost(cost);
    }

    public int getCost()
    {
        return cost;
    }

    public void setCost(int cost)
    {
        this.cost = cost;
    }
}