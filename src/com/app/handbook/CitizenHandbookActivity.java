package com.app.handbook;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.content.Intent;


import java.io.*;

public class CitizenHandbookActivity extends Activity {
    boolean IsAddressSet = false;
    public String app_ver = "";
    
    String MY_PREFS = "Handbook";
    int mode = Activity.MODE_PRIVATE;
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        boolean mExternalStorageAvailable = false;
        boolean mExternalStorageWriteable = false;
        
        String state = Environment.getExternalStorageState();

        if (Environment.MEDIA_MOUNTED.equals(state)) {
            // We can read and write the media
            mExternalStorageAvailable = mExternalStorageWriteable = true;
        } else if (Environment.MEDIA_MOUNTED_READ_ONLY.equals(state)) {
            // We can only read the media
            mExternalStorageAvailable = true;
            mExternalStorageWriteable = false;
        } else {
            // Something else is wrong. It may be one of many other states, but all we need
            //  to know is we can neither read nor write
            mExternalStorageAvailable = mExternalStorageWriteable = false;
        }        
        
        
        set_showButtonClickListener();
        set_aboutButtonClickListener();
        set_makeRequestButtonClickListener();
        /*
        final File dbfile = new File(getExternalFilesDir(null), "db.xml");
        IsAddressSet = dbfile.exists();
        
        if (!IsAddressSet) {
        	final Activity parentActivity = this;
        	final Dialog dialog = new Dialog(CitizenHandbookActivity.this);
			dialog.setContentView(R.layout.setup);
			Button button = (Button) dialog.findViewById(R.id.infoCloseButton);
			button.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {				
				Intent i = new Intent(parentActivity,MakeRequestActivity.class);	    		
	    		startActivity(i);	
				dialog.dismiss();
                }
            });
			dialog.show();			
        }       
        */        
                
        Log.d("CitizenHandbook", "Main view started");
        
    }
	
		
	private void set_showButtonClickListener() {
		final Activity parentActivity = this;
		Button showtButton = (Button)findViewById(R.id.button_Handbook_Show);
		showtButton.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				
				SharedPreferences settings = getSharedPreferences(MY_PREFS, mode);
		    	String street = settings.getString("Street","");
		    	String number = settings.getString("Number","");
		    	String building = settings.getString("Building","");
		    	
		    	if (street=="" && number==""){
		    		
		    		Intent i = new Intent(parentActivity,MakeRequestActivity.class);	    		
		    		startActivity(i);
		    	}
	    		
                Log.d("CitizenHandbook", "Handbook view showed");
	    	}
	    });   
		
	}


	private void set_makeRequestButtonClickListener() {
		// Method to show Make request view
		final Activity parentActivity = this;
		Button makeRequestButton = (Button)findViewById(R.id.button_Make_Request);
	    makeRequestButton.setOnClickListener(new View.OnClickListener() {
	    	public void onClick(View v) {	    			    		
	    		Intent i = new Intent(parentActivity,MakeRequestActivity.class);	    		
	    		startActivity(i);	    		
                Log.d("CitizenHandbook", "Make view showed");
	    	}
	    });   
	
	}


	private void set_aboutButtonClickListener() {
		// Method to show About view
			    
		Button aboutButton = (Button)findViewById(R.id.button_About_Show);
		aboutButton.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				
				final Dialog dialog = new Dialog(CitizenHandbookActivity.this);
				dialog.setContentView(R.layout.about);
				dialog.setTitle(R.string.about);
				           	            
				Button button = (Button) dialog.findViewById(R.id.button_Close);
				button.setOnClickListener(new View.OnClickListener() {
				public void onClick(View v) {
                		dialog.dismiss();
                    }
                });
				
				String versionName = "";
				
				PackageInfo packageInfo;
				try {
					packageInfo = getPackageManager().getPackageInfo(getPackageName(), 0);
					versionName = "v " + packageInfo.versionName;
				} catch (NameNotFoundException e) {
					e.printStackTrace();
				}			
				
				TextView tv = (TextView) findViewById(R.id.textViewVersion);
				tv.setText(versionName);
				
				dialog.show();
	    		
                Log.d("CitizenHandbook", "About view showed");
	    	}
	    });   
	}	
	
}
