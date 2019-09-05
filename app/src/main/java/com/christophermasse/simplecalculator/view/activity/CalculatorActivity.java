package com.christophermasse.simplecalculator.view.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.HapticFeedbackConstants;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.christophermasse.simplecalculator.Calculator;
import com.christophermasse.simplecalculator.R;
import com.christophermasse.simplecalculator.exception.ExcessiveValueException;
import com.christophermasse.simplecalculator.service.ArithmeticOperation;
import com.christophermasse.simplecalculator.utils.FormattingUtil;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import butterknife.BindView;
import butterknife.ButterKnife;


public class CalculatorActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "CalculatorActivity";

    private boolean CLEAR_ON_NEXT_KEY = false;

    private double mOperand;

    @BindView(R.id.btn_0)
    Button btn_0;

    @BindView(R.id.btn_1)
    Button btn_1;

    @BindView(R.id.btn_2)
    Button btn_2;

    @BindView(R.id.btn_3)
    Button btn_3;

    @BindView(R.id.btn_4)
    Button btn_4;

    @BindView(R.id.btn_5)
    Button btn_5;

    @BindView(R.id.btn_6)
    Button btn_6;

    @BindView(R.id.btn_7)
    Button btn_7;

    @BindView(R.id.btn_8)
    Button btn_8;

    @BindView(R.id.btn_9)
    Button btn_9;

    @BindView(R.id.btn_subtraction)
    Button btn_subtraction;

    @BindView(R.id.btn_addition)
    Button btn_addition;

    @BindView(R.id.btn_multiplication)
    Button bnt_multiplication;

    @BindView(R.id.btn_division)
    Button btn_division;

    @BindView(R.id.btn_sign)
    Button btn_sign;

    @BindView(R.id.btn_decimal)
    Button btn_decimal;

    @BindView(R.id.btn_clear_all)
    Button btn_clear;

    @BindView(R.id.btn_calculate)
    Button btn_calculate;

    @BindView(R.id.text_output)
    EditText mDisplay;


    @BindView(R.id.text_error)
    TextView mError;


    private boolean lastClickedWasOperator = false;

    private Calculator mCalculator;
    private int lastClicked;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);

        ButterKnife.bind(this);

        mCalculator = new Calculator();

        btn_0.setOnClickListener(this);
        btn_1.setOnClickListener(this);
        btn_2.setOnClickListener(this);
        btn_3.setOnClickListener(this);
        btn_4.setOnClickListener(this);
        btn_5.setOnClickListener(this);
        btn_6.setOnClickListener(this);
        btn_7.setOnClickListener(this);
        btn_8.setOnClickListener(this);
        btn_9.setOnClickListener(this);

        btn_subtraction.setOnClickListener(this);
        btn_addition.setOnClickListener(this);
        bnt_multiplication.setOnClickListener(this);
        btn_division.setOnClickListener(this);
        btn_decimal.setOnClickListener(this);
        btn_clear.setOnClickListener(this);
        btn_sign.setOnClickListener(this);
        btn_calculate.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        int viewId = view.getId();

        Log.d(TAG, "clicked on view with id " + viewId);

        view.performHapticFeedback(HapticFeedbackConstants.VIRTUAL_KEY);

        // Remove error message
        showError(false);

        switch (viewId) {
            case R.id.btn_0:
                handleNumberInput(0);
                break;
            case R.id.btn_1:
                handleNumberInput(1);
                break;
            case R.id.btn_2:
                handleNumberInput(2);
                break;
            case R.id.btn_3:
                handleNumberInput(3);
                break;
            case R.id.btn_4:
                handleNumberInput(4);
                break;
            case R.id.btn_5:
                handleNumberInput(5);
                break;
            case R.id.btn_6:
                handleNumberInput(6);
                break;
            case R.id.btn_7:
                handleNumberInput(7);
                break;
            case R.id.btn_8:
                handleNumberInput(8);
                break;
            case R.id.btn_9:
                handleNumberInput(9);
                break;
            case R.id.btn_addition:
                handleOperator(ArithmeticOperation.ADDITION);
                break;
            case R.id.btn_subtraction:
                handleOperator(ArithmeticOperation.SUBTRACTION);
                break;
            case R.id.btn_multiplication:
                handleOperator(ArithmeticOperation.MULTIPLICATION);
                break;
            case R.id.btn_division:
                handleOperator(ArithmeticOperation.DIVISION);
                break;
            case R.id.btn_calculate:
                // Don't use the displayed input if its the answer or if equals clicked twice
                if (lastClicked != viewId) {
                    mOperand = getCurrentVal();
                }
                try{
                    setCalcInput(mCalculator.calculate(mOperand));
                } catch (ArithmeticException e) {
                    showError(true);
                }
                CLEAR_ON_NEXT_KEY = true;
                break;
            case R.id.btn_sign:
                setCalcInput(getCurrentVal() * -1d);
                break;
            case R.id.btn_clear_all:
                mDisplay.setText("0");
                mCalculator.clearAll();
                //TODO: handle single vs double clear
                break;
            default:
        }
        lastClicked = viewId;
    }

    /**
     * Register click of a number key
     *
     * @param num value/key clicked
     */

    private void handleNumberInput(double num) {
        lastClickedWasOperator = false;
        //If key clicked after the completion of a previous operation, clear display before
        if (CLEAR_ON_NEXT_KEY) {
            mDisplay.setText("");
            this.CLEAR_ON_NEXT_KEY = false;
        }

        String str = mDisplay.getEditableText().toString();

        //Check if at maximum digits
        if (str.length() == FormattingUtil.MAX_LENGTH) return;

        //If only zero, clear so that a zero is returned again
        if (str.equals("0")) str = "";

        //Append existing display
        try {
            str = str + FormattingUtil.toString(num);
        } catch (ExcessiveValueException e) {
            e.printStackTrace();
        }
        mDisplay.setText(str);
    }

    private void handleOperator(ArithmeticOperation op) {
        if (lastClickedWasOperator) return;

        lastClickedWasOperator = true;

        //Operand is manually entered
        if (!CLEAR_ON_NEXT_KEY) {
            mOperand = getCurrentVal();
        }

        try{
            double d = mCalculator.operator(op, mOperand);
            setCalcInput(d);
        } catch (ArithmeticException e){
            showError(true);
            CLEAR_ON_NEXT_KEY = true;
        }

        CLEAR_ON_NEXT_KEY = true;
    }

    /**
     *
     * @return currently displayed input
     */

    double getCurrentVal() {
        try {
            return FormattingUtil.parseDouble(mDisplay.getEditableText().toString());
        } catch (NumberFormatException | NullPointerException e) {
            return 0d;
        }
    }

    private void setCalcInput(double input) {
        try {
            mDisplay.setText(FormattingUtil.toString(input));
        } catch (ExcessiveValueException e) {
            e.printStackTrace();
            showError(true);
        }
    }

    private void showError(boolean isShown) {
        if (isShown){
            mError.setVisibility(View.VISIBLE);
            mDisplay.setVisibility(View.INVISIBLE);
            mCalculator.clearAll();
        } else {
            mError.setVisibility(View.INVISIBLE);
            mDisplay.setVisibility(View.VISIBLE);
        }
    }
}
