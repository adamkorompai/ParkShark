package com.switchfully.parkshark.api;

import com.switchfully.parkshark.domain.dtos.CreateUserDTO;
import com.switchfully.parkshark.domain.dtos.UserDTO;
import com.switchfully.parkshark.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

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
}
