package com.example.hb.zoojumanji.animal.service;

import android.app.IntentService;
import android.content.Intent;
import android.os.IBinder;
import android.widget.Toast;

import com.example.hb.zoojumanji.R;
import com.example.hb.zoojumanji.WebService;
import com.example.hb.zoojumanji.animal.Animal;
import com.example.hb.zoojumanji.animal.manager.AnimalManager;

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

            if (!action.equals("delete")) {
                callAnimalReturningRequest(action, id);
            }
            else {
                callResponseReturningRequest(action, id);
            }
        }
    }

    private void callAnimalReturningRequest(String action, int id) {

        ServerAnimal serverAnimal = ServiceGenerator.createService(ServerAnimal.class);
        AnimalManager manager = new AnimalManager(this);

        Call<Animal> call;
        switch(action) {

            case "create" :
                call = serverAnimal.create(manager.getAnimal(id));
                break;
            case "modify" :
                call = serverAnimal.update(manager.getAnimal(id));
                break;
            default :
                return;
        }

        call.enqueue(new Callback<Animal>() {
                         @Override
                         public void onResponse(Call<Animal> call, Response<Animal> response) {

                             if (response.isSuccessful()) {
                                 Toast.makeText(AnimalService.this, R.string.message_update_success,
                                         Toast.LENGTH_SHORT).show();
                             } else {
                                 Toast.makeText(AnimalService.this, R.string .message_update_failure,
                                         Toast.LENGTH_SHORT).show();
                             }
                         }

                         @Override
                         public void onFailure(Call<Animal> call, Throwable t) {
                             Toast.makeText(AnimalService.this, R.string.message_update_failure,
                                     Toast.LENGTH_SHORT).show();
                         }
                     }
        );
    }

    private void callResponseReturningRequest(String action, int id) {

        ServerAnimal serverAnimal = ServiceGenerator.createService(ServerAnimal.class);

        Call<ResponseBody> call;
        switch(action) {

            case "delete" :
                call = serverAnimal.delete(id);
                break;
            default :
                return;
        }

        call.enqueue(new Callback<ResponseBody>() {
                         @Override
                         public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

                             if (response.isSuccessful()) {
                                 Toast.makeText(AnimalService.this, R.string.message_update_success,
                                         Toast.LENGTH_SHORT).show();
                             } else {
                                 Toast.makeText(AnimalService.this, R.string .message_update_failure,
                                         Toast.LENGTH_SHORT).show();
                             }
                         }

                         @Override
                         public void onFailure(Call<ResponseBody> call, Throwable t) {
                             Toast.makeText(AnimalService.this, R.string.message_update_failure,
                                     Toast.LENGTH_SHORT).show();
                         }
                     }
        );
    }

    public void getAnimalList(final AnimalManager manager) {

        // Create a very simple REST adapter.
        ServerAnimal serverAnimal = ServiceGenerator.createService(ServerAnimal.class);

        Call<List<Animal>> call = serverAnimal.list();

        Toast.makeText(this, R.string.message_updating, Toast.LENGTH_SHORT).show();

        call.enqueue(new Callback<List<Animal>>() {
                         @Override
                         public void onResponse(Call<List<Animal>> call, Response<List<Animal>> response) {

                             if (response.isSuccessful()) {
                                 List<Animal> animaux = response.body();
                                 manager.updateList(animaux);

                                 Toast.makeText(AnimalService.this, R.string.message_update_success,
                                         Toast.LENGTH_SHORT).show();
                             } else {
                                 Toast.makeText(AnimalService.this, R.string.message_update_failure,
                                         Toast.LENGTH_SHORT).show();
                             }
                         }

                         @Override
                         public void onFailure(Call<List<Animal>> call, Throwable t) {
                             Toast.makeText(AnimalService.this, R.string.message_update_failure,
                                     Toast.LENGTH_SHORT).show();
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
        @GET("/zoomanji/rest/animals")
        Call<List<Animal>> list(
        );

        @GET("/zoomanji/rest/animals/{id}")
        Call<Animal> get(
                @Path("id") int id
        );

        @POST("/zoomanji/rest/animals/")
        Call<Animal> create(
                @Body Animal animal
        );

        @PUT("/zoomanji/rest/animals/")
        Call<Animal> update(
                @Body Animal animal
        );

        @DELETE("/zoomanji/rest/animals/{id}")
        Call<ResponseBody> delete(
                @Path("id") int id
        );

    }

    public static class ServiceGenerator {

        public static <T> T createService(Class<T> serviceClass) {

            String API_BASE_URL = "http://" +
                    WebService.getIP() + ":8080";

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
