package com.whl.prescoloadimage;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.facebook.drawee.view.SimpleDraweeView;

import guoxin.base.imageloader.util.FrescoUtil;

public class MainActivity extends AppCompatActivity {
    private static final String url = "https://vstatic.douyucdn.cn/res/page/play/sign_477dfa9dcd.jpg";
    private SimpleDraweeView img2, img3, img4, img1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        img1 = (SimpleDraweeView) findViewById(R.id.img1);
        img2 = (SimpleDraweeView) findViewById(R.id.img2);
        img3 = (SimpleDraweeView) findViewById(R.id.img3);
        img4 = (SimpleDraweeView) findViewById(R.id.img4);
        FrescoUtil.loadCircleImage(img1, url, R.mipmap.ic_launcher);

    }
}
