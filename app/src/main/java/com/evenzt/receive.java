package com.evenzt;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;


public class receive extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {

        Log.d("asdReceiver" , "Received");

        Intent pushIntent = new Intent(context, NotifyService.class);
        context.startService(pushIntent);
    }

}
