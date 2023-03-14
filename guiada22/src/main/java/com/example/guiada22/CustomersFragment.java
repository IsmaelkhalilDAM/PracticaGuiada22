package com.example.guiada22;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.ListFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Spinner;

import com.google.firebase.database.FirebaseDatabase;

import java.util.*;

public class CustomersFragment extends ListFragment {
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    QueryCountries queryCountries;
    QueryCustomers queryCustomers;
    CustomersAdapter customersAdapter;
    CountriesAdapter countriesAdapter;
    Spinner spinner;
    public CustomersFragment() {}
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_customers, container, false);
        spinner = v.findViewById(R.id.spinner);
        countriesAdapter = new CountriesAdapter(getActivity(),
                new ArrayList<String>());
        spinner.setAdapter(countriesAdapter);
        spinner.setOnItemSelectedListener(
                new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView,
                                               View view, int i, long l) {
                        queryCustomers.query((String) adapterView.getItemAtPosition(i));
                    }
                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {}
                });
        queryCountries = new QueryCountries(database, countriesAdapter);
        customersAdapter = new CustomersAdapter(getContext(), new LinkedList<>());
        setListAdapter(customersAdapter);
        queryCustomers = new QueryCustomers(database, customersAdapter);
        return v;
    }
    @Override
    public void onResume() {
        super.onResume();
        queryCountries.query();
        queryCustomers.query((String) spinner.getSelectedItem());
    }
    @Override
    public void onPause() {
        super.onPause();
        queryCountries.removeListener();
        queryCustomers.removeListener();
    }
}