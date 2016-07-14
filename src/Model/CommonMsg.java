package Model;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Vahid on 7/2/2016.
 */
//VAHID
public class CommonMsg implements Serializable
{
    private int xp;
    private int money;
    private int immortalityPotion;
    private int turn = 0;
    private int chance = 0;
    private String name = "";
    private String starter = "";
    private boolean ready = false;
    private int winner = -1;
    private ArrayList<String> messages = new ArrayList<>();
    private ArrayList<Hero> heros = new ArrayList<>();
    private ArrayList<Hero> enemyHeros = new ArrayList<>();

    public CommonMsg(ArrayList<Hero> enemyHeros)
    {
        this.enemyHeros = enemyHeros;
        this.xp = 0;
        this.money = 0;
        this.immortalityPotion = 0;


    }

    public ArrayList<String> getMessages()
    {
        return messages;
    }

    public void setMessages(ArrayList<String> messages)
    {
        this.messages = messages;
    }

    public int getChance()
    {
        return chance;
    }

    public void setChance(int chance)
    {
        this.chance = chance;
    }


    public int getWinner()
    {
        return winner;
    }

    public void setWinner(int winner)
    {
        this.winner = winner;
    }


    public int getXp()
    {
        return xp;
    }

    public void setXp(int xp)
    {
        this.xp = xp;
    }

    public int getMoney()
    {
        return money;
    }

    public void setMoney(int money)
    {
        this.money = money;
    }

    public int getImmortalityPotion()
    {
        return immortalityPotion;
    }

    public void setImmortalityPotion(int immortalityPotion)
    {
        this.immortalityPotion = immortalityPotion;
    }

    public int getTurn()
    {
        return turn;
    }

    public void setTurn(int turn)
    {
        this.turn = turn;
    }

    public String getStarter()
    {
        return starter;
    }

    public void setStarter(String starter)
    {
        this.starter = starter;
    }

    public boolean isReady()
    {
        return ready;
    }

    public void setReady(boolean ready)
    {
        this.ready = ready;
    }

    public ArrayList<Hero> getHeros()
    {

        return heros;
    }

    public void setHeros(ArrayList<Hero> heros)
    {
        this.heros = heros;
    }

    public ArrayList<Hero> getEnemyHeros()
    {
        return enemyHeros;
    }

    public void setEnemyHeros(ArrayList<Hero> enemyHeros)
    {
        this.enemyHeros = enemyHeros;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }
}
