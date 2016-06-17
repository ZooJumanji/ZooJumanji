package com.example.hb.zoojumanji.animal.Service;

import android.app.IntentService;
import android.content.Intent;
import android.os.IBinder;

import com.example.hb.zoojumanji.JumanjiNotification;
import com.example.hb.zoojumanji.animal.Animal;
import com.example.hb.zoojumanji.animal.manager.AnimalManager;
import com.example.hb.zoojumanji.enclosure.service.EnclosureServiceBinder;

import java.util.List;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by isher on 17/06/2016.
 */
public class AnimalService extends IntentService {

    AnimalServiceBinder binder;

    public AnimalService() {
        super("AnimalService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {

        if (intent != null) {

            String action = intent.getAction();
            int id = intent.getIntExtra("id", 0);

            JumanjiNotification.make(this, "Action : " + action +
                    " on Animal of id " + String.valueOf(id), 1234);
        }
    }

    public void getAnimalList(final AnimalManager manager) {

        // Create a very simple REST adapter.
        ServerAnimal serverAnimal = ServiceGenerator.createService(ServerAnimal.class);

        Call<List<Animal>> call = serverAnimal.list();

        JumanjiNotification.make(this, "Refresh list of enclosures", 1234);

        call.enqueue(new Callback<List<Animal>>() {
                         @Override
                         public void onResponse(Call<List<Animal>> call, Response<List<Animal>> response) {

                             if (response.isSuccessful()) {
                                 List<Animal> animaux = response.body();
                                 manager.updateList(animaux);
                             } else {
                                 JumanjiNotification.make(AnimalService.this, "Listing error :"
                                         + response.code(), 1234);
                             }
                         }

                         @Override
                         public void onFailure(Call<List<Animal>> call, Throwable t) {
                             JumanjiNotification.make(AnimalService.this, "Listing failure :"
                                     + t.getMessage(), 1234);
                         }
                     }
        );
    }

    @Override
    public IBinder onBind(Intent intent) {

        binder = new AnimalServiceBinder(this);
        return binder;
    }

    public interface ServerAnimal {
        @GET("/zoomanji/REST/animaux")
        Call<List<Animal>> list(
        );

        @GET("/zoomanji/REST/animaux/{id}")
        Call<Animal> get(
                @Path("id") int id
        );
    }

    public static class ServiceGenerator {

        public static final String API_BASE_URL = "http://192.168.1.27";

        private static OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

        private static Retrofit.Builder builder =
                new Retrofit.Builder()
                        .baseUrl(API_BASE_URL)
                        .addConverterFactory(GsonConverterFactory.create());

        public static <T> T createService(Class<T> serviceClass) {
            Retrofit retrofit = builder.client(httpClient.build()).build();
            return retrofit.create(serviceClass);
        }
    }

}
