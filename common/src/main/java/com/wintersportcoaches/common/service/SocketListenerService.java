package com.wintersportcoaches.common.service;

import android.app.Activity;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Binder;
import android.os.Handler;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.JsonReader;
import android.widget.Toast;

import com.artem.common.R;
import com.google.gson.Gson;
import com.google.gson.JsonStreamParser;
import com.wintersportcoaches.common.WinterSportCoachesApplication;
import com.wintersportcoaches.common.model.Message;
import com.wintersportcoaches.common.user.BaseUser;
import com.wintersportcoaches.common.utils.Utils;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by artem on 25.05.16.
 */
public class SocketListenerService extends Service {

    BaseUser mCurrentUser;
    Socket mSocket;
    Thread socketHandler;
    private static final int PORT = 43455;
    private static final String HOST = "ec2-54-93-219-101.eu-central-1.compute.amazonaws.com";
    private boolean isConnectionEstablished = false;

    private BroadcastReceiver mReceiver;



    void registerBroadcastReceiver() {
        mReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                ConnectivityManager connectivityManager = (ConnectivityManager)
                        context.getSystemService(Context.CONNECTIVITY_SERVICE );
                NetworkInfo activeNetInfo = connectivityManager.getActiveNetworkInfo();
                boolean isConnected = activeNetInfo != null && activeNetInfo.isConnectedOrConnecting();
                if (isConnected && !isConnectionEstablished) {
                    establishConnectionWithServer();
                }
            }
        };
        IntentFilter intentFilter = new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE");
        registerReceiver(mReceiver, intentFilter);
    }


    @Override
    public void onCreate() {
        super.onCreate();
        registerBroadcastReceiver();
        mCurrentUser = ((WinterSportCoachesApplication)getApplication()).getUser();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Toast.makeText(this, "service stopped", Toast.LENGTH_SHORT).show();
        stopListening();
        unregisterBrodcastReceiver();
    }

    private void unregisterBrodcastReceiver() {
        unregisterReceiver(mReceiver);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    public static void start(Context context) {
        if (!Utils.isMyServiceRunning(SocketListenerService.class, context)) {
            Intent intent = new Intent(context, SocketListenerService.class);
            context.startService(intent);
        }
    }

    public static void stop(Context context) {
        if (Utils.isMyServiceRunning(SocketListenerService.class, context)) {
            Intent intent = new Intent(context, SocketListenerService.class);
            context.stopService(intent);
        }
    }


    void establishConnectionWithServer() {
        socketHandler = new Thread(new SocketConnectionHandler());
        socketHandler.start();
        isConnectionEstablished = true;
    }

    void stopListening() {
        isConnectionEstablished = false;
        if(socketHandler != null) {
            try {
                if(mSocket != null) mSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    boolean isCorrectHash(String hash) {
        return hash != null && !hash.equals("");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        String hash = mCurrentUser.getHash();
        Toast.makeText(this, "service onStartCommand", Toast.LENGTH_SHORT).show();
        if(isCorrectHash(hash) && !isConnectionEstablished) {
            Toast.makeText(this, "service establishConnectionWithServer", Toast.LENGTH_SHORT).show();
            establishConnectionWithServer();
        }
        return START_STICKY;
    }

    public void notifyListeners(final Message msg) {
        Intent intent = msg.insertToIntent();
        intent.setAction(getString(R.string.message_intent));
        sendBroadcast(intent);
    }


    private class SocketConnectionHandler implements Runnable {

        private InputStream in;
        private OutputStream out;



        public SocketConnectionHandler(){

        }

        @Override
        public void run() {
            try {
                mSocket = new Socket(HOST, PORT);
                in = mSocket.getInputStream();
                out = mSocket.getOutputStream();
                //reg into chat
                String hash = mCurrentUser.getHash();
                if(!isCorrectHash(hash))
                    return;
                String registrationString = Protocol.encodeInitString(hash);
                out.write(registrationString.getBytes(Charset.forName("UTF-8")));
            } catch (IOException e) {
                e.printStackTrace();
            }

            Gson gson = new Gson();
            while (!Thread.currentThread().isInterrupted()) {
                try {
                    JsonStreamParser parser = new JsonStreamParser(new InputStreamReader(in));
                    while(parser.hasNext())
                    {
                        Message message = gson.fromJson(parser.next(), Message.class);
                        notifyListeners(message);
                    }
                } catch (Exception e) {
                    isConnectionEstablished = false;
                    e.printStackTrace();
                    Thread.currentThread().interrupt();
                }
            }
        }
    }
}
