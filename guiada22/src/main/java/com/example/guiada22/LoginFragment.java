package com.example.guiada22;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

public class LoginFragment extends Fragment {
    private EditText emailEdit;
    private EditText passwordEdit;
    private TextView statusText;
    public LoginFragment() {}
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_login, container, false);
        emailEdit = v.findViewById(R.id.emailEdit);
        passwordEdit = v.findViewById(R.id.passwordEdit);
        statusText = v.findViewById(R.id.statusText);
        v.findViewById(R.id.loginButton).setOnClickListener(this::signIn);
        v.findViewById(R.id.signUpButton).setOnClickListener(this::createAccount);
        return v;
    }
    public void signIn(View v) {
        String email = emailEdit.getText().toString();
        String password = passwordEdit.getText().toString();
        ((MainActivity) getActivity())
                .auth
                .signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(getActivity(),
                        task -> {
                            if (!task.isSuccessful())
                                statusText.setText("Inicio de sesión incorrecto");
                            else {
                                getActivity()
                                        .getSupportFragmentManager()
                                        .beginTransaction()
                                        .replace(R.id.fragmentContainerView, new CustomersFragment())
                                        .commit();
                            }
                        });
    }
    public void createAccount(View v) {
        String email = emailEdit.getText().toString();
        String password = passwordEdit.getText().toString();
        if (email.length() == 0) {
            emailEdit.setError("Escriba una dirección de correo");
            return;
        }
        if (password.length() < 6) {
            passwordEdit.setError("La contraseña debe tener al menos 6 caracteres");
            return;
        }
        ((MainActivity) getActivity()).auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(getActivity(),
                        task -> {
                            if (!task.isSuccessful())
                                statusText.setText("La cuenta no se ha creado");
                            else {
                                getActivity()
                                        .getSupportFragmentManager()
                                        .beginTransaction()
                                        .replace(R.id.fragmentContainerView, new CustomersFragment())
                                        .commit();
                            }
                        });
    }
}
