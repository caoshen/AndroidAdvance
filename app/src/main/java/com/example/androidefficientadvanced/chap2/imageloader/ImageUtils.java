package com.example.androidefficientadvanced.chap2.imageloader;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.AbsListView;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.io.ByteArrayOutputStream;

/**
 * @author caoshen
 * @date 2020/9/8
 */
public class ImageUtils {

    /**
     * 获取图片宽和高
     *
     * @param context context
     * @param resId   res id
     */
    public static void decodeBounds(Context context, int resId) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(context.getResources(), resId, options);
    }

    /**
     * 设置缩放比
     *
     * @param context    context
     * @param resId      res id
     * @param sampleSize sample size
     */
    public static void decodeSampleSize(Context context, int resId, int sampleSize) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inSampleSize = sampleSize;
        BitmapFactory.decodeResource(context.getResources(), resId, options);
    }

    /**
     * 计算缩放比
     *
     * @param options   bitmap factory options
     * @param reqWidth  required image width
     * @param reqHeight required image height
     * @return in sample size
     */
    public static int calculateInSampleSize(BitmapFactory.Options options, int reqWidth, int reqHeight) {
        int originalWidth = options.outWidth;
        int originalHeight = options.outHeight;
        int inSampleSize = 1;
        if (originalWidth > reqWidth || originalHeight > reqHeight) {
            int halfWidth = originalWidth / 2;
            int halfHeight = originalHeight / 2;
            while ((halfWidth / inSampleSize > reqWidth) && (halfHeight / inSampleSize > reqHeight)) {
                inSampleSize *= 2;
            }
        }
        return inSampleSize;
    }

    /**
     * 根据缩放比将图片加载到内存中
     *
     * @param bitmap bitmap
     * @return byte array
     */
    public static byte[] compress(Bitmap bitmap) {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, bos);
        byte[] bitmapData = bos.toByteArray();
        return bitmapData;
    }

    public static void setImageLoadOnScrollListView(ListView listView) {
        listView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                switch (scrollState) {
                    case SCROLL_STATE_FLING: {
                        // pause image load
                        break;
                    }
                    case SCROLL_STATE_IDLE: {
                        // resume image load
                        break;
                    }
                    default: {
                        break;
                    }
                }
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {

            }
        });
    }

    public static void setImageLoadOnScrollRecyclerView(RecyclerView recyclerView) {
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                switch (newState) {
                    case RecyclerView.SCROLL_STATE_IDLE: {
                        // resume image load
                        break;
                    }
                    case RecyclerView.SCROLL_STATE_SETTLING: {
                        // pause image load
                        break;
                    }
                    default: {
                        break;
                    }
                }
            }
        });
    }
}
