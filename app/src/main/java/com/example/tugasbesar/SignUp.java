package com.example.tugasbesar;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignUp extends AppCompatActivity {

    TextInputLayout regName, regUser, regEmail, regPhone, regPasswd;
    Button regBtn;
    TextView ToLogin;

    FirebaseDatabase rootNode;
    DatabaseReference reference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_sign_up);

        regName = findViewById(R.id.nama);
        regUser = findViewById(R.id.username);
        regEmail = findViewById(R.id.email);
        regPhone= findViewById(R.id.no);
        regPasswd = findViewById(R.id.password);
        regBtn = findViewById(R.id.tombol_daftar);
        ToLogin = findViewById(R.id.daftar);

        regBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rootNode = FirebaseDatabase.getInstance();
                reference = rootNode.getReference("user");
                String nama = regName.getEditText().getText().toString();
                String usernama = regUser.getEditText().getText().toString();
                String email = regEmail.getEditText().getText().toString();
                String phone = regPhone.getEditText().getText().toString();
                String passwd = regPasswd.getEditText().getText().toString();

                UserHelper helper = new UserHelper(nama,usernama,email,phone,passwd);

                reference.child(usernama).setValue(helper);
            }
        });
        ToLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(SignUp.this,Login.class);
                startActivity(i);
            }
        });

    }
}