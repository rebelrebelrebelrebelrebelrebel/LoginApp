package com.rebel.myloginapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast; // Importar Toast para mostrar mensajes
import androidx.appcompat.app.AppCompatActivity;
import java.util.HashMap;
import java.util.Map;

public class MenuMainActivity extends AppCompatActivity {

    private Map<Integer, Class<?>> menuActions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Inicializar el mapa con las acciones del menú
        initializeMenuActions();
    }

    private void initializeMenuActions() {
        menuActions = new HashMap<>();
        addMenuAction(R.id.action_settings, SettingsActivity.class);
        addMenuAction(R.id.action_about, AboutActivity.class);
    }

    private void addMenuAction(int menuId, Class<?> activityClass) {
        menuActions.put(menuId, activityClass);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        Class<?> activityClass = menuActions.get(menuItem.getItemId());
        if (activityClass != null) {
            Intent intent = new Intent(this, activityClass);
            startActivity(intent);
            return true;
        } else {
            // Manejo de errores: mostrar un mensaje si el elemento no está definido
            Toast.makeText(this, "Acción no definida", Toast.LENGTH_SHORT).show();
        }
        return super.onOptionsItemSelected(menuItem);
    }
}
