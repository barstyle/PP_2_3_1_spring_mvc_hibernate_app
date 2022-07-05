package app.services;

import app.models.User;

import java.util.List;

public interface UserService {
    void save(User user);

    List<User> getAllUsers();

    void update(User user);

    void remove(User user);

    User getUserById(Long id);
}
