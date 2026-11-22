package model.animal;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

import model.Identifiable;
import model.habitat.EnvironmentType;

/**
 * Description: Represents an animal in the zoo system, storing its details,
 * status, and calculating food costs.
 */

public class Animal implements Identifiable, Serializable {

    private static final long SerialVersionUID = 10l;

    private String animalId;
    private String name;
    private String specie;
    private GenderType gender;
    private double weight;
    private DietType dietType;
    private EnvironmentType requiereEnvironment;
    private LifeStage lifeStage;
    private HealthStatus healthStatus;
    private String retirementReason;
    private AnimalStatusType animalStatus;
    private LocalDate retirementDate;

    // Constructor

    /**
     * Description: Constructs a new Animal object with its attributes and initial
     * status.
     * 
     * @param animalId            Unique identifier code for the animal.
     * @param name                Name of the animal.
     * @param specie              Species of the animal.
     * @param gender              Gender of the animal.
     * @param weight              Weight of the animal.
     * @param dietType            DietType enum constant.
     * @param requiereEnvironment EnvironmentType enum constant required by the
     *                            animal.
     * @param lifeStage           LifeStage enum constant.
     * @param healthStatus        HealthStatus enum constant.
     * @param status              AnimalStatusType enum constant.
     *                            Precondition: Parameters must be valid and
     *                            non-null for reference types.
     *                            Postcondition: An Animal instance is created with
     *                            all provided attributes initialized.
     */

    public Animal(String animalId, String name, String specie, GenderType gender, double weight, DietType dietType,
            EnvironmentType requiereEnvironment, LifeStage lifeStage, HealthStatus healthStatus) {
        this.animalId = animalId;
        this.name = name;
        this.specie = specie;
        this.gender = gender;
        this.weight = weight;
        this.dietType = dietType;
        this.requiereEnvironment = requiereEnvironment;
        this.lifeStage = lifeStage;
        this.healthStatus = healthStatus;
        this.animalStatus = AnimalStatusType.ACTIVE;
        this.retirementReason = null;
        this.retirementDate = null;

    }

    /**
     * Description: Sets the retirement details for the animal, updating reason,
     * status, and date.
     * 
     * @param reason Reason for retirement.
     * @param status New AnimalStatusType enum constant.
     * @param date   Date of retirement.
     *               Precondition: Animal instance must exist.
     *               Postcondition: Retirement attributes are updated.
     */

    public void setRetirementData(String reason, AnimalStatusType status, LocalDate date) {
        this.retirementReason = reason;
        this.animalStatus = status;
        this.retirementDate = date;
    }

    /**
     * Description: Gets the name of the animal.
     * 
     * @return The animal name as a String.
     *         Precondition: Animal object must be initialized.
     *         Postcondition: Returns the name attribute value.
     */

    public String getName() {
        return name;
    }

    /**
     * Description: Calculates the monthly food cost for the animal based on its
     * diet, weight, life stage, and health status.
     * 
     * @return The calculated monthly food cost as a double.
     *         Precondition: Animal must have valid weight, diet type, life stage,
     *         and health status.
     *         Postcondition: Computes and returns the monthly feeding cost.
     */

    public double calculateMonthlyFoodCost() {
        double monthlyFoodCost = 0.0;
        double rationfactor = 0.0;
        double rationUnitCost = 0.0;
        double incrementPercentage = 0.0;

        if (dietType == DietType.HERBIVORE) {
            rationfactor = 0.04;
            rationUnitCost = 8800;
        } else if (dietType == DietType.CARNIVORE) {
            rationfactor = 0.03;
            rationUnitCost = 25500;
        } else if (dietType == DietType.OMNIVORE) {
            rationfactor = 0.035;
            rationUnitCost = 15200;
        } else if (dietType == DietType.INSECTIVORE) {
            rationfactor = 0.025;
            rationUnitCost = 18300;
        }

        if (lifeStage == LifeStage.YOUNG) {
            incrementPercentage = 0.20;
        } else if (healthStatus == HealthStatus.CUARENTINE || healthStatus == HealthStatus.RECOVER) {
            incrementPercentage = 0.15;
        } else if (healthStatus == HealthStatus.OBSERVATION) {
            incrementPercentage = 0.10;
        }

        monthlyFoodCost = 30 * rationUnitCost * (weight * rationfactor) * (1 + incrementPercentage);

        return monthlyFoodCost;
    }

    /**
     * Gets the unique identifier of the animal.
     *
     * @return The unique identifier of the animal.
     */
    @Override
    public String getId() {

        return animalId;

    }

    /**
     * Generates a unique identifier based on the current date and time.
     *
     * @return A unique identifier as a String, generated using the current minute,
     *         second, and nanosecond.
     */
    @Override
    public String generateId() {

        return "A" + LocalDateTime.now().getMinute() + LocalDateTime.now().getSecond() + LocalDateTime.now().getNano();

    }

    /**
     * Description: Gets the species of the animal.
     * 
     * @return The species as a String.
     *         Precondition: Animal object must be initialized.
     *         Postcondition: Returns the specie attribute value.
     */

    public String getSpecie() {
        return specie;
    }

    /**
     * Description: Gets the gender of the animal.
     * 
     * @return The gender as a String.
     *         Precondition: Animal object must be initialized.
     *         Postcondition: Returns the gender attribute value.
     */

    public GenderType getGender() {
        return gender;
    }

    /**
     * Description: Gets the weight of the animal.
     * 
     * @return The weight as a double.
     *         Precondition: Animal object must be initialized.
     *         Postcondition: Returns the weight attribute value.
     */

    public double getWeight() {
        return weight;
    }

    /**
     * Description: Gets the diet type of the animal.
     * 
     * @return The DietType enum constant.
     *         Precondition: Animal object must be initialized.
     *         Postcondition: Returns the dietType attribute value.
     */

    public DietType getDietType() {
        return dietType;
    }

    /**
     * Description: Gets the required environment type for the animal.
     * 
     * @return The EnvironmentType enum constant.
     *         Precondition: Animal object must be initialized.
     *         Postcondition: Returns the requiereEnvironment attribute value.
     */

    public EnvironmentType getRequiereEnvironment() {
        return requiereEnvironment;
    }

    /**
     * Description: Gets the life stage of the animal.
     * 
     * @return The LifeStage enum constant.
     *         Precondition: Animal object must be initialized.
     *         Postcondition: Returns the lifeStage attribute value.
     */

    public LifeStage getLifeStage() {
        return lifeStage;
    }

    /**
     * Description: Gets the health status of the animal.
     * 
     * @return The HealthStatus enum constant.
     *         Precondition: Animal object must be initialized.
     *         Postcondition: Returns the healthStatus attribute value.
     */

    public HealthStatus getHealthStatus() {
        return healthStatus;
    }

    /**
     * Description: Gets the animal status type.
     * 
     * @return The AnimalStatusType enum constant.
     *         Precondition: Animal object must be initialized.
     *         Postcondition: Returns the animalStatus attribute value.
     */

    public AnimalStatusType getAnimalStatus() {
        return animalStatus;
    }

    /**
     * Description: Sets or updates the name of the animal.
     * 
     * @param name New name value.
     *             Precondition: Animal object must be initialized.
     *             Postcondition: The name attribute is updated.
     */

    public void setName(String name) {
        this.name = name;
    }

    /**
     * Description: Sets or updates the weight of the animal.
     * 
     * @param weight New weight value.
     *               Precondition: Animal object must be initialized.
     *               Postcondition: The weight attribute is updated.
     */

    public void setWeight(double weight) {
        this.weight = weight;
    }

    /**
     * Description: Sets or updates the diet type of the animal.
     * 
     * @param dietType New DietType enum constant.
     *                 Precondition: Animal object must be initialized.
     *                 Postcondition: The dietType attribute is updated.
     */

    public void setDietType(DietType dietType) {
        this.dietType = dietType;
    }

    /**
     * Description: Sets or updates the life stage of the animal.
     * 
     * @param lifeStage New LifeStage enum constant.
     *                  Precondition: Animal object must be initialized.
     *                  Postcondition: The lifeStage attribute is updated.
     */

    public void setLifeStage(LifeStage lifeStage) {
        this.lifeStage = lifeStage;
    }

    /**
     * Description: Sets or updates the health status of the animal.
     * 
     * @param healthStatus New HealthStatus enum constant.
     *                     Precondition: Animal object must be initialized.
     *                     Postcondition: The healthStatus attribute is updated.
     */

    public void setHealthStatus(HealthStatus healthStatus) {
        this.healthStatus = healthStatus;
    }

    public void setAnimalStatusType(AnimalStatusType animalStatus) {
        this.animalStatus = animalStatus;
    }

    public boolean isActive() {
        if (this.animalStatus == AnimalStatusType.ACTIVE) {
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return "Animal [animalId=" + animalId + ", name=" + name + ", specie=" + specie + ", gender=" + gender
                + ", weight=" + weight + ", dietType=" + dietType + ", requiereEnvironment=" + requiereEnvironment
                + ", lifeStage=" + lifeStage + ", healthStatus=" + healthStatus + ", retirementReason="
                + retirementReason + ", animalStatus=" + animalStatus + ", retirementDate=" + retirementDate + "]";
    }

    

}
