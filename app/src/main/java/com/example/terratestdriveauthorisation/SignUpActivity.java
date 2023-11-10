package com.example.terratestdriveauthorisation;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.ktx.Firebase;

public class SignUpActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private DatabaseReference mDatabase;
    public EditText nicknameEditText, emailEditText, passwordEditText;

    public SignUpActivity(){}

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        init();
    }
    public void init(){
        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference();
        nicknameEditText = findViewById(R.id.nickname_edittext);
        emailEditText = findViewById(R.id.emailSignUpEditText);
        passwordEditText = findViewById(R.id.passwordSignUpEditText);
    }

//    public void signUp(View view){
//        String nickname = nicknameEditText.getText().toString();
//        String email = emailEditText.getText().toString();
//        String password = passwordEditText.getText().toString();
//        if(TextUtils.isEmpty(nickname)){
//            Toast.makeText(SignUpActivity.this, "Забыли написать свой никнейм", Toast.LENGTH_SHORT).show();
//        } else if(TextUtils.isEmpty(email)){
//            Toast.makeText(SignUpActivity.this, "Забыли написать свою почту", Toast.LENGTH_SHORT).show();
//        } else if(TextUtils.isEmpty(password)){
//            Toast.makeText(SignUpActivity.this, "Забыли написать свой пароль", Toast.LENGTH_SHORT).show();
//        }
//        else{
//            mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
//                @Override
//                public void onComplete(@NonNull Task<AuthResult> task) {
//                    startActivity(new Intent(SignUpActivity.this, ChooseAvatarActivity.class));
//                }
//            });
//
//            User user = new User(nickname, email, password);
//            String userId = mDatabase.child("Users").push().getKey();
//            mDatabase.child("Users").child(userId).setValue(user);
//        }
//    }

    public void continueBtn(View view){
        startActivity(new Intent(SignUpActivity.this, ChooseAvatarActivity.class));
    }

    public void goBackToGreeting(View view){
        startActivity(new Intent(SignUpActivity.this, GreetingActivity.class));
    }
}