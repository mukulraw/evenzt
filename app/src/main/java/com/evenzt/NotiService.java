package com.evenzt;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.IBinder;

public class NotiService extends Service{

    final static String ACTION = "NotifyServiceAction";
    final static String STOP_SERVICE = "";
    final static int RQS_STOP_SERVICE = 1;

    NotifyServiceReceiver notifyServiceReceiver;

    private static final int MY_NOTIFICATION_ID=1;
    private NotificationManager notificationManager;
    private Notification myNotification;
    private final String myBlog = "http://android-er.blogspot.com/";

    @Override
    public void onCreate() {
// TODO Auto-generated method stub
        notifyServiceReceiver = new NotifyServiceReceiver();
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
// TODO Auto-generated method stub

        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(ACTION);
        registerReceiver(notifyServiceReceiver, intentFilter);

// Send Notification
        notificationManager =
                (NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
        myNotification = new Notification(R.drawable.notification,
                "Notification!",
                System.currentTimeMillis());
        Context context = getApplicationContext();
        String notificationTitle = "Exercise of Notification!";
        String notificationText = "http://android-er.blogspot.com/";

        myNotification.defaults |= Notification.DEFAULT_SOUND;
        myNotification.flags |= Notification.FLAG_AUTO_CANCEL;

        notificationManager.notify(MY_NOTIFICATION_ID, myNotification);

        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
// TODO Auto-generated method stub
        this.unregisterReceiver(notifyServiceReceiver);
        super.onDestroy();
    }

    @Override
    public IBinder onBind(Intent arg0) {
// TODO Auto-generated method stub
        return null;
    }

    public class NotifyServiceReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context arg0, Intent arg1) {
            // TODO Auto-generated method stub
            int rqs = arg1.getIntExtra("RQS", 0);
            if (rqs == RQS_STOP_SERVICE){
                stopSelf();
            }
        }
    }

}
