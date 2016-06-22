package com.example.hb.zoojumanji.animal.manager;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;

import com.example.hb.zoojumanji.R;
import com.example.hb.zoojumanji.animal.Animal;
import com.example.hb.zoojumanji.animal.AnimalSex;
import com.example.hb.zoojumanji.animal.AnimalSpecies;
import com.example.hb.zoojumanji.animal.AnimalType;
import com.example.hb.zoojumanji.animal.activity.AnimalActivity;
import com.example.hb.zoojumanji.animal.service.AnimalService;
import com.example.hb.zoojumanji.animal.service.AnimalServiceBinder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hb on 07/06/2016.
 */
public class AnimalManager {

    protected Context context;
    private ServiceConnection connection;

    protected static Animal deletedAnimal;
    protected static List<Animal> animalsList = new ArrayList<>();

    public AnimalManager(Context context) {
        this.context = context;
    }

    public List<Animal> getAnimals() {
        startBindingService();

        return cleanAnimalList(animalsList);
    }

    public static List<Animal> cleanAnimalList(List<Animal> animals) {
        List<Animal> list = new ArrayList<>();

        for (Animal animal : animals) {
            if ( deletedAnimal == null ||
                    (!animal.equals(deletedAnimal) && animal.getId() != deletedAnimal.getId())) {
                list.add(animal);
            }
        }

        return list;
    }

    protected void startBindingService() {

        Intent intent = new Intent(AnimalManager.this.context, AnimalService
                .class);
        intent.setAction("list");

        connection = new ServiceConnection() {

            AnimalServiceBinder serviceBinder;

            @Override
            public void onServiceConnected(ComponentName name, IBinder service) {
                serviceBinder = (AnimalServiceBinder) service;
                serviceBinder.getAnimalList(AnimalManager.this);
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
                Intent intent = new Intent(AnimalManager.this.context, AnimalService.class);
                intent.setAction(action);
                intent.putExtra("id", id);
                context.startService(intent);
            }
        };

        thread.start();
    }

    // Get Animal from id
    public Animal getAnimal(int id) {
        List<Animal> list = animalsList;

        for (Animal animal : list) {
            if (animal.getId() == id) {
                return animal;
            }
        }

        throw new IllegalArgumentException(context.getString(R.string.exception_unknown_animal));
    }

    public void createAnimal(String name, int age, AnimalSex sex, AnimalSpecies species, AnimalType type) {

        Animal animal = new Animal(name, age, sex, species, type);

        /* CANCEL ANDROID ID AUTO-INCREMENT */
        animal.setId(0);

        startService("create", animal.getId());
        animalsList.add(animal);
    }

    public void deleteAnimal(Animal animal) {
        if (animalsList.contains(animal)) {
            animalsList.remove(animal);
        }

        deletedAnimal = animal;
    }

    public void restoreAnimal() {
        if (deletedAnimal != null && !animalsList.contains(deletedAnimal)) {
            animalsList.add(deletedAnimal);
        }

        deletedAnimal = null;
        updateList(animalsList);
    }

    public static Boolean isInDeletion() {
        return deletedAnimal != null;
    }

    public void cleanAnimal() {
        startService("delete", deletedAnimal.getId());
        deletedAnimal = null;
    }

    public void modifyAnimal(int id, String name, int age, AnimalSex sex, AnimalSpecies species, AnimalType type) {
        startService("modify", id);

        Animal animal = getAnimal(id);
        animal.setName(name)
                .setAge(age)
                .setSex(sex)
                .setSpecies(species)
                .setType(type);
    }

    public void updateList(List<Animal> animals) {
        animalsList = animals;

        if (context != null && context.getClass() == AnimalActivity.class) {
            AnimalActivity activity = (AnimalActivity) context;
            activity.refreshList(animalsList);
        }
    }
}
