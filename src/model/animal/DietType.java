package model.animal;

/**
 * Description: Enumeration representing the diet type options for animals in
 * the zoo system.
 */

public enum DietType {
    HERBIVORE("Herbivora"),
    CARNIVORE("Carnivora"),
    OMNIVORE("Omnivora"),
    INSECTIVORE("Insectivora");

    public String typeName;

    /**
     * Description: Constructs a DietType enum constant with its corresponding
     * display name.
     * 
     * @param typeName The descriptive string name of the diet type.
     *                 Precondition: typeName must be a valid non-null string.
     *                 Postcondition: A DietType constant is created with its
     *                 assigned name.
     */

    private DietType(String typeName) {
        this.typeName = typeName;
    }

    /**
     * Description: Gets the descriptive string name of the diet type.
     * 
     * @return The type name as a String.
     *         Precondition: Enum constant must be initialized.
     *         Postcondition: Returns the typeName attribute value.
     */

    public String getTypeName() {
        return typeName;
    }

}
