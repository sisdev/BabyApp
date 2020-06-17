package in.sisoft.babyapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import in.sisoft.babyapp.model.Baby;

public class ActivityBabyDelete extends AppCompatActivity {
    private static final int REQUEST_CODE = 101;
    Baby b1;
    String babyname;
    Button btn_back;
    Button btn_delete;
    DatabaseHelper dbhelper;
    TextView txt_blood;
    TextView txt_dob;
    TextView txt_gender;
    TextView txt_name;
    TextView txt_note;
    TextView txt_rhfactor;
    TextView txtvw_name;

    public ActivityBabyDelete() {
    }

    private Baby loadDetails() {
        Baby var3 = new Baby(this.babyname);

        boolean var10001;
        Cursor var4;
        try {
            var4 = this.dbhelper.getbabyByName(this.babyname);
            this.startManagingCursor(var4);
            if (!var4.moveToFirst()) {
                return var3;
            }
        } catch (Exception var11) {
            var10001 = false;
            return var3;
        }

        boolean var2;
        do {
            try {
                int var1 = var4.getInt(0);
                String var5 = var4.getString(2);
                String var6 = var4.getString(3);
                String var7 = var4.getString(4);
                String var8 = var4.getString(5);
                String var9 = var4.getString(6);
                this.txt_dob.setText(var5);
                this.txt_blood.setText(var7);
                this.txt_gender.setText(var6);
                this.txt_rhfactor.setText(var8);
                this.txt_note.setText(var9);
                var3.setId(var1);
                var2 = var4.moveToNext();
            } catch (Exception var10) {
                var10001 = false;
                return var3;
            }
        } while(var2);

        return var3;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_baby_delete);
        this.babyname = this.getIntent().getExtras().getString("bname");
        Typeface var2 = Typeface.createFromAsset(this.getAssets(), "algi.ttf");
        this.txtvw_name = (TextView)this.findViewById(R.id.txt_showdetail);
        this.txtvw_name.setTypeface(var2);
        this.txtvw_name.setText("Delete " + this.babyname);
        this.txt_name = (TextView)this.findViewById(R.id.txt_babyname_det);
        this.txt_dob = (TextView)this.findViewById(R.id.txt_babyDOB_det);
        this.txt_blood = (TextView)this.findViewById(R.id.txt_babyBloodGroup_det);
        this.txt_gender = (TextView)this.findViewById(R.id.txt_babygender_det);
        this.txt_rhfactor = (TextView)this.findViewById(R.id.txt_babyRhFactor_det);
        this.txt_note = (TextView)this.findViewById(R.id.txt_babynote_det);
        this.btn_delete = (Button)this.findViewById(R.id.btn_delete);
        this.btn_back = (Button)this.findViewById(R.id.btn_back);
        this.btn_back.setOnClickListener(new View.OnClickListener() {
            public void onClick(View var1) {
                ActivityBabyDelete.this.onbackClick();
            }
        });
        this.btn_delete.setOnClickListener(new View.OnClickListener() {
            private void deleteBaby() {
                AlertDialog var1 = (new AlertDialog.Builder(ActivityBabyDelete.this)).create();
                var1.setTitle("Delete the " + ActivityBabyDelete.this.b1.getName() + "'s Record !!");
                var1.setIcon(R.drawable.babydelete);
                var1.setMessage("Do you want to delete " + ActivityBabyDelete.this.b1.getName() + ":" + ActivityBabyDelete.this.b1.getId());
                var1.setButton(-1, "Yes", new android.content.DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface var1, int var2) {
                        ActivityBabyDelete.this.dbhelper.deleteBabyManager(ActivityBabyDelete.this.b1);
                        ActivityBabyDelete.this.dbhelper.deleteImmunTable(ActivityBabyDelete.this.b1);
                        ActivityBabyDelete.this.finish();
                    }
                });
                var1.setButton(-2, "No", new android.content.DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface var1, int var2) {
                        var1.cancel();
                    }
                });
                var1.show();
            }

            public void onClick(View var1) {
                try {
                    this.deleteBaby();
                } catch (Exception var3) {
                    AlertDialog.Builder var2 = new AlertDialog.Builder(ActivityBabyDelete.this);
                    var2.setMessage(var3.toString());
                    var2.show();
                }
            }
        });
        this.txt_name.setText(this.babyname);
    }

    public void onStart() {
        super.onStart();
        this.dbhelper = new DatabaseHelper(this);
        this.b1 = this.loadDetails();
    }

    public void onUpdate() {
        Intent var1 = new Intent(this, ActivityBabyUpdate.class);
        var1.putExtra("bname", this.babyname);
        this.startActivityForResult(var1, 101);
    }

    protected void onbackClick() {
        this.setResult(-1, new Intent());
        super.finish();
    }
}