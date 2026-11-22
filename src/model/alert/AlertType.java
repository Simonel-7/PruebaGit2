package model.alert;


public enum AlertType {
    OCCUPANCY("Ocupacion"),
    ENVIRONMENTAL_INCOMPATIBILITY("Incompatibilidad ambiental"),
    INVALID_HABITAT_STATUS("estado no válido del hábitat"),
    INSUFFICIENT_HABITAT_CAPACITY("capacidad insuficiente del hábitat");

    public String typeName;

    private AlertType(String typeName) {
        this.typeName = typeName;
    }

    public String getTypeName() {
        return typeName;
    }

    public static String getAlerTypeList() {
        String list = "";
        AlertType[] values = AlertType.values();
        for (int i = 0; i < values.length; i++) {
            list += (i + 1) + ". " + values[i].getTypeName() + "\n";
        }
        return list;
    }

    public AlertType calculateActivityStatus(int option) {

        AlertType[] values = AlertType.values();

        if (option < 1 || option > values.length) {
            return null;
        }

        return values[option - 1];

    }


}
