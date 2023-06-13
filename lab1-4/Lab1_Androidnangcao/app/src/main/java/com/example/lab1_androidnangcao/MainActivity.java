package com.example.lab1_androidnangcao;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button bt1,bt2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bt1=findViewById(R.id.button);
        bt2=findViewById(R.id.button2);

        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, MyService.class);
                Bundle b = new Bundle();
                b.putString("ten", "Nguyễn Ngọc Hiền");
                b.putString("tuoi", "19");
                i.putExtra("caigio",b);
                startService(i);

                String CHANNEL_ID = "channel_id";
                CharSequence name = "chanel_name";
                int importance = NotificationManager.IMPORTANCE_DEFAULT;

                Notification builder=new NotificationCompat.Builder(MainActivity.this)
                        .setSmallIcon(R.mipmap.ic_launcher)
                        .setContentTitle("title")
                        .setContentText("Chương Trình Service Nghe Nhạc")
                        .setChannelId(CHANNEL_ID)
                        .build();
                NotificationManager manager= (NotificationManager) MainActivity.this.getSystemService(Context.NOTIFICATION_SERVICE);

                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                    NotificationChannel mchanel = new NotificationChannel(CHANNEL_ID,name,importance);
                    manager.createNotificationChannel(mchanel);
                }
                Intent intent = new Intent(MainActivity.this, MainActivity.class);
                PendingIntent p = PendingIntent.getActivity(MainActivity.this,0,intent,PendingIntent.FLAG_IMMUTABLE);
                builder.contentIntent=p;
                manager.notify(0,builder);
            }
        });
        bt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
           Intent i = new Intent(MainActivity.this, MyService.class);
           stopService(i);
            }
        });
    }
}