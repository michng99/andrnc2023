package com.example.lab2_androidnangcao;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    MyBroadcast myBroadcast = new MyBroadcast();
    IntentFilter intentFilter = new IntentFilter("ThitCho");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText edtInput = findViewById(R.id.edtInput);
        Button btnSend = findViewById(R.id.btnSend);

        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String data = edtInput.getText().toString();
                Intent intent = new Intent();
                Bundle b = new Bundle();
                b.putString("data", data);
                intent.putExtras(b);
                intent.setAction("ThitCho");
                sendBroadcast(intent);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        registerReceiver(myBroadcast,intentFilter);
    }

    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(myBroadcast);
    }
}