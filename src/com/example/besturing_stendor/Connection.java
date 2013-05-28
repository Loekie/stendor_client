package com.example.besturing_stendor;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class Connection implements Runnable
{
    String ip;
    int port;
    Socket socket;
    InputStream is;
    OutputStream os;

    public static Connection connect(String ip, int port)
    {
        return new Connection(ip, port);
    }

    private Connection(String ip, int port)
    {
        this.ip = ip;
        this.port = port;
    }

    public void run()
    {
        try
        {
            socket = new Socket(ip, port);
        }
        catch (Exception e)
        {
        }
    }

}

