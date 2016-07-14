package Model;

import Controller.GameScenario;
import Controller.LocalNetwork;
import Controller.NetworkScenario;
import Controller.UserInterface;

import java.util.Scanner;


public class Runner
{
    public static void main(String[] args)
    {
        Scanner scanner = new Scanner(System.in);
        UserInterface userInterface = new UserInterface(scanner);
        GameScenario gameScenario1 = new GameScenario(userInterface, scanner);
        GameScenario gameScenario2 = new GameScenario(userInterface, scanner);
        NetworkScenario networkScenario1 = new NetworkScenario(scanner, gameScenario1);
        NetworkScenario networkScenario2 = new NetworkScenario(scanner, gameScenario2);
        LocalNetwork localNetwork = new LocalNetwork(gameScenario1, gameScenario2, scanner, networkScenario1, networkScenario2);
        localNetwork.LocalNetworkStart();
//    }
//        networkScenario1.startNetworking();
    }
}
