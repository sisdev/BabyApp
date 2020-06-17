package in.sisoft.babyapp;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import in.sisoft.babyapp.model.VaccineObject;

public class VaccineParsingData {
    private String text;
    private VaccineObject vaccineObject;
    List<VaccineObject> vaccineObjectlist = new ArrayList();

    public VaccineParsingData() {
    }


    public List<VaccineObject> parse(InputStream var1) {
        XmlPullParserException var34;
        int event;
        XmlPullParser myParser;
        String tagName;
        try {
            XmlPullParserFactory var3 = XmlPullParserFactory.newInstance();
            var3.setNamespaceAware(true);
            myParser = var3.newPullParser();
            myParser.setInput(var1, (String) null);
            event = myParser.getEventType();
            while (event != XmlPullParser.END_DOCUMENT) {
                tagName = myParser.getName();
                switch (event) {
                    case XmlPullParser.START_TAG:
                        if (tagName.equalsIgnoreCase("chart")) {
                            this.vaccineObject = new VaccineObject();
                        }
                        break;
                    case XmlPullParser.TEXT:
                        this.text = myParser.getText();
                        break;
                    case XmlPullParser.END_TAG:
                        if (tagName.equalsIgnoreCase("chart")) {
                            this.vaccineObjectlist.add(this.vaccineObject);
                        }
                        if (tagName.equalsIgnoreCase("vid")) {
                            this.vaccineObject.setId(this.text);
                        }
                        if (tagName.equalsIgnoreCase("vaccineName")) {
                            this.vaccineObject.setVaccineName(this.text);
                        }
                        if (tagName.equalsIgnoreCase("day")) {
                            this.vaccineObject.setDay(this.text);
                        }
                        if (tagName.equalsIgnoreCase("month")) {
                            this.vaccineObject.setMonth(this.text);
                        }
                        if (tagName.equalsIgnoreCase("year")) {
                            this.vaccineObject.setYear(this.text);
                        }
                        if (tagName.equalsIgnoreCase("info")) {
                            this.vaccineObject.setInformation(this.text);
                        }
                        if (tagName.equalsIgnoreCase("duration")) {
                            this.vaccineObject.setDuration(this.text);
                        }
                        break ;

                }
                event = myParser.next();
            }

        } catch (Exception e) {
        }
        return this.vaccineObjectlist;
    }
}



