package com.globant.onboardingcalculator.mvp.presenter;

import com.globant.onboardingcalculator.mvp.model.CalculatorModel;
import com.globant.onboardingcalculator.mvp.view.CalculatorView;

import static com.globant.onboardingcalculator.utils.Constants.DECIMAL_POINT;
import static com.globant.onboardingcalculator.utils.Constants.EMPTY_CHAR;
import static com.globant.onboardingcalculator.utils.Constants.MATH_ERROR;
import static com.globant.onboardingcalculator.utils.Constants.NUMBER_ZERO;


public class CalculatorPresenter {

    private CalculatorModel model;
    private CalculatorView view;

    public CalculatorPresenter(CalculatorModel model, CalculatorView view) {
        this.model = model;
        this.view = view;
    }

    public void onClearPressed() {
        model.clearOperation();
        view.clearVisor();
    }

    public void onOperatorPressed(char operator) {
        if (!model.getFirstOperand().isEmpty()) {
            model.setOperator(operator);
            view.refreshVisor(String.valueOf(operator));
            view.enablePointBtn();
        } else
            view.showOperatorError();
    }

    public void onNumberPressed(String number) {
        if (model.getOperator() == EMPTY_CHAR) {
            model.setFirstOperand(number);
            view.refreshVisor(model.getFirstOperand());
        } else {
            model.setSecondOperand(number);
            view.refreshVisor(model.getSecondOperand());
        }
    }

    public void onEqualPressed() {
        if (!model.emptyOperation()) {
            model.operate();
            if (model.getFirstOperand().equals(MATH_ERROR))
                view.showMathError();
            else
                view.refreshVisor(model.getFirstOperand());
        }
        view.enablePointBtn();
    }

    public void onPointPressed() {
        if (model.getOperator() == EMPTY_CHAR) {
            if (model.getFirstOperand().isEmpty()) {
                model.setFirstOperand(NUMBER_ZERO);
                model.setFirstOperand(DECIMAL_POINT);
            } else if (model.getFirstOperand().contains(DECIMAL_POINT))
                view.showDecimalError();
            else
                model.setFirstOperand(DECIMAL_POINT);
            view.refreshVisor(model.getFirstOperand());
        } else {
            if (model.getSecondOperand().isEmpty()) {
                model.setSecondOperand(NUMBER_ZERO);
                model.setSecondOperand(DECIMAL_POINT);
            } else if (model.getSecondOperand().contains(DECIMAL_POINT))
                view.showDecimalError();
            else
                model.setSecondOperand(DECIMAL_POINT);
            view.refreshVisor(model.getSecondOperand());
        }
    }
}