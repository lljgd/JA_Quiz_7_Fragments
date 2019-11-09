package com.example.myapplication;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private Question question = new Question(
            R.string.question_text,
            true
    );

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView questionString = findViewById(R.id.question_string);
        questionString.setText(question.getQuestionResId());

        Button trueButton = findViewById(R.id.true_button);
        trueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onButtonClicked(true);
            }
        });

        Button falseButton = findViewById(R.id.false_button);
        falseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onButtonClicked(false);
            }
        });
    }

    private void onButtonClicked(boolean answer) {
        int toastMessage = (question.isCorrectAnswer() == answer) ?
                R.string.correct_toast :
                R.string.incorrect_toast;

        Toast.makeText(
                MainActivity.this,
                toastMessage,
                Toast.LENGTH_LONG
        ).show();
    }
}
