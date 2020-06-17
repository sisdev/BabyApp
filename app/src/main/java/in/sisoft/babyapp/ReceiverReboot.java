package in.sisoft.babyapp;

import android.content.BroadcastReceiver;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import java.util.Calendar;

import in.sisoft.babyapp.model.AppConstant;

public class ReceiverReboot extends BroadcastReceiver {
    public ReceiverReboot() {
    }

    public void onReceive(Context ctx, Intent intent) {
        AlarmManager alarmMgr = (AlarmManager)ctx.getSystemService(Context.ALARM_SERVICE);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(ctx, 0, new Intent(ctx, ReceiverReminderAlarm.class), 0);
        Calendar cal_today = Calendar.getInstance();
        Calendar cal_alarm = Calendar.getInstance();
        cal_alarm.set(Calendar.HOUR_OF_DAY, AppConstant.hourOfDay);
        cal_alarm.set(Calendar.MINUTE, 0);
        cal_alarm.set(Calendar.SECOND, 0);
        if (cal_alarm.before(cal_today)) {
            cal_alarm.add(Calendar.DATE, 1);
        }
        alarmMgr.setInexactRepeating(AlarmManager.RTC_WAKEUP, cal_alarm.getTimeInMillis(), 86400000L, pendingIntent);
        Log.d("Baby App:RebootReceiver", "Phone reboot completed");
    }
}

