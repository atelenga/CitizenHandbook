package com.app.handbook;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MakeRequestActivity extends Activity {
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.address_request);    			
    	setTitle(R.string.button_Make_Request_label);
        
    	set_buttonSendRequestClickListener();
    	set_buttonClearClickListener();
    	
        Log.d("CitizenHandbook", "Make Request view started");
        
    }

	private void set_buttonClearClickListener() {
		Button ClearButton = (Button)findViewById(R.id.button_Clear);
		ClearButton.setOnClickListener(new View.OnClickListener() {
	    	public void onClick(View v) {
	    		// Clear all fields	    		
                //Log.d("CitizenHandbook", "Main view showed");
	    	}
	    });
	}

	private void set_buttonSendRequestClickListener() {
		final Activity parentActivity = this;
		Button makeRequestButton = (Button)findViewById(R.id.button_Send_Request);
	    makeRequestButton.setOnClickListener(new View.OnClickListener() {
	    	public void onClick(View v) {
	    		
	    		Intent i = new Intent(parentActivity,CitizenHandbookActivity.class);
	    		i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_SINGLE_TOP);
	    		startActivity(i);	    		
                Log.d("CitizenHandbook", "Main view showed");
	    	}
	    });   
	
	}
		
		
}
