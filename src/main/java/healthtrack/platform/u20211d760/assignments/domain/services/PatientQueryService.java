package healthtrack.platform.u20211d760.assignments.domain.services;

import healthtrack.platform.u20211d760.assignments.domain.model.aggregates.Patient;
import healthtrack.platform.u20211d760.assignments.domain.model.queries.GetAllPatientsQuery;
import healthtrack.platform.u20211d760.assignments.domain.model.queries.GetPatientByIdQuery;

import java.util.List;
import java.util.Optional;

/**
 * Service interface for patient queries.
 * 
 * @author July Paico
 * @version 1.0.0
 */
public interface PatientQueryService {

    /**
     * Handles the query to get all patients
     * 
     * @param query The get all patients query
     * @return List of patients
     */
    List<Patient> handle(GetAllPatientsQuery query);

    /**
     * Handles the query to get a patient by ID
     * 
     * @param query The get patient by ID query
     * @return Optional patient
     */
    Optional<Patient> handle(GetPatientByIdQuery query);
}
