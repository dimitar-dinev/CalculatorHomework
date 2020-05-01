package com.example.calculatorhomework;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.calculatorhomework.calc.BaseCalculator;

public class MainActivity extends AppCompatActivity {


    public static final String TAG = "MAINACTIVITY";

    Button zeroButton;
    Button oneButton;
    Button twoButton;
    Button threeButton;
    Button fourButton;
    Button fiveButton;
    Button sixButton;
    Button sevenButton;
    Button eightButton;
    Button nineButton;

    Button allClearButton;
    Button clearButton;

    Button memoryClearButton;
    Button memoryReadButton;
    Button memorySubtractButton;
    Button memoryAdditionButton;

    Button additionButton;
    Button subtractionButton;
    Button multiplyButton;
    Button divisionButton;
    Button percentButton;
    Button squareRootButton;
    Button changeSignButton;
    Button decimalButton;
    Button resultButton;

    TextView mainTextView;
    TextView memoryTextView;

    private String currentInput;
    private boolean isMemoryValueSet = false;

    BaseCalculator calculator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setupViews();

        calculator = new BaseCalculator();
        calculatorDisplay();
    }


    private View.OnClickListener digitListener = v -> {
        Button button = (Button) v;
        if (currentInput == null) {
            currentInput = "";
        }

        if (calculator.isNewInput()) {
            currentInput = "";
        }

        currentInput += button.getText().toString();
        calculator.newDisplayValue(Double.parseDouble(currentInput));
        mainTextView.setText(currentInput);
    };

    private View.OnClickListener operationListener = v -> {
        switch (v.getId()) {
            case R.id.additionButton:
                calculator.newOperation(BaseCalculator.Operation.ADDITION);
                break;
            case R.id.subtractButton:
                calculator.newOperation(BaseCalculator.Operation.SUBTRACTION);
                break;
            case R.id.multiplyButton:
                calculator.newOperation(BaseCalculator.Operation.MULTIPLICATION);
                break;
            case R.id.divideButton:
                calculator.newOperation(BaseCalculator.Operation.DIVISION);
                break;
            case R.id.percentButton:
                calculator.newOperation(BaseCalculator.Operation.PERCENTAGE);
                calculator.calculate();
                break;
            case R.id.squareRootButton:
                calculator.newOperation(BaseCalculator.Operation.SQUARE_ROOT);
                calculator.calculate();
                break;
            case R.id.changeSignButton:
                calculator.changeSign();
                break;
            case R.id.memoryAdditionButton:
                calculator.addToMemory();
                isMemoryValueSet = true;
                memoryDisplay();
                break;
            case R.id.memorySubtractButton:
                calculator.subtractFromMemory();
                isMemoryValueSet = true;
                memoryDisplay();
                break;
            case R.id.memoryReadButton:
                calculator.memoryRead();
                break;
            case R.id.memoryClearButton:
                calculator.clearMemory();
                isMemoryValueSet = false;
                memoryDisplay();
                break;
            case R.id.clearButton:
                calculator.clear();
                calculatorDisplay();
                break;
            case R.id.allClearButton:
                calculator.allClear();
                isMemoryValueSet = false;
                calculatorDisplay();
                memoryDisplay();
                break;
            case R.id.calculateButton2:
                calculator.calculate();
                break;
        }

        if (calculator.hasNewResult()) {
            calculatorDisplay();
        }
    };


    private void setupViews() {

        // Views
        mainTextView = findViewById(R.id.mainTextView);
        memoryTextView = findViewById(R.id.memoryTextView);


        // Number buttons
        zeroButton = findViewById(R.id.zeroButton);
        oneButton = findViewById(R.id.oneButton);
        twoButton = findViewById(R.id.twoButton);
        threeButton = findViewById(R.id.threeButton);
        fourButton =  findViewById(R.id.fourButton);
        fiveButton = findViewById(R.id.fiveButton);
        sixButton = findViewById(R.id.sixButton);
        sevenButton = findViewById(R.id.sevenButton);
        eightButton = findViewById(R.id.eightButton);
        nineButton = findViewById(R.id.nineButton);

        zeroButton.setOnClickListener(digitListener);
        oneButton.setOnClickListener(digitListener);
        twoButton.setOnClickListener(digitListener);
        threeButton.setOnClickListener(digitListener);
        fourButton.setOnClickListener(digitListener);
        fiveButton.setOnClickListener(digitListener);
        sixButton.setOnClickListener(digitListener);
        sevenButton.setOnClickListener(digitListener);
        sevenButton.setOnClickListener(digitListener);
        eightButton.setOnClickListener(digitListener);
        nineButton.setOnClickListener(digitListener);



        // Memory buttons

        memoryClearButton = findViewById(R.id.memoryClearButton);
        memoryReadButton = findViewById(R.id.memoryReadButton);
        memorySubtractButton = findViewById(R.id.memorySubtractButton);
        memoryAdditionButton = findViewById(R.id.memoryAdditionButton);

        memoryReadButton.setOnClickListener(operationListener);
        memorySubtractButton.setOnClickListener(operationListener);
        memoryAdditionButton.setOnClickListener(operationListener);
        memoryClearButton.setOnClickListener(operationListener);


        // Operation buttons
        additionButton = findViewById(R.id.additionButton);
        subtractionButton = findViewById(R.id.subtractButton);
        multiplyButton = findViewById(R.id.multiplyButton);
        divisionButton = findViewById(R.id.divideButton);
        percentButton = findViewById(R.id.percentButton);
        squareRootButton = findViewById(R.id.squareRootButton);
        changeSignButton = findViewById(R.id.changeSignButton);
        resultButton = findViewById(R.id.calculateButton2);

        additionButton.setOnClickListener(operationListener);
        subtractionButton.setOnClickListener(operationListener);
        multiplyButton.setOnClickListener(operationListener);
        divisionButton.setOnClickListener(operationListener);
        percentButton.setOnClickListener(operationListener);
        squareRootButton.setOnClickListener(operationListener);
        changeSignButton.setOnClickListener(operationListener);
        resultButton.setOnClickListener(operationListener);


        // Clear buttons
        clearButton = findViewById(R.id.clearButton);
        allClearButton = findViewById(R.id.allClearButton);

        clearButton.setOnClickListener(operationListener);
        allClearButton.setOnClickListener(operationListener);


        // Decimal button
        decimalButton = findViewById(R.id.decimalButton);
        decimalButton.setOnClickListener(l -> {
            Log.d(TAG, "currentInput = " + currentInput);
            currentInput += ".";
            mainTextView.setText(currentInput);
            Log.d(TAG, "currentInput = " + currentInput);
            calculator.newDisplayValue(Double.parseDouble(currentInput));
        });
    }

    private void calculatorDisplay() {

        double result = calculator.getCurrentResult();
//        currentInput = String.valueOf(format.format(result));
        currentInput = String.valueOf(result);
        if (currentInput.substring(currentInput.length() - 2 ).equals(".0")) {
            currentInput = currentInput.substring(0, currentInput.length() - 2);
        }
        Log.d(TAG, "Mainactivity result =  " + result);
        mainTextView.setText(currentInput);
    }

    private void memoryDisplay() {
        if (isMemoryValueSet) {
            memoryTextView.setText("M");
        } else {
            memoryTextView.setText("");
        }
    }
}
