package com.joe.owo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.DatabaseReference;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputLayout;
import com.google.android.material.textview.MaterialTextView;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Objects;

public class Signup extends AppCompatActivity {

    TextInputLayout username_tv, password_tv, email_tv;
    MaterialTextView login_tv;
    MaterialButton signup_btn;
    private FirebaseAuth mAuth;

    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        mAuth = FirebaseAuth.getInstance();
        username_tv = findViewById(R.id.signupusername_email_tv);
        password_tv = findViewById(R.id.signup_password_tv);
        login_tv = findViewById(R.id.signup_signin);
        signup_btn = findViewById(R.id.signup_signup_btn2);
        email_tv = findViewById(R.id.signup_email_tv);


        signup_btn.setOnClickListener(v -> {
            String email = email_tv.getEditText().getText().toString();
            String password  = password_tv.getEditText().getText().toString();
            String username = username_tv.getEditText().getText().toString();

            if(isverified(email, password, username)){

                mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(
                        new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {

                                if (task.isSuccessful()) {
                                    mAuth.getCurrentUser().sendEmailVerification().addOnCompleteListener(
                                            new OnCompleteListener<Void>() {
                                                @Override
                                                public void onComplete(@NonNull Task<Void> task) {
                                                    if (task.isSuccessful()) {
                                                        FirebaseUser user = mAuth.getCurrentUser();
                                                        String userid = user.getUid();
                                                        reference = FirebaseDatabase.getInstance().getReference("Users").child(userid);

                                                        HashMap<String, String> hashMap = new HashMap<>();

                                                        hashMap.put("id", userid);
                                                        hashMap.put("Fullname", username);
                                                        hashMap.put("ImageURL", "default");

                                                        reference.setValue(hashMap).addOnCompleteListener(
                                                                new OnCompleteListener<Void>() {
                                                                    @Override
                                                                    public void onComplete(@NonNull Task<Void> task) {
                                                                        if(task.isSuccessful()){
                                                                            Toast.makeText(Signup.this, "Sign-Up Successful, A mail has been sent to you", Toast.LENGTH_LONG).show();
                                                                            Intent result = new Intent(Signup.this, Signin.class);
                                                                            result.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                                                                            startActivity(result);
                                                                            finish();
                                                                        }
                                                                    }
                                                                }
                                                        );
                                                    }
                                                }
                                            }
                                    );

                                } else {
                                    try {
                                        throw Objects.requireNonNull(task.getException());
                                    } catch (FirebaseAuthUserCollisionException e) {
                                        createToast("User with this Email already exist");
                                        createToast(e.getMessage());
                                    } catch (Exception e) {
                                        createToast(e.getMessage());
                                    }
                                }
                            }
                        }
                );




            }
        });


        login_tv.setOnClickListener(v -> {
            Intent intent =  new Intent(Signup.this, Signin.class);
            startActivity(intent);
        });






    }
    void createToast(String text) {
        Toast.makeText(Signup.this, text, Toast.LENGTH_LONG).show();
    }



private boolean isverified(String email, String password, String username) {

        if(email.isEmpty()){
        email_tv.setError("Email field is empty");
        return false;
        }
        else{
        email_tv.setErrorEnabled(false);
        }
        if(password.isEmpty()){
        password_tv.setError("Password field is empty");
        return false;
        }
        else{
        password_tv.setErrorEnabled(false);
        }

    if(username.isEmpty()){
        username_tv.setError("Username field is empty");
        return false;
    }
    else{
        username_tv.setErrorEnabled(false);
    }
        return true;
        }
}
