package com.codifica.compti.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import com.codifica.compti.dto.RegisterDTO;
import com.codifica.compti.models.user.User;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapper {
    User toModel(RegisterDTO registerDTO);
}
