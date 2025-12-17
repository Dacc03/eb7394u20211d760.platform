package healthtrack.platform.u20211d760.shared.domain.model.aggregates;

import jakarta.persistence.*;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.domain.AbstractAggregateRoot;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.Date;

/**
 * Base class for auditable aggregate roots.
 * Provides automatic auditing fields (createdAt, updatedAt) and domain event support.
 * 
 * @param <T> The aggregate root type
 * @author July Paico
 * @version 1.0.0
 */
@Getter
@EntityListeners(AuditingEntityListener.class)
@MappedSuperclass
public class AuditableAbstractAggregateRoot<T extends AbstractAggregateRoot<T>> extends AbstractAggregateRoot<T> {

    /**
     * Unique identifier for the aggregate
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Timestamp when the aggregate was created
     */
    @CreatedDate
    @Column(nullable = false, updatable = false)
    private Date createdAt;

    /**
     * Timestamp when the aggregate was last updated
     */
    @LastModifiedDate
    @Column(nullable = false)
    private Date updatedAt;
}
