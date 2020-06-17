package in.sisoft.babyapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.Calendar;

import in.sisoft.babyapp.model.Baby;

public class ActivityBabyUpdate extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    ArrayAdapter<String> aaBldgrp;
    ArrayAdapter<String> aaGen;
    ArrayAdapter<String> aaRh;
    String babyname;
    Button back;
    EditText bname;
    final Calendar c = Calendar.getInstance();
    DatabaseHelper dbhelper;
    TextView dob;
    TextView enter;
    TextView gender;
    TextView name;
    EditText note;
    TextView selection;
    Spinner spinblood;
    Spinner spinrh;
    String[] strBldgrpArray = new String[]{"A", "B", "AB", "O"};
    String[] strGenderArray = new String[]{"Boy", "Girl"};
    String[] strRhrray = new String[]{"Rh+", "Rh-"};
    TextView tv_dob;
    TextView tv_gen;
    Button updatesave;

    public ActivityBabyUpdate() {
    }

    private void CatchError(String var1) {
        Dialog var2 = new Dialog(this);
        var2.setTitle("Error");
        TextView var3 = new TextView(this);
        var3.setText(var1);
        var2.setContentView(var3);
        var2.show();
    }

    private void loadDetails() {
        DatabaseHelper var3 = new DatabaseHelper(this);

        boolean var10001;
        Cursor var12;
        try {
            var12 = var3.getbabyByName(this.babyname);
            this.startManagingCursor(var12);
            if (!var12.moveToFirst()) {
                return;
            }
        } catch (Exception var11) {
            var10001 = false;
            return;
        }

        boolean var2;
        do {
            try {
                String var4 = var12.getString(1);
                String var5 = var12.getString(2);
                String var6 = var12.getString(3);
                String var7 = var12.getString(4);
                String var8 = var12.getString(5);
                String var9 = var12.getString(6);
                this.bname.setText(var4);
                this.tv_dob.setText(var5);
                this.tv_gen.setText(var6);
                int var1 = this.aaBldgrp.getPosition(var7);
                this.spinblood.setSelection(var1);
                var1 = this.aaRh.getPosition(var8);
                this.spinrh.setSelection(var1);
                this.note.setText(var9);
                var2 = var12.moveToNext();
            } catch (Exception var10) {
                var10001 = false;
                return;
            }
        } while(var2);

    }

    public void ShowMemberAddedAlert(Context var1) {
        AlertDialog.Builder var3 = new AlertDialog.Builder(var1);
        var3.setTitle("Modify Baby ");
        var3.setIcon(R.drawable.babyadd);
        ActivityBabyUpdate.DialogListner var2 = new ActivityBabyUpdate.DialogListner();
        var3.setMessage("Baby Updated successfully");
        var3.setPositiveButton("ok", var2);
        var3.create().show();
    }

    public void backToMember() {
        super.finish();
    }

    protected void butOnclickBack() {
        this.setResult(-1, new Intent());
        super.finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_baby_update);
        this.babyname = this.getIntent().getExtras().getString("bname");
        ((TextView)this.findViewById(R.id.txt_titalupdate)).setText("Modify  " + this.babyname);
        this.bname = (EditText)this.findViewById(R.id.edit_bnameupdate);
        this.tv_dob = (TextView)this.findViewById(R.id.btn_dobupdate);
        this.note = (EditText)this.findViewById(R.id.edit_noteupdate);
        this.tv_gen = (TextView)this.findViewById(R.id.spin_genderupdate);
        this.spinblood = (Spinner)this.findViewById(R.id.spin_bloodupdate);
        this.spinblood.setOnItemSelectedListener(this);
        this.aaBldgrp = new ArrayAdapter(this, R.layout.spinner_item , this.strBldgrpArray);
        //this.aaBldgrp.setDropDownViewResource(17367049);
        this.spinblood.setAdapter(this.aaBldgrp);
        this.spinrh = (Spinner)this.findViewById(R.id.spin_rhupdate);
        this.spinrh.setOnItemSelectedListener(this);
        this.aaRh = new ArrayAdapter(this, R.layout.spinner_item, this.strRhrray);
        //this.aaRh.setDropDownViewResource(17367049);
        this.spinrh.setAdapter(this.aaRh);
        this.back = (Button)this.findViewById(R.id.back);
        this.back.setOnClickListener(new View.OnClickListener() {
            public void onClick(View var1) {
                ActivityBabyUpdate.this.butOnclickBack();
            }
        });
        this.updatesave = (Button)this.findViewById(R.id.btn_update);
        this.updatesave.setOnClickListener(new View.OnClickListener() {
            public void onClick(View var1) {
                ActivityBabyUpdate.this.updateBaby();
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
            this.loadDetails();
        } catch (Exception var2) {
        }
    }

    public void updateBaby() {
        String var1 = this.bname.getText().toString();
        String var2 = this.tv_dob.getText().toString();
        String var3 = this.tv_gen.getText().toString();
        String var4 = String.valueOf((String)this.spinblood.getItemAtPosition((int)this.spinblood.getSelectedItemId()));
        String var5 = String.valueOf((String)this.spinrh.getItemAtPosition((int)this.spinrh.getSelectedItemId()));
        String var6 = this.note.getText().toString();

        try {
            if (var1.length() > 0 && var2.length() > 0 && var3.length() > 0 && var4.length() > 0 && var5.length() > 0 && var6.length() > 0) {
                Baby var11 = new Baby(var1, var2, var3, var4, var5, var6);
                this.dbhelper.UpdateBabybyname(var11);
                this.ShowMemberAddedAlert(this);
            } else {
                this.CatchError("Error");
            }
        } catch (Exception var9) {
            this.CatchError(var9.toString());
        } finally {
            ;
        }
    }

    public class DialogListner implements android.content.DialogInterface.OnClickListener {
        public DialogListner() {
        }

        public void onClick(DialogInterface var1, int var2) {
            ActivityBabyUpdate.this.backToMember();
        }
    }
}

