package net.eldiosantos.brutauth.model.repository;


import net.eldiosantos.brutauth.model.auth.User;
import net.eldiosantos.brutauth.model.auth.profile.UserProfile;
import net.eldiosantos.brutauth.model.repository.interfaces.Repository;

public interface UserProfileRepository extends Repository<UserProfile, Long> {
    public UserProfile getByUser(User user);
    public UserProfile getByUser(Long userId);
}
