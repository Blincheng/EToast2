package com.mic.etoast2demo;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import com.mic.etoast2.EToastUtils;

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
        //如果要使用EToastUtils，请在Application先注册
        getApplication().registerActivityLifecycleCallbacks(EToastUtils.init());
        findViewById(R.id.textView).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(TwoActivity.this,"这是一个不管怎么样都能显示的Toast TwoActivity"+new Date().getTime(), EToast2.LENGTH_SHORT).show();
                EToastUtils.show("这是一个不管怎么样都能显示的Toast TwoActivity");
            }
        });
    }
}
