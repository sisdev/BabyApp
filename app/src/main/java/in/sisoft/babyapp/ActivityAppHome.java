package in.sisoft.babyapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.GridLayout;
import android.widget.LinearLayout;

public class ActivityAppHome extends AppCompatActivity implements Animation.AnimationListener {
    Animation a1;
    Animation a2;
    Animation a3;
    Animation a4;
    GridLayout gl;

    public ActivityAppHome() {
    }

    public void onAnimationEnd(Animation var1) {
        if (this.a1 == var1) {
            this.startActivity(new Intent(this.getApplicationContext(), ActivityBabyList.class));
            AlarmManager alarmMgr = (AlarmManager)getApplicationContext().getSystemService(Context.ALARM_SERVICE);
            PendingIntent pendingIntent = PendingIntent.getBroadcast(getApplicationContext(), 0, new Intent(getApplicationContext(), ReceiverReminderAlarm.class), 0);
            alarmMgr.set(AlarmManager.ELAPSED_REALTIME_WAKEUP,
                    SystemClock.elapsedRealtime() +
                            60 * 1000, pendingIntent);

        }

        if (this.a2 == var1) {
            this.startActivity(new Intent(this.getApplicationContext(), ActivityVaccineMainPage.class));
        }

        if (this.a3 == var1) {
            this.startActivity(new Intent(this, ActivityInformation.class));
        }

        if (this.a4 == var1) {
            this.startActivity(new Intent(this, ActivityFeeding.class));
        }

    }

    public void onAnimationRepeat(Animation var1) {
    }

    public void onAnimationStart(Animation var1) {
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app_home);
        this.gl = (GridLayout)this.findViewById(R.id.gl);
        this.gl.getChildCount();
        this.a1 = AnimationUtils.loadAnimation(this.getApplicationContext(), R.anim.rotate);
        this.a1.setAnimationListener(this);
        this.a2 = AnimationUtils.loadAnimation(this.getApplicationContext(), R.anim.rotate);
        this.a2.setAnimationListener(this);
        this.a3 = AnimationUtils.loadAnimation(this.getApplicationContext(), R.anim.rotate);
        this.a3.setAnimationListener(this);
        this.a4 = AnimationUtils.loadAnimation(this.getApplicationContext(), R.anim.rotate);
        this.a4.setAnimationListener(this);
        LinearLayout var2 = (LinearLayout)this.gl.getChildAt(0);
        final LinearLayout finalVar = var2;
        var2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View var1) {
                finalVar.startAnimation(ActivityAppHome.this.a1);
            }
        });
        var2 = (LinearLayout)this.gl.getChildAt(1);
        final LinearLayout finalVar1 = var2;
        var2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View var1) {
                finalVar1.startAnimation(ActivityAppHome.this.a2);
            }
        });
        var2 = (LinearLayout)this.gl.getChildAt(3);
        final LinearLayout finalVar2 = var2;
        var2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View var1) {
                finalVar2.startAnimation(ActivityAppHome.this.a3);
            }
        });
        var2 = (LinearLayout)this.gl.getChildAt(2);
        final LinearLayout finalVar3 = var2;
        var2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View var1) {
                finalVar3.startAnimation(ActivityAppHome.this.a4);
            }
        });
    }
}
