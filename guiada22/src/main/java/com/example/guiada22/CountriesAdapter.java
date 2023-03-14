package com.example.guiada22;

import android.content.Context;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;

import java.util.List;

public class CountriesAdapter extends ArrayAdapter<String> {
    List<String> objects;
    public CountriesAdapter(@NonNull Context context, @NonNull List<String> objects) {
        super(context, android.R.layout.simple_spinner_item , objects);
        this.objects = objects;
        setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    }
    public void addPais(String country) {
        if (!objects.contains(country))
            add(country);
    }
    public void removePais(String key) {
        objects.removeIf(key::equals);
        notifyDataSetChanged();
    }
}
