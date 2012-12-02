package com.circuit.zimek;

import java.io.File;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.os.Bundle;
import android.os.Environment;
import android.view.Gravity;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Toast;



public class Splash_Screen_Activity extends Activity  implements  View.OnLongClickListener, OnCompletionListener, SurfaceHolder.Callback {
    
	private SurfaceView mPreview;
    private SurfaceHolder holder;
    private MediaPlayer mMediaPlayer;
    private int mVidIndex=0;
    private int mNumVideos=0;
    private String [] mVideos = new String[100]; // Allocate max of 100 videos , quite arbitrary.
    

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_screen);

        mPreview = (SurfaceView) findViewById(R.id.surface);
        mPreview.setOnLongClickListener(this);
        holder = mPreview.getHolder();
        holder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
        holder.addCallback(this);
             
        OnClickListener exitListener = new OnClickListener(){
            @Override
            public void onClick(View v) {
            	try {
            		mMediaPlayer.reset();
        		} catch (Exception e){
        			e.printStackTrace();
        		}
            	mVidIndex = 1/0;
            	//super.finish();
            }
        };
        
            
        File dir;
        String filePath=null;
        
        dir = new File("/mnt/sdcard2/Movies");
        String [] chld = dir.list();
    	
        if (chld==null) {
        // The following call always returns internal sd card. 
        // File directory = Environment.getExternalStorageDirectory(); 
        	dir = new File(Environment.getExternalStorageDirectory() + "/LightsNVideo");
        	chld = dir.list();
        }
        
    
    	//String [] chld = dir.list();
    	if (chld==null) {
    		Context mContext = getApplicationContext();
    		Toast mToast = Toast.makeText(mContext, "No video files to play", Toast.LENGTH_LONG);
    		mToast.setGravity(Gravity.CENTER, 0, 0);
    		mToast.show();
    		Intent intent = new Intent(this, Home_Screen_Activity.class);
        	startActivity(intent);
    	} else {
    		for (int i=0; i< chld.length; i++) {
    			if (isVideoFile(chld[i])) {
    				filePath = dir.toString() + "/" + chld[i];
    				mVideos[mVidIndex] = filePath;
    				mVidIndex += 1;
    				mNumVideos += 1;
    				if (mNumVideos>=100) {
    					break;
    				}
    			}
    		}
    	}
    	mVidIndex = 0;
    	if (mNumVideos != 0) {
    		filePath = mVideos[mVidIndex];
    		try {
    			mMediaPlayer = new MediaPlayer();
    		    mMediaPlayer.setDataSource(filePath);
    		    mMediaPlayer.setDisplay(holder);
    		    holder.setKeepScreenOn(true);
    		} catch (Exception e){
    			e.printStackTrace();
    		}
            mMediaPlayer.setOnCompletionListener(this);
    	}
    }
    

    public void onCompletion(MediaPlayer mp) {
    	mMediaPlayer.stop();
    	Intent intent = new Intent(this, Home_Screen_Activity.class);
    	startActivity(intent);
    }
    
    public boolean onLongClick(View view) {
    	mMediaPlayer.stop();
    	Intent intent = new Intent(this, Home_Screen_Activity.class);
    	startActivity(intent);
    	return true;
    }
    

    public void surfaceChanged(SurfaceHolder surfaceholder, int i, int j, int k) {
    }
    public void surfaceDestroyed(SurfaceHolder surfaceholder) {
    }
    public void surfaceCreated(SurfaceHolder holder) {
    	try {
    		mMediaPlayer.prepare();
    		mMediaPlayer.start();
    	}
    	catch (Exception e){
    		e.printStackTrace();
    	}
    }
    
    private boolean isVideoFile(String filename) {
    	String[] temp;
    	temp = filename.split("\\.");
    	int len = temp.length;
    	if ("mp4".equals(temp[len-1]) || "avi".equals(temp[len-1]) || "3gp".equals(temp[len-1])) {
    		return true;
    	}
    	else {
    		return false;
    	}
    }
}
