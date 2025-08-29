package com.dev.common_dto.mapper;

import org.modelmapper.ModelMapper;

import lombok.Builder;

public class DtoEntityMapper<D, E> {

	    private final ModelMapper modelMapper;

	    public DtoEntityMapper() {
	        this.modelMapper = new ModelMapper();
	    }

	    public E convertFromDtoToEntity(D dto, Class<E> entityClass) {
	        return modelMapper.map(dto, entityClass);
	    }

	    public D convertFromEntityToDto(E entity, Class<D> dtoClass) {
	        return modelMapper.map(entity, dtoClass);
	    }

	    @Builder
	    private static <D, E> DtoEntityMapper<D, E> createDtoEntityMapper() {
	        return new DtoEntityMapper<>();
	    }

	    public static <D, E> DtoEntityMapper<D, E> getDtoEntityMapper() {
	        return createDtoEntityMapper();
	    }
	}
