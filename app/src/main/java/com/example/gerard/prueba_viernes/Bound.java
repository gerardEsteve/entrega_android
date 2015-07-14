package com.example.gerard.prueba_viernes;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Binder;
import android.os.Environment;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;

public class Bound extends Service implements MediaPlayer.OnCompletionListener{

   public MediaPlayer mediaPlayer;
    Uri myUri;

    @Override
    public void onCreate() {
        super.onCreate();

       }

    String path;
    public Bound(){}
     Bound(Context context) {
         myUri = Uri.parse("android.resource://"+context.getPackageName()+"/" + R.raw.end);
        mediaPlayer = new MediaPlayer();

        try {
            mediaPlayer.setDataSource(context,myUri);
            mediaPlayer.prepare();
            mediaPlayer.setOnCompletionListener(this);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }



    @Override
    public void onCompletion(MediaPlayer mp) {


    }
    public void empieza(){

            mediaPlayer.start();
    }

    public void pausa(){
        mediaPlayer.pause();
    }
    public void stop(Context context) {
        mediaPlayer.stop();
        mediaPlayer.reset();
        try{
            mediaPlayer.setDataSource(context,myUri);
            mediaPlayer.prepare();
            mediaPlayer.setOnCompletionListener(this);
        }
        catch (IOException e){
            e.printStackTrace();

        }
    }

        private final IBinder binder = new MyBinder();

        @Override
        public IBinder onBind(Intent intent) {
            return binder;
        }

        public class MyBinder extends Binder {
            Bound getService() {
                return Bound.this;
            }
        }
}
