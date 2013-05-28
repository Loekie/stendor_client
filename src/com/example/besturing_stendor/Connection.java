package com.example.besturing_stendor;

import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class Connection extends Thread implements Runnable
{
    String ip;
    int port;
    Socket socket;
    InputStream is;
    OutputStream os;
    boolean connected = false;

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
            os = socket.getOutputStream();
            connected = true;
        }
        catch (Exception e)
        {
        }
    }
    
    public void write(String text)
    {
    	if(connected) 
    	{
			try 
			{
				DataOutputStream out = new DataOutputStream(os);
				out.writeUTF(text);
				out.flush();
			} catch (Exception e1) 
			{
				e1.printStackTrace();
			}
		}
    }

}

