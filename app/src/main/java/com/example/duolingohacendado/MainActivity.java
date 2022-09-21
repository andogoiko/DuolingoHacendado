package com.example.duolingohacendado;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ImageView imagen;

    List<String> IdsIV = new ArrayList<String>(Arrays.asList("IVmoises", "IVskippy", "IVgabriel", "IVisaias"));



    List<ImageView> imagenes = new ArrayList<ImageView>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*mezclamos las respuestas*/

        Collections.shuffle(IdsIV);

        //recogemos las imágenes a pelo (no hay otra forma)

        imagenes.add(findViewById(R.id.IV1));
        imagenes.add(findViewById(R.id.IV2));
        imagenes.add(findViewById(R.id.IV3));
        imagenes.add(findViewById(R.id.IV4));

        for (int i = 0; i < 4; i++){
            switch (IdsIV.get(i)){

                case "IVmoises":
                    imagenes.get(i).setBackgroundResource(R.drawable.moises);
                    break;

                case "IVskippy":
                    imagenes.get(i).setBackgroundResource(R.drawable.skippy_el_mensajero_de_dios);
                    break;

                case "IVgabriel":
                    imagenes.get(i).setBackgroundResource(R.drawable.arcangel_gabriel);
                    break;

                case "IVisaias":
                    imagenes.get(i).setBackgroundResource(R.drawable.isaias_profeta);
                    break;
            }

            if (IdsIV.get(i) == "IVskippy"){
                imagenes.get(i).setOnClickListener(new View.OnClickListener(){
                    @Override
                    public void onClick(View v) {
                        opcionCorrecta();
                    }
                });
            }else{
                imagenes.get(i).setOnClickListener(new View.OnClickListener(){
                    @Override
                    public void onClick(View v) {
                        opcionIncorrecta();
                    }
                });
            }


        }

    }

    private void opcionIncorrecta(){
        Toast.makeText(MainActivity.this, "¡Incorrecta!", Toast.LENGTH_LONG).show();
    }

    private void opcionCorrecta(){
        Toast.makeText(MainActivity.this, "¡Correcta!", Toast.LENGTH_LONG).show();

    }

    private void borrarListener(List<ImageView> imagenes){

    }
}