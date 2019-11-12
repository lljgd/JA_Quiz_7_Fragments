package com.example.myapplication;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends LoggingActivity {

    private static final String KEY_CURRENT_INDEX = "key_current_index";

    private Question[] mQuestionBank = new Question[] {
            new Question(R.string.question_australia, true),
            new Question(R.string.question_oceans, true),
            new Question(R.string.question_mideast, false),
            new Question(R.string.question_africa, false),
            new Question(R.string.question_americas, true),
            new Question(R.string.question_asia, true),
    };

    private int mCurrentIndex = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState != null) {
            mCurrentIndex = savedInstanceState.getInt(KEY_CURRENT_INDEX);
        }

        final TextView questionString = findViewById(R.id.question_string);
        final Question currentQuestion = mQuestionBank[mCurrentIndex];
        questionString.setText(currentQuestion.getQuestionResId());

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

        Button nextButton = findViewById(R.id.next_button);
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCurrentIndex = (mCurrentIndex + 1) % mQuestionBank.length;

                final Question currentQuestion = mQuestionBank[mCurrentIndex];
                questionString.setText(currentQuestion.getQuestionResId());
            }
        });
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(KEY_CURRENT_INDEX, mCurrentIndex);
    }

    private void onButtonClicked(boolean answer) {
        Question currentQuestion = mQuestionBank[mCurrentIndex];
        int toastMessage = (currentQuestion.isCorrectAnswer() == answer) ?
                R.string.correct_toast :
                R.string.incorrect_toast;

        Toast.makeText(
                MainActivity.this,
                toastMessage,
                Toast.LENGTH_LONG
        ).show();
    }
}
