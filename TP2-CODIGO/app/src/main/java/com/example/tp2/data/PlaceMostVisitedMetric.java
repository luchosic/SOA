package com.example.tp2.data;

public class PlaceMostVisitedMetric {
    String place;
    Integer cantidad;

    public PlaceMostVisitedMetric() {
    }

    public PlaceMostVisitedMetric(String place, Integer cantidad) {
        this.place = place;
        this.cantidad = cantidad;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    @Override
    public String toString() {
        return place + ":  " + cantidad + " visitas" + "\n";
    }
}
