package healthtrack.platform.u20211d760.assignments.interfaces.rest;

import healthtrack.platform.u20211d760.assignments.domain.model.queries.GetAllPatientsQuery;
import healthtrack.platform.u20211d760.assignments.domain.services.PatientQueryService;
import healthtrack.platform.u20211d760.assignments.interfaces.rest.resources.PatientResource;
import healthtrack.platform.u20211d760.assignments.interfaces.rest.transform.PatientResourceFromEntityAssembler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * REST Controller for Patient operations.
 * Provides endpoints for patient management.
 * 
 * @author July Paico
 * @version 1.0.0
 */
@RestController
@RequestMapping("/api/v1/patients")
@Tag(name = "Patients", description = "Patient management endpoints")
public class PatientsController {

    private final PatientQueryService patientQueryService;

    public PatientsController(PatientQueryService patientQueryService) {
        this.patientQueryService = patientQueryService;
    }

    /**
     * Gets all patients
     * 
     * @return List of patient resources
     */
    @Operation(
            summary = "Get all patients",
            description = "Retrieves all patients currently registered in the system"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Patients retrieved successfully"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping
    public ResponseEntity<List<PatientResource>> getAllPatients() {
        var getAllPatientsQuery = new GetAllPatientsQuery();
        var patients = patientQueryService.handle(getAllPatientsQuery);
        var patientResources = patients.stream()
                .map(PatientResourceFromEntityAssembler::toResourceFromEntity)
                .toList();
        return ResponseEntity.ok(patientResources);
    }
}
