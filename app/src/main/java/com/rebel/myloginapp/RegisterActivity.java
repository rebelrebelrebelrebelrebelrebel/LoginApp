package com.rebel.myloginapp;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class RegisterActivity extends AppCompatActivity {

    private EditText editTextRegisterFullName, editTextRegisterEmail, editTextRegisterDoB, editTextRegisterMobile, editTextRegisterPwd, editTextRegisterConfirmPwd;
    private ProgressBar progressBar;
    private RadioGroup radioGroupRegisterGender;
    private RadioButton radioButtonRegisterGenderSelected;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        // Set Title
        getSupportActionBar().setTitle("Register");

        // Initialize UI elements
        progressBar = findViewById(R.id.progressBar);
        editTextRegisterFullName = findViewById(R.id.editText_register_full_name);
        editTextRegisterEmail = findViewById(R.id.editText_register_email);
        editTextRegisterDoB = findViewById(R.id.editText_register_dob);
        editTextRegisterMobile = findViewById(R.id.editText_register_mobile);
        editTextRegisterPwd = findViewById(R.id.editText_register_password);
        editTextRegisterConfirmPwd = findViewById(R.id.editText_register_confirm_password);
        radioGroupRegisterGender = findViewById(R.id.radio_group_register_gender);

        // Add TextWatchers to clear fields on focus
        setClearTextOnFocus(editTextRegisterFullName);
        setClearTextOnFocus(editTextRegisterEmail);
        setClearTextOnFocus(editTextRegisterDoB);
        setClearTextOnFocus(editTextRegisterMobile);
        setClearTextOnFocus(editTextRegisterPwd);
        setClearTextOnFocus(editTextRegisterConfirmPwd);

        Button buttonRegister = findViewById(R.id.button_register);
        buttonRegister.setOnClickListener(view -> {
            int selectGenderId = radioGroupRegisterGender.getCheckedRadioButtonId();
            radioButtonRegisterGenderSelected = findViewById(selectGenderId);

            // Obtain the entered data
            String textFullName = editTextRegisterFullName.getText().toString().trim();
            String textEmail = editTextRegisterEmail.getText().toString().trim();
            String textDoB = editTextRegisterDoB.getText().toString().trim();
            String textMobile = editTextRegisterMobile.getText().toString().trim();
            String textPwd = editTextRegisterPwd.getText().toString().trim();
            String textConfirmPwd = editTextRegisterConfirmPwd.getText().toString().trim();
            String textGender = (radioButtonRegisterGenderSelected != null) ? radioButtonRegisterGenderSelected.getText().toString() : null;

            // Validate input
            if (TextUtils.isEmpty(textFullName)) {
                showError(editTextRegisterFullName, "Se requiere nombre completo");
            } else if (TextUtils.isEmpty(textEmail)) {
                showError(editTextRegisterEmail, "Se requiere Email");
            } else if (!Patterns.EMAIL_ADDRESS.matcher(textEmail).matches()) {
                showError(editTextRegisterEmail, "Favor de re-ingresar su email");
            } else if (TextUtils.isEmpty(textDoB)) {
                showError(editTextRegisterDoB, "Se requiere fecha de nacimiento");
            } else if (selectGenderId == -1) {
                Toast.makeText(RegisterActivity.this, "Favor de seleccionar su género", Toast.LENGTH_LONG).show();
            } else if (TextUtils.isEmpty(textMobile)) {
                showError(editTextRegisterMobile, "Se requiere número telefónico");
            } else if (textMobile.length() != 10) {
                showError(editTextRegisterMobile, "Número telefónico debe ser de 10 dígitos");
            } else if (TextUtils.isEmpty(textPwd)) {
                showError(editTextRegisterPwd, "Se requiere contraseña");
            } else if (textPwd.length() < 6) {
                showError(editTextRegisterPwd, "Contraseña debe ser de 6 dígitos mínimo");
            } else if (TextUtils.isEmpty(textConfirmPwd)) {
                showError(editTextRegisterConfirmPwd, "Se requiere confirmación de contraseña");
            } else if (!textPwd.equals(textConfirmPwd)) {
                showError(editTextRegisterConfirmPwd, "Las contraseñas no coinciden");
                editTextRegisterPwd.clearComposingText();
                editTextRegisterConfirmPwd.clearComposingText();
            } else {
                progressBar.setVisibility(View.VISIBLE);
                registerUser(textFullName, textEmail, textDoB, textGender, textMobile, textPwd);
            }
        });
    }

    private void showError(EditText editText, String message) {
        Toast.makeText(RegisterActivity.this, message, Toast.LENGTH_LONG).show();
        editText.setError(message);
        editText.requestFocus();
    }

    // Register User using the credentials given
    private void registerUser(String textFullName, String textEmail, String textDoB, String textGender, String textMobile, String textPwd) {
        FirebaseAuth auth = FirebaseAuth.getInstance();
        auth.createUserWithEmailAndPassword(textEmail, textPwd).addOnCompleteListener(RegisterActivity.this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                progressBar.setVisibility(View.GONE); // Hide progress bar after registration attempt
                if (task.isSuccessful()) {
                    Toast.makeText(RegisterActivity.this, "Usuario registrado correctamente", Toast.LENGTH_LONG).show();
                    FirebaseUser firebaseUser = auth.getCurrentUser();
                    // Send Verification Email
                    if (firebaseUser != null) {
                        firebaseUser.sendEmailVerification();
                    }

                    // Optionally navigate to User Profile Activity
                    // Intent intent = new Intent(RegisterActivity.this, UserProfileActivity.class);
                    // startActivity(intent);
                    // finish();
                } else {
                    Toast.makeText(RegisterActivity.this, "Registro fallido: " + task.getException().getMessage(), Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private void setClearTextOnFocus(EditText editText) {
        editText.setOnFocusChangeListener((v, hasFocus) -> {
            if (hasFocus && TextUtils.isEmpty(editText.getText().toString().trim())) {
                editText.setText(""); // Borrar el texto solo si está vacío
            }
        });
    }
}
