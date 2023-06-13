package com.example.lab3_androidnangcao;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.ContentResolver;
import android.content.Context;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.provider.Settings;
import android.util.Log;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
      TextView tv;
      String dulieu="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv=findViewById(R.id.textView3);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
        { if (ContextCompat.checkSelfPermission(MainActivity.this,Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(MainActivity.this, Manifest.permission.READ_CONTACTS))
            { AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("Contacts access needed");
                builder.setPositiveButton(android.R.string.ok, null);
                builder.setMessage("please x Contacts access");//TODO put real question builder.setOnDismissListener(new DialogInterface.OnDismissListener() { @TargetApi(Build.VERSION_CODES.M) @Override public void onDismiss(DialogInterface dialog) { requestPermissions( new String[] {Manifest.permission.READ_CONTACTS} , 999); } }); builder.show();

} else {  ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.READ_CONTACTS}, 999);  }
        }else{ getcontact(); }
        } else{ getcontact(); }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) { case 999: {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) { getcontact();
            } else { Toast.makeText(this, "khong co permission", Toast.LENGTH_SHORT).show();
            } return; }
        }
    }

    public void getcontact(){
        ContentResolver cr = getContentResolver();
        Cursor c=cr.query(ContactsContract.Contacts.CONTENT_URI,null,null,null,null);
        if(  c.moveToFirst()){
            do{
                int cid=  c.getColumnIndex( ContactsContract.Contacts._ID);
                String id=c.getString(cid);



                int cten =c.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME);
                String ten =c.getString(cten);
                dulieu+=id+ " " +ten;

                int chasphone =c.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER);
                String hasphone =c.getString(chasphone);
                if(Integer.parseInt(hasphone)>=1){
                    Cursor pCur = cr.query(
                            ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                            null,
                            ContactsContract.CommonDataKinds.Phone.CONTACT_ID+"=?",
                            new String []{id}, null);
                    Log.d("dulieu", "soluong : " + pCur.getCount());
                    if(pCur.moveToFirst()){
                        do{

                            int cnumber =pCur.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER);
                            String sodt =pCur.getString(cnumber);
                            dulieu+= " "+sodt;

                        }while(pCur.moveToNext());

                    }
                    tv.setText(dulieu);
         }

                Log.d("dulieu",id +""+ten);
            }while (c.moveToNext());
        }
    }

}