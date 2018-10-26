package com.bwie.xiaqin.yuekao20181025lx;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.bwie.xiaqin.yuekao20181025lx.fragment.ShowFragment;

public class ViewqouActivity extends AppCompatActivity {
    private TextView txtCircle;
    public static final int FLAG = 1;
    private Handler handler = new Handler(Looper.getMainLooper()) {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what ==FLAG) {
                Intent intent=new Intent(ViewqouActivity.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        }
    };



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewqou);
        txtCircle = findViewById(R.id.text_view);
        handler.sendEmptyMessageDelayed(FLAG,6000);
//这个是你的模拟器的分辨率，是多少，大的那个就是长，小的就是宽
        ObjectAnimator tyAnimator = ObjectAnimator.ofFloat(txtCircle, "translationY", 0, 1920);
        ObjectAnimator txAnimator = ObjectAnimator.ofFloat(txtCircle, "translationX", 0, 1080);
        txAnimator.setDuration(5000);
        txAnimator.start();
        tyAnimator.setDuration(5000);
        tyAnimator.start();


    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        handler.removeCallbacksAndMessages(null);
    }

}
