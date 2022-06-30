package app.Services;

import app.Model.User;

import java.util.List;

public interface UserService {
    void save(User user);

    List<User> getAllUsers();

    void update(User user);

    void remove(User user);
}
