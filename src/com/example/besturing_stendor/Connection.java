package com.example.besturing_stendor;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class Connection implements Runnable
{
    Socket socket;
    InputStream is;
    OutputStream os;

    public static Connection connect(String ip)
    {
        return new Connection(ip);
    }

    private Connection(String ip)
    {
        try
        {
            socket = new Socket(ip, 2001);
        }
        catch (Exception e)
        {
        }
    }

    public void run()
    {

    }

}

