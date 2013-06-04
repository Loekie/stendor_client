package com.example.besturing_stendor;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.util.Base64;
import android.widget.Toast;

public class Connection extends Thread implements Runnable
{
	MainActivity activity = null;
    String ip;
    int port;
    Socket socket;
    InputStream is;
    OutputStream os;
    public boolean connected = false;

    public static Connection connect(MainActivity activity, String ip, int port)
    {
        return new Connection(activity, ip, port);
    }

    private Connection(MainActivity activity, String ip, int port)
    {
        this.ip = ip;
        this.port = port;
        this.activity = activity;
    }

    public void run()
    {
        try
        {
            socket = new Socket(ip, port);
            is = socket.getInputStream();
            os = socket.getOutputStream();
            connected = true;
            activity.robot.init();
            while(true) 
            {
				this.read();
            }
        
    	} catch (UnknownHostException e) {
		this.error(e.toString());
    	} catch (IOException e) {
		this.error(e.toString());
    	}
    }
    
    public void close() {
		if(connected) {
			try {
				socket.close();
				connected = false;
				activity.runOnUiThread(new Runnable() {
		            public void run() 
		            {
		            	Toast.makeText(activity.getApplicationContext(), "Connection Closed", Toast.LENGTH_LONG).show();
		            }
		        });
				
			} catch (IOException e) {
				// Error while Closing Socket Connection
			}
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
    
	private void read() {
			
			DataInputStream in = new DataInputStream(is);
			try {
				String base64String = in.readUTF();
				
				byte[] jdata = Base64.decode(base64String, Base64.DEFAULT);
				
				Bitmap bmp = BitmapFactory.decodeByteArray(jdata, 0, jdata.length);
				
				  Matrix matrix = new Matrix();
				  matrix.postRotate(0);
				  // We rotate the same Bitmap
				  bmp = Bitmap.createBitmap(bmp, 0, 0, bmp.getWidth(), bmp.getHeight(), matrix, false);
					
					activity.bmp = bmp;
					
					activity.runOnUiThread(new Runnable() {
			            public void run() 
			            {
			            	activity.changeImage();
			            }
			        });
				} 
			catch (Exception e)
			{
				e.printStackTrace();
			}
	}
	
	private void error(String e) {
		activity.runOnUiThread(new Runnable() {
            public void run() {
            	Toast.makeText(activity.getApplicationContext(), "Error!", Toast.LENGTH_LONG).show();
            }
        });
	}
	
	public void doToast(final String text) {
		activity.runOnUiThread(new Runnable() {
	        public void run() {
	        	Toast.makeText(activity.getApplicationContext(), text, Toast.LENGTH_LONG).show();
	        }
	    });
	}

}

