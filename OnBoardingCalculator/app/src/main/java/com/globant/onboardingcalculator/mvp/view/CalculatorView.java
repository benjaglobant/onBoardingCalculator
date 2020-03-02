package com.globant.onboardingcalculator.mvp.view;

import android.app.Activity;
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

    public CalculatorView(Activity activity) {
        super(activity);
        ButterKnife.bind(this, activity);
    }

    public void clearVisor() {
        visor.setText(Constants.NUMBER_ZERO);
    }

    public void refreshVisor(String operand) {
        visor.setText(operand);
    }

    public void showMathError() {
        Toast.makeText(getContext(), R.string.error_msj, Toast.LENGTH_LONG).show();
    }
}