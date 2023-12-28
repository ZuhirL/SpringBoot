package com.example.springboot.controller;

import com.example.springboot.dto.CdrDto;
import com.example.springboot.service.CdrService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("cdr")
@RequiredArgsConstructor
public class CdrController {

    private final CdrService cdrService;

    @PostMapping
    public CdrDto createCdr(@Valid @RequestBody CdrDto cdrDto) {
        return cdrService.createCdr(cdrDto);
    }

    @GetMapping("id/{id}")
    public CdrDto getById(@PathVariable Long id) {
        return cdrService.getById(id);
    }

    @GetMapping("vehicle-identification/{vehicleId}")
    public List<CdrDto> getByVehicleIdentification(@PathVariable String vehicleId,
                                                   @RequestParam(required = false) String sortBy,
                                                   @RequestParam(required = false) String order) {
        return cdrService.getByVehicleId(vehicleId, sortBy, order);
    }

}
