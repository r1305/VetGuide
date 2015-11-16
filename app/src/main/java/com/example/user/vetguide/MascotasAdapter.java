package com.example.user.vetguide;

import android.graphics.BitmapFactory;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.parse.GetDataCallback;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Rogger on 10/11/2015.
 */
public class MascotasAdapter extends RecyclerView.Adapter<MascotasAdapter.ViewHolder> {

    List<ParseObject> list=new ArrayList<>();


    public MascotasAdapter(List<ParseObject> list) {
        this.list = list;
    }
    @Override
    public MascotasAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_mascotas, parent, false));
    }

    @Override
    public void onBindViewHolder(final MascotasAdapter.ViewHolder holder, int position) {

        ParseObject po = list.get(position);

        holder.nombre.setText(po.getString("nombre"));
        holder.raza.setText(po.getString("raza"));
        holder.edad.setText(po.getString("edad"));
        holder.color.setText(po.getString("color"));
        ParseFile applicantResume = (ParseFile)po.get("foto");

        applicantResume.getDataInBackground(new GetDataCallback() {
            public void done(byte[] data, ParseException e) {
                if (e == null) {

                    holder.img.setImageBitmap(BitmapFactory.decodeByteArray(data, 0, data.length));

                } else {
                    // something went wrong

                }
            }
        });

    }

    @Override
    public int getItemCount() {
        if(list==null){
            return 0;
        }else {
            return list.size();
        }
    }

    class ViewHolder extends RecyclerView.ViewHolder {


        @Bind(R.id.nombre_masc)
        TextView nombre;
        @Bind(R.id.raza_masc)
        TextView raza;
        @Bind(R.id.color_masc)
        TextView color;
        @Bind(R.id.edad_masc)
        TextView edad;
        @Bind(R.id.img_masc)
        ImageView img;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

        }
    }
}
