package com.example.duolingohacendado;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.text.Layout;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    LinearLayout layout;
    MediaPlayer mpGut, mpNope;

    int posicion = 0;

    List<String> IdsIV = new ArrayList<String>(Arrays.asList("IVmoises", "IVskippy", "IVgabriel", "IVisaias"));

    List<ImageView> imagenes = new ArrayList<ImageView>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mpGut = MediaPlayer.create(this, R.raw.sabe_una_cosa);
        mpNope = MediaPlayer.create(this, R.raw.sabe_una_cosa);

        /*mezclamos las respuestas*/

        Collections.shuffle(IdsIV);

        //recogemos las imágenes a pelo (no hay otra forma)

        imagenes.add(findViewById(R.id.IV1));
        imagenes.add(findViewById(R.id.IV2));
        imagenes.add(findViewById(R.id.IV3));
        imagenes.add(findViewById(R.id.IV4));

        for (int i = 0; i < IdsIV.size(); i++){
            switch (IdsIV.get(i)){

                case "IVmoises":
                    imagenes.get(i).setBackgroundResource(R.drawable.moises);
                    imagenes.get(i).setOnClickListener(new View.OnClickListener(){
                        @Override
                        public void onClick(View v) {
                            opcionIncorrecta();
                            switch (IdsIV.indexOf("IVmoises")){

                                case 0:
                                    findViewById(R.id.LIV1).setPadding(5, 5, 5, 5);
                                    findViewById(R.id.LIV1).setBackgroundColor(Color.RED);
                                    break;

                                case 1:
                                    findViewById(R.id.LIV2).setPadding(5, 5, 5, 5);
                                    findViewById(R.id.LIV2).setBackgroundColor(Color.RED);
                                    break;

                                case 2:
                                    findViewById(R.id.LIV3).setPadding(5, 5, 5, 5);
                                    findViewById(R.id.LIV3).setBackgroundColor(Color.RED);
                                    break;

                                case 3:
                                    findViewById(R.id.LIV4).setPadding(5, 5, 5, 5);
                                    findViewById(R.id.LIV4).setBackgroundColor(Color.RED);
                                    break;
                            }

                            mpNope.start();
                        }
                    });
                    break;

                case "IVskippy":
                    imagenes.get(i).setBackgroundResource(R.drawable.skippy_el_mensajero_de_dios);
                    imagenes.get(i).setOnClickListener(new View.OnClickListener(){
                        @Override
                        public void onClick(View v) {
                            opcionCorrecta();
                            switch (IdsIV.indexOf("IVskippy")){

                                case 0:
                                    findViewById(R.id.LIV1).setPadding(5, 5, 5, 5);
                                    findViewById(R.id.LIV1).setBackgroundColor(Color.GREEN);
                                    break;

                                case 1:
                                    findViewById(R.id.LIV2).setPadding(5, 5, 5, 5);
                                    findViewById(R.id.LIV2).setBackgroundColor(Color.GREEN);
                                    break;

                                case 2:
                                    findViewById(R.id.LIV3).setPadding(5, 5, 5, 5);
                                    findViewById(R.id.LIV3).setBackgroundColor(Color.GREEN);
                                    break;

                                case 3:
                                    findViewById(R.id.LIV4).setPadding(5, 5, 5, 5);
                                    findViewById(R.id.LIV4).setBackgroundColor(Color.GREEN);
                                    break;
                            }
                            mpGut.start();
                        }
                    });
                    break;

                case "IVgabriel":
                    imagenes.get(i).setBackgroundResource(R.drawable.arcangel_gabriel);
                    imagenes.get(i).setOnClickListener(new View.OnClickListener(){
                        @Override
                        public void onClick(View v) {
                            opcionIncorrecta();
                            switch (IdsIV.indexOf("IVgabriel")){

                                case 0:
                                    findViewById(R.id.LIV1).setPadding(5, 5, 5, 5);
                                    findViewById(R.id.LIV1).setBackgroundColor(Color.RED);
                                    break;

                                case 1:
                                    findViewById(R.id.LIV2).setPadding(5, 5, 5, 5);
                                    findViewById(R.id.LIV2).setBackgroundColor(Color.RED);
                                    break;

                                case 2:
                                    findViewById(R.id.LIV3).setPadding(5, 5, 5, 5);
                                    findViewById(R.id.LIV3).setBackgroundColor(Color.RED);
                                    break;

                                case 3:
                                    findViewById(R.id.LIV4).setPadding(5, 5, 5, 5);
                                    findViewById(R.id.LIV4).setBackgroundColor(Color.RED);
                                    break;
                            }
                            mpNope.start();
                        }
                    });
                    break;

                case "IVisaias":
                    imagenes.get(i).setBackgroundResource(R.drawable.isaias_profeta);
                    imagenes.get(i).setOnClickListener(new View.OnClickListener(){
                        @Override
                        public void onClick(View v) {
                            opcionIncorrecta();
                            switch (IdsIV.indexOf("IVisaias")){

                                case 0:
                                    findViewById(R.id.LIV1).setPadding(5, 5, 5, 5);
                                    findViewById(R.id.LIV1).setBackgroundColor(Color.RED);
                                    break;

                                case 1:
                                    findViewById(R.id.LIV2).setPadding(5, 5, 5, 5);
                                    findViewById(R.id.LIV2).setBackgroundColor(Color.RED);
                                    break;

                                case 2:
                                    findViewById(R.id.LIV3).setPadding(5, 5, 5, 5);
                                    findViewById(R.id.LIV3).setBackgroundColor(Color.RED);
                                    break;

                                case 3:
                                    findViewById(R.id.LIV4).setPadding(5, 5, 5, 5);
                                    findViewById(R.id.LIV4).setBackgroundColor(Color.RED);
                                    break;
                            }
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
            imagenes.get(i).setEnabled(false);
            imagenes.get(i).setColorFilter(Color.argb(150,0,255,0));
        }

    }

    private void opcionCorrecta(){
        Toast.makeText(MainActivity.this, "¡Correcta!", Toast.LENGTH_SHORT).show();

        for (int i = 0; i < IdsIV.size(); i++){
            imagenes.get(i).setEnabled(false);
            imagenes.get(i).setColorFilter(Color.argb(150,0,255,0));
        }

    }

    private void borrarListener(List<ImageView> imagenes){

    }
}