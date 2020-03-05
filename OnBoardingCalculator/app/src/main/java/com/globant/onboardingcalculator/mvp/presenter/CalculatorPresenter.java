package com.globant.onboardingcalculator.mvp.presenter;

import com.globant.onboardingcalculator.mvp.model.CalculatorModel;
import com.globant.onboardingcalculator.mvp.view.CalculatorView;

import java.text.DecimalFormat;

import static com.globant.onboardingcalculator.utils.Constants.DECIMAL_FORMAT;
import static com.globant.onboardingcalculator.utils.Constants.DECIMAL_POINT;
import static com.globant.onboardingcalculator.utils.Constants.EMPTY_CHAR;
import static com.globant.onboardingcalculator.utils.Constants.EMPTY_STRING;
import static com.globant.onboardingcalculator.utils.Constants.NUMBER_ZERO;
import static com.globant.onboardingcalculator.utils.Constants.OPERATOR_DIVIDE;
import static com.globant.onboardingcalculator.utils.Constants.OPERATOR_MULTIPLY;
import static com.globant.onboardingcalculator.utils.Constants.OPERATOR_PLUS;
import static com.globant.onboardingcalculator.utils.Constants.OPERATOR_SUBSTRACTION;
import static java.lang.Double.parseDouble;


public class CalculatorPresenter {

    private CalculatorModel model;
    private CalculatorView view;
    private DecimalFormat decimalFormat = new DecimalFormat(DECIMAL_FORMAT);

    public CalculatorPresenter(CalculatorModel model, CalculatorView view) {
        this.model = model;
        this.view = view;
    }

    public void onClearPressed() {
        model.clearOperation();
        view.clearVisor();
        view.showCleanedOperationMessage();
    }

    public void onDeletePressed() {
        if (!model.emptyOperation()) {
            if (view.getVisorText().equals(model.getFirstOperand())) {
                if (model.getOperator() == EMPTY_CHAR) {
                    model.digitDeletedInOperand(model.getFirstOperand());
                    view.refreshVisor(model.getFirstOperand());
                } else if (model.getFirstOperand().isEmpty()) {
                    view.showOperatorError();
                }
            } else if (view.getVisorText().equals(String.valueOf(model.getOperator()))) {
                model.setOperator(EMPTY_CHAR);
                view.refreshVisor(model.getFirstOperand());
            } else if (view.getVisorText().equals(model.getSecondOperand())) {
                if (model.getSecondOperand().isEmpty()) {
                    model.digitDeletedInOperand(model.getSecondOperand());
                    view.refreshVisor(model.getSecondOperand());
                } else {
                    view.refreshVisor(String.valueOf(model.getOperator()));
                }
            }
        } else {
            view.showCleanedOperationMessage();
        }
    }

    public void onOperatorPressed(char operator) {
        if ((model.getOperator() != EMPTY_CHAR) || (!model.getSecondOperand().equals(EMPTY_STRING))) {
            model.operate(String.valueOf(decimalFormat.format(calculate())));
        }
        model.setOperator(operator);
        view.refreshVisor(String.valueOf(model.getOperator()));
    }

    public void onNumberPressed(String number) {
        if (!model.getResult().isEmpty() && (model.getOperator() == EMPTY_CHAR))
            view.showOperatorErrorAfterEqualPressed();
        else if (model.getOperator() == EMPTY_CHAR) {
            model.setFirstOperand(model.getFirstOperand() + number);
            view.refreshVisor(model.getFirstOperand());
        } else {
            model.setSecondOperand(model.getSecondOperand() + number);
            view.refreshVisor(model.getSecondOperand());
        }
    }

    private Double calculate() {
        switch (model.getOperator()) {
            case OPERATOR_PLUS:
                return (parseDouble(model.getFirstOperand()) + parseDouble(model.getSecondOperand()));
            case OPERATOR_SUBSTRACTION:
                return (parseDouble(model.getFirstOperand()) - parseDouble(model.getSecondOperand()));
            case OPERATOR_MULTIPLY:
                return (parseDouble(model.getFirstOperand()) * parseDouble(model.getSecondOperand()));
            case OPERATOR_DIVIDE:
                if (parseDouble(model.getSecondOperand()) != parseDouble(NUMBER_ZERO)) {
                    return (parseDouble(model.getFirstOperand()) / parseDouble(model.getSecondOperand()));
                } else {
                    model.clearOperation();
                    view.showMathError();
                }
        }
        return parseDouble(NUMBER_ZERO);
    }

    public void onEqualPressed() {
        model.operate(String.valueOf(decimalFormat.format(calculate())));
        view.refreshVisor(model.getFirstOperand());
    }

    public void onPointPressed() {
        if (!model.getResult().isEmpty() && (model.getOperator() == EMPTY_CHAR))
            view.showOperatorErrorAfterEqualPressed();
        else if (model.getOperator() == EMPTY_CHAR) {
            if (model.getFirstOperand().isEmpty()) {
                model.setFirstOperand(NUMBER_ZERO + DECIMAL_POINT);
                view.refreshVisor(model.getFirstOperand());
            } else if (model.getFirstOperand().contains(DECIMAL_POINT)) {
                view.showDecimalError();
            } else {
                model.setFirstOperand(DECIMAL_POINT);
                view.refreshVisor(model.getFirstOperand());
            }
        } else if (model.getSecondOperand().isEmpty()) {
            model.setSecondOperand(NUMBER_ZERO + DECIMAL_POINT);
            view.refreshVisor(model.getSecondOperand());
        } else if (model.getSecondOperand().contains(DECIMAL_POINT)) {
            view.showDecimalError();
        } else {
            model.setSecondOperand(DECIMAL_POINT);
            view.refreshVisor(model.getSecondOperand());
        }
    }
}