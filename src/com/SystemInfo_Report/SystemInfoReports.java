package com.SystemInfo_Report;

public class SystemInfoReports {
	 
    //private variables
    int _id;
    String _VipSerial;
    String _ZvacSerial;
    String _OplcSerial;
    String _Customer;
    String _DeliveryDate;
    String _Warranty;
    String _Other;
 
 
    // Empty constructor
    public SystemInfoReports(){
 
    }
    // constructor
    public SystemInfoReports(int id, String VipSerial, String ZvacSerial, String OplcSerial, String Customer, String DeliveryDate, String Warranty, String Other){
        this._id = id;
        this._VipSerial = VipSerial;
        this._ZvacSerial = ZvacSerial;
        this._OplcSerial = OplcSerial;
        this._Customer = Customer;
        this._DeliveryDate = DeliveryDate;
        this._Warranty = Warranty;
        this._Other = Other;

    }
    
    
    public SystemInfoReports(String VipSerial, String ZvacSerial, String OplcSerial, String Customer, String DeliveryDate, String Warranty, String Other){
        this._VipSerial = VipSerial;
        this._ZvacSerial = ZvacSerial;
        this._OplcSerial = OplcSerial;
        this._Customer = Customer;
        this._DeliveryDate = DeliveryDate;
        this._Warranty = Warranty;
        this._Other = Other;

    }
 
    // constructor
    // getting ID
    public int getID(){
        return this._id;
    }
 
    // setting id
    public void setID(int id){
        this._id = id;
    }
 
    // getting name
    public String getVipSerial(){
        return this._VipSerial;
    }
 
    // setting name
    public void setVipSerial(String VipSerial){
        this._VipSerial = VipSerial;
    }
 
    // getting phone number
    public String getZvacSerial(){
        return this._ZvacSerial;
    }
 
    // setting phone number
    public void setZvacSerial(String ZvacSerial){
        this._ZvacSerial = ZvacSerial;
    }
    
    // getting phone number
    public String getOplcSerial(){
        return this._OplcSerial;
    }
 
    // setting phone number
    public void setOplcSerial(String OplcSerial){
        this._OplcSerial = OplcSerial;
    }
    
    // getting phone number
    public String getCustomer(){
        return this._Customer;
    }
 
    // setting phone number
    public void setCustomer(String Customer){
        this._Customer = Customer;
    }
    
    // getting phone number
    public String getDeliveryDate(){
        return this._DeliveryDate;
    }
 
    // setting phone number
    public void setDeliveryDate(String DeliveryDate){
        this._DeliveryDate = DeliveryDate;
    }
    
    // getting phone number
    public String getWarranty(){
        return this._Warranty;
    }
 
    // setting phone number
    public void setWarranty(String Warranty){
        this._Warranty = Warranty;
    }
    
    // getting phone number
    public String getOther(){
        return this._Other;
    }
 
    // setting phone number
    public void setOther(String Other){
        this._Other = Other;
    }
    
    
}