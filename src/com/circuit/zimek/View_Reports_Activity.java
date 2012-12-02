package com.circuit.zimek;



import java.util.List;

import com.TSS_Report.DatabaseHandler;
import com.TSS_Report.TreatmentReports;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;



public class View_Reports_Activity extends Activity implements OnClickListener{
	
    public CommonState commonState = null;

	public String language;
	public int TotalReports;
	public int Id;
	public int CountReports = 8;
	public int IdCount = 0;
	public int IdCountUp = 1;
	public int Remaining;
	public int minus = 0;
	public int TotalCountReports;
	public String Key = "0";
	
	
	public int Row1 = 1;
	public int Row2 = 2;
	public int Row3 = 3;
	public int Row4 = 4;
	public int Row5 = 5;
	public int Row6 = 6;
	public int Row7 = 7;
	public int Row8 = 8;
	
	private ImageButton imBtnWarning;
	private ImageButton imBtnReturn;
	private ImageButton imBtnControlCenter;
	private ImageButton imBtnNext;
	
	 private ImageView imViewReportsHead;
	 private ImageView imViewReportTitle;
	 
	 private ImageView imView_1;
	 private ImageView imView_2;
	 private ImageView imView_3;
	 private ImageView imView_4;
	 private ImageView imView_5;
	 private ImageView imView_6;
	 private ImageView imView_7;
	 private ImageView imView_8;
	 
	 private TextView txtViewDateRow_1;
	 private TextView txtViewLocRow_1;
	 private TextView txtViewStatusRow_1;
	 private TextView txtViewStartRow_1;
	 private TextView txtViewTotalRow_1;
	 
	 private TextView txtViewDateRow_2;
	 private TextView txtViewLocRow_2;
	 private TextView txtViewStatusRow_2;
	 private TextView txtViewStartRow_2;
	 private TextView txtViewTotalRow_2;
	 
	 private TextView txtViewDateRow_3;
	 private TextView txtViewLocRow_3;
	 private TextView txtViewStatusRow_3;
	 private TextView txtViewStartRow_3;
	 private TextView txtViewTotalRow_3;
	 
	 private TextView txtViewDateRow_4;
	 private TextView txtViewLocRow_4;
	 private TextView txtViewStatusRow_4;
	 private TextView txtViewStartRow_4;
	 private TextView txtViewTotalRow_4;
	 
	 private TextView txtViewDateRow_5;
	 private TextView txtViewLocRow_5;
	 private TextView txtViewStatusRow_5;
	 private TextView txtViewStartRow_5;
	 private TextView txtViewTotalRow_5;
	 
	 private TextView txtViewDateRow_6;
	 private TextView txtViewLocRow_6;
	 private TextView txtViewStatusRow_6;
	 private TextView txtViewStartRow_6;
	 private TextView txtViewTotalRow_6;
	 
	 private TextView txtViewDateRow_7;
	 private TextView txtViewLocRow_7;
	 private TextView txtViewStatusRow_7;
	 private TextView txtViewStartRow_7;
	 private TextView txtViewTotalRow_7;
	 
	 private TextView txtViewDateRow_8;
	 private TextView txtViewLocRow_8;
	 private TextView txtViewStatusRow_8;
	 private TextView txtViewStartRow_8;
	 private TextView txtViewTotalRow_8;
	 
	 
		public String TotalRuntime;
		public String AppResult;
		public String myDate;
	    public String myTime;
		public String LocationId;
	 
	 
	
	
	
	
	
	@Override
	   public void onCreate(Bundle savedInstanceState) {
	       super.onCreate(savedInstanceState);
	       
	       requestWindowFeature(Window.FEATURE_NO_TITLE);
	       getWindow().getDecorView().setSystemUiVisibility(View.STATUS_BAR_HIDDEN);
	       getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, 
	               WindowManager.LayoutParams.FLAG_FULLSCREEN);
	       setContentView(R.layout.view_reports);
           commonState = (CommonState) getApplication();
           commonState.activity_name = "View_Reports_Activity";
	       
	       txtViewDateRow_1 = (TextView)findViewById(R.id.txtViewDateRow_1);
	       txtViewLocRow_1 = (TextView)findViewById(R.id.txtViewLocRow_1);
	       txtViewStatusRow_1 = (TextView)findViewById(R.id.txtViewStatusRow_1);
	       txtViewStartRow_1 = (TextView)findViewById(R.id.txtViewStartRow_1);
	       txtViewTotalRow_1  = (TextView)findViewById(R.id.txtViewTotalRow_1);
	       
	       txtViewDateRow_1.setVisibility(View.INVISIBLE);
	       txtViewLocRow_1.setVisibility(View.INVISIBLE);
	       txtViewStatusRow_1.setVisibility(View.INVISIBLE);
	       txtViewStartRow_1.setVisibility(View.INVISIBLE);
	       txtViewTotalRow_1.setVisibility(View.INVISIBLE);
	       
	       txtViewDateRow_2 = (TextView)findViewById(R.id.txtViewDateRow_2);
	       txtViewLocRow_2 = (TextView)findViewById(R.id.txtViewLocRow_2);
	       txtViewStatusRow_2 = (TextView)findViewById(R.id.txtViewStatusRow_2);
	       txtViewStartRow_2 = (TextView)findViewById(R.id.txtViewStartRow_2);
	       txtViewTotalRow_2  = (TextView)findViewById(R.id.txtViewTotalRow_2);
	       
	       txtViewDateRow_2.setVisibility(View.INVISIBLE);
	       txtViewLocRow_2.setVisibility(View.INVISIBLE);
	       txtViewStatusRow_2.setVisibility(View.INVISIBLE);
	       txtViewStartRow_2.setVisibility(View.INVISIBLE);
	       txtViewTotalRow_2.setVisibility(View.INVISIBLE);
	       
	       txtViewDateRow_3 = (TextView)findViewById(R.id.txtViewDateRow_3);
	       txtViewLocRow_3 = (TextView)findViewById(R.id.txtViewLocRow_3);
	       txtViewStatusRow_3 = (TextView)findViewById(R.id.txtViewStatusRow_3);
	       txtViewStartRow_3 = (TextView)findViewById(R.id.txtViewStartRow_3);
	       txtViewTotalRow_3  = (TextView)findViewById(R.id.txtViewTotalRow_3);
	       
	       txtViewDateRow_3.setVisibility(View.INVISIBLE);
	       txtViewLocRow_3.setVisibility(View.INVISIBLE);
	       txtViewStatusRow_3.setVisibility(View.INVISIBLE);
	       txtViewStartRow_3.setVisibility(View.INVISIBLE);
	       txtViewTotalRow_3.setVisibility(View.INVISIBLE);
	       
	       txtViewDateRow_4 = (TextView)findViewById(R.id.txtViewDateRow_4);
	       txtViewLocRow_4 = (TextView)findViewById(R.id.txtViewLocRow_4);
	       txtViewStatusRow_4 = (TextView)findViewById(R.id.txtViewStatusRow_4);
	       txtViewStartRow_4 = (TextView)findViewById(R.id.txtViewStartRow_4);
	       txtViewTotalRow_4  = (TextView)findViewById(R.id.txtViewTotalRow_4);
	       
	       txtViewDateRow_4.setVisibility(View.INVISIBLE);
	       txtViewLocRow_4.setVisibility(View.INVISIBLE);
	       txtViewStatusRow_4.setVisibility(View.INVISIBLE);
	       txtViewStartRow_4.setVisibility(View.INVISIBLE);
	       txtViewTotalRow_4.setVisibility(View.INVISIBLE);
	       
	       txtViewDateRow_5 = (TextView)findViewById(R.id.txtViewDateRow_5);
	       txtViewLocRow_5 = (TextView)findViewById(R.id.txtViewLocRow_5);
	       txtViewStatusRow_5 = (TextView)findViewById(R.id.txtViewStatusRow_5);
	       txtViewStartRow_5 = (TextView)findViewById(R.id.txtViewStartRow_5);
	       txtViewTotalRow_5  = (TextView)findViewById(R.id.txtViewTotalRow_5);
	       
	       txtViewDateRow_5.setVisibility(View.INVISIBLE);
	       txtViewLocRow_5.setVisibility(View.INVISIBLE);
	       txtViewStatusRow_5.setVisibility(View.INVISIBLE);
	       txtViewStartRow_5.setVisibility(View.INVISIBLE);
	       txtViewTotalRow_5.setVisibility(View.INVISIBLE);
	       
	       txtViewDateRow_6 = (TextView)findViewById(R.id.txtViewDateRow_6);
	       txtViewLocRow_6 = (TextView)findViewById(R.id.txtViewLocRow_6);
	       txtViewStatusRow_6 = (TextView)findViewById(R.id.txtViewStatusRow_6);
	       txtViewStartRow_6 = (TextView)findViewById(R.id.txtViewStartRow_6);
	       txtViewTotalRow_6  = (TextView)findViewById(R.id.txtViewTotalRow_6);
	       
	       txtViewDateRow_6.setVisibility(View.INVISIBLE);
	       txtViewLocRow_6.setVisibility(View.INVISIBLE);
	       txtViewStatusRow_6.setVisibility(View.INVISIBLE);
	       txtViewStartRow_6.setVisibility(View.INVISIBLE);
	       txtViewTotalRow_6.setVisibility(View.INVISIBLE);
	       
	       txtViewDateRow_7 = (TextView)findViewById(R.id.txtViewDateRow_7);
	       txtViewLocRow_7 = (TextView)findViewById(R.id.txtViewLocRow_7);
	       txtViewStatusRow_7 = (TextView)findViewById(R.id.txtViewStatusRow_7);
	       txtViewStartRow_7 = (TextView)findViewById(R.id.txtViewStartRow_7);
	       txtViewTotalRow_7  = (TextView)findViewById(R.id.txtViewTotalRow_7);
	       
	       txtViewDateRow_7.setVisibility(View.INVISIBLE);
	       txtViewLocRow_7.setVisibility(View.INVISIBLE);
	       txtViewStatusRow_7.setVisibility(View.INVISIBLE);
	       txtViewStartRow_7.setVisibility(View.INVISIBLE);
	       txtViewTotalRow_7.setVisibility(View.INVISIBLE);
	       
	       txtViewDateRow_8 = (TextView)findViewById(R.id.txtViewDateRow_8);
	       txtViewLocRow_8 = (TextView)findViewById(R.id.txtViewLocRow_8);
	       txtViewStatusRow_8 = (TextView)findViewById(R.id.txtViewStatusRow_8);
	       txtViewStartRow_8 = (TextView)findViewById(R.id.txtViewStartRow_8);
	       txtViewTotalRow_8  = (TextView)findViewById(R.id.txtViewTotalRow_8);
	       
	       txtViewDateRow_8.setVisibility(View.INVISIBLE);
	       txtViewLocRow_8.setVisibility(View.INVISIBLE);
	       txtViewStatusRow_8.setVisibility(View.INVISIBLE);
	       txtViewStartRow_8.setVisibility(View.INVISIBLE);
	       txtViewTotalRow_8.setVisibility(View.INVISIBLE);
	       
	       imView_1 = (ImageView)findViewById(R.id.imView_1);
	       imView_2 = (ImageView)findViewById(R.id.imView_2);
	       imView_3 = (ImageView)findViewById(R.id.imView_3);
	       imView_4 = (ImageView)findViewById(R.id.imView_4);
	       imView_5 = (ImageView)findViewById(R.id.imView_5);
	       imView_6 = (ImageView)findViewById(R.id.imView_6);
	       imView_7 = (ImageView)findViewById(R.id.imView_7);
	       imView_8 = (ImageView)findViewById(R.id.imView_8);
	       
	       imView_1.setVisibility(View.INVISIBLE);
	       imView_2.setVisibility(View.INVISIBLE);
	       imView_3.setVisibility(View.INVISIBLE);
	       imView_4.setVisibility(View.INVISIBLE);
	       imView_5.setVisibility(View.INVISIBLE);
	       imView_6.setVisibility(View.INVISIBLE);
	       imView_7.setVisibility(View.INVISIBLE);
	       imView_8.setVisibility(View.INVISIBLE);
	       
	       
	       language = commonState.language;
	       //Key = getIntent().getStringExtra("Key");
           Key = commonState.Key;
	       
	       
	       
	       if(language.equals("English")){
	       	   
	    	   imViewReportsHead = (ImageView)findViewById(R.id.imViewReportsHead);
	     	     
	    	   imViewReportTitle = (ImageView)findViewById(R.id.imViewReportTitle);
	    	   
	    	   
	           if(Key.equals("1")){
	        	   imBtnControlCenter = (ImageButton)findViewById(R.id.imBtnControlCenter);
	        	   //imBtnControlCenter.setImageResource(R.drawable.control_center_mgt_button_eng);
	        	   imBtnControlCenter.setImageResource(R.drawable.return_home_to_reports_menu_eng);
	        	   
	           } else{
	           imBtnControlCenter = (ImageButton)findViewById(R.id.imBtnControlCenter);
	           }
	           
	           imBtnNext = (ImageButton)findViewById(R.id.imBtnNext);
	           
	           imBtnReturn = (ImageButton)findViewById(R.id.imBtnReturn);
	           
	           imBtnWarning = (ImageButton)findViewById(R.id.imBtnWarning);
	         
	 	   
	    	   
	       } 
	       
	       if(language.equals("Spanish")){
	 		   
	           imBtnReturn = (ImageButton)findViewById(R.id.imBtnReturn);
	           imBtnReturn.setImageResource(R.drawable.return_spa);
	           
	           imBtnWarning = (ImageButton)findViewById(R.id.imBtnWarning);
	           imBtnWarning.setImageResource(R.drawable.warning_spa);
	           
	           
	           if(Key.equals("1")){
	        	   imBtnControlCenter = (ImageButton)findViewById(R.id.imBtnControlCenter);
	        	   //imBtnControlCenter.setImageResource(R.drawable.control_center_mgt_button_spa);
	        	   imBtnControlCenter.setImageResource(R.drawable.return_home_to_reports_menu_spa);
	        	   
	           } else{
	        	   imBtnControlCenter = (ImageButton)findViewById(R.id.imBtnControlCenter);
		           imBtnControlCenter.setImageResource(R.drawable.other_options_3_spa);
	           }
	           
	           
	           
	           imBtnNext = (ImageButton)findViewById(R.id.imBtnNext);
	           imBtnNext.setImageResource(R.drawable.next_spa);
	           
	           imViewReportsHead = (ImageView)findViewById(R.id.imViewReportsHead);
	           imViewReportsHead.setImageResource(R.drawable.view_reports_head_spa);
	  	     
	           imViewReportTitle = (ImageView)findViewById(R.id.imViewReportTitle);
	           imViewReportTitle.setImageResource(R.drawable.table_heading_spa);
	           
 
	 		   
	 	   }
	       
	       imBtnNext.setVisibility(View.INVISIBLE);
	       imBtnReturn.setVisibility(View.INVISIBLE);
	       imBtnWarning.setVisibility(View.INVISIBLE);
	       
	       imBtnWarning.setOnClickListener(this);
	       imBtnReturn.setOnClickListener(this);
	       imBtnNext.setOnClickListener(this);
	       imBtnControlCenter.setOnClickListener(this);
	       
	       DatabaseHandler db = new DatabaseHandler(this);
	       
	        
	       int ReportCount = db.getReportsCount();
	       
	       TotalCountReports = ReportCount;
		
	       if(ReportCount == 0){
			
	    	   Toast.makeText(this, "THERE ARE NO REPORTS", Toast.LENGTH_LONG).show();
		
	    	   db.close();
	    	   finish();

		
	       } else {
	    	   db.close();
	       }
	       
	      
	       ShowDataBase();
	       
      
	       
	}
	
	
	
	
	public void ShowDataBase(){
		
		DatabaseHandler db = new DatabaseHandler(this);
		
		Log.d("Reading: ", "Reading all contacts..");
        List<TreatmentReports> reportsList = db.getAllReports();     
        
        for (TreatmentReports cn : reportsList) {
            String log = "Id: "+cn.getID()+" ,Location: " + cn.getLocation() + " ,Date: " + cn.getmyDate() + " ,Time: " + cn.getmyTime() + " ,MistTime: " + cn.getMistTime()
            		+ " ,DwellTime: " + cn.getDwellTime() + " ,ZvacTime: " + cn.getZvacTime() + " ,Total Run Time: " + cn.getTotalTime() + " ,Zvac Serial: " + cn.getZvacSerial() 
            		+ " ,AppResult: " + cn.getAppResult() + " ,typeApplication: " + cn.gettypeApplication() + " ,typeConfigure: " + cn.gettypeConfigure();
                // Writing Contacts to log
        Log.d("Name: ", log);
        }
		
		
		
		TotalReports = db.getReportsCount();
		
		
		if (TotalReports < CountReports){
			
			CountReports = CountReports - 8;
		    Remaining = TotalReports - CountReports;
			
			if (Remaining <= 8){
				
				CountReports = TotalReports;

				
				if (Remaining == 1){
					
					  // imView_1.setVisibility(View.INVISIBLE);
				       imView_2.setVisibility(View.INVISIBLE);
				       imView_3.setVisibility(View.INVISIBLE);
				       imView_4.setVisibility(View.INVISIBLE);
				       imView_5.setVisibility(View.INVISIBLE);
				       imView_6.setVisibility(View.INVISIBLE);
				       imView_7.setVisibility(View.INVISIBLE);
				       imView_8.setVisibility(View.INVISIBLE);
				       
				       txtViewDateRow_2.setVisibility(View.INVISIBLE);
				       txtViewLocRow_2.setVisibility(View.INVISIBLE);
				       txtViewStatusRow_2.setVisibility(View.INVISIBLE);
				       txtViewStartRow_2.setVisibility(View.INVISIBLE);
				       txtViewTotalRow_2.setVisibility(View.INVISIBLE);
				       
				       txtViewDateRow_3.setVisibility(View.INVISIBLE);
				       txtViewLocRow_3.setVisibility(View.INVISIBLE);
				       txtViewStatusRow_3.setVisibility(View.INVISIBLE);
				       txtViewStartRow_3.setVisibility(View.INVISIBLE);
				       txtViewTotalRow_3.setVisibility(View.INVISIBLE);
				       
				       txtViewDateRow_4.setVisibility(View.INVISIBLE);
				       txtViewLocRow_4.setVisibility(View.INVISIBLE);
				       txtViewStatusRow_4.setVisibility(View.INVISIBLE);
				       txtViewStartRow_4.setVisibility(View.INVISIBLE);
				       txtViewTotalRow_4.setVisibility(View.INVISIBLE);
				       
				       txtViewDateRow_5.setVisibility(View.INVISIBLE);
				       txtViewLocRow_5.setVisibility(View.INVISIBLE);
				       txtViewStatusRow_5.setVisibility(View.INVISIBLE);
				       txtViewStartRow_5.setVisibility(View.INVISIBLE);
				       txtViewTotalRow_5.setVisibility(View.INVISIBLE);
				       
				       txtViewDateRow_6.setVisibility(View.INVISIBLE);
				       txtViewLocRow_6.setVisibility(View.INVISIBLE);
				       txtViewStatusRow_6.setVisibility(View.INVISIBLE);
				       txtViewStartRow_6.setVisibility(View.INVISIBLE);
				       txtViewTotalRow_6.setVisibility(View.INVISIBLE);
				       
				       txtViewDateRow_7.setVisibility(View.INVISIBLE);
				       txtViewLocRow_7.setVisibility(View.INVISIBLE);
				       txtViewStatusRow_7.setVisibility(View.INVISIBLE);
				       txtViewStartRow_7.setVisibility(View.INVISIBLE);
				       txtViewTotalRow_7.setVisibility(View.INVISIBLE);
				       
				       txtViewDateRow_8.setVisibility(View.INVISIBLE);
				       txtViewLocRow_8.setVisibility(View.INVISIBLE);
				       txtViewStatusRow_8.setVisibility(View.INVISIBLE);
				       txtViewStartRow_8.setVisibility(View.INVISIBLE);
				       txtViewTotalRow_8.setVisibility(View.INVISIBLE);
				       imBtnNext.setVisibility(View.INVISIBLE);
					
				}
				
				if (Remaining == 2){
					
					  // imView_1.setVisibility(View.INVISIBLE);
				       //imView_2.setVisibility(View.INVISIBLE);
				       imView_3.setVisibility(View.INVISIBLE);
				       imView_4.setVisibility(View.INVISIBLE);
				       imView_5.setVisibility(View.INVISIBLE);
				       imView_6.setVisibility(View.INVISIBLE);
				       imView_7.setVisibility(View.INVISIBLE);
				       imView_8.setVisibility(View.INVISIBLE);
				       
				       txtViewDateRow_3.setVisibility(View.INVISIBLE);
				       txtViewLocRow_3.setVisibility(View.INVISIBLE);
				       txtViewStatusRow_3.setVisibility(View.INVISIBLE);
				       txtViewStartRow_3.setVisibility(View.INVISIBLE);
				       txtViewTotalRow_3.setVisibility(View.INVISIBLE);
				       
				       txtViewDateRow_4.setVisibility(View.INVISIBLE);
				       txtViewLocRow_4.setVisibility(View.INVISIBLE);
				       txtViewStatusRow_4.setVisibility(View.INVISIBLE);
				       txtViewStartRow_4.setVisibility(View.INVISIBLE);
				       txtViewTotalRow_4.setVisibility(View.INVISIBLE);
				       
				       txtViewDateRow_5.setVisibility(View.INVISIBLE);
				       txtViewLocRow_5.setVisibility(View.INVISIBLE);
				       txtViewStatusRow_5.setVisibility(View.INVISIBLE);
				       txtViewStartRow_5.setVisibility(View.INVISIBLE);
				       txtViewTotalRow_5.setVisibility(View.INVISIBLE);
				       
				       txtViewDateRow_6.setVisibility(View.INVISIBLE);
				       txtViewLocRow_6.setVisibility(View.INVISIBLE);
				       txtViewStatusRow_6.setVisibility(View.INVISIBLE);
				       txtViewStartRow_6.setVisibility(View.INVISIBLE);
				       txtViewTotalRow_6.setVisibility(View.INVISIBLE);
				       
				       txtViewDateRow_7.setVisibility(View.INVISIBLE);
				       txtViewLocRow_7.setVisibility(View.INVISIBLE);
				       txtViewStatusRow_7.setVisibility(View.INVISIBLE);
				       txtViewStartRow_7.setVisibility(View.INVISIBLE);
				       txtViewTotalRow_7.setVisibility(View.INVISIBLE);
				       
				       txtViewDateRow_8.setVisibility(View.INVISIBLE);
				       txtViewLocRow_8.setVisibility(View.INVISIBLE);
				       txtViewStatusRow_8.setVisibility(View.INVISIBLE);
				       txtViewStartRow_8.setVisibility(View.INVISIBLE);
				       txtViewTotalRow_8.setVisibility(View.INVISIBLE);
				       imBtnNext.setVisibility(View.INVISIBLE);
					
				}
				
				if (Remaining == 3){
					
						//imView_1.setVisibility(View.INVISIBLE);
				       //imView_2.setVisibility(View.INVISIBLE);
				       //imView_3.setVisibility(View.INVISIBLE);
				       imView_4.setVisibility(View.INVISIBLE);
				       imView_5.setVisibility(View.INVISIBLE);
				       imView_6.setVisibility(View.INVISIBLE);
				       imView_7.setVisibility(View.INVISIBLE);
				       imView_8.setVisibility(View.INVISIBLE);
				       
				       txtViewDateRow_4.setVisibility(View.INVISIBLE);
				       txtViewLocRow_4.setVisibility(View.INVISIBLE);
				       txtViewStatusRow_4.setVisibility(View.INVISIBLE);
				       txtViewStartRow_4.setVisibility(View.INVISIBLE);
				       txtViewTotalRow_4.setVisibility(View.INVISIBLE);
				       
				       txtViewDateRow_5.setVisibility(View.INVISIBLE);
				       txtViewLocRow_5.setVisibility(View.INVISIBLE);
				       txtViewStatusRow_5.setVisibility(View.INVISIBLE);
				       txtViewStartRow_5.setVisibility(View.INVISIBLE);
				       txtViewTotalRow_5.setVisibility(View.INVISIBLE);
				       
				       txtViewDateRow_6.setVisibility(View.INVISIBLE);
				       txtViewLocRow_6.setVisibility(View.INVISIBLE);
				       txtViewStatusRow_6.setVisibility(View.INVISIBLE);
				       txtViewStartRow_6.setVisibility(View.INVISIBLE);
				       txtViewTotalRow_6.setVisibility(View.INVISIBLE);
				       
				       txtViewDateRow_7.setVisibility(View.INVISIBLE);
				       txtViewLocRow_7.setVisibility(View.INVISIBLE);
				       txtViewStatusRow_7.setVisibility(View.INVISIBLE);
				       txtViewStartRow_7.setVisibility(View.INVISIBLE);
				       txtViewTotalRow_7.setVisibility(View.INVISIBLE);
				       
				       txtViewDateRow_8.setVisibility(View.INVISIBLE);
				       txtViewLocRow_8.setVisibility(View.INVISIBLE);
				       txtViewStatusRow_8.setVisibility(View.INVISIBLE);
				       txtViewStartRow_8.setVisibility(View.INVISIBLE);
				       txtViewTotalRow_8.setVisibility(View.INVISIBLE);
				       imBtnNext.setVisibility(View.INVISIBLE);
					
				}
				
				if (Remaining == 4){
					
						//imView_1.setVisibility(View.INVISIBLE);
				       //imView_2.setVisibility(View.INVISIBLE);
				       //imView_3.setVisibility(View.INVISIBLE);
				       //imView_4.setVisibility(View.INVISIBLE);
				       imView_5.setVisibility(View.INVISIBLE);
				       imView_6.setVisibility(View.INVISIBLE);
				       imView_7.setVisibility(View.INVISIBLE);
				       imView_8.setVisibility(View.INVISIBLE);
				       
				       txtViewDateRow_5.setVisibility(View.INVISIBLE);
				       txtViewLocRow_5.setVisibility(View.INVISIBLE);
				       txtViewStatusRow_5.setVisibility(View.INVISIBLE);
				       txtViewStartRow_5.setVisibility(View.INVISIBLE);
				       txtViewTotalRow_5.setVisibility(View.INVISIBLE);
				       
				       txtViewDateRow_6.setVisibility(View.INVISIBLE);
				       txtViewLocRow_6.setVisibility(View.INVISIBLE);
				       txtViewStatusRow_6.setVisibility(View.INVISIBLE);
				       txtViewStartRow_6.setVisibility(View.INVISIBLE);
				       txtViewTotalRow_6.setVisibility(View.INVISIBLE);
				       
				       txtViewDateRow_7.setVisibility(View.INVISIBLE);
				       txtViewLocRow_7.setVisibility(View.INVISIBLE);
				       txtViewStatusRow_7.setVisibility(View.INVISIBLE);
				       txtViewStartRow_7.setVisibility(View.INVISIBLE);
				       txtViewTotalRow_7.setVisibility(View.INVISIBLE);
				       
				       txtViewDateRow_8.setVisibility(View.INVISIBLE);
				       txtViewLocRow_8.setVisibility(View.INVISIBLE);
				       txtViewStatusRow_8.setVisibility(View.INVISIBLE);
				       txtViewStartRow_8.setVisibility(View.INVISIBLE);
				       txtViewTotalRow_8.setVisibility(View.INVISIBLE);
				       
				       imBtnNext.setVisibility(View.INVISIBLE);
					
				}
				
				if (Remaining == 5){
					
						//imView_1.setVisibility(View.INVISIBLE);
				       //imView_2.setVisibility(View.INVISIBLE);
				       //imView_3.setVisibility(View.INVISIBLE);
				       //imView_4.setVisibility(View.INVISIBLE);
				       //imView_5.setVisibility(View.INVISIBLE);
				       imView_6.setVisibility(View.INVISIBLE);
				       imView_7.setVisibility(View.INVISIBLE);
				       imView_8.setVisibility(View.INVISIBLE);
				       
				       txtViewDateRow_6.setVisibility(View.INVISIBLE);
				       txtViewLocRow_6.setVisibility(View.INVISIBLE);
				       txtViewStatusRow_6.setVisibility(View.INVISIBLE);
				       txtViewStartRow_6.setVisibility(View.INVISIBLE);
				       txtViewTotalRow_6.setVisibility(View.INVISIBLE);
				       
				       txtViewDateRow_7.setVisibility(View.INVISIBLE);
				       txtViewLocRow_7.setVisibility(View.INVISIBLE);
				       txtViewStatusRow_7.setVisibility(View.INVISIBLE);
				       txtViewStartRow_7.setVisibility(View.INVISIBLE);
				       txtViewTotalRow_7.setVisibility(View.INVISIBLE);
				       
				       txtViewDateRow_8.setVisibility(View.INVISIBLE);
				       txtViewLocRow_8.setVisibility(View.INVISIBLE);
				       txtViewStatusRow_8.setVisibility(View.INVISIBLE);
				       txtViewStartRow_8.setVisibility(View.INVISIBLE);
				       txtViewTotalRow_8.setVisibility(View.INVISIBLE);
				       imBtnNext.setVisibility(View.INVISIBLE);
					
					
				}
				
				if (Remaining == 6){
					//imView_1.setVisibility(View.INVISIBLE);
				      // imView_2.setVisibility(View.INVISIBLE);
				       //imView_3.setVisibility(View.INVISIBLE);
				       //imView_4.setVisibility(View.INVISIBLE);
				       //imView_5.setVisibility(View.INVISIBLE);
				       //imView_6.setVisibility(View.INVISIBLE);
				       imView_7.setVisibility(View.INVISIBLE);
				       imView_8.setVisibility(View.INVISIBLE);
				       
				       txtViewDateRow_7.setVisibility(View.INVISIBLE);
				       txtViewLocRow_7.setVisibility(View.INVISIBLE);
				       txtViewStatusRow_7.setVisibility(View.INVISIBLE);
				       txtViewStartRow_7.setVisibility(View.INVISIBLE);
				       txtViewTotalRow_7.setVisibility(View.INVISIBLE);
				       
				       txtViewDateRow_8.setVisibility(View.INVISIBLE);
				       txtViewLocRow_8.setVisibility(View.INVISIBLE);
				       txtViewStatusRow_8.setVisibility(View.INVISIBLE);
				       txtViewStartRow_8.setVisibility(View.INVISIBLE);
				       txtViewTotalRow_8.setVisibility(View.INVISIBLE);
				       imBtnNext.setVisibility(View.INVISIBLE);
					
				}
				
				if (Remaining == 7){
					//imView_1.setVisibility(View.INVISIBLE);
				      // imView_2.setVisibility(View.INVISIBLE);
				       //imView_3.setVisibility(View.INVISIBLE);
				       //imView_4.setVisibility(View.INVISIBLE);
				       //imView_5.setVisibility(View.INVISIBLE);
				      // imView_6.setVisibility(View.INVISIBLE);
				       //imView_7.setVisibility(View.INVISIBLE);
				       imView_8.setVisibility(View.INVISIBLE);
				       
				       txtViewDateRow_8.setVisibility(View.INVISIBLE);
				       txtViewLocRow_8.setVisibility(View.INVISIBLE);
				       txtViewStatusRow_8.setVisibility(View.INVISIBLE);
				       txtViewStartRow_8.setVisibility(View.INVISIBLE);
				       txtViewTotalRow_8.setVisibility(View.INVISIBLE);
				       imBtnNext.setVisibility(View.INVISIBLE);
					
				}
				
			}
			
	
		
		}
		
		//Toast.makeText(this, "IdCount + CountReports:  " + IdCount + CountReports + TotalReports, Toast.LENGTH_LONG ).show();
		
		
		for (Id = IdCount; IdCount <= CountReports; IdCount++) {
			
			
			
			if (TotalCountReports > 8 || IdCount > 8){
				
				imBtnNext.setVisibility(View.VISIBLE);
			} else {
				imBtnNext.setVisibility(View.INVISIBLE);
			}
			
			if(IdCount == TotalCountReports && TotalCountReports > 8){
				
				imBtnNext.setVisibility(View.INVISIBLE);
				//imBtnReturn.setVisibility(View.VISIBLE);
				
			}
			
			if(IdCount > 8){
				
				imBtnReturn.setVisibility(View.VISIBLE);
				
			} else {
				
				imBtnReturn.setVisibility(View.INVISIBLE);
				
			}
			

					
				 
			    if (IdCount == Row1){
			    	
			    	   txtViewDateRow_1.setVisibility(View.VISIBLE);
				       txtViewLocRow_1.setVisibility(View.VISIBLE);
				       txtViewStatusRow_1.setVisibility(View.VISIBLE);
				       txtViewStartRow_1.setVisibility(View.VISIBLE);
				       txtViewTotalRow_1.setVisibility(View.VISIBLE);
				       imView_1.setVisibility(View.VISIBLE);
			    	
			    	TreatmentReports Results = db.getReport(IdCount);
			    	
			    	myDate = Results.getmyDate();
				    LocationId = Results.getLocation();
				    AppResult = Results.getAppResult();
	    	 	    myTime = Results.getmyTime();
	    	 	    TotalRuntime = Results.getTotalTime();
	    	 	    
	    	 	   txtViewDateRow_1.setText(myDate);
	    	 	   txtViewLocRow_1.setText(LocationId);
	    	 	   txtViewStatusRow_1.setText(AppResult);
	    	 	   txtViewStartRow_1.setText(myTime);
	    	 	   txtViewTotalRow_1.setText(TotalRuntime);
	    	 	   	    
	
			    }
			    
			    if (IdCount == Row2){
			    	
			    	   txtViewDateRow_2.setVisibility(View.VISIBLE);
				       txtViewLocRow_2.setVisibility(View.VISIBLE);
				       txtViewStatusRow_2.setVisibility(View.VISIBLE);
				       txtViewStartRow_2.setVisibility(View.VISIBLE);
				       txtViewTotalRow_2.setVisibility(View.VISIBLE);
				       imView_2.setVisibility(View.VISIBLE);
			    	
			    	TreatmentReports Results = db.getReport(IdCount);
			    	
			    	myDate = Results.getmyDate();
				    LocationId = Results.getLocation();
				    AppResult = Results.getAppResult();
	    	 	    myTime = Results.getmyTime();
	    	 	    TotalRuntime = Results.getTotalTime();
	    	 	    
	    	 	   txtViewDateRow_2.setText(myDate);
	    	 	   txtViewLocRow_2.setText(LocationId);
	    	 	   txtViewStatusRow_2.setText(AppResult);
	    	 	   txtViewStartRow_2.setText(myTime);
	    	 	   txtViewTotalRow_2.setText(TotalRuntime);
	    	 	   	    
	
			    }
			    
			    if (IdCount == Row3){
			    	
			    	   txtViewDateRow_3.setVisibility(View.VISIBLE);
				       txtViewLocRow_3.setVisibility(View.VISIBLE);
				       txtViewStatusRow_3.setVisibility(View.VISIBLE);
				       txtViewStartRow_3.setVisibility(View.VISIBLE);
				       txtViewTotalRow_3.setVisibility(View.VISIBLE);
				       imView_3.setVisibility(View.VISIBLE);
			    	
			    	TreatmentReports Results = db.getReport(IdCount);
			    	
			    	myDate = Results.getmyDate();
				    LocationId = Results.getLocation();
				    AppResult = Results.getAppResult();
	    	 	    myTime = Results.getmyTime();
	    	 	    TotalRuntime = Results.getTotalTime();
	    	 	    
	    	 	   txtViewDateRow_3.setText(myDate);
	    	 	   txtViewLocRow_3.setText(LocationId);
	    	 	   txtViewStatusRow_3.setText(AppResult);
	    	 	   txtViewStartRow_3.setText(myTime);
	    	 	   txtViewTotalRow_3.setText(TotalRuntime);
	    	 	   	    
	
			    }
			    
			    if (IdCount == Row4){
			    	
			    	   txtViewDateRow_4.setVisibility(View.VISIBLE);
				       txtViewLocRow_4.setVisibility(View.VISIBLE);
				       txtViewStatusRow_4.setVisibility(View.VISIBLE);
				       txtViewStartRow_4.setVisibility(View.VISIBLE);
				       txtViewTotalRow_4.setVisibility(View.VISIBLE);
				       imView_4.setVisibility(View.VISIBLE);
			    	
			    	TreatmentReports Results = db.getReport(IdCount);
			    	
			    	myDate = Results.getmyDate();
				    LocationId = Results.getLocation();
				    AppResult = Results.getAppResult();
	    	 	    myTime = Results.getmyTime();
	    	 	    TotalRuntime = Results.getTotalTime();
	    	 	    
	    	 	   txtViewDateRow_4.setText(myDate);
	    	 	   txtViewLocRow_4.setText(LocationId);
	    	 	   txtViewStatusRow_4.setText(AppResult);
	    	 	   txtViewStartRow_4.setText(myTime);
	    	 	   txtViewTotalRow_4.setText(TotalRuntime);
	    	 	   	    
	
			    }
			    
			    if (IdCount == Row5){
			    	
			    	   txtViewDateRow_5.setVisibility(View.VISIBLE);
				       txtViewLocRow_5.setVisibility(View.VISIBLE);
				       txtViewStatusRow_5.setVisibility(View.VISIBLE);
				       txtViewStartRow_5.setVisibility(View.VISIBLE);
				       txtViewTotalRow_5.setVisibility(View.VISIBLE);
				       imView_5.setVisibility(View.VISIBLE);
			    	
			    	TreatmentReports Results = db.getReport(IdCount);
			    	
			    	myDate = Results.getmyDate();
				    LocationId = Results.getLocation();
				    AppResult = Results.getAppResult();
	    	 	    myTime = Results.getmyTime();
	    	 	    TotalRuntime = Results.getTotalTime();
	    	 	    
	    	 	   txtViewDateRow_5.setText(myDate);
	    	 	   txtViewLocRow_5.setText(LocationId);
	    	 	   txtViewStatusRow_5.setText(AppResult);
	    	 	   txtViewStartRow_5.setText(myTime);
	    	 	   txtViewTotalRow_5.setText(TotalRuntime);
	    	 	   	    
	
			    }
			    
			    if (IdCount == Row6){
			    	
			    	   txtViewDateRow_6.setVisibility(View.VISIBLE);
				       txtViewLocRow_6.setVisibility(View.VISIBLE);
				       txtViewStatusRow_6.setVisibility(View.VISIBLE);
				       txtViewStartRow_6.setVisibility(View.VISIBLE);
				       txtViewTotalRow_6.setVisibility(View.VISIBLE);
				       imView_6.setVisibility(View.VISIBLE);
			    	
			    	TreatmentReports Results = db.getReport(IdCount);
			    	
			    	myDate = Results.getmyDate();
				    LocationId = Results.getLocation();
				    AppResult = Results.getAppResult();
	    	 	    myTime = Results.getmyTime();
	    	 	    TotalRuntime = Results.getTotalTime();
	    	 	    
	    	 	   txtViewDateRow_6.setText(myDate);
	    	 	   txtViewLocRow_6.setText(LocationId);
	    	 	   txtViewStatusRow_6.setText(AppResult);
	    	 	   txtViewStartRow_6.setText(myTime);
	    	 	   txtViewTotalRow_6.setText(TotalRuntime);
	    	 	   	    
	
			    }
			    
			    if (IdCount == Row7){
			    	
			    	   txtViewDateRow_7.setVisibility(View.VISIBLE);
				       txtViewLocRow_7.setVisibility(View.VISIBLE);
				       txtViewStatusRow_7.setVisibility(View.VISIBLE);
				       txtViewStartRow_7.setVisibility(View.VISIBLE);
				       txtViewTotalRow_7.setVisibility(View.VISIBLE);
				       imView_7.setVisibility(View.VISIBLE);
			    	
			    	TreatmentReports Results = db.getReport(IdCount);
			    	
			    	myDate = Results.getmyDate();
				    LocationId = Results.getLocation();
				    AppResult = Results.getAppResult();
	    	 	    myTime = Results.getmyTime();
	    	 	    TotalRuntime = Results.getTotalTime();
	    	 	    
	    	 	   txtViewDateRow_7.setText(myDate);
	    	 	   txtViewLocRow_7.setText(LocationId);
	    	 	   txtViewStatusRow_7.setText(AppResult);
	    	 	   txtViewStartRow_7.setText(myTime);
	    	 	   txtViewTotalRow_7.setText(TotalRuntime);
	    	 	   	    
	
			    }
			    
			    if (IdCount == Row8){
			    	
			    	   txtViewDateRow_8.setVisibility(View.VISIBLE);
				       txtViewLocRow_8.setVisibility(View.VISIBLE);
				       txtViewStatusRow_8.setVisibility(View.VISIBLE);
				       txtViewStartRow_8.setVisibility(View.VISIBLE);
				       txtViewTotalRow_8.setVisibility(View.VISIBLE);
				       imView_8.setVisibility(View.VISIBLE);
			    	
			    	TreatmentReports Results = db.getReport(IdCount);
			    	
			    	myDate = Results.getmyDate();
				    LocationId = Results.getLocation();
				    AppResult = Results.getAppResult();
	    	 	    myTime = Results.getmyTime();
	    	 	    TotalRuntime = Results.getTotalTime();
	    	 	    
	    	 	   txtViewDateRow_8.setText(myDate);
	    	 	   txtViewLocRow_8.setText(LocationId);
	    	 	   txtViewStatusRow_8.setText(AppResult);
	    	 	   txtViewStartRow_8.setText(myTime);
	    	 	   txtViewTotalRow_8.setText(TotalRuntime);
	    	 	   	    
	
			    }
			    
			    db.close();
			    
		}
		
		db.close();
		
		


			
	}
	
	

	
	public void onClick(View v) {
		
		if(v==imBtnReturn){
			
			
			
			if (CountReports == TotalCountReports){
				
				
				if (IdCount >= 17 && Remaining == 0){
					IdCount = IdCount - 16 ; //16
					CountReports = CountReports - 8;
					
				}else{
				IdCount = (IdCount - Remaining) - 8;
				CountReports = CountReports - Remaining;
				}
			} else {
			
			if (CountReports < TotalReports){
				
				IdCount = IdCount - 16 ; //16
				CountReports = CountReports - 8;
				
			 }
			}
			
			//if (IdCount >= 16 && TotalReports == TotalCountReports){
				
				

				//IdCount = IdCount - 16 ; //16
				//CountReports = CountReports - 8;
				
			//} 
			
			
			
			
			// (IdCount >= TotalReports && TotalReports < TotalCountReports){
				
			//	IdCount = IdCount - 16; //16
			//	CountReports = CountReports - 8;

				//IdCount = (IdCount - Remaining) - 8;
				//CountReports = CountReports - Remaining;
				
			//} 
				
				
					
		
							
			//IdCount = IdCount - 16; //16
			//CountReports = CountReports - 8;
			
			
			
		
			
			Row1 = Row1 - 8;
			Row2 = Row2 - 8;
			Row3 = Row3 - 8;
			Row4 = Row4 - 8;
			Row5 = Row5 - 8;
			Row6 = Row6 - 8;
			Row7 = Row7 - 8;
			Row8 = Row8 - 8;
			

			
			
			ShowDataBase();

    	}
    		
		
		if(v==imBtnNext){
			
			CountReports = CountReports + 8;
			//IdCount = IdCount + 8;
			
			Row1 = Row1 + 8;
			Row2 = Row2 + 8;
			Row3 = Row3 + 8;
			Row4 = Row4 + 8;
			Row5 = Row5 + 8;
			Row6 = Row6 + 8;
			Row7 = Row7 + 8;
			Row8 = Row8 + 8;
			
			
			
			ShowDataBase();
			
		}
		
		if(v==imBtnControlCenter){
			
			if (Key.equals("1")){
				
				Intent resultIntent = new Intent();
	  			//resultIntent.setClass(this,MGT_ControlCenter_Activity.class);
				resultIntent.setClass(this,Reports_Menu_Activity.class);
	  			startActivity(resultIntent);
				
			}else{
  			
  			Intent resultIntent = new Intent();
  			resultIntent.setClass(this,ZimekActivity.class);
  			setResult(Activity.RESULT_OK, resultIntent);
  			startActivity(resultIntent);
  			
			}
  			
    	}
  	
  	
		
		
	}
	
	
}
