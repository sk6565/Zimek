package com.circuit.zimek;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;



public class Other_Fuctions_Activity extends Activity implements OnClickListener{
	 public CommonState commonState = null; 
	 private ImageButton imBtnMgt;
	 private ImageButton imBtnReturn;
	 private ImageButton imBtnWarning;
	 private ImageButton imBtnMistRemaining;
	 private ImageButton imBtnViewReports;
	 private ImageButton imBtnPreHeat;
	 private ImageButton imBtnSprayApp;
	 private ImageView imViewOtherFunctions;
	 private TextView txtViewSpray;
	 private String Spray;
	 public String language;
	 public String Editable;
	 

	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().getDecorView().setSystemUiVisibility(View.STATUS_BAR_HIDDEN);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, 
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.otherfunctions_app);
        
        commonState = (CommonState) getApplication();
        commonState.activity_name="Other_Fuctions_Activity";
        txtViewSpray = (TextView)findViewById(R.id.txtViewSpray);
        txtViewSpray.setText("ON");

    	language = commonState.language;
        
    	
    	if (language.equals("English")){
    		
    		 
    		imBtnMgt = (ImageButton)findViewById(R.id.imBtnMgt);
    		imBtnReturn = (ImageButton)findViewById(R.id.imBtnReturn);
    		imBtnWarning = (ImageButton)findViewById(R.id.imBtnWarning);
    		imBtnMistRemaining = (ImageButton)findViewById(R.id.imBtnMistRemaining);
    		imBtnViewReports = (ImageButton)findViewById(R.id.imBtnViewReports);
    		imBtnPreHeat = (ImageButton)findViewById(R.id.imBtnPreHeat);
    		imBtnSprayApp = (ImageButton)findViewById(R.id.imBtnSprayApp);
    		imViewOtherFunctions = (ImageView)findViewById(R.id.imViewOtherFunctions);

		   	  
    	} 
    	
    	if (language.equals("Spanish")){
    			
    		   imBtnMgt = (ImageButton)findViewById(R.id.imBtnMgt);
  		   	   imBtnMgt.setImageResource(R.drawable.mgt_control_center_spa);
  		   	   
  		   	   imBtnReturn = (ImageButton)findViewById(R.id.imBtnReturn);
			   imBtnReturn.setImageResource(R.drawable.return_spa);
			   
			   imBtnWarning = (ImageButton)findViewById(R.id.imBtnWarning);
			   imBtnWarning.setImageResource(R.drawable.warning_spa);
			   
			   imBtnMistRemaining = (ImageButton)findViewById(R.id.imBtnMistRemaining);
			   imBtnMistRemaining.setImageResource(R.drawable.micro_mist_time_remaining_spa);
			   
			   imBtnViewReports = (ImageButton)findViewById(R.id.imBtnViewReports);
			   imBtnViewReports.setImageResource(R.drawable.view_reports_spa);
			   
			   imBtnPreHeat = (ImageButton)findViewById(R.id.imBtnPreHeat);
			   imBtnPreHeat.setImageResource(R.drawable.preheat_liquid_spa);
			   
			   imBtnSprayApp = (ImageButton)findViewById(R.id.imBtnSprayApp);
			   imBtnSprayApp.setImageResource(R.drawable.spray_application_spa);
			   
			   imViewOtherFunctions = (ImageView)findViewById(R.id.imViewOtherFunctions);
			   imViewOtherFunctions.setImageResource(R.drawable.menu_spa);
    	} 
    	
    	imBtnMgt.setOnClickListener(this);
    	imBtnReturn.setOnClickListener(this);
        imBtnWarning.setOnClickListener(this);
        imBtnMistRemaining.setOnClickListener(this);
        imBtnViewReports.setOnClickListener(this);
        imBtnPreHeat.setOnClickListener(this);
        imBtnSprayApp.setOnClickListener(this);
        imBtnWarning.setVisibility(View.INVISIBLE);
    }
    
    public void onClick(View v){
    	
    	if(v==imBtnMgt){
    		
    		Editable = "0";
    		Intent intent = new Intent();
        	intent.setClass(this,PassCode_Activity.class);
        	intent.putExtra("Editable", Editable);
        	startActivity(intent);
            	 
			
			//onDestroy();
    	}
    	
    	if(v==imBtnMistRemaining){

    		Intent intent = new Intent();
			intent.setClass(this,Liquid_Conversion_Activity.class);
			startActivity(intent);
			

    	}
    	
    	
    	
    	if(v==imBtnViewReports){
      		
      		
      		
    		Intent intent = new Intent();
    		intent.setClass(this,View_Reports_Activity.class);
      		// String Key = "0";
			// intent.putExtra("Key", Key);
            commonState.Key = "0";
			startActivity(intent);
			
			//onDestroy();
    		
    	}
    	
     	if(v==imBtnPreHeat){
     		 
      		
      		//Toast.makeText(this, "SOON TO BE AVAILABLE", Toast.LENGTH_LONG).show();
      		
      		Intent intent = new Intent();
    		intent.setClass(this,PreHeatLiquid_Activity.class);
    		startActivity(intent);
    		
    	}
     	
     	if(v==imBtnSprayApp){
      		
    		//Toast.makeText(this, "SOON TO BE AVAILABLE", Toast.LENGTH_LONG).show();
    		
    	    
    	   Spray = txtViewSpray.getText().toString();
    	   
       	if (Spray.equals("ON")){
    	    	
    	    	Animation animation = new AlphaAnimation(1, 0); 
    		    animation.setDuration(800); 
    		    animation.setInterpolator(new LinearInterpolator()); 
    		    animation.setRepeatCount(Animation.INFINITE); 
    		    animation.setRepeatMode(Animation.REVERSE); 
    		    imBtnSprayApp.startAnimation(animation);
    		    
                commonState.mService.sendZvacCommand("X1");
    		    Spray = "OFF";
    		    
    		    txtViewSpray.setText("OFF");
    	    	
    	    	
    	    } else{
    	    	
    	    	if(Spray.equals("OFF")){
    	    	imBtnSprayApp.clearAnimation();
    	    	commonState.mService.sendZvacCommand("X0");
    	    	Spray = "ON";
    	    	
    	    	txtViewSpray.setText("ON");
    	    		
    	    		
    	    	}
    	    }
    	    
        
    	
    	}
     	
     	
     	  if(v==imBtnReturn){
        	  
     		 Intent resultIntent = new Intent();
   			resultIntent.setClass(this,ZimekActivity.class);
   			setResult(Activity.RESULT_OK, resultIntent);
   			startActivity(resultIntent);
      	}
    	
    	
    	
    	
    }
    
}
