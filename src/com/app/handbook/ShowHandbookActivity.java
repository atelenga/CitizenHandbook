package com.app.handbook;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

public class ShowHandbookActivity extends Activity {
	
    String MY_PREFS = "Handbook";
    String searchString = "";
    String site = "http://handbook.voland.lolocum/search/"; 
    int mode = Activity.MODE_PRIVATE;   
    
	public void onCreate(Bundle savedInstanceState) {
				
        super.onCreate(savedInstanceState);
        getWindow().requestFeature(Window.FEATURE_PROGRESS);
    	getWindow().setFeatureInt( Window.FEATURE_PROGRESS, Window.PROGRESS_VISIBILITY_ON);

        setContentView(R.layout.webshow);
        
        SharedPreferences settings = getSharedPreferences(MY_PREFS, mode);
    	String city = settings.getString("City","");
        String street = settings.getString("Street","");
    	String number = settings.getString("Number","");
    	String building = settings.getString("Building","");
    	
    	//http://192.168.1.108/search/?q0=&q1=255&q2=&q3=
        
    	if (building=="")
    		searchString = site + "?q0=" + city + "&q1=" + street + "&q2=" + number;
    	else 
        	searchString = site + "?q0=" + city + "&q1=" + street + "&q2=" + number + "&q3=" + building;
    	
    	// Let's display the progress in the activity title bar, like the
    	// browser app does.
    	WebView webview = (WebView) findViewById(R.id.webview);   	
    	
    	webview.getSettings().setJavaScriptEnabled(true);
    	webview.getSettings().setSupportZoom(true);
    	webview.getSettings().setBuiltInZoomControls(true);
    	
    	final Activity activity = this;
    	webview.setWebChromeClient(new WebChromeClient() {
    	  public void onProgressChanged(WebView view, int progress) {
    	    // Activities and WebViews measure progress with different scales.
    	    // The progress meter will automatically disappear when we reach 100%
    	    activity.setProgress(progress * 100);
    	  }
    	});
    	
    	webview.setWebViewClient(new WebViewClient() {
    	  public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
    	    Toast.makeText(activity, "Ошибка!" + description, Toast.LENGTH_SHORT).show();
    	  }
    	});     	
    	
    	webview.loadUrl(searchString);
        
        Log.d("CitizenHandbook", "Main view started");
        
    }
	
}
