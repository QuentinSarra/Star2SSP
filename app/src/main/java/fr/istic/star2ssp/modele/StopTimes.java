package fr.istic.star2ssp.modele;

public class StopTimes{

    private String tripId;
    private String arrivalTime;
    private String departureTime;
    private String stopId;
    private String stopsequence;


    public String getTripId() {
        return tripId;
    }

    public void setTripId(String tripId) {
        this.tripId = tripId;
    }

    public String getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(String arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public String getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(String departureTime) {
        this.departureTime = departureTime;
    }

    public String getStopId() {
        return stopId;
    }

    public void setStopId(String stopId) {
        this.stopId = stopId;
    }

    public String getStopsequence() {
        return stopsequence;
    }

    public void setStopsequence(String stopsequence) {
        this.stopsequence = stopsequence;
    }
}
