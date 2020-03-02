package com.globant.onboardingcalculator.mvp.model;

import com.globant.onboardingcalculator.utils.Constants;

public class CalculatorModel {
    private String firstOperand;
    private String secondOperand;
    private char operator;

    public CalculatorModel() {
        firstOperand = Constants.EMPTY_STRING;
        secondOperand = Constants.EMPTY_STRING;
        operator = Constants.EMPTY_CHAR;
    }

    public boolean emptyOperation() {
        if (firstOperand.equals(Constants.EMPTY_STRING)) {
            if (secondOperand.equals(Constants.EMPTY_STRING))
                return operator == Constants.EMPTY_CHAR;
        }
        return false;
    }

    public void clearOperation() {
        firstOperand = Constants.EMPTY_STRING;
        secondOperand = Constants.EMPTY_STRING;
        operator = Constants.EMPTY_CHAR;
    }

    public void setOperator(char operator) {
        this.operator = operator;
    }

    public void setFirstOperand(String firstOperand) {
        if (this.firstOperand.equals(Constants.EMPTY_STRING))
            this.firstOperand = firstOperand;
        else
            this.firstOperand = this.firstOperand + firstOperand;
    }

    public void setSecondOperand(String secondOperand) {
        if (this.secondOperand.equals(Constants.EMPTY_STRING))
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
}
