package model.animal;

/**
 * Description: Enumeration representing the health status options for animals
 * in the zoo system.
 */

public enum HealthStatus {
    HEALTHY("Saludable"),
    CUARENTINE("Cuarentena"),
    RECOVER("Recuperacion"),
    OBSERVATION("En Observacion");

    public String typeName;

    /**
     * Description: Constructs a HealthStatus enum constant with its corresponding
     * display name.
     * 
     * @param typeName The descriptive string name of the health status.
     *                 Precondition: typeName must be a valid non-null string.
     *                 Postcondition: A HealthStatus constant is created with its
     *                 assigned name.
     */

    private HealthStatus(String typeName) {
        this.typeName = typeName;
    }

    /**
     * Description: Gets the descriptive string name of the health status.
     * 
     * @return The type name as a String.
     *         Precondition: Enum constant must be initialized.
     *         Postcondition: Returns the typeName attribute value.
     */

    public String getTypeName() {
        return typeName;
    }

}
