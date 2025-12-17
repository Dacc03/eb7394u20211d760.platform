package healthtrack.platform.u20211d760.assignments.infrastructure.persistence.jpa.repositories;

import healthtrack.platform.u20211d760.assignments.domain.model.aggregates.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * JPA Repository for Patient aggregate.
 * 
 * @author July Paico
 * @version 1.0.0
 */
@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {
}
