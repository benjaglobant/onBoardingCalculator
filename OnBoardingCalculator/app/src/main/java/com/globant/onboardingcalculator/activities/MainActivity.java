package com.globant.onboardingcalculator.activities;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.globant.onboardingcalculator.R;
import com.globant.onboardingcalculator.mvp.model.CalculatorModel;
import com.globant.onboardingcalculator.mvp.presenter.CalculatorPresenter;
import com.globant.onboardingcalculator.mvp.view.CalculatorView;
import com.globant.onboardingcalculator.utils.Constants;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    private CalculatorPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        presenter = new CalculatorPresenter(new CalculatorModel(), new CalculatorView(this));
    }

    @OnClick(R.id.btn_0)
    public void pressedZero() {
        presenter.onNumberPressed(Constants.NUMBER_ZERO);
    }

    @OnClick(R.id.btn_1)
    public void pressedOne() {
        presenter.onNumberPressed(Constants.NUMBER_ONE);
    }

    @OnClick(R.id.btn_2)
    public void pressedTwo() {
        presenter.onNumberPressed(Constants.NUMBER_TWO);
    }

    @OnClick(R.id.btn_3)
    public void pressedThree() {
        presenter.onNumberPressed(Constants.NUMBER_THREE);
    }

    @OnClick(R.id.btn_4)
    public void pressedFour() {
        presenter.onNumberPressed(Constants.NUMBER_FOUR);
    }

    @OnClick(R.id.btn_5)
    public void pressedFive() {
        presenter.onNumberPressed(Constants.NUMBER_FIVE);
    }

    @OnClick(R.id.btn_6)
    public void pressedSix() {
        presenter.onNumberPressed(Constants.NUMBER_SIX);
    }

    @OnClick(R.id.btn_7)
    public void pressedSeven() {
        presenter.onNumberPressed(Constants.NUMBER_SEVEN);
    }

    @OnClick(R.id.btn_8)
    public void pressedEight() {
        presenter.onNumberPressed(Constants.NUMBER_EIGHT);
    }

    @OnClick(R.id.btn_9)
    public void pressedNine() {
        presenter.onNumberPressed(Constants.NUMBER_NINE);
    }

    @OnClick(R.id.btn_plus)
    public void pressedPlus() {
        presenter.onOperatorPressed(Constants.OPERATOR_PLUS);
    }

    @OnClick(R.id.btn_sub)
    public void pressedSub() {
        presenter.onOperatorPressed(Constants.OPERATOR_SUBSTRACTION);
    }

    @OnClick(R.id.btn_multiply)
    public void pressedMultiply() {
        presenter.onOperatorPressed(Constants.OPERATOR_MULTIPLY);
    }

    @OnClick(R.id.btn_divide)
    public void pressedDivide() {
        presenter.onOperatorPressed(Constants.OPERATOR_DIVIDE);
    }

    @OnClick(R.id.btn_clear)
    public void pressedClear() {
        presenter.onClearPressed();
    }

    @OnClick(R.id.btn_point)
    public void pressedPoint() {
        presenter.onPointPressed();
    }

    @OnClick(R.id.btn_equal)
    public void pressedEqual() {
        presenter.onEqualPressed();
    }
}