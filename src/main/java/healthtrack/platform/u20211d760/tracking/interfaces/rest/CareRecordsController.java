package healthtrack.platform.u20211d760.tracking.interfaces.rest;

import healthtrack.platform.u20211d760.tracking.domain.model.aggregates.CareRecord;
import healthtrack.platform.u20211d760.tracking.domain.model.commands.CreateCareRecordCommand;
import healthtrack.platform.u20211d760.tracking.domain.services.CareRecordCommandService;
import healthtrack.platform.u20211d760.tracking.interfaces.rest.resources.CareRecordResource;
import healthtrack.platform.u20211d760.tracking.interfaces.rest.resources.CreateCareRecordResource;
import healthtrack.platform.u20211d760.tracking.interfaces.rest.transform.CareRecordResourceFromEntityAssembler;
import healthtrack.platform.u20211d760.tracking.interfaces.rest.transform.CreateCareRecordCommandFromResourceAssembler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * REST Controller for Care Record operations.
 * Provides endpoints for care record management.
 * 
 * @author July Paico
 * @version 1.0.0
 */
@RestController
@RequestMapping("/api/v1/care-records")
@Tag(name = "Care Records", description = "Care record management endpoints")
public class CareRecordsController {

    private final CareRecordCommandService careRecordCommandService;

    public CareRecordsController(CareRecordCommandService careRecordCommandService) {
        this.careRecordCommandService = careRecordCommandService;
    }

    /**
     * Creates a new care record
     * 
     * @param resource The create care record resource
     * @return Created care record resource
     */
    @Operation(
            summary = "Create a new care record",
            description = "Creates a new care record for a patient with vital signs and room conditions. " +
                    "Validates patient existence, temperature ranges, and timestamps. " +
                    "Emits an integration event to update patient preferred temperature."
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "Care record created successfully",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = CareRecordResource.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Invalid input data - validation errors",
                    content = @Content(mediaType = "application/json")
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Patient not found",
                    content = @Content(mediaType = "application/json")
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Internal server error",
                    content = @Content(mediaType = "application/json")
            )
    })
    @PostMapping
    public ResponseEntity<CareRecordResource> createCareRecord(
            @RequestBody CreateCareRecordResource resource) {

        CreateCareRecordCommand command = CreateCareRecordCommandFromResourceAssembler
                .toCommandFromResource(resource);

        CareRecord careRecord = careRecordCommandService.handle(command);

        CareRecordResource careRecordResource = CareRecordResourceFromEntityAssembler
                .toResourceFromEntity(careRecord);

        return new ResponseEntity<>(careRecordResource, HttpStatus.CREATED);
    }
}
