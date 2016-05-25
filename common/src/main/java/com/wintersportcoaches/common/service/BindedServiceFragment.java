package com.wintersportcoaches.common.service;

import android.app.Service;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wintersportcoaches.common.base.BaseFragment;
import com.wintersportcoaches.common.base.UserActivity;
import com.wintersportcoaches.common.model.Message;
import com.wintersportcoaches.common.user.BaseUser;

import java.lang.ref.WeakReference;

/**
 * Created by artem on 25.05.16.
 */
public abstract class BindedServiceFragment extends BaseFragment  {
    private LocalServiceConnection mLocalServiceConnection;

    @Override
    public void onCreate(Bundle savedInstance) {
        super.onCreate(savedInstance);
        initLocalServiceConnector();
        mLocalServiceConnection.bindToService();
    }



    @Override
    public void onDestroy() {
        super.onDestroy();
        mLocalServiceConnection.unbindFromService();
    }

    private void initLocalServiceConnector() {
        mLocalServiceConnection = new LocalServiceConnection(getActivity());
    }


    private static class ServiceListener implements MessageListener
            {
        private WeakReference<BindedServiceFragment> mWeakService;
        public ServiceListener(BindedServiceFragment fragment) {
            this.mWeakService =
                    new WeakReference<BindedServiceFragment>(fragment);
        }
        @Override
        public void onMessage(Message msg) {
            BindedServiceFragment localReferenceFragment =
                    mWeakService.get();
            if (localReferenceFragment != null) {
                localReferenceFragment.onMessage(msg);
            }
        }
    }

    protected abstract void onMessage(Message msg);

    private  class LocalServiceConnection implements ServiceConnection {
        private Context context;
        private SocketListenerService mBoundLocalService;
        private boolean mIsBound;

        public LocalServiceConnection(Context context) {
            this.context = context;
        }

        @Override
        public void onServiceConnected(ComponentName componentName,
                                       IBinder iBinder) {
            mBoundLocalService = ((SocketListenerService.ServiceBinder) iBinder)
                    .getService();
            mIsBound = true;
            mBoundLocalService.addListener(new ServiceListener(BindedServiceFragment.this));
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            mBoundLocalService = null;
            mIsBound = false;
        }

        public void bindToService() {
            Intent intent = new Intent(context, SocketListenerService.class);
            BaseUser user = ((UserActivity)getActivity()).getUser();
            intent.putExtra(BaseUser.HASH_ARG, user.getHash());
            context.bindService(intent, this, Context.BIND_AUTO_CREATE);
        }

        public void unbindFromService() {
            if (mIsBound) {
                try {
                    context.unbindService(this);
                    mIsBound = false;
                } catch (IllegalArgumentException e) {
                    // No bound service
                }
            }
        }


    }

}
