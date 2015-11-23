package com.example.user.vetguide;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.GetDataCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class Inicio extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{
    ParseQuery<ParseObject> query = ParseQuery.getQuery("Veterinaria");
    VeterinariaAdapter adapter;
    List<ParseObject> lista = new ArrayList<>();//lo cambie por un ParseObject
    String idUsuario;
    Toolbar toolbar;
    NavigationView nav;
    DrawerLayout dl;
    CircleImageView img;
    TextView txt_nav;
    RecyclerView rvi;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_inicio);

        toolbar=(Toolbar)findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(R.drawable.menu);
        nav=(NavigationView)findViewById(R.id.navigation);
        View headerLayout =nav.inflateHeaderView(R.layout.nav_header);
        img = (CircleImageView)headerLayout.findViewById(R.id.nav_img);
        txt_nav=(TextView)headerLayout.findViewById(R.id.nav_txt);

        setSupportActionBar(toolbar);

        dl=(DrawerLayout)findViewById(R.id.drawer_layout);

        getSupportActionBar().setHomeButtonEnabled(true);
        toolbar.animate();
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dl.openDrawer(GravityCompat.START);
                if (dl.isDrawerOpen(GravityCompat.START)) {
                    dl.closeDrawers();
                }
            }
        });
        ParseUser u=ParseUser.getCurrentUser();

        txt_nav.setText(u.getString("nombre"));
        ParseFile applicantResume = (ParseFile)u.getParseFile("foto");

         applicantResume.getDataInBackground(new GetDataCallback() {
            public void done(byte[] data, ParseException e) {
                if (e == null) {

                    img.setImageBitmap(BitmapFactory.decodeByteArray(data, 0, data.length));

                } else {

                }
            }
        });

        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Inicio.this, DetallePerfil.class);
                startActivity(i);
            }
        });

        rvi=(RecyclerView)findViewById(R.id.my_recycler_view);
        rvi.setLayoutManager(new LinearLayoutManager(this));

        Intent intentando=getIntent();
        idUsuario=intentando.getStringExtra("idUsuario");

        adapter=new VeterinariaAdapter(lista);

        rvi.setAdapter(adapter);
        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> objects, ParseException e) {

                for(ParseObject object:objects){
                    lista.add(object);
                }
                adapter.notifyDataSetChanged();

            }

        });


        adapter.SetOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               ParseObject ve = (ParseObject)v.getTag();
                Log.e("Click", ve.getString("nombre"));
                Intent i = new Intent(Inicio.this, VeterinariaDetalles.class);
                String idvet=ve.getObjectId();
                i.putExtra("idvet",idvet);
                i.putExtra("idUsuario",idUsuario);
                startActivity(i);
           }
        });

        nav.setNavigationItemSelectedListener(this);

    }

    @Override
    public boolean onNavigationItemSelected(MenuItem menuItem) {

        switch(menuItem.getItemId()){
            case R.id.principal:
                Toast.makeText(this,"Principal",Toast.LENGTH_LONG).show();
                dl.closeDrawers();
                return true;
            case R.id.explorar:
                Toast.makeText(this,"Explorar",Toast.LENGTH_LONG).show();
                dl.closeDrawers();
                return true;
            case R.id.hoja_medica:

                Toast.makeText(this,"Hoja Medica",Toast.LENGTH_LONG).show();
                dl.closeDrawers();
                return true;
            case R.id.favoritos:
                Toast.makeText(this,"Favoritos",Toast.LENGTH_LONG).show();
                dl.closeDrawers();
                return true;
            case R.id.calendario:

                Intent j=new Intent(this,CalendarioActivity.class);
                startActivity(j);
                dl.closeDrawers();
                return true;

            case R.id.emergencia:
                adapter=new VeterinariaAdapter(lista);

                rvi.setAdapter(adapter);

                Toast.makeText(this,"Emergencia",Toast.LENGTH_LONG).show();
                query.whereEqualTo("abierto_siempre", true);
                query.findInBackground(new FindCallback<ParseObject>() {
                    @Override
                    public void done(List<ParseObject> objects, ParseException e) {
                        lista.clear();
                        for (ParseObject object : objects) {
                            lista.add(object);
                        }
                        adapter.notifyDataSetChanged();
                    }
                });

                adapter.SetOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        ParseObject ve = (ParseObject) v.getTag();
                        Log.e("Click", ve.getString("nombre"));
                        Intent i = new Intent(Inicio.this, VeterinariaDetalles.class);
                        String idvet = ve.getObjectId();
                        i.putExtra("idvet", idvet);
                        i.putExtra("idUsuario", idUsuario);
                        startActivity(i);
                    }
                });
                dl.closeDrawers();
                return true;

            case R.id.mapa:

                Toast.makeText(this,"Mapa",Toast.LENGTH_LONG).show();
                Intent i=new Intent(Inicio.this,MapsActivity.class);
                startActivity(i);
                return true;

            case R.id.perfil:

                Toast.makeText(this,"Mapa",Toast.LENGTH_LONG).show();
                i=new Intent(Inicio.this,DetallePerfil.class);
                startActivity(i);
                return true;

            }

        return false;
    }



}
