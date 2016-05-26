package com.wintersportcoaches.pupil.coaches;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.wintersportcoaches.common.ActivityHolder;
import com.wintersportcoaches.common.user.BaseUser;
import com.wintersportcoaches.pupil.R;
import com.wintersportcoaches.pupil.chats.dialog.MessagesListFragment;
import com.wintersportcoaches.pupil.user.PupilUser;

/**
 * Created by artem on 22.05.16.
 */
public class CoachesViewHolder  extends RecyclerView.ViewHolder {
    private final ImageView openChatBtn;
    private TextView mFirstName;
    private TextView mSecondName;
    private CardView rootView;
    private BaseUser item;
    private int position;

    public CoachesViewHolder(final CardView itemView, final ActivityHolder activityHolder) {
        super(itemView);
        rootView = itemView;
        mFirstName = (TextView) itemView.findViewById(R.id.first_name_tv);
        mSecondName = (TextView)itemView.findViewById(R.id.second_name_tv);
        openChatBtn = (ImageView) itemView.findViewById(R.id.send_message_iv);

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
        mFirstName.setText(item.getFirstName());
        mSecondName.setText(item.getLastName());
    }

    public void setPosition(int position) {
        this.position = position;
    }

}
