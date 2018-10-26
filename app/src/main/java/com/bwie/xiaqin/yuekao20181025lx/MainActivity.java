package com.bwie.xiaqin.yuekao20181025lx;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.RadioGroup;

import com.bwie.xiaqin.yuekao20181025lx.adapter.MyFragmentAdapter;
import com.bwie.xiaqin.yuekao20181025lx.fragment.FenFragment;
import com.bwie.xiaqin.yuekao20181025lx.fragment.GWFragment;
import com.bwie.xiaqin.yuekao20181025lx.fragment.ShowFragment;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends FragmentActivity {
    private RadioGroup radio_group;
    private FragmentManager manager;
    private ShowFragment showFragment;
    private FenFragment fenFragment;
    private GWFragment gwFragment;
    private ViewPager frag_page;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        radio_group = findViewById(R.id.radio_group);
        frag_page = findViewById(R.id.frag_page);
        //创建fragment对象
        showFragment = new ShowFragment();
        fenFragment = new FenFragment();
        gwFragment = new GWFragment();
        //创建ArrayList集合
        List<Fragment> list = new ArrayList<>();
        list.add(new ShowFragment());
        list.add(new FenFragment());
        list.add(new GWFragment());
        MyFragmentAdapter adapter = new MyFragmentAdapter(getSupportFragmentManager(), list);
        frag_page.setAdapter(adapter);

        radio_group.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.rb1:

                        frag_page.setCurrentItem(0);
                        break;
                    case R.id.rb2:
                        frag_page.setCurrentItem(1);
                        break;
                    case R.id.rb3:
                        frag_page.setCurrentItem(2);
                        break;
                }
            }
        });
        frag_page.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                radio_group.check(radio_group.getChildAt(position).getId());
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }
}
