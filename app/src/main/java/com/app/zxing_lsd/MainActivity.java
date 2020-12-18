package com.app.zxing_lsd;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.app.zxinglibslsd.activity.CaptureFragment;
import com.app.zxinglibslsd.activity.CodeUtils;

public class MainActivity extends AppCompatActivity {
    private CaptureFragment captureFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Handler mHandler = new Handler();
        Runnable runnable = new Runnable() {
            @Override
            public void run() {

            }
        };
        captureFragment = new CaptureFragment();
        // 为二维码扫描界面设置定制化界面
        CodeUtils.setFragmentArgs(captureFragment, R.layout.scan);
        captureFragment.setAnalyzeCallback(new CodeUtils.AnalyzeCallback() {
            @Override
            public void onAnalyzeSuccess(Bitmap mBitmap, String result) {
                @SuppressLint("ShowToast") Toast toast = Toast.makeText(getApplicationContext(), result, Toast.LENGTH_LONG);
                toast.show();

                mHandler.postDelayed(runnable, 3000);
                CodeUtils.restartPreviewAndDecode(captureFragment);
            }

            @Override
            public void onAnalyzeFailed() {
                @SuppressLint("ShowToast") Toast toast = Toast.makeText(getApplicationContext(), "失败", Toast.LENGTH_LONG);
                toast.show();
            }
        });
        getSupportFragmentManager().beginTransaction().replace(R.id.fl_my_container, captureFragment).commit();

    }
}