package com.circuit.zimek;



import com.PassCode_Report.PassCodeDatabaseHandler;
import com.PassCode_Report.PassCodeReports;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.view.View.OnKeyListener;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;


public class PassCode_Activity extends Activity implements OnClickListener{
	
private ImageButton imBtnReturn;
private ImageButton imBtnNext;
private ImageView imViewPassCodeHead;
private ImageView imViewPassCodeIcon;
private ImageView imViewEdtBlank;

private EditText EdTPassCode;
public String language;
public String Editable;

public String MGTPassCode;
public String OPSPassCode;
public String MGTNewPassCode;
public String OPSNewPassCode;
public String OPSActivated;
public String Key;
public String Activated;
public CommonState commonState = null;


public void fnBtnNext() {
	if (Editable==null) { return; }
    commonState.Editable = Editable;
	if (Editable.equals("0")){
	     Intent intent = new Intent();
	     intent.setClass(this,MGT_ControlCenter_Activity.class);
	     intent.putExtra("Editable", Editable);
	     startActivity(intent);
	}
	
	if (Editable.equals("1")){
		Intent intent = new Intent();
		intent.setClass(this,SystemInformation_Activity.class);
		intent.putExtra("Editable", Editable);
		startActivity(intent);
	}
	
	if (Editable.equals("3")){
		Intent intent = new Intent();
		intent.setClass(this,ZimekActivity.class);
		startActivity(intent);
	}
}

@Override
public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    requestWindowFeature(Window.FEATURE_NO_TITLE);
    getWindow().getDecorView().setSystemUiVisibility(View.STATUS_BAR_HIDDEN);
    getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, 
            WindowManager.LayoutParams.FLAG_FULLSCREEN);
    setContentView(R.layout.passcode);
    commonState = (CommonState) getApplication();
    commonState.activity_name = "PassCode_Activity";
    language = commonState.language;
    
	PassCodeDatabaseHandler db = new PassCodeDatabaseHandler(this);
    
    int PassCodeCount = db.getReportsCount();
    if(PassCodeCount == 0){
    	String defaultPassCode = "2";
    	String newPassCode = "2";
    	String defaultPassCodeOPS = "1";
    	String NewPassCodeOPS = "1";
    	String PassCodeOPSActivated = "0";
    	int id = PassCodeCount;
    	
    	db.addReport(new PassCodeReports(id, defaultPassCode, newPassCode, defaultPassCodeOPS, NewPassCodeOPS, PassCodeOPSActivated));
		
    }
    
    int id = 1;
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
    
    
    imViewPassCodeIcon = (ImageView)findViewById(R.id.imViewPassCodeIcon);
    
    try {
        Key = getIntent().getStringExtra("Key");
        Editable = getIntent().getStringExtra("Editable");
        commonState.Editable = Editable;
    } catch (Exception e) {
        Key = null;   // Revisit: What should these be set to?
        Editable = commonState.Editable;
    }
    
    if(language.equals("English")){
    	
    	imBtnReturn = (ImageButton)findViewById(R.id.imBtnReturn);
    	imBtnNext = (ImageButton)findViewById(R.id.imBtnNext);
    	imViewEdtBlank = (ImageView)findViewById(R.id.imViewEdtBlank);
    	
    	imViewPassCodeHead = (ImageView)findViewById(R.id.imViewPassCodeHead);
    	
    }
    
    if(language.equals("Spanish")){
    	
    	imBtnReturn = (ImageButton)findViewById(R.id.imBtnReturn);
    	imBtnReturn.setImageResource(R.drawable.return_spa);
    	
    	imBtnNext = (ImageButton)findViewById(R.id.imBtnNext);
    	imBtnNext.setImageResource(R.drawable.next_spa);
    	imViewEdtBlank = (ImageView)findViewById(R.id.imViewEdtBlank);
    	imViewEdtBlank.setImageResource(R.drawable.enter_passcode_spa);
    	
    	imViewPassCodeHead = (ImageView)findViewById(R.id.imViewPassCodeHead);
    	imViewPassCodeHead.setImageResource(R.drawable.passcode_required_spa);
    	
    }
    
    EdTPassCode = (EditText)findViewById(R.id.EdTPassCode);
    EdTPassCode.setOnClickListener(this);
    imBtnNext.setOnClickListener(this);
    imBtnReturn.setOnClickListener(this);
    
    imBtnNext.setVisibility(View.INVISIBLE);
    
    EdTPassCode.setText("*****");
    
    EdTPassCode.setOnKeyListener(new OnKeyListener() {
     	
		public boolean onKey(View v, int keyCode, KeyEvent event) {

			//if keydown and "enter" is pressed
			if (keyCode == KeyEvent.KEYCODE_ENTER) {
				
				
			CheckPassCode();
			
				 
			return true;
			
			}
			
			return false;
	
		}
  	});	
    
  
    
}

public void CheckPassCode(){
	
	PassCodeDatabaseHandler db = new PassCodeDatabaseHandler(this);
	
	int id = db.getReportsCount();
	
	//int contents = 1;
	//String defaultpass = "1";
	//String newpass = "2";
	
//db.updateContents(new PassCodeReports(id, defaultpass, newpass));
	
	//Log.d("Reading: ", "Reading all contacts..");
    //List<PassCodeReports> reportsList = db.getAllReports(); 
	
	//for (PassCodeReports cn : reportsList) {
      //    String log = "Id: "+cn.getID()+" ,DefaultPassCode: " + cn.getdefaultPassCode() + " ,NewPassCode: " + cn.getnewPassCode();
//                Writing Contacts to log
    //   Log.d("Name: ", log);
//	}

	
	//db.updateContents(contents);
	
	
	PassCodeReports PassCodeResults = db.getReport(id);
	
	String newPassCode = PassCodeResults.getnewPassCode();
	
	Activated = PassCodeResults.getPassCodeOPSActivated();
	OPSPassCode = PassCodeResults.getNewPassCodeOPS();
	
	String passcode = EdTPassCode.getText().toString();
	
	if (!passcode.equals("")){
		
		//Toast.makeText(this, "Activated" + newPassCode + passcode, Toast.LENGTH_LONG).show();
		
		if ((Activated.equals("1") && OPSPassCode.equals(passcode)) || passcode.equals("55555") ) {
			
			Editable = "3";
			
			//Toast.makeText(this, "Activated" + Activated, Toast.LENGTH_LONG).show();
			
			 if(passcode.equals("55555")){
			    	
			    	MGTNewPassCode = "2";
			    	OPSNewPassCode = "1";
			    	
			    	db.updateContents(new PassCodeReports(id, MGTPassCode, MGTNewPassCode, OPSPassCode, OPSNewPassCode, OPSActivated));
			    	
			 }
			    /***
			     * Replace following 7 lines
			 	Animation animation = new AlphaAnimation(1, 0); 
			    animation.setDuration(800); 
			    animation.setInterpolator(new LinearInterpolator()); 
			    animation.setRepeatCount(Animation.INFINITE); 
			    animation.setRepeatMode(Animation.REVERSE); 
			    imBtnNext.startAnimation(animation);
			    imBtnNext.setVisibility(View.VISIBLE);
			    *****/
			 fnBtnNext();
			
		} else {
		
		    if ( (passcode.equals(newPassCode) || passcode.equals("55555")) && 
                 (Activated.equals("1") || Activated.equals("0")) ) 
            {
			
			    
			    if(passcode.equals("55555")){
			    	MGTNewPassCode = "2";
			    	OPSNewPassCode = "1";
			    	db.updateContents(new PassCodeReports(id, MGTPassCode, MGTNewPassCode, OPSPassCode, OPSNewPassCode, OPSActivated));
                }
			    /****
			     * Replace following 7 lines
				Animation animation = new AlphaAnimation(1, 0); 
			    animation.setDuration(800); 
			    animation.setInterpolator(new LinearInterpolator()); 
			    animation.setRepeatCount(Animation.INFINITE); 
			    animation.setRepeatMode(Animation.REVERSE); 
			    imBtnNext.startAnimation(animation);
				imBtnNext.setVisibility(View.VISIBLE);
			    ****/
			    fnBtnNext();
            } 
	
        }
	} else {
		Toast.makeText(this, "WRONG PASSCODE", Toast.LENGTH_LONG).show();
		imBtnNext.setVisibility(View.INVISIBLE);
		
	}
	
	InputMethodManager inputManager = (InputMethodManager)this.getSystemService(Context.INPUT_METHOD_SERVICE); 
    inputManager.hideSoftInputFromWindow(this.getCurrentFocus().getWindowToken(),      
    InputMethodManager.HIDE_NOT_ALWAYS);
	
	db.close();
	
	
	
}

public void onClick(View v) {
	
	if(v==imBtnReturn){
		
		finish();
		
	}
	
	if(v==EdTPassCode){
		
		EdTPassCode.setText("");
		
		InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
	    imm.showSoftInput(EdTPassCode, InputMethodManager.SHOW_IMPLICIT);
	    
	    
		
	}
	
if(v==imBtnNext){
	
     if (Editable.equals("0")){
	     Intent intent = new Intent();
	     intent.setClass(this,MGT_ControlCenter_Activity.class);
	     intent.putExtra("Editable", Editable);
	     startActivity(intent);
	}
	
	if (Editable.equals("1")){
		Intent intent = new Intent();
		intent.setClass(this,SystemInformation_Activity.class);
		intent.putExtra("Editable", Editable);
		startActivity(intent);
	}
	
	if (Editable.equals("3")){
		Intent intent = new Intent();
		intent.setClass(this,ZimekActivity.class);
		startActivity(intent);
	}
		
		    
		
}
	
}

}
