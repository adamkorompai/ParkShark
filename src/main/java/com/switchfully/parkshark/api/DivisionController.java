package com.switchfully.parkshark.api;

import com.switchfully.parkshark.domain.dtos.CreateDivisionDto;
import com.switchfully.parkshark.domain.dtos.DivisionDto;
import com.switchfully.parkshark.domain.user.User;
import com.switchfully.parkshark.domain.user.UserRole;
import com.switchfully.parkshark.repository.DivisionRepository;
import com.switchfully.parkshark.service.AuthenticationService;
import com.switchfully.parkshark.service.DivisionService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/divisions")
public class DivisionController {
    private final DivisionService divisionService;
    private final AuthenticationService authenticationService;

    public DivisionController(DivisionService divisionService, AuthenticationService authenticationService) {
        this.divisionService = divisionService;
        this.authenticationService = authenticationService;
    }

    @PostMapping
    public DivisionDto createDivision(@RequestBody CreateDivisionDto createDivisionDto, @RequestHeader("Authorization") String authHeader) {

        authenticateManager(authHeader);
        return divisionService.createDivision(createDivisionDto);
    }

    @GetMapping
    public List<DivisionDto> getAllDivisions(@RequestHeader("Authorization") String authHeader) {

        authenticateManager(authHeader);
        return divisionService.getAllDivisions();
    }

    @GetMapping("/{id}")
    public DivisionDto getDivisionById(@PathVariable Long id, @RequestHeader("Authorization") String authHeader) {

        authenticateManager(authHeader);
        return divisionService.getDivisionById(id);
    }

    private void authenticateManager(String authHeader) {
        User user = authenticationService.authenticate(authHeader);
        if (user.getRole() != UserRole.MANAGER) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Manager privileges required");
        }
    }
}
