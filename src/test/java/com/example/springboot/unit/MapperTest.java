package com.example.springboot.unit;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.example.springboot.dao.CdrEntity;
import com.example.springboot.dto.CdrDto;
import com.example.springboot.mapper.CdrMapper;
import com.example.springboot.mapper.CdrMapperImpl;
import java.math.BigDecimal;
import java.time.Instant;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;

@ExtendWith(MockitoExtension.class)
@DirtiesContext(classMode = ClassMode.BEFORE_EACH_TEST_METHOD)
public class MapperTest {

  @InjectMocks
  CdrMapperImpl cdrMapper;

  private CdrDto getCdrDto() {
    CdrDto cdrDto = new CdrDto();
    cdrDto.setId(8L);
    cdrDto.setSessionIdentification("CDR008");
    cdrDto.setVehicleIdentification("VH008");
    cdrDto.setStartAt(Instant.parse("2023-01-19T10:00:00Z"));
    cdrDto.setEndAt(Instant.parse("2023-01-19T12:00:00Z"));
    cdrDto.setAmount(new BigDecimal("10.00"));
    return cdrDto;
  }

  private CdrEntity getCdrEntity() {
    CdrEntity cdrEntity = new CdrEntity();
    cdrEntity.setId(8L);
    cdrEntity.setSessionId("CDR008");
    cdrEntity.setVehicleId("VH008");
    cdrEntity.setStartTime(Instant.parse("2023-01-19T10:00:00Z"));
    cdrEntity.setEndTime(Instant.parse("2023-01-19T12:00:00Z"));
    cdrEntity.setTotalCost(new BigDecimal("10.00"));
    return cdrEntity;
  }

  @Test
  public void mapToCdrDto() {
    CdrDto cdrDto = cdrMapper.toCdrDto(getCdrEntity());
    assertEquals(cdrDto, getCdrDto());
  }

  @Test
  public void mapToCdrEntity() {
    CdrEntity cdrEntity = cdrMapper.toCdrEntity(getCdrDto());
    assertEquals(cdrEntity, getCdrEntity());
  }


}
