package com.circuit.zimek;




import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.util.Log;


import com.SystemSettings_Report.SystemSettingsDatabaseHandler;
import com.SystemSettings_Report.SystemSettingsReports;


public class Begin_Countdown_Activity extends Activity {
	public CommonState commonState = null; 
	private static final boolean SHOW_DEBUG = true;
	String TAG = "Begin_Countdown_Activity";

	private CountDownTimer Countdown;
	private TextView EdTsec;	
	public TextView txtViewPause;
	
	private ImageButton imBtnStop;
	private ImageButton imBtnPause;
	private ImageButton imBtnWarning;
	
	private ImageView imViewAppBegHead;
	private ImageView imViewSec;
	private ImageView imViewEvac;
	
	public String Change;
	public String language;
	public int id;
	public int dummy;
	//public String mBeeper;
	public String pauseCause = "";
	
	public int Seconds_Left;

	public String formatTime(long millis) {
        String output = "00";
        long seconds = millis / 1000;
        
        seconds = seconds % 60;
        dummy = commonState.mService.sendZvacCommand("C1,H1,H2");

        String secondsD = String.valueOf(seconds);

        if (seconds < 10)
            secondsD = "0" + seconds;
    
        output = secondsD;
        return output;
    }
	
	
	
	
	public void onCreate(Bundle savedInstanceState) {
	      super.onCreate(savedInstanceState);
	      requestWindowFeature(Window.FEATURE_NO_TITLE);
	      getWindow().getDecorView().setSystemUiVisibility(View.STATUS_BAR_HIDDEN);
	       getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, 
	               WindowManager.LayoutParams.FLAG_FULLSCREEN);
	      setContentView(R.layout.begin_countdown);
	      
	      commonState = (CommonState) getApplication();	      
          commonState.activity_name = "Begin_Countdown_Activity";
	      mHandler.post(mUpdateUI);
	      
	      id = 1;
	      SystemSettingsDatabaseHandler db = new SystemSettingsDatabaseHandler(this);
	      SystemSettingsReports Results = db.getReport(id);
	     
	      commonState.mBeeper = Results.getBeeper();
	      dummy = commonState.mService.sendZvacCommand("S1,q2");

        // No need to read language from intent.
        if (commonState.language.equals("")) {
            commonState.language = "English";
        }
        language = commonState.language;
        
        imViewAppBegHead = (ImageView)findViewById(R.id.imViewAppBegHead);  
  	    imViewSec = (ImageView)findViewById(R.id.imViewSec);
  	    imViewEvac = (ImageView)findViewById(R.id.imViewEvac);
        imBtnStop = (ImageButton)findViewById(R.id.imBtnStop);
        imBtnPause = (ImageButton)findViewById(R.id.imBtnPause);
        imBtnWarning = (ImageButton)findViewById(R.id.imBtnWarning);
        
	    if(language.equals("Spanish")){ 
	    	  imViewAppBegHead.setImageResource(R.drawable.application_begins_in_spa);   
	    	  imViewSec.setImageResource(R.drawable.seconds_spa);
	    	  imViewEvac.setImageResource(R.drawable.evacuate_area_now_spa);
	          imBtnStop.setImageResource(R.drawable.stop_spa);         
	          imBtnPause.setImageResource(R.drawable.pause_spa);
	    	  imBtnWarning.setImageResource(R.drawable.warning_spa);
		}
	    imBtnWarning.setVisibility(View.INVISIBLE);
      
	      txtViewPause = (TextView) this.findViewById(R.id.txtViewPause);
	      txtViewPause.setText("PAUSE");
	      EdTsec = (TextView) this.findViewById(R.id.EdTsec);
	      
	}
	
	@Override
	public void onResume() {
	   	super.onResume();
	   	if (commonState.beginAppTimer == 0) {
	        Seconds_Left = 3000 + 1000;
        } else {
            Seconds_Left = commonState.beginAppTimer;
        }
	      
	    CountDown();  	
	}
	
	protected void onNewIntent(Intent intent) {
		if (SHOW_DEBUG) {
            Log.d(TAG, "onNewIntent");
        }
        if (commonState.beginAppTimer == 0) {
	        Seconds_Left = 3000 + 1000;
        } else {
            Seconds_Left = commonState.beginAppTimer;
        }  
	    CountDown(); 
    };
	
    
	
	public void CountDown(){
        BlinkNextButtonImage();
			
        Countdown = new CountDownTimer(Seconds_Left, 1000) {
  
            @Override
            public void onTick(long leftTimeInMilliseconds) {
                Seconds_Left = (int) leftTimeInMilliseconds;
                commonState.beginAppTimer = Seconds_Left;
                EdTsec.setText(formatTime(leftTimeInMilliseconds)); 
            }
            public void onFinish() { 
                mHandler.removeCallbacks(mUpdateUI); 
                EdTsec.setText("00");
                EdTsec.setVisibility(View.VISIBLE); 
                String myDate ;
                String myTime ;
                String LocationId ;
                String MistHour ;
                String MistMin ;
                String DwellHour ;
                String DwellMin ;
                String ZvacHour ;
                String ZvacMin ;
                String typeApplication ;
                String typeConfigure ;
                try {
					myDate = getIntent().getStringExtra("Date");
					myTime = getIntent().getStringExtra("Time");
					LocationId = getIntent().getStringExtra("locationId");
					MistHour = getIntent().getStringExtra("MistHour");
			        MistMin = getIntent().getStringExtra("MistMin");
			        DwellHour = getIntent().getStringExtra("DwellHour");
			        DwellMin = getIntent().getStringExtra("DwellMin");
			        ZvacHour = getIntent().getStringExtra("ZvacHour");
			        ZvacMin = getIntent().getStringExtra("ZvacMin");
			        typeApplication = getIntent().getStringExtra("typeApplication");
			        typeConfigure = getIntent().getStringExtra("typeConfigure");
                    if (myDate != null) commonState.myDate = myDate;
                    if (myTime != null) commonState.myTime = myTime;
                    if (MistHour != null) commonState.MistHour = MistHour ;
                    if (MistMin != null) commonState.MistMin = MistMin ;
                    if (DwellHour != null) commonState.DwellHour = DwellHour ;
                    if (DwellMin != null) commonState.DwellMin = DwellMin ;
                    if (ZvacHour != null) commonState.ZvacHour = ZvacHour ;
                    if (ZvacMin != null) commonState.ZvacMin = ZvacMin ;
                    if (LocationId != null) commonState.LocationId = LocationId;
                    if (typeApplication != null) commonState.typeApplication = typeApplication;
                    if (typeConfigure != null) commonState.typeConfigure = typeConfigure;
                } catch (Exception e){
                    myDate = commonState.myDate;
                    myTime = commonState.myTime;
                    MistHour = commonState.MistHour ;
                    MistMin = commonState.MistMin ;
                    DwellHour = commonState.DwellHour ;
                    DwellMin = commonState.DwellMin ;
                    ZvacHour = commonState.ZvacHour ;
                    ZvacMin = commonState.ZvacMin ;
                    LocationId = commonState.LocationId;
			        typeApplication = commonState.typeApplication;
			        typeConfigure = commonState.typeConfigure;
                }
                myDate = commonState.myDate;
                myTime = commonState.myTime;
                MistHour = commonState.MistHour ;
                MistMin = commonState.MistMin ;
                DwellHour = commonState.DwellHour ;
                DwellMin = commonState.DwellMin ;
                ZvacHour = commonState.ZvacHour ;
                ZvacMin = commonState.ZvacMin ;
                LocationId = commonState.LocationId;
                typeApplication = commonState.typeApplication;
                typeConfigure = commonState.typeConfigure;
                
			        
			        int MistHour_Length = MistHour.length();
			        
			        if(MistHour_Length <= 1 ){
			        	MistHour = ("0" + MistHour).trim();
			        		
			        }
			        
			        int MistMin_Length = MistMin.length();
			        
			        if(MistMin_Length <= 1 ){
			        	MistMin = ("0" + MistMin).trim();
			        		
			        }
			        
			        int DwellHour_Length = DwellHour.length();
			        
			        if(DwellHour_Length <= 1 ){
			        	DwellHour = ("0" + DwellHour).trim();
			        		
			        }
			        
			        int DwellMin_Length = DwellMin.length();
			        
			        if(DwellMin_Length <= 1 ){
			        	DwellMin = ("0" + DwellMin).trim();
			        		
			        }
			        
			        int ZvacHour_Length = ZvacHour.length();
			        
			        if(ZvacHour_Length <= 1 ){
			        	ZvacHour = ("0" + ZvacHour).trim();
			        		
			        }
			        
			        int ZvacMin_Length = ZvacMin.length();
			        
			        if(ZvacMin_Length <= 1 ){
			        	ZvacMin = ("0" + ZvacMin).trim();
			        		
			        }
			        
			        
			        Intent intent = new Intent();
					intent.setClass(Begin_Countdown_Activity.this,Application_Progress_Activity.class);
					intent.putExtra("Date", myDate);
					intent.putExtra("Time", myTime);
					intent.putExtra("locationId", LocationId);
					intent.putExtra("MistHour", MistHour);
					intent.putExtra("MistMin", MistMin);
					intent.putExtra("DwellHour", DwellHour);
					intent.putExtra("DwellMin", DwellMin);
					intent.putExtra("ZvacHour", ZvacHour);
					intent.putExtra("ZvacMin", ZvacMin);
					intent.putExtra("typeApplication", typeApplication);
					intent.putExtra("typeConfigure", typeConfigure);
					dummy = commonState.mService.sendZvacCommand("B0");
					startActivity(intent);
					onDestroy();
				  
					}
			}; 
			Countdown.start();	
				
				
			imBtnPause.setOnClickListener(new OnClickListener() {
					
					
		    public void onClick(View v) {
				    	
				    Change = txtViewPause.getText().toString(); 	     
				     String seconds_left = EdTsec.getText().toString();
				     
				     
				     Seconds_Left = (int) Long.parseLong(seconds_left); 
				     
				     if(Change.equals("PAUSE")){
				    	 
				    	 if (language.equals("English")){
				    		 dummy = commonState.mService.sendZvacCommand("C0,Q1");
				    		 
				    		 imBtnPause.setImageResource(R.drawable.resume_eng);	 
				    		 
				    		 
				    	 }
				    
				    	 if (language.equals("Spanish")){
				    		 
				    		 imBtnPause.setImageResource(R.drawable.resume_spa);	 
				    		 
				    		 
				    	 }
				    	 dummy= commonState.mService.sendZvacCommand("q1");
				     
				     Change = "Resume";

			    	 
				     Countdown.cancel();
				     txtViewPause.setText("RESUME");
				    
				     }
				     
				     if(Change.equals("RESUME")) {
				    	 
				    	// onDestroy();
				    	 
				    	 if (language.equals("English")){
				    		 
				    		 imBtnPause.setImageResource(R.drawable.pause_eng);	 
				    		 
				    		 
				    	 }
				    
				    	 if (language.equals("Spanish")){
				    		 
				    		 imBtnPause.setImageResource(R.drawable.pause_spa);	 
				    		 
				    		 
				    	 }

				    	 
				        Seconds_Left = Seconds_Left * 1000  + 1000;
				               
				        
				    	CountDown();
				    	txtViewPause.setText("PAUSE");
				    	dummy= commonState.mService.sendZvacCommand("q2");
				       }         
				        
				       }
				    
				   });
				
				imBtnStop.setOnClickListener(new OnClickListener() {
					
					
				    public void onClick(View v) {
				        
				     Countdown.cancel();
				     EdTsec.setVisibility(View.VISIBLE);
				     imViewEvac.clearAnimation();

				     
				     	Intent resultIntent = new Intent();
			  			resultIntent.setClass(Begin_Countdown_Activity.this,Home_Screen_Activity.class);
			  			setResult(Activity.RESULT_OK, resultIntent); 
			  			dummy= commonState.mService.sendZvacCommand("C0,S0,Q2,H0");
			  			startActivity(resultIntent);
										     
				          
				       }
				    
				   });
	
			}
	
	
	
	public void BlinkNextButtonImage(){
		  
	    Animation animation = new AlphaAnimation(1, 0); 
	    animation.setDuration(800); 
	    animation.setInterpolator(new LinearInterpolator()); 
	    animation.setRepeatCount(Animation.INFINITE); 
	    animation.setRepeatMode(Animation.REVERSE); 
	    imViewEvac.startAnimation(animation);
	  
	  
  }
	
	 public void onDestroy()
	  {   
	   //   Cleanup();
	     // super.onDestroy();
		 commonState.beginAppTimer = 0;
	      
	      super.onDestroy();
	      mHandler.removeCallbacks(mUpdateUI);
		  
		 shouldContinue = false;
	  }
	 
	 private boolean shouldContinue = true;
		private final Handler mHandler = new Handler();
		private final Runnable mUpdateUI = new Runnable() {
		    public void run() {
		      if (shouldContinue){
		    	  
		    	if (commonState.lastCommTime > 2) {
		    		Change = txtViewPause.getText().toString();
		    		if (Change.equals("PAUSE")) {
		    			imBtnPause.performClick();
		    			pauseCause = "powerInterruption";
		    		}
		    	} else {
		    		if (pauseCause.equals("powerInterruption")) {
		    			Change = txtViewPause.getText().toString();
		    			if (Change.equals("RESUME")) {
		    				pauseCause = "";
		    				imBtnPause.performClick();
		    			}
		    		}	
		    	}
		    	if (commonState.lastCommand != 0) {
                	int lastCommand = commonState.lastCommand;
                	commonState.lastCommand = 0;
                	Change = txtViewPause.getText().toString();
                	
                	switch (lastCommand){
                	case 4: // stop
                		imBtnStop.performClick();
                		mHandler.removeCallbacks(mUpdateUI);
                		shouldContinue = false;
                		onDestroy();
                		break;
                	case 2: //play
                		if (Change.equals("RESUME")) {
                			imBtnPause.performClick();
                		}
                		break;
                	case 1: //pause
                		if (Change.equals("PAUSE")) {
                			imBtnPause.performClick();
                		}
                        break;
                    default:
                        break;
                	}
   
                }
		    		
		        mHandler.postDelayed(mUpdateUI, 500); // 1 second
		        
		    	}
		    }
		    };
	 
		    
		    

	 
		
	}
	      
	      
	      
