package com.example.springboot.mapper;

import com.example.springboot.dao.CdrEntity;
import com.example.springboot.dto.CdrDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

@Mapper(componentModel = SPRING)
public interface CdrMapper {

    @Mapping(source = "sessionId", target = "sessionIdentification")
    @Mapping(source = "vehicleId", target = "vehicleIdentification")
    @Mapping(source = "startTime", target = "startAt")
    @Mapping(source = "endTime", target = "endAt")
    @Mapping(source = "totalCost", target = "amount")
    CdrDto toCdrDto(CdrEntity cdrEntity);

    @Mapping(source = "sessionIdentification", target = "sessionId")
    @Mapping(source = "vehicleIdentification", target = "vehicleId")
    @Mapping(source = "startAt", target = "startTime")
    @Mapping(source = "endAt", target = "endTime")
    @Mapping(source = "amount", target = "totalCost")
    CdrEntity toCdrEntity(CdrDto cdrDto);

}
