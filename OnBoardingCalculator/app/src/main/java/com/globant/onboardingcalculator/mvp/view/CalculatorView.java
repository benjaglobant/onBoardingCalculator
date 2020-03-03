package com.globant.onboardingcalculator.mvp.view;

import android.app.Activity;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.globant.onboardingcalculator.R;
import com.globant.onboardingcalculator.mvp.view.base.ActivityView;
import com.globant.onboardingcalculator.utils.Constants;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CalculatorView extends ActivityView {
    @BindView(R.id.visor)
    TextView visor;
    @BindView(R.id.btn_point)
    Button pointBtn;

    public CalculatorView(Activity activity) {
        super(activity);
        ButterKnife.bind(this, activity);
    }

    public void clearVisor() {
        enablePointBtn();
        visor.setText(Constants.NUMBER_ZERO);
    }

    public void refreshVisor(String operand) {
        visor.setText(operand);
    }

    public void showMathError() {
        Toast.makeText(getContext(), R.string.error_msj, Toast.LENGTH_LONG).show();
    }

    public void disablePointBtn() {
        pointBtn.setEnabled(false);
    }


    public void enablePointBtn() {
        pointBtn.setEnabled(true);
    }

    public void showDecimalError() {
        Toast.makeText(getContext(), R.string.decimal_error_msj, Toast.LENGTH_LONG).show();
    }

    public void showOperatorError() {
        Toast.makeText(getContext(), R.string.operator_error_msj, Toast.LENGTH_LONG).show();
    }

    public void showOperatorErrorAfterEqualPressed(){
        Toast.makeText(getContext(), R.string.operator_error_after_equal_pressed, Toast.LENGTH_LONG).show();
    }
}