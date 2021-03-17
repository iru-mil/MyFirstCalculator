package ru.geekbrains.applicationcalculator;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class ThemeActivity extends AppCompatActivity {
    private static final String NameSharedPreference = "INPUT";
    private static final String appTheme = "APP_THEME";

    static final int NightTheme = 0;
    static final int DayTheme = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(getAppTheme(R.style.AppThemeLight));
    }

    private int getAppTheme(int codeStyle) {
        return codeStyleToStyleId(getCodeStyle(codeStyle));
    }

    int getCodeStyle(int codeStyle) {
        SharedPreferences sharedPref = getSharedPreferences(NameSharedPreference,
                MODE_PRIVATE);
        return sharedPref.getInt(appTheme, codeStyle);
    }

    void setAppTheme(int codeStyle) {
        SharedPreferences sharedPref = getSharedPreferences(NameSharedPreference,
                MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putInt(appTheme, codeStyle);
        editor.apply();
    }

    private int codeStyleToStyleId(int codeStyle) {
        if (codeStyle == DayTheme) {
            return R.style.AppThemeLight;
        }
        return R.style.AppThemeDark;
    }
}
