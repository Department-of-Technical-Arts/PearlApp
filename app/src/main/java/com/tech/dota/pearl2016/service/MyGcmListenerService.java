package com.tech.dota.pearl2016.service;

import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.gcm.GcmListenerService;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Calendar;

import helper.EventTableManager;
import helper.FeedTableManager;

public class MyGcmListenerService extends GcmListenerService {

    private static final String TAG = "MyGcmListenerService";

    /**
     * Called when message is received.
     *
     * @param from SenderID of the sender.
     * @param data Data bundle containing message data as key/value pairs.
     *             For Set of keys use data.keySet().
     */
    // [START receive_message]
    @Override
    public void onMessageReceived(String from, Bundle data) {
        String message = data.getString("message");
        Log.d(TAG, "From: " + from);
        Log.d(TAG, "Message: " + message);

        if (from.startsWith("/topics/")) {
            // message received from some topic.
        } else {
            // normal downstream message.
        }

        // [START_EXCLUDE]
        /**
         * Production applications would usually process the message here.
         * Eg: - Syncing with server.
         *     - Store message in local database.
         *     - Update UI.
         */

        /**
         * In some cases it may be useful to show a notification indicating to the user
         * that a message was received.
         */


        try {
            JSONObject object = new JSONObject(message);
            int event_id = object.getInt("event_id");
            String messageToDisplay = object.getString("message");
            FeedTableManager feedTableManager = new FeedTableManager(this);
            EventTableManager eventTableManager = new EventTableManager(this);

            feedTableManager.addEntry(event_id, eventTableManager.getEventName(event_id), Calendar.getInstance().getTimeInMillis(), messageToDisplay);


            if (eventTableManager.checkFavourite(event_id))
                sendNotification(message, eventTableManager.getEventName(event_id), event_id);

        } catch (JSONException e) {
            e.printStackTrace();
        }


        // [END_EXCLUDE]
    }
    // [END receive_message]

    /**
     * Create and show a simple notification containing the received GCM message.
     *
     * @param message   GCM message received.
     * @param eventName
     * @param event_id
     */
    private void sendNotification(String message, String eventName, int event_id) {
        //todo add image and stuff to notification
        /*try {
            JSONObject object = new JSONObject(message);
            //todo change MainActivity.class
            Intent intent = new Intent(this, EventDataActivity.class);
            intent.putExtra("Event_id", event_id);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent,
                    PendingIntent.FLAG_ONE_SHOT);

            Uri defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
            NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this)
                    .setSmallIcon(R.mipmap.ic_launcher)
                    .setContentTitle(eventName)
                    .setContentText(object.getString("message"))
                    .setAutoCancel(true)
                    .setSound(defaultSoundUri)
                    .setContentIntent(pendingIntent);

            NotificationManager notificationManager =
                    (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

            notificationManager.notify(message, 0, notificationBuilder.build());
        } catch (JSONException e) {
            e.printStackTrace();
        }*/

    }
}