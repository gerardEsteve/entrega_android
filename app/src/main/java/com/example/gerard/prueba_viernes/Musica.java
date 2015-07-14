package com.example.gerard.prueba_viernes;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;


public class Musica extends ActionBarActivity implements View.OnClickListener,MediaPlayer.OnCompletionListener{

    private ServiceConnection connection;
    Bound bService;
    boolean bound ;
    Bound player;
    ImageButton start_b,stop_b;
    Boolean reproduint;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        player = new Bound();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_musica);
        bound = false;
        reproduint = false;
        connection = new ServiceConnection(){
            @Override
            public void onServiceConnected(ComponentName className, IBinder binder){
                Bound.MyBinder binderx = (Bound.MyBinder) binder;
                bService = binderx.getService();
                bound = true;
            }

            @Override
            public void onServiceDisconnected(ComponentName arg0){
                bound = false;
            }
        };
        Intent inter = new Intent(Musica.this,Bound.class);
        bindService(inter,connection,Context.BIND_AUTO_CREATE);
        
        start_b = (ImageButton)findViewById(R.id.btn_Play);
        stop_b = (ImageButton)findViewById(R.id.btn_back);
        start_b.setOnClickListener(this);
        stop_b.setOnClickListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_musica, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){

            case R.id.logout:
                SharedPreferences SP = getSharedPreferences("Savestate_login", 0);
                //Obtenemos el editor
                SharedPreferences.Editor editor = SP.edit();
                //Editamos
                editor.putBoolean("logout", true);
                //Guardamos los cambios
                editor.apply();
                // anar a la pantalla de login

                Intent intent = new Intent(getApplicationContext(), login.class);
                startActivity(intent);
                break;
        }


        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_Play:
                if(reproduint){
                    player.pausa();
                    reproduint = false;
                    start_b.setImageDrawable(getResources().getDrawable(R.drawable.ic_media_play));
                }
                else{
                    player.empieza();
                    reproduint = true;
                    start_b.setImageDrawable(getResources().getDrawable(R.drawable.ic_media_pause));
                }
                break;
            case R.id.btn_back:
                player.stop();
                reproduint = false;
                start_b.setImageDrawable(getResources().getDrawable(R.drawable.ic_media_play));
                break;
            default:
                break;
        }

    }

    @Override
    public void onCompletion(MediaPlayer mp) {

    }
    }

