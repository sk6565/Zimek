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



public class StopPreHeat_Activity extends Activity implements OnClickListener{
	 public CommonState commonState = null;
	 
	 public String language;
	 public long TotalPreHeatTime;
	 public int preheatprogress = 0;
	 private ImageView imViewWarning;
	 private ImageView imViewEndEarly;
	 private ImageButton imBtnYes;
	 private ImageButton imBtnNo;
	 
	
	
	@Override
	  public void onCreate(Bundle savedInstanceState) {
	      super.onCreate(savedInstanceState);
	      requestWindowFeature(Window.FEATURE_NO_TITLE);
	      getWindow().getDecorView().setSystemUiVisibility(View.STATUS_BAR_HIDDEN);
	      getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, 
	              WindowManager.LayoutParams.FLAG_FULLSCREEN);
	      setContentView(R.layout.stoppreheat_app);
	      
	      commonState = (CommonState) getApplication();
          commonState.activity_name = "StopPreHeat_Activity";
	  
	      imViewWarning = (ImageView)findViewById(R.id.imViewWarning);
	      
          try {
	        preheatprogress = getIntent().getIntExtra("preheatprogress", preheatprogress);
	      
	        TotalPreHeatTime = getIntent().getLongExtra("TotalPreHeatTime", TotalPreHeatTime);
            commonState.preheatprogress = preheatprogress;
            commonState.TotalPreHeatTime = TotalPreHeatTime;
          } catch (Exception e) {
            preheatprogress = commonState.preheatprogress;
            TotalPreHeatTime = commonState.TotalPreHeatTime;
          }
	      
	      language = commonState.language;
	      
	      if(language.equals("English")){
	          
	    	  imBtnYes = (ImageButton)findViewById(R.id.imBtnYes);
	    	  imBtnYes.setImageResource(R.drawable.return_to_menu_eng);
	          
	    	  imBtnNo = (ImageButton)findViewById(R.id.imBtnNo);
          
  
	          imViewEndEarly = (ImageView)findViewById(R.id.imViewEndEarly);
	               
	   	   
	      } 
	      
	      if(language.equals("Spanish")){
			   
	    	  imBtnYes = (ImageButton)findViewById(R.id.imBtnYes);
	    	  //imBtnYes.setImageResource(R.drawable.return_to_other_options_spa);
	    	  imBtnYes.setImageResource(R.drawable.return_to_menu_spa);
	    	  
	    	  imBtnNo = (ImageButton)findViewById(R.id.imBtnNo);
	    	  imBtnNo.setImageResource(R.drawable.no_spa);
	                  
	          imViewEndEarly = (ImageView)findViewById(R.id.imViewEndEarly);
	          imViewEndEarly.setImageResource(R.drawable.end_preheat_liquid_early_spa);
	   	   
			   
		   }
	      
	      imBtnYes.setOnClickListener(this);
	      imBtnNo.setOnClickListener(this);
	      
	      
	      
	      
	      
	}
	
	
	 public void onClick(View v) {
		 
		 if(v==imBtnYes){
			    commonState.mService.sendZvacCommand("M0,H0,S0,C0");
				Intent resultIntent = new Intent();
				resultIntent.setClass(this,Other_Fuctions_Activity.class);
				setResult(Activity.RESULT_OK, resultIntent);
				startActivity(resultIntent);
			 
		 }
		 
		 if(v==imBtnNo){
			 
			 Intent resultIntent = new Intent();
				resultIntent.setClass(this,ZimekActivity.class);
				resultIntent.putExtra("preheatprogress", preheatprogress);
				resultIntent.putExtra("TotalPreHeatTime", TotalPreHeatTime);
				
				setResult(Activity.RESULT_OK, resultIntent); 
			finish();
		 }
		 
		 
		 
	 }
	
	
}
