package healthtrack.platform.u20211d760.tracking.interfaces.rest.transform;

import healthtrack.platform.u20211d760.tracking.domain.model.aggregates.CareRecord;
import healthtrack.platform.u20211d760.tracking.interfaces.rest.resources.CareRecordResource;

import java.time.format.DateTimeFormatter;

/**
 * Assembler to transform CareRecord entity to CareRecordResource.
 * 
 * @author July Paico
 * @version 1.0.0
 */
public class CareRecordResourceFromEntityAssembler {

    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    /**
     * Transforms a CareRecord entity to a CareRecordResource
     * 
     * @param entity The care record entity
     * @return CareRecordResource
     */
    public static CareRecordResource toResourceFromEntity(CareRecord entity) {
        return new CareRecordResource(
                entity.getId(),
                entity.getPatientId(),
                entity.getCareMode().name(),
                entity.getTargetRoomTemperature(),
                entity.getCurrentRoomTemperature(),
                entity.getPatientState().name(),
                entity.getGeneratedAt().format(DATE_TIME_FORMATTER)
        );
    }
}
