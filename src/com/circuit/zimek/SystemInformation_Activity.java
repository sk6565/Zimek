package com.circuit.zimek;


import com.PassCode_Report.PassCodeReports;
import com.SystemInfo_Report.SystemInfoHandler;
import com.SystemInfo_Report.SystemInfoReports;
import com.TSS_Report.DatabaseHandler;
import com.TSS_Report.TreatmentReports;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnKeyListener;
import android.view.View.OnTouchListener;
import android.view.inputmethod.InputMethodManager;
import android.view.KeyEvent;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;



public class SystemInformation_Activity extends Activity implements OnClickListener{
	
	 
	 private ImageButton imBtnWarning;
	 private ImageButton imBtnReturn;
	 private ImageButton imBtnSave;
	 
	 
	 private ImageButton imViewSystemInfoHead;
	 private ImageView imViewVipSerial;
	 private ImageView imViewZvacSerial;
	 private ImageView imViewOplcSerial;
	 private ImageView imViewCustomer;
	 private ImageView imViewDeliveryDate;
	 private ImageView imViewWarranty;
	 private ImageView imViewOther;
	 
	 private EditText txtEditVipSerial;
	 private EditText txtEditZvacSerial;
	 private EditText txtEditOplcSerial;
	 private EditText txtEditCustomer;
	 private EditText txtEditDeliveryDate;
	 private EditText txtEditWarranty;
	 private EditText txtEditOther;

	  
	 
	 public String language;
	 public String VipSerial;
	 public String ZvacSerial;
	 public String OplcSerial;
	 public String Customer;
	 public String DeliveryDate;
	 public String Warranty;
	 public String Other;
	 
	 private final long millisRequired = 2000;
	 private long downTime;
	 
	 public String Editable = "0";
     public CommonState commonState = null;

	 
	 

	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().getDecorView().setSystemUiVisibility(View.STATUS_BAR_HIDDEN);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, 
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.systeminformation_app);
        commonState = (CommonState) getApplication();
        commonState.activity_name = "SystemInformation_Activity";
        
        txtEditVipSerial = (EditText)findViewById(R.id.txtEditVipSerial);
        txtEditVipSerial.setOnClickListener(this);
        
        txtEditZvacSerial = (EditText)findViewById(R.id.txtEditZvacSerial);
        txtEditZvacSerial.setOnClickListener(this);
        
        txtEditOplcSerial = (EditText)findViewById(R.id.txtEditOplcSerial);
        txtEditOplcSerial.setOnClickListener(this);
        
        txtEditCustomer = (EditText)findViewById(R.id.txtEditCustomer);
        txtEditCustomer.setOnClickListener(this);
        
        txtEditDeliveryDate = (EditText)findViewById(R.id.txtEditDeliveryDate);
        txtEditDeliveryDate.setOnClickListener(this);
        
        txtEditWarranty = (EditText)findViewById(R.id.txtEditWarranty);
        txtEditWarranty.setOnClickListener(this);
        
        txtEditOther = (EditText)findViewById(R.id.txtEditOther);
        txtEditOther.setOnClickListener(this);
        
        
        language = commonState.language;
        // Editable is set in commonState now.
        // Editable = getIntent().getStringExtra("Editable");
        Editable = commonState.Editable;

    	
    	if (language.equals("English")){
    		
    		imBtnWarning = (ImageButton)findViewById(R.id.imBtnWarning);
    		imBtnReturn = (ImageButton)findViewById(R.id.imBtnReturn);
    		imBtnSave = (ImageButton)findViewById(R.id.imBtnSave);
    		
    		imViewSystemInfoHead = (ImageButton)findViewById(R.id.imViewSystemInfoHead);
    		imViewVipSerial = (ImageView)findViewById(R.id.imViewVipSerial);
    		imViewZvacSerial = (ImageView)findViewById(R.id.imViewZvacSerial);
    		imViewOplcSerial = (ImageView)findViewById(R.id.imViewOplcSerial);
    		imViewCustomer = (ImageView)findViewById(R.id.imViewCustomer);
    		imViewDeliveryDate = (ImageView)findViewById(R.id.imViewDeliveryDate);
    		imViewWarranty = (ImageView)findViewById(R.id.imViewWarranty);
    		imViewOther = (ImageView)findViewById(R.id.imViewOther);		
    

		   	  
    	} 
    	
    	if (language.equals("Spanish")){
    		
    		
    		imBtnWarning = (ImageButton)findViewById(R.id.imBtnWarning);
    		imBtnWarning.setImageResource(R.drawable.warning_spa);
   		 
   		 	imBtnReturn = (ImageButton)findViewById(R.id.imBtnReturn);
			imBtnReturn.setImageResource(R.drawable.return_spa);
    			
			imViewSystemInfoHead = (ImageButton)findViewById(R.id.imViewSystemInfoHead);
			imViewSystemInfoHead.setImageResource(R.drawable.system_information_head_spa);
  		   	   
			imViewVipSerial = (ImageView)findViewById(R.id.imViewVipSerial);
			imViewVipSerial.setImageResource(R.drawable.vip_serial_spa);
			   
			imViewZvacSerial = (ImageView)findViewById(R.id.imViewZvacSerial);
			imViewZvacSerial.setImageResource(R.drawable.zvac_serial_spa);
			   
			imViewOplcSerial = (ImageView)findViewById(R.id.imViewOplcSerial);
			imViewOplcSerial.setImageResource(R.drawable.oplc_serial_spa);
			   
			imViewCustomer = (ImageView)findViewById(R.id.imViewCustomer);
			imViewCustomer.setImageResource(R.drawable.customer_spa);
			   
			imViewDeliveryDate = (ImageView)findViewById(R.id.imViewDeliveryDate);
			imViewDeliveryDate.setImageResource(R.drawable.date_of_delivery_spa);
			   
			imViewWarranty = (ImageView)findViewById(R.id.imViewWarranty);
			imViewWarranty.setImageResource(R.drawable.warranty_spa);
    		
			imViewOther = (ImageView)findViewById(R.id.imViewOther);
			imViewOther.setImageResource(R.drawable.other_spa);
			imBtnSave = (ImageButton)findViewById(R.id.imBtnSave);
			imBtnSave.setImageResource(R.drawable.save_spa);
    		 
    		 
    	} 
    	

    	imBtnReturn.setOnClickListener(this);
        imBtnWarning.setOnClickListener(this);
        imViewSystemInfoHead.setOnClickListener(this);
        imBtnSave.setOnClickListener(this);
      
        imBtnWarning.setVisibility(View.INVISIBLE);
        imBtnSave.setVisibility(View.INVISIBLE);
        
        
       SystemInfoHandler db = new SystemInfoHandler(this);
	       
        
	       int ReportCount = db.getReportsCount();
	       
		
	       if(ReportCount == 0){
			
	    	   Toast.makeText(this, "THERE ARE NO REPORTS", Toast.LENGTH_LONG).show();
		
	    	   db.close();
	    	   //finish();

		
	       } else {
	    	   
	    	   int id = 1;
	    	   SystemInfoReports Results = db.getReport(id);
		    	
		    	VipSerial = Results.getVipSerial();
			    ZvacSerial = Results.getZvacSerial();
			    OplcSerial = Results.getOplcSerial();
   	 	        Customer = Results.getCustomer();
   	 	        DeliveryDate = Results.getDeliveryDate();
   	 	        Warranty = Results.getWarranty();
   	 	        Other = Results.getOther();
   	 	        
   	 	        
   	 	        txtEditVipSerial.setText(VipSerial);
   	 	        txtEditZvacSerial.setText(ZvacSerial);
   	 	        txtEditOplcSerial.setText(OplcSerial);
	 	        txtEditCustomer.setText(Customer);
	 	        txtEditDeliveryDate.setText(DeliveryDate);
  	 	        txtEditWarranty.setText(Warranty);
	 	        txtEditOther.setText(Other);

	    	   db.close();
	       }
	       
	       
	       if(Editable.equals("0")){
	    	   
	       NotEditable();
	       
	       
	       } else {
	    	   
	      imBtnSave.setVisibility(View.VISIBLE);
 
	    	   
	       }
	       
	    
	       
	       
	       imViewSystemInfoHead.setOnTouchListener(new OnTouchListener() {
	       	    @Override
	       	    public boolean onTouch(View v, MotionEvent event) {
	       	    	switch(event.getAction()){
	            case MotionEvent.ACTION_DOWN:
	                downTime = System.currentTimeMillis();
	                return true;
	            case MotionEvent.ACTION_UP:
	                long upTime = System.currentTimeMillis();
	               // Toast.makeText(ZimekActivity.this, "Event" + ((upTime - downTime)/1000), Toast.LENGTH_SHORT).show();
	                if( upTime - downTime > millisRequired ){
	                	//Toast.makeText(ZimekActivity.this, "TRUE: LONG PRESSED for" + ((upTime - downTime)/1000) , Toast.LENGTH_SHORT).show();
	                    PassCodeRequired(); //doAction can be a method call, or any code you want to be executed.
	                    return true;
	                }else{
	                    return true;
	                }
	            }
	            return false;
	        }
	       	});
        
        

        txtEditVipSerial.setOnKeyListener(new OnKeyListener() {
			public boolean onKey(View v, int keyCode, KeyEvent event) {
				
				InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
			      imm.showSoftInput(txtEditVipSerial, InputMethodManager.SHOW_IMPLICIT);
		 
				//if keydown and "enter" is pressed
				if (keyCode == KeyEvent.KEYCODE_ENTER) {
					
					
					HideKeyBoard();
					
					return true;
				
				}
					
				return false;
			}	
			
			});
        
        txtEditZvacSerial.setOnKeyListener(new OnKeyListener() {
			public boolean onKey(View v, int keyCode, KeyEvent event) {
				
				InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
			      imm.showSoftInput(txtEditZvacSerial, InputMethodManager.SHOW_IMPLICIT);
		 
				//if keydown and "enter" is pressed
				if (keyCode == KeyEvent.KEYCODE_ENTER) {
					
					
					HideKeyBoard();
					
					return true;
				
				}
					
				return false;
			}	
			
			});
        
        txtEditOplcSerial.setOnKeyListener(new OnKeyListener() {
			public boolean onKey(View v, int keyCode, KeyEvent event) {
				
				InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
			      imm.showSoftInput(txtEditOplcSerial, InputMethodManager.SHOW_IMPLICIT);
		 
				//if keydown and "enter" is pressed
				if (keyCode == KeyEvent.KEYCODE_ENTER) {
					
					
					HideKeyBoard();
					
					return true;
				
				}
					
				return false;
			}	
			
			});
        
        txtEditCustomer.setOnKeyListener(new OnKeyListener() {
			public boolean onKey(View v, int keyCode, KeyEvent event) {
				
				InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
			      imm.showSoftInput(txtEditCustomer, InputMethodManager.SHOW_IMPLICIT);
		 
				//if keydown and "enter" is pressed
				if (keyCode == KeyEvent.KEYCODE_ENTER) {
					
					
					HideKeyBoard();
					
					return true;
				
				}
					
				return false;
			}	
			
			});
        
        
        
        txtEditDeliveryDate.setOnKeyListener(new OnKeyListener() {
			public boolean onKey(View v, int keyCode, KeyEvent event) {
				
				InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
			      imm.showSoftInput(txtEditDeliveryDate, InputMethodManager.SHOW_IMPLICIT);
		 
				//if keydown and "enter" is pressed
				if (keyCode == KeyEvent.KEYCODE_ENTER) {
					
					
					HideKeyBoard();
					
					return true;
				
				}
					
				return false;
			}	
			
			});
        
        txtEditWarranty.setOnKeyListener(new OnKeyListener() {
			public boolean onKey(View v, int keyCode, KeyEvent event) {
				
				InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
			      imm.showSoftInput(txtEditWarranty, InputMethodManager.SHOW_IMPLICIT);
		 
				//if keydown and "enter" is pressed
				if (keyCode == KeyEvent.KEYCODE_ENTER) {
					
					
					HideKeyBoard();
					
					return true;
				
				}
					
				return false;
			}	
			
			});
        
        txtEditOther.setOnKeyListener(new OnKeyListener() {
 			public boolean onKey(View v, int keyCode, KeyEvent event) {
 				
 				InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
 			      imm.showSoftInput(txtEditOther, InputMethodManager.SHOW_IMPLICIT);
 		 
 				//if keydown and "enter" is pressed
 				if (keyCode == KeyEvent.KEYCODE_ENTER) {
 					
 					
 					HideKeyBoard();
 					
 					return true;
 				
 				}
 					
 				return false;
 			}	
 			
 			});
          
         
         
        
        
    }
    
    public void NotEditable(){
    	
       
       txtEditVipSerial.setEnabled(false);
       txtEditVipSerial.setClickable(false);
       txtEditVipSerial.setFocusable(false);
       
       txtEditZvacSerial.setEnabled(false);
       txtEditZvacSerial.setClickable(false);
       txtEditZvacSerial.setFocusable(false);
       
       txtEditOplcSerial.setEnabled(false);
       txtEditOplcSerial.setClickable(false);
       txtEditOplcSerial.setFocusable(false);
       
       txtEditCustomer.setEnabled(false);
       txtEditCustomer.setClickable(false);
       txtEditCustomer.setFocusable(false);
       
       txtEditDeliveryDate.setEnabled(false);
       txtEditDeliveryDate.setClickable(false);
       txtEditDeliveryDate.setFocusable(false);
       
       txtEditWarranty.setEnabled(false);
       txtEditWarranty.setClickable(false);
       txtEditWarranty.setFocusable(false);
       
       txtEditOther.setEnabled(false);
       txtEditOther.setClickable(false);
       txtEditOther.setFocusable(false);
          
       
    }
    
    
    public void PassCodeRequired(){
    	
    	Editable = "1";
    	
    	Intent intent = new Intent();
    	intent.setClass(this,PassCode_Activity.class);
    	intent.putExtra("Editable", Editable);
    	startActivity(intent);
    		
    	
    }
    
    public void HideKeyBoard(){
    	
    	InputMethodManager inputManager = (InputMethodManager)this.getSystemService(Context.INPUT_METHOD_SERVICE); 
	    inputManager.hideSoftInputFromWindow(this.getCurrentFocus().getWindowToken(),      
	    InputMethodManager.HIDE_NOT_ALWAYS);
    	
    }
    
    
 public void onClick(View v){
	 
	 
	 if(v==imBtnReturn){
      	  
		 	Intent resultIntent = new Intent();
			resultIntent.setClass(this,SystemSettings_Activity.class);
			setResult(Activity.RESULT_OK, resultIntent);
			startActivity(resultIntent);
  	}
	 
	 if(v==imBtnSave){
		 
		 	int id = 1;
		 	
		 	
		 	SystemInfoHandler db = new SystemInfoHandler(this);
		 	
		 	int ReportCount = db.getReportsCount();
		 	
		 	VipSerial = txtEditVipSerial.getText().toString();
		    ZvacSerial = txtEditZvacSerial.getText().toString();
		    OplcSerial = txtEditOplcSerial.getText().toString();
		    Customer = txtEditCustomer.getText().toString();
		    DeliveryDate = txtEditDeliveryDate.getText().toString();
		    Warranty = txtEditWarranty.getText().toString();
		    Other = txtEditOther.getText().toString();
		 	
		 	if (ReportCount == 0){
		 		
		 		db.addReport(new SystemInfoReports(id,  VipSerial, ZvacSerial, OplcSerial, Customer, DeliveryDate, Warranty, Other ));
		 		
		 		
		 	} else {
		 	
		    
		    //.makeText(this, "Other" + Other, Toast.LENGTH_SHORT).show();
		 	
		 	
		 	
		 	//db.addReport(new SystemInfoReports(id, VipSerial, ZvacSerial, OplcSerial, Customer, DeliveryDate, Warranty, Other));
		 	db.updateContents(new SystemInfoReports(id, VipSerial, ZvacSerial, OplcSerial, Customer, DeliveryDate, Warranty, Other));
		    
		    
		 	}
		 	//Toast.makeText(this, "SYSTEM INFORMATION has been saved.", Toast.LENGTH_SHORT).show();
		 	db.close();
		    
		    DONE();
		 	
		 	
	} 
	 
 }
 
 public void DONE(){
	 	
	 	Editable = "0";
	 	
	 	Intent resultIntent = new Intent();
		resultIntent.setClass(this,SystemInformation_Activity.class);
		resultIntent.putExtra("Editable", Editable);
		startActivity(resultIntent);
	 
	 	//NotEditable();
	 	//imBtnNext.setVisibility(View.INVISIBLE);
	 
 }
 
}
