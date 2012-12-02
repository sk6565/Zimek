package com.circuit.zimek;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.ImageView;



public class Reports_Deleted_Activity extends Activity implements OnClickListener{
	
private ImageButton imBtnReturnReportsMenu;

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
    setContentView(R.layout.reportsdeleted_app);
    commonState = (CommonState) getApplication();
    commonState.activity_name = "Reports_Deleted_Activity";
    
    
   imViewDeleteIcon = (ImageView)findViewById(R.id.imViewDeleteIcon);
    
   language = commonState.language;
    
    if(language.equals("English")){
    	
    	imBtnReturnReportsMenu = (ImageButton)findViewById(R.id.imBtnReturnReportsMenu);
    	   
        imViewDeleteReportsHead = (ImageView)findViewById(R.id.imViewDeleteReportsHead);
        
        imViewDeleteDesc = (ImageView)findViewById(R.id.imViewDeleteDesc);
  	     
      
   
 	   
    } 
    
    if(language.equals("Spanish")){
		   
    	imBtnReturnReportsMenu = (ImageButton)findViewById(R.id.imBtnReturnReportsMenu);
    	imBtnReturnReportsMenu.setImageResource(R.drawable.return_to_reports_menu_spa);
        
        imViewDeleteReportsHead = (ImageView)findViewById(R.id.imViewDeleteReportsHead);
        imViewDeleteReportsHead.setImageResource(R.drawable.delete_reports_head_spa);
 	     
        imViewDeleteDesc = (ImageView)findViewById(R.id.imViewDeleteDesc);
        imViewDeleteDesc.setImageResource(R.drawable.reports_deleted_desc_spa);
       	   
	   }

    
    imBtnReturnReportsMenu.setOnClickListener(this);

    
    
}


public void onClick(View v) {
	
	if(v==imBtnReturnReportsMenu){
		
		Intent intent = new Intent();
		intent.setClass(this,Reports_Menu_Activity.class);
		startActivity(intent);
		
	}
	
}

}
