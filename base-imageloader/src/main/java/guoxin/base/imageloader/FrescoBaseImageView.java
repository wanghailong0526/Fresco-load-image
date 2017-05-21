package guoxin.base.imageloader;


import android.content.Context;
import android.net.Uri;
import android.util.AttributeSet;

import com.facebook.drawee.view.SimpleDraweeView;

class FrescoBaseImageView extends SimpleDraweeView implements IBaseImageView{

    public FrescoBaseImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public void setImageURI(Uri uri) {
        super.setImageURI(uri);

    }
}
