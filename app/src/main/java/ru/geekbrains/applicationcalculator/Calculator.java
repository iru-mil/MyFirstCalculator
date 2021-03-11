package ru.geekbrains.applicationcalculator;

public class Calculator {
    private double result;
    private boolean firstInput = true;
    private double argument1;
    private double argument2;
    private String operation;

    public boolean isFirstInput() {
        return firstInput;
    }

    public void setFirstInput(boolean firstInput) {
        this.firstInput = firstInput;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public void setArgument1(double argument1) {
        this.argument1 = argument1;
    }

    public void setArgument2(double argument2) {
        this.argument2 = argument2;
    }


    public double operation() {
        switch (operation) {
            case "addition":
                result = argument1 + argument2;
                break;
            case "subtraction":
                result = argument1 - argument2;
                break;
            case "multiplication":
                result = argument1 * argument2;
                break;
            case "division":
                result = argument1 / argument2;
                break;
        }
        return result;
    }
}
