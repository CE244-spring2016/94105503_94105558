package Model;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Vahid on 5/5/2016.
 */
public class Hero extends Warrior {
    private static int money;
    private static int XP;
    private static int immortalityPotionNum;

    private ArrayList<Ability> abilities;
    private Inventory inventory;
    private HashMap<String, Integer> data;

    public Hero(HashMap<String, Integer> data, int inventorySize, ArrayList<Ability> abilities) {
        this.abilities = abilities;
        this.data = data;
        this.inventory = new Inventory(inventorySize);
    }

    public HashMap<String, Integer> getData() {
        return data;
    }
}
