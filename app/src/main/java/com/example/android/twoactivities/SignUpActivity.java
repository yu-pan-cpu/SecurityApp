package com.example.android.twoactivities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import android.content.Intent;

import android.os.Bundle;

import android.util.Log;

import android.view.View;

import android.widget.Button;

import android.widget.EditText;

import android.widget.ProgressBar;

import android.widget.Toast;



//import com.example.android.twoactivities.model.user;

import com.google.android.gms.tasks.OnCompleteListener;

import com.google.android.gms.tasks.Task;

import com.google.firebase.auth.AuthResult;

import com.google.firebase.auth.FirebaseAuth;

import com.google.firebase.auth.FirebaseUser;

import com.google.firebase.auth.UserProfileChangeRequest;

import com.google.firebase.database.DatabaseReference;

import com.google.firebase.database.FirebaseDatabase;


public class SignUpActivity extends AppCompatActivity {

    FirebaseDatabase database;

    DatabaseReference users;

    private EditText usernames;

    private EditText emails;

    private EditText passwords;

    private EditText names;

    Button btnRegister;

    FirebaseAuth firebaseAuth;

    Button loginBtn;

    private static final String LOG_TAG = SignUpActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        firebaseAuth = FirebaseAuth.getInstance();
        names = (EditText)findViewById(R.id.editText_name);
        usernames = (EditText)findViewById(R.id.editText_username);
        emails = (EditText)findViewById(R.id.editText_email);
        passwords = (EditText)findViewById(R.id.editText_password);

    }


    @Override
    public void onPause(){
        super.onPause();
        Log.d(LOG_TAG, "onPause");
    }

    @Override
    public void onRestart(){
        super.onRestart();
        Log.d(LOG_TAG, "onRestart");
    }

    @Override
    public void onResume(){
        super.onResume();
        Log.d(LOG_TAG, "onResume");
    }

    @Override
    public void onStop(){
        super.onStop();
        Log.d(LOG_TAG, "onStop");
    }

    @Override
    public void onDestroy(){
        super.onDestroy();
        Log.d(LOG_TAG, "onDestroy");
    }

    @Override
    public void onStart(){
        super.onStart();
        Log.d(LOG_TAG, "onStart");
    }

    public void SignUserUp(View view) {
        final String email = emails.getText().toString().trim();
        String password = passwords.getText().toString().trim();
        final String name = names.getText().toString().trim();
        final String username = usernames.getText().toString().trim();

        if (email.isEmpty() || password.isEmpty() || name.isEmpty() || username.isEmpty()){
            Toast.makeText(SignUpActivity.this, "Fields are empty! Please enter all your information", Toast.LENGTH_SHORT).show();
            if (email.isEmpty()){
                emails.requestFocus();
            }
            if(password.isEmpty()){
                passwords.requestFocus();
            }
            if(name.isEmpty()){
                names.requestFocus();
            }
            if(username.isEmpty()){
                usernames.requestFocus();
            }
        } else if (!email.isEmpty()&& !password.isEmpty() && !name.isEmpty() && !username.isEmpty()){
            firebaseAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(SignUpActivity.this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (!task.isSuccessful()){
                        Toast.makeText(SignUpActivity.this,task.getException().getMessage(),Toast.LENGTH_SHORT).show();

                    } else{
                        FirebaseUser user = firebaseAuth.getCurrentUser();
                        UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder().setDisplayName(name).build();
                        user.updateProfile(profileUpdates).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()) {
                                    Toast.makeText(SignUpActivity.this, "Username saved!", Toast.LENGTH_LONG).show();
                                }



                            }
                        });
                        startActivity(new Intent(SignUpActivity.this, MainActivity.class));
                        Toast.makeText(SignUpActivity.this, "Registration is successful", Toast.LENGTH_SHORT).show();
                    }
                }
            });


        }
        else{
            Toast.makeText(SignUpActivity.this, "Error Occured!", Toast.LENGTH_SHORT).show();
        }



    }



}