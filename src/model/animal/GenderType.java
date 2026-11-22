package model.animal;

public enum GenderType {
    MASCULINE("Masculino"),
    FEMENINE("Femenino");

    private String typeName;

    private GenderType(String typeName) {
        this.typeName = typeName;
    }

    public String getTypeName() {
        return typeName;
    }

    public static String getGenderTypeList() {
        String list = "";
        GenderType[] values = GenderType.values();
        for (int i = 0; i < values.length; i++) {
            list += (i + 1) + ". " + values[i].getTypeName() + "\n";
        }
        return list;
    }

    public static GenderType calculateGenderType(int state) {
        GenderType[] values = GenderType.values();

        return values[state - 1];
    }

}
