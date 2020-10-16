package com.luis.audiolibros;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (findViewById(R.id.contenedor_pequeno) != null &&
                getSupportFragmentManager().findFragmentById(R.id.contenedor_pequeno)==null){
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            SelectorFragment fragment = new SelectorFragment();
            fragmentTransaction.add(R.id.contenedor_pequeno, fragment); //cuando se agrega fragmento dinámicamente se le asigna el mismo ID que tiene su contenedor
            fragmentTransaction.commit();
        }
    }

    /*RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    AdaptadorLibros adaptadorLibros;*/

    /*recyclerView = findViewById(R.id.recyclerView);
        layoutManager = new GridLayoutManager(this,2); // numero columnas
        recyclerView.setLayoutManager(layoutManager);
        adaptadorLibros = new AdaptadorLibros(getApplicationContext(), Libro.ejemploLibros());
        recyclerView.setAdapter(adaptadorLibros);*/

    public void mostrarDetalle(int index){
        FragmentManager fragmentManager = getSupportFragmentManager();
        DetalleFragment detalleFragment = (DetalleFragment)
                fragmentManager.findFragmentById(R.id.detalle_fragment);
        if(detalleFragment!=null&&detalleFragment.isVisible()){
            detalleFragment.ponInfoLibro(index);
        }
        else{
            detalleFragment = new DetalleFragment();
            Bundle bundle = new Bundle(); //colección llave-valor
            bundle.putInt(DetalleFragment.ARG_ID_LIBRO, index);
            detalleFragment.setArguments(bundle);
            fragmentManager.beginTransaction()
                    .replace(R.id.contenedor_pequeno, detalleFragment)
                    .addToBackStack(null).commit();
        }
    }
}