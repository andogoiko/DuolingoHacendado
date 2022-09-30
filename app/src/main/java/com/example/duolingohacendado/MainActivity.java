package com.example.duolingohacendado;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.widget.ImageViewCompat;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
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
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    MediaPlayer mpGut, mpNope, mpFin;
    Button continuar;
    TextToSpeech textToSpeech;

    int numPreg;

    int pregAct = 0;

    int pregIMGAct = 0;

    int pregBUTTAct = 0;

    int aciertos = 0;

    //array con todas las preguntas

    List<String> alPreguntas;

    // arrays con las respuestas de las imágenes

    private class objImagen {
        String respuesta;
        int drawableID;

        public objImagen (String respuesta, int drawableID){
            this.respuesta = respuesta;
            this.drawableID = drawableID;
        }

    }

    List<List<objImagen>> alIMGRespuestas = new ArrayList<List<objImagen>>(Arrays.asList(new ArrayList<objImagen>(Arrays.asList(new objImagen("IVmoises", R.drawable.moises ), new objImagen("*IVskippy", R.drawable.skippy_el_mensajero_de_dios ), new objImagen("IVgabriel", R.drawable.arcangel_gabriel ), new objImagen("IVisaias", R.drawable.isaias_profeta )))));

    // array con las respuestas de los botones

    List<List<String>> alRespuestas = new ArrayList<List<String>>();

    List<String> res1;

    List<String> res2;

    // arrays para saber a que objeto button o imageview nos referimos (sirve para hacer apaño dinámico)

    List<ImageView> imagenes = new ArrayList<ImageView>();
    List<Button> botones = new ArrayList<Button>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // añadiendo data a arrays

        alPreguntas = Arrays.asList(getResources().getStringArray(R.array.preguntas));

        res1 = Arrays.asList(getResources().getStringArray(R.array.respBot1));

        res2 = Arrays.asList(getResources().getStringArray(R.array.respBot2));

        /*mezclamos las respuestas de los arrays*/

        for (int i = 0; i < alIMGRespuestas.size(); i++){
            Collections.shuffle(alIMGRespuestas.get(i));
        }

        Collections.shuffle(res1);
        Collections.shuffle(res2);

        //introducimos las respuestas nuevas

        alRespuestas.add(res1);
        alRespuestas.add(res2);

        //cogemos la cantidad de preguntas

        numPreg = alPreguntas.size();

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

        // funcionalidad text to speech

        textToSpeech = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int i) {

                // if No error is found then only it will run
                if(i!=TextToSpeech.ERROR){
                    // To Choose language of speech
                    textToSpeech.setLanguage(Locale.UK);
                }
            }
        });

        TextView textPreguntas = (TextView) findViewById(R.id.textoPregunta);

        //implementando text to speech

        textPreguntas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                textToSpeech.speak(textPreguntas.getText().toString(),TextToSpeech.QUEUE_FLUSH,null);
            }
        });

        // funcionalidad del boton continuar a la siguiente pregunta

        continuar();

        // colocamos las preguntas y respuestas para comenzar el programa

        colocarPreguntas(textPreguntas);

        colocarRespuestas(findViewById(R.id.answersContainer));

    }

    private void continuar(){

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
                    colocarRespuestas(findViewById(R.id.answersContainer));
                }else{

                    if (aciertos == 0){
                        mpFin = MediaPlayer.create(getApplicationContext(), R.raw.jovani_vazquez_elden_ring);
                    }else{
                        mpFin = MediaPlayer.create(getApplicationContext(), R.raw.movistar_temazo);
                    }

                    mpGut.stop();
                    mpNope.stop();

                    mpFin.start();

                    textPreguntas.setText(getString(R.string.fin));
                    textPreguntas.setTextSize(30);

                    TextView resul = (TextView) findViewById(R.id.textPuntuacion);
                    resul.setText(aciertos + "/" + alPreguntas.size());

                    findViewById(R.id.layPuntuacion).setVisibility(View.VISIBLE);

                    continuar.setVisibility(View.GONE);

                    Button nextActivity = (Button) findViewById(R.id.botonNextActivity);

                    nextActivity.setVisibility(View.VISIBLE);

                    nextActivity.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {

                            mpFin.stop();

                            Intent myIntent = new Intent(MainActivity.this, SecondActivity.class);
                            //myIntent.putExtra("key", value); //Optional parameters
                            MainActivity.this.startActivity(myIntent);
                        }
                    });
                }

            }
        });
    }

    private void imagenIncorrecta(){
        Toast.makeText(MainActivity.this, getString(R.string.incorrecto), Toast.LENGTH_SHORT).show();

        for (int i = 0; i < alIMGRespuestas.get(pregIMGAct).size(); i++){
            imagenes.get(i).getBackground().setColorFilter(Color.parseColor("#7A7A7A"), PorterDuff.Mode.MULTIPLY);
            imagenes.get(i).setEnabled(false);
        }

        continuar.setEnabled(true);

        pregIMGAct++;

    }

    private void imagenCorrecta(){
        aciertos++;

        Toast.makeText(MainActivity.this, getString(R.string.correcto), Toast.LENGTH_SHORT).show();

        for (int i = 0; i < alIMGRespuestas.get(pregIMGAct).size(); i++){
            imagenes.get(i).getBackground().setColorFilter(Color.parseColor("#7A7A7A"), PorterDuff.Mode.MULTIPLY);
            imagenes.get(i).setEnabled(false);
        }

        continuar.setEnabled(true);

        pregIMGAct++;

    }

    private void cleanChilds(LinearLayout containerpapi){

        for ( int i = 0; i < containerpapi.getChildCount();  i++ ){
            View view = containerpapi.getChildAt(i);
            view.setVisibility(View.GONE); // Or whatever you want to do with the view.
        }

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

    private void colocarRespuestas(LinearLayout containerRespuestas){

        cleanChilds(containerRespuestas);

        // para respuestas de botón

        if(alPreguntas.get(pregAct).charAt(0) != '*'){

            mpGut = MediaPlayer.create(this, R.raw.si_tiritititiiiiiii);
            mpNope = MediaPlayer.create(this, R.raw.minecraft_old_pupa);

            containerRespuestas.getChildAt(0).setVisibility(View.VISIBLE);

            for (int i = 0; i < botones.size(); i++){

                // repuestas incorrectas

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

                    // respuestas correctas

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

        }else{

            // para respuestas de imagen

            mpGut = MediaPlayer.create(this, R.raw.sabe_una_cosa);
            mpNope = MediaPlayer.create(this, R.raw.mensajero_de_dios);

            containerRespuestas.getChildAt(1).setVisibility(View.VISIBLE);

            for (int i = 0; i < alIMGRespuestas.get(pregIMGAct).size(); i++){

                imagenes.get(i).setBackgroundResource(alIMGRespuestas.get(pregIMGAct).get(i).drawableID);

                // repuestas incorrectas

                if(alIMGRespuestas.get(pregIMGAct).get(i).respuesta.charAt(0) != '*'){

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
                }else{

                    // repuestas correctas

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
                }
            }
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

        Toast.makeText(MainActivity.this, getString(R.string.correcto), Toast.LENGTH_SHORT).show();

        for (int i = 0; i < botones.size(); i++){

            botones.get(i).setEnabled(false);
        }

        mpGut.start();

        pregBUTTAct++;

    }

    private void botonIncorrecto(){

        Toast.makeText(MainActivity.this, getString(R.string.incorrecto), Toast.LENGTH_SHORT).show();

        for (int i = 0; i < botones.size(); i++){

            botones.get(i).setEnabled(false);
        }

        mpNope.start();

        pregBUTTAct++;

    }
}