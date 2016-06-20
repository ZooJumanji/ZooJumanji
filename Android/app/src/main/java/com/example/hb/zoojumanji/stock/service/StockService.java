package com.example.hb.zoojumanji.stock.service;

import android.app.IntentService;
import android.content.Intent;
import android.os.IBinder;

import com.example.hb.zoojumanji.JumanjiNotification;
import com.example.hb.zoojumanji.stock.Stock;
import com.example.hb.zoojumanji.stock.manager.StockManager;

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

            Call<Stock> call;
            switch(action) {

                case "detail" :
                    call = serverStock.get(id);
                    break;
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

            call.enqueue(new Callback<Stock>() {
                             @Override
                             public void onResponse(Call<Stock> call, Response<Stock> response) {

                                 if (response.isSuccessful()) {
                                     JumanjiNotification.make(StockService.this, action +
                                             " succes : " + response.code(), 12345);
                                 } else {
                                     JumanjiNotification.make(StockService.this, action +
                                             " error : " + response.code(), 12345);
                                 }
                             }

                             @Override
                             public void onFailure(Call<Stock> call, Throwable t) {
                                 JumanjiNotification.make(StockService.this, action +
                                         " failure : " + t.getMessage(), 12345);
                             }
                         }
            );
        }
    }

    public void getStockList(final StockManager manager) {

        // Create a very simple REST adapter.
        ServerStock serverStock = ServiceGenerator.createService(ServerStock.class);

        Call<List<Stock>> call = serverStock.list();

        JumanjiNotification.make(this, "Refresh list of stocks", 1234);

        call.enqueue(new Callback<List<Stock>>() {
                         @Override
                         public void onResponse(Call<List<Stock>> call, Response<List<Stock>> response) {

                             if (response.isSuccessful()) {
                                 List<Stock> stocks = response.body();
                                 manager.updateList(stocks);
                             } else {
                                 JumanjiNotification.make(StockService.this, "Listing error :"
                                         + response.code(), 1234);
                             }
                         }

                         @Override
                         public void onFailure(Call<List<Stock>> call, Throwable t) {
                             JumanjiNotification.make(StockService.this, "Listing failure :"
                                     + t.getMessage(), 1234);
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
        Call<Stock> modify(
                @Path("id") int id,
                @Body Stock stock
        );

        @DELETE("/zoomanji/rest/stocks/{id}")
        Call<Stock> delete(
                @Path("id") int id
        );

        @POST("/zoomanji/rest/stocks/new")
        Call<Stock> add(
                @Body Stock stock
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
