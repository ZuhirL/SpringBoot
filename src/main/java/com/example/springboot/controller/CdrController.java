package com.example.springboot.controller;

import com.example.springboot.dto.CdrDto;
import com.example.springboot.error.ErrorResponse;
import com.example.springboot.service.CdrService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

import java.util.List;

@RestController
@RequestMapping("cdr")
@RequiredArgsConstructor
public class CdrController {

    private final CdrService cdrService;

    @Operation(summary = "Create a CDR")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "CDR created",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = CdrDto.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid body request",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class))}),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class))})})
    @PostMapping
    public CdrDto createCdr(@Valid @RequestBody CdrDto cdrDto) {
        return cdrService.createCdr(cdrDto);
    }

    @Operation(summary = "Get a CDR by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the CDR",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = CdrDto.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid id supplied",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class))}),
            @ApiResponse(responseCode = "404", description = "CDR not found",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class))}),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class))})})
    @GetMapping("id/{id}")
    public CdrDto getById(@PathVariable Long id) {
        return cdrService.getById(id);
    }

    @Operation(summary = "Get CDRs by vehicleId")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the CDRs",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = CdrDto.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid vehicleId supplied",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class))}),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class))})})
    @GetMapping("vehicle-identification/{vehicleId}")
    public List<CdrDto> getByVehicleIdentification(@PathVariable String vehicleId,
                                                   @RequestParam(required = false) String sortBy,
                                                   @RequestParam(required = false) String order) {
        return cdrService.getByVehicleId(vehicleId, sortBy, order);
    }

}
