package healthtrack.platform.u20211d760.tracking.interfaces.rest.resources;

/**
 * Care record resource for REST API responses.
 * 
 * @param id Care record ID
 * @param patientId Patient ID
 * @param careMode Care mode
 * @param targetRoomTemperature Target room temperature in Celsius
 * @param currentRoomTemperature Current room temperature in Celsius
 * @param patientState Patient state
 * @param generatedAt Generation timestamp
 * @author July Paico
 * @version 1.0.0
 */
public record CareRecordResource(
        Long id,
        Long patientId,
        String careMode,
        Double targetRoomTemperature,
        Double currentRoomTemperature,
        String patientState,
        String generatedAt
) {
}
