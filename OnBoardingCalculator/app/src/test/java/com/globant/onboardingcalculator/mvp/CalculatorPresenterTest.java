package com.globant.onboardingcalculator.mvp;

import com.globant.onboardingcalculator.mvp.model.CalculatorModel;
import com.globant.onboardingcalculator.mvp.presenter.CalculatorPresenter;
import com.globant.onboardingcalculator.mvp.view.CalculatorView;

import org.junit.Before;
import org.junit.Test;

import static com.globant.onboardingcalculator.utils.Constants.DECIMAL_POINT;
import static com.globant.onboardingcalculator.utils.Constants.EMPTY_CHAR;
import static com.globant.onboardingcalculator.utils.Constants.EMPTY_STRING;
import static com.globant.onboardingcalculator.utils.Constants.NUMBER_FIVE;
import static com.globant.onboardingcalculator.utils.Constants.NUMBER_TWO;
import static com.globant.onboardingcalculator.utils.Constants.NUMBER_ZERO;
import static com.globant.onboardingcalculator.utils.Constants.OPERATOR_DIVIDE;
import static com.globant.onboardingcalculator.utils.Constants.OPERATOR_MULTIPLY;
import static com.globant.onboardingcalculator.utils.Constants.OPERATOR_PLUS;
import static com.globant.onboardingcalculator.utils.Constants.OPERATOR_SUBSTRACTION;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class CalculatorPresenterTest {

    private CalculatorView mockedView = mock(CalculatorView.class);
    private CalculatorModel model = new CalculatorModel();
    private CalculatorPresenter presenter;

    private static final String TEST_NUMBER = "20";
    private static final String TEST_NUMBER_REDUCED = "2";
    private static final String TEST_DECIMAL_NUMBER = "20.";
    private static final String ZERO_AND_DECIMAL_POINT = "0.";
    private static final String TEST_NUMBER_PLUS = "40";
    private static final String TEST_NUMBER_MULTIPLY = "41";
    private static final String TEST_NUMBER_SUBSTRACTION = "17,5";
    private static final String TEST_NUMBER_DIVIDE = "16,2";

    @Before
    public void setUp(){
        presenter = new CalculatorPresenter(model, mockedView);
    }

    @Test
    public void onClearPressed(){
        presenter.onClearPressed();

        verify(mockedView).clearVisor();

        assertEquals(EMPTY_STRING, model.getFirstOperand());
        assertEquals(EMPTY_STRING, model.getSecondOperand());
        assertEquals(EMPTY_CHAR, model.getOperator());
    }

    @Test
    public void onNumberPressedWithoutFirstOperandAndShowInVisor(){
        presenter.onNumberPressed(TEST_NUMBER_REDUCED);

        assertEquals(TEST_NUMBER_REDUCED, model.getFirstOperand());

        verify(mockedView).refreshVisor(TEST_NUMBER_REDUCED);
    }

    @Test
    public void onNumberPressedWithFirstOperandAndShowInVisor(){
        model.setFirstOperand(TEST_NUMBER_REDUCED);

        presenter.onNumberPressed(NUMBER_ZERO);

        assertEquals(TEST_NUMBER, model.getFirstOperand());

        verify(mockedView).refreshVisor(model.getFirstOperand());
    }

    @Test
    public void onNumberPressedWithFirstOperandWithOperatorWithoutSecondOperandAndShowInVisor(){
        model.setFirstOperand(TEST_NUMBER);
        model.setOperator(OPERATOR_PLUS);

        presenter.onNumberPressed(TEST_NUMBER_REDUCED);

        assertEquals(TEST_NUMBER_REDUCED, model.getSecondOperand());

        verify(mockedView).refreshVisor(model.getSecondOperand());
    }

    @Test
    public void onNumberPressedWithFirstOperandWithOperatorWithSecondOperandAndShowInVisor(){
        model.setFirstOperand(TEST_NUMBER);
        model.setOperator(OPERATOR_MULTIPLY);
        model.setSecondOperand(TEST_NUMBER_REDUCED);

        presenter.onNumberPressed(NUMBER_ZERO);

        assertEquals(TEST_NUMBER, model.getSecondOperand());

        verify(mockedView).refreshVisor(model.getSecondOperand());
    }

    @Test
    public void onNumberPressedWithFirstOperandWithOperatorWithoutSecondOperandWithResultAndShowInVisor(){
        model.setFirstOperand(TEST_NUMBER);
        model.setOperator(OPERATOR_PLUS);
        model.setResult(TEST_NUMBER);

        presenter.onNumberPressed(TEST_NUMBER_REDUCED);

        assertEquals(TEST_NUMBER_REDUCED, model.getSecondOperand());

        verify(mockedView).refreshVisor(model.getSecondOperand());
    }

    @Test
    public void onNumberPressedWithFirstOperandWithResult(){
        model.setFirstOperand(TEST_NUMBER);
        model.setResult(TEST_NUMBER);

        presenter.onNumberPressed(NUMBER_FIVE);

        assertEquals(TEST_NUMBER, model.getFirstOperand());
        assertEquals(TEST_NUMBER, model.getResult());

        verify(mockedView).showOperatorError();
    }

    @Test
    public void onPointPressedWithOperationAndShowInVisor(){
        presenter.onPointPressed();

        assertEquals(ZERO_AND_DECIMAL_POINT, model.getFirstOperand());

        verify(mockedView).refreshVisor(model.getFirstOperand());
    }

    @Test
    public void onPointPressedWithFirstOperandAndShowInVisor(){
        model.setFirstOperand(TEST_NUMBER);

        presenter.onPointPressed();

        assertEquals(TEST_DECIMAL_NUMBER, model.getFirstOperand());

        verify(mockedView).refreshVisor(model.getFirstOperand());
    }

    @Test
    public void onPointPressedWithFirstOperandWithOperatorWithoutSecondOperandAndShowInVisor(){
        model.setFirstOperand(TEST_NUMBER);
        model.setOperator(OPERATOR_MULTIPLY);

        presenter.onPointPressed();

        assertEquals(ZERO_AND_DECIMAL_POINT, model.getSecondOperand());
        verify(mockedView).refreshVisor(model.getSecondOperand());
    }

    @Test
    public void onPointPressedWithDecimalInFirstOperandWithoutOperatorWithoutSecondOperandAndShowInVisor(){
        model.setFirstOperand(TEST_DECIMAL_NUMBER);

        presenter.onPointPressed();

        assertEquals(TEST_DECIMAL_NUMBER, model.getFirstOperand());

        verify(mockedView).showDecimalError();
        verify(mockedView).refreshVisor(model.getFirstOperand());
    }

    @Test
    public void onPointPressedWithFirstOperandWithOperatorWithSecondOperandAndShowInVisor(){
        model.setFirstOperand(TEST_NUMBER);
        model.setOperator(OPERATOR_PLUS);
        model.setSecondOperand(TEST_NUMBER);
        presenter.onPointPressed();
        assertEquals(TEST_DECIMAL_NUMBER, model.getSecondOperand());
        verify(mockedView).refreshVisor(model.getSecondOperand());
    }

    @Test
    public void onPointPressedWithFirstOperandWithOperatorWithDecimalInSecondOperandAndShowInVisor(){
        model.setFirstOperand(TEST_NUMBER);
        model.setOperator(OPERATOR_DIVIDE);
        model.setSecondOperand(TEST_DECIMAL_NUMBER);

        presenter.onPointPressed();

        assertEquals(TEST_DECIMAL_NUMBER, model.getSecondOperand());

        verify(mockedView).refreshVisor(model.getSecondOperand());
    }

    @Test
    public void onEqualPressedWithEmptyOperation(){
        presenter.onEqualPressed();

        assertEquals(EMPTY_STRING, model.getFirstOperand());
        assertEquals(EMPTY_STRING, model.getSecondOperand());
        assertEquals(EMPTY_CHAR, model.getOperator());

        verify(mockedView).showIncompletedOperationMessage();
    }

    @Test
    public void onEqualPressedWithFirstOperand(){
        model.setFirstOperand(TEST_NUMBER);

        presenter.onEqualPressed();

        assertEquals(TEST_NUMBER, model.getFirstOperand());

        verify(mockedView).showIncompletedOperationMessage();
    }

    @Test
    public void onEqualPressedWithFirstOperandAndOperator(){
        model.setFirstOperand(TEST_NUMBER);
        model.setOperator(OPERATOR_PLUS);

        presenter.onEqualPressed();

        assertEquals(TEST_NUMBER, model.getFirstOperand());
        assertEquals(OPERATOR_PLUS, model.getOperator());

        verify(mockedView).showIncompletedOperationMessage();
    }

    @Test
    public void onEqualPressedWithFirstOperandOperatorAndSecondOperandAndShowTheResultInVisor() {
        model.setFirstOperand(TEST_NUMBER);
        model.setOperator(OPERATOR_PLUS);
        model.setSecondOperand(TEST_NUMBER);

        presenter.onEqualPressed();

        assertEquals(TEST_NUMBER_PLUS, model.getFirstOperand());
        assertEquals(TEST_NUMBER_PLUS, model.getResult());
        assertEquals(EMPTY_STRING, model.getSecondOperand());
        assertEquals(EMPTY_CHAR, model.getOperator());

        verify(mockedView).refreshVisor(model.getFirstOperand());
    }

    @Test
    public void onOperatorPressedWithEmptyOperation(){
        presenter.onOperatorPressed(OPERATOR_PLUS);

        assertEquals(EMPTY_CHAR, model.getOperator());
        assertEquals(EMPTY_STRING, model.getFirstOperand());
        assertEquals(EMPTY_STRING, model.getSecondOperand());
        assertEquals(EMPTY_STRING, model.getResult());

        verify(mockedView).showOperatorError();
    }

    @Test
    public void onOperatorPressedWithFirstOperandAndShowInVisor(){
        model.setFirstOperand(TEST_NUMBER);

        presenter.onOperatorPressed(OPERATOR_MULTIPLY);

        assertEquals(TEST_NUMBER, model.getFirstOperand());
        assertEquals(OPERATOR_MULTIPLY, model.getOperator());

        verify(mockedView).refreshVisor(String.valueOf(model.getOperator()));
    }

    @Test
    public void onOperatorPressedWithFirstOperandWithOperatorAndShowInVisor(){
        model.setFirstOperand(TEST_NUMBER);
        model.setOperator(OPERATOR_DIVIDE);

        presenter.onOperatorPressed(OPERATOR_PLUS);

        assertEquals(OPERATOR_PLUS, model.getOperator());

        verify(mockedView).refreshVisor(String.valueOf(model.getOperator()));
    }

    @Test
    public void onOperatorPressedWithFirstOperandWithOperatorWithSecondOperandAndShowInVisor(){
        model.setFirstOperand(TEST_NUMBER);
        model.setOperator(OPERATOR_PLUS);
        model.setSecondOperand(TEST_NUMBER);

        presenter.onOperatorPressed(OPERATOR_PLUS);

        assertEquals(EMPTY_STRING, model.getSecondOperand());
        assertEquals(TEST_NUMBER_PLUS, model.getFirstOperand());
        assertEquals(TEST_NUMBER_PLUS, model.getResult());
        assertEquals(OPERATOR_PLUS, model.getOperator());

        verify(mockedView).refreshVisor(String.valueOf(model.getOperator()));
    }

    @Test
    public void onOperatorPressedWithFirstOperandWithOperatorWithSecondOperandWithResult(){
        model.setFirstOperand(TEST_NUMBER);
        model.setOperator(OPERATOR_PLUS);
        model.setSecondOperand(TEST_NUMBER);
        model.setResult(TEST_NUMBER);

        presenter.onOperatorPressed(OPERATOR_PLUS);

        assertEquals(TEST_NUMBER_PLUS, model.getFirstOperand());
        assertEquals(TEST_NUMBER_PLUS, model.getResult());
        assertEquals(OPERATOR_PLUS, model.getOperator());
        assertEquals(EMPTY_STRING, model.getSecondOperand());

        verify(mockedView).refreshVisor(String.valueOf(model.getOperator()));
    }

    @Test
    public void onDeletePressedWithEmptyOperation(){
        presenter.onDeletePressed();

        assertEquals(NUMBER_ZERO, model.getFirstOperand());
        assertEquals(EMPTY_CHAR, model.getOperator());
        assertEquals(EMPTY_STRING, model.getSecondOperand());

        verify(mockedView).showOperatorError();
    }

    @Test
    public void onDeletePressedWithoutFirstOperandWithoutOperatorWithoutSecondOperand(){
        presenter.onDeletePressed();

        verify(mockedView).showOperatorError();
    }

    @Test
    public void onDeletePressedWithFirstOperandAndRefreshVisor(){
        model.setFirstOperand(TEST_NUMBER);

        presenter.onDeletePressed();

        assertEquals(TEST_NUMBER_REDUCED, model.getFirstOperand());

        verify(mockedView).refreshVisor(model.getFirstOperand());
    }

    @Test
    public void onDeletePressedWithFirstOperandWithOperatorAndRefreshVisor(){
        model.setFirstOperand(TEST_NUMBER);
        model.setOperator(OPERATOR_PLUS);

        presenter.onDeletePressed();

        assertEquals(TEST_NUMBER, model.getFirstOperand());
        assertEquals(EMPTY_CHAR, model.getOperator());

        verify(mockedView).refreshVisor(model.getFirstOperand());
    }

    @Test
    public void onDeletePressedWithFirstOperandWithOperatorWithSecondOperandAndRefreshVisor(){
        model.setFirstOperand(TEST_NUMBER);
        model.setOperator(OPERATOR_MULTIPLY);
        model.setSecondOperand(TEST_NUMBER_REDUCED);

        presenter.onDeletePressed();

        assertEquals(TEST_NUMBER, model.getFirstOperand());
        assertEquals(OPERATOR_MULTIPLY, model.getOperator());
        assertEquals(EMPTY_STRING, model.getSecondOperand());

        verify(mockedView).refreshVisor(String.valueOf(model.getOperator()));
    }

    @Test
    public void onDeletePressedWithFirstOperandWithoutOperatorWithSecondOperand(){
        model.setFirstOperand(TEST_NUMBER);
        model.setSecondOperand(TEST_NUMBER);

        presenter.onDeletePressed();

        assertEquals(TEST_NUMBER, model.getSecondOperand());
        assertEquals(TEST_NUMBER, model.getFirstOperand());

        verify(mockedView).showOperatorError();
    }

    @Test
    public void onEqualPressedWithDecimalInFirstOperandWithOperatorMultiplyWithSecondOperandAndShowResult(){
        model.setFirstOperand(TEST_DECIMAL_NUMBER + NUMBER_FIVE);
        model.setOperator(OPERATOR_MULTIPLY);
        model.setSecondOperand(NUMBER_TWO);

        presenter.onEqualPressed();

        assertEquals(TEST_NUMBER_MULTIPLY, model.getFirstOperand());
        assertEquals(TEST_NUMBER_MULTIPLY, model.getResult());
        assertEquals(EMPTY_STRING, model.getSecondOperand());
        assertEquals(EMPTY_CHAR, model.getOperator());

        verify(mockedView).refreshVisor(model.getResult());
    }

    @Test
    public void onEqualPressedWithFirstOperandWithOperatorSubstractionWithDecimalInSecondOperandAndShowResult(){
        model.setFirstOperand(TEST_NUMBER);
        model.setOperator(OPERATOR_SUBSTRACTION);
        model.setSecondOperand(NUMBER_TWO + DECIMAL_POINT + NUMBER_FIVE);

        presenter.onEqualPressed();

        assertEquals(TEST_NUMBER_SUBSTRACTION, model.getFirstOperand());
        assertEquals(TEST_NUMBER_SUBSTRACTION, model.getResult());
        assertEquals(EMPTY_STRING, model.getSecondOperand());
        assertEquals(EMPTY_CHAR, model.getOperator());

        verify(mockedView).refreshVisor(model.getResult());
    }

    @Test
    public void onEqualPressedWithDecimalInFirstOperandWithDivideOperatorWithDecimalInSecondOperandAndShowResult(){
        model.setFirstOperand(TEST_NUMBER_PLUS + DECIMAL_POINT + NUMBER_FIVE);
        model.setOperator(OPERATOR_DIVIDE);
        model.setSecondOperand(NUMBER_TWO + DECIMAL_POINT + NUMBER_FIVE);

        presenter.onEqualPressed();

        assertEquals(TEST_NUMBER_DIVIDE, model.getResult());
        assertEquals(TEST_NUMBER_DIVIDE, model.getFirstOperand());
        assertEquals(EMPTY_STRING, model.getSecondOperand());
        assertEquals(EMPTY_CHAR, model.getOperator());

        verify(mockedView).refreshVisor(model.getResult());
    }

    @Test
    public void onEqualPressedWithFirstOperandWithDivideOperatorWithZeroInSecondOperand() {
        model.setFirstOperand(TEST_NUMBER);
        model.setOperator(OPERATOR_DIVIDE);
        model.setSecondOperand(NUMBER_ZERO);

        presenter.onEqualPressed();

        assertEquals(NUMBER_ZERO, model.getFirstOperand());
        assertEquals(EMPTY_STRING, model.getSecondOperand());
        assertEquals(EMPTY_CHAR, model.getOperator());
        assertEquals(NUMBER_ZERO, model.getResult());

        verify(mockedView).showMathError();
    }

}