package com.example.springboot.service;

import com.example.springboot.dao.CdrEntity;
import com.example.springboot.dao.CdrRepository;
import com.example.springboot.dto.CdrDto;
import com.example.springboot.error.exception.CdrNotFoundException;
import com.example.springboot.mapper.CdrMapper;
import com.example.springboot.validator.ValidationService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CdrService {

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
        .orElseThrow(CdrNotFoundException::new);
  }

  public List<CdrDto> getByVehicleId(String vehicleId, Pageable pageable) {
    return cdrRepository.findByVehicleId(vehicleId, pageable).stream()
        .map(cdrMapper::toCdrDto)
        .toList();
  }

}
