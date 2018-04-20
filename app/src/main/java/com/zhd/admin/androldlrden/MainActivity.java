package com.zhd.admin.androldlrden;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.zhd.admin.androldlrden.view.WaterRippleView;

public class MainActivity extends AppCompatActivity {

    private WaterRippleView waterRippleView;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        waterRippleView = findViewById(R.id.wrv_water);
        textView = findViewById(R.id.click);
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.click:
                waterRippleView.setStroke(true);
                waterRippleView.start();
                break;
            case R.id.stop:
                waterRippleView.stop();
                break;
        }
    }
}
