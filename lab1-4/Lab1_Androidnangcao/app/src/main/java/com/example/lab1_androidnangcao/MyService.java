package com.example.lab1_androidnangcao;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.IBinder;
import android.provider.MediaStore;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class MyService extends Service {
    MediaPlayer player;
    @Override
    public void onCreate() {
        super.onCreate();
        player= MediaPlayer.create(this,R.raw.mymusic);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
            return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        Toast.makeText(this, "Service Đang Chạy", Toast.LENGTH_SHORT).show();
        player.start();
        Bundle b=intent.getBundleExtra("caigio");
        String ten=b.getString("ten");
        String tuoi=b.getString("tuoi");
        Toast.makeText(this, "Tên : " +ten+ "Tuổi : "+tuoi, Toast.LENGTH_SHORT).show();
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        Toast.makeText(this, "Service Bị Chết", Toast.LENGTH_SHORT).show();
        player.stop();
        super.onDestroy();
    }
}
