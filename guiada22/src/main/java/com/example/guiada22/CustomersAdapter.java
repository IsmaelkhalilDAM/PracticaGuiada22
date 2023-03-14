package com.example.guiada22;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class CustomersAdapter extends ArrayAdapter<Customer> {
    List<Customer> objects;
    public CustomersAdapter(@NonNull Context context, @NonNull List<Customer> objects) {
        super(context, android.R.layout.simple_list_item_1 , objects);
        this.objects = objects;
    }
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null)
            convertView = LayoutInflater.from(getContext()).
                    inflate(android.R.layout.simple_list_item_1, parent, false);
        ((TextView) convertView).setText(getItem(position).customerName);
        return super.getView(position, convertView, parent);
    }
    public void removeCustomer(int key) {
        objects.removeIf(customer -> key == customer.customerNumber);
        notifyDataSetChanged();
    }
}