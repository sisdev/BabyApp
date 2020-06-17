package in.sisoft.babyapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import java.util.List;

import in.sisoft.babyapp.model.VaccineObject;

public class AdapterVaccine extends ArrayAdapter<VaccineObject> {
    Context ctx;
    List<VaccineObject> lbp;
    int res;

    public AdapterVaccine(Context var1, int var2) {
        super(var1, var2);
    }

    public AdapterVaccine(Context var1, int var2, List<VaccineObject> var3) {
        super(var1, var2, var3);
        this.ctx = var1;
        this.res = var2;
        this.lbp = var3;
    }

    public int getCount() {
        return this.lbp.size() <= 0 ? 1 : this.lbp.size();
    }

    public View getView(int var1, View var2, ViewGroup var3) {
        View var6 = var2;
        var2 = var2;
        if (var6 == null) {
            var2 = LayoutInflater.from(this.getContext()).inflate(R.layout.grid_vaccinechart, (ViewGroup)null);
        }

        VaccineObject var7 = (VaccineObject)this.lbp.get(var1);
        if (var7 != null) {
            TextView var4 = (TextView)var2.findViewById(R.id.colv_vaccineName );
            TextView var5 = (TextView)var2.findViewById(R.id.colv_duration );
            ((TextView)var2.findViewById(R.id.colv_sno )).setText(var7.getId());
            if (var4 != null) {
                var4.setText(var7.getVaccineName().toString().trim());
            }

            if (var5 != null) {
                var5.setText(var7.getDuration().toString().trim());
            }
        }

        return var2;
    }
}
