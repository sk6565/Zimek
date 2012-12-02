package com.circuit.zimek;


import com.PassCode_Report.PassCodeDatabaseHandler;
import com.PassCode_Report.PassCodeReports;
import com.SystemInfo_Report.SystemInfoReports;
import com.TSS_Report.TreatmentReports;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.view.View.OnKeyListener;
import android.view.inputmethod.InputMethodManager;
import android.view.KeyEvent;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;



public class Passcode_Edit_Activity extends Activity implements OnClickListener{
	
	
	 private ImageButton imBtnOPS_Activate;
	 private ImageButton imBtnSaveMGT;
	 private ImageButton imBtnSaveOPS;
	 private ImageButton imBtnReturn;
	 private ImageButton imBtnWarning;
	 
	 private ImageView imViewPasscodeHead;
	 private ImageView imViewMGTicon;
	 private ImageView imViewEnterPassCodeMGT;
	 private ImageView imViewEnterPassCodeOPS;
	 private ImageView imViewEnterNewPassCodeMGT;
	 private ImageView imViewEnterNewPassCodeOPS;
	 
	 
	 private EditText EdTPassCodeMGT; 
	 private EditText EdTPassCodeOPS; 
	 private EditText EdTNewPassCodeMGT; 
	 private EditText EdTNewPassCodeOPS; 
	 public String language;
	 
	 public String MGTPassCode;
	 public String OPSPassCode;
	 public String MGTNewPassCode;
	 public String OPSNewPassCode;
	 public String OPSActivated;
     public CommonState commonState = null;

	 

	 

	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().getDecorView().setSystemUiVisibility(View.STATUS_BAR_HIDDEN);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, 
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.passcode_edit_app);
        
        EdTPassCodeMGT = (EditText)findViewById(R.id.EdTPassCodeMGT);
        EdTPassCodeMGT.setText("*****");
        EdTPassCodeOPS = (EditText)findViewById(R.id.EdTPassCodeOPS);
        EdTPassCodeOPS.setText("*****");
        EdTNewPassCodeMGT = (EditText)findViewById(R.id.EdTNewPassCodeMGT);
        EdTNewPassCodeMGT.setText("*****");
        EdTNewPassCodeOPS = (EditText)findViewById(R.id.EdTNewPassCodeOPS);
        EdTNewPassCodeOPS.setText("*****");

        commonState = (CommonState) getApplication();
        commonState.activity_name = "Passcode_Edit_Activity";
        

        
        language = commonState.language;

    	
    	if (language.equals("English")){
    		
    		imBtnWarning = (ImageButton)findViewById(R.id.imBtnWarning);
    		imBtnReturn = (ImageButton)findViewById(R.id.imBtnReturn); 		 
    		imBtnSaveOPS = (ImageButton)findViewById(R.id.imBtnSaveOPS);
    		imBtnSaveMGT = (ImageButton)findViewById(R.id.imBtnSaveMGT);
    		
    		
    		imViewPasscodeHead = (ImageView)findViewById(R.id.imViewPasscodeHead);
    		imViewMGTicon = (ImageView)findViewById(R.id.imViewMGTicon);
    		imViewEnterPassCodeMGT = (ImageView)findViewById(R.id.imViewEnterPassCodeMGT);
    		imViewEnterPassCodeOPS = (ImageView)findViewById(R.id.imViewEnterPassCodeOPS);
    		imViewEnterNewPassCodeMGT = (ImageView)findViewById(R.id.imViewEnterNewPassCodeMGT);
    		imViewEnterNewPassCodeOPS = (ImageView)findViewById(R.id.imViewEnterNewPassCodeOPS);

		   	  
    	} 
    	
	if (language.equals("Spanish")){
    		
			imBtnWarning = (ImageButton)findViewById(R.id.imBtnWarning);
			imBtnWarning.setImageResource(R.drawable.warning_spa);
		 
			imBtnReturn = (ImageButton)findViewById(R.id.imBtnReturn);
			imBtnReturn.setImageResource(R.drawable.return_spa);
			
    		imBtnSaveOPS = (ImageButton)findViewById(R.id.imBtnSaveOPS);
    		imBtnSaveOPS.setImageResource(R.drawable.save_ops_spa);
    		
    		imBtnSaveMGT = (ImageButton)findViewById(R.id.imBtnSaveMGT);
    		imBtnSaveMGT.setImageResource(R.drawable.save_mgt_spa);
    		

    		imViewPasscodeHead = (ImageView)findViewById(R.id.imViewPasscodeHead);
    		imViewPasscodeHead.setImageResource(R.drawable.passcodes_head_spa);
    		
    		
    		imViewMGTicon = (ImageView)findViewById(R.id.imViewMGTicon);
    		imViewMGTicon.setImageResource(R.drawable.mgt_icon);
    		
    		
    		imViewEnterPassCodeMGT = (ImageView)findViewById(R.id.imViewEnterPassCodeMGT);
    		imViewEnterPassCodeMGT.setImageResource(R.drawable.enter_passcode_edit_spa);
    		
    		
    		imViewEnterPassCodeOPS = (ImageView)findViewById(R.id.imViewEnterPassCodeOPS);
    		imViewEnterPassCodeOPS.setImageResource(R.drawable.enter_passcode_edit_spa);
    		
    		imViewEnterNewPassCodeMGT = (ImageView)findViewById(R.id.imViewEnterNewPassCodeMGT);
    		imViewEnterNewPassCodeMGT.setImageResource(R.drawable.enter_new_passcode_spa);
    		
    		imViewEnterNewPassCodeOPS = (ImageView)findViewById(R.id.imViewEnterNewPassCodeOPS);
    		imViewEnterNewPassCodeOPS.setImageResource(R.drawable.enter_new_passcode_spa);

		   	  
    	} 
	
	imBtnReturn.setOnClickListener(this);
    imBtnWarning.setOnClickListener(this);
    imBtnSaveMGT.setOnClickListener(this);
    imBtnSaveOPS.setOnClickListener(this);
    
    EdTPassCodeMGT.setOnClickListener(this);
    EdTPassCodeOPS.setOnClickListener(this);
    EdTNewPassCodeMGT.setOnClickListener(this);
    EdTNewPassCodeOPS.setOnClickListener(this);
    
    imBtnWarning.setVisibility(View.INVISIBLE);
    imBtnSaveMGT.setVisibility(View.INVISIBLE);
    imBtnSaveOPS.setVisibility(View.INVISIBLE);
    //imBtnSaveOPS.setVisibility(View.INVISIBLE);
    
    int id = 1;
    //int CountReports;
    
    PassCodeDatabaseHandler db = new PassCodeDatabaseHandler(this);
    
  int PassCodeCount = db.getReportsCount();
	
    if(PassCodeCount == 0){
    	
    	String defaultPassCode = "2";
    	String defaultPassCodeOPS = "1";
    	String NewPassCodeOPS = "1";
    	String PassCodeOPSActivated = "0";
    	String newPassCode = "2";
    	id = PassCodeCount;
    	
    	db.addReport(new PassCodeReports(id, defaultPassCode, newPassCode, defaultPassCodeOPS, NewPassCodeOPS, PassCodeOPSActivated));
		
    }
    
    PassCodeReports Results = db.getReport(id);
    
    
    MGTPassCode = Results.getdefaultPassCode();
    MGTNewPassCode = Results.getnewPassCode();
    OPSPassCode = Results.getdefaultPassCodeOPS();
    OPSNewPassCode = Results.getNewPassCodeOPS();
    OPSActivated = Results.getPassCodeOPSActivated();
    
    
    
    if (OPSActivated.equals("")){
    	
    	OPSActivated = "0";
    	
    	db.updateContents(new PassCodeReports(id, MGTPassCode, MGTNewPassCode, OPSPassCode, OPSNewPassCode, OPSActivated));
		
		
	}
    
    if (OPSPassCode.equals("")){
    	
    	OPSPassCode = "1";
    	OPSNewPassCode = "1";
    	
    	db.updateContents(new PassCodeReports(id, MGTPassCode, MGTNewPassCode, OPSPassCode, OPSNewPassCode, OPSActivated));
    	
    	
    }
    
    if (MGTPassCode.equals("")){
    	
    	MGTPassCode = "2";
    	MGTNewPassCode = "2";
    	
    	db.updateContents(new PassCodeReports(id, MGTPassCode, MGTNewPassCode, OPSPassCode, OPSNewPassCode, OPSActivated));
    	
    	
    }
    
    
    
    db.close();
    
    
    EdTPassCodeMGT.requestFocus();
    
    EdTPassCodeMGT.setOnKeyListener(new OnKeyListener() {
		public boolean onKey(View v, int keyCode, KeyEvent event) {
			
			InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
		      imm.showSoftInput(EdTPassCodeMGT, InputMethodManager.SHOW_IMPLICIT);
	 
			//if keydown and "enter" is pressed
			if (keyCode == KeyEvent.KEYCODE_ENTER) {
				

				
				
				EdTNewPassCodeMGT.setText("");
				EdTNewPassCodeMGT.requestFocus();
				EdTNewPassCodeMGT.hasFocus();
				MGT();
				
				HideKeyBoard();
				
				return true;
			
			}
				
			return false;
		}	
		
		});
    
    EdTNewPassCodeMGT.setOnFocusChangeListener(new OnFocusChangeListener()
    {
       
        public void onFocusChange(View v, boolean hasFocus) 
        {
            
            if (hasFocus==true)
            {   
            	
            	InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
  		      	imm.showSoftInput(EdTNewPassCodeMGT, InputMethodManager.SHOW_IMPLICIT);
                
            }
        }
    });
    
    EdTNewPassCodeMGT.setOnKeyListener(new OnKeyListener() {
		public boolean onKey(View v, int keyCode, KeyEvent event) {
			
			InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
		      imm.showSoftInput(EdTNewPassCodeMGT, InputMethodManager.SHOW_IMPLICIT);
	 
			//if keydown and "enter" is pressed
			if (keyCode == KeyEvent.KEYCODE_ENTER) {
				
				MGT();
				HideKeyBoard();
				
				return true;
			
			}
				
			return false;
		}	
		
		});
    
    
    
    EdTPassCodeOPS.setOnKeyListener(new OnKeyListener() {
		public boolean onKey(View v, int keyCode, KeyEvent event) {
			
			InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
		      imm.showSoftInput(EdTPassCodeOPS, InputMethodManager.SHOW_IMPLICIT);
	 
			//if keydown and "enter" is pressed
			if (keyCode == KeyEvent.KEYCODE_ENTER) {
				
				//MGT();
				EdTNewPassCodeOPS.requestFocus();
				EdTNewPassCodeOPS.hasFocus();
				EdTNewPassCodeOPS.setText("");
				
				
				
				OPSPASSCODE();
				
				//HideKeyBoard();
				
				return true;
			
			}
				
			return false;
		}	
		
		});
    
    EdTPassCodeOPS.setOnFocusChangeListener(new OnFocusChangeListener()
    {
       
        public void onFocusChange(View v, boolean hasFocus) 
        {
            
            if (hasFocus==true)
            {   
            	
            	EdTPassCodeOPS.setText("");
            	InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
  		      	imm.showSoftInput(EdTNewPassCodeOPS, InputMethodManager.SHOW_IMPLICIT);
                
            }
        }
    });
    
    EdTNewPassCodeOPS.setOnFocusChangeListener(new OnFocusChangeListener()
    {
       
        public void onFocusChange(View v, boolean hasFocus) 
        {
            
            if (hasFocus==true)
            {   
            	
            	
            	InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
  		      	imm.showSoftInput(EdTNewPassCodeOPS, InputMethodManager.SHOW_IMPLICIT);
                
            }
        }
    });
    
    EdTNewPassCodeOPS.setOnKeyListener(new OnKeyListener() {
		public boolean onKey(View v, int keyCode, KeyEvent event) {
			
			InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
		      imm.showSoftInput(EdTNewPassCodeOPS, InputMethodManager.SHOW_IMPLICIT);
	 
			//if keydown and "enter" is pressed
			if (keyCode == KeyEvent.KEYCODE_ENTER) {
				
				EdTNewPassCodeOPS.requestFocus();
				EdTNewPassCodeOPS.hasFocus();
				//EdTNewPassCodeOPS.setText("");
				OPSPASSCODE();
				HideKeyBoard();
				
				return true;
			
			}
				
			return false;
		}	
		
		});
    
    
    MGT();
    OPS();
    
    imBtnOPS_Activate.setOnClickListener(this);
     
        
    }
    
    
    public void MGT(){
    	int id = 1;
    	
    	PassCodeDatabaseHandler db = new PassCodeDatabaseHandler(this);
    	
    	 PassCodeReports Results = db.getReport(id);
    	 
    	 MGTPassCode = Results.getnewPassCode();
    	 String MGTPassCode_Old = EdTPassCodeMGT.getText().toString();
    	 String MGTPassCode_New = EdTNewPassCodeMGT.getText().toString();
    	 
    	 if(MGTPassCode.equals(MGTPassCode_Old) && !MGTPassCode_New.equals("")){
    		 
    		 imBtnSaveMGT.setVisibility(View.VISIBLE);
    		 
    	 } else {
    		 
    		 imBtnSaveMGT.setVisibility(View.INVISIBLE);
    		 
    	 }
    	 	 
    	 
    	 db.close();
    	  	
    	
    }
    
    public void OPSPASSCODE(){
    	
    	int id = 1;
    	
    	PassCodeDatabaseHandler db = new PassCodeDatabaseHandler(this);
    	
    	 PassCodeReports Results = db.getReport(id);
    	 
    	 OPSPassCode = Results.getNewPassCodeOPS();
    	 String OPSPassCode_Old = EdTPassCodeOPS.getText().toString();
    	 String OPSPassCode_New = EdTNewPassCodeOPS.getText().toString();
    	 
    	 if(OPSPassCode.equals(OPSPassCode_Old) && !OPSPassCode_New.equals("")){
    		 
    		 imBtnSaveOPS.setVisibility(View.VISIBLE);
    		 
    	 } else {
    		 
    		 imBtnSaveOPS.setVisibility(View.INVISIBLE);
    		 
    	 }
    	 	 
    	 
    	 db.close();
    	
    	
    	
    }
    
   public void OPS(){
    	
	   if(OPSActivated.equals("0")){
	    	
	    	if(language.equals("English")){
	    			
	    		
	    		imBtnOPS_Activate = (ImageButton)findViewById(R.id.imBtnOPS_Activate);
	    		imBtnOPS_Activate.setImageResource(R.drawable.ops_press_to_activate_eng);
	    		
	    		
	    	}
	    	
	    	if(language.equals("Spanish")){
	    		
	    		imBtnOPS_Activate = (ImageButton)findViewById(R.id.imBtnOPS_Activate);
	    		imBtnOPS_Activate.setImageResource(R.drawable.ops_press_to_activate_spa);
	    		
	    		
	    	}
	    	
	    	imBtnSaveOPS.setVisibility(View.INVISIBLE);
	    	EdTPassCodeOPS.setVisibility(View.INVISIBLE);
	    	EdTNewPassCodeOPS.setVisibility(View.INVISIBLE);
	    	imViewEnterNewPassCodeOPS.setVisibility(View.INVISIBLE);
	    	imViewEnterPassCodeOPS.setVisibility(View.INVISIBLE);
	    		
	    } else {
	    	
	    	if(OPSActivated.equals("1")){
	    		
	    		if(language.equals("English")){
	    			
	    			imBtnOPS_Activate = (ImageButton)findViewById(R.id.imBtnOPS_Activate);
	    			imBtnOPS_Activate.setImageResource(R.drawable.ops_press_to_deactivate_eng);
	        		
	        		
	        	}
	        	
	        	if(language.equals("Spanish")){
	        		
	        		imBtnOPS_Activate = (ImageButton)findViewById(R.id.imBtnOPS_Activate);
	    			imBtnOPS_Activate.setImageResource(R.drawable.ops_press_to_deactivate_spa);
	        		
	        		
	        		
	        	}
	    		
	    		EdTPassCodeOPS.setVisibility(View.VISIBLE);
	        	EdTNewPassCodeOPS.setVisibility(View.VISIBLE);
	        	imViewEnterNewPassCodeOPS.setVisibility(View.VISIBLE);
	        	imViewEnterPassCodeOPS.setVisibility(View.VISIBLE);
	    			
	    		
	    	}
	    	
	    }
    	
    	
    	
    }
    
 public void HideKeyBoard(){
    	
    	InputMethodManager inputManager = (InputMethodManager)this.getSystemService(Context.INPUT_METHOD_SERVICE); 
	    inputManager.hideSoftInputFromWindow(this.getCurrentFocus().getWindowToken(),      
	    InputMethodManager.HIDE_NOT_ALWAYS);
    	
    }
    
    
    
    public void onClick(View v){
    	
    	if (v==EdTPassCodeMGT){
    		
    		//EdTPassCodeMGT.requestFocus();
    		//EdTPassCodeMGT.hasFocus();
    		
    		EdTPassCodeMGT.setText("");
    		//HideKeyBoard();
    		
    		
    	}
    	
    	if (v==EdTNewPassCodeMGT){
    		
    		//EdTNewPassCodeMGT.requestFocus();
    		//EdTNewPassCodeOPS.hasFocus();
    		
    		EdTNewPassCodeMGT.setText("");
    		//HideKeyBoard();
    		
    		
    	}
    	
    	if (v==EdTPassCodeOPS){
    		
    		
    		
    		EdTPassCodeOPS.setText("");
    		
    		//EdTPassCodeOPS.requestFocus();
    		//EdTPassCodeOPS.hasFocus();
    		
    		EdTPassCodeOPS.setText("");
    		//HideKeyBoard();
    		
    		
    	}
    	
    	if (v==EdTNewPassCodeOPS){
    		
    		//EdTNewPassCodeOPS.requestFocus();
    		//EdTNewPassCodeOPS.hasFocus();
    		
    		EdTNewPassCodeOPS.setText("");
    		//HideKeyBoard();
    		
    		
    	}
    	
    	if (v==imBtnSaveMGT){
    		
    		int id = 1;
        	
        	PassCodeDatabaseHandler db = new PassCodeDatabaseHandler(this);
        	
        	PassCodeReports Results = db.getReport(id);

            
            if (imBtnSaveMGT.getVisibility() == View.VISIBLE && imBtnSaveOPS.getVisibility() == View.VISIBLE) {
            	
            	MGTPassCode = Results.getdefaultPassCode();
                MGTNewPassCode = EdTNewPassCodeMGT.getText().toString();
                OPSPassCode = Results.getdefaultPassCodeOPS();
                OPSNewPassCode = EdTNewPassCodeOPS.getText().toString();
                OPSActivated = Results.getPassCodeOPSActivated();
            	
                // Its visible
            } else {
            	
            	MGTPassCode = Results.getdefaultPassCode();
                MGTNewPassCode = EdTNewPassCodeMGT.getText().toString();
                OPSPassCode = Results.getdefaultPassCodeOPS();
                OPSNewPassCode = Results.getNewPassCodeOPS();
                OPSActivated = Results.getPassCodeOPSActivated();
        		
                 
    
            }
            
            db.updateContents(new PassCodeReports(id, MGTPassCode, MGTNewPassCode, OPSPassCode, OPSNewPassCode, OPSActivated));     
            	db.close();
            	
            	EdTNewPassCodeMGT.clearFocus();
        		Intent resultIntent = new Intent();
    			resultIntent.setClass(this,Passcode_Edit_Activity.class);
    			setResult(Activity.RESULT_OK, resultIntent);
    			startActivity(resultIntent);
            
            
            
    		
    	}
    	
    	
    	if (v==imBtnSaveOPS){
    		
    		int id = 1;
        	
        	PassCodeDatabaseHandler db = new PassCodeDatabaseHandler(this);
        	
        	PassCodeReports Results = db.getReport(id);
            
            
        	 if (imBtnSaveMGT.getVisibility() == View.VISIBLE && imBtnSaveOPS.getVisibility() == View.VISIBLE) {
             	
             	MGTPassCode = Results.getdefaultPassCode();
                 MGTNewPassCode = EdTNewPassCodeMGT.getText().toString();
                 OPSPassCode = Results.getdefaultPassCodeOPS();
                 OPSNewPassCode = EdTNewPassCodeOPS.getText().toString();
                 OPSActivated = Results.getPassCodeOPSActivated();
         		
             	
                 // Its visible
             } else {
             	
            	 MGTPassCode = Results.getdefaultPassCode();
                 MGTNewPassCode = Results.getnewPassCode();
                 OPSPassCode = Results.getdefaultPassCodeOPS();
                 OPSNewPassCode = EdTNewPassCodeOPS.getText().toString();
                 OPSActivated = Results.getPassCodeOPSActivated();
        
     
             }
        	
        	 db.updateContents(new PassCodeReports(id, MGTPassCode, MGTNewPassCode, OPSPassCode, OPSNewPassCode, OPSActivated));   
        	       
            
            db.close();
            
            Intent resultIntent = new Intent();
			resultIntent.setClass(this,Passcode_Edit_Activity.class);
			setResult(Activity.RESULT_OK, resultIntent);
			startActivity(resultIntent);
            
	
    	}
    	
    	if (v==imBtnReturn){
    		Intent resultIntent = new Intent();
			resultIntent.setClass(this,SystemSettings_Activity.class);
			setResult(Activity.RESULT_OK, resultIntent);
			startActivity(resultIntent);
    	}
    	
    	
    	if (v== imBtnOPS_Activate){
    		int id = 1;
    		
    		PassCodeDatabaseHandler db = new PassCodeDatabaseHandler(this);
    		PassCodeReports Results = db.getReport(id);
    	    
    	    
    	    MGTPassCode = Results.getdefaultPassCode();
    	    MGTNewPassCode = Results.getnewPassCode();
    	    OPSPassCode = Results.getdefaultPassCodeOPS();
    	    OPSNewPassCode = Results.getNewPassCodeOPS();
    	    OPSActivated = Results.getPassCodeOPSActivated();
    		
    		if (OPSActivated.equals("0")){
    			
    			OPSActivated = "1";
    			
    			db.updateContents(new PassCodeReports(id, MGTPassCode, MGTNewPassCode, OPSPassCode, OPSNewPassCode, OPSActivated));
    			
    			OPS();
    			
    		} else {
    			
    			if (OPSActivated.equals("1")){
        			
        			OPSActivated = "0";
        			
        			db.updateContents(new PassCodeReports(id, MGTPassCode, MGTNewPassCode, OPSPassCode, OPSNewPassCode, OPSActivated));
        			
        			OPS();
        			
        		}
        				
    			
    		}
    		
    		db.close();
    		
    	}
    	
    	
    }
    
}
