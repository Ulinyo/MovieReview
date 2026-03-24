package kg.attractor.movie_review.controller;

import kg.attractor.movie_review.dto.UserDto;
import kg.attractor.movie_review.exception.UserNotFoundException;
import kg.attractor.movie_review.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@AllArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping
    public List<UserDto> getUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("{id}")
    public UserDto findById(@PathVariable int id) throws UserNotFoundException {
        return userService.findById(id);
    }
}
