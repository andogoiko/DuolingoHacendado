package com.example.duolingohacendado;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class SecondActivity extends AppCompatActivity {

    //array con todas las preguntas

    List<String> alPreguntas;

    // array con las respuestas de los botones

    List<List<String>> alRespuestas = new ArrayList<List<String>>();

    List<String> res1;

    List<String> res2;

    List<String> res3;

    // puntero de pregunta actual

    int pregAct = 0;

    int numPreg = 0;


    // array para saber a que objeto button  nos referimos (sirve para hacer apaño dinámico)

    List<Button> botones = new ArrayList<Button>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        // añadiendo data a arrays (hemos de wrappear todas las llamadas del xml en new Arraylist<>, sino no podremos hacer el método remove)

        alPreguntas = new ArrayList<>(Arrays.asList(getResources().getStringArray(R.array.preguntas2Act)));

        res1 = new ArrayList<>(Arrays.asList(getResources().getStringArray(R.array.act2RespP1)));

        res2 = new ArrayList<>(Arrays.asList(getResources().getStringArray(R.array.act2RespP2)));

        res3 = new ArrayList<>(Arrays.asList(getResources().getStringArray(R.array.act2RespP3)));

        // randomizamos ordend e respuestas

        Collections.shuffle(res1);
        Collections.shuffle(res2);
        Collections.shuffle(res3);

        //introducimos las respuestas nuevas

        alRespuestas.add(res1);
        alRespuestas.add(res2);
        alRespuestas.add(res3);

        //cogemos la cantidad de preguntas

        numPreg = alPreguntas.size();

        //recogemos los botones a pelo

        botones.add(findViewById(R.id.botoncito1));
        botones.add(findViewById(R.id.botoncito2));
        botones.add(findViewById(R.id.botoncito3));
        botones.add(findViewById(R.id.botoncito4));
        botones.add(findViewById(R.id.botoncito5));
        botones.add(findViewById(R.id.botoncito6));
        botones.add(findViewById(R.id.botoncito7));
        botones.add(findViewById(R.id.botoncito8));
        botones.add(findViewById(R.id.botoncito9));
        botones.add(findViewById(R.id.botoncito10));


        colocarPreguntas(findViewById(R.id.tvPregunta));

        colocarRespuestas();
    }


    private void colocarPreguntas(TextView textPreguntas){

        textPreguntas.setTextSize(15);

        if(alPreguntas.get(pregAct).charAt(0) != '*'){
            textPreguntas.setText(alPreguntas.get(pregAct));
        }else{
            textPreguntas.setText(alPreguntas.get(pregAct).substring(1));
        }


        /*
        // esto se ha utilizado con layouts y funciona

        TextView pregunta = new TextView(this);
        pregunta.setText("text");

        containerPreguntas.addView(pregunta);
        containerPreguntas.invalidate();
        */
    }

    private void colocarRespuestas(){


        for (int i = 0; i < botones.size(); i++){

            // repuestas incorrectas

            if(alRespuestas.get(pregAct).get(i).charAt(0) != '*'){

                botones.get(i).setText(alRespuestas.get(pregAct).get(i));

                botones.get(i).setOnClickListener(new View.OnClickListener(){
                    @Override
                    public void onClick(View v) {
                        v.setBackgroundColor(Color.RED);

                        botonIncorrecto();

                    }
                });

            }else{

                // respuestas correctas

                botones.get(i).setText(alRespuestas.get(pregAct).get(i).substring(1));
                botones.get(i).setOnClickListener(new View.OnClickListener(){
                    @Override
                    public void onClick(View v) {

                        v.setBackgroundColor(Color.GREEN);

                        botonCorrecto();

                    }
                });
            }

            botones.get(i).setBackgroundColor(Color.DKGRAY);
            botones.get(i).setEnabled(true);

        }


    }

    private void botonCorrecto(){

        Toast.makeText(SecondActivity.this, getString(R.string.correcto), Toast.LENGTH_SHORT).show();

        for (int i = 0; i < botones.size(); i++){

            botones.get(i).setEnabled(false);
        }

        alPreguntas.remove(pregAct);

        alRespuestas.remove(pregAct);

        if(alPreguntas.size() != 0){

            if(alPreguntas.size() == pregAct){
                pregAct = 0;
            }

            colocarPreguntas(findViewById(R.id.tvPregunta));

            colocarRespuestas();

        }else{
            findViewById(R.id.tvPregunta).setVisibility(View.GONE);
            findViewById(R.id.svPreg).setVisibility(View.GONE);
            findViewById(R.id.tvFinito).setVisibility(View.VISIBLE);
        }

    }

    private void botonIncorrecto(){

        Toast.makeText(SecondActivity.this, getString(R.string.incorrecto), Toast.LENGTH_SHORT).show();

        for (int i = 0; i < botones.size(); i++){

            botones.get(i).setEnabled(false);
        }


        if(alPreguntas.size() != 0){
            if (alPreguntas.size() - 1 != pregAct){
                pregAct++;
            }else{
                pregAct = 0;
            }
        }

        colocarPreguntas(findViewById(R.id.tvPregunta));

        colocarRespuestas();

    }


}