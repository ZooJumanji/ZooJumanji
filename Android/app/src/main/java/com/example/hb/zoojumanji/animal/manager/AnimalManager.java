package com.example.hb.zoojumanji.animal.manager;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;

import com.example.hb.zoojumanji.animal.Animal;
import com.example.hb.zoojumanji.animal.AnimalSex;
import com.example.hb.zoojumanji.animal.AnimalSpecies;
import com.example.hb.zoojumanji.animal.AnimalType;
import com.example.hb.zoojumanji.animal.Repository.AnimalRepository;
import com.example.hb.zoojumanji.animal.Service.AnimalService;
import com.example.hb.zoojumanji.animal.Service.AnimalServiceBinder;
import com.example.hb.zoojumanji.animal.activity.AnimalActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hb on 07/06/2016.
 */
public class AnimalManager {

    protected Context context;
    private ServiceConnection connection;

    protected static Animal deletedAnimal;
    protected static List<Animal> animalList = new ArrayList<>();

    public AnimalManager(Context context)
    {
        this.context = context;
    }

    public  List<Animal> getAnimalList(){
        startBindingService();

        AnimalRepository repo = new AnimalRepository();

        return repo.GetList();
    }

    public void startBindingService() {

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
//    public static List<Animal> getAnimals(AppCompatActivity activity) {
//
//        Intent intent = new Intent(activity, AnimalService.class);
//        activity.startService(intent);
//        ServiceConnection sc = new ServiceConnection() {
//
//            AnimalServiceBinder serviceBinder;
//
//            @Override
//            public void onServiceConnected(ComponentName name, IBinder service) {
//                serviceBinder = (AnimalServiceBinder) service;
//                animalsList = serviceBinder.getAnimalList(AnimalManager.this);
//            }
//
//            @Override
//            public void onServiceDisconnected(ComponentName name) {
//                // Returned IBinder is no longer usable...
//            }
//        };
//        activity.bindService(intent, sc, 0);
//
//        // Initialize list if is empty
////        if (animalsList.isEmpty()) {
////            animalsList.add(SIMBA);
////            animalsList.add(TIMON);
////            animalsList.add(PUMBA);
////            animalsList.add(NALA);
////            animalsList.add(RAFIKKI);
////        }
//        return animalsList;
//    }

    // Get Animal from id
    public static Animal getAnimal(int id) {
//        List<Animal> list = getAnimals(this);
//        for (Animal animal : list) {
//            if (animal.getId() == id) {
//                return animal;
//            }
//        }
//
//        throw new IllegalArgumentException(Resources.getSystem().getString(R.string.exception_unknown_animal));
        return null;
    }

    public static void createAnimal(String name, int age, AnimalSex sex, AnimalSpecies species, AnimalType type) {
        animalList.add(new Animal(name, age, sex, species, type));
    }

    public static void deleteAnimal(Animal animal) {
        if (animalList.contains(animal)) {
            animalList.remove(animal);
        }

        deletedAnimal = animal;
    }

    public static void restoreAnimal() {
        if (deletedAnimal != null && !animalList.contains(deletedAnimal)) {
            animalList.add(deletedAnimal);
        }

        cleanEnclosure();
    }

    public static Boolean isInDeletion() {
        return deletedAnimal != null;
    }

    public static void cleanEnclosure() {
        deletedAnimal = null;
    }

    public static void modifyAnimal(int id, String name, int age, AnimalSex sex, AnimalSpecies species, AnimalType type) {
//        Animal animal = getAnimal(id);
//        animal.setName(name)
//                .setAge(age)
//                .setSex(sex)
//                .setSpecies(species)
//                .setType(type);
    }
    public void updateList(List<Animal> Animaux) {
        animalList = Animaux;

        if (context != null && context.getClass() ==AnimalActivity.class) {
            AnimalActivity activity = (AnimalActivity) context;
            activity.refreshList(animalList);
        }
    }

}
