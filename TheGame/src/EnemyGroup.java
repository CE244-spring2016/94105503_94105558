public class EnemyGroup
{
	private ArrayList<Enemy> enemies;
	private int xp;
	private int money;
	private int battleNum;
	
	public EnemyGroup(ArrayList<Enemy> enemies, int xp, int money, int battleNum)
	{
		setXP(xp);
		setMoney(money);
		setBattleNum(battleNum);
		setEnemies(enemies);
	}
	
	
	public void setXP(int xp)
	{
		this.xp = xp;
	}
	
	
	public int getXP()
	{
		return xp;
	}
	
	
	public void setMoney(int money)
	{
		this.money = money;
	}
	
	
	public int getMoney()
	{
		return money;
	}
	
	
	public int setBattleNum(int battleNum)
	{
		this.battleNum = battleNum;
	}
	
	
	public int getBattleNum()
	{
		return battleNum;
	}
	
	
	public void setEnemies(ArrayList<Enemy> enemies)
	{
		this.enemies.addAll(enemies);
	}
	
	
	public Enemy getEnemy(String name, String ID)
	{
		for(Enemy enemy : enemies)
		{
			if(enemy.getName().equals(name) && enemy.getID().equals(ID))
			{
				return enemy;
			}
		}
		
		return null;
	}
}