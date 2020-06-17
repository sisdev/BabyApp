package in.sisoft.babyapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ActivityBabyView extends AppCompatActivity {

    String babyname;
    Button back;
    DatabaseHelper dbhelper;
    TextView txt_blood;
    TextView txt_dob;
    TextView txt_gender;
    TextView txt_name;
    TextView txt_note;
    TextView txt_rhfactor;
    TextView txtvw_name;

    public ActivityBabyView() {
    }

    private void butOnclickBack() {
        this.setResult(-1, new Intent());
        super.finish();
    }

    private void loadDetails() {
        DatabaseHelper var2 = new DatabaseHelper(this);

        boolean var10001;
        Cursor var10;
        try {
            var10 = var2.getbabyByName(this.babyname);
            this.startManagingCursor(var10);
            if (!var10.moveToFirst()) {
                return;
            }
        } catch (Exception var9) {
            var10001 = false;
            return;
        }

        boolean var1;
        do {
            try {
                String var3 = var10.getString(2);
                String var4 = var10.getString(3);
                String var5 = var10.getString(4);
                String var6 = var10.getString(5);
                String var7 = var10.getString(6);
                this.txt_dob.setText(var3);
                this.txt_blood.setText(var5);
                this.txt_gender.setText(var4);
                this.txt_rhfactor.setText(var6);
                this.txt_note.setText(var7);
                var1 = var10.moveToNext();
            } catch (Exception var8) {
                var10001 = false;
                return;
            }
        } while(var1);

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_baby_view);
        this.babyname = this.getIntent().getExtras().getString("bname");
        this.txtvw_name = (TextView)this.findViewById(R.id.txt_showdetail);
        this.txtvw_name.setText("View " + this.babyname);
        this.txt_name = (TextView)this.findViewById(R.id.txt_babyname_det);
        this.txt_dob = (TextView)this.findViewById(R.id.txt_babyDOB_det);
        this.txt_blood = (TextView)this.findViewById(R.id.txt_babyBloodGroup_det);
        this.txt_gender = (TextView)this.findViewById(R.id.txt_babygender_det);
        this.txt_rhfactor = (TextView)this.findViewById(R.id.txt_babyRhFactor_det);
        this.txt_note = (TextView)this.findViewById(R.id.txt_babynote_det);
        this.back = (Button)this.findViewById(R.id.back);
        this.back.setOnClickListener(new View.OnClickListener() {
            public void onClick(View var1) {
                ActivityBabyView.this.butOnclickBack();
            }
        });
        this.txt_name.setText(this.babyname);
    }

    public void onStart() {
        super.onStart();
        this.loadDetails();
    }
}
