package com.example.hb.zoojumanji.animal;

import android.content.res.Resources;

import com.example.hb.zoojumanji.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hb on 08/06/2016.
 */
public enum AnimalSexType  {
    MALE,
    FEMALE,
    HERMAPHRODITE,
    ASSEXUAL;

    public static List<AnimalSexType> getAll() {
        List<AnimalSexType> list = new ArrayList<>();

        list.add(MALE);
        list.add(FEMALE);
        list.add(HERMAPHRODITE);
        list.add(ASSEXUAL);

        return list;
    }

    public static AnimalSexType fromResource(int resource) {
        AnimalSexType type;
        switch (resource) {
            case R.string.animal_sex_male:
                type = MALE;
                break;
            case R.string.animal_sex_female:
                type = FEMALE;
                break;
            case R.string.animal_sex_hermaphrodite:
                type = HERMAPHRODITE;
                break;
            case R.string.animal_sex_assexual:
                type = ASSEXUAL;
                break;
            default:
                throw new Resources.NotFoundException();
        }

        return type;
    }

    public int getStringResource() {

        switch (this) {
            case MALE:
                return R.string.animal_sex_male;
            case FEMALE:
                return R.string.animal_sex_female;
            case HERMAPHRODITE:
                return R.string.animal_sex_hermaphrodite;
            case ASSEXUAL:
                return R.string.animal_sex_assexual;
            default:
                return R.string.Error_Not_Found_Exception;
        }
    }
}
