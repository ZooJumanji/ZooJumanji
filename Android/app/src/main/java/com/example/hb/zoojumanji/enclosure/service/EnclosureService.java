package com.example.hb.zoojumanji.enclosure.service;

import android.app.IntentService;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import android.widget.Toast;

import com.example.hb.zoojumanji.JumanjiNotification;
import com.example.hb.zoojumanji.R;
import com.example.hb.zoojumanji.enclosure.Enclosure;
import com.example.hb.zoojumanji.enclosure.activity.EnclosureActivity;
import com.example.hb.zoojumanji.enclosure.manager.EnclosureManager;

import java.util.ArrayList;
import java.util.List;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;

public class EnclosureService extends IntentService {

    EnclosureServiceBinder binder;
    public EnclosureService() {
        super("EnclosureService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {

        if (intent != null) {

            String action = intent.getAction();
            int id = intent.getIntExtra("id", 0);

            JumanjiNotification.make(this, "Action : "+action+
                    " on enclosure of id "+String.valueOf(id), 1234);
        }
    }

    public void getEnclosureList(final EnclosureManager manager) {

        // Create a very simple REST adapter.
        ServerEnclosure serverEnclosure = ServiceGenerator.createService(ServerEnclosure.class);

        Call<List<Enclosure>> call =
                serverEnclosure.list();

        JumanjiNotification.make(this, "Refresh list of enclosures", 1234);

        call.enqueue(new Callback<List<Enclosure>>() {
                         @Override
                         public void onResponse(Call<List<Enclosure>> call, Response<List<Enclosure>> response) {

                             if (response.isSuccessful()) {
                                 List<Enclosure> enclosures = response.body();
                                 manager.updateList(enclosures);
                             } else {
                                 JumanjiNotification.make(EnclosureService.this, "Listing error :"
                                         + response.code(), 1234);
                             }
                         }

                         @Override
                         public void onFailure(Call<List<Enclosure>> call, Throwable t) {
                             JumanjiNotification.make(EnclosureService.this, "Listing failure :"
                                     + t.getMessage(), 1234);
                         }
                     }
        );
    }

    @Override
    public IBinder onBind(Intent intent) {

        binder = new EnclosureServiceBinder(this);
        return binder;
    }

    public interface ServerEnclosure {
        @GET("/serveur_php/REST/enclosures")
        Call<List<Enclosure>> list(
        );

        @GET("/serveur_php/REST/enclosures/{id}")
        Call<Enclosure> get(
                @Path("id") int id
        );
    }

    public static class ServiceGenerator {

        public static final String API_BASE_URL = "http://192.168.1.27";

        private static OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

        private static Retrofit.Builder builder =
                new Retrofit.Builder()
                        .baseUrl(API_BASE_URL)
                        .addConverterFactory(GsonConverterFactory.create())
                ;

        public static <T> T createService(Class<T> serviceClass) {
            Retrofit retrofit = builder.client(httpClient.build()).build();
            return retrofit.create(serviceClass);
        }
    }

}
