package com.example.gerard.prueba_viernes;

/**
 * Created by gerard on 13/07/2015.
 */
public class Player {
    private Integer posicio;
    private Integer puntuacio;
    private String username;

    Player(int pos, int punts,String user){
        posicio = pos;
        puntuacio = punts;
        username = user;
    }

    public Integer get_pos(){
        return posicio;
    }
    public String get_username(){
        return username;
    }
    public Integer get_puntuacio(){
        return puntuacio;
    }


}
