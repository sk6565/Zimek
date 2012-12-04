package com.circuit.zimek;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;


public class MGT_ControlCenter_Activity extends Activity implements OnClickListener{
	
private ImageButton imBtnReturn;
private ImageButton imBtnWarning;
private ImageButton imBtnReportsMenu;
private ImageButton imBtnSystemSettings;
private ImageButton imBtnWifiEmail;
private ImageButton imBtnMarketSpecificApp;
private ImageButton imBtnCatalogue;
private ImageButton imBtnMaintenanceRepair;

private ImageView imViewMGTControlCenter;

public String language;
public CommonState commonState = null;

	
	


@Override
public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    requestWindowFeature(Window.FEATURE_NO_TITLE);
    getWindow().getDecorView().setSystemUiVisibility(View.STATUS_BAR_HIDDEN);
    getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, 
            WindowManager.LayoutParams.FLAG_FULLSCREEN);
    setContentView(R.layout.mgt_control_center_app);
    
    
    commonState = (CommonState) getApplication();
    commonState.activity_name = "MGT_ControlCenter_Activity";
    language = getIntent().getStringExtra("language");
    
    if(language.equals("English")){
    	
    	imBtnReturn = (ImageButton)findViewById(R.id.imBtnReturn);
        
        imBtnWarning = (ImageButton)findViewById(R.id.imBtnWarning);
    	   
        imViewMGTControlCenter = (ImageView)findViewById(R.id.imViewMGTControlCenter);
  	     
        imBtnReportsMenu = (ImageButton)findViewById(R.id.imBtnReportsMenu);
        
        imBtnSystemSettings = (ImageButton)findViewById(R.id.imBtnSystemSettings);
        
        imBtnWifiEmail = (ImageButton)findViewById(R.id.imBtnWifiEmail);
        
        imBtnMarketSpecificApp = (ImageButton)findViewById(R.id.imBtnMarketSpecificApp);
        
        imBtnCatalogue = (ImageButton)findViewById(R.id.imBtnCatalogue);
        
        imBtnMaintenanceRepair = (ImageButton)findViewById(R.id.imBtnMaintenanceRepair);
        
        
      
	   
 	   
    } 
    
    if(language.equals("Spanish")){
		   
        imBtnReturn = (ImageButton)findViewById(R.id.imBtnReturn);
        imBtnReturn.setImageResource(R.drawable.return_spa);
        
        imBtnWarning = (ImageButton)findViewById(R.id.imBtnWarning);
        imBtnWarning.setImageResource(R.drawable.warning_spa);
        
        imViewMGTControlCenter = (ImageView)findViewById(R.id.imViewMGTControlCenter);
        imViewMGTControlCenter.setImageResource(R.drawable.control_center_mgt_spa);
 	     
        imBtnReportsMenu = (ImageButton)findViewById(R.id.imBtnReportsMenu);
        imBtnReportsMenu.setImageResource(R.drawable.reports_menu_spa);
        
        imBtnSystemSettings = (ImageButton)findViewById(R.id.imBtnSystemSettings);
        imBtnSystemSettings.setImageResource(R.drawable.system_settings_spa);
        
        imBtnWifiEmail = (ImageButton)findViewById(R.id.imBtnWifiEmail);
        imBtnWifiEmail.setImageResource(R.drawable.email_and_wifi_spa);
        
        imBtnMarketSpecificApp = (ImageButton)findViewById(R.id.imBtnMarketSpecificApp);
        imBtnMarketSpecificApp.setImageResource(R.drawable.mgt_market_specific_app_spa);
        
        imBtnCatalogue = (ImageButton)findViewById(R.id.imBtnCatalogue);
        imBtnCatalogue.setImageResource(R.drawable.catalogue_spa);
        
        imBtnMaintenanceRepair = (ImageButton)findViewById(R.id.imBtnMaintenanceRepair);
        imBtnMaintenanceRepair.setImageResource(R.drawable.mgt_maintenance_and_repair_spa);
        
 	   
		   
	   }
    
    imBtnWarning.setVisibility(View.INVISIBLE);
    
    imBtnReturn.setOnClickListener(this);
    imBtnWarning.setOnClickListener(this);
    imBtnReportsMenu.setOnClickListener(this);
    imBtnSystemSettings.setOnClickListener(this);
    imBtnWifiEmail.setOnClickListener(this);
    imBtnMarketSpecificApp.setOnClickListener(this);
    imBtnCatalogue.setOnClickListener(this);
    imBtnMaintenanceRepair.setOnClickListener(this);
    
    
    
}


public void onClick(View v) {
	
	if(v==imBtnReturn){
		
			Intent resultIntent = new Intent();
			resultIntent.setClass(this,ZimekActivity.class);
			resultIntent.putExtra("language", language);
			setResult(Activity.RESULT_OK, resultIntent);
			startActivity(resultIntent);
	}
	
	if(v==imBtnReportsMenu){
		
		Intent resultIntent = new Intent();
		resultIntent.setClass(this,Reports_Menu_Activity.class);
		resultIntent.putExtra("language", language);
		startActivity(resultIntent);
	}
	
	if(v==imBtnSystemSettings){
		
		Intent intent = new Intent();
    	intent.setClass(this,SystemSettings_Activity.class);
    	intent.putExtra("language", language);
    	startActivity(intent);
		
		//Toast.makeText(this, "System Settings will be available soon.", Toast.LENGTH_SHORT).show();
	}
	
	if(v==imBtnWifiEmail){
		
		Toast.makeText(this, "Email & Wi-Fi Connection will be available soon.", Toast.LENGTH_SHORT).show();
	}
	
	/*********** Uncomment later 
	if(v==imBtnMarketSpecificApp){
		
		Intent intent = new Intent();
    	intent.setClass(this,Market_Specific_Activity.class);
    	intent.putExtra("language", language);
    	startActivity(intent);
		
		
		//Toast.makeText(this, "Market Specific Applications will be available soon.", Toast.LENGTH_SHORT).show();
	}
	
	if(v==imBtnCatalogue){
		
		Toast.makeText(this, "Product Catalogue will be available soon.", Toast.LENGTH_SHORT).show();
	}
	
	if(v==imBtnMaintenanceRepair){
		
		Intent intent = new Intent();
    	intent.setClass(this,Maintenance_And_Repair_Activity.class);
    	intent.putExtra("language", language);
    	startActivity(intent);
	}
	*****************/
	
	
	
	
	
}



}
