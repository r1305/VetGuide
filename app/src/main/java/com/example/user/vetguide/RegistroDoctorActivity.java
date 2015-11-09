package com.example.user.vetguide;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.SaveCallback;

public class RegistroDoctorActivity extends AppCompatActivity {

    Button butSiguiente, butCancelar;
    EditText nombredoctor, especialidad, diainicio, diafin, horainicio, horafin;
    String codVet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_doctor);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        butSiguiente=(Button)findViewById(R.id.buttonSiguiente6);


        nombredoctor = (EditText) findViewById(R.id.eteNombreDoctor);
        especialidad = (EditText) findViewById(R.id.eteEspecialidadDoctor);
        diainicio = (EditText) findViewById(R.id.eteDiaInicio);
        diafin = (EditText) findViewById(R.id.eteDiaFin);
        horainicio = (EditText) findViewById(R.id.eteHoraInicio);
        horafin = (EditText) findViewById(R.id.eteHoraFin);

        butSiguiente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i0 = getIntent();
                codVet = i0.getStringExtra("codVet");



                ParseObject doctor = new ParseObject("Doctor");
                doctor.put("nombre", nombredoctor.getText().toString());
                doctor.put("especialidad", especialidad.getText().toString());
                doctor.put("horario_atencion", diainicio.getText().toString() + "-" + diafin.getText().toString());
                doctor.put("tiempo_atencion", horainicio.getText().toString() + "-" + horafin.getText().toString());
                doctor.put("contratoVeterinaria", codVet);
                doctor.saveInBackground(new SaveCallback() {
                    @Override
                    public void done(ParseException e) {
                        if (e == null) {
                            Toast t = Toast.makeText(getBaseContext(), "Registro de Doctor Correcto", Toast.LENGTH_SHORT);
                            t.show();
                            Intent i = new Intent(RegistroDoctorActivity.this, MainActivity.class);
                            startActivity(i);
                        } else {
                            Toast t = Toast.makeText(getBaseContext(), "Registro de Doctor Incorrecto", Toast.LENGTH_SHORT);
                            t.show();
                        }

                    }
                });


            }
        });

        butCancelar=(Button)findViewById(R.id.buttonCancelar6);
        butCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(RegistroDoctorActivity.this, RegistroServiciosActivity.class);
                startActivity(i);
            }
        });



    }

}
