package com.example.user.vetguide;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.SaveCallback;

import org.json.simple.JSONArray;

public class RegistroServiciosActivity extends AppCompatActivity {

    Button butSiguiente, butCancelar;
    String codigovet;

    // String serviciosaBrindar

    CheckBox cb1,cb2,cb3,cb4,cb5,cb6,cb7,cb8,cb9;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_servicios);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        butSiguiente=(Button)findViewById(R.id.buttonSiguiente5);

        cb1=(CheckBox)findViewById(R.id.cbBanios);
        cb2=(CheckBox)findViewById(R.id.cbCorte);
        cb3=(CheckBox)findViewById(R.id.cbOdontologia);
        cb4=(CheckBox)findViewById(R.id.cbMaternidad);
        cb5=(CheckBox)findViewById(R.id.cbCirugia);
        cb6=(CheckBox)findViewById(R.id.cbMedicina);
        cb7=(CheckBox)findViewById(R.id.cbRayos);
        cb8=(CheckBox)findViewById(R.id.cbVacunaciones);
        cb9=(CheckBox)findViewById(R.id.cbEndoscopia);

        butSiguiente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent i0 = getIntent();
                codigovet =  i0.getStringExtra("codigoveterinaria");



                ParseQuery<ParseObject> query = ParseQuery.getQuery("Veterinaria");

                // Retrieve the object by id
                query.getInBackground(codigovet, new GetCallback<ParseObject>() {
                    @Override
                    public void done(ParseObject object, com.parse.ParseException e) {
                        if (e == null) {

                            JSONArray serviciosbrindados = new JSONArray();
                            if (cb1.isChecked()) {
                                // serviciosaBrindar = serviciosaBrindar + cb1.getText().toString() + ",";
                                //serviciosaBrindar = cb1.getText().toString();
                                serviciosbrindados.add(cb1.getText().toString());
                            }

                            if (cb2.isChecked()) {
                                //  serviciosaBrindar = serviciosaBrindar + cb2.getText().toString() + ",";
                                // serviciosaBrindar = cb2.getText().toString();
                                serviciosbrindados.add(cb2.getText().toString());
                            }

                            if (cb3.isChecked()) {
                                //  serviciosaBrindar = serviciosaBrindar + cb3.getText().toString() + ",";
                                // serviciosaBrindar = cb3.getText().toString();
                                serviciosbrindados.add(cb3.getText().toString());
                            }

                            if (cb4.isChecked()) {
                                //serviciosaBrindar = serviciosaBrindar + cb4.getText().toString() + ",";
                                // serviciosaBrindar = cb4.getText().toString();
                                serviciosbrindados.add(cb4.getText().toString());
                            }

                            if (cb5.isChecked()) {
                                // serviciosaBrindar = serviciosaBrindar + cb5.getText().toString() + ",";
                                // serviciosaBrindar = cb5.getText().toString();
                                serviciosbrindados.add(cb5.getText().toString());
                            }

                            if (cb6.isChecked()) {
                                //  serviciosaBrindar = serviciosaBrindar + cb6.getText().toString() + ",";
                                // serviciosaBrindar = cb6.getText().toString();
                                serviciosbrindados.add(cb6.getText().toString());
                            }

                            if (cb7.isChecked()) {
                                // serviciosaBrindar = serviciosaBrindar + cb7.getText().toString() + ",";
                                // serviciosaBrindar = cb7.getText().toString();
                                serviciosbrindados.add(cb7.getText().toString());
                            }

                            if (cb8.isChecked()) {
                                // serviciosaBrindar = serviciosaBrindar + cb8.getText().toString() + ",";
                                // serviciosaBrindar = cb8.getText().toString();
                                serviciosbrindados.add(cb8.getText().toString());
                            }

                            if (cb9.isChecked()) {
                                //  serviciosaBrindar = serviciosaBrindar + cb9.getText().toString() + ",";
                                // serviciosaBrindar = cb9.getText().toString();
                                serviciosbrindados.add(cb9.getText().toString());
                            }


                            //  object.put("serviciosBrindados",serviciosaBrindar);

                            object.addAllUnique("ServiciosBrindados", serviciosbrindados);

                            object.saveInBackground(new SaveCallback() {
                                @Override
                                public void done(ParseException e) {
                                    if (e == null) {
                                        Intent i = new Intent(RegistroServiciosActivity.this, RegistroDoctorActivity.class);
                                        i.putExtra("codVet", codigovet);
                                        startActivity(i);
                                    } else {
                                        Toast t = Toast.makeText(getBaseContext(), "No se puede agregar la lista", Toast.LENGTH_SHORT);
                                        t.show();
                                    }

                                }
                            });
                        }
                    }


                });



            }
        });

        butCancelar=(Button)findViewById(R.id.buttonCancelar5);
        butCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(RegistroServiciosActivity.this, MascotasVetActivity.class);
                startActivity(i);
            }
        });


    }

}
