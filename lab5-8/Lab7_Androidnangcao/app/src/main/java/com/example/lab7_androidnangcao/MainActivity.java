package com.example.lab7_androidnangcao;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
 Button bt1,bt2,bt3,bt4,bt5,bt6,bt7;
 TextView tv;
 Animation animation;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bt1=findViewById(R.id.button1);
        bt2=findViewById(R.id.button2);
        bt3=findViewById(R.id.button3);
        bt4=findViewById(R.id.button4);
        bt5=findViewById(R.id.button5);
        bt6=findViewById(R.id.button6);
        bt7=findViewById(R.id.button7);
        tv=findViewById(R.id.textview);

        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                animation= AnimationUtils.loadAnimation(MainActivity.this, R.anim.alpha);
                tv.startAnimation(animation);

            }
        });

        bt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlphaAnimation animation=new AlphaAnimation(1.0f, 0.0f);
                animation.setDuration(2000);
                animation.setFillAfter(true);
                tv.startAnimation(animation);
                animation.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {
                        Toast.makeText(MainActivity.this, "Đã Chạy Xong", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        Toast.makeText(MainActivity.this, "HiHi", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {
                        Toast.makeText(MainActivity.this, "^^", Toast.LENGTH_SHORT).show();
                    }
                });

            }
        });
        bt3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                animation= AnimationUtils.loadAnimation(MainActivity.this, R.anim.rotate);
                tv.startAnimation(animation);
            }
        });
        bt4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                animation= AnimationUtils.loadAnimation(MainActivity.this, R.anim.scale);
                tv.startAnimation(animation);
            }
        });
        bt5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                animation= AnimationUtils.loadAnimation(MainActivity.this, R.anim.tran);
                tv.startAnimation(animation);
            }
        });
        bt6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                animation= AnimationUtils.loadAnimation(MainActivity.this, R.anim.set);
                tv.startAnimation(animation);
            }
        });
        bt7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, MainActivity2.class);
                startActivity(i);
                overridePendingTransition(R.anim.pre_in,R.anim.pre_out);

            }
        });

    }
}