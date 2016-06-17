package com.example.hb.zoojumanji.enclosure.service;

import android.os.Binder;

import com.example.hb.zoojumanji.enclosure.Enclosure;
import com.example.hb.zoojumanji.enclosure.manager.EnclosureManager;

import java.util.List;

/**
 * Created by hb on 10/06/2016.
 */
public class EnclosureServiceBinder extends Binder {

    protected EnclosureService service;

    public EnclosureServiceBinder(EnclosureService service) {
        this.service = service;
    }

    public void getEnclosureList(EnclosureManager manager) {
        service.getEnclosureList(manager);
    }
}
