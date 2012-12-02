package com.circuit.zimek;


import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.StringTokenizer;
import android.app.Activity;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.InputType;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.Window;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.view.View.OnKeyListener;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;
import android.widget.Toast;




public class Enter_Location_Activity extends Activity implements OnClickListener{
	
	
	public int RemoteStart = 0;
	
	 private ImageButton imBtnWarning;
	 private ImageButton imBtnPressToChange;
	 private ImageButton imBtnReturn;
	 private ImageButton imBtnControlCenter;
	 private ImageButton imBtnStart;
	 
	 private ImageView imViewEnterLoc;
	 private ImageView imViewTextBg;
	 private ImageView imViewStart;
	 

	 private EditText locationId;
	 public InputMethodManager imm;
	 public String language;
	 
	 public CommonState commonState = null; 
	 

	 
	
	@Override
	  public void onCreate(Bundle savedInstanceState) {
	      super.onCreate(savedInstanceState);
	      requestWindowFeature(Window.FEATURE_NO_TITLE);
	      getWindow().getDecorView().setSystemUiVisibility(View.STATUS_BAR_HIDDEN);
	       getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, 
	               WindowManager.LayoutParams.FLAG_FULLSCREEN);
	      setContentView(R.layout.enter_location);
	      
	      commonState = (CommonState) getApplication();
          commonState.activity_name = "Enter_Location_Activity";
	       RemoteStart = 0;
	     mHandler.post(mUpdateUI);
	      
	     //mHandler.postDelayed(mUpdateUI, 1000);
	      
	      
	      language = commonState.language;
	      
	      if(language.equals("English")){
	    	  imViewEnterLoc = (ImageView)findViewById(R.id.imViewEnterLoc);
	    	  imViewTextBg = (ImageView)findViewById(R.id.imViewTextBg);
	    	  imViewStart = (ImageView)findViewById(R.id.imViewStart);
	          imBtnControlCenter = (ImageButton)findViewById(R.id.imBtnControlCenter);
	          imBtnStart = (ImageButton)findViewById(R.id.imBtnStart);
	          imBtnReturn = (ImageButton)findViewById(R.id.imBtnReturn);
	          imBtnWarning = (ImageButton)findViewById(R.id.imBtnWarning);
	          imBtnPressToChange = (ImageButton)findViewById(R.id.imBtnPressToChange);
	      } else { // i.e. (language.equals("Spanish"))
	          imBtnReturn = (ImageButton)findViewById(R.id.imBtnReturn);
	          imBtnReturn.setImageResource(R.drawable.return_spa);
	          
	          imBtnWarning = (ImageButton)findViewById(R.id.imBtnWarning);
	          imBtnWarning.setImageResource(R.drawable.warning_spa);
	          imBtnControlCenter = (ImageButton)findViewById(R.id.imBtnControlCenter);
	          imBtnControlCenter.setImageResource(R.drawable.control_center_spa);
	          imBtnStart = (ImageButton)findViewById(R.id.imBtnStart);
	          imBtnStart.setImageResource(R.drawable.start_spa);
	          imViewEnterLoc = (ImageView)findViewById(R.id.imViewEnterLoc);
	          imViewEnterLoc.setImageResource(R.drawable.enter_location_spa);
	 	     
	          imViewTextBg = (ImageView)findViewById(R.id.imViewTextBg);
	          imViewTextBg.setImageResource(R.drawable.confirm_location_blank);
	          
	          imViewStart = (ImageView)findViewById(R.id.imViewStart);
	          imViewStart.setImageResource(R.drawable.press_start_for_countdown_spa);
	          imBtnPressToChange = (ImageButton)findViewById(R.id.imBtnPressToChange);
	          imBtnPressToChange.setImageResource(R.drawable.press_to_change_loc_spa);
		   }
	      
	      imBtnStart.setVisibility(View.INVISIBLE);
	      imBtnWarning.setVisibility(View.INVISIBLE);
	      imBtnPressToChange.setVisibility(View.INVISIBLE);
	      
	      
	      
	      imBtnReturn.setOnClickListener(this);
	    
	      imBtnWarning.setOnClickListener(this);

	      imBtnControlCenter.setOnClickListener(this);

	      imBtnStart.setOnClickListener(this);
	      imBtnPressToChange.setOnClickListener(this);
	      
	   	      
	      
	      locationId = (EditText)findViewById(R.id.locationId);
	      //locationId.setOnClickListener(this);

	      
	      //locationId.setInputType( InputType.TYPE_TEXT_VARIATION_URI );
	      
	      locationId.hasFocus();
	      

	     imm = (InputMethodManager)this.getSystemService(Service.INPUT_METHOD_SERVICE);
	     imm.showSoftInput(locationId, 0);
	     
	     locationId.setOnFocusChangeListener(new OnFocusChangeListener()
	      {
	         
	          public void onFocusChange(View v, boolean hasFocus) 
	          {
	              if (hasFocus==true)
	              {   
	            	  //locationId.setText("");
	                  
	              }
	          }
	      });
	     
	     locationId.setOnKeyListener(new OnKeyListener() {
				public boolean onKey(View v, int keyCode, KeyEvent event) {  
					if (keyCode == KeyEvent.KEYCODE_ENTER) {
	            	 //Calculate();
	            	 
	                   //  locationId.clearFocus();
	                     
	                    // InputMethodManager imm = (InputMethodManager)getSystemService(INPUT_METHOD_SERVICE);
	                     //imm.hideSoftInputFromWindow(locationId.getWindowToken(), 0);
	            	 
	            	 
	            	 if(locationId.getText().toString().isEmpty()) {
							
							
							Calculate();
							locationId.clearFocus();
							imm = (InputMethodManager)Enter_Location_Activity.this.getSystemService(Service.INPUT_METHOD_SERVICE);
							imm.showSoftInput(locationId, 0);
							
							
							
						} else {
							
						Calculate();
						locationId.clearFocus();
						
						InputMethodManager inputManager = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE); 
		    		    inputManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),      
		    		    InputMethodManager.HIDE_NOT_ALWAYS);
					
							
						}
	     			return true;					
					
				}

				return false;
								
			}
	  	});
	    

	}
	

    
	
	public void Calculate(){
		
		if(locationId.getText().toString().trim().equals("")) {
			
			locationId.hasFocus();
			imBtnStart.clearAnimation();
			imBtnStart.setVisibility(View.INVISIBLE);
			imBtnPressToChange.clearAnimation();
			imBtnPressToChange.setVisibility(View.INVISIBLE);
			
			InputMethodManager imm = (InputMethodManager)getSystemService(INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(locationId.getWindowToken(), 0); 
			
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
	
			
		}
		      

	    // InputMethodManager inputManager = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE); 
		 //inputManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),      
		 //InputMethodManager.HIDE_NOT_ALWAYS);
		
	}
	
	 
	  public void onClick(View v){
	  	if(v==imBtnReturn){

	  		finish();
	  	}
	  	
		if(v==imBtnControlCenter){
			
			Intent resultIntent = new Intent();
  			resultIntent.setClass(this,ZimekActivity.class);
  			setResult(Activity.RESULT_OK, resultIntent);
  			startActivity(resultIntent);
	  	}
		

		
		if(v==imBtnPressToChange){
			
			locationId.hasFocus();
			locationId.setText("");
			imBtnPressToChange.clearAnimation();
			imBtnPressToChange.setVisibility(View.INVISIBLE);
			
			imBtnStart.clearAnimation();
			imBtnStart.setVisibility(View.INVISIBLE);	
			
			imm = (InputMethodManager)this.getSystemService(Service.INPUT_METHOD_SERVICE);
			imm.showSoftInput(locationId, 0);	    

 	}
		
		
		if(v==imBtnStart){
			
			onDestroy();	
			
			mHandler.removeCallbacks(mUpdateUI);
			
			if (locationId.getText().toString().isEmpty()) {
				
				Calculate();
				
				
			} else {
			
			String mydate = java.text.DateFormat.getDateTimeInstance().format(Calendar.getInstance().getTime());
			 // Toast.makeText(this, "TIME:" + mydate, Toast.LENGTH_SHORT).show();
			
			 StringTokenizer tokens = new StringTokenizer(mydate, " ");
		     String Month = tokens.nextToken();
		     String Day = tokens.nextToken();
		     String Year = tokens.nextToken();
		     String Time = tokens.nextToken();
		     String amorpm = tokens.nextToken();
		     
		     //String myDate = String.format("%s %s %s", Month, Day, Year);
		     
		     Date date = new Date(System.currentTimeMillis());
		     SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yy");
		     String newDate = formatter.format(date);
		     
		     
		     //Toast.makeText(this, "Date :" + newDate, Toast.LENGTH_LONG).show();
		     
		     String myDate = newDate;
		     
		     StringTokenizer Tokens = new StringTokenizer(Time, ":");
		     String Hour = Tokens.nextToken();
		     String Minutes = Tokens.nextToken(); 
		     
		     String myTime = String.format("%s:%s %s", Hour, Minutes, amorpm);
		    // Toast.makeText(this, "Time :" + myTime, Toast.LENGTH_SHORT).show();
			
			String LocationId = locationId.getText().toString();
            commonState.LocationId = LocationId;
			
            // Since MistHour etc. are already set in commonState, no need to
            // get them from intent and pass again in intent.
	        
			Intent intent = new Intent();
			intent.setClass(this,Begin_Countdown_Activity.class);
            commonState.myDate = myDate;
            commonState.myTime = myTime;
			startActivity(intent);
			}
		}
		
		onDestroy();
		
		
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

//	  private void Cleanup()
//	  {    
	  	
//	      System.gc();
//	      Runtime.getRuntime().gc();  
//	  }
	
	
}
