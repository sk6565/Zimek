package com.circuit.zimek;

import android.app.Application;
import android.content.ServiceConnection;
import com.driver.FTDriver;

import com.SystemInfo_Report.SystemInfoHandler;
import com.SystemInfo_Report.SystemInfoReports;

public class CommonState extends Application {
	public String activity_name = "Home_Screen";
	
	public String language = "English";
	public String typeApplication = "N/A";
	public String typeConfigure = "N/A";
	public String Editable = "0";   // For Systeminformation_Activity
	
	public int downloadprogress;    // These 3 are for Stop_Download_Activity
	public long TotalDownloadTime;
	public float downloaded;
	
	public int preheatprogress;    // These 2 are for StopPreHeat_Activity
	public long TotalPreHeatTime; 
	
	public String Key ;     // Used for View_Reports_Activity

	public int vipState = 0; //0 mist, 1 dwell, 2 zvac
	public String myDate = "";
	public String myTime = "";
	public String LocationId = "";
	public String jobNo = "";
	public String MistHour = "0";
	public String MistMin = "0";
	public String DwellHour = "0";
	public String DwellMin = "0";
	public String ZvacHour = "0";
	public String ZvacMin = "0";
	
	public int beginAppTimer = 0; // apptimer for begin activity


	public long totalMist = 0;  // time in milliseconds
	public long totalDwell = 0; // time in milliseconds
	public long totalZvac = 0;  // time in milliseconds
	public long appTimer = 0;    // time in milliseconds
	
	
	public FTDriver mSerial = null;
	public int startup_done = 0;
	
	public float mmTimeRemainingHours = 0;
	public int mmTimeRemainingMins = 0;
	public float gallons = 0;
	public float liters = 0;
	//public int beeperFlag = 0;
	public String mBeeper = "1";
	// End derived values.
	public int stop=4;
	public int play=2;
	public int pause=1;
	
	public int lastCommTime = 0;
	
	public String zvacStatusString;
	public byte[] zvacStatus;
	public int lastCommand;
	public String zvacSerial;
	
	public ServiceConnection mConnection;
	public ZComm_Service mService;
    public boolean mBound = false;
    //public int  getLiquidLevel() {
    	//liquidLevelPercentage = (100*zvacStatus[10]/6);
    	//return 100*zvacStatus[10]/6; 
    //	return zvacStatus[10];
    //}
    public int getLiquidLevel() {
    	int reading = zvacStatus[10];
    	double gallons;
    	int  percent;
    	
    	if (reading <= 1) gallons = reading * 0;
    	else if (reading <= 10)  gallons = 1 + reading * .1;
    	else if (reading <= 24) gallons = 2 + (reading - 10)*.0714;
    	else if (reading <= 40) gallons = 3 +(reading -24) * .0625;
    	else gallons = 4 +(reading-40)*.0667;
    	
    	if (gallons > 6) percent = 100;
    	else percent = (int) (16.67*gallons);
    	
    	return percent;
    }

    
    /////------------------- Read zvac serial from systeminfo ---------------------
    public void readZvacSerial() {
    	try {
    		SystemInfoHandler sysinfo_db = new SystemInfoHandler(this);
    		int ReportCount = sysinfo_db.getReportsCount();
    		if (ReportCount > 0 ) {
    			SystemInfoReports results = sysinfo_db.getReport(1);
    			zvacSerial = results.getZvacSerial();
    			String [] temp = zvacSerial.split("/");
    			zvacSerial = temp[1].trim();	
    		} else {
    			zvacSerial = "1";
    		}
    		sysinfo_db.close();
    	} catch (Exception e) {
    		zvacSerial = "1";
    	}
    }
    /////------------------- End read zvac serial from systeminfo ---------------------
    
}
