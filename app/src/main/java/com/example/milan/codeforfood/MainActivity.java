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
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{


    private Button buttonSignIn;
    private EditText editTextEmail;
    private EditText editTextPassword;
    private TextView textViewSignUp;

    private FirebaseAuth firebaseAuth;

    private ProgressDialog progressDialog;








    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        firebaseAuth =  FirebaseAuth.getInstance();

        if (firebaseAuth.getCurrentUser()!= null){
            //profile activity here
           finish();
            startActivity(new Intent(getApplicationContext(),ProfileView.class));
        }

        Button buttonLM = (Button)findViewById(R.id.buttonLM);
        Button buttonJTC = (Button)findViewById(R.id.buttonJtC);

        editTextEmail = (EditText)findViewById(R.id.editTextEmail);
        editTextPassword = (EditText)findViewById(R.id.editTextPassword);
        buttonSignIn = (Button)findViewById(R.id.buttonSignIn);
        textViewSignUp = (TextView)findViewById(R.id.textViewSignUp);

        progressDialog = new ProgressDialog(this);

        buttonSignIn.setOnClickListener(this);

       textViewSignUp.setOnClickListener(this);


//
        buttonLM.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent int1 = new Intent(MainActivity.this,LearnMore.class);
                startActivity(int1);
            }
        });
//
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

   private void userLogin(){
        String email = editTextEmail.getText().toString().trim();
      String password = editTextPassword.getText().toString().trim();

       if (TextUtils.isEmpty(email)){
           //email is Empty
           Toast.makeText(this,"PLease Enter the email",Toast.LENGTH_LONG).show();
            //stopping the function from executing
           return;
       }

        if(TextUtils.isEmpty(password)){
           //password is Empty
            Toast.makeText(this,"Please Enter the Password",Toast.LENGTH_LONG).show();
            return;
        }
       progressDialog.setMessage("Logged In");
       progressDialog.show();

        firebaseAuth.signInWithEmailAndPassword(email,password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
           public void onComplete(@NonNull Task<AuthResult> task) {
               progressDialog.dismiss();
                if (task.isSuccessful()){
                    //start the profile actiity
                    finish();
                    startActivity(new Intent(getApplicationContext(),ProfileView.class));

                }
            }
       });

    }

    @Override
    public void onClick(View view) {
        if (view == buttonSignIn){
            userLogin();
        }

        if(view == textViewSignUp){
            finish();
            startActivity(new Intent(this,SignUp.class));
        }
    }
}
