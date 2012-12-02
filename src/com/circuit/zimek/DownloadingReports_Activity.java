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



public class DownloadingReports_Activity extends Activity implements OnClickListener{
	
private ImageButton imBtnNext;
private ImageButton imBtnWarning;
private ImageButton imBtnStop;

private CountDownTimer Countdown;
public int downloadprogress = 1;

private ImageView imViewDownloadReportsHead;
private ImageView imViewDownloadingIcon;
private ImageView imViewDownloadingDesc;

private TextView txtViewDownload;
private ProgressBar pBarDownload;
public long TotalDownloadTime = 15000;
public float TotalDownload = 15;
public float downloaded = 0;

public TextView txtViewPause;

public String language;
public CommonState commonState = null;

	
	


@Override
public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    requestWindowFeature(Window.FEATURE_NO_TITLE);
    getWindow().getDecorView().setSystemUiVisibility(View.STATUS_BAR_HIDDEN);
    getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, 
            WindowManager.LayoutParams.FLAG_FULLSCREEN);
    setContentView(R.layout.downloadingreports_app);
    
    commonState = (CommonState) getApplication();
    commonState.activity_name = "DownloadingReports_Activity";

    imViewDownloadingIcon = (ImageView)findViewById(R.id.imViewDownloadingIcon);
    txtViewDownload = (TextView)findViewById(R.id.txtViewDownload);
    txtViewDownload.setText("0%");
    
    txtViewPause = (TextView) this.findViewById(R.id.txtViewPause);
    txtViewPause.setText("PAUSE");
    
    pBarDownload = (ProgressBar) findViewById(R.id.pBarDownload);
	  pBarDownload.setProgress(0);
	  pBarDownload.setMax(15);
    
    
    language = commonState.language;
    
    if(language.equals("English")){
    	
    	imBtnNext = (ImageButton)findViewById(R.id.imBtnNext);
        
        imBtnWarning = (ImageButton)findViewById(R.id.imBtnWarning);   
        
        imBtnStop = (ImageButton)findViewById(R.id.imBtnStop); 
        
        imViewDownloadReportsHead = (ImageView)findViewById(R.id.imViewDownloadReportsHead);
        
        imViewDownloadingDesc = (ImageView)findViewById(R.id.imViewDownloadingDesc);
  	     
 	   
    } 
    
    if(language.equals("Spanish")){
		   
        imBtnNext = (ImageButton)findViewById(R.id.imBtnNext);
        imBtnNext.setImageResource(R.drawable.next_spa);
        
        imBtnWarning = (ImageButton)findViewById(R.id.imBtnWarning);
        imBtnWarning.setImageResource(R.drawable.warning_spa);
        
        imViewDownloadReportsHead = (ImageView)findViewById(R.id.imViewDownloadReportsHead);
        imViewDownloadReportsHead.setImageResource(R.drawable.download_reports_head_spa);
        
        imBtnStop = (ImageButton)findViewById(R.id.imBtnStop);
        imBtnStop.setImageResource(R.drawable.stop_download_spa);
  	     
        imViewDownloadingDesc = (ImageView)findViewById(R.id.imViewDownloadingDesc);
        imViewDownloadingDesc.setImageResource(R.drawable.downloading_reports_spa);

	   
		   
	   }
    
    imBtnWarning.setVisibility(View.INVISIBLE);
    imBtnNext.setVisibility(View.INVISIBLE);
    
    imBtnNext.setOnClickListener(this);
    imBtnWarning.setOnClickListener(this);
    imBtnStop.setOnClickListener(this);
    
    CountDown();

    
}



public void CountDown(){
	
	
	 Countdown = new CountDownTimer(TotalDownloadTime, 1000) {

           @Override

           public void onTick(long leftTimeInMilliseconds) {
         	  TotalDownloadTime = leftTimeInMilliseconds;
         	  				 	 
				//txtViewPreHeatRemaining.setText(formatTime(leftTimeInMilliseconds));
				downloadprogress = downloadprogress + 1;
				pBarDownload.setProgress(downloadprogress);
				
				downloaded = downloaded + 1;
				
				float percent = (100 * downloaded) / TotalDownload;
				
				txtViewDownload.setText(String.format("%.0f%%",percent));

		 }
			public void onFinish() {
				
				 	Animation animation = new AlphaAnimation(1, 0); 
				    animation.setDuration(800); 
				    animation.setInterpolator(new LinearInterpolator()); 
				    animation.setRepeatCount(Animation.INFINITE); 
				    animation.setRepeatMode(Animation.REVERSE); 
				    imBtnNext.startAnimation(animation);
			
				pBarDownload.setProgress(15);
				txtViewDownload.setText("100%");
				imBtnStop.setVisibility(View.INVISIBLE);
				imBtnNext.setVisibility(View.VISIBLE);
				if (language.equals("Spanish")) {
					imViewDownloadingDesc.setImageResource(R.drawable.reports_downloaded_spa);	
				} else {
					imViewDownloadingDesc.setImageResource(R.drawable.reports_downloaded_eng);	
				}
					        
		}
		}; Countdown.start();	
			
			
			imBtnStop.setOnClickListener(new OnClickListener() {
				
				
			    public void onClick(View v) {
			        
			     Countdown.cancel();

			     
			     	Intent resultIntent = new Intent();
		  			resultIntent.setClass(DownloadingReports_Activity.this,Stop_Download_Activity.class);
		  			resultIntent.putExtra("TotalDownloadTime", TotalDownloadTime);
		  			resultIntent.putExtra("downloadprogress", downloadprogress);
		  			resultIntent.putExtra("downloaded", downloaded);
		  			startActivityForResult(resultIntent, 1);
									     
			          
			       }
			    
			   });

		}



@Override
public void onActivityResult(int requestCode, int resultCode, Intent data) {
	   super.onActivityResult(requestCode, resultCode, data);
	   
	  // Toast.makeText(Home_Screen_Activity.this, "Result OK: Home Screen DATA" + language, Toast.LENGTH_LONG).show();
	   
	   switch(requestCode) {
	     case 1: 
	    	 
      if (resultCode == RESULT_OK) {
     	TotalDownloadTime = data.getLongExtra("TotalDownloadTime", TotalDownloadTime);
     	downloadprogress = data.getIntExtra("downloadprogress", downloadprogress);
     	downloaded = data.getFloatExtra("downloaded", downloaded);
     	//txtViewDownload.setText();

     	 CountDown();
     	
     	 //Toast.makeText(Home_Screen_Activity.this, "Result OK: Home Screen" + data, Toast.LENGTH_LONG).show();
     	 
     	 
		      
		       
		       break;
      }
	   }
 }






public void onClick(View v) {
	
	if(v==imBtnNext){
		
		Intent resultIntent = new Intent();
		resultIntent.setClass(this,MGT_ControlCenter_Activity.class);
		startActivity(resultIntent);
		
	}
	
	
}





}
