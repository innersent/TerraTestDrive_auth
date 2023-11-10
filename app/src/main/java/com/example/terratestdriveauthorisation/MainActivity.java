package com.example.terratestdriveauthorisation;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.ktx.Firebase;

public class MainActivity extends AppCompatActivity {
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        startActivity(new Intent(MainActivity.this, ChooseAvatarActivity.class));
    }
    public void init(){
        mAuth = FirebaseAuth.getInstance();
    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if(user == null){
            startActivity(new Intent(MainActivity.this, GreetingActivity.class));
        }
    }
}