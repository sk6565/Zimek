package com.circuit.zimek;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;


public class BootUpReceiver extends BroadcastReceiver{


 @Override
 public void onReceive(Context context, Intent intent) {
     Intent i = new Intent(context, PhantomActivity.class); 
            i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(i); 
  
 }
}
