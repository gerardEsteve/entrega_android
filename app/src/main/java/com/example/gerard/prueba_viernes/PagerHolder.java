package com.example.gerard.prueba_viernes;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

public class PagerHolder extends ActionBarActivity implements View.OnClickListener,DialogFragment.OnCompleteListener {

    PagerAdapter p;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pager_holder);



        p = new PagerAdapter(getSupportFragmentManager(), PagerHolder.this);
        // Get the ViewPager and set it's PagerAdapter so that it can display items
        ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);
        viewPager.setAdapter(p);

        // Give the TabLayout the ViewPager (material)
        TabLayout tabLayout = (TabLayout) findViewById(R.id.sliding_tabs);
        tabLayout.setTabTextColors(Color.DKGRAY, Color.BLACK);
        tabLayout.setupWithViewPager(viewPager);
    }

    @Override
    public void onComplete(String res) {

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_pager_holder, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        switch (item.getItemId()){
            case R.id.clear:
                Memory aux = p.mem;
                aux.reset_game();
                break;
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
            case R.id.rank_reset:

                p.rank.rankAdapter.reset_ranking();

                break;
        }


        return super.onOptionsItemSelected(item);
    }

    @Override
   public void onClick(View v) {
    }
}
