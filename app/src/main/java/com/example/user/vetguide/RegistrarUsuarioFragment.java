package com.example.user.vetguide;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

public class RegistrarUsuarioFragment extends Fragment {
    Button butSiguiente, butCancelar;
    EditText nombre, contra, correo,  apellido;
    String objectid;
    ParseUser user;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.registrarusuario_fragment, container, false);
        butSiguiente=(Button)view.findViewById(R.id.buttonSiguiente);

        nombre = (EditText)view.findViewById(R.id.eteNombreUsuario);
        contra = (EditText) view.findViewById(R.id.etePasswordUsuario);
        correo = (EditText)view.findViewById(R.id.eteCorreoUsuario);
        apellido = (EditText)view.findViewById(R.id.eteApellidoUsuario);

        butSiguiente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ParseUser currentuser = ParseUser.getCurrentUser();
                currentuser.logOut();

                user = new ParseUser();
                user.setEmail(correo.getText().toString());
                user.setPassword(contra.getText().toString());
                user.setUsername(correo.getText().toString());
                user.put("nombre",nombre.getText().toString());
                user.put("apellido",apellido.getText().toString());

                user.signUpInBackground(new SignUpCallback() {
                    @Override
                    public void done(ParseException e) {
                        if (e == null) {


                            Toast t = Toast.makeText(getContext(), "Registro de datos Correcto", Toast.LENGTH_SHORT);
                            t.show();


                            Intent i = new Intent(getActivity(), RegistroMascotaActivity.class);
                          //  objectid = user.getObjectId();
                         //   i.putExtra("userId", objectid);

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





}
