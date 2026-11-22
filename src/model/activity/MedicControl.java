package model.activity;

import java.time.LocalDate;
import java.util.ArrayList;

import model.employee.Employee;
import model.habitat.Habitat;
import model.animal.*;

public class MedicControl extends Activity {

    private Animal animal;
    private String controlReason;
    private double weight;
    private String observations;
    private ArrayList<String> medications;
    private double treatmentCost;
    private HealthStatus animalHealth;
    private LocalDate followUpDate;

    public MedicControl(Habitat habitat, Employee responsible, LocalDate schedulingDate, Animal animal,
            String controlReason,
            double weight, String observations, ArrayList<String> medications, double treatmentCost) {
        super(habitat, responsible, schedulingDate);
        this.animal = animal;
        this.controlReason = controlReason;
        this.weight = weight;
        this.observations = observations;
        this.medications = medications;
        this.treatmentCost = treatmentCost;
        this.animalHealth = null;
        this.followUpDate = null;
    }

    public ArrayList<String> getMedications() {
        return medications;
    }

    public Animal getAnimal(){
        return this.animal;
    }

    public void setMedications(ArrayList<String> medications) {
        this.medications = medications;
    }

    public double getTreatmentCost() {
        return treatmentCost;
    }

    public void setTreatmentCost(double treatmentCost) {
        this.treatmentCost = treatmentCost;
    }

    public LocalDate getFollowUpDate() {
        return followUpDate;
    }

    public void setFollowUpDate(LocalDate followUpDate) {
        this.followUpDate = followUpDate;
    }

    public void setHealthStatus(HealthStatus animalHealth) {
        this.animalHealth = animalHealth;
        this.animal.setHealthStatus(animalHealth);
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "MedicControl [animal=" + animal + ", controlReason=" + controlReason + ", weight=" + weight
                + ", observations=" + observations + ", medications=" + medications + ", treatmentCost=" + treatmentCost
                + ", animalHealth=" + animalHealth + ", followUpDate=" + followUpDate + "]";
    }

}
