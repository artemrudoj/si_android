package com.wintersportcoaches.common.service;

import com.google.gson.Gson;
import com.google.gson.JsonStreamParser;
import com.wintersportcoaches.common.model.Message;

import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by artem on 25.05.16.
 */
public class Protocol {
    public static List<Message> decode(InputStreamReader inputStreamReader) {
        List<Message> messages = new ArrayList<>();
        JsonStreamParser parser = new JsonStreamParser(inputStreamReader);
        Gson gson = new Gson();
        while(parser.hasNext())
        {
            Message message = gson.fromJson(parser.next(), Message.class);
            messages.add(message);
        }
        return messages;
    }
    public static String encodeInitString(String hash) {
        if( hash == null || hash.equals("")) {
            throw new IllegalStateException("hash can not be empty");
        } else {
            return "{\"message_type\":2,\"message_data\":{\"hash\":\"" + hash + "\"}}";
        }
    }
}
