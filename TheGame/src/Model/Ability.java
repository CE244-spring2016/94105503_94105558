package Model;

import Auxiliary.Formula;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.ForkJoinPool;

/**
 * Created by Vahid on 5/5/2016.
 */
public class Ability {
    protected String name;
    protected HashMap<String, ArrayList<Formula>> formulas;
    protected ArrayList<Integer> upgradeXPs;
    protected int cooldownTurn;
    protected int currentUpgradeNum;
    protected ArrayList<HashMap<String, Integer>> requiredAbilities;
    protected Hero hero;
    protected ArrayList<Integer> luckPercents;
    protected String abilityTarget;
    protected boolean instantEffective;

}
