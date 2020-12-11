package fr.paris8univ.iut.csid.csidwebrepositorybase.core.domain;

public class Stats {

    private String name;
    private String entry_type;
    private String datetime;
    private Integer value;

    public Stats() {}

    public Stats(String name, String entry_type, String datetime, int value) {
        this.name=name;
        this.entry_type=entry_type;
        this.datetime=datetime;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEntry_type() {
        return entry_type;
    }

    public void setEntry_type(String entry_type) {
        this.entry_type = entry_type;
    }

    public String getDatetime() {
        return datetime;
    }

    public void setDatetime(String datetime) {
        this.datetime = datetime;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }
}



