package com.rebel.myloginapp;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class HistoryActivity extends AppCompatActivity {

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
        loadHistorialClinico();

        buttonGuardar.setOnClickListener(v -> guardarHistorialClinico());
    }

    private void loadHistorialClinico() {
        // Cargamos los datos del historial clínico desde las SharedPreferences (si existen)
        String nombres = sharedPreferences.getString("nombres", "");
        String apellidos = sharedPreferences.getString("apellidos", "");
        String edad = sharedPreferences.getString("edad", "");
        String talla = sharedPreferences.getString("talla", "");
        String peso = sharedPreferences.getString("peso", "");
        String imc = sharedPreferences.getString("imc", "");
        String temperatura = sharedPreferences.getString("temperatura", "");
        String frecuenciaRespiratoria = sharedPreferences.getString("frecuenciaRespiratoria", "");
        String frecuenciaCardiaca = sharedPreferences.getString("frecuenciaCardiaca", "");
        String tensionArterial = sharedPreferences.getString("tensionArterial", "");
        String tabaquismo = sharedPreferences.getString("tabaquismo", "");
        String alcoholismo = sharedPreferences.getString("alcoholismo", "");
        String sedentarismo = sharedPreferences.getString("sedentarismo", "");
        String habitosAlimenticios = sharedPreferences.getString("habitosAlimenticios", "");
        String tipoDiabetes = sharedPreferences.getString("tipoDiabetes", "");
        String hipertensionArterial = sharedPreferences.getString("hipertensionArterial", "");
        String dislipidemia = sharedPreferences.getString("dislipidemia", "");
        String obesidad = sharedPreferences.getString("obesidad", "");
        String controlGlicemico = sharedPreferences.getString("controlGlicemico", "");
        String manejoPieDiabetico = sharedPreferences.getString("manejoPieDiabetico", "");
        String controlComorbilidades = sharedPreferences.getString("controlComorbilidades", "");

        // Establecer los valores en los EditTexts
        editTextNombres.setText(nombres);
        editTextApellidos.setText(apellidos);
        editTextEdad.setText(edad);
        editTextTalla.setText(talla);
        editTextPeso.setText(peso);
        editTextIMC.setText(imc);
        editTextTemperatura.setText(temperatura);
        editTextFrecuenciaRespiratoria.setText(frecuenciaRespiratoria);
        editTextFrecuenciaCardiaca.setText(frecuenciaCardiaca);
        editTextTensionArterial.setText(tensionArterial);
        editTextTabaquismo.setText(tabaquismo);
        editTextAlcoholismo.setText(alcoholismo);
        editTextSedentarismo.setText(sedentarismo);
        editTextHabitosAlimenticios.setText(habitosAlimenticios);
        editTextTipoDiabetes.setText(tipoDiabetes);
        editTextHipertensionArterial.setText(hipertensionArterial);
        editTextDislipidemia.setText(dislipidemia);
        editTextObesidad.setText(obesidad);
        editTextControlGlicemico.setText(controlGlicemico);
        editTextManejoPieDiabetico.setText(manejoPieDiabetico);
        editTextControlComorbilidades.setText(controlComorbilidades);
    }

    private void guardarHistorialClinico() {
        // Validar que todos los campos estén llenos
        if (camposLlenos()) {
            // Guardamos los datos ingresados en las SharedPreferences
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

            Toast.makeText(this, "Historial clínico guardado localmente", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Por favor, complete todos los campos", Toast.LENGTH_SHORT).show();
        }
    }

    private boolean camposLlenos() {
        // Verificar que todos los EditText no estén vacíos
        return !editTextNombres.getText().toString().isEmpty() &&
                !editTextApellidos.getText().toString().isEmpty() &&
                !editTextEdad.getText().toString().isEmpty() &&
                !editTextTalla.getText().toString().isEmpty() &&
                !editTextPeso.getText().toString().isEmpty() &&
                !editTextIMC.getText().toString().isEmpty() &&
                !editTextTemperatura.getText().toString().isEmpty() &&
                !editTextFrecuenciaRespiratoria.getText().toString().isEmpty() &&
                !editTextFrecuenciaCardiaca.getText().toString().isEmpty() &&
                !editTextTensionArterial.getText().toString().isEmpty() &&
                !editTextTabaquismo.getText().toString().isEmpty() &&
                !editTextAlcoholismo.getText().toString().isEmpty() &&
                !editTextSedentarismo.getText().toString().isEmpty() &&
                !editTextHabitosAlimenticios.getText().toString().isEmpty() &&
                !editTextTipoDiabetes.getText().toString().isEmpty() &&
                !editTextHipertensionArterial.getText().toString().isEmpty() &&
                !editTextDislipidemia.getText().toString().isEmpty() &&
                !editTextObesidad.getText().toString().isEmpty() &&
                !editTextControlGlicemico.getText().toString().isEmpty() &&
                !editTextManejoPieDiabetico.getText().toString().isEmpty() &&
                !editTextControlComorbilidades.getText().toString().isEmpty();
    }
}
