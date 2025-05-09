package com.switchfully.parkshark.service.mappers;

import com.switchfully.parkshark.domain.Division;
import com.switchfully.parkshark.domain.dtos.DivisionDto;

public class DivisionMapper {

    public DivisionDto mapToDivisionDto(Division division) {
        Long parentId = division.getParentDivision() != null ? division.getParentDivision().getId() : null;

        DivisionDto divisionDto = new DivisionDto();
        divisionDto.setId(division.getId());
        divisionDto.setName(division.getName());
        divisionDto.setOriginalName(division.getOriginalName());
        divisionDto.setDirector(division.getDirector());
        divisionDto.setParentDivisionId(parentId);
        return divisionDto;
    }
}
