package com.example.hb.zoojumanji.enclosure.service;

import android.app.IntentService;
import android.content.Intent;
import android.os.IBinder;
import android.widget.Toast;

import com.example.hb.zoojumanji.MainActivity;
import com.example.hb.zoojumanji.R;
import com.example.hb.zoojumanji.enclosure.Enclosure;
import com.example.hb.zoojumanji.enclosure.manager.EnclosureManager;

import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
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

            Call<ResponseBody> call;
            switch(action) {

                /*
                case "detail" :
                    call = serverEnclosure.get(id);
                    break;
                //*/
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
                    return;
            }

            call.enqueue(new Callback<ResponseBody>() {
                             @Override
                             public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

                                 if (response.isSuccessful()) {
                                     Toast.makeText(EnclosureService.this, R.string.message_update_success,
                                             Toast.LENGTH_SHORT).show();
                                 } else {
                                     Toast.makeText(EnclosureService.this, R.string .message_update_failure,
                                             Toast.LENGTH_SHORT).show();
                                 }
                             }

                             @Override
                             public void onFailure(Call<ResponseBody> call, Throwable t) {
                                 Toast.makeText(EnclosureService.this, R.string.message_update_failure,
                                         Toast.LENGTH_SHORT).show();
                             }
                         }
            );
        }
    }

    public void getEnclosureList(final EnclosureManager manager) {

        // Create a very simple REST adapter.
        ServerEnclosure serverEnclosure = ServiceGenerator.createService(ServerEnclosure.class);

        Call<List<Enclosure>> call = serverEnclosure.list();

        Toast.makeText(this, R.string.message_updating, Toast.LENGTH_SHORT).show();

        call.enqueue(new Callback<List<Enclosure>>() {
                         @Override
                         public void onResponse(Call<List<Enclosure>> call, Response<List<Enclosure>> response) {

                             if (response.isSuccessful()) {
                                 List<Enclosure> enclosures = response.body();
                                 manager.updateList(enclosures);

                                 Toast.makeText(EnclosureService.this, R.string.message_update_success,
                                         Toast.LENGTH_SHORT).show();
                             } else {
                                 Toast.makeText(EnclosureService.this, R.string.message_update_failure,
                                         Toast.LENGTH_SHORT).show();
                             }
                         }

                         @Override
                         public void onFailure(Call<List<Enclosure>> call, Throwable t) {
                             Toast.makeText(EnclosureService.this, R.string.message_update_failure,
                                     Toast.LENGTH_SHORT).show();
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
        Call<ResponseBody> modify(
                @Path("id") int id,
                @Body Enclosure enclosure
        );

        @DELETE("/zoomanji/rest/enclosures/{id}")
        Call<ResponseBody> delete(
                @Path("id") int id
        );

        @POST("/zoomanji/rest/enclosures/new")
        Call<ResponseBody> add(
                @Body Enclosure enclosure
        );

    }

    public static class ServiceGenerator {

        public static <T> T createService(Class<T> serviceClass) {

            String API_BASE_URL = "http://" +
                    MainActivity.getWebServiceIP() + ":8080";

            OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

            Retrofit.Builder builder =
                    new Retrofit.Builder()
                            .baseUrl(API_BASE_URL)
                            .addConverterFactory(GsonConverterFactory.create())
                    ;

            Retrofit retrofit = builder.client(httpClient.build()).build();
            return retrofit.create(serviceClass);
        }
    }

}
