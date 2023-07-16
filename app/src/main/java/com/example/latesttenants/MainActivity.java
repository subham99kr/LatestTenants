package com.example.latesttenants;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {


    EditText editTextEmail , editTextPassword ;
    Button btnLogin, btnSignUp;
    ProgressBar prgLogin;
    private FirebaseAuth mAuth=FirebaseAuth.getInstance();

    @Override
    public void onBackPressed() {
        // Finish the current activity and all activities below it in the stack
        finishAffinity();
    }
    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null){
            Intent intent = new Intent(MainActivity.this, ParentActivity.class);
            startActivity(intent);
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnLogin = findViewById(R.id.btnLogin);
        btnSignUp = findViewById(R.id.btnSignUp);
        editTextEmail = findViewById(R.id.userId);
        editTextPassword = findViewById(R.id.userPass);
        prgLogin = findViewById(R.id.prgLogin);


        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, signUpActivity.class);
                startActivity(intent);
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email,password;
                email=String.valueOf(editTextEmail.getText());
                password=String.valueOf(editTextPassword.getText());
                loginFunction (email,password);

            }
        });



    }

    private void loginFunction (String email , String password){


        if(TextUtils.isEmpty(email))
        {
            Toast.makeText(MainActivity.this,"Enter Email", Toast.LENGTH_SHORT).show();
            return;
        }
        else if(TextUtils.isEmpty(password))
        {
            Toast.makeText(MainActivity.this,"Enter password", Toast.LENGTH_SHORT).show();
            return;
        }
         mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener( new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        prgLogin.setVisibility(View.GONE);
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            //FirebaseUser user = mAuth.getCurrentUser();
                            Intent intent = new Intent(MainActivity.this, ParentActivity.class);
                            startActivity(intent);
                            // updateUI(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            Toast.makeText(MainActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                            // updateUI(null);
                        }
                    }
                });
    }

}