package model.employee;

/**
 * Description: Enumeration representing the possible operational states of an
 * employee in the zoo system.
 */

public enum EmployeeState {
    ACTIVE("Activo"),
    INACTIVE("Inactivo");

    public String typeName;

    /**
     * Description: Constructs an EmployeeState enum constant with its corresponding
     * display name.
     * 
     * @param typeName The descriptive string name of the employee state.
     *                 Precondition: typeName must be a valid non-null string.
     *                 Postcondition: An EmployeeState constant is created with its
     *                 assigned name.
     */

    private EmployeeState(String typeName) {
        this.typeName = typeName;
    }

    /**
     * Description: Gets the descriptive string name of the employee state.
     * 
     * @return The type name as a String.
     *         Precondition: Enum constant must be initialized.
     *         Postcondition: Returns the typeName attribute value.
     */

    public String getTypeName() {
        return typeName;
    }
}
