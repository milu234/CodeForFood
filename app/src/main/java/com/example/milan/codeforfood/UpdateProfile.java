package com.example.milan.codeforfood;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Layout;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserInfo;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class UpdateProfile extends AppCompatActivity implements View.OnClickListener{

    private FirebaseAuth firebaseAuth;
    private FirebaseDatabase firebaseDatabase;

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
        firebaseDatabase = FirebaseDatabase.getInstance();
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

        DatabaseReference databaseReference = firebaseDatabase.getReference(firebaseAuth.getUid());
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                UserInformation userInformation = dataSnapshot.getValue(UserInformation.class);
                editTextFullName.setText(userInformation.getName());
                editTextMobileNumber.setText(userInformation.getMobile());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

//    private void showUpdateDialog(String name,String mob){
//        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
//
//        LayoutInflater inflater = getLayoutInflater();
//
//        final View dialogView =inflater.inflate(R.layout.update_dialog,null);
//
//        dialogBuilder.setView(dialogView);
//
//        final TextView textViewName = (EditText)dialogView.findViewById(R.id.textView2);
//        final EditText editTextName = (EditText)dialogView.findViewById(R.id.editTextName);
//        final  Button buttonUpdate = (Button)dialogView.findViewById(R.id.buttonUpdate);
//
//        dialogBuilder.setTitle("Updating Name"+name);
//
//    }

    private void saveUserInformation(){
        String name = editTextFullName.getText().toString().trim();
        String mob = editTextMobileNumber.getText().toString().trim();

        if (TextUtils.isEmpty(name)){
            //email is Empty
            Toast.makeText(this,"PLease Enter your name",Toast.LENGTH_LONG).show();
            //stopping the function from executing
            return;
        }

        if(TextUtils.isEmpty(mob)){
            //password is Empty
            Toast.makeText(this,"Please Enter the mobile number",Toast.LENGTH_LONG).show();
            return;
        }
//        progressDialog.setMessage("Logged In");
//        progressDialog.show();

        UserInformation userInformation = new UserInformation(name,mob);

        FirebaseUser user = firebaseAuth.getCurrentUser();

        databaseReference.child(user.getUid()).setValue(userInformation);

        Toast.makeText(this,"Information Saved",Toast.LENGTH_LONG).show();
        //startActivity(new Intent(this,SignUp.class));


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
            startActivity(new Intent(this,ProfileView.class));
        }

    }
}
