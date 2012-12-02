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


public class DownloadReports_Activity extends Activity implements OnClickListener{
	
private ImageButton imBtnReturn;
private ImageButton imBtnWarning;
private ImageButton imBtnMGTControlCenter;
private ImageButton imBtnDownload;


private ImageView imViewDownloadReportsHead;
private ImageView imViewUSBIcon;
private ImageView imViewDownloadDesc;

public String language;
public CommonState commonState = null;
	
	


@Override
public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    requestWindowFeature(Window.FEATURE_NO_TITLE);
    getWindow().getDecorView().setSystemUiVisibility(View.STATUS_BAR_HIDDEN);
    getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, 
            WindowManager.LayoutParams.FLAG_FULLSCREEN);
    setContentView(R.layout.downloadreports_app);
    
    imViewUSBIcon = (ImageView)findViewById(R.id.imViewUSBIcon);
    commonState = (CommonState) getApplication();
    commonState.activity_name = "DownloadReports_Activity";
    
    
    
    
    language = commonState.language;
    
    if(language.equals("English")){
    	
    	imBtnReturn = (ImageButton)findViewById(R.id.imBtnReturn);
        
        imBtnWarning = (ImageButton)findViewById(R.id.imBtnWarning);    
        
        imViewDownloadReportsHead = (ImageView)findViewById(R.id.imViewDownloadReportsHead);
        
        imViewDownloadDesc = (ImageView)findViewById(R.id.imViewDownloadDesc);
  	     
        imBtnMGTControlCenter = (ImageButton)findViewById(R.id.imBtnMGTControlCenter);
        
        imBtnDownload = (ImageButton)findViewById(R.id.imBtnDownload);
        
        
   
 	   
    } 
    
    if(language.equals("Spanish")){
		   
        imBtnReturn = (ImageButton)findViewById(R.id.imBtnReturn);
        imBtnReturn.setImageResource(R.drawable.return_spa);
        
        imBtnWarning = (ImageButton)findViewById(R.id.imBtnWarning);
        imBtnWarning.setImageResource(R.drawable.warning_spa);
        
        imViewDownloadReportsHead = (ImageView)findViewById(R.id.imViewDownloadReportsHead);
        imViewDownloadReportsHead.setImageResource(R.drawable.download_reports_head_spa);
        
        imViewDownloadDesc = (ImageView)findViewById(R.id.imViewDownloadDesc);
        imViewDownloadDesc.setImageResource(R.drawable.connect_usb_spa);
  	     
        imBtnMGTControlCenter = (ImageButton)findViewById(R.id.imBtnMGTControlCenter);
        imBtnMGTControlCenter.setImageResource(R.drawable.control_center_mgt_button_spa);
        
        imBtnDownload = (ImageButton)findViewById(R.id.imBtnDownload);
        imBtnDownload.setImageResource(R.drawable.download_spa);
        
        
 	   
		   
	   }
    
    imBtnWarning.setVisibility(View.INVISIBLE);
    
    imBtnReturn.setOnClickListener(this);
    imBtnWarning.setOnClickListener(this);
    imBtnMGTControlCenter.setOnClickListener(this);
    imBtnDownload.setOnClickListener(this);

    
    Animation animation = new AlphaAnimation(1, 0); 
    animation.setDuration(800); 
    animation.setInterpolator(new LinearInterpolator()); 
    animation.setRepeatCount(Animation.INFINITE); 
    animation.setRepeatMode(Animation.REVERSE); 
    imBtnDownload.startAnimation(animation);
    
    
 
    
}




public void onClick(View v) {
	
	if(v==imBtnDownload){
		
		imBtnDownload.clearAnimation();
		
		Intent resultIntent = new Intent();
		resultIntent.setClass(this,DownloadingReports_Activity.class);
		startActivity(resultIntent);
		
		
		
		
		
	}
	
	if(v==imBtnReturn){
		
		finish();
		
	}
	
	if(v==imBtnMGTControlCenter){
		
			Intent resultIntent = new Intent();
			resultIntent.setClass(this,MGT_ControlCenter_Activity.class);
			startActivity(resultIntent);
		
		
		
	}
	
	
	
	
}

}
