package com.example.gerard.prueba_viernes;

import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.io.IOException;
import java.util.List;


public class Perfil extends ActionBarActivity implements View.OnClickListener{

    TextView puntuacio;
    TextView usuari;
    ImageView imatge;
    SQLiteDatabase db;
    clase_bd ioh = new clase_bd(this);

    Button gps;

    List<Address> AdressList;
    private LocationManager locationManager;
    private LocationListener locationListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);
        puntuacio = (TextView)findViewById(R.id.puntuacio);
        usuari = (TextView)findViewById(R.id.usuari);
        imatge = (ImageView)findViewById(R.id.img_perfil);
        imatge.setOnClickListener(this);


        gps = (Button)findViewById(R.id.gps);
        gps.setOnClickListener(this);




        SharedPreferences SP = getSharedPreferences("Savestate_login", 0);

        String s = SP.getString("usuari"," " );
        usuari.setText("Usuari : "+s);

        db = ioh.getWritableDatabase();
        String[] s2 = {s};
        Cursor curs = null;
        if(db != null) {
            curs = db.rawQuery("SELECT imatge FROM log_in WHERE username=?", s2);
        }
        if( curs.moveToFirst()){
            if (!curs.getString(0).equals("nuller")){
                // si te imatge
                Bitmap yourSelectedImage = BitmapFactory.decodeFile(curs.getString(0));
                imatge.setImageBitmap(yourSelectedImage);
            }
            else {
                // no te imatge
            }
        }

        curs = null;
        if(db!= null) {
            curs = db.rawQuery("SELECT MIN(puntuacio) FROM ranking WHERE username=? AND puntuacio<>0",s2);
        }
        if(curs.moveToFirst()) {
            if(curs.getInt(0)!=0) puntuacio.setText("Puntuacio: "+String.valueOf(curs.getInt(0)));
            else puntuacio.setText("You need to play more");
        }
        else puntuacio.setText("You need to play more");


        db.close();

        AdressList = null;
        locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
        locationListener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                Geocoder gc = new Geocoder(getApplicationContext());
                try{
                    AdressList = gc.getFromLocation(location.getLatitude(),location.getLongitude(),5);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                for (int i = 0; i <AdressList.size(); i++) {
                    TextView t = (TextView) findViewById(R.id.direccio);
                    if(i==0) t.setText("");
                    t.setText((t.getText()+"\n"+AdressList.get(i).getAddressLine(0).toString()));
                }
            }
            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {        }
            @Override
            public void onProviderEnabled(String provider) { }
            @Override
            public void onProviderDisabled(String provider) { }
        };
        locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER,0,0,locationListener);
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locationListener);




    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_perfil, menu);
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
        switch(v.getId()){
            case R.id.img_perfil:
                Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
                photoPickerIntent.setType("image/*");
                startActivityForResult(photoPickerIntent, 100);
                break;
            case R.id.gps:
                Intent gps_sets = new Intent(android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                startActivity(gps_sets);
                break;
        }


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            case 100:
                if (resultCode == RESULT_OK) {
                    Uri selectedImage = data.getData();
                    String[] filePathColumn = {MediaStore.Images.Media.DATA};

                    Cursor cursor = getContentResolver().query(selectedImage, filePathColumn, null, null, null);
                    cursor.moveToFirst();

                    int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                    String filePath = cursor.getString(columnIndex);
                    cursor.close();

                    db = ioh.getWritableDatabase();
                    if(db != null) {

                        SharedPreferences SP = getSharedPreferences("Savestate_login", 0);

                        String user = SP.getString("usuari"," " );

                    String[] s = {filePath,user};
                        Log.i("Foto","User: "+usuari.getText().toString()+", path: "+filePath);
                        db.execSQL("UPDATE log_in SET imatge=? WHERE username=?", s);
                    }

                    db.close();


                    Bitmap yourSelectedImage = BitmapFactory.decodeFile(filePath);
                    imatge.setImageBitmap(yourSelectedImage);
                }
        }
    }

}

