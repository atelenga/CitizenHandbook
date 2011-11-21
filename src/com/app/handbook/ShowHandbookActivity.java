package com.app.handbook;

import android.app.Activity;
import android.content.SharedPreferences;
//import android.view.View;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebView;

public class ShowHandbookActivity extends Activity {
	
    String MY_PREFS = "Handbook";
    String searchString = "";
    int mode = Activity.MODE_PRIVATE;
    
	public void onCreate(Bundle savedInstanceState) {
				
        super.onCreate(savedInstanceState);
        setContentView(R.layout.webshow);
        
        SharedPreferences settings = getSharedPreferences(MY_PREFS, mode);
    	String city = settings.getString("City","");
        String street = settings.getString("Street","");
    	String number = settings.getString("Number","");
    	String building = settings.getString("Building","");
    	
    	//http://192.168.1.108/search/?q0=&q1=255&q2=&q3=
        
    	if (building==""){
    		searchString = "http://192.168.1.105/search/?q0=" + city + "&q1=" + street + "&q2=" + number;
    	}
    	else {
        	searchString = "http://192.168.1.105/search/?q0=" + city + "&q1=" + street + "&q2=" + number + "q3=" + building;
    	}
    	
        WebView webview = (WebView) findViewById(R.id.webview);
        webview.getSettings().setJavaScriptEnabled(true);
        webview.loadUrl(searchString);
        
        Log.d("CitizenHandbook", "Main view started");
        
    }
	
}
