package healthtrack.platform.u20211d760.assignments.domain.model.aggregates;

import healthtrack.platform.u20211d760.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

/**
 * Patient aggregate root.
 * Represents a patient in the hospital with room assignment and temperature preferences.
 * 
 * @author July Paico
 * @version 1.0.0
 */
@Entity
@Getter
public class Patient extends AuditableAbstractAggregateRoot<Patient> {

    /**
     * Full name of the patient
     */
    @NotBlank(message = "{validation.patient.fullname.required}")
    @Column(nullable = false)
    private String fullName;

    /**
     * Room ID where the patient is assigned
     */
    @NotNull(message = "{validation.patient.roomid.required}")
    @Positive(message = "{validation.patient.roomid.positive}")
    @Column(nullable = false)
    private Integer roomId;

    /**
     * Preferred room temperature for the patient in Celsius
     */
    @Setter
    @Column(nullable = true)
    private Double preferredRoomTemperature;

    /**
     * Default constructor for JPA
     */
    protected Patient() {
    }

    /**
     * Constructor with required fields
     * 
     * @param fullName Full name of the patient
     * @param roomId Room ID assignment
     * @param preferredRoomTemperature Preferred temperature in Celsius
     */
    public Patient(String fullName, Integer roomId, Double preferredRoomTemperature) {
        this.fullName = fullName;
        this.roomId = roomId;
        this.preferredRoomTemperature = preferredRoomTemperature;
    }

    /**
     * Updates the preferred room temperature
     * 
     * @param newTemperature The new preferred temperature
     */
    public void updatePreferredTemperature(Double newTemperature) {
        this.preferredRoomTemperature = newTemperature;
    }
}
