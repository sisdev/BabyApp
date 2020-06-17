package in.sisoft.babyapp.model;

import android.annotation.SuppressLint;

public class Baby {
    public String blood;
    public String dob;
    public String gender;
    int id;
    public String name;
    public String note;
    public String rh;

    @SuppressLint({"Instantiatable"})
    public Baby(String var1) {
        this.name = var1;
    }

    public Baby(String var1, String var2, String var3, String var4, String var5, String var6) {
        this.name = var1;
        this.dob = var2;
        this.gender = var3;
        this.rh = var5;
        this.blood = var4;
        this.note = var6;
    }

    public String getDOB() {
        return this.dob;
    }

    public int getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public String getblood() {
        return this.blood;
    }

    public String getgender() {
        return this.gender;
    }

    public String getnote() {
        return this.note;
    }

    public String getrh() {
        return this.rh;
    }

    public void setDOB(String var1) {
        this.dob = var1;
    }

    public void setId(int var1) {
        this.id = var1;
    }

    public void setName(String var1) {
        this.name = var1;
    }

    public void setRh(String var1) {
        this.rh = var1;
    }

    public void setblood(String var1) {
        this.blood = var1;
    }

    public void setgender(String var1) {
        this.gender = var1;
    }

    public void setnote(String var1) {
        this.note = var1;
    }
}
