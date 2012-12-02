package com.circuit.zimek;


import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;



public class ApplicationModules_Activity extends Activity implements OnClickListener{
	
	 private ImageButton imBtnRoomsOnly;
	 private ImageButton imBtnVehiclesOnly;
	 private ImageButton imBtnRoomandVehicles;
	 private ImageButton imBtnWarning;
	 private ImageButton imBtnReturn;
	 
	 private ImageView imViewApplicationModuleHead;
	 public String language;
	 
	 public String Module;
     public CommonState commonState = null;

	 
	 

	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().getDecorView().setSystemUiVisibility(View.STATUS_BAR_HIDDEN);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, 
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.applicationmodules_app);
       
        commonState = (CommonState) getApplication();
        commonState.activity_name = "ApplicationModules_Activity";
        language = commonState.language;

    	
    	if (language.equals("English")){
    		
    		 
    		imBtnRoomsOnly = (ImageButton)findViewById(R.id.imBtnRoomsOnly);
    		imBtnVehiclesOnly = (ImageButton)findViewById(R.id.imBtnVehiclesOnly);
    		imBtnRoomandVehicles = (ImageButton)findViewById(R.id.imBtnRoomandVehicles);
    		
    		imBtnWarning = (ImageButton)findViewById(R.id.imBtnWarning);
    		imBtnReturn = (ImageButton)findViewById(R.id.imBtnReturn);
    		imViewApplicationModuleHead = (ImageView)findViewById(R.id.imViewApplicationModuleHead);

		   	  
    	} 
    	
    	if (language.equals("Spanish")){
    			
    		imBtnRoomsOnly = (ImageButton)findViewById(R.id.imBtnRoomsOnly);
    		imBtnRoomsOnly.setImageResource(R.drawable.rooms_only_spa_1);
  		   	   
    		imBtnVehiclesOnly = (ImageButton)findViewById(R.id.imBtnVehiclesOnly);
    		imBtnVehiclesOnly.setImageResource(R.drawable.vehicles_only_spa_1);
			   
    		imBtnRoomandVehicles = (ImageButton)findViewById(R.id.imBtnRoomandVehicles);
    		imBtnRoomandVehicles.setImageResource(R.drawable.rooms_and_vehicles_spa_1);
			   
    	
    		 
    		 imBtnWarning = (ImageButton)findViewById(R.id.imBtnWarning);
    		 imBtnWarning.setImageResource(R.drawable.warning_spa);
    		 
    		 imBtnReturn = (ImageButton)findViewById(R.id.imBtnReturn);
			 imBtnReturn.setImageResource(R.drawable.return_spa);
			   
			 imViewApplicationModuleHead = (ImageView)findViewById(R.id.imViewApplicationModuleHead);
			 imViewApplicationModuleHead.setImageResource(R.drawable.application_modules_head_spa);
    		 
    	}  
        
        
        
       SharedPreferences Modules = PreferenceManager.getDefaultSharedPreferences(this);
 	   String ApplicationModule = Modules.getString("Module","");
 	   
 	   if (!ApplicationModule.equals("")){
 		   
 		 // Toast.makeText(this, "NOT NULL", Toast.LENGTH_SHORT).show();
 		  
 		  Module = ApplicationModule;
 		  
 		   ModuleSelected();
 		   
  	   
  	 
 	   } else {
 		   
 		  Module = "RoomsOnly";
 		   
 		  // Toast.makeText(this, "ELSE MODULE", Toast.LENGTH_SHORT).show();
 		   
 		  SharedPreferences Mods = PreferenceManager.getDefaultSharedPreferences(this);
   		  SharedPreferences.Editor editor=Mods.edit();
   		  editor.putString("Module",Module);
   		  editor.commit();
 		   

 		   
 		   ModuleSelected();
 	   }
        
        
        
    	
    	imBtnRoomsOnly.setOnClickListener(this);
    	imBtnReturn.setOnClickListener(this);
        imBtnWarning.setOnClickListener(this);
        imBtnVehiclesOnly.setOnClickListener(this);
        imBtnRoomandVehicles.setOnClickListener(this);
        
        imBtnWarning.setVisibility(View.INVISIBLE);
         
        
        
    }
    
    public void ModuleSelected(){
    	
    	 if(Module.equals("RoomsOnly")){
    		 
    		 
    		 if (language.equals("English")){
	       			
	       			imBtnRoomsOnly.setImageResource(R.drawable.rooms_only_eng_2);
	       			imBtnVehiclesOnly.setImageResource(R.drawable.vehicles_only_eng_1);
	       			imBtnRoomandVehicles.setImageResource(R.drawable.rooms_and_vehicles_eng_1);
	       			
	       		}
	       		
	       		if (language.equals("Spanish")){
	       			
	       			imBtnRoomsOnly.setImageResource(R.drawable.rooms_only_spa_2);
	       			imBtnVehiclesOnly.setImageResource(R.drawable.vehicles_only_spa_1);
	       			imBtnRoomandVehicles.setImageResource(R.drawable.rooms_and_vehicles_spa_1);
	       			
	       		}
	       		
	       		Module = "RoomsOnly";
    		 
    		 
 			   
 		   }
 		   
 		  if(Module.equals("VehiclesOnly")){
 			  

 	  	       		
 	  	       		if (language.equals("English")){
 	  	       			
 	  	       			imBtnRoomsOnly.setImageResource(R.drawable.rooms_only_eng_1);
 	  	       			imBtnVehiclesOnly.setImageResource(R.drawable.vehicles_only_eng_2);
 	  	       			imBtnRoomandVehicles.setImageResource(R.drawable.rooms_and_vehicles_eng_1);
 	  	       			
 	  	       		}
 	  	       		
 	  	       		if (language.equals("Spanish")){
 	  	       			
 	  	       			imBtnRoomsOnly.setImageResource(R.drawable.rooms_only_spa_1);
 	  	       			imBtnVehiclesOnly.setImageResource(R.drawable.vehicles_only_spa_2);
 	  	       			imBtnRoomandVehicles.setImageResource(R.drawable.rooms_and_vehicles_spa_1);
 	  	       			
 	  	       		}
 	  	       		
 	  	       		Module = "VehiclesOnly";
 			  
			   
		   }
 		  
 		 if(Module.equals("RoomsandVehicles")){
 			 

	       		if (language.equals("English")){
	       			
	       			imBtnRoomsOnly.setImageResource(R.drawable.rooms_only_eng_1);
	       			imBtnVehiclesOnly.setImageResource(R.drawable.vehicles_only_eng_1);
	       			imBtnRoomandVehicles.setImageResource(R.drawable.rooms_and_vehicles_eng_2);
	       			
	       		}
	       		
	       		if (language.equals("Spanish")){
	       			
	       			imBtnRoomsOnly.setImageResource(R.drawable.rooms_only_spa_1);
	       			imBtnVehiclesOnly.setImageResource(R.drawable.vehicles_only_spa_1);
	       			imBtnRoomandVehicles.setImageResource(R.drawable.rooms_and_vehicles_spa_2);
	       			
	       		}
	       		
	       		Module = "RoomsandVehicles";
	       		

 			 
 		 }
			   
 }
    	
    	
    	
    
    
 public void onClick(View v){
    	
    	if(v==imBtnRoomsOnly){


    	       	Module = "RoomsOnly";
    	       				
    	       	  SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
    	   		  SharedPreferences.Editor editor=preferences.edit();
    	   		  editor.putString("Module",Module);
    	   		  editor.commit();
    	   		  
    	   		  ModuleSelected();
    	    		    
    	   
    	}
    	
    	
     	if(v==imBtnVehiclesOnly){

     		  
  	       		
  	       		Module = "VehiclesOnly";
  	       				
  	       	  SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
  	   		  SharedPreferences.Editor editor=preferences.edit();
  	   		  editor.putString("Module",Module);
  	   		  editor.commit();
  	    		    
  	    		   ModuleSelected();
    	}
     	
     	
     	
     	
     	if(v==imBtnRoomandVehicles){

     	
    	       		
    	       		Module = "RoomsandVehicles";
    	       				
    	       	  SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
    	   		  SharedPreferences.Editor editor=preferences.edit();
    	   		  editor.putString("Module",Module);
    	   		  editor.commit();
    	    		    
    	    	 ModuleSelected();
    	    	    	
    	    	    	
    	    	   
    	}
     	
     	if(v==imBtnReturn){

    		Intent intent = new Intent();
        	intent.setClass(this,SystemSettings_Activity.class);
        	startActivity(intent);
            	 
			
			//onDestroy();
    	}
 }
}
