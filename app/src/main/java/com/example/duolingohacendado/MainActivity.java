package com.example.duolingohacendado;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.widget.ImageViewCompat;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.PorterDuff;
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

    int posicion = 0;

    int numPreg = 3;

    int pregAct = 1;

    int aciertos = 0;

    List<String> IdsIV = new ArrayList<String>(Arrays.asList("IVmoises", "IVskippy", "IVgabriel", "IVisaias"));

    List<String> alPreguntas = new ArrayList<String>(Arrays.asList("¿Cuándo vuelve Bleach en 2022?", "¿Qué nota merece este trabajo?"));

    List<List<String>> alRespuestas = new ArrayList<List<String>>();

    List<String> res1 = new ArrayList<String>(Arrays.asList("*10 de Octubre", "3 de Octubre", "27 de Octubre", "19 de Octubre"));

    List<String> res2 = new ArrayList<String>(Arrays.asList("3", "7", "4", "*10"));



    List<ImageView> imagenes = new ArrayList<ImageView>();
    ImageView[] aImagenAct = new ImageView[4];
    LinearLayout[] dinamicLayout = new LinearLayout[4];
    List<Button> botones = new ArrayList<Button>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        continuar = findViewById(R.id.cumtinuar);

        continuar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

                LinearLayout containerRespuestas = (LinearLayout) findViewById(R.id.answersContainer);

                cleanChilds(containerRespuestas);

                TextView textPreguntas = (TextView) findViewById(R.id.textoPregunta);

                continuar.setEnabled(false);

                if(pregAct < numPreg){
                    colocarPreguntas(textPreguntas);
                    colocarRespuestas(containerRespuestas);
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


        for (int i = 0; i < IdsIV.size(); i++){
            switch (IdsIV.get(i)){

                case "IVmoises":
                    imagenes.get(i).setBackgroundResource(R.drawable.moises);

                    aImagenAct[0] = imagenes.get(i);

                    imagenes.get(i).setOnClickListener(new View.OnClickListener(){
                        @Override
                        public void onClick(View v) {
                            opcionIncorrecta();

                            dinamicLayout[0] = (LinearLayout) aImagenAct[0].getParent();
                            dinamicLayout[0].setPadding(5, 5, 5, 5);
                            dinamicLayout[0].setBackgroundColor(Color.RED);

                            mpNope.start();
                        }
                    });
                    break;

                case "IVskippy":
                    imagenes.get(i).setBackgroundResource(R.drawable.skippy_el_mensajero_de_dios);

                    aImagenAct[1] = imagenes.get(i);

                    imagenes.get(i).setOnClickListener(new View.OnClickListener(){
                        @Override
                        public void onClick(View v) {
                            opcionCorrecta();

                            dinamicLayout[1] = (LinearLayout) aImagenAct[1].getParent();
                            dinamicLayout[1].setPadding(5, 5, 5, 5);
                            dinamicLayout[1].setBackgroundColor(Color.GREEN);

                            mpGut.start();
                        }
                    });
                    break;

                case "IVgabriel":
                    imagenes.get(i).setBackgroundResource(R.drawable.arcangel_gabriel);

                    aImagenAct[2] = imagenes.get(i);

                    imagenes.get(i).setOnClickListener(new View.OnClickListener(){
                        @Override
                        public void onClick(View v) {
                            opcionIncorrecta();

                            dinamicLayout[2] = (LinearLayout) aImagenAct[2].getParent();
                            dinamicLayout[2].setPadding(5, 5, 5, 5);
                            dinamicLayout[2].setBackgroundColor(Color.RED);

                            mpNope.start();
                        }
                    });
                    break;

                case "IVisaias":
                    imagenes.get(i).setBackgroundResource(R.drawable.isaias_profeta);

                    aImagenAct[3] = imagenes.get(i);

                    imagenes.get(i).setOnClickListener(new View.OnClickListener(){
                        @Override
                        public void onClick(View v) {
                            opcionIncorrecta();

                            dinamicLayout[3] = (LinearLayout) aImagenAct[3].getParent();
                            dinamicLayout[3].setPadding(5, 5, 5, 5);
                            dinamicLayout[3].setBackgroundColor(Color.RED);

                            mpNope.start();
                        }
                    });
                    break;
            }


        }

    }

    private void opcionIncorrecta(){
        Toast.makeText(MainActivity.this, "¡Incorrecta!", Toast.LENGTH_SHORT).show();

        for (int i = 0; i < IdsIV.size(); i++){
            imagenes.get(i).getBackground().setColorFilter(Color.parseColor("#7A7A7A"), PorterDuff.Mode.MULTIPLY);
            imagenes.get(i).setEnabled(false);
        }

        continuar.setEnabled(true);

    }

    private void opcionCorrecta(){
        aciertos++;

        Toast.makeText(MainActivity.this, "¡Correcta!", Toast.LENGTH_SHORT).show();

        for (int i = 0; i < IdsIV.size(); i++){
            imagenes.get(i).getBackground().setColorFilter(Color.parseColor("#7A7A7A"), PorterDuff.Mode.MULTIPLY);
            imagenes.get(i).setEnabled(false);
        }

        continuar.setEnabled(true);

        pregAct++;

    }

    private void cleanChilds(LinearLayout containerpapi){

        for ( int i = 0; i < containerpapi.getChildCount();  i++ ){
            View view = containerpapi.getChildAt(i);
            view.setVisibility(View.GONE); // Or whatever you want to do with the view.
        }

    }

    private void colocarPreguntas(TextView textPreguntas){

        textPreguntas.setText(alPreguntas.get(pregAct - 1));

        /*
        // esto se ha utilizado con layouts y funciona

        TextView pregunta = new TextView(this);
        pregunta.setText("text");

        containerPreguntas.addView(pregunta);
        containerPreguntas.invalidate();
        */
    }

    private void colocarRespuestas(LinearLayout containerRespuestas){

        findViewById(R.id.layoutBotones).setVisibility(View.VISIBLE);

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
}