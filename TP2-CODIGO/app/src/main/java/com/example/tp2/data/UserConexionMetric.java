package com.example.tp2.data;

public class UserConexionMetric {
    String franja;
    String cantidadLogueos;


    public UserConexionMetric() {

    }

    public UserConexionMetric(String franja, String cantidadLogueos) {
        this.franja = franja;
        this.cantidadLogueos = cantidadLogueos;
    }

    public String getFranja() {
        return franja;
    }

    public void setFranja(String franja) {
        this.franja = franja;
    }

    public String getCantidadLogueos() {
        return cantidadLogueos;
    }

    public void setCantidadLogueos(String cantidadLogueos) {
        this.cantidadLogueos = cantidadLogueos;
    }

    @Override
    public String toString() {
        return franja + ":  " + cantidadLogueos + " logueos" + "\n";
    }

}
