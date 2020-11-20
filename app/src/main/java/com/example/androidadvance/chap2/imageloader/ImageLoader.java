package com.example.androidadvance.chap2.imageloader;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Build;
import android.util.DisplayMetrics;

import com.example.androidadvance.MyApp;

import java.io.File;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * @author caoshen
 * @date 2020/9/3
 */
public class ImageLoader {

    public static final String PACKAGE_PARSER = "android.content.pm.PackageParser";
    public static final String ASSET_MANAGER = "android.content.res.AssetManager";

    /**
     * 只有 Android 4.4 API 19 以下版本可以使用。
     *
     * @param context context
     * @param apkPath apk path
     * @return bitmap
     */
    @TargetApi(Build.VERSION_CODES.KITKAT)
    public static Bitmap getApkIcon(Context context, String apkPath) {
        if (!new File(apkPath).exists()) {
            return null;
        }
        try {
            // Package Parser
            Class<?> pkgParserCls = Class.forName(PACKAGE_PARSER);

            // Package Parser Constructor
            Class<?>[] typeArgs = new Class[1];
            typeArgs[0] = String.class;
            Object[] valueArgs = new Object[1];
            valueArgs[0] = apkPath;
            Constructor<?> pkgParserCt = pkgParserCls.getConstructor(typeArgs);
            Object pkgParser = pkgParserCt.newInstance(valueArgs);

            // parsePackage method
            // public Package parsePackage(File sourceFile, String destCodePath,
            // DisplayMetrics metrics, int flags) {
            typeArgs = new Class[4];
            typeArgs[0] = File.class;
            typeArgs[1] = String.class;
            typeArgs[2] = DisplayMetrics.class;
            typeArgs[3] = Integer.TYPE;
            valueArgs = new Object[4];
            DisplayMetrics displayMetrics = new DisplayMetrics();
            displayMetrics.setToDefaults();
            valueArgs[0] = new File(apkPath);
            valueArgs[1] = apkPath;
            valueArgs[2] = displayMetrics;
            valueArgs[3] = 0;
            Method parsePackage = pkgParserCls.getDeclaredMethod("parsePackage", typeArgs);
            Object pkgParserPkg = parsePackage.invoke(pkgParser, valueArgs);

            // application info in Package
            Field appInfoFld = pkgParserPkg.getClass().getDeclaredField("applicationInfo");
            ApplicationInfo info = (ApplicationInfo) appInfoFld.get(pkgParserPkg);

            // AssetManger
            Class<?> assetMagCls = Class.forName(ASSET_MANAGER);
            // AssetManager constructor
            Constructor<?> asstMagCt = assetMagCls.getConstructor((Class[]) null);
            Object assetMag = asstMagCt.newInstance((Object[]) null);

            // addAssetPath
            typeArgs = new Class[1];
            typeArgs[0] = String.class;
            valueArgs = new Object[1];
            valueArgs[0] = apkPath;
            Method assetMagAddAssetPathMtd = assetMagCls.getDeclaredMethod("addAssetPath", typeArgs);
            // add apk path to asset manager
            assetMagAddAssetPathMtd.invoke(assetMag, valueArgs);

            // resources in this package, need construct a new resources
            // public Resources(AssetManager assets, DisplayMetrics metrics, Configuration config)
            Resources res = context.getResources();
            typeArgs = new Class[3];
            typeArgs[0] = assetMag.getClass();
            typeArgs[1] = DisplayMetrics.class;
            typeArgs[2] = res.getConfiguration().getClass();
            valueArgs = new Object[3];
            valueArgs[0] = assetMag;
            valueArgs[1] = displayMetrics;
            valueArgs[2] = res.getConfiguration();
            Constructor<Resources> resCt = Resources.class.getConstructor(typeArgs);
            res = resCt.newInstance(valueArgs);
            if (info.icon != 0) {
                return ((BitmapDrawable) res.getDrawable(info.icon)).getBitmap();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return getApkIconDefault(context, apkPath);
        }
        return null;
    }

    /**
     * 获取 APK 文件的 icon 图片，在上面的方法获取不到 icon 图片时使用
     *
     * @param context context
     * @param apkPath apk path
     * @return bitmap
     */
    private static Bitmap getApkIconDefault(Context context, String apkPath) {
        try {
            PackageInfo pi;
            PackageManager pm = context.getPackageManager();
            pi = pm.getPackageArchiveInfo(apkPath, PackageManager.GET_UNINSTALLED_PACKAGES);
            if (pi == null) {
                return null;
            }
            ApplicationInfo info = pi.applicationInfo;
            info.sourceDir = apkPath;
            info.publicSourceDir = apkPath;
            return ((BitmapDrawable) info.loadIcon(pm)).getBitmap();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 通过应用包名获取 App 的 icon 图片
     *
     * @param context     context
     * @param packageName package name
     * @return bitmap
     */
    public static Bitmap getAppIconByPackageName(Context context, String packageName) {
        try {
            Drawable appInfo = context.getApplicationContext().getPackageManager().getApplicationIcon(packageName);
            if (appInfo instanceof BitmapDrawable) {
                Bitmap bitmap = ((BitmapDrawable) appInfo).getBitmap();
                bitmap.copy(Bitmap.Config.ARGB_8888, false);
                return bitmap;
            }
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}
