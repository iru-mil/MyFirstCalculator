package ru.geekbrains.applicationcalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;

import com.google.android.material.radiobutton.MaterialRadioButton;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private static final String NameSharedPreference = "INPUT";

    private static final String appTheme = "APP_THEME";

    private static final int NightTheme = 0;
    private static final int DayTheme = 1;



    private Calculator calculator;
    private EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setTheme(getAppTheme(R.style.AppThemeLight));
        setContentView(R.layout.activity_main);
        initThemeChooser();

        calculator = new Calculator();
        initView();
    }
    private void initThemeChooser() {
        initRadioButton(findViewById(R.id.radioButtonNightTheme),
                NightTheme);
        initRadioButton(findViewById(R.id.radioButtonDayTheme),
                DayTheme);
        RadioGroup rg = findViewById(R.id.radioButtons);
        ((MaterialRadioButton)rg.getChildAt(getCodeStyle(DayTheme))).setChecked(true);
    }

    private void initRadioButton(View button, final int codeStyle){
        button.setOnClickListener(v -> {
            setAppTheme(codeStyle);
            recreate();
        });
    }

    private int getAppTheme(int codeStyle) {
        return codeStyleToStyleId(getCodeStyle(codeStyle));
    }


    private int getCodeStyle(int codeStyle){
        SharedPreferences sharedPref = getSharedPreferences(NameSharedPreference,
                MODE_PRIVATE);
        return sharedPref.getInt(appTheme, codeStyle);
    }
    private void setAppTheme(int codeStyle) {
        SharedPreferences sharedPref = getSharedPreferences(NameSharedPreference,
                MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putInt(appTheme, codeStyle);
        editor.apply();
    }
    private int codeStyleToStyleId(int codeStyle){
        if (codeStyle == DayTheme) {
            return R.style.AppThemeLight;
        }
        return R.style.AppThemeDark;
    }

    private void initView() {
        editText = findViewById(R.id.input_output);
        calculator.setFirstInput(true);
        initButtonsClickListener();
    }

    private void numberPressed(String number) {
        if ((calculator.isFirstInput() || editText.getText().toString() == null)) {
            editText.setText(number);
            calculator.setFirstInput(false);
        } else editText.setText(String.format("%s%s", editText.getText(), number));
    }

    private void operationPressed(String operation) {
        if ((calculator.isFirstInput() && editText.getText().toString() == null)) {
            editText.setText(null);
        } else {
            calculator.setArgument1(Double.parseDouble(editText.getText().toString()));
            calculator.setOperation(operation);
            calculator.setFirstInput(true);
        }
    }

    private void initButtonsClickListener() {

        Button buttonOppositeSign = findViewById(R.id.button_sign);
        buttonOppositeSign.setOnClickListener(v -> {
            if ((calculator.isFirstInput() && editText.getText().toString() == null)) {
                numberPressed("-");
            } else
                editText.setText(String.valueOf(-(Double.parseDouble(editText.getText().toString()))));
        });

        Button buttonAdd = findViewById(R.id.button_add);
        buttonAdd.setOnClickListener(v -> operationPressed("addition"));

        Button buttonSub = findViewById(R.id.button_sub);
        buttonSub.setOnClickListener(v -> operationPressed("subtraction"));

        Button buttonMul = findViewById(R.id.button_mul);
        buttonMul.setOnClickListener(v -> operationPressed("multiplication"));

        Button buttonDiv = findViewById(R.id.button_div);
        buttonDiv.setOnClickListener(v -> operationPressed("division"));

        Button buttonRes = findViewById(R.id.button_res);
        buttonRes.setOnClickListener(v -> {
            if (calculator.isFirstInput()) {
                editText.setText(null);
            } else {
                calculator.setArgument2(Double.parseDouble(editText.getText().toString()));
                editText.setText(String.format(Locale.getDefault(), "%g", calculator.operation()));
                calculator.setFirstInput(true);
            }
        });

        Button buttonClear = findViewById(R.id.button_clear);
        buttonClear.setOnClickListener(v -> editText.setText(null));

        Button button1 = findViewById(R.id.button_1);
        button1.setOnClickListener(v -> numberPressed("1"));

        Button button2 = findViewById(R.id.button_2);
        button2.setOnClickListener(v -> numberPressed("2"));

        Button button3 = findViewById(R.id.button_3);
        button3.setOnClickListener(v -> numberPressed("3"));

        Button button4 = findViewById(R.id.button_4);
        button4.setOnClickListener(v -> numberPressed("4"));

        Button button5 = findViewById(R.id.button_5);
        button5.setOnClickListener(v -> numberPressed("5"));

        Button button6 = findViewById(R.id.button_6);
        button6.setOnClickListener(v -> numberPressed("6"));

        Button button7 = findViewById(R.id.button_7);
        button7.setOnClickListener(v -> numberPressed("7"));

        Button button8 = findViewById(R.id.button_8);
        button8.setOnClickListener(v -> numberPressed("8"));

        Button button9 = findViewById(R.id.button_9);
        button9.setOnClickListener(v -> numberPressed("9"));

        Button button0 = findViewById(R.id.button_0);
        button0.setOnClickListener(v -> numberPressed("0"));

        Button buttonPoint = findViewById(R.id.button_point);
        buttonPoint.setOnClickListener(v -> numberPressed("."));
    }
}