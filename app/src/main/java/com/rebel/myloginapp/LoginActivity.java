package com.rebel.myloginapp;

import android.content.Intent;
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

public class LoginActivity extends MenuMainActivity {

    private EditText editTextLoginEmail, editTextLoginPassword;
    private ProgressBar progressBar;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Initialize Firebase Auth instance
        auth = FirebaseAuth.getInstance();

        // Initialize UI elements
        editTextLoginEmail = findViewById(R.id.editText_login_email);
        editTextLoginPassword = findViewById(R.id.editText_login_password);
        progressBar = findViewById(R.id.progressBar);

        Button buttonLogin = findViewById(R.id.button_login);
        buttonLogin.setOnClickListener(v -> loginUser());
    }

    private void loginUser() {
        String email = editTextLoginEmail.getText().toString().trim();
        String password = editTextLoginPassword.getText().toString().trim();

        // Validar el correo electrónico
        if (TextUtils.isEmpty(email) || !Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            showError(editTextLoginEmail, "Se requiere un email válido");
            return;
        }

        // Validar la contraseña
        if (TextUtils.isEmpty(password)) {
            showError(editTextLoginPassword, "Se requiere contraseña");
            return;
        }

        // Mostrar barra de progreso y deshabilitar el botón de inicio de sesión
        progressBar.setVisibility(View.VISIBLE);
        Button buttonLogin = findViewById(R.id.button_login);
        buttonLogin.setEnabled(false);

        // Iniciar sesión
        auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, task -> {
                    progressBar.setVisibility(View.GONE);
                    buttonLogin.setEnabled(true);
                    if (task.isSuccessful()) {
                        // Inicio de sesión exitoso
                        Toast.makeText(LoginActivity.this, "Inicio de sesión exitoso", Toast.LENGTH_SHORT).show();

                        // Iniciar HomeActivity
                        Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                        startActivity(intent);
                        finish(); // Opcional: termina LoginActivity para que no se pueda volver a ella
                    } else {
                        // Si el inicio de sesión falla, muestra un mensaje al usuario
                        Toast.makeText(LoginActivity.this, "Inicio de sesión fallido: " + Objects.requireNonNull(task.getException()).getMessage(), Toast.LENGTH_LONG).show();
                    }
                });
    }

    private void showError(EditText editText, String message) {
        editText.setError(message);
        editText.requestFocus();
    }
}
