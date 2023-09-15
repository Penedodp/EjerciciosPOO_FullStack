package com.campudual.ejercicio5;

public enum Days {

    L(0,"Lunes"),
    M(1,"Martes"),
    X(2,"Miercoles"),
    J(3,"Jueves"),
    V(4,"Viernes"),
    S(5,"Sabado"),
    D(6,"Domingo");

    private Integer position;
    private String  name;

    Days(Integer position, String name) {
        this.position = position;
        this.name = name;
    }

    public Integer getPosition() {
        return position;
    }

    public void setPosition(Integer position) {
        this.position = position;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
