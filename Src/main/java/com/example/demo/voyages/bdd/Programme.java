package com.example.demo.voyages.bdd;

public class Programme {        // les endroits visités pendant le voyage, le service et l'hotel programmé

    private String hotel;  // le nom de l'hotel réservé
    private String restaurant; // le nom du restaurant

    // les endroits visités
    private String endroit1;
    private String endroit2;
    private String endroit3;

    public Programme() {
        super();
    }

    public String getHotel() {
        return hotel;
    }

    public void setHotel(String hotel) {
        this.hotel = hotel;
    }

    public String getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(String restaurant) {
        this.restaurant = restaurant;
    }

    public String getEndroit1() {
        return endroit1;
    }

    public void setEndroit1(String endroit1) {
        this.endroit1 = endroit1;
    }

    public String getEndroit2() {
        return endroit2;
    }

    public void setEndroit2(String endroit2) {
        this.endroit2 = endroit2;
    }

    public String getEndroit3() {
        return endroit3;
    }

    public void setEndroit3(String endroit3) {
        this.endroit3 = endroit3;
    }

}

