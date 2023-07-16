package com.example.latesttenants;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class signUpActivity extends AppCompatActivity {

    EditText editTextEmail , editTextPassword;
    FirebaseAuth mAuth;
    ProgressBar progressBar;

    Button buttonReg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        editTextEmail = findViewById(R.id.mail);
        editTextPassword = findViewById(R.id.userPassword);
        buttonReg = findViewById(R.id.btnCreate);
        progressBar= findViewById(R.id.prgSign);
        mAuth = FirebaseAuth.getInstance();

        buttonReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressBar.setVisibility(View.VISIBLE);
                String email,password;
                email=String.valueOf(editTextEmail.getText());
                password=String.valueOf(editTextPassword.getText());

                if(TextUtils.isEmpty(email))
                {
                    Toast.makeText(signUpActivity.this,"Enter Email", Toast.LENGTH_SHORT).show();
                    progressBar.setVisibility(View.GONE);
                    return;
                }
                if(TextUtils.isEmpty(password))
                {
                    Toast.makeText(signUpActivity.this,"Enter password", Toast.LENGTH_SHORT).show();
                    progressBar.setVisibility(View.GONE);
                    return;
                }

                mAuth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener( new OnCompleteListener<AuthResult>() {

                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                progressBar.setVisibility(View.GONE);
                                if (task.isSuccessful()) {
                                    // Sign in success, update UI with the signed-in user's information
                                   // Log.d(TAG, "createUserWithEmail:success");
                                    FirebaseUser user = mAuth.getCurrentUser();
                                    Toast.makeText(signUpActivity.this, "Account successfully created",
                                            Toast.LENGTH_SHORT).show();
                                    startActivity(new Intent(signUpActivity.this, ParentActivity.class));
                                    //updateUI(user);
                                } else {
                                    // If sign in fails, display a message to the user.
                                    //Log.w(TAG, "createUserWithEmail:failure", task.getException());
                                    Toast.makeText(signUpActivity.this, "Authentication failed.",
                                            Toast.LENGTH_LONG).show();
                                    //updateUI(user);
                                }
                            }
                        });


            }
        });
    }
}