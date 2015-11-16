package com.example.user.vetguide;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseUser;
import com.parse.SaveCallback;
import com.parse.SignUpCallback;

import org.json.JSONException;
import org.json.JSONObject;

import butterknife.Bind;
import clases.BitmapToByteArray;
import clases.DownloadImageTask;
import clases.Utils;

public class RegistrarUsuarioFragment extends Fragment {
    Button butSiguiente, butCancelar;
    EditText nombre, contra, correo,  apellido;
    ParseUser user;
    @Bind(R.id.imageView)
    ImageView img;

    private static final int PICK_IMAGE = 2000;



    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if ( requestCode == PICK_IMAGE) {
            Uri imageUri = data.getData();
            img.setImageURI(imageUri);
            Bitmap bitmap = ((BitmapDrawable) img.getDrawable()).getBitmap();
            new BitmapToByteArray().execute(bitmap);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.registrarusuario_fragment, container, false);
        img=(ImageView)view.findViewById(R.id.imageView);
        final Bitmap bitmap = ((BitmapDrawable) img.getDrawable()).getBitmap();
        new clases.BitmapToByteArray().execute(bitmap);
        img.setImageBitmap(bitmap);

        butSiguiente=(Button)view.findViewById(R.id.buttonSiguiente);

        nombre = (EditText)view.findViewById(R.id.eteNombreUsuario);
        contra = (EditText) view.findViewById(R.id.etePasswordUsuario);
        correo = (EditText)view.findViewById(R.id.eteCorreoUsuario);
        apellido = (EditText)view.findViewById(R.id.eteApellidoUsuario);

        butSiguiente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                user = new ParseUser();
                user.setEmail(correo.getText().toString());
                user.setPassword(contra.getText().toString());
                user.setUsername(correo.getText().toString());
                user.put("nombre",nombre.getText().toString());
                user.put("apellido", apellido.getText().toString());

                final ParseFile foto = new ParseFile("foto.png", Utils.imageBuffer);

                foto.saveInBackground(new SaveCallback() {
                    @Override
                    public void done(ParseException e) {
                        user.put("foto", foto);
                        user.signUpInBackground(new SignUpCallback() {
                            @Override
                            public void done(ParseException e) {
                                if (e == null) {


                                    Toast t = Toast.makeText(getContext(), "Registro de datos Correcto", Toast.LENGTH_SHORT);
                                    t.show();
                                    Intent i = new Intent(getActivity(), RegistroMascotaActivity.class);
                                    startActivity(i);
                                    getActivity().finish();


                                } else {
                                    Toast t = Toast.makeText(getContext(), "Registro de datos Incorrecto", Toast.LENGTH_SHORT);
                                    t.show();
                                    e.printStackTrace();

                                }
                            }
                        });
                    }
                });

            }
        });
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openGallery();
            }
        });

        butCancelar=(Button)view.findViewById(R.id.buttonCancelar);
        butCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getActivity(),MainActivity.class);
                startActivity(i);
                getActivity().finish();
            }
        });

        return view;

    }

    private void openGallery() {
        Intent gallery =
                new Intent(Intent.ACTION_PICK,
                        android.provider.MediaStore.Images.Media.INTERNAL_CONTENT_URI);
        startActivityForResult(gallery, PICK_IMAGE);
    }











}
