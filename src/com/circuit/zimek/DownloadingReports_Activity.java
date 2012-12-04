package com.circuit.zimek;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.channels.FileChannel;
import java.util.List;

import com.TSS_Report.DatabaseHandler;
import com.TSS_Report.TreatmentReports;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.NumberPicker.Formatter;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import android.database.sqlite.SQLiteOpenHelper;


import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Font;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;



public class DownloadingReports_Activity extends Activity implements OnClickListener{
	
private ImageButton imBtnNext;
private ImageButton imBtnWarning;
private ImageButton imBtnStop;

private CountDownTimer Countdown;
public int downloadprogress = 1;

private ImageView imViewDownloadReportsHead;
private ImageView imViewDownloadingIcon;
private ImageView imViewDownloadingDesc;

private TextView txtViewDownload;
private ProgressBar pBarDownload;
public long TotalDownloadTime = 15000;
public float TotalDownload = 15;
public float downloaded = 0;

public TextView txtViewPause;

public String language;
public CommonState commonState = null;


public int IdCount = 0;
public String TotalRuntime;
public String AppResult;
public String myDate;
public String myTime;
public String LocationId;
public int TotalReports;

	
	


@Override
public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    requestWindowFeature(Window.FEATURE_NO_TITLE);
    getWindow().getDecorView().setSystemUiVisibility(View.STATUS_BAR_HIDDEN);
    getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, 
            WindowManager.LayoutParams.FLAG_FULLSCREEN);
    setContentView(R.layout.downloadingreports_app);
    
    commonState = (CommonState) getApplication();
    commonState.activity_name = "DownloadingReports_Activity";

    imViewDownloadingIcon = (ImageView)findViewById(R.id.imViewDownloadingIcon);
    txtViewDownload = (TextView)findViewById(R.id.txtViewDownload);
    txtViewDownload.setText("0%");
    
    txtViewPause = (TextView) this.findViewById(R.id.txtViewPause);
    txtViewPause.setText("PAUSE");
    
    pBarDownload = (ProgressBar) findViewById(R.id.pBarDownload);
	  pBarDownload.setProgress(0);
	  pBarDownload.setMax(15);
    
    
    
    
    language = commonState.language;
    
    if(language.equals("English")){
    	
    	imBtnNext = (ImageButton)findViewById(R.id.imBtnNext);
        
        imBtnWarning = (ImageButton)findViewById(R.id.imBtnWarning);   
        
        imBtnStop = (ImageButton)findViewById(R.id.imBtnStop); 
        
        imViewDownloadReportsHead = (ImageView)findViewById(R.id.imViewDownloadReportsHead);
        
        imViewDownloadingDesc = (ImageView)findViewById(R.id.imViewDownloadingDesc);
  	     
 	   
    } 
    
    if(language.equals("Spanish")){
		   
        imBtnNext = (ImageButton)findViewById(R.id.imBtnNext);
        imBtnNext.setImageResource(R.drawable.next_spa);
        
        imBtnWarning = (ImageButton)findViewById(R.id.imBtnWarning);
        imBtnWarning.setImageResource(R.drawable.warning_spa);
        
        imViewDownloadReportsHead = (ImageView)findViewById(R.id.imViewDownloadReportsHead);
        imViewDownloadReportsHead.setImageResource(R.drawable.download_reports_head_spa);
        
        imBtnStop = (ImageButton)findViewById(R.id.imBtnStop);
        imBtnStop.setImageResource(R.drawable.stop_download_spa);
  	     
        imViewDownloadingDesc = (ImageView)findViewById(R.id.imViewDownloadingDesc);
        imViewDownloadingDesc.setImageResource(R.drawable.downloading_reports_spa);

	   
		   
	   }
    
    imBtnWarning.setVisibility(View.INVISIBLE);
    imBtnNext.setVisibility(View.INVISIBLE);
    
    imBtnNext.setOnClickListener(this);
    imBtnWarning.setOnClickListener(this);
    imBtnStop.setOnClickListener(this);
    
    //get path for external storage
    String baseDir = Environment.getExternalStorageDirectory().getAbsolutePath();
    //save database to external storage
    backupDB(baseDir);
    
    //save database to usb drive
    //since i can't find a way to access it automatically, i just typed out the path
    //it might change depending the the tablet being used
    backupDB("/mnt/usb0/part0");
    
    backupDBPDF(baseDir);
    backupDBPDF("/mnt/usb0/part0");
    
    
    
    CountDown();

    
}



public void CountDown(){
	
	
	 Countdown = new CountDownTimer(TotalDownloadTime, 1000) {

           @Override

           public void onTick(long leftTimeInMilliseconds) {
         	  TotalDownloadTime = leftTimeInMilliseconds;
         	  				 	 
				//txtViewPreHeatRemaining.setText(formatTime(leftTimeInMilliseconds));
				downloadprogress = downloadprogress + 1;
				pBarDownload.setProgress(downloadprogress);
				
				downloaded = downloaded + 1;
				
				float percent = (100 * downloaded) / TotalDownload;
				
				txtViewDownload.setText(String.format("%.0f%%",percent));

		 }
			public void onFinish() {
				
				 	Animation animation = new AlphaAnimation(1, 0); 
				    animation.setDuration(800); 
				    animation.setInterpolator(new LinearInterpolator()); 
				    animation.setRepeatCount(Animation.INFINITE); 
				    animation.setRepeatMode(Animation.REVERSE); 
				    imBtnNext.startAnimation(animation);
			
				pBarDownload.setProgress(15);
				txtViewDownload.setText("100%");
				imBtnStop.setVisibility(View.INVISIBLE);
				imBtnNext.setVisibility(View.VISIBLE);
				if (language.equals("Spanish")) {
					imViewDownloadingDesc.setImageResource(R.drawable.reports_downloaded_spa);	
				} else {
					imViewDownloadingDesc.setImageResource(R.drawable.reports_downloaded_eng);	
				}
					        
		}
		}; Countdown.start();	
			
			
			imBtnStop.setOnClickListener(new OnClickListener() {
				
				
			    public void onClick(View v) {
			        
			     Countdown.cancel();

			     
			     	Intent resultIntent = new Intent();
		  			resultIntent.setClass(DownloadingReports_Activity.this,Stop_Download_Activity.class);
		  			resultIntent.putExtra("language", language);
		  			resultIntent.putExtra("TotalDownloadTime", TotalDownloadTime);
		  			resultIntent.putExtra("downloadprogress", downloadprogress);
		  			resultIntent.putExtra("downloaded", downloaded);
		  			startActivityForResult(resultIntent, 1);
									     
			          
			       }
			    
			   });

		}



@Override
public void onActivityResult(int requestCode, int resultCode, Intent data) {
	   super.onActivityResult(requestCode, resultCode, data);
	   
	   //language = getIntent().getStringExtra("language");
	   
	  // Toast.makeText(Home_Screen_Activity.this, "Result OK: Home Screen DATA" + language, Toast.LENGTH_LONG).show();
	   
	   switch(requestCode) {
	     case 1: 
	    	 
      if (resultCode == RESULT_OK) {
     	TotalDownloadTime = data.getLongExtra("TotalDownloadTime", TotalDownloadTime);
     	downloadprogress = data.getIntExtra("downloadprogress", downloadprogress);
     	downloaded = data.getFloatExtra("downloaded", downloaded);
     	//txtViewDownload.setText();

     	 CountDown();
     	
     	 //Toast.makeText(Home_Screen_Activity.this, "Result OK: Home Screen" + data, Toast.LENGTH_LONG).show();
     	 
     	 
		      
		       
		       break;
      }
	   }
 }






public void onClick(View v) {
	
	if(v==imBtnNext){
		
		Intent resultIntent = new Intent();
		resultIntent.setClass(this,MGT_ControlCenter_Activity.class);
		resultIntent.putExtra("language", language);
		startActivity(resultIntent);
		
	}
	
	
}

//copy db to SD card
public void backupDB (String filePath) {
	
    DatabaseHandler db = new DatabaseHandler(this);
    
    TotalReports = db.getReportsCount();
	
	try{
		//create folder to store the text file
        File root = new File(filePath, "Database");
        if (!root.exists()) {
            root.mkdirs();
        }

        File dbFile = new File(root, "TSSreports_Backup.txt");
        PrintWriter pw = new PrintWriter(new FileWriter(dbFile), true);
        
        pw.println("TSS Reports");
        pw.format("%-3s%-10s%-9s%-9s%-10s%-11s%-10s%-11s%-12s%-11s%-17s\n", "ID", "Location", "Date", "Time", "Mist Time", "Dwell Time", "ZVAC Time","Total Time", "ZVAC Serial", "App Result", "Type Application", "Type Configure" );
		
        List<TreatmentReports> reportsList = db.getAllReports();     
        
        for (TreatmentReports cn : reportsList) {
        pw.format("%-3d%-10s%-9s%-9s%-10s%-11s%-10s%-11s%-12s%-11s%-17s\n", cn.getID(), cn.getLocation(), cn.getmyDate() ,cn.getmyTime() , cn.getMistTime(),
            		cn.getDwellTime() , cn.getZvacTime(),cn.getTotalTime() , cn.getZvacSerial() , 
            		cn.getAppResult() , cn.gettypeApplication() ,cn.gettypeConfigure() );
        
        }   
        pw.close();
        db.close();

        Toast.makeText(this, "Saved", Toast.LENGTH_SHORT).show();
    }
    catch(IOException e){
    	
    }
	

    }
	
public void backupDBPDF (String filePath) {
		
	DatabaseHandler db = new DatabaseHandler(this);   

	Document document = new Document();
	
    try {
    	
            PdfWriter.getInstance(document, new FileOutputStream(filePath + java.io.File.separator + "Database" + java.io.File.separator + "report.pdf"));
    
            document.open();

            PdfPTable table = new PdfPTable(11); // 11 columns.
            
            //table.setWidths(new float[]{3, 10, 9, 9, 10, 11, 10, 11, 12, 11});
            table.setWidthPercentage(95);
            
            Font smallfont = new Font(Font.TIMES_ROMAN,  9, Font.NORMAL);
            
            PdfPCell cellTitle = new PdfPCell(new Phrase("TSS Report", smallfont));
            cellTitle.setColspan(11);
            cellTitle.setBorder(Rectangle.NO_BORDER);
            table.addCell(cellTitle);
 
 /*
            table.addCell("ID");
            table.addCell("Location");
            table.addCell("Date");
            table.addCell("Time");
            table.addCell("Mist Time");
            table.addCell("Dwell Time");
            table.addCell("ZVAC Time");
            table.addCell("Total Time");
            table.addCell("ZVAC Serial");
            table.addCell("App Result");
            table.addCell("Type App");
   */         
            PdfPCell cell1 = new PdfPCell(new Phrase("ID", smallfont));
            cell1.setBorder(Rectangle.NO_BORDER);
            table.addCell(cell1);
            
            PdfPCell cell2 = new PdfPCell(new Phrase("Location", smallfont));
            cell2.setBorder(Rectangle.NO_BORDER);
            table.addCell(cell2);
            
            PdfPCell cell3 = new PdfPCell(new Phrase("Date", smallfont));
            cell3.setBorder(Rectangle.NO_BORDER);
            table.addCell(cell3);
            
            PdfPCell cell4 = new PdfPCell(new Phrase("Time", smallfont));
            cell4.setBorder(Rectangle.NO_BORDER);
            table.addCell(cell4);
            
            PdfPCell cell5 = new PdfPCell(new Phrase("Mist Time", smallfont));
            cell5.setBorder(Rectangle.NO_BORDER);
            table.addCell(cell5);
            
            PdfPCell cell6 = new PdfPCell(new Phrase("Dwell Time", smallfont));
            cell6.setBorder(Rectangle.NO_BORDER);
            table.addCell(cell6);
            
            PdfPCell cell7 = new PdfPCell(new Phrase("ZVAC Time", smallfont));
            cell7.setBorder(Rectangle.NO_BORDER);
            table.addCell(cell7);
            
            PdfPCell cell8 = new PdfPCell(new Phrase("Total Time", smallfont));
            cell8.setBorder(Rectangle.NO_BORDER);
            table.addCell(cell8);
            
            PdfPCell cell9 = new PdfPCell(new Phrase("ZVAC serial", smallfont));
            cell9.setBorder(Rectangle.NO_BORDER);
            table.addCell(cell9);
            
                    
            PdfPCell cell10 = new PdfPCell(new Phrase("App Result", smallfont));
            cell10.setBorder(Rectangle.NO_BORDER);
            table.addCell(cell10);
            
            PdfPCell cell11 = new PdfPCell(new Phrase("Type App", smallfont));
            cell11.setBorder(Rectangle.NO_BORDER);
            table.addCell(cell11);

        
            
            List<TreatmentReports> reportsList = db.getAllReports();     
            
            for (TreatmentReports cn : reportsList) {

                PdfPCell cellID = new PdfPCell(new Phrase(Integer.toString(cn.getID()), smallfont));
                cellID.setBorder(Rectangle.NO_BORDER);
                
                PdfPCell cellLOC = new PdfPCell(new Phrase(cn.getLocation(), smallfont));
                cellLOC.setBorder(Rectangle.NO_BORDER);
                
                PdfPCell cellDA = new PdfPCell(new Phrase(cn.getmyDate(), smallfont));
                cellDA.setBorder(Rectangle.NO_BORDER);
                
                PdfPCell cellTI = new PdfPCell(new Phrase(cn.getmyTime(), smallfont));
                cellTI.setBorder(Rectangle.NO_BORDER);
                
                PdfPCell cellMT = new PdfPCell(new Phrase(cn.getMistTime(), smallfont));
                cellMT.setBorder(Rectangle.NO_BORDER);
                
                PdfPCell cellDT = new PdfPCell(new Phrase(cn.getDwellTime(), smallfont));
                cellDT.setBorder(Rectangle.NO_BORDER);
                
                PdfPCell cellZT = new PdfPCell(new Phrase(cn.getZvacTime(), smallfont));
                cellZT.setBorder(Rectangle.NO_BORDER);
                
                PdfPCell cellTT = new PdfPCell(new Phrase(cn.getTotalTime(), smallfont));
                cellTT.setBorder(Rectangle.NO_BORDER);
                
                PdfPCell cellZS = new PdfPCell(new Phrase(cn.getZvacSerial(),smallfont));
                cellZS.setBorder(Rectangle.NO_BORDER);
                
                PdfPCell cellAR = new PdfPCell(new Phrase(cn.getAppResult(), smallfont));
                cellAR.setBorder(Rectangle.NO_BORDER);
                
                PdfPCell cellTA = new PdfPCell(new Phrase(cn.gettypeApplication(), smallfont));
                cellTA.setBorder(Rectangle.NO_BORDER);
                
                table.addCell(cellID);
                table.addCell(cellLOC);
                table.addCell(cellDA);
                table.addCell(cellTI);
                table.addCell(cellMT);
                table.addCell(cellDT);
                table.addCell(cellZT);
                table.addCell(cellTT);
                table.addCell(cellZS);
                table.addCell(cellAR);
                table.addCell(cellTA);

                
                

            } 

            document.add(table);
           // document.add(new Paragraph("testestestestestestestes"));

    } 
    catch (DocumentException de) {
        System.err.println(de.getMessage());
    } 
    catch (IOException ioe) {
        System.err.println(ioe.getMessage());
    }
    document.close();
}
}
	

