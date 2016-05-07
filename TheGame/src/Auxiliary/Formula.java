package Auxiliary;

import java.util.HashMap;
import java.util.Objects;
import java.util.Set;

/**
 * Created by Vahid on 5/5/2016.
 */
public class Formula {
    private String theFormula;
    private HashMap<String, Double> extraVariable;
    //just for knowing --- not using
    private final String[] basicVariable = {"xp","ep","mn","hp","maj"};
    //

    public Formula(String theFormula, HashMap<String, Double> extraVariable) {
        this.theFormula = theFormula;
        this.extraVariable = extraVariable;
    }

    private String replaceFormula(HashMap<String, Integer> values) {
        String theNewFormula = theFormula;
        if(extraVariable != null) {
            String[] extraVariables = new String[extraVariable.size()];
            extraVariable.keySet().toArray(extraVariables);
            for (int i = 0; i < extraVariables.length; i++) {
                theNewFormula = theNewFormula.replaceAll(extraVariables[i], extraVariable.get(extraVariables[i]).toString());
            }
        }
        if(values != null) {
            String[] basicVariables = new String[values.size()];
            values.keySet().toArray(basicVariables);
            for (int i = 0; i < basicVariables.length; i++) {
                theNewFormula = theNewFormula.replaceAll(basicVariables[i], values.get(basicVariables[i]).toString());
            }
        }
        return theNewFormula;
    }

    public int parseFormula(HashMap<String, Integer> values){
        String formula = replaceFormula(values);
        Calculator calc = new Calculator(formula);
        calc.preprocessor();
        calc.infix2postfix();
        BinaryTree eTree = calc.buildExpressionTree();
        return (int)calc.evalExpressionTree(eTree);

    }

    public String getTheFormula() {
        return theFormula;
    }

    public HashMap<String, Double> getExtraVariable() {
        return extraVariable;
    }
}
