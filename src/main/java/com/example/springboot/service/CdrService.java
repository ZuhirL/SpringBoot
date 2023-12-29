package com.example.springboot.service;

import com.example.springboot.dao.CdrRepository;
import com.example.springboot.dto.CdrDto;
import com.example.springboot.error.exception.CdrNotFoundException;
import com.example.springboot.mapper.CdrMapper;
import com.example.springboot.validator.ValidationService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class CdrService {

    public final static Map<String, String> ORDER_TIME_MAP = new HashMap<>(
            Map.of("startAt", "startTime", "endAt", "endTime")
    );
    public final static String DESC_ORDER = "desc";

    private final CdrRepository cdrRepository;
    private final CdrMapper cdrMapper;
    private final ValidationService validationService;

    public CdrDto createCdr(CdrDto cdrDto) {
        validationService.isTimeOverlapValid(cdrDto);
        cdrRepository.save(cdrMapper.toCdrEntity(cdrDto));
        return cdrDto;
    }

    public CdrDto getById(Long id) {
        return cdrRepository.findById(id)
                .map(e -> cdrMapper.toCdrDto(e))
                .orElseThrow(CdrNotFoundException::new);
    }

    public List<CdrDto> getByVehicleId(String vehicleId, String sortBy, String order) {
        if (ORDER_TIME_MAP.containsKey(sortBy)) {
            if (DESC_ORDER.equals(order)) {
                return cdrRepository.findByVehicleId(vehicleId, Sort.by(ORDER_TIME_MAP.get(sortBy)).descending()).stream()
                        .map(cdrMapper::toCdrDto)
                        .toList();
            }
            return cdrRepository.findByVehicleId(vehicleId, Sort.by(ORDER_TIME_MAP.get(sortBy))).stream()
                    .map(cdrMapper::toCdrDto)
                    .toList();
        } else {
            return cdrRepository.findByVehicleId(vehicleId).stream()
                    .map(cdrMapper::toCdrDto)
                    .toList();
        }
    }

}
