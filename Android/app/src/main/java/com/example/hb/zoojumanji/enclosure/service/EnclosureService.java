package com.example.hb.zoojumanji.enclosure.service;

import android.app.IntentService;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.support.v4.app.NotificationCompat;

import com.example.hb.zoojumanji.R;
import com.example.hb.zoojumanji.enclosure.Enclosure;
import com.example.hb.zoojumanji.enclosure.manager.EnclosureManager;

import java.util.ArrayList;
import java.util.List;

public class EnclosureService extends IntentService {

    public EnclosureService() {
        super("EnclosureService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {

        if (intent != null) {

            String action = intent.getAction();
            int id = intent.getIntExtra("id", 0);

            NotificationCompat.Builder builder = new NotificationCompat.Builder(this)
                    .setSmallIcon(R.mipmap.ic_launcher);

            builder.setContentTitle("Jumanji notification");
            builder.setContentText("Action : "+action+
                    " on enclosure of id "+String.valueOf(id));

            NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
            manager.notify(1234567890, builder.build());
        }
    }

    public List<Enclosure> getEnclosureList() {

        EnclosureManager enclosureManager = new EnclosureManager(this);
        List<Enclosure> list = enclosureManager.getEnclosures(false);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this)
                .setSmallIcon(R.mipmap.ic_launcher);

        builder.setContentTitle("Jumanji notification");
        builder.setContentText("List count : "+String.valueOf(list.size()));

        NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        manager.notify(1234, builder.build());

        List<Enclosure> serviceList = new ArrayList<>();
        for (Enclosure enclosure : list) {
            serviceList.add(enclosure);
            serviceList.add(enclosure);
        }

        try {
            Thread.sleep(3 * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return serviceList;
    }

    @Override
    public IBinder onBind(Intent intent) {

        IBinder binder = new EnclosureServiceBinder(this);
        return binder;
    }
}
