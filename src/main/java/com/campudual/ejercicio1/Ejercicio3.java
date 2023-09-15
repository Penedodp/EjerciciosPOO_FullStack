package com.campudual.ejercicio1;

import java.lang.invoke.SwitchPoint;
import java.util.Scanner;

public class Ejercicio3 {
    public static void main(String[] args) {

        Integer numero;
        System.out.println("Escriba un numero del 1-20");
        Scanner introducido = new Scanner(System.in);
        numero = introducido.nextInt();


        switch (numero) {
            case 2:
            case 3:
            case 5:
            case 7:
            case 11:
            case 13:
            case 17:
            case 19:
                System.out.println("El numero es primo");
                break;
            default:
                System.out.println("No es primo");
                break;
        }
    }
}
