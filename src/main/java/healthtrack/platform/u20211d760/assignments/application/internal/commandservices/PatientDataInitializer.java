package healthtrack.platform.u20211d760.assignments.application.internal.commandservices;

import healthtrack.platform.u20211d760.assignments.domain.model.aggregates.Patient;
import healthtrack.platform.u20211d760.assignments.infrastructure.persistence.jpa.repositories.PatientRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Data initializer for Patient entities.
 * Populates initial patient data on application startup if database is empty.
 * 
 * @author July Paico
 * @version 1.0.0
 */
@Configuration
public class PatientDataInitializer {

    private static final Logger logger = LoggerFactory.getLogger(PatientDataInitializer.class);

    /**
     * Creates a CommandLineRunner bean to initialize patient data
     * 
     * @param patientRepository The patient repository
     * @return CommandLineRunner
     */
    @Bean
    CommandLineRunner initPatientsDatabase(PatientRepository patientRepository) {
        return args -> {
            if (patientRepository.count() == 0) {
                logger.info("Initializing patient data...");

                Patient patient1 = new Patient("Juan Pérez", 101, 22.0);
                Patient patient2 = new Patient("María López", 102, 21.5);
                Patient patient3 = new Patient("Carlos Gómez", 103, 23.0);
                Patient patient4 = new Patient("Ana Torres", 104, 22.5);

                patientRepository.save(patient1);
                patientRepository.save(patient2);
                patientRepository.save(patient3);
                patientRepository.save(patient4);

                logger.info("Patient data initialized successfully with {} records", patientRepository.count());
            } else {
                logger.info("Patient data already exists. Skipping initialization.");
            }
        };
    }
}
