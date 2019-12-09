package com.example.myapplication;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class QuestionsFragment extends Fragment {

    private static final String KEY_CURRENT_INDEX = "key_current_index";

    private Question[] mQuestionBank = new Question[] {
            new Question(R.string.question_australia, true),
            new Question(R.string.question_oceans, true),
            new Question(R.string.question_mideast, false),
            new Question(R.string.question_africa, false),
            new Question(R.string.question_americas, true),
            new Question(R.string.question_asia, true),
    };

    private TextView questionString;
    private Button trueButton;
    private Button falseButton;
    private Button nextButton;

    private int mCurrentIndex = 0;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (savedInstanceState != null) {
            mCurrentIndex = savedInstanceState.getInt(KEY_CURRENT_INDEX);
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_questions, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        questionString = view.findViewById(R.id.question_string);
        trueButton = view.findViewById(R.id.true_button);
        falseButton = view.findViewById(R.id.false_button);
        nextButton = view.findViewById(R.id.next_button);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        final Question currentQuestion = mQuestionBank[mCurrentIndex];
        questionString.setText(currentQuestion.getQuestionResId());

        trueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onButtonClicked(true);
            }
        });

        falseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onButtonClicked(false);
            }
        });

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
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(KEY_CURRENT_INDEX, mCurrentIndex);
    }

    private void onButtonClicked(boolean answer) {
        Question currentQuestion = mQuestionBank[mCurrentIndex];
        int toastMessage = (currentQuestion.isCorrectAnswer() == answer) ?
                R.string.correct_toast :
                R.string.incorrect_toast;

        Toast.makeText(
                getActivity(),
                toastMessage,
                Toast.LENGTH_LONG
        ).show();
    }
}

