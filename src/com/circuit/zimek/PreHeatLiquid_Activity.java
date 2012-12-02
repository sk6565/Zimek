package com.circuit.zimek;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;




public class PreHeatLiquid_Activity extends Activity implements OnClickListener{
	
	 
	 public String language;
	 
	 private ImageButton imBtnWarning;
	 private ImageButton imBtnReturn;
	 private ImageButton imBtnActivate;
	 
	 private ImageView imViewPreHeatLiquidHead;
	 private ImageView imViewPreHeatIcon;
	 private ImageView imViewInsertExhaust;
     public CommonState commonState = null;

	 
	 
	

 
	
   @Override
   public void onCreate(Bundle savedInstanceState) {
       super.onCreate(savedInstanceState);
       requestWindowFeature(Window.FEATURE_NO_TITLE);
       getWindow().getDecorView().setSystemUiVisibility(View.STATUS_BAR_HIDDEN);
       getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, 
               WindowManager.LayoutParams.FLAG_FULLSCREEN);
       setContentView(R.layout.preheatliquid_app);
       
       
       commonState = (CommonState) getApplication();
       commonState.activity_name = "PreHeatLiquid_Activity";
       language = commonState.language;
       
       imViewPreHeatIcon = (ImageView)findViewById(R.id.imViewPreHeatIcon);
       
       
       
       
       if(language.equals("English")){
           
           imBtnActivate = (ImageButton)findViewById(R.id.imBtnActivate);
           
           imBtnReturn = (ImageButton)findViewById(R.id.imBtnReturn);
           
           imBtnWarning = (ImageButton)findViewById(R.id.imBtnWarning);
           
           imViewPreHeatLiquidHead = (ImageView)findViewById(R.id.imViewPreHeatLiquidHead);
           
           imViewInsertExhaust = (ImageView)findViewById(R.id.imViewInsertExhaust);
         
 	   
    	   
       } 
       
       if(language.equals("Spanish")){
		   
           imBtnReturn = (ImageButton)findViewById(R.id.imBtnReturn);
           imBtnReturn.setImageResource(R.drawable.return_spa);
           
           imBtnWarning = (ImageButton)findViewById(R.id.imBtnWarning);
           imBtnWarning.setImageResource(R.drawable.warning_spa);
           

           imBtnActivate = (ImageButton)findViewById(R.id.imBtnActivate);
           imBtnActivate.setImageResource(R.drawable.activate_spa);
           
           imViewPreHeatLiquidHead = (ImageView)findViewById(R.id.imViewPreHeatLiquidHead);
           imViewPreHeatLiquidHead.setImageResource(R.drawable.preheat_liquid_head_spa);
           
           imViewInsertExhaust = (ImageView)findViewById(R.id.imViewInsertExhaust);
           imViewInsertExhaust.setImageResource(R.drawable.insert_exhaust_cover_spa);
    	   
 		   
 	   }
       
       imBtnWarning.setVisibility(View.INVISIBLE);
       
       imBtnWarning.setOnClickListener(this);
       imBtnReturn.setOnClickListener(this);
       imBtnActivate.setOnClickListener(this);
       
       	Animation animation = new AlphaAnimation(1, 0); 
	    animation.setDuration(800); 
	    animation.setInterpolator(new LinearInterpolator()); 
	    animation.setRepeatCount(Animation.INFINITE); 
	    animation.setRepeatMode(Animation.REVERSE); 
	    imBtnActivate.startAnimation(animation);
       
  
       
   }
   
   
   public void onClick(View v) {
	   
	   if(v==imBtnReturn){
		   
		   finish();
		   
	   }
	   
	   if(v==imBtnActivate){
		   
		   	imBtnActivate.clearAnimation();
		   	
		   	Intent intent = new Intent();
			intent.setClass(this,PreHeatActivated_Activity.class);
			startActivity(intent);
		   
		   
		   
	   }
	   
	   
   
    }
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
}
