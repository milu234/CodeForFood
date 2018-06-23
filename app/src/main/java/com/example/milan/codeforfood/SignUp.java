package com.example.milan.codeforfood;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignUp extends AppCompatActivity implements View.OnClickListener{

    private Button buttonRegister;
    private EditText editTextEmail;
    private EditText editTextPassword;
    private TextView textViewSignIn;

    private ProgressDialog progressDialog;

    private FirebaseAuth firebaseAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        firebaseAuth = FirebaseAuth.getInstance();

        if (firebaseAuth.getCurrentUser()!= null){
            //profile activity here
            finish();
            startActivity(new Intent(getApplicationContext(),ProfileActivity.class));
        }

        progressDialog = new ProgressDialog(this);

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
            return;
        }

        if(TextUtils.isEmpty(password)){
            //password is Empty
            Toast.makeText(this,"Please Enter the Password",Toast.LENGTH_SHORT).show();
            return;
        }
        progressDialog.setMessage("Registering User");
        progressDialog.show();

        firebaseAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                progressDialog.dismiss();
                if (task.isSuccessful()){
                    //User is completely register
                    //we will start the profile activity here


                        finish();
                        startActivity(new Intent(getApplicationContext(),ProfileActivity.class));


                }else{
                    Toast.makeText(SignUp.this,"Couldn't Register Please try again",Toast.LENGTH_SHORT).show();
                }
                progressDialog.dismiss();
            }
        });

        

        //if the form is valid we will show the progress bar

    }

    @Override
    public void onClick(View view) {
        if (view == buttonRegister){
            registerUser();
        }

        if(view == textViewSignIn){
            //open login
            startActivity(new Intent(this,MainActivity.class));
        }

    }
}
