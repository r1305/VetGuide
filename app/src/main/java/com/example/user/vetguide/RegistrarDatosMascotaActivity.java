package com.example.user.vetguide;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
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
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseUser;
import com.parse.SaveCallback;

import butterknife.Bind;
import butterknife.ButterKnife;
import clases.BitmapToByteArray;
import clases.Utils;

public class RegistrarDatosMascotaActivity extends AppCompatActivity {

    ImageView masc;
    Button butSiguiente, butCancelar;
    String tipoMascota, sexo, apoderado2, username, pass;
    EditText nombremascota, colormascota, razamascota, edadmascota;
    boolean esterilizada, alergica;
    RadioButton macho, hembra;
    CheckBox esteril, alergia;
    Integer edad;
    private static final int PICK_IMAGE = 2000;

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if ( requestCode == PICK_IMAGE) {
            Uri imageUri = data.getData();
            masc.setImageURI(imageUri);
            Bitmap bitmap = ((BitmapDrawable) masc.getDrawable()).getBitmap();
            new BitmapToByteArray().execute(bitmap);
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_registrar_datos_mascota);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Intent intento = getIntent();
        int mascota = intento.getIntExtra("mascota", R.drawable.iconoperruno);

        tipoMascota = intento.getStringExtra("tipoMascota");

        masc = (ImageView) findViewById(R.id.imageMascota);
        masc.setImageResource(mascota);
        final Bitmap bitmap = ((BitmapDrawable) masc.getDrawable()).getBitmap();
        new clases.BitmapToByteArray().execute(bitmap);
        masc.setImageBitmap(bitmap);
        masc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openGallery();

            }
        });

        butSiguiente = (Button) findViewById(R.id.buttonSiguiente2);
        butSiguiente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                apoderado2 = ParseUser.getCurrentUser().getObjectId();

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
                final ParseFile foto = new ParseFile("foto.png", Utils.imageBuffer);


                final ParseObject mascota = new ParseObject("Mascota");

                mascota.put("nombre", nombremascota.getText().toString());
                mascota.put("color", colormascota.getText().toString());
                mascota.put("raza", razamascota.getText().toString());
                mascota.put("categoria", tipoMascota);
                mascota.put("edad", Integer.parseInt(edadmascota.getText().toString()));


                mascota.put("Apoderado", apoderado2);
                mascota.put("esteril", esterilizada);
                mascota.put("sexo", sexo);
                mascota.put("alergia", alergica);
                foto.saveInBackground(new SaveCallback() {
                    @Override
                    public void done(ParseException e) {
                        if (e == null){
                            mascota.put("foto", foto);
                            mascota.saveInBackground(new SaveCallback() {
                            @Override
                            public void done(ParseException e) {
                                if (e == null) {
                                    Toast t = Toast.makeText(RegistrarDatosMascotaActivity.this, "Registro de mascota Correcta", Toast.LENGTH_SHORT);
                                    t.show();
                                    Intent t1 = new Intent(RegistrarDatosMascotaActivity.this, DetallePerfil.class);
                                    startActivity(t1);
                                } else {
                                    Toast t = Toast.makeText(RegistrarDatosMascotaActivity.this, "Registro de mascota Incorrecto" + e.toString(), Toast.LENGTH_SHORT);
                                    t.show();
                                    e.printStackTrace();
                                }
                            }
                        });
                    }
                    }
                });



            }
        });

        butCancelar = (Button) findViewById(R.id.buttonCancelar2);
        butCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(RegistrarDatosMascotaActivity.this, RegistroMascotaActivity.class);
                startActivity(i);
            }
        });



    }

    private void openGallery() {
        Intent gallery =
                new Intent(Intent.ACTION_PICK,
                        android.provider.MediaStore.Images.Media.INTERNAL_CONTENT_URI);
        startActivityForResult(gallery, PICK_IMAGE);
    }



}
