package Controller;

import Auxiliary.Formula;
import Auxiliary.StringParser;
import Model.EnemyVersion;
import Model.Warrior;

import java.util.ArrayList;
import java.util.HashMap;

public class GameScenario
{
	UserInterface userInterface;

	StringParser stringParser;
	ArrayList<Warrior> warrior; // Is this needed?
	/******/
	// Init is needed for these stuff
	
	HashMap<String, HashMap<String, Integer>> heroClassDatas;
	HashMap<String, ArrayList<EnemyVersion>> enemyDatas;
	// Boss Data needed
	HashMap<String, Integer> itemDatas;
	HashMap<String, ArrayList<HashMap<String, Formula>>> abilityDatas;
	
	/******/
	
	public GameScenario(UserInterface userInterface, StringParser stringParser)
	{
		this.userInterface = userInterface;
		this.stringParser = stringParser;
		heroClassDatas = new HashMap<>();
		enemyDatas = new HashMap<>();
		itemDatas = new HashMap<>();
		//creating objects like hero , enemy ,...
		if(!userInterface.isCustom())
		{
			init(); // I think we need this for any kind of start :\
		}	
		else
		{
			
		}
	}
	
	
	public void scenario()
	{
		introduceHeros();
		
		for(int i = 0; i < userInterface.getTurns() - 1; i++)
		{
			tellStory(i);
			showEnemyData();
			startUpgrading();
			shopping();
			startFighting(i);
		}
		
		tellStory(userInterface.getTurns() - 1);
		
	}
	
	
	public void init()
	{
		HashMap<String, Integer> fighterData = new HashMap<>();
		fighterData.put("attack", 120);
		fighterData.put("max health", 200);
		fighterData.put("current health", 200);
		fighterData.put("health refill", 10);
		fighterData.put("max magic", 120);
		fighterData.put("current magic", 120);
		fighterData.put("magic refill", 5);
		fighterData.put("max EP", 6);
		fighterData.put("current EP", 6);
		heroClassDatas.put("fighter", fighterData);
		
		/*************/
		
		HashMap<String, Integer> supporterData = new HashMap<>();
		supporterData.put("max health", 220);
		supporterData.put("current health", 220);
		supporterData.put("health refill", 5);
		supporterData.put("max magic", 200);
		supporterData.put("current magic", 200);
		supporterData.put("magic refill", 10);
		supporterData.put("attack", 80);
		supporterData.put("max EP", 5);
		supporterData.put("current EP", 5);
		heroClassDatas.put("supporter", supporterData);
		
		/*************/
		
		
	}
	
	
	public void introduceHeros()
	{
		
	}
	
	
	public void tellStory(int storyPart)
	{
		String story = userInterface.getStory(storyPart);
		
		System.out.println(story);
	}
	
	
	public void showEnemyData()
	{
		
	}
	
	
	public void startUpgrading()
	{
		String command = userInterface.getUpgradeCommand();
		while(!command.equals("Done"))
		{
			// do the job
			
			command = userInterface.getUpgradeCommand();
		}
	}
	
	
	public void shopping()
	{
		String command = userInterface.getShoppingCommand();
		while(!command.equals("Done"))
		{
			// do the job
			
			command = userInterface.getShoppingCommand();
		}
	}
	
	
	public void startFighting(int i)
	{
		
	}
}