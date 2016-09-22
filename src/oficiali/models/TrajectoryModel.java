package oficiali.models;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


/** 
 * 9857,2008-02-02 15:15:38,116.35613,39.88479
 */
public class TrajectoryModel {
    
    private final int idveicle;
    private final Date when;
    private final String longitude;
    private final String latitude;

    public TrajectoryModel(int idveicle, Date when, String longitude, String latitude) {
        this.idveicle = idveicle;
        this.when = when;
        this.longitude = longitude;
        this.latitude = latitude;
    }

    public int getIdveicle() {
        return idveicle;
    }

    public Date getWhen() {
        return when;
    }

    public String getLongitude() {
        return longitude;
    }

    public String getLatitude() {
        return latitude;
    }
    
    private TrajectoryModel(String idveicle, String when, String longitude, String latitude) throws ParseException {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
        Date date = dateFormat.parse(when);
        this.idveicle = Integer.parseInt(idveicle);
        this.longitude = longitude;
        this.latitude = latitude;
        this.when = date;
    }
    
    public static TrajectoryModel parser(String line) throws ParseException {
        String[] lineSplited = line.split(",");
        return new TrajectoryModel(lineSplited[0], lineSplited[1], lineSplited[2], lineSplited[3]);
    }

    @Override
    public String toString() {
        return idveicle + "," + when + "," + longitude + "," + latitude;
    }
    
    
    
}
