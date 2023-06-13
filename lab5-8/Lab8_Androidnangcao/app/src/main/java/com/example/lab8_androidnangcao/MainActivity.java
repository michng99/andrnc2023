package com.example.lab8_androidnangcao;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.VideoView;

public class MainActivity extends AppCompatActivity {
    Button bt;
    ImageView iv;
    VideoView vv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bt = this.findViewById(R.id.button);
        iv = this.findViewById(R.id.imageView);
        vv=this.findViewById(R.id.videoView);

        vv.setMediaController(new MediaController(MainActivity.this));
        Uri uri = Uri.parse("android.resource://"+getPackageName()+"/"+R.raw.videohoathinh);
        vv.setVideoURI(uri);
        vv.start();

        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent thuvien= new Intent(Intent.ACTION_GET_CONTENT);
                thuvien.setType("image/*");

                Intent camera= new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                Intent tuychon = Intent.createChooser(thuvien, "Chọn 1 Mục !" );
                tuychon.putExtra(Intent.EXTRA_INITIAL_INTENTS, new Intent[]{camera});

                startActivityForResult(tuychon, 123);


            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==123 && resultCode==RESULT_OK){
            if(data.getExtras()!=null){
                Bundle b = data.getExtras();
                Bitmap bm = (Bitmap) b.get("data");
                iv.setImageBitmap(bm);
            }
            else{
                Uri duongdan= data.getData();
                iv.setImageURI(duongdan);
            }
        }
    }
}