package healthtrack.platform.u20211d760.tracking.application.internal.eventhandlers;

import healthtrack.platform.u20211d760.assignments.domain.model.aggregates.Patient;
import healthtrack.platform.u20211d760.assignments.infrastructure.persistence.jpa.repositories.PatientRepository;
import healthtrack.platform.u20211d760.tracking.application.internal.outboundservices.acl.ExternalPatientService;
import healthtrack.platform.u20211d760.tracking.domain.model.events.CareRecordRegisteredEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Event handler for CareRecordRegisteredEvent.
 * Updates patient preferred temperature when a care record is registered.
 * 
 * @author July Paico
 * @version 1.0.0
 */
@Component
public class CareRecordRegisteredEventHandler {

    private static final Logger logger = LoggerFactory.getLogger(CareRecordRegisteredEventHandler.class);

    private final ExternalPatientService externalPatientService;
    private final PatientRepository patientRepository;

    public CareRecordRegisteredEventHandler(
            ExternalPatientService externalPatientService,
            PatientRepository patientRepository) {
        this.externalPatientService = externalPatientService;
        this.patientRepository = patientRepository;
    }

    /**
     * Handles the CareRecordRegisteredEvent.
     * Updates patient's preferred temperature if it differs from the target temperature.
     * 
     * @param event The care record registered event
     */
    @EventListener
    @Transactional
    public void on(CareRecordRegisteredEvent event) {
        logger.info("Handling CareRecordRegisteredEvent for patient ID: {}", event.getPatientId());

        Optional<Patient> patientOptional = externalPatientService.getPatientById(event.getPatientId());

        if (patientOptional.isEmpty()) {
            logger.warn("Patient not found with ID: {}", event.getPatientId());
            return;
        }

        Patient patient = patientOptional.get();
        Double currentPreferredTemp = patient.getPreferredRoomTemperature();
        Double targetTemp = event.getTargetRoomTemperature();

        // Update only if temperatures are different
        if (currentPreferredTemp == null || !currentPreferredTemp.equals(targetTemp)) {
            patient.updatePreferredTemperature(targetTemp);
            patientRepository.save(patient);
            logger.info("Updated preferred temperature for patient ID: {} from {} to {}",
                    event.getPatientId(), currentPreferredTemp, targetTemp);
        } else {
            logger.info("Preferred temperature unchanged for patient ID: {}", event.getPatientId());
        }
    }
}
