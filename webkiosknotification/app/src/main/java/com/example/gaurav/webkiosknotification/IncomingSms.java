package com.example.gaurav.webkiosknotification;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.TaskStackBuilder;
import android.telephony.SmsManager;
import android.telephony.SmsMessage;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by gaurav on 20/11/15.
 */
public class IncomingSms extends BroadcastReceiver {

    // Get the object of SmsManager
    final SmsManager sms = SmsManager.getDefault();
    public void putNotification(Context context){
        NotificationCompat.Builder mBuilder =
                new NotificationCompat.Builder(context)
                        .setSmallIcon(R.drawable.reload)
                        .setContentTitle("Webkiosk SMS notification")
                        .setContentText("New News available Please connect to Internet ");
// Creates an explicit intent for an Activity in your app
        Intent resultIntent = new Intent(context, MainActivity.class);

// The stack builder object will contain an artificial back stack for the
// started Activity.
        NotificationManager mNotificationManager =
                (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
// mId allows you to update the notification later on.
        int mId=20;
        mNotificationManager.notify(mId, mBuilder.build());
    }

    public void onReceive(Context context, Intent intent) {

        // Retrieves a map of extended data from the intent.
        final Bundle bundle = intent.getExtras();

        try {

            if (bundle != null) {

                final Object[] pdusObj = (Object[]) bundle.get("pdus");

                for (int i = 0; i < pdusObj.length; i++) {

                    SmsMessage currentMessage = SmsMessage.createFromPdu((byte[]) pdusObj[i]);
                    String phoneNumber = currentMessage.getDisplayOriginatingAddress();
                    String message = currentMessage.getDisplayMessageBody();
                    if (phoneNumber.contains("-RING") && message.contains("Teacher Kiosk New Notification PIN:")) {
                        abortBroadcast();
                        Log.i("SmsReceiver", "senderNum: " + phoneNumber + "; message: " + message);
                        putNotification(context);
                    }
                } // end for loop
            } // bundle is null

        } catch (Exception e) {
            Log.e("SmsReceiver", "Exception smsReceiver" +e);

        }
    }
}
