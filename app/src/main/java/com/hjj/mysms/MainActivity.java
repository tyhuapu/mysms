package com.hjj.mysms;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button bt_send = (Button) findViewById(R.id.bt_send);
        bt_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText et_number = (EditText) findViewById(R.id.et_number);
                EditText et_content = (EditText) findViewById(R.id.et_content);
                String number = et_number.getText().toString().trim();
                String content = et_content.getText().toString().trim();
                if (TextUtils.isEmpty(number) || TextUtils.isEmpty(content)) {
                    Toast.makeText(MainActivity.this, "电话号码和短信内容不能为空！", Toast.LENGTH_SHORT).show();
                    return;
                } else {
                    SmsManager smsmanager = SmsManager.getDefault();
                    ArrayList<String> contents = smsmanager.divideMessage(content);
                    for (String str : contents) {
//                        Toast.makeText(MainActivity.this, str, Toast.LENGTH_SHORT).show();
                        smsmanager.sendTextMessage(number, null, str, null, null);
                    }
                }
            }
        });

        Button bt_call = (Button) findViewById(R.id.bt_call);
        bt_call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText et_number = (EditText) findViewById(R.id.et_number);
                String number = et_number.getText().toString().trim();
                if (TextUtils.isEmpty(number)) {
                    Toast.makeText(MainActivity.this, "电话号码不能为空！", Toast.LENGTH_SHORT).show();
                    return;
                } else {
                    Intent intent = new Intent();
                    intent.setAction(Intent.ACTION_CALL);
                    intent.setData(Uri.parse("tel:" + number));
                    startActivity(intent);
                }
            }
        });

    }
}
