package model.habitat;

/**
 * Description: Enumeration representing the available environment types for
 * habitats and animal requirements in the zoo system.
 */

public enum EnvironmentType {

    AQUATIC("Acuatico"),
    AVIARY("Aviario"),
    TERRESTRIAL("Terrestre"),
    MEDICAL("Medico");

    private String typeName;

    /**
     * Description: Constructs an EnvironmentType enum constant with its
     * corresponding display name.
     * 
     * @param typeName The descriptive string name of the environment type.
     *                 Precondition: typeName must be a valid non-null string.
     *                 Postcondition: An EnvironmentType constant is created with
     *                 its assigned name.
     */

    private EnvironmentType(String typeName) {
        this.typeName = typeName;
    }

    /**
     * Description: Gets the descriptive string name of the environment type.
     * 
     * @return The type name as a String.
     *         Precondition: Enum constant must be initialized.
     *         Postcondition: Returns the typeName attribute value.
     */

    public String getTypeName() {
        return typeName;
    }
}
