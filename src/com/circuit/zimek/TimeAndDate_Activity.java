package com.circuit.zimek;


import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.StringTokenizer;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;



public class TimeAndDate_Activity extends Activity implements OnClickListener{
	
	 private ImageButton imBtnChangeTime;
	 private ImageButton imBtnChangeDate;
	 private ImageButton imBtnSave;
	 private ImageButton imBtnWarning;
	 private ImageButton imBtnReturn;
	 
	 
	 private ImageView imViewDateTimeHead;
	 private ImageView imViewDate;
	 
	 
	 private TextView txtViewDate;
	 private TextView txtViewTime;
	 
	 
	 
	 public String myTime;
	 public String language;
     public CommonState commonState = null;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().getDecorView().setSystemUiVisibility(View.STATUS_BAR_HIDDEN);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, 
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.timeanddate_app);
        
        txtViewDate = (TextView)findViewById(R.id.txtViewDate);
        txtViewTime = (TextView)findViewById(R.id.txtViewTime);

        
        
        commonState = (CommonState) getApplication();
        commonState.activity_name = "TimeAndDate_Activity";
        language = commonState.language;

    	
    	if (language.equals("English")){
    		
    		imBtnWarning = (ImageButton)findViewById(R.id.imBtnWarning);
    		imBtnReturn = (ImageButton)findViewById(R.id.imBtnReturn);
    		
    		imBtnChangeTime = (ImageButton)findViewById(R.id.imBtnChangeTime);
    		imBtnChangeDate = (ImageButton)findViewById(R.id.imBtnChangeDate);
    		
    		
    		imBtnSave = (ImageButton)findViewById(R.id.imBtnSave);
    		
    		
    		imViewDateTimeHead = (ImageView)findViewById(R.id.imViewDateTimeHead);
    		imViewDate = (ImageView)findViewById(R.id.imViewDate);
    	  
    	} 
    	
    	if (language.equals("Spanish")){
    		
    		imBtnWarning = (ImageButton)findViewById(R.id.imBtnWarning);
   		 	imBtnWarning.setImageResource(R.drawable.warning_spa);
   		 
   		 	 imBtnReturn = (ImageButton)findViewById(R.id.imBtnReturn);
			 imBtnReturn.setImageResource(R.drawable.return_spa);
			 
			   
			 imBtnChangeTime = (ImageButton)findViewById(R.id.imBtnChangeTime);
			 imBtnChangeTime.setImageResource(R.drawable.change_time_spa);
				   
			 imBtnChangeDate = (ImageButton)findViewById(R.id.imBtnChangeDate);
			 imBtnChangeDate.setImageResource(R.drawable.change_date_spa);
			   
			 imBtnSave = (ImageButton)findViewById(R.id.imBtnSave);
			 imBtnSave.setImageResource(R.drawable.save_spa);
   		 
    			
			 imViewDateTimeHead = (ImageView)findViewById(R.id.imViewDateTimeHead);
			 imViewDateTimeHead.setImageResource(R.drawable.date_and_time_head_spa);
  		   	   
			 imViewDate = (ImageView)findViewById(R.id.imViewDate);
			 imViewDate.setImageResource(R.drawable.mm_dd_yyyy_spa);
			
			   
    	
    		 
    		 
    	} 
    	
    	imBtnChangeTime.setOnClickListener(this);
    	imBtnReturn.setOnClickListener(this);
        imBtnWarning.setOnClickListener(this);
        imBtnChangeDate.setOnClickListener(this);
        imBtnSave.setOnClickListener(this);  
        imBtnWarning.setVisibility(View.INVISIBLE);
        imBtnSave.setVisibility(View.INVISIBLE);
         
        
        
    }
    
    public void onClick(View v){
    	
    	if(v== imBtnReturn){
    		
    		Intent intent = new Intent();
        	intent.setClass(this,SystemSettings_Activity.class);
        	startActivity(intent);
    	}
    	
    	
    }
    
    
    
}
