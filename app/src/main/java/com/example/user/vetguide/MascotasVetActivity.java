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
import android.widget.TextView;
import android.widget.Toast;

import com.parse.GetCallback;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.SaveCallback;

import org.json.simple.JSONArray;

import java.text.ParseException;
import java.util.List;

public class MascotasVetActivity extends AppCompatActivity {

    Button butSiguiente, butCancelar;
    CheckBox cb1,cb2,cb3,cb4,cb5,cb6,cb7,cb8,cb9,cb10,cb11,cb12,cb13,cb14;
    TextView tviSeleccionarTodos;
    String codigovet;
    // String mascotasparaatender;
    // List<String> listamascotasatencion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mascotas_vet);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        butSiguiente=(Button)findViewById(R.id.buttonSiguiente4);

        cb1=(CheckBox)findViewById(R.id.cbAracnidos);
        cb2=(CheckBox)findViewById(R.id.cbAuquenidos);
        cb3=(CheckBox)findViewById(R.id.cbAves);
        cb4=(CheckBox)findViewById(R.id.cbCaninos);
        cb5=(CheckBox)findViewById(R.id.cbChanchos);
        cb6=(CheckBox)findViewById(R.id.cbConejos);
        cb7=(CheckBox)findViewById(R.id.cbErizos);
        cb8=(CheckBox)findViewById(R.id.cbFelinos);
        cb9=(CheckBox)findViewById(R.id.cbIguanas);
        cb10=(CheckBox)findViewById(R.id.cbMonos);
        cb11=(CheckBox)findViewById(R.id.cbPeces);
        cb12=(CheckBox)findViewById(R.id.cbRoedores);
        cb13=(CheckBox)findViewById(R.id.cbTortugas);
        cb14=(CheckBox)findViewById(R.id.cbSerpientes);

        butSiguiente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent i0 = getIntent();
                codigovet =  i0.getStringExtra("codigovet");

                ParseQuery<ParseObject> query = ParseQuery.getQuery("Veterinaria");

                // Retrieve the object by id
                query.getInBackground(codigovet, new GetCallback<ParseObject>() {
                    @Override
                    public void done(ParseObject object, com.parse.ParseException e) {
                        if (e == null) {


                            JSONArray listamascotasatencion = new JSONArray();
                            if (cb1.isChecked()) {
                                // mascotasparaatender = mascotasparaatender + cb1.getText().toString() + ",";
                                //  mascotasparaatender = cb1.getText().toString();
                                listamascotasatencion.add(cb1.getText().toString());
                            }

                            if (cb2.isChecked()) {
                                //  mascotasparaatender = mascotasparaatender + cb2.getText().toString() + ",";
                                // mascotasparaatender = cb2.getText().toString();
                                listamascotasatencion.add(cb2.getText().toString());
                            }

                            if (cb3.isChecked()) {
                                // mascotasparaatender = mascotasparaatender + cb3.getText().toString() + ",";
                                // mascotasparaatender = cb3.getText().toString();
                                listamascotasatencion.add(cb3.getText().toString());
                            }


                            if (cb4.isChecked()) {
                                //  mascotasparaatender = mascotasparaatender + cb4.getText().toString() + ",";
                                // mascotasparaatender = cb4.getText().toString();
                                listamascotasatencion.add(cb4.getText().toString());
                            }

                            if (cb5.isChecked()) {
                                //  mascotasparaatender = mascotasparaatender + cb5.getText().toString() + ",";
                                // mascotasparaatender = cb5.getText().toString();
                                listamascotasatencion.add(cb5.getText().toString());
                            }

                            if (cb6.isChecked()) {
                                //  mascotasparaatender = mascotasparaatender + cb6.getText().toString() + ",";
                                // mascotasparaatender = cb6.getText().toString();
                                listamascotasatencion.add(cb6.getText().toString());
                            }

                            if (cb7.isChecked()) {
                                //  mascotasparaatender = mascotasparaatender + cb7.getText().toString() + ",";
                                // mascotasparaatender = cb7.getText().toString();
                                listamascotasatencion.add(cb7.getText().toString());
                            }

                            if (cb8.isChecked()) {
                                //  mascotasparaatender = mascotasparaatender + cb8.getText().toString() + ",";
                                //  mascotasparaatender = cb8.getText().toString();
                                listamascotasatencion.add(cb8.getText().toString());
                            }

                            if (cb9.isChecked()) {
                                //    mascotasparaatender = mascotasparaatender + cb9.getText().toString() + ",";
                                // mascotasparaatender = cb9.getText().toString();
                                listamascotasatencion.add(cb9.getText().toString());
                            }

                            if (cb10.isChecked()) {
                                //   mascotasparaatender = mascotasparaatender + cb10.getText().toString() + ",";
                                //  mascotasparaatender = cb10.getText().toString();
                                listamascotasatencion.add(cb10.getText().toString());
                            }

                            if (cb11.isChecked()) {
                                // mascotasparaatender = mascotasparaatender + cb11.getText().toString() + ",";
                                // mascotasparaatender = cb11.getText().toString();
                                listamascotasatencion.add(cb11.getText().toString());
                            }

                            if (cb12.isChecked()) {
                                //   mascotasparaatender = mascotasparaatender + cb12.getText().toString() + ",";
                                // mascotasparaatender = cb12.getText().toString();
                                listamascotasatencion.add(cb12.getText().toString());
                            }

                            if (cb13.isChecked()) {
                                // mascotasparaatender = mascotasparaatender + cb13.getText().toString() + ",";
                                // mascotasparaatender = cb13.getText().toString();
                                listamascotasatencion.add(cb13.getText().toString());
                            }

                            if (cb14.isChecked()) {
                                // mascotasparaatender = mascotasparaatender + cb14.getText().toString() + ",";
                                //  mascotasparaatender = cb14.getText().toString();
                                listamascotasatencion.add(cb14.getText().toString());
                            }


                            //object.put("mascotasAtendidas",mascotasparaatender);

                            object.addAllUnique("TipoMascota", listamascotasatencion);

                            object.saveInBackground(new SaveCallback() {

                                @Override
                                public void done(com.parse.ParseException e) {
                                    if (e == null) {
                                        Intent i = new Intent(MascotasVetActivity.this, RegistroServiciosActivity.class);
                                        i.putExtra("codigoveterinaria", codigovet);
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


        butCancelar=(Button)findViewById(R.id.buttonCancelar4);
        butCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MascotasVetActivity.this, RegistroActivity.class);
                startActivity(i);
            }
        });

        cb1=(CheckBox)findViewById(R.id.cbAracnidos);
        cb2=(CheckBox)findViewById(R.id.cbAuquenidos);
        cb3=(CheckBox)findViewById(R.id.cbAves);
        cb4=(CheckBox)findViewById(R.id.cbCaninos);
        cb5=(CheckBox)findViewById(R.id.cbChanchos);
        cb6=(CheckBox)findViewById(R.id.cbConejos);
        cb7=(CheckBox)findViewById(R.id.cbErizos);
        cb8=(CheckBox)findViewById(R.id.cbFelinos);
        cb9=(CheckBox)findViewById(R.id.cbIguanas);
        cb10=(CheckBox)findViewById(R.id.cbMonos);
        cb11=(CheckBox)findViewById(R.id.cbPeces);
        cb12=(CheckBox)findViewById(R.id.cbRoedores);
        cb13=(CheckBox)findViewById(R.id.cbTortugas);
        cb14=(CheckBox)findViewById(R.id.cbSerpientes);

        tviSeleccionarTodos=(TextView)findViewById(R.id.tviSeleccionarTodas);
        tviSeleccionarTodos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cb1.setChecked(true);
                cb2.setChecked(true);
                cb3.setChecked(true);
                cb4.setChecked(true);
                cb5.setChecked(true);
                cb6.setChecked(true);
                cb7.setChecked(true);
                cb8.setChecked(true);
                cb9.setChecked(true);
                cb10.setChecked(true);
                cb11.setChecked(true);
                cb12.setChecked(true);
                cb13.setChecked(true);
                cb14.setChecked(true);

            }
        });



    }

}
