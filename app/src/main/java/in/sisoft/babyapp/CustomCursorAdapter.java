package in.sisoft.babyapp;

import android.graphics.Color;
import android.widget.CursorAdapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

@SuppressLint({"ResourceAsColor"})

public class CustomCursorAdapter extends CursorAdapter {
    Calendar cal_today;
    private LayoutInflater mInflater;

    public CustomCursorAdapter(Context context, Cursor cursor, int var3) {
        super(context, cursor, var3);
        this.mInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.cal_today = Calendar.getInstance();
    }

    public void bindView(View view, Context context, Cursor cursor) {
        if (cursor.getPosition() % 2 == 1) {
            view.setBackgroundColor(context.getResources().getColor(R.color.oddback));
        } else {
            view.setBackgroundColor(context.getResources().getColor(R.color.evenback));
        }

        TextView tv_imm_id = (TextView)view.findViewById(R.id.colimmu_id);
        TextView tv_imm_name = (TextView)view.findViewById(R.id.colimmu_name );
        TextView tv_imm_due_date = (TextView)view.findViewById(R.id.colimmu_dudate );
        TextView tv_imm_given_date = (TextView)view.findViewById(R.id.colimmu_givdate );
        String str_due_date = cursor.getString(cursor.getColumnIndex("due_date"));
        String str_given_date = cursor.getString(cursor.getColumnIndex("given_date"));
        tv_imm_id.setText(cursor.getString(cursor.getColumnIndex("_id")));
        tv_imm_name.setText(cursor.getString(cursor.getColumnIndex("vaccine_name")));
        tv_imm_due_date.setText(str_due_date);

        try {
            Date due_date = (new SimpleDateFormat("dd-MMM-yyyy")).parse(str_due_date);
            Calendar cal_cur = Calendar.getInstance();
            cal_cur.setTime(due_date);
            if (str_given_date.equalsIgnoreCase("NA") && this.cal_today.after(cal_cur)) {
                tv_imm_given_date.setText("Over Due");
            } else {
                tv_imm_given_date.setText(str_given_date);
                tv_imm_given_date.setTextColor(context.getResources().getColor(R.color.black));
            }
        } catch (Exception var9) {
            tv_imm_given_date.setText(str_given_date);
            Log.d("CustomCursorAdapter", var9.getMessage() + ":" + str_given_date);
        }
    }

    public View newView(Context context, Cursor cursor, ViewGroup viewGroup) {
        return this.mInflater.inflate(R.layout.immunizationgrid, viewGroup, false);
    }
}
