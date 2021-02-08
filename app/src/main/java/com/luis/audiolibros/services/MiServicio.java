package com.luis.audiolibros.services;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;

public class MiServicio extends Service {

    @Override
    public void onCreate(){
        Log.d("msal","Se crea el servicio");
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        //Este método se manda llamar cuando invocas el servicio con startService()
        //tarea pesada debe ir en un subproceso y desecadenarse aquí
        Log.d("msal","Iniciando la tarea pesada");
        try{
            Thread.sleep(5000);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        Log.d("msal","Tarea pesada finalizada");

        stopSelf();
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
