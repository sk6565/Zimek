package com.circuit.zimek;

import java.io.File;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageButton;





public class TableOfContents_Activity extends Activity implements OnClickListener{
	
	public String language;
	public Uri path;
    public CommonState commonState = null;
    private ImageButton imBtnReturn;
    private ImageButton imBtnUserGuideOperators;
    private ImageButton imBtnUserGuideManagers;
    private ImageButton imBtnUserGuideMaint;
    private ImageButton imBtnUserGuideTroubleshooting;
	
   @Override
   public void onCreate(Bundle savedInstanceState) {
       super.onCreate(savedInstanceState);
       requestWindowFeature(Window.FEATURE_NO_TITLE);
       getWindow().getDecorView().setSystemUiVisibility(View.STATUS_BAR_HIDDEN);
       getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, 
       WindowManager.LayoutParams.FLAG_FULLSCREEN);
       setContentView(R.layout.tableofcontents_app);
       commonState = (CommonState) getApplication();
       commonState.activity_name = "TableOfContents_Activity";
       language = commonState.language;
       
       imBtnReturn = (ImageButton)findViewById(R.id.imBtnReturn);
       imBtnReturn.setOnClickListener(this);
       
       imBtnUserGuideOperators = (ImageButton)findViewById(R.id.imBtnUserGuideOperators);
       imBtnUserGuideOperators.setOnClickListener(this);
       
       imBtnUserGuideManagers = (ImageButton)findViewById(R.id.imBtnUserGuideManagers);
       imBtnUserGuideManagers.setOnClickListener(this);
       
       imBtnUserGuideMaint = (ImageButton)findViewById(R.id.imBtnUserGuideMaint);
       imBtnUserGuideMaint.setOnClickListener(this);
       
       imBtnUserGuideTroubleshooting = (ImageButton)findViewById(R.id.imBtnUserGuideTroubleshooting);
       imBtnUserGuideTroubleshooting.setOnClickListener(this);   
       
       if (language.equals("English")) {
   	        imBtnReturn.setImageResource(R.drawable.return_eng);
		   	imBtnUserGuideOperators.setImageResource(R.drawable.user_guide_operators_eng);
		    imBtnUserGuideManagers.setImageResource(R.drawable.user_guide_managers_spa);
		    imBtnUserGuideMaint.setImageResource(R.drawable.user_guide_maintenance_and_repair_eng);
		    imBtnUserGuideTroubleshooting.setImageResource(R.drawable.user_guide_troubleshooting_eng);
      } else {
 		   	imBtnReturn.setImageResource(R.drawable.return_spa);
 		   	imBtnUserGuideOperators.setImageResource(R.drawable.user_guide_operators_spa);
 		    imBtnUserGuideManagers.setImageResource(R.drawable.user_guide_managers_spa);
 		    imBtnUserGuideMaint.setImageResource(R.drawable.user_guide_maintenance_and_repair_spa);
 		    imBtnUserGuideTroubleshooting.setImageResource(R.drawable.user_guide_troubleshooting_spa);
      }
   }
   
   
   
   @Override  
   public void onResume(){
	 super.onResume();
	 
	 getWindow().getDecorView().setSystemUiVisibility(View.STATUS_BAR_HIDDEN);	 
   }
   
   
   
   public void onClick(View v){
	   if(v==imBtnReturn){
		   Intent resultIntent = new Intent();
		   resultIntent.setClass(this,Home_Screen_Activity.class);
		   setResult(Activity.RESULT_OK, resultIntent);
		   startActivity(resultIntent); 
	   } else {
		   PackageManager packageManager = getPackageManager();
	       Intent intent = new Intent(Intent.ACTION_VIEW);
	       intent.setType("application/pdf");
	       File file;
	       
	       if (v==imBtnUserGuideOperators) {
	    	   if(language.equals("English")){			 
	    		   file = new File("/mnt/sdcard2/Manuals/English/User_Guide_Oper_Eng.pdf");
	    	   } else {
	    		   file = new File("/mnt/sdcard2/Manuals/English/User_Guide_Oper_Spa.pdf");
	    	   }
	       } else if (v==imBtnUserGuideManagers) {
	    	   if(language.equals("English")){			 
	    		   file = new File("/mnt/sdcard2/Manuals/English/User_Guide_Mgr_Eng.pdf");
	    	   } else {
	    		   file = new File("/mnt/sdcard2/Manuals/English/User_Guide_Mgr_Spa.pdf");
	    	   }
	       } else if (v==imBtnUserGuideMaint) {
	    	   if(language.equals("English")){			 
	    		   file = new File("/mnt/sdcard2/Manuals/English/User_Guide_Maint_Eng.pdf");
	    	   } else {
	    		   file = new File("/mnt/sdcard2/Manuals/English/User_Guide_Maint_Spa.pdf");
	    	   }
	       } else {
	    	   if(language.equals("English")){			 
	    		   file = new File("/mnt/sdcard2/Manuals/English/User_Guide_Troubleshooting_Eng.pdf");
	    	   } else {
	    		   file = new File("/mnt/sdcard2/Manuals/English/User_Guide_Troubleshooting_Spa.pdf");
	    	   }
	       }
	       path = Uri.fromFile(file);
	       List<ResolveInfo> list = packageManager.queryIntentActivities(intent, PackageManager.MATCH_DEFAULT_ONLY);
	 		
	 	    if(list.size() > 0){
	 			intent.setDataAndType(path, "application/pdf");
	 			startActivity(intent);
	 		}
	    	    
	   }	   
   }
 
}
