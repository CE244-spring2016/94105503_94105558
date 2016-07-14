package Auxiliary;

import java.util.Random;

/**
 * Created by Vahid on 5/5/2016.
 */
public class Luck
{
    public static boolean isLikely(int luckPercent)
    {
        if (luckPercent < 100)
        {
            Random rand = new Random();
            int n = rand.nextInt(100) + 1;
            if (n >= 1 && n <= luckPercent) {
                return true;
            } else {
                return false;
            }

        } else
            return true;

    }

    public static int getRandom(int first, int second)
    {
        Random random = new Random();
        return (random.nextInt(second - first + 1) + first);
    }
}