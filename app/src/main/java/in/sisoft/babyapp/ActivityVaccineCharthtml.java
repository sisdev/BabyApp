package in.sisoft.babyapp;

import androidx.appcompat.app.AppCompatActivity;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;

import android.os.Bundle;

public class ActivityVaccineCharthtml extends AppCompatActivity {
    TextView Text;
    Button add;
    Button btn_modify;
    DatabaseHelper dbhelper;
    GridView grid;
    String name;
    WebView wv;

    public ActivityVaccineCharthtml() {
    }

    @SuppressLint({"SetJavaScriptEnabled"})

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vaccine_charthtml);
        String var2 = this.getIntent().getExtras().getString("babyname");
        this.wv = (WebView)this.findViewById(R.id.web_vaccine);
        this.wv.getSettings().setJavaScriptEnabled(true);
        this.wv.loadUrl("file:///android_asset/" + var2 + "_Vaccinechart.html");
    }
}
