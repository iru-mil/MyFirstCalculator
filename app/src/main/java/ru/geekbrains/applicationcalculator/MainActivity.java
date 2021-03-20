package ru.geekbrains.applicationcalculator;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;

import com.google.android.material.radiobutton.MaterialRadioButton;

import java.util.Locale;

public class MainActivity extends ThemeActivity {

    private static final int REQUEST_CODE_SETTING_ACTIVITY = 99;
    private Calculator calculator;
    private EditText editText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        calculator = new Calculator();
        initView();
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode != REQUEST_CODE_SETTING_ACTIVITY) {
            super.onActivityResult(requestCode, resultCode, data);
            return;
        }
        if (resultCode == RESULT_OK) {
            setAppTheme(data.getIntExtra("key", 0));
            recreate();
        }
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

        Button buttonSettings = findViewById(R.id.button_settings);
        buttonSettings.setOnClickListener(v -> {
            Intent runSettings = new Intent(MainActivity.this,
                    SettingsActivity.class);
            startActivityForResult(runSettings, REQUEST_CODE_SETTING_ACTIVITY);
        });

        Button buttonOppositeSign = findViewById(R.id.button_sign);
        buttonOppositeSign.setOnClickListener(v -> {
            if ((calculator.isFirstInput() && editText.getText().toString() == null)) {
                numberPressed("-");
            } else {
                editText.setText(String.valueOf(-(Double.parseDouble(editText.getText().toString()))));
            }
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