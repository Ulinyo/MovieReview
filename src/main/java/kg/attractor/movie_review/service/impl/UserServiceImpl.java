package kg.attractor.movie_review.service.impl;

import kg.attractor.movie_review.dao.UserDao;
import kg.attractor.movie_review.dto.UserDto;
import kg.attractor.movie_review.exception.UserNotFoundException;
import kg.attractor.movie_review.service.UserService;
import lombok.RequiredArgsConstructor;
import kg.attractor.movie_review.model.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserDao userDao;

    @Override
    public List<UserDto> getAllUsers() {
        List<User> users = userDao.getAllUsers();

        List<UserDto> result = users.stream()
                .map(u -> UserDto.builder()
                        .id(u.getId())
                        .username(u.getUsername())
                        .password(u.getPassword())
                        .build())
                .toList();

        return result;
    }

    @Override
    public UserDto findById(int id) throws UserNotFoundException {
        User user = userDao.findByIdName(id)
                .orElseThrow(UserNotFoundException::new);

        return UserDto.builder()
                .id(user.getId())
                .username(user.getUsername())
                .password(user.getPassword())
                .build();
    }
}
