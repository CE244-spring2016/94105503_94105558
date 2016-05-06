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
	private HashMap<String, ArrayList<Integer>> allAbilityUpgradeXPs;
	private ArrayList<String> abilityAttributes;                      // must make this fully in the constructor
	private HashMap<String, ArrayList<HashMap<String, Integer>>> allRequieredAbilities;
	private HashMap<String, ArrayList<Integer>> abilityLuckPercents;
	private HashMap<String, String> primaryVariableNames;
	private HashMap<String, ArrayList<Integer>> secondaryTargetShares;
	
	
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
		String abilityName, abilityTarget;
		int upgradeNum;
		HashMap<String, ArrayList<Formula>> formulas = new HashMap<>();
		ArrayList<Integer> abilityUpgradeXPs = new ArrayList<>();
		HashMap<String, ArrayList<Double>> extraVariables = new HashMap<>();
		
		while(true)
		{
			System.out.print("Please enter the name of the ability you want to make: ");
			abilityName = in.next();
			
			System.out.println("Are you sure?(Enter the right number)");
			
			if(yesNoQuestion(in))
			{
				abilityNames.add(abilityName);
				break;
			}
		}
		
		System.out.println("How many upgrades does this ability have?");
		//check Invalid input
		upgradeNum = in.nextInt();
		
		for(int i = 0; i < upgradeNum; i++)
		{
			System.out.print("Please enter how much xp is needed to get this upgrade: ");
			//check invalid input
			int upgradeXP = in.nextInt();
			
			abilityUpgradeXPs.add(upgradeXP);
		}
		
		System.out.println("For creating a formula do you need any extra variables?");
		
		if(yesNoQuestion(in))
		{
			System.out.println("How many?");
			//check invalid input
			int variableNum = in.nextInt();
			
			for(int i = 0; i < variableNum; i++)
			{
				ArrayList<Double> variableValues = new ArrayList<>();
				System.out.print("Enter variable name(don't use numbers): ");
				//check invalid input
				String variableName = in.next();
				System.out.println("Please enter its value for each upgrade");
				for(int j = 0; j < upgradeNum; j++)
				{
					System.out.print((j + 1) + ": ");
					//check invalid input.. NOT MORE THAN ONE FLOATING POINT
					double variableValue = in.nextDouble();
					variableValues.add(variableValue);
				}
				
				extraVariables.put(variableName, variableValues);
			}
		}
		
		System.out.println("Please enter the formula for this ability");
		showAllVariables();
		String formulaString = getFormulaString(in);
		ArrayList<Formula> differentUpgradeFormulas = new ArrayList<>();
		
		for(int i = 0; i < upgradeNum; i++)
		{
			HashMap<String, Double> formulaData = new HashMap<>();
			for(String variableName : extraVariables.keySet())
			{
				double variableValue = extraVariables.get(variableName).get(i); // might need more explanation
				formulaData.put(variableName, variableValue);
			}
			Formula formula = new Formula(formulaString, formulaData);
			differentUpgradeFormulas.add(formula);
		}
		
		formulas.put("normal", differentUpgradeFormulas);
		
		System.out.println("In some abilities there is a chance for the ability to work differently");
		System.out.println("Is this ability affected by luck?");
		
		if(yesNoQuestion(in))
		{
			System.out.println("Please enter the luck formula for this ability");
			showAllVariables();
			showNormalDamageFormula();
			String formulaString = getFormulaString(in);
			ArrayList<Formula> differentUpgradeFormulas = new ArrayList<>();
			ArrayList<Integer> luckPercents = new ArrayList<>();
			
			for(int i = 0; i < upgradeNum; i++)
			{
				HashMap<String, Double> formulaData = new HashMap<>();
				for(String variableName : extraVariables.keySet())
				{
					double variableValue = extraVariables.get(variableName).get(i); // might need more explanation
					formulaData.put(variableName, variableValue);
				}
				Formula formula = new Formula(formulaString, formulaData);
				differentUpgradeFormulas.add(formula);
				
				System.out.print("Please enter the percent of this luck: ");
				int luckPercent = in.nextInt();
				luckPercents.add(luckPercent);
			}
			
			abilityLuckPercents.put(abilityName, luckPercents);
			formulas.put("luck", differentUpgradeFormulas);
		}
		
		System.out.println("Is it requiered to have an upgrade of any other ability before getting any of the upgrades of this ability?");
		
		if(yesNoQuestion(in))
		{
			ArrayList<HashMap<String, Integer>> requieredAbilities = new ArrayList<>();
			for(int i = 0; i < upgradeNum; i++)
			{
				System.out.println("Does upgrade" + (i + 1) + " require any other abilities?");
				HashMap<String, Integer> requieredAbilityForUpgrade = new HashMap<>();
				if(yesNoQuestion)
				{
					while(true)
					{
						System.out.println("Which ability?");
						String requieredAbilityName = getRequieredAbilityName(in, abilityName);
						System.out.println("Which Upgrade?");
						int requieredAbilityUpgrade = getRequieredAbilityUpgrade(in, requieredAbilityName);
						
						requieredAbilityForUpgrade.put(requieredAbilityName, requieredAbilityUpgrade);
						
						System.out.println("Does this upgrade need more requiered abilities?");
						if(!yesNoQuestion)
						{
							break;
						}
					}
				}
				
				requieredAbilities.add(requieredAbilityForUpgrade);
			}
		}
		
		allRequieredAbilities.put(abilityName, requieredAbilities);
		
		while(true)
		{
			System.out.println("Please enter the target of this item");
			showPossibleAbilityTargets();
			abilityTarget = in.next();
			
			if(abilityTargets.containsKey(abilityTarget))
			{
				abilityTargets.put(abilityName, abilityTarget);
				if(isMultipuleTarget(abilityTarget))
				{
					System.out.println("We will choose one of the targets as the main one");
					ArrayList<Integer> secondaryTargetShare = new ArrayList<>();
					for(int i = 0; i < upgradeNum; i++)
					{
						System.out.println("How much do you want the percent share of other targets be in upgrade " + (i + 1) + "?");
						System.out.println("(obviously if you want all of the targets to be the same, enter 100");
						System.out.print("Share: ");
						//check valid input
						int secondaryTargetShareAmount = in.nextInt();
						secondaryTargetShare.add(secondaryTargetShareAmount);
					}
					
					secondaryTargetShares.put(abilityName, secondaryTargetShare);
				}
				break;
			}
			//invalid input
		}
		
		System.out.println("Ability was freaking finally made!")
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