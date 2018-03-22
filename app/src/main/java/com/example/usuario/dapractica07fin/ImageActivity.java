package com.example.usuario.dapractica07fin;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

public class ImageActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        String [] imagenes = getResources().getStringArray(R.array.imagenes);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image);


        Spinner spinner = (Spinner) findViewById(R.id.spinner);
        ArrayAdapter<String> adapter = new
                ArrayAdapter<String>(this,
                R.layout.spinner_layout, R.id.itemSpinner,
                imagenes);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);

    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView,
                               View view, int position, long l) {
        String [] imagenes = getResources().getStringArray(R.array.imagenes);
        ImageView imageView = (ImageView) findViewById(R.id.imageView2);
        int idImg = 0;
        Toast.makeText(getApplicationContext(),
                "Opcion " + position + "\n"+
                        imagenes[position], Toast.LENGTH_SHORT).show();
        switch (position){
            case 0:
                idImg = R.drawable.mujer;
                break;
            case 1:
                idImg = R.drawable.tigre;
                break;
            case 2:
                idImg = R.drawable.paisaj;
                break;
            case 3:
                idImg = R.drawable.control;
                break;
            case 4:
                idImg = R.drawable.bigote;
                break;
            default:
                idImg = android.R.drawable.ic_menu_agenda;
        }
        imageView.setImageResource(idImg);
    }
    @Override
    public void onNothingSelected(AdapterView<?>adapterView) {
    }



    }






