package com.mdtest.testScannerActivityProfile;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity3 extends AppCompatActivity {

    private final  String ACT2="com.example.mybroad2";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        TextView textView = findViewById(R.id.textView);
        TextView textView2 = findViewById(R.id.textView5);
        textView2.setText("current activity action filter:"+ACT2);
        EditText editText = findViewById(R.id.editText2);
        Button button = findViewById(R.id.button5);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setAction(editText.getText().toString());
                Bundle bundle = new Bundle();
                bundle.putString("data","Received the manually send broadcast data!");
                intent.putExtras(bundle);
                sendBroadcast(intent);
            }
        });

        BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                Toast.makeText(MainActivity3.this, intent.toString(), Toast.LENGTH_SHORT).show();
                textView.setText(intent.getExtras().getString("data","null"));
            }
        };

        IntentFilter filter = new IntentFilter();
        filter.addAction(ACT2);
        registerReceiver(broadcastReceiver,filter);
    }
}