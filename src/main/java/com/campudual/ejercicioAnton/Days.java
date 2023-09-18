package com.campudual.ejercicioAnton;

public enum Days {

    L("Lunes",1),
    M("Martes",2),
    X("Miercoles",3),
    J("Jueves",4),
    V("Viernes",5),
    S("Sabado",6),
    D("Domingo",7);


    String name;
    Integer position;

    Days(String name, Integer position) {
        this.name = name;
        this.position = position;
    }
}
