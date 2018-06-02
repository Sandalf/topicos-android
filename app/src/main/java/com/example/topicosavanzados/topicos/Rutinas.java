package com.example.topicosavanzados.topicos;

/**
 * Created by luis on 6/1/18.
 */

public class Rutinas {

    public static String PonBlancos(String Texto,int Tamaño){
        while (Texto.length() <Tamaño)
            Texto+=" ";
        return Texto;
    }

}
