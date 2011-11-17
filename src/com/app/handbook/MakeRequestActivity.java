package com.app.handbook;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.ArrayAdapter; 
import android.widget.Spinner;

public class MakeRequestActivity extends Activity {
	public String[] krd_settelements = { "Краснодар", "п. Белозерный", "п. Березовый", 
    		"п. Дорожный", "п. Дружелюбный", "п. Зеленопольский", "п. Знаменский",
    		"п. Индустриальный", "п. Колосистый", "п. Краснодарский", "п. Краснолит",
    		"п. Лазурный", "п. Лорис", "п. отделения N 2 СКЗНИИСиВ", "п. отделения N 3 ОПХ КНИИСХ" ,
    		"п. отделения N 3 СКЗНИИСиВ", "п. отделения N 4 совхоза «Пашковский»", "п. Пашковский", "п. Плодородный",
    		"п. Победитель", "п. подсобного производственного хозяйства биофабрики" ,"п. Разъезд" ,"п. Российский" ,
    		"ст. Елизаветинская", "ст. Старокорсунская", "х. Восточный", "х. Копанской",
    		"х. Ленина", "х. Новый", "х. Октябрьский", "х. Черников"}; 
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.address_request);    			
    	setTitle(R.string.button_Make_Request_label);
    	
    	Spinner SpinnerSettlements = (Spinner) findViewById(R.id.spinner_city); 
    	ArrayAdapter<CharSequence> AdapterSettlements = new ArrayAdapter<CharSequence>(this, 
    			android.R.layout.simple_spinner_item); 
    	AdapterSettlements.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); 
    	SpinnerSettlements.setAdapter(AdapterSettlements); 
        
    	int lenSettlements = krd_settelements.length;
    	
    	for (int i = 0; i < lenSettlements; i++) {    		 
    		AdapterSettlements.add(krd_settelements[i]); 
    		}
    	
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
