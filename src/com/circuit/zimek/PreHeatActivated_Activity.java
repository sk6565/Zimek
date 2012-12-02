package com.circuit.zimek;

import java.util.StringTokenizer;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
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


public class PreHeatActivated_Activity extends Activity implements OnClickListener{
	 public CommonState commonState = null;
	 
	 public String language;
	 public String Change;
	 public int preheatprogress = 1000;
	 private CountDownTimer Countdown;
	 
	 private ImageButton imBtnWarning;
	 private ImageButton imBtnPreHeatStop;
	 private ImageButton imBtnPreHeatPause;
	 private ImageButton imBtnNext;
	 
	 private ImageView imViewPreHeatLiquidHead;
	 private ImageView imViewPreHeatIcon;
	 private ImageView imViewTimeRemaining;
	 private ImageView imViewInProgress;
	 private TextView txtViewPreHeatRemaining;
	 public TextView txtViewPause;
	 
	 public ProgressBar pBarPreHeat;
	 //public long TotalPreHeatTime = 1800000; 
	 
	 public long TotalPreHeatTime = 1801000;
	 
 public String formatTime(long millis) {
    	
    	 
         String output = "00:00:00";
         long seconds = millis / 1000;
         long minutes = (millis / (1000*60));
         
         long hours 	= (millis / (1000*60*60));

         seconds = seconds % 60;
         minutes = minutes % 60;
         hours 	= hours % 24;

         String secondsD = String.valueOf(seconds);
         String minutesD = String.valueOf(minutes);
         String hoursD = String.valueOf(hours);

         if (seconds < 10)
           secondsD = "0" + seconds;
         if (minutes < 10)
           minutesD = "0" + minutes;
         if(hours <= 1)
         	hoursD = "0" + hours;

         output = hoursD + ":" + minutesD + ":" + secondsD;
         return output;
       }
	 
	 
	


	
  @Override
  public void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      requestWindowFeature(Window.FEATURE_NO_TITLE);
      getWindow().getDecorView().setSystemUiVisibility(View.STATUS_BAR_HIDDEN);
      getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, 
              WindowManager.LayoutParams.FLAG_FULLSCREEN);
      setContentView(R.layout.preheatactivated_app);
      commonState = (CommonState) getApplication();
      commonState.activity_name = "PreHeatActivated_Activity";
      commonState.mService.sendZvacCommand("M127,H1,S1,C1,F0");
      
      language = commonState.language;
      
      imViewPreHeatIcon = (ImageView)findViewById(R.id.imViewPreHeatIcon);
      txtViewPreHeatRemaining = (TextView)findViewById(R.id.txtViewPreHeatRemaining);
      
      txtViewPause = (TextView) this.findViewById(R.id.txtViewPause);
      txtViewPause.setText("PAUSE");
      
      pBarPreHeat = (ProgressBar) findViewById(R.id.pBarPreHeat);
	  pBarPreHeat.setProgress(0);
	  pBarPreHeat.setMax((int) TotalPreHeatTime);
      
      
      if(language.equals("English")){
          
    	  imBtnPreHeatStop = (ImageButton)findViewById(R.id.imBtnPreHeatStop);
          
    	  imBtnPreHeatPause = (ImageButton)findViewById(R.id.imBtnPreHeatPause);
    	  
    	  imBtnNext = (ImageButton)findViewById(R.id.imBtnNext);
          
          imBtnWarning = (ImageButton)findViewById(R.id.imBtnWarning);
          
          
          imViewPreHeatLiquidHead = (ImageView)findViewById(R.id.imViewPreHeatLiquidHead);
          
          imViewInProgress = (ImageView)findViewById(R.id.imViewInProgress);
          
          imViewTimeRemaining = (ImageView)findViewById(R.id.imViewTimeRemaining);
          
   	   
      } 
      
      if(language.equals("Spanish")){
		   
    	  imBtnPreHeatStop = (ImageButton)findViewById(R.id.imBtnPreHeatStop);
    	  imBtnPreHeatStop.setImageResource(R.drawable.stop_preheat_spa);
    	  
    	  imBtnPreHeatPause = (ImageButton)findViewById(R.id.imBtnPreHeatPause);
    	  imBtnPreHeatPause.setImageResource(R.drawable.pause_preheat_spa);
    	  
    	  imBtnNext = (ImageButton)findViewById(R.id.imBtnNext);
          imBtnNext.setImageResource(R.drawable.next_spa);
          
          imBtnWarning = (ImageButton)findViewById(R.id.imBtnWarning);
          imBtnWarning.setImageResource(R.drawable.warning_spa);
          
          imViewInProgress = (ImageView)findViewById(R.id.imViewInProgress);
          imViewInProgress.setImageResource(R.drawable.in_progress_spa);
         
          
          imViewPreHeatLiquidHead = (ImageView)findViewById(R.id.imViewPreHeatLiquidHead);
          imViewPreHeatLiquidHead.setImageResource(R.drawable.preheat_liquid_head_spa);
          
          imViewTimeRemaining = (ImageView)findViewById(R.id.imViewTimeRemaining);
          imViewTimeRemaining.setImageResource(R.drawable.time_remaining_spa);
   	   
		   
	   }
      
      imBtnWarning.setVisibility(View.INVISIBLE);
      imBtnNext.setVisibility(View.INVISIBLE);
      
      
      imBtnWarning.setOnClickListener(this);
      imBtnNext.setOnClickListener(this);
      imBtnPreHeatStop.setOnClickListener(this);
      imBtnPreHeatPause.setOnClickListener(this);
      
      CountDown();
      
      
      
  }
  
  
  public void CountDown(){
		
			
		 Countdown = new CountDownTimer(TotalPreHeatTime, 1000) {

                @Override

                public void onTick(long leftTimeInMilliseconds) {
              	  TotalPreHeatTime = leftTimeInMilliseconds;
              	  				 	 
					txtViewPreHeatRemaining.setText(formatTime(leftTimeInMilliseconds));
					preheatprogress = preheatprogress + 1000;
					pBarPreHeat.setProgress(preheatprogress);

			 }
				public void onFinish() {
					
					 	Animation animation = new AlphaAnimation(1, 0); 
					    animation.setDuration(800); 
					    animation.setInterpolator(new LinearInterpolator()); 
					    animation.setRepeatCount(Animation.INFINITE); 
					    animation.setRepeatMode(Animation.REVERSE); 
					    imBtnNext.startAnimation(animation);
					
					if(language.equals("English")){
						
						imViewInProgress.setImageResource(R.drawable.complete_eng);
						
					}
					
					if(language.equals("Spanish")){
						
						imViewInProgress.setImageResource(R.drawable.complete_spa);
						
					}
					
					
					
					pBarPreHeat.setProgress(2000000);
					txtViewPreHeatRemaining.setText("00:00:00");
					imBtnPreHeatStop.setVisibility(View.INVISIBLE);
					imBtnPreHeatPause.setVisibility(View.INVISIBLE);
					imBtnNext.setVisibility(View.VISIBLE);
						        
			}
			}; Countdown.start();	
				
				
					imBtnPreHeatPause.setOnClickListener(new OnClickListener() {
					
					
				    public void onClick(View v) {
				    	
				    Change = txtViewPause.getText().toString(); 	     
				    String time_left = txtViewPreHeatRemaining.getText().toString();
				     
				     
				     StringTokenizer tokens = new StringTokenizer(time_left, ":");
				     String Hour = tokens.nextToken();
				     String Minutes = tokens.nextToken();
				     String Seconds = tokens.nextToken();
				     
				     long PreHeathours = Integer.parseInt(Hour);
				     long PreHeatminute = Integer.parseInt(Minutes);
				     long PreHeatseconds = Integer.parseInt(Seconds);
				     
				     if(PreHeathours < 10){
							Hour = Hour.replaceFirst("^0+(?!$)","");
							PreHeathours = Integer.parseInt(Hour);		
						}
				     
						
						if(PreHeatminute < 10){
							Minutes = Minutes.replaceFirst("^0+(?!$)","");
							PreHeatminute = Integer.parseInt(Minutes);		
						}
						
						if(PreHeatseconds < 10){
							Seconds = Seconds.replaceFirst("^0+(?!$)","");
							PreHeatseconds = Integer.parseInt(Seconds);		
						}
						
						
						PreHeatseconds = PreHeatseconds * 1000;
						PreHeatminute = PreHeatminute * 60 * 1000;
						PreHeathours = PreHeathours * 60 * 60 * 1000;
						
					   
					    TotalPreHeatTime = PreHeatminute + PreHeathours + PreHeatseconds;
				     
				     if(Change.equals("PAUSE")){
				    	 commonState.mService.sendZvacCommand("M0,H0,C0,S0,F0");
				    	 if (language.equals("English")){
				    		 imBtnPreHeatPause.setImageResource(R.drawable.resume_preheat_eng);	 
				    	 }
				    
				    	 if (language.equals("Spanish")){
				    		 imBtnPreHeatPause.setImageResource(R.drawable.resume_preheat_spa);	 
				    	 }
				    	
				     
				         Change = "Resume";

			    	 
				         Countdown.cancel();
				         txtViewPause.setText("RESUME");
				     }
				     
				     if(Change.equals("RESUME")) {
				    	 
				    	// onDestroy();
				    	 commonState.mService.sendZvacCommand("M127,H1,C1,S1,F0");
				    	 if (language.equals("English")){
				    		 imBtnPreHeatPause.setImageResource(R.drawable.pause_preheat_eng);	 
				    	 }
				    
				    	 if (language.equals("Spanish")){
				    		 imBtnPreHeatPause.setImageResource(R.drawable.pause_preheat_spa);	 
				    	 }
				        
				    	CountDown();
				    	txtViewPause.setText("PAUSE");
				      }         
				        
				       }
				    
				   });
				
				imBtnPreHeatStop.setOnClickListener(new OnClickListener() {
					
					
				    public void onClick(View v) {
				        
				     Countdown.cancel();
				     //EdTsec.setVisibility(View.VISIBLE);
				     //imViewEvac.clearAnimation();

				     
				     	Intent resultIntent = new Intent();
			  			resultIntent.setClass(PreHeatActivated_Activity.this,StopPreHeat_Activity.class);
			  			resultIntent.putExtra("TotalPreHeatTime", TotalPreHeatTime);
			  			resultIntent.putExtra("preheatprogress", preheatprogress);
			  			startActivityForResult(resultIntent, 1);
										     
				          
				       }
				    
				   });
	
			}
	
  
  
  
  public void onClick(View v) {
	  
	  if(v==imBtnNext){
		  
		  imBtnNext.clearAnimation();
		  
		  	Intent resultIntent = new Intent();
			resultIntent.setClass(this,ZimekActivity.class);
			setResult(Activity.RESULT_OK, resultIntent);
			startActivity(resultIntent);
  
		  
	  }
	  
  }
  
  @Override
  public void onActivityResult(int requestCode, int resultCode, Intent data) {
	   super.onActivityResult(requestCode, resultCode, data);
	   
	   
	  // Toast.makeText(Home_Screen_Activity.this, "Result OK: Home Screen DATA" + language, Toast.LENGTH_LONG).show();
	   
	   switch(requestCode) {
	     case 1: 
	    	 
        if (resultCode == RESULT_OK) {
       	TotalPreHeatTime = data.getLongExtra("TotalPreHeatTime", TotalPreHeatTime);
       	txtViewPreHeatRemaining.setText(formatTime(TotalPreHeatTime));

       	 CountDown();
       	
       	 //Toast.makeText(Home_Screen_Activity.this, "Result OK: Home Screen" + data, Toast.LENGTH_LONG).show();
       	 
       	 
		      
		       
		       break;
        }
	   }
   }
 
  
  
  
  
}
