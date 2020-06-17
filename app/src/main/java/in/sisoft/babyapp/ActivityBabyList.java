package in.sisoft.babyapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.GridView;
import android.widget.TextView;

public class ActivityBabyList extends AppCompatActivity {
    TextView Text;
    DatabaseHelper dbhelper;
    GridView grid;
    String name;
    RecyclerView.LayoutManager layoutManager;
    RecyclerView recyclerView;

    public ActivityBabyList() {
    }

    public void loadGrid() {
        this.dbhelper = new DatabaseHelper(this);

        try {
            CustomAdapter ca = new CustomAdapter(this);
            recyclerView.setAdapter(ca);
        } catch (Exception var3) {
            AlertDialog.Builder var2 = new AlertDialog.Builder(this);
            var2.setMessage(var3.toString());
            var2.show();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_baby_list);
        recyclerView = (RecyclerView)findViewById(R.id.myrv);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
    }

    public boolean onCreateOptionsMenu(Menu var1) {
        this.getMenuInflater().inflate(R.menu.baby_list, var1);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem var1) {
        switch(var1.getItemId()) {
            case R.id.back:
                this.finish();
                break;
            case R.id.baby_add:
                this.startActivity(new Intent(this, ActivityBabyAdd.class));
        }

        return true;
    }

    public void onStart() {
        super.onStart();
        this.loadGrid();
    }

}

