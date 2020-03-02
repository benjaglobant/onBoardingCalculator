package com.globant.onboardingcalculator.mvp.presenter;

import com.globant.onboardingcalculator.mvp.model.CalculatorModel;
import com.globant.onboardingcalculator.mvp.view.CalculatorView;
import com.globant.onboardingcalculator.utils.Constants;

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
        if (model.getOperator() == Constants.EMPTY_CHAR)
            model.setOperator(operator);
        view.refreshVisor(String.valueOf(operator));
    }

    public void onNumberPressed(String number) {
        if (model.getOperator() == Constants.EMPTY_CHAR) {
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
            if (model.getFirstOperand().equals(Constants.MATH_ERROR))
                view.showMathError();
            else
                view.refreshVisor(model.getFirstOperand());
        }
    }
}