package healthtrack.platform.u20211d760.assignments.application.internal.queryservices;

import healthtrack.platform.u20211d760.assignments.domain.model.aggregates.Patient;
import healthtrack.platform.u20211d760.assignments.domain.model.queries.GetAllPatientsQuery;
import healthtrack.platform.u20211d760.assignments.domain.model.queries.GetPatientByIdQuery;
import healthtrack.platform.u20211d760.assignments.domain.services.PatientQueryService;
import healthtrack.platform.u20211d760.assignments.infrastructure.persistence.jpa.repositories.PatientRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Implementation of PatientQueryService.
 * Handles queries related to patients.
 * 
 * @author July Paico
 * @version 1.0.0
 */
@Service
public class PatientQueryServiceImpl implements PatientQueryService {

    private final PatientRepository patientRepository;

    public PatientQueryServiceImpl(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Patient> handle(GetAllPatientsQuery query) {
        return patientRepository.findAll();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<Patient> handle(GetPatientByIdQuery query) {
        return patientRepository.findById(query.patientId());
    }
}
