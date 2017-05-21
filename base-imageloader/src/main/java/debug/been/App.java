package debug.been;

import android.app.Application;
import android.graphics.Bitmap;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.imagepipeline.core.ImagePipelineConfig;

/**
 * Created by Administrator on 2017/3/2/0002.
 */

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        initFresco();
    }

    private void initFresco() {
        ImagePipelineConfig config = ImagePipelineConfig.newBuilder(this)
                .setBitmapsConfig(Bitmap.Config.RGB_565)
                .build();
        Fresco.initialize(this, config);
    }

}
