package com.example.hb.zoojumanji.stock.service;

import android.app.IntentService;
import android.content.Intent;
import android.os.IBinder;
import android.widget.Toast;

import com.example.hb.zoojumanji.JumanjiNotification;
import com.example.hb.zoojumanji.MainActivity;
import com.example.hb.zoojumanji.R;
import com.example.hb.zoojumanji.stock.Stock;
import com.example.hb.zoojumanji.stock.manager.StockManager;

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

public class StockService extends IntentService {

    StockServiceBinder binder;
    public StockService() {
        super("StockService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {

        if (intent != null) {

            final String action = intent.getAction();
            int id = intent.getIntExtra("id", 0);

            // Create a very simple REST adapter.
            ServerStock serverStock = ServiceGenerator.createService(ServerStock.class);
            StockManager manager = new StockManager(this);

            JumanjiNotification.make(this, "Action : "+action+
                    " on stock of id "+String.valueOf(id), 12345);

            Call<ResponseBody> call;
            switch(action) {

                /*
                case "detail" :
                    call = serverStock.get(id);
                    break;
                //*/
                case "create" :
                    call = serverStock.add(manager.getStock(id));
                    break;
                case "modify" :
                    call = serverStock.modify(id, manager.getStock(id));
                    break;
                case "delete" :
                    call = serverStock.delete(id);
                    break;
                default :
                    JumanjiNotification.make(StockService.this, "unknow action " + action, 12345);
                    return;
            }

            call.enqueue(new Callback<ResponseBody>() {
                             @Override
                             public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

                                 if (response.isSuccessful()) {
                                     Toast.makeText(StockService.this, R.string.message_update_success,
                                             Toast.LENGTH_SHORT).show();
                                 } else {
                                     Toast.makeText(StockService.this, R.string.message_update_failure,
                                             Toast.LENGTH_SHORT).show();
                                 }
                             }

                             @Override
                             public void onFailure(Call<ResponseBody> call, Throwable t) {
                                 Toast.makeText(StockService.this, R.string.message_update_failure,
                                         Toast.LENGTH_SHORT).show();
                             }
                         }
            );
        }
    }

    public void getStockList(final StockManager manager) {

        // Create a very simple REST adapter.
        ServerStock serverStock = ServiceGenerator.createService(ServerStock.class);

        Call<List<Stock>> call = serverStock.list();

        Toast.makeText(this, R.string.message_updating, Toast.LENGTH_SHORT).show();

        call.enqueue(new Callback<List<Stock>>() {
                         @Override
                         public void onResponse(Call<List<Stock>> call, Response<List<Stock>> response) {

                             if (response.isSuccessful()) {
                                 List<Stock> stocks = response.body();
                                 manager.updateList(stocks);

                                 Toast.makeText(StockService.this, R.string.message_update_success,
                                         Toast.LENGTH_SHORT).show();
                             } else {
                                 Toast.makeText(StockService.this, R.string.message_update_failure,
                                         Toast.LENGTH_SHORT).show();
                             }
                         }

                         @Override
                         public void onFailure(Call<List<Stock>> call, Throwable t) {
                             Toast.makeText(StockService.this, R.string.message_update_failure,
                                     Toast.LENGTH_SHORT).show();
                         }
                     }
        );
    }

    @Override
    public IBinder onBind(Intent intent) {

        binder = new StockServiceBinder(this);
        return binder;
    }

    public interface ServerStock {
        @GET("/zoomanji/rest/stocks")
        Call<List<Stock>> list(
        );

        @GET("/zoomanji/rest/stocks/{id}")
        Call<Stock> get(
                @Path("id") int id
        );

        @PUT("/zoomanji/rest/stocks/{id}")
        Call<ResponseBody> modify(
                @Path("id") int id,
                @Body Stock stock
        );

        @DELETE("/zoomanji/rest/stocks/{id}")
        Call<ResponseBody> delete(
                @Path("id") int id
        );

        @POST("/zoomanji/rest/stocks/new")
        Call<ResponseBody> add(
                @Body Stock stock
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
