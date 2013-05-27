package com.example.besturing_stendor;

public class Robot
{
    Connection connection;

    public Robot()
    {
    }

    public void connect(String ip)
    {
        connection = Connection.connect(ip);
    }
}

