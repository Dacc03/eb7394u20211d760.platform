package healthtrack.platform.u20211d760.tracking.infrastructure.persistence.jpa.repositories;

import healthtrack.platform.u20211d760.tracking.domain.model.aggregates.CareRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * JPA Repository for CareRecord aggregate.
 * 
 * @author July Paico
 * @version 1.0.0
 */
@Repository
public interface CareRecordRepository extends JpaRepository<CareRecord, Long> {
}
