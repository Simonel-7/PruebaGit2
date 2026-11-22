package model.animal;

/**
 * Description: Enumeration representing the possible status types of an animal
 * in the zoo system.
 */

public enum AnimalStatusType {
    ACTIVE("Activo"),
    RETIRED("Retirado");

    public String typeName;

    /**
     * Description: Constructs an AnimalStatusType enum constant with its display
     * name.
     * 
     * @param typeName The descriptive string name of the status.
     *                 Precondition: typeName must be a valid non-null string.
     *                 Postcondition: An AnimalStatusType constant is created with
     *                 its assigned name.
     */

    private AnimalStatusType(String typeName) {
        this.typeName = typeName;
    }

    /**
     * Description: Gets the descriptive string name of the animal status type.
     * 
     * @return The type name as a String.
     *         Precondition: Enum constant must be initialized.
     *         Postcondition: Returns the typeName attribute value.
     */

    public String getTypeName() {
        return typeName;
    }
}
