package healthtrack.platform.u20211d760.assignments.interfaces.rest.transform;

import healthtrack.platform.u20211d760.assignments.domain.model.aggregates.Patient;
import healthtrack.platform.u20211d760.assignments.interfaces.rest.resources.PatientResource;

/**
 * Assembler to transform Patient entity to PatientResource.
 * 
 * @author July Paico
 * @version 1.0.0
 */
public class PatientResourceFromEntityAssembler {

    /**
     * Transforms a Patient entity to a PatientResource
     * 
     * @param entity The patient entity
     * @return PatientResource
     */
    public static PatientResource toResourceFromEntity(Patient entity) {
        return new PatientResource(
                entity.getId(),
                entity.getFullName(),
                entity.getRoomId(),
                entity.getPreferredRoomTemperature()
        );
    }
}
