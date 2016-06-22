package com.wintersportcoaches.pupil.coaches;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.wintersportcoaches.common.ActivityHolder;
import com.wintersportcoaches.common.user.BaseUser;
import com.wintersportcoaches.pupil.R;
import com.wintersportcoaches.pupil.chats.dialog.MessagesListFragment;
import com.wintersportcoaches.pupil.lesson.create.CreateLessonActivity;
import com.wintersportcoaches.pupil.profile.ProfileContainerActivity;
import com.wintersportcoaches.pupil.user.PupilUser;

/**
 * Created by artem on 22.05.16.
 */
public class CoachesViewHolder  extends RecyclerView.ViewHolder {
    private final ImageView openChatBtn;
    private TextView mFullName;
    private ImageView addToLessonImageView;
    private CardView rootView;
    private BaseUser item;
    private int position;
    ImageView profileImageView;


    public void cleanup() {
        Picasso.with(profileImageView.getContext())
                .cancelRequest(profileImageView);
        profileImageView.setImageDrawable(null);
    }

    public CoachesViewHolder(CardView itemView, final ActivityHolder activityHolder) {
        super(itemView);
        rootView = itemView;
        mFullName = (TextView)itemView.findViewById(R.id.full_name_tv);
        openChatBtn = (ImageView) itemView.findViewById(R.id.send_message_iv);
        profileImageView = (ImageView) itemView.findViewById(R.id.profile_image);
        profileImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = ProfileContainerActivity.goTo(v.getContext(), item.getUserId());
                v.getContext().startActivity(intent);
            }
        });
        addToLessonImageView = (ImageView)itemView.findViewById(R.id.add_to_lesson_iv);
        addToLessonImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), CreateLessonActivity.class);
                intent.putExtra(BaseUser.EXTRA_USER, item.toBundle());
                v.getContext().startActivity(intent);
            }
        });

        openChatBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActivityHolder.ActivityCaller activityCaller = new ActivityHolder.ActivityCaller() {
                    @Override
                    public void perform(Activity activity) {
                        Fragment fragment = MessagesListFragment.newInstanceWithOpponentId(item.getUserId());
                        FragmentTransaction fragmentTransaction = activity.getFragmentManager().beginTransaction();
                        fragmentTransaction.replace(R.id.container,fragment);
                        fragmentTransaction.addToBackStack(null);
                        fragmentTransaction.commit();
                    }
                };
                activityHolder.performWithActivity(activityCaller);
            }
        });
    }




    public void setItem(BaseUser item) {
        this.item = item;
        mFullName.setText(item.getFullName());
    }

    public void setPosition(int position) {
        this.position = position;
    }

}
