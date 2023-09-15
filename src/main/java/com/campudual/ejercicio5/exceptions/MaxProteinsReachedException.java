package com.campudual.ejercicio5.exceptions;

public class MaxProteinsReachedException extends MaxValuedReachedException {

    public MaxProteinsReachedException() {
        super("Max proteins reached for the actual diet");
    }
}
