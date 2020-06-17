package in.sisoft.babyapp;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Context;
import android.util.Log;
import android.widget.Button;
import android.widget.GridView;
import java.io.IOException;
import java.util.List;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

public class ActivityVaccineMainPage extends AppCompatActivity {
    Button back;
    Context ctx;
    XmlPullParserFactory factory = null;
    GridView grid_vaccinechart;
    XmlPullParser parser = null;
    String xml_file_name;

    public ActivityVaccineMainPage() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vaccine_main_page);
        Log.d("VaccineMainPage", "Vaccine Main Page Called");
        this.grid_vaccinechart = (GridView)this.findViewById(R.id.grid_vaccinechartmain);

        try {
            List var3 = (new VaccineParsingData()).parse(this.getAssets().open("vaccine_chart.xml"));
            Log.d("VaccineMainPage:", var3.size() + ":" + var3.toString());
            AdapterVaccine var4 = new AdapterVaccine(this, R.layout.grid_vaccinechart , var3);
            this.grid_vaccinechart.setAdapter(var4);
        } catch (IOException var2) {
            var2.printStackTrace();
        }


    }
}
