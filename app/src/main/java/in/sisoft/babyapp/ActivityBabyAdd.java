package in.sisoft.babyapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlarmManager;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import in.sisoft.babyapp.model.AppConstant;
import in.sisoft.babyapp.model.Baby;
import in.sisoft.babyapp.model.VaccineObject;

public class ActivityBabyAdd extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    public static int notificationCount;
    private String babyname;
    String[] blood_grp_options = new String[]{"A", "B", "AB", "O"};
    Button btn_back;
    Button btn_dob;
    Button btn_save;
    final Calendar currentdate = Calendar.getInstance();
    DatabaseHelper dbhelper;
    EditText edit_date;
    TextView enter;
    TextView gender;
    String[] gender_options = new String[]{"Boy", "Girl"};
    private int mDay;
    private int mHour;
    private int mMinute;
    private int mMonth;
    private int mYear;
    TextView name;
    EditText note;
    String[] rh_options = new String[]{"Rh+", "Rh-"};
    EditText rname;
    SimpleDateFormat sdf;
    TextView selection;
    Spinner spin_gender;
    Spinner spinblood;
    Spinner spinrh;
    String str_dob;
    private String time;

    public ActivityBabyAdd() {
    }

    private void CatchError(String strError) {
        Dialog dlg = new Dialog(this);
        dlg.setTitle("Fill all details ");
        TextView textView = new TextView(this);
        textView.setText(strError);
        dlg.setContentView(textView);
        dlg.show();
    }

    private void commenceReminderAlarm() {
        AlarmManager alarmManager = (AlarmManager)this.getSystemService(Context.ALARM_SERVICE);
        PendingIntent pending = PendingIntent.getBroadcast(this, 0, new Intent(this, ReceiverReminderAlarm.class), 0);
        Calendar calAlarm = Calendar.getInstance();
        calAlarm.set(Calendar.HOUR_OF_DAY, AppConstant.hourOfDay);
        calAlarm.set(Calendar.MINUTE, 0);
        calAlarm.set(Calendar.SECOND, 0);
        alarmManager.setInexactRepeating(AlarmManager.RTC_WAKEUP, calAlarm.getTimeInMillis(), 86400000L, pending);
    }

    private void saveVaccineChart(Calendar calVaccine, String babyName, String vaccineName) {
        try {
            this.sdf = new SimpleDateFormat("dd-MMM-yyyy", Locale.UK);
            String vaccineDate = this.sdf.format(calVaccine.getTime()).toString();
            this.dbhelper.createVaccineChart(babyName, vaccineName, vaccineDate, "NA");
        } catch (Exception var6) {
            Toast.makeText(this.getApplicationContext(), "Error", Toast.LENGTH_LONG).show();
            this.CatchError(var6.toString());
        } finally {
            ;
        }
    }

    public void ShowMemberAddedAlert(Context ctx) {
        AlertDialog.Builder alertBuilder = new AlertDialog.Builder(ctx);
        alertBuilder.setTitle("Add new Baby ");
        alertBuilder.setIcon(R.drawable.babyadd);
        ActivityBabyAdd.DialogListner var2 = new ActivityBabyAdd.DialogListner();
        alertBuilder.setMessage("Baby Added successfully");
        alertBuilder.setPositiveButton("ok", var2);
        alertBuilder.create().show();
    }

    public void addbaby() {
        this.babyname = this.rname.getText().toString();
        String dob = this.edit_date.getText().toString();
        String gender = String.valueOf((String)this.spin_gender.getItemAtPosition((int)this.spin_gender.getSelectedItemId()));
        String bloodGrp = String.valueOf((String)this.spinblood.getItemAtPosition((int)this.spinblood.getSelectedItemId()));
        String rhFactor = String.valueOf((String)this.spinrh.getItemAtPosition((int)this.spinrh.getSelectedItemId()));
        String note = this.note.getText().toString();

        try {
            if (this.babyname.length() > 0 && dob.length() > 0 && gender.length() > 0 && bloodGrp.length() > 0 && rhFactor.length() > 0 && note.length() > 0) {
                Baby var10 = new Baby(this.babyname, dob, gender, bloodGrp, rhFactor, note);
                Toast.makeText(this.getApplicationContext(), var10.getName() + var10.getblood() + var10.getgender() + var10.getDOB(), Toast.LENGTH_SHORT).show();
                this.dbhelper.addBaby(var10);
                this.createBabyVaccinationChart(dob, gender);
                this.ShowMemberAddedAlert(this);
                this.commenceReminderAlarm();
            } else {
                this.CatchError("Fill All Details");
            }
        } catch (Exception exception) {
            this.CatchError(exception.toString());
        } finally {
            ;
        }
    }

    public void backToMember() {
        this.setResult(-1, new Intent(this, ActivityBabyList.class));
        super.finish();
    }

    protected void butOnclickBack() {
        this.setResult(-1, new Intent());
        super.finish();
    }

    public void createBabyVaccinationChart(String dob, String gender) {
                int n = 0 ;
                try {
                    Log.d("Str Dob Date", dob);
                    this.sdf = new SimpleDateFormat("dd-MMM-yyyy", Locale.UK);
                    final Date parse = this.sdf.parse(dob);
                    final Calendar instance = Calendar.getInstance();
                    instance.setTime(parse);
                    Log.d("DOB Date", this.sdf.format(instance.getTime()));
                    final Date time = instance.getTime();
                    final List<VaccineObject> parse2 = new VaccineParsingData().parse(this.getAssets().open("vaccine_chart.xml"));
                    Log.d("Size:", String.valueOf(parse2.size()) + ":" + parse2.toString());
                 while (true) {
                     final Calendar instance2 = Calendar.getInstance();
                     //  n = 0;
                    if (n >= parse2.size()) {
                        return;
                    }
                    final VaccineObject vaccineObject = (VaccineObject) parse2.get(n);
                    instance2.setTime(time);
                    Log.d("At Start Vaccine Cal", this.sdf.format(instance2.getTime()));
                    Log.d("At Start Vaccine Cal", String.valueOf(vaccineObject.getDay()) + ":" + vaccineObject.getMonth() + ":" + vaccineObject.getYear());
                    instance2.add(Calendar.DATE, Integer.parseInt(vaccineObject.getDay()));
                    instance2.add(Calendar.MONTH, Integer.parseInt(vaccineObject.getMonth()));
                    instance2.add(Calendar.YEAR, Integer.parseInt(vaccineObject.getYear()));
                    Log.d("End Vaccine Cal", this.sdf.format(instance2.getTime()));
                    if (!vaccineObject.getVaccineName().endsWith("HPV(only for females)") || !gender.equalsIgnoreCase("Boy")) {
                        this.saveVaccineChart(instance2, this.babyname, vaccineObject.getVaccineName());
                    }
                     ++n;
                     continue;
                }

                }

                catch (Exception ex) {
                    ex.printStackTrace();
                    return;
                }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_baby_add);
        this.enter = (TextView)this.findViewById(R.id.txt_enter);
        this.edit_date = (EditText)this.findViewById(R.id.edit_dob);
        this.rname = (EditText)this.findViewById(R.id.rname);
        this.rname.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable var1) {
                String var2 = ActivityBabyAdd.this.rname.getText().toString();
                if (ActivityBabyAdd.this.dbhelper.countBabyName(var2) > 0) {
                    Toast.makeText(ActivityBabyAdd.this.getApplicationContext(), var2 + ":This name already exists. Enter another name", Toast.LENGTH_LONG).show();
                    ActivityBabyAdd.this.rname.setText("");
                }

            }

            public void beforeTextChanged(CharSequence var1, int var2, int var3, int var4) {
            }

            public void onTextChanged(CharSequence var1, int var2, int var3, int var4) {
            }
        });
        this.note = (EditText)this.findViewById(R.id.notetakn);
        this.spin_gender = (Spinner)this.findViewById(R.id.spinner);
        this.spin_gender.setOnItemSelectedListener(this);
        ArrayAdapter aa_gender = new ArrayAdapter(this, R.layout.spinner_item, this.gender_options);
        this.spin_gender.setAdapter(aa_gender);
        this.spinblood = (Spinner)this.findViewById(R.id.spinnerblood);
        this.spinblood.setOnItemSelectedListener(this);
        ArrayAdapter aa_blood_grp = new ArrayAdapter(this, R.layout.spinner_item , this.blood_grp_options);
        this.spinblood.setAdapter(aa_blood_grp);
        this.spinrh = (Spinner)this.findViewById(R.id.spinnerrh);
        this.spinrh.setOnItemSelectedListener(this);
        ArrayAdapter aa_rh_factor = new ArrayAdapter(this, R.layout.spinner_item , this.rh_options);
        this.spinrh.setAdapter(aa_rh_factor);
        this.btn_dob = (Button)this.findViewById(R.id.btn_baby_DOB);
        this.btn_dob.setOnClickListener(new View.OnClickListener() {
            public void onClick(View var1) {
                ActivityBabyAdd.this.show_calendar();
            }
        });
        this.btn_back = (Button)this.findViewById(R.id.back);
        this.btn_back.setOnClickListener(new View.OnClickListener() {
            public void onClick(View var1) {
                ActivityBabyAdd.this.butOnclickBack();
            }
        });
        this.btn_save = (Button)this.findViewById(R.id.save);
        this.btn_save.setOnClickListener(new View.OnClickListener() {
            public void onClick(View var1) {
                ActivityBabyAdd.this.addbaby();
            }
        });
    }

    public void onItemSelected(AdapterView<?> var1, View var2, int var3, long var4) {
    }

    public void onNothingSelected(AdapterView<?> var1) {
    }

    public void onStart() {
        try {
            super.onStart();
            this.dbhelper = new DatabaseHelper(this);
        } catch (Exception var2) {
            Log.d("BabyAdd:onStart", "Exception:" + var2.getMessage());
        }
    }

    public void show_calendar() {
        final Calendar cal_current = Calendar.getInstance();
        this.mYear = cal_current.get(Calendar.YEAR);
        this.mMonth = cal_current.get(Calendar.MONTH);
        this.mDay = cal_current.get(Calendar.DATE);
        (new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            public void onDateSet(DatePicker var1x, int var2, int var3, int var4) {
                cal_current.set(var2, var3, var4);
                ActivityBabyAdd.this.sdf = new SimpleDateFormat("dd-MMM-yyyy", Locale.UK);
                String var5 = ActivityBabyAdd.this.sdf.format(cal_current.getTime()).toString();
                ActivityBabyAdd.this.edit_date.setText(var5);
                ActivityBabyAdd.this.mYear = cal_current.get(Calendar.YEAR);
                ActivityBabyAdd.this.mMonth = cal_current.get(Calendar.MONTH);
                ActivityBabyAdd.this.mDay = cal_current.get(Calendar.DATE);
            }
        }, this.mYear, this.mMonth, this.mDay)).show();
    }

    public class DialogListner implements android.content.DialogInterface.OnClickListener {
        public DialogListner() {
        }

        public void onClick(DialogInterface var1, int var2) {
            ActivityBabyAdd.this.backToMember();
        }
    }

}