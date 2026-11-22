package model.employee;

/**
 * Description: Enumeration representing the available role types for employees
 * in the zoo system.
 */

public enum RoleType {
    CAREGIVER("Personal Cuidador"),
    VETERINARY("Personal Veterinario");

    private String typeName;

    /**
     * Description: Constructs a RoleType enum constant with its corresponding
     * display name.
     * 
     * @param typeName The descriptive string name of the role type.
     *                 Precondition: typeName must be a valid non-null string.
     *                 Postcondition: A RoleType constant is created with its
     *                 assigned name.
     */

    private RoleType(String typeName) {
        this.typeName = typeName;
    }

    /**
     * Description: Gets the descriptive string name of the role type.
     * 
     * @return The type name as a String.
     *         Precondition: Enum constant must be initialized.
     *         Postcondition: Returns the typeName attribute value.
     */

    public String getTypeName() {
        return typeName;
    }
}
