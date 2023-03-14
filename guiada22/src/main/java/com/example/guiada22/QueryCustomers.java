package com.example.guiada22;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

public class QueryCustomers implements ChildEventListener {
    FirebaseDatabase db;
    CustomersAdapter adapter;
    Query query;
    public QueryCustomers(FirebaseDatabase db, CustomersAdapter adapter) {
        this.db = db;
        this.adapter = adapter;
    }
    public void query(String country) {
        removeListener();
        adapter.clear();
        query = db.getReference("ClassicModelsV2/customers").
                orderByChild("country").equalTo(country);
        query.addChildEventListener(this);
    }
    public void removeListener() {
        if (query != null)
            query.removeEventListener(this);
    }
    @Override
    public void onChildAdded(@NonNull DataSnapshot snapshot,
                             @Nullable String previousChildName) {
        Customer customer = snapshot.getValue(Customer.class);
        customer.customerNumber = Integer.parseInt(snapshot.getKey());
        adapter.add(customer);
    }
    @Override
    public void onChildChanged(@NonNull DataSnapshot snapshot,
                               @Nullable String previousChildName) {}
    @Override
    public void onChildRemoved(@NonNull DataSnapshot snapshot) {}
    @Override
    public void onChildMoved(@NonNull DataSnapshot snapshot,
                             @Nullable String previousChildName) {}
    @Override
    public void onCancelled(@NonNull DatabaseError error) {}
}


