package com.campudual.ejercicioAnton;


import com.campudual.ejercicioAnton.exceptions.MaxCaloriesReachedException;
import com.campudual.ejercicioAnton.exceptions.MaxCarbsReachedException;
import com.campudual.ejercicioAnton.exceptions.MaxFatsReachedException;
import com.campudual.ejercicioAnton.exceptions.MaxProteinsReachedException;

import java.util.ArrayList;
import java.util.List;

import static com.campudual.ejercicioAnton.Kb.getOption;

public class DietProgram {

    public static final String MAXCALORIES = "maxcalories";
    public static final String MAXCARBS = "maxcarbs";
    public static final String MAXFATS = "maxfats";
    public static final String MAXPROTEINS = "maxproteins";

    private List<Food> foodList;
    private List<Diet> dietList;
    private List<Customer> customerList;
    private Customer activeCustomer;
    private Diet diet = null;

    public DietProgram() {
        foodList = new ArrayList<>();
        dietList = new ArrayList<>();
        customerList = new ArrayList<>();
    }

    public void showMenuProgram() {
        System.out.println("########################################################");
        System.out.println("################# Programa de dietas ###################");
        System.out.println("########################################################");
        Integer option;
        do {
            System.out.println("Escriba una opción:");
            System.out.println("===================================");
            System.out.println("1-Gestión de dietas");
            System.out.println("2-Gestión de pacientes");
            System.out.println("3-Salir del programa");
            option = Kb.getOption(1, 3);
            switch (option) {
                case 1:
                    do {
                        System.out.println("Escriba una opción:");
                        System.out.println("===================================");
                        System.out.println("1-Agregar nueva dieta");
                        System.out.println("2-Listado de dietas");
                        System.out.println("3-Eliminar dieta");
                        System.out.println("4-Volver a inicio");
                        option = Kb.getOption(1, 3);
                        switch (option) {
                            case 1:
                                createDiet();
                                dietList.add(diet);
                                break;
                            case 2:
                                   editDiet();
                                break;
                            case 3:
                                deleteDiet();
                                break;
                        }
                    } while (option != 3);
                    break;
                case 2:
                    customerManager();
                    break;
                case 3:
                    System.out.println("Gracias por usar el programa, hasta pronto :)");
                    break;
            }
        } while (option != 4);
    }

    //@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
    //Sección de Pacientes
    //@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
    public void customerManager() {
        System.out.println("######################################");
        System.out.println("######## Gestión de Pacientes ########");
        System.out.println("######################################");
        Integer option;
        do {
            System.out.println("Escriba una opción:");
            System.out.println("===================================");
            System.out.println("1-Lista de pacientes");
            System.out.println("2-Nuevo paciente");
            System.out.println("3-Salir");
            option = Kb.getOption(1, 3);
            switch (option) {
                case 1:
                    showPatientList();
                    break;
                case 2:
                    addCostumer();
                    break;
                case 3:
                    System.out.println("Saliendo de Gestión de pacientes");
                    break;
            }
        } while (option != 3);
    }

    private void showPatientList() {
        Integer i;
        if (customerList.isEmpty()) {
            System.out.println("La lista de pacientes está vacía, prueba a crear uno nuevo");
        } else {
            System.out.println("Lista de pacientes");
            for (i = 0; i < customerList.size(); i++) {
                System.out.println(i + " - " + customerList.get(i));
            }

            System.out.println("Escriba un número para elegir un paciente");
            System.out.println("===================================");
            Integer customerIndex = Kb.getOption(1, i - 1);
            activeCustomer = customerList.get(customerIndex);
            System.out.println("===================================");
            System.out.println("Su paciente elegido es: " + activeCustomer.getName() + " " + activeCustomer.getSurname());
            activeCustomerMenu();
        }
    }

    private void addCostumer() {
        System.out.println("███████████████████████████████████████");
        System.out.println("Introduce los datos del nuevo paciente");
        System.out.println("███████████████████████████████████████");
        String name = Kb.nextLine("Nombre del paciente:");
        String surname = Kb.nextLine("Apellido del paciente:");
        Integer weight = Kb.forceNextInt("Peso(en kg):");
        Integer height = Kb.forceNextInt("Altura(en cm):");
        Integer age = Kb.forceNextInt("Edad:");
        String gender = Kb.nextLine("Género (mujer/hombre):");
        Customer patient = new Customer(name, surname, weight, height, age, gender);
        customerList.add(patient);
        System.out.println("Se ha creado el cliente: " + name + " " + surname);
        activeCustomer = patient;
        activeCustomerMenu();
    }

    private void activeCustomerMenu() {
        System.out.println("##########################################");
        System.out.println("############ Paciente Actual #############");
        System.out.println("##########################################");
        Integer option;
        System.out.println("Escriba una opción para :");
        System.out.println(activeCustomer.getName() + " " + activeCustomer.getSurname());
        System.out.println("===================================");
        System.out.println("1-Detalles del paciente");
        System.out.println("2-Asignar dieta al paciente");
        System.out.println("3-Dar de baja al paciente");
        System.out.println("4-Salir");
        option = getOption(1, 4);
        switch (option) {
            case 1:
                activeCustomer.showCustomerDetails();
                break;
            case 2:
                //todo hacer este método funcional
                asignDietToPatient();
                break;
            case 3:
                if (customerList.remove(activeCustomer)) {
                    System.out.println("Se ha eliminado a " + activeCustomer.getName() + " " + activeCustomer.getSurname() + " de la lista de pacientes");
                    activeCustomer = null;
                }
                break;
            case 4:
                System.out.println("Saliendo de Gestión de pacientes");
                break;
        }
    }

    //@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
    //Sección de Dietas
    //@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@

    private void asignDietToPatient() {
        System.out.println("Se ha añadido una dieta al paciente");
        System.out.println("obviamente hay que acabarlo");
    }



    private void editDiet() {
        System.out.println("Escoja una dieta a modificar:");
        Diet selected = getSelectedDiet();
        if (selected == null) {
            System.out.println("Operación cancelada");
        } else {
            Diet selectedDiet = selected;
            Integer option = null;
            do {
                showDetailsMenu(selectedDiet);
                System.out.println("1-Cambiar Calorías máximas");
                System.out.println("2-Cambiar Carbohidratos máximas");
                System.out.println("3-Cambiar Grasas máximas");
                System.out.println("4-Cambiar Proteinas máximas");
                System.out.println("5-Añadir alimento");
                System.out.println("6-Salir");
                option = getOption(1, 6);
                switch (option) {
                    case 1:
                        updateDiet(selectedDiet, MAXCALORIES);
                        break;
                    case 2:
                        updateDiet(selectedDiet, MAXCARBS);
                        break;
                    case 3:
                        updateDiet(selectedDiet, MAXFATS);
                        break;
                    case 4:
                        updateDiet(selectedDiet, MAXPROTEINS);
                        break;
                    case 5:
                        addFoodMenu();
                        //TODO hacer que el metodo addFoodMenu reciba por
                        // TODO parámetro una dieta aparte del o que ya recibe
                        break;
                    case 6:
                        System.out.println("Saliendo del menú de modificación de dietas");
                        break;
                }

            } while (option != 6);
        }
    }

    private void createDiet() {
        System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
        System.out.println("Crear/reiniciar dieta");
        System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
        System.out.println("Escriba una opción:");
        System.out.println("===================================");
        System.out.println("1-Dieta sin límite");
        System.out.println("2-Dieta por calorías");
        System.out.println("3-Dieta por macronutrientes");
        System.out.println("4-Dieta por datos personales");
        Integer option = Kb.getOption(1, 4);
        switch (option) {
            case 1:
                System.out.println("Nombre la dieta");
                String dietName = Kb.nextLine();
                this.diet = new Diet(dietName);
                System.out.println("Se ha creado una dieta sin límites");
                break;
            case 2:
                System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
                System.out.println("Escriba número de calorías");
                System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
                Integer calories = Kb.forceNextInt();
                System.out.println("Nombre la dieta");
                String dietName2 = Kb.nextLine();
                this.diet = new Diet(dietName2, calories);
                System.out.println("Se ha creado una dieta con " + calories + " calorías máximas");
                break;
            case 3:
                System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
                System.out.println("Escriba los macronutrientes");
                System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
                System.out.println("Carbohidratos:");
                Integer carbs = Kb.forceNextInt();
                System.out.println("Grasas:");
                Integer fats = Kb.forceNextInt();
                System.out.println("Proteínas:");
                Integer proteins = Kb.forceNextInt();
                System.out.println("Nombre la dieta");
                String dietName3 = Kb.nextLine();
                this.diet = new Diet(dietName3, fats, carbs, proteins);
                System.out.println("Se ha creado una dieta con Carbohidratos:" + carbs + ", Grasas:" + fats + " ,Proteínas:" + proteins);
                break;
            case 4:
                System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
                System.out.println("Escriba los datos personales del paciente");
                System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
                System.out.println("Peso:");
                Integer weight = Kb.forceNextInt();
                System.out.println("Altura:");
                Integer height = Kb.forceNextInt();
                System.out.println("Edad:");
                Integer age = Kb.forceNextInt();
                System.out.println("Mujer u Hombre(m/h):");
                String sexCharacter = Kb.nextLine();
                System.out.println("Nombre la dieta");
                String dietName4 = Kb.nextLine();
                this.diet = new Diet(dietName4, Genre.getByString(sexCharacter), age, height, weight);
                System.out.println("Se ha creado una dieta de " + this.diet.getMaxCalories() + " calorías máximas");
                break;
        }
    }

    private void updateDiet(Diet selectedDiet, String field) {
        System.out.println("Escriba un nuevo valor");
        Integer newValue = Kb.forceNextInt();
        if (MAXCALORIES.equalsIgnoreCase(field)) {
            selectedDiet.setMaxCalories(newValue);
        } else if (MAXFATS.equalsIgnoreCase(field)) {
            selectedDiet.setMaxFats(newValue);
        } else if (MAXCARBS.equalsIgnoreCase(field)) {
            selectedDiet.setMaxCarbs(newValue);
        } else if (MAXPROTEINS.equalsIgnoreCase(field))
            selectedDiet.setMaxProteins(newValue);
    }

    private void deleteDiet() {
        System.out.println("Escoja dieta para eliminar");
        Diet selected = getSelectedDiet();
        if (selected == null) {
            System.out.println("Operación cancelada");
        } else {
            if (!dietList.remove(selected)) {
                System.out.println("Error! No se ha encontrado el elemento");
            }
            selected = null;
        }

    }

    private Diet getSelectedDiet() {
        System.out.println("Lista de dietas");
        for (int i = 0; i < dietList.size(); i++) {
            System.out.println((i + 1) + "-" + dietList.get(i).getDietName());
        }
        System.out.println((dietList.size() + 1) + "-Salir");
        Integer selection = Kb.getOption(1, dietList.size() + 1);
        if (selection == dietList.size() + 1) {
            return null;
        }
        return dietList.get(selection - 1);

    }

    private void addFoodMenu() {
        if (this.diet == null) {
            System.out.println("Para agregar alimentos hace falta iniciar una dieta");
            return;
        }
        System.out.println("████████████████████████████████████");
        System.out.println("Agregar Alimentos a la dieta");
        System.out.println("████████████████████████████████████");
        System.out.println("Escriba una opción:");
        System.out.println("===================================");
        System.out.println("1-Agregar un nuevo alimento");
        System.out.println("2-Agregar un alimento ya existente");

        Integer option = getOption(1, 2);
        switch (option) {
            case 1:
                System.out.println("████████████████████████████████████");
                System.out.println("Datos de nuevo alimento");
                System.out.println("████████████████████████████████████");
                System.out.println("Nombre del alimento:");
                String name = Kb.nextLine();
                System.out.println("Carbohidratos:");
                Integer carbs = Kb.forceNextInt();
                System.out.println("Grasas:");
                Integer fats = Kb.forceNextInt();
                System.out.println("Proteínas:");
                Integer proteins = Kb.forceNextInt();
                System.out.println("Gramos:");
                Integer grams = Kb.forceNextInt();
                Food newFood = new Food(name, carbs, fats, proteins);
                validateAndAddFoodToDiet(newFood, grams);
                foodList.add(newFood);
                break;
            case 2:
                if (foodList.size() == 0) {
                    System.out.println("Para agregar un alimento existente, tienen que existir alimentos previos");
                    return;
                }
                System.out.println("████████████████████████████████████");
                System.out.println("Escoja un alimento");
                System.out.println("████████████████████████████████████");
                Integer i = 1;
                for (Food food : foodList) {
                    System.out.println(i + "- " + food.getName());
                    i++;
                }
                System.out.println(i + "- Cancelar");
                Integer element = getOption(1, i);
                if (element == i) {
                    System.out.println("Cancelando alimento");
                    return;
                }
                Food storedFood = foodList.get(element - 1);
                System.out.println("indique el número de gramos de " + storedFood.getName());
                Integer foodGrams = Kb.forceNextInt();
                validateAndAddFoodToDiet(storedFood, foodGrams);
                break;
        }
    }

    private void validateAndAddFoodToDiet(Food food, Integer grams) {
        try {
            this.diet.addFood(food, grams);
        } catch (MaxCaloriesReachedException ecal) {
            System.out.println("Se ha alcanzado el máximo valor de calorías permitido");
        } catch (MaxCarbsReachedException ecar) {
            System.out.println("Se ha alcanzado el máximo valor de carbohidratos permitido");
        } catch (MaxFatsReachedException efat) {
            System.out.println("Se ha alcanzado el máximo valor de grasas permitido");
        } catch (MaxProteinsReachedException epro) {
            System.out.println("Se ha alcanzado el máximo valor de proteínas permitido");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void showDetailsMenu(Diet showdiet) {
        if (showdiet != null) {
            System.out.println("████████████████████████████████████");
            System.out.println("Detalles de la dieta");
            System.out.println("████████████████████████████████████");
            if (showdiet.getMaxCalories() != null) {
                System.out.println("El número máximo de calorías es:" + showdiet.getMaxCalories());
            }
            if (showdiet.getMaxCarbs() != null || showdiet.getMaxFats() != null || showdiet.getMaxProteins() != null) {
                System.out.println("Los valores máximos de macronutrientes son: Carbohidratos:" + showdiet.getMaxCarbs() + " , Grasas:" + showdiet.getMaxFats() + " , Proteinas:" + showdiet.getMaxProteins());
            }
            System.out.println("Dieta:" + showdiet.getDietName());
            System.out.println("Número de alimentos de la dieta:" + showdiet.getFoodNumber());
            System.out.println("Calorías:" + showdiet.getTotalCalories());
            System.out.println("Carbohidratos:" + showdiet.getTotalCarbs());
            System.out.println("Grasas:" + showdiet.getTotalFats());
            System.out.println("Proteínas:" + showdiet.getTotalProteins());
            System.out.println("Alimentos de la dieta:" + showdiet.getDietIntakes());
        } else {
            System.out.println("████████████████████████████████████");
            System.out.println("La dieta no esta iniciada");
            System.out.println("████████████████████████████████████");
        }
    }




    /*

    private void createMenu() {
        System.out.println("████████████████████████████████████");
        System.out.println("Crear dieta");
        System.out.println("████████████████████████████████████");
        System.out.println("Escriba un nombre para la dieta");
        String dietName = null;
        boolean dietExist = false;
        do {
            dietName = Kb.nextLine();
            dietExist = dietList.containsKey(dietName);
            if (dietExist) {
                System.out.println("El nombre de la dieta ya existe, escriba otro");
            }
        } while (dietExist);
        System.out.println("Escriba una opción:");
        System.out.println("===================================");
        System.out.println("1-Dieta sin límite");
        System.out.println("2-Dieta por calorías");
        System.out.println("3-Dieta por macronutrientes");
        System.out.println("4-Dieta por datos personales");
        Integer option = getOption(1, 4);
        switch (option) {
            case 1:
                dietList.put(dietName, new Diet());
                System.out.println("Se ha creado una dieta sin límites");
                break;
            case 2:
                System.out.println("████████████████████████████████████");
                System.out.println("Escriba número de calorias");
                System.out.println("████████████████████████████████████");
                Integer calories = Kb.forceNextInt();
                dietList.put(dietName, new Diet(calories));
                System.out.println("Se ha creado una dieta con " + calories + " calorías máximas");
                break;
            case 3:
                System.out.println("████████████████████████████████████");
                System.out.println("Escriba los macronutrientes");
                System.out.println("████████████████████████████████████");
                System.out.println("Carbohidratos:");
                Integer carbs = Kb.forceNextInt();
                System.out.println("Grasas:");
                Integer fats = Kb.forceNextInt();
                System.out.println("Proteínas:");
                Integer proteins = Kb.forceNextInt();
                dietList.put(dietName, new Diet(fats, carbs, proteins));

                System.out.println("Se ha creado una dieta con Carbohidratos:" + carbs + ", Grasas:" + fats + " ,Proteínas:" + proteins);
                break;
            case 4:
                System.out.println("████████████████████████████████████");
                System.out.println("Escriba los datos personales del paciente");
                System.out.println("████████████████████████████████████");
                System.out.println("Peso:");
                Integer weight = Kb.forceNextInt();
                System.out.println("Altura:");
                Integer height = Kb.forceNextInt();
                System.out.println("Edad:");
                Integer age = Kb.forceNextInt();
                System.out.println("Mujer u Hombre(m/h):");
                String sexCharacter = Kb.nextLine();
               /* //TODO integrar a los clientes
                this.diet = new Diet("m".equalsIgnoreCase(sexCharacter), age, height, weight);
                System.out.println("Se ha creado una dieta de " + this.diet.getMaxCalories() + " calorías máximas");
                break;
        }
    }*/




}

