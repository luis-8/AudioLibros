package com.luis.audiolibros;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class SelectorFragment extends Fragment {

    private RecyclerView recyclerView;
    private GridLayoutManager layoutManager;
    private AdaptadorLibros adaptadorLibros;
    MainActivity mainActivity;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_selector, container,false);
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView3);

        layoutManager = new GridLayoutManager(getActivity(), 2);

        recyclerView.setLayoutManager(layoutManager);

        adaptadorLibros = new AdaptadorLibros(getActivity(), Libro.ejemploLibros());
        adaptadorLibros.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //Toast.makeText(getActivity(),"Elemento seleccionado"+recyclerView.getChildAdapterPosition(v),Toast.LENGTH_LONG).show();
                        mainActivity.mostrarDetalle(recyclerView.getChildAdapterPosition(v));
                    }
                }
        );
        recyclerView.setAdapter(adaptadorLibros);
        return view;
    }

    @Override
    public void onAttach(@NonNull Context context){
        super.onAttach(context);
        if(context instanceof MainActivity){
            this.mainActivity = (MainActivity) context;
        }
    }
}
//View view = inflater.inflate(R.layout.fragment_layout_lista_selector, null);