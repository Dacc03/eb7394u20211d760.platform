package healthtrack.platform.u20211d760.assignments.interfaces.rest.resources;

/**
 * Patient resource for REST API responses.
 * 
 * @param id Patient ID
 * @param fullName Patient full name
 * @param roomId Room ID assignment
 * @param preferredRoomTemperature Preferred room temperature in Celsius
 * @author July Paico
 * @version 1.0.0
 */
public record PatientResource(
        Long id,
        String fullName,
        Integer roomId,
        Double preferredRoomTemperature
) {
}
