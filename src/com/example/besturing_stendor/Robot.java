package com.example.besturing_stendor;

public class Robot
{
    Thread connection;

    public Robot()
    {
        
    }

    public void connect(String ip)
    {
        connection = new Thread(Connection.connect(ip));
    }
}

