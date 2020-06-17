package in.sisoft.babyapp;

import android.app.NotificationChannel;
import android.content.BroadcastReceiver;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.util.Log;

import androidx.core.app.NotificationCompat;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import in.sisoft.babyapp.model.*;


public class ReceiverReminderAlarm extends BroadcastReceiver {
    private NotificationManager mNotificationManager;
    private Notification notification;
    private int notificationCount = 0;
    String CHANNEL_ID = "10" ;

    public void onReceive(Context ctx, Intent intent) {
        Log.d("Sisoft:ReminderAlarm", "Alarm Received");
        this.mNotificationManager = (NotificationManager)ctx.getSystemService(Context.NOTIFICATION_SERVICE);
        createNotificationChannel(mNotificationManager);
        Calendar cal_today = Calendar.getInstance();
        SQLiteDatabase sqlDB = (new DatabaseHelper(ctx)).getReadableDatabase();
        Cursor cursor_immune_sch = sqlDB.rawQuery("SELECT vaccine_id, baby_name, vaccine_name, due_date FROM vaccine_table where given_date = 'NA'  Order By baby_name, vaccine_id", (String[])null);
        if (cursor_immune_sch.moveToFirst()) {
            do {
                cursor_immune_sch.getString(0);
                String baby_name = cursor_immune_sch.getString(1);
                String vaccine_name = cursor_immune_sch.getString(2);
                String due_date = cursor_immune_sch.getString(3);

                Date dtDueDate;
                try {
                    dtDueDate = (new SimpleDateFormat("dd-MMM-yyyy", Locale.getDefault())).parse(due_date);
                } catch (Exception exp) {
                    Log.d("ReminderAlarm", exp.getMessage());
                    continue;
                }

                Calendar calDueDate = Calendar.getInstance();
                calDueDate.setTime(dtDueDate);
                if (cal_today.after(calDueDate)) {
                    ++this.notificationCount;
                    NotificationCompat.Builder notiBuilder = new NotificationCompat.Builder(ctx,CHANNEL_ID);
                    vaccine_name = baby_name + ":" + vaccine_name + ":" + due_date;
                    Intent intent1 = new Intent(ctx, ActivityBabyImmunization.class);
                    intent1.putExtra(AppConstant.keyBabyName, baby_name);
                    PendingIntent pendingIntent = PendingIntent.getActivity(ctx, this.notificationCount, intent1, 0);
                    notiBuilder.setContentTitle("Immunization India: Vaccine Overdue");
                    notiBuilder.setSmallIcon(R.drawable.app_icon12 );
                    notiBuilder.setContentText(vaccine_name);
                    notiBuilder.setContentIntent(pendingIntent);
                    long[] vibrations = new long[]{0L, 100L, 200L, 300L};
                    notiBuilder.setVibrate(vibrations);
                    this.notification = notiBuilder.build();
                    this.mNotificationManager.notify(this.notificationCount, this.notification);
                }
            } while(cursor_immune_sch.moveToNext());
        }

        sqlDB.close();
    }

    private void createNotificationChannel(NotificationManager notificationManager) {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

            CharSequence name =  "Channel1" ;      //getString(R.string.channel_name);
            String description = "Description" ;   //getString(R.string.channel_description);
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
            channel.setDescription(description);
            // Register the channel with the system; you can't change the importance
            // or other notification behaviors after this
            // NotificationManager notificationManager = ctx.getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }


}
