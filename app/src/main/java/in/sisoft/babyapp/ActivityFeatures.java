package in.sisoft.babyapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ActivityFeatures extends AppCompatActivity {
    Button btn_back_features;

    public ActivityFeatures() {
    }

    public void backinfo() {
        this.setResult(-1, new Intent());
        super.finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_features);
        TextView var2 = (TextView)this.findViewById(R.id.txt_features_tital);
        var2 = (TextView)this.findViewById(R.id.txt_features_txt2);
        var2 = (TextView)this.findViewById(R.id.txt_features_txt3);
        this.btn_back_features = (Button)this.findViewById(R.id.btn_back_features);
        this.btn_back_features.setOnClickListener(new View.OnClickListener() {
            public void onClick(View var1) {
                ActivityFeatures.this.backinfo();
            }
        });
    }
}