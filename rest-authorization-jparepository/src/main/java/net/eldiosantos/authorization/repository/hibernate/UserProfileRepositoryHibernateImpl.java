package net.eldiosantos.authorization.repository.hibernate;

import net.eldiosantos.authorization.repository.hibernate.base.BaseRepository;
import net.eldiosantos.brutauth.model.auth.User;
import net.eldiosantos.brutauth.model.auth.profile.UserProfile;
import net.eldiosantos.brutauth.model.repository.UserProfileRepository;

import javax.persistence.EntityManager;

public class UserProfileRepositoryHibernateImpl extends BaseRepository<UserProfile, Long> implements UserProfileRepository {

    @Deprecated
    public UserProfileRepositoryHibernateImpl() {
    }

    public UserProfileRepositoryHibernateImpl(EntityManager entityManager) {
        super(entityManager);
    }

    @Override
    public UserProfile getByUser(User user) {
        return getByUser(user.getId());
    }

    @Override
    public UserProfile getByUser(Long userId) {
        return (UserProfile) entityManager.createQuery("select u from UserProfile u where u.id = :userId ")
                .setParameter("userId", userId)
                .getSingleResult();
    }
}
