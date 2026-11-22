package model.animal;

/**
 * Description: Enumeration representing the life stage options for animals in
 * the zoo system.
 */

public enum LifeStage {
    YOUNG("Juvenil"),
    ADULT("Adulto");

    public String typeName;

    /**
     * Description: Constructs a LifeStage enum constant with its corresponding
     * display name.
     * 
     * @param typeName The descriptive string name of the life stage.
     *                 Precondition: typeName must be a valid non-null string.
     *                 Postcondition: A LifeStage constant is created with its
     *                 assigned name.
     */

    private LifeStage(String typeName) {
        this.typeName = typeName;
    }

    /**
     * Description: Gets the descriptive string name of the life stage.
     * 
     * @return The type name as a String.
     *         Precondition: Enum constant must be initialized.
     *         Postcondition: Returns the typeName attribute value.
     */

    public String getTypeName() {
        return typeName;
    }

}
