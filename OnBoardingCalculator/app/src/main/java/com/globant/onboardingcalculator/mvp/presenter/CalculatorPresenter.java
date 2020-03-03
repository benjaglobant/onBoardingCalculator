package com.globant.onboardingcalculator.mvp.presenter;

import com.globant.onboardingcalculator.mvp.model.CalculatorModel;
import com.globant.onboardingcalculator.mvp.view.CalculatorView;

import java.text.DecimalFormat;

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
    private String result;
    private DecimalFormat decimalFormat = new DecimalFormat("#.##");

    public CalculatorPresenter(CalculatorModel model, CalculatorView view) {
        this.model = model;
        this.view = view;
        result = EMPTY_STRING;
    }

    public void onClearPressed() {
        result = EMPTY_STRING;
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
        if (!result.equals(EMPTY_STRING) && (model.getOperator() == EMPTY_CHAR))
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
                    result = String.valueOf(decimalFormat.format(parseDouble(model.getFirstOperand()) + parseDouble(model.getSecondOperand())));
                    model.operate(result);
                    break;
                case OPERATOR_SUBSTRACTION:
                    result = String.valueOf(decimalFormat.format(parseDouble(model.getFirstOperand()) - parseDouble(model.getSecondOperand())));
                    model.operate(result);
                    break;
                case OPERATOR_MULTIPLY:
                    result = String.valueOf(decimalFormat.format(parseDouble(model.getFirstOperand()) * parseDouble(model.getSecondOperand())));
                    model.operate(result);
                    break;
                case OPERATOR_DIVIDE:
                    if (parseDouble(model.getSecondOperand()) != parseDouble(NUMBER_ZERO)) {
                        result = String.valueOf(decimalFormat.format(parseDouble(model.getFirstOperand()) / parseDouble(model.getSecondOperand())));
                        model.operate(result);
                    } else {
                        model.clearOperation();
                        view.showMathError();
                        break;
                    }
            }
        view.refreshVisor(model.getResult());
    }

    public void onPointPressed() {
        if (!result.equals(EMPTY_STRING) && (model.getOperator() == EMPTY_CHAR))
            view.showOperatorErrorAfterEqualPressed();
        else if (model.getOperator() == EMPTY_CHAR) {
                if (model.getFirstOperand().isEmpty()) {
                    model.setFirstOperand(NUMBER_ZERO);
                    model.setFirstOperand(DECIMAL_POINT);
                    view.refreshVisor(model.getFirstOperand());
                } else if (model.getFirstOperand().contains(DECIMAL_POINT))
                            view.showDecimalError();
                        else {
                            model.setFirstOperand(DECIMAL_POINT);
                            view.refreshVisor(model.getFirstOperand());
                }
            } else if (model.getSecondOperand().isEmpty()) {
                        model.setSecondOperand(NUMBER_ZERO);
                        model.setSecondOperand(DECIMAL_POINT);
                        view.refreshVisor(model.getSecondOperand());
                    } else if (model.getSecondOperand().contains(DECIMAL_POINT))
                                view.showDecimalError();
                            else {
                                model.setSecondOperand(DECIMAL_POINT);
                                view.refreshVisor(model.getSecondOperand());
                            }
        }
    }