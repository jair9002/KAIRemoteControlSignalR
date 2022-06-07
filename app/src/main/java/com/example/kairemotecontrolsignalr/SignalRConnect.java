package com.example.kairemotecontrolsignalr;

import android.os.Handler;
import android.util.Log;
import android.widget.Toast;

import com.microsoft.signalr.HubConnection;
import com.microsoft.signalr.HubConnectionBuilder;

import java.util.HashMap;

public class SignalRConnect {
    HubConnection hubConnection; //Do the signalR definitions

    void connect(String ip_address, String port){
        String url = "http://"+ip_address+":"+port+"/TimLinkHub";
        String serverUrl = "http://172.30.1.63:58486/TimLinkHub";
//        String serverUrl = "https://event-hub.dev-timmanage.com/event-hub";

        HashMap<String, String> headers = new HashMap<>();
        headers.put("type", "Others");
        headers.put("group", "1");
        hubConnection = HubConnectionBuilder.create(serverUrl)
                .withHeaders(headers)
                .build();
        hubConnection.setKeepAliveInterval(12 * 60 * 60 * 1000);
        hubConnection.setServerTimeout(24 * 60 * 60 * 1000);

        Handler handler = new Handler();
        hubConnection.on("ReceiveCommand", (command, data) -> {

            String msg = command + " " + data;
            Log.d("New Message", msg);

            handler.post(new Runnable() {
                @Override
                public void run() {
                    //Toast.makeText(cx, msg, Toast.LENGTH_SHORT).show();
                }
            });

        }, String.class, String.class);

        hubConnection.start().blockingAwait();

        // Host, Unity
        hubConnection.send("SendCommandTo", "1", new String[] {"Host", "Unity"}, "SceneChange", "2");

    }

    void send(String msg){
        hubConnection.send("SendCommandTo", "1", new String[] {"Host", "Unity"}, "SceneChange",msg);
    }

    void disconnect() { //disconnection server
        hubConnection.stop();

    }
}
