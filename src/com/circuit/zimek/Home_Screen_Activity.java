package com.circuit.zimek;


import android.app.Activity;
import android.app.AlertDialog;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.IBinder;
import android.preference.PreferenceManager;
import android.view.ContextThemeWrapper;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.SystemSettings_Report.SystemSettingsDatabaseHandler;
import com.SystemSettings_Report.SystemSettingsReports;
import com.PassCode_Report.PassCodeDatabaseHandler;
import com.PassCode_Report.PassCodeReports;


public class Home_Screen_Activity extends Activity implements OnClickListener{
	
	
	private ImageButton imBtnLanguage;
	private ImageButton imBtnAgree;
	private ImageButton imBtnReadManual;
	private ImageView imViewOM;
	public String language;
	public TextView txtViewLanguage;
	public String OPSActivated;
	final Context context = this;

	public CommonState commonState = null;
	String[] values;
	//String zvacStatusStr;
	//byte [] zvacStatus;
	
	private ZComm_Service mService;
    boolean mBound = false;
    public ServiceConnection mConnection = new ServiceConnection() {

        @Override
        public void onServiceConnected(ComponentName className, IBinder service){
            mService = ((ZComm_Service.MyBinder) service).getService();
            mBound = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName className) {
            mService = null;
            mBound = false;
        }
    };	
    
    private void sendData(String str) {
    	if (mBound) {
            // Sets the cirulation pump on, fan at 50
            int num = mService.sendZvacCommand(str);
            // optional, to read the status of the Zvac:
            //zvacStatusStr = mService.readCompStatus();
            //mReceiveStatus.setText(zvacStatus);  Can be used for debugging
            // Do whatever with the zvacStatus. It is in binary format, total of 18 bytes
        } else {
            Intent intent = new Intent(Home_Screen_Activity.this, ZComm_Service.class);
            bindService(intent, mConnection, Context.BIND_AUTO_CREATE);
        }
    }
	
	
   @Override
   public void onCreate(Bundle savedInstanceState) {
       super.onCreate(savedInstanceState);
       
       requestWindowFeature(Window.FEATURE_NO_TITLE);
       getWindow().getDecorView().setSystemUiVisibility(View.STATUS_BAR_HIDDEN);
       getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, 
               WindowManager.LayoutParams.FLAG_FULLSCREEN);
       getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
       setContentView(R.layout.home_screen);

       commonState = (CommonState) getApplication();
       commonState.activity_name = "Home_Screen_Activity";
       if (commonState.language.equals("")) {
           commonState.language = "English";
       }
       mBound=false;
       sendData("C0,B0,F0,S0,W0,R0");
 	   
 	  imBtnLanguage = (ImageButton)findViewById(R.id.imBtnLanguage);
 	  imBtnReadManual = (ImageButton)findViewById(R.id.imBtnReadManual);
 	  imBtnAgree = (ImageButton)findViewById(R.id.imBtnAgree);
 	  txtViewLanguage = (TextView )findViewById(R.id.txtViewLanguage);
 	  imViewOM = (ImageView)findViewById(R.id.imViewOM);
      
 	   
      
       SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
	   String savedlanguage=preferences.getString("language","");
	   
	   if (!savedlanguage.equals(null)){
		   
		  language = savedlanguage;
     	  LanguageSelected();
 		
	   } else {
		   
		   
		     language = "English";
		   
		   	 //SharedPreferences preferences1 = PreferenceManager.getDefaultSharedPreferences(Home_Screen_Activity.this);
			  SharedPreferences.Editor editor=preferences.edit();
			  editor.putString("English",language);
			  editor.commit();
			  
			  LanguageSelected();
		   
		   
	   }
	   
	   OPSACTIVATED();
	
       
       
       imBtnLanguage.setOnClickListener(this);
       imBtnAgree.setOnClickListener(this);
       imBtnReadManual.setOnClickListener(this);

       values = new String[] { "English", "Espa�ol"};
       
       	Animation animation = new AlphaAnimation(1, 0); 
	    animation.setDuration(800); 
	    animation.setInterpolator(new LinearInterpolator()); 
	    animation.setRepeatCount(Animation.INFINITE); 
	    animation.setRepeatMode(Animation.REVERSE); 
	    imBtnAgree.startAnimation(animation);
	    //txtViewWearPPE.startAnimation(animation);
       //

       
       imBtnLanguage.setOnClickListener(new OnClickListener() {
    	   
    	   @Override
    	   public void onClick(View v) {
    		   
    		     
    	    SelectLanguage();
    	   }
    	  });

       
   }
   
    public void LanguageSelected(){
	    if (language.equals("English")){
            txtViewLanguage.setText("English");
            imBtnAgree.setImageResource(R.drawable.agree_eng);
            imViewOM.setImageResource(R.drawable.operator_shall_follow_eng);
            imBtnReadManual.setImageResource(R.drawable.read_manual_eng);
   	    } 
	   
        if (language.equals("Spanish")){
            txtViewLanguage.setText("Espa�ol");
            imBtnAgree.setImageResource(R.drawable.agree_spa);
            imViewOM.setImageResource(R.drawable.operator_shall_follow_spa);
            imBtnReadManual.setImageResource(R.drawable.read_manual_spa);
        } 
	   
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(Home_Screen_Activity.this);
        SharedPreferences.Editor editor=preferences.edit();
        editor.putString("language",language);
        editor.commit();
        commonState.language = language;
    }

    public void SelectLanguage(){
	    getWindow().getDecorView().setSystemUiVisibility(View.STATUS_BAR_HIDDEN);
	    AlertDialog.Builder builder = new AlertDialog.Builder(new ContextThemeWrapper(this, R.style.Theme_Dialog)); 
	    builder.setTitle("Select Language:"); 
        builder.setItems(values, new DialogInterface.OnClickListener() {
  	 	
	    public void onClick(DialogInterface dialog, int item) {  
	         //Toast.makeText(Home_Screen_Activity.this, "Selected Item"+ item, Toast.LENGTH_SHORT).show();
	         if (item == 0){
	        	 
	        	 getWindow().getDecorView().setSystemUiVisibility(View.STATUS_BAR_HIDDEN);
	        	 language = "English";
	        	 
	        	   //txtViewLanguage.setText("English");
	    		   //imBtnAgree.setImageResource(R.drawable.agree_eng);
	    		   //imViewOM.setImageResource(R.drawable.operator_shall_follow_eng);
	    		   //imBtnReadManual.setImageResource(R.drawable.read_manual_eng);
	        	 
	        	 LanguageSelected();
	        	 
	         }
	         
	         if (item == 1){
	        	 
	        	 getWindow().getDecorView().setSystemUiVisibility(View.STATUS_BAR_HIDDEN);
	        	 language = "Spanish";
        	 
	  		   //txtViewLanguage.setText("Espa�ol");
			   //imBtnAgree.setImageResource(R.drawable.agree_spa);
			   //imViewOM.setImageResource(R.drawable.operator_shall_follow_spa);
			   //imBtnReadManual.setImageResource(R.drawable.read_manual_spa);
	        	 
	        	 LanguageSelected();
	        	
	         }
	         
	         //SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(Home_Screen_Activity.this);
			  //SharedPreferences.Editor editor=preferences.edit();
			  //editor.putString("language",language);
			  //editor.commit();
			  
			  //getWindow().getDecorView().setSystemUiVisibility(View.STATUS_BAR_HIDDEN);
     
	         
	        }  
	     
	   });  
	     
	      AlertDialog alert = builder.create();  
	      alert.show(); 
	      
	       
   }
   
   public void OPSACTIVATED(){
	   
	   int id = 1;
	   PassCodeDatabaseHandler db = new PassCodeDatabaseHandler(this);
	   
	   int PassCodeCount = db.getReportsCount();
		
	    if(PassCodeCount == 0){
	    	
	    	String defaultPassCode = "2";
	    	String defaultPassCodeOPS = "1";
	    	String NewPassCodeOPS = "1";
	    	String PassCodeOPSActivated = "0";
	    	String newPassCode = "2";
	    	id = PassCodeCount;
	    	
	    	db.addReport(new PassCodeReports(id, defaultPassCode, newPassCode, defaultPassCodeOPS, NewPassCodeOPS, PassCodeOPSActivated));
			
	    } else {
	   
		PassCodeReports Results = db.getReport(id);
  		
  		OPSActivated = Results.getPassCodeOPSActivated();
  		
  		if(OPSActivated.equals("")){
  			
  			OPSActivated = "0";
  			String MGTPassCode = "2";
  			String MGTNewPassCode = "2";
  			String OPSPassCode = "1";
  			String OPSNewPassCode = "1";
  	    	
  	    	db.updateContents(new PassCodeReports(id, MGTPassCode, MGTNewPassCode, OPSPassCode, OPSNewPassCode, OPSActivated));
  		}	
  			
  		}
	    
	    db.close();
	    
	    SystemSettingsDatabaseHandler db2 = new SystemSettingsDatabaseHandler(this);
	    int reportCount = db2.getReportsCount();
	    if (reportCount == 0) {
	        id = 1;
	        db2.addReport(new SystemSettingsReports(id, "0", "0")); // id, beeper=0, metrics=0
	    } 
	    db2.close();
   }
   
 
   public void onClick(View v){     	
      	if(v==imBtnAgree){
      		language = txtViewLanguage.getText().toString();
      		sendData("C0");
      		commonState.mService = mService;
            commonState.mBound = mBound;
            //zvacStatus = mService.readRawCompStatus();
            //commonState.liquidLevelPercentage = zvacStatus[10];
      		
      		if(language.equals("Espa�ol")){
      			language = "Spanish";
      		}
      		      		
      		if(OPSActivated.equals("1")){
      			
      			
      			String Key = "3";
      			Intent intent = new Intent();
          		intent.setClass(this,PassCode_Activity.class);
          		intent.putExtra("Key", Key);
          		startActivityForResult(intent, 1);
      			
      			
      		} else {
      		    if(OPSActivated.equals("0")){
                    Intent intent = new Intent();
                    intent.setClass(this,ZimekActivity.class);
                    startActivityForResult(intent, 1);
      		    }
      		}
      		
      		//Toast.makeText(Home_Screen_Activity.this, "Selected Item"+ language, Toast.LENGTH_SHORT).show();
      	  
      	
  			//onStop();
  			onDestroy();
      	}
      	
      	if(v==imBtnReadManual){
      		
      		//String baseDir = Environment.getExternalStorageDirectory().getAbsolutePath();
      		
      		Intent intent = new Intent();
        	intent.setClass(this,TableOfContents_Activity.class);
        	startActivity(intent);
  
      		
      		
      		
      		
      		//File file = new File("/sdcard/OPS Coverpage Front.pdf");
      		
      	    // if (file.exists()) {
              //   Uri path = Uri.fromFile(file);
                // Intent intent = new Intent(Intent.ACTION_VIEW);
                // intent.setDataAndType(path, "application/pdf");
                 //intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

                 //try {
                	// Toast.makeText(Home_Screen_Activity.this, 
                      //       "Trying", 
                        //     Toast.LENGTH_SHORT).show();
                     //startActivity(intent);
                     
                // } 
                // catch (ActivityNotFoundException e) {
                  //   Toast.makeText(Home_Screen_Activity.this, 
                    //     "No Application Available to View PDF", 
                      //   Toast.LENGTH_SHORT).show();
                 //}
             //}
      		
      		
      		
      	}
      	

   
      	
      	
      	

   }
   
   @Override
   protected void onResume(){
	  super.onResume();
	  getWindow().getDecorView().setSystemUiVisibility(View.STATUS_BAR_HIDDEN);
	  
	  SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
	  String name=preferences.getString("language","");
	  
	  if(name.equals("English")){
    	  
		   txtViewLanguage.setText("English");
		   imBtnAgree.setImageResource(R.drawable.agree_eng);
		   imViewOM.setImageResource(R.drawable.operator_shall_follow_eng);
		   imBtnReadManual.setImageResource(R.drawable.read_manual_eng);

	  }
       
       if (name.equals("Spanish")){
    	   
    	   txtViewLanguage.setText("Espa�ol");
		   imBtnAgree.setImageResource(R.drawable.agree_spa);
		   imViewOM.setImageResource(R.drawable.operator_shall_follow_spa);
		   imBtnReadManual.setImageResource(R.drawable.read_manual_spa);
    	   
       }  
       
       OPSACTIVATED();
   }
   
   @Override
   public void onActivityResult(int requestCode, int resultCode, Intent data) {
	   super.onActivityResult(requestCode, resultCode, data);
	   
	  // Toast.makeText(Home_Screen_Activity.this, "Result OK: Home Screen DATA" + language, Toast.LENGTH_LONG).show();
	   
	   switch(requestCode) {
	     case 1: 
	    	 
         if (resultCode == RESULT_OK) {
        	 language = data.getStringExtra("language");
        	
        	 //Toast.makeText(Home_Screen_Activity.this, "Result OK: Home Screen" + data, Toast.LENGTH_LONG).show();
        	 
        	  if(language.equals("English")){
	    	  
        		   txtViewLanguage.setText("English");
	    		   imBtnAgree.setImageResource(R.drawable.agree_eng);
	    		   imViewOM.setImageResource(R.drawable.operator_shall_follow_eng);
	    		   imBtnReadManual.setImageResource(R.drawable.read_manual_eng);
        	  }
 		       
 		       if (language.equals("Spanish")){
 		    	   
 		    	   txtViewLanguage.setText("Espa�ol");
 				   imBtnAgree.setImageResource(R.drawable.agree_spa);
 				   imViewOM.setImageResource(R.drawable.operator_shall_follow_spa);
 				  imBtnReadManual.setImageResource(R.drawable.read_manual_spa);
 		    	   
 		       }  
 		       
 		       break;
         }
	   }
    }
}


   
   

   
         
   
