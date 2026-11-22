package exceptions;

/**
 * Custom exception class for handling business logic errors in the ZooCare system.
 * This exception is used to represent various error scenarios related to habitats,
 * employees, animals, and activities within the zoo.
 *
 * <p>Common error messages are defined as constants for reuse throughout the application.</p>
 *
 * <h3>Error Categories:</h3>
 * <ul>
 *   <li><b>Habitat Errors:</b>
 *     <ul>
 *       <li>{@link #HABITAT_NOT_FOUND}: Habitat with the specified ID does not exist.</li>
 *       <li>{@link #HABITAT_NOT_ACTIVE}: The selected habitat is not active.</li>
 *     </ul>
 *   </li>
 *   <li><b>Employee Errors:</b>
 *     <ul>
 *       <li>{@link #EMPLOYEE_NOT_FOUND}: Employee with the specified ID does not exist.</li>
 *       <li>{@link #EMPLOYEE_NOT_ACTIVE}: The selected employee is not active.</li>
 *       <li>{@link #EMPLOYEE_NOT_AUTHORIZED}: The employee is not authorized to perform the activity.</li>
 *     </ul>
 *   </li>
 *   <li><b>Animal Errors:</b>
 *     <ul>
 *       <li>{@link #ANIMAL_NOT_FOUND}: Animal with the specified ID does not exist.</li>
 *       <li>{@link #ANIMAL_NOT_ACTIVE}: The selected animal is not active.</li>
 *       <li>{@link #ANIMAL_NOT_IN_HABITAT}: The selected animal is not in the specified habitat.</li>
 *     </ul>
 *   </li>
 *   <li><b>Activity Errors:</b>
 *     <ul>
 *       <li>{@link #ACTIVITY_NOT_FOUND}: Activity with the specified ID does not exist.</li>
 *       <li>{@link #ACTIVITY_FINISHED}: The selected activity is finished.</li>
 *     </ul>
 *   </li>
 * </ul>
 */
public class ZooCareBusinessException extends Exception {

    // Errores del Habitat
    public static final String HABITAT_NOT_FOUND = "Error de busqueda, no existe un Habitat con el ID suministrado";
    public static final String HABITAT_NOT_ACTIVE = "El Habitat seleccionado no está activo";

    // Errores del Employee
    public static final String EMPLOYEE_NOT_FOUND = "Error de busqueda, no existe un Empleado con el ID suministrado";
    public static final String EMPLOYEE_NOT_ACTIVE = "El Empleado seleccionado no está activo";
     public static final String EMPLOYEE_NOT_AUTHORIZED = "El Empleado no está autorizado para realizar la actividad";

    // Errores del Animal
    public static final String ANIMAL_NOT_FOUND = "Error de busqueda, no existe un Animal con el ID suministrado";
    public static final String ANIMAL_NOT_ACTIVE = "El Animal seleccionado no está activo";
    public static final String ANIMAL_NOT_IN_HABITAT = "El Animal seleccionado no se encuentra en el Habitat proporcionado";

    //Errores de Actividades 
    public static final String ACTIVITY_NOT_FOUND = "Error de busqueda, no existe una Actividad con el ID suministrado";
    public static final String ACTIVITY_FINISHED = "La actividad seleccionada ya esta finalizada";
    public static final String ACTIVITY_CANCELED = "La actividad seleccionada ya esta cancelada";

    public ZooCareBusinessException(String errorMessage) {
        super(errorMessage);
    }

}
