package com.example.school.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.text.DateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("MainActivity", "User clicked the button");
                performCalc();
            }
        });

        Button helpButton = findViewById(R.id.helpButton);
        helpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showHelp();
            }
        });
    }

    void showHelp() {
        Intent intent = new Intent(this, HelpActivity.class);
        startActivity(intent);
    }

    void performCalc() {
        EditText editTextA = findViewById(R.id.editTextA);
        EditText editTextB = findViewById(R.id.editTextB);

        if (validateField(editTextA) & validateField(editTextB)) {
            String textA = editTextA.getText().toString();
            int a = parseInt(textA);

            String textB = editTextB.getText().toString();
            int b = parseInt(textB);

            int res = summ(a, b);

            TextView resultText = findViewById(R.id.resultText);
            resultText.setText(String.valueOf(res));
            addHistory(a, b, res);
        }
    }

    void addHistory(int a, int b, int res) {
        TextView historyTextView = findViewById(R.id.historyTextView);
        String history = historyTextView.getText().toString();

        Date date = new Date(System.currentTimeMillis());
        DateFormat dateFormat = android.text.format.DateFormat
                .getTimeFormat(getApplicationContext());
        String time = dateFormat.format(date);

        String item = String.format("%d+%d=%d %s\n", a, b, res, time);
        history += item;
        historyTextView.setText(history);
    }

    boolean validateField(TextView textView) {
        if (textView.getText().length() != 0) {
            return true;
        } else {
            textView.setError("Field is empty");
            return false;
        }
    }

    int parseInt(String text) {
        if (text.isEmpty()) {
            Log.w("MainActivity", "parseInt: text is empty");
            return 0;
        } else {
            return Integer.valueOf(text);
        }
    }

    int summ(int a, int b) {
        return a + b;
    }
}
