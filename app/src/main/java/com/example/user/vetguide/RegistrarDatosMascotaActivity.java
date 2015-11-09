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
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Toast;

import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseUser;
import com.parse.SaveCallback;

public class RegistrarDatosMascotaActivity extends AppCompatActivity {

    ImageView masc;
    Button butSiguiente, butCancelar;
    String tipoMascota, sexo, apoderado2,username,pass;
    EditText nombremascota,colormascota,razamascota,edadmascota;
    boolean esterilizada,alergica;
    RadioButton macho,hembra;
    CheckBox esteril, alergia;
    Integer edad;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar_datos_mascota);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Intent intento=getIntent();
        int mascota=intento.getIntExtra("mascota",R.drawable.iconoperruno);

        tipoMascota=intento.getStringExtra("tipoMascota");

        masc=(ImageView)findViewById(R.id.imageMascota);
        masc.setImageResource(mascota);

        butSiguiente=(Button)findViewById(R.id.buttonSiguiente2);
        butSiguiente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i2 = getIntent();

                apoderado2 = ParseUser.getCurrentUser().getObjectId();

                //i2.getStringExtra("usuario");

                nombremascota = (EditText) findViewById(R.id.eteNombreMascota);
                colormascota = (EditText) findViewById(R.id.eteDolorMascota);
                razamascota = (EditText) findViewById(R.id.eteRazaMascota);
                edadmascota = (EditText) findViewById(R.id.eteEdadMascota);

                macho = (RadioButton) findViewById(R.id.radioMacho);
                hembra = (RadioButton) findViewById(R.id.radioHembra);

                if (macho.isChecked()) {
                    sexo = macho.getText().toString();
                } else if (hembra.isChecked()) {
                    sexo = hembra.getText().toString();
                }

                esteril = (CheckBox) findViewById(R.id.checkEsteril);
                alergia = (CheckBox) findViewById(R.id.checkAlergias);

                if (esteril.isChecked()) {
                    esterilizada = true;
                } else {
                    esterilizada = false;
                }

                if (alergia.isChecked()) {
                    alergica = true;
                } else {
                    alergica = false;
                }


                ParseObject mascota = new ParseObject("Mascota");

                mascota.put("nombre", nombremascota.getText().toString());
                mascota.put("color", colormascota.getText().toString());
                mascota.put("raza", razamascota.getText().toString());
                mascota.put("categoria", tipoMascota);
                mascota.put("edad", Integer.parseInt(edadmascota.getText().toString()));



                mascota.put("Apoderado", apoderado2);
                mascota.put("esteril", esterilizada);
                mascota.put("sexo", sexo);
                mascota.put("alergia", alergica);
                mascota.saveInBackground(new SaveCallback() {
                    @Override
                    public void done(ParseException e) {
                        if (e == null) {
                            Toast t = Toast.makeText(getBaseContext(), "Registro de mascota Correcta", Toast.LENGTH_SHORT);
                            t.show();
                            Intent t1 = new Intent(RegistrarDatosMascotaActivity.this, MainActivity.class);
                            startActivity(t1);
                        } else {
                            Toast t = Toast.makeText(getBaseContext(), "Registro de mascota Incorrecto", Toast.LENGTH_SHORT);
                            t.show();
                            e.printStackTrace();
                        }
                    }
                });



            }
        });

        butCancelar=(Button)findViewById(R.id.buttonCancelar2);
        butCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(RegistrarDatosMascotaActivity.this,RegistroMascotaActivity.class);
                startActivity(i);
            }
        });

    }

}
