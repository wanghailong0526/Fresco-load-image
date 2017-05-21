package debug;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import debug.been.ImageBeen;
import guoxin.base.imageloader.R;
import guoxin.base.imageloader.view.MView;


/**
 * Created by Administrator on 2017/3/18/0018.
 */

public class BindSimpleDraweeActivity extends AppCompatActivity {


    private MView image5;

    public static void startActivity(Context c) {
        c.startActivity(new Intent(c, BindSimpleDraweeActivity.class));

    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bindsimpledraweeview);
        ImageBeen image = new ImageBeen("http://noavatar.csdn.net/C/9/2/1_wangbofei.jpg");
//        ImageBeen image2 = new ImageBeen("");
//        List<ImageBeen> imageList = new ArrayList<>();
//        imageList.add(image);
//        imageList.add(image2);
        image5 = (MView) findViewById(R.id.image5);
        image5.setImage(image.getUrl(), R.drawable.open_qilu);

    }
}
