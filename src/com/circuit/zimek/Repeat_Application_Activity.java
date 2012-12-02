package com.circuit.zimek;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.StringTokenizer;


import android.app.Activity;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnFocusChangeListener;
import android.view.Window;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.view.View.OnKeyListener;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;





public class Repeat_Application_Activity extends Activity implements OnClickListener {
	
	public CommonState commonState = null; 
	
	 private ImageButton imBtnReturn;
	 private ImageButton imBtnControlCenter;
	 private ImageButton imBtnStart;
	 private ImageButton imBtnEditApp;
	 private ImageButton imBtnWarning;
	 public InputMethodManager imm;
	 
	 private ImageButton imBtnPressToChange;
	 
	 private ImageView imViewConfirmAppHead;
	 private ImageView imViewTimePeriods;
	 private ImageView imViewConfirmLocHead;
	 private ImageView imViewConfirmLoc;
	 private ImageView imViewStartCountDown;

	 private EditText txtEditConfirmLoc;
	 private EditText EdTMistTime;
	 private EditText EdTDwellTime;
	 private EditText EdTZvacTime;
	 
	 public String locationId;
	 public String MistTime;
	 public String DwellTime;
	 public String ZvacTime;
	 public String MistMin;
	 public String DwellMin;
	 public String ZvacMin;
	 public String MistHour;
	 public String DwellHour;
	 public String ZvacHour;
	 public String RepeatApp = "1";
	 public String typeApplication;
	 public String typeConfigure;
	 public String language;


	public void onCreate(Bundle savedInstanceState) {
	      super.onCreate(savedInstanceState);
	      requestWindowFeature(Window.FEATURE_NO_TITLE);
	      getWindow().getDecorView().setSystemUiVisibility(View.STATUS_BAR_HIDDEN);
	       getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, 
	               WindowManager.LayoutParams.FLAG_FULLSCREEN);
	      setContentView(R.layout.repeat_application);
	      
	      commonState = (CommonState) getApplication();
          commonState.activity_name = "Repeat_Application_Activity";
	      mHandler.post(mUpdateUI);
		   
	      language = commonState.language;
	      if (language == "") {
	    	  language = "English";
	    	  commonState.language= "English";
	      }
	      
	      if(language.equals("English")){
	      	   
	    	  imViewConfirmAppHead = (ImageView)findViewById(R.id.imViewConfirmAppHead);
	    	     
	    	  imViewTimePeriods = (ImageView)findViewById(R.id.imViewTimePeriods);
	    	  
	    	  imViewConfirmLocHead = (ImageView)findViewById(R.id.imViewConfirmLocHead);
	    	     
	    	  imViewConfirmLoc = (ImageView)findViewById(R.id.imViewConfirmLoc);
	    	  
	    	  imViewStartCountDown = (ImageView)findViewById(R.id.imViewStartCountDown);
	          
	          imBtnControlCenter = (ImageButton)findViewById(R.id.imBtnControlCenter);
	          
	          imBtnStart = (ImageButton)findViewById(R.id.imBtnStart);
	          
	          imBtnReturn = (ImageButton)findViewById(R.id.imBtnReturn);
	          
	          imBtnWarning = (ImageButton)findViewById(R.id.imBtnWarning);
	          
	          imBtnEditApp = (ImageButton)findViewById(R.id.imBtnEditApp);
	          
	          imBtnPressToChange = (ImageButton)findViewById(R.id.imBtnPressToChange);
	        	   
	   	   
	      } else { // i.e. (language.equals("Spanish")
	    	  
	    	  
	    	  imViewConfirmAppHead = (ImageView)findViewById(R.id.imViewConfirmAppHead);
	    	  imViewConfirmAppHead.setImageResource(R.drawable.confirm_application_time_spa);
	    	  	    	     
	    	  imViewTimePeriods = (ImageView)findViewById(R.id.imViewTimePeriods);
	    	  imViewTimePeriods.setImageResource(R.drawable.application_time_periods_spa);
	    	  
	    	  imViewConfirmLocHead = (ImageView)findViewById(R.id.imViewConfirmLocHead);
	    	  imViewConfirmLocHead.setImageResource(R.drawable.confirm_location_id_spa);
	    	     
	    	  imViewConfirmLoc = (ImageView)findViewById(R.id.imViewConfirmLoc);
	    	  imViewConfirmLoc.setImageResource(R.drawable.edit_location_id_blank);
	    	  
	    	  imViewStartCountDown = (ImageView)findViewById(R.id.imViewStartCountDown);
	    	  imViewStartCountDown.setImageResource(R.drawable.press_start_for_countdown_spa);
	          
	          imBtnControlCenter = (ImageButton)findViewById(R.id.imBtnControlCenter);
	          imBtnControlCenter.setImageResource(R.drawable.control_center_spa);
	          
	          imBtnStart = (ImageButton)findViewById(R.id.imBtnStart);
	          imBtnStart.setImageResource(R.drawable.start_spa);
	          
	          
	          imBtnReturn = (ImageButton)findViewById(R.id.imBtnReturn);
	          imBtnReturn = (ImageButton)findViewById(R.id.imBtnReturn);
	          
	          imBtnWarning = (ImageButton)findViewById(R.id.imBtnWarning);
	          imBtnWarning.setImageResource(R.drawable.warning_spa);
	          
	          imBtnEditApp = (ImageButton)findViewById(R.id.imBtnEditApp);
	          imBtnEditApp.setImageResource(R.drawable.edit_application_time_spa);
	          
	          imBtnPressToChange = (ImageButton)findViewById(R.id.imBtnPressToChange);
	          imBtnPressToChange.setImageResource(R.drawable.press_to_change_loc_double_spa);
	          
	         //--------------------------------------------------------------
 		   
		   }
	      
	      	Animation animation = new AlphaAnimation(1, 0); 
		    animation.setDuration(800); 
		    animation.setInterpolator(new LinearInterpolator()); 
		    animation.setRepeatCount(Animation.INFINITE); 
		    animation.setRepeatMode(Animation.REVERSE); 
		    imBtnStart.startAnimation(animation);
		    imBtnPressToChange.startAnimation(animation);
	      

	      imBtnWarning.setVisibility(View.INVISIBLE);
	      
	      imBtnWarning.setOnClickListener(this);
	      imBtnReturn.setOnClickListener(this);
	      imBtnStart.setOnClickListener(this);
	      imBtnEditApp.setOnClickListener(this);
	      imBtnControlCenter.setOnClickListener(this);
	      imBtnPressToChange.setOnClickListener(this);
	      
	        
	        txtEditConfirmLoc = (EditText)findViewById(R.id.txtEditConfirmLoc);
	        txtEditConfirmLoc.setBackgroundColor(Color.TRANSPARENT);
	        //txtEditConfirmLoc.setOnClickListener(this);
	        
	        EdTMistTime = (EditText)findViewById(R.id.EdTMistTime);
	        EdTMistTime.setBackgroundColor(Color.TRANSPARENT);
	        EdTMistTime.setOnClickListener(this);
	        
	        EdTDwellTime = (EditText)findViewById(R.id.EdTDwellTime);
	        EdTDwellTime.setBackgroundColor(Color.TRANSPARENT);
	        EdTDwellTime.setOnClickListener(this);
	        
	        EdTZvacTime = (EditText)findViewById(R.id.EdTZvacTime);
	        EdTZvacTime.setBackgroundColor(Color.TRANSPARENT);
	        EdTZvacTime.setOnClickListener(this);
	        
	        
	        txtEditConfirmLoc.setOnFocusChangeListener(new OnFocusChangeListener()
	        {
	           
	            public void onFocusChange(View v, boolean hasFocus) 
	            {
	                if (hasFocus==true)
	                {   
	                    	//txtEditConfirmLoc.setText("");
	                    
	                }
	            }
	        });
	        
	        
	        txtEditConfirmLoc.setOnKeyListener(new OnKeyListener() {
				public boolean onKey(View v, int keyCode, KeyEvent event) {
					
		
					//if keydown and "enter" is pressed
					if (keyCode == KeyEvent.KEYCODE_ENTER) {

					if(txtEditConfirmLoc.getText().toString().isEmpty()) {
						
						
						Calculate();
						txtEditConfirmLoc.clearFocus();
						imm = (InputMethodManager)Repeat_Application_Activity.this.getSystemService(Service.INPUT_METHOD_SERVICE);
						imm.showSoftInput(txtEditConfirmLoc, 0);
						
						
						
					} else {
						
					Calculate();
					txtEditConfirmLoc.clearFocus();
					
					InputMethodManager inputManager = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE); 
	    		    inputManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),      
	    		    InputMethodManager.HIDE_NOT_ALWAYS);
				
						
					}
					      
				      // InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
				       //imm.hideSoftInputFromWindow(txtEditConfirmLoc.getWindowToken(), 0); 
					
					
					
				             
				   					
					return true;	
					
										
					}
					
					
					return false;
									
				}
		  	});
	        
	        
	      // RepeatApp = getIntent().getStringExtra("RepeatApp");
          // If the RepeatApp is always set to 1, why do we read it from
          // intent anyway ????
          RepeatApp= "1";
            try {
                locationId = getIntent().getStringExtra("locationId");
                MistHour = getIntent().getStringExtra("MistHour");
                MistMin = getIntent().getStringExtra("MistMin");
                DwellHour = getIntent().getStringExtra("DwellHour");
                DwellMin = getIntent().getStringExtra("DwellMin");
                ZvacHour = getIntent().getStringExtra("ZvacHour");
                ZvacMin = getIntent().getStringExtra("ZvacMin");
                typeApplication = getIntent().getStringExtra("typeApplication");
                typeConfigure = getIntent().getStringExtra("typeConfigure");

                commonState.LocationId = locationId;
                commonState.MistHour = MistHour;
                commonState.MistMin = MistMin;
                commonState.DwellHour = DwellHour;
                commonState.DwellMin = DwellMin;
                commonState.ZvacHour = ZvacHour;
                commonState.ZvacMin = ZvacMin;
                commonState.typeApplication = typeApplication;
                commonState.typeConfigure = typeConfigure;
            } catch (Exception e) {
                locationId = commonState.LocationId ;
                MistHour = commonState.MistHour ;
                MistMin = commonState.MistMin ;
                DwellHour = commonState.DwellHour ;
                DwellMin = commonState.DwellMin ;
                ZvacHour = commonState.ZvacHour ;
                ZvacMin = commonState.ZvacMin ;
                typeApplication = commonState.typeApplication ;
                typeConfigure = commonState.typeConfigure ;
            }
			locationId = locationId.trim();
	        txtEditConfirmLoc.setText(locationId);
	        EdTMistTime.setText(MistHour + ":" + MistMin);
	        EdTDwellTime.setText(DwellHour + ":" + DwellMin);
	        EdTZvacTime.setText(ZvacHour + ":" + ZvacMin);

            /************* Simplify the following code *************
	        if (RepeatApp.equals("1")) {
	            locationId = getIntent().getStringExtra("locationId");
	    		MistHour = getIntent().getStringExtra("MistHour");
		        MistMin = getIntent().getStringExtra("MistMin");
		        DwellHour = getIntent().getStringExtra("DwellHour");
		        DwellMin = getIntent().getStringExtra("DwellMin");
		        ZvacHour = getIntent().getStringExtra("ZvacHour");
		        ZvacMin = getIntent().getStringExtra("ZvacMin");
		           
		        locationId = locationId.trim();
		        txtEditConfirmLoc.setText(locationId);
		        EdTMistTime.setText(MistHour + ":" + MistMin);
		        EdTDwellTime.setText(DwellHour + ":" + DwellMin);
		        EdTZvacTime.setText(ZvacHour + ":" + ZvacMin);
		        	      
		        	        	
	        } 
	        else 
	        {
	        //Fetch Contents from previous screen
	        	
	        		locationId = getIntent().getStringExtra("locationId");
		    		MistHour = getIntent().getStringExtra("MistHour");
			        MistMin = getIntent().getStringExtra("MistMin");
			        DwellHour = getIntent().getStringExtra("DwellHour");
			        DwellMin = getIntent().getStringExtra("DwellMin");
			        ZvacHour = getIntent().getStringExtra("ZvacHour");
			        ZvacMin = getIntent().getStringExtra("ZvacMin");
			        typeApplication = getIntent().getStringExtra("typeApplication");
			        typeConfigure = getIntent().getStringExtra("typeConfigure");
			
			locationId = locationId.trim();
	        txtEditConfirmLoc.setText(locationId);
	        EdTMistTime.setText(MistHour + ":" + MistMin);
	        EdTDwellTime.setText(DwellHour + ":" + DwellMin);
	        EdTZvacTime.setText(ZvacHour + ":" + ZvacMin);
	        
	        }
            *******************************/

	}
	
	public void Calculate(){
		
		if(txtEditConfirmLoc.getText().toString().trim().equals("")) {
			
			txtEditConfirmLoc.requestFocus();
			imBtnStart.clearAnimation();
			imBtnStart.setVisibility(View.INVISIBLE);
			imBtnPressToChange.clearAnimation();
			imBtnPressToChange.setVisibility(View.INVISIBLE);
			imm = (InputMethodManager)this.getSystemService(Service.INPUT_METHOD_SERVICE);
			imm.showSoftInput(txtEditConfirmLoc, 0);	
			
		} else {
			
			imBtnStart.setVisibility(View.VISIBLE);
			imBtnPressToChange.setVisibility(View.VISIBLE);
		      
	    	Animation animation = new AlphaAnimation(1, 0); 
		    animation.setDuration(800); 
		    animation.setInterpolator(new LinearInterpolator()); 
		    animation.setRepeatCount(Animation.INFINITE); 
		    animation.setRepeatMode(Animation.REVERSE); 
		    imBtnStart.startAnimation(animation);
		    imBtnPressToChange.startAnimation(animation);
		    
		    InputMethodManager inputManager = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE); 
			 inputManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),      
			 InputMethodManager.HIDE_NOT_ALWAYS);
			 
			// InputMethodManager inputManager = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE); 
			 //inputManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),      
			 //InputMethodManager.HIDE_NOT_ALWAYS);
			
	
			
		}
		      

	     
	}
	
	
public void GetContents(){
		
		String TotalMist = EdTMistTime.getText().toString();
		TotalMist = TotalMist.trim();
		
		String TotalDwell = EdTDwellTime.getText().toString();
		TotalDwell = TotalDwell.trim();
		
		String TotalZvac = EdTZvacTime.getText().toString();
		TotalZvac = TotalZvac.trim();
		
		StringTokenizer token = new StringTokenizer(TotalMist, ":");
		MistHour = token.nextToken();
	    MistMin = token.nextToken();
	    
	    StringTokenizer tokens = new StringTokenizer(TotalDwell, ":");
		DwellHour = tokens.nextToken();
	    DwellMin = tokens.nextToken();
	    
	    StringTokenizer tokensz = new StringTokenizer(TotalZvac, ":");
		ZvacHour = tokensz.nextToken();
	    ZvacMin = tokensz.nextToken();
		
		
		
	}
	
	
	
	
	public void onClick(View v){
    	if(v==imBtnReturn){
    	
    		finish();
    	}
    	
    	if(v==imBtnPressToChange){
    		
    	txtEditConfirmLoc.requestFocus();
    	txtEditConfirmLoc.setText("");
    	
    	
    	imBtnPressToChange.clearAnimation();
		imBtnPressToChange.setVisibility(View.INVISIBLE);
		
		imBtnStart.clearAnimation();
		imBtnStart.setVisibility(View.INVISIBLE);	
		
		imm = (InputMethodManager)this.getSystemService(Service.INPUT_METHOD_SERVICE);
		imm.showSoftInput(txtEditConfirmLoc, 0);	    
			//onDestroy();
    		
    	}
    	
    	if(v==imBtnControlCenter){
    		
    		Intent resultIntent = new Intent();
  			resultIntent.setClass(this,ZimekActivity.class);
  			setResult(Activity.RESULT_OK, resultIntent);
  			startActivity(resultIntent);
			
			//onDestroy();
    		
    	}
    	
    	
    	if(v==imBtnStart){
    		
    		onDestroy();	
			
			mHandler.removeCallbacks(mUpdateUI);
    		
    		//onDestroy();
    	
    		
    		RepeatApp = "0";
    		
    		GetContents();

			
			String mydate = java.text.DateFormat.getDateTimeInstance().format(Calendar.getInstance().getTime());
			 // Toast.makeText(this, "TIME:" + mydate, Toast.LENGTH_SHORT).show();
			
			 StringTokenizer tokens = new StringTokenizer(mydate, " ");
		     String Month = tokens.nextToken();
		     String Day = tokens.nextToken();
		     String Year = tokens.nextToken();
		     String Time = tokens.nextToken();
		     String amorpm = tokens.nextToken();
		     
		     //String myDate = String.format("%s %s %s", Month, Day, Year);
		     //Toast.makeText(this, "Date :" + myDate, Toast.LENGTH_SHORT).show();
		     
		     Date date = new Date(System.currentTimeMillis());
		     SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yy");
		     String newDate = formatter.format(date);
		     
		     
		     //Toast.makeText(this, "Date :" + newDate, Toast.LENGTH_LONG).show();
		     
		     String myDate = newDate;
		     
		     StringTokenizer Tokens = new StringTokenizer(Time, ":");
		     String Hour = Tokens.nextToken();
		     String Minutes = Tokens.nextToken(); 
		     
		     String myTime = String.format("%s:%s %s", Hour, Minutes, amorpm);
			
			locationId = txtEditConfirmLoc.getText().toString();
	        
			Intent intent = new Intent();
			intent.setClass(this,Begin_Countdown_Activity.class);
			intent.putExtra("Date", myDate);
			intent.putExtra("Time", myTime);
			intent.putExtra("locationId", locationId);
			intent.putExtra("MistHour", MistHour);
			intent.putExtra("MistMin", MistMin);
			intent.putExtra("DwellHour", DwellHour);
			intent.putExtra("DwellMin", DwellMin);
			intent.putExtra("ZvacHour", ZvacHour);
			intent.putExtra("ZvacMin", ZvacMin);
			intent.putExtra("typeApplication", typeApplication);
			intent.putExtra("typeConfigure", typeConfigure);
			intent.putExtra("RepeatApp", RepeatApp);
			startActivity(intent);
			
			onDestroy();
    		
    	}
    	
  	if(v==imBtnEditApp){
  		
    		RepeatApp = "2";
    		
    		GetContents();
    		
    		typeApplication = "Manual";
    		typeConfigure ="N/A";
    			
    		
    		
    		Intent intent = new Intent();
    		intent.putExtra("locationId", locationId);
			intent.putExtra("MistHour", MistHour);
			intent.putExtra("MistMin", MistMin);
			intent.putExtra("DwellHour", DwellHour);
			intent.putExtra("DwellMin", DwellMin);
			intent.putExtra("ZvacHour", ZvacHour);
			intent.putExtra("ZvacMin", ZvacMin);
			intent.putExtra("typeApplication", typeApplication);
			intent.putExtra("typeConfigure", typeConfigure);
			intent.putExtra("RepeatApp", RepeatApp);
			//intent.setClass(this,ManualApp_Activity.class);
			intent.setClass(this,RepeatApp_ChangeTime_Activity.class);
			startActivity(intent);
			
			onDestroy();
    		
    	}
	}
	
	
	 private boolean shouldContinue = true;
	 private final Handler mHandler = new Handler();
		private final Runnable mUpdateUI = new Runnable() {
		    public void run() {
		    	
		    	if(shouldContinue){
		    	
		    	if (commonState.lastCommand != 0) {
              	int lastCommand = commonState.lastCommand;
              	commonState.lastCommand = 0;
              	
	
              	if (lastCommand == 2){
              		
              		imBtnStart.performClick();
              		shouldContinue = false;
              	}
         
              	}
		       
		    	 mHandler.postDelayed(this, 200); // 1 second
		    	}
		    	
		    }   	
		    };
		
	
	  

	 
	  
	  public void onDestroy()
	  {   
//	      Cleanup();
	      super.onDestroy();
	      
	      mHandler.removeCallbacks(mUpdateUI);
		  
			 shouldContinue = false;
	
	  }
	
	
	
}
