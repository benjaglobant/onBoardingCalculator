package com.globant.onboardingcalculator.mvp.presenter;

import com.globant.onboardingcalculator.mvp.model.CalculatorModel;
import com.globant.onboardingcalculator.mvp.view.CalculatorView;

import java.text.DecimalFormat;

import static com.globant.onboardingcalculator.utils.Constants.DECIMAL_FORMAT;
import static com.globant.onboardingcalculator.utils.Constants.DECIMAL_POINT;
import static com.globant.onboardingcalculator.utils.Constants.EMPTY_CHAR;
import static com.globant.onboardingcalculator.utils.Constants.NUMBER_ZERO;
import static com.globant.onboardingcalculator.utils.Constants.OPERATOR_DIVIDE;
import static com.globant.onboardingcalculator.utils.Constants.OPERATOR_MULTIPLY;
import static com.globant.onboardingcalculator.utils.Constants.OPERATOR_PLUS;
import static com.globant.onboardingcalculator.utils.Constants.OPERATOR_SUBSTRACTION;
import static java.lang.Double.parseDouble;
import static java.lang.Integer.parseInt;


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
    }

    public void onDeletePressed() {
        if (model.getOperator() == EMPTY_CHAR) {
            model.digitDeletedInFirstOperand(model.getFirstOperand().substring(parseInt(NUMBER_ZERO), model.getFirstOperand().length() - 1));
            view.refreshVisor(model.getFirstOperand());
        } else if (model.getSecondOperand().isEmpty()) {
            model.setOperator(EMPTY_CHAR);
            view.refreshVisor(String.valueOf(model.getOperator()));
        } else {
            model.digitDeletedInSecondOperand(model.getSecondOperand().substring(parseInt(NUMBER_ZERO), model.getSecondOperand().length() - 1));
            view.refreshVisor(model.getSecondOperand());
        }
    }

    public void onOperatorPressed(char operator) {
        if (!model.getFirstOperand().isEmpty()) {
            model.setOperator(operator);
            view.refreshVisor(String.valueOf(operator));
        } else
            view.showOperatorError();
    }

    public void onNumberPressed(String number) {
        if (!model.getResult().isEmpty() && (model.getOperator() == EMPTY_CHAR))
            view.showOperatorErrorAfterEqualPressed();
        else if (model.getOperator() == EMPTY_CHAR) {
            model.setFirstOperand(number);
            view.refreshVisor(model.getFirstOperand());
        } else {
            model.setSecondOperand(number);
            view.refreshVisor(model.getSecondOperand());
        }
    }

    public void onEqualPressed() {
        if (!model.emptyOperation())
            switch (model.getOperator()) {
                case OPERATOR_PLUS:
                    model.operate(String.valueOf(decimalFormat.format(parseDouble(model.getFirstOperand()) + parseDouble(model.getSecondOperand()))));
                    break;
                case OPERATOR_SUBSTRACTION:
                    String.valueOf(decimalFormat.format(parseDouble(model.getFirstOperand()) - parseDouble(model.getSecondOperand())));
                    model.operate(String.valueOf(decimalFormat.format(parseDouble(model.getFirstOperand()) - parseDouble(model.getSecondOperand()))));
                    break;
                case OPERATOR_MULTIPLY:
                    model.operate(String.valueOf(decimalFormat.format(parseDouble(model.getFirstOperand()) * parseDouble(model.getSecondOperand()))));
                    break;
                case OPERATOR_DIVIDE:
                    if (parseDouble(model.getSecondOperand()) != parseDouble(NUMBER_ZERO)) {
                        model.operate(String.valueOf(decimalFormat.format(parseDouble(model.getFirstOperand()) / parseDouble(model.getSecondOperand()))));
                    } else {
                        model.clearOperation();
                        view.showMathError();
                    }
                    break;
            }
        view.refreshVisor(model.getResult());
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