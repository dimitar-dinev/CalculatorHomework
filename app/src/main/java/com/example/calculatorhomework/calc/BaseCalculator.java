package com.example.calculatorhomework.calc;

import android.util.Log;

public  class BaseCalculator {

    private static final String TAG = "classcalc";

    public enum Operation {
        ADDITION,
        SUBTRACTION,
        MULTIPLICATION,
        DIVISION,
        PERCENTAGE,
        SQUARE_ROOT,
    }

    private double currentResult;
    private double secondOperand; // for (+ - * -) operations
    private double memoryValue;


    private Operation currentOperation;

    private boolean justCleared;
    private boolean isNewInput;
    private boolean hasNewResult;
    private boolean justCalculated;

    public BaseCalculator() {
        this.clear();
    }

    public double getCurrentResult() {
        return this.currentResult;
    }

    public boolean hasNewResult() {
//        if (hasResult) {
//            hasResult = false;
//            return true;
//        }
//        return false;
        return hasNewResult;
    }

    public boolean isNewInput() {
        return isNewInput;
    }

    public void newDisplayValue(double newValue) {
        // A digit input immediately after calculation resets everything
        if (this.justCalculated) {
            clear();
            this.justCalculated = false;
        }

        if (this.justCleared) {
            this.currentResult = newValue;
            this.hasNewResult = true;
        } else {
            this.secondOperand = newValue;
        }
        this.isNewInput = false;
    }


    // Calculations
    public void newOperation(Operation newOperation) {
        this.justCalculated = false;
        if (hasNewResult) {
            Log.d(TAG, "In operation..");
            if (this.currentOperation != null && this.currentOperation == newOperation && !isNewInput) {
                this.calculate();
            }

            this.justCleared = false;
            this.isNewInput = true;
            this.currentOperation = newOperation;
            Log.d(TAG, "newOperation - " + currentOperation.toString());
        }
    }

    private void add() {
        this.currentResult += this.secondOperand;
        Log.d(TAG, "Addition - " + this.currentResult);
    }

    private void subtract() {
        this.currentResult -= this.secondOperand;
        Log.d(TAG, "Subtraction - " + this.currentResult);
    }

    private void multiply() {
        this.currentResult *= this.secondOperand;
        Log.d(TAG, "Multiplciation - " + this.currentResult);
    }

    private void divide() {
        try {
            this.currentResult /= this.secondOperand;
            Log.d(TAG, "Divisioon - " + this.currentResult);
        } catch (ArithmeticException e) {
            if (this.currentResult > 0)
                this.currentResult = Double.POSITIVE_INFINITY;
            else
                this.currentResult = Double.NEGATIVE_INFINITY;
        }
    }

    private void squareRoot() {
        this.currentResult = Math.sqrt(this.currentResult);
    }

    private void percentage() {
        this.currentResult  = this.currentResult / 100;
    }

    public void changeSign() {
        this.currentResult = -currentResult;
        hasNewResult = true;
    }


    public void calculate() {
        if (currentOperation != null) {
            Log.d(TAG, "calculate() ..");
            switch (currentOperation) {
                case ADDITION:
                    add();
                    break;
                case SUBTRACTION:
                    subtract();
                    break;
                case MULTIPLICATION:
                    multiply();
                    break;
                case DIVISION:
                    divide();
                    break;
                case PERCENTAGE:
                    percentage();
                    break;
                case SQUARE_ROOT:
                    squareRoot();
                    break;
            }
            isNewInput = true;
            hasNewResult = true;
            justCalculated = true;
        }
    }


    // Clear operations
    public void clear() {
        this.justCleared = true;
        this.currentResult = 0;
        this.currentOperation = null;
        this.isNewInput = true;
        this.secondOperand = 0;
        this.hasNewResult = false;
        this.justCalculated = false;
    }


    // Memory operations

     public void allClear() {
         clear();
         this.memoryValue = 0;
     }

     public void clearMemory() {
         this.memoryValue = 0;
     }

     public void addToMemory() {
         this.memoryValue += this.currentResult;
     }

     public void subtractFromMemory() {
         this.memoryValue -= this.currentResult;
     }

     public void memoryRead() {
         this.currentResult = this.memoryValue;
         hasNewResult = true;
     }

}
