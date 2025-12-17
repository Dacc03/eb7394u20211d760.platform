package healthtrack.platform.u20211d760.tracking.domain.model.valueobjects;

/**
 * Enumeration for patient states.
 * Defines the different health states a patient can be in.
 * 
 * @author July Paico
 * @version 1.0.0
 */
public enum EPatientState {
    /**
     * Patient is in stable condition
     */
    STABLE(0),
    
    /**
     * Patient is in critical condition
     */
    CRITICAL(1);

    private final int id;

    EPatientState(int id) {
        this.id = id;
    }

    /**
     * Gets the numeric ID of the patient state
     * 
     * @return Patient state ID
     */
    public int getId() {
        return id;
    }

    /**
     * Gets patient state from string value
     * 
     * @param value The string value
     * @return EPatientState
     * @throws IllegalArgumentException if value is invalid
     */
    public static EPatientState fromString(String value) {
        for (EPatientState state : EPatientState.values()) {
            if (state.name().equalsIgnoreCase(value)) {
                return state;
            }
        }
        throw new IllegalArgumentException("Invalid patient state: " + value);
    }
}
