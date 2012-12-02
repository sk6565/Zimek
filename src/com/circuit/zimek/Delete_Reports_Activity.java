package com.circuit.zimek;

import com.TSS_Report.DatabaseHandler;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.ImageView;



public class Delete_Reports_Activity extends Activity implements OnClickListener{
	
private ImageButton imBtnYesDelete;
private ImageButton imBtnNoDelete;



private ImageView imViewDeleteReportsHead;
private ImageView imViewDeleteIcon;
private ImageView imViewDeleteDesc;

public String language;
public CommonState commonState = null;

	
	


@Override
public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    requestWindowFeature(Window.FEATURE_NO_TITLE);
    getWindow().getDecorView().setSystemUiVisibility(View.STATUS_BAR_HIDDEN);
    getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, 
            WindowManager.LayoutParams.FLAG_FULLSCREEN);
    setContentView(R.layout.delete_reports_app);
    
    
    imViewDeleteIcon = (ImageView)findViewById(R.id.imViewDeleteIcon);
    
    commonState = (CommonState) getApplication();
    commonState.activity_name = "Delete_Reports_Activity";
    language = commonState.language;
    
    if(language.equals("English")){
    	
    	imBtnYesDelete = (ImageButton)findViewById(R.id.imBtnYesDelete);
        
        imBtnNoDelete = (ImageButton)findViewById(R.id.imBtnNoDelete);
    	   
        imViewDeleteReportsHead = (ImageView)findViewById(R.id.imViewDeleteReportsHead);
        
        imViewDeleteDesc = (ImageView)findViewById(R.id.imViewDeleteDesc);
       
        
      
   
 	   
    } 
    
    if(language.equals("Spanish")){
		   
        imBtnYesDelete = (ImageButton)findViewById(R.id.imBtnYesDelete);
        imBtnYesDelete.setImageResource(R.drawable.yes_delete_spa);
        
        imBtnNoDelete = (ImageButton)findViewById(R.id.imBtnNoDelete);
        imBtnNoDelete.setImageResource(R.drawable.no_delete_spa);
        
        imViewDeleteReportsHead = (ImageView)findViewById(R.id.imViewDeleteReportsHead);
        imViewDeleteReportsHead.setImageResource(R.drawable.delete_reports_head_spa);
 	     
        imViewDeleteDesc = (ImageView)findViewById(R.id.imViewDeleteDesc);
        imViewDeleteDesc.setImageResource(R.drawable.are_you_sure_spa);
      
        
       	   
	   }
    
    imBtnYesDelete.setOnClickListener(this);
    imBtnNoDelete.setOnClickListener(this);
    
    
    
}


public void onClick(View v) {
	
	if(v==imBtnYesDelete){
		
		DeleteReports();
		
		Intent intent = new Intent();
		intent.setClass(this,Reports_Deleted_Activity.class);
		startActivity(intent);
		
		
	}
	
	if(v==imBtnNoDelete){
		
		Intent intent = new Intent();
		intent.setClass(this,Reports_Menu_Activity.class);
		startActivity(intent);
		
	}
	
 }


public void DeleteReports(){
	
	String DATABASE_NAME = "TSSReports";
	
	 
	//db.close();
	
	 DatabaseHandler db = new DatabaseHandler(this);
	 deleteDatabase(DATABASE_NAME);
	 db.close();
	
	
}

}
