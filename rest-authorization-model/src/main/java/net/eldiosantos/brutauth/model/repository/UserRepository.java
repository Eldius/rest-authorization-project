package net.eldiosantos.brutauth.model.repository;


import net.eldiosantos.brutauth.model.auth.User;
import net.eldiosantos.brutauth.model.repository.interfaces.Repository;

import java.util.List;

public interface UserRepository extends Repository<User, Long> {
    User findByLogin(String username);

    List<User> findAll(String query);
}
