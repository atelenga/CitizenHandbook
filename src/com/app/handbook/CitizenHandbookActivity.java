package com.app.handbook;

import android.app.Activity;
import android.app.Dialog;
import android.content.ContextWrapper;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class CitizenHandbookActivity extends Activity {
    boolean IsAddressSet = false;
    public String app_ver = "";

	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        checkIsAddressSet();
        set_aboutButtonClickListener();
        
        Log.d("CitizenHandbook", "Main view started");
        
    }
	
		
	private void set_aboutButtonClickListener() {
		// Method to show About view
			    
		Button aboutButton = (Button)findViewById(R.id.button_About_Show);
	    aboutButton.setOnClickListener(new View.OnClickListener() {
	    	public void onClick(View v) {
	    		
	    		final Dialog dialog = new Dialog(CitizenHandbookActivity.this);
	            dialog.setContentView(R.layout.about);
	            dialog.setTitle(R.string.about);
	            	             
	            //displayVersionName();
	            	            
	            Button button = (Button) dialog.findViewById(R.id.button_Close);
                button.setOnClickListener(new View.OnClickListener() {
                	public void onClick(View v) {
                		dialog.dismiss();
                    }
                });
                dialog.show();
	    		
                Log.d("CitizenHandbook", "About view showed");
	    	}
	    });   
	}
	private boolean checkIsAddressSet() {		
		/*
		 * Check if we already set address to use
		 * We check if db.xml exists
		 *  
		*/
		return IsAddressSet;
		
	}
	private void displayVersionName() {
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
	}
}
