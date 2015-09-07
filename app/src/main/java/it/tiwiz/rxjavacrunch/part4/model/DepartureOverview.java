package it.tiwiz.rxjavacrunch.part4.model;


import java.util.ArrayList;
import java.util.List;

public class DepartureOverview {

    private String longName;
    private String name;
    private List<Departure> departures = new ArrayList<>();
    private String lineType;

    /**
     * @return The longName
     */
    public String getLongName() {
        return longName;
    }

    /**
     * @param longName The longName
     */
    public void setLongName(String longName) {
        this.longName = longName;
    }

    /**
     * @return The name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name The name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return The departures
     */
    public List<Departure> getDepartures() {
        return departures;
    }

    /**
     * @param departures The departures
     */
    public void setDepartures(List<Departure> departures) {
        this.departures = departures;
    }

    /**
     * @return The lineType
     */
    public String getLineType() {
        return lineType;
    }

    /**
     * @param lineType The lineType
     */
    public void setLineType(String lineType) {
        this.lineType = lineType;
    }

}

