package in.sisoft.babyapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class ActivityAboutus extends AppCompatActivity {
    Button btn_aa;
    Button btn_bit;
    Button btn_firstaid;
    Button btn_ha;
    Button btn_sisbutton;
    Button btn_sisimg;
    Button btn_sistext;
    Button btn_ta;
    Button infoback;

    public ActivityAboutus() {
    }

    public void backinfo() {
        this.setResult(-1, new Intent());
        super.finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aboutus);
        this.infoback = (Button)this.findViewById(R.id.but_back_infous);
        this.infoback.setOnClickListener(new OnClickListener() {
            public void onClick(View var1) {
                ActivityAboutus.this.backinfo();
            }
        });
        ((Button)this.findViewById(R.id.btn_bit)).setOnClickListener(new OnClickListener() {
            public void onClick(View var1) {
                Intent var2 = new Intent("android.intent.action.VIEW");
                var2.setData(Uri.parse("https://play.google.com/store/apps/details?id=in.sisoft.babyapp"));
                ActivityAboutus.this.startActivity(var2);
            }
        });
        this.btn_aa = (Button)this.findViewById(R.id.btn_Health_app);
        this.btn_aa.setOnClickListener(new OnClickListener() {
            public void onClick(View var1) {
                Intent var2 = new Intent("android.intent.action.VIEW");
                var2.setData(Uri.parse("https://play.google.com/store/apps/details?id=in.sisoft.assessment.android"));
                ActivityAboutus.this.startActivity(var2);
            }
        });
        this.btn_ha = (Button)this.findViewById(R.id.btn_FirstAid);
        this.btn_ha.setOnClickListener(new OnClickListener() {
            public void onClick(View var1) {
                Intent var2 = new Intent("android.intent.action.VIEW");
                var2.setData(Uri.parse("https://play.google.com/store/apps/details?id=in.sisoft.healthrecorder"));
                ActivityAboutus.this.startActivity(var2);
            }
        });
        this.btn_firstaid = (Button)this.findViewById(R.id.btn_Assesment);
        this.btn_firstaid.setOnClickListener(new OnClickListener() {
            public void onClick(View var1) {
                Intent var2 = new Intent("android.intent.action.VIEW");
                var2.setData(Uri.parse("http://www.amazon.com/sisoft-Technologies-Pvt-Lit-First/dp/B00CM6ZHPI"));
                ActivityAboutus.this.startActivity(var2);
            }
        });
        this.btn_ta = (Button)this.findViewById(R.id.btn_tutorial);
        this.btn_ta.setOnClickListener(new OnClickListener() {
            public void onClick(View var1) {
            }
        });
    }
}

