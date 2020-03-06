package com.globant.onboardingcalculator.mvp.view;

import android.app.Activity;
import android.widget.Button;
import android.widget.HorizontalScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.globant.onboardingcalculator.R;
import com.globant.onboardingcalculator.mvp.view.base.ActivityView;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.globant.onboardingcalculator.utils.Constants.NUMBER_ZERO;
import static java.lang.Integer.parseInt;

public class CalculatorView extends ActivityView {
    @BindView(R.id.visor)
    TextView visor;
    @BindView(R.id.btn_point)
    Button pointBtn;
    @BindView(R.id.horizontal_scroll_text_view)
    HorizontalScrollView horizontal_scroll_text_view;

    public CalculatorView(Activity activity) {
        super(activity);
        ButterKnife.bind(this, activity);
    }

    public void clearVisor() {
        visor.setText(NUMBER_ZERO);
        horizontal_scroll_text_view.setScrollX(parseInt(NUMBER_ZERO));
    }

    public void refreshVisor(String operand) {
        visor.setText(operand);
    }

    private void showMessage(int message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_LONG).show();
    }

    public void showMathError() {
        showMessage(R.string.error_msj);
    }

    public void showDecimalError() {
        showMessage(R.string.decimal_error_msj);
    }

    public void showOperatorError() {
        showMessage(R.string.operator_error_msj);
    }

    public void showOperatorErrorAfterEqualPressed() {
        showMessage(R.string.operator_error_after_equal_pressed);
    }

    public void showCleanedOperationMessage() {
        showMessage(R.string.operation_cleaned);
    }

    public void showIncompletedOperationMessage() {
        showMessage(R.string.incomplete_operation_msj);
    }
}