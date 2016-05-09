package Auxiliary;

/**
 * Created by Vahid on 5/5/2016.
 */
public class StringParser
{
    //it smallize all the characters and sticks all of them together
    public String normalizer(String input) {
        String result;
        result = input.toLowerCase();
        result = result.replaceAll("\\s+","");
        return result;
    }
}
