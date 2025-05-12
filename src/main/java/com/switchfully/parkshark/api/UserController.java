package com.switchfully.parkshark.api;

import com.switchfully.parkshark.domain.dtos.CreateUserDTO;
import com.switchfully.parkshark.domain.dtos.MemberOverviewDTO;
import com.switchfully.parkshark.domain.dtos.UserDTO;
import com.switchfully.parkshark.domain.user.User;
import com.switchfully.parkshark.domain.user.UserRole;
import com.switchfully.parkshark.service.AuthenticationService;
import com.switchfully.parkshark.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;
    private final AuthenticationService authService;

    public UserController(UserService userService, AuthenticationService authService) {
        this.userService = userService;
        this.authService = authService;
    }

    @PostMapping(consumes = "application/json", produces = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public UserDTO createUser(@RequestBody CreateUserDTO dto) {
        return userService.registerMember(dto);
    }

    @GetMapping(produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public List<MemberOverviewDTO> getUserOverview(@RequestHeader   ("Authorization") String authHeader) {
        authenticateManager(authHeader);
        return userService.getAllUsers();
    }

    @GetMapping(path = "/{id}", produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public UserDTO getUserById(@PathVariable("id") Long id, @RequestHeader("Authorization") String authHeader) {
        authenticateManager(authHeader);
        return userService.getUserById(id);
    }

    private void authenticateManager(String authHeader) {
        User user = authService.authenticate(authHeader);
        if(user.getRole() != UserRole.MANAGER) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Manager privileges required");
        }
    }
}
