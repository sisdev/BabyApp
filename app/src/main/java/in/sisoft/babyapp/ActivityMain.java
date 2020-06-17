package in.sisoft.babyapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public class ActivityMain extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        (new Thread() {
            int wait = 0;

            public void run() {
                try {
                    Thread.sleep(3000);
                    Intent intent = new Intent(ActivityMain.this, ActivityAppHome.class);
                    ActivityMain.this.startActivity(intent);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
