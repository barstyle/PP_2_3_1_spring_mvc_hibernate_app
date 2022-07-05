package app.dao;

import app.models.User;

import java.util.List;

// на которую выводятся все юзеры с возможностью добавлять, удалять и изменять юзера.
public interface UserDao {
    void save(User user);

    List<User> getAllUsers();

    void update(User user);

    void remove(User user);

    User getUserById(Long id);

}
