package model;

import exceptions.*;

import java.io.IOException;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;

import model.animal.*;
import model.employee.*;
import model.habitat.*;
import reports.ReportManager;
import model.activity.*;
import model.alert.*;

/**
 * Description: Represents the main zoo manager, handling collections of
 * habitats, animals, and employees.
 */

public class Zoo implements Serializable{
    
    private static final long SerialVersionUID = 10l;

    // Atributos
    private String name;
    private String city;
    private String addres;
    private int legalRepresentativeCode;
    private String legalRepresentativeName;
    private double budget;

    // Relations
    private ArrayList<Habitat> myHabitats;

    private ArrayList<Employee> myEmployees;

    private ArrayList<Animal> allAnimals;

    private Map zooMap;

    private ArrayList<Activity> myActivities;

    private ArrayList<Alerts> myAlerts;

    // Constructor

    /**
     * Description: Constructs a new Zoo object with its general information,
     * initializes the veterinary clinic habitat,
     * and sets up the habitats and employees collections.
     * 
     * @param name                    Name of the zoo.
     * @param city                    City where the zoo is located.
     * @param addres                  Address of the zoo.
     * @param legalRepresentativeCode Identification code of the legal
     *                                representative.
     * @param legalRepresentativeName Name of the legal representative.
     * @param budget                  Initial budget of the zoo.
     * @return A new instance of the Zoo class.
     *         Precondition: Provided parameters must be valid (e.g., budget greater
     *         than 0).
     *         Postcondition: A Zoo instance is created with initialized collections
     *         and a default veterinary clinic habitat.
     */

    public Zoo(String name, String city, String addres, int legalRepresentativeCode, String legalRepresentativeName,
            double budget) {
        this.name = name;
        this.city = city;
        this.addres = addres;
        this.legalRepresentativeCode = legalRepresentativeCode;
        this.legalRepresentativeName = legalRepresentativeName;
        this.budget = budget;

        myHabitats = new ArrayList<Habitat>();
        myEmployees = new ArrayList<Employee>();
        allAnimals = new ArrayList<Animal>();
        myActivities = new ArrayList<Activity>();
        zooMap = new Map();
        initializeVeterinayClinic();
        myAlerts = new ArrayList<Alerts>();
    }

    // Analyzer

    /**
     * Description: Returns a concatenated string containing the general information
     * of the zoo.
     * 
     * @return A formatted String with the zoo's details separated by dashes.
     *         Precondition: The zoo object must be initialized.
     *         Postcondition: Returns the descriptive information string of the zoo
     *         without altering any state.
     */

    public String getInformation() {
        return name + "-" + city + "-" + addres + "-" + legalRepresentativeCode + "-" + legalRepresentativeName + "-"
                + budget;
    }

    /**
     * Description: Gets the total budget of the zoo.
     * 
     * @return The budget value as a double.
     *         Precondition: The zoo object must be initialized.
     *         Postcondition: Returns the current budget value.
     */

    public double getBudget() {
        return budget;
    }

    /**
     * Description: Calculates and returns the sum of the budgets of all registered
     * habitats in the zoo.
     * 
     * @return The total sum of habitat budgets as a double.
     *         Precondition: The habitats collection must be initialized.
     *         Postcondition: Iterates through habitats and returns the accumulated
     *         budget total.
     */

    public double getTotalHabitatsBudget() {
        double totalHabitatsBudget = this.budget;
        if (myHabitats != null) {
            for (int i = 0; i < myHabitats.size(); i++) {
                if (myHabitats.get(i) != null) {
                    totalHabitatsBudget = totalHabitatsBudget - myHabitats.get(i).getBudget();
                }
            }
        }
        return totalHabitatsBudget;
    }

    /**
     * Description: Adds a new habitat to the zoo's collection after calculating its
     * environment type.
     * 
     * @param HabitatId   Unique identifier code for the habitat.
     * @param name        Name of the habitat.
     * @param environment Integer representing the environment type option.
     * @param temperature Temperature of the habitat.
     * @param area        Area size of the habitat.
     * @param maxCapacity Maximum animal capacity of the habitat.
     * @param budget      Monthly budget allocated for the habitat.
     * @param state       Operational state of the habitat.
     * @return True if the habitat was successfully added, false otherwise.
     *         Precondition: The habitats list must be initialized. Habitat should
     *         not already exist in the list.
     *         Postcondition: A new Habitat object is created and added to the zoo's
     *         habitats collection.
     */

    public boolean addHabitat(String HabitatId, String name, int environment, double temperature, double area,
            int maxCapacity, double budget, int state) {

        if (myHabitats != null) {
            Habitat myHabitat = new Habitat(HabitatId, name, calculateEnvironmentType(environment), temperature, area,
                    maxCapacity, budget, StateType.calcuStateType(state));

            if (!myHabitats.contains(myHabitat)) {

                if (zooMap.addHabitat(myHabitat)) {
                    return myHabitats.add(myHabitat);
                }
            }
        }
        return false;
    }

    /**
     * Description: Initializes the default veterinary clinic habitat upon zoo
     * creation.
     * 
     * @return void
     *         Precondition: The zoo instance is being constructed.
     *         Postcondition: Adds the default veterinary clinic habitat to the
     *         habitats collection.
     */

    private void initializeVeterinayClinic() {
        addHabitat("CV", "Clinica Veterinaria", 4, 27, 400,
                25, 40000000, 1);
    }

    /**
     * Description: Assigns an employee to a specified habitat based on their IDs.
     * 
     * @param habitatId  Identifier of the target habitat.
     * @param employeeID Identifier of the target employee.
     * @return True if the employee was successfully assigned, false otherwise.
     *         Precondition: Both target habitat and employee must exist in the zoo
     *         system.
     *         Postcondition: The employee is added to the habitat's assigned
     *         personnel list if valid.
     */

    public boolean assignEmployeeToHabitat(String habitatId, String employeeID) {
        Habitat targetHabitat = findHabitat(habitatId);
        Employee targetEmployee = findEmployee(employeeID);

        if (targetHabitat != null && targetEmployee != null) {
            return targetHabitat.assingEmployee(targetEmployee);
        }

        return false;
    }

    /**
     * Description: Transfers an animal from its current habitat to a new
     * destination habitat.
     * 
     * @param AnimalId  Identifier code of the animal to transfer.
     * @param HabitatId Identifier code of the destination habitat.
     * @return True if the transfer is successful, false otherwise.
     *         Precondition: The destination habitat must exist and the animal must
     *         be located in an existing habitat.
     *         Postcondition: The animal is added to the target habitat and removed
     *         from its source habitat.
     */

    public boolean transferAnimal(String AnimalId, String HabitatId) {
        Habitat oldHabitat = null;
        Habitat targetHabitat = findHabitat(HabitatId);
        Animal targetAnimal = null;

        if (targetHabitat != null) {
            for (int i = 0; i < myHabitats.size(); i++) {
                targetAnimal = myHabitats.get(i).getAnimal(AnimalId);
                oldHabitat = myHabitats.get(i);

                if (targetAnimal != null && oldHabitat != null) {

                    if (compatibilityAnimalToHabitat(targetAnimal.getRequiereEnvironment(), HabitatId)) {
                        if (targetHabitat.getNumberOfAnimals() < targetHabitat.getMaxCapacity()) {
                            if (oldHabitat.removeAnimal(targetAnimal)) {
                                targetHabitat.addAnimal(targetAnimal);
                                return true;
                            }

                        }
                    }

                }
            }
        }

        return false;
    }

    /**
     * Description: Registers a new employee into the zoo system.
     * 
     * @param employeeID Unique identifier code for the employee.
     * @param name       Name of the employee.
     * @param number     Contact number of the employee.
     * @param email      Email address of the employee.
     * @param role       Integer representation of the employee role type.
     * @param state      Integer representation of the employee state type.
     * @return True if the employee is successfully added, false otherwise.
     *         Precondition: Employees collection must be initialized. Employee must
     *         not already exist.
     *         Postcondition: A new Employee object is created and added to the
     *         zoo's employee list.
     */

    public boolean addEmployee(String employeeID, String name, int number, String email, int role) {

        if (myEmployees != null) {

            Employee myEmployee = new Employee(employeeID, name, number, email, calculateRoleType(role));

            if (!myEmployees.contains(myEmployee)) {
                return myEmployees.add(myEmployee);
            }
        }

        return false;
    }

    /**
     * Description: Generates a formatted list of all available environment types.
     * 
     * @return A string containing numbered environment type options.
     *         Precondition: EnvironmentType enum must be defined.
     *         Postcondition: Returns a printable list of environment types.
     */

    public String getEnvironmentTypeList() {
        String environmentList = "";

        EnvironmentType[] types = EnvironmentType.values();

        for (int i = 0; i < types.length; i++) {

            environmentList += (i + 1) + ". " + types[i].getTypeName() + "\n";

        }

        return environmentList;
    }

    /**
     * Description: Maps an integer index to its corresponding EnvironmentType enum
     * value.
     * 
     * @param index Numeric option selected by the user.
     * @return The corresponding EnvironmentType constant.
     *         Precondition: Index must be within valid array bounds (1 to
     *         types.length).
     *         Postcondition: Returns the selected EnvironmentType.
     */

    private EnvironmentType calculateEnvironmentType(int index) {
        EnvironmentType[] types = EnvironmentType.values();

        return types[index - 1];
    }

    /**
     * Description: Generates a formatted list of all available employee role types.
     * 
     * @return A string containing numbered role type options.
     *         Precondition: RoleType enum must be defined.
     *         Postcondition: Returns a printable list of role types.
     */

    public String getRoleTypeList() {
        String roleList = "";

        RoleType[] types = RoleType.values();

        for (int i = 0; i < types.length; i++) {
            roleList += (i + 1) + ". " + types[i].getTypeName() + "\n";
        }
        return roleList;
    }

    /**
     * Description: Maps an integer index to its corresponding RoleType enum value.
     * 
     * @param index Numeric option selected by the user.
     * @return The corresponding RoleType constant.
     *         Precondition: Index must be within valid array bounds.
     *         Postcondition: Returns the selected RoleType.
     */

    private RoleType calculateRoleType(int index) {
        RoleType[] types = RoleType.values();

        return types[index - 1];
    }

    /**
     * Description: Generates a formatted list of employee state types.
     * 
     * @return A string containing numbered employee state options.
     *         Precondition: EmployeeState enum must be defined.
     *         Postcondition: Returns a printable list of employee states.
     */

    public String getEmployeeStateList() {
        String stateList = "";

        EmployeeState[] types = EmployeeState.values();

        for (int i = 0; i < types.length; i++) {
            stateList += (i + 1) + ". " + types[i].getTypeName() + "\n";
        }
        return stateList;
    }

    /**
     * Description: Maps an integer index to its corresponding EmployeeState enum
     * value.
     * 
     * @param index Numeric option selected by the user.
     * @return The corresponding EmployeeState constant.
     *         Precondition: Index must be within valid array bounds.
     *         Postcondition: Returns the selected EmployeeState.
     */

    private EmployeeState calcuEmployeeState(int index) {
        EmployeeState[] types = EmployeeState.values();

        return types[index - 1];
    }

    /**
     * Description: Generates a formatted list of diet types available for animals.
     * 
     * @return A string containing numbered diet type options.
     *         Precondition: DietType enum must be defined.
     *         Postcondition: Returns a printable list of diet types.
     */

    public String getdietTypeList() {
        String dietList = "";

        DietType[] types = DietType.values();

        for (int i = 0; i < types.length; i++) {
            dietList += (i + 1) + "." + types[i].getTypeName() + "\n";
        }
        return dietList;
    }

    /**
     * Description: Maps an integer index to its corresponding DietType enum value.
     * 
     * @param index Numeric option selected by the user.
     * @return The corresponding DietType constant.
     *         Precondition: Index must be within valid array bounds.
     *         Postcondition: Returns the selected DietType.
     */

    public DietType calculateDietType(int index) {
        DietType[] types = DietType.values();

        return types[index - 1];
    }

    /**
     * Description: Generates a formatted list of health status types for animals.
     * 
     * @return A string containing numbered health status options.
     *         Precondition: HealthStatus enum must be defined.
     *         Postcondition: Returns a printable list of health statuses.
     */

    public String getHealthStatusList() {
        String healthList = "";

        HealthStatus[] types = HealthStatus.values();

        for (int i = 0; i < types.length; i++) {
            healthList += (i + 1) + ". " + types[i].getTypeName() + "\n";
        }
        return healthList;
    }

    /**
     * Description: Maps an integer index to its corresponding HealthStatus enum
     * value.
     * 
     * @param index Numeric option selected by the user.
     * @return The corresponding HealthStatus constant.
     *         Precondition: Index must be within valid array bounds.
     *         Postcondition: Returns the selected HealthStatus.
     */

    public HealthStatus calcHealthStatus(int index) {
        HealthStatus[] types = HealthStatus.values();

        return types[index - 1];
    }

    /**
     * Description: Generates a formatted list of life stage types for animals.
     * 
     * @return A string containing numbered life stage options.
     *         Precondition: LifeStage enum must be defined.
     *         Postcondition: Returns a printable list of life stages.
     */

    public String getLifeStageList() {
        String lifeList = "";

        LifeStage[] types = LifeStage.values();

        for (int i = 0; i < types.length; i++) {
            lifeList += (i + 1) + ". " + types[i].getTypeName() + "\n";
        }
        return lifeList;
    }

    /**
     * Description: Maps an integer index to its corresponding LifeStage enum value.
     * 
     * @param index Numeric option selected by the user.
     * @return The corresponding LifeStage constant.
     *         Precondition: Index must be within valid array bounds.
     *         Postcondition: Returns the selected LifeStage.
     */

    public LifeStage calcLifeStage(int index) {
        LifeStage[] types = LifeStage.values();

        return types[index - 1];
    }

    /**
     * Description: Generates a formatted list of animal status types.
     * 
     * @return A string containing numbered animal status options.
     *         Precondition: AnimalStatusType enum must be defined.
     *         Postcondition: Returns a printable list of animal status types.
     */

    public String getAnimalStatusTypeList() {
        String animalStatisList = "";

        AnimalStatusType[] types = AnimalStatusType.values();

        for (int i = 0; i < types.length; i++) {
            animalStatisList += (i + 1) + ". " + types[i].getTypeName() + "\n";
        }
        return animalStatisList;
    }

    /**
     * Description: Maps an integer index to its corresponding AnimalStatusType enum
     * value.
     * 
     * @param index Numeric option selected by the user.
     * @return The corresponding AnimalStatusType constant.
     *         Precondition: Index must be within valid array bounds.
     *         Postcondition: Returns the selected AnimalStatusType.
     */

    public AnimalStatusType cAnimalStatusType(int index) {
        AnimalStatusType[] types = AnimalStatusType.values();

        return types[index - 1];
    }

    public String getStateTypeList() {
        return StateType.getStateTypeList();
    }

    public String getGenderTypeList() {
        return GenderType.getGenderTypeList();
    }

    /**
     * Description: Checks if there is space available to add more habitats based on
     * limits.
     * 
     * @return True if habitats list is initialized and size is under 33, false
     *         otherwise.
     *         Precondition: myHabitats collection must be initialized.
     *         Postcondition: Evaluates availability condition and returns boolean
     *         result.
     */

    public boolean hasAvialableHabitat() {

        if (myHabitats != null) {

            for (int i = 0; i < myHabitats.size(); i++) {
                if (myHabitats.get(i) != null && myHabitats.size() < 33) {
                    return true;
                }
            }
        }
        return false;

    }

    /**
     * Description: Checks if there are available employees registered in the zoo.
     * 
     * @return True if at least one valid employee exists, false otherwise.
     *         Precondition: myEmployees collection must be initialized.
     *         Postcondition: Evaluates availability condition and returns boolean
     *         result.
     */

    public boolean hasAvailableEmployee() {

        if (myEmployees != null) {

            for (int i = 0; i < myEmployees.size(); i++) {
                if (myEmployees.get(i) != null) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Description: Generates a summary string containing basic details of all
     * registered habitats.
     * 
     * @return A formatted string listing habitat IDs, names, environments, areas,
     *         and capacities.
     *         Precondition: myHabitats collection must be initialized.
     *         Postcondition: Returns a concatenated text report of all habitats.
     */

    public String getHabitatList() {

        String habitatList = "";

        if (myHabitats != null) {

            for (int i = 0; i < myHabitats.size(); i++) {
                if (myHabitats.get(i) != null) {

                    habitatList += "\n" + myHabitats.get(i).getId() + " - " + myHabitats.get(i).getName()
                            + " - Ambiente: " + myHabitats.get(i).getEnvironment().getTypeName() + " - Temperatura: "
                            + myHabitats.get(i).getTemperature() + " - Area: "
                            + myHabitats.get(i).getArea() + " - Maxima Capacidad: "
                            + myHabitats.get(i).getMaxCapacity() + " - Estado: "
                            + myHabitats.get(i).getState().getType()
                            + " - Presupuesto: " + myHabitats.get(i).getBudget();

                }
            }
        }
        return habitatList;

    }

    /**
     * Description: Instantiates and registers a new animal inside a specified
     * habitat.
     * 
     * @param habitatName         Name of the habitat where the animal will be
     *                            added.
     * @param animalId            Identifier code of the animal.
     * @param name                Name of the animal.
     * @param specie              Species of the animal.
     * @param gender              Gender of the animal.
     * @param weight              Weight of the animal.
     * @param dietType            Index of the diet type.
     * @param requiereEnvironment Index of the required environment type.
     * @param lifeStage           Index of the life stage.
     * @param healthStatus        Index of the health status.
     * @param animalState         Index of the animal status type.
     * @return True if the animal was successfully created and added, false
     *         otherwise.
     *         Precondition: The target habitat must exist and accept the animal
     *         according to rules.
     *         Postcondition: A new Animal object is added to the target habitat's
     *         collection.
     */

    public boolean addAnimalInHabitat(String habitatID, String animalId, String name, String specie, int gender,
            double weight, int dietType,
            int requiereEnvironment, int lifeStage, int healthStatus) {
        if (myHabitats != null) {

            for (int i = 0; i < myHabitats.size(); i++) {
                
                if (myHabitats.get(i).getState() == StateType.ACTIVE) {
                    if (myHabitats.get(i) != null && myHabitats.get(i).getId().equals(habitatID)) {

                        if (myHabitats.get(i).getNumberOfAnimals() == myHabitats.get(i).getMaxCapacity()) {

                            Animal myAnimal = new Animal(animalId, name, specie, GenderType.calculateGenderType(gender), weight,
                                    calculateDietType(dietType),
                                    calculateEnvironmentType(requiereEnvironment), calcLifeStage(lifeStage),
                                    calcHealthStatus(healthStatus));

                            if (compatibilityAnimalToHabitat(calculateEnvironmentType(requiereEnvironment), habitatID)) {

                                allAnimals.add(myAnimal);
                                if (myHabitats.get(i).calculateOccupancyPercentage() >= 90) {
                                    Alerts newAlert = new Alerts(AlertType.OCCUPANCY, "Habitat: " + myHabitats.get(i).getName(), "El habitat tiene un porcentaje de ocupacion mayor a 90%");
                                    myAlerts.add(newAlert);
                                }   
                                return myHabitats.get(i).addAnimal(myAnimal);
                            }
                        } else {
                            Alerts alert = new Alerts(AlertType.INSUFFICIENT_HABITAT_CAPACITY, "Habitat: " + myHabitats.get(i).getName(), "El habitat al que se intento agregar el animal ya esta lleno");
                            myAlerts.add(alert);
                        }
                    }
                } else {
                    Alerts newAlert = new Alerts(AlertType.INVALID_HABITAT_STATUS, "Habitat: " + myHabitats.get(i).getName(), "El habitat al que se intento agregar el animal no esta activo");
                    myAlerts.add(newAlert);
                }
            }
        }
        return false;
    }

    /**
     * Description: Calculates the monthly food cost for a specific animal across
     * all habitats.
     * 
     * @param animalName Name of the animal to search for.
     * @return The monthly food cost as a double, or 0.0 if not found.
     *         Precondition: Habitats and animals must be registered.
     *         Postcondition: Searches habitats and returns the calculated food
     *         cost.
     */

    public double getAnimalInHabitatMonthlyCost(String animalName) {
        double animalMonthlyCost = 0.0;

        for (int i = 0; i < myHabitats.size(); i++) {
            if (myHabitats.get(i) != null) {

                animalMonthlyCost = myHabitats.get(i).getAnimalMonthlyFoodCost(animalName);

                if (animalMonthlyCost != 0.0) {
                    return animalMonthlyCost;
                }
            }
        }
        return animalMonthlyCost;
    }

    /**
     * Description: Generates a complete food cost report for all habitats and total
     * zoo.
     * 
     * @return A formatted string report detailing habitat sub-totals and total zoo
     *         food cost.
     *         Precondition: Habitats must be registered.
     *         Postcondition: Returns a consolidated cost breakdown report.
     */

    public String getCompleteFoodCostReport() {
        String report = "";
        double totalZooCost = 0.0;

        if (myHabitats != null) {

            for (int i = 0; i < myHabitats.size(); i++) {

                if (myHabitats.get(i) != null) {
                    report += "Habitat: " + myHabitats.get(i).getName() + "\n";
                    report += myHabitats.get(i).getAnimalsFoodCostDetails();
                    report += " Subtotal habitat: $" + myHabitats.get(i).getTotalFoodCost() + "\n\n";

                    totalZooCost += myHabitats.get(i).getTotalFoodCost();
                }
            }
        }
        report += "Costo Total del Zoologico: $" + totalZooCost;

        return report;
    }

    /**
     * Description: Retrieves the occupancy details and status check for a specific
     * habitat ID.
     * 
     * @param HabitatId Identifier code of the target habitat.
     * @return A formatted string with occupancy rate details and high-occupancy
     *         alerts.
     *         Precondition: The habitat must exist in the zoo.
     *         Postcondition: Returns occupancy data string.
     */

    public String getHabitatsOccupancy(String HabitatId) {
        Habitat targetHabitat = findHabitat(HabitatId);
        String report = "";

        if (targetHabitat != null) {
            report += "Hábitat: " + targetHabitat.getName() + " - Tasa de ocupancia: "
                    + targetHabitat.calculateOccupancyPercentage() + "%\n";
            if (targetHabitat.calculateOccupancyPercentage() >= 90) {
                report += " El habitat tiene un porcentaje mayor a 90%";
            }

        }
        return report;
    }

    /**
     * Description: Retrieves a consolidated list of all animals across all habitats
     * in the zoo.
     * 
     * @return A string containing animal identification details from every habitat.
     *         Precondition: Habitats collection must be initialized.
     *         Postcondition: Returns a combined string of all registered animal
     *         lists.
     */

    public String getAllAnimalsList() {
        String allAnimalsList = "";
        for (int i = 0; i < allAnimals.size(); i++) {
            allAnimalsList += allAnimals.get(i).getId() + " - " + allAnimals.get(i).getName() + "\n";
        }

        return allAnimalsList;
    }

    /**
     * Description: Retrieves a summary list of all employees registered in the zoo.
     * 
     * @return A formatted string containing employee IDs and names.
     *         Precondition: myEmployees collection must be initialized.
     *         Postcondition: Returns a text list of all employees.
     */

    public String getAllEmployeeList() {
        String allEmployeeList = "";

        for (int i = 0; i < myEmployees.size(); i++) {
            if (myEmployees.get(i) != null) {
                allEmployeeList += myEmployees.get(i).getId() + " - " + myEmployees.get(i).getName() + "\n";
            }
        }
        return allEmployeeList;
    }

    /**
     * Description: Searches for and returns an employee object by their unique ID.
     * 
     * @param employeeID Identifier code of the employee to find.
     * @return The Employee object if found, or null otherwise.
     *         Precondition: myEmployees collection must be initialized.
     *         Postcondition: Iterates through employees and returns the matching
     *         instance or null.
     */

    public Employee findEmployee(String employeeID) {
        for (int i = 0; i < myEmployees.size(); i++) {
            if (myEmployees.get(i) != null && myEmployees.get(i).getId().equals(employeeID)) {
                return myEmployees.get(i);
            }
        }
        return null;
    }

    /**
     * Description: Searches for and returns a habitat object by its unique ID.
     * 
     * @param HabitatId Identifier code of the habitat to find.
     * @return The Habitat object if found, or null otherwise.
     *         Precondition: myHabitats collection must be initialized.
     *         Postcondition: Iterates through habitats and returns the matching
     *         instance or null.
     */

    public Habitat findHabitat(String HabitatId) {
        if (myHabitats != null) {
            for (int i = 0; i < myHabitats.size(); i++) {
                if (myHabitats.get(i) != null && myHabitats.get(i).getId().equals(HabitatId)) {
                    return myHabitats.get(i);
                }
            }
        }
        return null;
    }

    /**
     * Description: Computes the total food cost for all habitats combined in the
     * zoo.
     * 
     * @return The total food cost as a double value.
     *         Precondition: Habitats must be initialized.
     *         Postcondition: Accumulates and returns total feeding cost across the
     *         zoo.
     */

    public double getTotalZooFoodCost() {
        double totalZooCost = 0.0;
        if (myHabitats != null) {
            for (int i = 0; i < myHabitats.size(); i++) {
                if (myHabitats.get(i) != null) {
                    totalZooCost += myHabitats.get(i).getTotalFoodCost();
                }
            }
        }
        return totalZooCost;
    }

    /**
     * Description: Generates a list of all currently active employees in the zoo.
     * 
     * @return A formatted string listing active employees' IDs, names, and roles.
     *         Precondition: Employees collection must be initialized.
     *         Postcondition: Returns filtered list string of active personnel.
     */

    public String getActiveEmployeesList() {
        String activeList = "";
        if (myEmployees != null) {
            for (int i = 0; i < myEmployees.size(); i++) {
                if (myEmployees.get(i) != null && myEmployees.get(i).getState() == EmployeeState.ACTIVE) {
                    activeList += myEmployees.get(i).getId() + " - " + myEmployees.get(i).getName() + " - "
                            + myEmployees.get(i).getRole().getTypeName() + "\n";
                }
            }
        }
        return activeList;
    }

    /**
     * Description: Generates a comprehensive report containing complete details of
     * all habitats.
     * 
     * @return A formatted string with complete habitat information.
     *         Precondition: Habitats collection must be initialized.
     *         Postcondition: Returns combined complete habitat report string.
     */

    public String getAllCompleteHabitatsReport() {
        String report = "";
        if (myHabitats != null) {
            for (int i = 0; i < myHabitats.size(); i++) {
                if (myHabitats.get(i) != null) {
                    report += myHabitats.get(i).getCompleteHabitatInfo() + "\n";
                }
            }
        }
        return report;
    }

    /**
     * Description: Generates a report listing all active animals grouped by
     * habitat.
     * 
     * @return A formatted string detailing active animals per habitat.
     *         Precondition: Habitats and animals must be registered.
     *         Postcondition: Returns filtered active animal report string.
     */

    public String getActiveAnimalsReport() {
        String report = "";

        if (myHabitats != null) {

            for (int i = 0; i < myHabitats.size(); i++) {

                if (myHabitats.get(i) != null) {

                    String habitatActive = myHabitats.get(i).getActiveAnimalsList();
                    if (habitatActive != null && !habitatActive.isEmpty()) {

                        report += "Habitat: " + myHabitats.get(i).getName() + "\n";
                        report += habitatActive + "\n";
                    }
                }
            }
        }
        return report;
    }

    // Modifier

    /**
     * Description: Updates the zoo's overall budget value.
     * 
     * @param budget New budget amount to set.
     *               Precondition: Zoo object must be initialized.
     *               Postcondition: The budget attribute is updated with the new
     *               value.
     */
    public void setbudget(double budget) {
        this.budget = budget;
    }

    /**
     * Description: Modifies the information of an existing employee.
     * 
     * @param employeeID Identifier code of the employee to modify.
     * @param name       New name.
     * @param number     New contact number.
     * @param email      New email address.
     * @param role       New role option index.
     * @param state      New employee state option index.
     * @return True if modification was successful, false otherwise.
     *         Precondition: Employee must exist in the system.
     *         Postcondition: Employee attributes are updated with the new
     *         parameters.
     */

    public boolean setEmployee(String employeeID, String name, int number, String email, int role, int state) {
        Employee employeeToModify = findEmployee(employeeID);

        if (employeeToModify != null) {
            employeeToModify.setName(name);
            employeeToModify.setNumber(number);
            employeeToModify.setEmail(email);
            employeeToModify.setRole(calculateRoleType(role));
            employeeToModify.setState(calcuEmployeeState(state));

            return true;

        }
        return false;
    }

    /**
     * Description: Modifies the attributes of an existing habitat.
     * 
     * @param habitatId      Identifier code of the habitat to modify.
     * @param newName        New habitat name.
     * @param newTemperature New temperature value.
     * @param newArea        New area size.
     * @param newMaxCapacity New maximum capacity.
     * @param newBudget      New monthly budget.
     * @param newState       New operational state string.
     * @return True if modification was successful, false otherwise.
     *         Precondition: Habitat must exist in the zoo.
     *         Postcondition: Habitat attributes are updated with the new values.
     */

    public boolean setHabitat(String habitatId, String newName, double newTemperature,
            int newMaxCapacity, int newState) {

        Habitat habitatToModify = findHabitat(habitatId);

        if (habitatToModify != null) {

            habitatToModify.setName(newName);
            habitatToModify.setTemperature(newTemperature);
            habitatToModify.setMaxCapacity(newMaxCapacity);
            habitatToModify.setState(StateType.calcuStateType(newState));

            return true;

        }

        return false;
    }

    /**
     * Description: Modifies specific attributes of an existing animal.
     * 
     * @param animalId        Identifier code of the animal to modify.
     * @param newWeight       New weight value.
     * @param newDietType     New diet type option index.
     * @param newLifeStage    New life stage option index.
     * @param newHealthStatus New health status option index.
     * @return True if modification was successful, false otherwise.
     *         Precondition: Animal must exist within one of the zoo's habitats.
     *         Postcondition: Animal attributes are updated with the new values.
     */

    public boolean setAnimal(String animalId, double newWeight, int newDietType, int newLifeStage,
            int newHealthStatus, int newAnimalState) {

        if (myHabitats != null) {
            for (int i = 0; i < myHabitats.size(); i++) {
                if (myHabitats.get(i) != null) {
                    Animal animalToModify = myHabitats.get(i).getAnimal(animalId);

                    if (animalToModify != null) {
                        animalToModify.setWeight(newWeight);
                        animalToModify.setDietType(calculateDietType(newDietType));
                        animalToModify.setLifeStage(calcLifeStage(newLifeStage));
                        animalToModify.setHealthStatus(calcHealthStatus(newHealthStatus));
                        animalToModify.setAnimalStatusType(cAnimalStatusType(newAnimalState));
                        return true;
                    }
                }
            }
        }
        return false;
    }

    /**
     * Description: Processes the retirement of an animal by updating its status
     * data and removing it from its habitat.
     * 
     * @param animalId Identifier code of the animal to retire.
     * @param reason   Reason for retirement.
     * @param status   Index of the retirement status type.
     * @param date     Date of retirement.
     * @return True if retirement and removal were successful, false otherwise.
     *         Precondition: Animal must exist in a habitat.
     *         Postcondition: Animal retirement attributes are assigned and it is
     *         removed from active habitat inventory.
     */

    public boolean retireAnimal(String animalId, String reason, int status, LocalDate date) {
        if (myHabitats != null) {

            for (int i = 0; i < myHabitats.size(); i++) {

                if (myHabitats.get(i) != null) {
                    Animal animalToRetire = myHabitats.get(i).getAnimal(animalId);

                    if (animalToRetire != null) {

                        animalToRetire.setRetirementData(reason, cAnimalStatusType(status), date);

                        boolean removed = myHabitats.get(i).removeAnimal(animalToRetire);

                        return removed;
                    }
                }
            }
        }
        return false;
    }

    // New methods, for the correction

    public Animal findAnimal(String animalID) {

        Animal foundAnimal = null;

        for (int i = 0; i < allAnimals.size(); i++) {
            if (allAnimals.get(i).getId().equals(animalID)) {

                foundAnimal = allAnimals.get(i);
            }
        }
        return foundAnimal;

    }

    public boolean compatibilityAnimalToHabitat(EnvironmentType type, String habitatID) {

        Habitat targetHabitat = findHabitat(habitatID);

        if (type == targetHabitat.getEnvironment()) {
            return true;
        } else {
            Alerts newAlert = new Alerts(AlertType.ENVIRONMENTAL_INCOMPATIBILITY, "Habitat: " + targetHabitat.getName(), "El animal que se intento agregar al habitat no son compatibles por su tipo de ambiente");
            myAlerts.add(newAlert);
        }

        return false;
    }

    /**
     * Retrieves the map of the zoo as a string.
     *
     * Preconditions:
     * - The `zooMap` object must be initialized (not null).
     *
     * @return A string containing the zoo map.
     */
    public String getZooMap() {

        return zooMap.getMapAsString();
    }

    public boolean removeHabitatInMap(String habitatID) {

        Habitat targeHabitat = findHabitat(habitatID);

        if (myHabitats != null) {
            if (targeHabitat.getState() == StateType.RETIRED) {
                zooMap.removeHabitat(targeHabitat);
                return true;
            }
        }

        return false;
    }

    public boolean isActive(String habitatID) {
        Habitat targetHabitat = findHabitat(habitatID);

        if (myHabitats != null) {
            if (targetHabitat.getState() == StateType.ACTIVE) {
                return true;
            }
        }

        return false;
    }

    public boolean setHabitatState(String habitatID, int newState) {
        Habitat targetHabitat = findHabitat(habitatID);

        if (targetHabitat != null) {
            targetHabitat.setState(StateType.calcuStateType(newState));
            return true;
        }
        return false;
    }

    public boolean addFeedingActivity(String habitatId, String employeeId, LocalDate schedulingDate,
            String animalName, double weight) throws ZooCareBusinessException {

        // Busquedas
        Habitat myHabitat = findHabitat(habitatId); // null o el objeto
        Employee myEmployee = findEmployee(employeeId); // null o el objeto
        Animal myAnimal = findAnimal(animalName); // null o el objeto

        // Validaciones
        if (myHabitat == null) {

            throw new ZooCareBusinessException(ZooCareBusinessException.HABITAT_NOT_FOUND);

        }
        if (myEmployee == null) {

            throw new ZooCareBusinessException(ZooCareBusinessException.EMPLOYEE_NOT_FOUND);

        }
        if (myAnimal == null) {

            throw new ZooCareBusinessException(ZooCareBusinessException.ANIMAL_NOT_FOUND);

        }
        if (myHabitat.getAnimal(animalName) == null) {

            throw new ZooCareBusinessException(ZooCareBusinessException.ANIMAL_NOT_IN_HABITAT);
        }
        if (!myEmployee.isActive()) {
            throw new ZooCareBusinessException(ZooCareBusinessException.EMPLOYEE_NOT_ACTIVE);
        }

        FeedingActivity myActivity = new FeedingActivity(myHabitat, myEmployee, schedulingDate,
                myAnimal,
                weight);

        if (!myEmployee.addActivity(myActivity)) {
            throw new ZooCareBusinessException(ZooCareBusinessException.EMPLOYEE_NOT_AUTHORIZED);
        }
        return myActivities.add(myActivity);

    }

    /**
     * Adds a cleaning activity to the zoo. This method ensures that the habitat
     * and employee exist and are valid for the activity. It also validates that
     * the employee is active and authorized to perform the activity.
     *
     * @param habitatId      The unique identifier of the habitat where the activity
     *                       will take place.
     * @param employeeId     The unique identifier of the employee responsible for
     *                       the activity.
     * @param schedulingDate The date when the activity is scheduled.
     * @param description    A description of the cleaning activity.
     * @param usedProducts   A string containing the products used in the cleaning
     *                       activity, separated by a hyphen ("-").
     * @return {@code true} if the cleaning activity was successfully added,
     *         {@code false} otherwise.
     * @throws ZooCareBusinessException If any of the following conditions occur:
     *                                  <ul>
     *                                  <li>The habitat does not exist
     *                                  (HABITAT_NOT_FOUND).</li>
     *                                  <li>The employee does not exist
     *                                  (EMPLOYEE_NOT_FOUND).</li>
     *                                  <li>The employee is not active
     *                                  (EMPLOYEE_NOT_ACTIVE).</li>
     *                                  <li>The employee is not authorized to
     *                                  perform
     *                                  the activity (EMPLOYEE_NOT_AUTHORIZED).</li>
     *                                  </ul>
     */
    public boolean addCleaningActivity(String habitatId, String employeeId, LocalDate schedulingDate,
            String description, String usedProducts) throws ZooCareBusinessException {

        // Busquedas
        Habitat myHabitat = findHabitat(habitatId); // null o el objeto
        Employee myEmployee = findEmployee(employeeId); // null o el objeto

        // Validaciones
        if (myHabitat == null) {
            throw new ZooCareBusinessException(ZooCareBusinessException.HABITAT_NOT_FOUND);
        }

        if (myEmployee == null) {
            throw new ZooCareBusinessException(ZooCareBusinessException.EMPLOYEE_NOT_FOUND);
        }

        if (!myEmployee.isActive()) {
            throw new ZooCareBusinessException(ZooCareBusinessException.EMPLOYEE_NOT_ACTIVE);
        }

        String[] myUsedproducts = usedProducts.split("-"); // Solo funciona si el String de productos usados separa los
                                                           // productos con "-"

        CleaningActivity myActivity = new CleaningActivity(myHabitat, myEmployee, schedulingDate,
                description,
                new ArrayList<String>(Arrays.asList(myUsedproducts)));

        if (!myEmployee.addActivity(myActivity)) {
            throw new ZooCareBusinessException(ZooCareBusinessException.EMPLOYEE_NOT_AUTHORIZED);
        }
        return myActivities.add(myActivity);

    }

    /**
     * Adds an enrichment activity to the zoo. This method ensures that the habitat
     * and employee exist and are valid for the activity. It also validates that
     * the employee is active and authorized to perform the activity.
     *
     * @param habitatId      The unique identifier of the habitat where the activity
     *                       will take place.
     * @param employeeId     The unique identifier of the employee responsible for
     *                       the activity.
     * @param schedulingDate The date when the activity is scheduled.
     * @param description    A description of the enrichment activity.
     * @param durationInDays The duration of the enrichment activity in days.
     * @param usedProducts   A string containing the products used in the enrichment
     *                       activity, separated by a hyphen ("-").
     * @return {@code true} if the enrichment activity was successfully added,
     *         {@code false} otherwise.
     * @throws ZooCareBusinessException If any of the following conditions occur:
     *                                  <ul>
     *                                  <li>The habitat does not exist
     *                                  (HABITAT_NOT_FOUND).</li>
     *                                  <li>The employee does not exist
     *                                  (EMPLOYEE_NOT_FOUND).</li>
     *                                  <li>The employee is not active
     *                                  (EMPLOYEE_NOT_ACTIVE).</li>
     *                                  <li>The employee is not authorized to
     *                                  perform
     *                                  the activity (EMPLOYEE_NOT_AUTHORIZED).</li>
     *                                  </ul>
     */
    public boolean addEnrichmentActivity(String habitatId, String employeeId, LocalDate schedulingDate,
            String description, int durationInDays, String usedProducts) throws ZooCareBusinessException {

        // Busquedas
        Habitat myHabitat = findHabitat(habitatId); // null o el objeto
        Employee myEmployee = findEmployee(employeeId); // null o el objeto

        // Validaciones
        if (myHabitat == null) {
            throw new ZooCareBusinessException(ZooCareBusinessException.HABITAT_NOT_FOUND);
        }

        if (myEmployee == null) {
            throw new ZooCareBusinessException(ZooCareBusinessException.EMPLOYEE_NOT_FOUND);
        }

        if (!myEmployee.isActive()) {
            throw new ZooCareBusinessException(ZooCareBusinessException.EMPLOYEE_NOT_ACTIVE);
        }

        String[] myUsedproducts = usedProducts.split("-"); // Solo funciona si el String de productos usados separa los
                                                           // productos con "-"

        EnrichmentActivity myActivity = new EnrichmentActivity(myHabitat, myEmployee,
                schedulingDate,
                description, durationInDays,
                new ArrayList<String>(Arrays.asList(myUsedproducts)));

        if (!myEmployee.addActivity(myActivity)) {
            throw new ZooCareBusinessException(ZooCareBusinessException.EMPLOYEE_NOT_AUTHORIZED);
        }

        return myActivities.add(myActivity);

    }

    public boolean addMedicControlActivity(String employeeId, LocalDate schedulingDate,
            String animalId, double weight, String observations, String medications, double treatmentCost)
            throws ZooCareBusinessException {

        Employee myEmployee = findEmployee(employeeId);
        Animal myAnimal = findAnimal(animalId);
        Habitat myHabitat = findHabitat("CV");

        if (myEmployee == null) {
            throw new ZooCareBusinessException(ZooCareBusinessException.EMPLOYEE_NOT_FOUND);
        }

        if (!myEmployee.isActive()) {
            throw new ZooCareBusinessException(ZooCareBusinessException.EMPLOYEE_NOT_ACTIVE);
        }

        if (myAnimal == null) {
            throw new ZooCareBusinessException(ZooCareBusinessException.ANIMAL_NOT_FOUND);
        }

        if (!myAnimal.isActive()) {
            throw new ZooCareBusinessException(ZooCareBusinessException.ANIMAL_NOT_ACTIVE);
        }

        String[] myUsedMedications = medications.split("-"); // Solo funciona si el String de productos usados separa
                                                             // los
                                                             // productos con "-"

        MedicControl myActivity = new MedicControl(myHabitat, myEmployee, schedulingDate, myAnimal, medications, weight,
                observations, new ArrayList<String>(Arrays.asList(myUsedMedications)), treatmentCost);

        if (!myEmployee.addActivity(myActivity)) {
            throw new ZooCareBusinessException(ZooCareBusinessException.EMPLOYEE_NOT_AUTHORIZED);
        }

        return myActivities.add(myActivity);
    }

    /**
     * Retrieves information about all activities registered in the zoo.
     *
     * @return A formatted String containing information about all activities.
     *         If no activities are registered, returns "No hay actividades
     *         registradas".
     */
    public String getAllActivitiesInformation() {

        String list = "";

        for (Activity activity : myActivities) {

            list += activity.toString() + "\n";

        }

        if (list.equals("")) {
            return null;
        }

        return list;

    }

    /**
     * Retrieves information about activities of a specific type.
     *
     * @param option The type of activity to filter by:
     *               1 - FeedingActivity
     *               2 - CleaningActivity
     *               3 - EnrichmentActivity
     * @return A formatted String containing information about activities of the
     *         specified type.
     */
    public String getActivitiesInformationByType(int option) {

        String list = "";

        for (Activity activity : myActivities) {

            switch (option) {
                case 1:
                    if (activity instanceof FeedingActivity) {
                        list += activity.toString() + "\n";
                    }
                    break;
                case 2:
                    if (activity instanceof CleaningActivity) {
                        list += activity.toString() + "\n";
                    }
                    break;
                case 3:
                    if (activity instanceof EnrichmentActivity) {
                        list += activity.toString() + "\n";
                    }
                    break;
                case 4:
                    if (activity instanceof MedicControl) {
                        list += activity.toString() + "\n";
                    }

                default:
                    break;
            }

        }

        if (list.equals("")) {
            return "No hay actividades registradas del tipo seleccionado";
        }

        return list;

    }

    /**
     * Retrieves information about all feeding activities registered in the zoo.
     *
     * @return A formatted String containing information about all feeding
     *         activities.
     */
    public String getFeedingActivitiesInformation() {

        String list = "";

        for (Activity activity : myActivities) {

            if (activity instanceof FeedingActivity) {
                list += activity.toString() + "\n";
            }

        }

        return list;
    }

    /**
     * Retrieves a concatenated string of descriptions for all enrichment activities
     * that occurred in the same month and year as the specified date.
     *
     * Preconditions:
     * - The `date` parameter must not be null.
     * - The `myActivities` list must not be null and should contain valid
     * `Activity` objects.
     *
     *
     * @param date The {@link LocalDate} object representing the month and year
     *             to filter the enrichment activities by.
     * @return A string containing the descriptions of all enrichment activities
     *         that match the specified month and year. If no activities match,
     *         an empty string is returned.
     */
    public String getEnrichmentDescriptionInMonth(LocalDate date) {

        String list = "";

        for (Activity myActivity : myActivities) {

            if (myActivity instanceof EnrichmentActivity) {

                // EnrichmentActivity temporalActivity = (EnrichmentActivity) myActivity; //
                // Downcasting

                LocalDate executionDate = myActivity.getExecutionDate();

                if (executionDate.getMonth().equals(date.getMonth())
                        && executionDate.getYear() == date.getYear()) {
                    // list += temporalActivity.getDescription();

                    list += ((EnrichmentActivity) myActivity).getDescription();
                }

            }

        }

        return list;
    }

    /**
     * Updates the list of products used in a cleaning activity identified by the
     * given ID.
     *
     * Preconditions:
     * - The `newProducts` parameter must not be null or empty.
     * - The `idActivity` parameter must not be null or empty.
     * - The `myActivities` list must not be null and should contain valid
     * `Activity` objects.
     *
     * Postconditions:
     * - If a cleaning activity with the specified ID exists, its list of products
     * will be updated with the new products provided.
     *
     * @param newProducts A string containing the new products to be set, separated
     *                    by hyphens ("-").
     * @param idActivity  The ID of the cleaning activity to update.
     * @return {@code true} if the cleaning activity with the specified ID was found
     *         and updated;
     *         {@code false} otherwise.
     */
    public boolean setUsedProductsInCleaningActivity(String newProducts, String idActivity) {
        for (Activity myActivity : myActivities) {

            if (myActivity instanceof CleaningActivity && idActivity.equals(myActivity.getId())) {
                CleaningActivity semiActivity = (CleaningActivity) myActivity;

                String[] myUsedproducts = newProducts.split("-");

                semiActivity.setUsedProducts(new ArrayList<String>(Arrays.asList(myUsedproducts)));
                return true;
            }
        }
        return false;
    }

    /**
     * Counts the number of feeding activities that occurred in a specific habitat
     * during the specified month and year.
     *
     * Preconditions:
     * - The `habitatId` parameter must not be null or empty.
     * - The `date` parameter must not be null.
     * - The `myActivities` list must not be null and should contain valid
     * `Activity` objects.
     *
     *
     * @param habitatId The ID of the habitat to filter the feeding activities by.
     * @param date      The {@link LocalDate} object representing the month and year
     *                  to filter the feeding activities by.
     * @return The number of feeding activities that match the specified habitat ID
     *         and date.
     */
    public int countFeedingActivitiesInHabitatInMonth(String habitatId, LocalDate date) {
        int counter = 0;

        for (Activity myActivity : myActivities) {
            LocalDate executionDate = myActivity.getExecutionDate();

            if (myActivity.getHabitat().getId().equals(habitatId) && executionDate != null
                    && executionDate.getMonth().equals(date.getMonth())
                    && executionDate.getYear() == date.getYear()) {
                if (myActivity instanceof FeedingActivity) {
                    counter += 1;
                }
            }
        }
        return counter;

    }

    /**
     * Retrieves a concatenated string of details for all activities performed by a
     * specific employee
     * during the specified year.
     *
     * Preconditions:
     * - The `idEmployee` parameter must not be null or empty.
     * - The `date` parameter must not be null.
     * - The `myActivities` list must not be null and should contain valid
     * `Activity` objects.
     *
     *
     * @param idEmployee The ID of the employee whose activities are to be
     *                   retrieved.
     * @param date       The {@link LocalDate} object representing the year to
     *                   filter the activities by.
     * @return A string containing the details of all activities performed by the
     *         specified employee
     *         during the given year. If no activities match, an empty string is
     *         returned.
     */
    public String getDetailsFromEmployeeActivities(String idEmployee, LocalDate date) {

        String list = "";

        for (Activity myActivity : myActivities) {
            LocalDate executionDate = myActivity.getExecutionDate();

            if (myActivity.getResponsible().getId().equals(idEmployee) && executionDate.getYear() == date.getYear()) {

                list += myActivity.toString();
            }

        }
        return list;

    }

    /**
     * Builds a text inventory of the zoo's habitats, employees, animals, and
     * activities. Each non-null item is represented by its class name and
     * identifier on a separate line.
     *
     * @return the inventory as a newline-delimited string, or an empty string if
     *         there are no non-null items to include
     */
    public String buildCodeInventory() {

        String inventory = "";

        ArrayList<Identifiable> allIdentifiables = new ArrayList<Identifiable>();

        // Habitat (List), Employee (List), Animal (List), Activity (List)

        allIdentifiables.addAll(myHabitats);
        allIdentifiables.addAll(myEmployees);
        allIdentifiables.addAll(allAnimals);
        allIdentifiables.addAll(myActivities);
        allIdentifiables.addAll(myAlerts);

        for (Identifiable myIdentifiable : allIdentifiables) {

            if (myIdentifiable != null) {
                inventory += myIdentifiable.getClass() + "-" + myIdentifiable.getId() + "\n";
            }

        }

        return inventory;

    }

    public String getActivitiesInEmployee(String employeeID) {
        String list = "";
        Employee targetEmployee = findEmployee(employeeID);

        if (targetEmployee != null) {
            list += targetEmployee.getAssingActivities();
        }
        return list;
    }

    public boolean cancelActivity(String activityId) throws ZooCareBusinessException {

        Activity myActivity = findActivity(activityId);

        if (myActivity == null) {
            throw new ZooCareBusinessException(ZooCareBusinessException.ACTIVITY_NOT_FOUND);
        }

        if (myActivity.isFinished()) {
            throw new ZooCareBusinessException(ZooCareBusinessException.ACTIVITY_FINISHED);
        }

        myActivity.getResponsible().removeActivity(myActivity);
        myActivity.setStatus(ActivityStatus.CANCELED);
        return true;

    }

    public Activity findActivity(String activityId) {

        Activity foundActivity = null;

        for (int i = 0; i < myActivities.size(); i++) {
            if (myActivities.get(i).getId().equals(activityId)) {
                foundActivity = myActivities.get(i);
            }
        }
        return foundActivity;
    }

    public String getActiveActivitiesList() {

        String list = "";

        for (Activity activity : myActivities) {
            if (activity.isAssing()) {
                list += activity.toString() + "\n";
            }
            if (list.equals("")) {
                list = null;
            }
        }

        return list;
    }

    public int getActivityType(String id) {

        int type = 0;

        Activity activity = findActivity(id);

        if (activity != null) {

            if (activity instanceof CleaningActivity) {
                type = 1;
            }
            if (activity instanceof EnrichmentActivity) {
                type = 2;
            }
            if (activity instanceof FeedingActivity) {
                type = 3;
            }
            if (activity instanceof MedicControl) {
                type = 4;
            }
        }

        return type;
    }

    public boolean finalizeCleaningAct(String activityId, int cost, String description, String usedProducts) {

        Activity activity = findActivity(activityId);

        if (activity != null) {
            activity.setExecutionDate(LocalDate.now());
            activity.setCost(cost);
            String[] myUsedproducts = usedProducts.split("-");

            CleaningActivity act = (CleaningActivity) activity;
            act.setUsedProducts(new ArrayList<String>(Arrays.asList(myUsedproducts)));
            act.setDescription(description);
            act.setStatus(ActivityStatus.FINISHED);
            return true;
        }

        return false;
    }

    public boolean finalizeEnrichAct(String activityId, int cost, int durationDays, String description,
            String usedProducts) {

        Activity activity = findActivity(activityId);

        if (activity != null) {
            activity.setExecutionDate(LocalDate.now());
            activity.setCost(cost);
            String[] myUsedproducts = usedProducts.split("-");

            EnrichmentActivity act = (EnrichmentActivity) activity;
            act.setDurationInDays(durationDays);
            act.setDescription(description);
            act.setUsedProducts(new ArrayList<String>(Arrays.asList(myUsedproducts)));
            act.setStatus(ActivityStatus.FINISHED);
        }

        return false;
    }

    public boolean finalizeFeedingAct(String activityId, int cost) {

        Activity activity = findActivity(activityId);

        if (activity != null) {
            activity.setExecutionDate(LocalDate.now());
            activity.setCost(cost);
            activity.setStatus(ActivityStatus.FINISHED);
        }

        return false;
    }

    public boolean finalizeMedicAct(String activityId, int cost, double weight, int animalHealth,
            LocalDate followUpDate) {

        Activity activity = findActivity(activityId);

        if (activity != null) {
            activity.setExecutionDate(LocalDate.now());
            activity.setCost(cost);

            MedicControl act = (MedicControl) activity;
            act.setWeight(weight);
            act.setHealthStatus(calcHealthStatus(animalHealth));
            act.setFollowUpDate(followUpDate);
            act.setStatus(ActivityStatus.FINISHED);
        }

        return false;
    }

    public boolean tranferAnimalToCV(String animalId) {

        Animal targetAnimal = null;
        Habitat oldHabitat = null;
        Habitat medicControl = null;

        for (int i = 0; i < myHabitats.size(); i++) {
            if (myHabitats.get(i).getAnimal(animalId) != null) {
                targetAnimal = myHabitats.get(i).getAnimal(animalId);
                oldHabitat = myHabitats.get(i);
                break;
            }
        }

        for (int i = 0; i < myHabitats.size(); i++) {
            if (myHabitats.get(i).getId().equals("CV")) {
                medicControl = myHabitats.get(i);
                break;
            }
        }

        if (medicControl != null && oldHabitat != null && targetAnimal != null && oldHabitat != medicControl) {
            if (medicControl.getNumberOfAnimals() < medicControl.getMaxCapacity()) {
                oldHabitat.removeAnimal(targetAnimal);
                medicControl.addAnimal(targetAnimal);
                return true;
            }
        }

        return false;
    }

    public boolean putInactiveEmployee(String employeeId) {

        Employee employee = findEmployee(employeeId);

        if (employee != null) {

            for (int i = 0; i < myActivities.size(); i++) {
                if (myActivities.get(i).getResponsible().getId().equals(employee.getId())) {

                    if (hasActiveActivity(employeeId)) {

                        return false;

                    } else {
                        employee.setState(EmployeeState.INACTIVE);
                        return true;
                    }

                }
            }

        }
        if (employee.activitiesIsEmpty()) {
            employee.setState(EmployeeState.INACTIVE);
            return true;
        }

        return false;
    }

    public boolean hasActiveActivity(String employeeId) {

        Employee employee = findEmployee(employeeId);

        for (int i = 0; i < myActivities.size(); i++) {
            if (myActivities.get(i).getResponsible().getId().equals(employee.getId())) {

                if (myActivities.get(i).getStatus() == ActivityStatus.ASSIGNED) {
                    return true;
                }
            }
        }

        return false;
    }

    public Activity getActiveActivityInEmployee(String employeeId) {

        Employee employee = findEmployee(employeeId);

        if (employee != null) {
            for (int i = 0; i < myActivities.size(); i++) {
                if (myActivities.get(i).getResponsible().getId().equals(employee.getId())
                        && myActivities.get(i).getResponsible() != null) {

                    if (myActivities.get(i).getStatus() == ActivityStatus.ASSIGNED) {
                        return myActivities.get(i);
                    }

                }
            }
        }

        return null;
    }

    public boolean changeResponsableInActivity(String act, String employeeId, String newEmployee) {

        Employee employee = findEmployee(employeeId);
        Activity activity = findActivity(act);
        Employee replacementEmployee = findEmployee(newEmployee);

        if (employee != null && activity != null && replacementEmployee != null) {
            if (activity.getResponsible().getId().equals(employee.getId())) {

                if (replacementEmployee.addActivity(activity)) {
                    employee.removeActivity(activity);
                    activity.setResponsible(replacementEmployee);
                    return true;
                }
            }
        }
        return false;
    }

    public String zooSummary() throws IOException{
        String list = "";
        
        list += "=== Habitats por Estado ===\n";
        for (int i = 0; i < myHabitats.size(); i++) {
            if (myHabitats.get(i).getState() == StateType.ACTIVE) {
                list += "Habitats Activos: \n" + myHabitats.get(i).toString() + "\n";
            }
            if (myHabitats.get(i).getState() == StateType.CONSTRUCTION) {
                list += "Habitats en Construccion: \n" + myHabitats.get(i).toString() + "\n";
            }
            if (myHabitats.get(i).getState() == StateType.MANTEINMENT) {
                list += "Habitats en Mantenimiento: \n" + myHabitats.get(i).toString() + "\n";
            }
            if (myHabitats.get(i).getState() == StateType.RETIRED) {
                list += "Habitats Retirados: \n" + myHabitats.get(i).toString() + "\n";
            }
        }
        list += "=== Animales por Estado ===\n";
        for (int i = 0; i < allAnimals.size(); i++) {
            if (allAnimals.get(i).getHealthStatus() == HealthStatus.HEALTHY) {
                list += "Animales Saludables: \n" + allAnimals.get(i).toString() + "\n";
            }
            if (allAnimals.get(i).getHealthStatus() == HealthStatus.OBSERVATION) {
                list += "Animales en Observacion: \n" + allAnimals.get(i).toString() + "\n";
            }
            if (allAnimals.get(i).getHealthStatus() == HealthStatus.CUARENTINE) {
                list += "Animales en Cuarentena: \n" + allAnimals.get(i).toString() + "\n";
            }
            if (allAnimals.get(i).getHealthStatus() == HealthStatus.RECOVER) {
                list += "Animales en Recuperacion: \n" + allAnimals.get(i).toString() + "\n";
            }
        }
        list += "=== Actividades Asignadas ===";
        for (int i = 0; i < myActivities.size(); i++) {
            if (myActivities.get(i).getStatus() == ActivityStatus.ASSIGNED) {
                list += myActivities.get(i).toString() + "\n";
            }
        }
        list += "=== Costo Operativo ===\n";
        list += "Costo: " + getTotalZooCost();
        ReportManager.exportReport("Resumen Operativo Completo", list);


        return list;
    }

    public double getTotalZooCost(){
        double totalCost = 0.0;

        totalCost = (this.budget - getTotalHabitatsBudget()) + getTotalActivityCost();


        return totalCost;
    }

    public double getTotalActivityCost(){
        double totalCost = 0.0;

        for (int i = 0; i < myActivities.size(); i++) {
            if (myActivities.get(i).getStatus() == ActivityStatus.FINISHED) {
                totalCost += myActivities.get(i).getCost();
            }
        }

        return totalCost;
    }

    public String detailAnimalreport(String animalId) throws IOException{
        String list = "";
        String medicHistory = "";
        double cost = 0.0;

        Animal animal = findAnimal(animalId);

            for (int i = 0; i < myActivities.size(); i++) {
                if (myActivities.get(i) instanceof MedicControl && myActivities.get(i).getStatus() == ActivityStatus.FINISHED) {
                    MedicControl act = (MedicControl) myActivities.get(i);

                    if (act.getAnimal().getId().equals(animalId)) {
                        medicHistory += act.toString() + "\n";
                        cost += act.getCost();
                    }
                }
            }

            if (animal != null) {
                list += animal.toString() + "\n" + "Racion Estimada:" + animal.calculateMonthlyFoodCost() + "\n"
                + "- Historial de Controles Medicos\n" + medicHistory + "\n" + "Costo Operativo Mensual: " + cost;
                ReportManager.exportReport("Reporte detallado de" + animal.getName(), medicHistory);
            } else {
                list = null;
            }
                

        return list;
    }

    public String getActivitiesReport() throws IOException{
        String list = "";

        if (myActivities.isEmpty()) {
            list = null;
        } else{
            for (int i = 0; i < myActivities.size(); i++) {
                if (myActivities.get(i).getStatus() == ActivityStatus.ASSIGNED) {
                    list += "Actividades Asignadas: \n" + myActivities.get(i).toString() +"\n";
                }
                if (myActivities.get(i).getStatus() == ActivityStatus.FINISHED) {
                    list += "Actividades Finalizadas: \n" + myActivities.get(i).toString() +"\n";
                }
                if (myActivities.get(i).getStatus() == ActivityStatus.CANCELED) {
                    list += "Actividades Asignadas: \n" + myActivities.get(i).toString() +"\n";
                }
            }
            ReportManager.exportReport("Reporte de Actividades", list);
        } 

        return list;
    }

}