package com.example.lab2_androidnangcao;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

public class MyBroadcast extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {

        Bundle bundle = intent.getExtras();
        String data = bundle.getString("data");
        if(data.length()==9){
            if(data.substring(0,3).equals("MEM")|| data.substring(0,3).equals("VIP")){
                switch(data){
                    case"MEM537128":
                        Toast.makeText(context, "Bạn Được Giảm 10%", Toast.LENGTH_SHORT).show();
                        break;

                    case"MEM537129":
                        Toast.makeText(context, "Bạn Được Giảm 20%", Toast.LENGTH_SHORT).show();
                        break;

                    case"VIP537128":
                        Toast.makeText(context, "Bạn Được Giảm 30%", Toast.LENGTH_SHORT).show();
                        break;

                    case"VIP537129":
                        Toast.makeText(context, "Bạn Được Giảm 50%", Toast.LENGTH_SHORT).show();
                        break;

                    default:
                        Toast.makeText(context, "Lỗi", Toast.LENGTH_SHORT).show();
                        break;

                }
            }else{
                Toast.makeText(context, "Lỗi", Toast.LENGTH_SHORT).show();
            }
        }else{
            Toast.makeText(context, "Lỗi", Toast.LENGTH_SHORT).show();
        }
       // Toast.makeText(context, data, Toast.LENGTH_SHORT).show();
    }
}
