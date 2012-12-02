package com.circuit.zimek;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;





public class Liquid_Conversion_Activity extends Activity implements OnClickListener {
	public CommonState commonState = null; 
	
	 private ImageButton imBtnWarning;
	 private ImageButton imBtnReturn;
	 private ImageView imViewMistTimeRemaining;
	 private ImageView imViewTimeRemaing;
	 private ImageView imViewLitresGallons;
	 
	 public String language;
	 private EditText EdTHours;
	 private EditText EdTMin;
	 private EditText EdTGal;
	 private EditText EdTLit;
	 
	 
	 
   @Override
   public void onCreate(Bundle savedInstanceState) {
       super.onCreate(savedInstanceState);
       requestWindowFeature(Window.FEATURE_NO_TITLE);
       getWindow().getDecorView().setSystemUiVisibility(View.STATUS_BAR_HIDDEN);
       getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, 
               WindowManager.LayoutParams.FLAG_FULLSCREEN);
       setContentView(R.layout.liquid_conversion_rate);
       commonState = (CommonState) getApplication();
       commonState.activity_name = "Liquid_Conversion_Activity";


 
       language = commonState.language;
       
       EdTHours = (EditText)findViewById(R.id.EdTHours);
      // EdTHours.setBackgroundColor(Color.TRANSPARENT);
      // EdTHours.setOnClickListener(this);
       
       EdTMin = (EditText)findViewById(R.id.EdTMin);
       //EdTMin.setBackgroundColor(Color.TRANSPARENT);
       //EdTMin.setOnClickListener(this);
   	       
  
	   
	   if(language.equals("English")){
    	   
		   imViewMistTimeRemaining = (ImageView)findViewById(R.id.imViewMistTimeRemaining);
		   
		   imViewLitresGallons = (ImageView)findViewById(R.id.imViewLitresGallons);
		   
		   imViewTimeRemaing = (ImageView)findViewById(R.id.imViewTimeRemaing);
           
           imBtnReturn = (ImageButton)findViewById(R.id.imBtnReturn);
           
           imBtnWarning = (ImageButton)findViewById(R.id.imBtnWarning);
           
           EdTHours.setText(String.format("%.1f Hours", commonState.mmTimeRemainingHours));
           EdTMin.setText(String.format("%d Minutes", commonState.mmTimeRemainingMins));
       } 
	   
	   if(language.equals("Spanish")){
		   
		   imViewMistTimeRemaining = (ImageView)findViewById(R.id.imViewMistTimeRemaining);
		   
		   imViewLitresGallons = (ImageView)findViewById(R.id.imViewLitresGallons);
		   
		   imViewTimeRemaing = (ImageView)findViewById(R.id.imViewTimeRemaing);
           
           imBtnReturn = (ImageButton)findViewById(R.id.imBtnReturn);
           
           imBtnWarning = (ImageButton)findViewById(R.id.imBtnWarning);
		    
           imViewMistTimeRemaining.setImageResource(R.drawable.micro_mist_time_remaining_heading_spa);
            
           imViewTimeRemaing.setImageResource(R.drawable.micro_mist_cycle_time_remaining_spa);
           
           imViewLitresGallons.setImageResource(R.drawable.gallons_liters_spa);
           
           imBtnReturn.setImageResource(R.drawable.return_spa);
           
           imBtnWarning.setImageResource(R.drawable.warning_spa);
           EdTHours.setText(String.format("%.1f Horas", commonState.mmTimeRemainingHours));
           EdTMin.setText(String.format("%d Minutos", commonState.mmTimeRemainingMins));		   
	   }
	   
	   	   imBtnWarning.setOnClickListener(this);  
		   imBtnReturn.setOnClickListener(this);
		   imBtnWarning.setVisibility(View.INVISIBLE);
     

     
     
     
     EdTGal = (EditText)findViewById(R.id.EdTGal);
     EdTGal.setText(String.format("%.1f", commonState.gallons));
     EdTGal.setBackgroundColor(Color.TRANSPARENT);
     EdTGal.setOnClickListener(this);
     
     EdTLit = (EditText)findViewById(R.id.EdTLit);
     EdTGal.setText(String.format("%.1f", commonState.liters));
     EdTLit.setBackgroundColor(Color.TRANSPARENT);
     EdTLit.setOnClickListener(this);
       

   }
   
   public void onClick(View v){
   	if(v==imBtnReturn){

   		finish();
   	}
   	
   }
}
   
//   public void onDestroy()
//	  {   
//	      Cleanup();
//	      super.onDestroy();
//	  }

//	  private void Cleanup()
//	  {    
		  
//		  Bitmap Liquid_Conversion_background = BitmapFactory.decodeStream(getResources().openRawResource(R.drawable.liquid_app));
//  		  ImageView Liquid_Conversion_Screen_Background = (ImageView)findViewById(R.drawable.liquid_app);
  		

//  		if (Liquid_Conversion_Screen_Background!= null)
//  			Liquid_Conversion_Screen_Background.setImageBitmap(null);
 		 		
// 		 	if (Liquid_Conversion_background!= null) {
// 		 		Liquid_Conversion_background.recycle();
 			     
// 			   }
// 		 	  Liquid_Conversion_background = null;
// 		 	  Liquid_Conversion_Screen_Background = null;
 	   	
 			  // System.gc();
  		
//  		Bitmap btnReturnImage = BitmapFactory.decodeStream(getResources().openRawResource(R.drawable.back_button));
//  		ImageView btnReturn = (ImageView)findViewById(R.drawable.back_button);
  		
//  		 if (btnReturn!= null)
//  			 btnReturn.setImageBitmap(null);
  		 		
//  		 if (btnReturnImage!= null) {
//  			 btnReturnImage.recycle();
  			     
//  			   }
//  		 btnReturnImage = null;
//  		 btnReturn = null;
  		 
//  		Bitmap btnControlCenterImage = BitmapFactory.decodeStream(getResources().openRawResource(R.drawable.controlcenter_button));
//  		ImageView btnControlCenter = (ImageView)findViewById(R.drawable.controlcenter_button);
  		
//  		 if (btnControlCenter!= null)
//  			 btnControlCenter.setImageBitmap(null);
  		 		
//  		 if (btnControlCenterImage!= null) {
//  			 btnControlCenterImage.recycle();
  			     
//  			   }
//  		 btnControlCenterImage = null;
//  		 btnControlCenter = null;
  		
  		
	  	
//	      System.gc();
//	      Runtime.getRuntime().gc();  
//	  }
   
   
//}
