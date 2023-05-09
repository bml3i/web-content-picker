package bi.leo.picker.service;

import bi.leo.picker.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    public Optional<User> getUserById(Long id);

    public List<User> getUsers();

    public User save(User user);
}
