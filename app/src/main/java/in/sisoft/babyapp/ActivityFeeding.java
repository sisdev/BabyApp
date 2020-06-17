package in.sisoft.babyapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class ActivityFeeding extends AppCompatActivity {
    private static final int REQUEST_CODE = 14;
    Button TwentyFour;
    int a;
    Button back;
    Button eightin;
    TextView feedheading;
    TextView feedinfo;
    Button four;
    Button nine;
    Button six;
    Button twelve;
    Button zero;

    public ActivityFeeding() {
    }

    protected void Eightinclick() {
        Intent var1 = new Intent(this, ActivityWebView1.class);
        var1.putExtra("a", 5);
        this.startActivityForResult(var1, 14);
    }

    protected void TwentyFourclick() {
        Intent var1 = new Intent(this, ActivityWebView1.class);
        var1.putExtra("a", 6);
        this.startActivityForResult(var1, 14);
    }

    protected void fourclick() {
        Intent var1 = new Intent(this, ActivityWebView1.class);
        var1.putExtra("a", 1);
        this.startActivityForResult(var1, 14);
    }

    protected void nineclick() {
        Intent var1 = new Intent(this, ActivityWebView1.class);
        var1.putExtra("a", 3);
        this.startActivityForResult(var1, 14);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feeding);
        this.feedheading = (TextView)this.findViewById(R.id.feedhead);
        this.feedinfo = (TextView)this.findViewById(R.id.feedinfo);
        this.zero = (Button)this.findViewById(R.id.btn_zerotofour);
        this.four = (Button)this.findViewById(R.id.fourtosix);
        this.six = (Button)this.findViewById(R.id.sixtonine);
        this.nine = (Button)this.findViewById(R.id.ninetotwelve);
        this.twelve = (Button)this.findViewById(R.id.twelvetoeigthin);
        this.eightin = (Button)this.findViewById(R.id.eigthintoTwentyFour);
        this.TwentyFour = (Button)this.findViewById(R.id.TwentyFourMonths);
        this.back = (Button)this.findViewById(R.id.back);
        this.back.setOnClickListener(new OnClickListener() {
            public void onClick(View var1) {
                ActivityFeeding.this.onbackClick();
            }
        });
        this.zero.setOnClickListener(new OnClickListener() {
            public void onClick(View var1) {
                ActivityFeeding.this.zeroclick();
            }
        });
        this.four.setOnClickListener(new OnClickListener() {
            public void onClick(View var1) {
                ActivityFeeding.this.fourclick();
            }
        });
        this.six.setOnClickListener(new OnClickListener() {
            public void onClick(View var1) {
                ActivityFeeding.this.sixclick();
            }
        });
        this.nine.setOnClickListener(new OnClickListener() {
            public void onClick(View var1) {
                ActivityFeeding.this.nineclick();
            }
        });
        this.twelve.setOnClickListener(new OnClickListener() {
            public void onClick(View var1) {
                ActivityFeeding.this.twelveclick();
            }
        });
        this.eightin.setOnClickListener(new OnClickListener() {
            public void onClick(View var1) {
                ActivityFeeding.this.Eightinclick();
            }
        });
        this.TwentyFour.setOnClickListener(new OnClickListener() {
            public void onClick(View var1) {
                ActivityFeeding.this.TwentyFourclick();
            }
        });
    }

    protected void onbackClick() {
        this.setResult(-1, new Intent());
        super.finish();
    }

    protected void sixclick() {
        Intent var1 = new Intent(this, ActivityWebView1.class);
        var1.putExtra("a", 2);
        this.startActivityForResult(var1, 14);
    }

    protected void twelveclick() {
        Intent var1 = new Intent(this, ActivityWebView1.class);
        var1.putExtra("a", 4);
        this.startActivityForResult(var1, 14);
    }

    protected void zeroclick() {
        Intent var1 = new Intent(this, ActivityWebView1.class);
        var1.putExtra("a", 0);
        this.startActivityForResult(var1, 14);
    }
}
