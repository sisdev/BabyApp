package in.sisoft.babyapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ActivityVaccineDetails extends AppCompatActivity {
    Button btn_back;

    public ActivityVaccineDetails() {
    }

    public void onBack() {
        this.setResult(-1, new Intent());
        super.finish();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vaccine_details);
        Typeface var2 = Typeface.createFromAsset(this.getAssets(), "algi.ttf");
        ((TextView)this.findViewById(R.id.txt_vaccine_information)).setTypeface(var2);
        this.btn_back = (Button)this.findViewById(R.id.btn_back_vaccinedetails);
        this.btn_back.setOnClickListener(new View.OnClickListener() {
            public void onClick(View var1) {
                ActivityVaccineDetails.this.onBack();
            }
        });
    }
}

