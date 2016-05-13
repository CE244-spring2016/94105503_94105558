package Model;

import java.util.ArrayList;

public class EnemyGroup
{

    private ArrayList<Enemy> enemies = new ArrayList<>();
    private int xp;
    private int money;
    private int battleNum;

    public EnemyGroup(ArrayList<Enemy> enemies, int xp, int money, int battleNum)
    {
        setXP(xp);
        setMoney(money);
        setBattleNum(battleNum);
        setEnemies(enemies);
        setEnemyIDs();
    }

    private void setEnemyIDs()
    {
        for (int i = 0; i < enemies.size(); i++)
        {
            if (enemies.get(i).getID() == 0)
            {
                ArrayList<Enemy> foundedEnemies = findEnemeis(enemies.get(i));
                if (foundedEnemies.size() > 1)
                {
                    for (int j = 0; j < foundedEnemies.size(); j++)
                    {
                        foundedEnemies.get(j).setID(j + 1);
                    }
                }
            }
        }
    }

    private ArrayList<Enemy> findEnemeis(Enemy enemy)
    {
        ArrayList<Enemy> foundedEnemies = new ArrayList<>();
        if(enemy instanceof NormalEnemy)
        {
            for (Enemy enemy1 : enemies)
            {
                if (enemy1.getName().equals(enemy.getName()) && ((NormalEnemy) enemy1).getVersion().equals(((NormalEnemy) enemy).getVersion()))
                {
                    foundedEnemies.add(enemy1);
                }
            }
        }else if(enemy instanceof BossEnemy) {
            for (Enemy enemy1 : enemies)
            {
                if (enemy1.getName().equals(enemy.getName()))
                {
                    foundedEnemies.add(enemy1);
                }
            }
        }
        return foundedEnemies;
    }

    public int getXP()
    {
        return xp;
    }

    public void setXP(int xp)
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

    public int getBattleNum()
    {
        return battleNum;
    }

    public void setBattleNum(int battleNum)
    {
        this.battleNum = battleNum;
    }

    public ArrayList<Enemy> getEnemies()
    {
        return enemies;
    }

    public void setEnemies(ArrayList<Enemy> enemies)
    {
        this.enemies.addAll(enemies);
    }

    public int getXp()
    {
        return xp;
    }

    public void setXp(int xp)
    {
        this.xp = xp;
    }

}