package in.sisoft.babyapp;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteCursor;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import in.sisoft.babyapp.model.VaccineManager;
import in.sisoft.babyapp.model.* ;


public class ActivityBabyImmunization extends AppCompatActivity {
    private static final int REQUEST_CODE = 20;
    String babyname;
    String blood;
    Button btn_Email;
    Button btn_back;
    Button btn_vaccine;
    final Calendar currentcal = Calendar.getInstance();
    final Calendar currentdate = Calendar.getInstance();
    DatabaseHelper dbhelper;
    String dob;
    String dudate;
    String gd1;
    GridView grid;
    String immuid;
    TextView txt_dudate31;
    TextView txtbabyname;
    String vname;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_immunization);
        ActionBar ab = getSupportActionBar() ;
        ab.show();
        ab.setDisplayHomeAsUpEnabled(true);
        ab.setTitle((CharSequence)null);
        babyname = getIntent().getExtras().getString(AppConstant.keyBabyName);
        Log.d("Immunization:onCreate", "Baby Name:" + this.babyname);
        ((TextView)this.findViewById(R.id.txt_enter )).setText(this.babyname);
        SimpleDateFormat var4 = new SimpleDateFormat("dd-MMM-yyyy", Locale.getDefault());
        String var2 = var4.format(this.currentcal.getTime());

        try {
            this.currentdate.setTime(var4.parse(var2));
        } catch (ParseException var3) {
            var3.printStackTrace();
        }

        this.btn_back = (Button)this.findViewById(R.id.back);
        this.btn_back.setOnClickListener(new OnClickListener() {
            public void onClick(View var1) {
                ActivityBabyImmunization.this.butOnclickBack();
            }
        });
        this.grid = (GridView)this.findViewById(R.id.grid_immu);
        this.grid.setOnItemClickListener(new OnItemClickListener() {
            public void onItemClick(AdapterView<?> var1, View var2, int var3, long var4) {
                SQLiteCursor var6 = (SQLiteCursor)var1.getItemAtPosition(var3);
                (new VaccineManager()).setId((int)var4);
                ActivityBabyImmunization.this.immuid = var6.getString(var6.getColumnIndex("_id")).toString();
                ActivityBabyImmunization.this.vname = var6.getString(var6.getColumnIndex("vaccine_name")).toString();
                ActivityBabyImmunization.this.dudate = var6.getString(var6.getColumnIndex("due_date")).toString();
                AlertDialog var7 = (new Builder(ActivityBabyImmunization.this)).create();
                var7.setIcon(R.drawable.edituser);
                var7.setTitle("Edit Vaccine Completion Status !!");
                var7.setButton(-1, "Yes", new android.content.DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface var1, int var2) {
                        Intent var3 = new Intent(ActivityBabyImmunization.this, ActivityAddGivendate.class);
                        var3.putExtra("id", ActivityBabyImmunization.this.immuid);
                        var3.putExtra("bname", ActivityBabyImmunization.this.babyname);
                        var3.putExtra("vname", ActivityBabyImmunization.this.vname);
                        var3.putExtra("dudate", ActivityBabyImmunization.this.dudate);
                        ActivityBabyImmunization.this.startActivityForResult(var3, 20);
                    }
                });
                var7.setButton(-2, "No", new android.content.DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface var1, int var2) {
                        var1.cancel();
                    }
                });
                var7.show();
            }
        });
    }

    public void onStart() {
        super.onStart();
        dbhelper = new DatabaseHelper(this);
        loadGrid();
    }

    public void loadGrid() {
        dbhelper = new DatabaseHelper(this);

        try {
            Cursor cursor = dbhelper.getVaccinebabyByName(babyname);
            Toast.makeText(getApplicationContext(), "Count:" + cursor.getCount(), Toast.LENGTH_LONG).show();
            CustomCursorAdapter var4 = new CustomCursorAdapter(this, cursor, 0);
            grid.setAdapter(var4);
        } catch (Exception var3) {
            Toast.makeText(this.getApplicationContext(), "Exception:"+var3.getMessage(),Toast.LENGTH_LONG).show();

            //Builder var2 = new Builder(this);
            // var2.setMessage(var3.toString());
            //var2.show();
        }
    }



    public boolean onCreateOptionsMenu(Menu var1) {
        super.onCreateOptionsMenu(var1);
        this.getMenuInflater().inflate(R.menu.baby_option, var1);
        return super.onCreateOptionsMenu(var1);
    }

    public boolean onOptionsItemSelected(MenuItem var1) {
        Intent var2;
        switch(var1.getItemId()) {
            case R.id.parent:
                var2 = new Intent(this.getApplicationContext(), ActivityAppHome.class);
                var2.putExtra("bname", this.babyname);
                this.startActivity(var2);
                return true;
            case R.id.email :
                this.sendEmail();
                return true;
            case R.id.modify :
                var2 = new Intent(this.getApplicationContext(), ActivityBabyUpdate.class);
                var2.putExtra("bname", this.babyname);
                this.startActivity(var2);
                return true;
            case R.id.delete :
                var2 = new Intent(this.getApplicationContext(), ActivityBabyDelete.class);
                var2.putExtra("bname", this.babyname);
                this.startActivity(var2);
                return true;
            case R.id.view :
                var2 = new Intent(this.getApplicationContext(), ActivityBabyView.class);
                var2.putExtra("bname", this.babyname);
                this.startActivity(var2);
                return true;
            default:
                return super.onOptionsItemSelected(var1);
        }
    }


    private void butOnclickBack() {
        this.setResult(-1, new Intent());
        super.finish();
    }

    public String createHtmlFile(Uri var1) throws IOException {
        Cursor cursor_baby;

//        FileOutputStream var14;
        String strHeader;
        String strReportBody="";
        String strFooter = "";
        String strReport = "" ;

            try {
                cursor_baby = dbhelper.getbabyByName(this.babyname);
                if (cursor_baby.moveToFirst()) {
                    this.dob = cursor_baby.getString(2);
                    this.blood = cursor_baby.getString(4);

                    strHeader = "" + "<html>" + "<head>" + "<title>Baby Vaccine Chart</title>" + "</head>" +
                            "<body bgcolor=white>" +
                            strWidth("Baby Name:", 20) + this.babyname + "<br>" +
                            strWidth("DOB:", 20) + this.dob + "<br>" +
                            strWidth("Blood Group:",20) + this.blood + "<br>"+
                            "<p><b>" + strWidth("Vaccine", 20)+
                            strWidth("Due Date", 20)+
                            strWidth("Given Date", 20)+"</p>";

                    Cursor cursorBabyImmune = dbhelper.getVaccinebabyByName(this.babyname);

                    if (cursorBabyImmune.moveToFirst()) {
                        do {
                            strReportBody = strReportBody +   strWidth(cursorBabyImmune.getString(2),20) +
                                    strWidth( cursorBabyImmune.getString(3),20) +
                                    strWidth(cursorBabyImmune.getString(4), 20) + "<br>";
                        } while (cursorBabyImmune.moveToNext());
                        strFooter = "<p>" + "<b>Generated by: Baby Immunization Tracker</b> <br>" +
                                "<b>Developed by : Sisoft Technologies Pvt.Ltd(www.sisoft.in)</b></p>" +
                                 "</body>" + "</html>";
                        strReport = strHeader + strReportBody + strFooter ;
                         Log.d("createHTMLFile", strReport);


                    }
                }
            }catch (Exception var12) {
                    Log.d("createHtmlFileExp",var12.getMessage());

                }
            return strReport ;
    }

    public String createTextFile() throws IOException {
        Cursor cursor_baby;
        String strHeader;
        String strReportBody="";
        String strFooter = "";
        String strReport = "" ;

        try {
            cursor_baby = this.dbhelper.getbabyByName(this.babyname);
            Log.d("Immunization","Rec Count:"+cursor_baby.getCount());
            if (cursor_baby.moveToFirst()) {
                this.dob = cursor_baby.getString(2);
                this.blood = cursor_baby.getString(4);
                strHeader = strWidth("Baby Name:", 20) + this.babyname + "\n" +
                        strWidth("DOB:", 20) + this.dob + "\n" +
                        strWidth("Blood Group:",20) + this.blood + "\n\n"+
                        strWidth("Vaccine", 30)+
                        strWidth("Due Date", 20)+
                        strWidth("Given Date", 20)+"\n";

                Cursor cursorBabyImmune = this.dbhelper.getVaccinebabyByName(this.babyname);
                if (cursorBabyImmune.moveToFirst()) {
                    do {
                        strReportBody = strReportBody +   strWidth(cursorBabyImmune.getString(2),30) +
                                strWidth( cursorBabyImmune.getString(3),20) +
                                strWidth(cursorBabyImmune.getString(4), 20) + "\n";
                    } while (cursorBabyImmune.moveToNext());
                    strFooter = "\n" + "Generated by: Baby Immunization Tracker\n" +
                            "Developed by : Sisoft Technologies Pvt.Ltd(www.sisoft.in)\n" ;
                    strReport = strHeader + strReportBody + strFooter ;
                    Log.d("createTextFile", strReport);
                }
            }
        }catch (Exception var12) {
            Log.d("createTextFileExp",var12.getMessage());

        }
        return strReport ;
    }



    String strWidth(String s1, int wid)
    {
        s1 = s1.trim();
        int len = s1.length();
        for (int i=len ; i<wid; i++){
            s1=s1.concat(" ");
        }
        return s1 ;
    }


    @SuppressLint({"SdCardPath"})
    public void onPause() {
        super.onPause();
        Log.d("Immunization", "on Pause");
        //(new File("/data/data/in.sisoft.baby/" + this.babyname + ".html")).delete();
    }


    public void onclickback() {
        this.setResult(-1, new Intent());
        super.finish();
    }

    public void sendEmail() {
       // FileInputStream fis = null;
        try {
            /*
            String externalDrivePath = Environment.getExternalStorageDirectory().getAbsolutePath() ;
            File f1 = new File(externalDrivePath);
            for (String x: f1.list())
                Log.d("File List", x);
            String var1 = externalDrivePath + "/in.sisoft.babyapp/";

            File var2 = new File(var1);
            if (!var2.exists()) {
                var2.mkdirs();
            }

            Uri fileUri = Uri.fromFile(var2);

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                fileUri = FileProvider.getUriForFile(this, BuildConfig.APPLICATION_ID + ".provider", var2);
            }
*/
 //           fileUri = Uri.parse(var1  + this.babyname + ".html");

           Uri fileUri = Uri.parse(this.babyname + ".html");
//         String email_msg =   this.createHtmlFile(fileUri);
            String email_msg =   this.createTextFile();

          //  fis = openFileInput(String.valueOf(fileUri));
          //  InputStreamReader isr = new InputStreamReader(fis);
          //  BufferedReader br = new BufferedReader(isr);
            StringBuilder sb = new StringBuilder();
            String text;

            //while ((text = br.readLine()) != null) {
             //   sb.append(text).append("\n");
            //}

            Log.i(this.getClass().getSimpleName(), "Send Email task - start");
            Log.d("fileUri",fileUri.getPath());
            Intent intentEmail = new Intent("android.intent.action.SEND");
            intentEmail.setType("text/html");
            intentEmail.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            intentEmail.setFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
            intentEmail.putExtra("android.intent.extra.EMAIL", new String[]{""});
            intentEmail.putExtra("android.intent.extra.SUBJECT", "Vaccine Chart: "+this.babyname);
           // intentEmail.putExtra("android.intent.extra.STREAM", fileUri);
 //           intentEmail.putExtra("android.intent.extra.TEXT", Html.fromHtml(email_msg));
           intentEmail.putExtra("android.intent.extra.TEXT", email_msg);

            this.startActivity(Intent.createChooser(intentEmail, "Send mail..."));
            //fis.close();
        } catch (Throwable var3) {
            Toast.makeText(this, "Request failed: " + var3.toString(), Toast.LENGTH_LONG).show();
            Log.d("SendEmail",var3.toString());
        }
    }
}
