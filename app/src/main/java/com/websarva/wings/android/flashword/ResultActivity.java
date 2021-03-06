package com.websarva.wings.android.flashword;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

/**
 * Created by keita0508 on 2018/12/01.
 */
public class ResultActivity extends AppCompatActivity {

    TextView mTextResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        Intent intent = getIntent();
        String answer = intent.getStringExtra(FlashActivity.KEY_ANSWER);

        mTextResult = (TextView) findViewById(R.id.result_text);
        mTextResult.setText(String.valueOf(answer));
    }

    public void goToMain(View v) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}