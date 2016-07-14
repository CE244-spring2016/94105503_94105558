package GUI;

import java.awt.event.KeyEvent;

/**
 * Created by ruhollah on 7/10/2016.
 */
public class Door
{
    private int direction;
    private int id;

    public Door(int direction, int id)
    {
        this.direction = direction;
        this.id = id;
    }

    public int getDirection()
    {
        return direction;
    }

    public void setDirection(int direction)
    {
        this.direction = direction;
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }
}
