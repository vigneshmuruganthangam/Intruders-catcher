package com.example.asus.myapplication;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.IBinder;
import android.widget.Toast;

import static com.example.asus.myapplication.MainActivity.emaili;
import static com.example.asus.myapplication.MainActivity.mydata;

public class email extends Service {
    static int y=0;
    public void onStart(Intent intent,int startid) {
        SharedPreferences sh = getSharedPreferences(mydata, MODE_PRIVATE);
        String em = sh.getString(emaili,"");
        String messa = "we detect someone who theft your mobile";
        String subject = "intruders";
        y++;
        String filename=("/sdcard/image"+y+".jpg");
        SendMail sm = new SendMail(this, em, subject, messa,filename);
        sm.execute();
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
