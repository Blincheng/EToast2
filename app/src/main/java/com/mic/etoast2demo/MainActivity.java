package com.mic.etoast2demo;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import com.mic.etoast2.EToast2;
import com.mic.etoast2.Toast;

import java.util.Date;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.textView).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this,"这是一个不管怎么样都能显示的Toast"+new Date().getTime(), EToast2.LENGTH_SHORT).show();
            }
        });
    }
}
