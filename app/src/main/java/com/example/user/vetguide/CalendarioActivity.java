package com.example.user.vetguide;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;


public class CalendarioActivity extends AppCompatActivity {

    ArrayAdapter<String> adapter;
    ArrayList<String> list=new ArrayList<>();
    @Bind(R.id.calentadarioReservas)
    ListView reservas;
    @Bind(R.id.toolbar)
    Toolbar toolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendario);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(CalendarioActivity.this, Inicio.class);
                startActivity(i);
                CalendarioActivity.this.finish();

            }
        });

        ParseQuery<ParseObject> query = ParseQuery.getQuery("Cita");
//      query.whereEqualTo("idUsuario",ParseUser.getCurrentUser());
        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> objects, ParseException e) {

                Toast.makeText(CalendarioActivity.this,"Estas en el DOne",Toast.LENGTH_LONG).show();
                for (int i=0;i<objects.size();i++){
                    list.add(objects.get(i).getString("diaCita"));
                }

                adapter = new ArrayAdapter<String>(CalendarioActivity.this, android.R.layout.simple_list_item_1, list);
                reservas.setAdapter(adapter);


            }
        });



    }
}
