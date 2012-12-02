package com.PassCode_Report;

public class PassCodeReports {
	 
    //private variables
    int _id;
    String _defaultPassCode;
    String _newPassCode;
    String _defaultPassCodeOPS;
    String _NewPassCodeOPS;
    String _PassCodeOPSActivated;
    
  
 
    // Empty constructor
    public PassCodeReports(){
 
    }
    // constructor
    public PassCodeReports(int id, String defaultPassCode, String newPassCode, String defaultPassCodeOPS, String NewPassCodeOPS, String PassCodeOPSActivated){
        this._id = id;
        this._defaultPassCode = defaultPassCode;
        this._newPassCode = newPassCode;
        this._defaultPassCodeOPS = defaultPassCodeOPS;
        this._NewPassCodeOPS = NewPassCodeOPS;
        this._PassCodeOPSActivated = PassCodeOPSActivated;
  
    }
 
    // constructor
    public PassCodeReports(String defaultPassCode, String newPassCode, String defaultPassCodeOPS, String NewPassCodeOPS, String PassCodeOPSActivated){
    	this._defaultPassCode = defaultPassCode;
        this._newPassCode = newPassCode;
        this._defaultPassCodeOPS = defaultPassCodeOPS;
        this._NewPassCodeOPS = NewPassCodeOPS;
        this._PassCodeOPSActivated = PassCodeOPSActivated;
        
        
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
    public String getdefaultPassCode(){
        return this._defaultPassCode;
    }
 
    // setting name
    public void setdefaultPassCode(String defaultPassCode){
        this._defaultPassCode = defaultPassCode;
    }
 
    // getting phone number
    public String getnewPassCode(){
        return this._newPassCode;
    }
 
    // setting phone number
    public void setnewPassCode(String newPassCode){
        this._newPassCode = newPassCode;
    }
    
    // getting phone number
    public String getdefaultPassCodeOPS(){
        return this._defaultPassCodeOPS;
    }
 
    // setting phone number
    public void setdefaultPassCodeOPS(String defaultPassCodeOPS){
        this._defaultPassCodeOPS = defaultPassCodeOPS;
    }
    
    
    public String getNewPassCodeOPS(){
        return this._NewPassCodeOPS;
    }
 
    // setting phone number
    public void setNewPassCodeOPS(String NewPassCodeOPS){
        this._NewPassCodeOPS = NewPassCodeOPS;
    }
    
    public String getPassCodeOPSActivated(){
        return this._PassCodeOPSActivated;
    }
 
    // setting phone number
    public void setPassCodeOPSActivated(String PassCodeOPSActivated){
        this._PassCodeOPSActivated = PassCodeOPSActivated;
    }
    
    
}