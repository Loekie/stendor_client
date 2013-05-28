package com.example.besturing_stendor;

public class Robot
{
    private Connection connection;
    private int panDegrees = 90;
    private int tiltDegrees = 90;
    

    public Robot()
    {
        
    }

    public void connect(String ip, int port)
    {
        connection = Connection.connect(ip, port);
        connection.start();
        init();
    }
    
    private void init()
    {
		connection.write("P" + panDegrees);
		connection.write("T" + tiltDegrees);
    }
    
    public void pan(float pos)
    {
    	if((pos >= 0 && (pos + panDegrees) < 180) || (pos < 0 && (pos + panDegrees) > 0))
    	{
    		panDegrees += pos;
    		connection.write("P" + panDegrees);
    	}
    }
    
    public void tilt(float pos)
    {
    	if((pos >= 0 && (pos + tiltDegrees) < 180) || (pos < 0 && (pos + tiltDegrees) > 0))
    	{
    		tiltDegrees += pos;
    		connection.write("T" + tiltDegrees);
    	}
    }
}

