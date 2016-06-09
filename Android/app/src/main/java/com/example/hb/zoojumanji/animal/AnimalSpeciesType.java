package com.example.hb.zoojumanji.animal;

import android.content.res.Resources;

import com.example.hb.zoojumanji.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hb on 08/06/2016.
 */
public enum AnimalSpeciesType {
    LION,
    SURICATE,
    MONKEY,
    WARTHOG,
    TIGER,
    BIRD;

    public static List<AnimalSpeciesType> getAll() {
        List<AnimalSpeciesType> list = new ArrayList<>();

        list.add(LION);
        list.add(SURICATE);
        list.add(MONKEY);
        list.add(WARTHOG);
        list.add(TIGER);
        list.add(BIRD);

        return list;
    }

    public static AnimalSpeciesType fromResource(int resource) {
        AnimalSpeciesType type;
        switch (resource) {
            case R.string.animal_species_lion:
                type = LION;
                break;
            case R.string.animal_species_suricat:
                type = SURICATE;
                break;
            case R.string.animal_species_warthog:
                type = WARTHOG;
                break;
            case R.string.animal_species_tiger:
                type = TIGER;
                break;
            case R.string.animal_species_bird:
                type = BIRD;
                break;
            default:
                throw new Resources.NotFoundException();
        }

        return type;
    }

    public int getStringResource() {

        switch (this) {
            case LION:
                return R.string.animal_species_lion;
            case SURICATE:
                return R.string.animal_species_suricat;
            case MONKEY:
                return R.string.animal_species_monkey;
            case WARTHOG:
                return R.string.animal_species_warthog;
            case TIGER:
                return R.string.animal_species_tiger;
            case BIRD:
                return R.string.animal_species_bird;
            default:
                return R.string.Error_Not_Found_Exception;
        }
    }
}
