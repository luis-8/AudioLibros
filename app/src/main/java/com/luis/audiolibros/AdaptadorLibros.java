package com.luis.audiolibros;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Vector;

public class AdaptadorLibros extends RecyclerView.Adapter<AdaptadorLibros.Viewholder>{

    private LayoutInflater inflador;
    private Vector<Libro> vectorLibros;
    private View.OnClickListener onClickListener;

    public AdaptadorLibros(Context contexto, Vector<Libro> vectorLibros){
        this.inflador = (LayoutInflater)
                contexto.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.vectorLibros = vectorLibros;
    }

    private View.OnLongClickListener onLongClickListener;

    @NonNull
    @Override
    public Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = inflador.inflate(R.layout.elemento_selector, null);
        v.setOnClickListener(this.onClickListener);
        v.setOnLongClickListener(this.onLongClickListener);
        return new Viewholder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull Viewholder holder, int position) {
        Libro libro = vectorLibros.get(position); //elemento del vector identifiado por su id o posisicón
        holder.portada.setImageResource(libro.recursoImagen);
        holder.titulo.setText(libro.titulo);
    }

    @Override
    public int getItemCount() {
        return vectorLibros.size();
    }

    //Se crea ViewHolder; layout de xml como objeto de java
    public static class Viewholder extends RecyclerView.ViewHolder{
        public ImageView portada;
        public TextView titulo;

        public Viewholder(View itemView){
            super(itemView);
            portada = (ImageView) itemView.findViewById(R.id.portadaElemento);
            portada.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
            titulo = (TextView) itemView.findViewById(R.id.tituloElemento);
        }
    }

    public void setOnLongClickListener(View.OnLongClickListener onLongClickListener){
        this.onLongClickListener = onLongClickListener;
    }

    public void setOnClickListener(View.OnClickListener onClickListener){
        this.onClickListener = onClickListener;
    }
}
