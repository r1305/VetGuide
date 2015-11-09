package com.example.user.vetguide;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.parse.Parse;
import com.parse.ParseObject;

import org.w3c.dom.Text;

import java.util.List;

import clases.Veterinaria;

/**
 * Created by itlab on 11/5/15.
 */
public class VeterinariaAdapter extends RecyclerView.Adapter<VeterinariaAdapter.ViewHolder> {
    private List<ParseObject> lista;


    View.OnClickListener listener;

    public void SetOnClickListener(View.OnClickListener listener){
        this.listener=listener;
    }
    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView mTextView;
        public TextView direccion;
        public ViewHolder(View v) {
            super(v);
            mTextView = (TextView)v.findViewById(R.id.tviNombreVeterinariaLista);
            direccion = (TextView)v.findViewById(R.id.tviDireccionVeterinariaLista);
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public VeterinariaAdapter(List<ParseObject> myDataset) {
        lista = myDataset;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public VeterinariaAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                   int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.itemveterinaria, parent, false);
        // set the view's size, margins, paddings and layout parameters

        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element


        //recorriendo la lista de veterinarias (parse object)
        ParseObject po=lista.get(position);
        holder.mTextView.setText(po.getString("nombre"));
        holder.direccion.setText(po.getString("direccion"));
        holder.itemView.setTag(po);
        holder.itemView.setOnClickListener(listener);

    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return lista.size();
    }
}
