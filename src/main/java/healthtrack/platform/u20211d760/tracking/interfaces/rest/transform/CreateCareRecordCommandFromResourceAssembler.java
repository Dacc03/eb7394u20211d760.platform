package healthtrack.platform.u20211d760.tracking.interfaces.rest.transform;

import healthtrack.platform.u20211d760.tracking.domain.model.commands.CreateCareRecordCommand;
import healthtrack.platform.u20211d760.tracking.interfaces.rest.resources.CreateCareRecordResource;

/**
 * Assembler to transform CreateCareRecordResource to CreateCareRecordCommand.
 * 
 * @author July Paico
 * @version 1.0.0
 */
public class CreateCareRecordCommandFromResourceAssembler {

    /**
     * Transforms a CreateCareRecordResource to a CreateCareRecordCommand
     * 
     * @param resource The create care record resource
     * @return CreateCareRecordCommand
     */
    public static CreateCareRecordCommand toCommandFromResource(CreateCareRecordResource resource) {
        return new CreateCareRecordCommand(
                resource.patientId(),
                resource.careMode(),
                resource.targetRoomTemperature(),
                resource.currentRoomTemperature(),
                resource.patientState(),
                resource.generatedAt()
        );
    }
}
