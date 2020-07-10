package com.joe.owo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputLayout;
import com.google.android.material.textview.MaterialTextView;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseUser;

import java.util.Objects;

public class Signin extends AppCompatActivity {

    TextInputLayout password_tv, email_tv;
    MaterialTextView signup_tv;
    MaterialButton login_btn;
    private FirebaseAuth mAuth;
    private String TAG = "SigninActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);
        mAuth = FirebaseAuth.getInstance();

        password_tv = findViewById(R.id.siginin_password_tv);
        signup_tv = findViewById(R.id.signin_signup);
        login_btn = findViewById(R.id.login_login_btn);
        email_tv = findViewById(R.id.signin_email_tv);

        signup_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Signin.this, Signup.class);
                startActivity(intent);
            }
        });

        login_btn.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String email = email_tv.getEditText().getText().toString();
                        String password = password_tv.getEditText().getText().toString();
                        if (isverified(email, password)) {
                            mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        FirebaseUser user = mAuth.getCurrentUser();
                                        if (user != null) {
                                            if (mAuth.getCurrentUser().isEmailVerified()) {
                                                Intent result = new Intent(Signin.this, Dashboard.class);
                                                result.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                                                startActivity(result);
                                                finish();
                                            } else {
                                                createToast("Please Verifiy your Mail");
                                            }
                                        }
                                    } else {
                                        try {
                                            throw Objects.requireNonNull(task.getException());
                                        } catch (FirebaseAuthInvalidCredentialsException e) {
                                            createToast("Invalid Username and Password");
                                        } catch (Exception e) {
                                            createToast(e.getMessage());
                                        }
                                    }
                                }
                            });




                        }

                    }
                }
        );

    }
    void createToast(String text) {
        Toast.makeText(Signin.this, text, Toast.LENGTH_LONG).show();
    }

    private boolean isverified(String email, String password) {

        if (email.isEmpty()) {
            email_tv.setError("Email field is empty");
            return false;
        } else {
            email_tv.setErrorEnabled(false);
        }
        if (password.isEmpty()) {
            password_tv.setError("Password field is empty");
            return false;
        } else {
            password_tv.setErrorEnabled(false);
        }
        return true;
    }

}