package com.example.asus.myapplication;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.icu.util.Calendar;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.telephony.SmsManager;
import android.telephony.SmsMessage;
import android.util.Log;
import android.widget.Toast;

import static com.example.asus.myapplication.MainActivity.mse;
import static com.example.asus.myapplication.MainActivity.mydata;
import static com.example.asus.myapplication.MainActivity.phon;

/**
 * Created by ASUS on 06-06-2018.
 */
public class sms extends BroadcastReceiver {
    final SmsManager sms =SmsManager.getDefault();
    @Override
    public void onReceive(Context context, Intent intent) {
        SharedPreferences share=context.getSharedPreferences(mydata,Context.MODE_PRIVATE);
        final Bundle bundle=intent.getExtras();
        try{
            if(bundle !=null){
                final Object[] pdusObj=(Object[])bundle.get("pdus");
                for(int i=0;i<pdusObj.length;i++){
                    SmsMessage currrentMessage= SmsMessage.createFromPdu((byte[]) pdusObj[i]);
                    String sendno=currrentMessage.getDisplayOriginatingAddress();
                    String mg=currrentMessage.getDisplayMessageBody();
                    String n=share.getString(phon,"");
                    String m=share.getString(mse,"");
                    if(sendno.equals(n)&mg.equals(m)) {
                        Intent i1=new Intent(context,camera.class);
                        PendingIntent pendingIntent=PendingIntent.getService(context,0,i1,0);
                        AlarmManager alarmManager=(AlarmManager)context.getSystemService(Context.ALARM_SERVICE);
                        java.util.Calendar calendar= java.util.Calendar.getInstance();
                        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP,calendar.getTimeInMillis(),5*1000,pendingIntent);
                        Intent i12=new Intent(context,email.class);
                        PendingIntent pendingInten=PendingIntent.getService(context,0,i12,0);
                        AlarmManager alarmManage=(AlarmManager)context.getSystemService(Context.ALARM_SERVICE);
                        java.util.Calendar calenda= java.util.Calendar.getInstance();
                        alarmManage.setRepeating(AlarmManager.RTC_WAKEUP,calenda.getTimeInMillis(),5*1000,pendingInten);

                    }
                }
            }
        }catch (Exception e){
            Log.i("Smsreceiver","Exception smsReceiver"+e);
        }
    }
}