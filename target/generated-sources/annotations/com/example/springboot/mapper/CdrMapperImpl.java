package com.example.springboot.mapper;

import com.example.springboot.dao.CdrEntity;
import com.example.springboot.dto.CdrDto;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-12-17T23:59:36+0100",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.7 (Oracle Corporation)"
)
@Component
public class CdrMapperImpl implements CdrMapper {

    @Override
    public CdrDto toCdrDto(CdrEntity cdrEntity) {
        if ( cdrEntity == null ) {
            return null;
        }

        CdrDto cdrDto = new CdrDto();

        cdrDto.setSessionIdentification( cdrEntity.getSessionId() );
        cdrDto.setVehicleIdentification( cdrEntity.getVehicleId() );
        cdrDto.setStartAt( cdrEntity.getStartTime() );
        cdrDto.setEndAt( cdrEntity.getEndTime() );
        cdrDto.setAmount( cdrEntity.getTotalCost() );
        cdrDto.setId( cdrEntity.getId() );

        return cdrDto;
    }

    @Override
    public CdrEntity toCdrEntity(CdrDto cdrDto) {
        if ( cdrDto == null ) {
            return null;
        }

        CdrEntity cdrEntity = new CdrEntity();

        cdrEntity.setSessionId( cdrDto.getSessionIdentification() );
        cdrEntity.setVehicleId( cdrDto.getVehicleIdentification() );
        cdrEntity.setStartTime( cdrDto.getStartAt() );
        cdrEntity.setEndTime( cdrDto.getEndAt() );
        cdrEntity.setTotalCost( cdrDto.getAmount() );
        cdrEntity.setId( cdrDto.getId() );

        return cdrEntity;
    }
}
