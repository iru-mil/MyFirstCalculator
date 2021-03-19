package ru.geekbrains.applicationcalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SettingsActivity extends AppCompatActivity {
    private static int value;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        Button buttonReady = findViewById(R.id.button_return);
        buttonReady.setOnClickListener(v -> {
            Intent intentResult = new Intent();
            intentResult.putExtra("key", value);
            setResult(RESULT_OK, intentResult);
            finish();
        });
        Button radioBtnNightTheme = findViewById(R.id.radioButtonNightTheme);
        radioBtnNightTheme.setOnClickListener(v -> value = ThemeActivity.NIGHT_THEME);

        Button radioBtnDayTheme = findViewById(R.id.radioButtonDayTheme);
        radioBtnDayTheme.setOnClickListener(v -> value = ThemeActivity.DAY_THEME);
    }
}