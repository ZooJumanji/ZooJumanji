package com.example.hb.zoojumanji.animal.Service;

import android.os.Binder;

import com.example.hb.zoojumanji.animal.manager.AnimalManager;

/**
 * Created by isher on 17/06/2016.
 */

public class AnimalServiceBinder extends Binder {

    protected AnimalService service;

    public AnimalServiceBinder(AnimalService service) {
        this.service = service;
    }

    public void getAnimalList(AnimalManager manager) {
        service.getAnimalList(manager);
    }
}