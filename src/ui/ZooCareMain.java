package ui;

import java.io.EOFException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

import data.DataManager;
import exceptions.*;
import model.Zoo;

/**
 * Description: Main execution class and user interface for the ZooCare
 * application.
 */

public class ZooCareMain {

    // Atributos o Relaciones
    private static Scanner sc = new Scanner(System.in);
    private static Zoo myZoo;

    /**
     * Main entry point of the application.
     * 
     * Initializes the program execution by calling the main menu loop.
     * Command line arguments passed to the application.
     */

    public static void main(String[] args) {
        loadData();
        menu();
    }

    public static void menu() {
        int option = 0;

        do {

            System.out.println("Bienvenido a ZooCare");
            System.out.println("1. Registrar la informacion general del zoologico");
            System.out.println("2. Consultar informacion general de zoologico");
            System.out.println("3. Modificar informacion general del zoologico");
            System.out.println("4. Registrar informacion de un Habitat");
            System.out.println("5. Consultar informacion de los habitats");
            System.out.println("6. Modificar informacion de un habitat");
            System.out.println("7. Registrar informacion de un animal");
            System.out.println("8. Consultar animales registrados");
            System.out.println("9. Modificar infomracion de un animal");
            System.out.println("10. Transladar un animal");
            System.out.println("11. Remover un animal de un habitat");
            System.out.println("12. Calcular el costo de alimentacion de un animal");
            System.out.println("13. Consolidar Estimaciones de alimentacion");
            System.out.println("14. Registra un empleado");
            System.out.println("15. Consultar informacion de los empleados");
            System.out.println("16. Modificar informacion de un empleado");
            System.out.println("17. Consultar empleados activos en el zoologico");
            System.out.println("18. Consultar informacion completa de los habitats");
            System.out.println("19. Consultar animales activos del zoologico");
            System.out.println("20. Calcular la ocupacion de un Habitat");
            System.out.println("21. Mostrar mapa del zoologico");
            System.out.println("22. Retirar un Habitat");
            System.out.println("23. Agregar una actividad");
            System.out.println("24. Consultar actividades pendientes de un Empleado");
            System.out.println("25. Cancelar una actividad");
            System.out.println("26. Finalizar una actividad");
            System.out.println("27. Transferir un animal a la Clinica Veterinaria");
            System.out.println("28. Desactivar un empleado");
            System.out.println("29. Guardar Estado");
            System.out.println("30. Generar un Resumen Operativo Completo");
            System.out.println("31. Generar un Reporte detallado de un animal");
            System.out.println("32. Generar Reporte de actividades");
            System.out.println("0. Salir");
            option = sc.nextInt();
            switch (option) {
                case 1:
                    registerZooInformation();

                    break;
                case 2:
                    showZooInformacion();
                    break;
                case 3:
                    modifyZoobudget();
                    break;
                case 4:
                    registerHabitat();
                    break;
                case 5:
                    showAllHabitats();
                    break;
                case 6:
                    modifyHabitat();
                    break;
                case 7:
                    registerAnimal();
                    break;
                case 8:
                    showAllAnimals();
                    break;
                case 9:
                    modifyAnimal();
                    break;
                case 10:
                    transferAnimalToHabitat();
                    break;
                case 11:
                    removeAnimal();
                    break;
                case 12:
                    showAnimalMonthlyCost();
                    break;
                case 13:
                    showZooFoodCost();
                    break;
                case 14:
                    registerEmployee();
                    break;
                case 15:
                    showAllEmployee();
                    break;
                case 16:
                    modifyEmployee();
                    break;
                case 17:
                    showActiveEmployees();
                    break;
                case 18:
                    showCompleteHabitats();
                    break;
                case 19:
                    showActiveAnimals();
                    break;
                case 20:
                    showHabitatOccupancy();
                    break;
                case 21:
                    showZooMap();
                    break;
                case 22:
                    removeAHabitat();
                    break;
                case 23:
                    addActivity();
                    break;
                case 24:
                    showAssignActivities();
                    break;
                case 25:
                    removeActivity();
                    break;
                case 26:
                    finishActivity();
                    break;
                case 27: 
                    moveAnimalToVeterinaryClinic();
                    break;
                case 28:
                    desactivateEmployee();
                    break;
                case 29:
                    saveData();
                    break;
                case 30:
                    generateOperativeSummary();
                    break;
                case 31:
                    generateDetailedAnimalReport();
                    break;
                case 32:
                    generateActivitiesReport();
                    break;
                case 0:
                    System.out.println("Adios!");
                    break;

                default:
                    break;

            }

        } while (option != 0);
    }

    /**
     * Registers the general information of the zoo.
     * 
     * -Description: The user for zoo details (name, city, address, legal
     * representative data,
     * and budget)
     * -Precondition: The global variable myZoo must be null. User must input valid
     * values
     * (budget greater than 0).
     * -Postcondition: The variable Zoo is created and assigned to myZoo.
     */

    public static void registerZooInformation() {

        if (myZoo == null) {

            sc.nextLine();

            System.out.println("Digete el nombre del zoologico");
            String name = sc.nextLine();

            System.out.println("Digita la ciudad del zoologico");
            String city = sc.nextLine();

            System.out.println("Digite la direccion del zoologico");
            String addres = sc.nextLine();

            System.out.println("Digita la cedula del representante legal del zoologico");
            int legalRepresentativeCode = sc.nextInt();
            sc.nextLine();

            System.out.println("Digita el nombre del representante legal del zoologico");
            String legalRepresentativeName = sc.nextLine();

            System.out.println("Digite el presupuesto del zoologico");
            double budget = sc.nextDouble();
            while (budget <= 40000000) {
                System.out.println("Ingresa un presupuesto mayor a 40000000");
                budget = sc.nextDouble();
            }

            myZoo = new Zoo(name, city, addres, legalRepresentativeCode, legalRepresentativeName, budget);
        } else {
            System.out.println("Ya existe un zoologico registrado");
        }
    }

    /**
     * Displays the general information of the zoo.
     * 
     * -Description: Prints the details of the zoo if it has been previously
     * initialized.
     * -Precondition: The zoo must be initialized (`myZoo != null`).
     * -Postcondition: The zoo details are printed to the console standard output.
     */

    public static void showZooInformacion() {

        if (myZoo != null) {
            System.out.println(myZoo.getInformation());
        } else {
            System.out.println("Zoologico aun no inicializado");
        }
    }

    /**
     * Modifies the overall budget of the zoo.
     * 
     * -Description: The user will enter in the terminal for a new budget amount and
     * updates the zoo's budget attribute.
     * -Precondition The zoo must be initialized (`myZoo != null`).
     * -Postcondition: The zoo's budget property is updated with the new value
     * provided by the user.
     */

    public static void modifyZoobudget() {

        if (myZoo != null) {
            System.out.println("Digite el nuevo presupuesto del zoologico");
            double budget = sc.nextDouble();

            myZoo.setbudget(budget);
        } else {
            System.out.println("Zoologico aun no inicializado");
        }

    }

    /**
     * Registers a new habitat within the zoo.
     * 
     * -Description: Requests habitat details (ID, name, environment type,
     * temperature, area, max capacity,
     * budget, and state) from the user, validating that the budget does not exceed
     * constraints,
     * and adds it to the zoo.
     * -Precondition The zoo must be initialized (`myZoo != null`). Area must be
     * greater than 0.
     * Habitat budget cannot exceed the zoo budget or total available habitat budget
     * pool.
     * -Postcondition A new habitat is successfully registered and added to the
     * zoo's habitat collection.
     */

    public static void registerHabitat() {
        if (myZoo != null) {

            sc.nextLine();

            System.out.println("Digita el codigo identificador del habitat");
            String HabitatId = sc.nextLine();

            System.out.println("Digete el nombre del habitat");
            String name = sc.nextLine();

            System.out.println("Seleccione el ambiente del habitat");
            System.out.println(myZoo.getEnvironmentTypeList());
            int environment = sc.nextInt();

            System.out.println("Digita la temperatura del habitat");
            double temperature = sc.nextDouble();

            System.out.println("Digite el area del habitat");
            double area = sc.nextDouble();
            while (area <= 0) {
                System.out.println("Ingresa un area mayor a 0");
                area = sc.nextDouble();
            }

            System.out.println("Digite la capacidad maxima del habitat");
            int maxCapacity = sc.nextInt();

            System.out.println("Ingresa el presupuesto del habitat");
            double budget = sc.nextDouble();
            while (budget > myZoo.getTotalHabitatsBudget() || budget > myZoo.getBudget()) {
                if (budget > myZoo.getBudget()) {
                    System.out.println("Ingresa un presupuesto menor al del zoologico");
                    budget = sc.nextDouble();
                }
                if (budget > myZoo.getTotalHabitatsBudget()) {
                    System.out.println("Ingresa un presupuesto menor al total del zoologico");
                    budget = sc.nextDouble();
                }
            }

            System.out.println("Ingresa el estado del habitat");
            System.out.println(myZoo.getStateTypeList());
            int state = sc.nextInt();

            sc.nextLine();

            boolean result = myZoo.addHabitat(HabitatId, name, environment, temperature, area, maxCapacity, budget,
                    state);

            if (result) {
                System.out.println("Extio! Habitat registrado");
                System.out.println("Deseas Ingresar un Empleado (Si/No)?");
                String answer = sc.nextLine();

                if (answer.equals("si")) {
                    System.out.println(myZoo.getAllEmployeeList());
                    System.out.println("Ingresa el codigo de empleado que deseas agregar: ");
                    String employeeID = sc.nextLine();
                    boolean result1 = myZoo.assignEmployeeToHabitat(HabitatId, employeeID);

                    if (result1) {
                        System.out.println("Se ha agregado el empleado al habitat");
                    } else {
                        System.out.println("Error! No se pudo agregar el empleado");
                    }
                }
            } else {
                System.out.println("Error. Habitat no registrado");
            }

        } else {
            System.out.println("Zoologico aun no inicializado");
        }

    }

    /**
     * Displays a list of all registered habitats.
     * 
     * -Description: Checks if there are available habitats and prints their summary
     * list.
     * -Precondition: The zoo must be initialized (`myZoo != null`).
     * -Postcondition: Prints the list of habitats to the console if available.
     */

    public static void showAllHabitats() {
        if (myZoo != null) {

            if (myZoo.hasAvialableHabitat()) {

                System.out.println("La lista de habitats es:");
                System.out.println(myZoo.getHabitatList());

            } else {
                System.out.println("Aun no hay habitats registrados");
            }

        } else {
            System.out.println("Zoologico aun no inicializado");
        }

    }

    /**
     * Modifies the information of an existing habitat.
     * 
     * -Description: Prompts the user for a habitat ID and new attributes (name,
     * temperature, area,
     * max capacity, budget, and state) to update the specified habitat.
     * -Precondition: The zoo must be initialized and contain registered habitats.
     * New area must be > 0.
     * New budget must comply with zoo budget limits.
     * -Postcondition: The target habitat properties are updated with the new
     * user-provided values.
     */

    public static void modifyHabitat() {
        if (myZoo != null) {
            String habitatlist = myZoo.getHabitatList();

            if (habitatlist != null) {

                sc.nextLine();
                System.out.println("Los hábitats registrados son:");
                System.out.println(myZoo.getHabitatList());

                System.out.println("Digita el código identificador del hábitat que deseas modificar:");
                String habitatId = sc.nextLine();

                System.out.println("Digita el nuevo nombre del hábitat:");
                String newName = sc.nextLine();

                System.out.println("Digita la nueva temperatura del hábitat:");
                double newTemperature = sc.nextDouble();

                System.out.println("Digita la nueva capacidad máxima del hábitat:");
                int newMaxCapacity = sc.nextInt();

                sc.nextLine();

                System.out.println("Ingresa el nuevo estado del hábitat:");
                System.out.println(myZoo.getStateTypeList());
                int newState = sc.nextInt();

                boolean result = myZoo.setHabitat(habitatId, newName, newTemperature, newMaxCapacity, newState);

                if (result) {
                    System.out.println("Se modificaron los nuevos valores con exito");
                } else {
                    System.out.println("Error! No se pudo modificar el habitat");
                }

            } else {
                System.out.println("Error! Aun no hay habitats registrados");
            }

        } else {
            System.out.println("Zoologico aun no inicializado");
        }
    }

    /**
     * Registers a new animal within a specific habitat.
     * 
     * -Description: Gathers animal details (ID, name, species, gender, weight, diet
     * type, required environment,
     * life stage, health status, and status) and assigns it to a chosen habitat.
     * -Precondition: Habitats must be registered in the zoo. Weight must be greater
     * than 0.
     * -Postcondition: A new animal instance is created and added to the specified
     * habitat if compatibility
     * and capacity rules are met.
     */

    public static void registerAnimal() {
        if (myZoo != null) {
            String habitatlist = myZoo.getHabitatList();

            if (!habitatlist.equals("")) {

                sc.nextLine();

                System.out.println("Digita el codigo del animal");
                String AnimalId = sc.nextLine();

                System.out.println("\nDigite el nombre del animal");
                String name = sc.nextLine();

                System.out.println("Digita el tipo de especie del animal");
                String specie = sc.nextLine();

                System.out.println("Digita el tipo de genero del animal");
                System.out.println(myZoo.getGenderTypeList());
                int gender = sc.nextInt();

                System.out.println("\nDigite el peso del animal");
                double weight = sc.nextDouble();
                while (weight <= 0) {
                    System.out.println("Ingresa un peso mayor que 0");
                    weight = sc.nextDouble();
                }

                sc.nextLine();

                System.out.println("\nDigite el tipo de dieta del animal");
                System.out.println(myZoo.getdietTypeList());
                int dietType = sc.nextInt();

                System.out.println("Digita el tipo de ambiente requerido para el animal");
                System.out.println(myZoo.getEnvironmentTypeList());
                int requiereEnvironment = sc.nextInt();

                System.out.println("\nDigite la etapa de vida del animal (juvenil o adulto)");
                System.out.println(myZoo.getLifeStageList());
                int lifeStage = sc.nextInt();

                System.out.println("\nDigite el estado de salud del animal");
                System.out.println(myZoo.getHealthStatusList());
                int healthStatus = sc.nextInt();
                sc.nextLine();

                // Habitat
                System.out.println("A continuacion se presenta el listado de habitats registrados:");
                System.out.println(myZoo.getHabitatList());

                System.out.println("Digite el codigo del habitat al que se registrara el animal");
                String habitatName = sc.nextLine();

                boolean result = myZoo.addAnimalInHabitat(habitatName, AnimalId, name, specie, gender, weight, dietType,
                        requiereEnvironment, lifeStage, healthStatus);

                if (result) {
                    System.out.println("Animal registrado exitosamente");
                } else {
                    System.out.println("Error! No se pudo registrar el animal");
                }

            } else {
                System.out.println("Error! Habitats aun no registrados");
            }

        } else {
            System.out.println("Zoologico aun no inicializado");
        }
    }

    /**
     * Displays a list of all registered animals across all habitats.
     * 
     * -Description: Retrieves and prints the consolidated list of animals present
     * in the zoo.
     * -Precondition: The zoo must be initialized and contain valid habitats.
     * -Postcondition: Prints the list of all registered animals to the console.
     */

    public static void showAllAnimals() {
        if (myZoo != null) {

            if (myZoo.hasAvialableHabitat()) {

                System.out.println("La lista de animales es:\n" + myZoo.getAllAnimalsList());

            } else {
                System.out.println("Error! habitats aun no registrados");
            }

        } else {
            System.out.println("Zoologico aun no inicializado");
        }
    }

    /**
     * Modifies the information of an existing animal.
     * 
     * -Description: Prompts for an animal ID and updates its weight, diet type,
     * life stage, and health status.
     * -Precondition: Animals must be registered in the zoo. New weight must be
     * greater than 0.
     * -Postcondition The specified animal attributes are updated with new values.
     */

    public static void modifyAnimal() {
        if (myZoo != null) {

            String animalsList = myZoo.getAllAnimalsList();

            if (animalsList != null) {

                sc.nextLine();

                System.out.println("Los animales registrados en el zoológico son:");
                System.out.println(animalsList);

                System.out.println("Digita el código identificador del animal que deseas modificar:");
                String animalId = sc.nextLine();

                System.out.println("Digita el nuevo peso del animal:");
                double newWeight = sc.nextDouble();
                while (newWeight <= 0) {
                    System.out.println("Ingresa un peso mayor a 0");
                    newWeight = sc.nextDouble();
                }

                System.out.println("Selecciona el nuevo tipo de dieta del animal:");
                System.out.println(myZoo.getdietTypeList());
                int newDietType = sc.nextInt();

                System.out.println("Selecciona la nueva etapa de vida del animal:");
                System.out.println(myZoo.getLifeStageList());
                int newLifeStage = sc.nextInt();

                System.out.println("Selecciona el nuevo estado de salud del animal:");
                System.out.println(myZoo.getHealthStatusList());
                int newHealthStatus = sc.nextInt();

                System.out.println("Selecciona el estado del animal");
                System.out.println(myZoo.getAnimalStatusTypeList());
                int newAnimalState = sc.nextInt();

                sc.nextLine();

                boolean result = myZoo.setAnimal(animalId, newWeight, newDietType, newLifeStage, newHealthStatus,
                        newAnimalState);

                if (result) {
                    System.out.println("¡Éxito! Los datos del animal se han modificado correctamente.");
                } else {
                    System.out.println("Error. No se pudo encontrar un animal con ese código en el zoológico.");
                }
            } else {
                System.out.println("Aun no hay animales registrados");
            }

        } else {
            System.out.println("Zoologico aun no inicializado");
        }

    }

    /**
     * Transfers an animal from its current habitat to a target habitat.
     * 
     * -Description: Asks for an animal ID and a destination habitat ID, then
     * processes the relocation.
     * -Precondition: Animals and target habitats must exist within the zoo system.
     * -Postcondition: The animal is moved to the target habitat if capacity and
     * environmental compatibility allow it.
     */

    public static void transferAnimalToHabitat() {
        if (myZoo != null) {

            String animalsList = myZoo.getAllAnimalsList();
            sc.nextLine();

            if (!animalsList.equals("")) {

                System.out.println("Lista de animales");
                System.out.println(myZoo.getAllAnimalsList());
                System.out.println("Digita el codigo del animal que deseas transferir");
                String animalID = sc.nextLine();

                System.out.println("Lista de habitats");
                System.out.println(myZoo.getHabitatList());
                System.out.println("Digita el codigo del habitat al que deseas transferir el animal");
                String HabitatId = sc.nextLine();

                boolean result = myZoo.transferAnimal(animalID, HabitatId);

                if (result) {
                    System.out.println("Animal transferido con exito");
                } else {
                    System.out.println("Error! No se pudo transferir el animal");
                }

            } else {
                System.out.println("No hay animales registrados");
            }

        } else {
            System.out.println("Zoologico aun no inicializado");
        }

    }

    /**
     * Retires or removes an animal from a habitat.
     * 
     * -Description: Gathers retirement details (animal ID, reason, status type, and
     * date) and removes
     * the animal from active habitat tracking.
     * -Precondition Animal must exist in the system.
     * -Postcondition The animal status is updated with retirement data and removed
     * from its habitat list.
     */

    public static void removeAnimal() {

        if (myZoo != null) {
            String animalsList = myZoo.getAllAnimalsList();

            if (animalsList != null) {
                sc.nextLine();

                System.out.println("Lista de animales:");
                System.out.println(animalsList);

                System.out.println("Digita el codigo del animal que va a ser retirado:");
                String animalId = sc.nextLine();

                System.out.println("Digita el motivo de retiro:");
                String reason = sc.nextLine();

                System.out.println("Digita el estado del animal al retirarse:");
                System.out.println(myZoo.getAnimalStatusTypeList());
                int status = sc.nextInt();

                LocalDate date = LocalDate.now();

                boolean result = myZoo.retireAnimal(animalId, reason, status, date);

                if (result) {
                    System.out.println("¡Exito! El animal ha sido retirado del sistema correctamente.");
                } else {
                    System.out.println("Error. No se pudo encontrar el animal o procesar el retiro.");
                }

            } else {
                System.out.println("No hay animales registrados.");
            }
        } else {
            System.out.println("Zoologico aún no inicializado.");
        }
    }

    /**
     * Calculates and displays the monthly food cost for a specific animal.
     * 
     * -Description: Requests an animal name and prints its calculated monthly
     * feeding cost.
     * -Precondition: Animals must be registered in the zoo.
     * -Postcondition: Computes and prints the monetary cost to the console.
     */

    public static void showAnimalMonthlyCost() {
        showAllAnimals();

        if (myZoo != null) {

            sc.nextLine();
            System.out.println("\nDigite el codigo del animal a calcular su costo");
            String name = sc.nextLine();

            System.out.println("El costo mensual de " + name + " es: $" + myZoo.getAnimalInHabitatMonthlyCost(name));

        } else {
            System.out.println("Zoologico aun no inicializado");
        }

    }

    /**
     * Registers a new employee in the zoo.
     * 
     * -Description: Prompts for employee information (ID, name, number, email,
     * role, and state) and adds them.
     * -Precondition: The zoo must be initialized (`myZoo != null`).
     * -Postcondition: A new employee object is created and stored in the zoo's
     * employee collection.
     */

    public static void registerEmployee() {

        if (myZoo != null) {

            sc.nextLine();
            System.out.println("Digita el codigo de empleado");
            String employeeID = sc.nextLine();

            System.out.println("Digita el nombre del empleado");
            String name = sc.nextLine();

            System.out.println("Digita el numero del empleado");
            int number = sc.nextInt();
            sc.nextLine();

            System.out.println("Ingresa el correo electronico del empleado");
            String email = sc.nextLine();

            System.out.println("Digita el rol del empleado");
            System.out.println(myZoo.getRoleTypeList());
            int role = sc.nextInt();

            boolean result = myZoo.addEmployee(employeeID, name, number, email, role);

            if (result) {
                System.out.println("Exito! Se ha registrado el empleado");
            } else {
                System.out.println("Error! No se ha registrado el empleado");
            }
        } else {
            System.out.println("El zoologico aun no se ha inicializado");
        }
    }

    /**
     * Displays a list of all registered employees.
     * 
     * -Description: Prints the complete roster of employees currently registered in
     * the zoo.
     * -Precondition: The zoo must be initialized (`myZoo != null`).
     * -Postcondition: Prints employee summary details to the console.
     */

    public static void showAllEmployee() {
        if (myZoo != null) {
            System.out.println("Los empleados registrados son");
            System.out.println(myZoo.getAllEmployeeList());
        } else {
            System.out.println("El zoologico aun no se ha inicializado");
        }

    }

    /**
     * Modifies the information of an existing employee.
     * 
     * -Description: Prompts for an employee ID and updates their contact info,
     * role, and state.
     * -Precondition: Employees must be registered in the system.
     * -Postcondition The target employee attributes are updated with new values.
     */

    public static void modifyEmployee() {
        if (myZoo != null) {
            sc.nextLine();
            System.out.println("Digita el codigo de empleado que deseas cambiar la informacion");
            System.out.println(myZoo.getAllEmployeeList());
            String employeeID = sc.nextLine();

            System.out.println("Digita el nombre del empleado");
            String name = sc.nextLine();

            System.out.println("Digita el numero del empleado");
            int number = sc.nextInt();
            sc.nextLine();

            System.out.println("Ingresa el correo electronico del empleado");
            String email = sc.nextLine();

            System.out.println("Digita el rol del empleado");
            System.out.println(myZoo.getRoleTypeList());
            int role = sc.nextInt();

            System.out.println("Digita el estado del empleado");
            System.out.println(myZoo.getEmployeeStateList());
            int state = sc.nextInt();

            boolean result = myZoo.setEmployee(employeeID, name, number, email, role, state);

            if (result) {
                System.out.println("Los datos del empleado se han cambiado con exito");
            } else {
                System.out.println("Error! No se pudo cambiar los datos del empleado");
            }
        } else {
            System.out.println("El zoologico aun no se ha inicializado");
        }
    }

    /**
     * Displays consolidated food cost estimates for the entire zoo.
     * 
     * -Description: Generates and prints a full breakdown report of feeding costs
     * per habitat and total zoo cost.
     * -Precondition: The zoo must be initialized and contain habitats/animals.
     * -Postcondition Prints the complete food cost report to the console.
     */

    public static void showZooFoodCost() {
        if (myZoo != null) {

            System.out.println("\n=== Reporte de Costos de Alimentacion ===");
            System.out.println(myZoo.getCompleteFoodCostReport());

        } else {
            System.out.println("Zoológico aun no inicializado.");
        }

    }

    /**
     * Displays the occupancy rate for a specific habitat.
     * 
     * -Description: Prompts for a habitat ID and outputs its occupancy statistics
     * and thresholds.
     * -Precondition: Habitats must be registered in the zoo.
     * -Postcondition Prints occupancy metrics for the selected habitat.
     */

    public static void showHabitatOccupancy() {
        if (myZoo != null) {
            String habitatList = myZoo.getHabitatList();

            if (habitatList != null) {
                sc.nextLine();

                System.out.println("Los habitats registrados son:");
                System.out.println(myZoo.getHabitatList());

                System.out.println("Digita el codigo identificador del habitat al que deseas calcular la ocupacion:");
                String habitatId = sc.nextLine();

                String result = myZoo.getHabitatsOccupancy(habitatId);

                if (result != null) {
                    System.out.println("\n=== Ocupacion del Habitat ===");
                    System.out.println(result);

                } else {
                    System.out.println("Error. No se encontro ningun habitat con ese codigo.");
                }

            } else {
                System.out.println("Aun no hay hábitats registrados.");
            }

        } else {
            System.out.println("El zooogico aun no se ha inicializado.");
        }
    }

    /**
     * Displays a list of currently active employees.
     * 
     * -Description: Filters and prints all zoo personnel whose state is set to
     * ACTIVE.
     * -Precondition Employees must be registered in the zoo.
     * -Postcondition Prints the list of active employees to the console.
     */

    public static void showActiveEmployees() {
        if (myZoo != null) {
            String activeEmployees = myZoo.getActiveEmployeesList();

            if (!activeEmployees.equals("")) {
                System.out.println("\n=== Personal Activo del Zoologico ===");
                System.out.println(myZoo.getActiveEmployeesList());
            } else {
                System.out.println("No hay empleados activos registrados en este momento.");
            }
        } else {
            System.out.println("El zoologico aun no se ha inicializado.");
        }
    }

    /**
     * Displays comprehensive details of all habitats.
     * 
     * -Description: Generates a full report including habitat attributes, assigned
     * employees, and animal occupants.
     * -Precondition: Habitats must be registered in the zoo.
     * -Postcondition Prints the complete habitat report to the console.
     */

    public static void showCompleteHabitats() {
        if (myZoo != null) {
            String habitatList = myZoo.getHabitatList();
            if (habitatList != null) {

                String report = myZoo.getAllCompleteHabitatsReport();

                if (report != null) {
                    System.out.println("\n=== Reporte de todos los Habitats ===");
                    System.out.println(report);

                } else {
                    System.out.println("No hay informacion de habitats disponible.");
                }
            } else {
                System.out.println("Aun no hay habitats registrados.");
            }
        } else {
            System.out.println("El zoologico aun no se ha inicializado.");
        }
    }

    /**
     * Displays a list of all active animals in the zoo.
     * 
     * -Description: Retrieves and prints a report of all animals whose state is
     * active across all habitats.
     * -Precondition: Animals must be registered in the system.
     * -Postcondition: Prints the list of active animals grouped by habitat to the
     * console.
     */

    public static void showActiveAnimals() {
        if (myZoo != null) {
            String activeAnimals = myZoo.getActiveAnimalsReport();

            if (activeAnimals != null && !activeAnimals.isEmpty()) {
                System.out.println("\n=== Animales Activos del Zoologico ===");
                System.out.println(activeAnimals);
            } else {
                System.out.println("No hay animales activos registrados en este momento.");
            }
        } else {
            System.out.println("El zoologico aun no se ha inicializado.");
        }
    }

    /**
     * Displays the map of the zoo.
     *
     * Preconditions:
     * - The `myZoo` object must be initialized (not null).
     *
     */
    public static void showZooMap() {
        if (myZoo != null) {
            System.out.println("Mapa del Zoologico:");
            System.out.println(myZoo.getZooMap());
        } else {
            System.out.println("Error! Zoologico aun no registrado");
        }

    }

    public static void removeAHabitat() {
        if (myZoo != null) {

            sc.nextLine();

            System.out.println("Ingresa el codigo del habitat que deseas retirar");
            System.out.println(myZoo.getHabitatList());
            String habitatID = sc.nextLine();

            if (myZoo.isActive(habitatID)) {
                System.out.println("Se tiene que cambiar el estado del Habitat para retirarlo");
                System.out.println("Cambiar estado (si/no)?");
                String response = sc.nextLine();
                if (response.equals("si")) {

                    System.out.println("Ingresa el estado");
                    System.out.println(myZoo.getStateTypeList());
                    int newState = sc.nextInt();

                    boolean change = myZoo.setHabitatState(habitatID, newState);

                    if (change) {
                        System.out.println("Se cambio el estado exitosamente");

                    } else {
                        System.out.println("No se pudo cambiar el estado");
                    }

                }

                boolean result = myZoo.removeHabitatInMap(habitatID);
                if (result) {
                    System.out.println("Se removio exitosamente el habitat del mapa");
                } else {
                    System.out.println("Error no se pudo remover el habitat");
                }

            }

        } else {
            System.out.println("El zoologico aun no se ha inicializado.");
        }
    }

    public static void addActivity() {
        if (myZoo != null) {

            if (!myZoo.getHabitatList().equals("")) {

                try {

                    if (!myZoo.getActiveAnimalsReport().equals("")) {

                        System.out.println("Que tipo de actividad deseas registrar?");
                        System.out.println(
                                "1. Actividad de Alimentacion\n2. Actividad de Limpieza\n3. Actividad de Enriquecimiento\n4. Actividad de Control Medico");
                        int response = sc.nextInt();

                        sc.nextLine();

                        switch (response) {
                            case 1:
                                System.out.println("Ingresa el habitat donde se va a realizar la actividad");
                                System.out.println(myZoo.getHabitatList());
                                String habitatID = sc.nextLine();

                                System.out.println("Ingresa el codigo del empleado que va a realizar la actividad");
                                System.out.println(myZoo.getActiveEmployeesList());
                                String employeeID = sc.nextLine();

                                System.out.println("Ingresa el dia el cual se debe realizar la Actividad(AAAA-MM-DD)");
                                String schedulingDate = sc.nextLine();
                                LocalDate date = null;

                                try {
                                    date = LocalDate.parse(schedulingDate);
                                } catch (DateTimeParseException e) {
                                    System.out.println("Error: El formato debe ser AAAA-MM-DD (ejemplo: 2026-11-16).");
                                }

                                System.out.println("Ingresa el codigo del animal que se va a realizar la actividad");
                                System.out.println(myZoo.getActiveAnimalsReport());
                                String animalID = sc.nextLine();

                                System.out.println("Ingresa peso del alimento que se va a dar");
                                double weight = sc.nextDouble();

                                boolean result = myZoo.addFeedingActivity(habitatID, employeeID, date, animalID,
                                        weight);

                                if (result) {
                                    System.out.println("Se ha registrado con exito la actividad");
                                } else {
                                    System.out.println("Error. No se pudo registrar la actividad");
                                }

                                break;
                            case 2:
                                System.out.println("Ingresa el habitat donde se va a realizar la actividad");
                                System.out.println(myZoo.getHabitatList());
                                String habitatID2 = sc.nextLine();

                                System.out.println("Ingresa el codigo del empleado que va a realizar la actividad");
                                System.out.println(myZoo.getActiveEmployeesList());
                                String employeeID2 = sc.nextLine();

                                System.out.println("Ingresa el dia el cual se debe realizar la Actividad(AAAA-MM-DD)");
                                String schedulingDate2 = sc.nextLine();
                                LocalDate date2 = null;

                                try {
                                    date = LocalDate.parse(schedulingDate2);
                                } catch (DateTimeParseException e) {
                                    System.out.println("Error: El formato debe ser AAAA-MM-DD (ejemplo: 2026-11-16).");
                                }

                                System.out.println("Ingresa la descripcion de la actividad");
                                String description = sc.nextLine();

                                System.out.println(
                                        "Ingresa los productos utilizados, si es mas se uno separalo con \"-\"");
                                String usedProducts = sc.nextLine();

                                boolean result2 = myZoo.addCleaningActivity(habitatID2, employeeID2, date2, description,
                                        usedProducts);

                                if (result2) {
                                    System.out.println("Se ha registrado la actividad con exito");
                                } else {
                                    System.out.println("Error. No se pudo registrar la actividad");
                                }

                                break;
                            case 3:
                                System.out.println("Ingresa el habitat donde se va a realizar la actividad");
                                System.out.println(myZoo.getHabitatList());
                                String habitatID3 = sc.nextLine();

                                System.out.println("Ingresa el codigo del empleado que va a realizar la actividad");
                                System.out.println(myZoo.getActiveEmployeesList());
                                String employeeID3 = sc.nextLine();

                                System.out.println("Ingresa el dia el cual se debe realizar la Actividad(AAAA-MM-DD)");
                                String schedulingDate3 = sc.nextLine();
                                LocalDate date3 = null;

                                try {
                                    date = LocalDate.parse(schedulingDate3);
                                } catch (DateTimeParseException e) {
                                    System.out.println("Error: El formato debe ser AAAA-MM-DD (ejemplo: 2026-11-16).");
                                }

                                System.out.println("Ingresa la descripcion de la limpieza que se va a hacer");
                                String description1 = sc.nextLine();

                                System.out.println("Ingresa la duracion de dias que va a tener la actividad");
                                int numDays = sc.nextInt();

                                System.out.println("Ingresa los productos utilizados, separandolos con un \"-\"");
                                String usedProducts1 = sc.nextLine();

                                boolean result3 = myZoo.addEnrichmentActivity(habitatID3, employeeID3, date3,
                                        description1, numDays, usedProducts1);

                                if (result3) {
                                    System.out.println("Se ha registrado con exito la actividad");
                                } else {
                                    System.out.println("Error. No se ha podido registrar la actividad");
                                }
                                break;

                            case 4:

                                System.out.println("Ingresa el codigo del empleado que va a realizar la actividad");
                                System.out.println(myZoo.getActiveEmployeesList());
                                String employeeID4 = sc.nextLine();

                                System.out.println("Ingresa el dia el cual se debe realizar la Actividad(AAAA-MM-DD)");
                                String schedulingDate4 = sc.nextLine();
                                LocalDate date4 = null;

                                try {
                                    date = LocalDate.parse(schedulingDate4);
                                } catch (DateTimeParseException e) {
                                    System.out.println("Error: El formato debe ser AAAA-MM-DD (ejemplo: 2026-11-16).");
                                }

                                System.out.println("Ingresa el codigo del animal que se le va a realizar la actividad");
                                System.out.println(myZoo.getActiveAnimalsReport());
                                String animalId = sc.nextLine();

                                System.out.println("Ingresa el peso del animal al crear la actividad");
                                double weight1 = sc.nextDouble();

                                System.out.println("Ingresa las observaciones de la actividad");
                                String observations = sc.nextLine();

                                System.out.println("Se utilizaron medicamentos?");
                                System.out.println("1. Si\n2. No");
                                int option = sc.nextInt();
                                String medications = null;
                                double treatmentCost = 0.0;
                                if (option == 1) {
                                    System.out.println("Ingresa los medicamentos separandolos con \"-\"");
                                    medications = sc.nextLine();

                                    System.out.println("Ingresa el costo de tratamiento");
                                    treatmentCost = sc.nextDouble();
                                }

                                boolean result4 = myZoo.addMedicControlActivity(employeeID4, date4, animalId, weight1,
                                        observations, medications, treatmentCost);

                                if (result4) {
                                    System.out.println("Se ha registrado con exito la actividad");
                                } else {
                                    System.out.println("Error. No se ha podido registrar la actividad");
                                }

                                break;

                            default:
                                break;
                        }

                    } else {
                        System.out.println("Aun no hay animales registrados");
                    }

                } catch (ZooCareBusinessException e) {
                    System.err.println(e.getMessage());
                }

            } else {
                System.out.println("Aun no hay zoologicos registrados");
            }

        } else {
            System.out.println("El zoologico aun no se ha inicializado");
        }
    }

    public static void showAssignActivities() {
        if (myZoo != null) {

            if (myZoo.getActiveActivitiesList() != null) {

                System.out.println("Digita el codigo del empleado que deseas ver la informacion");
                System.out.println(myZoo.getActiveEmployeesList());
                String employeeID = sc.nextLine();

                System.out.println(myZoo.getActivitiesInEmployee(employeeID));

            } else {
                System.out.println("No hay actividades creadas.");
            }

        } else {
            System.out.println("El zoologico aun no se ha inicializado.");
        }
    }

    public static void removeActivity() {
        if (myZoo != null) {

            if (myZoo.getActiveActivitiesList() != null) {

                System.out.println("Ingresa el codigo de la actividad que deseas cancelar");
                System.out.println(myZoo.getActiveActivitiesList());
                String id = sc.nextLine();

                try {
                    boolean result = myZoo.cancelActivity(id);

                    if (result) {
                        System.out.println("La actividad se ha cancelado con exito");
                    } else {
                        System.out.println("Error no se ha podido cancelar la actividad");
                    }
                } catch (ZooCareBusinessException e) {
                    System.err.println(e.getMessage());
                }

            } else {
                System.out.println("No hay actividades creadas");
            }
        } else {
            System.out.println("El zoologico aun no se ha inicializado");
        }
    }

    public static void finishActivity() {
        if (myZoo != null) {

            if (myZoo.getActiveActivitiesList() != null) {

                System.out.println("Ingresa el id de la actividad que deseas finalizar");
                System.out.println(myZoo.getActiveActivitiesList());
                String id = sc.nextLine();

                int option = myZoo.getActivityType(id);

                boolean result = false;

                if (option != 0) {

                    if (option == 1) {

                        System.out.println("Ingresa el costo que tuvo la actividad");
                        int cost = sc.nextInt();

                        System.out.println("Ingresa la descripcion de la actividad");
                        String description = sc.nextLine();

                        System.out.println("Ingresa los productos que se utilizaron en la actividad separalos con\"-\"");
                        String usedProducts = sc.nextLine();

                        result = myZoo.finalizeCleaningAct(id, cost, description, usedProducts);

                    }

                    if (option == 2) {
                        
                        System.out.println("Ingresa el costo que tuvo la actividad");
                        int cost = sc.nextInt();

                        System.out.println("Ingresa la duracion de dias de la actividad");
                        int durationDays = sc.nextInt();

                        System.out.println("Ingresa la descripcion de la actividad");
                        String description = sc.nextLine();

                        System.out.println("Ingresa los productos que se utilizaron en la actividad separalos con\"-\"");
                        String usedProducts = sc.nextLine();

                        result = myZoo.finalizeEnrichAct(id, cost, durationDays, description, usedProducts);

                    }

                    if (option == 3) {

                        System.out.println("Ingresa el costo que tuvo la actividad");
                        int cost = sc.nextInt();

                        result = myZoo.finalizeFeedingAct(id, cost);
                    }

                    //Need to put the animal back to its habitat. Verifying that the animal is in CV
                    //TODO

                    if (option == 4) {

                        System.out.println("Ingresa el costo que tuvo la activiad");
                        int cost = sc.nextInt();

                        System.out.println("Ingresa el peso final del animal que se atendio");
                        double weight = sc.nextDouble();

                        System.out.println("Ingresa el estado de salud del animal");
                        System.out.println(myZoo.getHealthStatusList());
                        int animalHealth = sc.nextInt();

                        System.out.println("Tiene una fecha de seguimiento?");
                        System.out.println("1. No\n2. Si");
                        int response = sc.nextInt();
                        LocalDate followUpDate = null;

                        if (response == 1) {
                            followUpDate = null;
                        } 
                        if (response ==2) {
                            System.out.println("Ingres la proxima fecha de seguimiento(AAAA-MM-DD)");
                            String date = sc.nextLine();

                            try {
                                    followUpDate = LocalDate.parse(date);
                            } catch (DateTimeParseException e) {
                                    System.out.println("Error: El formato debe ser AAAA-MM-DD (ejemplo: 2026-11-16).");
                            }
                        }

                        result = myZoo.finalizeMedicAct(id, cost, weight, animalHealth, followUpDate);
                                     
                    }

                    if (result) {
                        System.out.println("Se ha finalizado la actividad con exito");
                    } else {
                        System.out.println("Error. No se ha podido finalizar la actividad.");
                    }

                } else {
                    System.out.println("Error, no hay niguna activity el id ingresado");
                }

            } else {
                System.out.println("No hay actividades registradas");
            }

        } else {
            System.out.println("El zoologico aun no se ha inicializado");
        }
    }

    public static void moveAnimalToVeterinaryClinic() {
        if (myZoo != null) {

            if (myZoo.getActiveAnimalsReport() != null) {

                System.out.println("Ingresa el codigo del animal que deseas transferir a la Clinica Veterinaria");
                System.out.println(myZoo.getActiveAnimalsReport());
                String id = sc.nextLine();

                boolean result = myZoo.tranferAnimalToCV(id);

                if (result) {
                    System.out.println("Se ha podido mover con exito el animal");
                } else {
                    System.out.println("Error. No se pudo transferir el animal a la Clinica");
                }

            } else {
                System.out.println("Aun no hay animales registrados");
            }
            
        } else {
            System.out.println("El zoologico aun no se ha inicializado");
        }

    }

    public static void desactivateEmployee() {
        if (myZoo != null) {

            if (!myZoo.getActiveEmployeesList().equals("")) {

                sc.nextLine();

                System.out.println("Ingresa el codigo del empleado que deseas desactivar");
                System.out.println(myZoo.getActiveEmployeesList());
                String employeeId = sc.nextLine();

                while (myZoo.hasActiveActivity(employeeId)) {
                    System.out.println("El empleado seleccionado tiene actividades seleccionadas\nreeasignalas para poder desactivarlo");
                    System.out.println(myZoo.getActiveActivityInEmployee(employeeId).toString());
                    String habitatId = myZoo.getActiveActivityInEmployee(employeeId).getId();
                    System.out.println("Ingresa el codigo del nuevo empleado que va a tener la actividad");
                    System.out.println(myZoo.getActiveEmployeesList());
                    String replacementEmployee = sc.nextLine();

                    while (replacementEmployee.equals(employeeId)) {
                        System.out.println("Ingresa un codigo diferente al que vas a desactivar");
                        System.out.println(myZoo.getActiveEmployeesList());
                        replacementEmployee = sc.nextLine();
                    }

                    boolean result = myZoo.changeResponsableInActivity(habitatId, employeeId, replacementEmployee);

                    if (result) {
                        System.out.println("Se cambio el empleado con exito");
                    } else {
                        System.out.println("No se pudo cambiar el empleado");
                    }

                }

                boolean result = myZoo.putInactiveEmployee(employeeId);

                if (result) {
                    System.out.println("Se ha podido desactivar el empleado con exito");
                } else {
                    System.out.println("Error. No se ha podido desactivar el empleado con exito");
                }

            } else {
                System.out.println("No hay empleados activos en el sistema");
            }

        } else {
            System.out.println("El zoologico aun no se ha inicializado");
        }
                
    }

    /**
     * Saves the current state of the zoo to a file using the {@link DataManager}.
     *
     * Preconditions:
     * - The `myZoo` object must not be null before calling this method.
     *
     * Postconditions:
     * - If the save operation is successful, a file is generated.
     * - If an error occurs during the save operation, an appropriate error message
     * is printed to the console.
     *
     * Error Handling:
     * - Catches and prints stack traces for {@link FileNotFoundException} and
     * {@link IOException}.
     */
    public static void saveData() {

        if (myZoo != null) {

            try {
                if (DataManager.saveState(myZoo)) {
                    System.out.println("Informacion guardada exitosamente");
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

    }

    public static void loadData() {

        if (myZoo == null) {

            try {
                myZoo = DataManager.loadState();
            } catch (FileNotFoundException e) {
                System.out.println("No existe un archivo con informacion del Zoologico");
            } catch (EOFException e) {
                System.out.println("Carga exitosa");
            } catch (IOException e) {
                System.out.println("Error, el archivo no pudo ser leido.");
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                System.out.println("Error, se debe actualizar el sistema para poder acceder al archivo");

            }
        }

    }

    public static void generateOperativeSummary() {
        if (myZoo != null) {

            try {
                System.out.println(myZoo.zooSummary());
            } catch (IOException e) {
                e.printStackTrace();
            }

        }else {
            System.out.println("El zoologico aun no se ha inicializado");
        }
    }

    public static void generateDetailedAnimalReport() {
        if (myZoo != null) {

            System.out.println("Ingresa el codigo del animal que deseas hacer el reporte");
            System.out.println(myZoo.getAllAnimalsList());
            String animalId = sc.nextLine();

            try {
                if (myZoo.detailAnimalreport(animalId) != null) {
                    System.out.println(myZoo.detailAnimalreport(animalId));
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        } else {
            System.out.println("El zoologico aun no se ha inicializado");
        }
    }

    public static void generateActivitiesReport() {
        if (myZoo != null) {

            try {
                if (myZoo.getActivitiesReport() != null) {

                    System.out.println(myZoo.getActivitiesReport());

                } else {
                    System.out.println("No hay actividades Registradas");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        } else {
            System.out.println("El zoologico aun no se ha inicializado");
        }
    }

}
