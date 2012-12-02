package com.TSS_Report;

public class TreatmentReports {
	 
    //private variables
    int _id;
    String _locationId;
    String _mydate;
    String _mytime;
    String _mistTime;
    String _dwellTime;
    String _zvacTime;
    String _totalTime;
    String _zvacSerial;
    String _appResult;
    String _typeApplication;
    String _typeConfigure;
 
    // Empty constructor
    public TreatmentReports(){
 
    }
    // constructor
    public TreatmentReports(int id, String locationId, String myDate, String myTime, String MistTime, String DwellTime, String ZvacTime, String TotalTime, String ZvacSerial, String AppResult, String typeApplication, String typeConfigure){
        this._id = id;
        this._locationId = locationId;
        this._mydate = myDate;
        this._mytime = myTime;
        this._mistTime = MistTime;
        this._dwellTime = DwellTime;
        this._zvacTime = ZvacTime;
        this._totalTime = TotalTime;
        this._zvacSerial = ZvacSerial;
        this._appResult = AppResult;
        this._typeApplication = typeApplication;
        this._typeConfigure = typeConfigure;
    }
 
    // constructor
    public TreatmentReports(String locationId, String myDate, String myTime, String MistTime, String DwellTime, String ZvacTime, String TotalTime, String ZvacSerial, String AppResult, String typeApplication, String typeConfigure){
        this._locationId = locationId;
        this._mydate = myDate;
        this._mytime = myTime;
        this._mistTime = MistTime;
        this._dwellTime = DwellTime;
        this._zvacTime = ZvacTime;
        this._totalTime = TotalTime;
        this._zvacSerial = ZvacSerial;
        this._appResult = AppResult;
        this._typeApplication = typeApplication;
        this._typeConfigure = typeConfigure;
        
        
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
    public String getLocation(){
        return this._locationId;
    }
 
    // setting name
    public void setLocation(String locationId){
        this._locationId = locationId;
    }
 
    // getting phone number
    public String getmyDate(){
        return this._mydate;
    }
 
    // setting phone number
    public void setmyDate(String myDate){
        this._mydate = myDate;
    }
    
    // getting phone number
    public String getmyTime(){
        return this._mytime;
    }
 
    // setting phone number
    public void setmyTime(String myTime){
        this._mytime = myTime;
    }
    
    // getting phone number
    public String getMistTime(){
        return this._mistTime;
    }
 
    // setting phone number
    public void setMistTime(String MistTime){
        this._mistTime = MistTime;
    }
    
    // getting phone number
    public String getDwellTime(){
        return this._dwellTime;
    }
 
    // setting phone number
    public void setDwellTime(String DwellTime){
        this._dwellTime = DwellTime;
    }
    
    // getting phone number
    public String getZvacTime(){
        return this._zvacTime;
    }
 
    // setting phone number
    public void setZvacTime(String ZvacTime){
        this._zvacTime = ZvacTime;
    }
    
    // getting phone number
    public String getTotalTime(){
        return this._totalTime;
    }
 
    // setting phone number
    public void setTotalTime(String TotalTime){
        this._totalTime = TotalTime;
    }
    
    // getting phone number
    public String getZvacSerial(){
        return this._zvacSerial;
    }
 
    // setting phone number
    public void setZvacSerial(String ZvacSerial){
        this._zvacSerial = ZvacSerial;
    }
    
    // getting phone number
    public String getAppResult(){
        return this._appResult;
    }
 
    // setting phone number
    public void setAppResult(String AppResult){
        this._appResult = AppResult;
    }
    
    public String gettypeApplication(){
        return this._typeApplication;
    }
 
    // setting phone number
    public void settypeApplication(String typeApplication){
        this._typeApplication = typeApplication;
    }
    
    public String gettypeConfigure(){
        return this._typeConfigure;
    }
 
    // setting phone number
    public void settypeConfigure(String typeConfigure){
        this._typeConfigure = typeConfigure;
    }
    
    
}