package in.sisoft.babyapp.model;

public class VaccineManager {
    public String bname;
    public String docname;
    public int id;
    public String note;
    public String vacname;
    public String vduedate;
    public String vgivendate;

    public VaccineManager() {
    }

    public VaccineManager(int var1, String var2, String var3) {
        this.id = var1;
        this.vacname = var2;
        this.vduedate = var3;
    }

    public VaccineManager(int var1, String var2, String var3, String var4, String var5, String var6) {
        this.id = var1;
        this.bname = var2;
        this.docname = var3;
        this.vacname = var4;
        this.vgivendate = var5;
        this.note = var6;
    }

    public VaccineManager(int var1, String var2, String var3, String var4, String var5, String var6, String var7) {
        this.id = var1;
        this.bname = var2;
        this.docname = var3;
        this.vacname = var4;
        this.vduedate = var5;
        this.vgivendate = var6;
        this.note = var7;
    }

    public VaccineManager(String var1) {
        this.bname = var1;
    }

    public VaccineManager(String var1, String var2, String var3, String var4, String var5, String var6) {
        this.bname = var1;
        this.docname = var2;
        this.vacname = var3;
        this.vduedate = var4;
        this.vgivendate = var5;
        this.note = var6;
    }

    public int getId() {
        return this.id;
    }

    public String getbName() {
        return this.bname;
    }

    public String getdocName() {
        return this.docname;
    }

    public String getdueDate() {
        return this.vduedate;
    }

    public String getgiveDate() {
        return this.vgivendate;
    }

    public String getnote() {
        return this.note;
    }

    public String getvName() {
        return this.vacname;
    }

    public void setId(int var1) {
        this.id = var1;
    }
}
