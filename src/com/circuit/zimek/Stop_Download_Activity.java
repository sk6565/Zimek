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
import android.widget.ProgressBar;
import android.widget.TextView;



public class Stop_Download_Activity extends Activity implements OnClickListener{
	
private ImageButton imBtnYes;
private ImageButton imBtnNo;

private ImageView imViewWarning;
private ImageView imViewEndDownload;

public String language;
public int downloadprogress;
public long TotalDownloadTime;
public float downloaded;
public CommonState commonState = null;



@Override
public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    requestWindowFeature(Window.FEATURE_NO_TITLE);
    getWindow().getDecorView().setSystemUiVisibility(View.STATUS_BAR_HIDDEN);
    getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, 
            WindowManager.LayoutParams.FLAG_FULLSCREEN);
    setContentView(R.layout.stopdownload_app);
    
    commonState = (CommonState) getApplication();
    commonState.activity_name = "Stop_Download_Activity";
    imViewWarning = (ImageView)findViewById(R.id.imViewWarning);
    
    try {
        downloadprogress = getIntent().getIntExtra("downloadprogress", downloadprogress);
        TotalDownloadTime = getIntent().getLongExtra("TotalDownloadTime", TotalDownloadTime);
        downloaded = getIntent().getFloatExtra("downloaded", downloaded);
        commonState.downloadprogress = downloadprogress;
        commonState.TotalDownloadTime = TotalDownloadTime;
        commonState.downloaded = downloaded;
    } catch (Exception e) {
        downloadprogress = commonState.downloadprogress;
        TotalDownloadTime = commonState.TotalDownloadTime;
        downloaded = commonState.downloaded;
    }

    language = commonState.language;
    
    
    if(language.equals("English")){
    	
    	imBtnYes = (ImageButton)findViewById(R.id.imBtnYes);
        
        imBtnNo = (ImageButton)findViewById(R.id.imBtnNo);   
        
        imViewEndDownload = (ImageView)findViewById(R.id.imViewEndDownload);
  	     
 	   
    } 
    
    if(language.equals("Spanish")){
		   
        imBtnYes = (ImageButton)findViewById(R.id.imBtnYes);
        imBtnYes.setImageResource(R.drawable.yes_downloading_spa);
        
        imBtnNo = (ImageButton)findViewById(R.id.imBtnNo);
        imBtnNo.setImageResource(R.drawable.no_downloading_spa);
        
        imViewEndDownload = (ImageView)findViewById(R.id.imViewEndDownload);
        imViewEndDownload.setImageResource(R.drawable.stop_download_desc_spa);
        
   
	   }
    
   imBtnYes.setOnClickListener(this);
   imBtnNo.setOnClickListener(this);


    
}


public void onClick(View v) {
	
	if(v==imBtnYes){
		
		Intent resultIntent = new Intent();
		//resultIntent.setClass(this,MGT_ControlCenter_Activity.class);
		resultIntent.setClass(this,Reports_Menu_Activity.class);
		startActivity(resultIntent);
		
	}
	
	if(v==imBtnNo){
		
		Intent resultIntent = new Intent();
		resultIntent.setClass(this,ZimekActivity.class);
		resultIntent.putExtra("downloadprogress", downloadprogress);
		resultIntent.putExtra("TotalDownloadTime", TotalDownloadTime);
		
		setResult(Activity.RESULT_OK, resultIntent); 
	finish();
		

		
	}
	
	
}
}
