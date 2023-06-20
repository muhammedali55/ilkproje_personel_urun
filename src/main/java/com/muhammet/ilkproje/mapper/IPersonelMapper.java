package com.muhammet.ilkproje.mapper;

import com.muhammet.ilkproje.dto.request.PersonelSaveRequestDto;
import com.muhammet.ilkproje.repository.entity.Personel;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface IPersonelMapper {

    static IPersonelMapper INSTANCE = Mappers.getMapper(IPersonelMapper.class);

    /**
     * Target  -> Method Name  -> Source
     * @param dto
     * @return
     */
    Personel fromPersonelDto(final PersonelSaveRequestDto dto);


}
