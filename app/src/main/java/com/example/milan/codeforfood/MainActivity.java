package com.example.milan.codeforfood;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {









    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button buttonLM = (Button)findViewById(R.id.buttonLM);
        Button buttonJTC = (Button)findViewById(R.id.buttonJtC);



        buttonLM.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent int1 = new Intent(MainActivity.this,LearnMore.class);
                startActivity(int1);
            }
        });

        buttonJTC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openActivity();
            }
        });
    }
    public void  openActivity(){
        Intent intent = new Intent(this,SignUp.class);
        startActivity(intent);
    }
}
