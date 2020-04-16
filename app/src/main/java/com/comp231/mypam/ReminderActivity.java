package com.comp231.mypam;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TimePicker;

public class ReminderActivity extends AppCompatActivity {
    Button btReminder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reminder);

        final TimePicker picker = (TimePicker)findViewById(R.id.timePicker);
        picker.setIs24HourView(true);

        btReminder = findViewById(R.id.bt_reminder);

        btReminder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String message = "Don't forget to pay bills!";
                NotificationCompat.Builder builder = new NotificationCompat.Builder(ReminderActivity.this, "alarm_channel_id")
                        .setSmallIcon(R.drawable.ic_message)
                        .setContentTitle("New Reminder")
                        .setContentText(message)
                        .setAutoCancel(true);

                Intent intent = new Intent(ReminderActivity.this,
                        ReminderActivityAdd.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.putExtra("message",message);

                PendingIntent pendingIntent = PendingIntent.getActivity(ReminderActivity.this,0,intent,PendingIntent.FLAG_UPDATE_CURRENT);
                builder.setContentIntent(pendingIntent);

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
                    NotificationChannel notificationChannel =
                            new NotificationChannel(
                                    "alarm_channel_id",
                                    "MyPam reminder",
                                    NotificationManager.IMPORTANCE_DEFAULT
                            );
                    notificationChannel.setDescription("reminder test");
                    notificationManager.createNotificationChannel(notificationChannel);
                    notificationManager.notify(0, builder.build());
                }
                //NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
                //                    notificationManager.notify(0, builder.build());
            }

        });
    }
}
