package ru.gb.androidstart.calculator;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class Calculator implements Parcelable {

    private double firstNumber;
    private double secondNumber;
    private double resultNumber;
    private Operation operation;

    public enum Operation {
        ADD ("+"),
        SUBTRACT ("-"),
        MULTIPLY ("*"),
        DIVIDE ("/");

        private final String OPERATION_SYMBOL;

        Operation (String OPERATION_SYMBOL) {
            this.OPERATION_SYMBOL = OPERATION_SYMBOL;
        }

        @NonNull
        @Override
        public String toString() {
            return OPERATION_SYMBOL;
        }
    }

    public Calculator() {
        firstNumber = 0;
        secondNumber = 0;
        resultNumber = 0;
        operation = Operation.ADD;
    }

    protected Calculator(Parcel in) {
        firstNumber = in.readDouble();
        secondNumber = in.readDouble();
        resultNumber = in.readDouble();
        operation = Operation.values()[in.readInt()];
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeDouble(firstNumber);
        dest.writeDouble(secondNumber);
        dest.writeDouble(resultNumber);
        dest.writeInt(operation.ordinal());
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Calculator> CREATOR = new Creator<Calculator>() {
        @Override
        public Calculator createFromParcel(Parcel in) {
            return new Calculator(in);
        }

        @Override
        public Calculator[] newArray(int size) {
            return new Calculator[size];
        }
    };

    public double calculate() {
        switch (operation) {
            case ADD:
                resultNumber = firstNumber + secondNumber;
                break;
            case SUBTRACT:
                resultNumber = firstNumber - secondNumber;
                break;
            case MULTIPLY:
                resultNumber = firstNumber * secondNumber;
                break;
            case DIVIDE:
                resultNumber = firstNumber / secondNumber;
                break;
        }
        return resultNumber;
    }

    public double noMemorizeCalculate(double outerFirstNumber, double outerSecondNumber, Operation outerOperation) {
        switch (outerOperation) {
            case ADD:
                resultNumber = outerFirstNumber + outerSecondNumber;
                break;
            case SUBTRACT:
                resultNumber = outerFirstNumber - outerSecondNumber;
                break;
            case MULTIPLY:
                resultNumber = outerFirstNumber * outerSecondNumber;
                break;
            case DIVIDE:
                resultNumber = outerFirstNumber / outerSecondNumber;
                break;
        }
        return resultNumber;
    }

    public double getFirstNumber() {
        return firstNumber;
    }

    public void setFirstNumber(double firstNumber) {
        this.firstNumber = firstNumber;
    }

    public double getSecondNumber() {
        return secondNumber;
    }

    public void setSecondNumber(double secondNumber) {
        this.secondNumber = secondNumber;
    }

    public Operation getOperation() {
        return operation;
    }

    public void setOperation(Operation operation) {
        this.operation = operation;
    }
}
