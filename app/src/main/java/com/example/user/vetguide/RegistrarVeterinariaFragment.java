package com.example.user.vetguide;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.maps.model.LatLng;
import com.parse.ParseException;
import com.parse.ParseGeoPoint;
import com.parse.ParseObject;
import com.parse.SaveCallback;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class RegistrarVeterinariaFragment extends Fragment {

    Button butSiguiente, butCancelar, butMapa;
    EditText nombrevete, direccionvete, distritovete, telefonovete, diasinicio, diasfin, horasinicio, horasfin;
    ParseObject veterinaria;
    double latitud, longitud;
    @Bind(R.id.fullday)
    CheckBox check;
    boolean fullday=false;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_registrar_veterinaria, container, false);
        ButterKnife.bind(this,view);

        butSiguiente = (Button) view.findViewById(R.id.buttonSiguiente3);
        butMapa = (Button) view.findViewById(R.id.buttonMapear);

        nombrevete = (EditText) view.findViewById(R.id.eteNombreVet);
        direccionvete = (EditText) view.findViewById(R.id.eteDireccionVet);
        distritovete = (EditText) view.findViewById(R.id.eteDistritoVet);
        telefonovete = (EditText) view.findViewById(R.id.eteTelefonoVet);
        diasinicio = (EditText) view.findViewById(R.id.eteDiaInicioVet);
        diasfin = (EditText) view.findViewById(R.id.eteDiaFinVet);
        horasinicio = (EditText) view.findViewById(R.id.eteHoraInicioVet);
        horasfin = (EditText) view.findViewById(R.id.eteHoraFinVet);

        check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                horasinicio.setText("00:00");
                horasfin.setText("00:00");
                fullday=true;
            }
        });




        butSiguiente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                veterinaria = new ParseObject("Veterinaria");

                LatLng c=getLocationFromAddress(getContext(),direccionvete.getText().toString()+","+distritovete.getText().toString());
                longitud=c.longitude;
                latitud=c.latitude;
                Toast.makeText(getActivity(),"Latitud: "+latitud+", Longitud: "+longitud,Toast.LENGTH_LONG).show();


                veterinaria.put("nombre", nombrevete.getText().toString());
                veterinaria.put("direccion", direccionvete.getText().toString());
                veterinaria.put("distrito", distritovete.getText().toString());
                veterinaria.put("telefono", Integer.parseInt(telefonovete.getText().toString()));
                veterinaria.put("dias_atencion", diasinicio.getText().toString() + "-" + diasfin.getText().toString());
                veterinaria.put("horas_atencion", horasinicio.getText().toString() + "-" + horasfin.getText().toString());

                veterinaria.put("abierto_siempre",fullday);
                ParseGeoPoint p=new ParseGeoPoint(latitud,longitud);
                veterinaria.put("location",p);

                veterinaria.saveInBackground(new SaveCallback() {

                    @Override
                    public void done(ParseException e) {
                        if (e == null) {
                            Toast t = Toast.makeText(getContext(), "Inscripción de Veterinaria Correcta", Toast.LENGTH_SHORT);
                            t.show();
                            Intent i = new Intent(getActivity(), MascotasVetActivity.class);
                            i.putExtra("codigovet", veterinaria.getObjectId());
                            startActivity(i);
                        } else {
                            Toast t = Toast.makeText(getContext(), "Inscripción de Veterinaria Incorrecta", Toast.LENGTH_SHORT);
                            t.show();
                        }
                    }
                });


            }
        });


        butCancelar = (Button) view.findViewById(R.id.buttonCancelar3);
        butCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getActivity(), MainActivity.class);
                startActivity(i);
            }
        });

        butMapa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LatLng c=getLocationFromAddress(getContext(),direccionvete.getText().toString()+","+distritovete.getText().toString());
                longitud=c.longitude;
                latitud=c.latitude;
                Toast.makeText(getActivity(),"Latitud: "+latitud+", Longitud: "+longitud,Toast.LENGTH_LONG).show();


            }
        });


        return view;
    }

    public LatLng getLocationFromAddress(Context context,String strAddress) {

        Geocoder coder = new Geocoder(context);
        List<Address> address;
        LatLng p1 = null;

        try {
            address = coder.getFromLocationName(strAddress, 5);
            if (address == null) {
                return null;
            }
            Address location = address.get(0);
            location.getLatitude();
            location.getLongitude();

            p1 = new LatLng(location.getLatitude(), location.getLongitude() );

        } catch (Exception ex) {

            ex.printStackTrace();
        }

        return p1;
    }
}
