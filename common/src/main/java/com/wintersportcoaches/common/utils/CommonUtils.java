package com.wintersportcoaches.common.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.Nullable;

import java.io.Closeable;
import java.io.File;
import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

/**
 * Created by artem on 22.06.16.
 */
public class CommonUtils {
    public static void closeSilently(@Nullable Closeable c) {
        if (c == null) return;
        try {
            c.close();
        } catch (Throwable t) {
            // Do nothing
        }
    }


    private static Bitmap rotateBitmap(Bitmap bitmap, int orientation) {
        Matrix matrix = new Matrix();
        switch (orientation) {
            case ExifInterface.ORIENTATION_NORMAL:
                return bitmap;
            case ExifInterface.ORIENTATION_FLIP_HORIZONTAL:
                matrix.setScale(-1, 1);
                break;
            case ExifInterface.ORIENTATION_ROTATE_180:
                matrix.setRotate(180);
                break;
            case ExifInterface.ORIENTATION_FLIP_VERTICAL:
                matrix.setRotate(180);
                matrix.postScale(-1, 1);
                break;
            case ExifInterface.ORIENTATION_TRANSPOSE:
                matrix.setRotate(90);
                matrix.postScale(-1, 1);
                break;
            case ExifInterface.ORIENTATION_ROTATE_90:
                matrix.setRotate(90);
                break;
            case ExifInterface.ORIENTATION_TRANSVERSE:
                matrix.setRotate(-90);
                matrix.postScale(-1, 1);
                break;
            case ExifInterface.ORIENTATION_ROTATE_270:
                matrix.setRotate(-90);
                break;
            default:
                return bitmap;
        }
        try {
            return Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
        } catch (OutOfMemoryError e) {
            e.printStackTrace();
            return null;
        }
    }


    /**
     * Allows to fix issue for some phones when image processed with android-crop
     * is not rotated properly.
     * Saves 2 bitmaps: normal and scaled down
     * Based on https://github.com/jdamcd/android-crop/issues/140#issuecomment-125109892
     * @param context - context to use while saving file
     * @param uri - origin file uri
     *
     * @return scaled down bitmap Uri
     */
    public static Uri normalizeImageForUri(Context context, Uri uri) {
        try {
            ExifInterface exif = new ExifInterface(uri.getPath());
            int orientation = exif.getAttributeInt(ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_UNDEFINED);
            Bitmap bitmap = MediaStore.Images.Media.getBitmap(context.getContentResolver(), uri);
            Bitmap rotatedBitmap = rotateBitmap(bitmap, orientation);
            if (!bitmap.equals(rotatedBitmap)) {
                FileUtils.saveBitmapToFile(context, rotatedBitmap, uri, false);
            }
            Uri scaledDownUri = Uri.parse(uri.toString() + SCALED_DOWN_SUFFIX);
            return FileUtils.saveBitmapToFile(context, rotatedBitmap, scaledDownUri, true);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static RequestBody fromStringToRequestBody(String string) {
        return RequestBody.create(MediaType.parse("multipart/form-data"), string);
    }

    public static MultipartBody.Part createImageJsonObject(String imageUriString) throws IOException {
        if (imageUriString == null || imageUriString.equals("")) {
            return null;
        }
        //   RequestBody requestBody = createImageJsonObject(context, Uri.parse(imageUriString));
        File file = new File(imageUriString);
        RequestBody requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), file);
        return MultipartBody.Part.createFormData(
                "image",
                file.getName(),
                requestFile);
    }

    public static final String SCALED_DOWN_SUFFIX = "_scaled_down";

    public static Bitmap getResizedBitmap(Bitmap bm, int newWidth, int newHeight) {
        int width = bm.getWidth();
        int height = bm.getHeight();
        float scaleWidth = ((float) newWidth) / width;
        float scaleHeight = ((float) newHeight) / height;
        // CREATE A MATRIX FOR THE MANIPULATION
        Matrix matrix = new Matrix();
        // RESIZE THE BIT MAP
        matrix.postScale(scaleWidth, scaleHeight);

        // "RECREATE" THE NEW BITMAP
        Bitmap resizedBitmap = Bitmap.createBitmap(
                bm, 0, 0, width, height, matrix, false);
        bm.recycle();
        return resizedBitmap;
    }
}
