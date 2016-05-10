package Model;

import java.net.InetSocketAddress;
import java.util.HashMap;

/**
 * Created by Vahid on 5/5/2016.
 */
public class Enemy extends Warrior
{
    public int getID()
    {
        return ID;
    }

    public void setID(int ID)
    {
        this.ID = ID;
    }

    private int ID;
}
