package com.rebel.myloginapp;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class HistoryActivity extends AppCompatActivity {

    private FirebaseFirestore db;
    private SharedPreferences sharedPreferences;

    private EditText editTextNombres, editTextApellidos, editTextEdad, editTextTalla, editTextPeso,
            editTextIMC, editTextTemperatura, editTextFrecuenciaRespiratoria, editTextFrecuenciaCardiaca,
            editTextTensionArterial, editTextTabaquismo, editTextAlcoholismo, editTextSedentarismo,
            editTextHabitosAlimenticios, editTextTipoDiabetes, editTextHipertensionArterial, editTextDislipidemia,
            editTextObesidad, editTextControlGlicemico, editTextManejoPieDiabetico, editTextControlComorbilidades;
    private Button buttonGuardar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_historial);

        // Inicializar Firestore
        db = FirebaseFirestore.getInstance();

        // Inicialización de los EditTexts
        editTextNombres = findViewById(R.id.editTextNombres);
        editTextApellidos = findViewById(R.id.editTextApellidos);
        editTextEdad = findViewById(R.id.editTextEdad);
        editTextTalla = findViewById(R.id.editTextTalla);
        editTextPeso = findViewById(R.id.editTextPeso);
        editTextIMC = findViewById(R.id.editTextIMC);
        editTextTemperatura = findViewById(R.id.editTextTemperatura);
        editTextFrecuenciaRespiratoria = findViewById(R.id.editTextFrecuenciaRespiratoria);
        editTextFrecuenciaCardiaca = findViewById(R.id.editTextFrecuenciaCardiaca);
        editTextTensionArterial = findViewById(R.id.editTextTensionArterial);
        editTextTabaquismo = findViewById(R.id.editTextTabaquismo);
        editTextAlcoholismo = findViewById(R.id.editTextAlcoholismo);
        editTextSedentarismo = findViewById(R.id.editTextSedentarismo);
        editTextHabitosAlimenticios = findViewById(R.id.editTextHabitosAlimenticios);
        editTextTipoDiabetes = findViewById(R.id.editTextTipoDiabetes);
        editTextHipertensionArterial = findViewById(R.id.editTextHipertensionArterial);
        editTextDislipidemia = findViewById(R.id.editTextDislipidemia);
        editTextObesidad = findViewById(R.id.editTextObesidad);
        editTextControlGlicemico = findViewById(R.id.editTextControlGlicemico);
        editTextManejoPieDiabetico = findViewById(R.id.editTextManejoPieDiabetico);
        editTextControlComorbilidades = findViewById(R.id.editTextControlComorbilidades);
        buttonGuardar = findViewById(R.id.buttonGuardar);

        // Obtener las preferencias compartidas
        sharedPreferences = getSharedPreferences("UserData", Context.MODE_PRIVATE);

        // Verificamos si existe un historial clínico previamente guardado en SharedPreferences
        if (historialYaGuardado()) {
            String documentId = sharedPreferences.getString("documentId", null);
            if (documentId != null) {
                cargarHistorialDesdeFirestore(documentId);
            } else {
                Toast.makeText(this, "No se encontró el ID del historial.", Toast.LENGTH_SHORT).show();
            }
        }

        buttonGuardar.setOnClickListener(v -> guardarHistorialClinico());
    }

    private void guardarHistorialClinico() {
        // Validar que todos los campos estén llenos
        if (camposLlenos()) {
            // Guardar en SharedPreferences
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("nombres", editTextNombres.getText().toString());
            editor.putString("apellidos", editTextApellidos.getText().toString());
            editor.putString("edad", editTextEdad.getText().toString());
            editor.putString("talla", editTextTalla.getText().toString());
            editor.putString("peso", editTextPeso.getText().toString());
            editor.putString("imc", editTextIMC.getText().toString());
            editor.putString("temperatura", editTextTemperatura.getText().toString());
            editor.putString("frecuenciaRespiratoria", editTextFrecuenciaRespiratoria.getText().toString());
            editor.putString("frecuenciaCardiaca", editTextFrecuenciaCardiaca.getText().toString());
            editor.putString("tensionArterial", editTextTensionArterial.getText().toString());
            editor.putString("tabaquismo", editTextTabaquismo.getText().toString());
            editor.putString("alcoholismo", editTextAlcoholismo.getText().toString());
            editor.putString("sedentarismo", editTextSedentarismo.getText().toString());
            editor.putString("habitosAlimenticios", editTextHabitosAlimenticios.getText().toString());
            editor.putString("tipoDiabetes", editTextTipoDiabetes.getText().toString());
            editor.putString("hipertensionArterial", editTextHipertensionArterial.getText().toString());
            editor.putString("dislipidemia", editTextDislipidemia.getText().toString());
            editor.putString("obesidad", editTextObesidad.getText().toString());
            editor.putString("controlGlicemico", editTextControlGlicemico.getText().toString());
            editor.putString("manejoPieDiabetico", editTextManejoPieDiabetico.getText().toString());
            editor.putString("controlComorbilidades", editTextControlComorbilidades.getText().toString());
            editor.apply();

            // Guardar en Firebase
            Map<String, String> historialClinico = new HashMap<>();
            historialClinico.put("nombres", editTextNombres.getText().toString());
            historialClinico.put("apellidos", editTextApellidos.getText().toString());
            historialClinico.put("edad", editTextEdad.getText().toString());
            historialClinico.put("talla", editTextTalla.getText().toString());
            historialClinico.put("peso", editTextPeso.getText().toString());
            historialClinico.put("imc", editTextIMC.getText().toString());
            historialClinico.put("temperatura", editTextTemperatura.getText().toString());
            historialClinico.put("frecuenciaRespiratoria", editTextFrecuenciaRespiratoria.getText().toString());
            historialClinico.put("frecuenciaCardiaca", editTextFrecuenciaCardiaca.getText().toString());
            historialClinico.put("tensionArterial", editTextTensionArterial.getText().toString());
            historialClinico.put("tabaquismo", editTextTabaquismo.getText().toString());
            historialClinico.put("alcoholismo", editTextAlcoholismo.getText().toString());
            historialClinico.put("sedentarismo", editTextSedentarismo.getText().toString());
            historialClinico.put("habitosAlimenticios", editTextHabitosAlimenticios.getText().toString());
            historialClinico.put("tipoDiabetes", editTextTipoDiabetes.getText().toString());
            historialClinico.put("hipertensionArterial", editTextHipertensionArterial.getText().toString());
            historialClinico.put("dislipidemia", editTextDislipidemia.getText().toString());
            historialClinico.put("obesidad", editTextObesidad.getText().toString());
            historialClinico.put("controlGlicemico", editTextControlGlicemico.getText().toString());
            historialClinico.put("manejoPieDiabetico", editTextManejoPieDiabetico.getText().toString());
            historialClinico.put("controlComorbilidades", editTextControlComorbilidades.getText().toString());

            db.collection("historiales").add(historialClinico)
                    .addOnSuccessListener(documentReference -> {
                        // Guardar el ID del documento en SharedPreferences
                        SharedPreferences.Editor sharedPreferencesEditor = sharedPreferences.edit();
                        sharedPreferencesEditor.putString("documentId", documentReference.getId());
                        sharedPreferencesEditor.putBoolean("historialGuardado", true);
                        sharedPreferencesEditor.apply();

                        Toast.makeText(this, "Historial guardado con éxito", Toast.LENGTH_SHORT).show();
                        disableForm();
                    })
                    .addOnFailureListener(e -> Toast.makeText(this, "Error al guardar el historial", Toast.LENGTH_SHORT).show());
        } else {
            Toast.makeText(this, "Por favor, completa todos los campos.", Toast.LENGTH_SHORT).show();
        }
    }

    private void cargarHistorialDesdeFirestore(String documentId) {
        db.collection("historiales").document(documentId)
                .get()
                .addOnSuccessListener(documentSnapshot -> {
                    if (documentSnapshot.exists()) {
                        editTextNombres.setText(documentSnapshot.getString("nombres"));
                        editTextApellidos.setText(documentSnapshot.getString("apellidos"));
                        editTextEdad.setText(documentSnapshot.getString("edad"));
                        editTextTalla.setText(documentSnapshot.getString("talla"));
                        editTextPeso.setText(documentSnapshot.getString("peso"));
                        editTextIMC.setText(documentSnapshot.getString("imc"));
                        editTextTemperatura.setText(documentSnapshot.getString("temperatura"));
                        editTextFrecuenciaRespiratoria.setText(documentSnapshot.getString("frecuenciaRespiratoria"));
                        editTextFrecuenciaCardiaca.setText(documentSnapshot.getString("frecuenciaCardiaca"));
                        editTextTensionArterial.setText(documentSnapshot.getString("tensionArterial"));
                        editTextTabaquismo.setText(documentSnapshot.getString("tabaquismo"));
                        editTextAlcoholismo.setText(documentSnapshot.getString("alcoholismo"));
                        editTextSedentarismo.setText(documentSnapshot.getString("sedentarismo"));
                        editTextHabitosAlimenticios.setText(documentSnapshot.getString("habitosAlimenticios"));
                        editTextTipoDiabetes.setText(documentSnapshot.getString("tipoDiabetes"));
                        editTextHipertensionArterial.setText(documentSnapshot.getString("hipertensionArterial"));
                        editTextDislipidemia.setText(documentSnapshot.getString("dislipidemia"));
                        editTextObesidad.setText(documentSnapshot.getString("obesidad"));
                        editTextControlGlicemico.setText(documentSnapshot.getString("controlGlicemico"));
                        editTextManejoPieDiabetico.setText(documentSnapshot.getString("manejoPieDiabetico"));
                        editTextControlComorbilidades.setText(documentSnapshot.getString("controlComorbilidades"));

                        disableForm();
                    } else {
                        Toast.makeText(this, "El historial no existe.", Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnFailureListener(e -> Toast.makeText(this, "Error al cargar el historial.", Toast.LENGTH_SHORT).show());
    }

    private boolean historialYaGuardado() {
        return sharedPreferences.getBoolean("historialGuardado", false);
    }

    private void disableForm() {
        editTextNombres.setEnabled(false);
        editTextApellidos.setEnabled(false);
        editTextEdad.setEnabled(false);
        editTextTalla.setEnabled(false);
        editTextPeso.setEnabled(false);
        editTextIMC.setEnabled(false);
        editTextTemperatura.setEnabled(false);
        editTextFrecuenciaRespiratoria.setEnabled(false);
        editTextFrecuenciaCardiaca.setEnabled(false);
        editTextTensionArterial.setEnabled(false);
        editTextTabaquismo.setEnabled(false);
        editTextAlcoholismo.setEnabled(false);
        editTextSedentarismo.setEnabled(false);
        editTextHabitosAlimenticios.setEnabled(false);
        editTextTipoDiabetes.setEnabled(false);
        editTextHipertensionArterial.setEnabled(false);
        editTextDislipidemia.setEnabled(false);
        editTextObesidad.setEnabled(false);
        editTextControlGlicemico.setEnabled(false);
        editTextManejoPieDiabetico.setEnabled(false);
        editTextControlComorbilidades.setEnabled(false);

        buttonGuardar.setEnabled(false);
    }

    private boolean camposLlenos() {
        return !editTextNombres.getText().toString().isEmpty()
                && !editTextApellidos.getText().toString().isEmpty()
                && !editTextEdad.getText().toString().isEmpty()
                && !editTextTalla.getText().toString().isEmpty()
                && !editTextPeso.getText().toString().isEmpty()
                && !editTextIMC.getText().toString().isEmpty()
                && !editTextTemperatura.getText().toString().isEmpty()
                && !editTextFrecuenciaRespiratoria.getText().toString().isEmpty()
                && !editTextFrecuenciaCardiaca.getText().toString().isEmpty()
                && !editTextTensionArterial.getText().toString().isEmpty()
                && !editTextTabaquismo.getText().toString().isEmpty()
                && !editTextAlcoholismo.getText().toString().isEmpty()
                && !editTextSedentarismo.getText().toString().isEmpty()
                && !editTextHabitosAlimenticios.getText().toString().isEmpty()
                && !editTextTipoDiabetes.getText().toString().isEmpty()
                && !editTextHipertensionArterial.getText().toString().isEmpty()
                && !editTextDislipidemia.getText().toString().isEmpty()
                && !editTextObesidad.getText().toString().isEmpty()
                && !editTextControlGlicemico.getText().toString().isEmpty()
                && !editTextManejoPieDiabetico.getText().toString().isEmpty()
                && !editTextControlComorbilidades.getText().toString().isEmpty();
    }


}
