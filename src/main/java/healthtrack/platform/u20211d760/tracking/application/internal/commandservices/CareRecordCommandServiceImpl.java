package healthtrack.platform.u20211d760.tracking.application.internal.commandservices;

import healthtrack.platform.u20211d760.tracking.application.internal.outboundservices.acl.ExternalPatientService;
import healthtrack.platform.u20211d760.tracking.domain.model.aggregates.CareRecord;
import healthtrack.platform.u20211d760.tracking.domain.model.commands.CreateCareRecordCommand;
import healthtrack.platform.u20211d760.tracking.domain.model.events.CareRecordRegisteredEvent;
import healthtrack.platform.u20211d760.tracking.domain.model.valueobjects.ECareMode;
import healthtrack.platform.u20211d760.tracking.domain.model.valueobjects.EPatientState;
import healthtrack.platform.u20211d760.tracking.domain.services.CareRecordCommandService;
import healthtrack.platform.u20211d760.tracking.infrastructure.persistence.jpa.repositories.CareRecordRepository;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Implementation of CareRecordCommandService.
 * Handles commands related to care records with complete business rule validation.
 * 
 * @author July Paico
 * @version 1.0.0
 */
@Service
public class CareRecordCommandServiceImpl implements CareRecordCommandService {

    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    private static final double MIN_TEMPERATURE = 18.0;
    private static final double MAX_TEMPERATURE = 25.0;

    private final CareRecordRepository careRecordRepository;
    private final ExternalPatientService externalPatientService;
    private final ApplicationEventPublisher eventPublisher;
    private final MessageSource messageSource;

    public CareRecordCommandServiceImpl(
            CareRecordRepository careRecordRepository,
            ExternalPatientService externalPatientService,
            ApplicationEventPublisher eventPublisher,
            MessageSource messageSource) {
        this.careRecordRepository = careRecordRepository;
        this.externalPatientService = externalPatientService;
        this.eventPublisher = eventPublisher;
        this.messageSource = messageSource;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public CareRecord handle(CreateCareRecordCommand command) {
        // Validate patient ID is not null
        if (command.patientId() == null) {
            throw new IllegalArgumentException(getMessage("validation.carerecord.patientid.required"));
        }

        // Validate patient exists
        externalPatientService.validatePatientExists(command.patientId());

        // Validate and parse care mode
        ECareMode careMode = validateAndParseCareMode(command.careMode());

        // Validate target temperature
        validateTargetTemperature(command.targetRoomTemperature());

        // Validate current temperature
        validateCurrentTemperature(command.currentRoomTemperature());

        // Validate and parse patient state
        EPatientState patientState = validateAndParsePatientState(command.patientState());

        // Validate and parse generated at
        LocalDateTime generatedAt = validateAndParseGeneratedAt(command.generatedAt());

        // Create care record
        CareRecord careRecord = new CareRecord(
                command.patientId(),
                careMode,
                command.targetRoomTemperature(),
                command.currentRoomTemperature(),
                patientState,
                generatedAt
        );

        // Save care record
        CareRecord savedCareRecord = careRecordRepository.save(careRecord);

        // Emit integration event
        CareRecordRegisteredEvent event = new CareRecordRegisteredEvent(
                savedCareRecord.getPatientId(),
                savedCareRecord.getTargetRoomTemperature()
        );
        eventPublisher.publishEvent(event);

        return savedCareRecord;
    }

    /**
     * Validates and parses care mode from string
     * 
     * @param careModeStr Care mode as string
     * @return ECareMode
     * @throws IllegalArgumentException if invalid
     */
    private ECareMode validateAndParseCareMode(String careModeStr) {
        if (careModeStr == null || careModeStr.trim().isEmpty()) {
            throw new IllegalArgumentException(getMessage("validation.carerecord.caremode.required"));
        }
        try {
            return ECareMode.fromString(careModeStr);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(getMessage("validation.carerecord.caremode.required"));
        }
    }

    /**
     * Validates target room temperature
     * 
     * @param temperature Temperature to validate
     * @throws IllegalArgumentException if invalid
     */
    private void validateTargetTemperature(Double temperature) {
        if (temperature == null) {
            throw new IllegalArgumentException(getMessage("validation.carerecord.targettemperature.required"));
        }
        if (temperature <= 0) {
            throw new IllegalArgumentException(getMessage("validation.carerecord.targettemperature.positive"));
        }
        if (temperature < MIN_TEMPERATURE || temperature > MAX_TEMPERATURE) {
            throw new IllegalArgumentException(getMessage("validation.carerecord.targettemperature.range"));
        }
    }

    /**
     * Validates current room temperature
     * 
     * @param temperature Temperature to validate
     * @throws IllegalArgumentException if invalid
     */
    private void validateCurrentTemperature(Double temperature) {
        if (temperature == null) {
            throw new IllegalArgumentException(getMessage("validation.carerecord.currenttemperature.required"));
        }
        if (temperature <= 0) {
            throw new IllegalArgumentException(getMessage("validation.carerecord.currenttemperature.positive"));
        }
    }

    /**
     * Validates and parses patient state from string
     * 
     * @param patientStateStr Patient state as string
     * @return EPatientState
     * @throws IllegalArgumentException if invalid
     */
    private EPatientState validateAndParsePatientState(String patientStateStr) {
        if (patientStateStr == null || patientStateStr.trim().isEmpty()) {
            throw new IllegalArgumentException(getMessage("validation.carerecord.patientstate.required"));
        }
        try {
            return EPatientState.fromString(patientStateStr);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(getMessage("validation.carerecord.patientstate.required"));
        }
    }

    /**
     * Validates and parses generated at timestamp
     * 
     * @param generatedAtStr Generated at as string
     * @return LocalDateTime
     * @throws IllegalArgumentException if invalid
     */
    private LocalDateTime validateAndParseGeneratedAt(String generatedAtStr) {
        if (generatedAtStr == null || generatedAtStr.trim().isEmpty()) {
            throw new IllegalArgumentException(getMessage("validation.carerecord.generatedat.required"));
        }

        LocalDateTime generatedAt;
        try {
            generatedAt = LocalDateTime.parse(generatedAtStr, DATE_TIME_FORMATTER);
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException(getMessage("validation.carerecord.generatedat.format"));
        }

        // Validate generated at is not in the future
        if (generatedAt.isAfter(LocalDateTime.now())) {
            throw new IllegalArgumentException(getMessage("validation.carerecord.generatedat.notfuture"));
        }

        return generatedAt;
    }

    /**
     * Gets localized message
     * 
     * @param key Message key
     * @param args Optional arguments
     * @return Localized message
     */
    private String getMessage(String key, Object... args) {
        return messageSource.getMessage(key, args, LocaleContextHolder.getLocale());
    }
}
