package com.example.gerard.prueba_viernes;

import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.support.design.widget.Snackbar;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


public class Calculadora extends ActionBarActivity implements View.OnClickListener{


    Integer decimal;
    String s_primer;
    String s_segon;
    Boolean coma;
    Button bcoma;

    String op;
    Button anss;
    Button delete;
    Button b9;
    Button b8;
    Button b7;
    Button b6;
    Button b5;
    Button b4;
    Button b3;
    Button b2;
    Button b1;
    Button b0;
    Button sumar;
    Button restar;
    Button multiplicar;
    Button dividir;
    Button result;
    Double resultat;
    Integer primer;
    Integer segon;
    Boolean sumatori;
    Boolean restador;
    Boolean multiplicador;
    Boolean divisor;
    TextView resultat_final;
    Button clear;
    Double ans;

    int notificacions;
    int mId;
    View layout;

    public void notis(String titol,String message){

        if (notificacions == 0){
            //notificacions d'estat
            //Entero que nos permite identificar la notificacion
            ++mId;
            //Instanciamos Notification Manager
            NotificationManager mNotificationManager =
                    (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
            // Para la notificaciones, en lugar de crearlas directamente, lo hacemos mediante
            // un Builder/contructor.
            NotificationCompat.Builder mBuilder =
                    new NotificationCompat.Builder(getApplicationContext())
                            .setSmallIcon(R.drawable.abc_btn_rating_star_off_mtrl_alpha)
                            .setContentTitle(titol)
                            .setContentText(message);

            mNotificationManager.notify(mId, mBuilder.build());
        }
        else if (notificacions == 1){
            //notificacions Toast
            Toast.makeText(Calculadora.this, message, Toast.LENGTH_SHORT).show();
        }
        else{
            View.OnClickListener myOnClickListener = new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    clear();
                    int aux = notificacions;
                    notificacions = 1;
                    notis("clear","se ha hecho clear");
                    notificacions = aux;
                }
            };

            Snackbar.make(layout, message, Snackbar.LENGTH_LONG)
                    .setAction("OK", myOnClickListener)
                    .show();
        }


    }

    private void number(int num){
        if(!sumatori && !restador && !multiplicador && !divisor ){
            if(!coma){
                primer = (primer*10)+num;
                resultat_final.setText(primer.toString());
            }
            else{
                decimal = (decimal*10)+num;
                resultat_final.setText(primer.toString()+"."+decimal.toString());
            }
            s_primer = primer.toString() + "." + decimal.toString();


        }
        else{
            if(!coma){
                decimal = 0;
                segon = (segon*10)+num;
                resultat_final.setText(s_primer +" "+ op +" "+ segon.toString());
            }
            else{
                decimal = (decimal*10)+num;
                resultat_final.setText(s_primer + " "+op +" "+ segon.toString()+"."+decimal.toString());
            }
            s_segon = segon.toString() + "." + decimal.toString();
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("Primer", primer);
        outState.putInt("Segon", segon);
        outState.putDouble("resultat", resultat);
        outState.putString("resultat_final", resultat_final.getText().toString());
        outState.putBoolean("sumatori", sumatori);
        outState.putBoolean("restador", restador);
        outState.putBoolean("multiplicador", multiplicador);
        outState.putBoolean("divisor", divisor);
        outState.putString("op", op);
        outState.putDouble("ans", ans);

        outState.putString("s_primer", s_primer);
        outState.putString("s_segon", s_segon);
        outState.putBoolean("coma", coma);
        outState.putInt("decimal", decimal);

        outState.putInt("noti", notificacions);
        outState.putInt("mId",mId);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        if(savedInstanceState != null){
            op = savedInstanceState.getString("op"," ");
            primer = savedInstanceState.getInt("Primer", 0);
            segon = savedInstanceState.getInt("Segon", 0);
            resultat = savedInstanceState.getDouble("resultat", 0);
            resultat_final.setText(savedInstanceState.getString("resultat_final", "0"));
            sumatori = savedInstanceState.getBoolean("sumatori", false);
            restador = savedInstanceState.getBoolean("restador",false);
            multiplicador = savedInstanceState.getBoolean("multiplicador",false);
            divisor = savedInstanceState.getBoolean("divisor", false);
            ans = savedInstanceState.getDouble("ans", 0);

            coma = savedInstanceState.getBoolean("coma", coma);
            s_primer=savedInstanceState.getString("s_primer", s_primer);
            s_segon=savedInstanceState.getString("s_segon", s_segon);
            decimal= savedInstanceState.getInt("decimal", 0);

            notificacions = savedInstanceState.getInt("noti", 0);
            mId = savedInstanceState.getInt("mId",1);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculadora);
        sumatori = false;
        restador = false;
        multiplicador = false;
        divisor = false;
        primer = 0;
        segon = 0;
        resultat = 0.0;
        ans = 0.0;
        resultat_final = (TextView)findViewById(R.id.textView6);
        op = " ";

        mId = 1;
        notificacions = 0;

        layout = findViewById(R.id.ter);

        s_primer=" ";
        s_segon=" ";
        coma = false;
        decimal = 0;

        resultat_final.setText(resultat.toString());

        bcoma = (Button)findViewById(R.id.coma);
        bcoma.setOnClickListener(this);

        delete = (Button)findViewById(R.id.delete);
        b9 = (Button)findViewById(R.id.b9);
        b8 = (Button)findViewById(R.id.b8);
        b7 = (Button)findViewById(R.id.b7);
        b6 = (Button)findViewById(R.id.b6);
        b5 = (Button)findViewById(R.id.b5);
        b4 = (Button)findViewById(R.id.b4);
        b3 = (Button)findViewById(R.id.b3);
        b2 = (Button)findViewById(R.id.b2);
        b1 = (Button)findViewById(R.id.b1);
        b0 = (Button)findViewById(R.id.b0);
        anss = (Button)findViewById(R.id.ans);
        sumar = (Button)findViewById(R.id.sum);
        restar = (Button)findViewById(R.id.rest);
        multiplicar = (Button)findViewById(R.id.mult);
        dividir = (Button)findViewById(R.id.div);
        result = (Button)findViewById(R.id.igual);
        clear = (Button)findViewById(R.id.clear);

        anss.setOnClickListener(this);
        delete.setOnClickListener(this);
        b9.setOnClickListener(this);
        b8.setOnClickListener(this);
        b7.setOnClickListener(this);
        b6.setOnClickListener(this);
        b5.setOnClickListener(this);
        b4.setOnClickListener(this);
        b3.setOnClickListener(this);
        b2.setOnClickListener(this);
        b1.setOnClickListener(this);
        b0.setOnClickListener(this);
        sumar.setOnClickListener(this);
        restar.setOnClickListener(this);
        multiplicar.setOnClickListener(this);
        dividir.setOnClickListener(this);
        result.setOnClickListener(this);
        clear.setOnClickListener(this);

        SharedPreferences SP = getSharedPreferences("Savestate_calculadora", Context.MODE_PRIVATE);
        resultat_final.setText(SP.getString("TxT","0.0"));
        String[] s;
        s = resultat_final.getText().toString().split(" ");
        if(s.length == 1){
            s_primer = s[0];
            if(s_primer.contains(".")){
                String[] s2 = s_primer.split("\\.");
                primer = Integer.valueOf(s2[0]);
                String aux = s_primer.substring(s_primer.length()-2,s_primer.length());
                if(aux.equals(".0")){
                    coma = false;
                }
                else {
                    coma = true;
                    decimal = Integer.valueOf(s2[1]);
                }
            }
            else{
                coma = false;
                primer = Integer.valueOf(s_primer);
            }

        }
        else if (s.length == 2){
            s_primer = s[0];
            String[] s2 = s_primer.split("\\.");
            primer = Integer.valueOf(s2[0]);
            op = s[1];
            if (op.equals("+")) sumatori = true;
            else if (op.equals("-"))restador = true;
            else if (op.equals( "*"))multiplicador = true;
            else if (op.equals("/"))divisor = true;

        }
        else if(s.length == 3){
            s_primer = s[0];
            String[] s2 = s_primer.split("\\.");
            primer = Integer.valueOf(s2[0]);
            op = s[1];
            if (op.equals("+")) sumatori = true;
            else if (op.equals("-"))restador = true;
            else if (op.equals( "*"))multiplicador = true;
            else if (op.equals("/"))divisor = true;
            s_segon = s[2];
            if(s_segon.contains(".")) {
                s2 = s_segon.split("\\.");
                segon = Integer.valueOf(s2[0]);
                if (s_segon.substring(s_segon.length() - 2, s_segon.length()).equals(".0"))
                    coma = false;
                else {
                    coma = true;
                    decimal = Integer.valueOf(s2[1]);
                }
            }
            else {
                coma = false;
                segon = Integer.valueOf(s_segon);
            }
        }
        notificacions = SP.getInt("notificacio", 0);


    }

    @Override
    public void onClick(View v) {



        switch(v.getId()){
            case R.id.coma:
                coma = true;
                if(!sumatori && !restador && !multiplicador && !divisor ) {
                    resultat_final.setText(s_primer);
                }
                else resultat_final.setText(s_primer + op + s_segon);

                break;
            case R.id.b9:
                number(9);
                break;
            case R.id.b8:
                number(8);
                break;

            case R.id.b7:
                number(7);
                break;

            case R.id.b6:
                number(6);
                break;

            case R.id.b5:
                number(5);
                break;

            case R.id.b4:
                number(4);
                break;

            case R.id.b3:
                number(3);
                break;

            case R.id.b2:
                number(2);
                break;

            case R.id.b1:
                number(1);
                break;

            case R.id.b0:
                number(0);
                break;
            case R.id.ans:
                if(sumatori||restador||multiplicador||divisor){
                    resultat_final.setText(primer.toString() +" "+ op +" "+ ans.toString());
                }
                else {
                    resultat_final.setText(ans.toString());
                }
                break;
            case R.id.sum:
                sumatori = true;
                decimal = 0 ;
                restador = multiplicador = divisor = coma =false;
                op = "+";
                resultat_final.setText(s_primer +" "+ op);
                break;
            case R.id.rest:
                op = "-";
                resultat_final.setText(s_primer +" "+ op);
                restador = true;
                multiplicador = sumatori = divisor = coma = false;
                decimal = 0 ;

                break;
            case R.id.mult:
                op = "*";
                resultat_final.setText(s_primer + " "+op);
                multiplicador = true;
                sumatori = restador = divisor = coma = false;
                decimal = 0 ;

                break;
            case R.id.div:
                op = "/";
                resultat_final.setText(s_primer + " "+op);
                divisor = true;
                multiplicador = sumatori = restador = coma = false;
                decimal = 0 ;

                break;
            case R.id.igual:
                if( (s_primer.equals("0.0")||s_primer.equals("0")) && ( s_segon.equals("0") || s_segon.equals("0.0")) && divisor){
                    notis("ERROR", " 0 entre 0 ");
                }
                else if(sumatori){

                    resultat = Double.parseDouble(s_primer) + Double.parseDouble(s_segon);
                    resultat_final.setText(resultat_final.getText() + " = " + resultat.toString());
                    ans = resultat;
                    segon = 0;
                    sumatori=false;
                    decimal = 0 ;
                    coma = false;
                }
                else if(restador){
                    resultat = Double.parseDouble(s_primer) - Double.parseDouble(s_segon);
                    resultat_final.setText(resultat_final.getText()+" = " + resultat.toString());
                    ans = resultat;
                    segon = 0;
                    restador = false;
                    decimal = 0 ;
                    coma = false;
                }
                else if(multiplicador){
                    resultat = Double.parseDouble(s_primer) * Double.parseDouble(s_segon);
                    resultat_final.setText(resultat_final.getText()+" = " + resultat.toString());
                    ans = resultat;
                    segon = 0;
                    multiplicador = false;
                    decimal = 0 ;
                    coma = false;
                }
                else if(divisor){
                    if(s_segon.equals("0") || s_segon.equals("0.0")){
                        notis("ERROR","division entre 0");
                    }
                    else{
                        resultat = Double.parseDouble(s_primer) / Double.parseDouble(s_segon);
                        resultat_final.setText(resultat_final.getText()+" = " + resultat.toString());
                        ans = resultat;
                        segon = 0;
                        decimal = 0 ;
                        coma = false;
                    }

                    divisor = false;
                }
                else notis("Choose","Click An Operation Before This");

                break;
            case R.id.clear:
                clear();

                break;
            case R.id.delete:

                delete();
                break;
            default:
                break;


        }
    }

    public void delete() {


        if(!sumatori && !restador && !multiplicador && !divisor ){
            if(coma){
                decimal = decimal/10;
                if(decimal==0)coma = false;
            }
            else{
                primer = primer/10;
            }

            s_primer = primer.toString() + "." + decimal.toString();

            resultat_final.setText(s_primer);
        }
        else if(s_segon.equals("0.0") &&( sumatori || restador || multiplicador || divisor)){
            resultat_final.setText(s_primer);
            Boolean trobat = false;
            char aux2;
            for(int i = 0; i < s_primer.length();++i){
                if(s_primer.charAt(i)=='.'){
                    trobat = true;
                    ++i;
                }
                if(trobat){
                    aux2 = s_primer.charAt(i);
                    decimal += Integer.valueOf(String.valueOf(aux2));
                    decimal *= 10;
                }
            }

            if(decimal==0)coma = false;
            else coma = true;
            sumatori = restador = multiplicador = divisor = false;
        }
        else{
            if(coma){
                decimal=decimal/10;
                if(decimal==0)coma = false;
            }
            else{
                segon = segon / 10;
            }
            s_segon = segon.toString() + "." + decimal.toString();
            resultat_final.setText(s_primer + op + s_segon);
        }
    }

    public void clear() {
        primer = 0;
        segon = 0;
        resultat = 0.0;
        sumatori = false;
        restador = false;
        multiplicador = false;
        divisor = false;
        resultat_final.setText(resultat.toString());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_calculadora, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.call:

                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:"));
                startActivity(intent);

                return true;

            case R.id.navegadorr:


                String link = "http://www.google.es";
                Intent intent2 = new Intent(Intent.ACTION_VIEW, Uri.parse(link));
                startActivity(intent2);

                return true;

            case R.id.toast:
                notificacions = 1;
                return true;
            case R.id.estat:
                notificacions = 0;
                return true;
            case R.id.snack:
                notificacions = 2;
                return true;
            case R.id.logout:

                SharedPreferences SP = getSharedPreferences("Savestate_login", 0);
                //Obtenemos el editor
                SharedPreferences.Editor editor = SP.edit();
                //Editamos
                editor.putBoolean("logout", true);
                //Guardamos los cambios
                editor.apply();
                // anar a la pantalla de login
                Intent intent4 = new Intent(this, login.class);
                startActivity(intent4);
                return true;

            default:

                return super.onOptionsItemSelected(item);
        }

    }

    @Override
    protected void onDestroy() {

        super.onDestroy();
        SharedPreferences SP = getSharedPreferences("Savestate_calculadora", 0);
        //Obtenemos el editor
        SharedPreferences.Editor editor = SP.edit();
        //Editamos
        editor.putString("TxT", resultat_final.getText().toString());
        editor.putInt("notificacio", notificacions);
        //Guardamos los cambios
        editor.apply();
    }
}
