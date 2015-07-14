package com.example.gerard.prueba_viernes;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;


public class Selection extends ActionBarActivity implements View.OnClickListener {

    Button music,calc,perfil,game;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selection);

        music = (Button)findViewById(R.id.musica);
        music.setOnClickListener(this);

        calc = (Button)findViewById(R.id.calc);
        calc.setOnClickListener(this);

        perfil = (Button)findViewById(R.id.perfil);
        perfil.setOnClickListener(this);

        game = (Button)findViewById(R.id.game);
        game.setOnClickListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_selection, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
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
                Intent intent = new Intent(this, login.class);
                startActivity(intent);
                finish();

                  break;
         }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.musica:
                Intent intent7 = new Intent(getApplicationContext(), Musica.class);
                startActivity(intent7);
                break;
            case R.id.calc:
                Intent intent = new Intent(getApplicationContext(), Calculadora.class);
                startActivity(intent);
                break;
            case R.id.perfil:
                Intent intent55 = new Intent(getApplicationContext(), Perfil.class);
                startActivity(intent55);
                break;
            case R.id.game:
                Intent intent2 = new Intent(getApplicationContext(),PagerHolder.class);
                startActivity(intent2);
                break;
        }
    }
}
