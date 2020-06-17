package in.sisoft.babyapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import in.sisoft.babyapp.model.Baby;
import in.sisoft.babyapp.model.VaccineManager;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 3;
    static final String DoctorTable = "Doctordet";
    static final String Vaccine_Table = "Vaccine_Table";
    static final String babytable = "baby";
    static final String colblood = "blood";
    public static final String coldob = "dob";
    public static final String colgen = "gender";
    public static final String colid = "babyid";
    public static final String colname = "babyname";
    static final String colnote = "note";
    static final String colrh = "rh";
    static final String coluAddress = "Address";
    static final String coluContactno = "Phone";
    public static final String coluDrName = "DrName";
    static final String coluID = "Doctor_id";
    static final String columail = "Mail";
    static final String columob = "Mobile";
    static final String coluspecialization = "Sepecialization";
    public static final String colvac_bname = "baby_name";
    public static final String colvac_dudate = "due_date";
    public static final String colvac_givdate = "given_date";
    public static final String colvac_id = "vaccine_id";
    public static final String colvac_vname = "vaccine_name";
    public static final String colvacdoc = "doc";
    public static final String colvacinfo = "info";
    static final String colvacnote = "note";
    static final String dbname = "babydb";
    static final String viewImmu = "viewImmunization";
    static final String viewVaccine = "viewvaccine";
    static final String viewbaby = "viewbaby";

    public DatabaseHelper(Context context) {
        super(context, "babydb", (SQLiteDatabase.CursorFactory)null, 3);
    }

    public int UpdateBabybyname(Baby var1) {
        SQLiteDatabase var3 = this.getWritableDatabase();
        ContentValues var4 = new ContentValues();
        var4.put("babyname", var1.getName());
        var4.put("dob", var1.getDOB());
        var4.put("gender", var1.getgender());
        var4.put("blood", var1.getblood());
        var4.put("rh", var1.getrh());
        var4.put("note", var1.getnote());
        int var2 = var3.update("baby", var4, "babyname=?", new String[]{var1.getName()});
        var3.close();
        return var2;
    }

    public void addBaby(Baby var1) {
        SQLiteDatabase var2 = this.getWritableDatabase();
        ContentValues var3 = new ContentValues();
        var3.put("babyname", var1.getName());
        var3.put("dob", var1.getDOB());
        var3.put("gender", var1.getgender());
        var3.put("blood", var1.getblood());
        var3.put("rh", var1.getrh());
        var3.put("note", var1.getnote());
        var2.insert("baby", (String)null, var3);
        var2.close();
    }

    public int countBabyName(String var1) {
        SQLiteDatabase var3 = this.getWritableDatabase();
        int var2 = var3.rawQuery("SELECT babyname FROM baby WHERE babyname=?", new String[]{var1}).getCount();
        var3.close();
        return var2;
    }

    public void createVaccineChart(String var1, String var2, String var3, String var4) {
        SQLiteDatabase var5 = this.getWritableDatabase();
        ContentValues var6 = new ContentValues();
        var6.put("baby_name", var1);
        var6.put("vaccine_name", var2);
        var6.put("due_date", var3);
        var6.put("given_date", var4);
        var6.put("doc", "Dr:");
        var6.put("note", "Note:");
        var5.insert("Vaccine_Table", (String)null, var6);
        var5.close();
    }

    public void deleteBabyManager(Baby var1) {
        SQLiteDatabase var2 = this.getWritableDatabase();
        var2.delete("baby", "babyid=?", new String[]{String.valueOf(var1.getId())});
        var2.close();
    }

    public void deleteImmunTable(Baby var1) {
        SQLiteDatabase var2 = this.getWritableDatabase();
        var2.delete("Vaccine_Table", "baby_name=?", new String[]{var1.getName()});
        var2.close();
    }

    public Cursor getAllBaby() {
        return this.getWritableDatabase().rawQuery("SELECT * FROM baby", (String[])null);
    }

    public Cursor getVaccinebabyByName(String var1) {
        return this.getReadableDatabase().query("viewImmunization", new String[]{"_id", "baby_name", "vaccine_name", "due_date", "given_date", "doc", "note"}, "baby_name=?", new String[]{var1}, (String)null, (String)null, (String)null);
    }

    public Cursor getbabyByName(String var1) {
        return this.getReadableDatabase().query("baby", new String[]{"babyid", "babyname", "dob", "gender", "blood", "rh", "note"}, "babyname=?", new String[]{var1}, (String)null, (String)null, (String)null);
    }

    public void onCreate(SQLiteDatabase var1) {
        var1.execSQL("CREATE TABLE baby " +
                "(babyid INTEGER PRIMARY KEY AUTOINCREMENT," +
                "babyname TEXT, " +
                "dob TEXT, " +
                "gender TEXT, " +
                "blood TEXT, " +
                "rh TEXT, " +
                "note TEXT);");

        var1.execSQL("CREATE TABLE Vaccine_Table " +
                "(vaccine_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "baby_name TEXT, " +
                "vaccine_name TEXT," +
                "due_date TEXT, " +
                "given_date TEXT, " +
                "doc TEXT," +
                "info TEXT, " +
                "note TEXT);");
        var1.execSQL("CREATE VIEW viewbaby " +
                "AS SELECT baby.babyid AS _id, baby.babyname, baby.dob, baby.gender, " +
                "baby.blood, baby.rh, baby.note  FROM baby");
        var1.execSQL("CREATE VIEW viewImmunization AS " +
                "SELECT Vaccine_Table.vaccine_id AS _id, " +
                "Vaccine_Table.baby_name, " +
                "Vaccine_Table.vaccine_name, " +
                "Vaccine_Table.due_date, " +
                "Vaccine_Table.given_date, " +
                "Vaccine_Table.doc, " +
                "Vaccine_Table.info, " +
                "Vaccine_Table.note  " +
                "FROM Vaccine_Table");
    }

    public void onUpgrade(SQLiteDatabase var1, int var2, int var3) {
        var1.execSQL("DROP TABLE IF EXISTS baby");
        var1.execSQL("DROP VIEW IF EXISTS viewbaby");
        var1.execSQL("DROP VIEW IF EXISTS viewvaccine");
        this.onCreate(var1);
    }

    public int updateBaby(Baby var1) {
        SQLiteDatabase var3 = this.getWritableDatabase();
        ContentValues var4 = new ContentValues();
        var4.put("babyid", var1.getId());
        var4.put("babyname", var1.getName());
        var4.put("dob", var1.getDOB());
        var4.put("gender", var1.getgender());
        var4.put("blood", var1.getblood());
        var4.put("rh", var1.getrh());
        var4.put("note", var1.getnote());
        int var2 = var3.update("baby", var4, "babyid=?", new String[]{String.valueOf(var1.getId())});
        var3.close();
        return var2;
    }

    public int updateImunization(VaccineManager var1) {
        SQLiteDatabase var3 = this.getWritableDatabase();
        ContentValues var4 = new ContentValues();
        var4.put("given_date", var1.getgiveDate());
        var4.put("doc", var1.getdocName());
        var4.put("note", var1.getnote());
        int var2 = var3.update("Vaccine_Table", var4, "vaccine_id=?", new String[]{String.valueOf(var1.getId())});
        var3.close();
        return var2;
    }

}

