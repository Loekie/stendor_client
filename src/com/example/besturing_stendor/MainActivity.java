package com.example.besturing_stendor;

import android.app.Activity;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.ToggleButton;
import android.view.View;

public class MainActivity
    extends Activity
    implements SensorEventListener, View.OnClickListener
{
	private SensorManager sensorManager;
	
	TextView xCoor;
	TextView yCoor; 
	EditText ipAdress;
    ToggleButton btnVerbinding;
    ProgressBar Activiteitbalk;
    
    Robot robot;
	
	@Override
	public void onCreate(Bundle savedInstanceState)
    {
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
        robot = new Robot();

		
		xCoor=(TextView)findViewById(R.id.xcoor); 
		yCoor=(TextView)findViewById(R.id.ycoor);
		ipAdress = (EditText) findViewById(R.id.editText1);
        btnVerbinding = (ToggleButton)findViewById(R.id.toggleButton1);
        btnVerbinding.setOnClickListener(this);
        Activiteitbalk = (ProgressBar)findViewById(R.id.progressBar1);
        		
		sensorManager=(SensorManager)getSystemService(SENSOR_SERVICE);
		
		sensorManager.registerListener(this, 
				sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),
				SensorManager.SENSOR_DELAY_NORMAL);
		
	}

	public void onAccuracyChanged(Sensor sensor,int accuracy)
    {
		
	}
	
    public void onClick(View arg0)
    {
    	if(btnVerbinding.getText().equals("Disconnect")) 
    	{
    		robot.connect(ipAdress.getText().toString(), 2002);
    		Activiteitbalk.setVisibility(View.VISIBLE);
		}
    	else
    	{
    		Activiteitbalk.setVisibility(View.INVISIBLE);
		}
    	
    }

	public void onSensorChanged(SensorEvent event){
		
		
		if(event.sensor.getType()==Sensor.TYPE_ACCELEROMETER)
		{
			float x=event.values[0];
			float y=event.values[1];
			
			xCoor.setText("X: "+x);
			yCoor.setText("Y: "+y);
		}
	}
}

