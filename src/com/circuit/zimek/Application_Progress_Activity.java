package com.circuit.zimek;

import java.util.StringTokenizer;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.IBinder;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.TSS_Report.DatabaseHandler;

public class Application_Progress_Activity extends Activity implements OnClickListener  {
    public CommonState commonState = null; 
    
    public ZComm_Service mService;
    public boolean mBound = false;
    
    public ServiceConnection mConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName className,IBinder service){
            commonState.mService = ((ZComm_Service.MyBinder) service).getService();
            commonState.mBound = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName className) {
            commonState.mService = null;
            commonState.mBound = false;
        }
    };    
    
    private void sendData(String str) {
        if (commonState.mBound) {
            int num = commonState.mService.sendZvacCommand(str);
            //String zvacStatusStr = commonState.mService.readCompStatus();
        } else {
            Intent intent = new Intent(Application_Progress_Activity.this, ZComm_Service.class);
            bindService(intent, mConnection, Context.BIND_AUTO_CREATE);
        }
    }
    private TextView txtViewId;
    private TextView txtViewJobNo;
    private TextView txtViewTotalCounter;
    private TextView txtViewMistCounter;
    private TextView txtViewDwellCounter;
    private TextView txtViewZvacCounter;
    
    public long MISTTIME;
    public long DWELLTIME;
    public long ZVACTIME;
    
    public String TotalMistReport;
    public String TotalDwellReport;
    public String TotalZvacReport;
    public String language;

    public long totalMistTime;    // amount of MistTime so far
    public long totalDwellTime;   // amount of DwellTime so far
    public long totalZvacTime;    // amount of ZvacTime so far
    public long totalRunTime;     // amount RunTime so far

    public long remainingMistTime ;    // amount of MistTime so far
    public long remainingDwellTime ;   // amount of DwellTime so far
    public long remainingZvacTime ;    // amount of ZvacTime so far
    public long remainingRunTime ;     // amount RunTime so far

    public long millisNow; // totalRunTime - millisUntilFinished

    public String MistHour;
    public String MistMin;
    public String DwellHour;
    public String DwellMin;
    public String ZvacHour;
    public String ZvacMin;
    public String LocationId;
    public String AppResult = "";
    public int pass = 0;
    public long DwellCount = 0;
    public long ZvacCount = 0;
    public String MistCountTotal;
    public String DwellCountTotal;
    public String ZvacCountTotal;
    public String ViewReports;
    //public DatabaseHandler db;
    
    public int progressMist = 0;
    public int progressDwell = 0;
    public int progressZvac = 0;
    public int progressTotal = 0;
    
    
    private ImageButton imBtnStopApp;
    private ImageButton imBtnPauseApp;
    
    private ImageView imViewAppProgressHead;
    private ImageView imViewLocationId;
    
    
    private ImageView imViewMist;
    private ImageView imViewDwell;
    private ImageView imViewZvac;
    private ImageView imViewTotalTime;
    

    private ImageView imViewMistText;
    private ImageView imViewDwellText;
    private ImageView imViewZvacText;
    private ImageView imViewTotalText;
    
    private ImageView imViewMistIcon;
    private ImageView imViewDwellIcon;
    private ImageView imViewZvacIcon;
    
    
    private CountDownTimer counter;
    
    private EditText EdTChange;
    public String Change;
    public String jobNo;
    
    public ProgressBar pBarMist;
    public ProgressBar pBarDwell;
    public ProgressBar pBarZvac;
    public ProgressBar pBarTotal;
    public String typeApplication;
    public String typeConfigure;
    
    public int prevState = 0; //0
    public String tempStr; // Used for temporary strings everywhere

       
    public String formatTime(long millis) {
        String output = "00:00:00";
        long seconds = millis / 1000;
        long minutes = (millis / (60000));
         
        long hours     = (millis / (3600000));

        seconds = seconds % 60;
        minutes = minutes % 60;
        hours     = hours % 24;

        String secondsD = String.valueOf(seconds);
        String minutesD = String.valueOf(minutes);
        String hoursD = String.valueOf(hours);

        if (seconds < 10) secondsD = "0" + seconds;
        if (minutes < 10) minutesD = "0" + minutes;
        if(hours <= 1) hoursD = "0" + hours;

        output = hoursD + ":" + minutesD + ":" + secondsD;
        return output;
    }

     
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().getDecorView().setSystemUiVisibility(View.STATUS_BAR_HIDDEN);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, 
        WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.application_progress);
          
        commonState = (CommonState) getApplication();
        commonState.activity_name = "Application_Progress_Activity";
        mHandler.post(mUpdateUI);

        
        imViewAppProgressHead = (ImageView)findViewById(R.id.imViewAppProgressHead);
        imViewLocationId = (ImageView)findViewById(R.id.imViewLocationId);
        imBtnStopApp = (ImageButton)findViewById(R.id.imBtnStopApp); 
        imBtnPauseApp = (ImageButton)findViewById(R.id.imBtnPauseApp);       

        pBarMist = (ProgressBar) findViewById(R.id.pBarMist);
        pBarMist.setProgress(0);
           
        pBarDwell = (ProgressBar) findViewById(R.id.pBarDwell);
        pBarDwell.setProgress(0);
           
        pBarZvac = (ProgressBar) findViewById(R.id.pBarZvac);
        pBarZvac.setProgress(0);
           
        pBarTotal = (ProgressBar) findViewById(R.id.pBarTotal);
        pBarTotal.setProgress(0);
           
        imViewMist = (ImageView)findViewById(R.id.imViewMist);
        imViewDwell = (ImageView)findViewById(R.id.imViewDwell);
        imViewZvac = (ImageView)findViewById(R.id.imViewZvac);
        imViewTotalTime = (ImageView)findViewById(R.id.imViewTotalTime);

        imViewMistText = (ImageView)findViewById(R.id.imViewMistText);  
        // The Text Micro-Mist
        imViewDwellText = (ImageView)findViewById(R.id.imViewDwellText);
        // The Text Dwell
        imViewZvacText = (ImageView)findViewById(R.id.imViewZvacText);
        // The Text Z-vac
        imViewTotalText = (ImageView)findViewById(R.id.imViewTotalText);
        // The Text Total Time Remaining

        imViewMistIcon = (ImageView)findViewById(R.id.imViewMistIcon);        
        imViewDwellIcon = (ImageView)findViewById(R.id.imViewDwellIcon);
        imViewZvacIcon = (ImageView)findViewById(R.id.imViewZvacIcon);
        
        language = commonState.language;

        if(language.equals("Spanish")){
            imBtnStopApp.setImageResource(R.drawable.stop_app_spa);
            imBtnPauseApp.setImageResource(R.drawable.pause_app_spa);
            imViewAppProgressHead.setImageResource(R.drawable.application_in_progress_spa);
            imViewLocationId.setImageResource(R.drawable.location_spa);
            imViewDwellText.setImageResource(R.drawable.dwell_text_spa);
            imViewTotalText.setImageResource(R.drawable.total_time_remaining_spa);
        }
        
        imViewMistIcon.setVisibility(View.INVISIBLE);
        imViewDwellIcon.setVisibility(View.INVISIBLE);
        imViewZvacIcon.setVisibility(View.INVISIBLE);
            
        EdTChange = (EditText)findViewById(R.id.EdTChange);
        EdTChange.setText("PAUSE");
          
        txtViewId = (TextView)findViewById(R.id.txtViewId);
        txtViewId.setBackgroundColor(Color.TRANSPARENT);
        //txtViewId.setOnClickListener(this);   // No Need
          
        txtViewJobNo = (TextView)findViewById(R.id.txtViewJobNo);
        txtViewJobNo.setBackgroundColor(Color.TRANSPARENT);
        //txtViewJobNo.setOnClickListener(this); // No Need. This is not even visible
          
        txtViewTotalCounter = (TextView)findViewById(R.id.txtViewTotalCounter);
        //txtViewTotalCounter.setOnClickListener(this); // No Need
          
        txtViewMistCounter = (TextView)findViewById(R.id.txtViewMistCounter);
        //txtViewMistCounter.setOnClickListener(this); // No Need
          
        txtViewDwellCounter = (TextView)findViewById(R.id.txtViewDwellCounter);
        //txtViewDwellCounter.setOnClickListener(this); // No Need
          
        txtViewZvacCounter = (TextView)findViewById(R.id.txtViewZvacCounter);
        //txtViewZvacCounter.setOnClickListener(this); // No Need

        if (commonState.appTimer == 0 && commonState.vipState == 0) {
        	DatabaseHandler db = new DatabaseHandler(this);
        	try {
        		int CountReports = db.getReportsCount() + 1;
        		jobNo = Integer.toString(CountReports);
        	} catch (Exception e) {
        		jobNo = "1";
        	}
        	db.close();   
        commonState.jobNo = jobNo;
        }

        MistHour = commonState.MistHour ;
        MistMin = commonState.MistMin ;
        DwellHour = commonState.DwellHour ;
        DwellMin = commonState.DwellMin ;
        ZvacHour = commonState.ZvacHour ;
        ZvacMin = commonState.ZvacMin ;
        LocationId = commonState.LocationId;
        commonState.vipState = 0;
        jobNo = commonState.jobNo;

        commonState.readZvacSerial();
            
        txtViewJobNo.setText(jobNo); 
        txtViewId.setText(LocationId);

        // Mist
        TotalMistReport = String.format("%s:%s" , MistHour, MistMin);
        tempStr = MistHour.replaceFirst("^0+(?!$)","");
        long misthour = Integer.parseInt(tempStr); 
        tempStr = MistMin.replaceFirst("^0+(?!$)","");
        long mistmin = Integer.parseInt(tempStr);        
        
        mistmin = mistmin * 60 * 1000;
        misthour = misthour * 60 * 60 * 1000;
        totalMistTime = mistmin + misthour;
        commonState.totalMist = totalMistTime;

        // Dwell
        TotalDwellReport = String.format("%s:%s" , DwellHour, DwellMin);
        tempStr = DwellHour.replaceFirst("^0+(?!$)","");
        long dwellhour = Integer.parseInt(tempStr); 
        tempStr = DwellMin.replaceFirst("^0+(?!$)","");
        long dwellmin = Integer.parseInt(tempStr);
        
        dwellmin = dwellmin * 60 * 1000;
        dwellhour = dwellhour * 60 * 60 * 1000;
        totalDwellTime = dwellmin + dwellhour;    
        commonState.totalDwell = totalDwellTime;
        
        //Zvac
        TotalZvacReport = String.format("%s:%s" , ZvacHour, ZvacMin);
        tempStr = ZvacHour.replaceFirst("^0+(?!$)","");
        long zvachour = Integer.parseInt(tempStr); 
     
        tempStr = ZvacMin.replaceFirst("^0+(?!$)","");
        long zvacmin = Integer.parseInt(tempStr);
            
        zvacmin = zvacmin * 60 * 1000;
        zvachour = zvachour * 60 * 60 * 1000;
        totalZvacTime = zvacmin + zvachour;
        commonState.totalZvac = totalZvacTime;

        totalRunTime = totalMistTime + totalDwellTime + totalZvacTime;
            
        pBarMist.setMax((int) totalMistTime);
        pBarDwell.setMax((int) totalDwellTime);
        pBarZvac.setMax((int) totalZvacTime);
        pBarTotal.setMax((int) totalRunTime);
        
        if (commonState.appTimer == 0 && commonState.vipState == 0) {
        	remainingMistTime = totalMistTime;
        	remainingDwellTime = totalDwellTime;
        	remainingZvacTime = totalZvacTime;
        	remainingRunTime = totalRunTime;
        	commonState.appTimer = 0;
        	commonState.vipState = 0;
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        totalMistTime = commonState.totalMist;
        totalDwellTime = commonState.totalDwell;
        totalZvacTime = commonState.totalZvac;

        if (commonState.appTimer <= totalMistTime) {
            remainingMistTime = totalMistTime-commonState.appTimer;
        } else if (commonState.appTimer <= totalMistTime+totalDwellTime) {
            //imViewMistIcon.clearAnimation();
            remainingMistTime = 0;
            remainingDwellTime = totalMistTime+totalDwellTime - commonState.appTimer;
            commonState.vipState = 1;
        } else if (millisNow <= totalRunTime) {
            //imViewMistIcon.clearAnimation();
            //imViewDwellIcon.clearAnimation();
            remainingMistTime = 0;
            remainingDwellTime = 0;
            remainingZvacTime = totalRunTime - commonState.appTimer;
            commonState.vipState = 2;
        } else {
            //imViewMistIcon.clearAnimation();
            //imViewDwellIcon.clearAnimation();
            //imViewZvacIcon.clearAnimation();
            remainingMistTime = 0;
            remainingDwellTime = 0;
            remainingZvacTime = 0;
            remainingRunTime = 0; 
            commonState.vipState = 3; // Finished
        }
        remainingRunTime = totalRunTime - commonState.appTimer;
        millisNow = commonState.appTimer;
        prevState = commonState.vipState;
        CountDown();
    }

    public void CountDown() {
    	Animation animation = new AlphaAnimation(1, 0); 
        animation.setDuration(800); 
        animation.setInterpolator(new LinearInterpolator()); 
        animation.setRepeatCount(Animation.INFINITE); 
        
        if (commonState.vipState == 0) {        // Mist
            sendData("M127,C1,F100");
            imViewMistIcon.setVisibility(View.VISIBLE);
            imViewMistIcon.startAnimation(animation);
        } else if (commonState.vipState == 1) {  // Dwell
            sendData("F127,M0,C0");
            imViewDwellIcon.setVisibility(View.VISIBLE);
            imViewDwellIcon.startAnimation(animation);
            remainingMistTime = 0;
        } else if (commonState.vipState == 2) {  // ZVac
            sendData("F0,M0,C0,W1,R2,E"+ commonState.zvacSerial);
            imViewZvacIcon.setVisibility(View.VISIBLE);
            imViewZvacIcon.startAnimation(animation);
            remainingMistTime = 0;
            remainingDwellTime = 0;
        }
        
        counter = new CountDownTimer(remainingRunTime, 1000) {
            public void onTick(long millisUntilFinished) {
                millisNow += 1000;
                remainingRunTime -= 1000;;
                
                if (millisNow <= totalMistTime) {
                    remainingMistTime = totalMistTime-millisNow;
                    sendData("M127,C1,F100");
                } else if (millisNow <= totalMistTime+totalDwellTime) {
                	sendData("F127,M0,C0");
                    remainingDwellTime = totalMistTime+totalDwellTime - millisNow;
                    commonState.vipState = 1;
                } else if (millisNow <= totalRunTime) {
                	sendData("F0,M0,C0,W1,R2,E"+ commonState.zvacSerial);
                    remainingZvacTime = totalRunTime - millisNow;
                    commonState.vipState = 2;
                } else {
                    //imViewMistIcon.clearAnimation();
                    //imViewDwellIcon.clearAnimation();
                    //imViewZvacIcon.clearAnimation();
                	sendData("M0,C0");
                    remainingMistTime = 0;
                    remainingDwellTime = 0;
                    remainingZvacTime = 0;
                    remainingRunTime = 0; 
                    commonState.vipState = 3; // Finished
                }
                commonState.appTimer = millisNow;

                // Now set progress bars 
                int tvar;
                tvar = (int) (totalMistTime-remainingMistTime);
                pBarMist.setProgress(tvar);
                tvar = (int) (totalDwellTime-remainingDwellTime);
                pBarDwell.setProgress(tvar);
                tvar = (int)(totalZvacTime-remainingZvacTime);
                pBarZvac.setProgress(tvar);
                tvar = (int)(millisNow);
                pBarTotal.setProgress(tvar);
                // And set time remaining text 
                txtViewMistCounter.setText(formatTime(remainingMistTime));
                txtViewDwellCounter.setText(formatTime(remainingDwellTime));
                txtViewZvacCounter.setText(formatTime(remainingZvacTime));
                txtViewTotalCounter.setText(formatTime(remainingRunTime));
                
                // Check for state change
                if (prevState != commonState.vipState) {
                	if (commonState.vipState < 3) {
                		// Will need to create an animation object.
                    	Animation animation = new AlphaAnimation(1, 0); 
                        animation.setDuration(800); 
                        animation.setInterpolator(new LinearInterpolator()); 
                        animation.setRepeatCount(Animation.INFINITE); 
                	
                        if (commonState.vipState==1){
                        	sendData("M0,C0");
                        	imViewMistIcon.clearAnimation();
                        	imViewDwellIcon.setVisibility(View.VISIBLE);
                        	imViewDwellIcon.startAnimation(animation);
                        } else {
                        	sendData("F0,M0,C0");
                        	imViewDwellIcon.clearAnimation();
                        	imViewZvacIcon.setVisibility(View.VISIBLE);
                        	imViewZvacIcon.startAnimation(animation);
                        } 
                	} else {
                		// Application should stop now.
                		sendData("q3");
                	}
                	prevState = commonState.vipState;
                }
                    
                // Check the mist current and give a warning if there is no current
                if (commonState.vipState==0) {
                    if (commonState.zvacStatus[4] == 0){
                        Toast.makeText(Application_Progress_Activity.this, "Warning: No Current in 1", Toast.LENGTH_SHORT).show();
                    }
                    if (commonState.zvacStatus[5] == 0){
                        Toast.makeText(Application_Progress_Activity.this, "Warning: No Current in 2", Toast.LENGTH_SHORT).show();
                    }
                    if (commonState.zvacStatus[6] == 0){
                        Toast.makeText(Application_Progress_Activity.this, "Warning: No Current in 3", Toast.LENGTH_SHORT).show();
                    }
                    if (commonState.zvacStatus[7] == 0){
                        Toast.makeText(Application_Progress_Activity.this, "Warning: No Current in 4", Toast.LENGTH_SHORT).show();
                    }
                }
            } 
            
            public void onFinish() {
                //onDestroy();
                sendData("M0,C0");
                txtViewMistCounter.setText("00:00:00");
                txtViewDwellCounter.setText("00:00:00");
                txtViewZvacCounter.setText("00:00:00");
                txtViewTotalCounter.setText("00:00:00");
                
                String Dwell = txtViewDwellCounter.getText().toString();
                String Zvac = txtViewZvacCounter.getText().toString();
                
                SendReport();
            }
        };
        counter.start();    
                
        imBtnPauseApp.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                if (commonState.vipState>0) { return ; }
                Change = EdTChange.getText().toString();    
                                     
                if(Change.equals("PAUSE")){
                    if (language.equals("English")){ 
                        imBtnPauseApp.setImageResource(R.drawable.resume_app_eng);     
                    } 
                    if (language.equals("Spanish")){ 
                        imBtnPauseApp.setImageResource(R.drawable.resume_app_spa);     
                    }
                    commonState.mService.sendZvacCommand("M0,C0,F0,q2");
                    counter.cancel();
                    EdTChange.setText("RESUME");
                 }
                     
                 if(Change.equals("RESUME")) {
                    EdTChange.setText("PAUSE");
                    if (language.equals("English")){ 
                        imBtnPauseApp.setImageResource(R.drawable.pause_app_eng);     
                    }
                    
                    if (language.equals("Spanish")){ 
                        imBtnPauseApp.setImageResource(R.drawable.pause_app_spa);     
                    }
                    commonState.mService.sendZvacCommand("M127,C1,F100,q2");
                    counter.start(); 
                }         
            } 
        }); 
        
        imBtnStopApp.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                if (commonState.vipState > 0) { return ; }
                commonState.mService.sendZvacCommand("M0,C0,F0,Q2");
                imViewMistIcon.clearAnimation();    
                counter.cancel();
                
                AppResult = "INCOMPLETE - STOP COMMAND WAS ISSUED!";
                sendData("S0,q2"); 
                
                if (commonState.vipState==0 && commonState.appTimer == 0) {
                    Intent resultIntent = new Intent();
                    resultIntent.setClass(Application_Progress_Activity.this,Home_Screen_Activity.class);
                    setResult(Activity.RESULT_OK, resultIntent);                    
                    startActivity(resultIntent);
                } else { 
                	totalMistTime = commonState.appTimer;
                	totalDwellTime = 0;
                	commonState.appTimer += 1000;
                	totalZvacTime = totalMistTime + 5000;
                	totalRunTime = totalMistTime + totalZvacTime;
                	remainingRunTime = totalZvacTime;
                    millisNow = commonState.appTimer;
                    pBarMist.setProgress((int)totalMistTime);

                    pBarZvac.setMax((int) totalZvacTime);
                    pBarTotal.setMax((int) totalRunTime);
                	CountDown();
                }
            }
        });
    }
    
    
    public void SendReport(){ 
        onDestroy();
        sendData("S0,q2");
        typeApplication = getIntent().getStringExtra("typeApplication");
        typeConfigure = getIntent().getStringExtra("typeConfigure");
        commonState.appTimer = 0;
        commonState.vipState = 0;
         
        String myDate = getIntent().getStringExtra("Date");
        String myTime = getIntent().getStringExtra("Time"); 
        ViewReports = "0";
        
        //TotalRuntime = TotalMistReport + TotalDwellReport + TotalZvacReport;
        commonState.readZvacSerial();
        Intent intent = new Intent();
        intent.setClass(this,Reports_Activity.class);
        intent.putExtra("ViewReports", ViewReports);
        intent.putExtra("TotalRuntime", totalRunTime);
        intent.putExtra("locationId", LocationId);
        intent.putExtra("AppResult", AppResult);
        intent.putExtra("Date", myDate);
        intent.putExtra("Time", myTime);
        intent.putExtra("jobNo", jobNo);
        intent.putExtra("zvacSerial", commonState.zvacSerial);
        intent.putExtra("TotalMistReport", TotalMistReport);
        intent.putExtra("TotalDwellReport", TotalDwellReport);
        intent.putExtra("TotalZvacReport", TotalZvacReport);
        intent.putExtra("typeApplication", typeApplication);
        intent.putExtra("typeConfigure", typeConfigure);
        startActivity(intent);
    }
    
    public void onClick(View v){
        if(v==imBtnStopApp){ 
              //Intent resultIntent = new Intent();
              //resultIntent.setClass(this,ZimekActivity.class);
              //resultIntent.putExtra("language", language);
              //setResult(Activity.RESULT_OK, resultIntent);
              //startActivity(resultIntent);
        }        
    }
        
	private boolean shouldContinue = true;
    private final Handler mHandler = new Handler();
        private final Runnable mUpdateUI = new Runnable() {
        public void run() {
            if (shouldContinue){
                if (commonState.lastCommand != 0) {
                    int lastCommand = commonState.lastCommand;
                    commonState.lastCommand = 0;
                    Change =  EdTChange.getText().toString(); 
                    
                    switch (lastCommand){
                    case 4: // stop
                        imBtnStopApp.performClick();
                        mHandler.removeCallbacks(mUpdateUI);
                        shouldContinue = false;
                        onDestroy();
                        break;
                    case 2: //play
                        if (Change.equals("RESUME")) {
                            imBtnPauseApp.performClick();
                        }
                        break;
                    case 1: //pause
                        if (Change.equals("PAUSE")) {
                            imBtnPauseApp.performClick(); 
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
    
    public void onDestroy() {   
        super.onDestroy();
        mHandler.removeCallbacks(mUpdateUI);
		shouldContinue = false;
    }
}
