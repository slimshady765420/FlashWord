package com.websarva.wings.android.flashword;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.sql.Time;
import java.util.Random;

/**
 * Created by keita0508 on 2018/12/01.
 */
public class FlashActivity extends AppCompatActivity {
    public static final String KEY_ANSWER = "answer";
    public final String TAG = "Flash";

    private final int QUESTION_NUMBER = 5;
    private final int TIME_MILLIS_PER_Q = 300;
    private final int TIME_MILLIS_TOTAL = TIME_MILLIS_PER_Q * QUESTION_NUMBER;

    private TextView mTextViewFlash;
    private Button mButtonNext;
    private Button mButtonStart;

    private CountDown mCountDown;
    private String[] mQuestions = new String[QUESTION_NUMBER];
    private String mAnswer = "finish";
    private int mCurrentNum = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flash);

        initViews();

        initQuestions();

        mCountDown = new CountDown(TIME_MILLIS_TOTAL, TIME_MILLIS_PER_Q);
        mCountDown.setOnTickListener(new CountDown.OnTickListener() {
            @Override
            public void onTick(long millisUntilFinished) {
                Log.d(TAG, "" + millisUntilFinished);
                showQuestion();
                nextQuestion();
            }
        });

        mCountDown.setOnFinishListener(new CountDown.OnFinishListener() {
            @Override
            public void onFinish() {
                finishQuestions();
                initQuestions();
            }
        });
    }

    private void initViews() {
        mTextViewFlash = (TextView) findViewById(R.id.flash_main);
        mButtonNext = (Button) findViewById(R.id.next_button);
        mButtonStart = (Button) findViewById(R.id.next_button);
    }

    private void initQuestions() {
        Random random = new Random();
        for (int i = 0; i < QUESTION_NUMBER; i++) {
            mQuestions[i] = "word";
        }
    }

    private void showQuestion() {
        if (mCurrentNum < QUESTION_NUMBER)
            mTextViewFlash.setText(String.valueOf(mQuestions[mCurrentNum]));
    }

    private void nextQuestion() {
        mCurrentNum++;
    }

    private void finishQuestions() {
        mButtonNext.setVisibility(View.VISIBLE);
        mTextViewFlash.setText("");
    }

    public void goToResult(View v) {
        Intent intent = new Intent(this, ResultActivity.class);
        intent.putExtra(KEY_ANSWER, mAnswer);
        startActivity(intent);
    }

    public void start(View v) {
        mButtonStart.setVisibility(View.INVISIBLE);
        mCountDown.start();
    }
}