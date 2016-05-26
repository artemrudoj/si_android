package com.wintersportcoaches.common.service;

import android.app.Activity;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Binder;
import android.os.Handler;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.JsonReader;

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

    Socket mSocket;
    Thread socketHandler;
    private static final int PORT = 43455;
    private static final String HOST = "ec2-54-93-219-101.eu-central-1.compute.amazonaws.com";
    private boolean isConnectionEstablished = false;





    @Override
    public void onDestroy() {
        super.onDestroy();
        stopListening();
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
        String hash = ((WinterSportCoachesApplication)getApplication()).getUser().getHash();
        if(isCorrectHash(hash) && !isConnectionEstablished) {
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
                String hash = ((WinterSportCoachesApplication)getApplication()).getUser().getHash();
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
                    e.printStackTrace();
                    Thread.currentThread().interrupt();
                }
            }
        }
    }
}
