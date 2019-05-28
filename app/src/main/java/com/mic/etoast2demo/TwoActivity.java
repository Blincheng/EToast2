package com.mic.etoast2demo;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import com.mic.etoast2.EToastUtils;

import java.util.Date;

/**
 * Author: Blincheng.
 * Date: 2017/7/28.
 * Description:
 */

public class TwoActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.textView).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EToastUtils.show("这是一个不管怎么样都能显示的Toast TwoActivity"+new Date().getTime());
            }
        });
    }
}
