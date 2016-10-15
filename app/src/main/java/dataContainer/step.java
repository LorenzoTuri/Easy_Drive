package dataContainer;

import com.google.android.gms.maps.model.LatLng;

/**
 * Created by Ge on 12/05/2016.
 */
public class step {
    private String poly;
    private String htmlInstructions;
    private LatLng startLocation;
    private LatLng endLocation;
    private int duration;
    private String distance;

    public step(String poly, String htmlInstructions, LatLng startLocation, LatLng endLocation, int duration, String distance){
        this.setPoly(poly);
        this.setHtmlInstructions(htmlInstructions);
        this.setEndLocation(endLocation);
        this.setStartLocation(startLocation);
        this.setDuration(duration);
        this.setDistance(distance);
    }

    public String getPoly() {
        return poly;
    }

    public void setPoly(String poly) {
        this.poly = poly;
    }

    public String getHtmlInstructions() {
        return htmlInstructions;
    }

    public void setHtmlInstructions(String htmlInstructions) {
        this.htmlInstructions = htmlInstructions;
    }

    public LatLng getStartLocation() {
        return startLocation;
    }

    public void setStartLocation(LatLng startLocation) {
        this.startLocation = startLocation;
    }

    public LatLng getEndLocation() {
        return endLocation;
    }

    public void setEndLocation(LatLng endLocation) {
        this.endLocation = endLocation;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }
}
