import java.util.*;
/*
	We need init here!
*/

public class UserInterface
{
	private boolean customed;
	private ArrayList<String> heroClassNames; // new it in the constructor
	private HashMap<String, HashMap<String, Integer>> heroClassDatas; // new it in the constructor
	private HashMap<String, String> heroClassAbilities;
	private ArrayList<String> abilityNames;
	private ArrayList<String> attributes; // must make this fully in the constructor
	
	
	
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
				//heroClassNames.add(heroClassName);
				break;
			}
		}
		
		System.out.println("Please enter the amount you want for each attribute");
		
		for(int i = 0; i < attributes.size(); i++)
		{
			while(true)
			{
				String attributeName = attributes.get(i);
				
				System.out.print(attributes.get(i) + ": ");
				
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
			if(yesNoQuestion(in))
			{
				break;
			}
		}
		
		System.out.println("Hero Class was made!");
	}
	
	
	private void createHero(Scanner in)
	{
		
	}
	
	
	private void createNormalEnemy(Scanner in)
	{
		
	}
	
	
	private void createBossEnemy(Scanner in)
	{
		
	}
	
	
	private void createItem(Scanner in)
	{
		
	}
	
	
	private void createAbility(Scanner in)
	{
		
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
}