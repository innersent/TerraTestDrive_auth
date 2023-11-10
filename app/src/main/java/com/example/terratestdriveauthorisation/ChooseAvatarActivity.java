package com.example.terratestdriveauthorisation;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.UUID;

public class ChooseAvatarActivity extends AppCompatActivity {
    private static final int REQUEST_CODE_GALLERY = 1;
    private ImageView uplodadedAvatar;
    private SignUpActivity signUpActivity;
    private FirebaseAuth mAuth;
    private DatabaseReference mDatabase;
    private FirebaseStorage storage;
    private Uri selectedImageUri;
    private StorageReference storageRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_avatar);
        init();
    }
    private void init(){
        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference();
        storage = FirebaseStorage.getInstance();
        storageRef = storage.getReference();
        uplodadedAvatar = findViewById(R.id.uploaded_avatar);
        Glide.with(this).load(R.drawable.pixel_man_profile).apply(RequestOptions.circleCropTransform()).into(uplodadedAvatar);
        signUpActivity = new SignUpActivity();
    }

//    public void openGallery(View view){
//        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
//        startActivityForResult(intent, REQUEST_CODE_GALLERY);
//
//        String filename = UUID.randomUUID().toString();
//        StorageReference imageRef = storageRef.child("user_avatars/" + filename);
//
//        imageRef.putFile(selectedImageUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
//            @Override
//            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
//                imageRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
//                    @Override
//                    public void onSuccess(Uri uri) {
//                        String imageUrl = uri.toString();
//                    }
//                });
//            }
//        }).addOnFailureListener(new OnFailureListener() {
//            @Override
//            public void onFailure(@NonNull Exception e) {
//                Toast.makeText(getActivity(), "Ошибка при загрузке изображения", Toast.LENGTH_SHORT).show();
//            }
//        });
//    }

    public void chooseAvatar(View view){
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, REQUEST_CODE_GALLERY);

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_CODE_GALLERY && resultCode == Activity.RESULT_OK && data != null) {
            selectedImageUri = data.getData();

            uplodadedAvatar.setImageURI(selectedImageUri);

            Glide.with(this).load(selectedImageUri).apply(RequestOptions.circleCropTransform()).into(uplodadedAvatar);
        }
    }

    public void signUp(View view){
        String nickname = signUpActivity.nicknameEditText.getText().toString();
        String email = signUpActivity.emailEditText.getText().toString();
        String password = signUpActivity.passwordEditText.getText().toString();
        if(TextUtils.isEmpty(nickname)){
            Toast.makeText(ChooseAvatarActivity.this, "Забыли написать свой никнейм", Toast.LENGTH_SHORT).show();
        } else if(TextUtils.isEmpty(email)){
            Toast.makeText(ChooseAvatarActivity.this, "Забыли написать свою почту", Toast.LENGTH_SHORT).show();
        } else if(TextUtils.isEmpty(password)){
            Toast.makeText(ChooseAvatarActivity.this, "Забыли написать свой пароль", Toast.LENGTH_SHORT).show();
        } else{
            mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    startActivity(new Intent(ChooseAvatarActivity.this, MainActivity.class));
                    Toast.makeText(ChooseAvatarActivity.this, "Авторизвция прошла успешно", Toast.LENGTH_SHORT).show();
                }
            });

            User user = new User(nickname, email, password);
            String userId = mDatabase.child("Users").push().getKey();
            mDatabase.child("Users").child(userId).setValue(user);
        }
    }

    public void goBackToSignUp(View view){
        startActivity(new Intent(ChooseAvatarActivity.this, SignUpActivity.class));
    }
}