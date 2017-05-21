package guoxin.base.imageloader.view;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.PointF;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.TypedValue;

import com.facebook.drawee.drawable.ScalingUtils;
import com.facebook.drawee.generic.GenericDraweeHierarchy;
import com.facebook.drawee.generic.RoundingParams;
import com.facebook.drawee.view.SimpleDraweeView;

import guoxin.base.imageloader.R;


/**
 * Created by Administrator on 2017/3/23/0023.
 */

public class MView extends SimpleDraweeView {
    private static RoundingParams circleParams = null;

    private static RoundingParams roundingParams = null;
    private String url = "";
    private String topRoundUrl = "";
    private String roundUrl = "";
    private String circleUrl = "";
    private int round = 0;
    private int defaultDrawable = 0;
    private int topRoundDrawable = 0;
    private int resourceDrawable = 0;
    private int circlRresourceDrawable = 0;
    private int roundResourceDrawable = 0;


    public MView(Context context, GenericDraweeHierarchy hierarchy) {
        super(context, hierarchy);
        init(context, null);
    }


    public MView(Context context) {
        super(context);
        init(context, null);
    }

    public MView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);

    }

    public MView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context, attrs);
    }

    public MView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context, attrs);
    }

    /**
     * pvwx
     *
     * @param context
     * @param attrs
     */
    private void init(Context context, AttributeSet attrs) {
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.MView);
        circleUrl = a.getString(R.styleable.MView_circleUrl);
        roundUrl = a.getString(R.styleable.MView_roundUrl);
        url = a.getString(R.styleable.MView_url);
        topRoundUrl = a.getString(R.styleable.MView_topRoundUrl);
        round = (int) a.getInt(R.styleable.MView_round, round);
        defaultDrawable = (int) a.getResourceId(R.styleable.MView_defaultDrawable, defaultDrawable);
        topRoundDrawable = (int) a.getResourceId(R.styleable.MView_topRoundDrawable, topRoundDrawable);
        roundResourceDrawable = (int) a.getResourceId(R.styleable.MView_roundResourceDrawable, roundResourceDrawable);
        circlRresourceDrawable = (int) a.getResourceId(R.styleable.MView_circlRresourceDrawable, circlRresourceDrawable);
        resourceDrawable = (int) a.getResourceId(R.styleable.MView_roundResourceDrawable, resourceDrawable);

        if (!TextUtils.isEmpty(url)) {
            setImage(url, defaultDrawable);
        } else if (!TextUtils.isEmpty(circleUrl)) {
            setCircleImage(circleUrl, defaultDrawable);
        } else if (!TextUtils.isEmpty(roundUrl)) {
            setRoundImage(roundUrl, round, defaultDrawable);
        } else if (!TextUtils.isEmpty(topRoundUrl)) {
            seteTopRoundImage(topRoundUrl, round, defaultDrawable);
        } else if (0 != topRoundDrawable) {
            //TODO 调用上圆角方法
            setTopRoundDrawable(topRoundDrawable, round, defaultDrawable);
        } else if (0 != roundResourceDrawable) {
            //TODO 调用圆角方法
            setRoundResourceDrawable(roundResourceDrawable, defaultDrawable, round);
        } else if (0 != circlRresourceDrawable) {
            setCirclRresourceDrawable(circlRresourceDrawable, defaultDrawable);
            //TODO 调用圆形方法
        } else if (0 != resourceDrawable) {
            //TODO 调用加载本地图片方法
            setResourceDrawable(resourceDrawable, defaultDrawable);
        }


    }

    /**
     * 加载圆形图片对应circleImage
     *
     * @param
     * @param imageUrl
     * @param defaultDrawableId
     */
    public void setCircleImage(String imageUrl, int defaultDrawableId) {
        if (!TextUtils.isEmpty(imageUrl)) {
            getHierarchy().setRoundingParams(getCircleParams());
            getHierarchy().setActualImageScaleType(ScalingUtils.ScaleType.FOCUS_CROP);
            PointF point = new PointF();
            point.set(0.5f, 0.0f);
            getHierarchy().setActualImageFocusPoint(point);
            setImageURI(Uri.parse(imageUrl));
        } else {
            setImageResource(defaultDrawableId);
        }
    }

    public void setRoundImage(String imageUrl, int round, int defaultImage) {
        int pix = dip2px(round);
        if (!TextUtils.isEmpty(imageUrl)) {
            getHierarchy().setRoundingParams(getRoundingParams(pix));
            setImageURI(Uri.parse(imageUrl));
        } else {
            setImageResource(defaultImage);
        }
    }

    public void seteTopRoundImage(String imageUrl, int round, int defaultImageID) {
        int pix = dip2px(round);
        if (!TextUtils.isEmpty(imageUrl)) {
            getHierarchy().setRoundingParams(getRoundingParams(pix).setCornersRadii(pix, pix, 0, 0));
            setImageURI(Uri.parse(imageUrl));
        } else {
            setImageResource(defaultImageID);
        }
    }

    public void setImage(String imageUrl, int defaultImageID) {

        if (!TextUtils.isEmpty(imageUrl)) {
            setImageURI(Uri.parse(imageUrl));
        } else {
            if (0 == defaultImageID) {
            } else {
                setImageResource(defaultImageID);
            }
        }
    }

    public void setRoundResourceDrawable(int roundResourceDrawable, int defaultImageResId, int round) {
        if (0 != roundResourceDrawable) {
            int pix = dip2px(round);
            getHierarchy().setRoundingParams(getRoundingParams(pix));
            setImageURI(new Uri.Builder().scheme("res").path(String.valueOf(roundResourceDrawable)).build());
        } else if (0 != defaultImageResId) {
            setImageResource(defaultImageResId);
        }
    }

    /**
     * 加载资源文件
     *
     * @param targetImageResId
     * @param defaultImageResId
     */
    public void setResourceDrawable(int targetImageResId, int defaultImageResId) {
        if (0 != targetImageResId) {
            setImageURI(new Uri.Builder().scheme("res").path(String.valueOf(targetImageResId)).build());
        } else if (0 != defaultImageResId) {
            setImageResource(defaultImageResId);
        }
    }

    /**
     * fresco加载本地图片处理为圆形
     *
     * @param circlRresourceDrawable
     * @param defaultImageResId
     */
    public void setCirclRresourceDrawable(int circlRresourceDrawable, int defaultImageResId) {
        if (0 != circlRresourceDrawable) {
            getHierarchy().setRoundingParams(getCircleParams());
            setImageURI(new Uri.Builder().scheme("res").path(String.valueOf(circlRresourceDrawable)).build());
        } else {
            if (defaultImageResId != 0) {
                setCirclRresourceDrawable(defaultImageResId, 0);
            }
        }
    }

    public void setTopRoundDrawable(int topRoundDrawable, int round, int defaultImageResId) {
        int pix = dip2px(round);
        if (0 != topRoundDrawable) {
            getHierarchy().setRoundingParams(getRoundingParams(pix).setCornersRadii(pix, pix, 0, 0));
            setImageURI(new Uri.Builder().scheme("res").path(String.valueOf(topRoundDrawable)).build());
        } else if (0 != defaultImageResId) {
            setImageResource(defaultImageResId);
        }
    }


    /**
     * Displays an image given by the uri.
     *
     * @param uri uri of the image
     * @undeprecate
     */
//    @Override
//    public void setImageURI(Uri uri) {
//        setImageURI(uri, null);
//    }


    /**
     * 圆形的配置
     *
     * @return
     */
    public static RoundingParams getCircleParams() {
        if (null == circleParams) {
            synchronized (MView.class) {
                if (null == circleParams) {
                    circleParams = new RoundingParams();
                    circleParams.setRoundAsCircle(true);
                }
            }
        }
        return circleParams;
    }

    /**
     * 圆角的配置
     *
     * @param round
     * @return
     */
    public static RoundingParams getRoundingParams(int round) {
        roundingParams = new RoundingParams();
        roundingParams.setCornersRadius(round);
        return roundingParams;
    }

    public static int dip2px(int dip) {

        Resources r = Resources.getSystem();

        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dip, r.getDisplayMetrics());
    }
}
