package com.campudual.ejercicio1;

import java.time.Year;

public class Ejercicio2 {
    public static void main(String[] args) {
        int year = 2005;

        if (esBisiesto(year)) {
            System.out.println("El año es bisiesto");
        } else {
            System.out.println("El año no es bisiesto");
        }
    }

    public static boolean esBisiesto(int year) {
        return (year % 4 == 0 && year % 100 != 0) || (year % 400 == 0);
    }
}
