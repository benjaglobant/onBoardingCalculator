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
        if (firstOperand.equals(EMPTY_STRING)) {
            if (secondOperand.equals(EMPTY_STRING))
                return operator == EMPTY_CHAR;
        }
        return false;
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
        if (this.firstOperand.equals(EMPTY_STRING))
            this.firstOperand = firstOperand;
        else
            this.firstOperand = this.firstOperand + firstOperand;
    }

    public void setSecondOperand(String secondOperand) {
        if (this.secondOperand.equals(EMPTY_STRING))
            this.secondOperand = secondOperand;
        else
            this.secondOperand = this.secondOperand + secondOperand;
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

    public void operate(String result) {
        this.result = result;
        firstOperand = result;
        operator = EMPTY_CHAR;
        secondOperand = EMPTY_STRING;
    }

    public String getResult() {
        return result;
    }
}