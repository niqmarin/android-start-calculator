package ru.gb.androidstart.calculator;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
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
    private Button reverseButton;
    private Button percentButton;
    private Button clearButton;
    private Button backspaceButton;
    private TextView inputTextView;
    private TextView lastOperationTextView;
    private int digitCounter = 0;
    private StringBuilder numberStringBuilder;
    private double number;
    private boolean isFloatingPoint;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initializeViews();
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
        reverseButton = findViewById(R.id.reverse_button);
        percentButton = findViewById(R.id.percent_button);
        backspaceButton = findViewById(R.id.backspace_button);
        clearButton = findViewById(R.id.clear_all_button);
        numberStringBuilder = new StringBuilder();
    }

    public void doButtonsClick() {
        zeroButton.setOnClickListener(v -> {
            if (inputTextView.getText() == "" || number != 0 || isFloatingPoint) {
                typeNumber(zeroButton);
            }
        });
        oneButton.setOnClickListener(v -> typeNumber(oneButton));
        twoButton.setOnClickListener(v -> typeNumber(twoButton));
        threeButton.setOnClickListener(v -> typeNumber(threeButton));
        fourButton.setOnClickListener(v -> typeNumber(fourButton));
        fiveButton.setOnClickListener(v -> typeNumber(fiveButton));
        sixButton.setOnClickListener(v -> typeNumber(sixButton));
        sevenButton.setOnClickListener(v -> typeNumber(sevenButton));
        eightButton.setOnClickListener(v -> typeNumber(eightButton));
        nineButton.setOnClickListener(v -> typeNumber(nineButton));
        pointButton.setOnClickListener(v -> typePoint());
    }

    public void typeNumber(Button button) {
        if (digitCounter < 16) {
            numberStringBuilder.append(button.getText());
            digitCounter++;
        }
        number = Double.parseDouble(numberStringBuilder.toString());
        if (number == 0 && isFloatingPoint) {
            inputTextView.setText(numberStringBuilder.toString().replace(".", ","));
        } else {
            DecimalFormat df = new DecimalFormat("###,###.###############",
                    DecimalFormatSymbols.getInstance(new Locale("ru", "RU")));
            inputTextView.setText(df.format(number));
        }
    }

    public void typePoint() {
        if (numberStringBuilder.length() == 0) {
            zeroButton.performClick();
        }
        if (!isFloatingPoint) {
            numberStringBuilder.append(".");
            isFloatingPoint = true;
            inputTextView.setText(inputTextView.getText() + ",");
        }
    }
}