package com.switchfully.parkshark.api;

import com.switchfully.parkshark.domain.Allocation;
import com.switchfully.parkshark.domain.dtos.AllocationDto;
import com.switchfully.parkshark.domain.dtos.CreateAllocationDto;
import com.switchfully.parkshark.service.AllocationService;
import com.switchfully.parkshark.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

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
    public AllocationDto createAllocation(@RequestBody CreateAllocationDto createAllocationDto) {
        return allocationService.createAllocation(createAllocationDto);
    }
}
