package com.example.myfirstapplicationhit;

import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import android.widget.TextView;
import android.widget.Button;



public class MainActivity extends AppCompatActivity {
    private TextView result;
    private double firstNumber = 0;
    private double secondNumber = 0;
    private String operation = "";
    private boolean isNewOperation = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        result = findViewById(R.id.textViewResult);
        result.setText("");
    }

    public void NumFunction(View view) {
        Button button = (Button) view;
        String buttonText = button.getText().toString();
        String currentText = result.getText().toString();

        if (isNewOperation) {

            if (buttonText.equals(".")) {
                result.setText("0.");
            } else {
                result.setText(buttonText);
            }
            isNewOperation = false;
        } else {

            if (buttonText.equals(".")) {
                if (currentText.contains(".")) {
                    return;
                } else {
                    result.setText(currentText + ".");
                }
            } else {
                result.setText(currentText + buttonText);
            }
        }
    }

    public void OperationFunction(View view) {
        Button button = (Button) view;
        String buttonText = button.getText().toString();

        if (!result.getText().toString().isEmpty()) {
            double currentNumber = Double.parseDouble(result.getText().toString());

            switch (buttonText) {
                case "+":
                    firstNumber = currentNumber;
                    operation = "+";
                    break;

                case "-":
                    firstNumber = currentNumber;
                    operation = "-";
                    break;

                case "*":
                    firstNumber = currentNumber;
                    operation = "*";
                    break;

                case "/":
                    firstNumber = currentNumber;
                    operation = "/";
                    break;

                case "%":
                    firstNumber = currentNumber / 100;
                    result.setText(ValidNumber(firstNumber));
                    operation = "";
                    isNewOperation = true;
                    return;

                default:
                    result.setText("Error");
                    return;
            }

            // מספר שני
            isNewOperation = true;
        }
    }

    private String ValidNumber(double number) {
        if (number == (long) number) {
           // int
            return String.format("%,d", (long) number);
        } else {
            // double
            return String.format("%,.2f", number);
        }
    }
    public void EqualFunction(View view) {
        if (!result.getText().toString().isEmpty()) {
            try {
                double currentNumber = Double.parseDouble(result.getText().toString());

                switch (operation) {
                    case "+":
                        firstNumber += currentNumber;
                        break;

                    case "-":
                        firstNumber -= currentNumber;
                        break;

                    case "*":
                        firstNumber *= currentNumber;
                        break;

                    case "/":
                        if (currentNumber != 0) {
                            firstNumber /= currentNumber;
                        } else {
                            result.setText("Error");
                            return;
                        }
                        break;

                    case "%":
                        firstNumber = firstNumber * (currentNumber / 100);
                        break;

                    default:
                        result.setText("Error");
                        return;
                }


                result.setText(ValidNumber(firstNumber));
                operation = "";
                isNewOperation = true;

            } catch (NumberFormatException e) {
                result.setText("Error");
            }
        } else {
            result.setText("Error");
        }
    }
 //clear
    public void ClearFunction(View view) {
    result.setText("");
    firstNumber = 0;
    secondNumber = 0;
    operation = "";
    isNewOperation = true;
}
}
