
package com.circuit.zimek;

import android.app.PendingIntent;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.hardware.usb.UsbManager;
import android.os.Binder;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;
import java.util.Timer;
import java.util.TimerTask;

import com.driver.FTDriver;

public class ZComm_Service extends Service {
    Handler mHandler = new Handler();
    
    FTDriver mSerial = null;
    ////////////////////   My functions and variables ///////////////
    private byte[] partialStatus = {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};
	private int bytesRead;
	int beeperFlag = 1;
	int buzzerLevel = 0;
	int strobeFlag = 1;
	int strobeLevel = 0;
	// kludge : use an easy way to setup less than 10 secs buzzer/beeper
	int buzzerTime = 0;
	int secCount = 1;
	
	public CommonState commonState = null; 	
    byte[] zvacStatus =  {(byte)130, 0, 0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};
    byte[] bytesToSend = {(byte)130, 1, 0,0,0,0,0,0,0,0,0,0,0,0}; // 14 bytes
    byte[] partialBytesToSend = {(byte)130, 1, 0,0,0,0,0,0,0,0,0,0,0,0}; // 14 bytes
	String retStr = "";
	
	int i=0;
	
    TimerTask scanTask;
    final Handler handler = new Handler();
    Timer t = new Timer();
    public class MyBinder extends Binder {
    	ZComm_Service getService() {
    		return ZComm_Service.this;
    	}
    }	
    
    @Override
    public IBinder onBind(Intent intent) {
    	return mBinder;
    }
    
    @Override
    public int onStartCommand(Intent internt, int flags, int startid) {
    	// start backgroundTask(intent, startId)
    	Context context = getApplicationContext();        
    	return Service.START_STICKY;
    }

    
    private final IBinder mBinder = new MyBinder();
    
    public void stopService() {
    	ZComm_Service.this.stopSelf();
    }
    
    public int sendZvacCommand(String str) {
    	prepareBytes(str);
    	return i;
    }
    
    public void readFromZvac() {
    	int i;
        int len;
        byte[] rbuf = new byte[4096];
        
        if (mSerial == null) { return; }
    	len = mSerial.read(rbuf);
        rbuf[len] = 0;
        
		commonState.lastCommTime = 0;
		for (i=0; i<len; i++) {
			if (rbuf[i]==-126) {
				bytesRead=0;
			}
			zvacStatus[bytesRead] = rbuf[i];
			bytesRead++;
		}
		if (zvacStatus[13] != 0) {
			commonState.lastCommand = zvacStatus[13];
		}
    }
    
    private void prepareBytes(String fromMain) {
    	String[] temp = fromMain.split(",");
    	String str;
    	int unit;
    	byte val;
    	int i = 0;
    	
    	for (i=0; i<temp.length; i++){
    		str = temp[i];
    		unit = (byte)str.charAt(0); 
    		val = (byte) Integer.parseInt(str.substring(1),10);
    		switch (unit) {
    		case 'F': bytesToSend[2] = val; break;
    		case 'S': bytesToSend[3] = val; strobeFlag = 1; strobeLevel = val; break;
    		case 's': bytesToSend[3] = val; strobeFlag = 0; strobeLevel = val; break;
    		case 'M': bytesToSend[4] = val; break;
    		case 'B': bytesToSend[5] = val; beeperFlag = 1; buzzerLevel = val; break;
    		case 'b': bytesToSend[5] = val; beeperFlag = 0; buzzerLevel = val; break;
    		case 'Q': bytesToSend[5] = val; beeperFlag = 1; buzzerLevel = 1; buzzerTime = val; break;
    		case 'q': bytesToSend[5] = val; beeperFlag = 0; buzzerLevel = 1; buzzerTime = val; break;
    		case 'W': if (zvacStatus[12] == 1) {bytesToSend[6] = 2;} else {bytesToSend[6] = val;} break;
    		case 'V': bytesToSend[7] = val; break;
    		case 'E': bytesToSend[8] = val; break;
    		case 'R': bytesToSend[9] = val; break;
    		case 'X': bytesToSend[10] = val; break;
    		case 'C': bytesToSend[11] = val; break;
    		case 'H': bytesToSend[12] = val; break;
    		}
    	}
    	if (commonState.mBeeper.equals("0")) {
    		beeperFlag = 0;
    		buzzerLevel = 0;
    		bytesToSend[5] = 0;
    	}
    	setCheckSum();
    }
    private void setCheckSum() {
        int checkSum = 0;
        for (i=1; i<13; i++) {
            checkSum =  bytesToSend[i] + checkSum;
        }
        checkSum = checkSum & 0x7f;
        bytesToSend[13] = (byte) checkSum;
    }
    
    public void refreshZvac(){
    	scanTask = new TimerTask() {
    		public void run() {
    			handler.post(new Runnable() {
    				public void run() {
    						
    					try {
    						readFromZvac();
    					} catch (Exception e){
    						e.printStackTrace();
    					}
    					
    						secCount++;
    						if (secCount % 2 == 1) {
    							if (strobeFlag == 1) bytesToSend[3] = 0;
    							if (beeperFlag == 1) bytesToSend[5] = 0;
    						} else {
    							if (strobeFlag == 1) bytesToSend[3] = (byte) strobeLevel;
    							if (beeperFlag == 1) bytesToSend[5] = (byte) buzzerLevel;
    						}
    						if (buzzerTime > 0) {
    							buzzerTime--;
    							if (buzzerTime <= 0) {
    								buzzerLevel = 0;
    								bytesToSend[5] = (byte) buzzerLevel;
    							}
    						}
    						setCheckSum();
    						if (secCount > 86400) {secCount = 0;}
    						
    						try {
    							if (mSerial != null) { mSerial.write(bytesToSend, 14, 0); }
    							else { mSerial = commonState.mSerial;}
    						} catch (Exception e) {
    							mSerial = commonState.mSerial;
    							e.printStackTrace();
    						}
    						commonState.lastCommTime++;
    						
    				}
    			});
    		}
    	};
    	t.schedule(scanTask, 300, 1000); 
    }
    
    ////////////////////   End My functions ///////////////
    

    /** Called when the service is first created. */
    @Override
    public void onCreate() {
        super.onCreate();
        
        commonState = (CommonState) getApplication();
        commonState.zvacStatus = zvacStatus;
        mSerial = commonState.mSerial;
    	
    	String value1 = "C0";
        prepareBytes(value1);
        refreshZvac();
    }
    
    @Override
    public void onDestroy() {
        mSerial.end();
        //unregisterReceiver(mUsbReceiver);
        super.onDestroy();
    }
}
