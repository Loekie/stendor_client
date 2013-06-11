package com.example.besturing_stendor;

public class Robot
{
    public Connection connection;
    public Connection connectioncamimage;
    
    private int panDegrees = 90;
    private int tiltDegrees = 90;
    

    public Robot()
    {
        
    }
    
    public void closeConnection()
    {
    	connection.close();
    	connectioncamimage.close();
    }

    public void beeldconnect(MainActivity activity, String ip, int port)
    {
    	connectioncamimage = Connection.connect(activity, ip, port);
    	connectioncamimage.start();
    }
    
    public void dataconnect(MainActivity activity, String ip, int port)
    {
    	connection = Connection.connect(activity, ip, port);
        connection.start();
    }
    
    public void init()
    {
		connection.write("P" + panDegrees);
		connection.write("T" + tiltDegrees);
    }
    
    public void pan(float pos)
    {
    	if((pos >= 0 && (pos + panDegrees) < 180) || (pos < 0 && (pos + panDegrees) > 0) && connection.connected)
    	{
    		panDegrees += pos;
    		connection.write("P" + panDegrees);
    	}
    }
    
    public void tilt(float pos)
    {
    	if((pos >= 0 && (pos + tiltDegrees) < 180) || (pos < 0 && (pos + tiltDegrees) > 0) && connection.connected)
    	{
    		tiltDegrees += pos;
    		connection.write("T" + tiltDegrees);
    	}
    }
    
    public void leftDrive(int ld)
    {
    	if(ld > 0)
    	{
    		connection.write("A" + ld);
    	}
    	if(ld < 0)
    	{
    		ld = ld* -1;
    		connection.write("Q" + ld);
    	} 	
    	
    }
       
    public void rightDrive(int rd)
    {
    	if(rd > 0)
    	{
    		connection.write("S" + rd);
    	}
    	if(rd < 0)
    	{
    		rd = rd * -1;
    		connection.write("W" + rd);
    	}
    }
    
}

