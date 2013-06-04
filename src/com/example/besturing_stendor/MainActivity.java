package com.example.besturing_stendor;

import android.app.Activity;
import android.graphics.Bitmap;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.ToggleButton;
import android.view.View;

public class MainActivity
    extends Activity
    implements SensorEventListener, View.OnClickListener
{
	private SensorManager sensorManager;

	ImageView camimage;
	Bitmap bmp;
	EditText ipAdress;
    ToggleButton btnVerbinding;
    ProgressBar progress;
    
    public Robot robot = null;
    float tolerance = 0.9f;
    
    
	
	@Override
	public void onCreate(Bundle savedInstanceState)
    {
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
        robot = new Robot();

		camimage = (ImageView) findViewById(R.id.imageView1);
		ipAdress = (EditText) findViewById(R.id.editText1);
        btnVerbinding = (ToggleButton)findViewById(R.id.toggleButton1);
        btnVerbinding.setOnClickListener(this);
        progress = (ProgressBar)findViewById(R.id.progressBar1);
        		
		sensorManager=(SensorManager)getSystemService(SENSOR_SERVICE);
		
		sensorManager.registerListener(this, 
				sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),
				SensorManager.SENSOR_DELAY_NORMAL);
		
	}


	
    public void onClick(View arg0)
    {
    	if(btnVerbinding.getText().equals("Disconnect")) 
    	{
    		robot.dataconnect(MainActivity.this, ipAdress.getText().toString(), 2002);
    		robot.beeldconnect(MainActivity.this, ipAdress.getText().toString(), 2001);
    		progress.setVisibility(View.VISIBLE);
		}
    	if(btnVerbinding.getText().equals("Connect"))
    	{
    		robot.closeConnection();
    		progress.setVisibility(View.INVISIBLE);
		}
    }
    
    public void changeImage() {
		camimage.setImageBitmap(this.bmp);
	}

	public void onSensorChanged(SensorEvent event){
		
		if(event.sensor.getType()==Sensor.TYPE_ACCELEROMETER)
		{
			float x=event.values[0];
			float y=event.values[1];
			
			if((x > tolerance && x >= 0) || (x < (tolerance * -1) && x < 0))
			{
				try
		        {
					robot.pan(x);
		        }
		        catch (Exception e)
		        {
		        }
			}
			if((y > tolerance && y >= 0) || (y < (tolerance * -1) && y < 0))
			{
				try
		        {
					robot.tilt(y);
		        }
		        catch (Exception e)
		        {
		        }	
			}
		 }	
	}
	
	public void onAccuracyChanged(Sensor sensor,int accuracy)
    {
		
	}
	
}


