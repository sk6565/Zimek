package com.circuit.zimek;

import com.TSS_Report.DatabaseHandler;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;



public class Reports_Menu_Activity extends Activity implements OnClickListener{
	
private ImageButton imBtnReturn;
private ImageButton imBtnWarning;
private ImageButton imBtnViewReports;
private ImageButton imBtnDownloadReports;
private ImageButton imBtnDeleteReports;


private ImageView imViewReportsMenu;

public String language;
public CommonState commonState = null;

	
	


@Override
public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    requestWindowFeature(Window.FEATURE_NO_TITLE);
    getWindow().getDecorView().setSystemUiVisibility(View.STATUS_BAR_HIDDEN);
    getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, 
            WindowManager.LayoutParams.FLAG_FULLSCREEN);
    setContentView(R.layout.reports_menu_app);
    
    commonState = (CommonState) getApplication();
    commonState.activity_name = "Reports_Menu_Activity";
    language = commonState.language;
    
    if(language.equals("English")){
    	
    	imBtnReturn = (ImageButton)findViewById(R.id.imBtnReturn);
        
        imBtnWarning = (ImageButton)findViewById(R.id.imBtnWarning);
    	   
        imViewReportsMenu = (ImageView)findViewById(R.id.imViewReportsMenu);
  	     
        imBtnViewReports = (ImageButton)findViewById(R.id.imBtnViewReports);
        
        imBtnDownloadReports = (ImageButton)findViewById(R.id.imBtnDownloadReports);
        
        imBtnDeleteReports = (ImageButton)findViewById(R.id.imBtnDeleteReports);
        
   
 	   
    } 
    
    if(language.equals("Spanish")){
		   
        imBtnReturn = (ImageButton)findViewById(R.id.imBtnReturn);
        imBtnReturn.setImageResource(R.drawable.return_spa);
        
        imBtnWarning = (ImageButton)findViewById(R.id.imBtnWarning);
        imBtnWarning.setImageResource(R.drawable.warning_spa);
        
        imViewReportsMenu = (ImageView)findViewById(R.id.imViewReportsMenu);
        imViewReportsMenu.setImageResource(R.drawable.control_center_mgt_spa);
 	     
        imBtnViewReports = (ImageButton)findViewById(R.id.imBtnViewReports);
        imBtnViewReports.setImageResource(R.drawable.view_reports_spa);
        
        imBtnDownloadReports = (ImageButton)findViewById(R.id.imBtnDownloadReports);
        imBtnDownloadReports.setImageResource(R.drawable.download_reports_spa);
        
        imBtnDeleteReports = (ImageButton)findViewById(R.id.imBtnDeleteReports);
        imBtnDeleteReports.setImageResource(R.drawable.delete_reports_spa);
        
        
 	   
		   
	   }
    
    imBtnWarning.setVisibility(View.INVISIBLE);
    
    imBtnReturn.setOnClickListener(this);
    imBtnWarning.setOnClickListener(this);
    imBtnViewReports.setOnClickListener(this);
    imBtnDownloadReports.setOnClickListener(this);
    imBtnDeleteReports.setOnClickListener(this);
   
    
    
 
    
    
}


public void onClick(View v) {
	
	if (v==imBtnViewReports){
		Intent resultIntent = new Intent();
		resultIntent.setClass(this,View_Reports_Activity.class);
		//String Key = "1";
		//resultIntent.putExtra("Key", Key);
        commonState.Key = "1";
		startActivity(resultIntent);
		
	}
	
	if (v==imBtnDownloadReports){
		
		  DatabaseHandler db = new DatabaseHandler(this);
	       
	        
	       int ReportCount = db.getReportsCount();
	       
	       //TotalCountReports = ReportCount;
		
	       if(ReportCount == 0){
			
	    	   Toast.makeText(this, "THERE ARE NO REPORTS", Toast.LENGTH_LONG).show();
		
	    	   db.close();

		
	       } else {
	    	   
	    	Intent resultIntent = new Intent();
	   		resultIntent.setClass(this,DownloadReports_Activity.class);
	   		startActivity(resultIntent);
	   		
	    	   db.close();
	       }
		
		
		
	}
	
	if (v==imBtnDeleteReports){
		
		
		  DatabaseHandler db = new DatabaseHandler(this);
	       
	        
	       int ReportCount = db.getReportsCount();
	       
	       //TotalCountReports = ReportCount;
		
	       if(ReportCount == 0){
			
	    	   Toast.makeText(this, "THERE ARE NO REPORTS", Toast.LENGTH_LONG).show();
		
	    	   db.close();

		
	       } else {
	    	   
	    	Intent resultIntent = new Intent();
	   		resultIntent.setClass(this,Delete_Reports_Activity.class);
	   		startActivity(resultIntent);
	   		
	    	   db.close();
	       }
		
		
		
	}
	
	if (v==imBtnReturn){
		
		Intent resultIntent = new Intent();
		resultIntent.setClass(this,MGT_ControlCenter_Activity.class);
		startActivity(resultIntent);
		
	}
	
	
	
}





}
