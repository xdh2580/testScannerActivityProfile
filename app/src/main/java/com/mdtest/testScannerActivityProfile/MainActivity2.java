package com.mdtest.testScannerActivityProfile;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity2 extends AppCompatActivity {


    public  TextView textView;
    private final  String ACT1="com.example.mybroad";
    //    private final  String ACT2="com.example.mybroad2";
//    private final  String ACT3="com.example.mybroad3";
    private BroadcastReceiver broadcastReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        textView = findViewById(R.id.textView2);
        TextView textView2 = findViewById(R.id.textView4);
        textView2.setText("current activity action filter:"+ACT1);
        EditText editText = findViewById(R.id.editText1);
        Button button = findViewById(R.id.button4);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setAction(editText.getText().toString());
//              intent2.setAction("com.example.mybroad");
                Log.d("xdh","action:"+editText.getText().toString());
                Bundle bundle = new Bundle();
                bundle.putString("data","Received the manually send broadcast data!");
                intent.putExtras(bundle);
                sendBroadcast(intent);
            }
        });

        broadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                if (intent.getAction().equals(ACT1)) {
                    Log.d("xdh", "RECEIVED!");
                    Toast.makeText(MainActivity2.this, intent.toString(), Toast.LENGTH_SHORT).show();
                    textView.setText(intent.getExtras().getString("data","null"));
                }
            }
        };

        IntentFilter filter = new IntentFilter();
        filter.addAction(ACT1);
//        filter.addAction(ACT2);
//        filter.addAction(ACT3);
        registerReceiver(broadcastReceiver,filter);

    }

}