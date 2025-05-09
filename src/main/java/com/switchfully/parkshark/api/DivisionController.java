package com.switchfully.parkshark.api;

import com.switchfully.parkshark.domain.dtos.CreateDivisionDto;
import com.switchfully.parkshark.domain.dtos.DivisionDto;
import com.switchfully.parkshark.repository.DivisionRepository;
import com.switchfully.parkshark.service.DivisionService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/divisions")
public class DivisionController {
    private final DivisionService divisionService;

    public DivisionController(DivisionService divisionService) {
        this.divisionService = divisionService;
    }

    @PostMapping
    public DivisionDto createDivision(@RequestBody CreateDivisionDto createDivisionDto) {
        return divisionService.createDivision(createDivisionDto);
    }

    @GetMapping
    public List<DivisionDto> getAllDivisions() {
        return divisionService.getAllDivisions();
    }

    @GetMapping("/{id}")
    public DivisionDto getDivisionById(@PathVariable Long id) {
        return divisionService.getDivisionById(id);
    }
}
