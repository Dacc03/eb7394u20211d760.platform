package healthtrack.platform.u20211d760.tracking.application.internal.outboundservices.acl;

import healthtrack.platform.u20211d760.assignments.domain.model.aggregates.Patient;
import healthtrack.platform.u20211d760.assignments.domain.model.queries.GetPatientByIdQuery;
import healthtrack.platform.u20211d760.assignments.domain.services.PatientQueryService;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * ACL service to access patient information from assignments bounded context.
 * Provides anti-corruption layer between tracking and assignments contexts.
 * 
 * @author July Paico
 * @version 1.0.0
 */
@Service
public class ExternalPatientService {

    private final PatientQueryService patientQueryService;
    private final MessageSource messageSource;

    public ExternalPatientService(PatientQueryService patientQueryService, MessageSource messageSource) {
        this.patientQueryService = patientQueryService;
        this.messageSource = messageSource;
    }

    /**
     * Verifies if a patient exists by ID
     * 
     * @param patientId The patient ID to verify
     * @return true if patient exists, false otherwise
     */
    public boolean patientExists(Long patientId) {
        var query = new GetPatientByIdQuery(patientId);
        return patientQueryService.handle(query).isPresent();
    }

    /**
     * Gets a patient by ID
     * 
     * @param patientId The patient ID
     * @return Optional patient
     */
    public Optional<Patient> getPatientById(Long patientId) {
        var query = new GetPatientByIdQuery(patientId);
        return patientQueryService.handle(query);
    }

    /**
     * Validates that a patient exists, throwing exception if not
     * 
     * @param patientId The patient ID to validate
     * @throws IllegalArgumentException if patient doesn't exist
     */
    public void validatePatientExists(Long patientId) {
        if (!patientExists(patientId)) {
            String message = messageSource.getMessage(
                    "validation.patient.notfound",
                    new Object[]{patientId},
                    LocaleContextHolder.getLocale()
            );
            throw new IllegalArgumentException(message);
        }
    }
}
