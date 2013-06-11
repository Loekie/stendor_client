package com.example.besturing_stendor;

import com.MobileAnarchy.Android.Widgets.Joystick.JoystickView;
import com.MobileAnarchy.Android.Widgets.Joystick.JoystickMovedListener;

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
import android.view.Window;
import android.view.WindowManager;

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
    JoystickView joystick;
	JoystickView joystick2;
    
    public Robot robot = null;
    float tolerance = 0.9f;
    
    
	
	@Override
	public void onCreate(Bundle savedInstanceState)
    {
		
		super.onCreate(savedInstanceState);
		
		requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN);
		
		setContentView(R.layout.activity_main);
        robot = new Robot();

		camimage = (ImageView) findViewById(R.id.imageView1);
		ipAdress = (EditText) findViewById(R.id.editText1);
        btnVerbinding = (ToggleButton)findViewById(R.id.toggleButton1);
        btnVerbinding.setOnClickListener(this);
        progress = (ProgressBar)findViewById(R.id.progressBar1);
        joystick = (JoystickView)findViewById(R.id.joystickView1);
		joystick2 = (JoystickView)findViewById(R.id.JoystickView01);
		
		joystick.setOnJostickMovedListener(_listener);
        joystick2.setOnJostickMovedListener(listener2);
        		
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
	
	private JoystickMovedListener _listener = new JoystickMovedListener() 
	{
		@Override
		public void OnMoved(int pan, int tilt) 
		{
			try
	        {
				robot.rightDrive(tilt*25);
	        }
	        catch (Exception e)
	        {
	        }	
		}
		@Override
		public void OnReleased() 
		{

		}
		
		public void OnReturnedToCenter() 
		{

		};
	};
	
	 private JoystickMovedListener listener2 = new JoystickMovedListener() 
	 {
			@Override
			public void OnMoved(int pan, int tilt) 
			{
				try
		        {
					robot.leftDrive(tilt*25);
		        }
		        catch (Exception e)
		        {
		        }	
			}
			@Override
			public void OnReleased() 
			{

			}
			
			public void OnReturnedToCenter() 
			{

			};
			
		}; 
	
}


