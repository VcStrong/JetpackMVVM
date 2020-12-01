package com.vc.wd.common.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.PixelFormat;
import android.graphics.drawable.Drawable;
import android.media.ExifInterface;
import android.media.MediaScannerConnection;
import android.media.ThumbnailUtils;
import android.net.Uri;
import android.util.Log;

import com.vc.wd.common.util.logger.Logger;

import java.io.Closeable;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;

public class IOUtils {
    private static final Logger logger = Logger.createLogger(IOUtils.class);
    private final static int IMAGE_AVATAR_SIZE = 720;

    /**
     * 关闭流
     */
    public static boolean close(Closeable io) {
        if (io != null) {
            try {
                io.close();
            } catch (IOException e) {
                logger.e(Log.getStackTraceString(e));
            }
        }
        return true;
    }

    /**
     * 把图片旋转为正的方向
     *
     * @param fileUrl
     * @param context
     * @return
     */
    public static File rotaingImageView(String fileUrl, Context context) {
        Bitmap bitmap;
        final Options options = new Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(fileUrl, options);
        logger.i("outWidth:" + options.outWidth + "     outHeight:"
                + options.outHeight);
        if (options.outWidth <= IMAGE_AVATAR_SIZE && options.outHeight <= IMAGE_AVATAR_SIZE) {// 如果宽高都小于规定尺寸
            bitmap = BitmapFactory.decodeFile(fileUrl, options);
        } else {
            int imageMaxLength = options.outWidth > options.outHeight ? options.outWidth
                    : options.outHeight;// 得到图片的最大长度
            options.inSampleSize = Integer.parseInt(""
                    + new BigDecimal(((double) imageMaxLength / IMAGE_AVATAR_SIZE) + "")
                    .setScale(0, BigDecimal.ROUND_HALF_UP));// 得到图片缩放比例
            logger.i("options.inSampleSize: " + options.inSampleSize);
        }
        options.inJustDecodeBounds = false;
        /**
         * 获取图片的旋转角度，有些系统把拍照的图片旋转了，有的没有旋转
         */
        bitmap = BitmapFactory.decodeFile(fileUrl, options);// 得到缩小后的图片
        int length = bitmap.getWidth() > bitmap.getHeight() ? bitmap
                .getHeight() : bitmap.getWidth();
        length = length > 720 ? 720 : length;
        bitmap = ThumbnailUtils.extractThumbnail(bitmap, length, length);// 得到裁剪后的图片
        int degree = readPictureDegree(fileUrl);
        logger.i("degree:" + degree);
        // 旋转图片 动作
        Matrix matrix = new Matrix();
        matrix.postRotate(degree);
        // 创建新的图片
        bitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(),
                bitmap.getHeight(), matrix, true);
        File file = FileUtils.getImageFile();
        FileOutputStream out;
        try {
            out = new FileOutputStream(file);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, out);
            out.flush();
            out.close();
            MediaScannerConnection.scanFile(context,
                    new String[]{file.toString()}, null,
                    new MediaScannerConnection.OnScanCompletedListener() {
                        public void onScanCompleted(String path, Uri uri) {
                        }
                    });
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return file;
    }

    /**
     * 读取图片属性：旋转的角度
     *
     * @param path 图片绝对路径
     * @return degree旋转的角度
     */
    public static int readPictureDegree(String path) {
        int degree = 0;
        try {
            ExifInterface exifInterface = new ExifInterface(path);
            int orientation = exifInterface.getAttributeInt(
                    ExifInterface.TAG_ORIENTATION,
                    ExifInterface.ORIENTATION_NORMAL);
            switch (orientation) {
                case ExifInterface.ORIENTATION_ROTATE_90:
                    degree = 90;
                    break;
                case ExifInterface.ORIENTATION_ROTATE_180:
                    degree = 180;
                    break;
                case ExifInterface.ORIENTATION_ROTATE_270:
                    degree = 270;
                    break;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return degree;
    }

    /**
     * Drawable转化为Bitmap
     */
    public static Bitmap drawableToBitmap(Drawable drawable) {
        int width = drawable.getIntrinsicWidth();
        int height = drawable.getIntrinsicHeight();
        Bitmap bitmap = Bitmap.createBitmap(width, height, drawable.getOpacity() != PixelFormat.OPAQUE ? Bitmap.Config.ARGB_8888 : Bitmap.Config.RGB_565);
        Canvas canvas = new Canvas(bitmap);
        drawable.setBounds(0, 0, width, height);
        drawable.draw(canvas);
        return bitmap;

    }
}
