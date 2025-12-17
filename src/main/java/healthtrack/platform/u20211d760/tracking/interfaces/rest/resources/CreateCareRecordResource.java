package healthtrack.platform.u20211d760.tracking.interfaces.rest.resources;

/**
 * Resource for creating a care record.
 * 
 * @param patientId Patient ID
 * @param careMode Care mode (STANDARD or INTENSIVE)
 * @param targetRoomTemperature Target room temperature in Celsius
 * @param currentRoomTemperature Current room temperature in Celsius
 * @param patientState Patient state (STABLE or CRITICAL)
 * @param generatedAt Generation timestamp in format: yyyy-MM-dd HH:mm:ss
 * @author July Paico
 * @version 1.0.0
 */
public record CreateCareRecordResource(
        Long patientId,
        String careMode,
        Double targetRoomTemperature,
        Double currentRoomTemperature,
        String patientState,
        String generatedAt
) {
}
