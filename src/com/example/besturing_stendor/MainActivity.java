package com.example.besturing_stendor;

import android.app.Activity;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.ToggleButton;
import android.view.View;

public class MainActivity
    extends Activity
    implements SensorEventListener, View.OnClickListener
{
	private SensorManager sensorManager;
	
	TextView xCoor; // declare X axis object
	TextView yCoor; // declare Y axis object
    ToggleButton btnVerbinding;
    Robot robot;
	
	@Override
	public void onCreate(Bundle savedInstanceState)
    {
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
        robot = new Robot();

		
		xCoor=(TextView)findViewById(R.id.xcoor); // create X axis object
		yCoor=(TextView)findViewById(R.id.ycoor); // create Y axis object
        btnVerbinding = (ToggleButton)findViewById(R.id.toggleButton1);
        btnVerbinding.setOnClickListener(this);
        		
		sensorManager=(SensorManager)getSystemService(SENSOR_SERVICE);
		// add listener. The listener will be HelloAndroid (this) class
		sensorManager.registerListener(this, 
				sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),
				SensorManager.SENSOR_DELAY_NORMAL);
		
		/*	More sensor speeds (taken from api docs)
		    SENSOR_DELAY_FASTEST get sensor data as fast as possible
		    SENSOR_DELAY_GAME	rate suitable for games
		 	SENSOR_DELAY_NORMAL	rate (default) suitable for screen orientation changes
		*/
	}

	public void onAccuracyChanged(Sensor sensor,int accuracy)
    {
		
	}
	
    public void onClick(View arg0)
    {
        robot.connect("10.210.6.40");
    }

	public void onSensorChanged(SensorEvent event){
		
		// check sensor type
		if(event.sensor.getType()==Sensor.TYPE_ACCELEROMETER)
		{
			// assign directions
			float x=event.values[0];
			float y=event.values[1];
			
			xCoor.setText("X: "+(int)x);
			yCoor.setText("Y: "+(int)y);
		}
	}
}

