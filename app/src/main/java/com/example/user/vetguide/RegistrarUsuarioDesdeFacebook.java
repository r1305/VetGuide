package com.example.user.vetguide;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
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

public class RegistrarUsuarioDesdeFacebook extends AppCompatActivity {

    Button butSiguiente, butCancelar;
    EditText nombre, contra, correo,  apellido;
    ParseUser user;
    @Bind(R.id.imageView)
    ImageView img;

    private static final int PICK_IMAGE = 2000;

    private void getFbData() {
        GraphRequest request = GraphRequest.newMeRequest(
                AccessToken.getCurrentAccessToken(),
                new GraphRequest.GraphJSONObjectCallback() {
                    @Override
                    public void onCompleted(
                            JSONObject object,
                            GraphResponse response) {
                        Log.d("FbUserData", object.toString());
                        try {
                            Utils.user.setFbid(object.getString("id"));
                            Utils.user.setNombre(object.getString("name"));

                            Utils.user.setEmail(object.getString("email"));
                            Utils.user.setSexo(object.getString("gender"));
                            Utils.user.setUrlFoto("https://graph.facebook.com/" + Utils.user.getFbid() + "/picture?height=400&width=400");

                            nombre.setText(Utils.user.getNombre());
                            correo.setText(Utils.user.getEmail());

                            new DownloadImageTask(img, true).execute(Utils.user.getUrlFoto());

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
        Bundle parameters = new Bundle();
        parameters.putString("fields", "id,name,link,email,gender");
        request.setParameters(parameters);
        request.executeAsync();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar_usuario_desde_facebook);

        img=(ImageView)findViewById(R.id.imageView);
        butSiguiente=(Button)findViewById(R.id.buttonSiguiente);

        nombre = (EditText)findViewById(R.id.eteNombreUsuario);
        contra = (EditText)findViewById(R.id.etePasswordUsuario);
        correo = (EditText)findViewById(R.id.eteCorreoUsuario);
        apellido = (EditText)findViewById(R.id.eteApellidoUsuario);
        final Bitmap bitmap = ((BitmapDrawable) img.getDrawable()).getBitmap();
        new clases.BitmapToByteArray().execute(bitmap);

        if (Utils.user.isFacebook()) {
            getFbData();
            nombre.setText(Utils.user.getNombre());
            correo.setText(Utils.user.getEmail());
            img.setImageBitmap(bitmap);
        }

        butSiguiente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (Utils.parseUser == null) {
                    Utils.parseUser = new ParseUser();
                }
                Utils.parseUser.setEmail(correo.getText().toString());
                Utils.parseUser.setPassword(contra.getText().toString());
                Utils.parseUser.setUsername(correo.getText().toString());
                Utils.parseUser.put("nombre", nombre.getText().toString());
                Utils.parseUser.put("apellido", apellido.getText().toString());

                final ParseFile foto = new ParseFile("foto.png", Utils.imageBuffer);

                foto.saveInBackground(new SaveCallback() {
                    @Override
                    public void done(ParseException e) {
                        if(e==null){

                        Utils.parseUser.put("foto", foto);
                        if (Utils.user.isFacebook()) {
                            Utils.parseUser.saveInBackground(new SaveCallback() {
                                @Override
                                public void done(ParseException e) {
                                    Utils.parseUser.signUpInBackground(new SignUpCallback() {

                                        @Override
                                        public void done(ParseException e) {
                                            Intent intent = new Intent(RegistrarUsuarioDesdeFacebook.this, Inicio.class);
                                            Toast t = Toast.makeText(RegistrarUsuarioDesdeFacebook.this, "Registro correcto", Toast.LENGTH_LONG);
                                            t.show();
                                            startActivity(intent);
                                            RegistrarUsuarioDesdeFacebook.this.finish();
                                            Utils.parseUser = null;

                                        }
                                    });
                                }
                            });
                        } else {
                            Utils.parseUser.saveInBackground(new SaveCallback() {
                                @Override
                                public void done(ParseException e) {
                                    Utils.parseUser.signUpInBackground(new SignUpCallback() {

                                        @Override
                                        public void done(ParseException e) {
                                            Intent intent = new Intent(RegistrarUsuarioDesdeFacebook.this, Inicio.class);
                                            Toast t = Toast.makeText(RegistrarUsuarioDesdeFacebook.this, "Registro correcto", Toast.LENGTH_LONG);
                                            t.show();
                                            startActivity(intent);
                                            RegistrarUsuarioDesdeFacebook.this.finish();
                                            Utils.parseUser = null;

                                        }
                                    });
                                }
                            });
                        }
                    }
                }
                  }

                );

            }
        });
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openGallery();
            }
        });

        butCancelar=(Button)findViewById(R.id.buttonCancelar);
        butCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(RegistrarUsuarioDesdeFacebook.this, MainActivity.class);
                startActivity(i);
                RegistrarUsuarioDesdeFacebook.this.finish();
            }
        });




    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == PICK_IMAGE) {
            Uri imageUri = data.getData();
            img.setImageURI(imageUri);
            Bitmap bitmap = ((BitmapDrawable) img.getDrawable()).getBitmap();
            new BitmapToByteArray().execute(bitmap);
        }
    }

    private void openGallery() {
        Intent gallery =
                new Intent(Intent.ACTION_PICK,
                        android.provider.MediaStore.Images.Media.INTERNAL_CONTENT_URI);
        startActivityForResult(gallery, PICK_IMAGE);
    }

}
