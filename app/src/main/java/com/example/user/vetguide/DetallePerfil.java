package com.example.user.vetguide;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class DetallePerfil extends AppCompatActivity {

    @Bind(R.id.name_profile)
    TextView name;
    @Bind(R.id.email_profile)
    TextView email;

    @Bind(R.id.recycler_view)
    RecyclerView recyclerView;

    ParseQuery<ParseObject> query= ParseQuery.getQuery("Mascota");
    ParseUser u=ParseUser.getCurrentUser();

    MascotasAdapter adapter;
    List<ParseObject> list=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_perfil);
        ButterKnife.bind(this);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        adapter= new MascotasAdapter(list);
        recyclerView.setAdapter(adapter);
        query.whereEqualTo("Apoderado", u.getObjectId());
        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> objects, ParseException e) {
                if (objects.size()==0) {
                    Toast.makeText(DetallePerfil.this, "No tiene mascotas registradas", Toast.LENGTH_LONG).show();
                }


                for (ParseObject object : objects) {
                    list.add(object);
                }

                adapter.notifyDataSetChanged();

            }
        });


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        name.setText(u.getString("nombre"));
        email.setText(u.getEmail());

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(DetallePerfil.this, RegistroMascotaActivity.class);
                startActivity(i);


            }
        });
    }

}
