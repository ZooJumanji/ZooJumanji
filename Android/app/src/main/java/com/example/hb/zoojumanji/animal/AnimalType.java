package com.example.hb.zoojumanji.animal;

import android.content.res.Resources;

import com.example.hb.zoojumanji.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hb on 08/06/2016.
 */
public enum AnimalType {
    CARNIVOROUS,
    HERBIVOROUS,
    OMNIVOROUS,
    FRUCTIVOROUS,
    GRANIVOROUS,
    INSECTIVOROUS;

    public static List<AnimalType> getAll() {
        List<AnimalType> list = new ArrayList<>();

        list.add(CARNIVOROUS);
        list.add(HERBIVOROUS);
        list.add(OMNIVOROUS);
        list.add(FRUCTIVOROUS);
        list.add(GRANIVOROUS);
        list.add(INSECTIVOROUS);

        return list;
    }

    public static AnimalType fromResource(int resource) {
        AnimalType type;
        switch (resource) {
            case R.string.animal_type_carnivorous:
                type = CARNIVOROUS;
                break;
            case R.string.animal_type_herbivorous:
                type = HERBIVOROUS;
                break;
            case R.string.animal_type_fructivorous:
                type = FRUCTIVOROUS;
                break;
            case R.string.animal_type_granivorous:
                type = GRANIVOROUS;
                break;
            case R.string.animal_type_insectivorous:
                type = INSECTIVOROUS;
                break;
            default:
                throw new Resources.NotFoundException();
        }

        return type;
    }

    public int getStringResource() {

        switch (this) {
            case CARNIVOROUS:
                return R.string.animal_type_carnivorous;
            case HERBIVOROUS:
                return R.string.animal_type_herbivorous;
            case OMNIVOROUS:
                return R.string.animal_type_omnivorous;
            case FRUCTIVOROUS:
                return R.string.animal_type_fructivorous;
            case GRANIVOROUS:
                return R.string.animal_type_granivorous;
            case INSECTIVOROUS:
                return R.string.animal_type_insectivorous;
            default:
                return R.string.Error_Not_Found_Exception;
        }
    }
}
