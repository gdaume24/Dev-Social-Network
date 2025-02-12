package com.network.mapper;

import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

import com.network.dto.ThemeDto;
import com.network.models.Theme;

@Component
@Mapper(componentModel = "spring")
public interface ThemeMapper extends EntityMapper<ThemeDto, Theme> {
    
}
