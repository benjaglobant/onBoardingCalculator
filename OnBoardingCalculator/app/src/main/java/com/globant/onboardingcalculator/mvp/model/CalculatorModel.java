package com.globant.onboardingcalculator.mvp.model;

import static com.globant.onboardingcalculator.utils.Constants.EMPTY_CHAR;
import static com.globant.onboardingcalculator.utils.Constants.EMPTY_STRING;
import static com.globant.onboardingcalculator.utils.Constants.NUMBER_ZERO;
import static java.lang.Integer.parseInt;

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
        if (firstOperand.isEmpty()) {
            if (secondOperand.isEmpty())
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
        this.firstOperand = firstOperand;
    }

    public void setSecondOperand(String secondOperand) {
        this.secondOperand = secondOperand;
    }

    public void digitDeletedInOperand(String operand) {
        if (operand.equals(firstOperand)) {
            firstOperand = firstOperand.substring(parseInt(NUMBER_ZERO), firstOperand.length() - 1);
        } else if (operand.equals(secondOperand)) {
            secondOperand = secondOperand.substring(parseInt(NUMBER_ZERO), firstOperand.length() - 1);
        }
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