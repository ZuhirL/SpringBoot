package com.example.springboot.validator;

import com.example.springboot.dao.CdrEntity;
import com.example.springboot.dao.CdrRepository;
import com.example.springboot.dto.CdrDto;
import com.example.springboot.error.exception.TimeOverlapException;
import com.example.springboot.mapper.CdrMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ValidationService {

  private final CdrRepository cdrRepository;
  private final CdrMapper cdrMapper;

  public void isTimeOverlapValid(CdrDto cdrDto) {
    Page<CdrEntity> cdrEntityPage = cdrRepository.findByVehicleId(cdrDto.getVehicleIdentification(),
        PageRequest.of(0, 1, Sort.by("startTime").descending()));
    if (!cdrEntityPage.isEmpty() && !cdrEntityPage.getContent().isEmpty() &&
        cdrDto.getStartAt().toEpochMilli() < cdrMapper.toCdrDto(
            cdrEntityPage.getContent().getFirst()).getEndAt().toEpochMilli()) {
      throw new TimeOverlapException();
    }
  }

}
