package com.example.tp2.data;

import java.util.Date;

public class UserConexionMetric {
    Date date;
    Integer cantidadLogueos;


    public UserConexionMetric() {

    }


    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Integer getCantidadLogueos() {
        return cantidadLogueos;
    }

    public void setCantidadLogueos(Integer cantidadLogueos) {
        this.cantidadLogueos = cantidadLogueos;
    }
}
