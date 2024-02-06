package com.example.springboot.service;


import com.example.springboot.dao.CdrEntity;
import com.example.springboot.dao.CdrRepository;
import com.example.springboot.dto.CdrDto;
import com.example.springboot.error.exception.CdrNotFoundException;
import com.example.springboot.mapper.CdrMapper;
import com.example.springboot.validator.ValidationService;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CdrService {

  private static final Map<String, String> fieldMappings = Map.of(
      "id", "id",
      "sessionIdentification", "sessionId",
      "vehicleIdentification", "vehicleId",
      "startAt", "startTime",
      "endAt", "endTime",
      "amount", "totalCost");

  private final CdrRepository cdrRepository;
  private final CdrMapper cdrMapper;
  private final ValidationService validationService;

  public CdrDto createCdr(CdrDto cdrDto) {
    validationService.isTimeOverlapValid(cdrDto);
    CdrEntity cdrEntity = cdrRepository.save(cdrMapper.toCdrEntity(cdrDto));
    cdrDto.setId(cdrEntity.getId());
    return cdrDto;
  }

  @Cacheable("cdr.by-id")
  public CdrDto getById(Long id) {
    return cdrRepository.findById(id)
        .map(cdrMapper::toCdrDto)
        .orElseThrow(() -> new CdrNotFoundException(id));
  }

  public Page<CdrDto> getByVehicleId(String vehicleId, int page, int size, String sort,
      String order) {
    return cdrRepository.findByVehicleId(vehicleId,
            PageRequest.of(page, size,
                Sort.by(Direction.fromString(order), fieldMappings.getOrDefault(sort, sort))))
        .map(cdrMapper::toCdrDto);
  }

}