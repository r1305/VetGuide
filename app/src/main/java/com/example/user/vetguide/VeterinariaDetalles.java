package com.example.user.vetguide;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.List;

public class VeterinariaDetalles extends AppCompatActivity {

    TextView nombrevetdetalle,direccionvetdetalle,telefonovetdetalle,horariovetdetalle, nombredocdetalle,especialidaddocdetalle,
            diasatenciondocdetalle,horasatenciondocdetalle;
    String codigovetdetalle;
    Button pedircita, pedirservicio;
    String nombrevet;
    String idUsuario;
    JSONArray serv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detella_veterinaria);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(R.drawable.menu);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        toolbar.animate();
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i=new Intent(VeterinariaDetalles.this,Inicio.class);
                startActivity(i);
            }
        });

        nombrevetdetalle = (TextView)findViewById(R.id.tviNombreVet);
        direccionvetdetalle = (TextView)findViewById(R.id.tviDireccionVet);
        telefonovetdetalle = (TextView)findViewById(R.id.tviTelefonoVet);
        horariovetdetalle = (TextView)findViewById(R.id.tviHorarioAttVet);

        nombredocdetalle = (TextView)findViewById(R.id.tviNombreDoc);
        especialidaddocdetalle =(TextView)findViewById(R.id.tviEspecialidadDoc);
        horasatenciondocdetalle = (TextView)findViewById(R.id.tviHorasAtencionDoc);
        diasatenciondocdetalle =(TextView)findViewById(R.id.tviDiasAtencionDoc);

        Intent i0 = getIntent();
        codigovetdetalle = i0.getStringExtra("idvet");
        idUsuario = i0.getStringExtra("idUsuario");

        ParseQuery<ParseObject> query = ParseQuery.getQuery("Veterinaria");


        query.whereEqualTo("objectId", codigovetdetalle);
        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> objects, com.parse.ParseException e) {
                if (e == null) {
                    for (ParseObject o : objects) {
                        nombrevet = o.getString("nombre");
                        String direccionvet = o.getString("direccion");
                        String distritovet = o.getString("distrito");
                        Number telefonovet = o.getNumber("telefono");
                        String horasvet = o.getString("horas_atencion");
                        nombrevetdetalle.setText(nombrevet);
                        direccionvetdetalle.setText(direccionvet);
                        telefonovetdetalle.setText(telefonovet.toString());
                        horariovetdetalle.setText(horasvet);
                        serv=o.getJSONArray("ServiciosBrindados");
                    }
                }
            }
        });




        ParseQuery<ParseObject> query2 = ParseQuery.getQuery("Doctor");
        query2.whereEqualTo("contratoVeterinaria", codigovetdetalle);
        query2.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> objects, com.parse.ParseException e) {
                if (e == null) {
                    for (ParseObject o2 : objects) {
                        String nombredoc = o2.getString("nombre");
                        String especialidaddoc = o2.getString("especialidad");
                        String horariodoc = o2.getString("horario_atencion");
                        String tiempodoc = o2.getString("tiempo_atencion");

                        nombredocdetalle.setText(nombredoc);
                        especialidaddocdetalle.setText(especialidaddoc);
                        horasatenciondocdetalle.setText(horariodoc);
                        diasatenciondocdetalle.setText(tiempodoc);
                    }
                }
            }
        });

        pedircita = (Button)findViewById(R.id.buttonPedirCita);
        pedircita.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i= new Intent(VeterinariaDetalles.this , PedirCitaActivity.class);
                i.putExtra("nombreVetCita",nombrevet);
                i.putExtra("idVet",codigovetdetalle);
                i.putExtra("idUsuario",idUsuario);
                startActivity(i);
            }
        });



        pedirservicio = (Button)findViewById(R.id.buttonServicios);
        pedirservicio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for(int i=0;i<serv.length();i++){
                    try {
                        Toast.makeText(VeterinariaDetalles.this,serv.get(i).toString(),Toast.LENGTH_SHORT).show();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }


            }
        });




    }

}

