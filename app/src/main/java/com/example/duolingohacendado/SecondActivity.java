package com.example.duolingohacendado;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class SecondActivity extends AppCompatActivity {

    //array con todas las preguntas

    List<String> alPreguntas;

    // array con las respuestas de los botones

    List<List<String>> alRespuestas = new ArrayList<List<String>>();

    List<String> res1;

    List<String> res2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
    }
}