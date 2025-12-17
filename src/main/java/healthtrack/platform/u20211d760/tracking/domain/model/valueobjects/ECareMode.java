package healthtrack.platform.u20211d760.tracking.domain.model.valueobjects;

/**
 * Enumeration for care modes.
 * Defines the different care modes available for patients.
 * 
 * @author July Paico
 * @version 1.0.0
 */
public enum ECareMode {
    /**
     * Standard care mode
     */
    STANDARD(0),
    
    /**
     * Intensive care mode
     */
    INTENSIVE(1);

    private final int id;

    ECareMode(int id) {
        this.id = id;
    }

    /**
     * Gets the numeric ID of the care mode
     * 
     * @return Care mode ID
     */
    public int getId() {
        return id;
    }

    /**
     * Gets care mode from string value
     * 
     * @param value The string value
     * @return ECareMode
     * @throws IllegalArgumentException if value is invalid
     */
    public static ECareMode fromString(String value) {
        for (ECareMode mode : ECareMode.values()) {
            if (mode.name().equalsIgnoreCase(value)) {
                return mode;
            }
        }
        throw new IllegalArgumentException("Invalid care mode: " + value);
    }
}
