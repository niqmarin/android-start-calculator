package ru.gb.androidstart.calculator;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private Button zeroButton;
    private Button oneButton;
    private Button twoButton;
    private Button threeButton;
    private Button fourButton;
    private Button fiveButton;
    private Button sixButton;
    private Button sevenButton;
    private Button eightButton;
    private Button nineButton;
    private Button pointButton;
    private Button plusButton;
    private Button minusButton;
    private Button multiplyButton;
    private Button divideButton;
    private Button negateButton;
    private Button percentButton;
    private Button clearButton;
    private Button backspaceButton;
    private Button equalsButton;
    private TextView inputTextView;
    private TextView lastOperationTextView;
    private int digitCounter = 0;
    private StringBuilder numberStringBuilder;
    private double currentNumber;
    private double firstNumber;
    private double secondNumber;
    private boolean isPointClicked;
    private boolean isCalculated;
    private char operation;
    private String lastOperationStr = "";
    private String inputStr = "";
    private DecimalFormat decimalFormat;
    private static final String KEY_LAST_OPERATION = "LAST_OPERATION";
    private static final String KEY_INPUT = "INPUT";
    private static final String KEY_DIGIT_COUNTER = "DIGIT_COUNTER";
    private static final String KEY_CURRENT_NUMBER = "CURRENT_NUMBER";
    private static final String KEY_FIRST_NUMBER = "FIRST_NUMBER";
    private static final String KEY_SECOND_NUMBER = "SECOND_NUMBER";
    private static final String KEY_OPERATION = "OPERATION";
    private static final String KEY_IS_POINT_CLICKED = "IS_POINT_CLICKED";
    private static final String KEY_IS_CALCULATED = "IS_CALCULATED";
    private static final String KEY_NUMBER_SB = "NUMBER_SB";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initializeViews();
        if (savedInstanceState != null) {
            lastOperationStr = savedInstanceState.getString(KEY_LAST_OPERATION);
            lastOperationTextView.setText(lastOperationStr);
            inputStr = savedInstanceState.getString(KEY_INPUT);
            inputTextView.setText(inputStr);
            digitCounter = savedInstanceState.getInt(KEY_DIGIT_COUNTER);
            numberStringBuilder = new StringBuilder(savedInstanceState.getString(KEY_NUMBER_SB));
            currentNumber = savedInstanceState.getDouble(KEY_CURRENT_NUMBER);
            firstNumber = savedInstanceState.getDouble(KEY_FIRST_NUMBER);
            secondNumber = savedInstanceState.getDouble(KEY_SECOND_NUMBER);
            operation = savedInstanceState.getChar(KEY_OPERATION);
            isPointClicked = savedInstanceState.getBoolean(KEY_IS_POINT_CLICKED);
            isCalculated = savedInstanceState.getBoolean(KEY_IS_CALCULATED);
        }
        doButtonsClick();
    }

    public void initializeViews() {
        inputTextView = findViewById(R.id.input_text_view);
        lastOperationTextView = findViewById(R.id.last_operation_text_view);
        zeroButton = findViewById(R.id.zero_button);
        oneButton = findViewById(R.id.one_button);
        twoButton = findViewById(R.id.two_button);
        threeButton = findViewById(R.id.three_button);
        fourButton = findViewById(R.id.four_button);
        fiveButton = findViewById(R.id.five_button);
        sixButton = findViewById(R.id.six_button);
        sevenButton = findViewById(R.id.seven_button);
        eightButton = findViewById(R.id.eight_button);
        nineButton = findViewById(R.id.nine_button);
        pointButton = findViewById(R.id.point_button);
        plusButton = findViewById(R.id.plus_button);
        minusButton = findViewById(R.id.minus_button);
        multiplyButton = findViewById(R.id.multiply_button);
        divideButton = findViewById(R.id.divide_button);
        negateButton = findViewById(R.id.negate_button);
        percentButton = findViewById(R.id.percent_button);
        backspaceButton = findViewById(R.id.backspace_button);
        clearButton = findViewById(R.id.clear_all_button);
        equalsButton = findViewById(R.id.equals_button);
        numberStringBuilder = new StringBuilder();
        decimalFormat = new DecimalFormat("###,###.###############",
                DecimalFormatSymbols.getInstance(new Locale("ru", "RU")));
    }

    public void doButtonsClick() {
        zeroButton.setOnClickListener(v -> {
            if (inputTextView.getText() == "" || currentNumber != 0 || isPointClicked) {
                typeDigit(zeroButton);
            }
        });
        oneButton.setOnClickListener(v -> typeDigit(oneButton));
        twoButton.setOnClickListener(v -> typeDigit(twoButton));
        threeButton.setOnClickListener(v -> typeDigit(threeButton));
        fourButton.setOnClickListener(v -> typeDigit(fourButton));
        fiveButton.setOnClickListener(v -> typeDigit(fiveButton));
        sixButton.setOnClickListener(v -> typeDigit(sixButton));
        sevenButton.setOnClickListener(v -> typeDigit(sevenButton));
        eightButton.setOnClickListener(v -> typeDigit(eightButton));
        nineButton.setOnClickListener(v -> typeDigit(nineButton));
        pointButton.setOnClickListener(v -> typePoint());
        clearButton.setOnClickListener(v -> clearAll());
        backspaceButton.setOnClickListener(v -> deleteLastChar());
        backspaceButton.setOnLongClickListener(v -> clear());
        plusButton.setOnClickListener(v -> getBasicOperation(plusButton));
        minusButton.setOnClickListener(v -> getBasicOperation(minusButton));
        multiplyButton.setOnClickListener(v -> getBasicOperation(multiplyButton));
        divideButton.setOnClickListener(v -> getBasicOperation(divideButton));
        percentButton.setOnClickListener(v -> calcPercent());
        negateButton.setOnClickListener(v -> negateNumber());
        equalsButton.setOnClickListener(v -> calculate());
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(KEY_LAST_OPERATION, lastOperationStr);
        outState.putString(KEY_INPUT, inputStr);
        outState.putInt(KEY_DIGIT_COUNTER, digitCounter);
        outState.putString(KEY_NUMBER_SB, numberStringBuilder.toString());
        outState.putDouble(KEY_CURRENT_NUMBER, currentNumber);
        outState.putDouble(KEY_FIRST_NUMBER, firstNumber);
        outState.putDouble(KEY_SECOND_NUMBER, secondNumber);
        outState.putChar(KEY_OPERATION, operation);
        outState.putBoolean(KEY_IS_POINT_CLICKED, isPointClicked);
        outState.putBoolean(KEY_IS_CALCULATED, isCalculated);
    }

    public void typeDigit(Button button) {
        if (isCalculated) {
            clearAll();
        }
        if (digitCounter < 16) {
            numberStringBuilder.append(button.getText());
            digitCounter++;
        }
        showNumber();
    }

    public void numberToStringBuilder() {
        numberStringBuilder.setLength(0);
        numberStringBuilder = new StringBuilder(Double.toString(currentNumber));
    }

    public void showNumber() {
        currentNumber = Double.parseDouble(numberStringBuilder.toString());
        if (!isCalculated && isPointClicked && numberStringBuilder.charAt(numberStringBuilder.length() - 1) == '0') {
            inputStr = inputTextView.getText() + "0";
        } else {
            inputStr = decimalFormat.format(currentNumber);
        }
        inputTextView.setText(inputStr);
    }

    public void updateLastOperation() {
        if (isCalculated) {
            lastOperationStr = decimalFormat.format(firstNumber) + " " + operation + " " + decimalFormat.format(secondNumber);
        } else if (lastOperationStr.length() == 0) {
            lastOperationStr = decimalFormat.format(firstNumber) + " " + operation + " ";
        } else {
            lastOperationStr = lastOperationStr + decimalFormat.format(secondNumber);
        }
        lastOperationTextView.setText(lastOperationStr);
    }

    public void typePoint() {
        if (numberStringBuilder.length() == 0) {
            zeroButton.performClick();
        }
        if (!isPointClicked) {
            numberStringBuilder.append(".");
            isPointClicked = true;
            inputStr = inputTextView.getText() + ",";
            inputTextView.setText(inputStr);
        }
    }

    public void clearAll() {
        lastOperationStr = "";
        lastOperationTextView.setText(lastOperationStr);
        isCalculated = false;
        clear();
    }

    public boolean clear() {
        inputStr = "";
        inputTextView.setText(inputStr);
        currentNumber = 0;
        numberStringBuilder.setLength(0);
        isPointClicked = false;
        digitCounter = 0;
        return true;
    }

    public void deleteLastChar() {
        if (numberStringBuilder.length() > 0) {
            if (numberStringBuilder.length() == 1) {
                clear();
            } else {
                if (numberStringBuilder.charAt(numberStringBuilder.length() - 1) == '.') {
                    isPointClicked = false;
                }
                numberStringBuilder.setLength(numberStringBuilder.length() - 1);
                showNumber();
            }
        }
    }

    public void getBasicOperation(Button button) {
        //if lastOperation field has operation and input field has a number second operationButton click
        //leads to calculating first operation and writing result to lastOperation with recent operation
        if (lastOperationStr.length()> 0 &&
                "+-*/".contains(String.valueOf(lastOperationStr.charAt(lastOperationStr.length() - 2))) &&
                inputStr.length() > 0) {
            equalsButton.performClick();
            firstNumber = currentNumber;
        }
        operation = button.getText().charAt(0);
        if (isCalculated) {
            lastOperationStr = "";
            isCalculated = false;
        }
        //if lastOperation field has operation and input field is empty second operationButton click leads to operation change
        if (lastOperationStr.length()> 0 &&
                "+-*/".contains(String.valueOf(lastOperationStr.charAt(lastOperationStr.length() - 2))) &&
                inputStr.length() == 0){
            lastOperationStr = "";
            currentNumber = firstNumber;
        } else {
            firstNumber = currentNumber;
        }
        updateLastOperation();
        clear();
    }

    public void calcPercent() {
        if (lastOperationTextView.getText() == "" || operation == '*' || operation == '/') {
            currentNumber /= 100;
        } else {
            currentNumber = firstNumber * currentNumber / 100;
        }
        numberToStringBuilder();
        showNumber();
        updateLastOperation();
    }

    public void negateNumber() {
        currentNumber *= -1;
        numberToStringBuilder();
        showNumber();
    }

    public void calculate() {
        if (isCalculated) {
            firstNumber = currentNumber;
        } else {
            secondNumber = currentNumber;
        }
        updateLastOperation();
        switch (operation) {
            case '+':
                currentNumber = firstNumber + secondNumber;
                break;
            case '-':
                currentNumber = firstNumber - secondNumber;
                break;
            case '*':
                currentNumber = firstNumber * secondNumber;
                break;
            case '/':
                currentNumber = firstNumber / secondNumber;
                break;
        }
        isCalculated = true;
        numberToStringBuilder();
        if (currentNumber % 1 == 0) {
            numberStringBuilder.setLength(numberStringBuilder.length() - 2);
        }
        showNumber();
    }
}