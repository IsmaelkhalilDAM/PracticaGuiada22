package com.example.guiada22;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {
    FirebaseAuth auth;
    private FirebaseAuth.AuthStateListener authListener;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        auth = FirebaseAuth.getInstance();
        authListener = firebaseAuth -> {
            FirebaseUser user = firebaseAuth.getCurrentUser();
            Fragment fragment = user != null ? new CustomersFragment() : new LoginFragment();
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.fragmentContainerView, fragment)
                    .commit();
        };
    }
    @Override
    protected void onResume() {
        super.onResume();
        auth.addAuthStateListener(authListener);
    }
    @Override
    protected void onPause() {
        super.onPause();
        if (authListener != null)
            auth.removeAuthStateListener(authListener);
    }
}