package com.globant.onboardingcalculator.mvp.model;

import static com.globant.onboardingcalculator.utils.Constants.EMPTY_CHAR;
import static com.globant.onboardingcalculator.utils.Constants.EMPTY_STRING;

public class CalculatorModel {
    private String firstOperand;
    private String secondOperand;
    private char operator;
    private String result;

    public CalculatorModel() {
        firstOperand = EMPTY_STRING;
        secondOperand = EMPTY_STRING;
        operator = EMPTY_CHAR;
        result = EMPTY_STRING;
    }

    public boolean emptyOperation() {
        return (firstOperand.isEmpty()) || (secondOperand.isEmpty()) || (operator == EMPTY_CHAR);
    }

    public void clearOperation() {
        firstOperand = EMPTY_STRING;
        secondOperand = EMPTY_STRING;
        operator = EMPTY_CHAR;
        result = EMPTY_STRING;
    }

    public void setOperator(char operator) {
        this.operator = operator;
    }

    public void setFirstOperand(String firstOperand) {
        this.firstOperand = firstOperand;
    }

    public void setSecondOperand(String secondOperand) {
        this.secondOperand = secondOperand;
    }

    public String getFirstOperand() {
        return firstOperand;
    }

    public String getSecondOperand() {
        return secondOperand;
    }

    public char getOperator() {
        return operator;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getResult() {
        return result;
    }
}