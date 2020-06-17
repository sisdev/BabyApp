package in.sisoft.babyapp;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ActivityInformation extends Activity {
    private static final int REQUEST_CODE = 22;
    Button back;
    Button btn_aboutus;
    Button btn_features;
    Button btn_sislogo;
    Button btn_vaccine;
    Button feedback;
    Button share;

    public ActivityInformation() {
    }

    protected void onAboutus() {
        this.startActivityForResult(new Intent(this, ActivityAboutus.class), 22);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information);
        TextView var2 = (TextView)this.findViewById(R.id.txt_information);
        this.share = (Button)this.findViewById(R.id.share);
        this.btn_vaccine = (Button)this.findViewById(R.id.btn_vaccineinfo);
        this.feedback = (Button)this.findViewById(R.id.btn_feadback);
        this.btn_aboutus = (Button)this.findViewById(R.id.btn_aboutus);
        this.btn_features = (Button)this.findViewById(R.id.btn_features);
        this.back = (Button)this.findViewById(R.id.back);
        this.back.setOnClickListener(new View.OnClickListener() {
            public void onClick(View var1) {
                ActivityInformation.this.onbackClick();
            }
        });
        this.btn_vaccine.setOnClickListener(new View.OnClickListener() {
            public void onClick(View var1) {
                ActivityInformation.this.onVaccineinfo();
            }
        });
        this.btn_features.setOnClickListener(new View.OnClickListener() {
            public void onClick(View var1) {
                ActivityInformation.this.onFeatures();
            }
        });
        this.feedback.setOnClickListener(new View.OnClickListener() {
            public void onClick(View var1) {
                ActivityInformation.this.onfeedbck();
            }
        });
        this.btn_aboutus.setOnClickListener(new View.OnClickListener() {
            public void onClick(View var1) {
                ActivityInformation.this.onAboutus();
            }
        });
        this.share.setOnClickListener(new View.OnClickListener() {
            public void onClick(View var1) {
                ActivityInformation.this.onshare();
            }
        });
    }

    protected void onFeatures() {
        this.startActivityForResult(new Intent(this, ActivityFeatures.class), 111);
    }

    protected void onVaccineinfo() {
        this.startActivityForResult(new Intent(this, ActivityVaccineDetails.class), 222);
    }

    protected void onbackClick() {
        this.setResult(-1, new Intent());
        super.finish();
    }

    protected void onfeedbck() {
        Intent var1 = new Intent("android.intent.action.SENDTO", Uri.parse("mailto:info@sisoft.in"));
        var1.putExtra("android.intent.extra.SUBJECT", "Baby Immunization Tracker(Android App)");
        var1.putExtra("android.intent.extra.TEXT", "  ");
        this.startActivity(var1);
    }

    protected void onshare() {
        Intent var1 = new Intent("android.intent.action.SEND");
        var1.setType("text/plain");
        var1.putExtra("android.intent.extra.SUBJECT", "Baby immunisation tracker India");
        var1.putExtra("android.intent.extra.TEXT", "http://play.google.com/store/apps/details?id=in.sisoft.babyapp");
        this.startActivity(Intent.createChooser(var1, "share via"));
    }
}
