package ru.geekbrains.applicationcalculator;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class ThemeActivity extends AppCompatActivity {
    private static final String NAME_SHARED_PREFERENCE = "INPUT";
    private static final String APP_THEME = "APP_THEME";

    static final int NIGHT_THEME = 0;
    static final int DAY_THEME = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(getAppTheme(R.style.AppThemeLight));
    }

    private int getAppTheme(int codeStyle) {
        return codeStyleToStyleId(getCodeStyle(codeStyle));
    }

    int getCodeStyle(int codeStyle) {
        SharedPreferences sharedPref = getSharedPreferences(NAME_SHARED_PREFERENCE,
                MODE_PRIVATE);
        return sharedPref.getInt(APP_THEME, codeStyle);
    }

    void setAppTheme(int codeStyle) {
        SharedPreferences sharedPref = getSharedPreferences(NAME_SHARED_PREFERENCE,
                MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putInt(APP_THEME, codeStyle);
        editor.apply();
    }

    private int codeStyleToStyleId(int codeStyle) {
        if (codeStyle == DAY_THEME) {
            return R.style.AppThemeLight;
        }
        return R.style.AppThemeDark;
    }
}
