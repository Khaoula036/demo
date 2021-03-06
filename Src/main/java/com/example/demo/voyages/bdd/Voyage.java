package com.example.demo.voyages.bdd;

import javax.persistence.*;

@Entity
public class Voyage {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Integer depart; // l'horaire de départ
    private Integer duree; // la duree du voyage
    private String destination; // la destination du voyage
    @Embedded
    private Programme programme;

    public Voyage() {
        super();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getDepart() {
        return depart;
    }

    public void setDepart(Integer depart) {
        this.depart = depart;
    }

    public Integer get Duree() {
        return  duree;
    }

    public void setDuree(Integer duree) {
        this. duree =  duree;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public Programme getProgramme() {
        return programme;
    }

    public void setProgramme(Programme programme) {
        this.programme = programme;
    }
}
