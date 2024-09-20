package com.rebel.myloginapp;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

import java.util.Objects;

public class RegisterActivity extends MenuMainActivity {

    private EditText editTextEmail, editTextPassword;
    private ProgressBar progressBar;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        // Initialize Firebase Auth
        auth = FirebaseAuth.getInstance();

        // Initialize UI elements
        editTextEmail = findViewById(R.id.editText_register_email);
        editTextPassword = findViewById(R.id.editText_register_password);
        progressBar = findViewById(R.id.progressBar);

        Button buttonRegister = findViewById(R.id.button_register);
        buttonRegister.setOnClickListener(v -> registerUser());
    }

    private void registerUser() {
        String email = editTextEmail.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();

        // Validate email
        if (TextUtils.isEmpty(email) || !Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            showError(editTextEmail, "Se requiere un email válido");
            return;
        }

        // Validate password
        if (TextUtils.isEmpty(password)) {
            showError(editTextPassword, "Se requiere contraseña");
            return;
        }

        // Show progress bar
        progressBar.setVisibility(View.VISIBLE);

        // Register the user
        auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, task -> {
                    progressBar.setVisibility(View.GONE);
                    if (task.isSuccessful()) {
                        Toast.makeText(RegisterActivity.this, "Registro exitoso", Toast.LENGTH_SHORT).show();
                        // Optionally redirect to LoginActivity or MainActivity
                        // Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
                        // startActivity(intent);
                        // finish();
                    } else {
                        Toast.makeText(RegisterActivity.this, "Registro fallido: " + Objects.requireNonNull(task.getException()).getMessage(), Toast.LENGTH_LONG).show();
                    }
                });
    }

    private void showError(EditText editText, String message) {
        editText.setError(message);
        editText.requestFocus();
    }
}
