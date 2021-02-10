package com.luis.audiolibros.services;

import android.app.IntentService;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;

public class MiIntentService extends IntentService {
    /**
     * A constructor is required, and must call the super <code><a href="/reference/android/app/IntentService.html#IntentService(java.lang.String)">IntentService(String)</a></code>
     * constructor with a name for the worker thread.
     */
    public MiIntentService() {
        super("MiIntentService");
    }

    class MiTareaAsincrona  extends AsyncTask<Integer,Integer,Boolean>{

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            //inicilizar recursos
            Log.d("MIIS", "Se inicio el subproceso en tarea Asincrona");
        }

        @Override
        protected Boolean doInBackground(Integer... integers) {
            Log.d("MIIS", "Se recibieron " + integers.length +" parametros" );
            for (int i=0; i<integers.length ; i++){
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Log.d("MIIS", "Valor del parametro "+ (i+1) + ": " + integers[i]);
                publishProgress(i, i);
            }
            return integers.length>0;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            Log.d("MIIS", "Progreso: " + values[0] );
        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {
            super.onPostExecute(aBoolean);
            if(aBoolean){
                Log.d("MIIS", "Subproceso/Tarea Asincrona finalizado" );
            }
        }
    }

    /**
     * The IntentService calls this method from the default worker thread with
     * the intent that started the service. When this method returns, IntentService
     * stops the service, as appropriate.
     */
    @Override
    protected void onHandleIntent(Intent intent) {
        // Normally we would do some work here, like download a file.
        // For our sample, we just sleep for 5 seconds.
        Log.d("MIIS", "Entra onHandleIntent" );
        try {
            Thread.sleep(5000);
            new MiTareaAsincrona().execute(1,2,3,4,5);
        } catch (InterruptedException e) {
            // Restore interrupt status.
            //Thread.currentThread().interrupt();
            e.printStackTrace();
        }
        Log.d ("MIIS", "Sale onHandleIntent");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d ("MIIS", "IntentService Destruido");
    }
}
