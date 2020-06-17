package in.sisoft.babyapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import in.sisoft.babyapp.model.VaccineManager;

public class ActivityAddGivendate extends AppCompatActivity {
    String BabyName;
    String VacId;
    String VaccineName;
    Button btn_back;
    Button btn_date;
    Button btn_savedate;
    int count = 0;
    DatabaseHelper dbhelper;
    String ddate;
    EditText edit_bname;
    EditText edit_docname;
    EditText edit_gdate;
    EditText edit_note;
    EditText edit_vname;
    Boolean flag = false;
    private int mDay;
    private int mMonth;
    private int mYear;
    TextView tital;

    public ActivityAddGivendate() {
    }

    private void loadGivendate() {
        DatabaseHelper var2 = new DatabaseHelper(this);


            Cursor cursor;
            try {
                cursor = var2.getVaccinebabyByName(this.BabyName);
                this.startManagingCursor(cursor);
                if (cursor.moveToFirst()) {
                    do{
                        String var3 = cursor.getString(1);
                        String var4 = cursor.getString(2);
                        String var5 = cursor.getString(3);
                        String var6 = cursor.getString(4);
                        String var7 = cursor.getString(5);
                        String var8 = cursor.getString(6);
                        ++this.count;
                        if (var3.equals(this.BabyName) && var4.equals(this.VaccineName) && this.ddate.equals(var5)) {
                            this.edit_gdate.setText(var6);
                            this.edit_docname.setText(var7);
                            this.edit_note.setText(var8);
                        }
                    } while (cursor.moveToNext());


                }
            } catch (Exception var11) {
                AlertDialog.Builder var14 = new AlertDialog.Builder(this);
                var14.setMessage(var11.toString());
                var14.show();
             }

        }


    public void ShowMemberAddedAlert(Context context) {
        AlertDialog.Builder var3 = new AlertDialog.Builder(context);
        var3.setTitle("Add  Given date ");
        var3.setIcon(R.drawable.injec);
        ActivityAddGivendate.DialogListner var2 = new ActivityAddGivendate.DialogListner();
        var3.setMessage("Date Added successfully");
        var3.setPositiveButton("ok", var2);
        var3.create().show();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_givendate);
        Intent var2 = this.getIntent();
        this.VacId = var2.getExtras().getString("id");
        this.VaccineName = var2.getExtras().getString("vname");
        this.BabyName = var2.getExtras().getString("bname");
        this.ddate = var2.getExtras().getString("dudate");
        this.tital = (TextView)this.findViewById(R.id.txt_tital);
        this.tital.setText(this.VaccineName);
        this.edit_docname = (EditText)this.findViewById(R.id.edit_docname);
        this.edit_gdate = (EditText)this.findViewById(R.id.edit_vaccinedate);
        this.edit_note = (EditText)this.findViewById(R.id.edit_vnote);
        this.btn_date = (Button)this.findViewById(R.id.btn_Givendate);
        this.btn_savedate = (Button)this.findViewById(R.id.btn_savegdate);
        this.btn_back = (Button)this.findViewById(R.id.btn_backgivendate);
        this.btn_back.setOnClickListener(new OnClickListener() {
            public void onClick(View var1) {
                ActivityAddGivendate.this.onbackclick();
            }
        });
        this.btn_date.setOnClickListener(new OnClickListener() {
            public void onClick(View var1) {
                ActivityAddGivendate.this.updateDisplay();
            }
        });
        this.btn_savedate.setOnClickListener(new OnClickListener() {
            public void onClick(View var1) {
                ActivityAddGivendate.this.updateGivenDate();
            }
        });
    }

    public void onStart() {
        try {
            super.onStart();
            this.dbhelper = new DatabaseHelper(this);
            this.loadGivendate();
        } catch (Exception var2) {
        }
    }

    public void onbackclick() {
        this.setResult(-1, new Intent());
        super.finish();
    }

    public void updateDisplay() {
        final Calendar cal_current = Calendar.getInstance();
        this.mYear = cal_current.get(Calendar.YEAR);
        this.mMonth = cal_current.get(Calendar.MONTH);
        this.mDay = cal_current.get(Calendar.DAY_OF_MONTH);
        (new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            public void onDateSet(DatePicker var1x, int var2, int var3, int var4) {
                cal_current.set(var2, var3, var4);
                String var5 = (new SimpleDateFormat("dd-MMM-yyyy")).format(cal_current.getTime()).toString();
                ActivityAddGivendate.this.edit_gdate.setText(var5);
            }
        }, this.mYear, this.mMonth, this.mDay)).show();
    }

    public void updateGivenDate() {
        if (!this.flag) {
            try {
                DatabaseHelper dbHelper = new DatabaseHelper(this);
                int var1 = Integer.parseInt(this.VacId);
                String var9 = this.BabyName;
                String var4 = this.edit_docname.getText().toString();
                String var5 = this.VaccineName;
                String var6 = this.edit_gdate.getText().toString();
                String var7 = this.edit_note.getText().toString();
                if (var9.length() > 0 && var4.length() > 0 && var5.length() > 0 && var6.length() > 0 && var7.length() > 0) {
                    dbHelper.updateImunization(new VaccineManager(var1, var9, var4, var5, var6, var7));
                    this.ShowMemberAddedAlert(this);
                    return;
                }

                Toast.makeText(this.getApplicationContext(), "Enter Value first", Toast.LENGTH_LONG).show();
                return;
            } catch (Exception var8) {
                AlertDialog.Builder var3 = new AlertDialog.Builder(this);
                var3.setMessage("Error :" + var8);
                var3.show();
            }
        }

    }

    public class DialogListner implements android.content.DialogInterface.OnClickListener {
        public DialogListner() {
        }

        public void onClick(DialogInterface var1, int var2) {
            ActivityAddGivendate.this.onbackclick();
        }
    }
}
