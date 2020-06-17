package in.sisoft.babyapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.TextView;

public class ActivityWebView1 extends AppCompatActivity {
    int a;
    Button back;
    TextView txt;
    WebView wv;

    public ActivityWebView1() {
    }

    protected void butOnclickBack() {
        super.finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view1);
        this.wv = (WebView)this.findViewById(R.id.wv);
        this.back = (Button)this.findViewById(R.id.back);
        this.txt = (TextView)this.findViewById(R.id.txt1);
        this.a = this.getIntent().getIntExtra("a", this.a);
        if (this.a == 0) {
            this.txt.setText("0 to 4 Months");
            this.wv.loadUrl("file:///android_asset/zero.html");
        }

        if (this.a == 1) {
            this.txt.setText("4 to 6 Months");
            this.wv.loadUrl("file:///android_asset/fourtosix.html");
        }

        if (this.a == 2) {
            this.txt.setText("6 to 9 Months");
            this.wv.loadUrl("file:///android_asset/sixtonine.html");
        }

        if (this.a == 3) {
            this.txt.setText("9 to 12 Months");
            this.wv.loadUrl("file:///android_asset/ninetotwelve.html");
        }

        if (this.a == 4) {
            this.txt.setText("12 to 18 Months ");
            this.wv.loadUrl("file:///android_asset/twelvetoeighteen.html");
        }

        if (this.a == 5) {
            this.txt.setText("18 to 24 Months");
            this.wv.loadUrl("file:///android_asset/eighteentotwentyfour.html");
        }

        if (this.a == 6) {
            this.txt.setText("24 Months");
            this.wv.loadUrl("file:///android_asset/twentyfour.html");
        }

        this.back.setOnClickListener(new View.OnClickListener() {
            public void onClick(View var1) {
                ActivityWebView1.this.butOnclickBack();
            }
        });
    }
}
