package com.circuit.zimek;


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
import android.view.View.OnFocusChangeListener;
import android.view.View.OnKeyListener;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;






public class ManualApp_Activity extends Activity implements OnClickListener{
	
	
	 private ImageView imViewManualApp;
	 private ImageView imViewManual;
	 private ImageButton imBtnWarning;
	 private ImageButton imBtnReturn;
	 private ImageButton imBtnControlCenter;
	 private ImageButton imBtnNext;
	 public String language;
	 private EditText EdTMistHour;
	 private EditText EdTMistMin;
	 private EditText EdTDwellHour;
	 private EditText EdTDwellMin;
	 private EditText EdTZvacHour;
	 private EditText EdTZvacMin;
	 public String RepeatApp = "0";
	 public String locationId;
	 public String MistMin;
	 public String DwellMin;
	 public String ZvacMin;
	 public String MistHour;
	 public String DwellHour;
	 public String ZvacHour;
	 public String typeApplication;
	 public String typeConfigure;
     public CommonState commonState = null;


     @Override
     protected void onNewIntent(Intent intent) {
         super.onNewIntent(intent);
         setIntent(intent);
     }
	 
  @Override
  public void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      requestWindowFeature(Window.FEATURE_NO_TITLE);
      getWindow().getDecorView().setSystemUiVisibility(View.STATUS_BAR_HIDDEN);
      getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, 
              WindowManager.LayoutParams.FLAG_FULLSCREEN);
      setContentView(R.layout.manual_app);
  	       
      commonState = (CommonState) getApplication();
      commonState.activity_name = "ManualApp_Activity";
      language = commonState.language;
      

      if (language.equals("English")){
    	  imViewManualApp = (ImageView)findViewById(R.id.imViewManualApp);
          imViewManual = (ImageView)findViewById(R.id.imViewManual);
          imBtnControlCenter = (ImageButton)findViewById(R.id.imBtnControlCenter);
          imBtnNext = (ImageButton)findViewById(R.id.imBtnNext);
          imBtnReturn = (ImageButton)findViewById(R.id.imBtnReturn);
          imBtnWarning = (ImageButton)findViewById(R.id.imBtnWarning);
      } else { // i.e. language.equals("Spanish")
          imBtnReturn = (ImageButton)findViewById(R.id.imBtnReturn);
          imBtnWarning = (ImageButton)findViewById(R.id.imBtnWarning);
          imViewManualApp = (ImageView)findViewById(R.id.imViewManualApp);
          imViewManual = (ImageView)findViewById(R.id.imViewManual);
          imBtnControlCenter = (ImageButton)findViewById(R.id.imBtnControlCenter);
          imBtnNext = (ImageButton)findViewById(R.id.imBtnNext);
          imViewManualApp.setImageResource(R.drawable.calibrate_application_time_spa);
          imViewManual.setImageResource(R.drawable.application_cycle_time_spa);
          imBtnControlCenter.setImageResource(R.drawable.control_center_spa);
          imBtnNext.setImageResource(R.drawable.next_spa);
          imBtnReturn.setImageResource(R.drawable.return_spa);
          imBtnWarning.setImageResource(R.drawable.warning_spa);
	   }
      
      imBtnNext.setVisibility(View.INVISIBLE);
      imBtnWarning.setVisibility(View.INVISIBLE);
      
      imBtnWarning.setOnClickListener(this);
      imBtnReturn.setOnClickListener(this);
      imBtnNext.setOnClickListener(this);
      imBtnControlCenter.setOnClickListener(this);
      

      EdTMistHour = (EditText)findViewById(R.id.EdTMistHour);
      EdTMistHour.setBackgroundColor(Color.TRANSPARENT);
      EdTMistHour.setOnClickListener(this);
      
      EdTMistMin = (EditText)findViewById(R.id.EdTMistMin);
      EdTMistMin.setBackgroundColor(Color.TRANSPARENT);
      EdTMistMin.setOnClickListener(this);
      
      EdTDwellHour = (EditText)findViewById(R.id.EdTDwellHour);
      EdTDwellHour.setBackgroundColor(Color.TRANSPARENT);
      EdTDwellHour.setOnClickListener(this);
      
      EdTDwellMin = (EditText)findViewById(R.id.EdTDwellMin);
      EdTDwellMin.setBackgroundColor(Color.TRANSPARENT);
      EdTDwellMin.setOnClickListener(this);
      
      EdTZvacHour = (EditText)findViewById(R.id.EdTZvacHour);
      EdTZvacHour.setBackgroundColor(Color.TRANSPARENT);
      EdTZvacHour.setOnClickListener(this);
      
      EdTZvacMin = (EditText)findViewById(R.id.EdTZvacMin);
      EdTZvacMin.setBackgroundColor(Color.TRANSPARENT);
      EdTZvacMin.setOnClickListener(this);
      //---------------------------------------------------------------------------------------
      
      EdTMistHour.requestFocus();
      
      InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
      imm.showSoftInput(EdTMistHour, InputMethodManager.SHOW_IMPLICIT);
      
      EdTMistHour.setOnFocusChangeListener(new OnFocusChangeListener()
      {
         
          public void onFocusChange(View v, boolean hasFocus) 
          {
        	  
              
              if (hasFocus==true)
              {   
            	  EdTMistHour.setText("");
                  
              }
          }
      });
      
      
      EdTMistMin.setOnFocusChangeListener(new OnFocusChangeListener()
      {
         
          public void onFocusChange(View v, boolean hasFocus) 
          {
              if (hasFocus==true)
              {   
            	  EdTMistMin.setText("");
                  
              }
          }
      });
      
      
      EdTDwellHour.setOnFocusChangeListener(new OnFocusChangeListener()
      {
         
          public void onFocusChange(View v, boolean hasFocus) 
          {
              if (hasFocus==true)
              {   
            	  EdTDwellHour.setText("");
                  
              }
          }
      });
      
      
      EdTDwellMin.setOnFocusChangeListener(new OnFocusChangeListener()
      {
         
          public void onFocusChange(View v, boolean hasFocus) 
          {
              if (hasFocus==true)
              {   
            	  EdTDwellMin.setText("");
                  
              }
          }
      });
      
      
      EdTZvacHour.setOnFocusChangeListener(new OnFocusChangeListener()
      {
         
          public void onFocusChange(View v, boolean hasFocus) 
          {
              if (hasFocus==true)
              {   
            	  EdTZvacHour.setText("");
                  
              }
          }
      });
      
      
      EdTZvacMin.setOnFocusChangeListener(new OnFocusChangeListener()
      {
         
          public void onFocusChange(View v, boolean hasFocus) 
          {
              if (hasFocus==true)
              {   
            	  EdTZvacMin.setText("");
                  
              }
          }
      });
      
  
      
      //----------------------------------------------------------------------------------------

      
            EdTMistHour.setOnKeyListener(new OnKeyListener() {
 	
			public boolean onKey(View v, int keyCode, KeyEvent event) {

				//if keydown and "enter" is pressed
				if (keyCode == KeyEvent.KEYCODE_ENTER) {
					
					
					  if ((EdTMistHour.getText().toString().trim().equals("00")) || (!"00".equals(EdTMistHour.getText().toString().trim()))){
						  //EdTMistMin.requestFocus();  
					  }else{
						  
					  }
				
					  Calculate();
				return true;
				
				}
				
				return false;
				
				
			}
	  	});
	  	 
	  	 
	  	 
	  	 EdTMistMin.setOnKeyListener(new OnKeyListener() {
	  		 
	  		     
				public boolean onKey(View v, int keyCode, KeyEvent event) {
					
					 InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
			  	     imm.showSoftInput(EdTMistMin, InputMethodManager.SHOW_IMPLICIT);

					if (keyCode == KeyEvent.KEYCODE_ENTER) {

					
					Calculate();
					
					return true;	
					
					
					}
					
					return false;
					
					
				}
		  	});
	  	 
	  	 
	  	 EdTDwellHour.setOnKeyListener(new OnKeyListener() {
				public boolean onKey(View v, int keyCode, KeyEvent event) {
					
					InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
			  	     imm.showSoftInput(EdTDwellHour, InputMethodManager.SHOW_IMPLICIT);
			 
					//if keydown and "enter" is pressed
					if (keyCode == KeyEvent.KEYCODE_ENTER) {

					
					Calculate();
					
					return true;	
					
					
					}
					
					return false;
					
					
				}
		  	});
	  	 
		 EdTDwellMin.setOnKeyListener(new OnKeyListener() {
				public boolean onKey(View v, int keyCode, KeyEvent event) {
					
					InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
			  	     imm.showSoftInput(EdTDwellMin, InputMethodManager.SHOW_IMPLICIT);
			 
					//if keydown and "enter" is pressed
					if (keyCode == KeyEvent.KEYCODE_ENTER) {

					
					Calculate();
					
					return true;	
					
					
					}
					
					return false;
					
					
				}
		  	});
		 
		 
		 EdTZvacHour.setOnKeyListener(new OnKeyListener() {
				public boolean onKey(View v, int keyCode, KeyEvent event) {
					
					InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
			  	     imm.showSoftInput(EdTZvacHour, InputMethodManager.SHOW_IMPLICIT);
			 
					//if keydown and "enter" is pressed
					if (keyCode == KeyEvent.KEYCODE_ENTER) {

					

					Calculate();
					
					return true;	
					
					
					}
					
					return false;
					
					
				}
		  	});
		 
    EdTZvacMin.setOnKeyListener(new OnKeyListener() {
				public boolean onKey(View v, int keyCode, KeyEvent event) {
					
					InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
			  	     imm.showSoftInput(EdTZvacMin, InputMethodManager.SHOW_IMPLICIT);
			 
					//if keydown and "enter" is pressed
					if (keyCode == KeyEvent.KEYCODE_ENTER) {

						Calculate();


					return true;	
					
					
					}
					
					return false;
					
					
				}
		  	});
      
      
      try {
        RepeatApp = getIntent().getStringExtra("RepeatApp");
        if (RepeatApp == null) RepeatApp="0";
      } catch (Exception e) {
        RepeatApp = "0";
      }
      
      
      if (RepeatApp != null && RepeatApp.equals("1")){
    	  
            try {
                locationId = getIntent().getStringExtra("locationId");
                MistHour = getIntent().getStringExtra("MistHour");
                MistMin = getIntent().getStringExtra("MistMin");
                DwellHour = getIntent().getStringExtra("DwellHour");
                DwellMin = getIntent().getStringExtra("DwellMin");
                ZvacHour = getIntent().getStringExtra("ZvacHour");
                ZvacMin = getIntent().getStringExtra("ZvacMin");
                typeApplication = getIntent().getStringExtra("typeApplication");
                typeConfigure = getIntent().getStringExtra("typeConfigure");

                commonState.LocationId = locationId;
                commonState.MistHour = MistHour;
                commonState.MistMin = MistMin;
                commonState.DwellHour = DwellHour;
                commonState.DwellMin = DwellMin;
                commonState.ZvacHour = ZvacHour;
                commonState.ZvacMin = ZvacMin;
                commonState.typeApplication = typeApplication;
                commonState.typeConfigure = typeConfigure;
            } catch (Exception e) {
                locationId = commonState.LocationId ;
                MistHour = commonState.MistHour ;
                MistMin = commonState.MistMin ;
                DwellHour = commonState.DwellHour ;
                DwellMin = commonState.DwellMin ;
                ZvacHour = commonState.ZvacHour ;
                ZvacMin = commonState.ZvacMin ;
                typeApplication = commonState.typeApplication ;
                typeConfigure = commonState.typeConfigure ;
            }
	        
	        EdTMistMin.setText(MistMin);
	        EdTDwellMin.setText(DwellMin);
	        EdTZvacMin.setText(ZvacMin);
	        
	        EdTMistHour.setText(MistHour);
	        EdTDwellHour.setText(DwellHour);
	        EdTZvacHour.setText(ZvacHour);
    	  
      }
      
      if (RepeatApp.equals("2")){
  	   
            try {
                locationId = getIntent().getStringExtra("locationId");
                MistHour = getIntent().getStringExtra("MistHour");
                MistMin = getIntent().getStringExtra("MistMin");
                DwellHour = getIntent().getStringExtra("DwellHour");
                DwellMin = getIntent().getStringExtra("DwellMin");
                ZvacHour = getIntent().getStringExtra("ZvacHour");
                ZvacMin = getIntent().getStringExtra("ZvacMin");
                typeApplication = getIntent().getStringExtra("typeApplication");
                typeConfigure = getIntent().getStringExtra("typeConfigure");

                commonState.LocationId = locationId;
                commonState.MistHour = MistHour;
                commonState.MistMin = MistMin;
                commonState.DwellHour = DwellHour;
                commonState.DwellMin = DwellMin;
                commonState.ZvacHour = ZvacHour;
                commonState.ZvacMin = ZvacMin;
                commonState.typeApplication = typeApplication;
                commonState.typeConfigure = typeConfigure;
            } catch (Exception e) {
                locationId = commonState.LocationId ;
                MistHour = commonState.MistHour ;
                MistMin = commonState.MistMin ;
                DwellHour = commonState.DwellHour ;
                DwellMin = commonState.DwellMin ;
                ZvacHour = commonState.ZvacHour ;
                ZvacMin = commonState.ZvacMin ;
                typeApplication = commonState.typeApplication ;
                typeConfigure = commonState.typeConfigure ;
            }
	        
	        EdTMistMin.setText(MistMin);
	        EdTDwellMin.setText(DwellMin);
	        EdTZvacMin.setText(ZvacMin);
	        
	        EdTMistHour.setText(MistHour);
	        EdTDwellHour.setText(DwellHour);
	        EdTZvacHour.setText(ZvacHour);
	        imBtnNext.setVisibility(View.VISIBLE);
    }
      		
  }
  
  
  
  
  public void Calculate(){
	  
	  
		if( (!"00".equals(EdTMistHour.getText()) || !"0".equals(EdTMistHour.getText())) && (EdTMistMin.getText().toString().trim().equals("0") || EdTMistMin.getText().toString().trim().equals("00"))){
			
			  imBtnNext.setVisibility(View.VISIBLE);
			  BlinkNextButtonImage();
			
		}
		
		if( (!"00".equals(EdTMistMin.getText()) || !"0".equals(EdTMistMin.getText())) && (EdTMistHour.getText().toString().trim().equals("0") || EdTMistHour.getText().toString().trim().equals("00"))){
			
			  imBtnNext.setVisibility(View.VISIBLE);
			  BlinkNextButtonImage();
			
		}
		
		if( (EdTMistMin.getText().toString().trim().equals("0") || EdTMistMin.getText().toString().trim().equals("00")) && (EdTMistHour.getText().toString().trim().equals("0") || EdTMistHour.getText().toString().trim().equals("00"))){
			  imBtnNext.clearAnimation();
			  imBtnNext.setVisibility(View.INVISIBLE);
			 
			
		}
	  
	  
	  if(EdTDwellMin.getText().toString().trim().equals("") || EdTZvacMin.getText().toString().trim().equals("") || EdTMistHour.getText().toString().trim().equals("")
		 || EdTDwellHour.getText().toString().trim().equals("") || EdTZvacHour.getText().toString().trim().equals("")){
		 // Toast.makeText(this, "MISTMIN is 0:" + EdTMistMin.getText().toString().trim() , Toast.LENGTH_LONG).show();
		  
		  imBtnNext.clearAnimation();
		  imBtnNext.setVisibility(View.INVISIBLE);
		  
		  
	  } 

//	  if(!"00".equals(EdTMistMin.getText().toString().trim()) || !"0".equals(EdTMistMin.getText().toString().trim()) || !"00".equals(EdTDwellMin.getText().toString().trim()) || 
//	  !"0".equals(EdTDwellMin.getText().toString().trim()) || !"00".equals(EdTZvacMin.getText().toString().trim()) || !"0".equals(EdTZvacMin.getText().toString().trim()) || 
//	  !"00".equals(EdTMistHour.getText().toString().trim()) || !"0".equals(EdTMistHour.getText().toString().trim()) || !"00".equals(EdTDwellHour.getText().toString().trim()) 
//	  || !"0".equals(EdTDwellHour.getText().toString().trim()) || !"00".equals(EdTZvacHour.getText().toString().trim())  || !"0".equals(EdTZvacHour.getText().toString().trim())){
		  
//		  imBtnNext.setVisibility(View.VISIBLE); 
		  
//		  BlinkNextButtonImage();
		  
//	    } else {
	    	
//	    	imBtnNext.clearAnimation();
//	    	imBtnNext.setVisibility(View.INVISIBLE);
	    	
//	    }
	  
			  
	  	InputMethodManager inputManager = (InputMethodManager)this.getSystemService(Context.INPUT_METHOD_SERVICE); 
	    inputManager.hideSoftInputFromWindow(this.getCurrentFocus().getWindowToken(),      
	    InputMethodManager.HIDE_NOT_ALWAYS);
	  
	  
  }
  
  
  public void BlinkNextButtonImage(){
	  
	    Animation animation = new AlphaAnimation(1, 0); 
	    animation.setDuration(800); 
	    animation.setInterpolator(new LinearInterpolator()); 
	    animation.setRepeatCount(Animation.INFINITE); 
	    animation.setRepeatMode(Animation.REVERSE); 
	    imBtnNext.startAnimation(animation);
  }
 

  public void onClick(View v) {
	  

      
      if(v==imBtnReturn){
	   		imBtnNext.clearAnimation();
    		finish();
    		Intent intent = new Intent();
			intent.setClass(this,New_Application_Activity.class);
			startActivity(intent);
    	}
    	
  	if(v==imBtnControlCenter){
  			Intent resultIntent = new Intent();
  			resultIntent.setClass(this,ZimekActivity.class);
  			setResult(Activity.RESULT_OK, resultIntent);
  			startActivity(resultIntent);
    	}
  	
  	if(v==imBtnNext){
  		  typeApplication = "Manual";
  		  typeConfigure = "N/A";
  		
  	   if (RepeatApp.equals("1")){   
	        MistMin = EdTMistMin.getText().toString();
	        DwellMin = EdTDwellMin.getText().toString();
	        ZvacMin = EdTZvacMin.getText().toString();
	        
	        MistHour = EdTMistHour.getText().toString();
	        DwellHour = EdTDwellHour.getText().toString();
	        ZvacHour = EdTZvacHour.getText().toString();

	              
	        Intent intent = new Intent();
  			intent.setClass(this,Repeat_Application_Activity.class);
  			intent.putExtra("locationId", locationId);
  			intent.putExtra("MistHour", MistHour);
			intent.putExtra("MistMin", MistMin);
			intent.putExtra("DwellHour", DwellHour);
			intent.putExtra("DwellMin", DwellMin);
			intent.putExtra("ZvacHour", ZvacHour);
			intent.putExtra("ZvacMin", ZvacMin);
			intent.putExtra("RepeatApp", RepeatApp);
  			
	        startActivity(intent);
     } else {
    	 
        try {
    	    typeApplication = getIntent().getStringExtra("typeApplication");
 		    typeConfigure = getIntent().getStringExtra("typeConfigure"); 
            
            if (typeApplication!=null) commonState.typeApplication = typeApplication;
            if (typeConfigure!=null) commonState.typeConfigure = typeConfigure;
        } catch (Exception e) {
            typeApplication = commonState.typeApplication ;
            typeConfigure = commonState.typeConfigure ;
        }
    	 
		String MistHour = EdTMistHour.getText().toString();
		String MistMin = EdTMistMin.getText().toString();
		String DwellHour = EdTDwellHour.getText().toString();
		String DwellMin = EdTDwellMin.getText().toString();
		String ZvacHour = EdTZvacHour.getText().toString();
		String ZvacMin = EdTZvacMin.getText().toString();
		
		//Toast.makeText(this, "EditAppAuto: " + typeApplication, Toast.LENGTH_LONG).show();
  		if ( (EdTMistMin.getText().toString().trim().equals("0") || EdTMistMin.getText().toString().trim().equals("00")) && (EdTMistHour.getText().toString().trim().equals("0") || EdTMistHour.getText().toString().trim().equals("00"))){
  			
  			imBtnNext.clearAnimation();
  		  imBtnNext.setVisibility(View.INVISIBLE);
  		  
  			Calculate();
  			
  		} else{
  			
  			if(EdTMistHour.getText().toString().trim().equals("")){
  	  			EdTMistHour.setText("00");
  	  		}
  	  		
  	  		if(EdTDwellHour.getText().toString().trim().equals("")){
  	  			EdTDwellHour.setText("00");
  	  		}
  	  		
  	  		if(EdTZvacHour.getText().toString().trim().equals("")){
  	  			EdTZvacHour.setText("00");
  	  		}
  	  		
  	  		if(EdTDwellMin.getText().toString().trim().equals("")){
  	  			EdTDwellMin.setText("00");
  	  		}
  	  		
  	  		if(EdTZvacMin.getText().toString().trim().equals("")){
  	  			EdTZvacMin.setText("00");
  	  		}
  			
    		Intent intent = new Intent();
  			intent.setClass(this,Enter_Location_Activity.class);
  			//intent.putExtra("MistHour", MistHour);
			//intent.putExtra("MistMin", MistMin);
			//intent.putExtra("DwellHour", DwellHour);
			//intent.putExtra("DwellMin", DwellMin);
			//intent.putExtra("ZvacHour", ZvacHour);
			//intent.putExtra("ZvacMin", ZvacMin);
			//intent.putExtra("typeApplication", typeApplication);
			//intent.putExtra("typeConfigure", typeConfigure);
  			commonState.MistHour = MistHour;
  			commonState.MistMin = MistMin;
  			commonState.DwellHour = DwellHour;
  			commonState.DwellMin = DwellMin;
  			commonState.ZvacHour = ZvacHour;
  			commonState.ZvacMin = ZvacMin;
  			commonState.typeApplication = typeApplication;
  			commonState.typeConfigure = typeConfigure;

  			startActivity(intent);
  		}
  			
     		}

  	   		//imBtnNext.clearAnimation();
  	   		onDestroy();
  	   
    	}

  	
  }
  
  public void onDestroy()
  {   
//      Cleanup();
      super.onDestroy();
  }
  
}

//  private void Cleanup()
//  {    
	  
//	  Bitmap ManualApplication_background = BitmapFactory.decodeStream(getResources().openRawResource(R.drawable.manual_application));
//		ImageView ManualApplication_Screen_Background = (ImageView)findViewById(R.drawable.manual_application);
		

//		if (ManualApplication_Screen_Background!= null)
//			 ManualApplication_Screen_Background.setImageBitmap(null);
		 		
//		 	if (ManualApplication_background!= null) {
//			      ManualApplication_background.recycle();
			     
//			   }
//			   ManualApplication_background = null;
//			   ManualApplication_Screen_Background = null;
	   	
			  // System.gc();
		
//		Bitmap btnReturnImage = BitmapFactory.decodeStream(getResources().openRawResource(R.drawable.back_button));
//		ImageView btnReturn = (ImageView)findViewById(R.drawable.back_button);
		
//		 if (btnReturn!= null)
//			 btnReturn.setImageBitmap(null);
		 		
//		 if (btnReturnImage!= null) {
//			 btnReturnImage.recycle();
			     
//			   }
//		 btnReturnImage = null;
//		 btnReturn = null;
		 
//		 Bitmap btnNextButtonImage = BitmapFactory.decodeStream(getResources().openRawResource(R.drawable.next_button));
//		 ImageView btnNextButton = (ImageView)findViewById(R.drawable.next_button);
			
//			 if (btnNextButton!= null)
//				 btnNextButton.setImageBitmap(null);
			 		
//			 if (btnNextButtonImage!= null) {
//				 btnNextButtonImage.recycle();
				     
//				   }
//			 btnNextButtonImage = null;
//			 btnNextButton = null;
			 
			 
//			 Bitmap btnControlCenterImage = BitmapFactory.decodeStream(getResources().openRawResource(R.drawable.controlcenter_button));
//			 ImageView btnControlCenterButton = (ImageView)findViewById(R.drawable.controlcenter_button);
				
//				 if (btnControlCenterButton!= null)
//					 btnControlCenterButton.setImageBitmap(null);
				 		
//				 if (btnControlCenterImage!= null) {
//					 btnControlCenterImage.recycle();
					     
//					   }
//				 btnControlCenterImage = null;
//				 btnControlCenterButton = null;

//      System.gc();
//      Runtime.getRuntime().gc();  
//  }
  
  
  

  
//}
  
  
