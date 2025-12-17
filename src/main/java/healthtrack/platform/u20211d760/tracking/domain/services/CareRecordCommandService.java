package healthtrack.platform.u20211d760.tracking.domain.services;

import healthtrack.platform.u20211d760.tracking.domain.model.aggregates.CareRecord;
import healthtrack.platform.u20211d760.tracking.domain.model.commands.CreateCareRecordCommand;

/**
 * Service interface for care record commands.
 * 
 * @author July Paico
 * @version 1.0.0
 */
public interface CareRecordCommandService {

    /**
     * Handles the command to create a new care record
     * 
     * @param command The create care record command
     * @return The created care record
     */
    CareRecord handle(CreateCareRecordCommand command);
}
