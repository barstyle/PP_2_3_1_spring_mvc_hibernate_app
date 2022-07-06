package app.services;

import app.models.User;

import java.util.List;

public interface UserService {
    void save(User user);

    List<User> getAllUsers();

    void update(User user);

    void removeUserById(Long id);

    User getUserById(Long id);
}
