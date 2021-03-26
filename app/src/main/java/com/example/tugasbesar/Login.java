package com.example.tugasbesar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class Login extends AppCompatActivity {

    TextView callSignUp;
    Button login_btn;
    ImageView img;
    TextView logoText,masuk;
    TextInputLayout username, password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_login);

        callSignUp = findViewById(R.id.daftar);
        img = findViewById(R.id.logo);
        login_btn = findViewById(R.id.tombol_login);
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        logoText = findViewById(R.id.slogan);
        masuk = findViewById(R.id.masuk);

        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Login.this, SignUp.class);
                startActivity(intent);
            }
        });

        callSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Login.this, SignUp.class);

                Pair[] pairs = new Pair[7];
                pairs[0] = new Pair<View,String>(img,"logo_img");
                pairs[1] = new Pair<View,String>(logoText,"logo_tex");
                pairs[2] = new Pair<View,String>(masuk,"mulai");
                pairs[3] = new Pair<View,String>(username,"username_tran");
                pairs[4] = new Pair<View,String>(password,"password_tran");
                pairs[5] = new Pair<View,String>(login_btn,"btn_tran");
                pairs[6] = new Pair<View,String>(callSignUp,"daftar_tran");

                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
                    ActivityOptions opsi = ActivityOptions.makeSceneTransitionAnimation(Login.this,pairs);
                    startActivity(i,opsi.toBundle());

                }
            }
        });


    }

    private Boolean validasiusername(){
        String val = username.getEditText().getText().toString();

        if (val.isEmpty()){
            username.setError("Isi tidak boleh kosong");
            return false;
        }else  {
            username.setError(null);
            username.setErrorEnabled(false);
            return true;
        }
    }
    private Boolean validasipassword(){
        String val = password.getEditText().getText().toString();

        if (val.isEmpty()){
            password.setError("Isi tidak boleh kosong");
            return false;
        }else {
            password.setError(null);
            password.setErrorEnabled(false);
            return true;
        }
    }
    public void loginUser(View view){
        if (!validasiusername() | validasipassword()){
            return;
        }else {
            isUser();
        }
    }

    private void isUser() {
        final String userEnterUsername = username.getEditText().toString().trim();
        final String userEnterPassword = password.getEditText().toString().trim();

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("user");

        Query checkuser = reference.orderByChild("usernama").equalTo(userEnterUsername);
        checkuser.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()){

                    username.setError(null);
                    username.setErrorEnabled(false);

                    String passwordDB = snapshot.child(userEnterUsername).child("passwd").getValue(String.class);

                    if (passwordDB.equals(userEnterPassword)){

                        username.setError(null);
                        username.setErrorEnabled(false);

                        String nameDB = snapshot.child(userEnterUsername).child("nama").getValue(String.class);
                        String userDB = snapshot.child(userEnterUsername).child("usernama").getValue(String.class);
                        String emailDB = snapshot.child(userEnterUsername).child("email").getValue(String.class);
                        String phoneDB = snapshot.child(userEnterUsername).child("phone").getValue(String.class);

                        Intent i = new Intent(getApplicationContext(),UserProfile.class);
                        i.putExtra("name",nameDB);
                        i.putExtra("username",userDB);
                        i.putExtra("email",emailDB);
                        i.putExtra("phone",phoneDB);
                        i.putExtra("password",passwordDB);

                        startActivity(i);
                    }else {
                        password.setError("Salah Password");
                        username.requestFocus();
                    }
                }else {
                    username.setError("No such User exist");
                    username.requestFocus();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

}