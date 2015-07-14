package com.example.gerard.prueba_viernes;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by gerard on 13/07/2015.
 */
public class ranking_adap extends RecyclerView.Adapter<ranking_adap.AdapterViewHolder> {

    ArrayList<Player> jugadors;
    SQLiteDatabase db;
    clase_bd ioh ;


    public void reset_ranking(){

        jugadors = new ArrayList<>();
        String[] s = {};
        db = ioh.getWritableDatabase();
        if(db != null) {
            db.execSQL("DELETE FROM ranking", s);
        }
        db.close();
        notifyDataSetChanged();
    }


    public void omple_ranking(){
        jugadors = new ArrayList<>();
        db = ioh.getWritableDatabase();
        Cursor curs = null;
        int position = 1;
        if(db != null) {
            String[] s = {};
            curs = db.rawQuery("SELECT username, puntuacio FROM ranking ORDER BY puntuacio ASC", s);
        }
        if(curs == null) Log.i("curs", "el cursor es null");
        if(curs.moveToFirst()) {
            do {
                Player aux = new Player(position, curs.getInt(1),curs.getString(0));
                Log.i("Player", "user: "+aux.get_username());
                jugadors.add(aux);
                ++position;
            }
            while(curs.moveToNext());

        }
        db.close();
    }

    ranking_adap(FragmentActivity frag){
        ioh = new clase_bd(frag);

        jugadors = new ArrayList<>();
        db = ioh.getWritableDatabase();
        Cursor curs = null;
        int position = 1;
        if(db != null) {
            String[] s = {};
            curs = db.rawQuery("SELECT username, puntuacio FROM ranking ORDER BY puntuacio ASC", s);
        }
        if(curs == null) Log.i("curs", "el cursor es null");
        if(curs.moveToFirst()) {
            do {
                Player aux = new Player(position, curs.getInt(1),curs.getString(0));
                Log.i("Player", "user: "+aux.get_username());
            jugadors.add(aux);
            ++position;
            }
            while(curs.moveToNext());

        }
        db.close();
    }

    @Override
    public void onBindViewHolder(ranking_adap.AdapterViewHolder adapterViewHolder, int i) {

        adapterViewHolder.username.setText(jugadors.get(i).get_username());
        adapterViewHolder.position.setText(String.valueOf(jugadors.get(i).get_pos()));
        adapterViewHolder.puntuacio.setText(String.valueOf(jugadors.get(i).get_puntuacio()));


    }

    @Override
    public int getItemCount() {
        return jugadors.size();
    }


    @Override
    public ranking_adap.AdapterViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        //Instancia un layout XML en la correspondiente vista.
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        //Inflamos en la vista el layout para cada elemento
        View view = inflater.inflate(R.layout.rowlayout, viewGroup, false);
        return new AdapterViewHolder(view);
    }

    public class AdapterViewHolder extends RecyclerView.ViewHolder {
        public TextView position;
        public TextView username;
        public TextView puntuacio;
        public View v;
        public AdapterViewHolder(View itemView) {
            super(itemView);
            this.v = itemView;
            this.position = (TextView) itemView.findViewById(R.id.pos);
            this.username = (TextView) itemView.findViewById(R.id.user);
            this.puntuacio = (TextView) itemView.findViewById(R.id.punts);
        }
    }



}
