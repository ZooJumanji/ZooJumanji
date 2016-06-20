package com.example.hb.zoojumanji.enclosure.service;

import android.app.IntentService;
import android.content.Intent;
import android.os.IBinder;

import com.example.hb.zoojumanji.JumanjiNotification;
import com.example.hb.zoojumanji.enclosure.Enclosure;
import com.example.hb.zoojumanji.enclosure.manager.EnclosureManager;

import java.util.List;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public class EnclosureService extends IntentService {

    EnclosureServiceBinder binder;
    public EnclosureService() {
        super("EnclosureService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {

        if (intent != null) {

            final String action = intent.getAction();
            int id = intent.getIntExtra("id", 0);

            // Create a very simple REST adapter.
            ServerEnclosure serverEnclosure = ServiceGenerator.createService(ServerEnclosure.class);
            EnclosureManager manager = new EnclosureManager(this);

            JumanjiNotification.make(this, "Action : "+action+
                    " on enclosure of id "+String.valueOf(id), 12345);

            Call<Enclosure> call;
            switch(action) {

                case "detail" :
                    call = serverEnclosure.get(id);
                    break;
                case "create" :
                    call = serverEnclosure.add(manager.getEnclosure(id));
                    break;
                case "modify" :
                    call = serverEnclosure.modify(id, manager.getEnclosure(id));
                    break;
                case "delete" :
                    call = serverEnclosure.delete(id);
                    break;
                default :
                    JumanjiNotification.make(EnclosureService.this, "unknow action " + action, 12345);
                    return;
            }

            call.enqueue(new Callback<Enclosure>() {
                             @Override
                             public void onResponse(Call<Enclosure> call, Response<Enclosure> response) {

                                 if (response.isSuccessful()) {
                                     JumanjiNotification.make(EnclosureService.this, action +
                                             " succes : " + response.code(), 12345);
                                 } else {
                                     JumanjiNotification.make(EnclosureService.this, action +
                                             " error : " + response.code(), 12345);
                                 }
                             }

                             @Override
                             public void onFailure(Call<Enclosure> call, Throwable t) {
                                 JumanjiNotification.make(EnclosureService.this, action +
                                         " failure : " + t.getMessage(), 12345);
                             }
                         }
            );
        }
    }

    public void getEnclosureList(final EnclosureManager manager) {

        // Create a very simple REST adapter.
        ServerEnclosure serverEnclosure = ServiceGenerator.createService(ServerEnclosure.class);

        Call<List<Enclosure>> call = serverEnclosure.list();

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
        @GET("/zoomanji/rest/enclosures")
        Call<List<Enclosure>> list(
        );

        @GET("/zoomanji/rest/enclosures/{id}")
        Call<Enclosure> get(
                @Path("id") int id
        );

        @PUT("/zoomanji/rest/enclosures/{id}")
        Call<Enclosure> modify(
                @Path("id") int id,
                @Body Enclosure enclosure
        );

        @DELETE("/zoomanji/rest/enclosures/{id}")
        Call<Enclosure> delete(
                @Path("id") int id
        );

        @POST("/zoomanji/rest/enclosures/new")
        Call<Enclosure> add(
                @Body Enclosure enclosure
        );

    }

    public static class ServiceGenerator {

        /*
        public static final String API_BASE_URL = "http://192.168.1.33:8080";
        /*/
        public static final String API_BASE_URL = "http://172.16.110.169:8080";
        //*/

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
