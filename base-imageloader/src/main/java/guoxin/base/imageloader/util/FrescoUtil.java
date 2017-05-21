package guoxin.base.imageloader.util;

import android.content.res.Resources;
import android.graphics.PointF;
import android.net.Uri;
import android.text.TextUtils;
import android.util.TypedValue;

import com.facebook.drawee.drawable.ScalingUtils;
import com.facebook.drawee.generic.RoundingParams;
import com.facebook.drawee.view.SimpleDraweeView;

import java.io.File;


public class FrescoUtil {

    private static String baseUrl = "http://image.91test-cloud.bj/";

    public static void setImageBaseUrl(String baseUrl) {
        FrescoUtil.baseUrl = baseUrl;
    }

    public static RoundingParams circleParams = null;

    public static RoundingParams roundingParams = null;

    public static SimpleDraweeView imageView = null;

    public static String getHttpUrl(String imageUrl) {
        if (TextUtils.isEmpty(imageUrl)) {
            return null;
        }
        if (!imageUrl.startsWith("http")) {
            imageUrl = baseUrl + imageUrl;
        }
        return imageUrl;
    }


    /**
     * Fresco 返回圆形图片的配置参数
     * 使用方法 SimpleDraweeView.getHierarchy().setRoundingParams(FrescoUtil.getRoundParams)
     *
     * @return
     */
    public static RoundingParams getCircleParams() {
        if (null == circleParams) {
            synchronized (FrescoUtil.class) {
                if (null == circleParams) {
                    circleParams = new RoundingParams();
                    circleParams.setRoundAsCircle(true);
                }
            }
        }
        return circleParams;
    }

    public static RoundingParams getRoundingParams(int round) {
        roundingParams = new RoundingParams();
        roundingParams.setCornersRadius(round);
        return roundingParams;
    }


    /**
     * Fresco加载圆形图片
     *
     * @param imageView
     * @param imageUrl  //     * @param defaultImageID
     */
    public static void loadCircleImage(SimpleDraweeView imageView, String imageUrl, int defaultDrawableId) {
        imageUrl = getHttpUrl(imageUrl);
        if (!TextUtils.isEmpty(imageUrl)) {
            imageView.getHierarchy().setRoundingParams(getCircleParams());
            imageView.getHierarchy().setActualImageScaleType(ScalingUtils.ScaleType.FOCUS_CROP);
            PointF point = new PointF();
            point.set(0.5f, 0.0f);
            imageView.getHierarchy().setActualImageFocusPoint(point);
            imageView.setImageURI(Uri.parse(imageUrl));
        } else {
            loadResourceCircleImage(imageView, defaultDrawableId, 0);
        }
    }

    /**
     * Fresco加载圆形图片
     *
     * @param imageView
     * @param imageUrl
     * @param defaultImage
     */

//    @BindingAdapter({"circleUrl", "defaultDrawable"})
//    public static void loadCircleImage(SimpleDraweeView imageView, String imageUrl, Drawable defaultImage) {
//        imageUrl = getHttpUrl(imageUrl);
//        if (!TextUtils.isEmpty(imageUrl)) {
//            imageView.getHierarchy().setRoundingParams(getCircleParams());
//            imageView.getHierarchy().setActualImageScaleType(ScalingUtils.ScaleType.FOCUS_CROP);
//            PointF point = new PointF();
//            point.set(0.5f, 0.0f);
//            imageView.getHierarchy().setActualImageFocusPoint(point);
//            imageView.setImageURI(Uri.parse(imageUrl));
//        } else {
//            imageView.setImageDrawable(defaultImage);
//        }
//
//    }

    /**
     * 加载网络图片圆角
     *
     * @param imageView
     * @param imageUrl
     * @param round
     * @param defaultImage
     */
//    @BindingAdapter({"roundUrl", "round", "defaultDrawable"})
//    public static void loadRoundImage(SimpleDraweeView imageView, String imageUrl, int round, Drawable defaultImage) {
//        imageUrl = getHttpUrl(imageUrl);
//        int pix = dip2px(round);
//        if (!TextUtils.isEmpty(imageUrl)) {
//            imageView.getHierarchy().setRoundingParams(getRoundingParams(pix));
//            imageView.setImageURI(Uri.parse(imageUrl));
//        } else {
//            imageView.setImageDrawable(defaultImage);
//        }
//    }

    /**
     * 加载网络图片圆角
     *
     * @param imageView
     * @param imageUrl
     * @param round
     * @param imageId
     */
    public static void loadRoundImage(SimpleDraweeView imageView, String imageUrl, int round, int imageId) {
        imageUrl = getHttpUrl(imageUrl);
        int pix = dip2px(round);
        if (!TextUtils.isEmpty(imageUrl)) {
            imageView.getHierarchy().setRoundingParams(getRoundingParams(pix));
            imageView.setImageURI(Uri.parse(imageUrl));
        } else {
            imageView.setBackgroundResource(imageId);
        }
    }

    /**
     * 加载网络图片，图片的顶部为圆角
     *
     * @param imageView
     * @param imageUrl
     * @param round
     * @param defaultImageID
     */
    public static void loadTopRoundImage(SimpleDraweeView imageView, String imageUrl, int round, int defaultImageID) {
        imageUrl = getHttpUrl(imageUrl);
        int pix = dip2px(round);
        if (!TextUtils.isEmpty(imageUrl)) {
            imageView.getHierarchy().setRoundingParams(getRoundingParams(pix).setCornersRadii(pix, pix, 0, 0));
            imageView.setImageURI(Uri.parse(imageUrl));
        } else {
            imageView.setBackgroundResource(defaultImageID);
        }
    }

    /**
     * 加载网络图片，图片的顶部为圆角
     *
     * @param imageView
     * @param imageUrl
     * @param round
     * @param defaultDrawable
     */
//    @BindingAdapter({"topRoundUrl", "round", "defaultDrawable"})
//    public static void loadTopRoundImage(SimpleDraweeView imageView, String imageUrl, int round, Drawable defaultDrawable) {
//        imageUrl = getHttpUrl(imageUrl);
//        int pix = dip2px(round);
//        if (!TextUtils.isEmpty(imageUrl)) {
//            imageView.getHierarchy().setRoundingParams(getRoundingParams(pix).setCornersRadii(pix, pix, 0, 0));
//            imageView.setImageURI(Uri.parse(imageUrl));
//        } else {
//            imageView.setImageDrawable(defaultDrawable);
//        }
//    }


    /**
     * 加载矩形图片
     *
     * @param imageView
     * @param imageUrl
     * @param defaultImageID
     */
    public static void loadImage(SimpleDraweeView imageView, String imageUrl, int defaultImageID) {

        imageUrl = getHttpUrl(imageUrl);
        if (!TextUtils.isEmpty(imageUrl)) {
            imageView.setImageURI(Uri.parse(imageUrl));
        } else {
            if (0 == defaultImageID) {
            } else {
                imageView.setImageResource(defaultImageID);
            }
        }
    }

//    @BindingAdapter({"url", "defaultDrawable"})
//    public static void loadImage(SimpleDraweeView imageView, String imageUrl, Drawable defaultDrawable) {
//        imageUrl = getHttpUrl(imageUrl);
//        if (!TextUtils.isEmpty(imageUrl)) {
//            imageView.setImageURI(Uri.parse(imageUrl));
//        } else {
//            if (null == defaultDrawable) {
//            } else {
//                imageView.setImageDrawable(defaultDrawable);
//            }
//        }
//    }

    /**
     * fresco 加载本地图片
     * 已测试

     */
    /**
     * 加载资源图片，顶部为圆角
     *
     * @param imageView
     * @param round
     */

//    @BindingAdapter(value = {"topRoundDrawable", "round", "defaultDrawable"}, requireAll = false)
//    public static void loadTopRoundimage(SimpleDraweeView imageView, Drawable topRoundDrawable, int round, Drawable defaultDrawable) {
//        int pix = dip2px(round);
//        if (null != topRoundDrawable) {
//            imageView.getHierarchy().setRoundingParams(getRoundingParams(pix).setCornersRadii(pix, pix, 0, 0));
//            imageView.setImageDrawable(topRoundDrawable);
//        } else if (null != defaultDrawable) {
//            imageView.setImageDrawable(defaultDrawable);
//        }
//    }

    /**
     * 加载资源文件
     *
     * @param imageView
     * @param resourceDrawable
     * @param defaultImageResId
     */
    public static void loadResourceDrawable(SimpleDraweeView imageView, int resourceDrawable, int defaultImageResId) {
        if (0 != resourceDrawable) {
            imageView.setImageURI(new Uri.Builder().scheme("res").path(String.valueOf(resourceDrawable)).build());
        } else if (0 != defaultImageResId) {
            imageView.setBackgroundResource(defaultImageResId);
        }
    }

//    @BindingAdapter({"resourceDrawable", "defaultDrawable"})
//    public static void loadResourceDrawable(SimpleDraweeView imageView, Drawable resourceDrawable, Drawable defaultDrawable) {
//        if (null != resourceDrawable) {
//            imageView.setImageDrawable(resourceDrawable);
//        } else if (null != defaultDrawable) {
//            imageView.setImageDrawable(defaultDrawable);
//        }
//    }

    /**
     * fresco加载本地图片处理为圆形
     *
     * @param imageView
     * @param targetImageResId
     * @param defaultImageResId
     */
    public static void loadResourceCircleImage(SimpleDraweeView imageView, int targetImageResId, int defaultImageResId) {
        if (0 != targetImageResId) {
            imageView.getHierarchy().setRoundingParams(getCircleParams());
            imageView.setImageURI(new Uri.Builder().scheme("res").path(String.valueOf(targetImageResId)).build());
        } else {
            if (defaultImageResId != 0) {
                loadResourceCircleImage(imageView, defaultImageResId, 0);
            }
        }
    }

//    @BindingAdapter({"circlRresourceDrawable", "defaultDrawable"})
//    public static void loadCirclRresourceDrawable(SimpleDraweeView imageView, Drawable circlRresourceDrawable, Drawable defaultDrawable) {
//        if (null != circlRresourceDrawable) {
//            imageView.getHierarchy().setRoundingParams(getCircleParams());
//            imageView.setImageDrawable(circlRresourceDrawable);
//        } else {
//            if (defaultDrawable != null) {
//                imageView.setImageDrawable(defaultDrawable);
//            }
//        }
//    }

    /**
     * fresco加载本地图片需要输入图片的角度 int
     *
     * @param imageView
     * @param targetImageResId
     * @param defaultImageResId
     * @param round
     */

    public static void loadLocalRoundImage(SimpleDraweeView imageView, int targetImageResId, int defaultImageResId, int round) {
        if (0 != targetImageResId) {
            int pix = dip2px(round);
            imageView.getHierarchy().setRoundingParams(getRoundingParams(pix));
            imageView.setImageURI(new Uri.Builder().scheme("res").path(String.valueOf(targetImageResId)).build());
        } else if (0 != defaultImageResId) {
            imageView.setBackgroundResource(defaultImageResId);
        }
    }

//    @BindingAdapter({"roundResourceDrawable", "round", "defaultDrawable"})
//    public static void loadRoundResourceDrawable(SimpleDraweeView imageView, Drawable roundResourceDrawable, int round, Drawable defaultDrawable) {
//        if (null != roundResourceDrawable) {
//            int pix = dip2px(round);
//            imageView.getHierarchy().setRoundingParams(getRoundingParams(pix));
//            imageView.setImageDrawable(roundResourceDrawable);
//        } else if (null != defaultDrawable) {
//            imageView.setImageDrawable(defaultDrawable);
//        }
//    }

    /**
     * fresco加载手机设备上的图片
     *
     * @param imageView
     * @param filePath
     * @param defaultImageResId
     */
    public static void loadFileImage(SimpleDraweeView imageView, String filePath, int defaultImageResId) {
        if (!TextUtils.isEmpty(filePath)) {
            if (new File(filePath).exists()) {
                Uri uri = Uri.parse("file://" + filePath);
                imageView.setImageURI(uri);
            } else {
                imageView.setBackgroundResource(defaultImageResId);
            }
        } else if (0 != defaultImageResId) {
            imageView.setBackgroundResource(defaultImageResId);
        }
    }

    /**
     * fresco加载设备上的文件，显示为圆形
     *
     * @param imageView
     * @param filePath
     * @param defaultImageResId
     */
    public static void LoadFileCircleImage(SimpleDraweeView imageView, String filePath, int defaultImageResId) {
        if (!TextUtils.isEmpty(filePath)) {
            if (new File(filePath).exists()) {
                imageView.getHierarchy().setRoundingParams(getCircleParams());
                Uri uri = Uri.parse("file://" + filePath);
                imageView.setImageURI(uri);
            } else {
                imageView.setBackgroundResource(defaultImageResId);
            }
        } else if (0 != defaultImageResId) {
            imageView.setBackgroundResource(defaultImageResId);
        }

    }

    /**
     * fresco加载设备图片显示为圆形，传入角度
     *
     * @param imageView
     * @param filePath
     * @param defaultImageResId
     * @param round
     */
    public static void LoadFileRoundImage(SimpleDraweeView imageView, String filePath, int defaultImageResId, int round) {
        if (!TextUtils.isEmpty(filePath)) {
            if (new File(filePath).exists()) {
                imageView.getHierarchy().setRoundingParams(getRoundingParams(round));
                Uri uri = Uri.parse("file://" + filePath);
                imageView.setImageURI(uri);
            } else {
                imageView.setBackgroundResource(defaultImageResId);
            }
        } else if (0 != defaultImageResId) {
            imageView.setBackgroundResource(defaultImageResId);
        }
    }

    public static int dip2px(int dip) {

        Resources r = Resources.getSystem();

        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dip, r.getDisplayMetrics());
    }
}
