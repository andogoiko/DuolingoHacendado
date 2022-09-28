package com.example.duolingohacendado;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.widget.ImageViewCompat;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.text.Layout;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    MediaPlayer mpGut, mpNope;
    Button continuar;

    int numPreg;

    int pregAct = 0;

    int pregIMGAct = 0;

    int pregBUTTAct = 0;

    int aciertos = 0;

    //array con todas las preguntas

    List<String> alPreguntas = new ArrayList<String>(Arrays.asList("*¿Quién es el mensajero de dios?", "¿Cuándo vuelve Bleach en 2022?", "¿Qué nota merece este trabajo?"));

    // arrays con las respuestas de las imágenes

    private class objImagen {
        String respuesta;
        int drawableID;

        public objImagen (String respuesta, int drawableID){
            this.respuesta = respuesta;
            this.drawableID = drawableID;
        }

    }

    List<objImagen> IdsIV = new ArrayList<objImagen>(Arrays.asList(new objImagen("IVmoises", R.drawable.moises ), new objImagen("*IVskippy", R.drawable.skippy_el_mensajero_de_dios ), new objImagen("IVgabriel", R.drawable.arcangel_gabriel ), new objImagen("IVisaias", R.drawable.isaias_profeta )));

    // array con las respuestas de los botones

    List<List<String>> alRespuestas = new ArrayList<List<String>>();

    List<String> res1 = new ArrayList<String>(Arrays.asList("*10 de Octubre", "3 de Octubre", "27 de Octubre", "19 de Octubre"));

    List<String> res2 = new ArrayList<String>(Arrays.asList("3", "7", "4", "*10"));

    // arrays para saber a que objeto button o imageview nos referimos (sirve para hacer apaño dinámico)

    List<ImageView> imagenes = new ArrayList<ImageView>();
    List<Button> botones = new ArrayList<Button>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView textPreguntas = (TextView) findViewById(R.id.textoPregunta);

        continuar = findViewById(R.id.cumtinuar);

        continuar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

                LinearLayout containerRespuestas = (LinearLayout) findViewById(R.id.answersContainer);

                cleanChilds(containerRespuestas);

                TextView textPreguntas = (TextView) findViewById(R.id.textoPregunta);

                continuar.setEnabled(false);

                pregAct++;

                if(pregAct < numPreg){
                    colocarPreguntas(textPreguntas);
                    colocarRespuestas(findViewById(R.id.layoutBotones));
                }else{
                    textPreguntas.setText("Tu puntuación es:");
                    continuar.setVisibility(View.GONE);
                }

            }
        });

        mpGut = MediaPlayer.create(this, R.raw.sabe_una_cosa);
        mpNope = MediaPlayer.create(this, R.raw.mensajero_de_dios);

        /*mezclamos las respuestas*/

        Collections.shuffle(IdsIV);

        Collections.shuffle(res1);
        Collections.shuffle(res2);

        //recogemos las imágenes a pelo (no hay otra forma)

        imagenes.add(findViewById(R.id.IV1));
        imagenes.add(findViewById(R.id.IV2));
        imagenes.add(findViewById(R.id.IV3));
        imagenes.add(findViewById(R.id.IV4));

        //recogemos los botones a pelo

        botones.add(findViewById(R.id.bResp1));
        botones.add(findViewById(R.id.bResp2));
        botones.add(findViewById(R.id.bResp3));
        botones.add(findViewById(R.id.bResp4));

        //introducimos las respuestas nuevas

        alRespuestas.add(res1);
        alRespuestas.add(res2);

        //cogemos la cantidad de preguntas

        numPreg = alPreguntas.size();

        // iniciamos el programa

        colocarPreguntas(textPreguntas);


        for (int i = 0; i < IdsIV.size(); i++){
            switch (IdsIV.get(i).respuesta){

                case "IVmoises":
                    imagenes.get(i).setBackgroundResource(R.drawable.moises);

                    imagenes.get(i).setOnClickListener(new View.OnClickListener(){
                        @Override
                        public void onClick(View v) {
                            imagenIncorrecta();

                            LinearLayout papi = (LinearLayout) v.getParent();
                            papi.setPadding(5, 5, 5, 5);
                            papi.setBackgroundColor(Color.RED);

                            mpNope.start();
                        }
                    });
                    break;

                case "*IVskippy":
                    imagenes.get(i).setBackgroundResource(R.drawable.skippy_el_mensajero_de_dios);

                    imagenes.get(i).setOnClickListener(new View.OnClickListener(){
                        @Override
                        public void onClick(View v) {
                            imagenCorrecta();

                            LinearLayout papi = (LinearLayout) v.getParent();
                            papi.setPadding(5, 5, 5, 5);
                            papi.setBackgroundColor(Color.GREEN);

                            mpGut.start();
                        }
                    });
                    break;

                case "IVgabriel":
                    imagenes.get(i).setBackgroundResource(R.drawable.arcangel_gabriel);

                    imagenes.get(i).setOnClickListener(new View.OnClickListener(){
                        @Override
                        public void onClick(View v) {
                            imagenIncorrecta();

                            LinearLayout papi = (LinearLayout) v.getParent();
                            papi.setPadding(5, 5, 5, 5);
                            papi.setBackgroundColor(Color.RED);

                            mpNope.start();
                        }
                    });
                    break;

                case "IVisaias":
                    imagenes.get(i).setBackgroundResource(R.drawable.isaias_profeta);

                    imagenes.get(i).setOnClickListener(new View.OnClickListener(){
                        @Override
                        public void onClick(View v) {
                            imagenIncorrecta();

                            LinearLayout papi = (LinearLayout) v.getParent();
                            papi.setPadding(5, 5, 5, 5);
                            papi.setBackgroundColor(Color.RED);

                            mpNope.start();
                        }
                    });
                    break;
            }


        }

    }

    private void imagenIncorrecta(){
        Toast.makeText(MainActivity.this, "¡Incorrecta!", Toast.LENGTH_SHORT).show();

        for (int i = 0; i < IdsIV.size(); i++){
            imagenes.get(i).getBackground().setColorFilter(Color.parseColor("#7A7A7A"), PorterDuff.Mode.MULTIPLY);
            imagenes.get(i).setEnabled(false);
        }

        continuar.setEnabled(true);

    }

    private void imagenCorrecta(){
        aciertos++;

        Toast.makeText(MainActivity.this, "¡Correcta!", Toast.LENGTH_SHORT).show();

        for (int i = 0; i < IdsIV.size(); i++){
            imagenes.get(i).getBackground().setColorFilter(Color.parseColor("#7A7A7A"), PorterDuff.Mode.MULTIPLY);
            imagenes.get(i).setEnabled(false);
        }

        continuar.setEnabled(true);

    }

    private void cleanChilds(LinearLayout containerpapi){

        for ( int i = 0; i < containerpapi.getChildCount();  i++ ){
            View view = containerpapi.getChildAt(i);
            view.setVisibility(View.GONE); // Or whatever you want to do with the view.
        }

    }

    private void colocarPreguntas(TextView textPreguntas){


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

    private void colocarRespuestas(LinearLayout containerRespuestas){

        if(alPreguntas.get(pregAct).charAt(0) != '*'){
            containerRespuestas.setVisibility(View.VISIBLE);

            for (int i = 0; i < botones.size(); i++){

                if(alRespuestas.get(pregBUTTAct).get(i).charAt(0) != '*'){
                    botones.get(i).setText(alRespuestas.get(pregBUTTAct).get(i));

                    botones.get(i).setOnClickListener(new View.OnClickListener(){
                        @Override
                        public void onClick(View v) {
                            v.setBackgroundColor(Color.RED);

                            botonIncorrecto();

                            continuar.setEnabled(true);
                        }
                    });

                }else{
                    botones.get(i).setText(alRespuestas.get(pregBUTTAct).get(i).substring(1));
                    botones.get(i).setOnClickListener(new View.OnClickListener(){
                        @Override
                        public void onClick(View v) {

                            v.setBackgroundColor(Color.GREEN);

                            botonCorrecto();

                            continuar.setEnabled(true);
                        }
                    });
                }

                botones.get(i).setBackgroundColor(Color.DKGRAY);
                botones.get(i).setEnabled(true);

            }

            pregBUTTAct++;
        }else{
            pregIMGAct++;
        }



        /*
        // esto se ha utilizado con layouts y funciona

        Button res1 = new Button(this);
        res1.setId(View.generateViewId());
        int id = res1.getId();

        res1.setText(id + "");

        res1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                findViewById(id).setBackgroundColor(Color.RED);
            }
        });



        containerRespuestas.addView(res1);
        containerRespuestas.invalidate();
        */
    }

    private void botonCorrecto(){

        aciertos++;

        Toast.makeText(MainActivity.this, "¡Correcta!", Toast.LENGTH_SHORT).show();

        for (int i = 0; i < botones.size(); i++){

            botones.get(i).setEnabled(false);
        }

    }

    private void botonIncorrecto(){

        Toast.makeText(MainActivity.this, "¡Incorrecta!", Toast.LENGTH_SHORT).show();

        for (int i = 0; i < botones.size(); i++){

            botones.get(i).setEnabled(false);
        }

    }
}