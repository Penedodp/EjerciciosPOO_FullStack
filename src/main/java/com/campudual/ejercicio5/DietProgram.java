package com.campudual.ejercicio5;




import com.campudual.ejercicio5.exceptions.MaxCaloriesReachedException;
import com.campudual.ejercicio5.exceptions.MaxCarbsReachedException;
import com.campudual.ejercicio5.exceptions.MaxFatsReachedException;
import com.campudual.ejercicio5.exceptions.MaxProteinsReachedException;

import java.util.ArrayList;
import java.util.List;

public class DietProgram {

    private Diet diet = null;

    private List<Diet> dietList;

    private List<Food> foodList;

    public DietProgram() {
        foodList = new ArrayList<>();
        dietList = new ArrayList<>();
    }


    public void showMenuProgram() {
        System.out.println("########################################################");
        System.out.println("################# Programa de dietas ###################");
        System.out.println("########################################################");
        Integer option;
        do {
            System.out.println("Escriba una opción:");
            System.out.println("===================================");
            System.out.println("1-Gestion de dietas");
            System.out.println("2-Gestion de pacientes");
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
                        System.out.println("4-volver a inicio");
                        option = Kb.getOption(1, 4);
                        switch (option) {
                            case 1:
                                createMenu();
                                dietList.add(diet);
                                break;
                            case 2:
                                manageDiets();
                                break;
                            case 3:
                                deleteDiet();
                                break;
                        }
                    } while (option != 4);
                    break;
                case 2:
                    customerManager();
                    break;
                case 3:
                    System.out.println("Gracias por usar el programa, hasta pronto :)");
                    break;
            }
        } while (option != 3);
    }



    private Diet getSelectedDiet() {
        System.out.println("Lista de dietas");
        for (int i = 0; i < dietList.size(); i++) {
            System.out.println((i + 1) + "-" + dietList.get(i).getName());
        }
        System.out.println((dietList.size() + 1) + "-Salir");
        Integer selection = Kb.getOption(1, dietList.size() + 1);
        if (selection == dietList.size() + 1) {
            return null;
        }
        return dietList.get(selection - 1);
    }

    private void deleteDiet(){
        //TODO revisar que la dieta no esta asignada a un cliente
        System.out.println("Escoja dieta para eliminar");
        Diet selected = getSelectedDiet();
        if (selected == null){
            System.out.println("operacion cancelada");
        }else{
            if(!dietList.remove(selected)){
                System.out.println("Error! no se ha encontrado el elemento");
            }
        }
    }

    private void createDiet(){




    }





    private void manageDiets() {
        System.out.println("########################################################");
        System.out.println("########### Listado de dietas ###########");
        System.out.println("########################################################");

        Integer option;

        do {
            System.out.println("Escriba una opción:");
            System.out.println("===================================");
            System.out.println("1-Ver detalles de la dieta actual");
            System.out.println("2-Agregar alimentos a la dieta actual");
            System.out.println("3-Regresar al menú anterior");

            option = Kb.getOption(1, 3);

            switch (option) {
                case 1:
                    showDetailsMenu();
                    break;
                case 2:
                    addFoodMenu();
                    break;
            }
        } while (option != 3);
    }


    private void customerManager() {


    }


    private void addFoodMenu() {
        if (this.diet == null) {
            System.out.println("Para agregar alimentos hace falta iniciar una dieta");
            return;
        }
        System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
        System.out.println("Agregar Alimentos a la dieta");
        System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
        System.out.println("Escriba una opción:");
        System.out.println("===================================");
        System.out.println("1-Agregar un nuevo alimento");
        System.out.println("2-Agregar un alimento ya existente");

        Integer option = Kb.getOption(1, 2);
        switch (option) {
            case 1:
                System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
                System.out.println("Datos de nuevo alimento");
                System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
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
                System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
                System.out.println("Escoja un alimento");
                System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
                Integer i = 1;
                for (Food food : foodList) {
                    System.out.println(i + "- " + food.getName());
                    i++;
                }
                System.out.println(i + "- Cancelar");
                Integer element = Kb.getOption(1, i);
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

    private void createMenu() {
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
                this.diet = new Diet("allUWant");
                System.out.println("Se ha creado una dieta sin límites");
                break;
            case 2:
                System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
                System.out.println("Escriba número de calorias");
                System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
                Integer calories = Kb.forceNextInt();
                this.diet = new Diet("maxCalories",calories);
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
                this.diet = new Diet("macroDiet",fats, carbs, proteins);
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
                this.diet = new Diet("Sexdiet",Genre.valueOf(sexCharacter), age, height, weight);
                System.out.println("Se ha creado una dieta de " + this.diet.getMaxCalories() + " calorías máximas");
                break;
        }
    }

    private void showDetailsMenu() {
        if (this.diet != null) {
            System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
            System.out.println("Detalles de la dieta");
            System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
            if (this.diet.getMaxCalories() != null) {
                System.out.println("El número máximo de calorías es:" + this.diet.getMaxCalories());
            }
            if (this.diet.getMaxCarbs() != null || this.diet.getMaxFats() != null || this.diet.getMaxProteins() != null) {
                System.out.println("Los valores máximos de macronutrientes son: Carbohidratos:" + this.diet.getMaxCarbs() + " , Grasas:" + this.diet.getMaxFats() + " , Proteinas:" + this.diet.getMaxProteins());
            }
            System.out.println("Número de alimentos de la dieta:" + this.diet.getFoodNumber());
            System.out.println("Calorías:" + this.diet.getTotalCalories());
            System.out.println("Carbohidratos:" + this.diet.getTotalCarbs());
            System.out.println("Grasas:" + this.diet.getTotalFats());
            System.out.println("Proteínas:" + this.diet.getTotalProteins());
            System.out.println("Alimentos de la dieta:" + this.diet.getDietIntakes());
        } else {
            System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
            System.out.println("La dieta no esta iniciada");
            System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
        }
    }
}
