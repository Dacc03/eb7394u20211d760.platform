package healthtrack.platform.u20211d760.tracking.domain.model.commands;

/**
 * Command to create a new care record.
 * 
 * @param patientId Patient ID
 * @param careMode Care mode as string
 * @param targetRoomTemperature Target room temperature
 * @param currentRoomTemperature Current room temperature
 * @param patientState Patient state as string
 * @param generatedAt Generation timestamp as string
 * @author July Paico
 * @version 1.0.0
 */
public record CreateCareRecordCommand(
        Long patientId,
        String careMode,
        Double targetRoomTemperature,
        Double currentRoomTemperature,
        String patientState,
        String generatedAt
) {
}
