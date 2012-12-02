package com.circuit.zimek;


import android.app.Activity;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.content.SharedPreferences;
import android.hardware.usb.UsbManager;
import android.os.Bundle;
import android.os.IBinder;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.WindowManager;
import android.widget.Toast;

import com.driver.FTDriver;

public class PhantomActivity extends Activity {
	//final Context context = this;
	Intent intent = null;
	public String language = "English";
	public FTDriver mSerial;
	// debug settings
    private static final boolean SHOW_DEBUG = true;

    // Linefeed Code Settings
    private static final int LINEFEED_CODE_CR = 0;
    private static final int LINEFEED_CODE_CRLF = 1;
    private static final int LINEFEED_CODE_LF = 2;

    // Load Bundle Key (for view switching)
    private static final String BUNDLEKEY_LOADTEXTVIEW = "bundlekey.LoadTextView";

    String TAG = "PhantomActivity";
    private int mReadLinefeedCode = LINEFEED_CODE_LF;
    private int mWriteLinefeedCode = LINEFEED_CODE_LF;
    private int mBaudrate = FTDriver.BAUD9600;
    private int mDataBits = FTDriver.FTDI_SET_DATA_BITS_8;
    private int mParity = FTDriver.FTDI_SET_DATA_PARITY_NONE;
    private int mStopBits = FTDriver.FTDI_SET_DATA_STOP_BITS_1;
    private int mFlowControl = FTDriver.FTDI_SET_FLOW_CTRL_NONE;
    private int mBreak = FTDriver.FTDI_SET_NOBREAK;

    private static final String ACTION_USB_PERMISSION =
            "com.circuit.zimek.USB_PERMISSION";    
    

	public CommonState commonState = null;
	private ZComm_Service mService;
    boolean mBound = false;
    
    public ServiceConnection mConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName className, IBinder service)
        {
            mService = ((ZComm_Service.MyBinder) service).getService();
            mBound = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName className) {
            mService = null;
            mBound = false;
        }
    };	
    
    private void sendData(String str) {
    	if (mBound) {
            int num = mService.sendZvacCommand(str);
        } else {
            Intent intent = new Intent(PhantomActivity.this, ZComm_Service.class);
            bindService(intent, mConnection, Context.BIND_AUTO_CREATE);
        }
    }
    
    private void openUsbSerial() {
        if (!mSerial.isConnected()) {
            if (SHOW_DEBUG) {
                Log.d(TAG, "onNewIntent begin");
            }
            mBaudrate = loadDefaultBaudrate();
            if (!mSerial.begin(mBaudrate)) {
                Toast.makeText(this, "cannot open", Toast.LENGTH_SHORT).show();
                return;
            } else {
                Toast.makeText(this, "connected", Toast.LENGTH_SHORT).show();
            }
        }
    }

    protected void onNewIntent(Intent intent) {
        if (SHOW_DEBUG) {
            Log.d(TAG, "onNewIntent");
        }
        
        openUsbSerial();
    };
    
    @Override
    public void onRestart()
    {
    	if (SHOW_DEBUG) {
            Log.d(TAG, "onRestart");
        }
    	super.onRestart();
    }
    
    @Override
    public void onStart()
    {
    	if (SHOW_DEBUG) {
            Log.d(TAG, "onStart");
        }
    	super.onStart();
    }
   
    
    @Override
    public void onPause()
    {
    	if (SHOW_DEBUG) {
            Log.d(TAG, "onPause");
        }
    	super.onPause();
    }
    
    @Override
    public void onStop()
    {
    	if (SHOW_DEBUG) {
            Log.d(TAG, "onStop");
        }
    	super.onStop();
    }
    
    @Override
    public void onDestroy()
    {
    	if (SHOW_DEBUG) {
            Log.d(TAG, "onDestroy");
        }
    	super.onDestroy();
    }
    
	
   @Override
   public void onCreate(Bundle savedInstanceState) {
       super.onCreate(savedInstanceState);
       
       Intent stintent = getIntent();
       String action = stintent.getAction();
       
       commonState = (CommonState) getApplication();
       if (SHOW_DEBUG) {
           Log.v(TAG, "Phantom Activity started");
       }
       if (action != null && action.equals("android.hardware.usb.action.USB_DEVICE_ATTACHED")) {
    	   if (commonState.startup_done == 0) 
    		   android.os.Process.killProcess(android.os.Process.myPid());
       }
       commonState.startup_done = 1;

       //getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

       if (SHOW_DEBUG) {
           Log.v(TAG, "New FTDriver");
       }
       mSerial = new FTDriver((UsbManager) getSystemService(Context.USB_SERVICE));
       if (SHOW_DEBUG) {
           Log.v(TAG, "New instance : " + mSerial);
       }
       // listen for new devices
       IntentFilter filter = new IntentFilter();
       filter.addAction(UsbManager.ACTION_USB_DEVICE_ATTACHED);
       filter.addAction(UsbManager.ACTION_USB_DEVICE_DETACHED);
       registerReceiver(mUsbReceiver, filter);

       mBaudrate = loadDefaultBaudrate();
       PendingIntent permissionIntent = PendingIntent.getBroadcast(this, 0, new Intent(
               ACTION_USB_PERMISSION), 0);
       mSerial.setPermissionIntent(permissionIntent);

       if (SHOW_DEBUG) {
           Log.d(TAG, "FTDriver beginning");
       }

       if (mSerial.begin(mBaudrate)) {
           if (SHOW_DEBUG) {
               Log.d(TAG, "FTDriver began");
           }
           loadDefaultSettingValues();
           Toast.makeText(this, "Connected", Toast.LENGTH_SHORT).show();
       } else {
           if (SHOW_DEBUG) {
               Log.d(TAG, "FTDriver no connection");
           }
           Toast.makeText(this, "No connection", Toast.LENGTH_SHORT).show();
       }
       
       commonState.mSerial = mSerial;
   }
   
   @Override
   public void onResume()
   {
   	if (SHOW_DEBUG) {
           Log.d(TAG, "onResume");
       }
   	super.onResume();

       sendData("C0,B0,F0,S0,W0,R0");
       
       Intent intent = new Intent();
       intent.setAction("foo");
       
       if (commonState.activity_name.equals("Home_Screen")) {
          intent.setClass(this,Home_Screen_Activity.class);
      }  else if (commonState.activity_name.equals("ApplicationModules_Activity")) {
            intent.setClass(this,ApplicationModules_Activity.class);
      } else if (commonState.activity_name.equals("Application_Progress_Activity")) {
          intent.setClass(this,Application_Progress_Activity.class);
      } else if (commonState.activity_name.equals("AutomaticApp_Activity")) {
          intent.setClass(this,AutomaticApp_Activity.class);
      } else if (commonState.activity_name.equals("Begin_Countdown_Activity")) {
          intent.setClass(this,Begin_Countdown_Activity.class);
      } else if (commonState.activity_name.equals("BootUpReceiver")) {
          intent.setClass(this,BootUpReceiver.class);
      } else if (commonState.activity_name.equals("Delete_Reports_Activity")) {
          intent.setClass(this,Delete_Reports_Activity.class);
      } else if (commonState.activity_name.equals("DownloadingReports_Activity")) {
          intent.setClass(this,DownloadingReports_Activity.class);
      } else if (commonState.activity_name.equals("DownloadReports_Activity")) {
          intent.setClass(this,DownloadReports_Activity.class);
      } else if (commonState.activity_name.equals("Enter_Location_Activity")) {
          intent.setClass(this,Enter_Location_Activity.class);
      } else if (commonState.activity_name.equals("Home_Screen_Activity")) {
          intent.setClass(this,Home_Screen_Activity.class);
      } else if (commonState.activity_name.equals("Liquid_Conversion_Activity")) {
          intent.setClass(this,Liquid_Conversion_Activity.class);
      } else if (commonState.activity_name.equals("ManualApp_Activity")) {
          intent.setClass(this,ManualApp_Activity.class);
      } else if (commonState.activity_name.equals("MGT_ControlCenter_Activity")) {
          intent.setClass(this,MGT_ControlCenter_Activity.class);
      } else if (commonState.activity_name.equals("New_Application_Activity")) {
          intent.setClass(this,New_Application_Activity.class);
      } else if (commonState.activity_name.equals("Zimek")) {
          intent.setClass(this,Zimek.class);
      } else if (commonState.activity_name.equals("Other_Fuctions_Activity")) {
          intent.setClass(this,Other_Fuctions_Activity.class);
      } else if (commonState.activity_name.equals("PassCode_Activity")) {
          intent.setClass(this,PassCode_Activity.class);
      } else if (commonState.activity_name.equals("Passcode_Edit_Activity")) {
          intent.setClass(this,Passcode_Edit_Activity.class);
      } else if (commonState.activity_name.equals("PhantomActivity")) {
          intent.setClass(this,PhantomActivity.class);
      } else if (commonState.activity_name.equals("PreHeatActivated_Activity")) {
          intent.setClass(this,PreHeatActivated_Activity.class);
      } else if (commonState.activity_name.equals("PreHeatLiquid_Activity")) {
          intent.setClass(this,PreHeatLiquid_Activity.class);
      } else if (commonState.activity_name.equals("RepeatApp_ChangeTime_Activity")) {
          intent.setClass(this,RepeatApp_ChangeTime_Activity.class);
      } else if (commonState.activity_name.equals("Repeat_Application_Activity")) {
          intent.setClass(this,Repeat_Application_Activity.class);
      } else if (commonState.activity_name.equals("Repeat_Last_Activity")) {
          intent.setClass(this,Repeat_Last_Activity.class);
      } else if (commonState.activity_name.equals("Reports_Activity")) {
          intent.setClass(this,Reports_Activity.class);
      } else if (commonState.activity_name.equals("Reports_Deleted_Activity")) {
          intent.setClass(this,Reports_Deleted_Activity.class);
      } else if (commonState.activity_name.equals("Reports_Menu_Activity")) {
          intent.setClass(this,Reports_Menu_Activity.class);
      } else if (commonState.activity_name.equals("Splash_Screen_Activity")) {
          intent.setClass(this,Splash_Screen_Activity.class);
      } else if (commonState.activity_name.equals("Stop_Download_Activity")) {
          intent.setClass(this,Stop_Download_Activity.class);
      } else if (commonState.activity_name.equals("StopPreHeat_Activity")) {
          intent.setClass(this,StopPreHeat_Activity.class);
      } else if (commonState.activity_name.equals("SystemInformation_Activity")) {
          intent.setClass(this,SystemInformation_Activity.class);
      } else if (commonState.activity_name.equals("SystemSettings_Activity")) {
          intent.setClass(this,SystemSettings_Activity.class);
      } else if (commonState.activity_name.equals("TableOfContents_Activity")) {
          intent.setClass(this,TableOfContents_Activity.class);
      } else if (commonState.activity_name.equals("TimeAndDate_Activity")) {
          intent.setClass(this,TimeAndDate_Activity.class);
      } else if (commonState.activity_name.equals("Total_RunTime_Activity")) {
          intent.setClass(this,Total_RunTime_Activity.class);
      } else if (commonState.activity_name.equals("View_Reports_Activity")) {
          intent.setClass(this,View_Reports_Activity.class);
      } else if (commonState.activity_name.equals("ZimekActivity")) {
          intent.setClass(this,ZimekActivity.class);
      } else {
          intent.setClass(this,Home_Screen_Activity.class);
      }	  
       
       SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
       language = preferences.getString("language","");
       
       if (! (language.equals("English") || language.equals("Spanish"))) {
           commonState.language = "English";
       } else {
    	   commonState.language = language;
       }
       //intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP); 
       //intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
       startActivity(intent);
   }
   
    // Load default baud rate
    int loadDefaultBaudrate() {
        return FTDriver.BAUD9600;
    }
    
    void loadDefaultSettingValues() {
        mReadLinefeedCode = LINEFEED_CODE_CRLF;
        mWriteLinefeedCode = LINEFEED_CODE_CRLF;
        
        mDataBits = FTDriver.FTDI_SET_DATA_BITS_8;
        mSerial.setSerialPropertyDataBit(mDataBits, FTDriver.CH_A);

        mParity = FTDriver.FTDI_SET_DATA_PARITY_NONE;
        mSerial.setSerialPropertyParity(mParity, FTDriver.CH_A);

        mStopBits = FTDriver.FTDI_SET_DATA_STOP_BITS_1;
        mSerial.setSerialPropertyStopBits(mStopBits, FTDriver.CH_A);

        mFlowControl = FTDriver.FTDI_SET_FLOW_CTRL_NONE;
        mSerial.setFlowControl(FTDriver.CH_A, mFlowControl);

        mBreak = FTDriver.FTDI_SET_NOBREAK;
        mSerial.setSerialPropertyBreak(mBreak, FTDriver.CH_A);

        mSerial.setSerialPropertyToChip(FTDriver.CH_A);
    }

   
   BroadcastReceiver mUsbReceiver = new BroadcastReceiver() {
       public void onReceive(Context context, Intent intent) {
           String action = intent.getAction();

           if (UsbManager.ACTION_USB_DEVICE_ATTACHED.equals(action)) {
               if (SHOW_DEBUG) {
                   Log.v(TAG, "Device attached");
               }
               if (true) {
            	   if (!mSerial.isConnected()) {
            		   if (SHOW_DEBUG) {
            			   Log.d(TAG, "Device attached begin");
            		   }
            		   mBaudrate = loadDefaultBaudrate();
            		   mSerial.begin(mBaudrate);
            		   loadDefaultSettingValues();
            	   }
               }
           } else if (UsbManager.ACTION_USB_DEVICE_DETACHED.equals(action)) {
               if (SHOW_DEBUG) {
                   Log.d(TAG, "Device detached");
               }
               mSerial.usbDetached(intent);
               mSerial.end();
           } else if (ACTION_USB_PERMISSION.equals(action)) {
               if (SHOW_DEBUG) {
                   Log.d(TAG, "Request permission");
               }
               synchronized (this) {
                   if (!mSerial.isConnected()) {
                       if (SHOW_DEBUG) {
                           Log.d(TAG, "Request permission begin");
                       }
                       mBaudrate = loadDefaultBaudrate();
                       mSerial.begin(mBaudrate);
                       loadDefaultSettingValues();
                   }
               }
           }
       }
   };
}


   
   

   
         
   
