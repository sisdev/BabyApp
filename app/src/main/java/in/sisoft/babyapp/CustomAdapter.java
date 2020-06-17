package in.sisoft.babyapp;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import in.sisoft.babyapp.model.*;

// List of Babies

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder>{

    Context context;
    DatabaseHelper dbhelper;
    Cursor cur1 ;

    public CustomAdapter(Context context){

        dbhelper = new DatabaseHelper(context);
        cur1 = this.dbhelper.getAllBaby();
        Log.d("Baby COunt", ""+cur1.getCount());
        this.context = context ;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        ImageView more;
        TextView colgName;


        MyViewHolder(View itemview) {
            super(itemview);

            this.more = (ImageView) itemview.findViewById(R.id.more);
            this.colgName = (TextView) itemview.findViewById(R.id.colgName);

        }
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater li = LayoutInflater.from(parent.getContext());
        View view = li.inflate(R.layout.baby, parent, false);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        cur1.moveToPosition(position);
        final String babyName = cur1.getString(cur1.getColumnIndex("babyname"));
        //ImageView more = holder.more;
        //TextView colgName = holder.colgName;

        Log.d("OnBindView",babyName);
        holder.colgName.setText(babyName);

        holder.more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ActivityBabyImmunization.class);
                Baby var1 = null;
                intent.putExtra(AppConstant.keyBabyName,babyName);
                context.startActivity(intent);

              //  showPopupMenu(v, babyName);
            }
        });
    }

    @Override
    public int getItemCount()
    {
        return cur1.getCount();
    }


    class SubMenuCLickListener implements PopupMenu.OnMenuItemClickListener
    {
        @Override
        public boolean onMenuItemClick(MenuItem item) {

            if (item.getItemId() == R.id.action_more) {
                Intent intent = new Intent(context, ActivityBabyImmunization.class);
                Baby var1 = null;
       //         intent.putExtra("babyName",holder);
                context.startActivity(intent);
            }

            return false;
        }
    }

    private void showPopupMenu(View view, String baby_name)
    {
        PopupMenu popup = new PopupMenu(context, view);
        MenuInflater inflater = popup.getMenuInflater();
        inflater.inflate(R.menu.menu_sub,popup.getMenu());
        popup.setOnMenuItemClickListener(new SubMenuCLickListener());
        popup.show();
    }
}

