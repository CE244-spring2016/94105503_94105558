package Model;

import Controller.GameScenario;
import Controller.UserInterface;

import java.util.Scanner;


public class Runner
{
    public static void main(String[] args)
    {
        Scanner scanner = new Scanner(System.in);
        UserInterface userInterface = new UserInterface(scanner);
        GameScenario gameScenario = new GameScenario(userInterface, scanner);
        gameScenario.scenario();
    }
}
