package com.sourcebits.taskgps;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity implements LocationListener
{

	 	protected LocationManager locationManager;
	 	
	    protected Context context;
	    private double latitudeValue = 0;
	    private double longitudeValue = 0;
	    TextView latitude,longitude;
	    Button refresh;
	    ProgressDialog dialog;
	    @Override
	    protected void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.activity_main);
	        latitude = (TextView)findViewById(R.id.latitude);
	        longitude = (TextView)findViewById(R.id.longitude);
	        refresh = (Button)findViewById(R.id.refresh);
	        
	        refresh.setOnClickListener(new View.OnClickListener(	) {
				
				@Override
				public void onClick(View v) {
					refresh();
					
				}
			});
	        
	        dialog = new ProgressDialog(MainActivity.this);
	        dialog.show();
	        dialog.setMessage("Getting Coordinates");
	 
	        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
	        if (locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)) 
	        {
	            locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 60000,1, this);
	        } 
	        else if (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER))
	        {
	            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 60000,1, this);
	        }
	        else {
	                dialog.dismiss();
	 
	                Toast.makeText(getApplicationContext(), "Enable Location", Toast.LENGTH_LONG).show();
	            }
	    }
	    protected void refresh() {
	 
	       super.onResume();
	       this.onCreate(null);
	 
	    }
	    
	    
	 
	    @Override
	    public void onLocationChanged(Location location) 
	    {
	        // TODO Auto-generated method stub
	        dialog.show();
	        latitudeValue = location.getLatitude();
	        longitudeValue =location.getLongitude();
	        if (latitudeValue != 0 && longitudeValue != 0){
	 
	        latitude.setText("Latitude is :" + latitudeValue);
	        longitude.setText("Longitude is :" +longitudeValue);
	        dialog.dismiss();
	        }
	    }
	 
	    @Override
	    public void onProviderDisabled(String provider) 
	    {
	        // TODO Auto-generated method stub
	 
	    }
	 
	    @Override
	    public void onProviderEnabled(String provider)
	    {
	        // TODO Auto-generated method stub
	 
	    }
	 
	    @Override
	    public void onStatusChanged(String provider, int status, Bundle extras) 
	    {
	        // TODO Auto-generated method stub
	    }
}
