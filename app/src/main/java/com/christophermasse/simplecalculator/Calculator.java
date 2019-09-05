package com.christophermasse.simplecalculator;

import com.christophermasse.simplecalculator.service.ArithmeticService;
import com.christophermasse.simplecalculator.service.ArithmeticOperation;
import com.christophermasse.simplecalculator.service.BasicOperations;

public class Calculator {

    private BasicOperations mService = new ArithmeticService();

    // Default: no active operation
    private ArithmeticOperation mActiveOp = ArithmeticOperation.NONE;

    private double mStoredValue;

    public double operator(ArithmeticOperation operator, double operand) throws ArithmeticException{

        if (mActiveOp == ArithmeticOperation.NONE){
            //Starting a new operation, don't perform any calculations
            this.mStoredValue = operand;
        } else if (mActiveOp == operator){
            // Chained operation- calculate and return the updated value
            this.mStoredValue = calculate(operand);
        }
        this.mActiveOp = operator;
        return this.mStoredValue;
    }

    public double calculate(double input) throws ArithmeticException{
        switch (mActiveOp){
            case ADDITION:
                this.mStoredValue = mService.add(mStoredValue, input);
                break;
            case SUBTRACTION:
                this.mStoredValue = mService.subtract(mStoredValue, input);
                break;
            case MULTIPLICATION:
                this.mStoredValue = mService.multiply(mStoredValue, input);
                break;
            case DIVISION:
                this.mStoredValue = mService.divide(mStoredValue, input);
                break;
            default:
                //Do nothing
        }
        return this.mStoredValue;
    }

    public void clearAll(){
        this.mActiveOp = ArithmeticOperation.NONE;
        this.mStoredValue = 0d;
    }
}
