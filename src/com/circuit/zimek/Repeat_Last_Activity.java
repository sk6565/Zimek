package com.circuit.zimek;

import java.util.StringTokenizer;

import com.TSS_Report.DatabaseHandler;
import com.TSS_Report.TreatmentReports;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;




public class Repeat_Last_Activity extends Activity implements OnClickListener{


	 public int CountReports;
	 public int TotalCounter;

	 public int finalValue;
	 public String language;
	 public int IdCount = 0;
	 public int TotalReport;
	 public int LeastCounter;
	 public int counter;
	 public int id;
	 
	 public int Row1 = 1;
	 public int Row2 = 2;
	 public int Row3 = 3;
	 public int Row4 = 4;
	 public int Row5 = 5;
	 
	 private ImageButton imBtn_1;
	 private ImageButton imBtn_2;
	 private ImageButton imBtn_3;
	 private ImageButton imBtn_4;
	 private ImageButton imBtn_5;
	 
	 private ImageButton imBtnWarning;
	 private ImageButton imBtnReturn;
	 private ImageButton imBtnControlCenter;
	 private ImageButton imBtnNext;
	 
	 private ImageView imView_1;
	 private ImageView imView_2;
	 private ImageView imView_3;
	 private ImageView imView_4;
	 private ImageView imView_5;
	 
	 private ImageView imViewRepeatHead;
	 private ImageView imViewRepeatTitle;
	 
 
	 
	 private TextView txtViewLocRow_1;
	 private TextView txtViewSizeRow_1;
	 private TextView txtViewTotalRow_1;
	 private TextView txtViewMistRow_1;
	 private TextView txtViewDwellRow_1;
	 private TextView txtViewZvacRow_1;
	 
	 private TextView txtViewLocRow_2;
	 private TextView txtViewSizeRow_2;
	 private TextView txtViewTotalRow_2;
	 private TextView txtViewMistRow_2;
	 private TextView txtViewDwellRow_2;
	 private TextView txtViewZvacRow_2;
	 
	 private TextView txtViewLocRow_3;
	 private TextView txtViewSizeRow_3;
	 private TextView txtViewTotalRow_3;
	 private TextView txtViewMistRow_3;
	 private TextView txtViewDwellRow_3;
	 private TextView txtViewZvacRow_3;
	 
	 private TextView txtViewLocRow_4;
	 private TextView txtViewSizeRow_4;
	 private TextView txtViewTotalRow_4;
	 private TextView txtViewMistRow_4;
	 private TextView txtViewDwellRow_4;
	 private TextView txtViewZvacRow_4;
	 
	 private TextView txtViewLocRow_5;
	 private TextView txtViewSizeRow_5;
	 private TextView txtViewTotalRow_5;
	 private TextView txtViewMistRow_5;
	 private TextView txtViewDwellRow_5;
	 private TextView txtViewZvacRow_5;
	 

	 public String LocationId;
	 public String locationId;
	 public String typeApplication;
	 public String typeConfigure;
	 public String TotalMistReport;
	 public String TotalDwellReport;
	 public String TotalZvacReport;
	 public String TotalRuntime;
	  
	    
	public String MistTime;
	public String DwellTime;
	public String ZvacTime;
	public String MistMin;
    public String DwellMin;
    public String ZvacMin;
    public String MistHour;
    public String DwellHour;
    public String ZvacHour;

    public CommonState commonState = null;

		 
	

	 
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().getDecorView().setSystemUiVisibility(View.STATUS_BAR_HIDDEN);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, 
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.repeat_last);
        commonState = (CommonState) getApplication();
        commonState.activity_name = "Repeat_Last_Activity";
        
        //onDestroy();
        
        	
	       txtViewLocRow_1 = (TextView)findViewById(R.id.txtViewLocRow_1);
	       txtViewSizeRow_1 = (TextView)findViewById(R.id.txtViewSizeRow_1);
	       txtViewTotalRow_1  = (TextView)findViewById(R.id.txtViewTotalRow_1);
	       txtViewMistRow_1 = (TextView)findViewById(R.id.txtViewMistRow_1);
	       txtViewDwellRow_1 = (TextView)findViewById(R.id.txtViewDwellRow_1);
	       txtViewZvacRow_1 = (TextView)findViewById(R.id.txtViewZvacRow_1);
	       
	       txtViewLocRow_1.setVisibility(View.INVISIBLE);
	       txtViewSizeRow_1.setVisibility(View.INVISIBLE);
	       txtViewTotalRow_1.setVisibility(View.INVISIBLE);
	       txtViewMistRow_1.setVisibility(View.INVISIBLE);
	       txtViewDwellRow_1.setVisibility(View.INVISIBLE);
	       txtViewZvacRow_1.setVisibility(View.INVISIBLE);
	       
	       txtViewLocRow_2 = (TextView)findViewById(R.id.txtViewLocRow_2);
	       txtViewSizeRow_2 = (TextView)findViewById(R.id.txtViewSizeRow_2);
	       txtViewTotalRow_2  = (TextView)findViewById(R.id.txtViewTotalRow_2);
	       txtViewMistRow_2 = (TextView)findViewById(R.id.txtViewMistRow_2);
	       txtViewDwellRow_2 = (TextView)findViewById(R.id.txtViewDwellRow_2);
	       txtViewZvacRow_2 = (TextView)findViewById(R.id.txtViewZvacRow_2);
	       
	       txtViewLocRow_2.setVisibility(View.INVISIBLE);
	       txtViewSizeRow_2.setVisibility(View.INVISIBLE);
	       txtViewTotalRow_2.setVisibility(View.INVISIBLE);
	       txtViewMistRow_2.setVisibility(View.INVISIBLE);
	       txtViewDwellRow_2.setVisibility(View.INVISIBLE);
	       txtViewZvacRow_2.setVisibility(View.INVISIBLE);
	       
	       txtViewLocRow_3 = (TextView)findViewById(R.id.txtViewLocRow_3);
	       txtViewSizeRow_3 = (TextView)findViewById(R.id.txtViewSizeRow_3);
	       txtViewTotalRow_3  = (TextView)findViewById(R.id.txtViewTotalRow_3);
	       txtViewMistRow_3 = (TextView)findViewById(R.id.txtViewMistRow_3);
	       txtViewDwellRow_3 = (TextView)findViewById(R.id.txtViewDwellRow_3);
	       txtViewZvacRow_3 = (TextView)findViewById(R.id.txtViewZvacRow_3);
	       
	       txtViewLocRow_3.setVisibility(View.INVISIBLE);
	       txtViewSizeRow_3.setVisibility(View.INVISIBLE);
	       txtViewTotalRow_3.setVisibility(View.INVISIBLE);
	       txtViewMistRow_3.setVisibility(View.INVISIBLE);
	       txtViewDwellRow_3.setVisibility(View.INVISIBLE);
	       txtViewZvacRow_3.setVisibility(View.INVISIBLE);
	       
	       txtViewLocRow_4 = (TextView)findViewById(R.id.txtViewLocRow_4);
	       txtViewSizeRow_4 = (TextView)findViewById(R.id.txtViewSizeRow_4);
	       txtViewTotalRow_4  = (TextView)findViewById(R.id.txtViewTotalRow_4);
	       txtViewMistRow_4 = (TextView)findViewById(R.id.txtViewMistRow_4);
	       txtViewDwellRow_4 = (TextView)findViewById(R.id.txtViewDwellRow_4);
	       txtViewZvacRow_4 = (TextView)findViewById(R.id.txtViewZvacRow_4);
	       
	       txtViewLocRow_4.setVisibility(View.INVISIBLE);
	       txtViewSizeRow_4.setVisibility(View.INVISIBLE);
	       txtViewTotalRow_4.setVisibility(View.INVISIBLE);
	       txtViewMistRow_4.setVisibility(View.INVISIBLE);
	       txtViewDwellRow_4.setVisibility(View.INVISIBLE);
	       txtViewZvacRow_4.setVisibility(View.INVISIBLE);
	       
	       txtViewLocRow_5 = (TextView)findViewById(R.id.txtViewLocRow_5);
	       txtViewSizeRow_5 = (TextView)findViewById(R.id.txtViewSizeRow_5);
	       txtViewTotalRow_5  = (TextView)findViewById(R.id.txtViewTotalRow_5);
	       txtViewMistRow_5 = (TextView)findViewById(R.id.txtViewMistRow_5);
	       txtViewDwellRow_5 = (TextView)findViewById(R.id.txtViewDwellRow_5);
	       txtViewZvacRow_5 = (TextView)findViewById(R.id.txtViewZvacRow_5);
	       
	       txtViewLocRow_5.setVisibility(View.INVISIBLE);
	       txtViewSizeRow_5.setVisibility(View.INVISIBLE);
	       txtViewTotalRow_5.setVisibility(View.INVISIBLE);
	       txtViewMistRow_5.setVisibility(View.INVISIBLE);
	       txtViewDwellRow_5.setVisibility(View.INVISIBLE);
	       txtViewZvacRow_5.setVisibility(View.INVISIBLE);
	       
	       language = commonState.language;
	       
	       
	       if(language.equals("English")){
	       	   
	    	   imViewRepeatHead = (ImageView)findViewById(R.id.imViewRepeatHead);
	     	     
	    	   imViewRepeatTitle = (ImageView)findViewById(R.id.imViewRepeatTitle);
	           
	           imBtnControlCenter = (ImageButton)findViewById(R.id.imBtnControlCenter);
	           
	           imBtnNext = (ImageButton)findViewById(R.id.imBtnNext);
	           
	           imBtnReturn = (ImageButton)findViewById(R.id.imBtnReturn);
	           
	           imBtnWarning = (ImageButton)findViewById(R.id.imBtnWarning);
	         
	 	   
	    	   
	       } 
	       
	       if(language.equals("Spanish")){
	 		   
	           imBtnReturn = (ImageButton)findViewById(R.id.imBtnReturn);
	           imBtnReturn.setImageResource(R.drawable.return_spa);
	           
	           imBtnWarning = (ImageButton)findViewById(R.id.imBtnWarning);
	           imBtnWarning.setImageResource(R.drawable.warning_spa);
	           
	           imBtnControlCenter = (ImageButton)findViewById(R.id.imBtnControlCenter);
	           imBtnControlCenter.setImageResource(R.drawable.control_center_spa);
	           
	           imBtnNext = (ImageButton)findViewById(R.id.imBtnNext);
	           imBtnNext.setImageResource(R.drawable.next_spa);
	           
	           imViewRepeatHead = (ImageView)findViewById(R.id.imViewRepeatHead);
	           imViewRepeatHead.setImageResource(R.drawable.repeat_an_application_spa);
	  	     
	           imViewRepeatTitle = (ImageView)findViewById(R.id.imViewRepeatTitle);
	           imViewRepeatTitle.setImageResource(R.drawable.table_heading_repeat_spa);
	           
 
	 		   
	 	   }
	       
	       imBtnNext.setVisibility(View.INVISIBLE);
	       imBtnReturn.setVisibility(View.INVISIBLE);
	       imBtnWarning.setVisibility(View.INVISIBLE);
	       
	       imView_1 = (ImageView)findViewById(R.id.imView_1);
	       imView_2 = (ImageView)findViewById(R.id.imView_2);
	       imView_3 = (ImageView)findViewById(R.id.imView_3);
	       imView_4 = (ImageView)findViewById(R.id.imView_4);
	       imView_5 = (ImageView)findViewById(R.id.imView_5);
	      
	       
	       imView_1.setVisibility(View.INVISIBLE);
	       imView_2.setVisibility(View.INVISIBLE);
	       imView_3.setVisibility(View.INVISIBLE);
	       imView_4.setVisibility(View.INVISIBLE);
	       imView_5.setVisibility(View.INVISIBLE);
	       
	       
	       imBtn_1 = (ImageButton)findViewById(R.id.imBtn_1);
	       imBtn_2 = (ImageButton)findViewById(R.id.imBtn_2);
	       imBtn_3 = (ImageButton)findViewById(R.id.imBtn_3);
	       imBtn_4 = (ImageButton)findViewById(R.id.imBtn_4);
	       imBtn_5 = (ImageButton)findViewById(R.id.imBtn_5);
	       
	       imBtn_1.setVisibility(View.INVISIBLE);
	       imBtn_2.setVisibility(View.INVISIBLE);
	       imBtn_3.setVisibility(View.INVISIBLE);
	       imBtn_4.setVisibility(View.INVISIBLE);
	       imBtn_5.setVisibility(View.INVISIBLE);
	       
	       
	       imBtnWarning.setOnClickListener(this);
	       imBtnReturn.setOnClickListener(this);
	       imBtnNext.setOnClickListener(this);
	       imBtnControlCenter.setOnClickListener(this);
	       imBtn_1.setOnClickListener(this);
	       imBtn_2.setOnClickListener(this);
	       imBtn_3.setOnClickListener(this);
	       imBtn_4.setOnClickListener(this);
	       imBtn_5.setOnClickListener(this);
	       
        
        
	       DatabaseHandler db = new DatabaseHandler(this);
        
	       CountReports = db.getReportsCount();
		
	       if(CountReports == 0){
			
	    	   Toast.makeText(this, "THERE ARE NO REPORTS", Toast.LENGTH_LONG).show();
		
	    	   db.close();
	    	   finish();

		
	       }
	       
	       TotalReport = CountReports;
	       
	       if(TotalReport > 5){
	    	   TotalCounter = TotalReport;
	    	  // LeastCounter = TotalReport - 15;
	    	   counter = 5;
	    	   imBtnNext.setVisibility(View.VISIBLE);
	       }
	       
	       db.close();
	       
	      RepeatReport();
	       
	       
	
	       
	}
	
	
	public void RepeatReport(){
		
		DatabaseHandler db = new DatabaseHandler(this);
		
		 //List<TreatmentReports> reportsList = db.getAllReports(); 
		 
		  //for (TreatmentReports cn : reportsList) {
	        //    String log = "Id: "+cn.getID()+" ,Location: " + cn.getLocation() + " ,Date: " + cn.getmyDate() + " ,Time: " + cn.getmyTime() + " ,MistTime: " + cn.getMistTime()
	          //  		+ " ,DwellTime: " + cn.getDwellTime() + " ,ZvacTime: " + cn.getZvacTime() + " ,Total Run Time: " + cn.getTotalTime() + " ,Zvac Serial: " + cn.getZvacSerial() 
	            //		+ " ,AppResult: " + cn.getAppResult() + " ,typeApplication: " + cn.gettypeApplication() + " ,typeConfigure: " + cn.gettypeConfigure();
	                // Writing Contacts to log
	       //Log.d("Name: ", log);
	        //}
		
		//TotalReport = db.getReportsCount();
		
		
		
		if(TotalReport > 5){		
			
			 IdCount = TotalReport - 5;
			 
			 Row1 = TotalReport;
			 Row2 = TotalReport - 1;
			 Row3 = TotalReport - 2;
			 Row4 = TotalReport - 3;
			 Row5 = TotalReport - 4;
			 			 
			   imView_1.setVisibility(View.VISIBLE);
		       imView_2.setVisibility(View.VISIBLE);
		       imView_3.setVisibility(View.VISIBLE);
		       imView_4.setVisibility(View.VISIBLE);
		       imView_5.setVisibility(View.VISIBLE);
		       
		       imBtn_1.setVisibility(View.VISIBLE);
		       imBtn_2.setVisibility(View.VISIBLE);
		       imBtn_3.setVisibility(View.VISIBLE);
		       imBtn_4.setVisibility(View.VISIBLE);
		       imBtn_5.setVisibility(View.VISIBLE);
		       
		       txtViewLocRow_1.setVisibility(View.VISIBLE);
		       txtViewSizeRow_1.setVisibility(View.VISIBLE);
		       txtViewTotalRow_1.setVisibility(View.VISIBLE);
		       txtViewMistRow_1.setVisibility(View.VISIBLE);
		       txtViewDwellRow_1.setVisibility(View.VISIBLE);
		       txtViewZvacRow_1.setVisibility(View.VISIBLE);
		       
		       txtViewLocRow_2.setVisibility(View.VISIBLE);
		       txtViewSizeRow_2.setVisibility(View.VISIBLE);
		       txtViewTotalRow_2.setVisibility(View.VISIBLE);
		       txtViewMistRow_2.setVisibility(View.VISIBLE);
		       txtViewDwellRow_2.setVisibility(View.VISIBLE);
		       txtViewZvacRow_2.setVisibility(View.VISIBLE);
		       
		       txtViewLocRow_3.setVisibility(View.VISIBLE);
		       txtViewSizeRow_3.setVisibility(View.VISIBLE);
		       txtViewTotalRow_3.setVisibility(View.VISIBLE);
		       txtViewMistRow_3.setVisibility(View.VISIBLE);
		       txtViewDwellRow_3.setVisibility(View.VISIBLE);
		       txtViewZvacRow_3.setVisibility(View.VISIBLE);
		       
		       txtViewLocRow_4.setVisibility(View.VISIBLE);
		       txtViewSizeRow_4.setVisibility(View.VISIBLE);
		       txtViewTotalRow_4.setVisibility(View.VISIBLE);
		       txtViewMistRow_4.setVisibility(View.VISIBLE);
		       txtViewDwellRow_4.setVisibility(View.VISIBLE);
		       txtViewZvacRow_4.setVisibility(View.VISIBLE);
		       
		       txtViewLocRow_5.setVisibility(View.VISIBLE);
		       txtViewSizeRow_5.setVisibility(View.VISIBLE);
		       txtViewTotalRow_5.setVisibility(View.VISIBLE);
		       txtViewMistRow_5.setVisibility(View.VISIBLE);
		       txtViewDwellRow_5.setVisibility(View.VISIBLE);
		       txtViewZvacRow_5.setVisibility(View.VISIBLE);
			 
			 
			 
			 		 
		 }
		
		
		if(TotalReport <= 5){
			
			//Row1 = TotalReport;
			
			if(TotalReport == 1){
				
				   imView_1.setVisibility(View.VISIBLE);
				   imBtn_1.setVisibility(View.VISIBLE);
				   
				   txtViewLocRow_1.setVisibility(View.VISIBLE);
			       txtViewSizeRow_1.setVisibility(View.VISIBLE);
			       txtViewTotalRow_1.setVisibility(View.VISIBLE);
			       txtViewMistRow_1.setVisibility(View.VISIBLE);
			       txtViewDwellRow_1.setVisibility(View.VISIBLE);
			       txtViewZvacRow_1.setVisibility(View.VISIBLE);
			       
			       Row1 = TotalReport;
			

				
			}
			
			if(TotalReport == 2){
				
				   imView_1.setVisibility(View.VISIBLE);
			       imView_2.setVisibility(View.VISIBLE);
			       
			       imBtn_1.setVisibility(View.VISIBLE);
			       imBtn_2.setVisibility(View.VISIBLE);
			       
			       txtViewLocRow_1.setVisibility(View.VISIBLE);
			       txtViewSizeRow_1.setVisibility(View.VISIBLE);
			       txtViewTotalRow_1.setVisibility(View.VISIBLE);
			       txtViewMistRow_1.setVisibility(View.VISIBLE);
			       txtViewDwellRow_1.setVisibility(View.VISIBLE);
			       txtViewZvacRow_1.setVisibility(View.VISIBLE);
			       
			       txtViewLocRow_2.setVisibility(View.VISIBLE);
			       txtViewSizeRow_2.setVisibility(View.VISIBLE);
			       txtViewTotalRow_2.setVisibility(View.VISIBLE);
			       txtViewMistRow_2.setVisibility(View.VISIBLE);
			       txtViewDwellRow_2.setVisibility(View.VISIBLE);
			       txtViewZvacRow_2.setVisibility(View.VISIBLE);
			       
			       Row1 = TotalReport;
			       Row2 = TotalReport - 1;
		
				
			}
			
			if(TotalReport == 3){
				
				   imView_1.setVisibility(View.VISIBLE);
			       imView_2.setVisibility(View.VISIBLE);
			       imView_3.setVisibility(View.VISIBLE);
			       
			       imBtn_1.setVisibility(View.VISIBLE);
			       imBtn_2.setVisibility(View.VISIBLE);
			       imBtn_3.setVisibility(View.VISIBLE);
			       
			       txtViewLocRow_1.setVisibility(View.VISIBLE);
			       txtViewSizeRow_1.setVisibility(View.VISIBLE);
			       txtViewTotalRow_1.setVisibility(View.VISIBLE);
			       txtViewMistRow_1.setVisibility(View.VISIBLE);
			       txtViewDwellRow_1.setVisibility(View.VISIBLE);
			       txtViewZvacRow_1.setVisibility(View.VISIBLE);
			       
			       txtViewLocRow_2.setVisibility(View.VISIBLE);
			       txtViewSizeRow_2.setVisibility(View.VISIBLE);
			       txtViewTotalRow_2.setVisibility(View.VISIBLE);
			       txtViewMistRow_2.setVisibility(View.VISIBLE);
			       txtViewDwellRow_2.setVisibility(View.VISIBLE);
			       txtViewZvacRow_2.setVisibility(View.VISIBLE);
			       
			       txtViewLocRow_3.setVisibility(View.VISIBLE);
			       txtViewSizeRow_3.setVisibility(View.VISIBLE);
			       txtViewTotalRow_3.setVisibility(View.VISIBLE);
			       txtViewMistRow_3.setVisibility(View.VISIBLE);
			       txtViewDwellRow_3.setVisibility(View.VISIBLE);
			       txtViewZvacRow_3.setVisibility(View.VISIBLE);
			       
			       Row1 = TotalReport;
			       Row2 = TotalReport - 1;
			       Row3 = TotalReport - 2;
			       

			    
				
			}
			
			if(TotalReport == 4){
				
				   imView_1.setVisibility(View.VISIBLE);
			       imView_2.setVisibility(View.VISIBLE);
			       imView_3.setVisibility(View.VISIBLE);
			       imView_4.setVisibility(View.VISIBLE);
			       
			       imBtn_1.setVisibility(View.VISIBLE);
			       imBtn_2.setVisibility(View.VISIBLE);
			       imBtn_3.setVisibility(View.VISIBLE);
			       imBtn_4.setVisibility(View.VISIBLE);
			       
			       txtViewLocRow_1.setVisibility(View.VISIBLE);
			       txtViewSizeRow_1.setVisibility(View.VISIBLE);
			       txtViewTotalRow_1.setVisibility(View.VISIBLE);
			       txtViewMistRow_1.setVisibility(View.VISIBLE);
			       txtViewDwellRow_1.setVisibility(View.VISIBLE);
			       txtViewZvacRow_1.setVisibility(View.VISIBLE);
			       
			       txtViewLocRow_2.setVisibility(View.VISIBLE);
			       txtViewSizeRow_2.setVisibility(View.VISIBLE);
			       txtViewTotalRow_2.setVisibility(View.VISIBLE);
			       txtViewMistRow_2.setVisibility(View.VISIBLE);
			       txtViewDwellRow_2.setVisibility(View.VISIBLE);
			       txtViewZvacRow_2.setVisibility(View.VISIBLE);
			       
			       txtViewLocRow_3.setVisibility(View.VISIBLE);
			       txtViewSizeRow_3.setVisibility(View.VISIBLE);
			       txtViewTotalRow_3.setVisibility(View.VISIBLE);
			       txtViewMistRow_3.setVisibility(View.VISIBLE);
			       txtViewDwellRow_3.setVisibility(View.VISIBLE);
			       txtViewZvacRow_3.setVisibility(View.VISIBLE);
			       
			       txtViewLocRow_4.setVisibility(View.VISIBLE);
			       txtViewSizeRow_4.setVisibility(View.VISIBLE);
			       txtViewTotalRow_4.setVisibility(View.VISIBLE);
			       txtViewMistRow_4.setVisibility(View.VISIBLE);
			       txtViewDwellRow_4.setVisibility(View.VISIBLE);
			       txtViewZvacRow_4.setVisibility(View.VISIBLE);
			       
			       Row1 = TotalReport;
			       Row2 = TotalReport - 1;
			       Row3 = TotalReport - 2;
			       Row4 = TotalReport - 3;
				
			}
			
			if(TotalReport == 5 ){
				
				   imView_1.setVisibility(View.VISIBLE);
			       imView_2.setVisibility(View.VISIBLE);
			       imView_3.setVisibility(View.VISIBLE);
			       imView_4.setVisibility(View.VISIBLE);
			       imView_5.setVisibility(View.VISIBLE);
			       
			       imBtn_1.setVisibility(View.VISIBLE);
			       imBtn_2.setVisibility(View.VISIBLE);
			       imBtn_3.setVisibility(View.VISIBLE);
			       imBtn_4.setVisibility(View.VISIBLE);
			       imBtn_5.setVisibility(View.VISIBLE);
			       
			       txtViewLocRow_1.setVisibility(View.VISIBLE);
			       txtViewSizeRow_1.setVisibility(View.VISIBLE);
			       txtViewTotalRow_1.setVisibility(View.VISIBLE);
			       txtViewMistRow_1.setVisibility(View.VISIBLE);
			       txtViewDwellRow_1.setVisibility(View.VISIBLE);
			       txtViewZvacRow_1.setVisibility(View.VISIBLE);
			       
			       txtViewLocRow_2.setVisibility(View.VISIBLE);
			       txtViewSizeRow_2.setVisibility(View.VISIBLE);
			       txtViewTotalRow_2.setVisibility(View.VISIBLE);
			       txtViewMistRow_2.setVisibility(View.VISIBLE);
			       txtViewDwellRow_2.setVisibility(View.VISIBLE);
			       txtViewZvacRow_2.setVisibility(View.VISIBLE);
			       
			       txtViewLocRow_3.setVisibility(View.VISIBLE);
			       txtViewSizeRow_3.setVisibility(View.VISIBLE);
			       txtViewTotalRow_3.setVisibility(View.VISIBLE);
			       txtViewMistRow_3.setVisibility(View.VISIBLE);
			       txtViewDwellRow_3.setVisibility(View.VISIBLE);
			       txtViewZvacRow_3.setVisibility(View.VISIBLE);
			       
			       txtViewLocRow_4.setVisibility(View.VISIBLE);
			       txtViewSizeRow_4.setVisibility(View.VISIBLE);
			       txtViewTotalRow_4.setVisibility(View.VISIBLE);
			       txtViewMistRow_4.setVisibility(View.VISIBLE);
			       txtViewDwellRow_4.setVisibility(View.VISIBLE);
			       txtViewZvacRow_4.setVisibility(View.VISIBLE);
			       
			       txtViewLocRow_5.setVisibility(View.VISIBLE);
			       txtViewSizeRow_5.setVisibility(View.VISIBLE);
			       txtViewTotalRow_5.setVisibility(View.VISIBLE);
			       txtViewMistRow_5.setVisibility(View.VISIBLE);
			       txtViewDwellRow_5.setVisibility(View.VISIBLE);
			       txtViewZvacRow_5.setVisibility(View.VISIBLE);
			       
			       Row1 = TotalReport;
			       Row2 = TotalReport - 1;
			       Row3 = TotalReport - 2;
			       Row4 = TotalReport - 3;
			       Row5 = TotalReport - 4;
			       
				
			}
			 
	 		 
		 }
		
		
		
		for(id = IdCount; id <= TotalReport; id++){
			
			//Toast.makeText(this, "TotalReport" + id, Toast.LENGTH_SHORT).show();
			
			if (Row1 == TotalCounter && TotalCounter > 5){
    			
    			imBtnReturn.setVisibility(View.INVISIBLE);
    			imBtnNext.setVisibility(View.VISIBLE);		
    		}
			
			if(IdCount == LeastCounter && TotalCounter > 5){
				
				imBtnNext.setVisibility(View.INVISIBLE);
				imBtnReturn.setVisibility(View.VISIBLE);
			}
			
		
    		
			
			if(id == Row1){
				
				//Toast.makeText(this, "Row - 1:" + Row1, Toast.LENGTH_SHORT).show();
				//txtViewLocRow_1.setText(Integer.toString(id));
					
				SetVisibility();
				if (id <= 0){
					if (TotalCounter > 5){
						imBtnReturn.setVisibility(View.VISIBLE);
					}
					
				} else {
					TreatmentReports Results = db.getReport(id);
					
					 locationId = Results.getLocation();
					 typeConfigure = Results.gettypeConfigure();
					 TotalMistReport = Results.getMistTime();
					 TotalDwellReport = Results.getDwellTime();
					 TotalZvacReport = Results.getZvacTime();
					 TotalRuntime = Results.getTotalTime();
				
					 txtViewLocRow_1.setText(locationId);
					 txtViewSizeRow_1.setText(typeConfigure);
					 txtViewTotalRow_1.setText(TotalRuntime);
					 txtViewMistRow_1.setText(TotalMistReport);
					 txtViewDwellRow_1.setText(TotalDwellReport);
					 txtViewZvacRow_1.setText(TotalZvacReport);
			       
				}
					
				
			}
			
			if(id == Row2){
				
			
				//Toast.makeText(this, "Row - 2:" + Row2, Toast.LENGTH_SHORT).show();
				//txtViewLocRow_2.setText(Integer.toString(id));
				SetVisibility();
				if (id <= 0){
					
					if (TotalCounter > 5){
						imBtnReturn.setVisibility(View.VISIBLE);
					}
					
				}else{
				TreatmentReports Results = db.getReport(id);
				
				 locationId = Results.getLocation();
				 typeConfigure = Results.gettypeConfigure();
				 TotalMistReport = Results.getMistTime();
				 TotalDwellReport = Results.getDwellTime();
				 TotalZvacReport = Results.getZvacTime();
				 TotalRuntime = Results.getTotalTime();
			
				 txtViewLocRow_2.setText(locationId);
				 txtViewSizeRow_2.setText(typeConfigure);
				 txtViewTotalRow_2.setText(TotalRuntime);
				 txtViewMistRow_2.setText(TotalMistReport);
				 txtViewDwellRow_2.setText(TotalDwellReport);
				 txtViewZvacRow_2.setText(TotalZvacReport);
				
				}	
				
				
			}
			
			if(id == Row3){
				
				//Toast.makeText(this, "Row - 3:" + Row3, Toast.LENGTH_SHORT).show();
				//txtViewLocRow_3.setText(Integer.toString(id));
				SetVisibility();
				if (id <= 0){
					
					if (TotalCounter > 5){
						imBtnReturn.setVisibility(View.VISIBLE);
					}
					
				}else{
				TreatmentReports Results = db.getReport(id);
				
				 locationId = Results.getLocation();
				 typeConfigure = Results.gettypeConfigure();
				 TotalMistReport = Results.getMistTime();
				 TotalDwellReport = Results.getDwellTime();
				 TotalZvacReport = Results.getZvacTime();
				 TotalRuntime = Results.getTotalTime();
			
				 txtViewLocRow_3.setText(locationId);
				 txtViewSizeRow_3.setText(typeConfigure);
				 txtViewTotalRow_3.setText(TotalRuntime);
				 txtViewMistRow_3.setText(TotalMistReport);
				 txtViewDwellRow_3.setText(TotalDwellReport);
				 txtViewZvacRow_3.setText(TotalZvacReport);
				 
				}
				
				
			}
			
			if(id == Row4){
				
				//Toast.makeText(this, "Row - 4:" + Row4, Toast.LENGTH_SHORT).show();
				//txtViewLocRow_4.setText(Integer.toString(id));
				
				SetVisibility();
				if (id <= 0){
					
					if (TotalCounter > 5){
						imBtnReturn.setVisibility(View.VISIBLE);
					}
					
				}else {
				TreatmentReports Results = db.getReport(id);
				
				 locationId = Results.getLocation();
				 typeConfigure = Results.gettypeConfigure();
				 TotalMistReport = Results.getMistTime();
				 TotalDwellReport = Results.getDwellTime();
				 TotalZvacReport = Results.getZvacTime();
				 TotalRuntime = Results.getTotalTime();
			
				 txtViewLocRow_4.setText(locationId);
				 txtViewSizeRow_4.setText(typeConfigure);
				 txtViewTotalRow_4.setText(TotalRuntime);
				 txtViewMistRow_4.setText(TotalMistReport);
				 txtViewDwellRow_4.setText(TotalDwellReport);
				 txtViewZvacRow_4.setText(TotalZvacReport);
				
				}
				
			}
			
			if(id == Row5){
				
				//Toast.makeText(this, "Row - 5:" + Row5, Toast.LENGTH_SHORT).show();
				//txtViewLocRow_5.setText(Integer.toString(id));
				
				SetVisibility();
				if (id <= 0){
					
					if (TotalCounter > 5){
						imBtnReturn.setVisibility(View.VISIBLE);
					}
					
				}else {
				TreatmentReports Results = db.getReport(id);
				
				 locationId = Results.getLocation();
				 typeConfigure = Results.gettypeConfigure();
				 TotalMistReport = Results.getMistTime();
				 TotalDwellReport = Results.getDwellTime();
				 TotalZvacReport = Results.getZvacTime();
				 TotalRuntime = Results.getTotalTime();
			
				 txtViewLocRow_5.setText(locationId);
				 txtViewSizeRow_5.setText(typeConfigure);
				 txtViewTotalRow_5.setText(TotalRuntime);
				 txtViewMistRow_5.setText(TotalMistReport);
				 txtViewDwellRow_5.setText(TotalDwellReport);
				 txtViewZvacRow_5.setText(TotalZvacReport);
				
				}
				
			}
			
			db.close();			
			
		}

		
		
	
		
	}
	
	public void SetVisibility(){
		
		if ((id == Row5) && (Row5 <= 0)){
			
			//Toast.makeText(this, "True" + Row5, Toast.LENGTH_SHORT).show();
			
			   imView_5.setVisibility(View.INVISIBLE);		       
		       imBtn_5.setVisibility(View.INVISIBLE);
		       imBtnNext.setVisibility(View.INVISIBLE);
		       imBtnReturn.setVisibility(View.VISIBLE);

		       txtViewLocRow_5.setVisibility(View.INVISIBLE);
		       txtViewSizeRow_5.setVisibility(View.INVISIBLE);
		       txtViewTotalRow_5.setVisibility(View.INVISIBLE);
		       txtViewMistRow_5.setVisibility(View.INVISIBLE);
		       txtViewDwellRow_5.setVisibility(View.INVISIBLE);
		       txtViewZvacRow_5.setVisibility(View.INVISIBLE);
		}
		
		if ((id == Row4) && (Row4 <= 0)){
			
			//Toast.makeText(this, "True" + Row5, Toast.LENGTH_SHORT).show();
			
			   imView_4.setVisibility(View.INVISIBLE);		       
		       imBtn_4.setVisibility(View.INVISIBLE);
		       imBtnNext.setVisibility(View.INVISIBLE);

		       txtViewLocRow_4.setVisibility(View.INVISIBLE);
		       txtViewSizeRow_4.setVisibility(View.INVISIBLE);
		       txtViewTotalRow_4.setVisibility(View.INVISIBLE);
		       txtViewMistRow_4.setVisibility(View.INVISIBLE);
		       txtViewDwellRow_4.setVisibility(View.INVISIBLE);
		       txtViewZvacRow_4.setVisibility(View.INVISIBLE);
		       imBtnReturn.setVisibility(View.VISIBLE);
		}
		
		if ((id == Row3) && (Row3 <= 0)){
			
			//Toast.makeText(this, "True" + Row5, Toast.LENGTH_SHORT).show();
			
			   imView_3.setVisibility(View.INVISIBLE);		       
		       imBtn_3.setVisibility(View.INVISIBLE);
		       imBtnNext.setVisibility(View.INVISIBLE);

		       txtViewLocRow_3.setVisibility(View.INVISIBLE);
		       txtViewSizeRow_3.setVisibility(View.INVISIBLE);
		       txtViewTotalRow_3.setVisibility(View.INVISIBLE);
		       txtViewMistRow_3.setVisibility(View.INVISIBLE);
		       txtViewDwellRow_3.setVisibility(View.INVISIBLE);
		       txtViewZvacRow_3.setVisibility(View.INVISIBLE);
		       imBtnReturn.setVisibility(View.VISIBLE);
		}
		
		if ((id == Row2) && (Row2 <= 0)){
			
			//Toast.makeText(this, "True" + Row5, Toast.LENGTH_SHORT).show();
			
			   imView_2.setVisibility(View.INVISIBLE);		       
		       imBtn_2.setVisibility(View.INVISIBLE);
		       imBtnNext.setVisibility(View.INVISIBLE);

		       txtViewLocRow_2.setVisibility(View.INVISIBLE);
		       txtViewSizeRow_2.setVisibility(View.INVISIBLE);
		       txtViewTotalRow_2.setVisibility(View.INVISIBLE);
		       txtViewMistRow_2.setVisibility(View.INVISIBLE);
		       txtViewDwellRow_2.setVisibility(View.INVISIBLE);
		       txtViewZvacRow_2.setVisibility(View.INVISIBLE);
		       imBtnReturn.setVisibility(View.VISIBLE);
		}
		
		if ((id == Row1) && (Row1 <= 0)){
			
			//Toast.makeText(this, "True" + Row5, Toast.LENGTH_SHORT).show();
			imBtnNext.setVisibility(View.INVISIBLE);
			   imView_1.setVisibility(View.INVISIBLE);		       
		       imBtn_1.setVisibility(View.INVISIBLE);

		       txtViewLocRow_1.setVisibility(View.INVISIBLE);
		       txtViewSizeRow_1.setVisibility(View.INVISIBLE);
		       txtViewTotalRow_1.setVisibility(View.INVISIBLE);
		       txtViewMistRow_1.setVisibility(View.INVISIBLE);
		       txtViewDwellRow_1.setVisibility(View.INVISIBLE);
		       txtViewZvacRow_1.setVisibility(View.INVISIBLE);
		       imBtnReturn.setVisibility(View.VISIBLE);
		}
		
	}

	

	
	public void onClick(View v){
    	if(v==imBtnReturn){
    		
    		IdCount = IdCount + 5;
    		TotalReport = TotalReport + 5;
    		
    		counter = counter + 5;
	
    		Row1 = TotalReport + 1;
    		Row2 = TotalReport + 2;
    		Row3 = TotalReport + 3;
    		Row4 = TotalReport + 4;
    		Row5 = TotalReport + 5;
    		
  
    		if (counter > 5){
    			imBtnNext.setVisibility(View.VISIBLE);
    			
    		}
    	
    		RepeatReport();
    	
    		
    	}
    	
    	if(v==imBtnNext){
    				
    		IdCount = IdCount - 5;
    		TotalReport = TotalReport - 5;
    		
    		//if(IdCount > 5){
    		
    		Row1 = TotalReport - 1;
    		Row2 = TotalReport - 2;
    		Row3 = TotalReport - 3;
    		Row4 = TotalReport - 4;
    		Row5 = TotalReport - 5;
    		
    		//}
    		
    		//if(IdCount < 5){
    			
    		//	Row1 = TotalReport;
    		//	Row2 = 2;
    		//	Row3 = 3;
    		//	Row4 = 4;
    		//	Row5 = 5;
    			
    			
    			
    		//}
    		
    		
    		counter = counter + 5;
    		
    		if(counter == 15){
    			
    			imBtnNext.setVisibility(View.INVISIBLE);
    			imBtnReturn.setVisibility(View.VISIBLE);
    			
    			
    		}
    		
    		if(TotalCounter > 15){
    			LeastCounter = TotalCounter - 15;
    		}
    		
    		if (TotalReport > 5){
    			imBtnReturn.setVisibility(View.VISIBLE);
    			
    		}
    		

    	
    		//Toast.makeText(this, "TotalReport IDCOUNT:" + TotalReport + IdCount, Toast.LENGTH_LONG).show();
    		
    		RepeatReport();
    		
    		


    		
    	}
    	
    	if(v==imBtnControlCenter){
    		Intent resultIntent = new Intent();
  			resultIntent.setClass(this,ZimekActivity.class);
  			setResult(Activity.RESULT_OK, resultIntent);
  			startActivity(resultIntent);
			//onDestroy();
    		
    	}
    	
    	if(v==imBtn_1){
    		
    		//Toast.makeText(this, "Button1 pressed", Toast.LENGTH_LONG).show();
    		
    		locationId = txtViewLocRow_1.getText().toString();
    		typeConfigure = txtViewSizeRow_1.getText().toString();
    		TotalRuntime =	txtViewTotalRow_1.getText().toString();
    		MistTime = txtViewMistRow_1.getText().toString();
    		DwellTime = txtViewDwellRow_1.getText().toString();
    		ZvacTime = txtViewZvacRow_1.getText().toString();
    		
    		SendValues();
    		//onDestroy();
    		
    			
    	}
    	
    	if(v==imBtn_2){
    		
    		
    		locationId = txtViewLocRow_2.getText().toString();
    		typeConfigure = txtViewSizeRow_2.getText().toString();
    		TotalRuntime =	txtViewTotalRow_2.getText().toString();
    		MistTime = txtViewMistRow_2.getText().toString();
    		DwellTime = txtViewDwellRow_2.getText().toString();
    		ZvacTime = txtViewZvacRow_2.getText().toString();
    		
    		
    		SendValues();
    		
    	}

		if(v==imBtn_3){
	
			locationId = txtViewLocRow_3.getText().toString();
    		typeConfigure = txtViewSizeRow_3.getText().toString();
    		TotalRuntime =	txtViewTotalRow_3.getText().toString();
    		MistTime = txtViewMistRow_3.getText().toString();
    		DwellTime = txtViewDwellRow_3.getText().toString();
    		ZvacTime = txtViewZvacRow_3.getText().toString();
	
	
			SendValues();
	
	
		}

		if(v==imBtn_4){
	
			locationId = txtViewLocRow_4.getText().toString();
    		typeConfigure = txtViewSizeRow_4.getText().toString();
    		TotalRuntime =	txtViewTotalRow_4.getText().toString();
    		MistTime = txtViewMistRow_4.getText().toString();
    		DwellTime = txtViewDwellRow_4.getText().toString();
    		ZvacTime = txtViewZvacRow_4.getText().toString();
	
	
			SendValues();
	
	
		}

		if(v==imBtn_5){
	
			locationId = txtViewLocRow_5.getText().toString();
    		typeConfigure = txtViewSizeRow_5.getText().toString();
    		TotalRuntime =	txtViewTotalRow_5.getText().toString();
    		MistTime = txtViewMistRow_5.getText().toString();
    		DwellTime = txtViewDwellRow_5.getText().toString();
    		ZvacTime = txtViewZvacRow_5.getText().toString();
	
	
			SendValues();
	
	
		}


    }
	
	public void SendValues(){
		
		String RepeatApp = "0";
		
		StringTokenizer token = new StringTokenizer(MistTime, ":");
		MistHour = token.nextToken();
	    MistMin = token.nextToken();
	    
	    StringTokenizer tokens = new StringTokenizer(DwellTime, ":");
		DwellHour = tokens.nextToken();
	    DwellMin = tokens.nextToken();
	    
	    StringTokenizer tokensz = new StringTokenizer(ZvacTime, ":");
		ZvacHour = tokensz.nextToken();
	    ZvacMin = tokensz.nextToken();
		
		
		Intent intent = new Intent();
		intent.setClass(this,Repeat_Application_Activity.class);
		intent.putExtra("locationId", locationId);
		intent.putExtra("typeApplication", typeApplication);
		intent.putExtra("typeConfigure", typeConfigure);
		intent.putExtra("MistHour", MistHour);
		intent.putExtra("MistMin", MistMin);
		intent.putExtra("DwellHour", DwellHour);
		intent.putExtra("DwellMin", DwellMin);
		intent.putExtra("ZvacHour", ZvacHour);
		intent.putExtra("ZvacMin", ZvacMin);
		intent.putExtra("RepeatApp", RepeatApp);
		startActivity(intent);
		
		onDestroy();
		
	}
	
	public void onDestroy()
	  {   
	    //  Cleanup();
	      super.onDestroy();
	  }

	//  private void Cleanup()
	  //{    
	  	
	  //    System.gc();
	    //  Runtime.getRuntime().gc();  
	  //}
	
	
}
