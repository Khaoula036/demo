package com.example.demo.destinations;

import javax.persistence.*;

@Entity
public class Destination { // la classe des destinations proposées par l'agence

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String nom; // nom de la destination
    private String pays; // pays de la destination

    public Destination() {
        super();
    }

    public Destination(String nom, String pays) {
        super();
        this.nom = nom;
        this.pays = pays;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getPays() {
        return pays;
    }

    public void setPays(String pays) {
        this.pays = pays;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}