package com.SystemSettings_Report;

public class SystemSettingsReports {
	 
    //private variables
    int _id;
    String _Beeper;
    String _Metrics;

    
  
 
    // Empty constructor
    public SystemSettingsReports(){
 
    }
    // constructor
    public SystemSettingsReports(int id, String Beeper, String Metrics){
        this._id = id;
        this._Beeper = Beeper;
        this._Metrics = Metrics;
       
  
    }
 
    // constructor
    public SystemSettingsReports(String Beeper, String Metrics){
    	this._Beeper = Beeper;
        this._Metrics = Metrics;
        
        
        
    }
    // getting ID
    public int getID(){
        return this._id;
    }
 
    // setting id
    public void setID(int id){
        this._id = id;
    }
 
    // getting name
    public String getBeeper(){
        return this._Beeper;
    }
 
    // setting name
    public void setBeeper(String Beeper){
        this._Beeper = Beeper;
    }
 
    // getting phone number
    public String getMetrics(){
        return this._Metrics;
    }
 
    // setting phone number
    public void setMetrics(String Metrics){
        this._Metrics = Metrics;
    }
    
  
    
    
}