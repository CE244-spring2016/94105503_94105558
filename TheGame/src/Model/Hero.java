package Model;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Vahid on 5/5/2016.
 */
public class Hero extends Warrior
{
    private static int money;
    private static int XP;
    private static int immortalityPotionNum;


    private String heroClassName;
    private ArrayList<Ability> abilities = new ArrayList<>();
    private Inventory inventory;
    private HashMap<String, Integer> data = new HashMap<>();

    public Hero(String heroName, String heroClassName, HashMap<String, Integer> data, int inventorySize, ArrayList<Ability> abilities)
    {
        setName(heroName);
        setHeroClassName(heroClassName);
        setInventory(new Inventory(inventorySize));
        setAbilities(abilities);
        setData(data);
    }

    public static int getMoney()
    {
        return money;
    }

    public static void setMoney(int money)
    {
        Hero.money = money;
    }

    public static int getXP()
    {
        return XP;
    }

    public static void setXP(int XP)
    {
        Hero.XP = XP;
    }

    public static int getImmortalityPotionNum()
    {
        return immortalityPotionNum;
    }

    public static void setImmortalityPotionNum(int immortalityPotionNum)
    {
        Hero.immortalityPotionNum = immortalityPotionNum;
    }

    public Inventory getInventory()
    {
        return inventory;
    }

    public void setInventory(Inventory inventory)
    {
        this.inventory = inventory;
    }

    public ArrayList<Ability> getAbilities()
    {
        return abilities;
    }

    public void setAbilities(ArrayList<Ability> abilities)
    {
        this.abilities = abilities;
    }

    public HashMap<String, Integer> getData()
    {
        return data;
    }

    public void setData(HashMap<String, Integer> data)
    {
        this.data = data;
    }

    public String getHeroClassName()
    {
        return heroClassName;
    }

    public void setHeroClassName(String heroClassName)
    {
        this.heroClassName = heroClassName;
    }
}
