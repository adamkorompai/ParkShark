package com.switchfully.parkshark.api;

import com.switchfully.parkshark.domain.dtos.CreateUserDTO;
import com.switchfully.parkshark.domain.dtos.MemberOverviewDTO;
import com.switchfully.parkshark.domain.dtos.UserDTO;
import com.switchfully.parkshark.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(consumes = "application/json", produces = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public UserDTO createUser(@RequestBody CreateUserDTO dto) {
        return userService.registerMember(dto);
    }

    @GetMapping(produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public List<MemberOverviewDTO> getUserOverview() {
        return userService.getAllUsers();
    }

    @GetMapping(path = "/{id}", produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public UserDTO getUserById(@PathVariable("id") Long id) {
        return userService.getUserById(id);
    }
}
