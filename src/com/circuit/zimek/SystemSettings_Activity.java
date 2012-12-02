package com.circuit.zimek;


import com.PassCode_Report.PassCodeDatabaseHandler;
import com.PassCode_Report.PassCodeReports;
import com.SystemSettings_Report.SystemSettingsDatabaseHandler;
import com.SystemSettings_Report.SystemSettingsReports;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;



public class SystemSettings_Activity extends Activity implements OnClickListener{
	
	 private ImageButton imBtnSystemInfo;
	 private ImageButton imBtnPassCodes;
	 private ImageButton imBtnTotalRunTime;
	 private ImageButton imBtnBeeper;
	 private ImageButton imBtnApplicationModules;
	 private ImageButton imBtnTimeDate;
	 private ImageButton imBtnLightingApplication;
	 private ImageButton imBtnMetrics;
	 private ImageButton imBtnWarning;
	 private ImageButton imBtnReturn;
	 
	 private ImageView imViewSystemSettingsHead;
	 public String language;
	 public String Editable;
	 public String Beeper;
	 public String Metrics;
     public CommonState commonState = null;

	 

	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().getDecorView().setSystemUiVisibility(View.STATUS_BAR_HIDDEN);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, 
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.systemsettings_app);
        commonState = (CommonState) getApplication();
        commonState.activity_name = "SystemSettings_Activity";
        
        int id = 1;
        SystemSettingsDatabaseHandler db = new SystemSettingsDatabaseHandler(this);
        
        int Count = db.getReportsCount();
    	
        if(Count == 0){
        	Beeper = "0";
        	Metrics = "0";
        	id = Count;
        	db.addReport(new SystemSettingsReports(id, Beeper, Metrics));
        }
        
        
        SystemSettingsReports Results = db.getReport(id);
        
        Beeper = Results.getBeeper();
        Metrics = Results.getMetrics();
        
        db.close();
        
        language = commonState.language;
        imBtnSystemInfo = (ImageButton)findViewById(R.id.imBtnSystemInfo);
		imBtnPassCodes = (ImageButton)findViewById(R.id.imBtnPassCodes);
		imBtnTotalRunTime = (ImageButton)findViewById(R.id.imBtnTotalRunTime);
		imBtnBeeper = (ImageButton)findViewById(R.id.imBtnBeeper);
		imBtnMetrics = (ImageButton)findViewById(R.id.imBtnMetrics);
		imBtnApplicationModules = (ImageButton)findViewById(R.id.imBtnApplicationModules);
		imBtnTimeDate = (ImageButton)findViewById(R.id.imBtnTimeDate);
		imBtnLightingApplication = (ImageButton)findViewById(R.id.imBtnLightingApplication); 
		imBtnWarning = (ImageButton)findViewById(R.id.imBtnWarning);
		imBtnReturn = (ImageButton)findViewById(R.id.imBtnReturn);
		imViewSystemSettingsHead = (ImageView)findViewById(R.id.imViewSystemSettingsHead);

    	if (language.equals("English")){    		
    		if(Beeper.equals("0")){
    			imBtnBeeper.setImageResource(R.drawable.beeper_disabled_eng);
    		} else {
    			imBtnBeeper.setImageResource(R.drawable.beeper_enabled_eng);
    		}
    		
    		if(Metrics.equals("0")){
    			imBtnMetrics.setImageResource(R.drawable.metrics_liters_and_meters_eng);
    		} else {
    			imBtnMetrics.setImageResource(R.drawable.metrics_gal_and_feet_eng);
    		}   	 
    	} 
    	
    	if (language.equals("Spanish")){
       		imBtnSystemInfo.setImageResource(R.drawable.system_info_spa);
    		imBtnPassCodes.setImageResource(R.drawable.passcodes_spa);
    		imBtnTotalRunTime.setImageResource(R.drawable.total_run_time_spa);	
    		imBtnApplicationModules.setImageResource(R.drawable.application_modules_spa);
    		imBtnTimeDate.setImageResource(R.drawable.date_and_time_spa);
    		imBtnLightingApplication.setImageResource(R.drawable.lighting_application_spa);
    		imBtnWarning.setImageResource(R.drawable.warning_spa);
			imBtnReturn.setImageResource(R.drawable.return_spa);
    		imViewSystemSettingsHead.setImageResource(R.drawable.system_settings_head_spa);
    		 
    		if(Beeper.equals("0")){
     			imBtnBeeper.setImageResource(R.drawable.beeper_disabled_spa);
     		} else {
     			imBtnBeeper.setImageResource(R.drawable.beeper_enabled_spa);
     		}
     		
     		if(Metrics.equals("0")){
     			imBtnMetrics.setImageResource(R.drawable.metrics_liters_and_meters_spa);
     		} else {
     			imBtnMetrics.setImageResource(R.drawable.metrics_gal_and_feet_spa);
     		}
    		 
    	} 
    	
    	imBtnSystemInfo.setOnClickListener(this);
    	imBtnReturn.setOnClickListener(this);
        imBtnWarning.setOnClickListener(this);
        imBtnPassCodes.setOnClickListener(this);
        imBtnTotalRunTime.setOnClickListener(this);
        imBtnBeeper.setOnClickListener(this);
        imBtnApplicationModules.setOnClickListener(this);
        imBtnTimeDate.setOnClickListener(this);
        imBtnLightingApplication.setOnClickListener(this);
        imBtnMetrics.setOnClickListener(this);
        
        imBtnWarning.setVisibility(View.INVISIBLE);
         
        
        
    }
    
    
 public void onClick(View v){
    	
    	if(v==imBtnSystemInfo){
    		
    		Editable = "0";
            commonState.Editable = Editable;

    		Intent intent = new Intent();
        	intent.setClass(this,SystemInformation_Activity.class);
        	startActivity(intent);
            	 
			
			//onDestroy();
    	}
    	
    	if(v==imBtnPassCodes){

    		Intent intent = new Intent();
        	intent.setClass(this,Passcode_Edit_Activity.class);
        	startActivity(intent);
            	 
    		//Toast.makeText(this, "Passcodes Settings will be available soon.", Toast.LENGTH_SHORT).show();
			//onDestroy();
    	}
    	
    	if(v==imBtnTotalRunTime){

    		Intent intent = new Intent();
        	intent.setClass(this,Total_RunTime_Activity.class);
        	startActivity(intent);
    		
    		//Toast.makeText(this, "Total Run Time Settings will be available soon.", Toast.LENGTH_SHORT).show();
            	 
			
			//onDestroy();
    	}
    	
    	if(v==imBtnBeeper){
    		
    		 int id = 1;
    	        //int CountReports;
    	        
    	        SystemSettingsDatabaseHandler db = new SystemSettingsDatabaseHandler(this);
    	        
    	        SystemSettingsReports Results = db.getReport(id);
    	        
    	        Beeper = Results.getBeeper();
    	        
    	        if (Beeper.equals("0")){
    	        	
    	        	Beeper = "1";
    	        	
    	        	if (language.equals("English")){
    	        		
    	        		
    	        		imBtnBeeper = (ImageButton)findViewById(R.id.imBtnBeeper); 
    	    			imBtnBeeper.setImageResource(R.drawable.beeper_enabled_eng);	

    	        	}
    	        	
    	        	if (language.equals("Spanish")){
    	        		
    	        		
    	        		imBtnLightingApplication = (ImageButton)findViewById(R.id.imBtnBeeper); 
    	    			imBtnBeeper.setImageResource(R.drawable.beeper_enabled_spa);	

    	        	}

    	        	
    	        } else {
    	        	
    	        	if (Beeper.equals("1")){
    	        		
    	        		Beeper = "0";
    	        		
    	        		if (language.equals("English")){
        	        		
        	        		
    	        			imBtnBeeper = (ImageButton)findViewById(R.id.imBtnBeeper); 
        	    			imBtnBeeper.setImageResource(R.drawable.beeper_disabled_eng);	

        	        	}
        	        	
        	        	if (language.equals("Spanish")){
        	        		
        	        		
        	        		imBtnBeeper = (ImageButton)findViewById(R.id.imBtnBeeper); 
        	    			imBtnBeeper.setImageResource(R.drawable.beeper_disabled_spa);	

        	        	}
    	        		
    	        	}
    	        		
    	        	
    	        }
    	        
    	        db.updateContents(new SystemSettingsReports(id, Beeper, Metrics));
    	        db.close();
    		
            	
    		//Toast.makeText(this, "Beeper Settings will be available soon.", Toast.LENGTH_SHORT).show();
			
			//onDestroy();
    	}
    	
    	if(v==imBtnApplicationModules){

    		Intent intent = new Intent();
        	intent.setClass(this,ApplicationModules_Activity.class);
        	startActivity(intent);
            	 
			
			//onDestroy();
    	}
    	
    	if(v==imBtnTimeDate){
    		
    		//Intent intent = new Intent();
        	//intent.setClass(this,TimeAndDate_Activity.class);
        	//startActivity(intent);
        	startActivity(new Intent(android.provider.Settings.ACTION_DATE_SETTINGS));
           
    		//Toast.makeText(this, "Time & Date Settings will be available soon.", Toast.LENGTH_SHORT).show();
			
			//onDestroy();
    	}
    	
    	if(v==imBtnLightingApplication){

    		//Intent intent = new Intent();
        	//intent.setClass(this,PassCode_Activity.class);
        	//startActivity(intent);
            	 
    		Toast.makeText(this, "Lighting Application Settings will be available soon.", Toast.LENGTH_SHORT).show();
    		
			//onDestroy();
    	}
    	
    	if(v==imBtnMetrics){
    		
    		
    		int id = 1;
	        //int CountReports;
	        
	        SystemSettingsDatabaseHandler db = new SystemSettingsDatabaseHandler(this);
	        
	        SystemSettingsReports Results = db.getReport(id);
	        
	        Metrics = Results.getMetrics();
	        
	        if (Metrics.equals("0")){
	        	
	        	Metrics = "1";
	        	
	        	if (language.equals("English")){
	        		
	        		
	        		imBtnMetrics = (ImageButton)findViewById(R.id.imBtnMetrics);
	     			imBtnMetrics.setImageResource(R.drawable.metrics_gal_and_feet_eng);	

	        	}
	        	
	        	if (language.equals("Spanish")){
	        		
	        		
	        		imBtnMetrics = (ImageButton)findViewById(R.id.imBtnMetrics);
	     			imBtnMetrics.setImageResource(R.drawable.metrics_gal_and_feet_spa);		

	        	}

	        	
	        } else {
	        	
	        	if (Metrics.equals("1")){
	        		
	        		Metrics = "0";
	        		
	        		if (language.equals("English")){
    	        		
    	        		
	        			imBtnMetrics = (ImageButton)findViewById(R.id.imBtnMetrics);
		     			imBtnMetrics.setImageResource(R.drawable.metrics_liters_and_meters_eng);		

    	        	}
    	        	
    	        	if (language.equals("Spanish")){
    	        		
    	        		
    	        		imBtnBeeper = (ImageButton)findViewById(R.id.imBtnBeeper); 
    	    			imBtnBeeper.setImageResource(R.drawable.metrics_liters_and_meters_spa);	

    	        	}
	        		
	        	}
	        		
	        	
	        }
	        
	        db.updateContents(new SystemSettingsReports(id, Beeper, Metrics));
	        db.close();

    		//Intent intent = new Intent();
        	//intent.setClass(this,PassCode_Activity.class);
        	//startActivity(intent);
           
    		//Toast.makeText(this, "Metrics Settings will be available soon.", Toast.LENGTH_SHORT).show();
			
			//onDestroy();
    	}
    	
    	 if(v==imBtnReturn){
       	  
     		 Intent resultIntent = new Intent();
   			//resultIntent.setClass(this,Other_Fuctions_Activity.class);
     		resultIntent.setClass(this,MGT_ControlCenter_Activity.class);
   			setResult(Activity.RESULT_OK, resultIntent);
   			startActivity(resultIntent);
      	}
    	
    	
    	
    	
    	
    	
    	
    	
 }
    
    
    
    
    
    
    
}
