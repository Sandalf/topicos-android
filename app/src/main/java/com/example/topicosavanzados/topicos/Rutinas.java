package com.example.topicosavanzados.topicos;
public class Rutinas {

    public static String PonBlancos(String Texto,int Tamaño){
        while (Texto.length() <Tamaño)
            Texto+=" ";
        return Texto;
    }

}
