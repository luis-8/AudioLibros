package com.luis.audiolibros.services;

import android.annotation.SuppressLint;
import android.app.Service;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;

public class MiServicio extends Service {

    @Override
    public void onCreate(){
        super.onCreate();
        Log.d("msal","Servicio creado");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        //Este método se manda llamar cuando invocas el servicio con startService()
        //tarea pesada debe ir en un subproceso y desecadenarse aquí
        Log.d("msal","Inicia onStartCommand");
        try{
            Thread.sleep(5000);
            @SuppressLint("StaticFieldLeak") AsyncTask<Integer, Integer, Boolean> task = new AsyncTask<Integer, Integer, Boolean>() {
                @Override
                protected void onPreExecute() {
                    super.onPreExecute();
                    Log.d("msal","Iniciando la tarea pesada");
                    //Inicializacion de objetos
                }

                @Override
                protected Boolean doInBackground(Integer... integers) {

                    //código de tarea pesada
                    //consultar un API web o un recurso

                    for (int i = 0; i < integers.length; i++) {
                        Log.d("msal", "Valor del parametro "+ (i+1) + ": " + integers[i]);
                        onProgressUpdate(i, i);
                    }

                    return integers.length > 0;
                }

                @Override
                protected void onProgressUpdate(Integer... values) {
                    super.onProgressUpdate(values);
                    Log.d("msal", "Progreso: " + values[0]);

                }

                @Override
                protected void onPostExecute(Boolean aBoolean) {
                    super.onPostExecute(aBoolean);
                    if (aBoolean) {
                        Log.d("msal", "Tarea exhautiva finalizada");
                    }
                }
            };

            task.execute(5,4,3,2,1);

        }catch (InterruptedException e){
            e.printStackTrace();
        }

        Log.d("msal","Termina onStartCommand");

        //stopSelf();
        return super.onStartCommand(intent, flags, startId);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onDestroy(){
        super.onDestroy();
        Log.d("msal","Servicio destruido");
    }
}
