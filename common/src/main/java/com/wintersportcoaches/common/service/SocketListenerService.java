package com.wintersportcoaches.common.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.Handler;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.JsonReader;

import com.artem.common.R;
import com.google.gson.Gson;
import com.google.gson.JsonStreamParser;
import com.wintersportcoaches.common.model.Message;
import com.wintersportcoaches.common.user.BaseUser;

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
    private String hash;
    private boolean isConnectionEstablished = false;
    private final ServiceBinder mBinder = new ServiceBinder();
    private List<MessageListener> listeners = new ArrayList<>();





    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        stopListening();
    }




    public class ServiceBinder extends Binder {
        public SocketListenerService getService() {
            return SocketListenerService.this;
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

    boolean isCorrectHash() {
        return hash != null && !hash.equals("");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if(intent != null) {
            hash = intent.getExtras().getString(BaseUser.HASH_ARG);
            if(isCorrectHash() && !isConnectionEstablished) {
                establishConnectionWithServer();
            }
        }
        return START_STICKY;
    }

    public void notifyListeners(final Message msg) {
        Intent intent = msg.insertToIntent();
        intent.setAction(getString(R.string.message_intent));
        sendBroadcast(intent);
    }


    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        if(intent != null) {
            hash = intent.getExtras().getString(BaseUser.HASH_ARG);
            if(isCorrectHash() && !isConnectionEstablished) {
                establishConnectionWithServer();
            }
        }
        return mBinder;
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
