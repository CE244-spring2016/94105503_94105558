package Model;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ConnectException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * Created by Vahid on 7/7/2016.
 */
public class HostJoin
{
    public ObjectInputStream in;
    public ObjectOutputStream out;
    private CommonMsg commonMsg;
    public HostJoin(CommonMsg commonMsg)
    {
        this.commonMsg = commonMsg;
    }

    public ObjectInputStream getIn()
    {
        return in;
    }

    public ObjectOutputStream getOut()
    {

        return out;
    }

    public void setClient(int i)
    {
        Socket socket = null;
        try
        {
            socket = new Socket("127.0.0.1", i);
        } catch (UnknownHostException e)
        {
//            System.out.println("IP is not correct");
        } catch (ConnectException e)
        {
            setClient(i);
        } catch (IOException e)
        {
            e.printStackTrace();
        }
        try
        {
            if (socket != null)
            {
                out = new ObjectOutputStream(socket.getOutputStream());
                in = new ObjectInputStream(socket.getInputStream());
            } else
            {
//                System.out.println("socket is null");
            }
        } catch (IOException e)
        {
            e.printStackTrace();
        }
//        System.out.println("success");
        try
        {
            out.writeObject(commonMsg);
        } catch (IOException e)
        {
            e.printStackTrace();
        }

    }

    public void setServer(int i)
    {
        ServerSocket servsock = null;
        Socket sock = null;
        try
        {
            servsock = new ServerSocket(i);
        } catch (IOException e)
        {
//            System.out.println("couldn't make a host");
        }

//        System.out.println("Waiting for your opponet to connect");

        try
        {
            sock = servsock.accept();
        } catch (IOException e)
        {
//            System.out.println("couldn't connect");
        }
//        System.out.println("connected");
        try
        {
            if (sock != null)
            {
                out = new ObjectOutputStream(sock.getOutputStream());
                in = new ObjectInputStream(sock.getInputStream());
            } else
            {
//                System.out.println("socket is null");
            }
        } catch (IOException e)
        {
            e.printStackTrace();
        }
        try
        {
            commonMsg = (CommonMsg) in.readObject();
        } catch (IOException e)
        {
            e.printStackTrace();
        } catch (ClassNotFoundException e)
        {
            e.printStackTrace();
        }
//        System.out.println("Oponent has conected");
    }

    public CommonMsg getCommonMsg()
    {
        return commonMsg;
    }
}
