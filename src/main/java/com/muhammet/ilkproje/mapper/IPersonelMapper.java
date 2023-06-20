package com.muhammet.ilkproje.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface IPersonelMapper {

    IPersonelMapper INSTANCE = Mappers.getMapper(IPersonelMapper.class);



}
