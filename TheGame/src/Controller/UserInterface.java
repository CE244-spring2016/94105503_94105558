import java.util.*;

/*
	We need init here!
	
	We could divide this class to 5-6 other classes
*/

public class UserInterface
{
	private boolean customed;
	
	private ArrayList<String> heroClassNames; // new it in the constructor
	private HashMap<String, HashMap<String, Integer>> heroClassDatas; // new it in the constructor
	private HashMap<String, String> heroClassAbilities;
	
	//private ArrayList<String> abilityNames;
	private ArrayList<String> heroAttributes;                         // must make this fully in the constructor
	
	private HashMap<String, String> herosAndTheirClasses;
	private HashMap<String, ArrayList<String>> herosAndTheirAbilities;
	
	private ArrayList<String> normalEnemyNames;
	private HashMap<String, ArrayList<EnemyVersion>> normalEnemyDatas;
	private ArrayList<String> enemyAttributes;                        // must make this fully in the constructor
	
	private ArrayList<String> bossEnemyNames;
	private HashMap<String, HashMap<String, Integer>> bossEnemyDatas;
	private HashMap<String, ArrayList<String>> bossEnemySpecialConditions;
	private HashMap<String, ArrayList<String>> bossEnemyEarlyTurnEffects;
	
	private ArrayList<String> itemNames;
	private HashMap<String, HashMap<String, Integer>> itemDatas;
	private HashMap<String, String> itemTargets;
	private ArrayList<String> itemAttributes;                         // must make this fully in the constructor
	private ArrayList<String> possibleItemTargets;                    // must make this fully in the constructor
	
	private ArrayList<String> abilityNames;
	private HashMap<String, HashMap<String, ArrayList<Formula>>> allAbiliyFormulas; // Even non targeted enemy share and cooldown is handled here
	private HashMap<String, String> abilityTargets;
	private ArrayList<String> possibleAbilityTargets;                 // must make this fully in the constructor
	private HashMap<String, ArrayList<Integer>> abilityUpgradeXPs;
	private ArrayList<String> abilityAttributes;                      // must make this fully in the constructor
	private HashMap<String, HashMap<String, Integer>> allRequieredAbilities;
	private HashMap<String, Integer> abilityLuckPercents;
	
	
	public void checkCustom(Scanner in)
	{
		System.out.println("How do you want to start?(Enter the right number)");
		System.out.println("1- Start normal game");
		System.out.println("2- Start custom game(you can create your own game here)");
		
		String input = in.next();
		// Check wrong input
		if(input.equals("2"))
		{
			this.customed = true;
			startCreating(in);
		}
		else
		{
			this.customed = false;
		}
	}
	
	
	public void startCreating(Scanner in)
	{
		System.out.println("Welcome!");
		while(true)
		{
			System.out.println("What do you want to create?(Enter the right number)");
			System.out.println("1- Hero Class");
			System.out.println("2- Hero");
			System.out.println("3- Normal Enemy");
			System.out.println("4- Boss Enemy");
			System.out.println("5- Item");
			System.out.println("6- Ability");
			
			String input = in.next();
			if(input.equals("1"))
			{
				creatHeroClass(in);
			}
			else if(input.equals("2"))
			{
				createHero(in);
			}
			else if(input.equals("3"))
			{
				createNormalEnemy(in);
			}
			else if(input.equals("4"))
			{
				createBossEnemy(in);
			}
			else if(input.equals("5"))
			{
				createItem(in);
			}
			else if(input.equals("6"))
			{
				createAbility(in);
			}
			
			System.out.println("Want to create anything else?(Enter the right number)");
			
			if(!yesNoQuestion(in))
			{
				break;
			}
		}
	}
	
	
	private void creatHeroClass(Scanner in)
	{
		String heroClassName;
		HashMap<String, Integer> heroClassData = new HashMap<>();
		
		while(true)
		{
			System.out.print("Please enter the name of the class you want to make: ");
			heroClassName = in.next();
			
			System.out.println("Are you sure?(Enter the right number)");
			
			if(yesNoQuestion(in))
			{
				heroClassNames.add(heroClassName);
				break;
			}
		}
		
		System.out.println("Please enter the amount you want for each attribute");
		
		for(int i = 0; i < heroAttributes.size(); i++)
		{
			while(true)
			{
				String attributeName = heroAttributes.get(i);
				
				System.out.print(attributeName + ": ");
				
				String attributeAmount = in.next();
				if(attributeAmount.matches("[0-9]+") && attributeAmount.length() < 8)
				{
					int attributeAmountNum = Integer.parseInt(attributeAmount);
					
					heroClassData.put(attributeName, attributeAmountNum);
					break;
				}
				else
				{
					// Invalid Input
				}
			}
		}
		
		System.out.println("How many abilities do you want to give to this class?");
		//check invalid input
		
		int abilityNum = in.nextInt();
		
		System.out.println("Please enter the name of the abilities you want this class to have:");
		showAbilityNames();
		while(true)
		{
			String abilityName = in.next();
			if(abilityNames.contains(abilityName))
			{
				heroClassAbilities.put(heroClassName, abilityName);
			}
			else
			{
				//invalid input
				continue;
			}
			
			System.out.println("Do you want to add any other ability?(Enter the right number)");
			if(!yesNoQuestion(in))
			{
				break;
			}
		}
		
		System.out.println("Hero Class was made!");
	}
	
	
	private void createHero(Scanner in)
	{
		String heroName, heroClassName;
		ArrayList<String> abilityList = new ArrayList<>();
		
		while(true)
		{
			System.out.print("Please enter the name of the hero you want to make: ");
			heroName = in.next();
			
			System.out.println("Are you sure?(Enter the right number)");
			
			if(yesNoQuestion(in))
			{
				//heroClassNames.add(heroClassName);
				break;
			}
		}
		
		while(true)
		{
			System.out.println("Please choose a class for this hero: ");
			showHeroClasses();
			
			heroClassName = in.next();
			if(heroClassNames.contains(heroClassName))
			{
				System.out.println("Are you sure?(Enter the right number)");
				
				if(yesNoQuestion(in))
				{
					herosAndTheirClasses.put(heroName, heroClassName);
					break;
				}
			}
			else
			{
				// invalid input
			}
		}
		
		System.out.println("Please enter the name of the abilities you want this hero to have:");
		showAbilityNames();
		while(true)
		{
			String abilityName = in.next();
			if(abilityNames.contains(abilityName))
			{
				abilityList.add(abilityName);
			}
			else
			{
				//invalid input
				continue;
			}
			
			System.out.println("Do you want to add any other ability?(Enter the right number)");
			if(!yesNoQuestion(in))
			{
				break;
			}
			
			System.out.println("Please enter the name of the next ability you want this hero to have: ");
		}
		herosAndTheirAbilities.put(heroName, abilityList);
		
		System.out.println("Hero was made!");
	}
	
	
	private void createNormalEnemy(Scanner in)
	{
		String enemyName;
		ArrayList<EnemyVersion> enemyVersions = new ArrayList<>();
		
		while(true)
		{
			System.out.print("Please enter the name of the enemy you want to make: ");
			enemyName = in.next();
			
			System.out.println("Are you sure?(Enter the right number)");
			
			if(yesNoQuestion(in))
			{
				normalEnemyNames.add(enemyName);
				break;
			}
		}
		
		System.out.println("How many versions do you want to make for this enemy?");
		//check invalid input
		int versionNum = in.nextInt();
		
		for(int i = 0; i < versionNum; i++)
		{
			EnemyVersion enemyVersion = makeEnemyVersion(in);
			enemyVersions.add(enemyVersion);
			
			System.out.println("Version added!");
		}
		normalEnemyDatas.put(enemyName, enemyVersions);
		
		System.out.println("Enemy was made!");
	}
	
	
	private void createBossEnemy(Scanner in)
	{
		String bossName;
		ArrayList<String> specialConditions = new ArrayList<>();
		ArrayList<String> earlySpecialEffects = new ArrayList<>();
		HashMap<String, Integer> bossData;
		
		while(true)
		{
			System.out.print("Please enter the name of the boss enemy you want to make: ");
			bossName = in.next();
			
			System.out.println("Are you sure?(Enter the right number)");
			
			if(yesNoQuestion(in))
			{
				bossEnemyNames.add(bossName);
				break;
			}
		}
		
		System.out.println("Please enter the amount you want for each attribute");
		
		for(int i = 0; i < enemyAttributes.size(); i++)
		{
			while(true)
			{
				String attributeName = enemyAttributes.get(i); // It will be nice if it goes up
				
				System.out.print(attributeName + ": ");
				
				String attributeAmount = in.next();
				if(attributeAmount.matches("[0-9]+") && attributeAmount.length() < 8)
				{
					int attributeAmountNum = Integer.parseInt(attributeAmount);
					bossData.put(attributeName, attributeAmountNum);
					break;
				}
				else
				{
					// Invalid Input
				}
			}
		}
		
		bossEnemyDatas.put(bossName, bossData);
		
		// Must handle Condition and early effects
	}
	
	
	private void createItem(Scanner in)
	{
		String itemName, itemTarget;
		HashMap<String, Integer> itemData;
		
		while(true)
		{
			System.out.print("Please enter the name of the item you want to make: ");
			itemName = in.next();
			
			System.out.println("Are you sure?(Enter the right number)");
			
			if(yesNoQuestion(in))
			{
				itemNames.add(itemName);
				break;
			}
		}
		
		while(true)
		{
			System.out.println("Please enter the name of the attribute that you want this item to affect it");
			showItemAttributes();
			String attributeName = in.next();
			
			if(!itemAttributes.contains(attributeName))
			{
				// invalid input
				continue;
			}
			
			while(true)
			{
				System.out.println("Please enter the amount of effect: ");
				String attributeAmount = in.next();
				
				if(attributeAmount.matches("[0-9]+") && attributeAmount.length() < 8)
				{
					int attributeAmountNum = Integer.parseInt(attributeAmount);
					itemData.put(attributeName, attributeAmountNum);
					break;
				}
				else
				{
					// Invalid Input
				}
			}
			
			System.out.println("Do you want any other effect for this item?");
			
			if(!yesNoQuestion(in))
			{
				break;
			}
		}
		
		while(true)
		{
			System.out.println("Please enter the target of this item");
			showPossibleItemTargets();
			itemTarget = in.next();
			
			if(itemTargets.containsKey(itemTarget))
			{
				itemTargets.put(itemName, itemTarget);
				break;
			}
			
			//invalid input
		}
		
		System.out.println("Item was made!");
	}
	
	
	private void createAbility(Scanner in)
	{
		
	}
	
	
	private EnemyVersion makeEnemyVersion(Scanner in)
	{
		String versionName;
		HashMap<String, Integer> versionData = new HashMap<>();
		
		while(true)
		{
			System.out.print("Please enter the name of the version you want to make: ");
			versionName = in.next();
			
			System.out.println("Are you sure?(Enter the right number)");
			
			if(yesNoQuestion(in))
			{
				break;
			}
		}
		
		System.out.println("Please enter the amount you want for each attribute");
		
		for(int i = 0; i < enemyAttributes.size(); i++)
		{
			while(true)
			{
				String attributeName = enemyAttributes.get(i); // It will be nice if it goes up
				
				System.out.print(attributeName + ": ");
				
				String attributeAmount = in.next();
				if(attributeAmount.matches("[0-9]+") && attributeAmount.length() < 8)
				{
					int attributeAmountNum = Integer.parseInt(attributeAmount);
					
					versionData.put(attributeName, attributeAmountNum);
					break;
				}
				else
				{
					// Invalid Input
				}
			}
		}
		
		EnemyVersion enemyVersion = new EnemyVersion(versionName, versionData);
		
		return enemyVersion;
	}
	
	
	private boolean yesNoQuestion(Scanner in)
	{
		System.out.println("1- Yes");
		System.out.println("2- No");
		while(true)
		{
			String input = in.next();
			if(input.equals("1"))
			{
				return true;
			}
			else if(input.equals("2"))
			{
				return false;
			}
			
			System.out.println("Invalid input! Please try again.");
		}
	}
	
	
	private void showAbilityNames()
	{
		for(int i = 0; i < abilityNames.size(); i++)
		{
			System.out.println(abilityNames.get(i));
		}
	}
	
	
	private void showHeroClasses()
	{
		for(String heroClassName : heroClassNames)
		{
			System.out.println(heroClassName);
		}
	}
	
	
	private void showItemAttributes()
	{
		for(int i = 0; i < itemAttributes.size(); i++)
		{
			System.out.println(itemAttributes.get(i));
		}
	}
	
	
	private void showPossibleItemTargets()
	{
		for(int i = 0; i < possibleItemTargets.size(); i++)
		{
			System.out.println(possibleItemTargets.get(i));
		}
	}
}