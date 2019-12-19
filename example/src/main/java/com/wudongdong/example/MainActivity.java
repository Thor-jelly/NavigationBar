package com.wudongdong.example;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import androidx.appcompat.app.AppCompatActivity;

import com.wudongdong.navigationbar.NavigationBar;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ViewGroup viewGroup = findViewById(R.id.view_root);

        new NavigationBar.Builder(this, R.layout.title_view, viewGroup)
                .setText(R.id.title_tv, "我是标题")
                .setOnClickListener(R.id.title_tv, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Log.e("123===", "我点击的title view");
                    }
                })
                .build();
    }
}
