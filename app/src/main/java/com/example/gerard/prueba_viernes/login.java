package com.example.gerard.prueba_viernes;

import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.CookieSyncManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.twitter.sdk.android.Twitter;
import com.twitter.sdk.android.core.TwitterAuthConfig;
import io.fabric.sdk.android.Fabric;



import com.twitter.sdk.android.core.Callback;
import com.twitter.sdk.android.core.Result;
import com.twitter.sdk.android.core.TwitterException;
import com.twitter.sdk.android.core.TwitterSession;
import com.twitter.sdk.android.core.identity.TwitterLoginButton;

import java.net.CookieManager;


public class login extends ActionBarActivity implements View.OnClickListener,DialogFragment.OnCompleteListener,DialogFragment2.OnCompleteListener {

    // Note: Your consumer key and secret should be obfuscated in your source code before shipping.
    private static final String TWITTER_KEY = "vmmmcf5OgAN3l5gseP8C6qOnP";
    private static final String TWITTER_SECRET = "9nRRsiKQzuvMTs9FfUE2KNwG2nCimvXQn0TcQbHZU0yVD8Y0vl";


    TwitterLoginButton loginButton;


    Button register;

    Button log;
    clase_bd ioh = new clase_bd(this);
    SQLiteDatabase db;

    EditText username;
    EditText password;

    Boolean no_sesio_iniciada;

    @Override
        protected void onCreate(Bundle savedInstanceState) {

            super.onCreate(savedInstanceState);
            TwitterAuthConfig authConfig = new TwitterAuthConfig(TWITTER_KEY, TWITTER_SECRET);
            Fabric.with(this, new Twitter(authConfig));
            setContentView(R.layout.activity_login);

            SharedPreferences SP = getSharedPreferences("Savestate_login", Context.MODE_PRIVATE);
            no_sesio_iniciada = SP.getBoolean("logout",true);

            if (no_sesio_iniciada){
                log = (Button)findViewById(R.id.log);
                log.setOnClickListener(this);

                register = (Button)findViewById(R.id.register);
                register.setOnClickListener(this);

                username = (EditText)findViewById(R.id.user);
                password = (EditText)findViewById(R.id.pass);
            }
            else{
                Intent inte = new Intent(this,Selection.class);
                startActivity(inte);
                finish();
            }

        loginButton = (TwitterLoginButton) findViewById(R.id.login_button);
        loginButton.setCallback(new Callback<TwitterSession>() {
            @Override
            public void success(Result<TwitterSession> result) {
                // Do something with result, which provides a TwitterSession for making API calls
                TwitterSession session = Twitter.getSessionManager().getActiveSession();
                String us = session.getUserName();
                String pass = us;

                db = ioh.getWritableDatabase();
                Cursor curs2 = null;
                if(db != null) {
                    String[] s = {};
                    curs2 = db.rawQuery("SELECT username FROM log_in", s);
                }
                Boolean trobat2= false;
                if(curs2.moveToFirst()) {
                    do {
                        if(curs2.getString(0).equals(us)) trobat2 = true;
                    } while(curs2.moveToNext() && !trobat2);
                }
                if(trobat2);
                else {
                        String[] s = {};
                        db.execSQL("INSERT INTO log_in (username,password,imatge,notificacio) VALUES ('" + us + "','" + pass + "','nuller','nuller')", s);

                        FragmentTransaction ft = getFragmentManager().beginTransaction();
                        android.app.DialogFragment dialogFragment = DialogFragment.newInstance("REGISTER OK","CONFIRM");
                        dialogFragment.show(ft, "dialog");

                }
                db.close();

                SharedPreferences SP = getSharedPreferences("Savestate_login", 0);
                //Obtenemos el editor
                SharedPreferences.Editor editor = SP.edit();
                //Editamos
                editor.putBoolean("logout", false);
                editor.putString("usuari",us );
                //Guardamos los cambios
                editor.apply();
                // anar a la pantalla de login
                Intent intent = new Intent(getApplicationContext(), Selection.class);
                startActivity(intent);
                finish();

            }

            @Override
            public void failure(TwitterException exception) {
                // Do something on failure
                Toast.makeText(login.this, "im in da failure", Toast.LENGTH_SHORT).show();
                Log.i("fail","im in da failure");
            }
        });
        }

        @Override
        public boolean onCreateOptionsMenu(Menu menu) {
            // Inflate the menu; this adds items to the action bar if it is present.
            getMenuInflater().inflate(R.menu.menu_login, menu);
            return true;
        }

        @Override
        public boolean onOptionsItemSelected(MenuItem item) {
            // Handle action bar item clicks here. The action bar will
            // automatically handle clicks on the Home/Up button, so long
            // as you specify a parent activity in AndroidManifest.xml.
            int id = item.getItemId();

            //noinspection SimplifiableIfStatement
            if (id == R.id.action_settings) {
                return true;
            }

            return super.onOptionsItemSelected(item);
        }


    @Override
    public void onClick(View v) {

        switch (v.getId()){

            case R.id.log:
                db = ioh.getWritableDatabase();
                Cursor curs = null;
                if(db != null) {
                    String[] s = {};
                    curs = db.rawQuery("SELECT username, password FROM log_in", s);
                }
                Boolean trobat= false;
                if(curs.moveToFirst()) {
                    do {
                        if(curs.getString(0).equals(username.getText().toString()) && curs.getString(1).equals(password.getText().toString())) trobat = true;
                    } while(curs.moveToNext() && !trobat);
                }

                db.close();

                if(trobat) {
                    SharedPreferences SP = getSharedPreferences("Savestate_login", 0);
                    //Obtenemos el editor
                    SharedPreferences.Editor editor = SP.edit();
                    //Editamos
                    editor.putBoolean("logout", false);
                    editor.putString("usuari",username.getText().toString() );
                    //Guardamos los cambios
                    editor.apply();
                    // anar a la pantalla de login
                    Intent intent = new Intent(getApplicationContext(), Selection.class);
                    startActivity(intent);
                    finish();
                }
                else{
                    FragmentTransaction ft = getFragmentManager().beginTransaction();
                    android.app.DialogFragment dialogFragment = DialogFragment2.newInstance("Login incorrecte","Intenta de nou");
                    dialogFragment.show(ft, "dialog");
                }
                break;

            case R.id.register:
                db = ioh.getWritableDatabase();
                Cursor curs2 = null;
                if(db != null) {
                    String[] s = {};
                    curs2 = db.rawQuery("SELECT username FROM log_in", s);
                }
                Boolean trobat2= false;
                if(curs2.moveToFirst()) {
                    do {
                        if(curs2.getString(0).equals(username.getText().toString())) trobat2 = true;
                    } while(curs2.moveToNext() && !trobat2);
                }
                if(trobat2) Toast.makeText(login.this, "Username is being used", Toast.LENGTH_SHORT).show();
                else {
                    if(username.getText().toString().isEmpty()) Toast.makeText(login.this, "Username required", Toast.LENGTH_SHORT).show();
                    else if(password.getText().toString().isEmpty()) Toast.makeText(login.this, "Password required", Toast.LENGTH_SHORT).show();
                    else {
                        String[] s = {};
                        db.execSQL("INSERT INTO log_in (username,password,imatge,notificacio) VALUES ('" + username.getText().toString() + "','" + password.getText().toString() + "','nuller','nuller')", s);

                        FragmentTransaction ft = getFragmentManager().beginTransaction();
                        android.app.DialogFragment dialogFragment = DialogFragment.newInstance("REGISTER OK","CONFIRM");
                        dialogFragment.show(ft, "dialog");
                    }
                }
                db.close();
                break;

                default:
                break;

        }

    }

    @Override
    public void onComplete(String res) {

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // Pass the activity result to the login button.
        loginButton.onActivityResult(requestCode, resultCode, data);
    }
}
