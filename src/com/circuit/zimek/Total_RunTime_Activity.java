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



public class Total_RunTime_Activity extends Activity implements OnClickListener{
	
	 private ImageButton imBtnResetSystemActive;
	 private ImageButton imBtnResetMicroMistActive;
	 private ImageButton imBtnWarning;
	 private ImageButton imBtnReturn;
	 
	 
	 private ImageView imViewTotalRunTimeHead;
	 private ImageView imViewSystemActive;
	 private ImageView imViewMicroMistActive;
	 
	 
	 private TextView txtViewSystemTotalTimeNone;
	 private TextView txtViewSystemTotalTimeAdjustable;
	 private TextView txtViewMistTotalTimeNone;
	 private TextView txtViewMistTotalTimeAdjustable;
	 private TextView txtViewTimeDateSystem;
	 private TextView txtViewTimeDateMist;
     public CommonState commonState = null;

	 
	 public String myTime;
	 public String language;
	// public String Editable;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().getDecorView().setSystemUiVisibility(View.STATUS_BAR_HIDDEN);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, 
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.total_runtime_app);
        commonState = (CommonState) getApplication();
        commonState.activity_name = "Total_RunTime_Activity";
        
        txtViewSystemTotalTimeNone = (TextView)findViewById(R.id.txtViewSystemTotalTimeNone);
        txtViewSystemTotalTimeAdjustable = (TextView)findViewById(R.id.txtViewSystemTotalTimeAdjustable);
        txtViewMistTotalTimeNone = (TextView)findViewById(R.id.txtViewMistTotalTimeNone);
        txtViewMistTotalTimeAdjustable = (TextView)findViewById(R.id.txtViewMistTotalTimeAdjustable);
        txtViewTimeDateSystem = (TextView)findViewById(R.id.txtViewTimeDateSystem);
        txtViewTimeDateMist = (TextView)findViewById(R.id.txtViewTimeDateMist);
        
        
        language = commonState.language;

    	
    	if (language.equals("English")){
    		
    		imBtnWarning = (ImageButton)findViewById(R.id.imBtnWarning);
    		imBtnReturn = (ImageButton)findViewById(R.id.imBtnReturn);
    		
    		imBtnResetSystemActive = (ImageButton)findViewById(R.id.imBtnResetSystemActive);
    		imBtnResetMicroMistActive = (ImageButton)findViewById(R.id.imBtnResetMicroMistActive);
    		
    		
    		imViewTotalRunTimeHead = (ImageView)findViewById(R.id.imViewTotalRunTimeHead);
    		imViewSystemActive = (ImageView)findViewById(R.id.imViewSystemActive);
    		imViewMicroMistActive = (ImageView)findViewById(R.id.imViewMicroMistActive);
    		
    		
    		

		   	  
    	} 
    	
    	if (language.equals("Spanish")){
    		
    		imBtnWarning = (ImageButton)findViewById(R.id.imBtnWarning);
   		 imBtnWarning.setImageResource(R.drawable.warning_spa);
   		 
   		 imBtnReturn = (ImageButton)findViewById(R.id.imBtnReturn);
			 imBtnReturn.setImageResource(R.drawable.return_spa);
			 
			   
			 imBtnResetSystemActive = (ImageButton)findViewById(R.id.imBtnResetSystemActive);
			 imBtnResetSystemActive.setImageResource(R.drawable.reset_system_spa);
				   
			 imBtnResetMicroMistActive = (ImageButton)findViewById(R.id.imBtnResetMicroMistActive);
			 imBtnResetMicroMistActive.setImageResource(R.drawable.reset_micromist_spa);
			   
			 imViewTotalRunTimeHead = (ImageView)findViewById(R.id.imViewTotalRunTimeHead);
			 imViewTotalRunTimeHead.setImageResource(R.drawable.total_run_time_head_spa);
   		 
    			
			 imViewSystemActive = (ImageView)findViewById(R.id.imViewSystemActive);
			 imViewSystemActive.setImageResource(R.drawable.system_active_spa);
  		   	   
			 imViewMicroMistActive = (ImageView)findViewById(R.id.imViewMicroMistActive);
			 imViewMicroMistActive.setImageResource(R.drawable.micromist_active_spa);
			
			   
    	
    		 
    		 
    	} 
    	
    	imBtnResetSystemActive.setOnClickListener(this);
    	imBtnReturn.setOnClickListener(this);
        imBtnWarning.setOnClickListener(this);
        imBtnResetMicroMistActive.setOnClickListener(this);        
        imBtnWarning.setVisibility(View.INVISIBLE);
         
        
        
    }
    
    public void GetTime(){
    	
    	String mydate = java.text.DateFormat.getDateTimeInstance().format(Calendar.getInstance().getTime());
		 // Toast.makeText(this, "TIME:" + mydate, Toast.LENGTH_SHORT).show();
		
		 StringTokenizer tokens = new StringTokenizer(mydate, " ");
	     String Month = tokens.nextToken();
	     String Day = tokens.nextToken();
	     String Year = tokens.nextToken();
	     String Time = tokens.nextToken();
	     String amorpm = tokens.nextToken();
	     
	     StringTokenizer Tokens = new StringTokenizer(Time, ":");
	     String Hour = Tokens.nextToken();
	     String Minutes = Tokens.nextToken(); 
	     
	      myTime = String.format("%s:%s %s", Hour, Minutes, amorpm);
    }
    
    
    public void onClick(View v){
    	
    	if(v== imBtnReturn){
    		
    		Intent intent = new Intent();
        	intent.setClass(this,SystemSettings_Activity.class);
        	startActivity(intent);
    	}
    	
    	if(v == imBtnResetSystemActive){
    		
    		txtViewSystemTotalTimeAdjustable.setText("00:00");
    		
    		 Date date = new Date(System.currentTimeMillis());
		     SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yy");
		     String newDate = formatter.format(date);
		     
		     GetTime();
		     
		     txtViewTimeDateSystem.setText(newDate + ", " + myTime);
    		
    		
    		
    	}
    	
	if(v == imBtnResetMicroMistActive){
    		
		txtViewMistTotalTimeAdjustable.setText("00:00");
		
		 Date date = new Date(System.currentTimeMillis());
	     SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yy");
	     String newDate = formatter.format(date);
	     
	     GetTime();
	     
	     txtViewTimeDateMist.setText(newDate + ", " + myTime);
    		
    		  		
    	}
    	
    	
    	
    }
}
