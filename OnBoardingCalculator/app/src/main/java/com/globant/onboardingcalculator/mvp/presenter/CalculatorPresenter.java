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

    private void updateVisor() {
        if (model.getFirstOperand().isEmpty()) {
            view.refreshVisor(NUMBER_ZERO);
        } else if ((model.getSecondOperand().isEmpty()) && (model.getOperator() == EMPTY_CHAR)) {
            view.refreshVisor(model.getFirstOperand());
        } else if ((model.getOperator() != EMPTY_CHAR) && (model.getSecondOperand().isEmpty())) {
            view.refreshVisor(String.valueOf(model.getOperator()));
        } else if ((!model.getSecondOperand().isEmpty()) && (model.getOperator() != EMPTY_CHAR)) {
            view.refreshVisor(model.getSecondOperand());
        }
    }

    public void onDeletePressed() {
        if ((model.getSecondOperand().isEmpty()) && (model.getOperator() == EMPTY_CHAR)) {
            if (!model.getFirstOperand().isEmpty() && (!model.getFirstOperand().equals(NUMBER_ZERO))) {
                model.setFirstOperand(model.getFirstOperand().substring(0, model.getFirstOperand().length() - 1));
            } else {
                model.setFirstOperand(NUMBER_ZERO);
                view.showOperatorError();
            }
        } else if ((model.getSecondOperand().isEmpty()) && (model.getOperator() != EMPTY_CHAR)) {
            model.setOperator(EMPTY_CHAR);
        } else if ((!model.getSecondOperand().isEmpty()) && (model.getOperator() != EMPTY_CHAR)) {
            model.setSecondOperand(model.getSecondOperand().substring(0, model.getSecondOperand().length() - 1));
        } else {
            view.showOperatorError();
        }
        updateVisor();
    }

    public void onOperatorPressed(char operator) {
        if (model.getFirstOperand().equals(EMPTY_STRING)) {
            view.showOperatorError();
        } else if ((model.getOperator() == EMPTY_CHAR) || (model.getSecondOperand().isEmpty())) {
            model.setOperator(operator);
        } else if ((!model.getSecondOperand().equals(EMPTY_STRING)) || (!model.getResult().isEmpty())) {
            model.setResult(String.valueOf(decimalFormat.format(calculate())));
            model.setFirstOperand(model.getResult());
            model.setSecondOperand(EMPTY_STRING);
            model.setOperator(operator);
        }
        updateVisor();
    }

    public void onNumberPressed(String number) {
        if (model.getOperator() == EMPTY_CHAR) {
            if (model.getSecondOperand().isEmpty()) {
                if (model.getResult().isEmpty()) {
                    model.setFirstOperand(model.getFirstOperand() + number);
                } else {
                    view.showOperatorError();
                }
            }
        } else {
            model.setSecondOperand(model.getSecondOperand() + number);
        }
        updateVisor();
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
                    return parseDouble(model.getFirstOperand()) / parseDouble(model.getSecondOperand());
                } else {
                    model.clearOperation();
                    view.showMathError();
                }
        }
        return parseDouble(NUMBER_ZERO);
    }

    public void onEqualPressed() {
        if (!model.emptyOperation()) {
            model.setFirstOperand(decimalFormat.format((calculate())));
            model.setResult(model.getFirstOperand());
            model.setSecondOperand(EMPTY_STRING);
            model.setOperator(EMPTY_CHAR);
            view.refreshVisor(model.getResult());
        } else {
            view.showIncompletedOperationMessage();
        }
    }

    public void onPointPressed() {
        if ((model.getSecondOperand().isEmpty()) && (model.getOperator() == EMPTY_CHAR)) {
            if (model.getFirstOperand().isEmpty()) {
                model.setFirstOperand(NUMBER_ZERO + DECIMAL_POINT);
            } else if (!model.getFirstOperand().contains(DECIMAL_POINT)) {
                model.setFirstOperand(model.getFirstOperand() + DECIMAL_POINT);
            } else {
                view.showDecimalError();
            }
        } else if ((model.getOperator() != EMPTY_CHAR)) {
            if (model.getSecondOperand().isEmpty()) {
                model.setSecondOperand(NUMBER_ZERO + DECIMAL_POINT);
            } else if (!model.getSecondOperand().contains(DECIMAL_POINT)) {
                model.setSecondOperand(model.getSecondOperand() + DECIMAL_POINT);
            } else {
                view.showDecimalError();
            }
        }
        updateVisor();
    }
}