//    -Las personas podrán tener asignadas una lista de dietas que se catalogarán de lunes a domingo sacadas de la lista de dietas anterior.

package com.campudual.ejercicioAnton;

public class Customer {
    private String name;
    private String surname;
    private Integer weight;
    private Integer height;
    private Integer age;
    private String gender;

    public Customer() {
    }

    public Customer(String name, String surname, Integer weight, Integer height, Integer age, String gender) {
        this.name = name;
        this.surname = surname;
        this.weight = weight;
        this.height = height;
        this.age = age;
        this.gender = gender;
    }
    public void showCustomerDetails() {
        System.out.println("Nombre: " + this.name );
        System.out.println("Apellido: " + this.surname);
        System.out.println("Peso: " + this.weight);
        System.out.println("Altura: " + this.height + " cm");
        System.out.println("Edad: " + this.age + " años");
        System.out.println("Género: " + this.gender);
    }

    @Override
    public String toString(){
        return this.getName() + " " + this.getSurname();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeigth(Integer height) {
        this.height = height;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}
