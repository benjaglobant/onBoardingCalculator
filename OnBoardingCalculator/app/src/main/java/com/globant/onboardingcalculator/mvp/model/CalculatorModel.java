package com.globant.onboardingcalculator.mvp.model;

import java.text.DecimalFormat;

import static com.globant.onboardingcalculator.utils.Constants.EMPTY_CHAR;
import static com.globant.onboardingcalculator.utils.Constants.EMPTY_STRING;
import static com.globant.onboardingcalculator.utils.Constants.MATH_ERROR;
import static com.globant.onboardingcalculator.utils.Constants.NUMBER_ZERO;
import static com.globant.onboardingcalculator.utils.Constants.OPERATOR_DIVIDE;
import static com.globant.onboardingcalculator.utils.Constants.OPERATOR_MULTIPLY;
import static com.globant.onboardingcalculator.utils.Constants.OPERATOR_PLUS;
import static com.globant.onboardingcalculator.utils.Constants.OPERATOR_SUBSTRACTION;
import static java.lang.Double.parseDouble;

public class CalculatorModel {
    private String firstOperand;
    private String secondOperand;
    private char operator;

    private DecimalFormat df = new DecimalFormat("#.##");

    public CalculatorModel() {
        firstOperand = EMPTY_STRING;
        secondOperand = EMPTY_STRING;
        operator = EMPTY_CHAR;
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

    public void operate() {
        Double result;
        switch (operator) {
            case OPERATOR_PLUS:
                result = parseDouble(firstOperand) + parseDouble(secondOperand);
                clearOperation();
                setFirstOperand(String.valueOf(df.format(result)));
                break;
            case OPERATOR_SUBSTRACTION:
                result = parseDouble(firstOperand) - parseDouble(secondOperand);
                clearOperation();
                setFirstOperand(String.valueOf(df.format(result)));
                break;
            case OPERATOR_MULTIPLY:
                result = parseDouble(firstOperand) * parseDouble(secondOperand);
                clearOperation();
                setFirstOperand(String.valueOf(df.format(result)));
                break;
            case OPERATOR_DIVIDE:
                if (parseDouble(secondOperand) != parseDouble(NUMBER_ZERO)) {
                    result = parseDouble(firstOperand) / parseDouble(secondOperand);
                    clearOperation();
                    setFirstOperand(String.valueOf(df.format(result)));
                } else
                    firstOperand = MATH_ERROR;
                break;
        }
    }
}