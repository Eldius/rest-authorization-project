package net.eldiosantos.authorization.repository;


import net.eldiosantos.authorization.model.auth.User;
import net.eldiosantos.authorization.repository.interfaces.Repository;

import java.util.List;

public interface UserRepository extends Repository<User, Long> {
    User findByLogin(String username);

    List<User> findAll(String query);
}
