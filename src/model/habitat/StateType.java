package model.habitat;

public enum StateType {
    ACTIVE("Activo"),
    MANTEINMENT("En mantenimiento"),
    CONSTRUCTION("En construccion"),
    RETIRED("Retirado");

    private String type;

    private StateType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public static String getStateTypeList() {
        String list = "";
        StateType[] values = StateType.values();
        for (int i = 0; i < values.length; i++) {
            list += (i + 1) + ". " + values[i].getType() + "\n";
        }
        return list;
    }

    public static StateType calcuStateType(int state) {
        StateType[] values = StateType.values();

        return values[state - 1];
    }
}
