package it.tiwiz.rxjavacrunch.part4.model;


public class Departure {

    private Integer arrivalTimeInt;
    private String time;
    private Boolean rt;

    /**
     * @return The arrivalTimeInt
     */
    public Integer getArrivalTimeInt() {
        return arrivalTimeInt;
    }

    /**
     * @param arrivalTimeInt The arrivalTimeInt
     */
    public void setArrivalTimeInt(Integer arrivalTimeInt) {
        this.arrivalTimeInt = arrivalTimeInt;
    }

    /**
     * @return The time
     */
    public String getTime() {
        return time;
    }

    /**
     * @param time The time
     */
    public void setTime(String time) {
        this.time = time;
    }

    /**
     * @return The rt
     */
    public Boolean getRt() {
        return rt;
    }

    /**
     * @param rt The rt
     */
    public void setRt(Boolean rt) {
        this.rt = rt;
    }

}
