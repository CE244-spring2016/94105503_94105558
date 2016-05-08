package Model;

import java.net.InetSocketAddress;
import java.util.HashMap;

/**
 * Created by Vahid on 5/5/2016.
 */
public class Enemy extends Warrior
{
    private HashMap<String, Integer> data;
    private InetSocketAddress name;
    private InetSocketAddress ID;

    public HashMap<String, Integer> getData()
    {
        return data;
    }

    public InetSocketAddress getName()
    {
        return name;
    }

    public InetSocketAddress getID()
    {
        return ID;
    }
}
