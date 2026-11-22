package model.habitat;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;

import model.Identifiable;
import model.animal.Animal;
import model.animal.AnimalStatusType;
import model.employee.Employee;
import model.employee.RoleType;

/**
 * Description: Represents a physical habitat in the zoo, managing housed
 * animals and assigned employees.
 */

public class Habitat implements Identifiable, Serializable {

    private static final long SerialVersionUID = 10l;

    private String HabitatId;
    private String name;
    private EnvironmentType environment;
    private double temperature;
    private int maxCapacity;
    private double area;
    private double budget;
    private StateType state;

    private ArrayList<Animal> myAnimals;
    private ArrayList<Employee> assignEmployees;

    /**
     * Description: Constructs a new Habitat object with its attributes and
     * initializes the internal animal and employee lists.
     * 
     * @param HabitatId   Unique identifier for the habitat.
     * @param name        Name of the habitat.
     * @param environment EnvironmentType enum specifying the habitat's environment.
     * @param temperature Temperature value of the habitat.
     * @param area        Area size of the habitat.
     * @param maxCapacity Maximum capacity of animals allowed.
     * @param budget      Budget allocated for the habitat.
     * @param state       Operational state of the habitat.
     *                    Precondition: Parameters must be valid and non-null for
     *                    reference types.
     *                    Postcondition: A new Habitat instance is created with
     *                    initialized collections.
     */

    public Habitat(String HabitatId, String name, EnvironmentType environment, double temperature, double area,
            int maxCapacity, double budget, StateType state) {
        this.HabitatId = HabitatId;
        this.name = name;
        this.environment = environment;
        this.temperature = temperature;
        this.area = area;
        this.maxCapacity = maxCapacity;
        this.budget = budget;
        this.state = state;

        myAnimals = new ArrayList<Animal>();
        assignEmployees = new ArrayList<Employee>();

    }

    /**
     * Description: Adds an animal to the habitat if capacity allows, it is not
     * already contained, and environments match.
     * 
     * @param myAnimal Animal object to add.
     * @return True if the animal is successfully added, false otherwise.
     *         Precondition: myAnimals list must be initialized.
     *         Postcondition: The animal is added to the habitat's animal collection
     *         if rules pass.
     */

    public boolean addAnimal(Animal myAnimal) {

        if (myAnimal != null && myAnimals.size() < maxCapacity && !myAnimals.contains(myAnimal)) {

            if (environment == myAnimal.getRequiereEnvironment()) {
                return myAnimals.add(myAnimal);
            }
        }

        return false;
    }

    /**
     * Description: Assigns an employee to the habitat, validating medical
     * environment restrictions.
     * 
     * @param myEmployee Employee object to assign.
     * @return True if the employee is successfully assigned, false otherwise.
     *         Precondition: assignEmployees list must be initialized.
     *         Postcondition: The employee is added to the habitat's employee list
     *         if role and environment constraints are satisfied.
     */

    public boolean assingEmployee(Employee myEmployee) {
        if (myEmployee != null && !assignEmployees.contains(myEmployee)) {

            if (environment == EnvironmentType.MEDICAL) {
                if (myEmployee.getRole() != RoleType.VETERINARY) {
                    return false;
                }
            }
        }
        return assignEmployees.add(myEmployee);
    }

    /**
     * Description: Gets the name of the habitat.
     * 
     * @return The habitat name as a String.
     *         Precondition: Habitat must be initialized.
     *         Postcondition: Returns the name attribute value.
     */

    public String getName() {
        return name;
    }

    /**
     * Description: Gets the environment type of the habitat.
     * 
     * @return The EnvironmentType enum constant.
     *         Precondition: Habitat must be initialized.
     *         Postcondition: Returns the environment attribute value.
     */

    public EnvironmentType getEnvironment() {
        return environment;
    }

    /**
     * Description: Generates a list string containing IDs and names of animals in
     * this habitat.
     * 
     * @return A formatted String of the animal list.
     *         Precondition: myAnimals list must be initialized.
     *         Postcondition: Returns concatenated animal summaries.
     */

    public String getAnimalList() {
        String animallist = "";

        for (int i = 0; i < myAnimals.size(); i++) {
            if (myAnimals.get(i) != null) {
                animallist += myAnimals.get(i).getId() + "-" + myAnimals.get(i).getName() + "\n";
            }
        }

        return animallist;
    }

    /**
     * Description: Finds and returns an animal object by its ID within this
     * habitat.
     * 
     * @param AnimalId Identifier code of the animal.
     * @return The Animal object if found, or null otherwise.
     *         Precondition: myAnimals list must be initialized.
     *         Postcondition: Iterates through the list and returns the matching
     *         animal or null.
     */

    public Animal getAnimal(String AnimalId) {
        for (int i = 0; i < myAnimals.size(); i++) {
            if (myAnimals.get(i) != null && myAnimals.get(i).getId().equals(AnimalId)) {
                return myAnimals.get(i);
            }
        }
        return null;
    }

    /**
     * Description: Calculates the total food cost for all animals residing in this
     * habitat.
     * 
     * @return Total monthly food cost as a double.
     *         Precondition: myAnimals list must be initialized.
     *         Postcondition: Accumulates and returns the sum of all individual
     *         animal food costs.
     */

    public double getTotalFoodCost() {
        double totalHabitatCost = 0.0;
        for (int i = 0; i < myAnimals.size(); i++) {
            if (myAnimals.get(i) != null) {
                totalHabitatCost += myAnimals.get(i).calculateMonthlyFoodCost();
            }
        }
        return totalHabitatCost;
    }

    /**
     * Description: Generates a detail string of monthly food costs for each animal
     * in the habitat.
     * 
     * @return A formatted String with individual animal food cost details.
     *         Precondition: myAnimals list must be initialized.
     *         Postcondition: Returns text summary of animal food costs.
     */

    public String getAnimalsFoodCostDetails() {
        String details = "";
        for (int i = 0; i < myAnimals.size(); i++) {
            if (myAnimals.get(i) != null) {
                details += "- Animal: " + myAnimals.get(i).getName() + "Costo mensual: $"
                        + myAnimals.get(i).calculateMonthlyFoodCost() + "\n";
            }
        }
        return details;
    }

    /**
     * Description: Calculates the percentage of occupancy of the habitat based on
     * current animals and max capacity.
     * 
     * @return Occupancy rate percentage as a double.
     *         Precondition: maxCapacity must be greater than 0.
     *         Postcondition: Computes and returns the occupancy percentage.
     */

    public double calculateOccupancyPercentage() {
        if (maxCapacity > 0) {

            return ((double) myAnimals.size() / maxCapacity) * 100.0;
        }
        return 0.0;
    }

    /**
     * Description: Gets the current count of animals in the habitat.
     * 
     * @return Number of animals as an integer.
     *         Precondition: myAnimals list must be initialized.
     *         Postcondition: Returns the size of the animal collection.
     */

    public int getCurrentAnimalsCount() {
        return myAnimals.size();
    }

    /**
     * Description: Gets the monthly food cost for a specific animal matched by
     * name.
     * 
     * @param animalName Name of the animal to search for.
     * @return The monthly food cost as a double, or 0.0 if not found.
     *         Precondition: myAnimals list must be initialized.
     *         Postcondition: Searches animals and returns the food cost of the
     *         matching animal.
     */

    public double getAnimalMonthlyFoodCost(String animalName) {

        for (int i = 0; i < myAnimals.size(); i++) {
            if (myAnimals.get(i).getId().equals(animalName)) {
                return myAnimals.get(i).calculateMonthlyFoodCost();
            }

        }

        return 0.0;
    }

    /**
     * Description: Removes an animal from the habitat's animal collection.
     * 
     * @param animal Animal object to remove.
     * @return True if the animal was successfully removed, false otherwise.
     *         Precondition: myAnimals list must be initialized.
     *         Postcondition: Removes the animal from the list and returns operation
     *         result.
     */

    public boolean removeAnimal(Animal animal) {
        return myAnimals.remove(animal);
    }

    /**
     * Retrieves the code of the habitat.
     *
     * @return The code of the habitat.
     */
    @Override
    public String getId() {
        return HabitatId;
    }

    /**
     * Description: Gets the temperature of the habitat.
     * 
     * @return Temperature as a double.
     *         Precondition: Habitat must be initialized.
     *         Postcondition: Returns the temperature attribute value.
     */

    public double getTemperature() {
        return temperature;
    }

    /**
     * Description: Gets the maximum capacity of the habitat.
     * 
     * @return Max capacity as an integer.
     *         Precondition: Habitat must be initialized.
     *         Postcondition: Returns the maxCapacity attribute value.
     */

    public int getMaxCapacity() {
        return maxCapacity;
    }

    /**
     * Description: Gets the area size of the habitat.
     * 
     * @return Area as a double.
     *         Precondition: Habitat must be initialized.
     *         Postcondition: Returns the area attribute value.
     */

    public double getArea() {
        return area;
    }

    /**
     * Description: Gets the allocated budget of the habitat.
     * 
     * @return Budget as a double.
     *         Precondition: Habitat must be initialized.
     *         Postcondition: Returns the budget attribute value.
     */

    public double getBudget() {
        return budget;
    }

    /**
     * Description: Gets the operational state of the habitat.
     * 
     * @return State as a String.
     *         Precondition: Habitat must be initialized.
     *         Postcondition: Returns the state attribute value.
     */

    public StateType getState() {
        return state;
    }

    /**
     * Description: Generates a complete information report string including
     * attributes, animals, and assigned employees.
     * 
     * @return A formatted String containing full habitat details.
     *         Precondition: Habitat collections must be initialized.
     *         Postcondition: Returns a comprehensive summary report string.
     */

    public String getCompleteHabitatInfo() {
        String info = "Codigo: " + HabitatId + " - " + "Nombre: " + name + "\n" + "Ambiente: "
                + environment.getTypeName() + " - " + "Temperatura: " + temperature + " - " + "Area: " + area + "\n" +
                "Capacidad Máxima: " + maxCapacity + " - " + "Presupuesto: $" + budget + " - " + "Estado: " + state + "\n"
                + " Animales en este hábitat\n";

        if (myAnimals.isEmpty()) {
            info += "No hay animales registrados\n";
        } else {
            for (int i = 0; i < myAnimals.size(); i++) {
                if (myAnimals.get(i) != null) {
                    info += "  - ID: " + myAnimals.get(i).getId() + ", Nombre: " + myAnimals.get(i).getName()
                            + " Especie: " + myAnimals.get(i).getSpecie() + "\n";
                }
            }
        }

        info += " Empleados asignados\n";
        if (assignEmployees.isEmpty()) {
            info += "No hay empleados asignados\n";
        } else {
            for (int i = 0; i < assignEmployees.size(); i++) {
                if (assignEmployees.get(i) != null) {
                    info += "  - " + assignEmployees.get(i).getName() + " - "
                            + assignEmployees.get(i).getRole().getTypeName() + "\n";
                }
            }
        }

        return info;
    }

    /**
     * Description: Generates a list string of active animals present in this
     * habitat.
     * 
     * @return A formatted String containing active animal details.
     *         Precondition: myAnimals list must be initialized.
     *         Postcondition: Returns filtered list of active animals.
     */

    public String getActiveAnimalsList() {
        String activeList = "";
        for (int i = 0; i < myAnimals.size(); i++) {
            if (myAnimals.get(i) != null && myAnimals.get(i).getAnimalStatus() == AnimalStatusType.ACTIVE) {
                activeList += "- ID: " + myAnimals.get(i).getId() + " - Nombre: " + myAnimals.get(i).getName()
                        + "- Especie: " + myAnimals.get(i).getSpecie() + " - Dieta: " + myAnimals.get(i).getDietType().getTypeName() + " - Estado de Salud: " + 
                        myAnimals.get(i).getHealthStatus().getTypeName();
            }
        }
        return activeList;
    }

    /**
     * Description: Sets or updates the name of the habitat.
     * 
     * @param name New name value.
     *             Precondition: Habitat must be initialized.
     *             Postcondition: The name attribute is updated.
     */

    public void setName(String name) {
        this.name = name;
    }

    /**
     * Description: Sets or updates the environment type of the habitat.
     * 
     * @param environment New EnvironmentType enum value.
     *                    Precondition: Habitat must be initialized.
     *                    Postcondition: The environment attribute is updated.
     */

    public void setEnvironment(EnvironmentType environment) {
        this.environment = environment;
    }

    /**
     * Description: Sets or updates the temperature of the habitat.
     * 
     * @param temperature New temperature value.
     *                    Precondition: Habitat must be initialized.
     *                    Postcondition: The temperature attribute is updated.
     */

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    /**
     * Description: Sets or updates the maximum capacity of the habitat.
     * 
     * @param maxCapacity New maximum capacity value.
     *                    Precondition: Habitat must be initialized.
     *                    Postcondition: The maxCapacity attribute is updated.
     */

    public void setMaxCapacity(int maxCapacity) {
        this.maxCapacity = maxCapacity;
    }

    /**
     * Description: Sets or updates the area size of the habitat.
     * 
     * @param area New area value.
     *             Precondition: Habitat must be initialized.
     *             Postcondition: The area attribute is updated.
     */

    public void setArea(double area) {
        this.area = area;
    }

    /**
     * Description: Sets or updates the budget of the habitat.
     * 
     * @param budget New budget value.
     *               Precondition: Habitat must be initialized.
     *               Postcondition: The budget attribute is updated.
     */

    public void setBudget(double budget) {
        this.budget = budget;
    }

    /**
     * Description: Sets or updates the operational state of the habitat.
     * 
     * @param state New state string.
     *              Precondition: Habitat must be initialized.
     *              Postcondition: The state attribute is updated.
     */

    public void setState(StateType state) {
        this.state = state;
    }

    public int getNumberOfAnimals() {
        int counter = 0;

        for (int i = 0; i < myAnimals.size(); i++) {
            if (myAnimals.get(i) != null) {
                counter++;
            }
        }

        return counter;
    }

    /**
     * Generates a unique identifier based on the current date and time.
     *
     * @return A unique identifier as a String, generated using the current minute,
     *         second, and nanosecond.
     */
    @Override
    public String generateId() {
        return "H" + LocalDateTime.now().getMinute() + LocalDateTime.now().getSecond() + LocalDateTime.now().getNano();
    }

    @Override
    public String toString() {
        return "Habitat [HabitatId=" + HabitatId + ", name=" + name + ", environment=" + environment + ", temperature="
                + temperature + ", maxCapacity=" + maxCapacity + ", area=" + area + ", budget=" + budget + ", state="
                + state + ", myAnimals=" + myAnimals + ", assignEmployees=" + assignEmployees + "]";
    }

    


}
