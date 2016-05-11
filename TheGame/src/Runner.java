import Controller.GameScenario;
import Controller.UserInterface;

import java.util.Scanner;

/**
 * Created by Vahid on 5/11/2016.
 */
public class Runner
{
    public static void main(String[] args)
    {
        Scanner scanner = new Scanner(System.in);
        UserInterface userInterface = new UserInterface();
        GameScenario gameScenario = new GameScenario(userInterface, scanner);
        gameScenario.scenario();
    }
}
