package com.campudual.ejercicioAnton;

public enum Genre {

    FEMALE,
    MALE;

    public static  Genre getByString(String genreName){
        if("m".equals(genreName.trim().toLowerCase())){
            return MALE;
        }else if("f".equals(genreName.trim().toLowerCase())){
            return FEMALE;
        }else if ("mujer".equals(genreName.trim().toLowerCase())){
            return FEMALE;
        }else{
            return MALE;
        }
    }
}
