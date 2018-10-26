package com.bwie.xiaqin.yuekao20181025lx;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;


import com.bwie.xiaqin.yuekao20181025lx.fragment.ShowFragment;
import com.bwie.xiaqin.yuekao20181025lx.lushi.myview.flow;

import java.util.ArrayList;
import java.util.List;

public class LushibujuActivity extends AppCompatActivity{
    private String names[] = {"黄焖鸡", "麻辣烫", "盖饭", "披萨", "鸡丁饭", "米线", "饺子", "披萨", "武汉黄焖鸡", "黄焖鸡", "黄焖鸡"};

    private ImageView back, delete;
    private EditText editText;
    private Button button;
    private flow hot_flow, main_flow;
    private List<String> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lushibuju);
        back = findViewById(R.id.lushi_back);
        button = findViewById(R.id.lushi_butt);
        editText = findViewById(R.id.lushi_edit);
        hot_flow = findViewById(R.id.hot);
        main_flow = findViewById(R.id.lushi_flow);
        delete = findViewById(R.id.delect);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LushibujuActivity.this, ShowFragment.class);
                startActivity(intent);
            }
        });
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ViewGroup.MarginLayoutParams lp = new ViewGroup.MarginLayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                lp.rightMargin = 5;
                lp.topMargin = 5;
                lp.bottomMargin = 5;
                lp.leftMargin = 5;
                String edit_text = editText.getText().toString();
                list.add(edit_text);
                TextView textView = new TextView(LushibujuActivity.this);
                textView.setText(list.get(list.size() - 1));
                textView.setTextColor(Color.WHITE);
                textView.setBackgroundDrawable(getResources().getDrawable(R.drawable.myshape));
                main_flow.addView(textView, lp);
            }
        });
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                list = new ArrayList<>();
                main_flow.removeAllViews();
            }
        });
        main_flow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LushibujuActivity.this, ShowFragment.class);
                startActivity(intent);
            }
        });
        initData();

    }

    private void initData() {
        ViewGroup.MarginLayoutParams lp = new ViewGroup.MarginLayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        lp.rightMargin = 5;
        lp.topMargin = 5;
        lp.bottomMargin = 2;
        lp.leftMargin = 5;
        for (int i = 0; i < names.length; i++){
            TextView textView = new TextView(this);
            textView.setText(names[i]);
            textView.setTextColor(Color.WHITE);
            textView.setBackgroundDrawable(getResources().getDrawable(R.drawable.myshape));
            hot_flow.addView(textView, lp);
        }
    }


}
