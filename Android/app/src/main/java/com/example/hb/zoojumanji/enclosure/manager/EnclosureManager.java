package com.example.hb.zoojumanji.enclosure.manager;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;

import com.example.hb.zoojumanji.R;
import com.example.hb.zoojumanji.enclosure.Enclosure;
import com.example.hb.zoojumanji.enclosure.EnclosureType;
import com.example.hb.zoojumanji.enclosure.activity.EnclosureActivity;
import com.example.hb.zoojumanji.enclosure.service.EnclosureService;
import com.example.hb.zoojumanji.enclosure.service.EnclosureServiceBinder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hb on 07/06/2016.
 */
public class EnclosureManager {

    protected Context context;
    private ServiceConnection connection;

    protected static Enclosure deletedEnclosure;
    protected static List<Enclosure> enclosuresList = new ArrayList<>();

    public EnclosureManager(Context context) {
        this.context = context;
    }

    public List<Enclosure> getEnclosures() {
        startBindingService();

        return cleanEnclosureList(enclosuresList);
    }

    public static List<Enclosure> cleanEnclosureList(List<Enclosure> enclosures) {
        List<Enclosure> list = new ArrayList<>();

        for (Enclosure enclosure : enclosures) {
            if ( deletedEnclosure == null ||
                    (!enclosure.equals(deletedEnclosure) && enclosure.getId() != deletedEnclosure.getId())) {
                list.add(enclosure);
            }
        }

        return list;
    }

    protected void startBindingService() {

        Intent intent = new Intent(EnclosureManager.this.context, EnclosureService
                .class);
        intent.setAction("list");

        connection = new ServiceConnection() {

            EnclosureServiceBinder serviceBinder;

            @Override
            public void onServiceConnected(ComponentName name, IBinder service) {
                serviceBinder = (EnclosureServiceBinder) service;
                serviceBinder.getEnclosureList(EnclosureManager.this);
            }

            @Override
            public void onServiceDisconnected(ComponentName name) {
            }
        };

        context.bindService(intent, connection, Context.BIND_AUTO_CREATE);
    }

    protected void startService(final String action, final int id) {

        Thread thread = new Thread() {
            @Override
            public void run() {
                Intent intent = new Intent(EnclosureManager.this.context, EnclosureService.class);
                intent.setAction(action);
                intent.putExtra("id", id);
                context.startService(intent);
            }
        };

        thread.start();
    }

    // Get Enclosure from id
    public Enclosure getEnclosure(int id) {
        List<Enclosure> list = enclosuresList;

        for (Enclosure enclosure : list) {
            if (enclosure.getId() == id) {
                return enclosure;
            }
        }

        throw new IllegalArgumentException(context.getString(R.string.exception_unknown_enclosure));
    }

    public void createEnclosure(String name, int capacity, EnclosureType type) {

        Enclosure enclosure = new Enclosure(name, capacity, type);

        /* CANCEL ANDROID ID AUTO-INCREMENT */
        enclosure.setId(0);

        startService("create", enclosure.getId());
        enclosuresList.add(enclosure);
    }

    public void deleteEnclosure(Enclosure enclosure) {
        if (enclosuresList.contains(enclosure)) {
            enclosuresList.remove(enclosure);
        }

        deletedEnclosure = enclosure;
    }

    public void restoreEnclosure() {
        if (deletedEnclosure != null && !enclosuresList.contains(deletedEnclosure)) {
            enclosuresList.add(deletedEnclosure);
        }

        deletedEnclosure = null;
        updateList(enclosuresList);
    }

    public static Boolean isInDeletion() {
        return deletedEnclosure != null;
    }

    public void cleanEnclosure() {
        startService("delete", deletedEnclosure.getId());
        deletedEnclosure = null;
    }

    public void modifyEnclosure(int id, String name, int max, EnclosureType type) {
        startService("modify", id);

        Enclosure enclosure = getEnclosure(id);
        enclosure.setName(name)
                .setMax(max)
                .setType(type);
    }

    public void updateList(List<Enclosure> enclosures) {
        enclosuresList = enclosures;

        if (context != null && context.getClass() == EnclosureActivity.class) {
            EnclosureActivity activity = (EnclosureActivity) context;
            activity.refreshList(enclosuresList);
        }
    }
}
