package com.estibyan_Freestand;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;


public class ServiceToChekApp extends Service {
	 @Override
	 public void onCreate() {
	  super.onCreate();
	  final Context context = this;
	 

	  final Handler h = new Handler();
	    h.postDelayed(new Runnable()
	    {
	        @Override
	        public void run()
	        {
	      	  
	      	  if (commonFun.CommonFunction.isAppRunning(context, "com.Pakage_Name_Here")) {	      		  
	      		   // Toast.makeText(context, "App is  running", 100).show();    
	      		    
	      		} else {	      			
	      		    // App is not running
	      			//Toast.makeText(context, "App is not running", 100).show();	
	      			Intent intent = new Intent(context,MainActivity.class);
	      			intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
	      			startActivity(intent);
	      		}
	      	
	      	//Toast.makeText(context, "HeapMemmory: "+CommonFunction.logHeapMem(), 100).show();
	      	Double Available_Mem = commonFun.CommonFunction.logHeapMem();

			System.out.println("memmory Reach now : " + Available_Mem);

			if (Available_Mem >= 50) {

				System.out.println("Memmory Reach 50 ; so  clearing cash memmory");
				Intent mStartActivity = new Intent(context,
						MainActivity.class);
				int mPendingIntentId = 123456;
				PendingIntent mPendingIntent = PendingIntent.getActivity(
						context, mPendingIntentId, mStartActivity,
						PendingIntent.FLAG_CANCEL_CURRENT);
				AlarmManager mgr = (AlarmManager) context
						.getSystemService(context.ALARM_SERVICE);
				mgr.set(AlarmManager.RTC, System.currentTimeMillis() + 100,
						mPendingIntent);
				System.exit(0);

			}
	      	
	      	
	      	  
	            h.postDelayed(this, 1000);
	        }
	    }, 1000); // 1 second delay (takes millis)
	  

	 }
	 
	 @Override
	 public void onDestroy() {
	  super.onDestroy();
	 }
	 

	 
	 @Override
	 public int onStartCommand(Intent intent, int flags, int startId) {
	  return super.onStartCommand(intent, flags, startId);
	 }
	 
	
	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	
}
