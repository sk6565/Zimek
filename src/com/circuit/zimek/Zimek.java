package com.circuit.zimek;


import java.io.File;
import java.io.FileInputStream;
import java.util.List;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.preference.PreferenceManager;
import android.view.ContextThemeWrapper;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


public class Zimek extends Activity implements OnClickListener{
	
	
	private ImageButton imBtnLanguage;
	private ImageButton imBtnAgree;
	private ImageButton imBtnReadManual;
	private ImageView imViewOM;
	public String language;
	public TextView txtViewLanguage;
	final Context context = this;
    public CommonState commonState = null;


	String[] values;
	
	
   @Override
   public void onCreate(Bundle savedInstanceState) {
       super.onCreate(savedInstanceState);
       
       requestWindowFeature(Window.FEATURE_NO_TITLE);
       //getWindow().getDecorView().setSystemUiVisibility(View.STATUS_BAR_HIDDEN);
       getWindow().getDecorView().setSystemUiVisibility(View.STATUS_BAR_HIDDEN);
       getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, 
               WindowManager.LayoutParams.FLAG_FULLSCREEN);
       setContentView(R.layout.home_screen);
       commonState = (CommonState) getApplication();
       commonState.activity_name = "Zimek";
       
      
 	   ///
 	   
 	  imBtnLanguage = (ImageButton)findViewById(R.id.imBtnLanguage);
 	  imBtnReadManual = (ImageButton)findViewById(R.id.imBtnReadManual);
 	  imBtnAgree = (ImageButton)findViewById(R.id.imBtnAgree);
 	  txtViewLanguage = (TextView )findViewById(R.id.txtViewLanguage);
 	  imViewOM = (ImageView)findViewById(R.id.imViewOM);
      
 	   
      
       SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
	   String savedlanguage=preferences.getString("language","");
	   
	   if (!savedlanguage.equals(null)){
		   
		  language = savedlanguage;
     	  LanguageSelected();
 		
	   } else {
		   
		   
		     language = "English";
		   
		   	 //SharedPreferences preferences1 = PreferenceManager.getDefaultSharedPreferences(Home_Screen_Activity.this);
			  SharedPreferences.Editor editor=preferences.edit();
			  editor.putString("English",language);
			  editor.commit();
			  
			  LanguageSelected();
		   
		   
	   }
	
       
       
       imBtnLanguage.setOnClickListener(this);
       imBtnAgree.setOnClickListener(this);
       imBtnReadManual.setOnClickListener(this);

       values = new String[] { "English", "Espa�ol"};
       
       	Animation animation = new AlphaAnimation(1, 0); 
	    animation.setDuration(800); 
	    animation.setInterpolator(new LinearInterpolator()); 
	    animation.setRepeatCount(Animation.INFINITE); 
	    animation.setRepeatMode(Animation.REVERSE); 
	    imBtnAgree.startAnimation(animation);
	    //txtViewWearPPE.startAnimation(animation);
       //

       
       imBtnLanguage.setOnClickListener(new OnClickListener() {
    	   
    	   @Override
    	   public void onClick(View v) {
    		   
    		     
    	    SelectLanguage();
    	   }
    	  });

       
   }
   
   public void LanguageSelected(){
	   
	   if (language.equals("English")){
   		
		     txtViewLanguage.setText("English");
			 imBtnAgree.setImageResource(R.drawable.agree_eng);
			 imViewOM.setImageResource(R.drawable.operator_shall_follow_eng);
			 imBtnReadManual.setImageResource(R.drawable.read_manual_eng);
			  
		   	  
   	} 
	   
	   if (language.equals("Spanish")){
		   
		   txtViewLanguage.setText("Espa�ol");
		   imBtnAgree.setImageResource(R.drawable.agree_spa);
		   imViewOM.setImageResource(R.drawable.operator_shall_follow_spa);
		   imBtnReadManual.setImageResource(R.drawable.read_manual_spa);
	   		
	   	  
   	} 
	   
	      SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(Zimek.this);
		  SharedPreferences.Editor editor=preferences.edit();
		  editor.putString("language",language);
		  editor.commit();

	   
   }
   
   
   public void SelectLanguage(){
	   
	   getWindow().getDecorView().setSystemUiVisibility(View.STATUS_BAR_HIDDEN);
	  AlertDialog.Builder builder = new AlertDialog.Builder(new ContextThemeWrapper(this, R.style.Theme_Dialog)); 
	  builder.setTitle("Select Language:"); 
	    builder.setItems(values, new DialogInterface.OnClickListener() {
  	 	
	     public void onClick(DialogInterface dialog, int item) {  
	         //Toast.makeText(Home_Screen_Activity.this, "Selected Item"+ item, Toast.LENGTH_SHORT).show();
	         if (item == 0){
	        	 
	        	 getWindow().getDecorView().setSystemUiVisibility(View.STATUS_BAR_HIDDEN);
	        	 language = "English";
	        	 
	        	   //txtViewLanguage.setText("English");
	    		   //imBtnAgree.setImageResource(R.drawable.agree_eng);
	    		   //imViewOM.setImageResource(R.drawable.operator_shall_follow_eng);
	    		   //imBtnReadManual.setImageResource(R.drawable.read_manual_eng);
	        	 
	        	 LanguageSelected();
	        	 
	         }
	         
	         if (item == 1){
	        	 
	        	 getWindow().getDecorView().setSystemUiVisibility(View.STATUS_BAR_HIDDEN);
	        	 language = "Spanish";
        	 
	  		   //txtViewLanguage.setText("Espa�ol");
			   //imBtnAgree.setImageResource(R.drawable.agree_spa);
			   //imViewOM.setImageResource(R.drawable.operator_shall_follow_spa);
			   //imBtnReadManual.setImageResource(R.drawable.read_manual_spa);
	        	 
	        	 LanguageSelected();
	        	
	         }
	         
	         //SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(Home_Screen_Activity.this);
			  //SharedPreferences.Editor editor=preferences.edit();
			  //editor.putString("language",language);
			  //editor.commit();
			  
			  //getWindow().getDecorView().setSystemUiVisibility(View.STATUS_BAR_HIDDEN);
     
	         
	        }  
	     
	   });  
	     
	      AlertDialog alert = builder.create();  
	      alert.show(); 
	      
	       
   }
   
 
   public void onClick(View v){
	   
      	
      	if(v==imBtnAgree){
      		
      		language = txtViewLanguage.getText().toString();
      		
      		if(language.equals("Espa�ol")){
      			language = "Spanish";
      		}
      		
      		//Toast.makeText(Home_Screen_Activity.this, "Selected Item"+ language, Toast.LENGTH_SHORT).show();
      	    Intent intent = new Intent();
    		intent.setClass(this,ZimekActivity.class);
    		startActivityForResult(intent, 1);
      	
  			//onStop();
  			onDestroy();
      	}
      	
      	if(v==imBtnReadManual){
      		
      		//String baseDir = Environment.getExternalStorageDirectory().getAbsolutePath();
      		
      		Intent intent = new Intent();
        	intent.setClass(this,TableOfContents_Activity.class);
        	startActivity(intent);
  
      		
      		
      		
      		
      		//File file = new File("/sdcard/OPS Coverpage Front.pdf");
      		
      	    // if (file.exists()) {
              //   Uri path = Uri.fromFile(file);
                // Intent intent = new Intent(Intent.ACTION_VIEW);
                // intent.setDataAndType(path, "application/pdf");
                 //intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

                 //try {
                	// Toast.makeText(Home_Screen_Activity.this, 
                      //       "Trying", 
                        //     Toast.LENGTH_SHORT).show();
                     //startActivity(intent);
                     
                // } 
                // catch (ActivityNotFoundException e) {
                  //   Toast.makeText(Home_Screen_Activity.this, 
                    //     "No Application Available to View PDF", 
                      //   Toast.LENGTH_SHORT).show();
                 //}
             //}
      		
      		
      		
      	}
      	

   
      	
      	
      	

   }
   
   @Override
   protected void onResume(){
	  super.onResume();
	  
	 
	  
	  getWindow().getDecorView().setSystemUiVisibility(View.STATUS_BAR_HIDDEN);
	  
	  SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
	  String name=preferences.getString("language","");
	  
	  if(name.equals("English")){
    	  
		   txtViewLanguage.setText("English");
		   imBtnAgree.setImageResource(R.drawable.agree_eng);
		   imViewOM.setImageResource(R.drawable.operator_shall_follow_eng);
		   imBtnReadManual.setImageResource(R.drawable.read_manual_eng);

	  }
       
       if (name.equals("Spanish")){
    	   
    	   txtViewLanguage.setText("Espa�ol");
		   imBtnAgree.setImageResource(R.drawable.agree_spa);
		   imViewOM.setImageResource(R.drawable.operator_shall_follow_spa);
		   imBtnReadManual.setImageResource(R.drawable.read_manual_spa);
    	   
       }  
       

       //Toast.makeText(Home_Screen_Activity.this, "BUNDLE: " + name, Toast.LENGTH_LONG).show();

	   
   }
   
   @Override
   public void onActivityResult(int requestCode, int resultCode, Intent data) {
	   super.onActivityResult(requestCode, resultCode, data);
	   
	   
	  // Toast.makeText(Home_Screen_Activity.this, "Result OK: Home Screen DATA" + language, Toast.LENGTH_LONG).show();
	   
	   switch(requestCode) {
	     case 1: 
	    	 
         if (resultCode == RESULT_OK) {
        	 language = data.getStringExtra("language");
        	
        	 //Toast.makeText(Home_Screen_Activity.this, "Result OK: Home Screen" + data, Toast.LENGTH_LONG).show();
        	 
        	  if(language.equals("English")){
	    	  
        		   txtViewLanguage.setText("English");
	    		   imBtnAgree.setImageResource(R.drawable.agree_eng);
	    		   imViewOM.setImageResource(R.drawable.operator_shall_follow_eng);
	    		   imBtnReadManual.setImageResource(R.drawable.read_manual_eng);
        	  }
 		       
 		       if (language.equals("Spanish")){
 		    	   
 		    	   txtViewLanguage.setText("Espa�ol");
 				   imBtnAgree.setImageResource(R.drawable.agree_spa);
 				   imViewOM.setImageResource(R.drawable.operator_shall_follow_spa);
 				  imBtnReadManual.setImageResource(R.drawable.read_manual_spa);
 		    	   
 		       }  
 		       
 		       break;
         }
	   }
    }

  
  

//@Override
//protected void onDestroy() {
//super.onDestroy();
//System.gc();


//Drawable drawable = imBtnAgree.getDrawable();
//drawable.setCallback(null);

//if (drawable instanceof BitmapDrawable) {
//    BitmapDrawable bitmapDrawable = (BitmapDrawable) drawable;
//    Bitmap bitmap = bitmapDrawable.getBitmap();
//    bitmap.recycle();
//}

//unbindDrawables(findViewById(R.id.HomeScreen));
//
//}
}


   
   

   
         
   
