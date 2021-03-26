package com.example.tugasbesar;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Pair;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    private static int splash = 3000;
    Animation atas,bawah;
    ImageView gambar1,gambar2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);

        atas = AnimationUtils.loadAnimation(this,R.anim.top_animasi);
        bawah = AnimationUtils.loadAnimation(this,R.anim.buttom_animasi);

        gambar1 = findViewById(R.id.imageView);
        gambar2 = findViewById(R.id.imageView2);


        gambar1.setAnimation(atas);
        gambar2.setAnimation(bawah);


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i = new Intent(MainActivity.this,Login.class);

                Pair[] pairs = new Pair[2];
                pairs[0] = new Pair<View,String>(gambar1,"logo_img");
                pairs[1] = new Pair<View,String>(gambar2,"logo_tex");

                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
                    ActivityOptions opsi = ActivityOptions.makeSceneTransitionAnimation(MainActivity.this,pairs);
                    startActivity(i,opsi.toBundle());

                }
            }
        },splash);
    }
}