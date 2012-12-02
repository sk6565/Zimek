package com.circuit.zimek;


import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.SystemInfo_Report.SystemInfoHandler;
import com.SystemInfo_Report.SystemInfoReports;


public class ZimekActivity extends Activity implements OnClickListener{
	 public CommonState commonState = null;
	 
	 private ImageButton imBtnReturn;
	 private ImageButton imBtnWarning;
	 private ImageButton imBtnOtherFunctions;
	 private ImageButton imBtnRepeatApp;
	 private ImageButton imBtnNewApp;
	 
	 private ImageView imViewLiquidTank;
 
	 private ImageView imViewControlCenter;
	 
	 public String language;
	 
	 String[] values;

	 private ProgressBar liquidProgress;
	 public String ViewReports = "0";
  
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().getDecorView().setSystemUiVisibility(View.STATUS_BAR_HIDDEN);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, 
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.ops_control_center);
	 	
        commonState = (CommonState) getApplication();
        commonState.activity_name = "ZimekActivity";
        commonState.readZvacSerial();
        commonState.mService.sendZvacCommand("W1,R0,E"+commonState.zvacSerial);
        mHandler.post(mUpdateUI);
        
        liquidProgress = (ProgressBar) findViewById(R.id.vertical_progressbar);
        liquidProgress.setProgress(commonState.getLiquidLevel());
        
    	imViewControlCenter = (ImageView)findViewById(R.id.imViewControlCenter);
    	
    	
    	
        imBtnReturn = (ImageButton)findViewById(R.id.imBtnReturn);
        imBtnReturn.setOnClickListener(this);
        
        imBtnWarning = (ImageButton)findViewById(R.id.imBtnWarning);
        imBtnWarning.setOnClickListener(this);
        
        imBtnOtherFunctions = (ImageButton)findViewById(R.id.imBtnOtherFunctions);
        imBtnOtherFunctions.setOnClickListener(this);
        
        imBtnRepeatApp = (ImageButton)findViewById(R.id.imBtnRepeatApp);
        imBtnRepeatApp.setOnClickListener(this);
        
        imBtnNewApp = (ImageButton)findViewById(R.id.imBtnNewApp);
        imBtnNewApp.setOnClickListener(this);
 
        imViewLiquidTank = (ImageView)findViewById(R.id.imViewLiquidTank);
        
        imBtnWarning.setVisibility(View.INVISIBLE);

    	language = commonState.language;
    	if (language.equals("")) {
    		language = "English";
    		commonState.language = "English";
    	}

	
    	if (language.equals("Spanish")){
  		   	   imViewLiquidTank.setImageResource(R.drawable.liquid_tank_spa);
	  		   imViewControlCenter.setImageResource(R.drawable.control_center_text_spa);
			   imBtnReturn.setImageResource(R.drawable.return_spa);
			   imBtnWarning.setImageResource(R.drawable.warning_spa);
			   imBtnOtherFunctions.setImageResource(R.drawable.other_options_spa);
			   imBtnRepeatApp.setImageResource(R.drawable.repeat_application_spa);
			   imBtnNewApp.setImageResource(R.drawable.new_application_spa);
    	} 
    		
    }
    
    
          
    
    public void onClick(View v){
    	
     	if(v==imBtnOtherFunctions){

    		Intent intent = new Intent();
			intent.setClass(this,Other_Fuctions_Activity.class);
			startActivity(intent);
			
			//onDestroy();
    		
    	}
    	
  	
    	if(v==imBtnNewApp){

    		Intent intent = new Intent();
			intent.setClass(this,New_Application_Activity.class);
			startActivity(intent);
			
			//onDestroy();
    		
    	}
    	
     	if(v==imBtnRepeatApp){

    		Intent intent = new Intent();
			intent.setClass(this,Repeat_Last_Activity.class);
			startActivity(intent);
			
			//onDestroy();
    		
    	}
     	
      	
 	if(v==imBtnReturn){
 		
 		//language = txtViewLanguage.getText().toString();
 		
 		//if(language.equals("Espa�ol")){
  		//	language = "Spanish";
  			// Toast.makeText(ZimekActivity.this, "Result OK: Zimekactivity" + language, Toast.LENGTH_SHORT).show();
  		//}
 		
 		  SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
 		  SharedPreferences.Editor editor=preferences.edit();
 		  editor.putString("language",language);
 		  editor.commit();
 		

 		  	Intent resultIntent = new Intent();
			resultIntent.setClass(this,Home_Screen_Activity.class);
			setResult(Activity.RESULT_OK, resultIntent);
			startActivity(resultIntent);


    	}
 	
    }
 	

    
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
      super.onActivityResult(requestCode, resultCode, data);
     switch (requestCode){
     case 1:
          if (resultCode == RESULT_OK) {
         	 //language = commonState.language;
         	 language = data.getStringExtra("language");
         	 if (language.equals("English") || language.equals("Spanish")) {
         		 commonState.language = language;
         	 } else {
         		 language = commonState.language;
         	 }


         	 
         	  if(language.equals("English")){
 	    	  
  	    	 
  	    	 imViewLiquidTank.setImageResource(R.drawable.liquid_tank_eng);
  	    	  imViewControlCenter.setImageResource(R.drawable.control_center_text_eng);
	      	   imBtnReturn.setImageResource(R.drawable.return_eng);
			   imBtnWarning.setImageResource(R.drawable.warning_eng);
			   imBtnOtherFunctions.setImageResource(R.drawable.other_options_eng);
			   imBtnRepeatApp.setImageResource(R.drawable.repeat_application_eng);
			   imBtnNewApp.setImageResource(R.drawable.new_application_eng);
			   
         	  } else {     // i.e. (language.equals("Spanish"))
  		    	   
  		    	   
  		    	   imViewLiquidTank.setImageResource(R.drawable.liquid_tank_spa);
  		    	   imViewControlCenter.setImageResource(R.drawable.control_center_text_spa);
  	  			   imBtnReturn.setImageResource(R.drawable.return_spa);
  	  			   imBtnWarning.setImageResource(R.drawable.warning_spa);
  	  			   imBtnOtherFunctions.setImageResource(R.drawable.other_options_spa);
  	  			   imBtnRepeatApp.setImageResource(R.drawable.repeat_application_spa);
  	  			   imBtnNewApp.setImageResource(R.drawable.new_application_spa);
  		    	   
  		       }  
        break;
      }
     }
     }
    
    private boolean shouldContinue = true;
	private final Handler mHandler = new Handler();
	private final Runnable mUpdateUI = new Runnable() {
		public void run() {
			if (shouldContinue){
				liquidProgress.setProgress(commonState.getLiquidLevel());
				if ((int)commonState.zvacStatus[12]==0) { //no umblical chord
					if ((int)commonState.zvacStatus[14]==0) {
						// warning
						imBtnWarning.setVisibility(View.VISIBLE);
						BlinkWarningButton();
					} else {
						// Umblical chord is not connected
						switch ((int)commonState.zvacStatus[14]) {
						case 2:
						case 3: //011 fan status off, remote mode, link good
							// warning if umblical chord is not connected
							imBtnWarning.setVisibility(View.VISIBLE);
							BlinkWarningButton();
							break;
						default:
							imBtnWarning.clearAnimation();
							imBtnWarning.setVisibility(View.INVISIBLE);
							break;
						}
					}
				} else {
					imBtnWarning.clearAnimation();
					imBtnWarning.setVisibility(View.INVISIBLE);
				}
				mHandler.postDelayed(mUpdateUI, 2000); // 2 second
			}
		}
	};
	
	public void BlinkWarningButton(){
		Animation animation = new AlphaAnimation(1, 0); 
		animation.setDuration(800); 
		animation.setInterpolator(new LinearInterpolator()); 
		animation.setRepeatCount(Animation.INFINITE); 
		animation.setRepeatMode(Animation.REVERSE); 
		imBtnWarning.startAnimation(animation);
	}
}


