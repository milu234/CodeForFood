package com.example.milan.codeforfood;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserInfo;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ProfileActivity extends AppCompatActivity implements View.OnClickListener{

    private FirebaseAuth firebaseAuth;

    private TextView textViewUserEmail;
    private Button buttonLogout;
    private DatabaseReference databaseReference;

    private EditText editTextFullName,editTextMobileNumber;
    private Button buttonSaveInformation;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        firebaseAuth = FirebaseAuth.getInstance();

        if (firebaseAuth.getCurrentUser()==null){
            finish();
            startActivity(new Intent(this,MainActivity.class));
        }

        databaseReference = FirebaseDatabase.getInstance().getReference();

        editTextFullName = (EditText)findViewById(R.id.editTextFullName);
        editTextMobileNumber =(EditText)findViewById(R.id.editTextMobileNumber);
        buttonSaveInformation =(Button)findViewById(R.id.buttonSaveInformation);

        FirebaseUser user = firebaseAuth.getCurrentUser();



        textViewUserEmail = (TextView)findViewById(R.id.textViewUserEmail);

        textViewUserEmail.setText("Welcome " + user.getEmail());
        buttonLogout = (Button)findViewById(R.id.buttonLogout);

        buttonLogout.setOnClickListener(this);
        buttonSaveInformation.setOnClickListener(this);
    }

    private void saveUserInformation(){
        String name = editTextFullName.getText().toString().trim();
        String mob = editTextMobileNumber.getText().toString().trim();

        UserInformation userInformation = new UserInformation(name,mob);

        FirebaseUser user = firebaseAuth.getCurrentUser();

        databaseReference.child(user.getUid()).setValue(userInformation);

        Toast.makeText(this,"Information Saved",Toast.LENGTH_LONG).show();


    }

    @Override
    public void onClick(View view) {
        if (view==buttonLogout){
            firebaseAuth.signOut();
            finish();
            startActivity(new Intent(this,MainActivity.class));
        }

        if (view == buttonSaveInformation){
            saveUserInformation();
        }

    }
}
