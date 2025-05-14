package com.switchfully.parkshark.api;

import com.switchfully.parkshark.domain.dtos.AllocationDto;
import com.switchfully.parkshark.domain.dtos.CreateAllocationDto;
import com.switchfully.parkshark.domain.user.User;
import com.switchfully.parkshark.domain.user.UserRole;
import com.switchfully.parkshark.service.AllocationService;
import com.switchfully.parkshark.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/allocations")
public class AllocationController {

    private final AllocationService allocationService;
    private final AuthenticationService authenticationService;

    @Autowired
    public AllocationController(AllocationService allocationService, AuthenticationService authenticationService) {
        this.allocationService = allocationService;
        this.authenticationService = authenticationService;
    }

    @PostMapping(consumes = "application/json", produces = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public AllocationDto createAllocation(@RequestBody CreateAllocationDto createAllocationDto, @RequestHeader("Authorization") String authHeader) {
        authenticateMemberOrManager(authHeader);
        return allocationService.createAllocation(createAllocationDto);
    }

    @PutMapping(path = "{id}", produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public AllocationDto updateAllocation(@PathVariable Long id, @RequestHeader("Authorization") String authHeader) {
        authenticateMemberOrManager(authHeader);
        return allocationService.stopAllocation(id);
    }

    @GetMapping(produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public List<AllocationDto> getAllAllocations(
            @RequestParam(defaultValue = "ALL") String status,
            @RequestParam(defaultValue = "ASC") String order,
            @RequestParam(defaultValue = "20") int limit,
            @RequestHeader("Authorization") String authHeader) {

        authenticateManager(authHeader);
        return allocationService.getFilteredAllocations(status.toUpperCase(), order.toUpperCase(), limit);
    }

    private void authenticateManager(String authHeader) {
        User user = authenticationService.authenticate(authHeader);
        if (user.getRole() != UserRole.MANAGER) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Manager privileges required");
        }
    }

    private void authenticateMemberOrManager(String authHeader) {
        User user = authenticationService.authenticate(authHeader);
        if (user.getRole() != UserRole.MEMBER && user.getRole() != UserRole.MANAGER) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Member or Manager privileges required");
        }
    }
}
