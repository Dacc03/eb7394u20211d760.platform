package healthtrack.platform.u20211d760.tracking.domain.model.events;

import lombok.Getter;

/**
 * Domain event fired when a care record is registered.
 * This event triggers temperature preference update in the patient context.
 * 
 * @author July Paico
 * @version 1.0.0
 */
@Getter
public final class CareRecordRegisteredEvent {
    
    private final Long patientId;
    private final Double targetRoomTemperature;

    /**
     * Constructor for the event
     * 
     * @param patientId ID of the patient
     * @param targetRoomTemperature Target temperature from the care record
     */
    public CareRecordRegisteredEvent(Long patientId, Double targetRoomTemperature) {
        this.patientId = patientId;
        this.targetRoomTemperature = targetRoomTemperature;
    }
}
