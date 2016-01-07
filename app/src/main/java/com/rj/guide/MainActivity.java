package com.rj.guide;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.LinearLayout;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private LinearLayout ll;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //ll代表你想把导航自定义控件放到哪里的布局
        ll = (LinearLayout) findViewById(R.id.ll);
        //初始化标题
        final String[] titles = new String[]{"查询系统","档案查询","资料搜索","收藏管理"};
        //0代表想默认选中哪个
        final Guide guide = new Guide(this,titles,0);
        guide.setOnColumnClickListener(new Guide.OnColumnClickListener() {

            @Override
            public void OnColumnClick(int position) {
                Toast.makeText(getBaseContext(), titles[position], Toast.LENGTH_SHORT).show();

            }
        });
        //放进布局
        ll.addView(guide);
    }
}
