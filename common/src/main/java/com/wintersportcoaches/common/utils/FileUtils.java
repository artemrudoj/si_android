package com.wintersportcoaches.common.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;
import android.widget.ImageView;
import android.widget.Toast;

import com.artem.common.R;
import com.wintersportcoaches.common.user.BaseUser;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;

/**
 * Created by artem on 22.06.16.
 */
public class FileUtils {

    public static void saveFromImageView(ImageView profileImage, BaseUser user,
                                         boolean loadedFlag, Context context) {
        if (loadedFlag) {
            profileImage.buildDrawingCache();
            Bitmap bitmap = profileImage.getDrawingCache();
            Uri uri = saveToFile(context, bitmap, "imageView.jpg");
            if (uri != null) {
                String localPhotoUrl = uri.getPath();
                user.setPhotoLocalUrl(localPhotoUrl);
            }
        }
    }


    public static Uri saveToFile(Context context, Bitmap bitmap, String filename) {
        File root = context.getCacheDir();
        root.mkdirs();
        File path = new File(root, filename);
        Uri uri = Uri.fromFile(path);
        return saveBitmapToFile(context, bitmap, uri, true);
    }

    public static Uri saveBitmapToFile(Context context, Bitmap bitmap, Uri saveUri, boolean scaleDownFlag) {
        if (saveUri != null) {
            OutputStream outputStream = null;
            try {
                outputStream = context.getContentResolver().openOutputStream(saveUri);
                if (outputStream != null) {
                    if (scaleDownFlag) {
                        int newSize = context.getResources()
                                .getDimensionPixelSize(R.dimen.edit_profile_image_view_size);
                        bitmap = CommonUtils.getResizedBitmap(bitmap, newSize, newSize);
                    }
                    bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outputStream);
                    outputStream.flush();
                }
            } catch (IOException e) {
                Toast.makeText(context, "Error occured while saving image to file",
                        Toast.LENGTH_SHORT).show();
                return null;
            } finally {
                CommonUtils.closeSilently(outputStream);
                if (scaleDownFlag) {
                    bitmap.recycle();
                }
            }
        }
        return saveUri;
    }

    public static File createTemporaryFile(String part, String ext) throws Exception {
        File tempDir = Environment.getExternalStorageDirectory();
        tempDir = new File(tempDir.getAbsolutePath() + "/.temp/");
        if(!tempDir.exists())
        {
            tempDir.mkdir();
        }
        return File.createTempFile(part, ext, tempDir);
    }

    public static File createEmptyProfileImageFile(Context context) {
        File photo;
        try {
            photo = createTemporaryFile("new_profile_photo", ".jpg");
            photo.delete();
        } catch (Exception e) {
            Toast.makeText(context, "Please check SD card! Image shot is impossible!", Toast.LENGTH_LONG).show();
            return null;
        }
        return photo;
    }

}
