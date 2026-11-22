package model.employee;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;

import model.Identifiable;
import model.activity.*;

/**
 * Description: Represents an animal in the zoo system, storing its details,
 * status, and calculating food costs.
 */

public class Employee implements Identifiable, Serializable{

    private static final long SerialVersionUID = 10l;

    private String employeeID;
    private String name;
    private int number;
    private String email;
    private RoleType role;
    private EmployeeState state;

    //Atributos
    ArrayList<Activity> myActivities;

    /**
     * Description: Constructs a new Employee object with its unique identification,
     * personal info, role, and state.
     * 
     * @param employeeID Unique identification code for the employee.
     * @param name       Name of the employee.
     * @param number     Contact number of the employee.
     * @param email      Email address of the employee.
     * @param role       RoleType enum constant specifying the employee's role.
     * @param state      EmployeeState enum constant specifying the employee's
     *                   state.
     *                   Precondition: Parameters must be valid and non-null for
     *                   reference types.
     *                   Postcondition: An Employee instance is created with all
     *                   provided attributes initialized.
     */

    public Employee(String employeeID, String name, int number, String email, RoleType role) {
        this.employeeID = employeeID;
        this.name = name;
        this.number = number;
        this.email = email;
        this.role = role;
        this.state = EmployeeState.ACTIVE;
        myActivities = new ArrayList<Activity>();
    }

    /**
     * Gets the unique identifier of the employee.
     *
     * @return the unique identifier of the employee
     */
    @Override
    public String getId() {
        return employeeID;
    }

    /**
     * Generates a unique identifier based on the current date and time.
     *
     * @return A unique identifier as a String, generated using the current minute,
     *         second, and nanosecond.
     */
    @Override
    public String generateId() {
        return "E" + LocalDateTime.now().getMinute() + LocalDateTime.now().getSecond() + LocalDateTime.now().getNano();
    }

    /**
     * Description: Gets the name of the employee.
     * 
     * @return The employee name as a String.
     *         Precondition: Employee object must be initialized.
     *         Postcondition: Returns the name attribute value.
     */

    public String getName() {
        return name;
    }

    /**
     * Description: Gets the contact number of the employee.
     * 
     * @return The contact number as an integer.
     *         Precondition: Employee object must be initialized.
     *         Postcondition: Returns the number attribute value.
     */

    public int getNumber() {
        return number;
    }

    /**
     * Description: Gets the email address of the employee.
     * 
     * @return The email as a String.
     *         Precondition: Employee object must be initialized.
     *         Postcondition: Returns the email attribute value.
     */

    public String getEmail() {
        return email;
    }

    /**
     * Description: Gets the role type of the employee.
     * 
     * @return The RoleType enum constant.
     *         Precondition: Employee object must be initialized.
     *         Postcondition: Returns the role attribute value.
     */

    public RoleType getRole() {
        return role;
    }

    /**
     * Description: Gets the operational state of the employee.
     * 
     * @return The EmployeeState enum constant.
     *         Precondition: Employee object must be initialized.
     *         Postcondition: Returns the state attribute value.
     */

    public EmployeeState getState() {
        return state;
    }

    /**
     * Description: Sets or updates the name of the employee.
     * 
     * @param name New name value.
     *             Precondition: Employee object must be initialized.
     *             Postcondition: The name attribute is updated.
     */

    public void setName(String name) {
        this.name = name;
    }

    /**
     * Description: Sets or updates the contact number of the employee.
     * 
     * @param number New contact number.
     *               Precondition: Employee object must be initialized.
     *               Postcondition: The number attribute is updated.
     */
    public void setNumber(int number) {
        this.number = number;
    }

    /**
     * Description: Sets or updates the email address of the employee.
     * 
     * @param email New email address.
     *              Precondition: Employee object must be initialized.
     *              Postcondition: The email attribute is updated.
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Description: Sets or updates the role of the employee.
     * 
     * @param role New RoleType enum constant.
     *             Precondition: Employee object must be initialized.
     *             Postcondition: The role attribute is updated.
     */
    public void setRole(RoleType role) {
        this.role = role;
    }

    /**
     * Description: Sets or updates the operational state of the employee.
     * 
     * @param state New EmployeeState enum constant.
     *              Precondition: Employee object must be initialized.
     *              Postcondition: The state attribute is updated.
     */

    public void setState(EmployeeState state) {
        this.state = state;
    }

    public boolean isActive() {
        if (this.state == EmployeeState.ACTIVE) {
            return true;
        }

        return false;
    }

    /**
     * Adds an activity to the employee's list of activities.
     *
     * @param myActivity the activity to be added
     * @return {@code true} if the activity was successfully added, {@code false}
     *         otherwise
     */
    public boolean addActivity(Activity myActivity) {

        if (isActive()) {
            
            if (this.role == RoleType.CAREGIVER) {
                if (myActivity instanceof FeedingActivity || myActivity instanceof CleaningActivity
                    || myActivity instanceof EnrichmentActivity) {
                    
                    return myActivities.add(myActivity);

                }
            } else if (this.role == RoleType.VETERINARY) {
                if (myActivity instanceof MedicControl) {
                    return myActivities.add(myActivity);
                }
            }

        }
        return false;
    }

    public boolean removeActivity(Activity myActivity) {

        for (int i = 0; i < myActivities.size(); i++) {
            if (myActivities.get(i) == myActivity) {
                myActivities.remove(myActivity);
                return true;
            }
        }
        return false;
    }

    public String getAssingActivities() {
        String list = "";

        for (int i = 0; i < myActivities.size(); i++) {
            if (myActivities.get(i).isAssing()) {
                list += myActivities.get(i).toString() + "\n";
            }
        }


        return list;
    }

    public boolean activitiesIsEmpty() {

        if (myActivities.isEmpty()) {
            return true;
        }

        return false;
    }
}
