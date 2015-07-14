package com.example.gerard.prueba_viernes;


import android.app.FragmentTransaction;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class Memory extends Fragment implements View.OnClickListener{

        SQLiteDatabase db;
        clase_bd ioh;

        PagerAdapter PA;

        List<Drawable> drawableList;
        List<Integer> integerList;
        ImageView i16,i1,i2,i3,i4,i5,i6,i7,i8,i9,i10,i11,i12,i13,i14,i15;
        Drawable base;
        Integer puntuacio;
        Integer numClicks;
        Boolean primer;
        Boolean segon;

        Integer num_aux_primer;
        Integer num_aux_segon;
        ImageView id_1;
        ImageView id_2;

        Boolean entro;

        TextView punts;
        TextView numIntents;

        public void funcio(PagerAdapter pager){
            PA = pager;
        }


        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            // Inflate the layout for this fragment
            View v = inflater.inflate(R.layout.fragment_memory, container, false);
            puntuacio = 0;
            numClicks = 0;
            primer = true;
            segon = false;
            num_aux_primer = num_aux_segon =0;
            entro = false;


            ioh = new clase_bd(getActivity());
            base = getResources().getDrawable(R.drawable.not_cliked_yet);

            settings(v);
            adding();

            return v;
        }

        @Override
        public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
            super.onCreateOptionsMenu(menu, inflater);

        }

        private void settings(View v) {
            i16 = (ImageView)v.findViewById(R.id.img16);
            i16.setOnClickListener(this);
            i16.setImageDrawable(base);

            i15 = (ImageView)v.findViewById(R.id.img15);
            i15.setOnClickListener(this);
            i15.setImageDrawable(base);

            i14 = (ImageView)v.findViewById(R.id.img14);
            i14.setOnClickListener(this);
            i14.setImageDrawable(base);

            i13 = (ImageView)v.findViewById(R.id.img13);
            i13.setOnClickListener(this);
            i13.setImageDrawable(base);

            i12 = (ImageView)v.findViewById(R.id.img12);
            i12.setOnClickListener(this);
            i12.setImageDrawable(base);

            i11 = (ImageView)v.findViewById(R.id.img11);
            i11.setOnClickListener(this);
            i11.setImageDrawable(base);

            i10 = (ImageView)v.findViewById(R.id.img10);
            i10.setOnClickListener(this);
            i10.setImageDrawable(base);

            i9 = (ImageView)v.findViewById(R.id.img9);
            i9.setOnClickListener(this);
            i9.setImageDrawable(base);

            i8 = (ImageView)v.findViewById(R.id.img8);
            i8.setOnClickListener(this);
            i8.setImageDrawable(base);

            i7 = (ImageView)v.findViewById(R.id.img7);
            i7.setOnClickListener(this);
            i7.setImageDrawable(base);

            i6 = (ImageView)v.findViewById(R.id.img6);
            i6.setOnClickListener(this);
            i6.setImageDrawable(base);

            i5 = (ImageView)v.findViewById(R.id.img5);
            i5.setOnClickListener(this);
            i5.setImageDrawable(base);

            i4 = (ImageView)v.findViewById(R.id.img4);
            i4.setOnClickListener(this);
            i4.setImageDrawable(base);

            i3 = (ImageView)v.findViewById(R.id.img3);
            i3.setOnClickListener(this);
            i3.setImageDrawable(base);

            i2 = (ImageView)v.findViewById(R.id.img2);
            i2.setOnClickListener(this);
            i2.setImageDrawable(base);

            i1 = (ImageView)v.findViewById(R.id.img1);
            i1.setOnClickListener(this);
            i1.setImageDrawable(base);

            punts = (TextView)v.findViewById(R.id.puntuacio);
            punts.setText(" Puntuacio : " + puntuacio.toString());

            numIntents = (TextView)v.findViewById(R.id.text2);
            numIntents.setText(" Numero_intents : "+ numClicks.toString());
        }

        private void adding() {
            drawableList = new ArrayList<>();
            drawableList.add(getResources().getDrawable(R.drawable.denmark_flag_48_2));
            drawableList.add(getResources().getDrawable(R.drawable.finland_flag_48_1));
            drawableList.add(getResources().getDrawable(R.drawable.iceland_flag_48));
            drawableList.add(getResources().getDrawable(R.drawable.mali_flag_48));
            drawableList.add(getResources().getDrawable(R.drawable.new_zealand_flag_48));
            drawableList.add(getResources().getDrawable(R.drawable.senegal_flag_48));
            drawableList.add(getResources().getDrawable(R.drawable.sweden_flag_48));
            drawableList.add(getResources().getDrawable(R.drawable.tokelau_flag_48));

            integerList = new ArrayList<>();

            integerList.add(0);
            integerList.add(0);
            integerList.add(1);
            integerList.add(1);
            integerList.add(2);
            integerList.add(2);
            integerList.add(3);
            integerList.add(3);
            integerList.add(4);
            integerList.add(4);
            integerList.add(5);
            integerList.add(5);
            integerList.add(6);
            integerList.add(6);
            integerList.add(7);
            integerList.add(7);
            Collections.shuffle(integerList);

        }

    public void reset_game() {
        puntuacio = 0;
        numClicks = 0;
        numIntents.setText(" Numero_intents : "+ numClicks.toString());
        punts.setText(" Puntuacio : " + puntuacio.toString());

        i16.setImageDrawable(base);
        i15.setImageDrawable(base);
        i14.setImageDrawable(base);
        i13.setImageDrawable(base);
        i12.setImageDrawable(base);
        i11.setImageDrawable(base);
        i10.setImageDrawable(base);
        i9.setImageDrawable(base);
        i8.setImageDrawable(base);
        i7.setImageDrawable(base);
        i6.setImageDrawable(base);
        i5.setImageDrawable(base);
        i4.setImageDrawable(base);
        i3.setImageDrawable(base);
        i2.setImageDrawable(base);
        i1.setImageDrawable(base);

        i16.setClickable(true);
        i15.setClickable(true);
        i14.setClickable(true);
        i13.setClickable(true);
        i12.setClickable(true);
        i11.setClickable(true);
        i10.setClickable(true);
        i9.setClickable(true);
        i8.setClickable(true);
        i7.setClickable(true);
        i6.setClickable(true);
        i5.setClickable(true);
        i4.setClickable(true);
        i3.setClickable(true);
        i2.setClickable(true);
        i1.setClickable(true);

        Collections.shuffle(integerList);
       // adding();

    }

        @Override
        public void onClick(View v) {

            switch (v.getId()){
                case R.id.img1:
                    detectada(1,i1);
                    compare(1,i1);
                    break;
                case R.id.img2:
                    detectada(2,i2);
                    compare(2,i2);
                    break;
                case R.id.img3:
                    detectada(3,i3);
                    compare(3,i3);
                    break;
                case R.id.img4:
                    detectada(4,i4);
                    compare(4,i4);
                    break;
                case R.id.img5:
                    detectada(5,i5);
                    compare(5,i5);
                    break;
                case R.id.img7:
                    detectada(7,i7);
                    compare(7,i7);
                    break;
                case R.id.img8:
                    detectada(8,i8);
                    compare(8,i8);
                    break;
                case R.id.img9:
                    detectada(9,i9);
                    compare(9,i9);
                    break;
                case R.id.img10:
                    detectada(10,i10);
                    compare(10,i10);
                    break;
                case R.id.img11:
                    detectada(11,i11);
                    compare(11,i11);
                    break;
                case R.id.img12:
                    detectada(12,i12);
                    compare(12,i12);
                    break;
                case R.id.img13:
                    detectada(13,i13);
                    compare(13,i13);
                    break;
                case R.id.img14:
                    detectada(14,i14);
                    compare(14,i14);
                    break;
                case R.id.img15:
                    detectada(15,i15);
                    compare(15,i15);
                    break;
                case R.id.img16:
                    detectada(0,i16);
                    compare(0,i16);
                    break;
                case R.id.img6:
                    detectada(6, i6);
                    compare(6,i6);
                    break;


            }

        }

        private void detectada(int num,ImageView i) {
            if(!entro)i.setImageDrawable(drawableList.get(integerList.get(num)));
        }

        private void compare(int num,ImageView i){
            if(primer && !entro){
                segon = true;
                primer = false;
                id_1 = i;
                num_aux_primer = num;
                i.setClickable(false);
            }
            else if(segon && !entro){
                primer = true;
                id_2 = i;
                num_aux_segon = num;
                ++numClicks;
                numIntents.setText(" Numero_intents : "+ numClicks.toString());
                i.setClickable(false);

                if (integerList.get(num_aux_primer) == integerList.get(num_aux_segon)) {
                    Mytask esp2 = new Mytask();
                    esp2.execute();
                    ++puntuacio;
                    punts.setText(" Puntuacio : " + puntuacio.toString());

                    if(puntuacio==8){
                        SharedPreferences SP = getActivity().getSharedPreferences("Savestate_login", 0);
                        String user = SP.getString("usuari"," " );


                        db = ioh.getWritableDatabase();
                        String[] s_guard = {};
                        if(db!=null){
                            db.execSQL("INSERT INTO ranking (username,puntuacio) VALUES ('" + user + "'," + numClicks+")", s_guard);
                        }
                        db.close();

                        FragmentTransaction ft = getActivity().getFragmentManager().beginTransaction();
                        android.app.DialogFragment dialogFragment = DialogFragment.newInstance("GAME OVER","CONFIRM");
                        dialogFragment.show(ft, "dialog");
                        PagerAdapter.rank.rankAdapter.omple_ranking();
                        PagerAdapter.rank.rankAdapter.notifyDataSetChanged();

                    }

                }
                else if(!entro) {
                    id_1.setClickable(true);
                    id_2.setClickable(true);
                    Mytask esperar = new Mytask();
                    esperar.execute();


                }
            }
        }

        private class Mytask extends AsyncTask<Void,Integer,Void> {

            @Override
            protected Void doInBackground(Void... params) {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                return null;
            }

            @Override
            protected void onProgressUpdate(Integer... values) {
                super.onProgressUpdate(values[0]);
            }

            @Override
            protected void onPreExecute() {
                entro = true;
                super.onPreExecute();
            }

            @Override
            protected void onCancelled() {
                super.onCancelled();
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                if(!(integerList.get(num_aux_primer) == integerList.get(num_aux_segon))){
                    id_1.setImageDrawable(base);
                    id_2.setImageDrawable(base);
                }
                else{
                    segon = false;
                    id_1.setImageDrawable(getResources().getDrawable(R.drawable.ok));
                    id_2.setImageDrawable(getResources().getDrawable(R.drawable.ok));
                    id_1.setClickable(false);
                    id_2.setClickable(false);
                }

                entro = false;
            }
        }

}
