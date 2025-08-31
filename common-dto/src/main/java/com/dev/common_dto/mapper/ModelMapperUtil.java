package com.dev.common_dto.mapper;

import org.modelmapper.ModelMapper;

public class ModelMapperUtil {

    private static final ModelMapper modelMapper = new ModelMapper();

    private ModelMapperUtil() {
    	
    }

    public static ModelMapper getModelMapper() {
        return modelMapper;
    }
}
