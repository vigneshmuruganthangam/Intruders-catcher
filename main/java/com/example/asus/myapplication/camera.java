package com.example.asus.myapplication;

import android.app.Service;
import android.content.Intent;
import android.hardware.Camera;
import android.hardware.Camera.Parameters;
import android.os.IBinder;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
public class camera extends Service {
    static int x=0;
    private SurfaceHolder sHolder;
    private Camera mCamera;
    private Parameters parameters;
    @Override
    public void onCreate()
    {
        super.onCreate();

    }
    public void onStart(Intent intent,int startid){
        // TODO Auto-generated method stub
        mCamera = Camera.open(1);
        SurfaceView sv = new SurfaceView(getApplicationContext());


        try {
            mCamera.setPreviewDisplay(sv.getHolder());
            parameters = mCamera.getParameters();
            mCamera.setParameters(parameters);
            mCamera.startPreview();
            mCamera.takePicture(null, null, mCall);

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        sHolder = sv.getHolder();
        sHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
    }



    Camera.PictureCallback mCall;

    {
        mCall = new Camera.PictureCallback() {

            public void onPictureTaken(byte[] data, Camera camera) {
                FileOutputStream outStream = null;
                try {
                    x++;
                    outStream = new FileOutputStream("/sdcard/Image" + x + ".jpg");
                    outStream.write(data);
                    outStream.close();
                    mCamera.release();
                } catch (FileNotFoundException e) {
                    Log.d("CAMERA", e.getMessage());
                } catch (IOException e) {
                    Log.d("CAMERA", e.getMessage());
                }
            }
        };
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
