package com.circuit.zimek;


import java.util.StringTokenizer;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
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
import android.widget.TextView;
//import android.widget.Toast;

import com.SystemSettings_Report.SystemSettingsDatabaseHandler;
import com.SystemSettings_Report.SystemSettingsReports;


public class AutomaticApp_Activity extends Activity implements OnClickListener {
	
	 private ImageButton imBtnWarning;
	 private ImageButton imBtnReturn;
	 private ImageButton imBtnControlCenter;
	 private ImageButton imBtnNext;
	 private ImageButton imBtnEditAppTime;
	 
	 private ImageView imViewEnterDimenHead;
	 private ImageView imViewDimensions;
	 private ImageView imViewCalAppTime;
	 private ImageView imViewAppTimePeriods;
	 
	 private EditText EdTLength;
	 private EditText EdTWidth;
	 private EditText EdTHeight;
	 private EditText EdTMistTime;
	 private EditText EdTDwellTime;
	 private EditText EdTZvacTime;
	 private TextView EdTtotalFeet;
	 
	 public String language;
	 public String Length;
	 public String Width;
	 public String Height;
	 public int Length_int;
	 public int Width_int;
	 public int Height_int;
	 public int TotalCf;
	 public String ShowTotal;
	 public String RepeatApp = "0";
	 public String MistTime;
	 public String DwellTime;
	 public String ZvacTime;
	 
	 public String MistMin;
	 public String MistHour;
	 public String DwellMin;
	 public String DwellHour;
	 public String ZvacMin;
	 public String ZvacHour;
	 public String typeApplication;
	 public String typeConfigure;

	 public int id;
     public String mMetric;
     public CommonState commonState = null;
	 
	 
	
	public void onCreate(Bundle savedInstanceState) {
	      super.onCreate(savedInstanceState);
	      requestWindowFeature(Window.FEATURE_NO_TITLE);
	      getWindow().getDecorView().setSystemUiVisibility(View.STATUS_BAR_HIDDEN);
	       getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, 
	               WindowManager.LayoutParams.FLAG_FULLSCREEN);
	      setContentView(R.layout.automatic_app);
	      
	      
          commonState = (CommonState) getApplication();
          commonState.activity_name = "AutomaticApp_Activity";
          language = commonState.language;

	      
	      id = 1;
	      SystemSettingsDatabaseHandler db = new SystemSettingsDatabaseHandler(this);
	      int reportCount = db.getReportsCount();
	      if (reportCount == 0) {
	          id = 1;
	          db.addReport(new SystemSettingsReports(id, "0", "0")); // id, beeper=0, metrics=0
	      } else {
	    	  SystemSettingsReports Results = db.getReport(id);
	    	  mMetric = Results.getMetrics();
	      }
	      db.close();

	      if(language.equals("English")){
	   	   
	    	  imViewEnterDimenHead = (ImageView)findViewById(R.id.imViewEnterDimenHead);
	    	     
	    	  imViewDimensions = (ImageView)findViewById(R.id.imViewDimensions);
	          
	    	  imViewCalAppTime = (ImageView)findViewById(R.id.imViewCalAppTime);
	    	     
	    	  imViewAppTimePeriods = (ImageView)findViewById(R.id.imViewAppTimePeriods);
	          
	          imBtnEditAppTime = (ImageButton)findViewById(R.id.imBtnEditAppTime);
	          
	          imBtnControlCenter = (ImageButton)findViewById(R.id.imBtnControlCenter);
	          
	          imBtnNext = (ImageButton)findViewById(R.id.imBtnNext);
	          
	          imBtnReturn = (ImageButton)findViewById(R.id.imBtnReturn);
	          
	          imBtnWarning = (ImageButton)findViewById(R.id.imBtnWarning);
	          if (mMetric.equals("1")) {
	        	  imViewDimensions.setImageResource(R.drawable.dimensions_eng_meters);
	          }
	        
		   
	   	   
	      } 
	      
	      if(language.equals("Spanish")){
			   
	          imBtnReturn = (ImageButton)findViewById(R.id.imBtnReturn);
	          imBtnReturn.setImageResource(R.drawable.return_spa);
	          
	          imBtnNext = (ImageButton)findViewById(R.id.imBtnNext);
	          imBtnNext.setImageResource(R.drawable.next_spa);
	          
	          imBtnWarning = (ImageButton)findViewById(R.id.imBtnWarning);
	          imBtnWarning.setImageResource(R.drawable.warning_spa);
	          
	          imBtnControlCenter = (ImageButton)findViewById(R.id.imBtnControlCenter);
	          imBtnControlCenter.setImageResource(R.drawable.control_center_spa);
	          
	          imBtnEditAppTime = (ImageButton)findViewById(R.id.imBtnEditAppTime);
	          imBtnEditAppTime.setImageResource(R.drawable.edit_application_time_spa);

	          
	          imViewEnterDimenHead = (ImageView)findViewById(R.id.imViewEnterDimenHead);
	          imViewEnterDimenHead.setImageResource(R.drawable.enter_dimensions_spa);
	          
	          
	          imViewDimensions = (ImageView)findViewById(R.id.imViewDimensions);
	          if (mMetric.equals("1")) {
	        	  imViewDimensions.setImageResource(R.drawable.dimensions_spa_meters);
	          } else {
	        	  imViewDimensions.setImageResource(R.drawable.dimensions_spa);
	          }
	          
	          imViewCalAppTime = (ImageView)findViewById(R.id.imViewCalAppTime);
	          imViewCalAppTime.setImageResource(R.drawable.calibrated_application_time_spa);
	          
	          
	          imViewAppTimePeriods = (ImageView)findViewById(R.id.imViewAppTimePeriods);
	          imViewAppTimePeriods.setImageResource(R.drawable.application_time_periods_spa);

		   }
	      
	      imBtnNext.setVisibility(View.INVISIBLE);
	      imBtnWarning.setVisibility(View.INVISIBLE);
	      imBtnEditAppTime.setVisibility(View.INVISIBLE);
	      
	      imBtnWarning.setOnClickListener(this);
	      imBtnReturn.setOnClickListener(this);
	      imBtnNext.setOnClickListener(this);
	      imBtnControlCenter.setOnClickListener(this);
	      imBtnEditAppTime.setOnClickListener(this);
	      
	   
	      
	      EdTLength = (EditText)findViewById(R.id.EdTLength);
	      EdTLength.setBackgroundColor(Color.TRANSPARENT);
	      EdTLength.setOnClickListener(this);
	      
	      EdTWidth = (EditText)findViewById(R.id.EdTWidth);
	      EdTWidth.setBackgroundColor(Color.TRANSPARENT);
	      EdTWidth.setOnClickListener(this);
	      
	      EdTtotalFeet = (TextView)findViewById(R.id.EdTtotalFeet);
	      EdTtotalFeet.setOnClickListener(this);
	      
	      EdTHeight = (EditText)findViewById(R.id.EdTHeight);
	      EdTHeight.setBackgroundColor(Color.TRANSPARENT);
	      EdTHeight.setOnClickListener(this);
	      
	      EdTMistTime = (EditText)findViewById(R.id.EdTMistTime);
	      EdTMistTime.setBackgroundColor(Color.TRANSPARENT);
	      EdTMistTime.setOnClickListener(this);
	      
	      EdTDwellTime = (EditText)findViewById(R.id.EdTDwellTime);
	      EdTDwellTime.setBackgroundColor(Color.TRANSPARENT);
	      EdTDwellTime.setOnClickListener(this);
	      
	      EdTZvacTime = (EditText)findViewById(R.id.EdTZvacTime);
	      EdTZvacTime.setBackgroundColor(Color.TRANSPARENT);
	      EdTZvacTime.setOnClickListener(this);
	      
	      
	     EdTLength.requestFocus();
	      
	      InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
	      imm.showSoftInput(EdTLength, InputMethodManager.SHOW_IMPLICIT);
	     

	      
	  	 EdTLength.setOnKeyListener(new OnKeyListener() {
			public boolean onKey(View v, int keyCode, KeyEvent event) {
		 
				//if keydown and "enter" is pressed
				if (keyCode == KeyEvent.KEYCODE_ENTER) {
		 
					
				Length = EdTLength.getText().toString();
				
				if(EdTLength.getText().toString().isEmpty()) {
					
					EdTLength.requestFocus();
				} else {
			
					EdTWidth.requestFocus();
				
					
				}
				Calculate();
				
				return true;	
				
				
				}
				
				return false;
				
				
			}
	  	});
	
	  	  
		
			EdTWidth.setOnKeyListener(new OnKeyListener() {
				public boolean onKey(View v, int keyCode, KeyEvent event) {
					
					InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
				      imm.showSoftInput(EdTWidth, InputMethodManager.SHOW_IMPLICIT);
			 
					//if keydown and "enter" is pressed
					if (keyCode == KeyEvent.KEYCODE_ENTER) {
						
						Width = EdTWidth.getText().toString();
						
						if (EdTWidth.getText().toString().isEmpty()){
							EdTWidth.requestFocus();
						} else{
							EdTHeight.requestFocus();
						}
						Calculate();
						
						
						
						
						return true;
					
					}
						
					return false;
				}	
				
				});
			
		  	EdTHeight.setOnKeyListener(new OnKeyListener() {
				public boolean onKey(View v, int keyCode, KeyEvent event) {
					
					InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
				    imm.showSoftInput(EdTHeight, InputMethodManager.SHOW_IMPLICIT);
			 
					//if keydown and "enter" is pressed
					if (keyCode == KeyEvent.KEYCODE_ENTER) {
						
						
						Height = EdTHeight.getText().toString();
							
						
						if(EdTHeight.getText().toString().isEmpty()) {
							EdTHeight.requestFocus();
						} else {
							Calculate();
						
						}
							
										
								
							return true;
					
					}
					
					
					
					return false;
				}	
				
				});
				
	       
	}
	
	public void Calculate() {
		
		
		if(EdTLength.getText().toString().length() > 0 && EdTWidth.getText().toString().length() > 0 && EdTHeight.getText().toString().length() > 0) {
		     
			Length = EdTLength.getText().toString();
			Width = EdTWidth.getText().toString();
			Height = EdTHeight.getText().toString();
			
			Length = Length.trim();
			Width = Width.trim();
			Height = Height.trim();
			
			Length_int = Integer.parseInt(Length);
			Width_int = Integer.parseInt(Width);
			Height_int = Integer.parseInt(Height);
			
			TotalCf = Length_int * Width_int * Height_int;
			
			ShowTotal = String.valueOf(TotalCf);
			EdTtotalFeet.setText(ShowTotal);
			EdTtotalFeet.setVisibility(View.VISIBLE);
			
			  
			  imBtnNext.setVisibility(View.VISIBLE);
		     // iViewNext.setVisibility(View.VISIBLE);
		      imBtnEditAppTime.setVisibility(View.VISIBLE);
		      BlinkNextButtonImage();
		      
		      
		       //InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
		      // imm.hideSoftInputFromWindow(txtEditConfirmLoc.getWindowToken(), 0); 
		      InputMethodManager inputManager = (InputMethodManager)this.getSystemService(Context.INPUT_METHOD_SERVICE); 
		    		    inputManager.hideSoftInputFromWindow(this.getCurrentFocus().getWindowToken(),      
		    		    InputMethodManager.HIDE_NOT_ALWAYS);
		} 
		
		if(EdTLength.getText().toString().length() > 0 && EdTWidth.getText().toString().isEmpty()  && EdTHeight.getText().toString().length() > 0) {
			EdTtotalFeet.setVisibility(View.INVISIBLE);
			EdTWidth.requestFocus();	
		}
		
		if(EdTLength.getText().toString().isEmpty() && EdTWidth.getText().toString().length() > 0  && EdTHeight.getText().toString().length() > 0) {
			EdTtotalFeet.setVisibility(View.INVISIBLE);
			EdTLength.requestFocus();	
		}
		
		
	
		
		
		
	}
	
	public void BlinkNextButtonImage(){
	    Animation animation = new AlphaAnimation(1, 0); 
	    animation.setDuration(800); 
	    animation.setInterpolator(new LinearInterpolator()); 
	    animation.setRepeatCount(Animation.INFINITE); 
	    animation.setRepeatMode(Animation.REVERSE); 
	    imBtnNext.startAnimation(animation);
    }
	
	public void GetContents(){
		String TotalMist = EdTMistTime.getText().toString();
		TotalMist = TotalMist.trim();
		
		String TotalDwell = EdTDwellTime.getText().toString();
		TotalDwell = TotalDwell.trim();
		
		String TotalZvac = EdTZvacTime.getText().toString();
		TotalZvac = TotalZvac.trim();
		
		StringTokenizer token = new StringTokenizer(TotalMist, ":");
		MistHour = token.nextToken();
	    MistMin = token.nextToken();
	    
	    StringTokenizer tokens = new StringTokenizer(TotalDwell, ":");
		DwellHour = tokens.nextToken();
	    DwellMin = tokens.nextToken();
	    
	    StringTokenizer tokensz = new StringTokenizer(TotalZvac, ":");
		ZvacHour = tokensz.nextToken();
	    ZvacMin = tokensz.nextToken();
	}
	
	public void onClick(View v){
	  	if(v==imBtnReturn){
	  		imBtnNext.clearAnimation();
	   		//onDestroy();
	  		finish();
	  	}
	  	
		if(v==imBtnControlCenter){
			Intent resultIntent = new Intent();
  			resultIntent.setClass(this,ZimekActivity.class);
  			setResult(Activity.RESULT_OK, resultIntent);
  			startActivity(resultIntent);
	  	}
		
		if(v==imBtnNext){
			
			//double power = Math.pow(3, 2);
			//String ft = String.valueOf(power);			
			//ft = ft.replaceFirst("^2+(?!$)"," ft");
			//Toast.makeText(this, "Cubicfeet replaced " + ft, Toast.LENGTH_LONG).show();
			
			if(EdTLength.getText().toString().isEmpty() || EdTWidth.getText().toString().isEmpty() || EdTHeight.getText().toString().isEmpty()){
				
				imBtnNext.setVisibility(View.INVISIBLE);
				imBtnEditAppTime.setVisibility(View.INVISIBLE);
				EdTtotalFeet.setVisibility(View.INVISIBLE);
				
				
			} else {
			
			
			
			String cubicfeet = EdTtotalFeet.getText().toString() + " ft\u00B3";
			
			typeConfigure = cubicfeet;
            try {
			    typeApplication = getIntent().getStringExtra("typeApplication");
                commonState.typeApplication = typeApplication;
            } catch (Exception e) {
                typeApplication = commonState.typeApplication;
            }
			
				GetContents();
				
	  			Intent intent = new Intent();
				intent.setClass(this,Enter_Location_Activity.class);
				intent.putExtra("MistHour", MistHour);
				intent.putExtra("MistMin", MistMin);
				intent.putExtra("DwellHour", DwellHour);
				intent.putExtra("DwellMin", DwellMin);
				intent.putExtra("ZvacHour", ZvacHour);
				intent.putExtra("ZvacMin", ZvacMin);
				intent.putExtra("typeApplication", typeApplication);
				intent.putExtra("typeConfigure", typeConfigure);
				startActivity(intent);
				
				
	  	    }
		}
		
		if(v==imBtnEditAppTime){
			typeApplication = "Manual";
			typeConfigure = "N/A";
			
			RepeatApp = "2";
			
			GetContents();
			
				
	  		 	Intent intent = new Intent();
				intent.setClass(this,ManualApp_Activity.class);
				intent.putExtra("MistHour", MistHour);
				intent.putExtra("MistMin", MistMin);
				intent.putExtra("DwellHour", DwellHour);
				intent.putExtra("DwellMin", DwellMin);
				intent.putExtra("ZvacHour", ZvacHour);
				intent.putExtra("ZvacMin", ZvacMin);
				intent.putExtra("RepeatApp", RepeatApp);
				intent.putExtra("typeApplication", typeApplication);
				intent.putExtra("typeConfigure", typeConfigure);
				startActivity(intent);
	  	}
		
			imBtnNext.clearAnimation();
	   	
		//onDestroy();
		
		
	  }
	
	public void onDestroy()
	  {   
	     // Cleanup();
	      super.onDestroy();
	  }

//	  private void Cleanup()
//	  {    
		  
//		  Bitmap ManualApplication_background = BitmapFactory.decodeStream(getResources().openRawResource(R.drawable.automatic_app));
//			ImageView ManualApplication_Screen_Background = (ImageView)findViewById(R.drawable.automatic_app);
			

//			if (ManualApplication_Screen_Background!= null)
//				 ManualApplication_Screen_Background.setImageBitmap(null);
			 		
//			 	if (ManualApplication_background!= null) {
//				      ManualApplication_background.recycle();
				     
//				   }
//				   ManualApplication_background = null;
//				   ManualApplication_Screen_Background = null;
		   	
				  // System.gc();
			
//			Bitmap btnReturnImage = BitmapFactory.decodeStream(getResources().openRawResource(R.drawable.back_button));
//			ImageView btnReturn = (ImageView)findViewById(R.drawable.back_button);
			
//			 if (btnReturn!= null)
//				 btnReturn.setImageBitmap(null);
			 		
//			 if (btnReturnImage!= null) {
//				 btnReturnImage.recycle();
				     
//				   }
//			 btnReturnImage = null;
//			 btnReturn = null;
			 
//			 Bitmap btnNextButtonImage = BitmapFactory.decodeStream(getResources().openRawResource(R.drawable.next_button));
//			 ImageView btnNextButton = (ImageView)findViewById(R.drawable.next_button);
				
//				 if (btnNextButton!= null)
//					 btnNextButton.setImageBitmap(null);
				 		
//				 if (btnNextButtonImage!= null) {
//					 btnNextButtonImage.recycle();
					     
//					   }
//				 btnNextButtonImage = null;
//				 btnNextButton = null;
				 
				 
//				 Bitmap btnControlCenterImage = BitmapFactory.decodeStream(getResources().openRawResource(R.drawable.controlcenter_button));
//				 ImageView btnControlCenterButton = (ImageView)findViewById(R.drawable.controlcenter_button);
					
//					 if (btnControlCenterButton!= null)
//						 btnControlCenterButton.setImageBitmap(null);
					 		
//					 if (btnControlCenterImage!= null) {
//						 btnControlCenterImage.recycle();
						     
//						   }
//					 btnControlCenterImage = null;
//					 btnControlCenterButton = null;

//	      System.gc();
//	      Runtime.getRuntime().gc();  
//	  }
	
	
	
}
