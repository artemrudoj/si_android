package com.wintersportcoaches.common.service;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.artem.common.R;
import com.wintersportcoaches.common.base.BaseFragment;
import com.wintersportcoaches.common.base.UserActivity;
import com.wintersportcoaches.common.model.Message;
import com.wintersportcoaches.common.user.BaseUser;
import com.wintersportcoaches.common.utils.Utils;

import java.lang.ref.WeakReference;

/**
 * Created by artem on 25.05.16.
 */
public abstract class BindedServiceFragment extends BaseFragment  {

    private BroadcastReceiver mReceiver;



    void registerBroadcastReceiver() {
        mReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                onMessage(new Message(intent));
            }
        };
        IntentFilter intentFilter = new IntentFilter(
                getString(R.string.message_intent));
        getActivity().registerReceiver(mReceiver, intentFilter);
    }

    void unregisterBrodcastReceiver() {
        getActivity().unregisterReceiver(mReceiver);
    }


    @Override
    public void onResume() {
        super.onResume();
        registerBroadcastReceiver();
    }

    @Override
    public void onPause() {
        super.onPause();
        unregisterBrodcastReceiver();
    }

    @Override
    public void onCreate(Bundle savedInstance) {
        super.onCreate(savedInstance);
        SocketListenerService.start(getActivity());

    }




//
//    private static class ServiceListener implements MessageListener
//            {
//        private WeakReference<BindedServiceFragment> mWeakService;
//        public ServiceListener(BindedServiceFragment fragment) {
//            this.mWeakService =
//                    new WeakReference<BindedServiceFragment>(fragment);
//        }
//        @Override
//        public void onMessage(Message msg) {
//            BindedServiceFragment localReferenceFragment =
//                    mWeakService.get();
//            if (localReferenceFragment != null) {
//                localReferenceFragment.onMessage(msg);
//            }
//        }
//    }

    protected abstract void onMessage(Message msg);




}
