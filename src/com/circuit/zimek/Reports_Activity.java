package com.circuit.zimek;


import com.TSS_Report.DatabaseHandler;
import com.TSS_Report.TreatmentReports;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;



public class Reports_Activity extends Activity implements OnClickListener {
	private ImageButton imBtnNext;
	private ImageButton imBtnWarning;
	
	private ImageView imViewReportHead;
	private ImageView imViewAppResultLoc;
	private ImageView imViewStartDateTotal;
	private ImageView imViewMistDwellZvac;
	
	
	
	private TextView txtViewResult;
	private TextView txtViewJobno;
	private TextView txtViewId;
	private TextView txtViewZserial;
	private TextView txtViewDate;
	private TextView txtViewTime;
	private TextView txtViewMist;
	private TextView txtViewDwell;
	private TextView txtViewZvac;
	private TextView txtViewTotal;
	
	public String ViewReports;
	public int CountReports;
	public String TotalRuntime;
	public String AppResult;
  	public String myDate;
    public String myTime;
	public String LocationId;
	public String jobNo;
	public String TotalMistReport;
    public String TotalDwellReport;
    public String TotalZvacReport;
    public String zvacSerial;
    public String typeApplication;
    public String typeConfigure;
    public int job_No;
    public int id;
    public String language;
    public CommonState commonState = null;
	
	
	
	
	public void onCreate(Bundle savedInstanceState) {
	      super.onCreate(savedInstanceState);
	      requestWindowFeature(Window.FEATURE_NO_TITLE);
	      getWindow().getDecorView().setSystemUiVisibility(View.STATUS_BAR_HIDDEN);
	       getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, 
	               WindowManager.LayoutParams.FLAG_FULLSCREEN);
	      setContentView(R.layout.reports);
          commonState = (CommonState) getApplication();
          commonState.activity_name = "Reports_Activity";
	      

	      //onDestroy();
	      
	      DatabaseHandler db = new DatabaseHandler(this);
	      
	      language = commonState.language;
	      
	      
	      
	      if(language.equals("English")){
	      	   
	    	  imBtnNext = (ImageButton)findViewById(R.id.imBtnNext);
	    	  
	    	  imBtnWarning = (ImageButton)findViewById(R.id.imBtnWarning);
	    	  
	    	  imViewReportHead = (ImageView)findViewById(R.id.imViewReportHead);
	    	     
	    	  imViewAppResultLoc = (ImageView)findViewById(R.id.imViewAppResultLoc);
	          
	    	  imViewStartDateTotal = (ImageView)findViewById(R.id.imViewStartDateTotal);
	    	  	     
	    	  imViewMistDwellZvac = (ImageView)findViewById(R.id.imViewMistDwellZvac);
	    	     
	   	   
	      } 
	      
	      if(language.equals("Spanish")){
	    	  
	    	  imBtnWarning = (ImageButton)findViewById(R.id.imBtnWarning);
	    	  imBtnWarning.setImageResource(R.drawable.warning_spa);
	    	  
	    	  imBtnNext = (ImageButton)findViewById(R.id.imBtnNext);
	    	  imBtnNext.setImageResource(R.drawable.next_spa);
	    	  
	    	  imViewReportHead = (ImageView)findViewById(R.id.imViewReportHead);
	    	  imViewReportHead.setImageResource(R.drawable.report_spa);
	    	     
	    	  imViewAppResultLoc = (ImageView)findViewById(R.id.imViewAppResultLoc);
	    	  imViewAppResultLoc.setImageResource(R.drawable.table_result_location_spa);
	          
	    	  imViewStartDateTotal = (ImageView)findViewById(R.id.imViewStartDateTotal);
	    	  imViewStartDateTotal.setImageResource(R.drawable.start_date_total_time_spa);
	    	  	     
	    	  imViewMistDwellZvac = (ImageView)findViewById(R.id.imViewMistDwellZvac);
	    	  imViewMistDwellZvac.setImageResource(R.drawable.application_report_time_periods_spa);
                    
			   
		   }
	  		
	      imBtnNext.setOnClickListener(this);
	      imBtnWarning.setOnClickListener(this);
	      imBtnWarning.setVisibility(View.INVISIBLE);
	      
	      txtViewResult = (TextView)findViewById(R.id.txtViewResult);

	      txtViewJobno = (TextView)findViewById(R.id.txtViewJobno);
    
	      txtViewId = (TextView)findViewById(R.id.txtViewId);

	      txtViewZserial = (TextView)findViewById(R.id.txtViewZserial);
      
	      txtViewDate = (TextView)findViewById(R.id.txtViewDate);
 
	      txtViewTime = (TextView)findViewById(R.id.txtViewTime);

	      txtViewMist = (TextView)findViewById(R.id.txtViewMist);

	      txtViewDwell = (TextView)findViewById(R.id.txtViewDwell);
	      
	      txtViewZvac = (TextView)findViewById(R.id.txtViewZvac);
  
	      txtViewTotal = (TextView)findViewById(R.id.txtViewTotal);


	      
	      ViewReports = getIntent().getStringExtra("ViewReports");
	      
	      if(ViewReports.equals("0"))
	      {
	    	  
	        TotalRuntime = getIntent().getStringExtra("TotalRuntime");
	      	AppResult = getIntent().getStringExtra("AppResult");
	        myDate = getIntent().getStringExtra("Date");
			myTime = getIntent().getStringExtra("Time");
			LocationId = getIntent().getStringExtra("locationId");
			jobNo = getIntent().getStringExtra("jobNo");
	        TotalMistReport = getIntent().getStringExtra("TotalMistReport");
	        TotalDwellReport = getIntent().getStringExtra("TotalDwellReport");
	        TotalZvacReport = getIntent().getStringExtra("TotalZvacReport");
	        zvacSerial = getIntent().getStringExtra("zvacSerial");
	        typeApplication = getIntent().getStringExtra("typeApplication");
		     typeConfigure = getIntent().getStringExtra("typeConfigure");
	        
	        job_No = Integer.parseInt(jobNo);
	        
	        
	       // Log.d("Insert: ", "Inserting ..");
	        db.addReport(new TreatmentReports(job_No, LocationId, myDate, myTime, TotalMistReport, TotalDwellReport, TotalZvacReport, TotalRuntime, zvacSerial, AppResult, typeApplication, typeConfigure));
	        
	       // Log.d("Reading: ", "Reading all contacts..");
	       // List<TreatmentReports> reportsList = db.getAllReports();     
	        
	       // for (TreatmentReports cn : reportsList) {
	         //   String log = "Id: "+cn.getID()+" ,Location: " + cn.getLocation() + " ,Date: " + cn.getmyDate() + " ,Time: " + cn.getmyTime() + " ,MistTime: " + cn.getMistTime()
	           // 		+ " ,DwellTime: " + cn.getDwellTime() + " ,ZvacTime: " + cn.getZvacTime() + " ,Total Run Time: " + cn.getTotalTime() + " ,Zvac Serial: " + cn.getZvacSerial() 
	            //		+ " ,AppResult: " + cn.getAppResult() + " ,typeApplication: " + cn.gettypeApplication() + " ,typeConfigure: " + cn.gettypeConfigure();
	                // Writing Contacts to log
	        //Log.d("Name: ", log);
	        
	       // onDestroy();
	        
	        
	       // }
	          
	        txtViewResult.setText(AppResult);
	        txtViewJobno.setText(jobNo);
	        txtViewId.setText(LocationId);
	        txtViewZserial.setText(zvacSerial);
	        txtViewDate.setText(myDate);
	        txtViewTime.setText(myTime);
	        txtViewMist.setText(TotalMistReport);
	        txtViewDwell.setText(TotalDwellReport);
	        txtViewZvac.setText(TotalZvacReport);
	        txtViewTotal.setText(TotalRuntime);
	        
	        
	        }
	        
	      db.close();
      }
	      
	      
	

	
	
	
	public void onClick(View v){
	  	if(v==imBtnNext){
	  		
	  		DatabaseHandler db = new DatabaseHandler(this);
	  		db.close();
	  		
	  		  SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
	 		  SharedPreferences.Editor editor=preferences.edit();
	 		  editor.putString("Name",language);
              commonState.language = language;
	 		  editor.commit();
	 		  
	  		Intent resultIntent = new Intent();
  			resultIntent.setClass(this,Home_Screen_Activity.class);
  			setResult(Activity.RESULT_OK, resultIntent);
  			startActivity(resultIntent);
				
	  	}
	}
	  	

	
	public void onDestroy()
	  {   
//	      Cleanup();
	      super.onDestroy();
	  }

//	  private void Cleanup()
//	  {    
	  	
//	      System.gc();
//	      Runtime.getRuntime().gc();  
//	  }
	
	

}
