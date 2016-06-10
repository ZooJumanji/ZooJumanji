package com.example.hb.zoojumanji.enclosure.manager;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;

import com.example.hb.zoojumanji.R;
import com.example.hb.zoojumanji.animal.manager.AnimalManager;
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

    // Static Enclosure List
    public static final Enclosure LION_FOSS = new Enclosure("lion foss", 2, EnclosureType.PADDOCK);
    public static final Enclosure MONKEY_CAGE = new Enclosure("Rafikki cage", 12, EnclosureType.CAGE);
    public static final Enclosure TIMON_POOL = new Enclosure("Timon pool", 4, EnclosureType.POOL);

    protected static Enclosure deletedEnclosure;
    protected static List<Enclosure> enclosuresList = new ArrayList<>();

    public EnclosureManager(Context context) {
        this.context = context;
    }

    public List<Enclosure> getEnclosures() {
        return getEnclosures(true);
    }

    public List<Enclosure> getEnclosures(boolean refresh) {

        // Initialize list if is empty
        if (enclosuresList.isEmpty()) {
            enclosuresList.add(LION_FOSS);
            enclosuresList.add(MONKEY_CAGE);
            enclosuresList.add(TIMON_POOL);

            LION_FOSS.addAnimal(AnimalManager.SIMBA);
            LION_FOSS.addAnimal(AnimalManager.NALA);
            MONKEY_CAGE.addAnimal(AnimalManager.RAFIKKI);
            TIMON_POOL.addAnimal(AnimalManager.TIMON);
            TIMON_POOL.addAnimal(AnimalManager.PUMBA);
        }

        if (refresh) {
            startBindingService();
        }

        return enclosuresList;
    }

    public void startBindingService() {

        Intent intent = new Intent(context, EnclosureService.class);
        intent.setAction("list");

        connection = new ServiceConnection() {

            @Override
            public void onServiceConnected(ComponentName name, IBinder service) {
                EnclosureServiceBinder serviceBinder = (EnclosureServiceBinder) service;
                List<Enclosure> updatedList = serviceBinder.getEnclosureList();
                if (context.getClass() == EnclosureActivity.class) {
                    EnclosureActivity activity = (EnclosureActivity) context;
                    activity.refreshList(updatedList);
                }
            }

            @Override
            public void onServiceDisconnected(ComponentName name) {
            }
        };

        context.bindService(intent, connection, Context.BIND_AUTO_CREATE);
    }

    public void startService(String action, int id) {
        Intent intent = new Intent(context, EnclosureService.class);
        intent.setAction(action);
        intent.putExtra("id", id);
        context.startService(intent);
    }

    // Get Animal from id
    public Enclosure getEnclosure(int id) {
        List<Enclosure> list = getEnclosures(false);

        startService("detail", id);

        for (Enclosure enclosure : list) {
            if (enclosure.getId() == id) {
                return enclosure;
            }
        }

        throw new IllegalArgumentException(context.getString(R.string.exception_unknown_enclosure));
    }

    public void createEnclosure(String name, int capacity, EnclosureType type) {

        Enclosure enclosure = new Enclosure(name, capacity, type);
        startService("create", enclosure.getId());
        enclosuresList.add(enclosure);
    }

    public void deleteEnclosure(Enclosure enclosure) {
        if (enclosuresList.contains(enclosure)) {
            enclosuresList.remove(enclosure);
        }

        startService("delete", enclosure.getId());
        deletedEnclosure = enclosure;
    }

    public void restoreEnclosure() {
        if (deletedEnclosure != null && !enclosuresList.contains(deletedEnclosure)) {
            enclosuresList.add(deletedEnclosure);
        }

        startService("restore", deletedEnclosure.getId());
        cleanEnclosure();
    }

    public static Boolean isInDeletion() {
        return deletedEnclosure != null;
    }

    public static void cleanEnclosure() {
        deletedEnclosure = null;
    }

    public void modifyEnclosure(int id, String name, int max, EnclosureType type) {
        startService("modify", id);

        Enclosure enclosure = getEnclosure(id);
        enclosure.setName(name)
                .setMax(max)
                .setType(type);
    }
}
