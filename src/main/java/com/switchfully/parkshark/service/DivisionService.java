package com.switchfully.parkshark.service;

import com.switchfully.parkshark.domain.Division;
import com.switchfully.parkshark.domain.dtos.CreateDivisionDto;
import com.switchfully.parkshark.domain.dtos.DivisionDto;
import com.switchfully.parkshark.repository.DivisionRepository;
import com.switchfully.parkshark.service.mappers.DivisionMapper;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class DivisionService {

    private final DivisionRepository divisionRepository;
    private final DivisionMapper divisionMapper;

    @Autowired
    public DivisionService(DivisionRepository divisionRepository) {
        this.divisionRepository = divisionRepository;
        this.divisionMapper = new DivisionMapper();
    }

    public DivisionDto createDivision(CreateDivisionDto createDivisionDto) {
        Division parent = null;
        if (createDivisionDto.getParentDivisionId() != null) {
            Optional<Division> optionalParent = divisionRepository.findById(createDivisionDto.getParentDivisionId());
            if (optionalParent.isEmpty()) {
                throw new RuntimeException("Parent division does not exist");
            }
            parent = optionalParent.get();
        }
        Division division = new Division(
                createDivisionDto.getName(),
                createDivisionDto.getOriginalName(),
                createDivisionDto.getDirector(),
                parent
        );

        Division savedDivision = divisionRepository.save(division);

        return divisionMapper.mapToDivisionDto(savedDivision);
    }

    public List<DivisionDto> getAllDivisions() {
        return divisionRepository.findAll().stream().map(divisionMapper::mapToDivisionDto).collect(Collectors.toList());
    }

    public DivisionDto getDivisionById(Long id) {
        Division division = divisionRepository.findById(id).orElseThrow(() -> new RuntimeException("Division not found"));
        return divisionMapper.mapToDivisionDto(division);
    }
}
