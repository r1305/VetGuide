package com.example.user.vetguide;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseUser;
import com.parse.SaveCallback;

public class PedirCitaActivity extends AppCompatActivity {
    TextView tviNombreVet;
    Button butReservar, butCancelar;
    EditText eteDiaCita,eteHoraCita;
    String idvet;
    String idUsuario;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pedir_cita);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        tviNombreVet=(TextView)findViewById(R.id.tviNombreVetCita);
        ParseUser u=ParseUser.getCurrentUser();
        Intent i=getIntent();
        String nomb=i.getStringExtra("nombreVetCita");
        idUsuario = u.getObjectId();
        idvet = i.getStringExtra("idVet");

        tviNombreVet.setText(nomb);

        butReservar=(Button)findViewById(R.id.buttonReservar);
        eteDiaCita=(EditText)findViewById(R.id.eteDiaCita);
        eteHoraCita=(EditText)findViewById(R.id.eteHoraCita);
        butReservar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String diaCita=eteDiaCita.getText().toString();
                String horaCita=eteHoraCita.getText().toString();
                ParseObject cita = new ParseObject("Cita");
                cita.put("horaCita",horaCita);
                cita.put("diaCita",diaCita);
                cita.put("idVeterinaria", idvet);
                cita.put("idUsuario", idUsuario);
                cita.saveInBackground(new SaveCallback() {
                    @Override
                    public void done(ParseException e) {
                        if (e == null) {
                            Toast t = Toast.makeText(PedirCitaActivity.this, "Reserva realizada", Toast.LENGTH_SHORT);
                            t.show();
                            Intent i2 = new Intent(PedirCitaActivity.this, Inicio.class);
                            startActivity(i2);
                        }
                    }
                });
            }
        });

        butCancelar=(Button)findViewById(R.id.buttonCancelar7);
        butCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast t = Toast.makeText(PedirCitaActivity.this, "Reserva cancelada", Toast.LENGTH_SHORT);
                t.show();
                Intent i3 = new Intent(PedirCitaActivity.this, VeterinariaDetalles.class);
                startActivity(i3);
            }
        });



    }

}
