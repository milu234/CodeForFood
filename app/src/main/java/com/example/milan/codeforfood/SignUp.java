package com.example.milan.codeforfood;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class SignUp extends AppCompatActivity implements View.OnClickListener{

    private Button buttonRegister;
    private EditText editTextEmail;
    private EditText editTextPassword;
    private TextView textViewSignIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        buttonRegister = (Button)findViewById(R.id.buttonRegister);

        editTextEmail = (EditText)findViewById(R.id.editTextEMail);
        editTextPassword = (EditText)findViewById(R.id.editTextPassword);

        textViewSignIn = (TextView)findViewById(R.id.textViewSignIn);

        buttonRegister.setOnClickListener(this);
        textViewSignIn.setOnClickListener(this);
    }

    private void registerUser(){
        String email = editTextEmail.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();

        if (TextUtils.isEmpty(email)){
            //email is Empty
            Toast.makeText(this,"PLease Enter the email",Toast.LENGTH_SHORT).show();
            //stopping the function from executing
        }

        if(TextUtils.isEmpty(password)){
            //password is Empty
        }
    }

    @Override
    public void onClick(View view) {
        if (view == buttonRegister){
            registerUser;
        }

        if(view == textViewSignIn){
            //open login
        }

    }
}
