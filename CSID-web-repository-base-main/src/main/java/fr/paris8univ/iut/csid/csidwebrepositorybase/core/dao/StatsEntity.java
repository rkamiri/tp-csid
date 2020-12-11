package fr.paris8univ.iut.csid.csidwebrepositorybase.core.dao;

import javax.persistence.*;

@Entity
@Table(name = "statistiques")
public class StatsEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "entry_type")
    private String entry_type;

    @Column(name = "datetime")
    private String datetime;

    @Column(name = "value")
    private Integer value;

    public StatsEntity(){}

    public StatsEntity(String name, String entry_type, String datetime, int value){
        this.name = name;
        this.entry_type = entry_type;
        this.datetime = datetime;
        this.value=value;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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