package com.wintersportcoaches.common.utils;

import android.content.Context;
import android.net.Uri;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.artem.common.R;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

/**
 * Created by artem on 22.06.16.
 */
public class NetworkUtils {

    public static void loadProfileImage(final String photo,
                                        final ImageView profileImage,
                                        ImageView photoIcon,
                                        final ProgressBar photoAnimation,
                                        final Context context) {

        Callback callback = new Callback() {
            @Override
            public void onSuccess() {
                ViewGroup layout = (ViewGroup) profileImage.getParent();
                layout.findViewById(R.id.photo_icon_iv).setVisibility(View.GONE);
                profileImage.setBackground(null);
                profileImage.setVisibility(View.VISIBLE);
                photoAnimation.setVisibility(View.GONE);
            }

            @Override
            public void onError() {
                ViewGroup layout = (ViewGroup) profileImage.getParent();
                layout.findViewById(R.id.photo_icon_iv).setVisibility(View.VISIBLE);
                profileImage.setBackground(context.getResources().getDrawable(R.drawable.circle));
                profileImage.setVisibility(View.VISIBLE);
                photoAnimation.setVisibility(View.GONE);
            }
        };

        loadProfileImage(photo, profileImage, photoIcon, photoAnimation, context, callback);
    }

    public static void loadProfileImage(final String photo,
                                        final ImageView profileImage,
                                        ImageView photoIcon,
                                        final ProgressBar photoAnimation,
                                        final Context context,
                                        Callback picassoCallback) {
        profileImage.setVisibility(View.INVISIBLE);
        photoIcon.setVisibility(View.INVISIBLE);
        photoAnimation.setVisibility(View.VISIBLE);
        Picasso.with(context)
                .load(photo)
                .placeholder(R.drawable.vector_photo_default)
                .into(profileImage, picassoCallback);
    }

    public static void downloadImage(final Context context, Uri photoUri, Target file) {
        Picasso.with(context).load(photoUri).into(file);
    }
}