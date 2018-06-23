package com.example.milan.codeforfood;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;

public class ProfileView extends AppCompatActivity implements View.OnClickListener{
    private FirebaseAuth firebaseAuth;

    private Button buttonLogout;
    private Button buttonCompleteProfile;
    private  Button buttonUpdateProfile;
    private Button buttonUserInfo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_view);

        firebaseAuth = FirebaseAuth.getInstance();
        buttonLogout = (Button)findViewById(R.id.buttonLogout);
        buttonCompleteProfile = (Button)findViewById(R.id.buttonCompleteProfile);
        buttonUpdateProfile = (Button)findViewById(R.id.buttonUpdateProfile);
        buttonUserInfo = (Button)findViewById(R.id.buttonUserInfo);
        if (firebaseAuth.getCurrentUser()==null){
            finish();
            startActivity(new Intent(this,MainActivity.class));
        }

        buttonLogout.setOnClickListener(this);
        buttonCompleteProfile.setOnClickListener(this);
        buttonUpdateProfile.setOnClickListener(this);
        buttonUserInfo.setOnClickListener(this);



    }


    @Override
    public void onClick(View view) {

        if (view==buttonLogout){
            firebaseAuth.signOut();
            finish();
            startActivity(new Intent(this,MainActivity.class));
        }

        if (view==buttonCompleteProfile){
            finish();
//            extractData();
            startActivity(new Intent(this,ProfileActivity.class));
        }

        if (view==buttonUpdateProfile){
            //Update Profile
            finish();
            startActivity(new Intent(this,UpdateProfile.class));
        }

        if(view==buttonUserInfo){
            finish();
            startActivity(new Intent(this,UserInfo.class));
        }


    }
}
