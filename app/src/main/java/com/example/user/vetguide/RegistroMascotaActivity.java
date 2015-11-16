package com.example.user.vetguide;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

public class RegistroMascotaActivity extends AppCompatActivity {

    String apoderado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_mascota);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        final GridView gridview = (GridView) findViewById(R.id.gridView);
        final ImageAdapter iamd=new ImageAdapter(this);
        gridview.setAdapter(iamd);

        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {
            Intent i = new Intent(RegistroMascotaActivity.this,RegistrarDatosMascotaActivity.class);
            i.putExtra("mascota",iamd.mThumbIds[position]);
            i.putExtra("tipoMascota", tipoMascota[position]);
            startActivity(i);
            }
        });
    }

    public String[] tipoMascota = {
            "Can", "Felino",
            "Ave", "Conejo",
            "Iguana", "Mono",
            "Serpiente", "Roedor",
            "Araña", "Pez",
            "Tortuga", "Auquenido",
            "Chancho", "Erizo"
    };

}
