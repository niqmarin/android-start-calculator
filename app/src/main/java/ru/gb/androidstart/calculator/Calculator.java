package ru.gb.androidstart.calculator;

import android.os.Parcel;
import android.os.Parcelable;

import java.math.BigDecimal;

import androidx.annotation.NonNull;

public class Calculator implements Parcelable {

    private BigDecimal firstNumber;
    private BigDecimal secondNumber;
    private BigDecimal resultNumber;
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
        firstNumber = new BigDecimal(0);
        secondNumber =new BigDecimal(0);
        resultNumber = new BigDecimal(0);
        operation = Operation.ADD;
    }

    protected Calculator(Parcel in) {
        firstNumber = (BigDecimal) in.readSerializable();
        secondNumber = (BigDecimal) in.readSerializable();
        resultNumber = (BigDecimal) in.readSerializable();
        operation = Operation.values()[in.readInt()];
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeSerializable(firstNumber);
        dest.writeSerializable(secondNumber);
        dest.writeSerializable(resultNumber);
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

    public BigDecimal calculate() {
        switch (operation) {
            case ADD:
                resultNumber = firstNumber.add(secondNumber);
                break;
            case SUBTRACT:
                resultNumber = firstNumber.subtract(secondNumber);
                break;
            case MULTIPLY:
                resultNumber = firstNumber.multiply(secondNumber);
                break;
            case DIVIDE:
                resultNumber = firstNumber.divide(secondNumber);
                break;
        }
        return resultNumber;
    }

    public BigDecimal noMemorizeCalculate(BigDecimal outerFirstNumber, BigDecimal outerSecondNumber, Operation outerOperation) {
        switch (outerOperation) {
            case ADD:
                resultNumber = outerFirstNumber.add(outerSecondNumber);
                break;
            case SUBTRACT:
                resultNumber = outerFirstNumber.subtract(outerSecondNumber);
                break;
            case MULTIPLY:
                resultNumber = outerFirstNumber.multiply(outerSecondNumber);
                break;
            case DIVIDE:
                resultNumber = outerFirstNumber.divide(outerSecondNumber);
                break;
        }
        return resultNumber;
    }

    public BigDecimal getFirstNumber() {
        return firstNumber;
    }

    public void setFirstNumber(BigDecimal firstNumber) {
        this.firstNumber = firstNumber;
    }

    public BigDecimal getSecondNumber() {
        return secondNumber;
    }

    public void setSecondNumber(BigDecimal secondNumber) {
        this.secondNumber = secondNumber;
    }

    public Operation getOperation() {
        return operation;
    }

    public void setOperation(Operation operation) {
        this.operation = operation;
    }
}
