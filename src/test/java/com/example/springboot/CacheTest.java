package com.example.springboot;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.example.springboot.dto.CdrDto;
import com.example.springboot.error.exception.CdrNotFoundException;
import com.example.springboot.service.CdrService;
import java.math.BigDecimal;
import java.time.Instant;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cache.CacheManager;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;

@SpringBootTest
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
class CacheTest {

  @Autowired
  CacheManager cacheManager;
  @Autowired
  CdrService cdrService;

  private CdrDto getCachedBook(Long id) {
    return cacheManager.getCache("cdr.by-id").get(id, CdrDto.class);
  }

  private CdrDto getCdrDto1() {
    CdrDto cdrDto = new CdrDto();
    cdrDto.setId(1L);
    cdrDto.setSessionIdentification("CDR001");
    cdrDto.setVehicleIdentification("VH001");
    cdrDto.setStartAt(Instant.parse("2023-05-19T10:00:00Z"));
    cdrDto.setEndAt(Instant.parse("2023-05-19T12:00:00Z"));
    cdrDto.setAmount(new BigDecimal("25.50"));
    return cdrDto;
  }

  private CdrDto getCdrDtoNew() {
    CdrDto cdrDto = new CdrDto();
    cdrDto.setSessionIdentification("CDR008");
    cdrDto.setVehicleIdentification("VH008");
    cdrDto.setStartAt(Instant.parse("2023-01-19T10:00:00Z"));
    cdrDto.setEndAt(Instant.parse("2023-01-19T12:00:00Z"));
    cdrDto.setAmount(new BigDecimal("10.00"));
    return cdrDto;
  }

  @Test
  void cacheGetById() {
    assertEquals(null, getCachedBook(1L));
    cdrService.getById(1L);
    assertEquals(getCdrDto1(), getCachedBook(1L));
  }

  @Test
  void cacheCreateAndGetById() {
    assertThrows(CdrNotFoundException.class, () -> cdrService.getById(8L));
    assertEquals(null, getCachedBook(8L));
    CdrDto cdrDto = getCdrDtoNew();
    cdrService.createCdr(cdrDto);
    cdrService.getById(8L);
    assertEquals(cdrDto, getCachedBook(8L));
  }

}
