package Model;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * Created by Vahid on 7/2/2016.
 */
//VAHID
public class NetworkHandler
{
    public ObjectInputStream in;
    public ObjectOutputStream out;
    private CommonMsg commonMsg;

    public NetworkHandler(CommonMsg commonMsg, ObjectInputStream in, ObjectOutputStream out)
    {
        this.commonMsg = commonMsg;
        this.in = in;
        this.out = out;
    }

    public CommonMsg getCommonMsg()
    {
        return commonMsg;
    }

    public void setCommonMsg(CommonMsg commonMsg)
    {
        this.commonMsg = commonMsg;
    }

    public void send()
    {
        try
        {
            out.writeUnshared(commonMsg);
            out.flush();
        } catch (IOException ex)
        {
            ex.printStackTrace();
        }
    }

    public void receive()
    {
        try
        {
            commonMsg = (CommonMsg) in.readUnshared();
        } catch (IOException ex)
        {
            ex.printStackTrace();
        } catch (ClassNotFoundException ex)
        {
            ex.printStackTrace();
        }
    }


}

