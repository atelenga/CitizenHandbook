package com.app.handbook;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ArrayAdapter; 
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.Spinner;
import android.widget.EditText;

public class MakeRequestActivity extends Activity 
implements AdapterView.OnItemSelectedListener {
	public String[] krd_settelements = {"", "Краснодар", "п. Белозерный", "п. Березовый", 
    		"п. Дорожный", "п. Дружелюбный", "п. Зеленопольский", "п. Знаменский",
    		"п. Индустриальный", "п. Колосистый", "п. Краснодарский", "п. Краснолит",
    		"п. Лазурный", "п. Лорис", "п. отделения N 2 СКЗНИИСиВ", "п. отделения N 3 ОПХ КНИИСХ" ,
    		"п. отделения N 3 СКЗНИИСиВ", "п. отделения N 4 совхоза «Пашковский»", "п. Пашковский", "п. Плодородный",
    		"п. Победитель", "п. подсобного производственного хозяйства биофабрики" ,"п. Разъезд" ,"п. Российский" ,
    		"ст. Елизаветинская", "ст. Старокорсунская", "х. Восточный", "х. Копанской",
    		"х. Ленина", "х. Новый", "х. Октябрьский", "х. Черников"};
    String MY_PREFS = "Handbook";
    String city;
    int mode = Activity.MODE_PRIVATE;
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.address_request);    			
    	setTitle(R.string.button_Make_Request_label);
    	
    	Spinner spinnerSettlements = (Spinner) findViewById(R.id.spinner_city); 
    	ArrayAdapter<CharSequence> AdapterSettlements = new ArrayAdapter<CharSequence>(this, 
    			android.R.layout.simple_spinner_item); 
    	AdapterSettlements.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); 
    	spinnerSettlements.setAdapter(AdapterSettlements); 
        
    	int lenSettlements = krd_settelements.length;
    	
    	for (int i = 0; i < lenSettlements; i++) {    		 
    		AdapterSettlements.add(krd_settelements[i]); 
    		}   	
    	
    	EditText streetEdit = (EditText) findViewById(R.id.editText_street);		
		EditText numberEdit = (EditText) findViewById(R.id.editText_number);		
		final EditText buildingEdit = (EditText) findViewById(R.id.editText_building);
		
		SharedPreferences settings = getSharedPreferences(MY_PREFS, mode);
    	city = settings.getString("City","");
		String street = settings.getString("Street","");
    	String number = settings.getString("Number","");
    	String building = settings.getString("Building","");
    	boolean buildingChecked = settings.getBoolean("IsBuilding", false);
		
		CheckBox chkBx = (CheckBox) findViewById(R.id.checkBoxBuilding);
		chkBx.setChecked(buildingChecked);
		if (buildingChecked){
			buildingEdit.setEnabled(true);
		}
		else{
			buildingEdit.setEnabled(false);
		}
		
		chkBx.setOnCheckedChangeListener(new OnCheckedChangeListener()
		{
		    public void onCheckedChanged(CompoundButton arg0, boolean arg1) {
		    	 if ( arg1 ){
		    		buildingEdit.setEnabled(true);		    		
 		        }
		    	 else{
		    		 buildingEdit.setEnabled(false);
		    	 }				
			}
		});
		    	
    	int spinnerPos = AdapterSettlements.getPosition(city);
    	
    	spinnerSettlements.setSelection(spinnerPos);
    	streetEdit.setText(street);
    	numberEdit.setText(number);
    	buildingEdit.setText(building);
    	
    	set_buttonSendRequestClickListener();
    	set_buttonClearClickListener();
    	    	
        Log.d("CitizenHandbook", "Make Request view started");
        
    }

	private void set_buttonClearClickListener() {
		Button ClearButton = (Button)findViewById(R.id.button_Clear);
		ClearButton.setOnClickListener(new View.OnClickListener() {
	    	public void onClick(View v) {
	    		// Clear all fields	    		
                Log.d("CitizenHandbook", "Address fields are cleared");
	    		EditText street = (EditText) findViewById(R.id.editText_street);
	    		street.setText("");
	    		EditText number = (EditText) findViewById(R.id.editText_number);
	    		number.setText("");
	    		EditText building = (EditText) findViewById(R.id.editText_building);
	    		building.setText("");
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
	    		
	    		
	    		EditText streetEdit = (EditText) findViewById(R.id.editText_street);
	    		EditText numberEdit = (EditText) findViewById(R.id.editText_number);
	    		EditText buildingEdit = (EditText) findViewById(R.id.editText_building);
	    		CheckBox chkBx = (CheckBox) findViewById(R.id.checkBoxBuilding);
	    		
	    		Spinner spinnerSettlements = (Spinner) findViewById(R.id.spinner_city); 
	    		city = spinnerSettlements.getSelectedItem().toString();
	    		String street = streetEdit.getText().toString().trim();
	    	    String number = numberEdit.getText().toString().trim();
	    	    String building = buildingEdit.getText().toString().trim();
	    	    boolean buildingChecked = chkBx.isChecked();
	    	    
	    	    SharedPreferences settings = getSharedPreferences(MY_PREFS, mode);
	    	    SharedPreferences.Editor editor = settings.edit();
	    	    editor.putString("City", city);
	    	    editor.putString("Street", street);
	    	    editor.putString("Number", number);
	    	    editor.putString("Building", building);
	    	    editor.putBoolean("IsBuilding", buildingChecked);
	    	    editor.commit();  		
	    		
                Log.d("CitizenHandbook", "Address fields are saved into shared preferenses");
	    	}
	    });   
	
	}

	public void onItemSelected(AdapterView<?> parent,
            View v, int position, long id) {
		// TODO Auto-generated method stub
		city = krd_settelements[position];
	}

	public void onNothingSelected(AdapterView<?> parent) {
		// TODO Auto-generated method stub
		city = krd_settelements[0];
	}
		
		
}
