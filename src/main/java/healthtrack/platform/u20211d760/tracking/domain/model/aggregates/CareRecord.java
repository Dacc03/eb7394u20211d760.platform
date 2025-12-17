package healthtrack.platform.u20211d760.tracking.domain.model.aggregates;

import healthtrack.platform.u20211d760.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import healthtrack.platform.u20211d760.tracking.domain.model.valueobjects.ECareMode;
import healthtrack.platform.u20211d760.tracking.domain.model.valueobjects.EPatientState;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;

import java.time.LocalDateTime;

/**
 * CareRecord aggregate root.
 * Represents a care record for patient monitoring with vital signs and room conditions.
 * 
 * @author July Paico
 * @version 1.0.0
 */
@Entity
@Getter
public class CareRecord extends AuditableAbstractAggregateRoot<CareRecord> {

    /**
     * ID of the patient this care record belongs to
     */
    @NotNull(message = "{validation.carerecord.patientid.required}")
    @Column(nullable = false)
    private Long patientId;

    /**
     * Care mode for this record
     */
    @NotNull(message = "{validation.carerecord.caremode.required}")
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ECareMode careMode;

    /**
     * Target room temperature in Celsius
     */
    @NotNull(message = "{validation.carerecord.targettemperature.required}")
    @Positive(message = "{validation.carerecord.targettemperature.positive}")
    @Column(nullable = false)
    private Double targetRoomTemperature;

    /**
     * Current room temperature in Celsius
     */
    @NotNull(message = "{validation.carerecord.currenttemperature.required}")
    @Positive(message = "{validation.carerecord.currenttemperature.positive}")
    @Column(nullable = false)
    private Double currentRoomTemperature;

    /**
     * Current state of the patient
     */
    @NotNull(message = "{validation.carerecord.patientstate.required}")
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private EPatientState patientState;

    /**
     * Timestamp when this care record was generated
     */
    @NotNull(message = "{validation.carerecord.generatedat.required}")
    @Column(nullable = false)
    private LocalDateTime generatedAt;

    /**
     * Default constructor for JPA
     */
    protected CareRecord() {
    }

    /**
     * Constructor with all required fields
     * 
     * @param patientId Patient ID
     * @param careMode Care mode
     * @param targetRoomTemperature Target temperature
     * @param currentRoomTemperature Current temperature
     * @param patientState Patient state
     * @param generatedAt Generation timestamp
     */
    public CareRecord(Long patientId, ECareMode careMode, Double targetRoomTemperature,
                     Double currentRoomTemperature, EPatientState patientState, 
                     LocalDateTime generatedAt) {
        this.patientId = patientId;
        this.careMode = careMode;
        this.targetRoomTemperature = targetRoomTemperature;
        this.currentRoomTemperature = currentRoomTemperature;
        this.patientState = patientState;
        this.generatedAt = generatedAt;
    }
}
