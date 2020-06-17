package in.sisoft.babyapp.model;

public class VaccineObject {private String day;
    private String duration;
    private String id;
    private String information;
    private String month;
    private String vaccineName;
    private String year;

    public VaccineObject() {
    }

    public String geInformation() {
        return this.information;
    }

    public String getDay() {
        return this.day;
    }

    public String getDuration() {
        return this.duration;
    }

    public String getId() {
        return this.id;
    }

    public String getMonth() {
        return this.month;
    }

    public String getVaccineName() {
        return this.vaccineName;
    }

    public String getYear() {
        return this.year;
    }

    public void setDay(String var1) {
        this.day = var1;
    }

    public void setDuration(String var1) {
        this.duration = var1;
    }

    public void setId(String var1) {
        this.id = var1;
    }

    public void setInformation(String var1) {
        this.information = var1;
    }

    public void setMonth(String var1) {
        this.month = var1;
    }

    public void setVaccineName(String var1) {
        this.vaccineName = var1;
    }

    public void setYear(String var1) {
        this.year = var1;
    }

    public String toString() {
        return this.id + "\n" + this.vaccineName + "\n" + this.day + "\n" + this.month + "\n" + this.year + "\n" + this.information + "\n" + this.duration;
    }
}
