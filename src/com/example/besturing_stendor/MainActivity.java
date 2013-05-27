package com.example.besturing_stendor;

	import android.app.Activity;
	import android.hardware.Sensor;
	import android.hardware.SensorEvent;
	import android.hardware.SensorEventListener;
	import android.hardware.SensorManager;
	import android.os.Bundle;
	import android.widget.TextView;

	public class MainActivity extends Activity implements SensorEventListener
    {
		private SensorManager sensorManager;
		
		TextView xCoor; // declare X axis object
		TextView yCoor; // declare Y axis object
		
		@Override
		public void onCreate(Bundle savedInstanceState)
        {
			
			super.onCreate(savedInstanceState);
			setContentView(R.layout.activity_main);
			
			xCoor=(TextView)findViewById(R.id.xcoor); // create X axis object
			yCoor=(TextView)findViewById(R.id.ycoor); // create Y axis object
			
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
