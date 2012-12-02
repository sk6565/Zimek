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


public class New_Application_Activity extends Activity implements OnClickListener {
	 private ImageView iViewNewApp;
	 private ImageButton imBtnManualCali;
	 private ImageButton imBtnAutoCali;
	 private ImageButton imBtnReturn;
	 private ImageButton imBtnWarning;
	 public String language;
	 
	 public String typeApplication;
	 public String typeConfigure;

	 public String RepeatApp;
     public CommonState commonState = null;

	 
   @Override
   protected void onNewIntent(Intent intent) {
       super.onNewIntent(intent);
       setIntent(intent);
   }

   @Override
   public void onCreate(Bundle savedInstanceState) {
       super.onCreate(savedInstanceState);
       requestWindowFeature(Window.FEATURE_NO_TITLE);
       getWindow().getDecorView().setSystemUiVisibility(View.STATUS_BAR_HIDDEN);
       getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, 
               WindowManager.LayoutParams.FLAG_FULLSCREEN);
       setContentView(R.layout.new_application);
       
       RepeatApp = "0";
       
       commonState = (CommonState) getApplication();
       commonState.activity_name = "New_Application_Activity";
       language = commonState.language;
       
       iViewNewApp = (ImageView)findViewById(R.id.imViewNewApp);
       imBtnManualCali = (ImageButton)findViewById(R.id.imBtnManualCali);
       imBtnAutoCali = (ImageButton)findViewById(R.id.imBtnAutoCali);
       imBtnReturn = (ImageButton)findViewById(R.id.imBtnReturn);
       imBtnWarning = (ImageButton)findViewById(R.id.imBtnWarning);

       if(language.equals("Spanish")){
           iViewNewApp.setImageResource(R.drawable.new_application_heading_spa);
           imBtnManualCali.setImageResource(R.drawable.manual_calibration_spa);
           imBtnAutoCali.setImageResource(R.drawable.automatic_calibration_spa);
           imBtnReturn.setImageResource(R.drawable.return_spa);
           imBtnWarning.setImageResource(R.drawable.warning_spa);
       }

       iViewNewApp.setOnClickListener(this);
       imBtnManualCali.setOnClickListener(this);
       imBtnAutoCali.setOnClickListener(this);
       imBtnReturn.setOnClickListener(this);
       imBtnWarning.setOnClickListener(this);
       imBtnWarning.setVisibility(View.INVISIBLE); 
   }

   public void onClick(View v) {
        if(v==imBtnManualCali){
   		    typeConfigure = "N/A";
   		    typeApplication = "N/A";
   		
            Intent intent = new Intent();
            intent.setClass(New_Application_Activity.this,ManualApp_Activity.class);
            intent.putExtra("typeApplication", typeApplication);
            intent.putExtra("typeConfigure", typeConfigure);
            intent.putExtra("RepeatApp", RepeatApp);
            commonState.typeApplication = typeApplication;
            commonState.typeConfigure = typeConfigure;
            startActivity(intent);
   	    }
   	
   	    if(v==imBtnReturn){
   		    finish();
   		    onDestroy();
   	    }
   	
 	    if(v==imBtnAutoCali){
 		    typeConfigure = "Automatic";
 		    typeApplication = "N/A";
   		
 	   	    Intent intent = new Intent();
 			intent.setClass(New_Application_Activity.this,AutomaticApp_Activity.class);
 			intent.putExtra("typeApplication", typeApplication);
 			intent.putExtra("typeConfigure", typeConfigure);
 			intent.putExtra("RepeatApp", RepeatApp);
            commonState.typeApplication = typeApplication;
            commonState.typeConfigure = typeConfigure;
 			startActivity(intent);
   	    }
   	}
   	
   
   public void onDestroy()
   {   
       super.onDestroy();
   }
   
}

